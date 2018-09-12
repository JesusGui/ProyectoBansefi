package mx.babel.bansefi.banksystem.personas.backend;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.backends.ModificacionPerfilTransaccionalPersonaBackEnd;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaFisicaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionpersonafisica.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionpersonafisica.Ejecutar.ITRPEMODIFINDVTRNI.TRPEMODIFINDVEVTY.DRDMDPOBJTRDV.CODPERSRLDIRV;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionpersonafisica.Ejecutar.ITRPEMODIFINDVTRNI.TRPEMODIFINDVEVTY.DRDMDPOBJTRDV.COMPDOMICV;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionpersonafisica.Ejecutar.ITRPEMODIFINDVTRNI.TRPEMODIFINDVEVTY.INDMODIFCLINDVV;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionpersonafisica.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionpersonafisica.ModificacionPersonaFisicaServicio;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionpersonafisica.ResponseBansefi;
import mx.babel.bansefi.banksystem.personas.wrappers.PersonasWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para al modificacion de un cliente en especifico
 * 
 * @author javier.martinnino
 *
 */
@Component
public class ModificacionPersonaFisicaBackEnd extends BackEndBean{
	
	private static final long serialVersionUID = -585586199240389201L;
	private static final int FECHA_INICIO = 11111111;
	private static final int FECHA_FIN = 99991231;
	private static final String COD_POSTAL_NO_COD = "99412";
	private static final String COD_ARGEO_NO_COD = "11";
	private static final String COD_PERS_RL_AR_GEO_NO_COD = "01";
	private static final String GUION = "-";
	
	@Autowired
	ServicioWebUtils servicioWebUtils;

	@Autowired
	private ModificacionPerfilTransaccionalPersonaBackEnd modificacionPerfilTransaccionalPersonaBackend;
	
	@Autowired
	PersonasWrapper personaWrapper;
	
	/**
	 * Función encargada de la modificacion de un cliente
	 * por medio de servicios web.
	 * 
	 * @param personaModificada con los datos modificados
	 * @param personaConsultada con los datos de host
	 * @return idInterna del nuevo cliente
	 */
	public Integer ejecutarTRN(ClientePersonaFisicaBean personaModificada,ClientePersonaFisicaBean personaConsultada){
		Ejecutar.ITRPEMODIFINDVTRNI contexto = initPeticion(personaModificada,personaConsultada);
		EjecutarResult respuesta = ejecutarWS(contexto);
		super.verificaRespuesta(respuesta);
		if(verificaResponseBansefi(respuesta)){
			return modificacionCliente(respuesta.getResponseBansefi(),personaModificada);
		}
		return null;
	}
	
