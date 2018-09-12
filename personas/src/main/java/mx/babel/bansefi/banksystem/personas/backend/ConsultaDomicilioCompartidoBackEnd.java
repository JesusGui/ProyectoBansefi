package mx.babel.bansefi.banksystem.personas.backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioCompartidoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.personas.webservices.consultadomiciliocompartido.ConsultaDomicilioCompartidoServicio;
import mx.babel.bansefi.banksystem.personas.webservices.consultadomiciliocompartido.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.consultadomiciliocompartido.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.consultadomiciliocompartido.ResponseBansefi;
import mx.babel.bansefi.banksystem.personas.wrappers.ConsultaDomiciliosCompartidosWrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaDomicilioCompartidoBackEnd extends BackEndBean implements
		Serializable {

	private static final long serialVersionUID = 4784182990409133975L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	@Autowired
	ConsultaDomiciliosCompartidosWrapper consultaDomicilioCompartidoWrapper;

	private static final Logger LOGGER = LogManager
			.getLogger(ConsultaDomicilioCompartidoBackEnd.class);

	/**
	 * Función principal que descencadena todo el llamado de la TRN/WebService
	 * 
	 * @param contadores
	 *            Bean generico para la consulta de Contadores de Centro y
	 *            Puesto
	 * @return ContadoresCentroBean
	 */
	public List<DomicilioCompartidoBean> ejecutarTRN(int numeroDireccion){
		Ejecutar.ITRPECOMPLACNSTRNI contexto = initPeticion(numeroDireccion);
		EjecutarResult respuesta = ejecutarWS(contexto);
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return new ArrayList<DomicilioCompartidoBean>();
			}
		}
		
		if (verificaResponseBansefi(respuesta)) {
			return respuestaDomicilioCompartido(respuesta.getResponseBansefi());
		}
		return null;
	}

	/**
	 * Funcion que recibe la respuesta de la TRN y Wrappea a un objeto propio
	 * del sistema
	 * 
	 * @param response
	 * @return ContadoresCentroBean
	 */
	private List<DomicilioCompartidoBean> respuestaDomicilioCompartido(ResponseBansefi response){
		List<DomicilioCompartidoBean> consultaDomicilio = new ArrayList<DomicilioCompartidoBean>();		
		if (verificaRespuestaCliente(response)) {
			// Se recuperan y wrappean los datos de la cuenta
			for (int i = 0; i < response.getOTRPECOMPLACNSTRNO()
					.getNUMBEROFRECORDS(); i++) {
				DomicilioCompartidoBean domicilio = consultaDomicilioCompartidoWrapper
						.wrappRespuestaConsulta(response
								.getOTRPECOMPLACNSTRNO().getTRPECOMPLACNSEVTZ()
								.getTRPECOMPLACNSEVTV().get(i));
				consultaDomicilio.add(domicilio);
			}

		}

		return consultaDomicilio;
	}

	/**
	 * Función que inicializa los parametros de entrada que necesita el servicio
	 * web para ser ejecutado
	 * 
	 * @param contadoresCentroBean
	 * @return Ejecutar.ITROBTECONTTRNI
	 */
	private Ejecutar.ITRPECOMPLACNSTRNI initPeticion(int numeroDireccion) {
		Ejecutar.ITRPECOMPLACNSTRNI contexto = new Ejecutar.ITRPECOMPLACNSTRNI();
		Ejecutar.ITRPECOMPLACNSTRNI.STDTRNIPARMV contextoEntrada = new Ejecutar.ITRPECOMPLACNSTRNI.STDTRNIPARMV();
		Ejecutar.ITRPECOMPLACNSTRNI.TRPECOMPLACNSEVTY datosEntrada = new Ejecutar.ITRPECOMPLACNSTRNI.TRPECOMPLACNSEVTY();

		initialize(contexto);

		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_PE_COMP_LA_CNS_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(getTerminal());
		
		Ejecutar.ITRPECOMPLACNSTRNI.TRPECOMPLACNSEVTY.PECOMPV pecompv = new Ejecutar.ITRPECOMPLACNSTRNI.TRPECOMPLACNSEVTY.PECOMPV();
		pecompv.setCODNRBEEN(getEntidad());
		pecompv.setNUMDIR(numeroDireccion);

		datosEntrada.setPECOMPV(pecompv);

		contexto.setTRPECOMPLACNSEVTY(datosEntrada);
		contexto.setSTDTRNIPARMV(contextoEntrada);
		contexto.setSCROLLABLEOCCURS(50);
		initialize(contexto);

		return contexto;

	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto
	 *            Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRPECOMPLACNSTRNI contexto) {
		EjecutarResult respuesta = null;
		try {
			respuesta = (EjecutarResult) this.servicioWebUtils.ejecutarWS(
					ConsultaDomicilioCompartidoServicio.class, contexto);
		} catch (NoControlableException e) {
			LOGGER.error("error", e);
			throw new NoControlableException(
					"Error al invocar servicio web de consulta de "
							+ "domicilios compartidos.", e);
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
	 * datos de la consulta.
	 * 
	 * @param response
	 *            Respuesta Bansefi con datos de la consulta
	 * @return <code>true</code> si la respuesta Bansefi contiene elemento no
	 *         nulos
	 */
	private Boolean verificaRespuestaCliente(ResponseBansefi response) {
		Boolean noNulo = false;
		if (response != null
				&& response.getOTRPECOMPLACNSTRNO() != null
				&& response.getOTRPECOMPLACNSTRNO().getTRPECOMPLACNSEVTZ()
						.getTRPECOMPLACNSEVTV() != null) {
			noNulo = true;
		}
		return noNulo;
	}
	
}
