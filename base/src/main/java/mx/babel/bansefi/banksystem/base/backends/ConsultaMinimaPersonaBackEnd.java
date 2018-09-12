package mx.babel.bansefi.banksystem.base.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.ConsultaMinimaPersonaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.webservices.validaidinterna.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.validaidinterna.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.validaidinterna.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.webservices.validaidinterna.ValidaIdInternaServicio;
import mx.babel.bansefi.banksystem.base.wrappers.ConsultaMinimaPersonaWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backen que consulta los datos minimos de una persona.
 * Para este Backend no se hizo un Servicio específico, sino se reutilizó
 * el servicio ValidaIdInternaServicio, ya que utilizan la misma TRN
 * TR_CONS_MINIMA_PERSONA_TRN. Pero este backend obtiene mas datos
 * @author manuel.nieto
 *
 */
@Component
public class ConsultaMinimaPersonaBackEnd extends BackEndBean {

	private static final long serialVersionUID = 8627765410469563715L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	@Autowired
	private ConsultaMinimaPersonaWrapper consultaMinimaWrapper;

	/**
	 * Función encargada de validar la id interna a un cliente en especifico por
	 * medio de servicios web.
	 * 
	 * @param idInterna
	 *            idInterna del cliente consultar
	 * @return ClientePersonaFisicaBean datos recuperados del cliente existente
	 * @throws NoControlableException
	 *             Excepción controlada de errores en front end
	 * @throws ControlableException
	 *             Excepción no controlada de errores en host
	 */
	public ConsultaMinimaPersonaBean ejecutarTRN(Integer idInterna){
		Ejecutar.ITRCONSMINIMAPERSONAT contexto = initPeticion(idInterna);
		EjecutarResult respuesta = ejecutarWS(contexto);
		super.verificaRespuesta(respuesta);
		if (verificaResponseBansefi(respuesta)) {
			return getDatosCliente(respuesta.getResponseBansefi());
		}
		return null;
	}

	/**
	 * Función que construye la respuesta a partir de los datos del servidor.
	 * 
	 * @param response
	 * @return
	 */
	private ConsultaMinimaPersonaBean getDatosCliente(ResponseBansefi response){
		ConsultaMinimaPersonaBean persona = null;		
		if (verificaRespuestaCliente(response)) {
			persona = consultaMinimaWrapper.wrappConsultaMinimaPersona(response
					.getOTRCONSMINIMAPERSONAT().getTRCONSMINIMAPERSONAEV());
		}
		return persona;
	}

	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio
	 * web.
	 * 
	 * @param idInterna
	 *            idInterna del cliente a consultar
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITRCONSMINIMAPERSONAT initPeticion(Integer idInterna) {

		Ejecutar.ITRCONSMINIMAPERSONAT contexto = new Ejecutar.ITRCONSMINIMAPERSONAT();

		Ejecutar.ITRCONSMINIMAPERSONAT.STDTRNIPARMV contextoEntrada = new Ejecutar.ITRCONSMINIMAPERSONAT.STDTRNIPARMV();

		Ejecutar.ITRCONSMINIMAPERSONAT.TRCONSMINIMAPERSONAEV datosEntrada = new Ejecutar.ITRCONSMINIMAPERSONAT.TRCONSMINIMAPERSONAEV();

		Ejecutar.ITRCONSMINIMAPERSONAT.TRCONSMINIMAPERSONAEV.PEPERSP datosPersona = new Ejecutar.ITRCONSMINIMAPERSONAT.TRCONSMINIMAPERSONAEV.PEPERSP();

		super.initialize(contexto);

		contextoEntrada
				.setCODTX(CodTxConstants.COD_TX_TR_CONS_MINIMA_PERSONA_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());

		datosPersona.setCODNRBEEN(super.getEntidad());
		datosPersona.setIDINTERNOPE(idInterna);

		datosEntrada.setPEPERSP(datosPersona);

		contexto.setTRCONSMINIMAPERSONAEV(datosEntrada);
		contexto.setSTDTRNIPARMV(contextoEntrada);

		return contexto;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto
	 *            Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRCONSMINIMAPERSONAT contexto) {
		EjecutarResult respuesta = null;
		try {
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ValidaIdInternaServicio.class, contexto);
		} catch (NoControlableException e) {
			throw new NoControlableException(
					"Error al invocar servicio web que realiza "
							+ "la consulta minimoa de un cliente por su id interna.",
					e);
		}
		return respuesta;
	}

	/**
	 * Función que valida que la respuesta del servidor no este vacía.
	 * 
	 * @param respuesta
	 *            Objeto respuesta del servicio web
	 * @return <code>true</code> si la respuesta no esta vacía.
	 */
	private Boolean verificaResponseBansefi(EjecutarResult respuesta) {
		Boolean noNulo = false;
		if (respuesta != null && respuesta.getResponseBansefi() != null) {
			noNulo = true;
		}
		return noNulo;
	}

	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con los
	 * datos de la persona física.
	 * 
	 * @param response
	 *            Respuesta Bansefi con datos de la persona moral
	 * @return <code>true</code> si la respuesta Bansefi contiene elemento no
	 *         nulos
	 */
	private Boolean verificaRespuestaCliente(ResponseBansefi response) {
		Boolean noNulo = false;
		if (response != null
				&& response.getOTRCONSMINIMAPERSONAT() != null
				&& response.getOTRCONSMINIMAPERSONAT()
						.getTRCONSMINIMAPERSONAEV() != null) {
			noNulo = true;
		}
		return noNulo;
	}	
}
