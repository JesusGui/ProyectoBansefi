package mx.babel.bansefi.banksystem.base.backends.atribuciones;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.AtribucionBean;
import mx.babel.bansefi.banksystem.base.beans.domain.EmpleadoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.webservices.consultaatribuciones.ConsultaAtribucionesServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultaatribuciones.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultaatribuciones.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultaatribuciones.ResponseBansefi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para la consulta de atribuciones de un empleado
 * 
 * @author javier.martinnino
 *
 */
@Component
public class ConsultaAtribucionesBackEnd extends BackEndBean implements Serializable{
	
	private static final long serialVersionUID = -6020643104774989839L;
	private static final Integer FECHA_ATRIBUCION_FIJA = 99991231;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;

	@Autowired
	ConsultaDetalleAtribucionesBackEnd consultaDetalleAtribucionesBackEnd;
	
	/**
	 * Función encargada de la consulta de atribuciones a partir de un empleado
	 * por medio de servicios web.
	 * 
	 * @param EmpleadoBean empleado a consultar
	 * @return Codigo de respuesta del servicio
	 */
	public EmpleadoBean ejecutarTRN(EmpleadoBean empleado){
		Ejecutar.ITREPCONLISATRIBEMPL contexto = initPeticion(empleado);
		EjecutarResult respuesta = ejecutarWS(contexto);
		try{
			super.verificaRespuesta(respuesta);
		}catch(ControlableException ce){
			if(ce.getRtncod()==RETURN_COD_SIN_DATOS){
				return empleado;
			}else{
				throw ce;
			}
		}
		if(verificaResponseBansefi(respuesta)){
			return consultaAtribuciones(respuesta.getResponseBansefi(),empleado);
		}
		return null;
	}
	
	/**
	 * Función que la lista de atribuciones a partir de la respuesta al servidor.
	 * 
	 * @param response
	 * @return
	 */
	private EmpleadoBean consultaAtribuciones(ResponseBansefi response,EmpleadoBean empleado){
		EmpleadoBean empleadoConsultado = empleado;
		empleadoConsultado.setAtribucionesTemporales(new ArrayList<AtribucionBean>());		
		if(verificaRespuestaCliente(response)){
			List<Integer> fechas = new ArrayList<>();
			for (int i=0;i< response.getOTREPCONLISATRIBEMPL().getNUMBEROFRECORDS();i++){
				if (response.getOTREPCONLISATRIBEMPL().getTREPCONLISATRIBEMPL().getTREPCONLISATRIBEMPL().get(i).getFECHAINICTEMPEP() != FECHA_ATRIBUCION_FIJA){
					fechas.add(response.getOTREPCONLISATRIBEMPL().getTREPCONLISATRIBEMPL().getTREPCONLISATRIBEMPL().get(i).getFECHAINICTEMPEP());
				}
			}
			
			// Obtenemos la atribucion fija del empleado			
			empleadoConsultado.setAtribucionFija(this.consultaDetalleAtribucionesBackEnd.ejecutarTRN(empleado.getNumEmpleado(),FECHA_ATRIBUCION_FIJA));
			
			// Obtenemos las atribuciones temporales del empleado			
			for (int i=0;i<fechas.size();i++){
				empleadoConsultado.getAtribucionesTemporales().add(this.consultaDetalleAtribucionesBackEnd.ejecutarTRN(empleado.getNumEmpleado(),fechas.get(i)));
			}
			
		}
		return empleadoConsultado;
	}
	
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param idEmpleado a consultar
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITREPCONLISATRIBEMPL initPeticion(EmpleadoBean empleado){
		Ejecutar.ITREPCONLISATRIBEMPL contexto = new Ejecutar.ITREPCONLISATRIBEMPL();
		
		Ejecutar.ITREPCONLISATRIBEMPL.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITREPCONLISATRIBEMPL.STDTRNIPARMV();
		
		Ejecutar.ITREPCONLISATRIBEMPL.TREPCONLISATRIBEMPL.EPATRIBUCIONESP datosConsulta =
				new Ejecutar.ITREPCONLISATRIBEMPL.TREPCONLISATRIBEMPL.EPATRIBUCIONESP();
		
		super.initialize(contexto);
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_EP_CON_LIS_ATRIB_EMPL_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
				
		datosConsulta.setCODNRBEEN(super.getEntidad());
		datosConsulta.setIDINTERNOEMPLEP(empleado.getNumEmpleado());
		
		contexto.getTREPCONLISATRIBEMPL().setEPATRIBUCIONESP(datosConsulta);
		contexto.setSTDTRNIPARMV(contextoEntrada);
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITREPCONLISATRIBEMPL contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaAtribucionesServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta de atribuciones de empleados.", e);
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
		if(response!= null && response.getOTREPCONLISATRIBEMPL() != null &&
				response.getOTREPCONLISATRIBEMPL().getTREPCONLISATRIBEMPL() != null &&
				response.getOTREPCONLISATRIBEMPL().getTREPCONLISATRIBEMPL().getTREPCONLISATRIBEMPL() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
}
