package mx.babel.bansefi.banksystem.personas.backend;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.AltaPerfilTransaccionalPersonaBackEnd;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaFisicaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.personas.webservices.altapersona.AltaPersonaServicio;
import mx.babel.bansefi.banksystem.personas.webservices.altapersona.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.altapersona.Ejecutar.ITRPEALTAINDVTRNI.TRPEALTAINDVEVTY.DRDMDPOBJTRDV.CODPERSRLDIRV;
import mx.babel.bansefi.banksystem.personas.webservices.altapersona.Ejecutar.ITRPEALTAINDVTRNI.TRPEALTAINDVEVTY.DRDMDPOBJTRDV.COMPDOMICV;
import mx.babel.bansefi.banksystem.personas.webservices.altapersona.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.altapersona.ResponseBansefi;
import mx.babel.bansefi.banksystem.personas.wrappers.PersonasWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para el alta de un cliente en especifico
 * 
 * @author javier.martinnino
 *
 */
@Component
public class AltaPersonaBackEnd extends BackEndBean{
	
	private static final long serialVersionUID = -4401141330672127248L;
	private static final int FECHA_INICIO = 11111111;
	private static final int FECHA_FIN = 99991231;
	private static final String COD_POSTAL_NO_COD = "99412";
	private static final String COD_ARGEO_NO_COD = "11";
	private static final String COD_PERS_RL_AR_GEO_NO_COD = "01";	
	private static final String GUION = "-";
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	@Autowired
	private AltaPerfilTransaccionalPersonaBackEnd altaPerfilTransaccionalPersonaBackend;
	
	@Autowired
	PersonasWrapper personaWrapper;
	
	/**
	 * Función encargada del alta de un cliente
	 * por medio de servicios web.
	 * 
	 * @param persona persona a dar de alta como cliente
	 * @return idInterna del nuevo cliente
	 */
	public Integer ejecutarTRN(ClientePersonaFisicaBean persona){
		Ejecutar.ITRPEALTAINDVTRNI contexto = initPeticion(persona);
		EjecutarResult respuesta = ejecutarWS(contexto);
		super.verificaRespuesta(respuesta);
		if(verificaResponseBansefi(respuesta)){
			return altaCliente(respuesta.getResponseBansefi(),persona);
		}
		return null;
	}
	
	/**
	 * Función que construye la lista de cuentas a partir de la respuesta al servidor.
	 * 
	 * @param response
	 * @param persona
	 * @return
	 */
	private Integer altaCliente(ResponseBansefi response, ClientePersonaFisicaBean persona){
		Integer resultado = null;
		if(verificaRespuestaCliente(response)){
			resultado = response.getOTRPEALTAINDVTRNO().getTRPEALTAINDVEVTZ().getPEPERSP().getIDINTERNOPE();
		}
		
		if (resultado != null){
			persona.setIdInterna(resultado);
			try{
				TimeUnit.SECONDS.sleep(1);
			}catch(InterruptedException e){
			}
			this.altaPerfilTransaccionalPersonaBackend.ejecutarTRN(persona);
		}		
		
		return resultado;
	}
	
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param persona persona a dar de alta en la aplicacion
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITRPEALTAINDVTRNI initPeticion(ClientePersonaFisicaBean persona){
		Ejecutar.ITRPEALTAINDVTRNI contexto = new Ejecutar.ITRPEALTAINDVTRNI();
		Ejecutar.ITRPEALTAINDVTRNI.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRPEALTAINDVTRNI.STDTRNIPARMV();
		Ejecutar.ITRPEALTAINDVTRNI.TRPEALTAINDVEVTY cliente =
				new Ejecutar.ITRPEALTAINDVTRNI.TRPEALTAINDVEVTY();
		
		IntegerToDateConverter intDateConverter = new IntegerToDateConverter();
		
		super.initialize(contexto);
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_PE_ALTA_INDV_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());		
				
		cliente = personaWrapper.wrappBeanAltaCliente(persona);
		super.initialize(cliente);
		
		// Incluimos los datos de nacimiento
		
