package mx.babel.bansefi.banksystem.cajas.backend;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.cajas.beans.AutorizacionPeticionUrgenteBean;
import mx.babel.bansefi.banksystem.cajas.beans.ConsultaPeticionUrgenteBean;
import mx.babel.bansefi.banksystem.cajas.webservice.autorizacionpeticionurgente.AutorizacionPeticionUrgenteServicio;
import mx.babel.bansefi.banksystem.cajas.webservice.autorizacionpeticionurgente.Ejecutar;
import mx.babel.bansefi.banksystem.cajas.webservice.autorizacionpeticionurgente.EjecutarResult;

@Component
public class AutorizacionPeticionUrgenteBackend extends BackEndBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6894325821243510992L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;

	public int ejecutarTRN(AutorizacionPeticionUrgenteBean autorizacionPeticionUrgenteBean){
		
		int resultado = 0;
		
		Ejecutar.ITRAUTURGSMTRNI contexto = initPeticion(autorizacionPeticionUrgenteBean);
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
			resultado = 1;
		}catch (ControlableException ce){
			throw ce;
		}
		
		return resultado;
	}
	
	private Ejecutar.ITRAUTURGSMTRNI initPeticion(AutorizacionPeticionUrgenteBean autorizacionPeticionUrgenteBean){
		
		Ejecutar.ITRAUTURGSMTRNI contexto = new Ejecutar.ITRAUTURGSMTRNI();
		
		Ejecutar.ITRAUTURGSMTRNI.TRAUTURGSMEVTY nivel1_1 = new Ejecutar.ITRAUTURGSMTRNI.TRAUTURGSMEVTY();
		Ejecutar.ITRAUTURGSMTRNI.SMPEDIDOV nivel1_2 = new Ejecutar.ITRAUTURGSMTRNI.SMPEDIDOV();
		Ejecutar.ITRAUTURGSMTRNI.SMIMPV nivel1_3 = new Ejecutar.ITRAUTURGSMTRNI.SMIMPV();
		Ejecutar.ITRAUTURGSMTRNI.SMSIGNOCTBLE nivel1_4 = new Ejecutar.ITRAUTURGSMTRNI.SMSIGNOCTBLE();
		Ejecutar.ITRAUTURGSMTRNI.STDTRNIPARMV nivel1_5 = new Ejecutar.ITRAUTURGSMTRNI.STDTRNIPARMV();
		
		Ejecutar.ITRAUTURGSMTRNI.TRAUTURGSMEVTY.SMSOLCTMONEDAE nivel2_1 = new Ejecutar.ITRAUTURGSMTRNI.TRAUTURGSMEVTY.SMSOLCTMONEDAE(); 
		Ejecutar.ITRAUTURGSMTRNI.TRAUTURGSMEVTY.SMSOLCTDESGLSE nivel2_2 = new Ejecutar.ITRAUTURGSMTRNI.TRAUTURGSMEVTY.SMSOLCTDESGLSE();
		
		contexto.setELEVATORPOSITION(0);
		contexto.setSCROLLABLEOCCURS(0);
		
		nivel2_1.setCODNRBEEN(autorizacionPeticionUrgenteBean.getEntidad());
		nivel2_1.setCODINTERNOUO(autorizacionPeticionUrgenteBean.getCentro());
		nivel2_1.setFECHASOLCTSM(autorizacionPeticionUrgenteBean.getFechaSolicitud());
		nivel2_1.setCODPPL(autorizacionPeticionUrgenteBean.getTipoMoneda());
		nivel2_1.setCODDISTRIB(autorizacionPeticionUrgenteBean.getCodigoDistribucion());
		nivel2_1.setINDURG(autorizacionPeticionUrgenteBean.getNoUrgente());
		nivel2_1.setCODINTERNOUO1(autorizacionPeticionUrgenteBean.getCentroControlador());
		nivel2_1.setCODECVSM(autorizacionPeticionUrgenteBean.getEstatusPeticionC());
		nivel2_1.setFECHAABASTREC(autorizacionPeticionUrgenteBean.getFechaAbastecimiento());
		nivel2_1.setFECHAPROCESOSM(autorizacionPeticionUrgenteBean.getFechaProceso());
		nivel2_1.setIMPAUTV(autorizacionPeticionUrgenteBean.getTotalAutorizado());
		nivel2_1.setIMPPEDIDOV(autorizacionPeticionUrgenteBean.getTotalPedido());
		nivel2_1.setIMPRECBDOV(autorizacionPeticionUrgenteBean.getImporteRecibido());
		nivel2_1.setIMPCERTFDOV(autorizacionPeticionUrgenteBean.getImporteCertificado());
		nivel2_1.setIDINTERNOEMPLEP(autorizacionPeticionUrgenteBean.getIdEmpleado());
		nivel2_1.setVALOROBSERSM(autorizacionPeticionUrgenteBean.getObservaciones().trim());
		
		nivel1_1.setSMSOLCTMONEDAE(nivel2_1);
		
		contexto.setTRAUTURGSMEVTY(nivel1_1);
		
		for(ConsultaPeticionUrgenteBean consultaPeticionUrgenteBean : autorizacionPeticionUrgenteBean.getListaDenominacionesPeticion()){
			
			if(consultaPeticionUrgenteBean.getImporteAutorizado().compareTo(BigDecimal.ZERO) > 0){
				nivel2_2 = new Ejecutar.ITRAUTURGSMTRNI.TRAUTURGSMEVTY.SMSOLCTDESGLSE();
				
				nivel2_2.setCODNRBEEN(consultaPeticionUrgenteBean.getEntidad());
				nivel2_2.setCODINTERNOUO(consultaPeticionUrgenteBean.getCentro());
				nivel2_2.setFECHASOLCTSM(consultaPeticionUrgenteBean.getFechaSolicitud());
				nivel2_2.setCODPPL(consultaPeticionUrgenteBean.getCodigoMoneda());
				nivel2_2.setCODDISTRIB(consultaPeticionUrgenteBean.getCodigoDistribucion());
				nivel2_2.setINDURG(consultaPeticionUrgenteBean.getNoUrgente());
				nivel2_2.setCODINTERNOUO1(consultaPeticionUrgenteBean.getCentroControlador());
				nivel2_2.setCODDSTN(consultaPeticionUrgenteBean.getOrigen());
				nivel2_2.setCODVALORFACIAL(consultaPeticionUrgenteBean.getCodigoValorFacial());
				nivel2_2.setIMPAUT(consultaPeticionUrgenteBean.getImporteAutorizado());
				nivel2_2.setIMPPEDIDO(consultaPeticionUrgenteBean.getImportePedido());
				nivel2_2.setIMPRECBDO(consultaPeticionUrgenteBean.getImporteRecibido());
				
				contexto.getTRAUTURGSMEVTY().getSMSOLCTDESGLSE().add(nivel2_2);
			}
		}
		
		nivel1_2.setIMPPEDIDOV(autorizacionPeticionUrgenteBean.getImportePedido());
		
		contexto.setSMPEDIDOV(nivel1_2);
		
		nivel1_3.setSTDDEC15Y2(new BigDecimal(0.00));
		
		contexto.setSMIMPV(nivel1_3);
		
		nivel1_4.setSTDCHAR02("CH");
		
		contexto.setSMSIGNOCTBLE(nivel1_4);

		nivel1_5.setIDINTERNOTERMTN(super.getTerminal());
		nivel1_5.setNUMSEC(0);
		nivel1_5.setCODTX("VCC17OOU");
		nivel1_5.setCODTXDI("CC17");
		
		contexto.setSTDTRNIPARMV(nivel1_5);
		
		super.initialize(contexto);
		
		return contexto;
	}
		
	
	private EjecutarResult ejecutarWS(Ejecutar.ITRAUTURGSMTRNI contexto){
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(AutorizacionPeticionUrgenteServicio.class, contexto);
		}catch (NoControlableException nce){
			throw new NoControlableException("Error al invocar el web service "
					+ "de autorización de petición urgente", nce);
		}
		
		return respuesta;
	}
}
