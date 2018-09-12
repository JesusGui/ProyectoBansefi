package mx.babel.bansefi.banksystem.base.backends.anotaciones;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.AnotacionBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.webservices.altarelacionanotaciones.AltaRelacionAnotacionesServicio;
import mx.babel.bansefi.banksystem.base.webservices.altarelacionanotaciones.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.altarelacionanotaciones.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.altarelacionanotaciones.ResponseBansefi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para el alta de una anotacion
 * 
 * @author javier.martinnino
 *
 */
@Component
public class AltaRelacionAnotacionesBackEnd extends BackEndBean{

	private static final long serialVersionUID = 7098072688190208441L;
	
	private static final String DIRIGIDO_PERSONA = "1";
	private static final String DIRIGIDO_PERSONAS = "2";
	private static final String DIRIGIDO_CUENTA = "3";
	private static final String DIRIGIDO_CUENTAS = "4";
	
	private static final Integer EVENT_CD_CLIENTE = 3;
	private static final Integer EVENT_CD_CUENTA = 1;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	/**
	 * Función encargada de compartir cuentas/personas a una anotacion.
	 * 
	 * @param anotacion anotacion a consultar
	 * @param pos posicion del elemento a compartir en la lista de identificadores
	 * @return resultado de la operacion
	 */
	public Integer ejecutarTRN(AnotacionBean anotacion, int pos){
		Ejecutar.ITRALTAANCOMPPANTTRN contexto = initPeticion(anotacion, pos);
		EjecutarResult respuesta = ejecutarWS(contexto);
		super.verificaRespuesta(respuesta);
		if(verificaResponseBansefi(respuesta)){
			return altaRelacionAnotacion(respuesta.getResponseBansefi());
		}
		return null;
	}
	
	/**
	 * Función que devuelve el resultado de la operacion.
	 * 
	 * @param response
	 * @return
	 */
	private Integer altaRelacionAnotacion(ResponseBansefi response){
		Integer resultado = null;		
		if(verificaRespuestaCliente(response)){
			resultado= response.getOTRALTAANCOMPPANTTRN().getRTRNCD();
		}
		return resultado;
	}
	
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param anotacion anotacion a consultar
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITRALTAANCOMPPANTTRN initPeticion(AnotacionBean anotacion,int pos){
		Ejecutar.ITRALTAANCOMPPANTTRN contexto = new Ejecutar.ITRALTAANCOMPPANTTRN();
		
		Ejecutar.ITRALTAANCOMPPANTTRN.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRALTAANCOMPPANTTRN.STDTRNIPARMV();
		
		Ejecutar.ITRALTAANCOMPPANTTRN.TRALTAANCOMPPANTEVT cuerpoContexto =
				new Ejecutar.ITRALTAANCOMPPANTTRN.TRALTAANCOMPPANTEVT();
		
		Ejecutar.ITRALTAANCOMPPANTTRN.TRALTAANCOMPPANTEVT.ANANTCNP anotPadre =
				new Ejecutar.ITRALTAANCOMPPANTTRN.TRALTAANCOMPPANTEVT.ANANTCNP();
		
		Ejecutar.ITRALTAANCOMPPANTTRN.TRALTAANCOMPPANTEVT.ANRLCLAVESV relacionada =
				new Ejecutar.ITRALTAANCOMPPANTTRN.TRALTAANCOMPPANTEVT.ANRLCLAVESV();
						
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_ALTA_AN_COMP_PANT_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());		
		
		anotPadre.setCODNRBEEN(super.getEntidad());
		anotPadre.setCODANTCN(anotacion.getTipo());
		anotPadre.setSUBCDANTCN(anotacion.getSubcodigo());
		
		// Incluimos la logica de front para hacer viajar el valor correcto esperado en back 
		// quitando el primer elemento de la cadena que viaja
		if (anotacion.getSubcodigo() !=null && anotacion.getSubcodigo().length()>1){
			anotPadre.setSUBCDANTCN(anotacion.getSubcodigo().substring(1));
		}
		
		anotPadre.setNUMEROANTCN(anotacion.getNumero());
		
		if (anotacion.getDirigidoA().equals(AltaRelacionAnotacionesBackEnd.DIRIGIDO_CUENTA) ||
				anotacion.getDirigidoA().equals(AltaRelacionAnotacionesBackEnd.DIRIGIDO_CUENTAS)){
			contexto.setEVENTCD(EVENT_CD_CUENTA);
			relacionada.setNUMSECAC(Long.valueOf(anotacion.getIdentificadores().get(pos)));
		}else if (anotacion.getDirigidoA().equals(AltaRelacionAnotacionesBackEnd.DIRIGIDO_PERSONA) ||
				anotacion.getDirigidoA().equals(AltaRelacionAnotacionesBackEnd.DIRIGIDO_PERSONAS)){
			contexto.setEVENTCD(EVENT_CD_CLIENTE);
			relacionada.setIDINTERNOPE(Integer.valueOf(anotacion.getIdentificadores().get(pos)));
		}
		
		cuerpoContexto.setANANTCNP(anotPadre);		
		cuerpoContexto.setANRLCLAVESV(relacionada);
		
		contexto.setSTDTRNIPARMV(contextoEntrada);
		contexto.setTRALTAANCOMPPANTEVT(cuerpoContexto);
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRALTAANCOMPPANTTRN contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(AltaRelacionAnotacionesServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de alta de anotaciones.", e);
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
		if(response!= null && response.getOTRALTAANCOMPPANTTRN() != null &&
				response.getOTRALTAANCOMPPANTTRN().getANANTCNP() != null &&
						response.getOTRALTAANCOMPPANTTRN().getANNUMANTCNV() != null){
			noNulo = true;
		}
		return noNulo;
	}
		
}