		if (persona.getMunicipioCatGeo().getCodigoPostal() != null && !("").equals(persona.getMunicipioCatGeo().getCodigoPostal())){
			cliente.getPEALTAINDVOBJTRDV().setCODENTCOLECAG(persona.getMunicipioCatGeo().getCodigoEntidadColectiva());
			cliente.getPEALTAINDVOBJTRDV().setCODENTSINGAG(persona.getMunicipioCatGeo().getCodigoProvincia());
			cliente.getPEALTAINDVOBJTRDV().setCODPROVINCIAAG(persona.getMunicipioCatGeo().getCodigoProvincia());
			cliente.getPEALTAINDVOBJTRDV().setCODMUNICIPIOAG(persona.getMunicipioCatGeo().getCodigoMunicipio());
			cliente.getPEALTAINDVOBJTRDV().setCODPAISNACTO(persona.getMunicipioCatGeo().getCodigoPais());
			cliente.getPEALTAINDVOBJTRDV().setNOMBLOCALIDADAG(persona.getMunicipioCatGeo().getNombreLocalidad());
			cliente.getPEALTAINDVOBJTRDV().setNOMBPROVINCIAAG(persona.getMunicipioCatGeo().getNombreProvincia());
			cliente.getPEALTAINDVOBJTRDV().setCODPOSTALAG(persona.getMunicipioCatGeo().getCodigoPostal());
			
			if (persona.getMunicipioCatGeo().getPrNumArGeo() !=null){
				cliente.getPEALTAINDVOBJTRDV().setNUMARGEOPROV(Integer.parseInt(persona.getMunicipioCatGeo().getPrNumArGeo()));
			}
			
			if (persona.getMunicipioCatGeo().getNumArGeo() !=null){
				cliente.getPEALTAINDVOBJTRDV().setNUMARGEOPAIS(Integer.parseInt(persona.getMunicipioCatGeo().getNumArGeo()));
			}
		}else{
			cliente.getPEALTAINDVOBJTRDV().setNOMBLOCALIDADAG(persona.getMunicipioCatGeo().getNombreMunicipio());
			cliente.getPEALTAINDVOBJTRDV().setNOMBPROVINCIAAG(persona.getEstado().getNombreProvincia());
			cliente.getPEALTAINDVOBJTRDV().setCODPAISNACTO(persona.getPais());
			
			// Localidad no Codificada
			cliente.getPEALTAINDVOBJTRDV().getAGLOCNOCODNUMV().setCODARGEO(AltaPersonaBackEnd.COD_ARGEO_NO_COD);
			cliente.getPEALTAINDVOBJTRDV().setCODPOSTALAG(AltaPersonaBackEnd.COD_POSTAL_NO_COD);
			cliente.getPEALTAINDVOBJTRDV().setCODPERSRLARGEO(AltaPersonaBackEnd.COD_PERS_RL_AR_GEO_NO_COD);
			cliente.getAGLOCALIDADNOCODV().setCODPROVINCIAAG(persona.getEstado().getCodigoProvincia());
			cliente.getAGLOCALIDADNOCODV().setCODPAISAG(persona.getPais());
			
		}
		
		// Incluimos los datos de domicilio
		cliente.getDRDMDPOBJTRDV().setCODNRBEEN(super.getEntidad());
		cliente.getDRDMDPOBJTRDV().setCODDIR("1");
		
		cliente.getDRDMDPOBJTRDV().setCODVIA(persona.getDomicilios().get(0).getTipoCalle());
		cliente.getDRDMDPOBJTRDV().setCODREGIMOCUP(persona.getDomicilios().get(0).getRegimenOcupacion());
		cliente.getDRDMDPOBJTRDV().setNUMTLFNODOMIC(persona.getDomicilios().get(0).getTelefono());
		cliente.getDRDMDPOBJTRDV().setCODPOSTALAG(persona.getDomicilios().get(0).getDatosFinalesCatGeo().getCodigoPostal());
		cliente.getDRDMDPOBJTRDV().setNOMBLOCALIDADAG(persona.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreMunicipio());
		cliente.getDRDMDPOBJTRDV().setCODPROVINCIAAG(persona.getDomicilios().get(0).getDatosFinalesCatGeo().getCodigoProvincia());
		cliente.getDRDMDPOBJTRDV().setNOMBPROVINCIAAG(persona.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreProvincia());
		cliente.getDRDMDPOBJTRDV().setCODCOMAUTNMAAG(persona.getDomicilios().get(0).getDatosFinalesCatGeo().getCodigoComunidadAutonoma());
		cliente.getDRDMDPOBJTRDV().setCODPAISAG(persona.getDomicilios().get(0).getDatosFinalesCatGeo().getCodigoPais());
		
