package mx.babel.bansefi.banksystem.cajas.backend;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cajas.beans.HoraBean;
import mx.babel.bansefi.banksystem.cajas.webservice.consultahoralimite.*;

@Component

public class ConsultaHoraLimiteBackend extends BackEndBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7065648055708456016L;
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	
	public void ejecutarTRN(HoraBean hora)
	{
		Ejecutar.ITRCONSPXCCATRNI contexto=initPeticion();
		EjecutarResult respuesta= ejecutarWS(contexto);
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return;
			}
		}
		
		if(verificaResponseBansefi(respuesta)){
			getHora(respuesta.getResponseBansefi(),hora);
		}
		
		
	}
	
	public Date ejecutarTRN()
	{
		Ejecutar.ITRCONSPXCCATRNI contexto=initPeticion();
		EjecutarResult respuesta= ejecutarWS(contexto);
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_OK){
				throw ce;
			}
		}
		
		if(verificaResponseBansefi(respuesta)){
			return getHoraRecogida(respuesta.getResponseBansefi());
		}else{
			return new Date();
		}
	}
	
	private void getHora(ResponseBansefi respuesta, HoraBean hora)
	{
		IntegerToDateConverter converter = new IntegerToDateConverter();
		
		if(verificaRespuesta(respuesta)){
			
			Date horaLimitePeticion = converter.convertTo(respuesta.getOTRCONSPXCCATRNO().getTRCONSPXCCAEVTZ().getPXPARAMCCAE().getHORALIMITEPETCN(), null);
			hora.setHoraLimitePeticon(horaLimitePeticion);	
		}
	}
	
	private Date getHoraRecogida(ResponseBansefi respuesta){
		
		Date horaLimitePeticion = new Date();
		
		if(verificaRespuesta(respuesta)){
			
			IntegerToDateConverter converter = new IntegerToDateConverter();
			horaLimitePeticion = converter.convertTo(respuesta.getOTRCONSPXCCATRNO().getTRCONSPXCCAEVTZ().getPXPARAMCCAE().getHORALIMITERECOG(), null);
		}
		
		return horaLimitePeticion;
	}
	
	/**
	 * Método para inicializar objeto de petición
	 * @return Objeto de petición al ws
	 */
	private Ejecutar.ITRCONSPXCCATRNI initPeticion(){
	Ejecutar.ITRCONSPXCCATRNI contexto= new Ejecutar.ITRCONSPXCCATRNI();
	
	Ejecutar.ITRCONSPXCCATRNI.TRCONSPXCCAEVTY evt_y= new Ejecutar.ITRCONSPXCCATRNI.TRCONSPXCCAEVTY();
	
	Ejecutar.ITRCONSPXCCATRNI.TRCONSPXCCAEVTY.PXPARAMCCAP pxparamccap= new Ejecutar.ITRCONSPXCCATRNI.TRCONSPXCCAEVTY.PXPARAMCCAP();
	
	pxparamccap.setCODNRBEEN(super.getEntidad());
	evt_y.setPXPARAMCCAP(pxparamccap);
	
	Ejecutar.ITRCONSPXCCATRNI.STDTRNIPARMV  stdtrniparamv = new Ejecutar.ITRCONSPXCCATRNI.STDTRNIPARMV();
	
	stdtrniparamv.setIDINTERNOTERMTN(super.getTerminal());
	stdtrniparamv.setCODTX("VCM11COU");
	stdtrniparamv.setCODTXDI("CC27");
	
	contexto.setSTDTRNIPARMV(stdtrniparamv);
	contexto.setTRCONSPXCCAEVTY(evt_y);
	
	super.initialize(contexto);
	
	return contexto;
		
	
	}
	/**
	 * Método para obtener la respuesta del ws a partir de un objeto de petición
	 * @param contexto Objeto de petición
	 * @return respuesta del ws
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRCONSPXCCATRNI contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaHoraLimiteServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "de hora limite.", e);
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
		if(response != null && response.getOTRCONSPXCCATRNO() != null &&
				response.getOTRCONSPXCCATRNO().getTRCONSPXCCAEVTZ() != null &&
				response.getOTRCONSPXCCATRNO().getTRCONSPXCCAEVTZ().getPXPARAMCCAE() != null){
			noNulo = true;
		}
		return noNulo;
	}
}
