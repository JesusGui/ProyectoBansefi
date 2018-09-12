package mx.babel.bansefi.banksystem.base.backends.anotaciones;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.busqueda.AnotacionClienteBusquedaBean;
import mx.babel.bansefi.banksystem.base.beans.busqueda.AnotacionCuentaBusquedaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.AnotacionBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.webservices.consultalistaanotaciones.ConsultaListaAnotacionesServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultalistaanotaciones.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultalistaanotaciones.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultalistaanotaciones.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.webservices.consultalistaanotaciones.ResponseBansefi.OTRANCONSANOTMTRNO.TRANCONSANOTMEVTZ.ANANTCNE;
import mx.babel.bansefi.banksystem.base.webservices.consultalistaanotaciones.ResponseBansefi.OTRANCONSANOTMTRNO.TRANCONSANOTMEVTZ.ANCLAVEPADREV;
import mx.babel.bansefi.banksystem.base.wrappers.AnotacionWrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back End para el listado de anotaciones relacionadas a un cliente o a una cuenta
 * @author mario.montesdeoca
 *
 */
@Component
public class ConsultaListaAnotacionesBackEnd extends BackEndBean{

	private static final long serialVersionUID = 8203110958472279207L;
	
	private static final Logger LOGGER = LogManager.getLogger(ConsultaListaAnotacionesBackEnd.class.getName());
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	@Autowired
	CatalogoUtils catalogoUtils;
	
	@Autowired
	AnotacionWrapper anotacionWrapper;
	
	private static final Integer EVENT_CD_CLIENTE = 3;
	private static final Integer EVENT_CD_CUENTA = 1;
	
	/**
	 * Mètodo para consultar las anotaciones de un cliente por medio de un ws
	 * @param idInterna del cliente
	 * @param activas Si queremos solo las anotaciones activas
	 * @param busqueda indicador de que se esta accediendo desde la busqueda
	 * @return lista de anotaciones
	 */
	public List<Object> ejecutarTRN(int idInterna, boolean activas, boolean busqueda){
		List <AnotacionBean> anotaciones = this.ejecutarTRN(idInterna,activas);
		List <Object> resultado = new ArrayList<>();
		for (int i=0;i<anotaciones.size();i++){
			AnotacionClienteBusquedaBean anot = new AnotacionClienteBusquedaBean();
			anot.setNumero(anotaciones.get(i).getNumero());
			anot.setTipo(anotaciones.get(i).getTipo());
			anot.setSubcodigo(anotaciones.get(i).getSubcodigo());			
			
			try{
				if (anotaciones.get(i).getPrioridad() !=null){
					anot.setPrioridad(catalogoUtils.getCatalogoBean(CatalogoEnum.PRIORIDAD_ANOTACION,anotaciones.get(i).getPrioridad()).getDescripcionL());
				}
			}catch (ControlableException ce){
				LOGGER.debug("Error al intentar obtener la descripcion de la prioridad de anotacion a partir del codigo: "+ anotaciones.get(i).getPrioridad(),ce);				
			}
			try{
				if (anot.getSubcodigo() !=null){
					anot.setSubCodigoDescripcion(catalogoUtils.getCatalogoBean(CatalogoEnum.TP_ANTCN,anot.getSubcodigo()).getDescripcionL());
				}
			}catch (ControlableException ce){
				LOGGER.debug("Error al intentar obtener la descripcion del subcodigo de anotacion a partir del codigo: "+ anot.getSubcodigo(),ce);				
			}
			anot.setDescripcionCorta(anotaciones.get(i).getDescripcionCorta());
			
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			
			if (anotaciones.get(i).getFechaInicio() != null){
				anot.setFechaInicio(df.format(anotaciones.get(i).getFechaInicio()));
			}
			if (anotaciones.get(i).getFechaCierre() != null){
				anot.setFechaCierre(df.format(anotaciones.get(i).getFechaCierre()));
			}			
			
			// Tratamos la fecha de anotaciones para visualizarla a vacio si viene como 31/12/9999
			if (anot.getFechaCierre() !=null && ConstantesFuncionales.MAX_FECHA_FIN.equals(anot.getFechaCierre())){
				anot.setFechaCierre(null);
			}
			
			anot.setIdInterna(idInterna);
			
			resultado.add(anot);
		}
		return resultado;
	}
	
