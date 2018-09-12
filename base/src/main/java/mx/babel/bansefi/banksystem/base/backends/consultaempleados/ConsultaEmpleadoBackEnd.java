package mx.babel.bansefi.banksystem.base.backends.consultaempleados;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.catalogo.beans.CatalogoGeoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.AbcPerfilEmpleadoBackEnd;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.backends.atribuciones.ConsultaAtribucionesBackEnd;
import mx.babel.bansefi.banksystem.base.backends.consultadomicilio.ConsultaDomicilioBackend;
import mx.babel.bansefi.banksystem.base.beans.busqueda.EmpleadoBusquedaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaFisicaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioTipoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.EmpleadoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.utils.CatalogoGeoUtils;
import mx.babel.bansefi.banksystem.base.utils.CedulaConocimientoUtils;
import mx.babel.bansefi.banksystem.base.utils.DomicilioUtils;
import mx.babel.bansefi.banksystem.base.webservices.consultaempleado.ConsultaEmpleadoServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultaempleado.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultaempleado.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultaempleado.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.wrappers.EmpleadoWrapper;
import mx.babel.bansefi.banksystem.base.wrappers.WrapperBusquedasUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para consulta de un empleado en especifico
 * 
 * @author javier.martinnino
 *
 */
@Component
public class ConsultaEmpleadoBackEnd extends BackEndBean implements Serializable {
	
	private static final String FECHA_INICIO_MIN = "11/11/1111";
	
	private static final long serialVersionUID = -5397617229403876960L;

	@Autowired
	private DomicilioUtils domicilioUtils;
	
	@Autowired
	CatalogoGeoUtils catalogoGeoUtils;
	
	@Autowired
	private ConsultaDomicilioBackend consultaDomicilio;
	
	@Autowired
	private CedulaConocimientoUtils cedulaConocimientoUtils;
	
	@Autowired
	private ConsultaPerfilEmpleadoTCBBackEnd consultaPerfilTCBEmpleadoBackEnd;
	
	@Autowired
	private AbcPerfilEmpleadoBackEnd abcPerfilEmpleadoBackEnd;
	
	@Autowired
	private ConsultaAtribucionesBackEnd consultaAtribucionesBackEnd;
	
	@Autowired
	private WrapperBusquedasUtils wrapperBeanService;
	
