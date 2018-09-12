package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.constituircuenta.ConstituirCuentaServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.constituircuenta.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.constituircuenta.EjecutarResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConstituirCuentaBackEnd extends BackEndBean{

	private static final long serialVersionUID = 3289844982313542106L;
	
	private static final String RESPUESTA_SINCRONA_V_S = "S";
	
	private static final String RESPUESTA_SINCRONA_V_N = "N";
	
	private static final Integer NRO_LLAMADA_V_1 = 1;
	
	private static final Integer NRO_LLAMADA_V_2 = 2;
	
	private static final Integer NRO_LLAMADA_V_3 = 3;
		
	private static final String ACV_TIPO_COBRO_V = "CT";
	
	private static final int NUM_PSVERRORV = 5;
	
	private static final int NUM_AR_TRN_MSJ_PARM_V = 10;
	
	private static final int NUM_STD_TARGET_TERMINAL_V = 50;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	@Autowired
	ConsultaLimiteConcedidoBackEnd consultaLimiteConcedidoBackEnd;
		
	public String ejecutarTRN(Long numeroCuenta, Date fechaConstitucion, Boolean isMedioPago, Boolean isCredito){
		EjecutarResult respuesta = ejecutarTRN(numeroCuenta, fechaConstitucion, isMedioPago, isCredito, NRO_LLAMADA_V_1, RESPUESTA_SINCRONA_V_S);
		
		if(verificaResponseBansefi(respuesta)){
			return getAprobacion(respuesta, numeroCuenta,fechaConstitucion, isMedioPago, isCredito);
		}
		
		return "";
	}
	
	private EjecutarResult ejecutarTRN(Long numeroCuenta, Date fechaConstitucion, Boolean isMedioPago, 
			Boolean isCredito, Integer numeroLLamada, String respuestaSincrona){
		Ejecutar.TRCONSTITOTALACTRNI contexto = initPeticion(
				numeroCuenta, fechaConstitucion, numeroLLamada, isCredito, respuestaSincrona);
		
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		super.verificaRespuesta(respuesta);

		return respuesta;
	}
	
	/**
	 * Mètodo que devuelde el estado del acuerdo después de ser aporbado
	 * @param respuesta objeto respuesta del ws
	 * @return Estado del acuerdo
	 */
	private String getAprobacion(EjecutarResult respuesta, Long numeroCuenta, Date fechaConstitucion, Boolean isMedioPago, Boolean isCredito){
		String constitucion = "";
		String retorno = verificaRetorno(respuesta);
		if(retorno != null){
			return retorno;
		}
		if(respuesta.getResponseBansefi().getTRCONSTITOTALACTRNO().getRESPUESTASINCRONAV().getSTDCHAR01().trim().equals(RESPUESTA_SINCRONA_V_N)){
			try{
				TimeUnit.SECONDS.sleep(1);
			}catch(final InterruptedException e){
			}
			EjecutarResult segundaRespuesta = ejecutarTRN(numeroCuenta, fechaConstitucion, isMedioPago, isCredito,
					NRO_LLAMADA_V_2 , RESPUESTA_SINCRONA_V_N);
			if(verificaResponseBansefi(segundaRespuesta)){
				retorno = verificaRetorno(segundaRespuesta);
				if(retorno != null){
					return retorno;
				}
				if(isMedioPago){
					try{
						TimeUnit.SECONDS.sleep(1);
					}catch(final InterruptedException e){
					}
					EjecutarResult terceraRespuesta = ejecutarTRN(numeroCuenta, fechaConstitucion, isMedioPago, isCredito,
							NRO_LLAMADA_V_3 , RESPUESTA_SINCRONA_V_S);
					if(verificaResponseBansefi(terceraRespuesta)){
						retorno = verificaRetorno(terceraRespuesta);
						if(retorno != null){
							return retorno;
						}
						constitucion = segundaRespuesta.getResponseBansefi().getTRCONSTITOTALACTRNO().
								getTRCONSTITOTALACEVTZ().getACDATOSV().getCODECVAC();
					}
				}else{
					constitucion = segundaRespuesta.getResponseBansefi().getTRCONSTITOTALACTRNO().
						getTRCONSTITOTALACEVTZ().getACDATOSV().getCODECVAC();
				}
			}
		}
		if (respuesta.getResponseBansefi().getTRCONSTITOTALACTRNO() != null && respuesta.getResponseBansefi().getTRCONSTITOTALACTRNO().getTRCONSTITOTALACEVTZ() != null){
			constitucion = respuesta.getResponseBansefi().getTRCONSTITOTALACTRNO().getTRCONSTITOTALACEVTZ().getACDATOSV().getCODECVAC();
		}
		return constitucion;
	}
	
	private String verificaRetorno(EjecutarResult respuesta){
		String constitucion = "";
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return constitucion;
			}
		}
		return null;
	}
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param numeroCuenta Numero de la cuenta que se quiere aprobar
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.TRCONSTITOTALACTRNI initPeticion(Long numeroCuenta, Date fechaConstitucion,
			Integer numeroLLamada, Boolean isCredito, String respuestaSincrona) {
		Ejecutar.TRCONSTITOTALACTRNI contexto = new Ejecutar.TRCONSTITOTALACTRNI();
		Ejecutar.TRCONSTITOTALACTRNI.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.TRCONSTITOTALACTRNI.STDTRNIPARMV();
		Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY cuerpoContexto =
				new Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY();
		Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.ACACP acuerdo = 
				new Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.ACACP();
		contexto.setSTDTRNIPARMV(contextoEntrada);
		contexto.setTRCONSTITOTALACEVTY(cuerpoContexto);
		cuerpoContexto.setACACP(acuerdo);
		super.initialize(contexto);
		
		acuerdo.setCODNRBEEN(super.getEntidad());
		acuerdo.setNUMSECAC(numeroCuenta);
		
		cuerpoContexto.getACVTIPOCOBROV().setSTDCHAR02(ACV_TIPO_COBRO_V);
		cuerpoContexto.getACVLIMCONCEDIDOV().setIMPLIMITE(consultaLimiteConcedidoBackEnd.ejecutarTRN(numeroCuenta));
		IntegerToDateConverter itdc = new IntegerToDateConverter();
		cuerpoContexto.getFECHAV().setSTDFECHA(itdc.convertFrom(fechaConstitucion));
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_CONSTI_TOTAL_AC_TRN);
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		
		contexto.getRESPUESTASINCRONAV().setSTDCHAR01(respuestaSincrona);
		contexto.getNROLLAMADAV().setSTDCHAR01(numeroLLamada.toString());
		
		for(int i = 0; i < NUM_PSVERRORV; i++){
			Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.PSVERRORV errorv = 
					new Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.PSVERRORV();
			super.initialize(errorv);
			cuerpoContexto.getPSVERRORV().add(errorv);
		}
		
		for(int i = 0; i < NUM_AR_TRN_MSJ_PARM_V; i++){
			Ejecutar.TRCONSTITOTALACTRNI.STDAUTORIZAV.ARTRNMSJPARMV parmv = 
					new Ejecutar.TRCONSTITOTALACTRNI.STDAUTORIZAV.ARTRNMSJPARMV();
			super.initialize(parmv);
			contexto.getSTDAUTORIZAV().getARTRNMSJPARMV().add(parmv);
		}
		
		for(int i = 0; i < NUM_STD_TARGET_TERMINAL_V; i++){
			Ejecutar.TRCONSTITOTALACTRNI.STDAUTORIZAV.STDTARGETTERMINALV terminal = 
					new Ejecutar.TRCONSTITOTALACTRNI.STDAUTORIZAV.STDTARGETTERMINALV();
			super.initialize(terminal);
			contexto.getSTDAUTORIZAV().getSTDTARGETTERMINALV().add(terminal);
		}
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.TRCONSTITOTALACTRNI contexto){
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConstituirCuentaServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de constituciòn de "
					+ "cuenta.", e);
		}
		return respuesta;
	}
	
	
	 /** Función que verifica si una respuesta del servicio web contiene datos.
	 * 
	 * @param respuesta Respuesta del servicio web
	 * @return <code>true</code> Si la respuesta no esta vacía.
	 */
	private Boolean verificaResponseBansefi(EjecutarResult respuesta){
		Boolean noNulo = false;
		if(respuesta != null && respuesta.getResponseBansefi() != null){
			noNulo = true;
		}
		return noNulo;
	}
}