	/**
	 * Mètodo para consultar las anotaciones de un cliente por medio de un ws
	 * @param numCuenta numero de cuenta
	 * @param activas Si queremos solo las anotaciones activas
	 * @param busqueda indicador de que se esta accediendo desde la busqueda
	 * @return lista de anotaciones
	 */
	public List<Object> ejecutarTRN(long numCuenta, boolean activas, boolean busqueda){
		List <AnotacionBean> anotaciones = this.ejecutarTRN(numCuenta,activas);
		List <Object> resultado = new ArrayList<>();
		for (int i=0;i<anotaciones.size();i++){
			AnotacionCuentaBusquedaBean anot = new AnotacionCuentaBusquedaBean();
			anot.setNumero(anotaciones.get(i).getNumero());
			anot.setTipo(anotaciones.get(i).getTipo());
			anot.setSubcodigo(anotaciones.get(i).getSubcodigo());
			
			try{
				if (anotaciones.get(i).getPrioridad() !=null){
					anot.setPrioridad(catalogoUtils.getCatalogoBean(CatalogoEnum.PRIORIDAD_ANOTACION,anotaciones.get(i).getPrioridad()).getDescripcionL());
				}
			}catch (ControlableException ce){
				LOGGER.debug("Error al intentar obtener la descripcion de la prioridad de anotacion a partir del codigo: "+ anotaciones.get(i).getPrioridad(),ce);				
			}
			try{
				if (anot.getSubcodigo() !=null){
					anot.setSubCodigoDescripcion(catalogoUtils.getCatalogoBean(CatalogoEnum.TP_ANTCN,anot.getSubcodigo()).getDescripcionL());
				}
			}catch (ControlableException ce){
				LOGGER.debug("Error al intentar obtener la descripcion del subcodigo de anotacion a partir del codigo: "+ anot.getSubcodigo(),ce);				
			}
			anot.setDescripcionCorta(anotaciones.get(i).getDescripcionCorta());
			
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			
			if (anotaciones.get(i).getFechaInicio() != null){
				anot.setFechaInicio(df.format(anotaciones.get(i).getFechaInicio()));
			}
			if (anotaciones.get(i).getFechaCierre() != null){
				anot.setFechaCierre(df.format(anotaciones.get(i).getFechaCierre()));
			}			
			
			// Tratamos la fecha de anotaciones para visualizarla a vacio si viene como 31/12/9999
			if (anot.getFechaCierre() !=null && ConstantesFuncionales.MAX_FECHA_FIN.equals(anot.getFechaCierre())){
				anot.setFechaCierre(null);
			}
			
			anot.setNumeroCuenta(numCuenta);
			
			resultado.add(anot);
		}
		return resultado;
	}
		
	
	/**
	 * Mètodo para consultar las anotaciones de un cliente por medio de un ws
	 * @param idInterna del cliente
	 * @param activas Si queremos solo las anotaciones activas
	 * @return lista de anotaciones
	 */
	public List<AnotacionBean> ejecutarTRN(int idInterna,boolean activas){
		Ejecutar.ITRANCONSANOTMTRNI contexto = initPeticion();
		preparaPeticion(contexto, idInterna);
		return construirListado(contexto,activas);
	}
	
	/**
	 * Mètodo para consultar las anotaciones de una cuenta por medio de un ws
	 * @param numCuenta numero de cuenta
	 * @param activas Si queremos solo las anotaciones activas
	 * @return lista de anotaciones
	 */
	public List<AnotacionBean> ejecutarTRN(long numCuenta,boolean activas){		
		Ejecutar.ITRANCONSANOTMTRNI contexto = initPeticion();
		preparaPeticion(contexto, numCuenta);
		return construirListado(contexto,activas);
	}
	
