package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.DatosAmpliadosBean;
import mx.babel.bansefi.banksystem.base.beans.ParametrosBusquedaApunteBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.base.utils.StringToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaampliadaapunte.ConsultaAmpliadaApunteServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaampliadaapunte.Ejecutar.ITRCONSDETALLEAFTRNI;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaampliadaapunte.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaampliadaapunte.ResponseBansefi.OTRCONSDETALLEAFTRNO.TRCONSDETALLEAFEVTZ;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaampliadaapunte.ResponseBansefi.OTRCONSDETALLEAFTRNO.TRCONSDETALLEAFEVTZ.AFINFDRVDE;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaampliadaapunte.ResponseBansefi.OTRCONSDETALLEAFTRNO.TRCONSDETALLEAFEVTZ.AFPLNFCNC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para el servicio de consulta ampliada de un apunte.
 * 
 * @author omar.marquez
 * 
 */
@Component
public class ConsultaAmpliadaApunteBackEnd extends BackEndBean {

	private static final long serialVersionUID = 4741306099001034201L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Método que ejecuta la TRN de consulta ampliada de un apunte.
	 * 
	 * @param parametrosBean
	 * @return parametrosBean con más datos
	 */
	public ParametrosBusquedaApunteBean ejecutarTRN(ParametrosBusquedaApunteBean parametrosBean){
		
		ITRCONSDETALLEAFTRNI itrconsdetalleaftrni = initPeticion(parametrosBean);

		EjecutarResult resultado = (EjecutarResult) servicioWebUtils
				.ejecutarWS(ConsultaAmpliadaApunteServicio.class,
						itrconsdetalleaftrni);

		super.verificaRespuesta(resultado);


		return getDatosAmpliados(parametrosBean, resultado.getResponseBansefi()
				.getOTRCONSDETALLEAFTRNO().getTRCONSDETALLEAFEVTZ());
	}

	/**
	 * Método privado que prepara los datos que serán enviados a la TRN.
	 * 
	 * @param parametrosBean
	 * @return parametros de entrada encapsulados en un objeto
	 *         ITRCONSDETALLEAFTRNI
	 */
	private ITRCONSDETALLEAFTRNI initPeticion(ParametrosBusquedaApunteBean parametrosBean){
		ITRCONSDETALLEAFTRNI itrconsdetalleaftrni = new ITRCONSDETALLEAFTRNI();

		super.initialize(itrconsdetalleaftrni);

		itrconsdetalleaftrni.getTRCONSDETALLEAFEVTY().getIDFRAFAPNTEV()
				.setCODNRBEEN(super.getEntidad());
		itrconsdetalleaftrni.getTRCONSDETALLEAFEVTY().getIDFRAFAPNTEV()
				.setPRPDADCTA(ConstantesFuncionales.PRPDAD_CTA);
		itrconsdetalleaftrni.getTRCONSDETALLEAFEVTY().getIDFRAFAPNTEV()
				.setNUMSECAC(parametrosBean.getCuentaBean().getNumeroCuenta());
		itrconsdetalleaftrni.getTRCONSDETALLEAFEVTY().getIDFRAFAPNTEV()
				.setCODCTA(parametrosBean.getFiltroTipoCuenta());
		itrconsdetalleaftrni.getTRCONSDETALLEAFEVTY().getIDFRAFAPNTEV()
				.setCODNUMRCOMONEDA(ConstantesFuncionales.COD_MONEDA);
		itrconsdetalleaftrni.getTRCONSDETALLEAFEVTY().getIDFRAFAPNTEV()
				.setNUMSEC(parametrosBean.getApunteSeleccionado().getNumSec());

		itrconsdetalleaftrni.getSTDTRNIPARMV().setIDINTERNOTERMTN(
				super.getTerminal());
		itrconsdetalleaftrni.getSTDTRNIPARMV().setCODTX(
				CodTxConstants.COD_TX_TR_CONS_DETALLE_AF_TRN);

		return itrconsdetalleaftrni;
	}

	/**
	 * Método privado que devuelve los datos de la consulta ampliada.
	 * 
	 * @param parametrosBean
	 * @param elemento
	 * @return parametrosBean con más atributos
	 * @throws NullPointerException
	 */
	private ParametrosBusquedaApunteBean getDatosAmpliados(
			ParametrosBusquedaApunteBean parametrosBean, TRCONSDETALLEAFEVTZ elemento)
			throws NullPointerException {
		// mapear tabla tipo transaccion		
		if (elemento != null && elemento.getAFPLNFCNC() != null) {
			
			for(AFPLNFCNC result: elemento.getAFPLNFCNC()){
				DatosAmpliadosBean detalleTipoTranPlanif = new DatosAmpliadosBean();
				if(result.getCODTX() != null && !result.getCODTX().trim().isEmpty()){
					detalleTipoTranPlanif.setCodInf(result.getCODTX());
				}
				if(result.getFECHAPLANIF() != 0){										
					IntegerToDateConverter converter1 = new IntegerToDateConverter();
					StringToDateConverter converter2 = new StringToDateConverter();
					detalleTipoTranPlanif.setValorInf(converter2.convertFrom(converter1.convertTo(result.getFECHAPLANIF())));
				}
				if(detalleTipoTranPlanif.getCodInf() != null && detalleTipoTranPlanif.getValorInf() != null){
					parametrosBean.getApunteSeleccionado().getDetalleTipoTranPlanifApunte().add(detalleTipoTranPlanif);
				}
			}
			
			
		}
		
		//mapear tabla info adicional apunte
		
		if (elemento != null && elemento.getAFINFDRVDE() != null) {
			
			for(AFINFDRVDE result: elemento.getAFINFDRVDE()){
				DatosAmpliadosBean detalleInfAdic = new DatosAmpliadosBean();
				if(result.getCODINFDRVDAF() != null && !result.getCODINFDRVDAF().trim().isEmpty()){
					detalleInfAdic.setCodInf(result.getCODINFDRVDAF());
				}
				if(result.getVALORINFDRVDAF() != null && !result.getVALORINFDRVDAF().trim().isEmpty()){
					detalleInfAdic.setValorInf(result.getVALORINFDRVDAF());
				}
				if(detalleInfAdic.getCodInf() != null && detalleInfAdic.getValorInf() != null){
					parametrosBean.getApunteSeleccionado().getDetalleInfoAdicionalApunte().add(detalleInfAdic);
				}
			}
			
			
		}
		
		return parametrosBean;
	}
	
}