package mx.babel.bansefi.banksystem.personas.backend;

import java.io.Serializable;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ReferenciaPersonalBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionreferenciaspersonalesriesgo.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionreferenciaspersonalesriesgo.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionreferenciaspersonalesriesgo.ModificacionReferenciasPersonalesRiesgoServicio;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionreferenciaspersonalesriesgo.ResponseBansefi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para la modificacion de referencias personales para personas de riesgo
 * 
 * @author javier.martinnino
 *
 */
@Component
public class ModificacionReferenciasPersonalesRiesgoBackEnd extends BackEndBean implements Serializable {
	
	private static final long serialVersionUID = -5397617229403876960L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	/**
	 * Función encargada de modificar las referencias personales de una persona de riesgo por medio de servicios web.
	 * 
	 * @param idInterna del cliente 
	 * @param referencia a modificar
	 * @return Integer resultado de la operacion
	 */
	public Integer ejecutarTRN(Integer idInterna,ReferenciaPersonalBean referencia){
		Ejecutar.IPEMODIINDVREFPERTRN contexto = initPeticion(idInterna,referencia);
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
		if(verificaResponseBansefi(respuesta)){
			return modificacionReferencia(respuesta.getResponseBansefi());
		}
		return null;
	}
		
	/**
	 * Función que devuelve el resultado de la operacion
	 * 
	 * @param response
	 * @return Integer resultado de la operacion
	 */
	private Integer modificacionReferencia(ResponseBansefi response){
		Integer resultado = null;		
		if(verificaRespuestaCliente(response)){						
			resultado = response.getOPEMODIINDVREFPERTRN().getRTRNCD();
		}
				
		return resultado;
	}
	
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param idInterna del cliente
	 * @param referencia a modificar
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.IPEMODIINDVREFPERTRN initPeticion(Integer idInterna,ReferenciaPersonalBean referencia){
		Ejecutar.IPEMODIINDVREFPERTRN contexto = new Ejecutar.IPEMODIINDVREFPERTRN();
		
		Ejecutar.IPEMODIINDVREFPERTRN.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.IPEMODIINDVREFPERTRN.STDTRNIPARMV();
		
		Ejecutar.IPEMODIINDVREFPERTRN.PEMODIINDVREFPEREVT.PEPERSRLREFPERP referenciasPersonales =
				new Ejecutar.IPEMODIINDVREFPERTRN.PEMODIINDVREFPEREVT.PEPERSRLREFPERP();
		
		Ejecutar.IPEMODIINDVREFPERTRN.PEMODIINDVREFPEREVT.PEPERSRLREFPERE referenciaModificada =
				new Ejecutar.IPEMODIINDVREFPERTRN.PEMODIINDVREFPEREVT.PEPERSRLREFPERE();
		
		super.initialize(contexto);
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_PE_ALTA_INDV_REF_PER_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		
		referenciasPersonales.setCODNRBEEN(super.getEntidad());
		referenciasPersonales.setIDINTERNOPE(idInterna);
		referenciasPersonales.setNUMSEC(referencia.getNumeroSecuencia());
		
		referenciaModificada.setCODNRBEEN(super.getEntidad());
		referenciaModificada.setIDINTERNOPE(idInterna);
		referenciaModificada.setNUMSEC(referencia.getNumeroSecuencia());
		referenciaModificada.setNOMBINNOMBPILA(referencia.getNombre());
		referenciaModificada.setNOMBINAPE1IN(referencia.getApellidoPaterno());
		referenciaModificada.setNOMBINAPE2IN(referencia.getApellidoMaterno());
		referenciaModificada.setTELEFONO(referencia.getTelefono());
		referenciaModificada.setDOMIC50(referencia.getDomicilio());
		referenciaModificada.setPERSRLTIT(referencia.getRelacionParentesco());
				
		contexto.getPEMODIINDVREFPEREVT().setPEPERSRLREFPERP(referenciasPersonales);
		contexto.getPEMODIINDVREFPEREVT().setPEPERSRLREFPERE(referenciaModificada);
		contexto.setSTDTRNIPARMV(contextoEntrada);
				
		return contexto;
	}
		
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.IPEMODIINDVREFPERTRN contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ModificacionReferenciasPersonalesRiesgoServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de modificacion de referencias personales"
					+ "para clientes de riesgo.", e);
		}
		return respuesta;
	}
	
	/**
	 * Función que valida que la respuesta del servidor no este vacía. 
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
	 * no nulos.
	 * 
	 * @param response Respuesta Bansefi
	 * @return <code>true</code> si la respuesta Bansefi contiene elemento no nulos
	 */
	private Boolean verificaRespuestaCliente(ResponseBansefi response){
		Boolean noNulo = false;
		if(response!= null && response.getOPEMODIINDVREFPERTRN() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
}
