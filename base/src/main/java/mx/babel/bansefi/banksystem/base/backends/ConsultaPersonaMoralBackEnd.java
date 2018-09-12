package mx.babel.bansefi.banksystem.base.backends;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import mx.babel.arq.catalogo.beans.CatalogoGeoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.consultadomicilio.ConsultaDomicilioBackend;
import mx.babel.bansefi.banksystem.base.backends.consultalocalidad.ConsultaLocalidadBackend;
import mx.babel.bansefi.banksystem.base.beans.DocumentoPersonaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaFisicaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaMoralBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ConstitucionPersonaMoralBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioTipoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.TipoCliente;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.utils.CatalogoGeoUtils;
import mx.babel.bansefi.banksystem.base.utils.CedulaConocimientoUtils;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.base.utils.StringToDateConverter;
import mx.babel.bansefi.banksystem.base.webservices.consultapersonamoral.ConsultaPersonaMoralServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultapersonamoral.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultapersonamoral.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultapersonamoral.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.webservices.consultapersonamoral.ResponseBansefi.OTRPECONSORGTRNO.TRPECONSORGEVTZ.PECONSORGOBJTRDV;
import mx.babel.bansefi.banksystem.base.webservices.consultapersonamoral.ResponseBansefi.OTRPECONSORGTRNO.TRPECONSORGEVTZ.PECONSORGOBJTRDV.DRCOMPRGSTROV;
import mx.babel.bansefi.banksystem.base.wrappers.ClienteWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * BackEnd para consultas de personas morales.
 * 
 * @author mario.montesdeoca
 * 
 */
@Component
public class ConsultaPersonaMoralBackEnd extends BackEndBean {

	private static final long serialVersionUID = -3354675248588179157L;

	private static final String FECHA_INICIO_MIN = "11/11/1111";

	@Autowired
	ServicioWebUtils servicioWebUtils;

	@Autowired
	CatalogoGeoUtils catalogoGeoUtils;

	@Autowired
	ClienteWrapper clienteWrapper;

	@Autowired
	ConsultaDomicilioBackend consultaDomicilio;

	@Autowired
	ConsultaLocalidadBackend consultaLocalidad;

	@Autowired
	ConsultaListaDocumentosBackend consultaListaDocumentos;

	@Autowired
	ConsultaRecursosCuentaMoralBackEnd consultaRecursosCuentaBackEnd;

	@Autowired
	CedulaConocimientoUtils cedulaConocimientoUtils;

	@Autowired
	ConsultaRepresentanteLegalBackEnd clienteConsultaRepresentanteLegal;

	@Autowired
	ConsultaPersonaFisicaBackEnd clienteConsultaPersonaFisica;

	/**
	 * Función para obtener los datos de un cliente de tipo persona moral
	 * invocando un servicio web.
	 * 
	 * @param idInterno
	 *            Id interno del cliente de tipo persona moral.
	 * @return Objeto con atributos del cliente consultado.
	 */
	public ClientePersonaMoralBean ejecutarTRN(Integer idInterno) {
		Ejecutar.ITRPECONSORGTRNI contexto = initPeticion(idInterno);

		EjecutarResult respuesta = ejecutarWS(contexto);

		try {
			super.verificaRespuesta(respuesta);
		} catch (ControlableException ce) {
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS) {
				throw ce;
			} else {
				return null;
			}
		}

