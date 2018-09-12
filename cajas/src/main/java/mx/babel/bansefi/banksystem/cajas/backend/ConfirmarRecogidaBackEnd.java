package mx.babel.bansefi.banksystem.cajas.backend;

import java.math.BigDecimal;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;
import mx.babel.bansefi.banksystem.cajas.beans.RecogidaEfectivoBean;
import mx.babel.bansefi.banksystem.cajas.webservice.confirmarrecogida.ConfirmarRecogidaServicio;
import mx.babel.bansefi.banksystem.cajas.webservice.confirmarrecogida.Ejecutar;
import mx.babel.bansefi.banksystem.cajas.webservice.confirmarrecogida.EjecutarResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConfirmarRecogidaBackEnd extends BackEndBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2882129596666995192L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	public Integer ejecutarTRN(RecogidaEfectivoBean recogidaEfectivoBean, List<ExistenciaDenominacionBean> listaExistencia){
		Ejecutar.ITRCONFRECOGEFCTSMTR contexto = initPeticion(recogidaEfectivoBean, listaExistencia);
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if(ce.getRtncod() != RETURN_COD_OK){
				throw ce;
			}
		}
		
		return respuesta.getResponseBansefi().getOTRCONFRECOGEFCTSMTR().getRTRNCD();
	}
	
	private Ejecutar.ITRCONFRECOGEFCTSMTR initPeticion(RecogidaEfectivoBean recogidaEfectivoBean, List<ExistenciaDenominacionBean> listaExistencia){
		Ejecutar.ITRCONFRECOGEFCTSMTR contexto = new Ejecutar.ITRCONFRECOGEFCTSMTR();
		
		Ejecutar.ITRCONFRECOGEFCTSMTR.TRCONFRECOGEFCTSMEVT nivel1_1 = new Ejecutar.ITRCONFRECOGEFCTSMTR.TRCONFRECOGEFCTSMEVT();
		Ejecutar.ITRCONFRECOGEFCTSMTR.SMPEDIDOV nivel1_2 = new Ejecutar.ITRCONFRECOGEFCTSMTR.SMPEDIDOV();
		Ejecutar.ITRCONFRECOGEFCTSMTR.SMSIGNOCTBLE nivel1_3 = new Ejecutar.ITRCONFRECOGEFCTSMTR.SMSIGNOCTBLE();
		Ejecutar.ITRCONFRECOGEFCTSMTR.STDTRNIPARMV nivel1_4 = new Ejecutar.ITRCONFRECOGEFCTSMTR.STDTRNIPARMV();
		
		Ejecutar.ITRCONFRECOGEFCTSMTR.TRCONFRECOGEFCTSMEVT.SMSOLCTMONEDAE nivel2_1 = new Ejecutar.ITRCONFRECOGEFCTSMTR.TRCONFRECOGEFCTSMEVT.SMSOLCTMONEDAE();
		Ejecutar.ITRCONFRECOGEFCTSMTR.TRCONFRECOGEFCTSMEVT.SMSOLCTDESGLSE nivel2_2;
		
		super.initialize(contexto);
		
		nivel1_1.setCODNUMRCOMONEDA("MXN");
		
		nivel2_1.setCODNRBEEN(super.getEntidad());
		nivel2_1.setCODINTERNOUO(super.getSucursal());
		nivel2_1.setFECHASOLCTSM(recogidaEfectivoBean.getFechaSolicitud());
		nivel2_1.setCODPPL(recogidaEfectivoBean.getCodigoMoneda());
		nivel2_1.setCODDISTRIB(recogidaEfectivoBean.getCodigoDistribucion());
		nivel2_1.setCODDISTRIB("T");
		nivel2_1.setINDURG(recogidaEfectivoBean.getIndicadorUrgencia());
		nivel2_1.setCODINTERNOUO1(recogidaEfectivoBean.getCentroControlador());
		nivel2_1.setCODECVSM(recogidaEfectivoBean.getEstatusC());
		nivel2_1.setFECHAABASTREC(recogidaEfectivoBean.getFechaAbastecimiento());
		nivel2_1.setFECHAPROCESOSM(recogidaEfectivoBean.getFechaProceso());
		nivel2_1.setIMPAUTV(BigDecimal.ZERO);
		nivel2_1.setIMPPEDIDOV(recogidaEfectivoBean.getTotalRecogida());
		nivel2_1.setIMPRECBDOV(BigDecimal.ZERO);
		nivel2_1.setIMPCERTFDOV(BigDecimal.ZERO);
		nivel2_1.setIDINTERNOEMPLEP(recogidaEfectivoBean.getIdEmpleado());
		nivel2_1.setVALOROBSERSM(recogidaEfectivoBean.getObservacion());
		
		nivel1_1.setSMSOLCTMONEDAE(nivel2_1);
		
		for(ExistenciaDenominacionBean existenciaDenominacionBean : listaExistencia){
			
			if(existenciaDenominacionBean.getImporteAEnviar().compareTo(BigDecimal.ZERO) > 0){
				nivel2_2 = new Ejecutar.ITRCONFRECOGEFCTSMTR.TRCONFRECOGEFCTSMEVT.SMSOLCTDESGLSE();
				
				nivel2_2.setINDURG(recogidaEfectivoBean.getIndicadorUrgencia());
				nivel2_2.setCODDSTN(existenciaDenominacionBean.getOrigen());
				nivel2_2.setCODVALORFACIAL(existenciaDenominacionBean.getValorFacial());
				nivel2_2.setIMPAUT(BigDecimal.ZERO);
				nivel2_2.setIMPPEDIDO(existenciaDenominacionBean.getImporteAEnviar());
				nivel2_2.setIMPRECBDO(BigDecimal.ZERO);
				
				nivel1_1.getSMSOLCTDESGLSE().add(nivel2_2);	
			}
		}

		contexto.setELEVATORPOSITION(0);
		contexto.setSCROLLABLEOCCURS(0);
		
		contexto.setTRCONFRECOGEFCTSMEVT(nivel1_1);
		
		nivel1_2.setIMPPEDIDOV(recogidaEfectivoBean.getTotalRecogida());
		contexto.setSMPEDIDOV(nivel1_2);
		
		nivel1_3.setSTDCHAR02("CH");
		contexto.setSMSIGNOCTBLE(nivel1_3);
		
		nivel1_4.setIDINTERNOTERMTN(super.getTerminal());
		nivel1_4.setNUMSEC(0);
		nivel1_4.setCODTX("VCO09OOU");
		nivel1_4.setCODTXDI("CO09");
		
		contexto.setSTDTRNIPARMV(nivel1_4);
		
		return contexto;
	}
	
	private EjecutarResult ejecutarWS(Ejecutar.ITRCONFRECOGEFCTSMTR contexto){
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConfirmarRecogidaServicio.class, contexto);
		}catch (NoControlableException nce){
			throw new NoControlableException("No se pudo invocar el servicio de"
					+ " confirmar recogida.", nce);
		}
		return respuesta;
	}
}
