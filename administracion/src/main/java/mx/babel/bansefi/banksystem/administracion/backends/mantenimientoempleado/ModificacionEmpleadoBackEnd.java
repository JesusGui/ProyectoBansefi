package mx.babel.bansefi.banksystem.administracion.backends.mantenimientoempleado;

import java.io.Serializable;
import java.util.Date;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.administracion.webservices.modificacionempleado.Ejecutar;
import mx.babel.bansefi.banksystem.administracion.webservices.modificacionempleado.Ejecutar.ITRMODIFEMPLTRNI.TRMODIFEMPLEVTY.DRDMDPOBJTRDV.CODPERSRLDIRV;
import mx.babel.bansefi.banksystem.administracion.webservices.modificacionempleado.Ejecutar.ITRMODIFEMPLTRNI.TRMODIFEMPLEVTY.DRDMDPOBJTRDV.COMPDOMICV;
import mx.babel.bansefi.banksystem.administracion.webservices.modificacionempleado.Ejecutar.ITRMODIFEMPLTRNI.TRMODIFEMPLEVTY.INDMODIFCLINDVV;
import mx.babel.bansefi.banksystem.administracion.webservices.modificacionempleado.EjecutarResult;
import mx.babel.bansefi.banksystem.administracion.webservices.modificacionempleado.ModificacionEmpleadoServicio;
import mx.babel.bansefi.banksystem.administracion.webservices.modificacionempleado.ResponseBansefi;
import mx.babel.bansefi.banksystem.administracion.wrappers.EmpleadosWrapper;
import mx.babel.bansefi.banksystem.base.backends.AbcPerfilEmpleadoBackEnd;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.backends.ModificacionPerfilTransaccionalPersonaBackEnd;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaFisicaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.EmpleadoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para modificacion de empleados
 * 
 * @author javier.martinnino
 *
 */
@Component
public class ModificacionEmpleadoBackEnd extends BackEndBean implements Serializable {
	
	private static final long serialVersionUID = 7586503073054810812L;
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
	private ModificacionPerfilEmpleadoTCBBackEnd modificacionPerfilEmpleadoTCBBackEnd;
	
	@Autowired
	private AbcPerfilEmpleadoBackEnd abcPerfilEmpleadoBackEnd;
	
	@Autowired 
	private EmpleadosWrapper empleadosWrapper;
	
	/**
	 * Función encargada de modificar a un empleado
	 * por medio de servicios web.
	 * 
	 * @param empleadoModificado a modificar
	 * @param empleadoConsultado con los datos previos a la modificacion
	 * @return Resultado de la operacion
	 */
	public Integer ejecutarTRN(EmpleadoBean empleadoModificado,EmpleadoBean empleadoConsultado){
		Ejecutar.ITRMODIFEMPLTRNI contexto = initPeticion(empleadoModificado,empleadoConsultado);
		EjecutarResult respuesta = ejecutarWS(contexto);
		super.verificaRespuesta(respuesta);
		if(verificaResponseBansefi(respuesta)){
			return modificacionEmpleado(respuesta.getResponseBansefi(),empleadoModificado);
		}
		return null;
	}
	
