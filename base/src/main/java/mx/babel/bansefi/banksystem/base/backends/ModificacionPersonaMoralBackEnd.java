package mx.babel.bansefi.banksystem.base.backends;

import java.text.SimpleDateFormat;
import java.util.Date;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaMoralBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.base.webservices.modificacionpersonamoral.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.modificacionpersonamoral.Ejecutar.ITRPEMODIFORGTRNI;
import mx.babel.bansefi.banksystem.base.webservices.modificacionpersonamoral.Ejecutar.ITRPEMODIFORGTRNI.TRPEMODIFORGEVTY.DRDMDPOBJTRDV.CODPERSRLDIRV;
import mx.babel.bansefi.banksystem.base.webservices.modificacionpersonamoral.Ejecutar.ITRPEMODIFORGTRNI.TRPEMODIFORGEVTY.DRDMDPOBJTRDV.COMPDOMICV;
import mx.babel.bansefi.banksystem.base.webservices.modificacionpersonamoral.Ejecutar.ITRPEMODIFORGTRNI.TRPEMODIFORGEVTY.INDMODIFCLINDVV;
import mx.babel.bansefi.banksystem.base.webservices.modificacionpersonamoral.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.modificacionpersonamoral.ModificarPersonaMoralServicio;
import mx.babel.bansefi.banksystem.base.webservices.modificacionpersonamoral.ResponseBansefi;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModificacionPersonaMoralBackEnd extends BackEndBean {

	private static final long serialVersionUID = 1297098271973073955L;
	private static final Integer FECHA_INICIO = 11111111;
	private static final Integer FECHA_FINAL = 99991231;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	public Integer ejecutarTRN(ClientePersonaMoralBean personaModificada,
			ClientePersonaMoralBean personaConsultada) {
		Ejecutar.ITRPEMODIFORGTRNI contexto = initPeticion(personaModificada,
				personaConsultada);
		EjecutarResult respuesta = ejecutarWS(contexto);
		super.verificaRespuesta(respuesta);
		if (verificaResponseBansefi(respuesta)) {
			return modificacionPersonaMoral(respuesta.getResponseBansefi());
		}
		return null;
	}

	private Integer modificacionPersonaMoral(ResponseBansefi response) {
		Integer resultado = null;
		if (verificaRespuestaCliente(response)) {
			resultado = response.getOTRPEMODIFORGTRNO().getTRPEMODIFORGEVTZ()
					.getPEPERSP().getIDINTERNOPE();
		}
		return resultado;
	}

	private boolean verificaRespuestaCliente(ResponseBansefi response) {
		Boolean noNulo = false;
		if (response != null
				&& response.getOTRPEMODIFORGTRNO() != null
				&& response.getOTRPEMODIFORGTRNO().getTRPEMODIFORGEVTZ() != null
				&& response.getOTRPEMODIFORGTRNO().getTRPEMODIFORGEVTZ()
						.getPEPERSP() != null) {
			noNulo = true;
		}
		return noNulo;
	}

	private boolean verificaResponseBansefi(EjecutarResult respuesta) {
		Boolean noNulo = false;
		if (respuesta != null && respuesta.getResponseBansefi() != null) {
			noNulo = true;
		}
		return noNulo;
	}

	private EjecutarResult ejecutarWS(ITRPEMODIFORGTRNI contexto) {
		EjecutarResult respuesta = null;
		try {
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ModificarPersonaMoralServicio.class, contexto);
		} catch (NoControlableException e) {
			throw new NoControlableException(
					"Error al invocar servicio web de modificacion de persona moral",
					e);
		}
		return respuesta;
	}

	private Ejecutar.ITRPEMODIFORGTRNI initPeticion(
			ClientePersonaMoralBean personaModificada,
			ClientePersonaMoralBean personaConsultada) {

		Ejecutar.ITRPEMODIFORGTRNI contexto = new Ejecutar.ITRPEMODIFORGTRNI();
		Ejecutar.ITRPEMODIFORGTRNI.STDTRNIPARMV contextoEntrada = new Ejecutar.ITRPEMODIFORGTRNI.STDTRNIPARMV();
		Ejecutar.ITRPEMODIFORGTRNI.TRPEMODIFORGEVTY.PEALTAORGOBJTRDV cliente = new Ejecutar.ITRPEMODIFORGTRNI.TRPEMODIFORGEVTY.PEALTAORGOBJTRDV();
		Ejecutar.ITRPEMODIFORGTRNI.TRPEMODIFORGEVTY.DRDMDPOBJTRDV domicilio = new Ejecutar.ITRPEMODIFORGTRNI.TRPEMODIFORGEVTY.DRDMDPOBJTRDV();

		final IntegerToDateConverter intDateConverter = new IntegerToDateConverter();

		super.initialize(contexto);
		super.initialize(domicilio);
		super.initialize(cliente);

		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_PE_ALTA_ORG_TRN);
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());

		// Se inicializan los indicadores del objeto IND_MODIF_CL_INDV_V
		contexto.getTRPEMODIFORGEVTY().setINDMODIFCLINDVV(
				this.inicializaIndicadoresModificacionPersonaMoral(
						personaModificada, personaConsultada));

		// Se inicializan los indicadores del objeto IND_MODIF_CL_ORG_V
		contexto.getTRPEMODIFORGEVTY().getINDMODIFCLORGV().getINDPEORGUPD()
				.setSTDCHAR01("S");
		contexto.getTRPEMODIFORGEVTY().getINDMODIFCLORGV()
				.getINDPEORGFRDLNTAUPD().setSTDCHAR01("N");
		contexto.getTRPEMODIFORGEVTY().getINDMODIFCLORGV().getINDDRRGSTROINS()
				.setSTDCHAR01("N");
		contexto.getTRPEMODIFORGEVTY().getINDMODIFCLORGV().getINDDRRGSTROUPD()
				.setSTDCHAR01("N");

		cliente.setCODNRBEEN(getEntidad());
		cliente.setIDINTERNOPE(personaModificada.getIdInterna());

		cliente.setIDEXTPE(personaModificada.getNumIdentificacion());
		cliente.setCODIDEXTPERS(personaModificada.getTipoIdentificacion());

		cliente.setCODTRATMTOPERS("99");
		cliente.setINDLDEROPNNPE("N");
		cliente.setINDAVISOPE("N");
		cliente.setINDANTCNPE("N");
		cliente.setINDPERTNGRPPE("N");
		cliente.setINDPUBLCDPE("N");
		cliente.setINDACCESORSTRG("N");
		cliente.setINDMASGT("N");
		cliente.setINDMASDIR("N");

		cliente.setNUMDIRPRAL(personaModificada.getDomicilios().get(0)
				.getNumeroDireccion());
		cliente.setNUMDIRRGSTRO(0);

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

		cliente.setCODESTRCTLGLORG(personaModificada.getEstructuraLegal());
		cliente.setCODRZNALTAPERS(personaModificada.getRazonAlta());

		cliente.setCODCNAEPERS(personaModificada.getCnae());
		cliente.setCODSITECONPERS(personaModificada.getSituacionEconomica());
		cliente.setCODOFCNAALTA(personaModificada.getOficinaAlta());
		cliente.setNOMBTARJETA(personaModificada.getRazonSocial().trim() + "-");
		cliente.setNOMB50(personaModificada.getRazonSocial());
		cliente.setCODNIVINGRINDV(personaModificada.getSituacionEconomica());

		cliente.setCODAMBTOORG(personaModificada.getAmbito());
		cliente.setDENOMLEGALOR(personaModificada.getRazonSocial());

		cliente.setSECTOROR(personaModificada.getSector());
		cliente.setDISPBURSTLOR(personaModificada.getDisponibilidadBursatil());

		/**
		 * Evaluamos los indicadores: Importador y Exportador de acuerdo a la
		 * siguiente lógica:
		 * 
		 * 1) Sí ambos indicadores se encuentran marcados, establece 3. 
		 * 2) Sí sólo el indicador de importación se encuentra marcado, establece 2.
		 * 3) Sí sólo el indicador de exportación se encuentra marcado, establece 1.
		 * 4) Sí ninguno de los indicadores se encuentra marcado, establece 0.
		 */
		if (personaModificada.getImportador() == true && personaModificada.getExportador() == true) {
			cliente.setINDIMPEXPOR("3");
		} else if (personaModificada.getImportador() == true	&& personaModificada.getExportador() == false) {
			cliente.setINDIMPEXPOR("2");
		} else if (personaModificada.getImportador() == false && personaModificada.getExportador() == true) {
			cliente.setINDIMPEXPOR("1");
		} else {
			cliente.setINDIMPEXPOR("0");
		}

		cliente.setCODIDIOMPERS(personaModificada.getConstitucionBean()
				.getIdioma());

		cliente.setIDRFC(personaModificada.getRfc());
		cliente.setNOMBABREVPE(personaModificada.getRazonSocial());
		cliente.setCODPAISRES(personaModificada.getPais());
		cliente.setINDPPALIDPE("S");

		if (personaModificada.getConstitucionBean().getFechaConstitucion() != null) {
			cliente.setFECNCTOCONSTPE(intDateConverter
					.convertFrom(personaModificada.getConstitucionBean()
							.getFechaConstitucion()));
		}

		// Modificamos a la persona moral de alta con la fecha 31/12/9999 como
		// fecha de cierre de la empresa
		// si no viene informada
		if (personaModificada.getConstitucionBean().getFechaCierre() == null) {
			cliente.setFECHAFALLCRREPE(ModificacionPersonaMoralBackEnd.FECHA_FINAL);
		} else {
			cliente.setFECHAFALLCRREPE(intDateConverter
					.convertFrom(personaModificada.getConstitucionBean()
							.getFechaCierre()));
		}

		if (personaModificada.getConstitucionBean()
				.getObservacionesPoderLegal() != null) {
			cliente.setTXTFECBASOR(personaModificada.getConstitucionBean()
					.getObservacionesPoderLegal());
		}

		if (personaModificada.getConstitucionBean().getFechaAlta() == null) {
			cliente.setFECBASCONSTOR(ModificacionPersonaMoralBackEnd.FECHA_INICIO);
		} else {
			cliente.setFECBASCONSTOR(intDateConverter
					.convertFrom(personaModificada.getConstitucionBean()
							.getFechaAlta()));
		}

		if (personaModificada.getConstitucionBean().getFechaBaja() == null) {
			cliente.setFECCADCARGOSOR(ModificacionPersonaMoralBackEnd.FECHA_INICIO);
		} else {
			cliente.setFECCADCARGOSOR(intDateConverter
					.convertFrom(personaModificada.getConstitucionBean()
							.getFechaBaja()));
		}

		if (personaModificada.getConstitucionBean().getImporteCapitalSocial() != null) {
			cliente.setCAPITALSOCIALOR(personaModificada.getConstitucionBean()
					.getImporteCapitalSocial());
		}

		if (personaModificada.getConstitucionBean().getDistribucion() != null) {
			cliente.setTXTDISTCAPOR(personaModificada.getConstitucionBean()
					.getDistribucion());
		}

		// / Incluimos los datos de Constitucion
		if (personaModificada.getConstitucionBean().getMunicipioCatGeo() != null
				&& personaModificada.getConstitucionBean().getMunicipioCatGeo()
						.getCodigoPostal() != null
				&& !("").equals(personaModificada.getConstitucionBean()
						.getMunicipioCatGeo().getCodigoPostal())) {
			cliente.setCODENTCOLECAG(personaModificada.getConstitucionBean()
					.getMunicipioCatGeo().getCodigoEntidadColectiva());
			cliente.setCODENTSINGAG(personaModificada.getConstitucionBean()
					.getMunicipioCatGeo().getCodigoProvincia());
			cliente.setCODPROVINCIAAG(personaModificada.getConstitucionBean()
					.getMunicipioCatGeo().getCodigoProvincia());
			cliente.setCODMUNICIPIOAG(personaModificada.getConstitucionBean()
					.getMunicipioCatGeo().getCodigoMunicipio());
			cliente.setCODPAISNACTO(personaModificada.getConstitucionBean()
					.getMunicipioCatGeo().getCodigoPais());
			cliente.setNOMBLOCALIDADAG(personaModificada.getConstitucionBean()
					.getMunicipioCatGeo().getNombreLocalidad());
			cliente.setNOMBPROVINCIAAG(personaModificada.getConstitucionBean()
					.getMunicipioCatGeo().getNombreProvincia());
			cliente.setCODPOSTALAG(personaModificada.getConstitucionBean()
					.getMunicipioCatGeo().getCodigoPostal());

			if (personaModificada.getConstitucionBean().getMunicipioCatGeo()
					.getPrNumArGeo() != null) {
				cliente.setNUMARGEOPROV(Integer.parseInt(personaModificada
						.getConstitucionBean().getMunicipioCatGeo()
						.getPrNumArGeo()));
			}

			if (personaModificada.getConstitucionBean().getMunicipioCatGeo()
					.getNumArGeo() != null) {
				cliente.setNUMARGEOPAIS(Integer.parseInt(personaModificada
						.getConstitucionBean().getMunicipioCatGeo()
						.getNumArGeo()));
			}
		}

		// Invertimos el valor del indicador puesto que el indice es de
		// publicidad
		cliente.setINDPUBLCDPE(this.formatBoolean(!personaModificada
				.getConstitucionBean().getNoPublicidad()));

		if (personaModificada.getConstitucionBean().isCorrespondencia()) {
			cliente.setINDCORREOOFCNA("S");
			cliente.setCODOFCNACORR(personaModificada.getConstitucionBean()
					.getNumOficina());
		}
		if (personaModificada.getConstitucionBean().getObservaciones() != null) {
			cliente.setTXTOBSERVOR(personaModificada.getConstitucionBean()
					.getObservaciones());
		}

		/**
		 * Modificacion domicilio
		 */
		if (!personaModificada.getDomicilios().isEmpty()) {

			// Incluimos los datos de domicilio
			domicilio.setCODNRBEEN(super.getEntidad());
			domicilio.setIDINTERNOPE(personaModificada.getIdInterna());
			if (personaModificada.getDireccionPrincipal() != null) {
				domicilio.setNUMDIR(personaModificada.getDireccionPrincipal());
			} else {
				domicilio.setNUMDIR(0);
			}
			domicilio.setCODDIR("1");

			domicilio.setCODVIA(personaModificada.getDomicilios().get(0)
					.getTipoCalle());
			domicilio.setCODREGIMOCUP(personaModificada.getDomicilios().get(0)
					.getRegimenOcupacion());
			domicilio.setNUMTLFNODOMIC(personaModificada.getDomicilios().get(0)
					.getTelefono());
			domicilio.setCODPOSTALAG(personaModificada.getDomicilios().get(0)
					.getDatosFinalesCatGeo().getCodigoPostal());
			domicilio.setNOMBLOCALIDADAG(personaModificada.getDomicilios()
					.get(0).getDatosFinalesCatGeo().getNombreMunicipio());
			domicilio.setCODPROVINCIAAG(personaModificada.getDomicilios()
					.get(0).getDatosFinalesCatGeo().getCodigoProvincia());
			domicilio.setNOMBPROVINCIAAG(personaModificada.getDomicilios()
					.get(0).getDatosFinalesCatGeo().getNombreProvincia());
			domicilio.setCODCOMAUTNMAAG(personaModificada.getDomicilios()
					.get(0).getDatosFinalesCatGeo()
					.getCodigoComunidadAutonoma());
			domicilio.setCODPAISAG(personaModificada.getDomicilios().get(0)
					.getDatosFinalesCatGeo().getCodigoPais());

			domicilio.getCODARGEODOMICILIOV().setCODARGEO(
					personaModificada.getDomicilios().get(0).getCodArGeo());
			if (personaModificada.getDomicilios().get(0)
					.getDatosFinalesCatGeo().getNumArGeo() != null) {
				domicilio.getNUMARGEODOMICILIOV().setNUMARGEO(
						Integer.parseInt(personaModificada.getDomicilios()
								.get(0).getDatosFinalesCatGeo().getNumArGeo()));
			}

			// Incluimos los datos recogidos en el apartado de masDatos
			if (personaModificada.getDomicilios().get(0).getNombreCalle() != null
					&& !("").equals(personaModificada.getDomicilios().get(0)
							.getNombreCalle())) {
				COMPDOMICV nombreCalle = new COMPDOMICV();
				nombreCalle.setCODCOMPDOMIC(ConstantesFuncionales.CALLE);
				nombreCalle
						.setVALCOMPDOMICDRLEN(ConstantesFuncionales.CALLE_LONG);
				nombreCalle.setVALCOMPDOMICDR(personaModificada.getDomicilios()
						.get(0).getNombreCalle());
				domicilio.getCOMPDOMICV().add(nombreCalle);
			}

			if (personaModificada.getDomicilios().get(0).getNumeroExterior() != null
					&& !("").equals(personaModificada.getDomicilios().get(0)
							.getNumeroExterior())) {
				COMPDOMICV numExterior = new COMPDOMICV();
				numExterior.setCODCOMPDOMIC(ConstantesFuncionales.NUMERO_EXT);
				numExterior
						.setVALCOMPDOMICDRLEN(ConstantesFuncionales.NUMERO_EXT_LONG);
				numExterior.setVALCOMPDOMICDR(personaModificada.getDomicilios()
						.get(0).getNumeroExterior());
				domicilio.getCOMPDOMICV().add(numExterior);
			}

			if (personaModificada.getDomicilios().get(0).getInterior() != null
					&& !("").equals(personaModificada.getDomicilios().get(0)
							.getInterior())) {
				COMPDOMICV numInterior = new COMPDOMICV();
				numInterior.setCODCOMPDOMIC(ConstantesFuncionales.INTERIOR);
				numInterior
						.setVALCOMPDOMICDRLEN(ConstantesFuncionales.INTERIOR_LONG);
				numInterior.setVALCOMPDOMICDR(personaModificada.getDomicilios()
						.get(0).getInterior());
				domicilio.getCOMPDOMICV().add(numInterior);
			}

			if (personaModificada.getDomicilios().get(0).getDepartamento() != null
					&& !("").equals(personaModificada.getDomicilios().get(0)
							.getDepartamento())) {
				COMPDOMICV departamento = new COMPDOMICV();
				departamento
						.setCODCOMPDOMIC(ConstantesFuncionales.DEPARTAMENTO);
				departamento
						.setVALCOMPDOMICDRLEN(ConstantesFuncionales.DEPARTAMENTO_LONG);
				departamento.setVALCOMPDOMICDR(personaModificada
						.getDomicilios().get(0).getDepartamento());
				domicilio.getCOMPDOMICV().add(departamento);
			}

			if (personaModificada.getDomicilios().get(0).getColonia() != null
					&& !("").equals(personaModificada.getDomicilios().get(0)
							.getColonia())) {
				COMPDOMICV colonia = new COMPDOMICV();
				colonia.setCODCOMPDOMIC(ConstantesFuncionales.COLONIA);
				colonia.setVALCOMPDOMICDRLEN(ConstantesFuncionales.COLONIA_LONG);
				colonia.setVALCOMPDOMICDR(personaModificada.getDomicilios()
						.get(0).getColonia());
				domicilio.getCOMPDOMICV().add(colonia);
			}

			if (personaModificada.getDomicilios().get(0).getOtrosDatos() != null
					&& !("").equals(personaModificada.getDomicilios().get(0)
							.getOtrosDatos())) {
				COMPDOMICV otrosDatos = new COMPDOMICV();
				otrosDatos.setCODCOMPDOMIC(ConstantesFuncionales.OTROS_DATOS);
				otrosDatos
						.setVALCOMPDOMICDRLEN(ConstantesFuncionales.OTROS_DATOS_LONG);
				otrosDatos.setVALCOMPDOMICDR(personaModificada.getDomicilios()
						.get(0).getOtrosDatos());
				domicilio.getCOMPDOMICV().add(otrosDatos);
			}

			if (personaModificada.getDomicilios().get(0).getEdificio() != null
					&& !("").equals(personaModificada.getDomicilios().get(0)
							.getEdificio())) {
				COMPDOMICV edificio = new COMPDOMICV();
				edificio.setCODCOMPDOMIC(ConstantesFuncionales.EDIFICIO);
				edificio.setVALCOMPDOMICDRLEN(ConstantesFuncionales.EDIFICIO_LONG);
				edificio.setVALCOMPDOMICDR(personaModificada.getDomicilios()
						.get(0).getEdificio());
				domicilio.getCOMPDOMICV().add(edificio);
			}

			if (personaModificada.getDomicilios().get(0)
					.getUnidadHabitacional() != null
					&& !("").equals(personaModificada.getDomicilios().get(0)
							.getUnidadHabitacional())) {
				COMPDOMICV unidadHabitacional = new COMPDOMICV();
				unidadHabitacional
						.setCODCOMPDOMIC(ConstantesFuncionales.UNIDAD_HABITACIONAL);
				unidadHabitacional
						.setVALCOMPDOMICDRLEN(ConstantesFuncionales.UNIDAD_HABITACIONAL_LONG);
				unidadHabitacional.setVALCOMPDOMICDR(personaModificada
						.getDomicilios().get(0).getUnidadHabitacional());
				domicilio.getCOMPDOMICV().add(unidadHabitacional);
			}

			if (personaModificada.getDomicilios().get(0).getManzana() != null
					&& !("").equals(personaModificada.getDomicilios().get(0)
							.getManzana())) {
				COMPDOMICV manzana = new COMPDOMICV();
				manzana.setCODCOMPDOMIC(ConstantesFuncionales.MANZANA);
				manzana.setVALCOMPDOMICDRLEN(ConstantesFuncionales.MANZANA_LONG);
				manzana.setVALCOMPDOMICDR(personaModificada.getDomicilios()
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
		 * Modificacion datos registrales.
		 */

		cliente.setNUMDIRRGSTRO(personaModificada.getConstitucionBean()
				.getNumDirRegistro());
		cliente.setCODRGSTRO(personaModificada.getConstitucionBean()
				.getTipoRegistro());

		// Numero de registro
		if (personaModificada.getConstitucionBean().getNumRegistro() != null
				&& !("").equals(personaModificada.getConstitucionBean()
						.getNumRegistro())) {
			cliente.setNUMRGSTRO(Integer.parseInt(personaModificada
					.getConstitucionBean().getNumRegistro()));
		}

		// Direccion registral
		if (personaModificada.getConstitucionBean().getMunicipioRegistro() != null
				&& personaModificada.getConstitucionBean()
						.getMunicipioRegistro().getCodigoPostal() != null
				&& !("").equals(personaModificada.getConstitucionBean()
						.getMunicipioRegistro().getCodigoPostal())) {
			cliente.getDRREGNUMV().setCODARGEO(
					personaModificada.getConstitucionBean()
							.getMunicipioRegistro().getCodArGeo());
			if (personaModificada.getConstitucionBean().getMunicipioRegistro()
					.getNumArGeo() != null) {
				cliente.getDRREGNUMV().setNUMARGEO(
						Integer.parseInt(personaModificada
								.getConstitucionBean().getMunicipioRegistro()
								.getNumArGeo()));
			}
		}

		// Numero registral
		if (personaModificada.getConstitucionBean().getNumRegistral() != null) {
			Ejecutar.ITRPEMODIFORGTRNI.TRPEMODIFORGEVTY.PEALTAORGOBJTRDV.DRCOMPRGSTROV numRegistral = new Ejecutar.ITRPEMODIFORGTRNI.TRPEMODIFORGEVTY.PEALTAORGOBJTRDV.DRCOMPRGSTROV();
			numRegistral
					.setCODCOMPRGSTRO(ConstantesFuncionales.TP_COD_COMP_RGSTRO_NUM_REGISTRAL);
			numRegistral.setVALCOMPRGSTRODR(personaModificada
					.getConstitucionBean().getNumRegistral());
			numRegistral
					.setVALCOMPRGSTRODRLEN(ConstantesFuncionales.TP_VAL_COMP_RGSTRO_NUM_REGISTRAL_LEN);
			cliente.getDRCOMPRGSTROV().add(numRegistral);
		}
		// Tomo
		if (personaModificada.getConstitucionBean().getTomo() != null) {
			Ejecutar.ITRPEMODIFORGTRNI.TRPEMODIFORGEVTY.PEALTAORGOBJTRDV.DRCOMPRGSTROV tomo = new Ejecutar.ITRPEMODIFORGTRNI.TRPEMODIFORGEVTY.PEALTAORGOBJTRDV.DRCOMPRGSTROV();
			tomo.setCODCOMPRGSTRO(ConstantesFuncionales.TP_COD_COMP_RGSTRO_TOMO);
			tomo.setVALCOMPRGSTRODR(personaModificada.getConstitucionBean()
					.getTomo());
			tomo.setVALCOMPRGSTRODRLEN(ConstantesFuncionales.TP_VAL_COMP_RGSTRO_TOMO_LEN);
			cliente.getDRCOMPRGSTROV().add(tomo);
		}
		// Libro
		if (personaModificada.getConstitucionBean().getLibro() != null) {
			Ejecutar.ITRPEMODIFORGTRNI.TRPEMODIFORGEVTY.PEALTAORGOBJTRDV.DRCOMPRGSTROV libro = new Ejecutar.ITRPEMODIFORGTRNI.TRPEMODIFORGEVTY.PEALTAORGOBJTRDV.DRCOMPRGSTROV();
			libro.setCODCOMPRGSTRO(ConstantesFuncionales.TP_COD_COMP_RGSTRO_LIBRO);
			libro.setVALCOMPRGSTRODR(personaModificada.getConstitucionBean()
					.getLibro());
			libro.setVALCOMPRGSTRODRLEN(ConstantesFuncionales.TP_VAL_COMP_RGSTRO_LIBRO_LEN);
			cliente.getDRCOMPRGSTROV().add(libro);
		}
		// Folio
		if (personaModificada.getConstitucionBean().getFolio() != null) {
			Ejecutar.ITRPEMODIFORGTRNI.TRPEMODIFORGEVTY.PEALTAORGOBJTRDV.DRCOMPRGSTROV folio = new Ejecutar.ITRPEMODIFORGTRNI.TRPEMODIFORGEVTY.PEALTAORGOBJTRDV.DRCOMPRGSTROV();
			folio.setCODCOMPRGSTRO(ConstantesFuncionales.TP_COD_COMP_RGSTRO_FOLIO);
			folio.setVALCOMPRGSTRODR(personaModificada.getConstitucionBean()
					.getFolio());
			folio.setVALCOMPRGSTRODRLEN(ConstantesFuncionales.TP_VAL_COMP_RGSTRO_FOLIO_LEN);
			cliente.getDRCOMPRGSTROV().add(folio);
		}
		// Número de inscripción
		if (personaModificada.getConstitucionBean().getNumInscripcion() != null) {
			Ejecutar.ITRPEMODIFORGTRNI.TRPEMODIFORGEVTY.PEALTAORGOBJTRDV.DRCOMPRGSTROV numInscripcion = new Ejecutar.ITRPEMODIFORGTRNI.TRPEMODIFORGEVTY.PEALTAORGOBJTRDV.DRCOMPRGSTROV();
			numInscripcion
					.setCODCOMPRGSTRO(ConstantesFuncionales.TP_COD_COMP_RGSTRO_NUM_INSC);
			numInscripcion.setVALCOMPRGSTRODR(personaModificada
					.getConstitucionBean().getNumInscripcion());
			numInscripcion
					.setVALCOMPRGSTRODRLEN(ConstantesFuncionales.TP_VAL_COMP_RGSTRO_NUM_INSC_LEN);
			cliente.getDRCOMPRGSTROV().add(numInscripcion);
		}
		// Terreno
		if (personaModificada.getConstitucionBean().getFinca() != null) {
			Ejecutar.ITRPEMODIFORGTRNI.TRPEMODIFORGEVTY.PEALTAORGOBJTRDV.DRCOMPRGSTROV terreno = new Ejecutar.ITRPEMODIFORGTRNI.TRPEMODIFORGEVTY.PEALTAORGOBJTRDV.DRCOMPRGSTROV();
			terreno.setCODCOMPRGSTRO("06");
			terreno.setVALCOMPRGSTRODR(personaModificada.getConstitucionBean()
					.getFinca());
			terreno.setVALCOMPRGSTRODRLEN(personaModificada
					.getConstitucionBean().getFinca().length());
			cliente.getDRCOMPRGSTROV().add(terreno);
		}
		// Fecha
		if (personaModificada.getConstitucionBean().getFechaExpedicion() != null) {
			Ejecutar.ITRPEMODIFORGTRNI.TRPEMODIFORGEVTY.PEALTAORGOBJTRDV.DRCOMPRGSTROV fechaExpedicion = new Ejecutar.ITRPEMODIFORGTRNI.TRPEMODIFORGEVTY.PEALTAORGOBJTRDV.DRCOMPRGSTROV();
			fechaExpedicion
					.setCODCOMPRGSTRO(ConstantesFuncionales.TP_COD_COMP_RGSTRO_FECHA_EXP);
			fechaExpedicion.setVALCOMPRGSTRODR(this.formatearFecha(
					personaModificada.getConstitucionBean()
							.getFechaExpedicion(), "dd/MM/yyyy"));
			fechaExpedicion
					.setVALCOMPRGSTRODRLEN(ConstantesFuncionales.TP_VAL_COMP_RGSTRO_FECHA_EXP_LEN);
			cliente.getDRCOMPRGSTROV().add(fechaExpedicion);
		}

		// modif num dir y ins dr registral si no tenia direccion registral
		if (personaConsultada.getConstitucionBean().getNumDirRegistro() == 0
				&& !StringUtils.isBlank(personaModificada.getConstitucionBean()
						.getTipoRegistro())
				&& personaModificada.getConstitucionBean()
						.getMunicipioRegistro() != null
				&& !StringUtils.isBlank(personaModificada.getConstitucionBean()
						.getMunicipioRegistro().getCodigoMunicipio())) {
			contexto.getTRPEMODIFORGEVTY().getINDMODIFCLINDVV()
					.getINDMODINUMDIRPRAL().setSTDCHAR01("S");
			contexto.getTRPEMODIFORGEVTY().getINDMODIFCLORGV()
					.getINDDRRGSTROINS().setSTDCHAR01("S");
		} else if (!StringUtils.isBlank(personaModificada.getConstitucionBean()
				.getTipoRegistro())
				&& personaModificada.getConstitucionBean()
						.getMunicipioRegistro() != null
				&& !StringUtils.isBlank(personaModificada.getConstitucionBean()
						.getMunicipioRegistro().getCodigoMunicipio())) {
			contexto.getTRPEMODIFORGEVTY().getINDMODIFCLINDVV()
					.getINDMODINUMDIRPRAL().setSTDCHAR01("S");
			contexto.getTRPEMODIFORGEVTY().getINDMODIFCLORGV()
					.getINDDRRGSTROUPD().setSTDCHAR01("S");
		} else if (personaConsultada.getConstitucionBean().getNumDirRegistro() != 0
				&& StringUtils.isBlank(personaModificada.getConstitucionBean()
						.getTipoRegistro())
				&& (personaModificada.getConstitucionBean()
						.getMunicipioRegistro() == null || StringUtils
						.isBlank(personaModificada.getConstitucionBean()
								.getMunicipioRegistro().getCodigoMunicipio()))) {
			cliente.setNUMDIRRGSTRO(0);
			contexto.getTRPEMODIFORGEVTY().getINDMODIFCLINDVV()
					.getINDMODINUMDIRPRAL().setSTDCHAR01("S");
			contexto.getTRPEMODIFORGEVTY().getINDMODIFCLORGV()
					.getINDDRRGSTROUPD().setSTDCHAR01("S");
		}
		
			
		// Si la fecha de cierre viene informada y no estaba informada se incluye la anotacino de persona moral cerrada
		// y se elimina la anotacion en caso contrario
		if (personaModificada.getConstitucionBean().getFechaCierre() != null && personaConsultada.getConstitucionBean().getFechaCierre() ==null){
			contexto.getTRPEMODIFORGEVTY().getINDAVISOFALLEC().setSTDCHAR01("A");
		}else if(personaModificada.getConstitucionBean().getFechaCierre() == null && personaConsultada.getConstitucionBean().getFechaCierre() !=null){
			contexto.getTRPEMODIFORGEVTY().getINDAVISOFALLEC().setSTDCHAR01("B");
		}
		
		// Si la situacion economica de la empresa es en QUIEBRA, SUSPENSION DE PAGOS O QUIEBRA FRAUDULENTA y no estaba en esos estados
		// se incluye anotacion y se elimina en caso contrario
		if (((ConstantesFuncionales.SIT_ECON_QUIEBRA).equals(personaModificada.getSituacionEconomica()) 
						|| (ConstantesFuncionales.SIT_ECON_SUSP_PAGO).equals(personaModificada.getSituacionEconomica()) 
						|| (ConstantesFuncionales.SIT_ECON_QUIEBRA_FRAUD).equals(personaModificada.getSituacionEconomica())) &&
				(!(ConstantesFuncionales.SIT_ECON_QUIEBRA).equals(personaConsultada.getSituacionEconomica()) 
						&& !(ConstantesFuncionales.SIT_ECON_SUSP_PAGO).equals(personaConsultada.getSituacionEconomica()) 
						&& !(ConstantesFuncionales.SIT_ECON_QUIEBRA_FRAUD).equals(personaConsultada.getSituacionEconomica()))){
			contexto.getTRPEMODIFORGEVTY().getINDAVISOSITECO().setSTDCHAR01("A");
		}else if ((!(ConstantesFuncionales.SIT_ECON_QUIEBRA).equals(personaModificada.getSituacionEconomica()) 
					&& !(ConstantesFuncionales.SIT_ECON_SUSP_PAGO).equals(personaModificada.getSituacionEconomica()) 
					&& !(ConstantesFuncionales.SIT_ECON_QUIEBRA_FRAUD).equals(personaModificada.getSituacionEconomica())) &&
				((ConstantesFuncionales.SIT_ECON_QUIEBRA).equals(personaConsultada.getSituacionEconomica()) 
					|| (ConstantesFuncionales.SIT_ECON_SUSP_PAGO).equals(personaConsultada.getSituacionEconomica()) 
					|| (ConstantesFuncionales.SIT_ECON_QUIEBRA_FRAUD).equals(personaConsultada.getSituacionEconomica()))){
			contexto.getTRPEMODIFORGEVTY().getINDAVISOSITECO().setSTDCHAR01("B");
		}
		
		contexto.getTRPEMODIFORGEVTY().setDRDMDPOBJTRDV(domicilio);
		contexto.getTRPEMODIFORGEVTY().setPEALTAORGOBJTRDV(cliente);

		contexto.setSTDTRNIPARMV(contextoEntrada);
		return contexto;
	}

	private INDMODIFCLINDVV inicializaIndicadoresModificacionPersonaMoral(
			ClientePersonaMoralBean personaModificada,
			ClientePersonaMoralBean personaConsultada) {

		INDMODIFCLINDVV indicadores = new INDMODIFCLINDVV();
		super.initialize(indicadores);

		// Se inicializan todos a NO
		indicadores.getINDAGLOCALIDADNOCODINS().setSTDCHAR01("N");
		indicadores.getINDCLCLIENTINS().setSTDCHAR01("N");
		indicadores.getINDDRDOMICCOMPARTIR().setSTDCHAR01("N");
		indicadores.getINDDRDOMICINS().setSTDCHAR01("N");
		indicadores.getINDDRDOMICUPD().setSTDCHAR01("N");
		indicadores.getINDDRDOMICUPDCOMPARTIR().setSTDCHAR01("X");
		indicadores.getINDMODINUMDIRPRAL().setSTDCHAR01("N");
		indicadores.getINDPEACTPROFINDVINS().setSTDCHAR01("N");
		indicadores.getINDPECPCDADLGLINDVINS().setSTDCHAR01("N");
		indicadores.getINDPECPCDADLGLINDVUPD().setSTDCHAR01("N");
		indicadores.getINDPECPCDADLGLINDVUPDDEL().setSTDCHAR01("N");
		indicadores.getINDPEIDEXTINS().setSTDCHAR01("N");
		indicadores.getINDPEIDEXTUPD().setSTDCHAR01("N");
		indicadores.getINDPEIDEXTUPDDEL().setSTDCHAR01("N");
		indicadores.getINDPEINDVFRDLNTAUPD().setSTDCHAR01("N");
		indicadores.getINDPEINDVUPD().setSTDCHAR01("N");
		indicadores.getINDPEOTROCNAEINS().setSTDCHAR01("N");
		indicadores.getINDPEOTROCNAEUPD().setSTDCHAR01("N");
		indicadores.getINDPEPERSRLARGEOINS().setSTDCHAR01("N");
		indicadores.getINDPEPERSRLARGEOUPD().setSTDCHAR01("N");
		indicadores.getINDPEPERSUPD().setSTDCHAR01("N");
		indicadores.getINDPESITECONINS().setSTDCHAR01("N");
		indicadores.getINDPESITECONUPD().setSTDCHAR01("N");
		indicadores.getINDSOLOMODIDOMIC().setSTDCHAR01("N");

		// Como estamos modificando al cliente de entrada activamos los
		// indicadores de insertar en CL_CLIENT, actualizar PE_PERS y actualizar
		// PE_INDV
		indicadores.getINDPEPERSUPD().setSTDCHAR01("S");
		indicadores.getINDPEINDVUPD().setSTDCHAR01("S");

		// Se validan los cambios sobre EP_PE_DP: //

		// Se valida si la idExterna ha sido modificada
		if (!this.compare(personaModificada.getNumIdentificacion(),
				personaConsultada.getNumIdentificacion())) {

			// Si la idExterna ha sido eliminada activamos indicadores de
			// actualizar persona y eliminar/actualizar idExterna
			if (personaModificada.getNumIdentificacion() == null
					|| ("").equals(personaModificada)) {
				indicadores.getINDPEIDEXTUPDDEL().setSTDCHAR01("S");
			}

			// Si la idExterna estaba vacia pero ahora contiene datos activamos
			// indicadores de actualizar persona e insertar la idExterna
			else if (personaConsultada.getNumIdentificacion() == null
					|| ("").equals(personaConsultada.getNumIdentificacion())) {
				indicadores.getINDPEIDEXTINS().setSTDCHAR01("S");
			}

			// Si ninguna de las idExterna estan vacias se activan los
			// indicadores de actualizar personas, actualizar idExterna
			else {
				indicadores.getINDPEIDEXTUPD().setSTDCHAR01("S");

				// Si el tipo de identificacion ha sido modificada, se activa el
				// indicador de insertar idExterna
				if (!this.compare(personaModificada.getTipoIdentificacion(),
						personaConsultada.getTipoIdentificacion())) {
					indicadores.getINDPEIDEXTINS().setSTDCHAR01("S");
				}
			}
		}

		// Si el cnae ha sido modificado se activa el indicador de actualizar
		// persona
		if (!this.compare(personaModificada.getCnae(),
				personaConsultada.getCnae())) {

			// Si el cnae estaba vacio pero ahora tiene datos activamos el
			// indicador de insertar otro CNAE
			if (personaConsultada.getCnae() == null
					|| ("").equals(personaConsultada.getCnae())) {
				indicadores.getINDPEOTROCNAEINS().setSTDCHAR01("S");
			}

			// En caso de que el cnae tuvise datos
			else {
				// Si el cnae ahora esta vacio indicador de actualizar otro CNAE
				if (personaModificada.getCnae() == null
						|| ("").equals(personaModificada.getCnae())) {
					indicadores.getINDPEOTROCNAEUPD().setSTDCHAR01("S");
				}
				// En caso de que el cnae ya tuviese datos y los siga teniendo
				// se activa el indicador de actualizar otro CNAE y de insertar
				// otro CNAE
				else {
					indicadores.getINDPEOTROCNAEUPD().setSTDCHAR01("S");
					indicadores.getINDPEOTROCNAEINS().setSTDCHAR01("S");
				}
			}

		}

		indicadores.getINDDRDOMICUPD().setSTDCHAR01("S");
		indicadores.getINDDRDOMICUPDCOMPARTIR().setSTDCHAR01("S");

		return indicadores;
	}

	/**
	 * Función para comparar dos Dates incluyendo nulos
	 * 
	 * @param str1
	 * @param str2
	 */
	public boolean compare(Date str1, Date str2) {
		return str1 == null ? str2 == null : str1.compareTo(str2) == 0;
	}

	/**
	 * Función para comparar dos Strings incluyendo nulos
	 * 
	 * @param str1
	 * @param str2
	 */
	public boolean compare(String str1, String str2) {
		return str1 == null ? str2 == null : str1.equals(str2);
	}

	private String formatearFecha(Date fechaExpedicion, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(fechaExpedicion);
	}

	private String formatBoolean(boolean bool) {
		if (bool) {
			return "S";
		}
		return "N";
	}

}