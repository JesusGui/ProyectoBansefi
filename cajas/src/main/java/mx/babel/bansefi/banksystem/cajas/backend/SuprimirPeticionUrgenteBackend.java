package mx.babel.bansefi.banksystem.cajas.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.cajas.beans.AutorizacionPeticionUrgenteBean;
import mx.babel.bansefi.banksystem.cajas.webservice.suprimirpeticionurgente.Ejecutar;
import mx.babel.bansefi.banksystem.cajas.webservice.suprimirpeticionurgente.EjecutarResult;
import mx.babel.bansefi.banksystem.cajas.webservice.suprimirpeticionurgente.SuprimirPeticionUrgenteServicio;

@Component
public class SuprimirPeticionUrgenteBackend extends BackEndBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1420925437070314013L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	public int ejecutarTRN(AutorizacionPeticionUrgenteBean autorizacionPeticionUrgenteBean){
		int resultado = 0;
		
		Ejecutar.ITRBAJAPETCNEFCTSMTR contexto = initPeticion(autorizacionPeticionUrgenteBean);
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
			resultado = RETURN_COD_OK;
		}catch (ControlableException ce){
			if(ce.getRtncod() != RETURN_COD_OK){
				throw ce;
			}
		}
		
		return resultado;
	}
	
	private Ejecutar.ITRBAJAPETCNEFCTSMTR initPeticion(AutorizacionPeticionUrgenteBean autorizacionPeticionUrgenteBean) {
		
		Ejecutar.ITRBAJAPETCNEFCTSMTR contexto = new Ejecutar.ITRBAJAPETCNEFCTSMTR();
		
		Ejecutar.ITRBAJAPETCNEFCTSMTR.TRBAJAPETCNEFCTSMEVT nivel1_1 = new Ejecutar.ITRBAJAPETCNEFCTSMTR.TRBAJAPETCNEFCTSMEVT();
		Ejecutar.ITRBAJAPETCNEFCTSMTR.STDTRNIPARMV nivel1_2 = new Ejecutar.ITRBAJAPETCNEFCTSMTR.STDTRNIPARMV();
		
		Ejecutar.ITRBAJAPETCNEFCTSMTR.TRBAJAPETCNEFCTSMEVT.SMSOLCTMONEDAE nivel2_1 = new Ejecutar.ITRBAJAPETCNEFCTSMTR.TRBAJAPETCNEFCTSMEVT.SMSOLCTMONEDAE();
		
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
		nivel2_1.setVALOROBSERSM(autorizacionPeticionUrgenteBean.getObservaciones());
		
		nivel1_1.setCODNUMRCOMONEDA("MXN");
		
		nivel1_1.setSMSOLCTMONEDAE(nivel2_1);
		
		nivel1_2.setIDINTERNOTERMTN(super.getTerminal());
		nivel1_2.setNUMSEC(0);
		nivel1_2.setCODTX("VCC67MOU");
		nivel1_2.setCODTXDI("CO02");
		
		contexto.setTRBAJAPETCNEFCTSMEVT(nivel1_1);
		contexto.setSTDTRNIPARMV(nivel1_2);
		
		super.initialize(contexto);
		
		return contexto;
	}
	
	private EjecutarResult ejecutarWS(Ejecutar.ITRBAJAPETCNEFCTSMTR contexto){
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(SuprimirPeticionUrgenteServicio.class, contexto);
		}catch (NoControlableException nce){
			throw new NoControlableException("Error al invocar el web service"
					+ " de eliminación de petición urgente", nce);
		}
		
		return respuesta;
	}
}
