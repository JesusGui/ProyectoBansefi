package mx.babel.bansefi.banksystem.cajas.backend;

import java.math.BigDecimal;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;
import mx.babel.bansefi.banksystem.cajas.beans.EnvioEntreOficinasBean;
import mx.babel.bansefi.banksystem.cajas.webservice.altaenvioentreoficinas.AltaEnvioEntreOficinasServicio;
import mx.babel.bansefi.banksystem.cajas.webservice.altaenvioentreoficinas.Ejecutar;
import mx.babel.bansefi.banksystem.cajas.webservice.altaenvioentreoficinas.EjecutarResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AltaEnvioEntreOficinasBackEnd extends BackEndBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4669725445642589868L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	public Integer ejecutarTRN(EnvioEntreOficinasBean envioEntreOficinasBean){
		Ejecutar.ITRALTAENVIOOFCNASMT contexto = initPeticion(envioEntreOficinasBean);
		EjecutarResult respuesta = ejecutarWS(contexto);
		Integer codigoRetorno = 0;
		
		try{
			super.verificaRespuesta(respuesta);
			codigoRetorno = RETURN_COD_OK;
		}catch (ControlableException ce){
			if(ce.getRtncod() != RETURN_COD_OK){
				throw ce;
			}
		}
		
		return codigoRetorno;
	}
	
	private Ejecutar.ITRALTAENVIOOFCNASMT initPeticion(EnvioEntreOficinasBean envioEntreOficinasBean){
		Ejecutar.ITRALTAENVIOOFCNASMT contexto = new Ejecutar.ITRALTAENVIOOFCNASMT();
		
		Ejecutar.ITRALTAENVIOOFCNASMT.TRALTAENVIOOFCNASMEV nivel1_1 = new Ejecutar.ITRALTAENVIOOFCNASMT.TRALTAENVIOOFCNASMEV();
		Ejecutar.ITRALTAENVIOOFCNASMT.STDTRNIPARMV nivel1_2 = new Ejecutar.ITRALTAENVIOOFCNASMT.STDTRNIPARMV();
		
		Ejecutar.ITRALTAENVIOOFCNASMT.TRALTAENVIOOFCNASMEV.SMSOLCTMONEDAE nivel2_1 = new Ejecutar.ITRALTAENVIOOFCNASMT.TRALTAENVIOOFCNASMEV.SMSOLCTMONEDAE();
		Ejecutar.ITRALTAENVIOOFCNASMT.TRALTAENVIOOFCNASMEV.SMSOLCTDESGLSE nivel2_2;
		
		super.initialize(contexto);
		
		contexto.setELEVATORPOSITION(0);
		contexto.setSCROLLABLEOCCURS(0);
		
		nivel2_1.setCODNRBEEN(super.getEntidad());
		nivel2_1.setCODINTERNOUO(super.getSucursal());
		nivel2_1.setFECHASOLCTSM(envioEntreOficinasBean.getFechaContableEnvioInteger());
		nivel2_1.setCODPPL("");
		nivel2_1.setCODDISTRIB("V");
		nivel2_1.setINDURG(0);
		nivel2_1.setCODINTERNOUO1(envioEntreOficinasBean.getCentroDestino());
		nivel2_1.setCODINTERNOUO2("");
		nivel2_1.setFECHAABASTREC(0);
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
				
				nivel2_2 = new Ejecutar.ITRALTAENVIOOFCNASMT.TRALTAENVIOOFCNASMEV.SMSOLCTDESGLSE();
				
				nivel2_2.setCODDSTN(existenciaDenominacionBean.getOrigen());
				nivel2_2.setCODVALORFACIAL(existenciaDenominacionBean.getValorFacial());
				nivel2_2.setIMPPEDIDO(existenciaDenominacionBean.getImporteAEnviar());
				nivel2_2.setNUMPRECINTO(existenciaDenominacionBean.getPrecinto());
				
				nivel1_1.getSMSOLCTDESGLSE().add(nivel2_2);
			}
		}
		
		nivel1_2.setIDINTERNOTERMTN(super.getTerminal());
		nivel1_2.setNUMSEC(0);
		nivel1_2.setCODTX("VCO15MOU");
		nivel1_2.setCODTXDI("VCO1");
		
		contexto.setTRALTAENVIOOFCNASMEV(nivel1_1);
		contexto.setSTDTRNIPARMV(nivel1_2);
		
		return contexto;
	}
	
	private EjecutarResult ejecutarWS(Ejecutar.ITRALTAENVIOOFCNASMT contexto){
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(AltaEnvioEntreOficinasServicio.class, contexto);
		}catch (NoControlableException nce){
			throw new NoControlableException("No se pudo invocar el servicio de"
					+ " alta de envio entre oficinas.", nce);
		}
		
		return respuesta;
	}
}
