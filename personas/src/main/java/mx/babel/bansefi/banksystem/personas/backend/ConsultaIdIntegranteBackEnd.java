package mx.babel.bansefi.banksystem.personas.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.personas.webservices.consultaidintegrante.ConsultaIdIntegranteServicio;
import mx.babel.bansefi.banksystem.personas.webservices.consultaidintegrante.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.consultaidintegrante.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.consultaidintegrante.ResponseBansefi;

/**
 * Back End para el ws de consulta de id integrante.
 * @author mario.montesdeoca
 *
 */
@Component
public class ConsultaIdIntegranteBackEnd extends BackEndBean{

	private static final long serialVersionUID = -2876651346271278558L;
	
	@Autowired
    ServicioWebUtils servicioWebUtils;
    
	/**
	 * Función para obtener los datos de un gestor invocando un 
	 * servicio web.
	 * 
	 * @param idInterno Id interno del gestor.
	 * @return Objeto con atributos del gestor.
	 */
    public String ejecutarTRN(Integer idInterno, String idExterno)throws NoControlableException, ControlableException{
		Ejecutar.IPECONSINTGRNTTRNI contexto = initPeticion(idInterno, idExterno);
		
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		String idIntegrante  = null;
		
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return idIntegrante;
			}
		}		
		
		if(verificaResponseBansefi(respuesta)){
			idIntegrante = getIdIntegrante(respuesta.getResponseBansefi());
		}
		return idIntegrante;
    }
    
	/**
	 * Funciòn encargada de crear un objeto de tipo grupo a través de la 
	 * respuesta obetenida del servicio web.
	 * 
	 * @param response Respuesta del servicio web
	 * @return Objeto de tipo grupo
	 */
	private String getIdIntegrante(ResponseBansefi response){
		String idIntegrante  = null;		
		if(verificaRespuesta(response)){
			ResponseBansefi.OPECONSINTGRNTTRNO.PECONSINTGRNTEVTZ integrante = 
				response.getOPECONSINTGRNTTRNO().getPECONSINTGRNTEVTZ();
			idIntegrante = integrante.getPEDICDTAPCE().getIDINTEGRANTEPE();
		}
		return idIntegrante;
	}
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param idExterno Id del cliente de tipo grupo
	 * @return Objeto de petición al servicio web
	 */
	private Ejecutar.IPECONSINTGRNTTRNI initPeticion(Integer idInterno, String idExterno){
		Ejecutar.IPECONSINTGRNTTRNI contexto = new Ejecutar.IPECONSINTGRNTTRNI();
		Ejecutar.IPECONSINTGRNTTRNI.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.IPECONSINTGRNTTRNI.STDTRNIPARMV();
		Ejecutar.IPECONSINTGRNTTRNI.PECONSINTGRNTEVTY cuerpoContexto =
				new Ejecutar.IPECONSINTGRNTTRNI.PECONSINTGRNTEVTY();
		Ejecutar.IPECONSINTGRNTTRNI.PECONSINTGRNTEVTY.PEDICDTAPCP integrante = 
				new Ejecutar.IPECONSINTGRNTTRNI.PECONSINTGRNTEVTY.PEDICDTAPCP();
		
		integrante.setCODNRBEEN(super.getEntidad());
		integrante.setIDINTERNOPE(idInterno);
		integrante.setIDEXTPE(idExterno);
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_PE_CONS_INTGRNT_TRN);
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		
		contexto.setSTDTRNIPARMV(contextoEntrada);
		contexto.setPECONSINTGRNTEVTY(cuerpoContexto);
		cuerpoContexto.setPEDICDTAPCP(integrante);
		super.initialize(contexto);
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.IPECONSINTGRNTTRNI contexto){
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaIdIntegranteServicio.class, contexto);
		}catch(Exception e){
			throw new ControlableException("Error al invocar servicio web de consulta de "
					+ "id integrante.", e);
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
		if(respuesta != null && respuesta.getResponseBansefi() != null ){
			noNulo = true;
		}
		return noNulo;
	}
	
	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con los datos
	 * del grupo.
	 * 
	 * @param response Respuesta Bansefi con datos del grupo
	 * @return <code>true</code> si la respuesta Bansefi contiene un objeto de tipo grupo
	 */
	private Boolean verificaRespuesta(ResponseBansefi response){
		Boolean noNulo = false;
		if(response!= null && response.getOPECONSINTGRNTTRNO() != null &&
				response.getOPECONSINTGRNTTRNO().getPECONSINTGRNTEVTZ() != null){
			noNulo = true;
		}
		return noNulo;
	}
}
