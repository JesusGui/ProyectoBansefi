package mx.babel.bansefi.banksystem.oficina.backends;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.EstadoEnum;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.ApuntesBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.FechaUtils;
import mx.babel.bansefi.banksystem.oficina.webservices.altaapuntes.AltaApuntesServicio;
import mx.babel.bansefi.banksystem.oficina.webservices.altaapuntes.Ejecutar;
import mx.babel.bansefi.banksystem.oficina.webservices.altaapuntes.Ejecutar.ITRIMPUTACCTASBANCOST;
import mx.babel.bansefi.banksystem.oficina.webservices.altaapuntes.EjecutarResult;
import mx.babel.bansefi.banksystem.oficina.webservices.altaapuntes.ResponseBansefi;
import mx.babel.bansefi.banksystem.oficina.webservices.altaapuntes.ResponseBansefi.OTRIMPUTACCTASBANCOST;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AltaApuntesBackEnd extends BackEndBean {

	private static final long serialVersionUID = -2859325215916916135L;
	private static final Logger LOGGER = LogManager.getLogger(AltaApuntesBackEnd.class);
	
	private static final int ERROR_TIPO_CUENTA = 8429;
	private static final String DATE_FORMAT = "yyyyMMdd";

	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	FechaUtils fechaUtils;
	DateFormat df = new SimpleDateFormat("dd/mm/yyy");
		
	/**
	 * Método que recibe un bean de Alta de apuntes y reliza el alta del apunte.
	 * @param apunte Apuntesbean 
	 * @return <code>true</code> si la operaciòn fue realizada con èxito.
	 */
	public ApuntesBean ejecutarTRN(ApuntesBean apunte){
		if(apunte != null){
			
			int codigoRetorno = 0;
			
			Ejecutar.ITRIMPUTACCTASBANCOST contexto = initPeticion(apunte);
			EjecutarResult respuesta = ejecutarWS(contexto);
			
			try{
				super.verificaRespuesta(respuesta);
				codigoRetorno = RETURN_COD_OK;
			}catch (ControlableException ce){
				if(ce.getRtncod() != RETURN_COD_OK){
					throw ce;
				}
			}
				
			if(verificaResponseBansefi(respuesta)){
				return altaCorrecta(respuesta.getResponseBansefi(), apunte);
			}
		}
		return apunte;
	}
	
	/**
	 * Método que envía la operación de Alta de apunte
	 * @param response
	 * @param apunte
	 * @return
	 */
	private ApuntesBean altaCorrecta(ResponseBansefi response,
			ApuntesBean apunte) {
		if (verificaResultado(response)) {
			apunte.setStatus(0);
			if(verificaRespuestaCliente(response)){
				return formatearRespuesta(response, apunte);
			}
		} else {
			apunte.setStatus(AltaApuntesBackEnd.ERROR_TIPO_CUENTA);
		}
		
		return apunte;
	}
	
	/**
	 * Crea un nuevo ApuntesBean con los datos de la respuesta
	 * @param response
	 * @return
	 */
	private ApuntesBean formatearRespuesta(ResponseBansefi response, ApuntesBean apunte) {
		 OTRIMPUTACCTASBANCOST datosRespuesta = response.getOTRIMPUTACCTASBANCOST();
		 if (datosRespuesta.getTRIMPUTACCTASBANCOSEV().getPSVTITULARV().getNOMB50() != null ){
			 apunte.setTitular(datosRespuesta.getTRIMPUTACCTASBANCOSEV().getPSVTITULARV().getNOMB50());
		 }
		
		apunte.setNumTransaccion(String.valueOf(datosRespuesta.getTRIMPUTACCTASBANCOSEV().getPSVAPUNTEV().getNUMSEC()));
		apunte.setHoraOperacion(this.getHora(datosRespuesta.getSTDTRNOPARMV().getHORAOPRCN()));
		apunte.setFechaOperacion(this.getFecha(datosRespuesta.getSTDTRNOPARMV().getFECHAOPRCN()));
		apunte.setImporte(datosRespuesta.getTRIMPUTACCTASBANCOSEV().getIMPAPNTE());
		apunte.setTerminal(super.getTerminal());
		apunte.setPuesto(super.getTerminal().substring(super.getTerminal().length() - 2));
		apunte.setCentro(super.getSucursal());
		return apunte;
	}

	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con los datos
	 * de la persona física.
	 * 
	 * @param response Respuesta Bansefi con datos de la persona moral
	 * @return <code>true</code> si la respuesta Bansefi contiene elemento no nulos
	 */
	private boolean verificaRespuestaCliente(ResponseBansefi response) {
		Boolean noNulo = false;
		if(response!= null && response.getOTRIMPUTACCTASBANCOST() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
	/**
	 * Método que verifica los códigos de respuesta entregados por el servicio web.
	 * 
	 * @param response Respuesta Bansefi con mensajes
	 * @throws NoControlableException Excepción controlada de errores en front end
	 * @throws ControlableException Excepción no controlada de errores en host
	 */
	private boolean verificaResultado(ResponseBansefi response) {
		int codigo = 1;
		if(response.getOTRIMPUTACCTASBANCOST() !=null && response.getOTRIMPUTACCTASBANCOST().getRTRNCD() != 1){
			for (ResponseBansefi.OTRIMPUTACCTASBANCOST.STDTRNMSJPARMV mensaje : 
				response.getOTRIMPUTACCTASBANCOST().getSTDTRNMSJPARMV()) {
				if (mensaje !=null){
					codigo = mensaje.getTEXTCODE();
					break;
				}
			}
		}
		if (codigo != 1) {
			if (AltaApuntesBackEnd.ERROR_TIPO_CUENTA == codigo) {
				return false;
			} else {
				EstadoEnum.mensajesError("trn", codigo);
			}
		} 
		
		return true;
	}

	/**
	 * Función que valida que la respues del servidor no este vacía. 
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
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(ITRIMPUTACCTASBANCOST contexto) {
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					AltaApuntesServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de alta "
					+ "de apuntes.", e);
		}
		return respuesta;
	}

	private ITRIMPUTACCTASBANCOST initPeticion(ApuntesBean apunteBean) {
		Ejecutar.ITRIMPUTACCTASBANCOST contexto = new Ejecutar.ITRIMPUTACCTASBANCOST();
		
		Ejecutar.ITRIMPUTACCTASBANCOST.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRIMPUTACCTASBANCOST.STDTRNIPARMV();
		
		Ejecutar.ITRIMPUTACCTASBANCOST.TRIMPUTACCTASBANCOSEV apunte = 
				new Ejecutar.ITRIMPUTACCTASBANCOST.TRIMPUTACCTASBANCOSEV();
		
		Ejecutar.ITRIMPUTACCTASBANCOST.TRIMPUTACCTASBANCOSEV.PSVCODNUMRCOMONEDAV moneda = 
				new Ejecutar.ITRIMPUTACCTASBANCOST.TRIMPUTACCTASBANCOSEV.PSVCODNUMRCOMONEDAV();
		
//		Ejecutar.ITRIMPUTACCTASBANCOST.TRIMPUTACCTASBANCOSEV.PSVACCIONCTASBANCOSV accionBancos = 
//				new Ejecutar.ITRIMPUTACCTASBANCOST.TRIMPUTACCTASBANCOSEV.PSVACCIONCTASBANCOSV();
		
		Ejecutar.ITRIMPUTACCTASBANCOST.TRIMPUTACCTASBANCOSEV.PSVAPUNTEV apunteV = 
				new Ejecutar.ITRIMPUTACCTASBANCOST.TRIMPUTACCTASBANCOSEV.PSVAPUNTEV();
		
		Ejecutar.ITRIMPUTACCTASBANCOST.TRIMPUTACCTASBANCOSEV.PSVTPCLOPV clop = 
				new Ejecutar.ITRIMPUTACCTASBANCOST.TRIMPUTACCTASBANCOSEV.PSVTPCLOPV();
		
		super.initialize(contexto);
		
		contextoEntrada.setCODTX(obtenerCodigo(apunteBean.getTipoApunte()));
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());

		// Fecha valor
		apunte.setFECHAVALOR(Integer.parseInt(FechaUtils.formatFecha(apunteBean.getFechaValor(), AltaApuntesBackEnd.DATE_FORMAT)));
		// Importe
		apunte.setIMPAPNTE(apunteBean.getImporte());
		// Tipo operacion
		apunte.setTIPOOPRCN(apunteBean.getTipoApunte());
		// Concepto
		apunte.setTEXTOREMITENTE(apunteBean.getConcepto());
		
		// Moneda
		moneda.setCODNUMRCOMONEDA("MXN");
		apunte.setPSVCODNUMRCOMONEDAV(moneda);
		
		// TODO: CLOP
		clop.setCODCLOPSIST("01");
		clop.setTIPOSBCLOP("0000");
		apunte.setPSVTPCLOPV(clop);
		
		//TODO: APUNTEV
		apunteV.setCODNRBEEN(super.getEntidad());
		apunteV.setCODCTA("");
		apunteV.setCODNUMRCOMONEDA("MXN");
		apunteV.setNUMSECAC(apunteBean.getNumCuenta());
		apunteV.setPRPDADCTA("");
		apunte.setPSVAPUNTEV(apunteV);
		
		contexto.setTRIMPUTACCTASBANCOSEV(apunte);
		contexto.setSTDTRNIPARMV(contextoEntrada);
		
		return contexto;
	}
	
	/**
	 * Método para obtener el código de la transacción según el tipo de operación
	 * @param tipoApunte
	 * @return
	 */
	private String obtenerCodigo(String tipoApunte) {
		
		if ("C".equals(tipoApunte)) {
			return CodTxConstants.COD_TX_TR_IMPUTAC_CTAS_BANCOS_TRN_CARGO;
		}
		return CodTxConstants.COD_TX_TR_IMPUTAC_CTAS_BANCOS_TRN_ABONO;
	}
	

	private Date getFecha(int fecha) { 
		DateFormat formatter = new SimpleDateFormat("yyyymmdd");
		Date date = null;
		try {
			date = formatter.parse(String.valueOf(fecha));
		} catch (ParseException e) {
			LOGGER.error("Error al parsear la fecha",e);
		}
		return date;
	}
	
	private String getHora(int hora) {
		if (hora != 0) {
			String horaFormat = String.valueOf(hora);
			return horaFormat.substring(0, 2) + ":" + horaFormat.substring(2, 4) +
					":" + horaFormat.substring(4, 6);
		}
		
		return null;
	}

	public static int getErrorTipoCuenta() {
		return ERROR_TIPO_CUENTA;
	}

}
