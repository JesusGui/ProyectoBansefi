package mx.babel.bansefi.banksystem.administracion.backends.mantenimientoempleado;

import java.io.Serializable;
import java.util.Date;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.administracion.webservices.altaempleado.AltaEmpleadoServicio;
import mx.babel.bansefi.banksystem.administracion.webservices.altaempleado.Ejecutar;
import mx.babel.bansefi.banksystem.administracion.webservices.altaempleado.Ejecutar.ITRALTAEMPLTRNI.TRALTAEMPLEVTY.DRDMDPOBJTRDV.CODPERSRLDIRV;
import mx.babel.bansefi.banksystem.administracion.webservices.altaempleado.Ejecutar.ITRALTAEMPLTRNI.TRALTAEMPLEVTY.DRDMDPOBJTRDV.COMPDOMICV;
import mx.babel.bansefi.banksystem.administracion.webservices.altaempleado.Ejecutar.ITRALTAEMPLTRNI.TRALTAEMPLEVTY.PECOMPARTIRDOMICOBJTR;
import mx.babel.bansefi.banksystem.administracion.webservices.altaempleado.EjecutarResult;
import mx.babel.bansefi.banksystem.administracion.webservices.altaempleado.ResponseBansefi;
import mx.babel.bansefi.banksystem.administracion.wrappers.EmpleadosWrapper;
import mx.babel.bansefi.banksystem.base.backends.AbcPerfilEmpleadoBackEnd;
import mx.babel.bansefi.banksystem.base.backends.AltaPerfilTransaccionalPersonaBackEnd;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaFisicaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.EmpleadoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para el alta de un empleado en especifico
 * 
 * @author javier.martinnino
 *
 */
@Component
public class AltaEmpleadoBackEnd extends BackEndBean implements Serializable{
	
	private static final long serialVersionUID = -6020643104774989839L;
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
	private AltaPerfilEmpleadoTCBBackEnd altaPerfilEmpleadoTCBBackEnd;
	
	@Autowired
	private AbcPerfilEmpleadoBackEnd abcPerfilEmpleadoBackEnd;
	
	@Autowired 
	private EmpleadosWrapper empleadosWrapper;
	
	/**
	 * Función encargada del alta de empleados
	 * por medio de servicios web.
	 * 
	 * @param EmpleadoBean empleado a dar de alta
	 * @return Codigo de respuesta del servicio
	 */
	public Integer ejecutarTRN(EmpleadoBean empleado){
		Ejecutar.ITRALTAEMPLTRNI contexto = initPeticion(empleado);
		EjecutarResult respuesta = ejecutarWS(contexto);
		super.verificaRespuesta(respuesta);
		if(verificaResponseBansefi(respuesta)){
			return altaEmpleado(respuesta.getResponseBansefi(),empleado);
		}
		return null;
	}
	
