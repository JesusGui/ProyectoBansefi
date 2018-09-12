package mx.babel.bansefi.banksystem.cajas.backend;

import java.math.BigDecimal;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;
import mx.babel.bansefi.banksystem.cajas.beans.EnvioEntreOficinasBean;
import mx.babel.bansefi.banksystem.cajas.webservice.modificarenviooficinas.Ejecutar;
import mx.babel.bansefi.banksystem.cajas.webservice.modificarenviooficinas.EjecutarResult;
import mx.babel.bansefi.banksystem.cajas.webservice.modificarenviooficinas.ModificarEnvioEntreOficinasServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModificarEnvioEntreOficinasBackEnd extends BackEndBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 538145309183456741L;
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	public Integer ejecutarTRN(EnvioEntreOficinasBean envioEntreOficinasBean){
		Integer codigoRetorno = 0;
		Ejecutar.ITRMDFENVIOOFCNASMTR contexto = initPeticion(envioEntreOficinasBean);
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if(ce.getRtncod() != RETURN_COD_OK){
				throw ce;
			}
		}
		if(verificaRespuesta(respuesta)){
			codigoRetorno = respuesta.getResponseBansefi().getOTRMDFENVIOOFCNASMTR().getRTRNCD();
		}
		
		return codigoRetorno;
	}
	
	private Ejecutar.ITRMDFENVIOOFCNASMTR initPeticion(EnvioEntreOficinasBean envioEntreOficinasBean){
		Ejecutar.ITRMDFENVIOOFCNASMTR contexto = new Ejecutar.ITRMDFENVIOOFCNASMTR();
		
		Ejecutar.ITRMDFENVIOOFCNASMTR.TRMDFENVIOOFCNASMEVT nivel1_1 = new Ejecutar.ITRMDFENVIOOFCNASMTR.TRMDFENVIOOFCNASMEVT();
		Ejecutar.ITRMDFENVIOOFCNASMTR.STDTRNIPARMV nivel1_2 = new Ejecutar.ITRMDFENVIOOFCNASMTR.STDTRNIPARMV();
		
		Ejecutar.ITRMDFENVIOOFCNASMTR.TRMDFENVIOOFCNASMEVT.SMSOLCTMONEDAE nivel2_1 = new Ejecutar.ITRMDFENVIOOFCNASMTR.TRMDFENVIOOFCNASMEVT.SMSOLCTMONEDAE();
		Ejecutar.ITRMDFENVIOOFCNASMTR.TRMDFENVIOOFCNASMEVT.SMSOLCTDESGLSE nivel2_2;
		
		nivel2_1.setCODNRBEEN(super.getEntidad());
		nivel2_1.setCODINTERNOUO(super.getSucursal());
		nivel2_1.setFECHASOLCTSM(envioEntreOficinasBean.getFechaContableEnvioInteger());
		nivel2_1.setCODPPL("");
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
			if(existenciaDenominacionBean.getImporteAEnviar() != null
					&& existenciaDenominacionBean.getImporteAEnviar().compareTo(BigDecimal.ZERO) > 0){
				
				nivel2_2 = new Ejecutar.ITRMDFENVIOOFCNASMTR.TRMDFENVIOOFCNASMEVT.SMSOLCTDESGLSE();
				
				nivel2_2.setINDURG(0);
				nivel2_2.setCODDSTN(existenciaDenominacionBean.getOrigen());
				nivel2_2.setCODVALORFACIAL(existenciaDenominacionBean.getValorFacial());
				nivel2_2.setIMPAUT(BigDecimal.ZERO);
				nivel2_2.setIMPPEDIDO(existenciaDenominacionBean.getImporteAEnviar());
				nivel2_2.setIMPRECBDO(BigDecimal.ZERO);
				nivel2_2.setNUMPRECINTO(existenciaDenominacionBean.getPrecinto());
				
				nivel1_1.getSMSOLCTDESGLSE().add(nivel2_2);	
			}
		}
		
		nivel1_2.setIDINTERNOTERMTN(super.getTerminal());
		nivel1_2.setIDEMPLAUT("");
		nivel1_2.setNUMSEC(0);
		nivel1_2.setCODTX("VCM22MOU");
		
		contexto.setELEVATORPOSITION(0);
		contexto.setTRMDFENVIOOFCNASMEVT(nivel1_1);
		contexto.setSTDTRNIPARMV(nivel1_2);
		
		return contexto;
	}
	
	private EjecutarResult ejecutarWS(Ejecutar.ITRMDFENVIOOFCNASMTR contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ModificarEnvioEntreOficinasServicio.class, contexto);
		}catch (NoControlableException nce){
			throw new NoControlableException("No se pudo invocar el servicio de"
					+ " modificación de envio entre oficinas.", nce);
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