	/**
	 * Función que verifica la modificacion del cliente.
	 * 
	 * @param response
	 * @param persona
	 * @return
	 */
	private Integer modificacionCliente(ResponseBansefi response, ClientePersonaFisicaBean persona){
		Integer resultado = null;
		
		if(verificaRespuestaCliente(response)){
			resultado = response.getOTRPEMODIFINDVTRNO().getRTRNCD();
		}
		
		if (resultado != null && resultado == 1){
			try{
				TimeUnit.SECONDS.sleep(1);
			}catch(InterruptedException e){
			}
			this.modificacionPerfilTransaccionalPersonaBackend.ejecutarTRN(persona);
		}
				
		return resultado;
	}
	
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param personaModificada con los datos modificados
	 * @param personaConsultada con los datos de host
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITRPEMODIFINDVTRNI initPeticion(ClientePersonaFisicaBean personaModificada, ClientePersonaFisicaBean personaConsultada){
		Ejecutar.ITRPEMODIFINDVTRNI contexto = new Ejecutar.ITRPEMODIFINDVTRNI();
		Ejecutar.ITRPEMODIFINDVTRNI.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRPEMODIFINDVTRNI.STDTRNIPARMV();
		Ejecutar.ITRPEMODIFINDVTRNI.TRPEMODIFINDVEVTY cliente =
				new Ejecutar.ITRPEMODIFINDVTRNI.TRPEMODIFINDVEVTY();
		
		super.initialize(contexto);
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_PE_MODIF_INDV_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
										
		cliente = personaWrapper.wrappBeanModificacionCliente(personaModificada);
		super.initialize(cliente);
		
		// Se inicializan los indicadores del objeto IND_MODIF_CL_INDV_V
		
		cliente.setINDMODIFCLINDVV(this.inicializaIndicadoresModificacionCliente(personaModificada,personaConsultada));
		
		// Si el cliente no es trabajador independiente vaciamos los datos de fecha de alta y de 
		if (personaModificada.getAutonomo() ==null || !personaModificada.getAutonomo()){
			cliente.getPEALTAINDVOBJTRDV().setFECHAAUTNMOIN(ModificacionPersonaFisicaBackEnd.FECHA_INICIO);
			cliente.getPEALTAINDVOBJTRDV().setCODCNAEPERS("");
		}
		
		// Comprobamos si la fecha de alta de autonomo esta vacia, para ponerla a la fecha de inicio 11/11/1111
		if (personaModificada.getFechaAltaAutonomo() == null){
			cliente.getPEALTAINDVOBJTRDV().setFECHAAUTNMOIN(ModificacionPersonaFisicaBackEnd.FECHA_INICIO);
		}
		
		
		// Comprobamos si la fecha de fallecimiento esta vacia para ponerla a 31/12/9999
		if (personaModificada.getFechaFallecimiento() == null){
			cliente.getPEALTAINDVOBJTRDV().setFECHAFALLCRREPE(ModificacionPersonaFisicaBackEnd.FECHA_FIN);
		}
		
		// Incluimos los datos de nacimiento
		
		if (personaModificada.getMunicipioCatGeo().getCodigoPostal() != null && !("").equals(personaModificada.getMunicipioCatGeo().getCodigoPostal())){
			cliente.getPEALTAINDVOBJTRDV().setCODENTCOLECAG(personaModificada.getMunicipioCatGeo().getCodigoEntidadColectiva());
			cliente.getPEALTAINDVOBJTRDV().setCODENTSINGAG(personaModificada.getMunicipioCatGeo().getCodigoProvincia());
			cliente.getPEALTAINDVOBJTRDV().setCODPROVINCIAAG(personaModificada.getMunicipioCatGeo().getCodigoProvincia());
			cliente.getPEALTAINDVOBJTRDV().setCODMUNICIPIOAG(personaModificada.getMunicipioCatGeo().getCodigoMunicipio());
			cliente.getPEALTAINDVOBJTRDV().setCODPAISNACTO(personaModificada.getMunicipioCatGeo().getCodigoPais());
			cliente.getPEALTAINDVOBJTRDV().setNOMBLOCALIDADAG(personaModificada.getMunicipioCatGeo().getNombreLocalidad());
			cliente.getPEALTAINDVOBJTRDV().setNOMBPROVINCIAAG(personaModificada.getMunicipioCatGeo().getNombreProvincia());
			cliente.getPEALTAINDVOBJTRDV().setCODPOSTALAG(personaModificada.getMunicipioCatGeo().getCodigoPostal());
			
			if (personaModificada.getMunicipioCatGeo().getPrNumArGeo() !=null){
				cliente.getPEALTAINDVOBJTRDV().setNUMARGEOPROV(Integer.parseInt(personaModificada.getMunicipioCatGeo().getPrNumArGeo()));
			}
			
			if (personaModificada.getMunicipioCatGeo().getNumArGeo() !=null){
				cliente.getPEALTAINDVOBJTRDV().setNUMARGEOPAIS(Integer.parseInt(personaModificada.getMunicipioCatGeo().getNumArGeo()));
			}
		}else{
			cliente.getPEALTAINDVOBJTRDV().setNOMBLOCALIDADAG(personaModificada.getMunicipioCatGeo().getNombreMunicipio());
			cliente.getPEALTAINDVOBJTRDV().setNOMBPROVINCIAAG(personaModificada.getEstado().getNombreProvincia());
			cliente.getPEALTAINDVOBJTRDV().setCODPAISNACTO(personaModificada.getPais());
			
			// Localidad no Codificada
			cliente.getPEALTAINDVOBJTRDV().getAGLOCNOCODNUMV().setCODARGEO(ModificacionPersonaFisicaBackEnd.COD_ARGEO_NO_COD);
			cliente.getPEALTAINDVOBJTRDV().setCODPOSTALAG(ModificacionPersonaFisicaBackEnd.COD_POSTAL_NO_COD);
			cliente.getPEALTAINDVOBJTRDV().setCODPERSRLARGEO(ModificacionPersonaFisicaBackEnd.COD_PERS_RL_AR_GEO_NO_COD);
			cliente.getAGLOCALIDADNOCODV().setCODPROVINCIAAG(personaModificada.getEstado().getCodigoProvincia());
			cliente.getAGLOCALIDADNOCODV().setCODPAISAG(personaModificada.getPais());
			
			// Ponemos los indicadores de modificacion de localidad no codificada en funcion de si la localidad anterior estaba codificada
			cliente.getINDMODIFCLINDVV().getINDAGLOCALIDADNOCODINS().setSTDCHAR01("S");
			cliente.getINDMODIFCLINDVV().getINDPEPERSRLARGEOINS().setSTDCHAR01("S");			
		}
		
		// Si la localidad era no codificada se limpian los datos que habian
		if (personaConsultada.getMunicipioCatGeo() !=null && (personaConsultada.getMunicipioCatGeo().getCodigoPostal() == null || ("").equals(personaConsultada.getMunicipioCatGeo().getCodigoPostal()))){
			cliente.getINDMODIFCLINDVV().getINDPEPERSRLARGEOUPD().setSTDCHAR01("S");
		}
		
		
		// Incluimos los datos de domicilio
		cliente.getDRDMDPOBJTRDV().setCODNRBEEN(super.getEntidad());
		cliente.getDRDMDPOBJTRDV().setCODDIR("1");
		cliente.getDRDMDPOBJTRDV().setIDINTERNOPE(personaModificada.getIdInterna());
				
		cliente.getDRDMDPOBJTRDV().setNUMDIR(personaModificada.getDomicilios().get(0).getNumeroDireccion());
		
		cliente.getDRDMDPOBJTRDV().setCODVIA(personaModificada.getDomicilios().get(0).getTipoCalle());
		cliente.getDRDMDPOBJTRDV().setCODREGIMOCUP(personaModificada.getDomicilios().get(0).getRegimenOcupacion());
		cliente.getDRDMDPOBJTRDV().setNUMTLFNODOMIC(personaModificada.getDomicilios().get(0).getTelefono());
		cliente.getDRDMDPOBJTRDV().setCODPOSTALAG(personaModificada.getDomicilios().get(0).getDatosFinalesCatGeo().getCodigoPostal());
		cliente.getDRDMDPOBJTRDV().setNOMBLOCALIDADAG(personaModificada.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreMunicipio());
		cliente.getDRDMDPOBJTRDV().setCODPROVINCIAAG(personaModificada.getDomicilios().get(0).getDatosFinalesCatGeo().getCodigoProvincia());
		cliente.getDRDMDPOBJTRDV().setNOMBPROVINCIAAG(personaModificada.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreProvincia());
		cliente.getDRDMDPOBJTRDV().setCODCOMAUTNMAAG(personaModificada.getDomicilios().get(0).getDatosFinalesCatGeo().getCodigoComunidadAutonoma());
		cliente.getDRDMDPOBJTRDV().setCODPAISAG(personaModificada.getDomicilios().get(0).getDatosFinalesCatGeo().getCodigoPais());
		
		cliente.getDRDMDPOBJTRDV().getCODARGEODOMICILIOV().setCODARGEO(personaModificada.getDomicilios().get(0).getCodArGeo());
		if (personaModificada.getDomicilios().get(0).getDatosFinalesCatGeo().getNumArGeo() !=null){
			cliente.getDRDMDPOBJTRDV().getNUMARGEODOMICILIOV().setNUMARGEO(Integer.parseInt(personaModificada.getDomicilios().get(0).getDatosFinalesCatGeo().getNumArGeo()));
		}
		
		
		// Incluimos los datos recogidos en el apartado de masDatos
		COMPDOMICV nombreCalle = new COMPDOMICV();
		nombreCalle.setCODCOMPDOMIC(ConstantesFuncionales.CALLE);
		nombreCalle.setVALCOMPDOMICDRLEN(ConstantesFuncionales.CALLE_LONG);
		nombreCalle.setVALCOMPDOMICDR(personaModificada.getDomicilios().get(0).getNombreCalle());			
		cliente.getDRDMDPOBJTRDV().getCOMPDOMICV().add(nombreCalle);
	
		COMPDOMICV numExterior = new COMPDOMICV();
		numExterior.setCODCOMPDOMIC(ConstantesFuncionales.NUMERO_EXT);
		numExterior.setVALCOMPDOMICDRLEN(ConstantesFuncionales.NUMERO_EXT_LONG);
		numExterior.setVALCOMPDOMICDR(personaModificada.getDomicilios().get(0).getNumeroExterior());			
		cliente.getDRDMDPOBJTRDV().getCOMPDOMICV().add(numExterior);
	
		COMPDOMICV numInterior = new COMPDOMICV();
		numInterior.setCODCOMPDOMIC(ConstantesFuncionales.INTERIOR);
		numInterior.setVALCOMPDOMICDRLEN(ConstantesFuncionales.INTERIOR_LONG);
		numInterior.setVALCOMPDOMICDR(personaModificada.getDomicilios().get(0).getInterior());			
		cliente.getDRDMDPOBJTRDV().getCOMPDOMICV().add(numInterior);

		COMPDOMICV departamento = new COMPDOMICV();
		departamento.setCODCOMPDOMIC(ConstantesFuncionales.DEPARTAMENTO);
		departamento.setVALCOMPDOMICDRLEN(ConstantesFuncionales.DEPARTAMENTO_LONG);
		departamento.setVALCOMPDOMICDR(personaModificada.getDomicilios().get(0).getDepartamento());			
		cliente.getDRDMDPOBJTRDV().getCOMPDOMICV().add(departamento);
	
		COMPDOMICV colonia = new COMPDOMICV();
		colonia.setCODCOMPDOMIC(ConstantesFuncionales.COLONIA);
		colonia.setVALCOMPDOMICDRLEN(ConstantesFuncionales.COLONIA_LONG);
		colonia.setVALCOMPDOMICDR(personaModificada.getDomicilios().get(0).getColonia());			
		cliente.getDRDMDPOBJTRDV().getCOMPDOMICV().add(colonia);
	
		COMPDOMICV otrosDatos = new COMPDOMICV();
		otrosDatos.setCODCOMPDOMIC(ConstantesFuncionales.OTROS_DATOS);
		otrosDatos.setVALCOMPDOMICDRLEN(ConstantesFuncionales.OTROS_DATOS_LONG);
		otrosDatos.setVALCOMPDOMICDR(personaModificada.getDomicilios().get(0).getOtrosDatos());			
		cliente.getDRDMDPOBJTRDV().getCOMPDOMICV().add(otrosDatos);
	
		COMPDOMICV edificio = new COMPDOMICV();
		edificio.setCODCOMPDOMIC(ConstantesFuncionales.EDIFICIO);
		edificio.setVALCOMPDOMICDRLEN(ConstantesFuncionales.EDIFICIO_LONG);
		edificio.setVALCOMPDOMICDR(personaModificada.getDomicilios().get(0).getEdificio());			
		cliente.getDRDMDPOBJTRDV().getCOMPDOMICV().add(edificio);
	
		COMPDOMICV unidadHabitacional = new COMPDOMICV();
		unidadHabitacional.setCODCOMPDOMIC(ConstantesFuncionales.UNIDAD_HABITACIONAL);
		unidadHabitacional.setVALCOMPDOMICDRLEN(ConstantesFuncionales.UNIDAD_HABITACIONAL_LONG);
		unidadHabitacional.setVALCOMPDOMICDR(personaModificada.getDomicilios().get(0).getUnidadHabitacional());			
		cliente.getDRDMDPOBJTRDV().getCOMPDOMICV().add(unidadHabitacional);
	
		COMPDOMICV manzana = new COMPDOMICV();
		manzana.setCODCOMPDOMIC(ConstantesFuncionales.MANZANA);
		manzana.setVALCOMPDOMICDRLEN(ConstantesFuncionales.MANZANA_LONG);
		manzana.setVALCOMPDOMICDR(personaModificada.getDomicilios().get(0).getManzana());			
		cliente.getDRDMDPOBJTRDV().getCOMPDOMICV().add(manzana);
			
		// Incluimos tipo de direccion fiscal y habitual
		CODPERSRLDIRV tipoDireccionFiscal = new CODPERSRLDIRV();
		tipoDireccionFiscal.setCODPERSRLDIR("01");
		
		CODPERSRLDIRV tipoDireccionHabitual = new CODPERSRLDIRV();
		tipoDireccionHabitual.setCODPERSRLDIR("02");
		
		cliente.getDRDMDPOBJTRDV().getCODPERSRLDIRV().add(tipoDireccionFiscal);
		cliente.getDRDMDPOBJTRDV().getCODPERSRLDIRV().add(tipoDireccionHabitual);
				
		
		cliente.getPEALTAINDVOBJTRDV().setCODNRBEEN(super.getEntidad());
		cliente.getPEALTAINDVOBJTRDV().setIDINTERNOPE(personaModificada.getIdInterna());
		cliente.getPEALTAINDVOBJTRDV().setSITRESDNCPE("1");
		cliente.getPEALTAINDVOBJTRDV().setCODPE("F");
		cliente.getPEALTAINDVOBJTRDV().setINDLDEROPNNPE("N");
		cliente.getPEALTAINDVOBJTRDV().setINDAVISOPE("N");
		cliente.getPEALTAINDVOBJTRDV().setINDANTCNPE("N");
		cliente.getPEALTAINDVOBJTRDV().setINDPERTNGRPPE("N");
		cliente.getPEALTAINDVOBJTRDV().setINDACCESORSTRG("N");
		cliente.getPEALTAINDVOBJTRDV().setINDNODOCUM("N");
		// Incluimos la logica para el indicador de No Publicidad
		if (personaModificada.getNoPublicidad() !=null && personaModificada.getNoPublicidad()){
			cliente.getPEALTAINDVOBJTRDV().setINDPUBLCDPE("N");
		}else{
			cliente.getPEALTAINDVOBJTRDV().setINDPUBLCDPE("S");
		}		
		cliente.getPEALTAINDVOBJTRDV().setCODSITIRREG("");
		cliente.getPEALTAINDVOBJTRDV().setCODOFCNAALTA(personaModificada.getOficinaAlta());
		cliente.getPEALTAINDVOBJTRDV().setNOMBTARJETA(obtenerNombTarjeta(personaModificada));
		cliente.getPEALTAINDVOBJTRDV().setFECINICCNAE(0);
		cliente.getPEALTAINDVOBJTRDV().setFECINICSITECON(0);
		cliente.getPEALTAINDVOBJTRDV().setINDCLIENTE("S");
		cliente.getPEALTAINDVOBJTRDV().setINDOFERTAS("N");
		cliente.getPEALTAINDVOBJTRDV().setINDOTRRL("N");
		cliente.getPEALTAINDVOBJTRDV().setINDPERTNLSTNGR("N");
		cliente.getPEALTAINDVOBJTRDV().setINDCLIENTESQVCL("N");		
		cliente.getPEALTAINDVOBJTRDV().setINDCLIENTPRSPCTV("N");
		cliente.getPEALTAINDVOBJTRDV().setINDCLIENTRCHZD("N");
		cliente.getPEALTAINDVOBJTRDV().setINDSOCIOPE("N");
		cliente.getPEALTAINDVOBJTRDV().setIDINTERNOGTCOM(0);
		
		cliente.getPEALTAINDVOBJTRDV().setNOMB50(personaModificada.getNombreCompleto());
		cliente.getPEALTAINDVOBJTRDV().setINDMASGT("N");
		
		cliente.getPEALTAINDVOBJTRDV().setNUMDIRPRAL(personaModificada.getDomicilios().get(0).getNumeroDireccion());
		cliente.getPEALTAINDVOBJTRDV().setNUMDIRRGSTRO(0);
		cliente.getPEALTAINDVOBJTRDV().setINDMASDIR("S");
		cliente.getPEALTAINDVOBJTRDV().setINDNOESPAWOL("N");

		
		cliente.getPEALTAINDVOBJTRDV().setCODEMPLINDV("");
		cliente.getPEALTAINDVOBJTRDV().setFECHAINICDIR(0);
		cliente.getPEALTAINDVOBJTRDV().setINDEMPLDIN("N");
		cliente.getPEALTAINDVOBJTRDV().setINDCTLVIVIN("N");
		cliente.getPEALTAINDVOBJTRDV().setINDGESTOR("N");
		
		cliente.getPEALTAINDVOBJTRDV().setFECINICCPCDADIN(0);
		cliente.getPEALTAINDVOBJTRDV().setINDPPALIDPE("S");
		cliente.getPEALTAINDVOBJTRDV().setNUMACTPROFIN(0);
		cliente.getPEALTAINDVOBJTRDV().setCODNIVINGRINDV("");
		cliente.getPEALTAINDVOBJTRDV().setFECANTACTPROIN(0);
		cliente.getPEALTAINDVOBJTRDV().setINDDEDICACTPROF("");
		cliente.getPEALTAINDVOBJTRDV().setINDDURACTPROF("");
		cliente.getPEALTAINDVOBJTRDV().setINDCTDADACTPROF("");
		cliente.getPEALTAINDVOBJTRDV().setINDTMPOACTPROF("");
		cliente.getPEALTAINDVOBJTRDV().setCARGOCUACPROFIN("");
		cliente.getPEALTAINDVOBJTRDV().setCODCNTRTOINDV("");
		cliente.getPEALTAINDVOBJTRDV().setFECHAINICRL(0);
		cliente.getPEALTAINDVOBJTRDV().setFECHAFINRL(0);
		cliente.getPEALTAINDVOBJTRDV().setNUMARGEOPAIS(0);
		cliente.getPEALTAINDVOBJTRDV().setNUMARGEOPROV(0);
		
		// Si el cliente es menor o tiene capacidad legal informada distinta a capacidad Plena se incluye anotacion de necesidad de representante Legal
		// en caso de modificacion de fecha de nacimiento o de la capacidad de actuar
		if (!this.compare(personaModificada.getFechaNacimiento(),personaConsultada.getFechaNacimiento()) || 
				!this.compare(personaModificada.getSolvenciaMoral(),personaConsultada.getSolvenciaMoral())){		
			if(ConstantesFuncionales.isMenorEdad(personaModificada.getFechaNacimiento()) ||  
					(!ConstantesFuncionales.CAPACIDAD_LEGAL_PLENA.equals(personaModificada.getSolvenciaMoral())  && !"".equals(personaModificada.getSolvenciaMoral().trim()))){
				cliente.getINDAVISOREPRES().setSTDCHAR01("A");
			}else{
				cliente.getINDAVISOREPRES().setSTDCHAR01("B");
			}
		}
			
		// Si la fecha de fallecimiento viene informada y no estaba informada se incluye la anotacino de cliente fallecido
		// y se elimina la anotacion en caso contrario
		if (personaModificada.getFechaFallecimiento() != null && personaConsultada.getFechaFallecimiento() ==null){
			cliente.getINDAVISOFALLEC().setSTDCHAR01("A");
		}else if(personaModificada.getFechaFallecimiento() == null && personaConsultada.getFechaFallecimiento() !=null){
			cliente.getINDAVISOFALLEC().setSTDCHAR01("B");
		}
		
		// Si la situacion economica de la empresa es en QUIEBRA, SUSPENSION DE PAGOS O QUIEBRA FRAUDULENTA y no estaba en esos estados
		// se incluye anotacion y se elimina en caso contrario
		if (((ConstantesFuncionales.SIT_ECON_QUIEBRA).equals(personaModificada.getSituacionEconomica()) 
						|| (ConstantesFuncionales.SIT_ECON_SUSP_PAGO).equals(personaModificada.getSituacionEconomica()) 
						|| (ConstantesFuncionales.SIT_ECON_QUIEBRA_FRAUD).equals(personaModificada.getSituacionEconomica())) &&
				(!(ConstantesFuncionales.SIT_ECON_QUIEBRA).equals(personaConsultada.getSituacionEconomica()) 
						&& !(ConstantesFuncionales.SIT_ECON_SUSP_PAGO).equals(personaConsultada.getSituacionEconomica()) 
						&& !(ConstantesFuncionales.SIT_ECON_QUIEBRA_FRAUD).equals(personaConsultada.getSituacionEconomica()))){
			cliente.getINDAVISOSITECO().setSTDCHAR01("A");
		}else if ((!(ConstantesFuncionales.SIT_ECON_QUIEBRA).equals(personaModificada.getSituacionEconomica()) 
					&& !(ConstantesFuncionales.SIT_ECON_SUSP_PAGO).equals(personaModificada.getSituacionEconomica()) 
					&& !(ConstantesFuncionales.SIT_ECON_QUIEBRA_FRAUD).equals(personaModificada.getSituacionEconomica())) &&
				((ConstantesFuncionales.SIT_ECON_QUIEBRA).equals(personaConsultada.getSituacionEconomica()) 
					|| (ConstantesFuncionales.SIT_ECON_SUSP_PAGO).equals(personaConsultada.getSituacionEconomica()) 
					|| (ConstantesFuncionales.SIT_ECON_QUIEBRA_FRAUD).equals(personaConsultada.getSituacionEconomica()))){
			cliente.getINDAVISOSITECO().setSTDCHAR01("B");
		}
		
		contexto.setTRPEMODIFINDVEVTY(cliente);
		contexto.setSTDTRNIPARMV(contextoEntrada);
		
		return contexto;
	}
	
	
	private INDMODIFCLINDVV inicializaIndicadoresModificacionCliente(ClientePersonaFisicaBean personaModificada, ClientePersonaFisicaBean personaConsultada) {
		
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
		
		
		// Como estamos modificando al cliente de entrada activamos los indicadores de insertar en CL_CLIENT, actualizar PE_PERS y actualizar PE_INDV
		indicadores.getINDPEPERSUPD().setSTDCHAR01("S");
		indicadores.getINDPEINDVUPD().setSTDCHAR01("S");
		
		// Se validan los cambios sobre EP_PE_DP: //
		
		// Se valida si la idExterna ha sido modificada
		if (!this.compare(personaModificada.getNumIdentificacion(),personaConsultada.getNumIdentificacion())){

			// Si la idExterna ha sido eliminada activamos indicadores de actualizar persona y eliminar/actualizar idExterna
			if (personaModificada.getNumIdentificacion() == null || ("").equals(personaModificada)){
				indicadores.getINDPEIDEXTUPDDEL().setSTDCHAR01("S");
			}		
			
			// Si la idExterna estaba vacia pero ahora contiene datos activamos indicadores de actualizar persona e insertar la idExterna
			else if (personaConsultada.getNumIdentificacion() == null || ("").equals(personaConsultada.getNumIdentificacion())){
				indicadores.getINDPEIDEXTINS().setSTDCHAR01("S");
			}
			
			// Si ninguna de las idExterna estan vacias se activan los indicadores de actualizar personas, actualizar idExterna
			else {
				//indicadores.getINDPEPERSUPD().setSTDCHAR01("S");
				indicadores.getINDPEIDEXTUPD().setSTDCHAR01("S");
				
				// Si el tipo de identificacion ha sido modificada, se activa el indicador de insertar idExterna
				if (!this.compare(personaModificada.getTipoIdentificacion(),personaConsultada.getTipoIdentificacion())){
					indicadores.getINDPEIDEXTINS().setSTDCHAR01("S");
				}
			}
		}
			
		// Se validan cambios sobre PE_CL_INDV_DS: //
			
		// Si el cno ha sido modificado se activa el indicador de actualizar individuo
		if (!this.compare(personaModificada.getCno(), personaConsultada.getCno())){
			indicadores.getINDPEINDVUPD().setSTDCHAR01("S");
		}
		
		// Si el cnae ha sido modificado se activa el indicador de actualizar persona
		if (!this.compare(personaModificada.getCnae(), personaConsultada.getCnae())){
			
			// Si el cnae estaba vacio pero ahora tiene datos activamos el indicador de insertar otro CNAE
			if (personaConsultada.getCnae() == null || ("").equals(personaConsultada.getCnae())){
				indicadores.getINDPEOTROCNAEINS().setSTDCHAR01("S");
			}
			
			// En caso de que el cnae tuvise datos
			else{
				// Si el cnae ahora esta vacio indicador de actualizar otro CNAE
				if (personaModificada.getCnae() == null || ("").equals(personaModificada.getCnae())){
					indicadores.getINDPEOTROCNAEUPD().setSTDCHAR01("S");
				}
				// En caso de que el cnae ya tuviese datos y los siga teniendo se activa el indicador de actualizar otro CNAE y de insertar otro CNAE
				else{
					indicadores.getINDPEOTROCNAEUPD().setSTDCHAR01("S");
					indicadores.getINDPEOTROCNAEINS().setSTDCHAR01("S");
				}
			}
			
		}
		
		// Si la solvenciaMoral(CapacidadLgl) ha sido modificado se activa el indicador de actualizar individuo
		if (!this.compare(personaModificada.getSolvenciaMoral(), personaConsultada.getSolvenciaMoral())){
			
			// Si la solvenciaMoral(CapacidadLgl) estaba vacio pero ahora tiene datos activamos el indicador de insertar solvenciaMoral(CapacidadLgl)
			if (personaConsultada.getSolvenciaMoral() == null || ("").equals(personaConsultada.getSolvenciaMoral())){
				indicadores.getINDPECPCDADLGLINDVINS().setSTDCHAR01("S");
			}
			
			// En caso de que la solvenciaMoral(CapacidadLgl) tuvise datos
			else{
				// Si la solvenciaMoral(CapacidadLgl) ahora esta vacio indicador de actualizar/eliminar solvenciaMoral(CapacidadLgl)
				if (personaModificada.getSolvenciaMoral() == null || ("").equals(personaModificada.getSolvenciaMoral())){
					indicadores.getINDPECPCDADLGLINDVUPDDEL().setSTDCHAR01("S");
				}
				// En caso de que la solvenciaMoral(CapacidadLgl) ya tuviese datos y los siga teniendo se activa el indicador de actualizar solvenciaMoral(CapacidadLgl) y de insertar solvenciaMoral(CapacidadLgl)
				else{
					indicadores.getINDPECPCDADLGLINDVUPDDEL().setSTDCHAR01("S");
					indicadores.getINDPECPCDADLGLINDVINS().setSTDCHAR01("S");
				}
			}
			
		}
		// Si la solvenciaMoral(CapacidadLgl) no ha sido modificado se verifica si ha cambiado el valor de las fechas de inicio y fin
		else{
			// Si se ha modificado la fecha de fin de la solvenciaMoral(CapacidadLgl) activamos el indicador de actualizacion de la solvenciaMoral(CapacidadLgl)
			if (!(this.compare(personaModificada.getSolvenciaMoralHasta(),personaConsultada.getSolvenciaMoralHasta()))){						
				indicadores.getINDPECPCDADLGLINDVUPD().setSTDCHAR01("S");
			}
			// Si se ha modificado la fecha de inicio de la solvenciaMoral(CapacidadLgl) activamos el indicador de actualizacion de la solvenciaMoral(CapacidadLgl)
			if (!(this.compare(personaModificada.getSolvenciaMoralDesde(),personaConsultada.getSolvenciaMoralDesde()))){
				indicadores.getINDPECPCDADLGLINDVUPD().setSTDCHAR01("S");
			}
		}
		
		// Si la situacionEconomica ha sido modificado se activa el indicador de actualizar persona
		if (!this.compare(personaModificada.getSolvenciaMoral(), personaConsultada.getSolvenciaMoral())){
			
			// Si la situacionEconomica estaba vacio pero ahora tiene datos activamos el indicador de insertar situacionEconomica
			if (personaConsultada.getSolvenciaMoral() == null || ("").equals(personaConsultada.getSolvenciaMoral())){
				indicadores.getINDPESITECONINS().setSTDCHAR01("S");
			}
			
			// En caso de que la situacionEconomica tuvise datos
			else{
				// Si la situacionEconomica ahora esta vacio indicador de actualizar situacionEconomica
				if (personaModificada.getSolvenciaMoral() == null || ("").equals(personaModificada.getSolvenciaMoral())){
					indicadores.getINDPESITECONUPD().setSTDCHAR01("S");
				}
				// En caso de que la solvenciaMoral(CapacidadLgl) ya tuviese datos y los siga teniendo se activa el indicador de actualizar situacionEconomica y de insertar situacionEconomica
				else{
					indicadores.getINDPESITECONUPD().setSTDCHAR01("S");
					indicadores.getINDPESITECONINS().setSTDCHAR01("S");
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
	
	/**
	 * Función para obtener el valor del campo NombTarjeta.
	 * 
	 * @param personaModificada Cliente indicado a dar de alta
	 * @return La respuesta del servicio web.
	 */
	private String obtenerNombTarjeta(ClientePersonaFisicaBean persona){
		
		String nombre = persona.getNombre();
		String ape2 = persona.getApeMaterno();
		
		String nombTarjeta = persona.getApePaterno();
		
		if (ape2 !=null){
			if (nombTarjeta !=null && !("").equals(nombTarjeta)){
				nombTarjeta += ModificacionPersonaFisicaBackEnd.GUION + ape2;
			}else{
				nombTarjeta = ape2;
			}
		}
		
		if (nombre !=null){
			if (nombTarjeta !=null && !("").equals(nombTarjeta)){
				nombTarjeta += ModificacionPersonaFisicaBackEnd.GUION + nombre;
			}else{
				nombTarjeta += nombre;
			}
		}
		
		if (nombTarjeta !=null && nombTarjeta.length() > 26){
			nombTarjeta = nombTarjeta.substring(0, 26);
		}
		
		return nombTarjeta;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRPEMODIFINDVTRNI contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ModificacionPersonaFisicaServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de modificacion de persona fisica", e);
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
		if(response!= null && response.getOTRPEMODIFINDVTRNO() != null &&
				response.getOTRPEMODIFINDVTRNO().getTRPEMODIFINDVEVTZ() != null &&
				response.getOTRPEMODIFINDVTRNO().getTRPEMODIFINDVEVTZ().getPEPERSP() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
}
