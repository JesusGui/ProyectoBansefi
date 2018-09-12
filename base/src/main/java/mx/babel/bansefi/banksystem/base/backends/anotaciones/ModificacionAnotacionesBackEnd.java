package mx.babel.bansefi.banksystem.base.backends.anotaciones;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.AnotacionBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.base.webservices.modificacionanotaciones.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.modificacionanotaciones.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.modificacionanotaciones.ModificacionAnotacionesServicio;
import mx.babel.bansefi.banksystem.base.webservices.modificacionanotaciones.ResponseBansefi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para la modificacion de una anotacion
 * 
 * @author javier.martinnino
 *
 */
@Component
public class ModificacionAnotacionesBackEnd extends BackEndBean{
	
	private static final long serialVersionUID = 6262936610543987485L;
	private static final int FECHA_FIN = 99991231;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Función encargada de modificar una anotacion
	 * por medio de servicios web.
	 * 
	 * @param anotacion anotacion a consultar
	 * @return Integer resultado de la operacion
	 */
	public Integer ejecutarTRN(AnotacionBean anotacion){
		Ejecutar.ITRANMODIDATOSDPTRN contexto = initPeticion(anotacion);
		EjecutarResult respuesta = ejecutarWS(contexto);
		super.verificaRespuesta(respuesta);
		return modificaAnotacion(respuesta.getResponseBansefi());
	}
	
	/**
	 * Función que construye el detalle de la anotacion a partir de la respuesta al servidor.
	 * 
	 * @param response
	 * @return
	 */
	private Integer modificaAnotacion(ResponseBansefi response){
		Integer resultado = null;
		if(verificaRespuestaCliente(response)){
			resultado= response.getOTRANMODIDATOSDPTRN().getRTRNCD();
		}
		return resultado;
	}
	
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param anotacion anotacion a consultar
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITRANMODIDATOSDPTRN initPeticion(AnotacionBean anotacion){
		Ejecutar.ITRANMODIDATOSDPTRN contexto = new Ejecutar.ITRANMODIDATOSDPTRN();
		
		Ejecutar.ITRANMODIDATOSDPTRN.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRANMODIDATOSDPTRN.STDTRNIPARMV();
		
		Ejecutar.ITRANMODIDATOSDPTRN.TRANMODIDATOSDPEVTY cuerpoContexto =
				new Ejecutar.ITRANMODIDATOSDPTRN.TRANMODIDATOSDPEVTY();
		
		Ejecutar.ITRANMODIDATOSDPTRN.TRANMODIDATOSDPEVTY.ANANTCNP anot =
				new Ejecutar.ITRANMODIDATOSDPTRN.TRANMODIDATOSDPEVTY.ANANTCNP();
		
		Ejecutar.ITRANMODIDATOSDPTRN.TRANMODIDATOSDPEVTY.ANEMPLDESTINOV empleadoDestino =
				new Ejecutar.ITRANMODIDATOSDPTRN.TRANMODIDATOSDPEVTY.ANEMPLDESTINOV();
		
		IntegerToDateConverter converter = new IntegerToDateConverter();
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_AN_MODI_DATOS_DP_TRN);
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
		
		empleadoDestino.setIDINTERNOEMPLEP(super.getUsuarioId());
		empleadoDestino.setNOMB50(super.getNombreUsuario());
		empleadoDestino.setINDREVISADA("N");
		empleadoDestino.setINDCONF("A");
      
		cuerpoContexto.setANEMPLDESTINOV(empleadoDestino);
		cuerpoContexto.setANANTCNP(anot);
		cuerpoContexto.setINDPRDAD(anotacion.getPrioridad());
		cuerpoContexto.setTXTBREVE(anotacion.getDescripcionCorta());
		cuerpoContexto.setTXTLARGA(anotacion.getDescripcionLarga());
		
		if (anotacion.getFechaCierre() != null){
			cuerpoContexto.setFECHACRRE(converter.convertFrom(anotacion.getFechaCierre(), null));
		}else{
			cuerpoContexto.setFECHACRRE(ModificacionAnotacionesBackEnd.FECHA_FIN);
		}
		
		contexto.setTRANMODIDATOSDPEVTY(cuerpoContexto);		
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
	private EjecutarResult ejecutarWS(Ejecutar.ITRANMODIDATOSDPTRN contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ModificacionAnotacionesServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de modificacion de "
					+ "anotaciones.", e);
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
		if(response!= null && response.getOTRANMODIDATOSDPTRN() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
}