	@Autowired 
	private EmpleadoWrapper empleadoWrapper;
			
	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Función encargada de consultar un empleado por medio de servicios web desde el menu de Busquedas.
	 * 
	 * @param idInterno del empleado a consultar
	 * @param tipoResultado esperado
	 * @return Bean de Empleado con los datos recuperados
	 */
	public List<Object> ejecutarTRN(int idInterno, String tipoResultado){
		Ejecutar.ITRCONSEMPLTRNI contexto = initPeticion(idInterno);
		EjecutarResult respuesta = ejecutarWS(contexto);
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return new ArrayList<>();
			}
		}
		if(verificaResponseBansefi(respuesta)){
			return getResultadoBusqueda(respuesta.getResponseBansefi(),tipoResultado);
		}
		return null;
	}
	
	/**
	 * Función encargada de consultar un empleado
	 * por medio de servicios web.
	 * 
	 * @param idEmpleado del empleado a consultar
	 * @return Bean de Empleado con los datos recuperados
	 */
	public EmpleadoBean ejecutarTRN(String idEmpleado){
		Ejecutar.ITRCONSEMPLTRNI contexto = initPeticion(idEmpleado);
		EjecutarResult respuesta = ejecutarWS(contexto);
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return null;
			}
		}
		if(verificaResponseBansefi(respuesta)){
			return consultaEmpleado(respuesta.getResponseBansefi());
		}
		return null;
	}
	
	/**
	 * Función encargada de consultar un empleado por medio de servicios web desde el menu de Busquedas.
	 * 
	 * @param idEmpleado del empleado a consultar
	 * @param tipoResultado esperado
	 * @return Bean de Empleado con los datos recuperados
	 */
	public List<Object> ejecutarTRN(String idEmpleado, String tipoResultado){
		Ejecutar.ITRCONSEMPLTRNI contexto = initPeticion(idEmpleado);
		EjecutarResult respuesta = ejecutarWS(contexto);
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return new ArrayList<>();
			}
		}
		if(verificaResponseBansefi(respuesta)){
			return getResultadoBusqueda(respuesta.getResponseBansefi(),tipoResultado);
		}
		return null;
	}
	
	/**
	 * Función que construye la lista de cuentas a partir de la respuesta al servidor.
	 * 
	 * @param response
	 * @param tipoResultado esperado
	 * @return
	 */
	private List<Object> getResultadoBusqueda(ResponseBansefi response, String tipoResultado){
		List<Object> resultado = null;		
		if(verificaRespuestaCliente(response)){
			
			resultado = new ArrayList<Object>();
			String idEmpleado = response.getOTRCONSEMPLTRNO().getTRCONSEMPLEVTZ().getEPPEDPOBJTRDV().getIDINTERNOEMPLEP();
			if (idEmpleado != null && !("").equals(idEmpleado.trim())){
				EmpleadoBusquedaBean empleado = wrapperBeanService.wrappBean(response.getOTRCONSEMPLTRNO().getTRCONSEMPLEVTZ(), tipoResultado);
				resultado.add(empleado);
			}
					
		}
		return resultado;
	}
	
	/**
	 * Función que construye el bean de empleado a devolver.
	 * 
	 * @param response
	 * @return
	 */
	private EmpleadoBean consultaEmpleado(ResponseBansefi response){
		EmpleadoBean empleado = null;		
		if(verificaRespuestaCliente(response)){//			
			// Se recuperan y wrappean los datos del empleado
			empleado = empleadoWrapper.wrappBeanConsultaEmpleado(response.getOTRCONSEMPLTRNO().getTRCONSEMPLEVTZ());
			
			// Se inicializa el objeto del estado
			empleado.getDatos().setEstado(new CatalogoGeoBean());
			
			// Construimos el lugar de nacimiento del cliente, cuando es codificado recuperamos los datos a
			// partir del nombre del municipio a partir de uno de los datos obligatorios en los codificados
			if (response.getOTRCONSEMPLTRNO().getTRCONSEMPLEVTZ().getPECONSINDVOBJTRDV().getCODENTCOLECAG()!= null && !("").equalsIgnoreCase(response.getOTRCONSEMPLTRNO().getTRCONSEMPLEVTZ().getPECONSINDVOBJTRDV().getCODENTCOLECAG().trim())){
				boolean encontrado = false;

				List<CatalogoGeoBean> candidatos = catalogoGeoUtils.getCatalogoGeoBean();
				
				for (int i=0; i<candidatos.size() && !encontrado;i++){
					if (candidatos.get(i).getCodigoMunicipio() != null && 
							candidatos.get(i).getCodigoMunicipio().equals(
							response.getOTRCONSEMPLTRNO().getTRCONSEMPLEVTZ().getPECONSINDVOBJTRDV().getCODMUNICIPIOAG().trim())){
						empleado.getDatos().setMunicipioCatGeo(candidatos.get(i));
						encontrado = true;
					}
				}
			}else{
				empleado.getDatos().setMunicipioCatGeo(new CatalogoGeoBean());	
				empleado.getDatos().getMunicipioCatGeo().setNombreMunicipio(response.getOTRCONSEMPLTRNO().getTRCONSEMPLEVTZ().getPECONSINDVOBJTRDV().getNOMBLOCALIDADAG().trim());
				empleado.getDatos().getEstado().setNombreProvincia(response.getOTRCONSEMPLTRNO().getTRCONSEMPLEVTZ().getPECONSINDVOBJTRDV().getNOMBPROVINCIAAG().trim());
				empleado.getDatos().setPais(response.getOTRCONSEMPLTRNO().getTRCONSEMPLEVTZ().getPECONSINDVOBJTRDV().getCODPAISAG().trim());
				if (response.getOTRCONSEMPLTRNO().getTRCONSEMPLEVTZ().getPECONSINDVOBJTRDV().getCODPROVINCIAAG() != null && !("").equalsIgnoreCase(response.getOTRCONSEMPLTRNO().getTRCONSEMPLEVTZ().getPECONSINDVOBJTRDV().getCODPROVINCIAAG().trim())){
					empleado.getDatos().getEstado().setCodigoProvincia(response.getOTRCONSEMPLTRNO().getTRCONSEMPLEVTZ().getPECONSINDVOBJTRDV().getCODPROVINCIAAG());
				}
			}
					
					
			// Invertimos el valor del indicador puesto que el indice que devuelve la TRN es de Publicidad
			if (empleado.getDatos().getNoPublicidad() != null && empleado.getDatos().getNoPublicidad()){
				empleado.getDatos().setNoPublicidad(false);
			}else{
				empleado.getDatos().setNoPublicidad(true);
			}
			
			// Tratamos la fecha de fallecimiento para visualizarla a vacio si viene como cliente no fallecido
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			
			// Si el cliente no es trabajador independiente vaciamos los datos de fecha de alta y de 
			if (empleado.getDatos().getAutonomo() ==null || !empleado.getDatos().getAutonomo()){
				empleado.getDatos().setFechaAltaAutonomo(null);
				empleado.getDatos().setCnae(null);
			}
			
			// Tratamos la fecha de autonomo para no informarla si viene a la fecha de inicio minima
			if (empleado.getDatos().getFechaAltaAutonomo() !=null && ConsultaEmpleadoBackEnd.FECHA_INICIO_MIN.equals(df.format(empleado.getDatos().getFechaAltaAutonomo()))){
				empleado.getDatos().setFechaAltaAutonomo(null);
			}
			
			if (empleado.getDatos().getFechaFallecimiento() !=null && ConstantesFuncionales.MAX_FECHA_FIN.equals(df.format(empleado.getDatos().getFechaFallecimiento()))){
				empleado.getDatos().setFechaFallecimiento(null);
			}
			
			// Se recuperan los datos de direccion 		
			DomicilioTipoBean domicilio = consultaDomicilio.ejectuarTRN(null,response.getOTRCONSEMPLTRNO().getTRCONSEMPLEVTZ().getPECONSINDVOBJTRDV().getNUMDIRPRAL());
			List<DomicilioTipoBean> domicilios = new ArrayList<DomicilioTipoBean>();
			domicilios.add(domicilio);
			empleado.getDatos().setDomicilios(domicilios);	
			
			// Se recuperan los datos de perfil transaccional
			ClientePersonaFisicaBean persona = cedulaConocimientoUtils.consultaCedulaConocimiento(empleado.getDatos().getIdInterna());
			empleado.getDatos().setTransaccionalidad(persona.getTransaccionalidad());
			empleado.getDatos().setUsoCuenta(persona.getUsoCuenta());
			
			// Se recupera el perfil del empleado en TCB
			empleado.setPerfilTCB(this.consultaPerfilTCBEmpleadoBackEnd.ejecutarTRN(empleado.getNumEmpleado()));
			
			// Se recupera el perfil del empleado
			empleado.setPerfil(abcPerfilEmpleadoBackEnd.ejecutarWS(empleado.getNumEmpleado()));
			
			// Se incluyen las atribuciones del empleado
			empleado = this.consultaAtribucionesBackEnd.ejecutarTRN(empleado);
			
		}
		return empleado;
	}
	
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param idInterno Id del cliente de tipo persona física
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITRCONSEMPLTRNI initPeticion(Integer idInterno){
		Ejecutar.ITRCONSEMPLTRNI contexto = new Ejecutar.ITRCONSEMPLTRNI();
		Ejecutar.ITRCONSEMPLTRNI.STDTRNIPARMV contextoEntrada =  new Ejecutar.ITRCONSEMPLTRNI.STDTRNIPARMV();
		Ejecutar.ITRCONSEMPLTRNI.TRCONSEMPLEVTY empleado = new Ejecutar.ITRCONSEMPLTRNI.TRCONSEMPLEVTY();
		Ejecutar.ITRCONSEMPLTRNI.TRCONSEMPLEVTY.CLAVEEMPLEADOV claveEmpleado = new Ejecutar.ITRCONSEMPLTRNI.TRCONSEMPLEVTY.CLAVEEMPLEADOV();
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_CONS_EMPL_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		
		claveEmpleado.setCODNRBEEN(super.getEntidad());
		claveEmpleado.setIDINTERNOPE(idInterno);
		claveEmpleado.setIDINTERNOEMPLEP("");
		
		contexto.setTRCONSEMPLEVTY(empleado);
		contexto.getTRCONSEMPLEVTY().setCLAVEEMPLEADOV(claveEmpleado);
		contexto.setSTDTRNIPARMV(contextoEntrada);
		
		return contexto;
	}
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param idEmpleado Id del empleado a consultar
	 * @param tipoBusqueda a realizar por idInterna o por idEmpleado
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITRCONSEMPLTRNI initPeticion(String idEmpleado){
		Ejecutar.ITRCONSEMPLTRNI contexto = new Ejecutar.ITRCONSEMPLTRNI();
		Ejecutar.ITRCONSEMPLTRNI.STDTRNIPARMV contextoEntrada =  new Ejecutar.ITRCONSEMPLTRNI.STDTRNIPARMV();
		Ejecutar.ITRCONSEMPLTRNI.TRCONSEMPLEVTY empleado = new Ejecutar.ITRCONSEMPLTRNI.TRCONSEMPLEVTY();
		Ejecutar.ITRCONSEMPLTRNI.TRCONSEMPLEVTY.CLAVEEMPLEADOV claveEmpleado = new Ejecutar.ITRCONSEMPLTRNI.TRCONSEMPLEVTY.CLAVEEMPLEADOV();
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_CONS_EMPL_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		
		claveEmpleado.setCODNRBEEN(super.getEntidad());
		claveEmpleado.setIDINTERNOEMPLEP(idEmpleado);
		
		contexto.setTRCONSEMPLEVTY(empleado);
		contexto.getTRCONSEMPLEVTY().setCLAVEEMPLEADOV(claveEmpleado);
		contexto.setSTDTRNIPARMV(contextoEntrada);
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRCONSEMPLTRNI contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaEmpleadoServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta de empleado.", e);
		}
		return respuesta;
	}
	
	/**
	 * Función que valida que la respuesta del servidor no este vacía. 
	 *  
	 * @param respuesta Objeto respuesta del servicio web
	 * @return <code>true</code> si la respuesta no esta vacía.
	 */
	private Boolean verificaResponseBansefi(EjecutarResult respuesta){
		Boolean noNulo = false;
		if(respuesta != null && respuesta.getResponseBansefi() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con los datos
	 * de la persona física.
	 * 
	 * @param response Respuesta Bansefi con datos de la persona moral
	 * @return <code>true</code> si la respuesta Bansefi contiene elemento no nulos
	 */
	private Boolean verificaRespuestaCliente(ResponseBansefi response){
		Boolean noNulo = false;
		if(response!= null && response.getOTRCONSEMPLTRNO() != null &&
				response.getOTRCONSEMPLTRNO().getTRCONSEMPLEVTZ() != null &&
						response.getOTRCONSEMPLTRNO().getTRCONSEMPLEVTZ().getEPPEDPOBJTRDV() != null
						&& response.getOTRCONSEMPLTRNO().getTRCONSEMPLEVTZ().getPECONSINDVOBJTRDV() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
}
