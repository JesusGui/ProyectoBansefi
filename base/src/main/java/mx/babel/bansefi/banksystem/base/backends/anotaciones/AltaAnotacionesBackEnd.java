package mx.babel.bansefi.banksystem.base.backends.anotaciones;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.AnotacionBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.base.webservices.altaanotaciones.AltaAnotacionesServicio;
import mx.babel.bansefi.banksystem.base.webservices.altaanotaciones.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.altaanotaciones.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.altaanotaciones.ResponseBansefi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para el alta de una anotacion
 * 
 * @author javier.martinnino
 *
 */
@Component
public class AltaAnotacionesBackEnd extends BackEndBean{

	private static final long serialVersionUID = 7098072688190208441L;
	private static final int FECHA_FIN = 99991231;
	
	private static final String DIRIGIDO_PERSONA = "1";
	private static final String DIRIGIDO_PERSONAS = "2";
	private static final String DIRIGIDO_CUENTA = "3";
	private static final String DIRIGIDO_CUENTAS = "4";
	
	private static final Integer EVENT_CD_CLIENTE = 3;
	private static final Integer EVENT_CD_CUENTA = 1;
		
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	/**
	 * Función encargada de el alta de una anotacion
	 * por medio de servicios web.
	 * 
	 * @param anotacion anotacion a consultar
	 * @param esRespuesta indicador de si es una respuesta o una anotacion a dar de alta
	 * @return El numero de la anotacion creada de la operacion
	 */
	public Long ejecutarTRN(AnotacionBean anotacion, boolean esRespuesta){
		Ejecutar.ITRANALTADATOSDPTRN contexto = initPeticion(anotacion,esRespuesta);
		EjecutarResult respuesta = ejecutarWS(contexto);
		super.verificaRespuesta(respuesta);
		if(verificaResponseBansefi(respuesta)){
			return altaAnotacion(respuesta.getResponseBansefi());
		}
		return null;
	}
	
	/**
	 * Función que construye el detalle de la anotacion a partir de la respuesta al servidor.
	 * 
	 * @param response
	 * @return
	 */
	private Long altaAnotacion(ResponseBansefi response){
		Long resultado = null;		
		if(verificaRespuestaCliente(response)){
			resultado= response.getOTRANALTADATOSDPTRN().getTRANALTADATOSDPEVTZ().getANNUMANTCNV().getNUMEROANTCN();
		}
		return resultado;
	}
	
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param anotacion anotacion a consultar
	 * @param esRespuesta indicador de si es una respuesta en vez de un alta de anotacion
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITRANALTADATOSDPTRN initPeticion(AnotacionBean anotacion, boolean esRespuesta){
		Ejecutar.ITRANALTADATOSDPTRN contexto = new Ejecutar.ITRANALTADATOSDPTRN();
		
		Ejecutar.ITRANALTADATOSDPTRN.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRANALTADATOSDPTRN.STDTRNIPARMV();
		
		Ejecutar.ITRANALTADATOSDPTRN.TRANALTADATOSDPEVTY cuerpoContexto =
				new Ejecutar.ITRANALTADATOSDPTRN.TRANALTADATOSDPEVTY();
		
		Ejecutar.ITRANALTADATOSDPTRN.TRANALTADATOSDPEVTY.ANANTCNE anot =
				new Ejecutar.ITRANALTADATOSDPTRN.TRANALTADATOSDPEVTY.ANANTCNE();
		
		Ejecutar.ITRANALTADATOSDPTRN.TRANALTADATOSDPEVTY.ANEMPLDESTINOV datosEmpleado =
				new Ejecutar.ITRANALTADATOSDPTRN.TRANALTADATOSDPEVTY.ANEMPLDESTINOV();
		
		Ejecutar.ITRANALTADATOSDPTRN.TRANALTADATOSDPEVTY.ANRLCLAVESV clave =
				new Ejecutar.ITRANALTADATOSDPTRN.TRANALTADATOSDPEVTY.ANRLCLAVESV();
		
		Ejecutar.ITRANALTADATOSDPTRN.TRANALTADATOSDPEVTY.ANANTCNRLANTCNP anotacionPadre =
				new Ejecutar.ITRANALTADATOSDPTRN.TRANALTADATOSDPEVTY.ANANTCNRLANTCNP();
		
		IntegerToDateConverter converter = new IntegerToDateConverter();
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_AN_ALTA_DATOS_DP_TRN);
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
		anot.setTXTBREVE(anotacion.getDescripcionCorta());
		anot.setTXTLARGA(anotacion.getDescripcionLarga());
		if (anotacion.getDescripcionLarga() != null){
			anot.setTXTLARGALEN(anotacion.getDescripcionLarga().length());
		}
		anot.setCODGRPANTCN(anotacion.getArea());
				
