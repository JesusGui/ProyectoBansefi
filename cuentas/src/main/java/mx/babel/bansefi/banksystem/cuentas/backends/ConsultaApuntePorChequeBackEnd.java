package mx.babel.bansefi.banksystem.cuentas.backends;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.ApunteChequeBean;
import mx.babel.bansefi.banksystem.base.beans.movimientos.MovimientoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaapunteporcheque.ConsultaApuntePorChequeServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaapunteporcheque.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaapunteporcheque.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaapunteporcheque.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaapunteporcheque.ResponseBansefi.OTRCPCONSULTADETALLET.TRCPCONSULTADETALLEEV;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaApunteWrapper;

/**
 * 
 * @author mario.montesdeoca
 *
 */
@Component
public class ConsultaApuntePorChequeBackEnd extends BackEndBean{
	
	private static final long serialVersionUID = -973399157543643253L;
	
	private static final String SITUACION_PAGO_N = "N";
	
	private static final String ESTADO_CHEQUE_APLICADO = "L";
	
	private static final String SITUACION_PAGO_TOTAL = "T";

	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	@Autowired
	ConsultaApunteWrapper consultaApunteWrapper;
	
	/**
	 * Método para ejecutar la consulta de apunte con origen en cheque
	 * @param apunte Apunte a consultar el origen
	 * @return Bean con datos del origen del origen
	 */
	public ApunteChequeBean ejecutarTRN(MovimientoBean apunte){		
		Ejecutar.ITRCPCONSULTADETALLET contexto = initPeticion(apunte);
		
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
		
		return getDetalleApunteCheque(respuesta.getResponseBansefi());
	}
	
	/**
	 * Método que obtiene los detalles del apunte que el servicio devolvío
	 * @param respuesta respuesta del servicio web
	 * 
	 * @return bean con detalles del apunte
	 */
	private ApunteChequeBean getDetalleApunteCheque(ResponseBansefi respuesta){
		ApunteChequeBean apunteChequeBean = new ApunteChequeBean();
		if(respuesta.getOTRCPCONSULTADETALLET() != null && 
				respuesta.getOTRCPCONSULTADETALLET().getTRCPCONSULTADETALLEEV() != null){
			TRCPCONSULTADETALLEEV apunteCheque = respuesta.getOTRCPCONSULTADETALLET().getTRCPCONSULTADETALLEEV();
			apunteChequeBean = consultaApunteWrapper.consultaOrigenChequeWrapper(apunteCheque);
			apunteChequeBean.setNumCheque(apunteCheque.getCODCJCHQPG() + " "+ apunteCheque.getNUMCHQPAG());
			if(SITUACION_PAGO_N.equals(apunteChequeBean.getSituacionPago()) &&
					ESTADO_CHEQUE_APLICADO.equals(apunteChequeBean.getEstado())){
				apunteChequeBean.setSituacionPago(SITUACION_PAGO_TOTAL);
			}
		}
		return apunteChequeBean;
		
	}
	
	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRCPCONSULTADETALLET initPeticion(MovimientoBean apunte){
		Ejecutar.ITRCPCONSULTADETALLET contexto = new Ejecutar.ITRCPCONSULTADETALLET();
		Ejecutar.ITRCPCONSULTADETALLET.TRCPCONSULTADETALLEEV detalle = 
				new Ejecutar.ITRCPCONSULTADETALLET.TRCPCONSULTADETALLEEV();
		Ejecutar.ITRCPCONSULTADETALLET.TRCPCONSULTADETALLEEV.CPCHQPAGPROPIOP cheque =
				new Ejecutar.ITRCPCONSULTADETALLET.TRCPCONSULTADETALLEEV.CPCHQPAGPROPIOP();
		contexto.setTRCPCONSULTADETALLEEV(detalle);
		detalle.setCPCHQPAGPROPIOP(cheque);
		super.initialize(contexto);
		
		String origenApunte = apunte.getIdOrigenApunte();
		
		if(origenApunte.length() > 17){
			cheque.setCODNRBEEN(origenApunte.substring(0,4));
			cheque.setCODCJCHQPG(origenApunte.substring(4,6));
			cheque.setNUMCHQPAGCP(origenApunte.substring(6,13));
			cheque.setCODCENTUO(origenApunte.substring(13,17));
			Long numCuenta = 0L;
			try{
				numCuenta = Long.parseLong(origenApunte.substring(17));
			}catch(NumberFormatException e){
			}
			cheque.setNUMSECAC(numCuenta);
		}
		
		contexto.getSTDTRNIPARMV().setCODTX(CodTxConstants.COD_TX_TR_CP_CONSULTA_DETALLE_TRN);
		contexto.getSTDTRNIPARMV().setIDINTERNOTERMTN(super.getTerminal());
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRCPCONSULTADETALLET contexto){
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaApuntePorChequeServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta apunta "
					+ "de origen cheque.", e);
		}
		return respuesta;
	}

}
