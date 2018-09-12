package mx.babel.bansefi.banksystem.cajas.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.cajas.webservice.validaentidad.Ejecutar;
import mx.babel.bansefi.banksystem.cajas.webservice.validaentidad.EjecutarResult;
import mx.babel.bansefi.banksystem.cajas.webservice.validaentidad.ValidaEntidadServicio;

@Component
public class ValidaEntidadBackend extends BackEndBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2675625808947486719L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	public int ejecutarTRN(){
		
		int resultado = 0;
		
		Ejecutar.ITRVALIDAENTIDADTRNI contexto = initPeticion();
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch(ControlableException ce){
			if(ce.getRtncod() != RETURN_COD_OK){
				throw ce;
			}
		}
		
		if(verificaResponseBansefi(respuesta)){
			resultado = getResultado(respuesta);
		}
		
		return resultado;
		
	}
	
	private Ejecutar.ITRVALIDAENTIDADTRNI initPeticion(){
		
		Ejecutar.ITRVALIDAENTIDADTRNI contexto = new Ejecutar.ITRVALIDAENTIDADTRNI();
		
		Ejecutar.ITRVALIDAENTIDADTRNI.TRCONENTIDADEVTY nivel1_1 = new Ejecutar.ITRVALIDAENTIDADTRNI.TRCONENTIDADEVTY();
		Ejecutar.ITRVALIDAENTIDADTRNI.STDTRNIPARMV nivel1_2 = new Ejecutar.ITRVALIDAENTIDADTRNI.STDTRNIPARMV();
		Ejecutar.ITRVALIDAENTIDADTRNI.TRCONENTIDADEVTY.CRENTIDADP nivel2_1 = new Ejecutar.ITRVALIDAENTIDADTRNI.TRCONENTIDADEVTY.CRENTIDADP();
		
		nivel2_1.setCODNRBEEN(super.getEntidad());
		
		nivel1_2.setIDINTERNOTERMTN(super.getTerminal());
		nivel1_2.setNUMSEC(0);
		nivel1_2.setCODTX("GCA05CON");
		
		nivel1_1.setCRENTIDADP(nivel2_1);
		
		contexto.setTRCONENTIDADEVTY(nivel1_1);
		contexto.setSTDTRNIPARMV(nivel1_2);
		
		super.initialize(contexto);
		
		return contexto;
	}
	
	private EjecutarResult ejecutarWS(Ejecutar.ITRVALIDAENTIDADTRNI contexto){
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ValidaEntidadServicio.class, contexto);
		}catch (NoControlableException nce){
			throw new NoControlableException("Error al ejecutar el web service de"
					+ " valida entidad.", nce);			
		}
		
		return respuesta;
	}
	
	private Boolean verificaResponseBansefi(EjecutarResult respuesta){
		Boolean noNulo = false;
		if(respuesta != null && respuesta.getResponseBansefi() != null){
			noNulo = true;
		}
		
		return noNulo;
	}
	
	private int getResultado(EjecutarResult respuesta){
		
		int resultado = 0;
		
		if(respuesta.getResponseBansefi().getOTRVALIDAENTIDADTRNO().getTRCONENTIDADEVTZ().getCODNRBEEN().equals(super.getEntidad())){
			resultado = RETURN_COD_OK;
		}
		
		return resultado;
	}
}