	/**
	 * Mètodo para construir la lista de anotaciones
	 * @param contexto objeto peticiòn al ws
	 * @param activas indicador de si solo se quieren anotaciones activas
	 * @return lista de anotaciones
	 */
	private List<AnotacionBean> construirListado(Ejecutar.ITRANCONSANOTMTRNI contexto, boolean activas){
		List<AnotacionBean> anotaciones = new ArrayList<AnotacionBean>();
		
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return anotaciones;
			}
		}
		
		return getAnotaciones(respuesta.getResponseBansefi(),activas);
	}
	
	/**
	 * Mètodo que inicializa los beans de anotaciones y construye la lista
	 * @param respuesta objeto respuesta del ws
	 * @param activas indicador de si solo se quieren anotaciones activas
	 * @return lista de anotaciones
	 */
	private List<AnotacionBean> getAnotaciones(ResponseBansefi respuesta,boolean activas){
		List<AnotacionBean> anotaciones = new ArrayList<AnotacionBean>();
		Map<Long, ArrayList<AnotacionBean>> anotacionesRespuesta = new HashMap<Long, ArrayList<AnotacionBean>>();		
		if(verificaListaAnotaciones(respuesta)){
			for(int i = 0; i < respuesta.getOTRANCONSANOTMTRNO().getNUMBEROFRECORDS(); i++){
				ANANTCNE anotacion = respuesta.getOTRANCONSANOTMTRNO().getTRANCONSANOTMEVTZ().getANANTCNE().get(i);
				ANCLAVEPADREV anotacionPadre = respuesta.getOTRANCONSANOTMTRNO().getTRANCONSANOTMEVTZ().getANCLAVEPADREV().get(i);
				if(anotacionPadre.getNUMEROANTCN() == 0L){
					if (!activas){
						anotaciones.add(anotacionWrapper.wrappBean(anotacion));
					}else{
						String estadoAnotacion = anotacion.getCODECVANTCN() != null ? anotacion.getCODECVANTCN().trim() : null;
						if (estadoAnotacion != null && estadoAnotacion.equals(ConstantesFuncionales.COD_ESTADO_ANOTACION_ACTIVA)){
							anotaciones.add(anotacionWrapper.wrappBean(anotacion));
						}
					}
					
				}else{
					if(anotacionesRespuesta.get(anotacionPadre.getNUMEROANTCN()) == null){
						anotacionesRespuesta.put(anotacionPadre.getNUMEROANTCN(), new ArrayList<AnotacionBean>());
					}
					anotacionesRespuesta.get(anotacionPadre.getNUMEROANTCN()).add(anotacionWrapper.wrappBean(anotacion));
				}
			}
			for (AnotacionBean anotacionBean : anotaciones) {
				// Incluimos la logica de front para montar el valor de catalogo y poder obtener el tipo correcto.
				if (anotacionBean.getTipo() !=null && anotacionBean.getSubcodigo() !=null){
					anotacionBean.setSubcodigo(anotacionBean.getTipo()+anotacionBean.getSubcodigo());
				}
				if(anotacionesRespuesta.get(anotacionBean.getNumero()) != null){
					anotacionBean.setRespuestas(anotacionesRespuesta.get(anotacionBean.getNumero()));
				}
			}
		}
		
		return anotaciones;
	}
	
	/**
	 * Función para ejecutar el servicio web de consulta de representante legal 
	 * y obtener su respuesta.
	 * 
	 * @param contexto Objeto para envío a servicio web.
	 * @return Respuesta del servicio web
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRANCONSANOTMTRNI contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaListaAnotacionesServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta de "
					+ "anotaciones.", e);
		}
		return respuesta;
	}
	
	/**
	 * Función que verifica que la lista de personas de la respuesta del servicio web no sea vacía.
	 * 
	 * @param response Respuesta Bansefi con la lista de personas
	 * @return <code>true</code> sí la lista contiene por lo menos una persona.
	 */
	private Boolean verificaListaAnotaciones(ResponseBansefi response) {
		Boolean noNulo =false;
		if(response != null && response.getOTRANCONSANOTMTRNO() != null 
				&& response.getOTRANCONSANOTMTRNO().getTRANCONSANOTMEVTZ() != null
				&& !response.getOTRANCONSANOTMTRNO().getTRANCONSANOTMEVTZ().getANANTCNE().isEmpty()){
			noNulo = true;
		}
		return noNulo;
	}
			
	/**
	 * Mètodo para definir los filtros de anotaciones de cliente
	 * @param contexto objeto peticiòn
	 * @param idInterna del cliente
	 */
	private void preparaPeticion(Ejecutar.ITRANCONSANOTMTRNI contexto, int idInterna){
		Ejecutar.ITRANCONSANOTMTRNI.TRANCONSANOTMEVTY.ANANTCNRLPERSP cliente =
				new Ejecutar.ITRANCONSANOTMTRNI.TRANCONSANOTMEVTY.ANANTCNRLPERSP();
		super.initialize(cliente);
		cliente.setCODNRBEEN(super.getEntidad());
		cliente.setIDINTERNOPE(idInterna);
		
		contexto.setEVENTCD(EVENT_CD_CLIENTE);
		contexto.getTRANCONSANOTMEVTY().setANANTCNRLPERSP(cliente);
	}
	
	/**
	 * Mètodo para definir los filtros de anotaciones de cuenta
	 * @param contexto objeto peticiòn
	 * @param numCuenta numero de cuenta
	 */
	private void preparaPeticion(Ejecutar.ITRANCONSANOTMTRNI contexto, long numCuenta){
		Ejecutar.ITRANCONSANOTMTRNI.TRANCONSANOTMEVTY.ANANTCNRLACP cuenta =
				new Ejecutar.ITRANCONSANOTMTRNI.TRANCONSANOTMEVTY.ANANTCNRLACP();
		super.initialize(cuenta);
		cuenta.setCODNRBEEN(super.getEntidad());
		cuenta.setNUMSECAC(numCuenta);
		
		contexto.setEVENTCD(EVENT_CD_CUENTA);
		contexto.getTRANCONSANOTMEVTY().setANANTCNRLACP(cuenta);
	}
	
	/**
	 * Mètodo para construir la petición al ws
	 * @return objeto de petición
	 */
	private Ejecutar.ITRANCONSANOTMTRNI initPeticion(){
		Ejecutar.ITRANCONSANOTMTRNI contexto = new Ejecutar.ITRANCONSANOTMTRNI();
		Ejecutar.ITRANCONSANOTMTRNI.STDTRNIPARMV cuerpoContexto = 
				new Ejecutar.ITRANCONSANOTMTRNI.STDTRNIPARMV();
		Ejecutar.ITRANCONSANOTMTRNI.TRANCONSANOTMEVTY detalle =
				new Ejecutar.ITRANCONSANOTMTRNI.TRANCONSANOTMEVTY();
		contexto.setSTDTRNIPARMV(cuerpoContexto);
		contexto.setTRANCONSANOTMEVTY(detalle);
		super.initialize(contexto);
		
		for (String tipo : ConstantesFuncionales.TIPOS_ANOTACION) {
			Ejecutar.ITRANCONSANOTMTRNI.TRANCONSANOTMEVTY.ANANTCNRLOPCV tipoAnotacion = 
					new Ejecutar.ITRANCONSANOTMTRNI.TRANCONSANOTMEVTY.ANANTCNRLOPCV();
			tipoAnotacion.setCODANTCN(tipo);
			detalle.getANANTCNRLOPCV().add(tipoAnotacion);
		}
		
		cuerpoContexto.setCODTX(CodTxConstants.COD_TX_TR_AN_CONS_ANOT_M_TRN);
		cuerpoContexto.setIDINTERNOTERMTN(super.getTerminal());

		return contexto;
	}
}
