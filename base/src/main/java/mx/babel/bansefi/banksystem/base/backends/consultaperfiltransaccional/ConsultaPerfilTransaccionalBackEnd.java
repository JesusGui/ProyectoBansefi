package mx.babel.bansefi.banksystem.base.backends.consultaperfiltransaccional;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.TransaccionalidadBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.webservices.consultaperfiltransaccional.ConsultaPerfilTransaccionalServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultaperfiltransaccional.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultaperfiltransaccional.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultaperfiltransaccional.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.wrappers.CedulaConocimientoWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para consulta del perfil transaccional de una persona
 * 
 * @author javier.martinnino
 *
 */
@Component
public class ConsultaPerfilTransaccionalBackEnd extends BackEndBean{

	private static final long serialVersionUID = -388288247366434576L;

	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	@Autowired
	private CedulaConocimientoWrapper cedulaConocimientoWrapper; 
	
	/**
	 * Función encargada de consultar el perfil transaccional de una persona
	 * por medio de servicios web.
	 * 
	 * @param idInterno id de la persona a consultar
	 * @return perfil transaccional de la persona
	 */
	public TransaccionalidadBean ejecutarTRN(Integer idInterno){
		Ejecutar.IPECONSTRANESTIMTRNI contexto = initPeticion(idInterno);
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
			return getTransaccionalidad(respuesta.getResponseBansefi());
		}
		return null;
	}
	
	/**
	 * Función que construye la transaccionalidad a partir de la respuesta al servidor.
	 * 
	 * @param response
	 * @return bean de Transaccionalidad
	 */
	private TransaccionalidadBean getTransaccionalidad(ResponseBansefi response){
		TransaccionalidadBean resultado = null;		
		if(verificaRespuestaCliente(response)){
			resultado = cedulaConocimientoWrapper.wrappTransaccionalidad(response.getOPECONSTRANESTIMTRNO().getPECONSTRANESTIMEVTZ());
		}
		return resultado;
	}	
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param idInterno Id del cliente de tipo persona física
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.IPECONSTRANESTIMTRNI initPeticion(Integer idInterno){
		Ejecutar.IPECONSTRANESTIMTRNI contexto = new Ejecutar.IPECONSTRANESTIMTRNI();
		Ejecutar.IPECONSTRANESTIMTRNI.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.IPECONSTRANESTIMTRNI.STDTRNIPARMV();
		Ejecutar.IPECONSTRANESTIMTRNI.PECONSTRANESTIMEVTY cliente =
				new Ejecutar.IPECONSTRANESTIMTRNI.PECONSTRANESTIMEVTY();
		
		super.initialize(contexto);
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_PE_CONS_INDV_USO_CTA_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		
		cliente.setCODNRBEEN(super.getEntidad());
		if (idInterno !=null){
			cliente.setIDINTERNOPE(idInterno);
		}
		contexto.setPECONSTRANESTIMEVTY(cliente);
		contexto.setSTDTRNIPARMV(contextoEntrada);
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.IPECONSTRANESTIMTRNI contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaPerfilTransaccionalServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta de "
					+ "transaccionalidad de personas.", e);
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
	 * de la persona física.
	 * 
	 * @param response Respuesta Bansefi con datos de la persona moral
	 * @return <code>true</code> si la respuesta Bansefi contiene elemento no nulos
	 */
	private Boolean verificaRespuestaCliente(ResponseBansefi response){
		Boolean noNulo = false;
		if(response!= null && response.getOPECONSTRANESTIMTRNO() != null &&
				response.getOPECONSTRANESTIMTRNO().getPECONSTRANESTIMEVTZ() != null &&
						response.getOPECONSTRANESTIMTRNO().getPECONSTRANESTIMEVTZ().getPEPERSFNTEINGRE() != null
						&& response.getOPECONSTRANESTIMTRNO().getPECONSTRANESTIMEVTZ().getPEPERSTRANESTIME() != null){
			noNulo = true;
		}
		return noNulo;
	}
		
}
