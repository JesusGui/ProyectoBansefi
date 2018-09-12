package mx.babel.bansefi.banksystem.base.backends;

import java.math.BigDecimal;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaFisicaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.webservices.altaperfiltransaccional.AltaPerfilTransaccionalPersonaServicio;
import mx.babel.bansefi.banksystem.base.webservices.altaperfiltransaccional.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.altaperfiltransaccional.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.altaperfiltransaccional.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.wrappers.ClienteWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para el alta del perfil transaccional de un cliente en especifico
 * 
 * @author javier.martinnino
 *
 */
@Component
public class AltaPerfilTransaccionalPersonaBackEnd extends BackEndBean{
	
	private static final long serialVersionUID = 5150523901889129912L;

	@Autowired
	ClienteWrapper clienteWrapper;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;

	
	/**
	 * Función encargada del alta del perfil transaccional de un cliente en especifico
	 * por medio de servicios web.
	 * 
	 * @param persona persona a dar de alta como cliente
	 * @return lista de cuentas asociadas a un cliente
	 * @throws NoControlableException Excepción controlada de errores en front end
	 * @throws ControlableException Excepción no controlada de errores en host
	 */
	public Integer ejecutarTRN(ClientePersonaFisicaBean persona)
		throws NoControlableException, ControlableException{
		Ejecutar.IPEALTAINDVPERFTRANT contexto = initPeticion(persona);
		EjecutarResult respuesta = ejecutarWS(contexto);
		super.verificaRespuesta(respuesta);
		if(verificaResponseBansefi(respuesta)){
			return altaPerfilTransaccional(respuesta.getResponseBansefi());
		}
		return null;
	}
	
	/**
	 * Función que construye la lista de cuentas a partir de la respuesta al servidor.
	 * 
	 * @param response
	 * @return
	 */
	private Integer altaPerfilTransaccional(ResponseBansefi response)
			throws NoControlableException, ControlableException{
		Integer resultado = null;		
		if(verificaRespuestaCliente(response)){
			return response.getOPEALTAINDVPERFTRANT().getRTRNCD();
		}
		return resultado;
	}
	
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param persona persona a dar de alta como cliente
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.IPEALTAINDVPERFTRANT initPeticion(ClientePersonaFisicaBean persona){
		Ejecutar.IPEALTAINDVPERFTRANT contexto = new Ejecutar.IPEALTAINDVPERFTRANT();
		Ejecutar.IPEALTAINDVPERFTRANT.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.IPEALTAINDVPERFTRANT.STDTRNIPARMV();
		Ejecutar.IPEALTAINDVPERFTRANT.PEALTAINDVPERFTRANEV perfTrans=
				new Ejecutar.IPEALTAINDVPERFTRANT.PEALTAINDVPERFTRANEV();
		
		super.initialize(contexto);
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_PE_ALTA_INDV_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		
		perfTrans = clienteWrapper.wrappBeanAltaClientePerfTrans(persona);
		super.initialize(perfTrans);
		
		perfTrans.getPEPERSRLFPUBLCP().setCODNRBEEN(super.getEntidad());
		perfTrans.getPEPERSRLFPUBLCP().setIDINTERNOPE(persona.getIdInterna());
		
		perfTrans.getPEPERFILTRANSCLE().setCODNRBEEN(super.getEntidad());
		perfTrans.getPEPERFILTRANSCLE().setIDINTERNOPE(persona.getIdInterna());
		
		perfTrans.getPEPERSRLINFFINT().setCODNRBEEN(super.getEntidad());
		perfTrans.getPEPERSRLINFFINT().setIDINTERNOPE(persona.getIdInterna());
		perfTrans.getPEPERSRLINFFINT().setIMPANUALFAC(new BigDecimal(0));
		perfTrans.getPEPERSRLINFFINT().setIMPEXPRTCN(new BigDecimal(0));
		perfTrans.getPEPERSRLINFFINT().setIMPACTIVO(new BigDecimal(0));
		perfTrans.getPEPERSRLINFFINT().setIMPPASIVO(new BigDecimal(0));
		perfTrans.getPEPERSRLINFFINT().setIMPCAPITAL(new BigDecimal(0));
		
		perfTrans.getPEINFFINANADICV().setIMPANUALFAC(new BigDecimal(0));
		perfTrans.getPEINFFINANADICV().setIMPEXPRTCN(new BigDecimal(0));
		perfTrans.getPEINFFINANADICV().setIMPACTIVO(new BigDecimal(0));
		perfTrans.getPEINFFINANADICV().setIMPPASIVO(new BigDecimal(0));
		perfTrans.getPEINFFINANADICV().setIMPCAPITAL(new BigDecimal(0));
		
		if(persona.getApeMaterno() != null
				&& !persona.getApeMaterno().equals("")){
			perfTrans.getPEINDSEGAPV().setSTDCHAR01("N");
		}else{
			perfTrans.getPEINDSEGAPV().setSTDCHAR01("S");
		}
		
		contexto.setPEALTAINDVPERFTRANEV(perfTrans);
		contexto.setSTDTRNIPARMV(contextoEntrada);
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.IPEALTAINDVPERFTRANT contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					AltaPerfilTransaccionalPersonaServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de alta de perfil"
					+ "transaccional de clientes.", e);
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
		if(response!= null && response.getOPEALTAINDVPERFTRANT() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
}