		cliente.getDRDMDPOBJTRDV().getCODARGEODOMICILIOV().setCODARGEO(persona.getDomicilios().get(0).getCodArGeo());
		if (persona.getDomicilios().get(0).getDatosFinalesCatGeo().getNumArGeo() !=null){
			cliente.getDRDMDPOBJTRDV().getNUMARGEODOMICILIOV().setNUMARGEO(Integer.parseInt(persona.getDomicilios().get(0).getDatosFinalesCatGeo().getNumArGeo()));
		}
		
		
		// Incluimos los datos recogidos en el apartado de masDatos
		if (persona.getDomicilios().get(0).getNombreCalle() !=null && !("").equals(persona.getDomicilios().get(0).getNombreCalle())){
			COMPDOMICV nombreCalle = new COMPDOMICV();
			nombreCalle.setCODCOMPDOMIC(ConstantesFuncionales.CALLE);
			nombreCalle.setVALCOMPDOMICDRLEN(ConstantesFuncionales.CALLE_LONG);
			nombreCalle.setVALCOMPDOMICDR(persona.getDomicilios().get(0).getNombreCalle());			
			cliente.getDRDMDPOBJTRDV().getCOMPDOMICV().add(nombreCalle);
		}
		
		if (persona.getDomicilios().get(0).getNumeroExterior() !=null && !("").equals(persona.getDomicilios().get(0).getNumeroExterior())){
			COMPDOMICV numExterior = new COMPDOMICV();
			numExterior.setCODCOMPDOMIC(ConstantesFuncionales.NUMERO_EXT);
			numExterior.setVALCOMPDOMICDRLEN(ConstantesFuncionales.NUMERO_EXT_LONG);
			numExterior.setVALCOMPDOMICDR(persona.getDomicilios().get(0).getNumeroExterior());			
			cliente.getDRDMDPOBJTRDV().getCOMPDOMICV().add(numExterior);
		}
		
		if (persona.getDomicilios().get(0).getInterior() !=null && !("").equals(persona.getDomicilios().get(0).getInterior())){
			COMPDOMICV numInterior = new COMPDOMICV();
			numInterior.setCODCOMPDOMIC(ConstantesFuncionales.INTERIOR);
			numInterior.setVALCOMPDOMICDRLEN(ConstantesFuncionales.INTERIOR_LONG);
			numInterior.setVALCOMPDOMICDR(persona.getDomicilios().get(0).getInterior());			
			cliente.getDRDMDPOBJTRDV().getCOMPDOMICV().add(numInterior);
		}
		
		if (persona.getDomicilios().get(0).getDepartamento() !=null && !("").equals(persona.getDomicilios().get(0).getDepartamento())){
			COMPDOMICV departamento = new COMPDOMICV();
			departamento.setCODCOMPDOMIC(ConstantesFuncionales.DEPARTAMENTO);
			departamento.setVALCOMPDOMICDRLEN(ConstantesFuncionales.DEPARTAMENTO_LONG);
			departamento.setVALCOMPDOMICDR(persona.getDomicilios().get(0).getDepartamento());			
			cliente.getDRDMDPOBJTRDV().getCOMPDOMICV().add(departamento);
		}
		
		if (persona.getDomicilios().get(0).getColonia() !=null && !("").equals(persona.getDomicilios().get(0).getColonia())){
			COMPDOMICV colonia = new COMPDOMICV();
			colonia.setCODCOMPDOMIC(ConstantesFuncionales.COLONIA);
			colonia.setVALCOMPDOMICDRLEN(ConstantesFuncionales.COLONIA_LONG);
			colonia.setVALCOMPDOMICDR(persona.getDomicilios().get(0).getColonia());			
			cliente.getDRDMDPOBJTRDV().getCOMPDOMICV().add(colonia);
		}
		
		if (persona.getDomicilios().get(0).getOtrosDatos() !=null && !("").equals(persona.getDomicilios().get(0).getOtrosDatos())){
			COMPDOMICV otrosDatos = new COMPDOMICV();
			otrosDatos.setCODCOMPDOMIC(ConstantesFuncionales.OTROS_DATOS);
			otrosDatos.setVALCOMPDOMICDRLEN(ConstantesFuncionales.OTROS_DATOS_LONG);
			otrosDatos.setVALCOMPDOMICDR(persona.getDomicilios().get(0).getOtrosDatos());			
			cliente.getDRDMDPOBJTRDV().getCOMPDOMICV().add(otrosDatos);
		}
		
