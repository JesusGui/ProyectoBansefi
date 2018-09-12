package mx.babel.bansefi.banksystem.base.backends.anotaciones;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.AnotacionBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.webservices.consultaanotaciones.ConsultaAnotacionesServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultaanotaciones.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultaanotaciones.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultaanotaciones.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.wrappers.AnotacionWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para la consulta en detalle de una anotacion
 * 
 * @author javier.martinnino
 *
 */
@Component
public class ConsultaAnotacionBackEnd extends BackEndBean{

	private static final long serialVersionUID = 5735215188099920209L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;

	@Autowired
	private AnotacionWrapper anotacionWrapper;
	
	/**
	 * Función encargada de consultar el detalle de una anotacion
	 * por medio de servicios web.
	 * 
	 * @param anotacion anotacion a consultar
	 * @return bean de uso de cuenta
	 */
	public AnotacionBean ejecutarTRN(AnotacionBean anotacion){
		Ejecutar.ITRANCONSDATOSDPTRN contexto = initPeticion(anotacion);
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
			return getAnotacion(respuesta.getResponseBansefi());
		}
		return null;
	}
	
	/**
	 * Función que construye el detalle de la anotacion a partir de la respuesta al servidor.
	 * 
	 * @param response
	 * @return
	 */
	private AnotacionBean getAnotacion(ResponseBansefi response){
		AnotacionBean resultado = null;		
		if(verificaRespuestaCliente(response)){
			resultado= anotacionWrapper.wrappConsultaDetalleAnotacion(response.getOTRANCONSDATOSDPTRN().getTRANCONSDATOSDPEVTZ());
			// Incluimos la logica de front para montar el valor de catalogo y poder obtener el tipo correcto.
			if (resultado.getTipo()!=null && resultado.getSubcodigo()!=null){
				resultado.setSubcodigo(resultado.getTipo()+resultado.getSubcodigo());
			}
			
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
						
			// Tratamos la fecha de anotaciones para visualizarla a vacio si viene como 31/12/9999
			if (resultado.getFechaCierre() !=null && ConstantesFuncionales.MAX_FECHA_FIN.equals(df.format(resultado.getFechaCierre()))){
				resultado.setFechaCierre(null);
			}
			
		}
		return resultado;
	}
	
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param anotacion anotacion a consultar
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITRANCONSDATOSDPTRN initPeticion(AnotacionBean anotacion){
		Ejecutar.ITRANCONSDATOSDPTRN contexto = new Ejecutar.ITRANCONSDATOSDPTRN();
		
		Ejecutar.ITRANCONSDATOSDPTRN.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRANCONSDATOSDPTRN.STDTRNIPARMV();
		
		Ejecutar.ITRANCONSDATOSDPTRN.TRANCONSDATOSDPEVTY cuerpoContexto =
				new Ejecutar.ITRANCONSDATOSDPTRN.TRANCONSDATOSDPEVTY();
		
		Ejecutar.ITRANCONSDATOSDPTRN.TRANCONSDATOSDPEVTY.ANANTCNP anot =
				new Ejecutar.ITRANCONSDATOSDPTRN.TRANCONSDATOSDPEVTY.ANANTCNP();		
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_CONSUL_AN_PANT_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());		
		
		anot.setCODNRBEEN(super.getEntidad());
		anot.setCODANTCN(anotacion.getTipo());
		anot.setSUBCDANTCN(anotacion.getSubcodigo());
		// Incluimos la logica de front para hacer viajar el valor correcto esperado en back 
		// quitando el primer elemento de la cadena que viaja
		if (anotacion.getSubcodigo() !=null ){
			if(anotacion.getSubcodigo().length()>2){
				anot.setSUBCDANTCN(anotacion.getSubcodigo().substring(1));
			} else {
				anot.setSUBCDANTCN(anotacion.getSubcodigo());
			}
			
		}
		anot.setNUMEROANTCN(anotacion.getNumero());
		
		cuerpoContexto.setANANTCNP(anot);
		contexto.setTRANCONSDATOSDPEVTY(cuerpoContexto);		
		contexto.setSTDTRNIPARMV(contextoEntrada);
		
		super.initialize(contexto);
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRANCONSDATOSDPTRN contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaAnotacionesServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta de "
					+ "anotaciones.", e);
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
		if(response!= null && response.getOTRANCONSDATOSDPTRN() != null &&
				response.getOTRANCONSDATOSDPTRN().getTRANCONSDATOSDPEVTZ() != null &&
						response.getOTRANCONSDATOSDPTRN().getTRANCONSDATOSDPEVTZ().getANANTCNE() != null){
			noNulo = true;
		}
		return noNulo;
	}
		
}
