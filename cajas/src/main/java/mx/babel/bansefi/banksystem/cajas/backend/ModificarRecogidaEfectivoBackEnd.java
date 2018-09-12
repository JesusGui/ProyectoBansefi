package mx.babel.bansefi.banksystem.cajas.backend;

import java.math.BigDecimal;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;
import mx.babel.bansefi.banksystem.cajas.beans.RecogidaEfectivoBean;
import mx.babel.bansefi.banksystem.cajas.webservice.modificapeticionefectivo.Ejecutar;
import mx.babel.bansefi.banksystem.cajas.webservice.modificapeticionefectivo.EjecutarResult;
import mx.babel.bansefi.banksystem.cajas.webservice.modificapeticionefectivo.ModificaPeticionEfectivoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModificarRecogidaEfectivoBackEnd extends BackEndBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7626092117038707843L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	public int ejecutarTRN(RecogidaEfectivoBean recogidaEfectivoBean, List<ExistenciaDenominacionBean> listaExistenciasParrilla){
		
		int codigoRetorno = 0;
		Ejecutar.ITRMDFSOLCTEFCTSMTRN contexto = initPeticion(recogidaEfectivoBean, listaExistenciasParrilla);
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
			codigoRetorno = respuesta.getResponseBansefi().getOTRMDFSOLCTEFCTSMTRN().getRTRNCD();
		}catch (ControlableException ce){
			if(ce.getRtncod() != RETURN_COD_OK){
				throw ce;
			}
		}
		
		return codigoRetorno;
	}
	
	private Ejecutar.ITRMDFSOLCTEFCTSMTRN initPeticion(RecogidaEfectivoBean recogidaEfectivoBean, List<ExistenciaDenominacionBean> listaExistenciasParrilla){
		Ejecutar.ITRMDFSOLCTEFCTSMTRN contexto = new Ejecutar.ITRMDFSOLCTEFCTSMTRN();
		
		Ejecutar.ITRMDFSOLCTEFCTSMTRN.TRMDFSOLCTEFCTSMEVT nivel1_1 = new Ejecutar.ITRMDFSOLCTEFCTSMTRN.TRMDFSOLCTEFCTSMEVT();
		Ejecutar.ITRMDFSOLCTEFCTSMTRN.STDTRNIPARMV nivel1_2 = new Ejecutar.ITRMDFSOLCTEFCTSMTRN.STDTRNIPARMV();
		
		Ejecutar.ITRMDFSOLCTEFCTSMTRN.TRMDFSOLCTEFCTSMEVT.SMSOLCTMONEDAE nivel2_1 = new Ejecutar.ITRMDFSOLCTEFCTSMTRN.TRMDFSOLCTEFCTSMEVT.SMSOLCTMONEDAE();
		Ejecutar.ITRMDFSOLCTEFCTSMTRN.TRMDFSOLCTEFCTSMEVT.SMSOLCTDESGLSE nivel2_2;
		Ejecutar.ITRMDFSOLCTEFCTSMTRN.TRMDFSOLCTEFCTSMEVT.SMSOLCTDESGLSP nivel2_3 = new Ejecutar.ITRMDFSOLCTEFCTSMTRN.TRMDFSOLCTEFCTSMEVT.SMSOLCTDESGLSP();
		
		super.initialize(contexto);
		
		contexto.setELEVATORPOSITION(0);
		contexto.setSCROLLABLEOCCURS(0);
		
		nivel2_1.setCODNRBEEN(super.getEntidad());
		nivel2_1.setCODINTERNOUO(super.getSucursal());
		nivel2_1.setFECHASOLCTSM(recogidaEfectivoBean.getFechaSolicitud());
		nivel2_1.setCODPPL(recogidaEfectivoBean.getCodigoMoneda());
		nivel2_1.setCODDISTRIB("T");
		nivel2_1.setINDURG(recogidaEfectivoBean.getIndicadorUrgencia());
		nivel2_1.setCODINTERNOUO1(recogidaEfectivoBean.getCentroControlador());
		nivel2_1.setCODECVSM(recogidaEfectivoBean.getEstatusC());
		nivel2_1.setFECHAABASTREC(recogidaEfectivoBean.getFechaAbastecimiento());
		nivel2_1.setHORAABASTREC(0);
		nivel2_1.setFECHAPROCESOSM(recogidaEfectivoBean.getFechaProceso());
		nivel2_1.setIMPAUTV(BigDecimal.ZERO);
		nivel2_1.setIMPPEDIDOV(recogidaEfectivoBean.getTotalRecogida());
		nivel2_1.setIMPRECBDOV(BigDecimal.ZERO);
		nivel2_1.setIMPCERTFDOV(BigDecimal.ZERO);
		nivel2_1.setVALOROBSERSM(recogidaEfectivoBean.getObservacion());
		
		nivel1_1.setSMSOLCTMONEDAE(nivel2_1);
		
		for(ExistenciaDenominacionBean existencia : listaExistenciasParrilla){
			
			if(existencia.getValorFacial() != null
					&& existencia.getImporteAEnviar().compareTo(BigDecimal.ZERO) > 0){
				nivel2_2 = new Ejecutar.ITRMDFSOLCTEFCTSMTRN.TRMDFSOLCTEFCTSMEVT.SMSOLCTDESGLSE();
				
				nivel2_2.setCODDSTN(existencia.getOrigen());
				nivel2_2.setCODVALORFACIAL(existencia.getValorFacial());
				nivel2_2.setIMPAUT(BigDecimal.ZERO);
				nivel2_2.setIMPPEDIDO(existencia.getImporteAEnviar());
				nivel2_2.setIMPRECBDO(BigDecimal.ZERO);
				nivel2_2.setNUMPRECINTO("0");
				
				nivel1_1.getSMSOLCTDESGLSE().add(nivel2_2);
			}
		}
		
		for(int i = 0; i < 50; i++){
			nivel2_3 = new Ejecutar.ITRMDFSOLCTEFCTSMTRN.TRMDFSOLCTEFCTSMEVT.SMSOLCTDESGLSP();
			
			nivel2_3.setFECHASOLCTSM(0);
			nivel2_3.setINDURG(0);
			
			nivel1_1.getSMSOLCTDESGLSP().add(nivel2_3);
		}
		
		contexto.setTRMDFSOLCTEFCTSMEVT(nivel1_1);
		
		nivel1_2.setIDINTERNOTERMTN(super.getTerminal());
		nivel1_2.setNUMSEC(0);
		nivel1_2.setCODTX("VCM23MOU");
		nivel1_2.setCODTXDI("CO08");
		
		contexto.setSTDTRNIPARMV(nivel1_2);
		
		return contexto;
	}
	
	private EjecutarResult ejecutarWS(Ejecutar.ITRMDFSOLCTEFCTSMTRN contexto){
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ModificaPeticionEfectivoServicio.class, contexto);
		}catch (NoControlableException nce){
			throw new NoControlableException("No se pudo invocar el servicio de"
					+ " modificar recogida de efectivo.", nce);
		}
		
		return respuesta;
	}
}