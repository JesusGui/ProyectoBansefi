package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.webservices.consultadatoscuenta.ConsultaDatosCuentaServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultadatoscuenta.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultadatoscuenta.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultadatoscuenta.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.wrappers.ConsultaCuentaWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la consulta de datos de cuentas.
 * 
 * @author javier.martinnino
 * 
 */

@Component
public class ConsultaDatosCuentaBackend extends BackEndBean {
	
	private static final long serialVersionUID = 2288951886924575526L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;

	public static final String ESPACIO = " ";
	
	@Autowired
	ConsultaCuentaWrapper consultaCuentaWrapper;

	/**
	 * Método que ejecuta la transacción de consulta de datos de cuentas a partir de un
	 * objeto CuentaBean.
	 * 
	 * @param numeroCuenta el numero de la cuenta a consultar
	 * @return cuentaBean con valores de atributos recuperados
	 */
	public CuentaBean ejecutarTRN(long numeroCuenta){
		Ejecutar.ITRCONSUDATOSACTRNI contexto = initPeticion(numeroCuenta);
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		super.verificaRespuesta(respuesta);
		
		return consultaCuenta(respuesta.getResponseBansefi());
	}
		
	/**
	 * Función que construye el bean cuentaBean a devolver.
	 * 
	 * @param response
	 * @return
	 */
	private CuentaBean consultaCuenta(ResponseBansefi response){
		CuentaBean cuenta = null;		
		if(verificaRespuestaCliente(response)){//			
			// Se recuperan y wrappean los datos de la cuenta
			//wrappConsultaDatosCuenta(response.getTRCONSUDATOSACTRNO().getTRCONSUDATOSACEVTZ());
			cuenta.setCuentaClabe(cuenta.getEntidad()
					+ cuenta.getPlazaBancaria()
					+ cuenta.getNumeroCuenta()
					+ cuenta.getDigitoVerificador());
			cuenta.setTarifa(cuenta.getCodLinea() + ConsultaDatosCuentaBackend.ESPACIO
					+ cuenta.getIdGrupoProducto() + ConsultaDatosCuentaBackend.ESPACIO
					+ cuenta.getIdProducto() + ConsultaDatosCuentaBackend.ESPACIO
					+ cuenta.getIdTarifaProducto());
		}
		return cuenta;
	}
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param numeroCuenta el numero de cuenta a consultat
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITRCONSUDATOSACTRNI initPeticion(long numeroCuenta){
		Ejecutar.ITRCONSUDATOSACTRNI contexto = new Ejecutar.ITRCONSUDATOSACTRNI();
		Ejecutar.ITRCONSUDATOSACTRNI.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRCONSUDATOSACTRNI.STDTRNIPARMV();
		Ejecutar.ITRCONSUDATOSACTRNI.TRCONSUDATOSACEVTY consultaAcuerdo =
				new Ejecutar.ITRCONSUDATOSACTRNI.TRCONSUDATOSACEVTY();
		Ejecutar.ITRCONSUDATOSACTRNI.TRCONSUDATOSACEVTY.ACACP acuerdo =
				new Ejecutar.ITRCONSUDATOSACTRNI.TRCONSUDATOSACEVTY.ACACP();
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_CONSU_DATOS_AC_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		
		acuerdo.setCODCENTUO("");
		acuerdo.setCODNRBEEN(super.getEntidad());
		acuerdo.setNUMSECAC(numeroCuenta);
		
		consultaAcuerdo.setACACP(acuerdo);
		
		contexto.setTRCONSUDATOSACEVTY(consultaAcuerdo);
		contexto.setSTDTRNIPARMV(contextoEntrada);
		super.initialize(contexto);
				
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRCONSUDATOSACTRNI contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaDatosCuentaServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta de "
					+ "datos de cuentas.", e);
		}
		return respuesta;
	}		
	
	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con los datos
	 * de la consulta.
	 * 
	 * @param response Respuesta Bansefi con datos de la consulta
	 * @return <code>true</code> si la respuesta Bansefi contiene elemento no nulos
	 */
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