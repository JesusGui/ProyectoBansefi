package mx.babel.bansefi.banksystem.base.backends.busquedas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.busqueda.PersonaGestorBusquedaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaFisicaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.TipoCliente;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.webservices.busquedaidexterna.BusquedaIdExternaServicio;
import mx.babel.bansefi.banksystem.base.webservices.busquedaidexterna.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.busquedaidexterna.Ejecutar.ITRPECBIDEXTERNACNS.TRPECBIDEXTERNACNSE.PECLCBDISCRV.STDAWONACCONSTPE2V;
import mx.babel.bansefi.banksystem.base.webservices.busquedaidexterna.Ejecutar.ITRPECBIDEXTERNACNS.TRPECBIDEXTERNACNSE.PECLCBDISCRV.STDAWONACCONSTPE3V;
import mx.babel.bansefi.banksystem.base.webservices.busquedaidexterna.Ejecutar.ITRPECBIDEXTERNACNS.TRPECBIDEXTERNACNSE.PECLCBDISCRV.STDFECALTADESDEPEV;
import mx.babel.bansefi.banksystem.base.webservices.busquedaidexterna.Ejecutar.ITRPECBIDEXTERNACNS.TRPECBIDEXTERNACNSE.PECLCBDISCRV.STDFECALTAHASTAPEV;
import mx.babel.bansefi.banksystem.base.webservices.busquedaidexterna.Ejecutar.ITRPECBIDEXTERNACNS.TRPECBIDEXTERNACNSE.PECLCBDISCRV.STDSELECCLTEPEV;
import mx.babel.bansefi.banksystem.base.webservices.busquedaidexterna.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.busquedaidexterna.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.webservices.busquedaidexterna.ResponseBansefi.OTRPECBIDEXTERNACNS.TRPECBIDEXTERNACNSE.TRPECBIDEXTERNACNSE1;
import mx.babel.bansefi.banksystem.base.wrappers.WrapperBusquedasUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para consultar una persona a traves de su id externa.
 * 
 * @author javier.martinnino
 * 
 */