	/**
	 * Función que construye la lista de cuentas a partir de la respuesta al servidor.
	 * 
	 * @param response
	 * @param empleado Empleado a modificar
	 * @return
	 */
	private Integer modificacionEmpleado(ResponseBansefi response, EmpleadoBean empleado){
		Integer resultado = null;
		if(verificaRespuestaCliente(response)){
			resultado = response.getOTRMODIFEMPLTRNO().getRTRNCD();
			modificacionPerfilTransaccionalPersonaBackend.ejecutarTRN(empleado.getDatos());
			modificacionPerfilEmpleadoTCBBackEnd.ejecutarTRN(empleado);
			abcPerfilEmpleadoBackEnd.ejecutarWS(empleado.getNumEmpleado(), empleado.getPerfil());
		}
		return resultado;
	}
	
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param empleadoModificado a modificar
	 * @param empleadoConsultado con los datos a consultar
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITRMODIFEMPLTRNI initPeticion(EmpleadoBean empleadoModificado,EmpleadoBean empleadoConsultado){
		Ejecutar.ITRMODIFEMPLTRNI contexto = new Ejecutar.ITRMODIFEMPLTRNI();
		Ejecutar.ITRMODIFEMPLTRNI.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRMODIFEMPLTRNI.STDTRNIPARMV();
		Ejecutar.ITRMODIFEMPLTRNI.TRMODIFEMPLEVTY empl=
				new Ejecutar.ITRMODIFEMPLTRNI.TRMODIFEMPLEVTY();
		
		IntegerToDateConverter converter = new IntegerToDateConverter();
		
		// Inicializamos el objeto e incluimos todos los datos a wrappear
		empl = empleadosWrapper.wrappBeanModificacionEmpleado(empleadoModificado);
		super.initialize(empl);
		
		// Se inicializan los indicadores del objeto IND_MODIF_CL_INDV_V
		
		empl.setINDMODIFCLINDVV(this.inicializaIndicadoresModificacionCliente(empleadoModificado.getDatos(),empleadoConsultado.getDatos()));
		
		// Inicializamos por defecto los indicadores de empleados con los valores 
		empl.getINDMODIFEPEMPLV().getINDEPEMPLINS().setSTDCHAR01("N");
		empl.getINDMODIFEPEMPLV().getINDEPEMPLUPD().setSTDCHAR01("S");
		empl.getINDMODIFEPEMPLV().getINDEPCARGOEMPLUPD().setSTDCHAR01("N");
		empl.getINDMODIFEPEMPLV().getINDEPEMPLRLCENTUPD().setSTDCHAR01("N");
		
		
		// Incluimos los datos de nacimiento
		
		if (empleadoModificado.getDatos().getMunicipioCatGeo().getCodigoPostal() != null && !("").equals(empleadoModificado.getDatos().getMunicipioCatGeo().getCodigoPostal())){
			empl.getPEALTAINDVOBJTRDV().setCODENTCOLECAG(empleadoModificado.getDatos().getMunicipioCatGeo().getCodigoEntidadColectiva());
			empl.getPEALTAINDVOBJTRDV().setCODENTSINGAG(empleadoModificado.getDatos().getMunicipioCatGeo().getCodigoProvincia());
			empl.getPEALTAINDVOBJTRDV().setCODPROVINCIAAG(empleadoModificado.getDatos().getMunicipioCatGeo().getCodigoProvincia());
			empl.getPEALTAINDVOBJTRDV().setCODMUNICIPIOAG(empleadoModificado.getDatos().getMunicipioCatGeo().getCodigoMunicipio());
			empl.getPEALTAINDVOBJTRDV().setCODPAISNACTO(empleadoModificado.getDatos().getMunicipioCatGeo().getCodigoPais());
			empl.getPEALTAINDVOBJTRDV().setNOMBLOCALIDADAG(empleadoModificado.getDatos().getMunicipioCatGeo().getNombreLocalidad());
			empl.getPEALTAINDVOBJTRDV().setNOMBPROVINCIAAG(empleadoModificado.getDatos().getMunicipioCatGeo().getNombreProvincia());
			empl.getPEALTAINDVOBJTRDV().setCODPOSTALAG(empleadoModificado.getDatos().getMunicipioCatGeo().getCodigoPostal());
			
			if (empleadoModificado.getDatos().getMunicipioCatGeo().getPrNumArGeo() !=null){
				empl.getPEALTAINDVOBJTRDV().setNUMARGEOPROV(Integer.parseInt(empleadoModificado.getDatos().getMunicipioCatGeo().getPrNumArGeo()));
			}
			
			if (empleadoModificado.getDatos().getMunicipioCatGeo().getNumArGeo() !=null){
				empl.getPEALTAINDVOBJTRDV().setNUMARGEOPAIS(Integer.parseInt(empleadoModificado.getDatos().getMunicipioCatGeo().getNumArGeo()));
			}
		}else{
			empl.getPEALTAINDVOBJTRDV().setNOMBLOCALIDADAG(empleadoModificado.getDatos().getMunicipioCatGeo().getNombreMunicipio());
			empl.getPEALTAINDVOBJTRDV().setNOMBPROVINCIAAG(empleadoModificado.getDatos().getEstado().getNombreProvincia());
			empl.getPEALTAINDVOBJTRDV().setCODPAISNACTO(empleadoModificado.getDatos().getPais());
			
			// Localidad no Codificada
			empl.getPEALTAINDVOBJTRDV().getAGLOCNOCODNUMV().setCODARGEO(ModificacionEmpleadoBackEnd.COD_ARGEO_NO_COD);
			empl.getPEALTAINDVOBJTRDV().setCODPOSTALAG(ModificacionEmpleadoBackEnd.COD_POSTAL_NO_COD);
			empl.getPEALTAINDVOBJTRDV().setCODPERSRLARGEO(ModificacionEmpleadoBackEnd.COD_PERS_RL_AR_GEO_NO_COD);
			empl.getAGLOCALIDADNOCODV().setCODPROVINCIAAG(empleadoModificado.getDatos().getEstado().getCodigoProvincia());
			empl.getAGLOCALIDADNOCODV().setCODPAISAG(empleadoModificado.getDatos().getPais());
			
			// Ponemos los indicadores de modificacion de localidad no codificada en funcion de si la localidad anterior estaba codificada
			empl.getINDMODIFCLINDVV().getINDAGLOCALIDADNOCODINS().setSTDCHAR01("S");
			empl.getINDMODIFCLINDVV().getINDPEPERSRLARGEOINS().setSTDCHAR01("S");
			
		}
		
		// Si la localidad era no codificada se limpian los datos que habian
		if (empleadoConsultado.getDatos().getMunicipioCatGeo() != null && (empleadoConsultado.getDatos().getMunicipioCatGeo().getCodigoPostal() == null || ("").equals(empleadoConsultado.getDatos().getMunicipioCatGeo().getCodigoPostal()))){
			empl.getINDMODIFCLINDVV().getINDPEPERSRLARGEOUPD().setSTDCHAR01("S");
		}
		
		
		// Incluimos los datos de domicilio
		empl.getDRDMDPOBJTRDV().setCODNRBEEN(super.getEntidad());
		empl.getDRDMDPOBJTRDV().setCODDIR("1");
		empl.getDRDMDPOBJTRDV().setIDINTERNOPE(empleadoModificado.getDatos().getIdInterna());
		
		empl.getDRDMDPOBJTRDV().setNUMDIR(empleadoModificado.getDatos().getDomicilios().get(0).getNumeroDireccion());
		
		empl.getDRDMDPOBJTRDV().setCODVIA(empleadoModificado.getDatos().getDomicilios().get(0).getTipoCalle());
		empl.getDRDMDPOBJTRDV().setCODREGIMOCUP(empleadoModificado.getDatos().getDomicilios().get(0).getRegimenOcupacion());
		empl.getDRDMDPOBJTRDV().setNUMTLFNODOMIC(empleadoModificado.getDatos().getDomicilios().get(0).getTelefono());
		empl.getDRDMDPOBJTRDV().setCODPOSTALAG(empleadoModificado.getDatos().getDomicilios().get(0).getDatosFinalesCatGeo().getCodigoPostal());
		empl.getDRDMDPOBJTRDV().setNOMBLOCALIDADAG(empleadoModificado.getDatos().getDomicilios().get(0).getDatosFinalesCatGeo().getNombreMunicipio());
		empl.getDRDMDPOBJTRDV().setCODPROVINCIAAG(empleadoModificado.getDatos().getDomicilios().get(0).getDatosFinalesCatGeo().getCodigoProvincia());
		empl.getDRDMDPOBJTRDV().setNOMBPROVINCIAAG(empleadoModificado.getDatos().getDomicilios().get(0).getDatosFinalesCatGeo().getNombreProvincia());
		empl.getDRDMDPOBJTRDV().setCODCOMAUTNMAAG(empleadoModificado.getDatos().getDomicilios().get(0).getDatosFinalesCatGeo().getCodigoComunidadAutonoma());
		empl.getDRDMDPOBJTRDV().setCODPAISAG(empleadoModificado.getDatos().getDomicilios().get(0).getDatosFinalesCatGeo().getCodigoPais());
		
		empl.getDRDMDPOBJTRDV().getCODARGEODOMICILIOV().setCODARGEO(empleadoModificado.getDatos().getDomicilios().get(0).getCodArGeo());
		if (empleadoModificado.getDatos().getDomicilios().get(0).getDatosFinalesCatGeo().getNumArGeo() !=null){
			empl.getDRDMDPOBJTRDV().getNUMARGEODOMICILIOV().setNUMARGEO(Integer.parseInt(empleadoModificado.getDatos().getDomicilios().get(0).getDatosFinalesCatGeo().getNumArGeo()));
		}
		
		
		// Incluimos los datos recogidos en el apartado de masDatos
		COMPDOMICV nombreCalle = new COMPDOMICV();
		nombreCalle.setCODCOMPDOMIC(ConstantesFuncionales.CALLE);
		nombreCalle.setVALCOMPDOMICDRLEN(ConstantesFuncionales.CALLE_LONG);
		nombreCalle.setVALCOMPDOMICDR(empleadoModificado.getDatos().getDomicilios().get(0).getNombreCalle());			
		empl.getDRDMDPOBJTRDV().getCOMPDOMICV().add(nombreCalle);
	
		COMPDOMICV numExterior = new COMPDOMICV();
		numExterior.setCODCOMPDOMIC(ConstantesFuncionales.NUMERO_EXT);
		numExterior.setVALCOMPDOMICDRLEN(ConstantesFuncionales.NUMERO_EXT_LONG);
		numExterior.setVALCOMPDOMICDR(empleadoModificado.getDatos().getDomicilios().get(0).getNumeroExterior());			
		empl.getDRDMDPOBJTRDV().getCOMPDOMICV().add(numExterior);
	
		COMPDOMICV numInterior = new COMPDOMICV();
		numInterior.setCODCOMPDOMIC(ConstantesFuncionales.INTERIOR);
		numInterior.setVALCOMPDOMICDRLEN(ConstantesFuncionales.INTERIOR_LONG);
		numInterior.setVALCOMPDOMICDR(empleadoModificado.getDatos().getDomicilios().get(0).getInterior());			
		empl.getDRDMDPOBJTRDV().getCOMPDOMICV().add(numInterior);
	
		COMPDOMICV departamento = new COMPDOMICV();
		departamento.setCODCOMPDOMIC(ConstantesFuncionales.DEPARTAMENTO);
		departamento.setVALCOMPDOMICDRLEN(ConstantesFuncionales.DEPARTAMENTO_LONG);
		departamento.setVALCOMPDOMICDR(empleadoModificado.getDatos().getDomicilios().get(0).getDepartamento());			
		empl.getDRDMDPOBJTRDV().getCOMPDOMICV().add(departamento);
	
		COMPDOMICV colonia = new COMPDOMICV();
		colonia.setCODCOMPDOMIC(ConstantesFuncionales.COLONIA);
		colonia.setVALCOMPDOMICDRLEN(ConstantesFuncionales.COLONIA_LONG);
		colonia.setVALCOMPDOMICDR(empleadoModificado.getDatos().getDomicilios().get(0).getColonia());			
		empl.getDRDMDPOBJTRDV().getCOMPDOMICV().add(colonia);
	
		COMPDOMICV otrosDatos = new COMPDOMICV();
		otrosDatos.setCODCOMPDOMIC(ConstantesFuncionales.OTROS_DATOS);
		otrosDatos.setVALCOMPDOMICDRLEN(ConstantesFuncionales.OTROS_DATOS_LONG);
		otrosDatos.setVALCOMPDOMICDR(empleadoModificado.getDatos().getDomicilios().get(0).getOtrosDatos());			
		empl.getDRDMDPOBJTRDV().getCOMPDOMICV().add(otrosDatos);
	
		COMPDOMICV edificio = new COMPDOMICV();
		edificio.setCODCOMPDOMIC(ConstantesFuncionales.EDIFICIO);
		edificio.setVALCOMPDOMICDRLEN(ConstantesFuncionales.EDIFICIO_LONG);
		edificio.setVALCOMPDOMICDR(empleadoModificado.getDatos().getDomicilios().get(0).getEdificio());			
		empl.getDRDMDPOBJTRDV().getCOMPDOMICV().add(edificio);
	
		COMPDOMICV unidadHabitacional = new COMPDOMICV();
		unidadHabitacional.setCODCOMPDOMIC(ConstantesFuncionales.UNIDAD_HABITACIONAL);
		unidadHabitacional.setVALCOMPDOMICDRLEN(ConstantesFuncionales.UNIDAD_HABITACIONAL_LONG);
		unidadHabitacional.setVALCOMPDOMICDR(empleadoModificado.getDatos().getDomicilios().get(0).getUnidadHabitacional());			
		empl.getDRDMDPOBJTRDV().getCOMPDOMICV().add(unidadHabitacional);
	
		COMPDOMICV manzana = new COMPDOMICV();
		manzana.setCODCOMPDOMIC(ConstantesFuncionales.MANZANA);
		manzana.setVALCOMPDOMICDRLEN(ConstantesFuncionales.MANZANA_LONG);
		manzana.setVALCOMPDOMICDR(empleadoModificado.getDatos().getDomicilios().get(0).getManzana());		
		empl.getDRDMDPOBJTRDV().getCOMPDOMICV().add(manzana);		
		
		// Incluimos tipo de direccion fiscal y habitual
		CODPERSRLDIRV tipoDireccionFiscal = new CODPERSRLDIRV();
		tipoDireccionFiscal.setCODPERSRLDIR("01");
		
		CODPERSRLDIRV tipoDireccionHabitual = new CODPERSRLDIRV();
		tipoDireccionHabitual.setCODPERSRLDIR("02");
		
		empl.getDRDMDPOBJTRDV().getCODPERSRLDIRV().add(tipoDireccionFiscal);
		empl.getDRDMDPOBJTRDV().getCODPERSRLDIRV().add(tipoDireccionHabitual);		
				
		// Indicamos el empleadoModificado que estamos modificando
		empl.getEPEMPLP().setCODNRBEEN(super.getEntidad());
		empl.getEPEMPLP().setIDINTERNOEMPLEP(empleadoModificado.getNumEmpleado());
				
		// Se inicializan el resto de datos que no se wrappean del bean 
		// y que son necesarios como datos de entrada a la TRN		
		empl.getPEPERSINDCLIENTEV().setINDCLIENTE("S");
		empl.getPEPERSINDCLIENTEV().setINDEMPLDIN("S");
		
		empl.getEPPEDPOBJTRDV().setCODNRBEEN(super.getEntidad());
		empl.getEPPEDPOBJTRDV().setIDINTERNOPE(empleadoModificado.getDatos().getIdInterna());
		empl.getEPPEDPOBJTRDV().setIDINTERNOEMPLEP(empleadoModificado.getNumEmpleado());
		empl.getEPPEDPOBJTRDV().setINDAUSNCAEP("N");
		empl.getEPPEDPOBJTRDV().setINDATRIBMANCEP("N");
		empl.getEPPEDPOBJTRDV().setINDMASCENT("N");	
		
		empl.getPEALTAINDVOBJTRDV().setCODNRBEEN(super.getEntidad());
		empl.getPEALTAINDVOBJTRDV().setIDINTERNOPE(empleadoModificado.getDatos().getIdInterna());
		
		// Si el cliente no es trabajador independiente vaciamos los datos de fecha de alta y de 
		if (empleadoModificado.getDatos().getAutonomo() ==null || !empleadoModificado.getDatos().getAutonomo()){
			empl.getPEALTAINDVOBJTRDV().setFECHAAUTNMOIN(ModificacionEmpleadoBackEnd.FECHA_INICIO);
			empl.getPEALTAINDVOBJTRDV().setCODCNAEPERS("");
		}
		
		// Comprobamos si la fecha de alta de autonomo esta vacia, para ponerla a la fecha de inicio 11/11/1111
		if (empleadoModificado.getDatos().getFechaAltaAutonomo() == null){
			empl.getPEALTAINDVOBJTRDV().setFECHAAUTNMOIN(ModificacionEmpleadoBackEnd.FECHA_INICIO);
		}
		
		//Comprobamos si la fecha de fallecimiento esta vacia para ponerla a 31/12/9999
		empl.getPEALTAINDVOBJTRDV().setFECHAFALLCRREPE(ModificacionEmpleadoBackEnd.FECHA_FIN);
		
		empl.getPEALTAINDVOBJTRDV().setDISCRIDPE(0);		
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
		if (empleadoModificado.getDatos().getNoPublicidad() !=null && empleadoModificado.getDatos().getNoPublicidad()){
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
		empl.getPEALTAINDVOBJTRDV().setNUMDIRPRAL(empleadoModificado.getDatos().getDomicilios().get(0).getNumeroDireccion());
		
		empl.getPEALTAINDVOBJTRDV().setINDMASDIR("N");
		empl.getPEALTAINDVOBJTRDV().setINDNOESPAWOL("N");
		
		empl.getPEALTAINDVOBJTRDV().setCODOFCNAALTA(empleadoModificado.getDatos().getOficinaAlta());
		
		empl.getPEALTAINDVOBJTRDV().setNOMB50(empleadoModificado.getDatos().getNombreCompleto());
		empl.getPEALTAINDVOBJTRDV().setNOMBTARJETA(obtenerNombTarjeta(empleadoModificado.getDatos()));
		
		empl.getPEALTAINDVOBJTRDV().setINDEMPLDIN("S");
		empl.getPEALTAINDVOBJTRDV().setINDCTLVIVIN("N");
		empl.getPEALTAINDVOBJTRDV().setINDGESTOR("N");
		empl.getPEALTAINDVOBJTRDV().setINDCLIENTE("N");
				
		empl.getPEALTAINDVOBJTRDV().setINDPPALIDPE("S");
		
		super.initialize(contexto);
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_MODIF_EMPL_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		
		contexto.setTRMODIFEMPLEVTY(empl);
		contexto.setSTDTRNIPARMV(contextoEntrada);
		
		return contexto;
	}
	
	/**
	 * Función para obtener los indicadores de datos de clientes.
	 * 
	 * @param personaModificada datos del cliente modificados
	 * @param personaConsultada datos del cliente previo a la modificacion
	 * @return los indicadores inicializados.
	 */
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
	 * @param persona Cliente indicado a dar de alta
	 * @return La respuesta del servicio web.
	 */
	private String obtenerNombTarjeta(ClientePersonaFisicaBean persona){
		
		String nombre = persona.getNombre();
		String ape2 = persona.getApeMaterno();
		
		String nombTarjeta = persona.getApePaterno();
		
		if (ape2 !=null){
			if (nombTarjeta !=null && !("").equals(nombTarjeta)){
				nombTarjeta += ModificacionEmpleadoBackEnd.GUION + ape2;
			}else{
				nombTarjeta = ape2;
			}
		}
		
		if (nombre !=null){
			if (nombTarjeta !=null && !("").equals(nombTarjeta)){
				nombTarjeta += ModificacionEmpleadoBackEnd.GUION + nombre;
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
	private EjecutarResult ejecutarWS(Ejecutar.ITRMODIFEMPLTRNI contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ModificacionEmpleadoServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta de "
					+ "modificacion de empleados.", e);
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
		if(response!= null && response.getOTRMODIFEMPLTRNO() != null &&
				response.getOTRMODIFEMPLTRNO().getTRMODIFEMPLEVTZ() != null){
			noNulo = true;
		}
		return noNulo;
	}
		
}
