package mx.babel.bansefi.banksystem.personas.backend;

import java.text.SimpleDateFormat;
import java.util.Date;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaMoralBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.personas.webservices.altapersonamoral.AltaPersonaMoralServicio;
import mx.babel.bansefi.banksystem.personas.webservices.altapersonamoral.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.altapersonamoral.Ejecutar.ITRPEALTAORGTRNI;
import mx.babel.bansefi.banksystem.personas.webservices.altapersonamoral.Ejecutar.ITRPEALTAORGTRNI.TRPEALTAORGEVTY.DRDMDPOBJTRDV.CODPERSRLDIRV;
import mx.babel.bansefi.banksystem.personas.webservices.altapersonamoral.Ejecutar.ITRPEALTAORGTRNI.TRPEALTAORGEVTY.DRDMDPOBJTRDV.COMPDOMICV;
import mx.babel.bansefi.banksystem.personas.webservices.altapersonamoral.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.altapersonamoral.ResponseBansefi;
import mx.babel.bansefi.banksystem.personas.wrappers.PersonaMoralWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author alejandro.perez
 * 
 */
@Component
public class AltaPersonaMoralBackEnd extends BackEndBean {

	private static final long serialVersionUID = -5379007168578177578L;

	private static final Integer FECHA_INICIO = 11111111;

	private static final Integer FECHA_FINAL = 99991231;

	@Autowired
	PersonaMoralWrapper personaMoralWrapper;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	public Integer ejecutarTRN(final ClientePersonaMoralBean personaMoral)
			throws NoControlableException, ControlableException {
		final Ejecutar.ITRPEALTAORGTRNI contexto = initPeticion(personaMoral);
		final EjecutarResult respuesta = ejecutarWS(contexto);
		super.verificaRespuesta(respuesta);
		if (verificaResponseBansefi(respuesta)) {
			return altaPersonaMoral(respuesta.getResponseBansefi(),
					personaMoral);
		}
		return null;
	}

	private Integer altaPersonaMoral(final ResponseBansefi response,
			final ClientePersonaMoralBean personaMoral) {
		Integer resultado = null;
		if (verificaRespuestaCliente(response)) {
			resultado = response.getOTRPEALTAORGTRNO().getTRPEALTAORGEVTZ()
					.getPEPERSP().getIDINTERNOPE();
		}
		return resultado;
	}

	private boolean verificaRespuestaCliente(final ResponseBansefi response) {
		Boolean noNulo = false;
		if (response != null
				&& response.getOTRPEALTAORGTRNO() != null
				&& response.getOTRPEALTAORGTRNO().getTRPEALTAORGEVTZ() != null
				&& response.getOTRPEALTAORGTRNO().getTRPEALTAORGEVTZ()
						.getPEPERSP() != null) {
			noNulo = true;
		}
		return noNulo;
	}

	private boolean verificaResponseBansefi(final EjecutarResult respuesta) {
		Boolean noNulo = false;
		if (respuesta != null && respuesta.getResponseBansefi() != null) {
			noNulo = true;
		}
		return noNulo;
	}

