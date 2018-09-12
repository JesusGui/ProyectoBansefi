package mx.babel.bansefi.banksystem.cajas.backend;

import java.math.BigDecimal;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.cajas.beans.RecogidaEfectivoBean;
import mx.babel.bansefi.banksystem.cajas.webservice.suprimirrecogidaefectivo.EjecutarResult;
import mx.babel.bansefi.banksystem.cajas.webservice.suprimirrecogidaefectivo.Ejecutar;
import mx.babel.bansefi.banksystem.cajas.webservice.suprimirrecogidaefectivo.SuprimirRecogidaEfectivoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SuprimirRecogidaEfectivoBackEnd extends BackEndBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5898862229194998849L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	public int ejecutarTRN(RecogidaEfectivoBean recogidaEfectivoBean){
		
		int codigoRetorno = 0;
		
		Ejecutar.ITRBAJARECOGEFCTSMTR contexto = initPeticion(recogidaEfectivoBean);
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if(ce.getRtncod() != RETURN_COD_OK){
				throw ce;
			}
		}
		
		if(verificaRespuesta(respuesta)){
			codigoRetorno = respuesta.getResponseBansefi().getOTRBAJARECOGEFCTSMTR().getRTRNCD();
		}
		
		return codigoRetorno;
	}
	
	private Ejecutar.ITRBAJARECOGEFCTSMTR initPeticion(RecogidaEfectivoBean recogidaEfectivoBean){
		Ejecutar.ITRBAJARECOGEFCTSMTR contexto = new Ejecutar.ITRBAJARECOGEFCTSMTR();
		
		Ejecutar.ITRBAJARECOGEFCTSMTR.TRBAJARECOGEFCTSMEVT nivel1_1 = new Ejecutar.ITRBAJARECOGEFCTSMTR.TRBAJARECOGEFCTSMEVT();
		Ejecutar.ITRBAJARECOGEFCTSMTR.SMSIGNOCTBLE nivel1_2 = new Ejecutar.ITRBAJARECOGEFCTSMTR.SMSIGNOCTBLE();
		Ejecutar.ITRBAJARECOGEFCTSMTR.STDTRNIPARMV nivel1_3 = new Ejecutar.ITRBAJARECOGEFCTSMTR.STDTRNIPARMV();
		
		Ejecutar.ITRBAJARECOGEFCTSMTR.TRBAJARECOGEFCTSMEVT.SMSOLCTMONEDAE nivel2_1 = new Ejecutar.ITRBAJARECOGEFCTSMTR.TRBAJARECOGEFCTSMEVT.SMSOLCTMONEDAE();
		
		nivel1_1.setCODNUMRCOMONEDA("MXN");
		
		nivel2_1.setCODNRBEEN(super.getEntidad());
		nivel2_1.setCODINTERNOUO(super.getSucursal());
		nivel2_1.setFECHASOLCTSM(recogidaEfectivoBean.getFechaSolicitud());
		nivel2_1.setCODPPL("M");
		nivel2_1.setCODDISTRIB("T");
		nivel2_1.setINDURG(recogidaEfectivoBean.getIndicadorUrgencia());
		nivel2_1.setCODINTERNOUO1(recogidaEfectivoBean.getCentroControlador());
		nivel2_1.setCODINTERNOUO2("");
		nivel2_1.setCODECVSM(recogidaEfectivoBean.getEstatusC());
		nivel2_1.setFECHAABASTREC(recogidaEfectivoBean.getFechaAbastecimiento());
		nivel2_1.setFECHAPROCESOSM(recogidaEfectivoBean.getFechaProceso());
		nivel2_1.setIMPAUTV(BigDecimal.ZERO);
		nivel2_1.setIMPPEDIDOV(recogidaEfectivoBean.getTotalRecogida());
		nivel2_1.setIMPRECBDOV(BigDecimal.ZERO);
		nivel2_1.setIMPCERTFDOV(BigDecimal.ZERO);
		nivel2_1.setINDSMCUADRE("");
		nivel2_1.setINDMOTIVOCUADRE("");
		nivel2_1.setIDINTERNOEMPLEP("");
		nivel2_1.setVALOROBSERSM("");
		
		nivel1_1.setSMSOLCTMONEDAE(nivel2_1);
		
		nivel1_2.setSTDCHAR02("");
		
		nivel1_3.setIDINTERNOTERMTN(super.getTerminal());
		nivel1_3.setNUMSEC(0);
		nivel1_3.setCODTX("VCM06MOU");
		nivel1_3.setCODTXDI("CO07");
		
		contexto.setTRBAJARECOGEFCTSMEVT(nivel1_1);
		contexto.setSMSIGNOCTBLE(nivel1_2);
		contexto.setSTDTRNIPARMV(nivel1_3);
		
		return contexto;
	}
	
	private EjecutarResult ejecutarWS(Ejecutar.ITRBAJARECOGEFCTSMTR contexto){
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(SuprimirRecogidaEfectivoServicio.class, contexto);
		}catch(NoControlableException nce){
			throw new NoControlableException("No se pudo invocar el servicio de"
					+ " suprimir recogida de efectivo.", nce);
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
