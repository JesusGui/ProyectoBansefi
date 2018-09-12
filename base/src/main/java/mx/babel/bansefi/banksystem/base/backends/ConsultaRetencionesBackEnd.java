package mx.babel.bansefi.banksystem.base.backends;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.movimientos.MovimientoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.base.utils.MovimientosWrapper;
import mx.babel.bansefi.banksystem.base.webservices.consultaretenciones.ConsultaRetencionesServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultaretenciones.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultaretenciones.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultaretenciones.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.webservices.consultaretenciones.ResponseBansefi.OTRCONSULTAGLOBALRTTR.TRCONSULTAGLOBALRTEVT.TRAFCONSEVTV;
import mx.babel.bansefi.banksystem.base.webservices.consultaretenciones.ResponseBansefi.OTRCONSULTAGLOBALRTTR.TRCONSULTAGLOBALRTEVT.TRAFCONSEVTV.AFAPNTEE;
import mx.babel.bansefi.banksystem.base.webservices.consultaretenciones.ResponseBansefi.OTRCONSULTAGLOBALRTTR.TRCONSULTAGLOBALRTEVT.TRAFCONSEVTV.AFAUDITE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para consultas de retenciones de una cuenta
 * 
 * @author mario.montesdeoca
 *
 */
@Component
public class ConsultaRetencionesBackEnd extends BackEndBean{

	private static final long serialVersionUID = -2803716637739302086L;

	@Autowired
	MovimientosWrapper movimientosWrapper;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	@Autowired
	CatalogoUtils catalogoUtils;

	
	/**
	 * Función para obtener las retenciones de una cuenta invocando un 
	 * servicio web.
	 * 
	 * @param consultaMovimientosBean Bean con los detalles de la cuenta a ser consultados
	 */
	public List<MovimientoBean> ejecutarTRN(String numeroCuenta)
			throws NoControlableException, ControlableException{
		List<MovimientoBean> retenciones = new ArrayList<MovimientoBean>();
		
		Ejecutar.ITRCONSULTAGLOBALRTTR contexto = initPeticion(numeroCuenta);
		
		EjecutarResult respuesta = getResponse(contexto);
		
		super.verificaRespuesta(respuesta);
		
		retenciones = getRetenciones(respuesta.getResponseBansefi());
		return retenciones;
	}
	
