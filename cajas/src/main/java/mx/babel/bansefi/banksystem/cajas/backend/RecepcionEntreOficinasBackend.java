package mx.babel.bansefi.banksystem.cajas.backend;

import java.math.BigDecimal;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.cajas.beans.RecepcionEntreOficinasBean;
import mx.babel.bansefi.banksystem.cajas.webservice.altarecepcionentreoficinas.Ejecutar;
import mx.babel.bansefi.banksystem.cajas.webservice.altarecepcionentreoficinas.EjecutarResult;
import mx.babel.bansefi.banksystem.cajas.webservice.altarecepcionentreoficinas.RecepcionEntreOficinasServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecepcionEntreOficinasBackend extends BackEndBean{
	
	private static final long serialVersionUID = 3974559404893779060L;
	
	@Autowired
	ContextoUtils contextoUtils;
	@Autowired
	ServicioWebUtils servicioWebUtils;
	@Autowired
	CatalogoUtils catalogoUtils;
	
	public RecepcionEntreOficinasBean ejecutarTRN(RecepcionEntreOficinasBean recepcionEntreOficinasBean){
		
		RecepcionEntreOficinasBean recepcion = new RecepcionEntreOficinasBean();
		Ejecutar.ITRRECPENVIOOFCNASMT contexto = initPeticion(recepcionEntreOficinasBean);
		EjecutarResult resultado = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(resultado);
		}catch (ControlableException ce){
			if(ce.getRtncod() != 1){
				throw ce;
			}
		}
		
		if(verificaResultado(resultado)){
			recepcion = obtenerResultado(resultado);
		}else{
			recepcion = null;
		}
		
		return recepcion;
	}
	
	private Ejecutar.ITRRECPENVIOOFCNASMT initPeticion(RecepcionEntreOficinasBean recepcionEntreOficinasBean){
		
		Ejecutar.ITRRECPENVIOOFCNASMT contexto = new Ejecutar.ITRRECPENVIOOFCNASMT();
		Ejecutar.ITRRECPENVIOOFCNASMT.TRRECPENVIOOFCNASMEV nivel1_1 = new Ejecutar.ITRRECPENVIOOFCNASMT.TRRECPENVIOOFCNASMEV();
		Ejecutar.ITRRECPENVIOOFCNASMT.SMINICIALV nivel1_2 = new Ejecutar.ITRRECPENVIOOFCNASMT.SMINICIALV();
		Ejecutar.ITRRECPENVIOOFCNASMT.SMSIGNOCTBLE nivel1_3 = new Ejecutar.ITRRECPENVIOOFCNASMT.SMSIGNOCTBLE();
		Ejecutar.ITRRECPENVIOOFCNASMT.STDTRNIPARMV nivel1_4 = new Ejecutar.ITRRECPENVIOOFCNASMT.STDTRNIPARMV();
		Ejecutar.ITRRECPENVIOOFCNASMT.STDAUTORIZAV nivel1_5 = new Ejecutar.ITRRECPENVIOOFCNASMT.STDAUTORIZAV();
		
		Ejecutar.ITRRECPENVIOOFCNASMT.TRRECPENVIOOFCNASMEV.SMSOLCTMONEDAE nivel2_1 = new Ejecutar.ITRRECPENVIOOFCNASMT.TRRECPENVIOOFCNASMEV.SMSOLCTMONEDAE();
		Ejecutar.ITRRECPENVIOOFCNASMT.TRRECPENVIOOFCNASMEV.SMSOLCTDESGLSE nivel2_2;
		
		Ejecutar.ITRRECPENVIOOFCNASMT.STDAUTORIZAV.ARAUTREMOTAP nivel5_1 = new Ejecutar.ITRRECPENVIOOFCNASMT.STDAUTORIZAV.ARAUTREMOTAP();
		Ejecutar.ITRRECPENVIOOFCNASMT.STDAUTORIZAV.ARTRNMSJPARMV nivel5_2 = new Ejecutar.ITRRECPENVIOOFCNASMT.STDAUTORIZAV.ARTRNMSJPARMV();
		Ejecutar.ITRRECPENVIOOFCNASMT.STDAUTORIZAV.STDTARGETTERMINALV nivel5_3 = new Ejecutar.ITRRECPENVIOOFCNASMT.STDAUTORIZAV.STDTARGETTERMINALV();
		Ejecutar.ITRRECPENVIOOFCNASMT.STDAUTORIZAV.ARIDSALTADOV nivel5_4 = new Ejecutar.ITRRECPENVIOOFCNASMT.STDAUTORIZAV.ARIDSALTADOV();
		
		contexto.setELEVATORPOSITION(0);
		contexto.setSCROLLABLEOCCURS(0);
		
		nivel2_1.setCODNRBEEN(super.getEntidad());
		nivel2_1.setCODINTERNOUO(super.getSucursal());
		nivel2_1.setFECHASOLCTSM(recepcionEntreOficinasBean.getFechaEnvioInteger());
		nivel2_1.setCODPPL("M");
		nivel2_1.setCODDISTRIB("V");
		nivel2_1.setINDURG(0);
		nivel2_1.setCODINTERNOUO1(recepcionEntreOficinasBean.getCentroOrigen());
		nivel2_1.setCODINTERNOUO2(recepcionEntreOficinasBean.getCentroControlador());
		nivel2_1.setCODECVSM(recepcionEntreOficinasBean.getEstatusC());
		nivel2_1.setIMPAUTV(recepcionEntreOficinasBean.getTotalAutorizado());
		nivel2_1.setIMPPEDIDOV(recepcionEntreOficinasBean.getTotalEnviado());
		nivel2_1.setIMPRECBDOV(recepcionEntreOficinasBean.getTotalRecibido());
		nivel2_1.setIDINTERNOEMPLEP(super.getUsuarioId());
		nivel2_1.setVALOROBSERSM(recepcionEntreOficinasBean.getObservaciones());
		
		nivel1_1.setSMSOLCTMONEDAE(nivel2_1);
		
		for(ExistenciaDenominacionBean existencia : recepcionEntreOficinasBean.getParrilla().getListaDenominaciones()){
			
			if(existencia.getImporteRecibido() != null
					&& existencia.getImporteRecibido().compareTo(BigDecimal.ZERO) > 0){
				
				nivel2_2 = new Ejecutar.ITRRECPENVIOOFCNASMT.TRRECPENVIOOFCNASMEV.SMSOLCTDESGLSE();
				
				nivel2_2.setCODDSTN(existencia.getOrigen());
				nivel2_2.setCODVALORFACIAL(existencia.getValorFacial());
				nivel2_2.setIMPAUT(existencia.getImporteAutorizado());
				nivel2_2.setIMPPEDIDO(existencia.getImporteAEnviar());
				nivel2_2.setIMPRECBDO(existencia.getImporteRecibido());
				
				nivel1_1.getSMSOLCTDESGLSE().add(nivel2_2);
			}
		}
		contexto.setTRRECPENVIOOFCNASMEV(nivel1_1);
		
		nivel1_2.setIMPRECBDOV(recepcionEntreOficinasBean.getTotalRecibido());
		
		contexto.setSMINICIALV(nivel1_2);
		
		nivel1_3.setSTDCHAR02("CD");
		
		contexto.setSMSIGNOCTBLE(nivel1_3);
		
		nivel1_4.setIDINTERNOTERMTN(super.getTerminal());
		nivel1_4.setIDEMPLAUT("");
		nivel1_4.setNUMSEC(0);
		nivel1_4.setCODTX("VCO20OOU");
		nivel1_4.setCODTXDI("CO20");
		
		contexto.setSTDTRNIPARMV(nivel1_4);
		
		nivel1_5.setARAUTREMOTAP(nivel5_1);
		nivel1_5.getARTRNMSJPARMV().add(nivel5_2);
		nivel1_5.getSTDTARGETTERMINALV().add(nivel5_3);
		nivel1_5.setARIDSALTADOV(nivel5_4);
		
		contexto.setSTDAUTORIZAV(nivel1_5);
		
		return contexto;
	}
	
	private EjecutarResult ejecutarWS(Ejecutar.ITRRECPENVIOOFCNASMT contexto){
		
		EjecutarResult resultado = null;
		
		try{
			resultado = (EjecutarResult) servicioWebUtils.ejecutarWS(RecepcionEntreOficinasServicio.class, contexto);
		}catch (NoControlableException nce){
			throw new NoControlableException("No se puede invocar el servicio de alta de"
					+ " recepción entre oficinas.", nce);
		}
		
		return resultado;
	}
	
	private boolean verificaResultado(EjecutarResult resultado){
		boolean noNulo = false;
		if(resultado != null
				&& resultado.getResponseBansefi() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
	private RecepcionEntreOficinasBean obtenerResultado(EjecutarResult resultado){
		RecepcionEntreOficinasBean recepcionEntreOficinasBean = new RecepcionEntreOficinasBean();
		
		mx.babel.bansefi.banksystem.cajas.webservice.altarecepcionentreoficinas.ResponseBansefi.OTRRECPENVIOOFCNASMT.TRRECPENVIOOFCNASMEV.SMSOLCTMONEDAE smsolctmonedae = resultado.getResponseBansefi().getOTRRECPENVIOOFCNASMT().getTRRECPENVIOOFCNASMEV().getSMSOLCTMONEDAE();
		
		recepcionEntreOficinasBean.setEntidad(smsolctmonedae.getCODNRBEEN());
		recepcionEntreOficinasBean.setCentroOrigen(smsolctmonedae.getCODINTERNOUO());
		recepcionEntreOficinasBean.setCodigoMoneda(smsolctmonedae.getCODPPL());
		recepcionEntreOficinasBean.setCodigoDistribucion(smsolctmonedae.getCODDISTRIB());
		recepcionEntreOficinasBean.setIndicadorUrgente(smsolctmonedae.getINDURG());
		recepcionEntreOficinasBean.setCentroControlador(smsolctmonedae.getCODINTERNOUO2());
		recepcionEntreOficinasBean.setEstatusC(smsolctmonedae.getCODECVSM());
		final CatalogoBean estatus = catalogoUtils.getCatalogoBean(CatalogoEnum.ESTATUS_CAJA_CENT, recepcionEntreOficinasBean.getEstatusC());
		recepcionEntreOficinasBean.setEstatusL(estatus.getDescripcionL());
		recepcionEntreOficinasBean.setTotalAutorizado(smsolctmonedae.getIMPAUTV());
		recepcionEntreOficinasBean.setTotalEnviado(smsolctmonedae.getIMPPEDIDOV());
		recepcionEntreOficinasBean.setTotalRecibido(smsolctmonedae.getIMPRECBDOV());
		recepcionEntreOficinasBean.setObservaciones(smsolctmonedae.getVALOROBSERSM().trim());
		
		return recepcionEntreOficinasBean;
	}

}
 