@Component
public class BusquedaIdExternaBackEnd extends BackEndBean implements
		Serializable {

	private static final long serialVersionUID = -1163551718093391866L;

	private static final Logger LOGGER = LogManager
			.getLogger(BusquedaIdExternaBackEnd.class.getName());

	@Autowired
	private WrapperBusquedasUtils wrapperBeanService;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Función encargada de consultar una persona a traves de su id externa por
	 * medio de servicios web.
	 * 
	 * @param idExterno
	 *            identificacion de la persona a consultar
	 * @return cliente
	 * @throws NoControlableException
	 *             Excepción controlada de errores en front end
	 * @throws ControlableException
	 *             Excepción no controlada de errores en host
	 */
	public List<ClientePersonaFisicaBean> ejecutarTRN(String idExterno){
		List<ClientePersonaFisicaBean> clientes = new ArrayList<ClientePersonaFisicaBean>();
		Ejecutar.ITRPECBIDEXTERNACNS contexto = initPeticion(idExterno);
		EjecutarResult respuesta = ejecutarWS(contexto);
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if(ce.getRtncod()==RETURN_COD_SIN_DATOS){
				return new ArrayList<>();
			}else{
				throw ce;
			}
		}
		
		if (verificaResponseBansefi(respuesta)) {
			clientes.addAll(getPersona(respuesta.getResponseBansefi()));
		}
		return clientes;
	}

	/**
	 * Función encargada de consultar una persona a traves de su id externa por
	 * medio de servicios web.
	 * 
	 * @param beanBusqueda
	 *            Bean de la persona a consultar.
	 * @return Lista de resultados.
	 * @throws NoControlableException
	 *             Excepción controlada de errores en front end
	 * @throws ControlableException
	 *             Excepción no controlada de errores en host
	 */
	public List<Object> ejecutarTRN(Object beanBusqueda)
			throws NoControlableException, ControlableException {
		PersonaGestorBusquedaBean personaBusqueda = (PersonaGestorBusquedaBean) beanBusqueda;
		Ejecutar.ITRPECBIDEXTERNACNS contexto = initPeticion(personaBusqueda
				.getNoIdentificador());
		EjecutarResult respuesta = ejecutarWS(contexto);
		if (verificaResponseBansefi(respuesta)) {
			return getResultadoBusqueda(respuesta.getResponseBansefi(),
					personaBusqueda);
		}
		return null;
	}

	/**
	 * Función que construye la persona encontrada a partir del id externa.
	 * 
	 * @param response
	 * @return
	 */
	private List<ClientePersonaFisicaBean> getPersona(ResponseBansefi response)
			throws NoControlableException, ControlableException {
		List<ClientePersonaFisicaBean> clientes = new ArrayList<ClientePersonaFisicaBean>();
		if (verificaRespuestaCliente(response)) {
			List<TRPECBIDEXTERNACNSE1> personasEncontradas = response
					.getOTRPECBIDEXTERNACNS().getTRPECBIDEXTERNACNSE()
					.getTRPECBIDEXTERNACNSE();

			for (int i = 0; i < response.getOTRPECBIDEXTERNACNS().getNUMBEROFRECORDS(); i++) {
				TRPECBIDEXTERNACNSE1 trpecbidexternacnse1 = personasEncontradas.get(i);
				ClientePersonaFisicaBean resultado = new ClientePersonaFisicaBean();
				resultado = new ClientePersonaFisicaBean();
				resultado.setIdInterna(trpecbidexternacnse1.getIDINTERNOPE());
				resultado.setNombre(trpecbidexternacnse1.getNOMB50().trim());
				resultado.setNumIdentificacion(trpecbidexternacnse1.getIDEXTPE().trim());
				if ("F".equals(trpecbidexternacnse1.getCODPE().trim())){
					resultado.setTipoCliente(TipoCliente.PERSONA_FISICA.getTipo());
				}else if ("J".equals(trpecbidexternacnse1.getCODPE().trim())){
					resultado.setTipoCliente(TipoCliente.PERSONA_MORAL.getTipo());
				}
				clientes.add(resultado);
			}
		}
		return clientes;
	}

	/**
	 * Función que construye los resultados para la consulta de idExterna desde
	 * las busquedas.
	 * 
	 * @param response
	 * @param personaBusqueda
	 * @return
	 */
	private List<Object> getResultadoBusqueda(ResponseBansefi response,
			PersonaGestorBusquedaBean personaBusqueda)
			throws NoControlableException, ControlableException {
		List<Object> resultado = null;		
		if (verificaRespuestaCliente(response)) {
			personaBusqueda.setMasDatos(response.getOTRPECBIDEXTERNACNS()
					.getMOREDATAIN() == 1);
			try {
				resultado = wrapperBeanService
						.wrappResultadosIdExternaGestores(
								response.getOTRPECBIDEXTERNACNS()
										.getTRPECBIDEXTERNACNSE()).getDatos();
			} catch (MappingException e) {
				LOGGER.debug("Error mapeando objetos para el tipo Resultado");
			}

		}
		return resultado;
	}

	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio
	 * web.
	 * 
	 * @param idExterno
	 *            Identificacion del cliente.
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITRPECBIDEXTERNACNS initPeticion(String idExterno) {
		Ejecutar.ITRPECBIDEXTERNACNS contexto = new Ejecutar.ITRPECBIDEXTERNACNS();
		Ejecutar.ITRPECBIDEXTERNACNS.STDTRNIPARMV contextoEntrada = new Ejecutar.ITRPECBIDEXTERNACNS.STDTRNIPARMV();
		Ejecutar.ITRPECBIDEXTERNACNS.TRPECBIDEXTERNACNSE persona = new Ejecutar.ITRPECBIDEXTERNACNS.TRPECBIDEXTERNACNSE();
		Ejecutar.ITRPECBIDEXTERNACNS.TRPECBIDEXTERNACNSE.PEIDEXTCBIDEXTV datosPersonales = new Ejecutar.ITRPECBIDEXTERNACNS.TRPECBIDEXTERNACNSE.PEIDEXTCBIDEXTV();

		Ejecutar.ITRPECBIDEXTERNACNS.TRPECBIDEXTERNACNSE.PECLCBDISCRV otrosDatos = new Ejecutar.ITRPECBIDEXTERNACNS.TRPECBIDEXTERNACNSE.PECLCBDISCRV();

		contextoEntrada
				.setCODTX(CodTxConstants.COD_TX_TR_PE_CB_ID_EXTERNA_CNS_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());

		datosPersonales.setCODNRBEEN(super.getEntidad());
		if (idExterno != null) {
			datosPersonales.setIDEXTPE(idExterno);
		}
		datosPersonales.setCODIDEXTPERS("");

		otrosDatos.setCODOFCNAALTA("");
		otrosDatos.setCODPOSTALAG("");
		otrosDatos.setCODARGEO("");
		otrosDatos.setNUMARGEO(0);

		STDFECALTADESDEPEV fecDesde = new STDFECALTADESDEPEV();
		fecDesde.setSTDFECHA(0);
		otrosDatos.setSTDFECALTADESDEPEV(fecDesde);

		STDFECALTAHASTAPEV fecHasta = new STDFECALTAHASTAPEV();
		fecHasta.setSTDFECHA(0);
		otrosDatos.setSTDFECALTAHASTAPEV(fecHasta);

		STDAWONACCONSTPE2V valor1 = new STDAWONACCONSTPE2V();
		valor1.setSTDFECHA(0);
		otrosDatos.setSTDAWONACCONSTPE2V(valor1);

		STDAWONACCONSTPE3V valor2 = new STDAWONACCONSTPE3V();
		valor2.setSTDFECHA(0);
		otrosDatos.setSTDAWONACCONSTPE3V(valor2);

		STDSELECCLTEPEV valor3 = new STDSELECCLTEPEV();
		valor3.setSTDCHAR01("");
		otrosDatos.setSTDSELECCLTEPEV(valor3);

		contexto.setELEVATORPOSITION(1);
		contexto.setSCROLLABLEOCCURS(50);

		contexto.setTRPECBIDEXTERNACNSE(persona);
		contexto.getTRPECBIDEXTERNACNSE().setPECLCBDISCRV(otrosDatos);
		contexto.getTRPECBIDEXTERNACNSE().setPEIDEXTCBIDEXTV(datosPersonales);
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
	private EjecutarResult ejecutarWS(Ejecutar.ITRPECBIDEXTERNACNS contexto) {
		EjecutarResult respuesta = null;
		try {
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					BusquedaIdExternaServicio.class, contexto);
		} catch (NoControlableException e) {
			throw new NoControlableException(
					"Error al invocar servicio web de consulta de "
							+ "persona a traves de id externa.", e);
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
				&& response.getOTRPECBIDEXTERNACNS() != null
				&& response.getOTRPECBIDEXTERNACNS().getTRPECBIDEXTERNACNSE() != null
				&& response.getOTRPECBIDEXTERNACNS().getTRPECBIDEXTERNACNSE()
						.getTRPECBIDEXTERNACNSE() != null) {
			noNulo = true;
		}
		return noNulo;
	}	
}