		return getCliente(idInterno, respuesta.getResponseBansefi());
	}

	/**
	 * Función encargada de obtener el cliente a partir de la respuesta del
	 * servicio web
	 * 
	 * @param idInterno
	 *            El id interno de la persona moral
	 * @param response
	 *            El objeto de reultado enviado por el servicio web.
	 * @return
	 */
	private ClientePersonaMoralBean getCliente(Integer idInterno,
			ResponseBansefi response) {
		ClientePersonaMoralBean clienteMoral = null;
		if (verificaRespuestaPersonaMoral(response)) {
			ResponseBansefi.OTRPECONSORGTRNO.TRPECONSORGEVTZ.PECONSORGOBJTRDV cliente = response
					.getOTRPECONSORGTRNO().getTRPECONSORGEVTZ()
					.getPECONSORGOBJTRDV();
			clienteMoral = construyeCliente(idInterno, cliente);
		}
		return clienteMoral;
	}

	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio
	 * web.
	 * 
	 * @param idInterno
	 *            Id del cliente de tipo persona moral
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITRPECONSORGTRNI initPeticion(Integer idInterno) {
		Ejecutar.ITRPECONSORGTRNI contexto = new Ejecutar.ITRPECONSORGTRNI();
		Ejecutar.ITRPECONSORGTRNI.STDTRNIPARMV contextoEntrada = new Ejecutar.ITRPECONSORGTRNI.STDTRNIPARMV();
		Ejecutar.ITRPECONSORGTRNI.TRPECONSORGEVTY cuerpoContexto = new Ejecutar.ITRPECONSORGTRNI.TRPECONSORGEVTY();
		Ejecutar.ITRPECONSORGTRNI.TRPECONSORGEVTY.PEPERSP persona = new Ejecutar.ITRPECONSORGTRNI.TRPECONSORGEVTY.PEPERSP();
		cuerpoContexto.setPEPERSP(persona);
		contexto.setSTDTRNIPARMV(contextoEntrada);
		contexto.setTRPECONSORGEVTY(cuerpoContexto);
		super.initialize(contexto);

		persona.setCODNRBEEN(super.getEntidad());
		if (idInterno != null) {
			persona.setIDINTERNOPE(idInterno);
		}

		contextoEntrada
				.setCODTX(CodTxConstants.COD_TX_PE_CONS_ORG_PERF_TRAN_TRN);
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());

		return contexto;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto
	 *            Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRPECONSORGTRNI contexto) {
		EjecutarResult respuesta = null;

		try {
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaPersonaMoralServicio.class, contexto);
		} catch (NoControlableException e) {
			throw new NoControlableException(
					"Error al invocar servicio web de consulta de "
							+ "persona moral.", e);
		}
		return respuesta;
	}

	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con los
	 * datos de la persona moral.
	 * 
	 * @param response
	 *            Respuesta Bansefi con datos de la persona moral
	 * @return <code>true</code> si la respuesta Bansefi contiene un cliente de
	 *         tipo persona moral
	 */
	private Boolean verificaRespuestaPersonaMoral(ResponseBansefi response) {
		Boolean noNulo = false;
		if (response != null
				&& response.getOTRPECONSORGTRNO() != null
				&& response.getOTRPECONSORGTRNO().getTRPECONSORGEVTZ() != null
				&& response.getOTRPECONSORGTRNO().getTRPECONSORGEVTZ()
						.getPECONSORGOBJTRDV() != null) {
			noNulo = true;
		}
		return noNulo;
	}

	/**
	 * Función para construir un bean de cliente persona moral a través de el
	 * objeto devuelto por el servicio web.
	 * 
	 * @param idInterno
	 *            El id interno del cliente de tipo persona moral
	 * @param cliente
	 *            El objeto creado por el servicio web con los detalles del
	 *            cliente.
	 * @return Objeto cliente persona moral
	 */
	private ClientePersonaMoralBean construyeCliente(
			Integer idInterno,
			ResponseBansefi.OTRPECONSORGTRNO.TRPECONSORGEVTZ.PECONSORGOBJTRDV cliente) {

		ClientePersonaMoralBean clienteMoral = clienteWrapper
				.wrappBean(cliente);

		clienteMoral = this.setearPersonaMoral(cliente, clienteMoral);

		clienteMoral.setFusionado(ConstantesFuncionales.CLIENTE_FUSIONADO
				.equals(cliente.getINDCLIENTE()));
		
		clienteMoral.setIndCliente(("S").equals(cliente.getINDCLIENTE()));

		DomicilioTipoBean domicilio = consultaDomicilio.ejectuarTRN(null,
				cliente.getNUMDIRPRAL());
		clienteMoral.getDomicilios().add(domicilio);
		clienteMoral.setRepresentante(new ClientePersonaFisicaBean());

		// Consulta de comprobante de domicilios
		List<DocumentoPersonaBean> documentosConsultados = this.consultaListaDocumentos
				.ejectuarTRN(clienteMoral.getIdInterna(), false);
		boolean encontrado = false;
		for (int i = 0; i < documentosConsultados.size() && !encontrado; i++) {
			if (ConstantesFuncionales.getCodigosComprobanteDomicilio()
					.contains(documentosConsultados.get(i).getTipo())) {
				clienteMoral
						.getDomicilios()
						.get(0)
						.setComprobanteDomicilio(
								documentosConsultados.get(i).getTipo());
				clienteMoral
						.getDomicilios()
						.get(0)
						.setDescripcion(
								documentosConsultados.get(i).getDescripcion());
				encontrado = true;
			}
		}

		Integer idRepresentante = clienteConsultaRepresentanteLegal
				.ejecutarTRN(idInterno, TipoCliente.PERSONA_MORAL);
		if (idRepresentante != null) {
			clienteMoral.setRepresentante(clienteConsultaPersonaFisica
					.getClienteResponse(idRepresentante));
		}

		ClientePersonaMoralBean personaMoralRecursos = this.consultaRecursosCuentaBackEnd
				.ejecutarTRN(idInterno);
		clienteMoral.setRecursos(personaMoralRecursos.getRecursos());
		clienteMoral.setOtrosRecursos(personaMoralRecursos.getOtrosRecursos());

		ClientePersonaMoralBean personaMoral = cedulaConocimientoUtils
				.consultaCedulaConocimientoMoral(clienteMoral.getIdInterna());
		clienteMoral.setTransaccionalidad(personaMoral.getTransaccionalidad());
		clienteMoral.setUsoCuenta(personaMoral.getUsoCuenta());

		// Se guardan los datos de riesgo
		clienteMoral.setEsClienteRiesgo(personaMoral.getEsClienteRiesgo());
		clienteMoral.setGrupoFilialBean(personaMoral.getGrupoFilialBean());
		clienteMoral.setDonativosBean(personaMoral.getDonativosBean());
		clienteMoral.setRelacionesClienteRiesgo(personaMoral
				.getRelacionesClienteRiesgo());

		return clienteMoral;
	}

	/**
	 * Seteo para la persona Moral
	 * 
	 * @param cliente
	 * @return
	 */
	private ClientePersonaMoralBean setearPersonaMoral(
			PECONSORGOBJTRDV cliente, ClientePersonaMoralBean moral) {

		ConstitucionPersonaMoralBean constitucionBean = new ConstitucionPersonaMoralBean();

		moral.setOficinaAlta(cliente.getCODOFCNAALTA());

		// Datos Generales
		moral.setRazonSocial(cliente.getNOMB50().trim());
		moral.setRazonAlta(cliente.getCODRZNALTAPERS().trim());
		moral.setTipoIdentificacion(cliente.getCODIDEXTPERS().trim());
		moral.setNumIdentificacion(cliente.getIDEXTPE().trim());
		moral.setRfc(cliente.getIDRFC().trim());
		moral.setPais(cliente.getCODPAISRES().trim());
		moral.setSector(cliente.getSECTOROR().trim());
		moral.setEstructuraLegal(cliente.getCODESTRCTLGLORG().trim());
		moral.setSituacionEconomica(cliente.getCODSITECONPERS().trim());
		moral.setDisponibilidadBursatil(cliente.getDISPBURSTLOR().trim());
		moral.setCnae(cliente.getCODCNAEPERS().trim());
		moral.setAmbito(cliente.getCODAMBTOORG().trim());

		/**
		 * Evaluamos el campo IND_IMP_EXP_OR para marcar las casillas de
		 * importador y exportador de acuerdo a la siguiente lógica:
		 * 
		 * 3 > Ambos, 2 > Importador, 1 > Exportador, 0 > Ninguno.
		 */
		if ("3".equals(cliente.getINDIMPEXPOR().trim())) {
			moral.setImportador(true);
			moral.setExportador(true);
		} else if ("2".equals(cliente.getINDIMPEXPOR().trim())) {
			moral.setImportador(true);
			moral.setExportador(false);
		} else if ("1".equals(cliente.getINDIMPEXPOR().trim())) {
			moral.setImportador(false);
			moral.setExportador(true);
		} else {
			moral.setImportador(false);
			moral.setExportador(false);
		}
		
		IntegerToDateConverter intToDateCon = new IntegerToDateConverter();

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		// Datos Constitucion
		constitucionBean.setFechaConstitucion(intToDateCon.convertTo(cliente
				.getFECNCTOCONSTPE()));
		constitucionBean.setFechaCierre(intToDateCon.convertTo(cliente
				.getFECHAFALLCRREPE()));

		// Tratamos la fecha de fallecimiento para visualizarla a vacio si viene
		// como cliente no fallecido
		if (constitucionBean.getFechaCierre() != null
				&& ConstantesFuncionales.MAX_FECHA_FIN.equals(df
						.format(constitucionBean.getFechaCierre()))) {
			constitucionBean.setFechaCierre(null);
		}

		constitucionBean.setMunicipioCatGeo(this.obtenerMunicipio(cliente
				.getCODMUNICIPIOAG()));

		// Datos Registrales
		constitucionBean.setNumDirRegistro(cliente.getNUMDIR());

		constitucionBean.setTipoRegistro(cliente.getCODRGSTRO().trim());

		// Obtenemos la direccion registral informada
		if (cliente.getDRREGNUMV().getNUMARGEO() != 0) {
			DomicilioTipoBean domicilioRegistro = new DomicilioTipoBean();
			domicilioRegistro.setNumArGeo(cliente.getDRREGNUMV().getNUMARGEO());
			domicilioRegistro.setCodArGeo("07");
			constitucionBean.setMunicipioRegistro(this.consultaLocalidad
					.ejecutarTRN(domicilioRegistro).getDatosFinalesCatGeo());

			constitucionBean.setNumRegistro(String.valueOf(cliente
					.getNUMRGSTRO()));
		}

		for (DRCOMPRGSTROV datoRegistral : cliente.getDRCOMPRGSTROV()) {
			if ("01".equals(datoRegistral.getCODCOMPRGSTRO().trim())) {
				constitucionBean.setNumRegistral(datoRegistral
						.getVALCOMPRGSTRODR().trim());
			}
			if ("02".equals(datoRegistral.getCODCOMPRGSTRO().trim())) {
				constitucionBean.setTomo(datoRegistral.getVALCOMPRGSTRODR()
						.trim());
			}
			if ("03".equals(datoRegistral.getCODCOMPRGSTRO().trim())) {
				constitucionBean.setLibro(datoRegistral.getVALCOMPRGSTRODR()
						.trim());
			}
			if ("04".equals(datoRegistral.getCODCOMPRGSTRO().trim())) {
				constitucionBean.setFolio(datoRegistral.getVALCOMPRGSTRODR()
						.trim());
			}
			if ("05".equals(datoRegistral.getCODCOMPRGSTRO().trim())) {
				constitucionBean.setNumInscripcion(datoRegistral
						.getVALCOMPRGSTRODR().trim());
			}
			if ("06".equals(datoRegistral.getCODCOMPRGSTRO().trim())) {
				constitucionBean.setFinca(datoRegistral.getVALCOMPRGSTRODR()
						.trim());
			}
			if ("07".equals(datoRegistral.getCODCOMPRGSTRO().trim())) {
				StringToDateConverter strToDate = new StringToDateConverter();
				constitucionBean.setFechaExpedicion(strToDate
						.convertTo(datoRegistral.getVALCOMPRGSTRODR().trim()));
			}

		}
		// Poder Legal
		if (cliente.getFECBASCONSTOR() != 0) {
			constitucionBean.setFechaAlta(intToDateCon.convertTo(cliente
					.getFECBASCONSTOR()));
		}
		if (cliente.getFECCADCARGOSOR() != 0) {
			constitucionBean.setFechaBaja(intToDateCon.convertTo(cliente
					.getFECCADCARGOSOR()));
		}

		// Tratamos la fecha de inicio de poderes y fin de poderes para ponerlas
		// a nula si viene la de inicio del sistema
		if (constitucionBean.getFechaAlta() != null
				&& ConsultaPersonaMoralBackEnd.FECHA_INICIO_MIN.equals(df
						.format(constitucionBean.getFechaAlta()))) {
			constitucionBean.setFechaAlta(null);
		}

		// Tratamos la fecha de inicio de poderes y fin de poderes para ponerlas
		// a nula si viene la de inicio del sistema
		if (constitucionBean.getFechaBaja() != null
				&& ConsultaPersonaMoralBackEnd.FECHA_INICIO_MIN.equals(df
						.format(constitucionBean.getFechaBaja()))) {
			constitucionBean.setFechaBaja(null);
		}

		constitucionBean.setObservacionesPoderLegal(cliente.getTXTFECBASOR()
				.trim());

		// Capital Social
		constitucionBean.setImporteCapitalSocial(cliente.getCAPITALSOCIALOR());
		constitucionBean.setDistribucion(cliente.getTXTDISTCAPOR().trim());
		constitucionBean.setIdioma(cliente.getCODIDIOMPERS());

		// Invertimos el valor del indicador puesto que el indice es de
		// publicidad
		constitucionBean.setNoPublicidad(!this.formatBool(cliente
				.getINDPUBLCDPE()));

		constitucionBean.setCorrespondencia(this.formatBool(cliente
				.getINDCORREOOFCNA()));

		if (constitucionBean.isCorrespondencia()) {
			constitucionBean.setCorrespondencia(this.formatBool(cliente
					.getINDCORREOOFCNA()));
			constitucionBean.setNumOficina(cliente.getCODOFCNACORR().trim());
		}
		constitucionBean.setObservaciones(cliente.getTXTOBSERVOR().trim());

		moral.setConstitucionBean(constitucionBean);

		return moral;
	}

	/**
	 * Obtener el municipio
	 * 
	 * @param codmunicipioag
	 * @return
	 */
	private CatalogoGeoBean obtenerMunicipio(String codmunicipioag) {

		CatalogoGeoBean resultado = new CatalogoGeoBean();

		List<CatalogoGeoBean> resultadoBusqueda = this.catalogoGeoUtils
				.getCatalogoGeoBean();
		for (int i = 0; i < resultadoBusqueda.size(); i++) {
			if (resultadoBusqueda.get(i).getCodigoMunicipio()
					.contains(codmunicipioag)) {

				resultado = resultadoBusqueda.get(i);
			}
		}

		return resultado;
	}

	private boolean formatBool(String respuesta) {
		if ("S".equals(respuesta)) {
			return true;
		}
		return false;
	}

}
