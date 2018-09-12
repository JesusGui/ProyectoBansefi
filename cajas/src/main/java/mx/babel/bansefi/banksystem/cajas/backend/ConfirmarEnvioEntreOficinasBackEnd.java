package mx.babel.bansefi.banksystem.cajas.backend;

import java.math.BigDecimal;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;
import mx.babel.bansefi.banksystem.cajas.beans.EnvioEntreOficinasBean;
import mx.babel.bansefi.banksystem.cajas.webservice.confirmarenvioentreoficinas.ConfirmarEnvioEntreOficinasServicio;
import mx.babel.bansefi.banksystem.cajas.webservice.confirmarenvioentreoficinas.Ejecutar;
import mx.babel.bansefi.banksystem.cajas.webservice.confirmarenvioentreoficinas.EjecutarResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConfirmarEnvioEntreOficinasBackEnd extends BackEndBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3467675170295936462L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	public Integer ejecutarTRN(EnvioEntreOficinasBean envioEntreOficinasBean){
		Ejecutar.ITRCONFENVIOOFCNASMT contexto = initPeticion(envioEntreOficinasBean);
		EjecutarResult respuesta = ejecutarWS(contexto);
		Integer codigoRetorno = 0;
		
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if(ce.getRtncod() != RETURN_COD_OK){
				throw ce;
			}
		}
		if(verificaRespuesta(respuesta)){
			codigoRetorno = respuesta.getResponseBansefi().getOTRCONFENVIOOFCNASMT().getRTRNCD();
			envioEntreOficinasBean.setEstatusC(respuesta.getResponseBansefi().getOTRCONFENVIOOFCNASMT().getTRCONFENVIOOFCNASMEV().getSMSOLCTMONEDAE().getCODECVSM());
		}
		
		return codigoRetorno;
		
	}
	
	private Ejecutar.ITRCONFENVIOOFCNASMT initPeticion(EnvioEntreOficinasBean envioEntreOficinasBean){
		Ejecutar.ITRCONFENVIOOFCNASMT contexto = new Ejecutar.ITRCONFENVIOOFCNASMT();
		
		Ejecutar.ITRCONFENVIOOFCNASMT.TRCONFENVIOOFCNASMEV nivel1_1 = new Ejecutar.ITRCONFENVIOOFCNASMT.TRCONFENVIOOFCNASMEV();
		Ejecutar.ITRCONFENVIOOFCNASMT.SMPEDIDOV nivel1_2 = new Ejecutar.ITRCONFENVIOOFCNASMT.SMPEDIDOV();
		Ejecutar.ITRCONFENVIOOFCNASMT.SMSIGNOCTBLE nivel1_3 = new Ejecutar.ITRCONFENVIOOFCNASMT.SMSIGNOCTBLE();
		Ejecutar.ITRCONFENVIOOFCNASMT.STDTRNIPARMV nivel1_4 = new Ejecutar.ITRCONFENVIOOFCNASMT.STDTRNIPARMV();
		Ejecutar.ITRCONFENVIOOFCNASMT.STDAUTORIZAV nivel1_5 = new Ejecutar.ITRCONFENVIOOFCNASMT.STDAUTORIZAV();
		
		
		
		Ejecutar.ITRCONFENVIOOFCNASMT.TRCONFENVIOOFCNASMEV.SMSOLCTMONEDAE nivel2_1 = new Ejecutar.ITRCONFENVIOOFCNASMT.TRCONFENVIOOFCNASMEV.SMSOLCTMONEDAE();
		Ejecutar.ITRCONFENVIOOFCNASMT.TRCONFENVIOOFCNASMEV.SMSOLCTDESGLSE nivel2_2;
		
		Ejecutar.ITRCONFENVIOOFCNASMT.STDAUTORIZAV.ARAUTREMOTAP nivel3_1 = new Ejecutar.ITRCONFENVIOOFCNASMT.STDAUTORIZAV.ARAUTREMOTAP();
		Ejecutar.ITRCONFENVIOOFCNASMT.STDAUTORIZAV.ARIDSALTADOV nivel3_2 = new Ejecutar.ITRCONFENVIOOFCNASMT.STDAUTORIZAV.ARIDSALTADOV();
		
		
		super.initialize(contexto);
		
		nivel2_1.setCODNRBEEN(super.getEntidad());
		nivel2_1.setCODINTERNOUO(super.getSucursal());
		nivel2_1.setFECHASOLCTSM(envioEntreOficinasBean.getFechaContableEnvioInteger());
		nivel2_1.setCODPPL("M");
		nivel2_1.setCODDISTRIB("V");
		nivel2_1.setINDURG(0);
		nivel2_1.setCODINTERNOUO1(envioEntreOficinasBean.getCentroDestino());
		nivel2_1.setCODINTERNOUO2(envioEntreOficinasBean.getCentroControlador());
		nivel2_1.setCODECVSM(envioEntreOficinasBean.getEstatusC());
		nivel2_1.setFECHAABASTREC(0);
		nivel2_1.setHORAABASTREC(0);
		nivel2_1.setFECHAPROCESOSM(0);
		nivel2_1.setIMPAUTV(BigDecimal.ZERO);
		nivel2_1.setIMPPEDIDOV(envioEntreOficinasBean.getTotalAEnviar());
		nivel2_1.setIMPRECBDOV(BigDecimal.ZERO);
		nivel2_1.setIMPCERTFDOV(BigDecimal.ZERO);
		nivel2_1.setIDINTERNOEMPLEP(super.getUsuarioId());
		nivel2_1.setVALOROBSERSM(envioEntreOficinasBean.getObservaciones());
		
		nivel1_1.setSMSOLCTMONEDAE(nivel2_1);
		
		for(ExistenciaDenominacionBean existenciaDenominacionBean : envioEntreOficinasBean.getParrilla().getListaDenominaciones()){
			if(existenciaDenominacionBean.getValorFacial() != null
					&& existenciaDenominacionBean.getImporteAEnviar() != null
					&& existenciaDenominacionBean.getImporteAEnviar().compareTo(BigDecimal.ZERO) > 0){
				nivel2_2 = new Ejecutar.ITRCONFENVIOOFCNASMT.TRCONFENVIOOFCNASMEV.SMSOLCTDESGLSE();
				
				nivel2_2.setCODINTERNOUO(super.getSucursal());
				nivel2_2.setFECHASOLCTSM(envioEntreOficinasBean.getFechaContableEnvioInteger());
				nivel2_2.setINDURG(0);
				nivel2_2.setCODINTERNOUO1(envioEntreOficinasBean.getCentroDestino());
				nivel2_2.setCODDSTN(existenciaDenominacionBean.getOrigen());
				nivel2_2.setCODVALORFACIAL(existenciaDenominacionBean.getValorFacial());
				nivel2_2.setIMPAUT(new BigDecimal(0.00));
				nivel2_2.setIMPPEDIDO(existenciaDenominacionBean.getImporteAEnviar());
				nivel2_2.setIMPRECBDO(new BigDecimal(0.00));
				nivel2_2.setNUMPRECINTO(existenciaDenominacionBean.getPrecinto());
				
				nivel1_1.getSMSOLCTDESGLSE().add(nivel2_2);
			}
		}
		
		nivel1_2.setIMPPEDIDOV(envioEntreOficinasBean.getTotalAEnviar());
		
		nivel1_3.setSTDCHAR02("CH");
		
		nivel1_4.setIDINTERNOTERMTN(super.getTerminal());
		nivel1_4.setIDEMPLAUT("");
		nivel1_4.setNUMSEC(0);
		nivel1_4.setCODTX("VCO18OOU");
		nivel1_4.setCODTXDI("");
		
		nivel1_5.setINDBORRADOAR("");
		nivel1_5.setDESCRTX("");
		nivel1_5.setINDAUTSOLIC("");
		nivel1_5.setINDATRIBMANCEP("");
		nivel1_5.setCODESTADOAR("");
		nivel1_5.setIDEMPLSOLAUT("");
		nivel1_5.setINDVERIFATRIB("");
		nivel1_5.setINDURGAR("");
		nivel1_5.setMOTIVOACCIONAUT("");
		nivel1_5.setINDESCALABLE("");
		nivel1_5.setIMPAUT(new BigDecimal(0.00));
		nivel1_5.setIMPORTEAR(new BigDecimal(0.00));
		
		nivel3_1.setCODNRBEEN("");
		nivel3_1.setFECHAOPRCN(0);
		nivel3_1.setHORAOPRCN(0);
		nivel3_1.setIDINTERNOTERMTN("");
		nivel1_5.setARAUTREMOTAP(nivel3_1);
		
		nivel3_2.setIDINTERNOEMPLEP("");
		nivel1_5.setARIDSALTADOV(nivel3_2);
		
		contexto.setELEVATORPOSITION(0);
		contexto.setSCROLLABLEOCCURS(0);
		contexto.setTRCONFENVIOOFCNASMEV(nivel1_1);
		contexto.setSMPEDIDOV(nivel1_2);
		contexto.setSMSIGNOCTBLE(nivel1_3);
		contexto.setSTDTRNIPARMV(nivel1_4);
		contexto.setSTDAUTORIZAV(nivel1_5);
		
		return contexto;
	}
	
	private EjecutarResult ejecutarWS(Ejecutar.ITRCONFENVIOOFCNASMT contexto){
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConfirmarEnvioEntreOficinasServicio.class, contexto);
		}catch (NoControlableException nce){
			throw new NoControlableException("No se pudo invocar el servicio de"
					+ " confirmar envío entre oficinas.", nce);
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

}
