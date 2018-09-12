package mx.babel.bansefi.banksystem.base.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaFisicaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.webservices.modificacionperfiltransaccional.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.modificacionperfiltransaccional.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.modificacionperfiltransaccional.ModificacionPerfilTransaccionalServicio;
import mx.babel.bansefi.banksystem.base.webservices.modificacionperfiltransaccional.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.wrappers.ClienteWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para la modificacion del perfil transaccional de un cliente en especifico
 * 
 * @author javier.martinnino
 *
 */
@Component
public class ModificacionPerfilTransaccionalPersonaBackEnd extends BackEndBean{

	private static final long serialVersionUID = 8959394319961221281L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	@Autowired
	ClienteWrapper clienteWrapper;
	
	/**
	 * Función encargada de la modificacion del perfil transaccional de un cliente en especifico
	 * por medio de servicios web.
	 * 
	 * @param persona persona a modificar
	 * @return Resultado de la modificacion
	 * @throws NoControlableException Excepción controlada de errores en front end
	 * @throws ControlableException Excepción no controlada de errores en host
	 */
	public Integer ejecutarTRN(ClientePersonaFisicaBean persona)
		throws NoControlableException, ControlableException{
		Ejecutar.IPEMODIINDVPERFTRANT contexto = initPeticion(persona);
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
		return modificacionPerfilTransaccional(respuesta.getResponseBansefi());
	}
	
	/**
	 * Función que construye la salida a partir de la respuesta al servidor.
	 * 
	 * @param response
	 * @return
	 */
	private Integer modificacionPerfilTransaccional(ResponseBansefi response){
		Integer resultado = null;		
		if(verificaRespuestaCliente(response)){
			return response.getOPEMODIINDVPERFTRANT().getRTRNCD();			
		}
		return resultado;
	}
	
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param persona persona a dar de alta como cliente
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.IPEMODIINDVPERFTRANT initPeticion(ClientePersonaFisicaBean persona){
		Ejecutar.IPEMODIINDVPERFTRANT contexto = new Ejecutar.IPEMODIINDVPERFTRANT();
		Ejecutar.IPEMODIINDVPERFTRANT.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.IPEMODIINDVPERFTRANT.STDTRNIPARMV();
		Ejecutar.IPEMODIINDVPERFTRANT.PEMODIINDVPERFTRANEV perfTrans=
				new Ejecutar.IPEMODIINDVPERFTRANT.PEMODIINDVPERFTRANEV();
		
		super.initialize(contexto);
				
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_PE_MODIF_INDV_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		
		perfTrans = clienteWrapper.wrappBeanModifClientePerfTrans(persona);
		super.initialize(perfTrans);
		
		perfTrans.getPEPERSRLFPUBLCP().setCODNRBEEN(super.getEntidad());
		perfTrans.getPEPERSRLFPUBLCP().setIDINTERNOPE(persona.getIdInterna());
		
		perfTrans.getPEPERFILTRANSCLE().setCODNRBEEN(super.getEntidad());
		perfTrans.getPEPERFILTRANSCLE().setIDINTERNOPE(persona.getIdInterna());
		
		perfTrans.getPEPERSRLINFFINT().setCODNRBEEN(super.getEntidad());
		perfTrans.getPEPERSRLINFFINT().setIDINTERNOPE(persona.getIdInterna());
		
		if(persona.getApeMaterno() != null
				&& !persona.getApeMaterno().equals("")){
			perfTrans.getPEINDSEGAPV().setSTDCHAR01("N");
		}else{
			perfTrans.getPEINDSEGAPV().setSTDCHAR01("S");
		}
		
		contexto.setPEMODIINDVPERFTRANEV(perfTrans);
		contexto.setSTDTRNIPARMV(contextoEntrada);
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.IPEMODIINDVPERFTRANT contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ModificacionPerfilTransaccionalServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de modificacion de perfil"
					+ "transaccional de clientes.", e);
		}
		return respuesta;
	}
	
	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con los datos
	 * de la persona física.
	 * 
	 * @param response Respuesta Bansefi con datos de la persona fisica
	 * @return <code>true</code> si la respuesta Bansefi contiene elemento no nulos
	 */
	private Boolean verificaRespuestaCliente(ResponseBansefi response){
		Boolean noNulo = false;
		if(response!= null && response.getOPEMODIINDVPERFTRANT() != null){
			noNulo = true;
		}
		return noNulo;
	}
		
}
