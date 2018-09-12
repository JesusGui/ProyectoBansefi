package mx.babel.bansefi.banksystem.cajas.backend;

import java.math.BigDecimal;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ParrillaBean;
import mx.babel.bansefi.banksystem.cajas.beans.RecogidaEfectivoBean;
import mx.babel.bansefi.banksystem.cajas.webservice.altapeticionefectivo.AltaPeticionEfectivoServicio;
import mx.babel.bansefi.banksystem.cajas.webservice.altapeticionefectivo.Ejecutar;
import mx.babel.bansefi.banksystem.cajas.webservice.altapeticionefectivo.EjecutarResult;
import mx.babel.bansefi.banksystem.cajas.webservice.altapeticionefectivo.ResponseBansefi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AltaRecogidaEfectivoBackEnd extends BackEndBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2529647828892518673L;
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	private RecogidaEfectivoBean recogidaEfectivoBean;
	
	public RecogidaEfectivoBean getRecogidaEfectivoBean() {
		return recogidaEfectivoBean;
	}

	public void setRecogidaEfectivoBean(RecogidaEfectivoBean recogidaEfectivoBean) {
		this.recogidaEfectivoBean = recogidaEfectivoBean;
	}

	public int ejecutarTRN(RecogidaEfectivoBean recogidaEfectivoBean, int tipoPeticion, int fechaRecogida, ParrillaBean parrilla){
		
		int codigoRetorno = 0;
		
		Ejecutar.ITRALTASOLCTEFCTSMTR contexto = initPeticion(recogidaEfectivoBean, tipoPeticion, fechaRecogida, parrilla);
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
			codigoRetorno = RETURN_COD_OK;
		}catch (ControlableException ce){
			if(ce.getRtncod() != RETURN_COD_OK){
				throw ce;
			}
		}
		
		if(verificaRespuesta(respuesta)){
			getDatos(respuesta);
		}
		return codigoRetorno;
	}
	
	private Ejecutar.ITRALTASOLCTEFCTSMTR initPeticion(RecogidaEfectivoBean recogidaEfectivoBean, int tipoPeticion, int fechaRecogida, ParrillaBean parrilla){
		Ejecutar.ITRALTASOLCTEFCTSMTR contexto = new Ejecutar.ITRALTASOLCTEFCTSMTR();
		
		super.initialize(contexto);
		
		contexto.setELEVATORPOSITION(0);
		contexto.setSCROLLABLEOCCURS(0);
		
		Ejecutar.ITRALTASOLCTEFCTSMTR.TRALTASOLCTEFCTSMEVT nivel1_1 = new Ejecutar.ITRALTASOLCTEFCTSMTR.TRALTASOLCTEFCTSMEVT();
		Ejecutar.ITRALTASOLCTEFCTSMTR.STDTRNIPARMV nivel1_2 = new Ejecutar.ITRALTASOLCTEFCTSMTR.STDTRNIPARMV();
		
		Ejecutar.ITRALTASOLCTEFCTSMTR.TRALTASOLCTEFCTSMEVT.SMURGENTEV nivel2_1 = new Ejecutar.ITRALTASOLCTEFCTSMTR.TRALTASOLCTEFCTSMEVT.SMURGENTEV();
		Ejecutar.ITRALTASOLCTEFCTSMTR.TRALTASOLCTEFCTSMEVT.SMSOLCTMONEDAE nivel2_2 = new Ejecutar.ITRALTASOLCTEFCTSMTR.TRALTASOLCTEFCTSMEVT.SMSOLCTMONEDAE();
		Ejecutar.ITRALTASOLCTEFCTSMTR.TRALTASOLCTEFCTSMEVT.SMSOLCTDESGLSE nivel2_3 = new Ejecutar.ITRALTASOLCTEFCTSMTR.TRALTASOLCTEFCTSMEVT.SMSOLCTDESGLSE();
		
		if(tipoPeticion == 1){
			nivel2_1.setSTDCHAR01("");
		}else{
			nivel2_1.setSTDCHAR01("X");
		}
		
		nivel1_1.setSMURGENTEV(nivel2_1);
		nivel2_2.setCODNRBEEN(super.getEntidad());
		nivel2_2.setCODINTERNOUO(super.getSucursal());
		nivel2_2.setFECHASOLCTSM(fechaRecogida);
		nivel2_2.setCODPPL("M");
		nivel2_2.setCODDISTRIB("T");
		nivel2_2.setINDURG(0);
		nivel2_2.setCODINTERNOUO1("");
		nivel2_2.setCODINTERNOUO2("");
		nivel2_2.setCODECVSM("");
		nivel2_2.setFECHAABASTREC(0);
		nivel2_2.setHORAABASTREC(0);
		nivel2_2.setFECHAPROCESOSM(0);
		nivel2_2.setIMPAUTV(new BigDecimal(0.00));
		nivel2_2.setIMPPEDIDOV(recogidaEfectivoBean.getTotalRecogida());
		nivel2_2.setIMPRECBDOV(new BigDecimal(0.00));
		nivel2_2.setIMPCERTFDOV(new BigDecimal(0.00));
		nivel2_2.setINDSMCUADRE("");
		nivel2_2.setINDMOTIVOCUADRE("");
		nivel2_2.setIDINTERNOEMPLEP("");
		nivel2_2.setVALOROBSERSM(recogidaEfectivoBean.getObservacion());
		
		nivel1_1.setSMSOLCTMONEDAE(nivel2_2);
		
		contexto.setTRALTASOLCTEFCTSMEVT(nivel1_1);
		
		for(ExistenciaDenominacionBean existenciaDenominacionBean : parrilla.getListaDenominaciones()){
			
			if(existenciaDenominacionBean.getImporteAEnviar().compareTo(BigDecimal.ZERO) > 0){
				nivel2_3 = new Ejecutar.ITRALTASOLCTEFCTSMTR.TRALTASOLCTEFCTSMEVT.SMSOLCTDESGLSE();
				
				nivel2_3.setINDURG(0);
				nivel2_3.setCODDSTN(existenciaDenominacionBean.getOrigen());
				nivel2_3.setCODVALORFACIAL(existenciaDenominacionBean.getValorFacial());
				nivel2_3.setIMPPEDIDO(existenciaDenominacionBean.getImporteAEnviar());	
				
				contexto.getTRALTASOLCTEFCTSMEVT().getSMSOLCTDESGLSE().add(nivel2_3);
			}
		}
		
		nivel1_2.setIDINTERNOTERMTN(super.getTerminal());
		nivel1_2.setNUMSEC(0);;
		nivel1_2.setCODTX("VCM04MOU");
		nivel1_2.setCODTXDI("CO06");
		
		contexto.setSTDTRNIPARMV(nivel1_2);
		
		return contexto;
	}
	
	private EjecutarResult ejecutarWS(Ejecutar.ITRALTASOLCTEFCTSMTR contexto){
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(AltaPeticionEfectivoServicio.class, contexto);
		}catch(NoControlableException nce){
			throw new NoControlableException("No se pudo invocar el servicio"
					+ " de alta de recogida de efectivo.", nce);
		}
		
		return respuesta;
	}
	
	private Boolean verificaRespuesta(EjecutarResult respuesta){
		Boolean noNulo = false;
		
		if(respuesta != null
				&& respuesta.getResponseBansefi() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
	private Boolean verificaResponseBansefi(ResponseBansefi response){
		Boolean noNulo = false;
		
		if(response.getOTRALTASOLCTEFCTSMTR() != null
				&& response.getOTRALTASOLCTEFCTSMTR().getSTDTRNMSJPARMV() != null
				&& response.getOTRALTASOLCTEFCTSMTR().getSTDTRNOPARMV() != null
				&& response.getOTRALTASOLCTEFCTSMTR().getTRALTASOLCTEFCTSMEVT() != null
				&& response.getOTRALTASOLCTEFCTSMTR().getTRALTASOLCTEFCTSMEVT().getCCAINDMSJV() != null
				&& response.getOTRALTASOLCTEFCTSMTR().getTRALTASOLCTEFCTSMEVT().getMSJOINEV() != null
				&& response.getOTRALTASOLCTEFCTSMTR().getTRALTASOLCTEFCTSMEVT().getSMSOLCTMONEDAE() != null){
			noNulo = true;
		}
		
		return noNulo;
	}
	
	private void getDatos(EjecutarResult respuesta){
		if(verificaResponseBansefi(respuesta.getResponseBansefi())){
			this.recogidaEfectivoBean = new RecogidaEfectivoBean();
			this.recogidaEfectivoBean.setEstatusC(respuesta.getResponseBansefi().getOTRALTASOLCTEFCTSMTR().getTRALTASOLCTEFCTSMEVT().getSMSOLCTMONEDAE().getCODECVSM());
			this.recogidaEfectivoBean.setIndicadorUrgencia(respuesta.getResponseBansefi().getOTRALTASOLCTEFCTSMTR().getTRALTASOLCTEFCTSMEVT().getSMSOLCTMONEDAE().getINDURG());
		}
	}
}