package mx.babel.bansefi.banksystem.personas.backend;

import java.math.BigDecimal;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaMoralBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionperfiltransaccionalmoral.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionperfiltransaccionalmoral.Ejecutar.IPEMODIORGPERFTRANTR;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionperfiltransaccionalmoral.Ejecutar.IPEMODIORGPERFTRANTR.PEMODIORGPERFTRANEVT;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionperfiltransaccionalmoral.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionperfiltransaccionalmoral.ModificacionPerfilTransaccionalMoralServicio;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionperfiltransaccionalmoral.ResponseBansefi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModificacionPerfilTransaccionalMoralBackEnd extends BackEndBean{
	
	private static final long serialVersionUID = -4211460465908999056L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	
	public Integer ejecutarTRN(ClientePersonaMoralBean personaMoral) {
		Ejecutar.IPEMODIORGPERFTRANTR contexto = initPeticion(personaMoral);
		EjecutarResult respuesta = ejecutarWS(contexto);
		super.verificaRespuesta(respuesta);
		if(verificaResponseBansefi(respuesta)){
			return altaPerfilTransaccionalMoral(respuesta.getResponseBansefi());
		}
		return null;
	}

	private Integer altaPerfilTransaccionalMoral(ResponseBansefi response) {
		Integer resultado = null;		
		if(verificaRespuestaCliente(response)){
			return response.getOPEMODIORGPERFTRANTR().getRTRNCD();
			
		}
		return resultado;
	}

	private boolean verificaRespuestaCliente(ResponseBansefi response) {
		Boolean noNulo = false;
		if(response!= null && response.getOPEMODIORGPERFTRANTR() != null){
			noNulo = true;
		}
		return noNulo;
	}

	
	private boolean verificaResponseBansefi(EjecutarResult respuesta) {
		Boolean noNulo = false;
		if(respuesta != null && respuesta.getResponseBansefi() != null){
			noNulo = true;
		}
		return noNulo;
	}

	private EjecutarResult ejecutarWS(IPEMODIORGPERFTRANTR contexto) {
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ModificacionPerfilTransaccionalMoralServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de modificacion de perfil"
					+ "transaccional de clientes.", e);
		}
		return respuesta;
	}

	private IPEMODIORGPERFTRANTR initPeticion(ClientePersonaMoralBean personaMoral) {
		IPEMODIORGPERFTRANTR contexto = new Ejecutar.IPEMODIORGPERFTRANTR();
		Ejecutar.IPEMODIORGPERFTRANTR.STDTRNIPARMV contextoEntrada = new IPEMODIORGPERFTRANTR.STDTRNIPARMV();
		PEMODIORGPERFTRANEVT perfTrans = new IPEMODIORGPERFTRANTR.PEMODIORGPERFTRANEVT();
		
		super.initialize(contexto);
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_PE_MODI_ORG_PERF_TRAN_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());

		super.initialize(perfTrans);
		
		// Preguntas de relacion con cargo publico
		
		perfTrans.getPEPERSRLFPUBLCP().setCODNRBEEN(super.getEntidad());
		perfTrans.getPEPERSRLFPUBLCP().setIDINTERNOPE(personaMoral.getIdInterna());
	
		perfTrans.getPEORGFUNCPUBLCDS().setINDTXT1(this.deBooleanATexto(personaMoral.getUsoCuenta().getFuncionarioPublico()));
		if (personaMoral.getUsoCuenta().getFuncionarioPublico() !=null && personaMoral.getUsoCuenta().getFuncionarioPublico()) {
			perfTrans.getPEORGFUNCPUBLCDS().setTXTRESPUESTA1(personaMoral.getUsoCuenta().getFuncionarioPublicoCargo());
		}
		perfTrans.getPEORGFUNCPUBLCDS().setINDTXT2(this.deBooleanATexto(personaMoral.getUsoCuenta().getAsociadoFuncionarioPublico()));
		if (personaMoral.getUsoCuenta().getAsociadoFuncionarioPublico() !=null && personaMoral.getUsoCuenta().getAsociadoFuncionarioPublico()) {
			perfTrans.getPEORGFUNCPUBLCDS().setTXTRESPUESTA2(personaMoral.getUsoCuenta().getAsociadoFuncionarioPublicoCargo());
			perfTrans.getPEORGFUNCPUBLCDS().setNOMBRERESPUESTA2(personaMoral.getUsoCuenta().getAsociadoFuncionarioPublicoNombre());
		}
		
		perfTrans.getPEPERFILTRANSCLE().setCODNRBEEN(super.getEntidad());
		perfTrans.getPEPERFILTRANSCLE().setIDINTERNOPE(personaMoral.getIdInterna());
		if (personaMoral.getEsClienteRiesgo() != null && personaMoral.getEsClienteRiesgo()){
			perfTrans.getPEPERFILTRANSCLE().setIDNIVELRSGO("01");
			perfTrans.getPEPERFILTRANSCLE().setIDPERFILTRANSCL("AR");
		}else{
			perfTrans.getPEPERFILTRANSCLE().setIDNIVELRSGO("03");
			perfTrans.getPEPERFILTRANSCLE().setIDPERFILTRANSCL("BR");
		}
		
		// Datos de transaccionalidad
		
		perfTrans.getPEPERSTRANESTIME().setCODNRBEEN(super.getEntidad());
		if (personaMoral.getTransaccionalidad() != null) {
			perfTrans.getPEPERSTRANESTIME().setIDINTERNOPE(personaMoral.getIdInterna());
			if (personaMoral.getTransaccionalidad().getNumDepositos() != null) {
				perfTrans.getPEPERSTRANESTIME().setNUMDEPOSITOS(personaMoral.getTransaccionalidad().getNumDepositos());
			}
			if (personaMoral.getTransaccionalidad().getMontoDepositos() != null) {
				perfTrans.getPEPERSTRANESTIME().setIMPDEPOSITOS(personaMoral.getTransaccionalidad().getMontoDepositos());
			}
			if (personaMoral.getTransaccionalidad().getNumRetiros() != null) {
				perfTrans.getPEPERSTRANESTIME().setNUMRETIROS(personaMoral.getTransaccionalidad().getNumRetiros());
			}
			if (personaMoral.getTransaccionalidad().getMontoRetiros() != null) {
				perfTrans.getPEPERSTRANESTIME().setIMPRETIROS(personaMoral.getTransaccionalidad().getMontoRetiros());
			}
			
		}
		
		/**
		 * Antiguas de transaccionalidad (van todas a N por si acaso)
		 */
		
		// Transaccionalidad
		perfTrans.getPEPERSFNTEINGRE().setCODNRBEEN(super.getEntidad());
		perfTrans.getPEPERSFNTEINGRE().setIDINTERNOPE(personaMoral.getIdInterna());
		perfTrans.getPEPERSFNTEINGRE().setINVERSIONES("N");
		perfTrans.getPEPERSFNTEINGRE().setCOMISIONES("N");
		perfTrans.getPEPERSFNTEINGRE().setHONORARIOS("N");
		perfTrans.getPEPERSFNTEINGRE().setARRENDAMIENTO("N");
		perfTrans.getPEPERSFNTEINGRE().setSUELDOS("N");
		perfTrans.getPEPERSFNTEINGRE().setHERENCIA("N");
		perfTrans.getPEPERSFNTEINGRE().setBECA("N");
		perfTrans.getPEPERSFNTEINGRE().setPENSION("N");
		perfTrans.getPEPERSFNTEINGRE().setPREMIOS("N");
		perfTrans.getPEPERSFNTEINGRE().setACTCOMERCIAL("N");
		perfTrans.getPEPERSFNTEINGRE().setRECTERCEROS("N");
		perfTrans.getPEPERSFNTEINGRE().setPRESTAMOS("N");
		
		if (personaMoral.getTransaccionalidad() != null) {
			
			for (String id : personaMoral.getTransaccionalidad().getTransaccionalidad()) {
				
				if (id.equals("Otros")){					
					perfTrans.getPEPERSFNTEINGRE().setOTROS("S");
					perfTrans.getPEPERSFNTEINGRE().setOTROSESP(personaMoral.getTransaccionalidad().getProvieneOtros());
				}else{					
					switch (Integer.parseInt(id)) {
					case 0: 
						perfTrans.getPEPERSFNTEINGRE().setVENTAS("S");
						break;
					case 1: 
						perfTrans.getPEPERSFNTEINGRE().setCOMISIONES("S");
						break;
					case 2: 
						perfTrans.getPEPERSFNTEINGRE().setPRESTAMOS("S");
						break;
					case 3:
						perfTrans.getPEPERSFNTEINGRE().setDONATIVOS("S");
						break;
					case 4:
						perfTrans.getPEPERSFNTEINGRE().setHONORARIOS("S");
						break;						
					case 5: 
						perfTrans.getPEPERSFNTEINGRE().setINVERSIONES("S");
						break;
					case 6: 
						perfTrans.getPEPERSFNTEINGRE().setARRENDAMIENTO("S");
						break;
					default:
						break;
					}
					
				}
				
			}
					
			perfTrans.getPEPERSFNTEINGRE().setMENINGRESOS(BigDecimal.valueOf(0));
			perfTrans.getPEPERSFNTEINGRE().setANUINGRESOS(BigDecimal.valueOf(0));
			
			if (personaMoral.getTransaccionalidad().getMontoAnualIngresos() != null) {
				perfTrans.getPEPERSFNTEINGRE().setANUINGRESOS(personaMoral.getTransaccionalidad().getMontoAnualIngresos());
			}
			
		}
		
		// Uso de Cuentas
		perfTrans.getPEPERSUSOCTAE().setCODNRBEEN(super.getEntidad());
		perfTrans.getPEPERSUSOCTAE().setIDINTERNOPE(personaMoral.getIdInterna());
		perfTrans.getPEPERSUSOCTAE().setADMINGRGTO("N");
		perfTrans.getPEPERSUSOCTAE().setCONCDISPFOND("N");
		perfTrans.getPEPERSUSOCTAE().setPAGPROVCOMI("N");
		perfTrans.getPEPERSUSOCTAE().setPAGSERV("N");
		perfTrans.getPEPERSUSOCTAE().setCTAPNTEINVER("N");
		perfTrans.getPEPERSUSOCTAE().setPAGCRED("N");
		perfTrans.getPEPERSUSOCTAE().setINVERSIONES("N");
		perfTrans.getPEPERSUSOCTAE().setFIDEICOMISOS("N");
		perfTrans.getPEPERSUSOCTAE().setDEPNOMINA("N");	

		if (personaMoral.getUsoCuenta() != null && personaMoral.getUsoCuenta().getUsos() !=null) {			
			
			for (String id : personaMoral.getUsoCuenta().getUsos()) {
								
				if (id.equals("Otros")){					
					perfTrans.getPEPERSUSOCTAE().setOTROS("S");
					perfTrans.getPEPERSUSOCTAE().setOTROSESP(personaMoral.getUsoCuenta().getOtrosUsos());
				}else{
					switch (Integer.parseInt(id)) {
					case 0: 
						perfTrans.getPEPERSUSOCTAE().setADMINGRGTO("S");
						break;
					case 1: 
						perfTrans.getPEPERSUSOCTAE().setCONCDISPFOND("S");
						break;
					case 2: 
						perfTrans.getPEPERSUSOCTAE().setPAGPROVCOMI("S");
						break;					
					case 3:
						perfTrans.getPEPERSUSOCTAE().setCTAPNTEINVER("S");
						break;						
					case 4: 
						perfTrans.getPEPERSUSOCTAE().setPAGCRED("S");
						break;
					case 5: 
						perfTrans.getPEPERSUSOCTAE().setINVERSIONES("S");
						break;
					case 6:
						perfTrans.getPEPERSUSOCTAE().setFIDEICOMISOS("S");
						break;
					case 7:
						perfTrans.getPEPERSUSOCTAE().setDEPNOMINA("S");
						break;
					default:
						break;
					}
				}				
			}
		}
			
		// Operaciones
		perfTrans.getPEPERSUSOCTAE().setDEPOSITOS("N");
		perfTrans.getPEPERSUSOCTAE().setCOBROGIROS("N");
		perfTrans.getPEPERSUSOCTAE().setRETIROS("N");
		perfTrans.getPEPERSUSOCTAE().setTRANSFERENCIAS("N");
		
		if (personaMoral.getUsoCuenta() != null && personaMoral.getUsoCuenta().getOperaciones() !=null) {
			for (String id1 : personaMoral.getUsoCuenta().getOperaciones()) {
				switch (Integer.parseInt(id1)) {
				case 0: 
					perfTrans.getPEPERSUSOCTAE().setDEPOSITOS("S");
					break;
				case 1: 
					perfTrans.getPEPERSUSOCTAE().setCOBROGIROS("S");
					break;
				case 2: 
					perfTrans.getPEPERSUSOCTAE().setRETIROS("S");
					break;
				case 3:
					perfTrans.getPEPERSUSOCTAE().setTRANSFERENCIAS("S");
					break;
				default:
					break;
				}
			}
		}
		
		perfTrans.getPEPERSUSOCTAE().setPORCPROPIOS(Integer.parseInt(personaMoral.getUsoCuenta().getManejoRecursosPropios()));
		perfTrans.getPEPERSUSOCTAE().setPORCTERCEROS(Integer.parseInt(personaMoral.getUsoCuenta().getManejoRecursosTerceros()));
			
		
		// Recursos de cuenta
		perfTrans.getPEPERSRCSOSCTAE().setCODNRBEEN(super.getEntidad());
		perfTrans.getPEPERSRCSOSCTAE().setIDINTERNOPE(personaMoral.getIdInterna());
		perfTrans.getPEPERSRCSOSCTAE().setAPORTCAPITAL("N");
		perfTrans.getPEPERSRCSOSCTAE().setBENEFICIOS("N");
		perfTrans.getPEPERSRCSOSCTAE().setADJJUDICIALES("N");
		perfTrans.getPEPERSRCSOSCTAE().setINGRCORRIENTE("N");
		perfTrans.getPEPERSRCSOSCTAE().setPRESTAMOS("N");
		perfTrans.getPEPERSRCSOSCTAE().setVTAACTIVO("N");
		perfTrans.getPEPERSRCSOSCTAE().setRECTERCEROS("N");
		
		if (personaMoral.getRecursos() != null ) {
			for (String id2 : personaMoral.getRecursos()) {
				
				if (id2.equals("Otros")){					
					perfTrans.getPEPERSRCSOSCTAE().setOTROS("S");
					perfTrans.getPEPERSRCSOSCTAE().setOTROSESP(personaMoral.getOtrosRecursos());
				}else{
				
					switch (Integer.parseInt(id2)) {
					case 0: 
						perfTrans.getPEPERSRCSOSCTAE().setAPORTCAPITAL("S");
						break;
					case 1: 
						perfTrans.getPEPERSRCSOSCTAE().setBENEFICIOS("S");
						break;
					case 2: 
						perfTrans.getPEPERSRCSOSCTAE().setADJJUDICIALES("S");
						break;						
					case 3:
						perfTrans.getPEPERSRCSOSCTAE().setINGRCORRIENTE("S");
						break;
					case 4:
						perfTrans.getPEPERSRCSOSCTAE().setPRESTAMOS("S");
						break;				
					case 5: 
						perfTrans.getPEPERSRCSOSCTAE().setVTAACTIVO("S");
						break;
					case 6: 
						perfTrans.getPEPERSRCSOSCTAE().setRECTERCEROS("S");
						break;
					default:
						break;
					}
				}
			}
		}				
		
		/**
		 * Datos de Riesgo
		 */
		
		// Datos de Grupo/Filial
		
		if (personaMoral.getGrupoFilialBean() != null) {
			
			// Opciones
			perfTrans.getPEINFFINANPZAV().setPLAZAOPER01(personaMoral.getGrupoFilialBean().getOpcion1());
			perfTrans.getPEINFFINANPZAV().setPLAZAOPER02(personaMoral.getGrupoFilialBean().getOpcion2());		
			perfTrans.getPEINFFINANPZAV().setPLAZAOPER03(personaMoral.getGrupoFilialBean().getOpcion3());
			perfTrans.getPEINFFINANPZAV().setPLAZAOPER04(personaMoral.getGrupoFilialBean().getOpcion4());
			perfTrans.getPEINFFINANPZAV().setPLAZAOPER05(personaMoral.getGrupoFilialBean().getOpcion5());
			perfTrans.getPEINFFINANPZAV().setPLAZAOPER06(personaMoral.getGrupoFilialBean().getOpcion6());
			
			// Datos de Grupo/Filial
			perfTrans.getPEPERSRLGRPEMPT().setCODNRBEEN(super.getEntidad());
			perfTrans.getPEPERSRLGRPEMPT().setIDINTERNOPE(personaMoral.getIdInterna());
			perfTrans.getPEPERSRLGRPEMPT().setINDGPOFILIA(this.deBooleanATexto(personaMoral.getGrupoFilialBean().isPerteneceGrupoFiliar()));
			perfTrans.getPEPERSRLGRPEMPT().setNOMBGR(personaMoral.getGrupoFilialBean().getNombre());
			perfTrans.getPEPERSRLGRPEMPT().setRFC(personaMoral.getGrupoFilialBean().getRfc());
			perfTrans.getPEPERSRLGRPEMPT().setCODAMBTOORG(personaMoral.getGrupoFilialBean().getCoberturaAmbito());
			perfTrans.getPEPERSRLGRPEMPT().setDESCRPROCTO(personaMoral.getGrupoFilialBean().getProducto());
			perfTrans.getPEPERSRLGRPEMPT().setCODPAISRES(personaMoral.getGrupoFilialBean().getPaisResidencia());
			perfTrans.getPEPERSRLGRPEMPT().setCODPAISNAL(personaMoral.getGrupoFilialBean().getPais());
			if (personaMoral.getGrupoFilialBean().getParticipacion() != null){
				perfTrans.getPEPERSRLGRPEMPT().setPORCGPOFILIAL(personaMoral.getGrupoFilialBean().getParticipacion());
			}			
			if (personaMoral.getGrupoFilialBean().getEmpleados() != null) {
				perfTrans.getPEPERSRLGRPEMPT().setNUMEMPLEADO(personaMoral.getGrupoFilialBean().getEmpleados());
			}
			if (personaMoral.getGrupoFilialBean().getOficinas() != null) {
				perfTrans.getPEPERSRLGRPEMPT().setNUMOFCNA(personaMoral.getGrupoFilialBean().getOficinas());
			}
			if (personaMoral.getGrupoFilialBean().getVentasAnuales() != null) {
				perfTrans.getPEPERSRLGRPEMPT().setIMPVTASANUAL(personaMoral.getGrupoFilialBean().getVentasAnuales());
			}
			if (personaMoral.getGrupoFilialBean().getTelefono() != null) {
				perfTrans.getPEPERSRLGRPEMPT().setNUMTLFNODOMIC(personaMoral.getGrupoFilialBean().getTelefono());
			}
			if (personaMoral.getGrupoFilialBean().getEmail() != null) {
				perfTrans.getPEPERSRLGRPEMPT().setNOMBCORREO(personaMoral.getGrupoFilialBean().getEmail());
			}
			
			perfTrans.getPEGRPEMPREADICV().setINDGPOFILIA(this.deBooleanATexto(personaMoral.getGrupoFilialBean().isPerteneceGrupoFiliar()));
			perfTrans.getPEGRPEMPREADICV().setNOMBGR(personaMoral.getGrupoFilialBean().getNombre());
			perfTrans.getPEGRPEMPREADICV().setRFC(personaMoral.getGrupoFilialBean().getRfc());
			perfTrans.getPEGRPEMPREADICV().setDESCRPROCTO(personaMoral.getGrupoFilialBean().getProducto());
			
			if (personaMoral.getGrupoFilialBean().getParticipacion() != null){
				perfTrans.getPEGRPEMPREADICV().setPORCGPOFILIAL(personaMoral.getGrupoFilialBean().getParticipacion());
			}			
			if (personaMoral.getGrupoFilialBean().getEmpleados() != null) {
				perfTrans.getPEGRPEMPREADICV().setNUMEMPLEADO(personaMoral.getGrupoFilialBean().getEmpleados());
			}
			if (personaMoral.getGrupoFilialBean().getOficinas() != null) {
				perfTrans.getPEGRPEMPREADICV().setNUMOFCNA(personaMoral.getGrupoFilialBean().getOficinas());
			}
			if (personaMoral.getGrupoFilialBean().getVentasAnuales() != null) {
				perfTrans.getPEGRPEMPREADICV().setIMPVTASANUAL(personaMoral.getGrupoFilialBean().getVentasAnuales());
			}
			if (personaMoral.getGrupoFilialBean().getTelefono() != null) {
				perfTrans.getPEGRPEMPREADICV().setNUMTLFNODOMIC(personaMoral.getGrupoFilialBean().getTelefono());
			}
			if (personaMoral.getGrupoFilialBean().getEmail() != null) {
				perfTrans.getPEGRPEMPREADICV().setNOMBCORREO(personaMoral.getGrupoFilialBean().getEmail());
			}
		}
		
		// Donativos
		
		// Frecuencia
		perfTrans.getPEORGDNTVSV().setINDFRCESPCA("N");
		perfTrans.getPEORGDNTVSV().setINDFRCANUAL("N");
		perfTrans.getPEORGDNTVSV().setINDFRCMENSUAL("N");
		perfTrans.getPEORGDNTVSV().setINDFRCSEMANAL("N");
		perfTrans.getPEORGDNTVSV().setINDFRCDIARIA("N");
		if (personaMoral.getDonativosBean() != null ) {
			for (String frecuencia : personaMoral.getDonativosBean().getFrecuencia()) {
				switch (Integer.parseInt(frecuencia)) {
					case 0: 
						perfTrans.getPEORGDNTVSV().setINDFRCESPCA("S");
						break;
					case 1: 
						perfTrans.getPEORGDNTVSV().setINDFRCANUAL("S");
						break;
					case 2: 
						perfTrans.getPEORGDNTVSV().setINDFRCMENSUAL("S");
						break;
					case 3:
						perfTrans.getPEORGDNTVSV().setINDFRCSEMANAL("S");
						break;
					case 4:
						perfTrans.getPEORGDNTVSV().setINDFRCDIARIA("S");
						break;
					default:
						break;
				}
			}
			
		}
		
		//Forma de entrega
		perfTrans.getPEORGDNTVSV().setINDFENEFTVO("N");
		perfTrans.getPEORGDNTVSV().setINDFENCHQ("N");
		perfTrans.getPEORGDNTVSV().setINDFENTDC("N");
		perfTrans.getPEORGDNTVSV().setINDFENTRANS("N");
		perfTrans.getPEORGDNTVSV().setINDFENESPC("N");
		if (personaMoral.getDonativosBean() != null ) {
			for (String entrega : personaMoral.getDonativosBean().getEntrega()) {
				switch (Integer.parseInt(entrega)) {
					case 0: 
						perfTrans.getPEORGDNTVSV().setINDFENEFTVO("S");
						break;
					case 1: 
						perfTrans.getPEORGDNTVSV().setINDFENCHQ("S");
						break;
					case 2: 
						perfTrans.getPEORGDNTVSV().setINDFENTDC("S");
						break;
					case 3:
						perfTrans.getPEORGDNTVSV().setINDFENTRANS("S");
						break;
					case 4:
						perfTrans.getPEORGDNTVSV().setINDFENESPC("S");
						break;
					default:
						break;
				}
			}
		}		
		
		// Tipos de personas
		perfTrans.getPEORGDNTVSV().setINDTPOGNRAL("N");
		if (personaMoral.getDonativosBean() != null ) {
			if (personaMoral.getDonativosBean().getPersonas() !=null && personaMoral.getDonativosBean().getPersonas().contains("0")) {
				perfTrans.getPEORGDNTVSV().setINDTPOGNRAL("S");
			}
			
			if (personaMoral.getDonativosBean().getPersonasEspecificas() != null && !personaMoral.getDonativosBean().getPersonasEspecificas().isEmpty()) {
				perfTrans.getPEORGDNTVSV().setINDTPOPERS("S");
				perfTrans.getPEORGDNTVSV().setNOMB50(personaMoral.getDonativosBean().getPersonasEspecificas());
			} else {
				perfTrans.getPEORGDNTVSV().setINDTPOPERS("N");
				perfTrans.getPEORGDNTVSV().setNOMB50("");
			}
			
			// Balance
			perfTrans.getPEORGFINANCV().setIMPACTFIJO(personaMoral.getDonativosBean().getActivoFijo());
			perfTrans.getPEORGFINANCV().setIMPACTCRCLT(personaMoral.getDonativosBean().getActivoCirculante());
			perfTrans.getPEORGFINANCV().setIMPPASCTPLZ(personaMoral.getDonativosBean().getPasivoCortoPlazo());
			perfTrans.getPEORGFINANCV().setIMPPASLGPLZ(personaMoral.getDonativosBean().getPasivoLargoPlazo());
			perfTrans.getPEORGFINANCV().setIMPCAPCTBLE(personaMoral.getDonativosBean().getCapitalContable());
			perfTrans.getPEORGFINANCV().setCODNUMRCOMONEDA(personaMoral.getDonativosBean().getMoneda());
			perfTrans.getPEORGFINANCV().setCODNUMRCOMONEDA1(personaMoral.getDonativosBean().getIndicarMoneda());
			
			//Resultados
			perfTrans.getPEORGFINANCV().setIMPINGANUAL(personaMoral.getDonativosBean().getIngresoAnual());
			perfTrans.getPEORGFINANCV().setIMPCOSTVTA(personaMoral.getDonativosBean().getCostoVentas());
			perfTrans.getPEORGFINANCV().setIMPGASTO(personaMoral.getDonativosBean().getGastos());
			perfTrans.getPEORGFINANCV().setIMPUTILIDAD(personaMoral.getDonativosBean().getUtilidad());
			perfTrans.getPEORGFINANCV().setNUMEMPLEADO(personaMoral.getDonativosBean().getNumEmpleados());
			if (personaMoral.getDonativosBean().getNumSucursales() != null) {
				perfTrans.getPEORGFINANCV().setNUMSUCURSAL(personaMoral.getDonativosBean().getNumSucursales());
			}
			
			
			// Comercio Exterior
			if (personaMoral.getDonativosBean().getIngresoExp() != null) {
				perfTrans.getPEORGFINANCV().setIMPINGEXP(personaMoral.getDonativosBean().getIngresoExp());
			}
			if (personaMoral.getDonativosBean().getPagoExp() != null) {
				perfTrans.getPEORGFINANCV().setIMPPAGOEXP(personaMoral.getDonativosBean().getPagoExp());
			}
			if (personaMoral.getDonativosBean().getUsDolares() != null) {
				perfTrans.getPEORGFINANCV().setIMPDLLSMNDA(personaMoral.getDonativosBean().getUsDolares());
			}
			
		}
		
		contexto.setPEMODIORGPERFTRANEVT(perfTrans);
		contexto.setSTDTRNIPARMV(contextoEntrada);
		
		return contexto;
	}

	private String deBooleanATexto(Boolean bool) {
		if (bool !=null && bool) {
			return "S";
		}
		
		return "N";
	}

}
