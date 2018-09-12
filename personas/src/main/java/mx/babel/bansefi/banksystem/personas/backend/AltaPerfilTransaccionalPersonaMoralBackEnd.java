package mx.babel.bansefi.banksystem.personas.backend;

import java.math.BigDecimal;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaMoralBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.personas.webservices.altaperfiltransaccionalmoral.AltaPerfilTransaccionalMoralServicio;
import mx.babel.bansefi.banksystem.personas.webservices.altaperfiltransaccionalmoral.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.altaperfiltransaccionalmoral.Ejecutar.IPEALTAORGPERFTRANTR;
import mx.babel.bansefi.banksystem.personas.webservices.altaperfiltransaccionalmoral.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.altaperfiltransaccionalmoral.ResponseBansefi;
import mx.babel.bansefi.banksystem.personas.wrappers.PersonaMoralWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd para la ejecución del servicio de Alta de perfil Transaccional de
 * Persona Moral.
 * 
 */
@Component
public class AltaPerfilTransaccionalPersonaMoralBackEnd extends BackEndBean{

	private static final long serialVersionUID = 7808665758994962025L;
	
	@Autowired
	PersonaMoralWrapper personaMoralWrapper;
	@Autowired
	ServicioWebUtils servicioWebUtils;

	
	public Integer ejecutarTRN(ClientePersonaMoralBean personaMoral) {
		Ejecutar.IPEALTAORGPERFTRANTR contexto = initPeticion(personaMoral);
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
			return response.getOPEALTAORGPERFTRANTR().getRTRNCD();
			
		}
		return resultado;
	}

	private boolean verificaRespuestaCliente(ResponseBansefi response) {
		Boolean noNulo = false;
		if(response!= null && response.getOPEALTAORGPERFTRANTR() != null){
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

	private EjecutarResult ejecutarWS(IPEALTAORGPERFTRANTR contexto) {
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					AltaPerfilTransaccionalMoralServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de alta de perfil"
					+ "transaccional de clientes.", e);
		}
		return respuesta;
	}

	private Ejecutar.IPEALTAORGPERFTRANTR initPeticion(
			ClientePersonaMoralBean personaMoral) {
		Ejecutar.IPEALTAORGPERFTRANTR contexto = new Ejecutar.IPEALTAORGPERFTRANTR();
		Ejecutar.IPEALTAORGPERFTRANTR.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.IPEALTAORGPERFTRANTR.STDTRNIPARMV();
		Ejecutar.IPEALTAORGPERFTRANTR.PEALTAORGPERFTRANEVT perfTrans = 
				new Ejecutar.IPEALTAORGPERFTRANTR.PEALTAORGPERFTRANEVT();
		
		super.initialize(contexto);
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_PE_MODI_ORG_PERF_TRAN_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());

		super.initialize(perfTrans);
		
		perfTrans.getPEPERSRLFPUBLCP().setCODNRBEEN(super.getEntidad());
		perfTrans.getPEPERSRLFPUBLCP().setIDINTERNOPE(personaMoral.getIdInterna());
		
	
		perfTrans.getPEORGFUNCPUBLCDS().setINDTXT1(this.deBooleanATexto(personaMoral.getUsoCuenta().getFuncionarioPublico()));
		if (personaMoral.getUsoCuenta().getFuncionarioPublico()) {
			perfTrans.getPEORGFUNCPUBLCDS().setTXTRESPUESTA1(personaMoral.getUsoCuenta().getFuncionarioPublicoCargo());
		}
		perfTrans.getPEORGFUNCPUBLCDS().setINDTXT2(this.deBooleanATexto(personaMoral.getUsoCuenta().getAsociadoFuncionarioPublico()));
		if (personaMoral.getUsoCuenta().getAsociadoFuncionarioPublico()) {
			perfTrans.getPEORGFUNCPUBLCDS().setTXTRESPUESTA2(personaMoral.getUsoCuenta().getAsociadoFuncionarioPublicoCargo());
			perfTrans.getPEORGFUNCPUBLCDS().setNOMBRERESPUESTA2(personaMoral.getUsoCuenta().getAsociadoFuncionarioPublicoNombre());
		}
		
		perfTrans.getPEPERFILTRANSCLE().setCODNRBEEN(super.getEntidad());
		perfTrans.getPEPERFILTRANSCLE().setIDINTERNOPE(personaMoral.getIdInterna());
		
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
		perfTrans.getPEPERSFNTEINGRE().setSUELDOS("N");
		perfTrans.getPEPERSFNTEINGRE().setHERENCIA("N");
		perfTrans.getPEPERSFNTEINGRE().setBECA("N");
		perfTrans.getPEPERSFNTEINGRE().setPENSION("N");
		perfTrans.getPEPERSFNTEINGRE().setPREMIOS("N");
		perfTrans.getPEPERSFNTEINGRE().setACTCOMERCIAL("N");
		perfTrans.getPEPERSFNTEINGRE().setRECTERCEROS("N");
		perfTrans.getPEPERSFNTEINGRE().setPRESTAMOS("N");
	
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
		

		perfTrans.getPEPERSUSOCTAE().setDEPOSITOS("N");
		perfTrans.getPEPERSUSOCTAE().setCOBROGIROS("N");
		perfTrans.getPEPERSUSOCTAE().setRETIROS("N");
		perfTrans.getPEPERSUSOCTAE().setTRANSFERENCIAS("N");
		if (personaMoral.getUsoCuenta().getUsos() != null) {
			
			
			for (String id : personaMoral.getUsoCuenta().getUsos()) {
				try {
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
				} catch (Exception e){
					// DO NOTHING
				}
				
			}
			if (personaMoral.getUsoCuenta().getOperaciones() != null) {
				for (String id1 : personaMoral.getUsoCuenta().getOperaciones()) {
					try {
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
					} catch (Exception e) {
						// DO NOTHING
					}
				}
			}
			
			if (personaMoral.getUsoCuenta().getOtrosUsos() != null) {
				perfTrans.getPEPERSUSOCTAE().setOTROS(personaMoral.getUsoCuenta().getOtrosUsos());
			}
			
			if (personaMoral.getUsoCuenta().getManejoRecursosPropios() != null) {
				perfTrans.getPEPERSUSOCTAE().setPORCPROPIOS(Integer.parseInt(personaMoral.getUsoCuenta().getManejoRecursosPropios()));
			} 
			if (personaMoral.getUsoCuenta().getManejoRecursosTerceros() != null) {
				perfTrans.getPEPERSUSOCTAE().setPORCTERCEROS(Integer.parseInt(personaMoral.getUsoCuenta().getManejoRecursosTerceros()));
			}			
		}
		
		perfTrans.getPEPERSRCSOSCTAE().setIDINTERNOPE(personaMoral.getIdInterna());
		perfTrans.getPEPERSRCSOSCTAE().setAPORTCAPITAL("N");
		perfTrans.getPEPERSRCSOSCTAE().setBENEFICIOS("N");
		perfTrans.getPEPERSRCSOSCTAE().setADJJUDICIALES("N");
		perfTrans.getPEPERSRCSOSCTAE().setINGRCORRIENTE("N");
		perfTrans.getPEPERSRCSOSCTAE().setPRESTAMOS("N");
		perfTrans.getPEPERSRCSOSCTAE().setVTAACTIVO("N");
		perfTrans.getPEPERSRCSOSCTAE().setRECTERCEROS("N");
		
		for (String id2 : personaMoral.getRecursos()) {
			try {
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
			} catch (Exception e) {
				// DO NOTHING
			}		
			
		}
		
		if (personaMoral.getOtrosRecursos() != null) {
			perfTrans.getPEPERSRCSOSCTAE().setOTROS(personaMoral.getOtrosRecursos());
		}
			
			
		
		perfTrans.getPEPERSFNTEINGRE().setIDINTERNOPE(0);
		perfTrans.getPEPERSFNTEINGRE().setVENTAS("N");
		perfTrans.getPEPERSFNTEINGRE().setCOMISIONES("N");
		perfTrans.getPEPERSFNTEINGRE().setPRESTAMOS("N");
		perfTrans.getPEPERSFNTEINGRE().setDONATIVOS("N");
		perfTrans.getPEPERSFNTEINGRE().setHONORARIOS("N");
		perfTrans.getPEPERSFNTEINGRE().setINVERSIONES("N");
		perfTrans.getPEPERSFNTEINGRE().setARRENDAMIENTO("N");
		
		if (personaMoral.getTransaccionalidad().getTransaccionalidad() != null) {
			
			for (String id : personaMoral.getTransaccionalidad().getTransaccionalidad()) {
				try {
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
				} catch (Exception e) {
					// DO NOTHING
				}
			}
			
			if (personaMoral.getTransaccionalidad().getProvieneOtros() != null) {
				perfTrans.getPEPERSFNTEINGRE().setOTROS(personaMoral.getTransaccionalidad().getProvieneOtros());
			}
			
			if (personaMoral.getTransaccionalidad().getMontoAnualIngresos() != null) {
				perfTrans.getPEPERSFNTEINGRE().setANUINGRESOS(personaMoral.getTransaccionalidad().getMontoAnualIngresos());
			}
			
		}
		
		/**
		 * COSAS DE PERSONA DE RIESGO
		 */
		//Definiciones de relaciones de grupo
		perfTrans.getPEPERSRLGRPEMPT().setCODNRBEEN(super.getEntidad());
		perfTrans.getPEPERSRLGRPEMPT().setIDINTERNOPE(personaMoral.getIdInterna());

		perfTrans.getPEPERSRLGRPEMPT().setPORCGPOFILIAL(0);

		perfTrans.getPEPERSRLGRPEMPT().setNUMOFCNA(0);
	
		perfTrans.getPEPERSRLGRPEMPT().setNUMEMPLEADO(0);

		perfTrans.getPEPERSRLGRPEMPT().setIMPVTASANUAL(new BigDecimal("0"));
		perfTrans.getPEINFFINANPZAV().setPLAZAOPER01("");
		perfTrans.getPEINFFINANPZAV().setPLAZAOPER02("");		
		perfTrans.getPEINFFINANPZAV().setPLAZAOPER03("");
		perfTrans.getPEINFFINANPZAV().setPLAZAOPER04("");
		perfTrans.getPEINFFINANPZAV().setPLAZAOPER05("");
		perfTrans.getPEINFFINANPZAV().setPLAZAOPER06("");
		// Definiciones de adicion de empresas
		perfTrans.getPEGRPEMPREADICV().setPORCGPOFILIAL(0);
		perfTrans.getPEGRPEMPREADICV().setNUMOFCNA(0);
		perfTrans.getPEGRPEMPREADICV().setNUMEMPLEADO(0);
		perfTrans.getPEGRPEMPREADICV().setIMPVTASANUAL(new BigDecimal("0"));
		
		// Definiciones de donativos, en el alta siempre a N
		perfTrans.getPEORGDNTVSV().setINDFRCESPCA("N");
		perfTrans.getPEORGDNTVSV().setINDFRCANUAL("N");
		perfTrans.getPEORGDNTVSV().setINDFRCMENSUAL("N");
		perfTrans.getPEORGDNTVSV().setINDFRCSEMANAL("N");
		perfTrans.getPEORGDNTVSV().setINDFRCDIARIA("N");
		perfTrans.getPEORGDNTVSV().setINDFENEFTVO("N");
		perfTrans.getPEORGDNTVSV().setINDFENCHQ("N");
		perfTrans.getPEORGDNTVSV().setINDFENTDC("N");
		perfTrans.getPEORGDNTVSV().setINDFENTRANS("N");
		perfTrans.getPEORGDNTVSV().setINDFENESPC("N");
		perfTrans.getPEORGDNTVSV().setINDTPOGNRAL("N");
		perfTrans.getPEORGDNTVSV().setINDTPOPERS("N");
		
		// Definiciones de la financiación, son siempre 0 en el alta.
		perfTrans.getPEORGFINANCV().setIMPACTFIJO(new BigDecimal("0"));
		perfTrans.getPEORGFINANCV().setIMPACTCRCLT(new BigDecimal("0"));
		perfTrans.getPEORGFINANCV().setIMPPASCTPLZ(new BigDecimal("0"));
		perfTrans.getPEORGFINANCV().setIMPPASLGPLZ(new BigDecimal("0"));
		perfTrans.getPEORGFINANCV().setIMPCAPCTBLE(new BigDecimal("0"));
		perfTrans.getPEORGFINANCV().setIMPINGANUAL(new BigDecimal("0"));
		perfTrans.getPEORGFINANCV().setIMPCOSTVTA(new BigDecimal("0"));
		perfTrans.getPEORGFINANCV().setIMPGASTO(new BigDecimal("0"));
		perfTrans.getPEORGFINANCV().setIMPUTILIDAD(new BigDecimal("0"));
		perfTrans.getPEORGFINANCV().setIMPDLLSMNDA(new BigDecimal("0"));

		contexto.setPEALTAORGPERFTRANEVT(perfTrans);
		contexto.setSTDTRNIPARMV(contextoEntrada);
		
		return contexto;
	}

	
	private String deBooleanATexto(Boolean booleano) {
		
		if (booleano!=null && booleano) {
			return "S";
		}
		
		return "N";
	}

}