	/**
	 * Función encargada de obtener las retenciones a partir de la respuesta del servicio web
	 * 
	 * @param response El objeto de reultado enviado por el servicio web.
	 * @return
	 */
	private List<MovimientoBean> getRetenciones(ResponseBansefi response)
			throws NoControlableException, ControlableException{
		List<MovimientoBean> retenciones = new ArrayList<MovimientoBean>();
		List<MovimientoBean> auditoria = new ArrayList<MovimientoBean>();
		
		if(verificaRespuestaTRN(response)){
			List<TRAFCONSEVTV> listaRetenciones = response.getOTRCONSULTAGLOBALRTTR().getTRCONSULTAGLOBALRTEVT().getTRAFCONSEVTV();
			for (TRAFCONSEVTV consevtv : listaRetenciones) {
				AFAPNTEE fap = consevtv.getAFAPNTEE();
				if(!fap.getCODNRBEEN().trim().equals("")){
					MovimientoBean movimientoBean = this.movimientosWrapper.wrappRetencion(fap);
					retenciones.add(movimientoBean);
				}				
			}
			
			List<TRAFCONSEVTV> listaRAudit = response.getOTRCONSULTAGLOBALRTTR().getTRCONSULTAGLOBALRTEVT().getTRAFCONSEVTV();
			for (TRAFCONSEVTV trafconsevtv : listaRAudit) {
				List<AFAUDITE>audit = trafconsevtv.getAFAUDITE();
				for (AFAUDITE afaudite : audit) {
					if(!afaudite.getCODNRBEEN().trim().contentEquals("")){
						MovimientoBean movimientoAuditoria = this.movimientosWrapper.wrappAuditoria(afaudite);
						auditoria.add(movimientoAuditoria);
					}
				}
			}
			
			int i = 0;
			for (Iterator iterator = retenciones.iterator(); iterator.hasNext();) {
				MovimientoBean retencion = (MovimientoBean) iterator.next();
				IntegerToDateConverter converter = new IntegerToDateConverter();
				retencion.setFechaVencimiento(converter.convertTo(response.getOTRCONSULTAGLOBALRTTR().getTRCONSULTAGLOBALRTEVT().getRTFVTOV().get(i).getFECHAOPRCN()));
				retencion.setOriginador(response.getOTRCONSULTAGLOBALRTTR().getTRCONSULTAGLOBALRTEVT().getIDORGNAPNTEV().get(i).getSTDCHAR50());
				i++;
			}
			
			for(int j=0; j < retenciones.size(); j++){
				retenciones.get(j).setEmpleado(auditoria.get(j).getEmpleado());
			}
		}
		
		List<CatalogoBean> catalogo = catalogoUtils.getCatalogo(CatalogoEnum.ESTADO_RETENCION);
		for(int i = 0; i < retenciones.size(); i++){
			for(int j = 0; j < catalogo.size(); j++){
				if(retenciones.get(i).getEstado().trim().equals(catalogo.get(j).getClaveFila())){
					retenciones.get(i).setEstado(catalogo.get(j).getDescripcionL());
				}
			}
		}
		
		List<CatalogoBean> catalogoTipos = catalogoUtils.getCatalogo(CatalogoEnum.TIPO_RETENCION);
		for(int i = 0; i < retenciones.size(); i++){
			for(int j = 0; j < catalogoTipos.size(); j++){
				if(retenciones.get(i).getSubTipo().trim().equals(catalogoTipos.get(j).getClaveFila())){
					retenciones.get(i).setSubTipo(catalogoTipos.get(j).getDescripcionL());
				}
			}
		}
		
		return retenciones;
	}
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param numeroCuenta Número de la cuenta que poseé las retenciones
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITRCONSULTAGLOBALRTTR initPeticion(
			String numeroCuenta) throws ControlableException, NoControlableException{
		Ejecutar.ITRCONSULTAGLOBALRTTR contexto = new Ejecutar.ITRCONSULTAGLOBALRTTR();
		Ejecutar.ITRCONSULTAGLOBALRTTR.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRCONSULTAGLOBALRTTR.STDTRNIPARMV();
		Ejecutar.ITRCONSULTAGLOBALRTTR.TRCONSULTAGLOBALRTEVT cuerpoContexto =
				new Ejecutar.ITRCONSULTAGLOBALRTTR.TRCONSULTAGLOBALRTEVT();
		Ejecutar.ITRCONSULTAGLOBALRTTR.TRCONSULTAGLOBALRTEVT.RTRTCNECVV estado = 
				new Ejecutar.ITRCONSULTAGLOBALRTTR.TRCONSULTAGLOBALRTEVT.RTRTCNECVV();
		contexto.setSTDTRNIPARMV(contextoEntrada);
		contexto.setTRCONSULTAGLOBALRTEVT(cuerpoContexto);
		cuerpoContexto.setRTRTCNECVV(estado);
		super.initialize(contexto);
		
		try{
			cuerpoContexto.setNUMSECAC(Long.parseLong(numeroCuenta));
		}catch(NumberFormatException nfe){
			throw new ControlableException("No se puede realizar la consulta", 
					"El formato de alguno de los parámetros es erroneo");
		}
		
		cuerpoContexto.setCODNRBEEN(super.getEntidad());
		estado.setSTDCHAR10("ACTIVO");
		
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		contextoEntrada.setCODTX(CodTxConstants.COD_TR_CONSULTA_GLOBAL_RT_TRN);
		
		contexto.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult getResponse(Ejecutar.ITRCONSULTAGLOBALRTTR contexto){
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
						ConsultaRetencionesServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta de "
					+ "retenciones.", e);
		}
		return respuesta;
	}
		
	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con los datos
	 * de las retenciones.
	 * 
	 * @param response Respuesta Bansefi con datos de la persona moral
	 * @return <code>true</code> si la respuesta Bansefi contiene una lista de retenciones
	 */
	private Boolean verificaRespuestaTRN(ResponseBansefi response){
		Boolean noNulo = false;
		if(response != null && response.getOTRCONSULTAGLOBALRTTR() != null && 
				response.getOTRCONSULTAGLOBALRTTR().getTRCONSULTAGLOBALRTEVT() != null &&
				response.getOTRCONSULTAGLOBALRTTR().getTRCONSULTAGLOBALRTEVT()
				.getTRAFCONSEVTV() != null){
			noNulo = true;
		}
		return noNulo;
	}	
	
}