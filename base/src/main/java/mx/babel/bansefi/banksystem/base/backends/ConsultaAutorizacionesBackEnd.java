package mx.babel.bansefi.banksystem.base.backends;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.movimientos.MovimientoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.MovimientosWrapper;
import mx.babel.bansefi.banksystem.base.webservices.consultaautorizaciones.ConsultaAutorizacionesServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultaautorizaciones.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultaautorizaciones.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultaautorizaciones.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.webservices.consultaautorizaciones.ResponseBansefi.OTRCONSULTAGLOBALATTR.TRCONSULTAGLOBALATEVT.TRAFCONSEVTV;
import mx.babel.bansefi.banksystem.base.webservices.consultaautorizaciones.ResponseBansefi.OTRCONSULTAGLOBALATTR.TRCONSULTAGLOBALATEVT.TRAFCONSEVTV.AFAPNTEE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para consultas de autorizaciones en una cuenta
 * 
 * @author mario.montesdeoca
 *
 */
@Component
public class ConsultaAutorizacionesBackEnd extends BackEndBean{


	private static final long serialVersionUID = -3546574754052144533L;

	@Autowired
	MovimientosWrapper movimientosWrapper;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;

	
	/**
	 * Función para obtener las retenciones de una cuenta invocando un 
	 * servicio web.
	 * 
	 * @param consultaMovimientosBean Bean con los detalles de la cuenta a ser consultados
	 */
	public List<MovimientoBean> ejecutarTRN(String numeroCuenta){
		Ejecutar.ITRCONSULTAGLOBALATTR contexto = initPeticion(numeroCuenta);
		
		EjecutarResult respuesta = getResponse(contexto);
		
		super.verificaRespuesta(respuesta);
		
		return getAutorizaciones(respuesta.getResponseBansefi());
	}
	
	/**
	 * Función encargada de obtener las retenciones a partir de la respuesta del servicio web
	 * 
	 * @param response El objeto de reultado enviado por el servicio web.
	 * @return
	 */
	private List<MovimientoBean> getAutorizaciones(ResponseBansefi response)
			throws NoControlableException, ControlableException{
		List<MovimientoBean> autorizaciones = new ArrayList<MovimientoBean>();
		
		if(verificaRespuestaTRN(response)){
			List<TRAFCONSEVTV> listTrafconsev = response.getOTRCONSULTAGLOBALATTR().getTRCONSULTAGLOBALATEVT().getTRAFCONSEVTV();
			for (TRAFCONSEVTV trafconsev : listTrafconsev) {
				AFAPNTEE fap = trafconsev.getAFAPNTEE();
				MovimientoBean movimientoBean = this.movimientosWrapper.wrappAutorizacion(fap);
				autorizaciones.add(movimientoBean);
			}
		}
		return autorizaciones;
	}
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param numeroCuenta Número de la cuenta que poseé las retenciones
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITRCONSULTAGLOBALATTR initPeticion(
			String numeroCuenta) throws ControlableException{
		Ejecutar.ITRCONSULTAGLOBALATTR contexto = new Ejecutar.ITRCONSULTAGLOBALATTR();
		Ejecutar.ITRCONSULTAGLOBALATTR.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRCONSULTAGLOBALATTR.STDTRNIPARMV();
		Ejecutar.ITRCONSULTAGLOBALATTR.TRCONSULTAGLOBALATEVT cuerpoContexto =
				new Ejecutar.ITRCONSULTAGLOBALATTR.TRCONSULTAGLOBALATEVT();
		Ejecutar.ITRCONSULTAGLOBALATTR.TRCONSULTAGLOBALATEVT.PSVTPSELECV estado = 
				new Ejecutar.ITRCONSULTAGLOBALATTR.TRCONSULTAGLOBALATEVT.PSVTPSELECV();
		contexto.setSTDTRNIPARMV(contextoEntrada);
		contexto.setTRCONSULTAGLOBALATEVT(cuerpoContexto);
		cuerpoContexto.setPSVTPSELECV(estado);
		super.initialize(contexto);
		
		try{
			cuerpoContexto.setNUMSECAC(Long.parseLong(numeroCuenta));
		}catch(NumberFormatException nfe){
			throw new ControlableException("No se puede realizar la consulta", 
					"El formato de alguno de los parámetros es erroneo");
		}
		cuerpoContexto.setCODNRBEEN(super.getEntidad());
		estado.setSTDCHAR10("ACTIVO");
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TR_CONSULTA_GLOBAL_AT_TRN);
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		contexto.setSCROLLABLEOCCURS(50);
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult getResponse(Ejecutar.ITRCONSULTAGLOBALATTR contexto){
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
						ConsultaAutorizacionesServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta de "
					+ "autorizaciones.", e);
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
		if(response != null && response.getOTRCONSULTAGLOBALATTR() != null && 
				response.getOTRCONSULTAGLOBALATTR().getTRCONSULTAGLOBALATEVT() != null &&
				response.getOTRCONSULTAGLOBALATTR().getTRCONSULTAGLOBALATEVT()
				.getTRAFCONSEVTV() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
}