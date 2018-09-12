package mx.babel.bansefi.banksystem.cajas.backend;

import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.cajas.webservice.autorizacionpeticiontotales.AutorizacionPeticionTotalesServicio;
import mx.babel.bansefi.banksystem.cajas.webservice.autorizacionpeticiontotales.Ejecutar;
import mx.babel.bansefi.banksystem.cajas.webservice.autorizacionpeticiontotales.EjecutarResult;
import mx.babel.bansefi.banksystem.cajas.beans.AutorizacionPeticionTotalesBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutorizacionPeticionTotalesBackend extends BackEndBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6661981719450222103L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	public int ejecutarTRN(List<AutorizacionPeticionTotalesBean> listaAutorizaciones, int fechaAbastecimiento){
		
		Ejecutar.ITRAUTTOTCONFSMTRNI contexto = initPeticion(listaAutorizaciones, fechaAbastecimiento);
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
			return RETURN_COD_OK;
		}catch (ControlableException ce){
			if(ce.getRtncod() != RETURN_COD_OK){
				throw ce;
			}
		}
		return 0;
	}
	
	/**
	 * Método para inicializar objeto de petición
	 * @return Objeto de petición al ws
	 */
	private Ejecutar.ITRAUTTOTCONFSMTRNI initPeticion(List<AutorizacionPeticionTotalesBean> listaAutorizaciones, int fechaAbastecimiento){
		
		Ejecutar.ITRAUTTOTCONFSMTRNI contexto = new Ejecutar.ITRAUTTOTCONFSMTRNI();
		
		contexto.setELEVATORPOSITION(0);
		contexto.setSCROLLABLEOCCURS(300);
		
		Ejecutar.ITRAUTTOTCONFSMTRNI.STDTRNIPARMV nivel1_1 = new Ejecutar.ITRAUTTOTCONFSMTRNI.STDTRNIPARMV();
		Ejecutar.ITRAUTTOTCONFSMTRNI.TRAUTTOTCONFSMEVTY nivel1_2 = new Ejecutar.ITRAUTTOTCONFSMTRNI.TRAUTTOTCONFSMEVTY();
		Ejecutar.ITRAUTTOTCONFSMTRNI.TRAUTTOTCONFSMEVTY.SMAUTTOTV nivel1_3;
		
		nivel1_1.setIDINTERNOTERMTN(super.getTerminal());
		nivel1_1.setNUMSEC(0);
		nivel1_1.setCODTX("VCC16MOU");
		nivel1_1.setCODTXDI("CC16");
		
		nivel1_2.setFECHASOLCTSM(fechaAbastecimiento);
		
		contexto.setSTDTRNIPARMV(nivel1_1);
		contexto.setTRAUTTOTCONFSMEVTY(nivel1_2);
		
		for (AutorizacionPeticionTotalesBean autorizacionPeticionTotalesBean : listaAutorizaciones) {
			
			nivel1_3 = new Ejecutar.ITRAUTTOTCONFSMTRNI.TRAUTTOTCONFSMEVTY.SMAUTTOTV();
			
			nivel1_3.setCODINTERNOUO(autorizacionPeticionTotalesBean.getCentro());
			nivel1_3.setIMPPEDIDOV(autorizacionPeticionTotalesBean.getImportePedido());
			nivel1_3.setIMPAUTV(autorizacionPeticionTotalesBean.getImporteAutorizado());
			
			contexto.getTRAUTTOTCONFSMEVTY().getSMAUTTOTV().add(nivel1_3);
		}
		
		super.initialize(contexto);
		
		return contexto;
	}
	
	private EjecutarResult ejecutarWS(Ejecutar.ITRAUTTOTCONFSMTRNI contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(AutorizacionPeticionTotalesServicio.class, contexto);
		}catch (NoControlableException nce){
			throw new NoControlableException("Error al invocar servicio web de autorización "
					+ " de peticiones de efectivo.", nce);
		}
		
		return respuesta;
	}
}