		if (persona.getDomicilios().get(0).getEdificio() !=null && !("").equals(persona.getDomicilios().get(0).getEdificio())){
			COMPDOMICV edificio = new COMPDOMICV();
			edificio.setCODCOMPDOMIC(ConstantesFuncionales.EDIFICIO);
			edificio.setVALCOMPDOMICDRLEN(ConstantesFuncionales.EDIFICIO_LONG);
			edificio.setVALCOMPDOMICDR(persona.getDomicilios().get(0).getEdificio());			
			cliente.getDRDMDPOBJTRDV().getCOMPDOMICV().add(edificio);
		}
		
		if (persona.getDomicilios().get(0).getUnidadHabitacional() !=null && !("").equals(persona.getDomicilios().get(0).getUnidadHabitacional())){
			COMPDOMICV unidadHabitacional = new COMPDOMICV();
			unidadHabitacional.setCODCOMPDOMIC(ConstantesFuncionales.UNIDAD_HABITACIONAL);
			unidadHabitacional.setVALCOMPDOMICDRLEN(ConstantesFuncionales.UNIDAD_HABITACIONAL_LONG);
			unidadHabitacional.setVALCOMPDOMICDR(persona.getDomicilios().get(0).getUnidadHabitacional());			
			cliente.getDRDMDPOBJTRDV().getCOMPDOMICV().add(unidadHabitacional);
		}
		
		if (persona.getDomicilios().get(0).getManzana() !=null && !("").equals(persona.getDomicilios().get(0).getManzana())){
			COMPDOMICV manzana = new COMPDOMICV();
			manzana.setCODCOMPDOMIC(ConstantesFuncionales.MANZANA);
			manzana.setVALCOMPDOMICDRLEN(ConstantesFuncionales.MANZANA_LONG);
			manzana.setVALCOMPDOMICDR(persona.getDomicilios().get(0).getManzana());			
			cliente.getDRDMDPOBJTRDV().getCOMPDOMICV().add(manzana);
		}
		
		
		// Incluimos tipo de direccion fiscal y habitual
		CODPERSRLDIRV tipoDireccionFiscal = new CODPERSRLDIRV();
		tipoDireccionFiscal.setCODPERSRLDIR("01");
		
		CODPERSRLDIRV tipoDireccionHabitual = new CODPERSRLDIRV();
		tipoDireccionHabitual.setCODPERSRLDIR("02");
		
		cliente.getDRDMDPOBJTRDV().getCODPERSRLDIRV().add(tipoDireccionFiscal);
		cliente.getDRDMDPOBJTRDV().getCODPERSRLDIRV().add(tipoDireccionHabitual);
				
		
		cliente.getPEALTAINDVOBJTRDV().setCODNRBEEN(super.getEntidad());
		cliente.getPEALTAINDVOBJTRDV().setIDINTERNOPE(0);
		
		// Si el cliente no es trabajador independiente vaciamos los datos de fecha de alta y de 
		if (persona.getAutonomo() ==null || !persona.getAutonomo()){
			cliente.getPEALTAINDVOBJTRDV().setFECHAAUTNMOIN(AltaPersonaBackEnd.FECHA_INICIO);
			cliente.getPEALTAINDVOBJTRDV().setCODCNAEPERS("");
		}
		
		// Comprobamos si la fecha de alta de autonomo esta vacia, para ponerla a la fecha de inicio 11/11/1111
		if (persona.getFechaAltaAutonomo() == null){
			cliente.getPEALTAINDVOBJTRDV().setFECHAAUTNMOIN(AltaPersonaBackEnd.FECHA_INICIO);
		}
		
