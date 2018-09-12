package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.StatusCuentaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.webservices.consultastatuscuenta.ConsultaStatusCuentaServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultastatuscuenta.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultastatuscuenta.EjecutarResult;
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
public class ConsultaStatusCuentaBackend extends BackEndBean {
	
	private static final long serialVersionUID = -7614211858404361365L;

	public static final String ESPACIO = " ";
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	@Autowired
	ConsultaCuentaWrapper consultaCuentaWrapper;

	/**
	 * Método que ejecuta la transacción de consulta de datos de cuentas a partir de un
	 * objeto CuentaBean.
	 * 
	 * @param numeroCuenta el numero de la cuenta a consultar
	 * @return statusCuentaBean con valores de status de cuenta recuperados
	 */
	public StatusCuentaBean ejecutarTRN(long numeroCuenta){
		Ejecutar.ITRCONSULDTALACCTETR contexto = initPeticion(numeroCuenta);
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
		return consultaCuentaWrapper.wrappConsultaStatusCuenta(respuesta.getResponseBansefi().getOTRCONSULDTALACCTETR().getTRCONSULDTALACCTEEVT().getPEINDVDTRLACV());
	}	
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param numeroCuenta el numero de cuenta a consultat
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITRCONSULDTALACCTETR initPeticion(long numeroCuenta){
		Ejecutar.ITRCONSULDTALACCTETR contexto = new Ejecutar.ITRCONSULDTALACCTETR();
		Ejecutar.ITRCONSULDTALACCTETR.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRCONSULDTALACCTETR.STDTRNIPARMV();
		Ejecutar.ITRCONSULDTALACCTETR.TRCONSULDTALACCTEEVT consultaAcuerdo =
				new Ejecutar.ITRCONSULDTALACCTETR.TRCONSULDTALACCTEEVT();
		Ejecutar.ITRCONSULDTALACCTETR.TRCONSULDTALACCTEEVT.CONSULMONTOSV consultaStatus =
				new Ejecutar.ITRCONSULDTALACCTETR.TRCONSULDTALACCTEEVT.CONSULMONTOSV();
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_CONSU_DATOS_AC_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		
		consultaStatus.setCODNRBEEN(super.getEntidad());
		consultaStatus.setNUMSECAC(numeroCuenta);
		consultaStatus.setSTDCHAR01("2");
		
		consultaAcuerdo.setCONSULMONTOSV(consultaStatus);
		
		contexto.setTRCONSULDTALACCTEEVT(consultaAcuerdo);
		contexto.setSTDTRNIPARMV(contextoEntrada);
		
		contexto.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);
		
		super.initialize(contexto);
				
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRCONSULDTALACCTETR contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaStatusCuentaServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta de "
					+ "status de cuentas.", e);
		}
		return respuesta;
	}
	
}