	/**
	 * Función que devuelve el resultado del alta de empleado.
	 * 
	 * @param response
	 * @param EmpleadoBean empleado a dar de alta el perfil en caso de alta correcta
	 * @return
	 */
	private Integer altaEmpleado(ResponseBansefi response,EmpleadoBean empleado){
		Integer resultado = null;
		if(verificaRespuestaCliente(response)){
			resultado = response.getOTRALTAEMPLTRNO().getRTRNCD();
			if (response.getOTRALTAEMPLTRNO().getTRALTAEMPLEVTZ().getCLAVEEMPLEADOV().getIDINTERNOPE() !=0){
				empleado.getDatos().setIdInterna(response.getOTRALTAEMPLTRNO().getTRALTAEMPLEVTZ().getCLAVEEMPLEADOV().getIDINTERNOPE());
			}
			altaPerfilTransaccionalPersonaBackend.ejecutarTRN(empleado.getDatos());
			altaPerfilEmpleadoTCBBackEnd.ejecutarTRN(empleado);
			abcPerfilEmpleadoBackEnd.ejecutarWS(empleado.getNumEmpleado(), empleado.getPerfil());
		}
		return resultado;
	}
	
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param empleado a dar de alta
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITRALTAEMPLTRNI initPeticion(EmpleadoBean empleado){
		Ejecutar.ITRALTAEMPLTRNI contexto = new Ejecutar.ITRALTAEMPLTRNI();
		Ejecutar.ITRALTAEMPLTRNI.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRALTAEMPLTRNI.STDTRNIPARMV();
		Ejecutar.ITRALTAEMPLTRNI.TRALTAEMPLEVTY empl =
				new Ejecutar.ITRALTAEMPLTRNI.TRALTAEMPLEVTY();
		
		IntegerToDateConverter converter = new IntegerToDateConverter();
		
		// Inicializamos el objeto e incluimos todos los datos a wrappear
		empl = empleadosWrapper.wrappBeanAltaEmpleado(empleado);
		super.initialize(empl);
		
		// Incluimos los datos de nacimiento
		
		if (empleado.getDatos().getMunicipioCatGeo().getCodigoPostal() != null && !("").equals(empleado.getDatos().getMunicipioCatGeo().getCodigoPostal())){
			empl.getPEALTAINDVOBJTRDV().setCODENTCOLECAG(empleado.getDatos().getMunicipioCatGeo().getCodigoEntidadColectiva());
			empl.getPEALTAINDVOBJTRDV().setCODENTSINGAG(empleado.getDatos().getMunicipioCatGeo().getCodigoProvincia());
			empl.getPEALTAINDVOBJTRDV().setCODPROVINCIAAG(empleado.getDatos().getMunicipioCatGeo().getCodigoProvincia());
			empl.getPEALTAINDVOBJTRDV().setCODMUNICIPIOAG(empleado.getDatos().getMunicipioCatGeo().getCodigoMunicipio());
			empl.getPEALTAINDVOBJTRDV().setCODPAISNACTO(empleado.getDatos().getMunicipioCatGeo().getCodigoPais());
			empl.getPEALTAINDVOBJTRDV().setNOMBLOCALIDADAG(empleado.getDatos().getMunicipioCatGeo().getNombreLocalidad());
			empl.getPEALTAINDVOBJTRDV().setNOMBPROVINCIAAG(empleado.getDatos().getMunicipioCatGeo().getNombreProvincia());
			empl.getPEALTAINDVOBJTRDV().setCODPOSTALAG(empleado.getDatos().getMunicipioCatGeo().getCodigoPostal());
			
			if (empleado.getDatos().getMunicipioCatGeo().getPrNumArGeo() !=null){
				empl.getPEALTAINDVOBJTRDV().setNUMARGEOPROV(Integer.parseInt(empleado.getDatos().getMunicipioCatGeo().getPrNumArGeo()));
			}
			
			if (empleado.getDatos().getMunicipioCatGeo().getNumArGeo() !=null){
				empl.getPEALTAINDVOBJTRDV().setNUMARGEOPAIS(Integer.parseInt(empleado.getDatos().getMunicipioCatGeo().getNumArGeo()));
			}
		}else{
			empl.getPEALTAINDVOBJTRDV().setNOMBLOCALIDADAG(empleado.getDatos().getMunicipioCatGeo().getNombreMunicipio());
			empl.getPEALTAINDVOBJTRDV().setNOMBPROVINCIAAG(empleado.getDatos().getEstado().getNombreProvincia());
			empl.getPEALTAINDVOBJTRDV().setCODPAISNACTO(empleado.getDatos().getPais());
			
			// Localidad no Codificada
			empl.getPEALTAINDVOBJTRDV().getAGLOCNOCODNUMV().setCODARGEO(AltaEmpleadoBackEnd.COD_ARGEO_NO_COD);
			empl.getPEALTAINDVOBJTRDV().setCODPOSTALAG(AltaEmpleadoBackEnd.COD_POSTAL_NO_COD);
			empl.getPEALTAINDVOBJTRDV().setCODPERSRLARGEO(AltaEmpleadoBackEnd.COD_PERS_RL_AR_GEO_NO_COD);
			empl.getAGLOCALIDADNOCODV().setCODPROVINCIAAG(empleado.getDatos().getEstado().getCodigoProvincia());
			empl.getAGLOCALIDADNOCODV().setCODPAISAG(empleado.getDatos().getPais());
			
		}
		
		// Incluimos los datos de domicilio
		empl.getDRDMDPOBJTRDV().setCODNRBEEN(super.getEntidad());
		empl.getDRDMDPOBJTRDV().setCODDIR("1");
		
		empl.getDRDMDPOBJTRDV().setCODVIA(empleado.getDatos().getDomicilios().get(0).getTipoCalle());
		empl.getDRDMDPOBJTRDV().setCODREGIMOCUP(empleado.getDatos().getDomicilios().get(0).getRegimenOcupacion());
		empl.getDRDMDPOBJTRDV().setNUMTLFNODOMIC(empleado.getDatos().getDomicilios().get(0).getTelefono());
		empl.getDRDMDPOBJTRDV().setCODPOSTALAG(empleado.getDatos().getDomicilios().get(0).getDatosFinalesCatGeo().getCodigoPostal());
		empl.getDRDMDPOBJTRDV().setNOMBLOCALIDADAG(empleado.getDatos().getDomicilios().get(0).getDatosFinalesCatGeo().getNombreMunicipio());
		empl.getDRDMDPOBJTRDV().setCODPROVINCIAAG(empleado.getDatos().getDomicilios().get(0).getDatosFinalesCatGeo().getCodigoProvincia());
		empl.getDRDMDPOBJTRDV().setNOMBPROVINCIAAG(empleado.getDatos().getDomicilios().get(0).getDatosFinalesCatGeo().getNombreProvincia());
		empl.getDRDMDPOBJTRDV().setCODCOMAUTNMAAG(empleado.getDatos().getDomicilios().get(0).getDatosFinalesCatGeo().getCodigoComunidadAutonoma());
		empl.getDRDMDPOBJTRDV().setCODPAISAG(empleado.getDatos().getDomicilios().get(0).getDatosFinalesCatGeo().getCodigoPais());
		
		empl.getDRDMDPOBJTRDV().getCODARGEODOMICILIOV().setCODARGEO(empleado.getDatos().getDomicilios().get(0).getCodArGeo());
		if (empleado.getDatos().getDomicilios().get(0).getDatosFinalesCatGeo().getNumArGeo() !=null){
			empl.getDRDMDPOBJTRDV().getNUMARGEODOMICILIOV().setNUMARGEO(Integer.parseInt(empleado.getDatos().getDomicilios().get(0).getDatosFinalesCatGeo().getNumArGeo()));
		}
		
		
		// Incluimos los datos recogidos en el apartado de masDatos
		if (empleado.getDatos().getDomicilios().get(0).getNombreCalle() !=null && !("").equals(empleado.getDatos().getDomicilios().get(0).getNombreCalle())){
			COMPDOMICV nombreCalle = new COMPDOMICV();
			nombreCalle.setCODCOMPDOMIC(ConstantesFuncionales.CALLE);
			nombreCalle.setVALCOMPDOMICDRLEN(ConstantesFuncionales.CALLE_LONG);
			nombreCalle.setVALCOMPDOMICDR(empleado.getDatos().getDomicilios().get(0).getNombreCalle());			
			empl.getDRDMDPOBJTRDV().getCOMPDOMICV().add(nombreCalle);
		}
		
		if (empleado.getDatos().getDomicilios().get(0).getNumeroExterior() !=null && !("").equals(empleado.getDatos().getDomicilios().get(0).getNumeroExterior())){
			COMPDOMICV numExterior = new COMPDOMICV();
			numExterior.setCODCOMPDOMIC(ConstantesFuncionales.NUMERO_EXT);
			numExterior.setVALCOMPDOMICDRLEN(ConstantesFuncionales.NUMERO_EXT_LONG);
			numExterior.setVALCOMPDOMICDR(empleado.getDatos().getDomicilios().get(0).getNumeroExterior());			
			empl.getDRDMDPOBJTRDV().getCOMPDOMICV().add(numExterior);
		}
		
		if (empleado.getDatos().getDomicilios().get(0).getInterior() !=null && !("").equals(empleado.getDatos().getDomicilios().get(0).getInterior())){
			COMPDOMICV numInterior = new COMPDOMICV();
			numInterior.setCODCOMPDOMIC(ConstantesFuncionales.INTERIOR);
			numInterior.setVALCOMPDOMICDRLEN(ConstantesFuncionales.INTERIOR_LONG);
			numInterior.setVALCOMPDOMICDR(empleado.getDatos().getDomicilios().get(0).getInterior());			
			empl.getDRDMDPOBJTRDV().getCOMPDOMICV().add(numInterior);
		}
		
		if (empleado.getDatos().getDomicilios().get(0).getDepartamento() !=null && !("").equals(empleado.getDatos().getDomicilios().get(0).getDepartamento())){
			COMPDOMICV departamento = new COMPDOMICV();
			departamento.setCODCOMPDOMIC(ConstantesFuncionales.DEPARTAMENTO);
			departamento.setVALCOMPDOMICDRLEN(ConstantesFuncionales.DEPARTAMENTO_LONG);
			departamento.setVALCOMPDOMICDR(empleado.getDatos().getDomicilios().get(0).getDepartamento());			
			empl.getDRDMDPOBJTRDV().getCOMPDOMICV().add(departamento);
		}
		
		if (empleado.getDatos().getDomicilios().get(0).getColonia() !=null && !("").equals(empleado.getDatos().getDomicilios().get(0).getColonia())){
			COMPDOMICV colonia = new COMPDOMICV();
			colonia.setCODCOMPDOMIC(ConstantesFuncionales.COLONIA);
			colonia.setVALCOMPDOMICDRLEN(ConstantesFuncionales.COLONIA_LONG);
			colonia.setVALCOMPDOMICDR(empleado.getDatos().getDomicilios().get(0).getColonia());			
			empl.getDRDMDPOBJTRDV().getCOMPDOMICV().add(colonia);
		}
		
		if (empleado.getDatos().getDomicilios().get(0).getOtrosDatos() !=null && !("").equals(empleado.getDatos().getDomicilios().get(0).getOtrosDatos())){
			COMPDOMICV otrosDatos = new COMPDOMICV();
			otrosDatos.setCODCOMPDOMIC(ConstantesFuncionales.OTROS_DATOS);
			otrosDatos.setVALCOMPDOMICDRLEN(ConstantesFuncionales.OTROS_DATOS_LONG);
			otrosDatos.setVALCOMPDOMICDR(empleado.getDatos().getDomicilios().get(0).getOtrosDatos());			
			empl.getDRDMDPOBJTRDV().getCOMPDOMICV().add(otrosDatos);
		}
		
		if (empleado.getDatos().getDomicilios().get(0).getEdificio() !=null && !("").equals(empleado.getDatos().getDomicilios().get(0).getEdificio())){
			COMPDOMICV edificio = new COMPDOMICV();
			edificio.setCODCOMPDOMIC(ConstantesFuncionales.EDIFICIO);
			edificio.setVALCOMPDOMICDRLEN(ConstantesFuncionales.EDIFICIO_LONG);
			edificio.setVALCOMPDOMICDR(empleado.getDatos().getDomicilios().get(0).getEdificio());			
			empl.getDRDMDPOBJTRDV().getCOMPDOMICV().add(edificio);
		}
		
		if (empleado.getDatos().getDomicilios().get(0).getUnidadHabitacional() !=null && !("").equals(empleado.getDatos().getDomicilios().get(0).getUnidadHabitacional())){
			COMPDOMICV unidadHabitacional = new COMPDOMICV();
			unidadHabitacional.setCODCOMPDOMIC(ConstantesFuncionales.UNIDAD_HABITACIONAL);
			unidadHabitacional.setVALCOMPDOMICDRLEN(ConstantesFuncionales.UNIDAD_HABITACIONAL_LONG);
			unidadHabitacional.setVALCOMPDOMICDR(empleado.getDatos().getDomicilios().get(0).getUnidadHabitacional());			
			empl.getDRDMDPOBJTRDV().getCOMPDOMICV().add(unidadHabitacional);
		}
		
		if (empleado.getDatos().getDomicilios().get(0).getManzana() !=null && !("").equals(empleado.getDatos().getDomicilios().get(0).getManzana())){
			COMPDOMICV manzana = new COMPDOMICV();
			manzana.setCODCOMPDOMIC(ConstantesFuncionales.MANZANA);
			manzana.setVALCOMPDOMICDRLEN(ConstantesFuncionales.MANZANA_LONG);
			manzana.setVALCOMPDOMICDR(empleado.getDatos().getDomicilios().get(0).getManzana());			
			empl.getDRDMDPOBJTRDV().getCOMPDOMICV().add(manzana);
		}
		
		
		// Incluimos tipo de direccion fiscal y habitual
		CODPERSRLDIRV tipoDireccionFiscal = new CODPERSRLDIRV();
		tipoDireccionFiscal.setCODPERSRLDIR("01");
		
		CODPERSRLDIRV tipoDireccionHabitual = new CODPERSRLDIRV();
		tipoDireccionHabitual.setCODPERSRLDIR("02");
		
		empl.getDRDMDPOBJTRDV().getCODPERSRLDIRV().add(tipoDireccionFiscal);
		empl.getDRDMDPOBJTRDV().getCODPERSRLDIRV().add(tipoDireccionHabitual);
				
		// Se inicializan el resto de datos que no se wrappean del bean 
		// y que son necesarios como datos de entrada a la TRN
		empl.getEPPEDPOBJTRDV().setCODNRBEEN(super.getEntidad());
		empl.getEPPEDPOBJTRDV().setINDAUSNCAEP("N");
		empl.getEPPEDPOBJTRDV().setINDATRIBMANCEP("N");
		empl.getEPPEDPOBJTRDV().setINDMASCENT("N");		
		
		empl.getPEPERSINDCLIENTEV().setINDCLIENTE("S");
		empl.getPEPERSINDCLIENTEV().setINDEMPLDIN("S");
		
		empl.getDRDMDPOBJTRDV().setCODNRBEEN(super.getEntidad());
		empl.getDRDMDPOBJTRDV().setCODDIR("1");		
		empl.getDRDMDPOBJTRDV().setINDMASDOMIC("N");
		
		empl.getPEALTAINDVOBJTRDV().setCODNRBEEN(super.getEntidad());
		empl.getPEALTAINDVOBJTRDV().setDISCRIDPE(0);
		
		// Si el cliente no es trabajador independiente vaciamos los datos de fecha de alta y de 
		if (empleado.getDatos().getAutonomo() ==null || !empleado.getDatos().getAutonomo()){
			empl.getPEALTAINDVOBJTRDV().setFECHAAUTNMOIN(AltaEmpleadoBackEnd.FECHA_INICIO);
			empl.getPEALTAINDVOBJTRDV().setCODCNAEPERS("");
		}
		
		// Comprobamos si la fecha de alta de autonomo esta vacia, para ponerla a la fecha de inicio 11/11/1111
		if (empleado.getDatos().getFechaAltaAutonomo() == null){
			empl.getPEALTAINDVOBJTRDV().setFECHAAUTNMOIN(AltaEmpleadoBackEnd.FECHA_INICIO);
		}
		
		empl.getPEALTAINDVOBJTRDV().setFECHAFALLCRREPE(AltaEmpleadoBackEnd.FECHA_FIN);
		
		empl.getPEALTAINDVOBJTRDV().setFECHAALTAPER(converter.convertFrom(new Date(),null));
		empl.getPEALTAINDVOBJTRDV().setSITRESDNCPE("1");
		empl.getPEALTAINDVOBJTRDV().setCODPE("F");
		empl.getPEALTAINDVOBJTRDV().setINDLDEROPNNPE("N");
		empl.getPEALTAINDVOBJTRDV().setINDAVISOPE("N");
		empl.getPEALTAINDVOBJTRDV().setINDANTCNPE("N");
		empl.getPEALTAINDVOBJTRDV().setINDPERTNGRPPE("N");
		empl.getPEALTAINDVOBJTRDV().setINDACCESORSTRG("N");
		empl.getPEALTAINDVOBJTRDV().setINDNODOCUM("N");
		// Incluimos la logica para el indicador de No Publicidad
		if (empleado.getDatos().getNoPublicidad() !=null && empleado.getDatos().getNoPublicidad()){
			empl.getPEALTAINDVOBJTRDV().setINDPUBLCDPE("N");
		}else{
			empl.getPEALTAINDVOBJTRDV().setINDPUBLCDPE("S");
		}
		empl.getPEALTAINDVOBJTRDV().setINDCLIENTE("N");
		empl.getPEALTAINDVOBJTRDV().setINDOFERTAS("N");
		empl.getPEALTAINDVOBJTRDV().setINDOTRRL("N");
		empl.getPEALTAINDVOBJTRDV().setINDPERTNLSTNGR("N");
		empl.getPEALTAINDVOBJTRDV().setINDCLIENTESQVCL("N");
		empl.getPEALTAINDVOBJTRDV().setINDCLIENTPRSPCTV("N");
		empl.getPEALTAINDVOBJTRDV().setINDCLIENTRCHZD("N");
		empl.getPEALTAINDVOBJTRDV().setINDSOCIOPE("N");
		empl.getPEALTAINDVOBJTRDV().setINDMASGT("N");
		
		empl.getPEALTAINDVOBJTRDV().setINDMASDIR("N");
		empl.getPEALTAINDVOBJTRDV().setINDNOESPAWOL("N");
		
		empl.getPEALTAINDVOBJTRDV().setCODOFCNAALTA(super.getSucursal());
		
		empl.getPEALTAINDVOBJTRDV().setNOMB50(empleado.getDatos().getNombreCompleto());
		empl.getPEALTAINDVOBJTRDV().setNOMBTARJETA(obtenerNombTarjeta(empleado.getDatos()));
		
		empl.getPEALTAINDVOBJTRDV().setINDEMPLDIN("S");
		empl.getPEALTAINDVOBJTRDV().setINDCTLVIVIN("N");
		empl.getPEALTAINDVOBJTRDV().setINDGESTOR("N");
		empl.getPEALTAINDVOBJTRDV().setINDCLIENTE("N");
		
		empl.getPEALTAINDVOBJTRDV().setINDPPALIDPE("S");
		
		super.initialize(contexto);		
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_ALTA_EMPL_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		
		contexto.setTRALTAEMPLEVTY(empl);
		contexto.setSTDTRNIPARMV(contextoEntrada);
		
		for(int i = 0; i < 4; i++){
			PECOMPARTIRDOMICOBJTR.CODPERSRLDIRV codPersRlDirV = new PECOMPARTIRDOMICOBJTR.CODPERSRLDIRV();
			super.initialize(codPersRlDirV);
			empl.getPECOMPARTIRDOMICOBJTR().getCODPERSRLDIRV().add(codPersRlDirV);
		}
		
		super.initialize(contexto);
		
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
				nombTarjeta += AltaEmpleadoBackEnd.GUION + ape2;
			}else{
				nombTarjeta = ape2;
			}
		}
		
		if (nombre !=null){
			if (nombTarjeta !=null && !("").equals(nombTarjeta)){
				nombTarjeta += AltaEmpleadoBackEnd.GUION + nombre;
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
	private EjecutarResult ejecutarWS(Ejecutar.ITRALTAEMPLTRNI contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					AltaEmpleadoServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta de "
					+ "alta de empleado.", e);
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
	 * de la llamada a TRN.
	 * 
	 * @param response Respuesta Bansefi con datos de la persona moral
	 * @return <code>true</code> si la respuesta Bansefi contiene elemento no nulos
	 */
	private Boolean verificaRespuestaCliente(ResponseBansefi response){
		Boolean noNulo = false;
		if(response!= null && response.getOTRALTAEMPLTRNO() != null &&
				response.getOTRALTAEMPLTRNO().getTRALTAEMPLEVTZ() != null){
			noNulo = true;
		}
		return noNulo;
	}
		
}