		if (anotacion.getFechaCierre() != null){
			anot.setFECHACRRE(converter.convertFrom(anotacion.getFechaCierre(), null));
		}else{
			anot.setFECHACRRE(AltaAnotacionesBackEnd.FECHA_FIN);
		}
		anot.setINDPRDAD(anotacion.getPrioridad());
		anot.setIDINTERNOEMPLEP(super.getUsuarioId());
		
		anot.setINDRESPANTCN("N");
		anot.setINDCONFIDENCIAL("N");
		anot.setINDCONTESTADA("N");
		
		// Si es respuesta de anotacion ponemos el indicador de respuesta e incluimos la anotacion
		// padre		
		if (esRespuesta){
			anot.setINDRESPANTCN("S");
			anotacionPadre.setCODNRBEEN(super.getEntidad());
			anotacionPadre.setCODANTCN(anotacion.getTipo());
			anotacionPadre.setSUBCDANTCN(anotacion.getSubcodigo());
			// Incluimos la logica de front para hacer viajar el valor correcto esperado en back 
			// quitando el primer elemento de la cadena que viaja
			if (anotacion.getSubcodigo() !=null && anotacion.getSubcodigo().length()>1){
				anotacionPadre.setSUBCDANTCN(anotacion.getSubcodigo().substring(1));
			}
			anotacionPadre.setNUMEROANTCN(anotacion.getNumero());
			cuerpoContexto.setANANTCNRLANTCNP(anotacionPadre);
		}
		
		datosEmpleado.setIDINTERNOEMPLEP(super.getUsuarioId());
		datosEmpleado.setNOMB50(super.getNombreUsuario());
		datosEmpleado.setINDREVISADA("N");
		datosEmpleado.setINDCONF("A");
		
		try{
			if (anotacion.getDirigidoA().equals(AltaAnotacionesBackEnd.DIRIGIDO_CUENTA) ||
					anotacion.getDirigidoA().equals(AltaAnotacionesBackEnd.DIRIGIDO_CUENTAS)){
				contexto.setEVENTCD(EVENT_CD_CUENTA);
				clave.setNUMSECAC(Long.valueOf(anotacion.getIdentificadores().get(0)));
			}else if (anotacion.getDirigidoA().equals(AltaAnotacionesBackEnd.DIRIGIDO_PERSONA) ||
					anotacion.getDirigidoA().equals(AltaAnotacionesBackEnd.DIRIGIDO_PERSONAS)){
				contexto.setEVENTCD(EVENT_CD_CLIENTE);
				clave.setIDINTERNOPE(Integer.valueOf(anotacion.getIdentificadores().get(0)));
			}
		}catch (NumberFormatException nfe){
			throw new ControlableException("El identificador debe ser de tipo numerico",nfe);
		}catch (IndexOutOfBoundsException iob){
			throw new ControlableException("Debe informar al menos un identificador para cada una de las anotaciones a "
					+ "dar de alta en el sistema",iob);
		}		
				
		cuerpoContexto.setANANTCNE(anot);
		cuerpoContexto.setANEMPLDESTINOV(datosEmpleado);
		cuerpoContexto.setANRLCLAVESV(clave);
		
		contexto.setTRANALTADATOSDPEVTY(cuerpoContexto);		
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
	private EjecutarResult ejecutarWS(Ejecutar.ITRANALTADATOSDPTRN contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(AltaAnotacionesServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de alta de "
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
		if(response!= null && response.getOTRANALTADATOSDPTRN() != null &&
				response.getOTRANALTADATOSDPTRN().getTRANALTADATOSDPEVTZ() != null &&
						response.getOTRANALTADATOSDPTRN().getTRANALTADATOSDPEVTZ().getANNUMANTCNV() != null){
			noNulo = true;
		}
		return noNulo;
	}
		
}
