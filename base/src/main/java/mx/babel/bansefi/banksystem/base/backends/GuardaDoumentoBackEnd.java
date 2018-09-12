package mx.babel.bansefi.banksystem.base.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.webservices.guardadocumento.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.guardadocumento.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.guardadocumento.GuardaDocumentoServicio;
import mx.babel.bansefi.banksystem.base.webservices.guardadocumento.ResponseBansefi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GuardaDoumentoBackEnd extends BackEndBean{

	private static final long serialVersionUID = -7634099718545355777L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Función para almacenar un documento de autenticación.
	 * 
	 * @param documento nùmero del documento
	 * @param tipo tipo del documento
	 * 
	 * @return <code>true</code> si logra guardar el documento.
	 */
	public Boolean ejecutarTRN(String documento, String tipo){
		Ejecutar.ITRGENERAIDCTETRNI contexto = initPeticion(documento, tipo);
		
		EjecutarResult respuesta = getResponse(contexto);
		
		super.verificaRespuesta(respuesta);
		
		return verificaRespuestaTRN(respuesta.getResponseBansefi());
	}
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param numeroCuenta Número de la cuenta que poseé las retenciones
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITRGENERAIDCTETRNI initPeticion(String documento, String tipo)
			throws ControlableException, NoControlableException{
		Ejecutar.ITRGENERAIDCTETRNI contexto = new Ejecutar.ITRGENERAIDCTETRNI();
		Ejecutar.ITRGENERAIDCTETRNI.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRGENERAIDCTETRNI.STDTRNIPARMV();
		Ejecutar.ITRGENERAIDCTETRNI.TRGENERAIDCTEEVTY cuerpoContexto =
				new Ejecutar.ITRGENERAIDCTETRNI.TRGENERAIDCTEEVTY();
		Ejecutar.ITRGENERAIDCTETRNI.TRGENERAIDCTEEVTY.IDIFEV identificacion = 
				new Ejecutar.ITRGENERAIDCTETRNI.TRGENERAIDCTEEVTY.IDIFEV();
		
		identificacion.setCODIDEXTPERS(tipo);
		identificacion.setIDEXTPE(documento);
		
		cuerpoContexto.setIDIFEV(identificacion);
		
		contexto.setSTDTRNIPARMV(contextoEntrada);
		contexto.setTRGENERAIDCTEEVTY(cuerpoContexto);
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult getResponse(Ejecutar.ITRGENERAIDCTETRNI contexto){
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(GuardaDocumentoServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de guardado de "
					+ "documentos.", e);
		}
		return respuesta;
	}
	
	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con los datos
	 * de las retenciones.
	 * 
	 * @param response Respuesta Bansefi con datos de la persona moral
	 * @return <code>true</code> si la respuesta Bansefi contiene una lista de retenciones
	 */
	private Boolean verificaRespuestaTRN(ResponseBansefi response){
		Boolean noNulo = false;
		if(response != null && response.getOTRGENERAIDCTETRNO() != null && 
				response.getOTRGENERAIDCTETRNO().getTRGENERAIDCTEEVTZ() != null &&
				response.getOTRGENERAIDCTETRNO().getTRGENERAIDCTEEVTZ()
				.getIDIFEV() != null){
			noNulo = true;
		}
		return noNulo;
	}	
}