		// Comprobamos si la fecha de fallecimiento esta vacia para ponerla a 31/12/9999
		if (persona.getFechaFallecimiento() == null){
			cliente.getPEALTAINDVOBJTRDV().setFECHAFALLCRREPE(AltaPersonaBackEnd.FECHA_FIN);
		}
		cliente.getPEALTAINDVOBJTRDV().setFECHAALTAPER(intDateConverter.convertFrom(new Date()));
		cliente.getPEALTAINDVOBJTRDV().setSITRESDNCPE("1");
		cliente.getPEALTAINDVOBJTRDV().setCODPE("F");
		cliente.getPEALTAINDVOBJTRDV().setINDLDEROPNNPE("N");
		cliente.getPEALTAINDVOBJTRDV().setINDAVISOPE("N");
		cliente.getPEALTAINDVOBJTRDV().setINDANTCNPE("N");
		cliente.getPEALTAINDVOBJTRDV().setINDPERTNGRPPE("N");
		cliente.getPEALTAINDVOBJTRDV().setINDACCESORSTRG("N");
		cliente.getPEALTAINDVOBJTRDV().setINDNODOCUM("N");
		// Incluimos la logica para el indicador de No Publicidad
		if (persona.getNoPublicidad() !=null && persona.getNoPublicidad()){
			cliente.getPEALTAINDVOBJTRDV().setINDPUBLCDPE("N");
		}else{
			cliente.getPEALTAINDVOBJTRDV().setINDPUBLCDPE("S");
		}		
		cliente.getPEALTAINDVOBJTRDV().setCODSITIRREG("");
		cliente.getPEALTAINDVOBJTRDV().setCODOFCNAALTA(super.getSucursal());
		cliente.getPEALTAINDVOBJTRDV().setNOMBTARJETA(obtenerNombTarjeta(persona));
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
		
		cliente.getPEALTAINDVOBJTRDV().setNOMB50(persona.getNombreCompleto());
		cliente.getPEALTAINDVOBJTRDV().setINDMASGT("N");
		
		cliente.getPEALTAINDVOBJTRDV().setNUMDIRPRAL(0);
		cliente.getPEALTAINDVOBJTRDV().setNUMDIRRGSTRO(0);
		cliente.getPEALTAINDVOBJTRDV().setINDMASDIR("N");
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
		
		// Se inicializan los indicadores de anotaciones
		
		// Si el cliente es menor o tiene capacidad legal informada distinta a capacidad Plena se incluye anotacion de necesidad de representante Legal
		if(ConstantesFuncionales.isMenorEdad(persona.getFechaNacimiento()) ||  
				(!ConstantesFuncionales.CAPACIDAD_LEGAL_PLENA.equals(persona.getSolvenciaMoral())  && !"".equals(persona.getSolvenciaMoral().trim()))){
			cliente.getINDAVISOREPRES().setSTDCHAR01("A");
		}
		
		// Si la situacion economica de la empresa es en QUIEBRA, SUSPENSION DE PAGOS O QUIEBRA FRAUDULENTA
		if (persona.getSituacionEconomica() !=null && 
				(persona.getSituacionEconomica().equals(ConstantesFuncionales.SIT_ECON_QUIEBRA) 
						|| persona.getSituacionEconomica().equals(ConstantesFuncionales.SIT_ECON_SUSP_PAGO) 
						|| persona.getSituacionEconomica().equals(ConstantesFuncionales.SIT_ECON_QUIEBRA_FRAUD))){
			cliente.getINDAVISOSITECO().setSTDCHAR01("A");
		}
		
		super.initialize(cliente);
				
		contexto.setTRPEALTAINDVEVTY(cliente);
		contexto.setSTDTRNIPARMV(contextoEntrada);
		
		return contexto;
	}
	
	/**
	 * Función para obtener el valor del campo NombTarjeta.
	 * 
	 * @param persona Cliente indicado a dar de alta
	 * @return La respuesta del servicio web.
	 */
	private String obtenerNombTarjeta(ClientePersonaFisicaBean persona){
		
		String nombre = persona.getNombre();
		String ape2 = persona.getApeMaterno();
		
		String nombTarjeta = persona.getApePaterno();
		
		if (ape2 !=null){
			if (nombTarjeta !=null && !("").equals(nombTarjeta)){
				nombTarjeta += AltaPersonaBackEnd.GUION + ape2;
			}else{
				nombTarjeta = ape2;
			}
		}
		
		if (nombre !=null){
			if (nombTarjeta !=null && !("").equals(nombTarjeta)){
				nombTarjeta += AltaPersonaBackEnd.GUION + nombre;
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
	private EjecutarResult ejecutarWS(Ejecutar.ITRPEALTAINDVTRNI contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					AltaPersonaServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de alta de persona", e);
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
		if(response!= null && response.getOTRPEALTAINDVTRNO() != null &&
				response.getOTRPEALTAINDVTRNO().getTRPEALTAINDVEVTZ() != null &&
				response.getOTRPEALTAINDVTRNO().getTRPEALTAINDVEVTZ().getPEPERSP() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
}