	private EjecutarResult ejecutarWS(final ITRPEALTAORGTRNI contexto) {
		EjecutarResult respuesta = null;
		try {
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					AltaPersonaMoralServicio.class, contexto);
		} catch (final NoControlableException e) {
			throw new NoControlableException(
					"Error al invocar servicio web de alta de persona", e);
		}
		return respuesta;
	}

	private Ejecutar.ITRPEALTAORGTRNI initPeticion(
			final ClientePersonaMoralBean personaMoralBean) {
		final Ejecutar.ITRPEALTAORGTRNI contexto = new Ejecutar.ITRPEALTAORGTRNI();
		final Ejecutar.ITRPEALTAORGTRNI.STDTRNIPARMV contextoEntrada = new Ejecutar.ITRPEALTAORGTRNI.STDTRNIPARMV();
		final Ejecutar.ITRPEALTAORGTRNI.TRPEALTAORGEVTY.PEALTAORGOBJTRDV cliente = new Ejecutar.ITRPEALTAORGTRNI.TRPEALTAORGEVTY.PEALTAORGOBJTRDV();

		final Ejecutar.ITRPEALTAORGTRNI.TRPEALTAORGEVTY.DRDMDPOBJTRDV domicilio = new Ejecutar.ITRPEALTAORGTRNI.TRPEALTAORGEVTY.DRDMDPOBJTRDV();

		final IntegerToDateConverter intDateConverter = new IntegerToDateConverter();

		super.initialize(contexto);
		super.initialize(domicilio);
		super.initialize(cliente);

		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_PE_ALTA_ORG_TRN);
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());

		cliente.setCODNRBEEN(getEntidad());

		cliente.setIDEXTPE(personaMoralBean.getNumIdentificacion());
		cliente.setCODIDEXTPERS(personaMoralBean.getTipoIdentificacion());

		cliente.setFECHAALTAPER(intDateConverter.convertFrom(new Date()));

		// Damos a la persona moral de alta con la fecha 31/12/9999 como fecha
		// de cierre de la empresa
		cliente.setFECHAFALLCRREPE(AltaPersonaMoralBackEnd.FECHA_FINAL);

		cliente.setCODTRATMTOPERS("99");
		cliente.setINDLDEROPNNPE("N");
		cliente.setINDAVISOPE("N");
		cliente.setINDANTCNPE("N");
		cliente.setINDPERTNGRPPE("N");
		cliente.setINDPUBLCDPE("N");
		cliente.setINDACCESORSTRG("N");
		cliente.setINDMASGT("N");
		cliente.setINDMASDIR("N");

		cliente.setNUMACTPROFIN(0);
		cliente.setFECHAINICDIR(intDateConverter.convertFrom(new Date()));
		cliente.setFECINICCNAE(intDateConverter.convertFrom(new Date()));
		cliente.setFECINICSITECON(intDateConverter.convertFrom(new Date()));
		cliente.setINDCORREOOFCNA("N");
		cliente.setINDNODOCUM("N");
		cliente.setINDCLIENTE("S");
		cliente.setINDOFERTAS("N");
		cliente.setINDOTRRL("N");
		cliente.setINDPERTNLSTNGR("N");
		cliente.setINDCLIENTPRSPCTV("N");
		cliente.setINDCLIENTRCHZD("N");
		cliente.setINDSOCIOPE("N");
		cliente.setIDINTERNOGTCOM(0);
		cliente.setINDCLIENTESQVCL("N");
		cliente.setINDNOESPAWOL("N");

		cliente.setSITRESDNCPE("1");
		cliente.setCODPE("J");

		cliente.setCODESTRCTLGLORG(personaMoralBean.getEstructuraLegal());
		cliente.setCODRZNALTAPERS(personaMoralBean.getRazonAlta());

		cliente.setCODCNAEPERS(personaMoralBean.getCnae());
		cliente.setCODSITECONPERS(personaMoralBean.getSituacionEconomica());
		cliente.setCODOFCNAALTA(getSucursal());
		cliente.setNOMBTARJETA(personaMoralBean.getRazonSocial().trim() + "-");
		cliente.setNOMB50(personaMoralBean.getRazonSocial());
		cliente.setCODNIVINGRINDV(personaMoralBean.getSituacionEconomica());

		cliente.setCODAMBTOORG(personaMoralBean.getAmbito());
		cliente.setDENOMLEGALOR(personaMoralBean.getRazonSocial());

		cliente.setSECTOROR(personaMoralBean.getSector());
		cliente.setCODRGSTRO(personaMoralBean.getConstitucionBean()
				.getTipoRegistro());
		cliente.setDISPBURSTLOR(personaMoralBean.getDisponibilidadBursatil());

		/**
		 * Evaluamos los indicadores: Importador y Exportador de acuerdo a la
		 * siguiente lógica:
		 * 
		 * 1) Sí ambos indicadores se encuentran marcados, establece 3. 
		 * 2) Sí sólo el indicador de importación se encuentra marcado, establece 2.
		 * 3) Sí sólo el indicador de exportación se encuentra marcado, establece 1.
		 * 4) Sí ninguno de los indicadores se encuentra marcado, establece 0.
		 */
		if (personaMoralBean.getImportador() == true && personaMoralBean.getExportador() == true) {
			cliente.setINDIMPEXPOR("3");
		} else if (personaMoralBean.getImportador() == true	&& personaMoralBean.getExportador() == false) {
			cliente.setINDIMPEXPOR("2");
		} else if (personaMoralBean.getImportador() == false && personaMoralBean.getExportador() == true) {
			cliente.setINDIMPEXPOR("1");
		} else {
			cliente.setINDIMPEXPOR("0");
		}

		cliente.setCODIDIOMPERS(personaMoralBean.getConstitucionBean()
				.getIdioma());

		cliente.setIDRFC(personaMoralBean.getRfc());
		cliente.setNOMBABREVPE(personaMoralBean.getRazonSocial());
		cliente.setCODPAISRES(personaMoralBean.getPais());
		cliente.setINDPPALIDPE("S");

		if (personaMoralBean.getConstitucionBean().getFechaConstitucion() != null) {
			cliente.setFECNCTOCONSTPE(intDateConverter
					.convertFrom(personaMoralBean.getConstitucionBean()
							.getFechaConstitucion()));
		}

		if (personaMoralBean.getConstitucionBean().getObservacionesPoderLegal() != null) {
			cliente.setTXTFECBASOR(personaMoralBean.getConstitucionBean()
					.getObservacionesPoderLegal());
		}

		if (personaMoralBean.getConstitucionBean().getFechaAlta() == null) {
			cliente.setFECBASCONSTOR(AltaPersonaMoralBackEnd.FECHA_INICIO);
		} else {
			cliente.setFECBASCONSTOR(intDateConverter
					.convertFrom(personaMoralBean.getConstitucionBean()
							.getFechaAlta()));
		}

		if (personaMoralBean.getConstitucionBean().getFechaBaja() == null) {
			cliente.setFECCADCARGOSOR(AltaPersonaMoralBackEnd.FECHA_INICIO);
		} else {
			cliente.setFECCADCARGOSOR(intDateConverter
					.convertFrom(personaMoralBean.getConstitucionBean()
							.getFechaBaja()));
		}

		if (personaMoralBean.getConstitucionBean().getImporteCapitalSocial() != null) {
			cliente.setCAPITALSOCIALOR(personaMoralBean.getConstitucionBean()
					.getImporteCapitalSocial());
		}

		if (personaMoralBean.getConstitucionBean().getDistribucion() != null) {
			cliente.setTXTDISTCAPOR(personaMoralBean.getConstitucionBean()
					.getDistribucion());
		}

		// / Incluimos los datos de Constitucion
		if (personaMoralBean.getConstitucionBean().getMunicipioCatGeo() != null
				&& personaMoralBean.getConstitucionBean().getMunicipioCatGeo()
						.getCodigoPostal() != null
				&& !("").equals(personaMoralBean.getConstitucionBean()
						.getMunicipioCatGeo().getCodigoPostal())) {
			cliente.setCODENTCOLECAG(personaMoralBean.getConstitucionBean()
					.getMunicipioCatGeo().getCodigoEntidadColectiva());
			cliente.setCODENTSINGAG(personaMoralBean.getConstitucionBean()
					.getMunicipioCatGeo().getCodigoProvincia());
			cliente.setCODPROVINCIAAG(personaMoralBean.getConstitucionBean()
					.getMunicipioCatGeo().getCodigoProvincia());
			cliente.setCODMUNICIPIOAG(personaMoralBean.getConstitucionBean()
					.getMunicipioCatGeo().getCodigoMunicipio());
			cliente.setCODPAISNACTO(personaMoralBean.getConstitucionBean()
					.getMunicipioCatGeo().getCodigoPais());
			cliente.setNOMBLOCALIDADAG(personaMoralBean.getConstitucionBean()
					.getMunicipioCatGeo().getNombreLocalidad());
			cliente.setNOMBPROVINCIAAG(personaMoralBean.getConstitucionBean()
					.getMunicipioCatGeo().getNombreProvincia());
			cliente.setCODPOSTALAG(personaMoralBean.getConstitucionBean()
					.getMunicipioCatGeo().getCodigoPostal());

			if (personaMoralBean.getConstitucionBean().getMunicipioCatGeo()
					.getPrNumArGeo() != null) {
				cliente.setNUMARGEOPROV(Integer.parseInt(personaMoralBean
						.getConstitucionBean().getMunicipioCatGeo()
						.getPrNumArGeo()));
			}

			if (personaMoralBean.getConstitucionBean().getMunicipioCatGeo()
					.getNumArGeo() != null) {
				cliente.setNUMARGEOPAIS(Integer.parseInt(personaMoralBean
						.getConstitucionBean().getMunicipioCatGeo()
						.getNumArGeo()));
			}
		}

		// Invertimos el valor del indicador puesto que el indice es de
		// publicidad
		cliente.setINDPUBLCDPE(this.formatBoolean(!personaMoralBean
				.getConstitucionBean().getNoPublicidad()));

		if (personaMoralBean.getConstitucionBean().isCorrespondencia()) {
			cliente.setINDCORREOOFCNA("S");
			cliente.setCODOFCNACORR(personaMoralBean.getConstitucionBean()
					.getNumOficina());
		}
		if (personaMoralBean.getConstitucionBean().getObservaciones() != null) {
			cliente.setTXTOBSERVOR(personaMoralBean.getConstitucionBean()
					.getObservaciones());
		}

		/**
		 * Alta domicilio
		 */
		if (!personaMoralBean.getDomicilios().isEmpty()) {

			// Incluimos los datos de domicilio
			domicilio.setCODNRBEEN(super.getEntidad());
			domicilio.setCODDIR("1");

			domicilio.setCODVIA(personaMoralBean.getDomicilios().get(0)
					.getTipoCalle());
			domicilio.setCODREGIMOCUP(personaMoralBean.getDomicilios().get(0)
					.getRegimenOcupacion());
			domicilio.setNUMTLFNODOMIC(personaMoralBean.getDomicilios().get(0)
					.getTelefono());
			domicilio.setCODPOSTALAG(personaMoralBean.getDomicilios().get(0)
					.getDatosFinalesCatGeo().getCodigoPostal());
			domicilio.setNOMBLOCALIDADAG(personaMoralBean.getDomicilios()
					.get(0).getDatosFinalesCatGeo().getNombreMunicipio());
			domicilio.setCODPROVINCIAAG(personaMoralBean.getDomicilios().get(0)
					.getDatosFinalesCatGeo().getCodigoProvincia());
			domicilio.setNOMBPROVINCIAAG(personaMoralBean.getDomicilios()
					.get(0).getDatosFinalesCatGeo().getNombreProvincia());
			domicilio.setCODCOMAUTNMAAG(personaMoralBean.getDomicilios().get(0)
					.getDatosFinalesCatGeo().getCodigoComunidadAutonoma());
			domicilio.setCODPAISAG(personaMoralBean.getDomicilios().get(0)
					.getDatosFinalesCatGeo().getCodigoPais());

			domicilio.getCODARGEODOMICILIOV().setCODARGEO(
					personaMoralBean.getDomicilios().get(0).getCodArGeo());
			if (personaMoralBean.getDomicilios().get(0).getDatosFinalesCatGeo()
					.getNumArGeo() != null) {
				domicilio.getNUMARGEODOMICILIOV().setNUMARGEO(
						Integer.parseInt(personaMoralBean.getDomicilios()
								.get(0).getDatosFinalesCatGeo().getNumArGeo()));
			}

			// Incluimos los datos recogidos en el apartado de masDatos
			if (personaMoralBean.getDomicilios().get(0).getNombreCalle() != null
					&& !("").equals(personaMoralBean.getDomicilios().get(0)
							.getNombreCalle())) {
				COMPDOMICV nombreCalle = new COMPDOMICV();
				nombreCalle.setCODCOMPDOMIC(ConstantesFuncionales.CALLE);
				nombreCalle
						.setVALCOMPDOMICDRLEN(ConstantesFuncionales.CALLE_LONG);
				nombreCalle.setVALCOMPDOMICDR(personaMoralBean.getDomicilios()
						.get(0).getNombreCalle());
				domicilio.getCOMPDOMICV().add(nombreCalle);
			}

			if (personaMoralBean.getDomicilios().get(0).getNumeroExterior() != null
					&& !("").equals(personaMoralBean.getDomicilios().get(0)
							.getNumeroExterior())) {
				COMPDOMICV numExterior = new COMPDOMICV();
				numExterior.setCODCOMPDOMIC(ConstantesFuncionales.NUMERO_EXT);
				numExterior
						.setVALCOMPDOMICDRLEN(ConstantesFuncionales.NUMERO_EXT_LONG);
				numExterior.setVALCOMPDOMICDR(personaMoralBean.getDomicilios()
						.get(0).getNumeroExterior());
				domicilio.getCOMPDOMICV().add(numExterior);
			}

			if (personaMoralBean.getDomicilios().get(0).getInterior() != null
					&& !("").equals(personaMoralBean.getDomicilios().get(0)
							.getInterior())) {
				COMPDOMICV numInterior = new COMPDOMICV();
				numInterior.setCODCOMPDOMIC(ConstantesFuncionales.INTERIOR);
				numInterior
						.setVALCOMPDOMICDRLEN(ConstantesFuncionales.INTERIOR_LONG);
				numInterior.setVALCOMPDOMICDR(personaMoralBean.getDomicilios()
						.get(0).getInterior());
				domicilio.getCOMPDOMICV().add(numInterior);
			}

			if (personaMoralBean.getDomicilios().get(0).getDepartamento() != null
					&& !("").equals(personaMoralBean.getDomicilios().get(0)
							.getDepartamento())) {
				COMPDOMICV departamento = new COMPDOMICV();
				departamento
						.setCODCOMPDOMIC(ConstantesFuncionales.DEPARTAMENTO);
				departamento
						.setVALCOMPDOMICDRLEN(ConstantesFuncionales.DEPARTAMENTO_LONG);
				departamento.setVALCOMPDOMICDR(personaMoralBean.getDomicilios()
						.get(0).getDepartamento());
				domicilio.getCOMPDOMICV().add(departamento);
			}

			if (personaMoralBean.getDomicilios().get(0).getColonia() != null
					&& !("").equals(personaMoralBean.getDomicilios().get(0)
							.getColonia())) {
				COMPDOMICV colonia = new COMPDOMICV();
				colonia.setCODCOMPDOMIC(ConstantesFuncionales.COLONIA);
				colonia.setVALCOMPDOMICDRLEN(ConstantesFuncionales.COLONIA_LONG);
				colonia.setVALCOMPDOMICDR(personaMoralBean.getDomicilios()
						.get(0).getColonia());
				domicilio.getCOMPDOMICV().add(colonia);
			}

			if (personaMoralBean.getDomicilios().get(0).getOtrosDatos() != null
					&& !("").equals(personaMoralBean.getDomicilios().get(0)
							.getOtrosDatos())) {
				COMPDOMICV otrosDatos = new COMPDOMICV();
				otrosDatos.setCODCOMPDOMIC(ConstantesFuncionales.OTROS_DATOS);
				otrosDatos
						.setVALCOMPDOMICDRLEN(ConstantesFuncionales.OTROS_DATOS_LONG);
				otrosDatos.setVALCOMPDOMICDR(personaMoralBean.getDomicilios()
						.get(0).getOtrosDatos());
				domicilio.getCOMPDOMICV().add(otrosDatos);
			}

			if (personaMoralBean.getDomicilios().get(0).getEdificio() != null
					&& !("").equals(personaMoralBean.getDomicilios().get(0)
							.getEdificio())) {
				COMPDOMICV edificio = new COMPDOMICV();
				edificio.setCODCOMPDOMIC(ConstantesFuncionales.EDIFICIO);
				edificio.setVALCOMPDOMICDRLEN(ConstantesFuncionales.EDIFICIO_LONG);
				edificio.setVALCOMPDOMICDR(personaMoralBean.getDomicilios()
						.get(0).getEdificio());
				domicilio.getCOMPDOMICV().add(edificio);
			}

			if (personaMoralBean.getDomicilios().get(0).getUnidadHabitacional() != null
					&& !("").equals(personaMoralBean.getDomicilios().get(0)
							.getUnidadHabitacional())) {
				COMPDOMICV unidadHabitacional = new COMPDOMICV();
				unidadHabitacional
						.setCODCOMPDOMIC(ConstantesFuncionales.UNIDAD_HABITACIONAL);
				unidadHabitacional
						.setVALCOMPDOMICDRLEN(ConstantesFuncionales.UNIDAD_HABITACIONAL_LONG);
				unidadHabitacional.setVALCOMPDOMICDR(personaMoralBean
						.getDomicilios().get(0).getUnidadHabitacional());
				domicilio.getCOMPDOMICV().add(unidadHabitacional);
			}

			if (personaMoralBean.getDomicilios().get(0).getManzana() != null
					&& !("").equals(personaMoralBean.getDomicilios().get(0)
							.getManzana())) {
				COMPDOMICV manzana = new COMPDOMICV();
				manzana.setCODCOMPDOMIC(ConstantesFuncionales.MANZANA);
				manzana.setVALCOMPDOMICDRLEN(ConstantesFuncionales.MANZANA_LONG);
				manzana.setVALCOMPDOMICDR(personaMoralBean.getDomicilios()
						.get(0).getManzana());
				domicilio.getCOMPDOMICV().add(manzana);
			}

			// Incluimos tipo de direccion fiscal y social
			CODPERSRLDIRV tipoDireccionFiscal = new CODPERSRLDIRV();
			tipoDireccionFiscal.setCODPERSRLDIR("02");

			CODPERSRLDIRV tipoDireccionHabitual = new CODPERSRLDIRV();
			tipoDireccionHabitual.setCODPERSRLDIR("03");

			domicilio.getCODPERSRLDIRV().add(tipoDireccionFiscal);
			domicilio.getCODPERSRLDIRV().add(tipoDireccionHabitual);

		}

		/**
		 * Alta datos registrales.
		 */
		// Numero de registro
		if (personaMoralBean.getConstitucionBean().getNumRegistro() != null
				&& !("").equals(personaMoralBean.getConstitucionBean()
						.getNumRegistro())) {
			cliente.setNUMRGSTRO(Integer.parseInt(personaMoralBean
					.getConstitucionBean().getNumRegistro()));
		}

		// Direccion registral
		if (personaMoralBean.getConstitucionBean().getMunicipioRegistro() != null
				&& personaMoralBean.getConstitucionBean()
						.getMunicipioRegistro().getCodigoPostal() != null
				&& !("").equals(personaMoralBean.getConstitucionBean()
						.getMunicipioRegistro().getCodigoPostal())) {
			cliente.getDRREGNUMV().setCODARGEO(
					personaMoralBean.getConstitucionBean()
							.getMunicipioRegistro().getCodArGeo());
			if (personaMoralBean.getConstitucionBean().getMunicipioRegistro()
					.getNumArGeo() != null) {
				cliente.getDRREGNUMV().setNUMARGEO(
						Integer.parseInt(personaMoralBean.getConstitucionBean()
								.getMunicipioRegistro().getNumArGeo()));
			}
		}

		// Numero registral
		if (personaMoralBean.getConstitucionBean().getNumRegistral() != null) {
			final Ejecutar.ITRPEALTAORGTRNI.TRPEALTAORGEVTY.PEALTAORGOBJTRDV.DRCOMPRGSTROV numRegistral = new Ejecutar.ITRPEALTAORGTRNI.TRPEALTAORGEVTY.PEALTAORGOBJTRDV.DRCOMPRGSTROV();
			numRegistral
					.setCODCOMPRGSTRO(ConstantesFuncionales.TP_COD_COMP_RGSTRO_NUM_REGISTRAL);
			numRegistral.setVALCOMPRGSTRODR(personaMoralBean
					.getConstitucionBean().getNumRegistral());
			numRegistral
					.setVALCOMPRGSTRODRLEN(ConstantesFuncionales.TP_VAL_COMP_RGSTRO_NUM_REGISTRAL_LEN);
			cliente.getDRCOMPRGSTROV().add(numRegistral);
		}
		// Tomo
		if (personaMoralBean.getConstitucionBean().getTomo() != null) {
			final Ejecutar.ITRPEALTAORGTRNI.TRPEALTAORGEVTY.PEALTAORGOBJTRDV.DRCOMPRGSTROV tomo = new Ejecutar.ITRPEALTAORGTRNI.TRPEALTAORGEVTY.PEALTAORGOBJTRDV.DRCOMPRGSTROV();
			tomo.setCODCOMPRGSTRO(ConstantesFuncionales.TP_COD_COMP_RGSTRO_TOMO);
			tomo.setVALCOMPRGSTRODR(personaMoralBean.getConstitucionBean()
					.getTomo());
			tomo.setVALCOMPRGSTRODRLEN(ConstantesFuncionales.TP_VAL_COMP_RGSTRO_TOMO_LEN);
			cliente.getDRCOMPRGSTROV().add(tomo);
		}
		// Libro
		if (personaMoralBean.getConstitucionBean().getLibro() != null) {
			final Ejecutar.ITRPEALTAORGTRNI.TRPEALTAORGEVTY.PEALTAORGOBJTRDV.DRCOMPRGSTROV libro = new Ejecutar.ITRPEALTAORGTRNI.TRPEALTAORGEVTY.PEALTAORGOBJTRDV.DRCOMPRGSTROV();
			libro.setCODCOMPRGSTRO(ConstantesFuncionales.TP_COD_COMP_RGSTRO_LIBRO);
			libro.setVALCOMPRGSTRODR(personaMoralBean.getConstitucionBean()
					.getLibro());
			libro.setVALCOMPRGSTRODRLEN(ConstantesFuncionales.TP_VAL_COMP_RGSTRO_LIBRO_LEN);
			cliente.getDRCOMPRGSTROV().add(libro);
		}
		// Folio
		if (personaMoralBean.getConstitucionBean().getFolio() != null) {
			final Ejecutar.ITRPEALTAORGTRNI.TRPEALTAORGEVTY.PEALTAORGOBJTRDV.DRCOMPRGSTROV folio = new Ejecutar.ITRPEALTAORGTRNI.TRPEALTAORGEVTY.PEALTAORGOBJTRDV.DRCOMPRGSTROV();
			folio.setCODCOMPRGSTRO(ConstantesFuncionales.TP_COD_COMP_RGSTRO_FOLIO);
			folio.setVALCOMPRGSTRODR(personaMoralBean.getConstitucionBean()
					.getFolio());
			folio.setVALCOMPRGSTRODRLEN(ConstantesFuncionales.TP_VAL_COMP_RGSTRO_FOLIO_LEN);
			cliente.getDRCOMPRGSTROV().add(folio);
		}
		// Número de inscripción
		if (personaMoralBean.getConstitucionBean().getNumInscripcion() != null) {
			final Ejecutar.ITRPEALTAORGTRNI.TRPEALTAORGEVTY.PEALTAORGOBJTRDV.DRCOMPRGSTROV numInscripcion = new Ejecutar.ITRPEALTAORGTRNI.TRPEALTAORGEVTY.PEALTAORGOBJTRDV.DRCOMPRGSTROV();
			numInscripcion
					.setCODCOMPRGSTRO(ConstantesFuncionales.TP_COD_COMP_RGSTRO_NUM_INSC);
			numInscripcion.setVALCOMPRGSTRODR(personaMoralBean
					.getConstitucionBean().getNumInscripcion());
			numInscripcion
					.setVALCOMPRGSTRODRLEN(ConstantesFuncionales.TP_VAL_COMP_RGSTRO_NUM_INSC_LEN);
			cliente.getDRCOMPRGSTROV().add(numInscripcion);
		}
		// Terreno
		if (personaMoralBean.getConstitucionBean().getFinca() != null) {
			final Ejecutar.ITRPEALTAORGTRNI.TRPEALTAORGEVTY.PEALTAORGOBJTRDV.DRCOMPRGSTROV terreno = new Ejecutar.ITRPEALTAORGTRNI.TRPEALTAORGEVTY.PEALTAORGOBJTRDV.DRCOMPRGSTROV();
			terreno.setCODCOMPRGSTRO("06");
			terreno.setVALCOMPRGSTRODR(personaMoralBean.getConstitucionBean()
					.getFinca());
			terreno.setVALCOMPRGSTRODRLEN(personaMoralBean
					.getConstitucionBean().getFinca().length());
			cliente.getDRCOMPRGSTROV().add(terreno);
		}
		// Fecha
		if (personaMoralBean.getConstitucionBean().getFechaExpedicion() != null) {
			final Ejecutar.ITRPEALTAORGTRNI.TRPEALTAORGEVTY.PEALTAORGOBJTRDV.DRCOMPRGSTROV fechaExpedicion = new Ejecutar.ITRPEALTAORGTRNI.TRPEALTAORGEVTY.PEALTAORGOBJTRDV.DRCOMPRGSTROV();
			fechaExpedicion
					.setCODCOMPRGSTRO(ConstantesFuncionales.TP_COD_COMP_RGSTRO_FECHA_EXP);
			fechaExpedicion.setVALCOMPRGSTRODR(this
					.formatearFecha(personaMoralBean.getConstitucionBean()
							.getFechaExpedicion(), "dd/MM/yyyy"));
			fechaExpedicion
					.setVALCOMPRGSTRODRLEN(ConstantesFuncionales.TP_VAL_COMP_RGSTRO_FECHA_EXP_LEN);
			cliente.getDRCOMPRGSTROV().add(fechaExpedicion);
		}
		
		// Se inicializan los indicadores de anotaciones
		
		// Si la situacion economica de la empresa es en QUIEBRA, SUSPENSION DE PAGOS O QUIEBRA FRAUDULENTA
		if (personaMoralBean.getSituacionEconomica() !=null && 
				(personaMoralBean.getSituacionEconomica().equals(ConstantesFuncionales.SIT_ECON_QUIEBRA) 
						|| personaMoralBean.getSituacionEconomica().equals(ConstantesFuncionales.SIT_ECON_SUSP_PAGO) 
						|| personaMoralBean.getSituacionEconomica().equals(ConstantesFuncionales.SIT_ECON_QUIEBRA_FRAUD))){
			contexto.getTRPEALTAORGEVTY().getINDAVISOSITECO().setSTDCHAR01("A");
		}
		
		contexto.getTRPEALTAORGEVTY().setDRDMDPOBJTRDV(domicilio);
		contexto.getTRPEALTAORGEVTY().setPEALTAORGOBJTRDV(cliente);

		contexto.setSTDTRNIPARMV(contextoEntrada);
		return contexto;
	}

	private String formatearFecha(Date fechaExpedicion, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(fechaExpedicion);
	}

	private String formatBoolean(final boolean bool) {
		if (bool) {
			return "S";
		}
		return "N";
	}

}