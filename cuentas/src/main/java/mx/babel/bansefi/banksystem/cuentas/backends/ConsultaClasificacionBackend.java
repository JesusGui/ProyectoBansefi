package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.webservices.consultadatoscuenta.ConsultaDatosCuentaServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultadatoscuenta.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultadatoscuenta.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultadatoscuenta.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.beans.ClasificacionBean;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaClasificacionWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaClasificacionBackend extends BackEndBean{

	private static final long serialVersionUID = -1208434064679135635L;

	@Autowired
	ConsultaClasificacionWrapper consultaClasificacionWrapper;
	
    @Autowired
    ServicioWebUtils servicioWebUtils;
	
	/**
	 * Método que ejecuta la consulta de informe de clasificación a partir de un número de cuenta
	 * 
	 * @param clasificacionBean
	 * @return clasificacionBean con valores de los atributos recuperados
	 */
	public ClasificacionBean ejecutarTRN(Long numeroCuenta){
		Ejecutar.ITRCONSUDATOSACTRNI contexto = initPeticion(numeroCuenta);
		
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		super.verificaRespuesta(respuesta);
		
		return consultaClasificacion(respuesta.getResponseBansefi());		
	}
	
	private ClasificacionBean consultaClasificacion(ResponseBansefi response){
		ClasificacionBean clasificacion = null;		
		if(verificaRespuestaCliente(response)){
			clasificacion = consultaClasificacionWrapper.wrappConsultaClasificacionCuenta(response.getOTRCONSUDATOSACTRNO()
					.getTRCONSUDATOSACEVTZ());
			
			
		}
		
		return clasificacion;
	}
	
	private Ejecutar.ITRCONSUDATOSACTRNI initPeticion(long numeroCuenta){
		Ejecutar.ITRCONSUDATOSACTRNI contexto = new Ejecutar.ITRCONSUDATOSACTRNI();
		Ejecutar.ITRCONSUDATOSACTRNI.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRCONSUDATOSACTRNI.STDTRNIPARMV();
		Ejecutar.ITRCONSUDATOSACTRNI.TRCONSUDATOSACEVTY consultaAcuerdo =
				new Ejecutar.ITRCONSUDATOSACTRNI.TRCONSUDATOSACEVTY();
		Ejecutar.ITRCONSUDATOSACTRNI.TRCONSUDATOSACEVTY.ACACP datosEntrada = new Ejecutar.ITRCONSUDATOSACTRNI.TRCONSUDATOSACEVTY.ACACP();
		
		super.initialize(contexto);
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_CONSU_DATOS_AC_TRN);
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
						
		datosEntrada.setCODNRBEEN(getEntidad());
		datosEntrada.setNUMSECAC(numeroCuenta);
		
		consultaAcuerdo.setACACP(datosEntrada);
		
		contexto.setSTDTRNIPARMV(contextoEntrada);
		contexto.setTRCONSUDATOSACEVTY(consultaAcuerdo);
		super.initialize(contexto);
						
		return contexto;
	}
	
	private EjecutarResult ejecutarWS(Ejecutar.ITRCONSUDATOSACTRNI contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaDatosCuentaServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta de "
					+ "detalle de cuentas.", e);
		}
		return respuesta;
	}
		
	private Boolean verificaRespuestaCliente(ResponseBansefi response){
		Boolean noNulo = false;
		if(response!= null && response.getOTRCONSUDATOSACTRNO() != null &&
				response.getOTRCONSUDATOSACTRNO().getTRCONSUDATOSACEVTZ() != null &&
						response.getOTRCONSUDATOSACTRNO().getTRCONSUDATOSACEVTZ().getACACE() != null
						&& response.getOTRCONSUDATOSACTRNO().getTRCONSUDATOSACEVTZ().getPVNOMBPDVV() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
}
