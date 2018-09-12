package mx.babel.bansefi.banksystem.cajas.backend;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.cajas.beans.AutorizacionPeticionTotalesBean;
import mx.babel.bansefi.banksystem.cajas.webservice.consultapeticionestotales.*;
import mx.babel.bansefi.banksystem.cajas.webservice.consultapeticionestotales.ResponseBansefi.OTRAUTTOTINICSMTRNO.TRAUTTOTINICSMEVTZ.SMAUTTOTV;

@Component
public class ConsultaPeticionTotalesBackend extends BackEndBean{
	
	private static final long serialVersionUID = -2528914585118856058L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	private List<AutorizacionPeticionTotalesBean> listaPeticiones;
	
	private BigDecimal saldoAnterior;
	
	public List<AutorizacionPeticionTotalesBean> getListaPeticiones() {
		return listaPeticiones;
	}

	public void setListaPeticiones(
			List<AutorizacionPeticionTotalesBean> listaPeticiones) {
		this.listaPeticiones = listaPeticiones;
	}
	
	public BigDecimal getSaldoAnterior(){
		return saldoAnterior;
	}
	
	public void setSaldoAnterior(BigDecimal saldoAnterior){
		this.saldoAnterior = saldoAnterior;
	}

	public void ejecutarTRN(int fechaAbastecimiento){
		
		Ejecutar.ITRAUTTOTINICSMTRNI contexto = initPeticion(fechaAbastecimiento);
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if(ce.getRtncod() != RETURN_COD_OK){
				throw ce;
			}
		}
		if(verificaResponseBansefi(respuesta)){			
			getParrilla(respuesta.getResponseBansefi());
		}
	}
	
	/**
	 * Método para inicializar objeto de petición
	 * @return Objeto de petición al ws
	 */
	private Ejecutar.ITRAUTTOTINICSMTRNI initPeticion(int fechaAbastecimiento){
		
		Ejecutar.ITRAUTTOTINICSMTRNI contexto = new Ejecutar.ITRAUTTOTINICSMTRNI();
		Ejecutar.ITRAUTTOTINICSMTRNI.STDTRNIPARMV nivel1_1 = new Ejecutar.ITRAUTTOTINICSMTRNI.STDTRNIPARMV();
		Ejecutar.ITRAUTTOTINICSMTRNI.TRAUTTOTINICSMEVTY nivel1_2 = new Ejecutar.ITRAUTTOTINICSMTRNI.TRAUTTOTINICSMEVTY();
		
		nivel1_1.setIDINTERNOTERMTN(super.getTerminal());
		nivel1_1.setNUMSEC(0);
		nivel1_1.setCODTX("VCC15COU");
		nivel1_1.setCODTXDI("CC15");
		
		nivel1_2.setFECHASOLCTSM(fechaAbastecimiento);
		
		contexto.setSTDTRNIPARMV(nivel1_1);
		contexto.setTRAUTTOTINICSMEVTY(nivel1_2);
		
		super.initialize(contexto);
		
		return contexto;
	}
	
	/**
	 * Método para obtener la respuesta del ws a partir de un objeto de petición
	 * @param contexto Objeto de petición
	 * @return respuesta del ws
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRAUTTOTINICSMTRNI contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaPeticionTotalesServicio.class, contexto);
		}catch (NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ " de autorización de peticiones de efectivo totales.", e);
		}
		return respuesta;
	}
	
	/**
	 * Función que valida que la respues del servidor no este vacía. 
	 *  
	 * @param respuesta Objeto respuesta del servicio web
	 * @return <code>true</code> si la respuesta no esta vacía.
	 */
	private Boolean verificaResponseBansefi(EjecutarResult respuesta){
		Boolean noNulo = false;
		if(respuesta != null && respuesta.getResponseBansefi() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con los datos
	 * de cuentas.
	 * 
	 * @param response Respuesta Bansefi con datos de cuentas
	 * @return <code>true</code> si la respuesta Bansefi contiene los datos de alta
	 */
	private Boolean verificaRespuesta(ResponseBansefi response){
		Boolean noNulo = false;
		if(response != null && response.getOTRAUTTOTINICSMTRNO() != null &&
				response.getOTRAUTTOTINICSMTRNO().getSTDTRNMSJPARMV() != null &&
				response.getOTRAUTTOTINICSMTRNO().getTRAUTTOTINICSMEVTZ() != null &&
				response.getOTRAUTTOTINICSMTRNO().getTRAUTTOTINICSMEVTZ().getSMAUTTOTV() != null &&
				response.getOTRAUTTOTINICSMTRNO().getTRAUTTOTINICSMEVTZ().getEXEXISTMONEDAE() != null){
			noNulo = true;
		}
		return noNulo;
	}	
	
	/**
	 * Método que construye la lista de peticiones a partir de la respuesta del ws
	 * @param respuesta del ws
	 * @return lista de existencia de peticiones
	 */
	private List<AutorizacionPeticionTotalesBean> getParrilla(ResponseBansefi respuesta){
		
		List<AutorizacionPeticionTotalesBean> listaPeticiones = new ArrayList<>();
		AutorizacionPeticionTotalesBean autorizacionPeticionTotalesBean;
		
		setSaldoAnterior(respuesta.getOTRAUTTOTINICSMTRNO().getTRAUTTOTINICSMEVTZ().getEXEXISTMONEDAE().getTOTEFCT());
		if(verificaRespuesta(respuesta)){
			for(SMAUTTOTV peticion : respuesta.getOTRAUTTOTINICSMTRNO().getTRAUTTOTINICSMEVTZ().getSMAUTTOTV()){				
				if(peticion.getIMPPEDIDOV().intValue() > 0){
					
					autorizacionPeticionTotalesBean = new AutorizacionPeticionTotalesBean();
					
					autorizacionPeticionTotalesBean.setCentro(peticion.getCODINTERNOUO());
					autorizacionPeticionTotalesBean.setImportePedido(peticion.getIMPPEDIDOV());
					autorizacionPeticionTotalesBean.setImporteAutorizado(peticion.getIMPAUTV());
					
					listaPeticiones.add(autorizacionPeticionTotalesBean);
				}
			}
		}
		
		setListaPeticiones(listaPeticiones);
		
		return getListaPeticiones();
	}
}