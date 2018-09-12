package mx.babel.bansefi.banksystem.base.backends.validaidexterna;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaFisicaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaMoralBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.webservices.validaidexterna.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.validaidexterna.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.validaidexterna.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.webservices.validaidexterna.ValidaIdExternaServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para validar si un cliente ya existe a partir de su nombre e identificacion externa
 * 
 * @author javier.martinnino
 *
 */
@Component
public class ValidaIdExternaBackEnd extends BackEndBean{
	
	private static final long serialVersionUID = -3526829254384298196L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Función encargada de validar si un cliente ya existe a partir de su nombre e identificacion externa
	 * por medio de servicios web.
	 * 
	 * @param cliente cliente consultar
	 * @return idInterno del cliente existente
	 */
	public Integer ejecutarTRN(String nombre, String apePaterno, String apeMaterno, String tipoIdentificacion,
			String numIdentificacion){
		Ejecutar.ITRPECONSVALIDEXTTR contexto = initPeticion(nombre, apePaterno, apeMaterno, tipoIdentificacion,
				numIdentificacion);
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return null;
			}			
		}
		
		return getIdInterno(respuesta.getResponseBansefi());
	}
	
	/**
	 * Función encargada de validar si un cliente ya existe a partir de su nombre e identificacion externa
	 * por medio de servicios web.
	 * 
	 * @param cliente cliente consultar
	 * @return idInterno del cliente existente
	 */
	public Integer ejecutarTRN(String razonSocial, String tipoIdentificacion,
			String numIdentificacion){
		Ejecutar.ITRPECONSVALIDEXTTR contexto = initPeticion(razonSocial, tipoIdentificacion,
				numIdentificacion);
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return null;
			}			
		}
		
		return getIdInterno(respuesta.getResponseBansefi());
	}
	
	/**
	 * Función que construye la respuesta a partir de los datos del servidor.
	 * 
	 * @param response
	 * @return
	 */
	private Integer getIdInterno(ResponseBansefi response){
		Integer resultado = null;		
		if(verificaRespuestaCliente(response)){
			resultado = response.getOTRPECONSVALIDEXTTR().getTRPECONSVALIDEXTEVT().getPEIDINTERNODUPLIV().getIDINTERNOPE();			
		}
		return resultado;
	}
	
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param cliente cliente de tipo persona física
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITRPECONSVALIDEXTTR initPeticion(String nombre, String apePaterno, String apeMaterno, String tipoIdentificacion,
			String numIdentificacion){
		
		Ejecutar.ITRPECONSVALIDEXTTR contexto = new Ejecutar.ITRPECONSVALIDEXTTR();
		
		Ejecutar.ITRPECONSVALIDEXTTR.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRPECONSVALIDEXTTR.STDTRNIPARMV();
		
		Ejecutar.ITRPECONSVALIDEXTTR.TRPECONSVALIDEXTEVT cuerpoContexto =
				new Ejecutar.ITRPECONSVALIDEXTTR.TRPECONSVALIDEXTEVT();
		
		Ejecutar.ITRPECONSVALIDEXTTR.TRPECONSVALIDEXTEVT.PEINDVPERSCBNOMIDEX persona =
				new Ejecutar.ITRPECONSVALIDEXTTR.TRPECONSVALIDEXTEVT.PEINDVPERSCBNOMIDEX();
		
		Ejecutar.ITRPECONSVALIDEXTTR.TRPECONSVALIDEXTEVT.PEINDVPERSCBNOMIDEX.INDFISICAJURIDICA personajuridica =
				new Ejecutar.ITRPECONSVALIDEXTTR.TRPECONSVALIDEXTEVT.PEINDVPERSCBNOMIDEX.INDFISICAJURIDICA();
		
		super.initialize(contexto);
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_PE_CONS_VAL_ID_EXT_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
			
		persona.setCODNRBEEN(super.getEntidad());
		if((apePaterno == null || "".equals(apePaterno)) 
				&& (apeMaterno == null || "".equals(apeMaterno)) 
				&& (nombre == null || "".equals(nombre))){
			persona.setTRANCD("ID_EXT_PE");
			persona.setNOMBINAPE1IN("");
			persona.setNOMBINAPE2IN("");
			persona.setNOMBINNOMBPILA("");
		} else {
			persona.setTRANCD("");
			persona.setNOMBINAPE1IN(apePaterno);
			persona.setNOMBINAPE2IN(apeMaterno);
			persona.setNOMBINNOMBPILA(nombre);
		}
		persona.setCODIDEXTPERS(tipoIdentificacion);
		persona.setIDEXTPE(numIdentificacion);
		
		personajuridica.setSTDCHAR01("F");
		persona.setINDFISICAJURIDICA(personajuridica);
				
		contexto.setTRPECONSVALIDEXTEVT(cuerpoContexto);
		contexto.getTRPECONSVALIDEXTEVT().setPEINDVPERSCBNOMIDEX(persona);
		contexto.setSTDTRNIPARMV(contextoEntrada);
		
		return contexto;
	}
	
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param cliente cliente de tipo persona física
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITRPECONSVALIDEXTTR initPeticion(String razonSocial, String tipoIdentificacion,
			String numIdentificacion){
		
		Ejecutar.ITRPECONSVALIDEXTTR contexto = new Ejecutar.ITRPECONSVALIDEXTTR();
		
		Ejecutar.ITRPECONSVALIDEXTTR.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRPECONSVALIDEXTTR.STDTRNIPARMV();
		
		Ejecutar.ITRPECONSVALIDEXTTR.TRPECONSVALIDEXTEVT cuerpoContexto =
				new Ejecutar.ITRPECONSVALIDEXTTR.TRPECONSVALIDEXTEVT();
		
		Ejecutar.ITRPECONSVALIDEXTTR.TRPECONSVALIDEXTEVT.PEINDVPERSCBNOMIDEX persona =
				new Ejecutar.ITRPECONSVALIDEXTTR.TRPECONSVALIDEXTEVT.PEINDVPERSCBNOMIDEX();
		
		Ejecutar.ITRPECONSVALIDEXTTR.TRPECONSVALIDEXTEVT.PEINDVPERSCBNOMIDEX.INDFISICAJURIDICA personajuridica =
				new Ejecutar.ITRPECONSVALIDEXTTR.TRPECONSVALIDEXTEVT.PEINDVPERSCBNOMIDEX.INDFISICAJURIDICA();
		
		super.initialize(contexto);
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_PE_CONS_VAL_ID_EXT_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
			
		persona.setCODNRBEEN(super.getEntidad());
		
		if((razonSocial == null || "".equals(razonSocial))){
			persona.setTRANCD("ID_EXT_PE");
			persona.setDENOMLEGALOR("");
		} else {
			persona.setTRANCD("");
			persona.setDENOMLEGALOR(razonSocial);
		}
		
		persona.setCODIDEXTPERS(tipoIdentificacion);
		persona.setIDEXTPE(numIdentificacion);
		
		personajuridica.setSTDCHAR01("J");
		persona.setINDFISICAJURIDICA(personajuridica);
				
		contexto.setTRPECONSVALIDEXTEVT(cuerpoContexto);
		contexto.getTRPECONSVALIDEXTEVT().setPEINDVPERSCBNOMIDEX(persona);
		contexto.setSTDTRNIPARMV(contextoEntrada);
		
		return contexto;
	}
	
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRPECONSVALIDEXTTR contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ValidaIdExternaServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web que valida "
					+ "si existe un cliente con la misma id externa.", e);
		}
		return respuesta;
	}
	
	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con los datos
	 * de la persona física.
	 * 
	 * @param response Respuesta Bansefi con datos de la persona moral
	 * @return <code>true</code> si la respuesta Bansefi contiene elemento no nulos
	 */
	private Boolean verificaRespuestaCliente(ResponseBansefi response){
		Boolean noNulo = false;
		if(response!= null && response.getOTRPECONSVALIDEXTTR() != null &&
				response.getOTRPECONSVALIDEXTTR().getTRPECONSVALIDEXTEVT() != null &&
						response.getOTRPECONSVALIDEXTTR().getTRPECONSVALIDEXTEVT().getPEIDINTERNODUPLIV() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
}
