package mx.babel.bansefi.banksystem.base.backends.anotaciones;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.AnotacionBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.webservices.consultaanotacionesrelacionadas.ConsultaAnotacionesRelacionadasServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultaanotacionesrelacionadas.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultaanotacionesrelacionadas.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultaanotacionesrelacionadas.ResponseBansefi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para la consulta de cuentas/personas relacionadas con anotaciones
 * 
 * @author javier.martinnino
 *
 */
@Component
public class ConsultaAnotacionesRelacionadasBackEnd extends BackEndBean{

	private static final long serialVersionUID = -6562030750912640629L;
	
	private static final String DIRIGIDO_PERSONA = "1";
	private static final String DIRIGIDO_PERSONAS = "2";
	private static final String DIRIGIDO_CUENTA = "3";
	private static final String DIRIGIDO_CUENTAS = "4";
	
	private static final Integer EVENT_CD_CLIENTE = 3;
	private static final Integer EVENT_CD_CUENTA = 1;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Función encargada de consultar las cuentas/personas relacionadas con anotaciones
	 * 
	 * @param anotacion anotacion a consultar
	 * @return AnotacionBean la anotacion completa con los datos consultados
	 */
	public AnotacionBean ejecutarTRN(AnotacionBean anotacion){
		Ejecutar.ITRCONSULANACPANTTRN contexto = initPeticion(anotacion);
		EjecutarResult respuesta = ejecutarWS(contexto);
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return anotacion;
			}
		}
		if(verificaResponseBansefi(respuesta)){
			return getAnotacion(respuesta.getResponseBansefi(),anotacion);
		}
		return null;
	}
	
	/**
	 * Función que construye la lista de cuentas/personas relacionadas con anotaciones.
	 * 
	 * @param response del servidor
	 * @param anotacion la anotacion consultada para saber el tipo
	 * @return AnotacionBean la anotacion con los datos completos
	 */
	private AnotacionBean getAnotacion(ResponseBansefi response,AnotacionBean anotacion){
		List<String> resultado = new ArrayList<String>();		
		if(verificaRespuestaCliente(response)){
			if (anotacion.getIdentificadores() == null){
				anotacion.setIdentificadores(new ArrayList<String>());
			}
			for (int i=0;i< response.getOTRCONSULANACPANTTRN().getNUMBEROFRECORDS();i++){
				if (anotacion.getDirigidoA().equals(ConsultaAnotacionesRelacionadasBackEnd.DIRIGIDO_CUENTA) ||
						anotacion.getDirigidoA().equals(ConsultaAnotacionesRelacionadasBackEnd.DIRIGIDO_CUENTAS)){
					resultado.add(response.getOTRCONSULANACPANTTRN().getTRCONSULANACPANTEVT().getANANTCNRLACE().get(i).getNUMSECAC()
							+ ", "+response.getOTRCONSULANACPANTTRN().getTRCONSULANACPANTEVT().getANOTROSACV().get(i).getNOMBPDV().trim());
					if (!anotacion.getIdentificadores().contains(Long.toString(response.getOTRCONSULANACPANTTRN().getTRCONSULANACPANTEVT().getANANTCNRLACE().get(i).getNUMSECAC()))){
						anotacion.getIdentificadores().add(Long.toString(response.getOTRCONSULANACPANTTRN().getTRCONSULANACPANTEVT().getANANTCNRLACE().get(i).getNUMSECAC()));
					}
				}else if (anotacion.getDirigidoA().equals(ConsultaAnotacionesRelacionadasBackEnd.DIRIGIDO_PERSONA) ||
						anotacion.getDirigidoA().equals(ConsultaAnotacionesRelacionadasBackEnd.DIRIGIDO_PERSONAS)){
					resultado.add(response.getOTRCONSULANACPANTTRN().getTRCONSULANACPANTEVT().getANANTCNRLPERSE().get(i).getIDINTERNOPE()
							+ ", "+response.getOTRCONSULANACPANTTRN().getTRCONSULANACPANTEVT().getANOTROSPERSV().get(i).getNOMB50().trim());
					if (!anotacion.getIdentificadores().contains(Integer.toString(response.getOTRCONSULANACPANTTRN().getTRCONSULANACPANTEVT().getANANTCNRLPERSE().get(i).getIDINTERNOPE()))){
						anotacion.getIdentificadores().add(Integer.toString(response.getOTRCONSULANACPANTTRN().getTRCONSULANACPANTEVT().getANANTCNRLPERSE().get(i).getIDINTERNOPE()));
					}
				}				
			}
			anotacion.setRelacionados(resultado);
		}
		return anotacion;
	}
	
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param anotacion anotacion a consultar
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITRCONSULANACPANTTRN initPeticion(AnotacionBean anotacion){
		Ejecutar.ITRCONSULANACPANTTRN contexto = new Ejecutar.ITRCONSULANACPANTTRN();
		
		Ejecutar.ITRCONSULANACPANTTRN.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRCONSULANACPANTTRN.STDTRNIPARMV();
		
		Ejecutar.ITRCONSULANACPANTTRN.TRCONSULANACPANTEVT cuerpoContexto =
				new Ejecutar.ITRCONSULANACPANTTRN.TRCONSULANACPANTEVT();
		
		Ejecutar.ITRCONSULANACPANTTRN.TRCONSULANACPANTEVT.ANANTCNP anot =
				new Ejecutar.ITRCONSULANACPANTTRN.TRCONSULANACPANTEVT.ANANTCNP();		
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_CONSUL_AN_AC_PANT_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());		
		
		anot.setCODNRBEEN(super.getEntidad());
		anot.setCODANTCN(anotacion.getTipo());
		anot.setSUBCDANTCN(anotacion.getSubcodigo());
		// Incluimos la logica de front para hacer viajar el valor correcto esperado en back 
		// quitando el primer elemento de la cadena que viaja
		if (anotacion.getSubcodigo() !=null && anotacion.getSubcodigo().length()>1){
			anot.setSUBCDANTCN(anotacion.getSubcodigo().substring(1));
		}			
		
		anot.setNUMEROANTCN(anotacion.getNumero());
		
		cuerpoContexto.setANANTCNP(anot);
		contexto.setTRCONSULANACPANTEVT(cuerpoContexto);		
		contexto.setSTDTRNIPARMV(contextoEntrada);
		
		if (anotacion.getDirigidoA().equals(ConsultaAnotacionesRelacionadasBackEnd.DIRIGIDO_CUENTA) ||
				anotacion.getDirigidoA().equals(ConsultaAnotacionesRelacionadasBackEnd.DIRIGIDO_CUENTAS)){
			contexto.setEVENTCD(EVENT_CD_CUENTA);
		}else if (anotacion.getDirigidoA().equals(ConsultaAnotacionesRelacionadasBackEnd.DIRIGIDO_PERSONA) ||
				anotacion.getDirigidoA().equals(ConsultaAnotacionesRelacionadasBackEnd.DIRIGIDO_PERSONAS)){
			contexto.setEVENTCD(EVENT_CD_CLIENTE);
		}
		
		super.initialize(contexto);
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRCONSULANACPANTTRN contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaAnotacionesRelacionadasServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta de "
					+ "anotaciones relacionadas.", e);
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
		if(response!= null && response.getOTRCONSULANACPANTTRN() != null &&
				response.getOTRCONSULANACPANTTRN().getTRCONSULANACPANTEVT() != null){
			noNulo = true;
		}
		return noNulo;
	}
		
}
