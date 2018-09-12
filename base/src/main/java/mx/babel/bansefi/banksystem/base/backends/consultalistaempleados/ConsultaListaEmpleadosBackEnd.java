package mx.babel.bansefi.banksystem.base.backends.consultalistaempleados;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.cache.annotation.Cache;
import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.utils.ProveedorMensajeSpringUtils;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.busqueda.EmpleadoBusquedaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.webservices.consultalistaempleados.ConsultaListaEmpleadosServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultalistaempleados.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultalistaempleados.EjecutarResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para consulta de empleados a traves de un centro o perfil
 * 
 * @author javier.martinnino
 *
 */
@Component
public class ConsultaListaEmpleadosBackEnd extends BackEndBean implements Serializable {
	

	private static final long serialVersionUID = 3957124410460011599L;
	
	private static final String PERFILES = "PERFILES";
	private static final String CENTROS = "CENTROS";
	private static final Logger LOGGER = LogManager.getLogger(ConsultaListaEmpleadosBackEnd.class.getName());
	
		
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	/**
	 * Función encargada de consultar un empleado por medio de servicios web para el uso como catalogo.
	 * 
	 * @param entidad La entidad a Consultar
	 * @return List<CatalogoBean> de Empleado con los datos recuperados
	 */
	@Cache
	public List<CatalogoBean> ejecutarTRN (String entidad){
		
		final List<CatalogoBean> result = new ArrayList<CatalogoBean>();
		
		Ejecutar.ITRCONLISTAEMPLTRNI contexto = new Ejecutar.ITRCONLISTAEMPLTRNI();
		
		Ejecutar.ITRCONLISTAEMPLTRNI.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRCONLISTAEMPLTRNI.STDTRNIPARMV();
		
		Ejecutar.ITRCONLISTAEMPLTRNI.TRCONLISTAEMPLEVTY datosEntrada =
				new Ejecutar.ITRCONLISTAEMPLTRNI.TRCONLISTAEMPLEVTY();
		
		Ejecutar.ITRCONLISTAEMPLTRNI.TRCONLISTAEMPLEVTY.EPEMPLP datosCentro =
				new Ejecutar.ITRCONLISTAEMPLTRNI.TRCONLISTAEMPLEVTY.EPEMPLP();
				
		super.initialize(contexto);
		
		
		contexto.setELEVATORPOSITION(1);
		contexto.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);
		contexto.setACTNCD(ConsultaListaEmpleadosBackEnd.CENTROS);
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_CON_LISTA_EMPL_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		
		contexto.setSTDTRNIPARMV(contextoEntrada);		
		
		datosCentro.setCODNRBEEN(entidad);		
		datosEntrada.setEPEMPLP(datosCentro);
		contexto.setTRCONLISTAEMPLEVTY(datosEntrada);
		
		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS( ConsultaListaEmpleadosServicio.class, contexto);
		
		try{
			super.verificaRespuesta(respuesta);
			for (int i=0;i<respuesta.getResponseBansefi().getOTRCONLISTAEMPLTRNO().getNUMBEROFRECORDS();i++){
				CatalogoBean catalogo = new CatalogoBean();
				catalogo.setClaveFila(respuesta.getResponseBansefi().getOTRCONLISTAEMPLTRNO().getTRCONLISTAEMPLEVTZ().getEPEMPLE().get(i).getIDINTERNOEMPLEP());
				catalogo.setDescripcionC(respuesta.getResponseBansefi().getOTRCONLISTAEMPLTRNO().getTRCONLISTAEMPLEVTZ().getEPEMPLE().get(i).getIDINTERNOEMPLEP());
				catalogo.setDescripcionL(respuesta.getResponseBansefi().getOTRCONLISTAEMPLTRNO().getTRCONLISTAEMPLEVTZ().getEPEMPLE().get(i).getIDINTERNOEMPLEP());
				result.add(catalogo);
			}
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return result;
			}
		}
		
		boolean hayMasDatos = respuesta.getResponseBansefi().getOTRCONLISTAEMPLTRNO().getMOREDATAIN() == 1;
		while (hayMasDatos){
			datosCentro.setIDINTERNOEMPLEP(respuesta.getResponseBansefi().getOTRCONLISTAEMPLTRNO().getTRCONLISTAEMPLEVTZ().getEPEMPLE().get(
					respuesta.getResponseBansefi().getOTRCONLISTAEMPLTRNO().getNUMBEROFRECORDS()-1).getIDINTERNOEMPLEP());
	        
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS( ConsultaListaEmpleadosServicio.class, contexto);
			
			try{
				super.verificaRespuesta(respuesta);
				for (int i=0;i<respuesta.getResponseBansefi().getOTRCONLISTAEMPLTRNO().getNUMBEROFRECORDS();i++){
					CatalogoBean catalogo = new CatalogoBean();
					catalogo.setClaveFila(respuesta.getResponseBansefi().getOTRCONLISTAEMPLTRNO().getTRCONLISTAEMPLEVTZ().getEPEMPLE().get(i).getIDINTERNOEMPLEP());
					catalogo.setDescripcionC(respuesta.getResponseBansefi().getOTRCONLISTAEMPLTRNO().getTRCONLISTAEMPLEVTZ().getEPEMPLE().get(i).getIDINTERNOEMPLEP());
					catalogo.setDescripcionL(respuesta.getResponseBansefi().getOTRCONLISTAEMPLTRNO().getTRCONLISTAEMPLEVTZ().getEPEMPLE().get(i).getIDINTERNOEMPLEP());
					result.add(catalogo);
				}
			}catch (ControlableException ce){
				if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
					throw ce;
				}else{
					return result;
				}
			}
			
        }
		return result;
	}
	
	/**
	 * Función encargada de consultar un empleado por medio de servicios web desde el menu de Busquedas.
	 * 
	 * @param valor centro o perfil a consultar
	 * @param tipoResultado esperado
	 * @return Bean de Empleado con los datos recuperados
	 */
	public List<Object> ejecutarTRN(Object obj, String tipoBusqueda){
		
		LOGGER.debug("Accedemos a la busqueda de lista de empleados por: " + tipoBusqueda);
		
		List<Object> result = new ArrayList<Object>();
		
		Ejecutar.ITRCONLISTAEMPLTRNI contexto = new Ejecutar.ITRCONLISTAEMPLTRNI();
		
		Ejecutar.ITRCONLISTAEMPLTRNI.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRCONLISTAEMPLTRNI.STDTRNIPARMV();
		
		Ejecutar.ITRCONLISTAEMPLTRNI.TRCONLISTAEMPLEVTY datosEntrada =
				new Ejecutar.ITRCONLISTAEMPLTRNI.TRCONLISTAEMPLEVTY();
		
		Ejecutar.ITRCONLISTAEMPLTRNI.TRCONLISTAEMPLEVTY.EPEMPLP datosCentro =
				new Ejecutar.ITRCONLISTAEMPLTRNI.TRCONLISTAEMPLEVTY.EPEMPLP();
		
		Ejecutar.ITRCONLISTAEMPLTRNI.TRCONLISTAEMPLEVTY.EPEMPLV datosOficina =
				new Ejecutar.ITRCONLISTAEMPLTRNI.TRCONLISTAEMPLEVTY.EPEMPLV();
		
		Ejecutar.ITRCONLISTAEMPLTRNI.TRCONLISTAEMPLEVTY.EPEMPLPERFILTXP datosPerfil =
				new Ejecutar.ITRCONLISTAEMPLTRNI.TRCONLISTAEMPLEVTY.EPEMPLPERFILTXP();
		
		super.initialize(contexto);
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_CON_LISTA_EMPL_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		
		datosCentro.setCODNRBEEN(super.getEntidad());
		
		// Comprobamos si hay que paginar 
		if (((EmpleadoBusquedaBean)obj).getUltimoDatoConsultaAnterior() !=null){
			String idInternoEmplUltBus = (String)((EmpleadoBusquedaBean)obj).getUltimoDatoConsultaAnterior();
			datosCentro.setIDINTERNOEMPLEP(idInternoEmplUltBus);
			datosPerfil.setIDINTERNOEMPLEP(idInternoEmplUltBus);
		}else{
			datosCentro.setIDINTERNOEMPLEP("");
			datosPerfil.setIDINTERNOEMPLEP("");
		}
				
		if (tipoBusqueda.equals(ConsultaListaEmpleadosBackEnd.CENTROS)){
			String idCentro = ((EmpleadoBusquedaBean) obj).getCodigoCentro();
			LOGGER.debug("Consultamos los empleados del centro: " + idCentro + " de la entidad " + super.getEntidad());
			datosOficina.setCODINTERNOUO(idCentro);
			datosOficina.setINDMASCENT("");
			datosEntrada.setEPEMPLP(datosCentro);
			datosEntrada.setEPEMPLV(datosOficina);
			contexto.setACTNCD(ConsultaListaEmpleadosBackEnd.CENTROS);
			contexto.setTRCONLISTAEMPLEVTY(datosEntrada);
		} else if (tipoBusqueda.equals(ConsultaListaEmpleadosBackEnd.PERFILES)){
			String perfil = ((EmpleadoBusquedaBean) obj).getPerfil();
			LOGGER.debug("Consultamos los empleados con perfil: " + perfil + " de la entidad " + super.getEntidad());
			datosPerfil.setCODNRBEEN(super.getEntidad());
			datosPerfil.setNOMPERFILEN(perfil);
			datosPerfil.setFECHAOPRCN(0);
			datosPerfil.setHORAOPRCN(0);
			datosEntrada.setEPEMPLPERFILTXP(datosPerfil);
			contexto.setACTNCD(ConsultaListaEmpleadosBackEnd.PERFILES);
			contexto.setTRCONLISTAEMPLEVTY(datosEntrada);
		}
				
		contexto.setSTDTRNIPARMV(contextoEntrada);
		contexto.setELEVATORPOSITION(1);
		contexto.setSCROLLABLEOCCURS(50);
		super.initialize(contexto);
		
		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS( ConsultaListaEmpleadosServicio.class, contexto);
				
		try{
			super.verificaRespuesta(respuesta);
			// Comprobamos si la busqueda tiene paginacion
			if (respuesta.getResponseBansefi().getOTRCONLISTAEMPLTRNO().getMOREDATAIN() == 1) {
				((EmpleadoBusquedaBean)obj).setMasDatos(true);
				((EmpleadoBusquedaBean)obj).setUltimoDatoConsultaAnterior(respuesta.getResponseBansefi().getOTRCONLISTAEMPLTRNO().getTRCONLISTAEMPLEVTZ().getEPEMPLE().get(
						respuesta.getResponseBansefi().getOTRCONLISTAEMPLTRNO().getNUMBEROFRECORDS()-1).getIDINTERNOEMPLEP());
			}
			for (int i=0;i<respuesta.getResponseBansefi().getOTRCONLISTAEMPLTRNO().getNUMBEROFRECORDS();i++){
				EmpleadoBusquedaBean empleado = new EmpleadoBusquedaBean();
				empleado.setIdEmpleado(respuesta.getResponseBansefi().getOTRCONLISTAEMPLTRNO().getTRCONLISTAEMPLEVTZ().getEPEMPLE().get(i).getIDINTERNOEMPLEP().trim());
				empleado.setCodigoCentro(respuesta.getResponseBansefi().getOTRCONLISTAEMPLTRNO().getTRCONLISTAEMPLEVTZ().getEPEMPLV().get(i).getCODINTERNOUO().trim());
				empleado.setIdInterna((respuesta.getResponseBansefi().getOTRCONLISTAEMPLTRNO().getTRCONLISTAEMPLEVTZ().getEPEMPLE().get(i).getIDINTERNOPE()));
				empleado.setNombre(respuesta.getResponseBansefi().getOTRCONLISTAEMPLTRNO().getTRCONLISTAEMPLEVTZ().getPEPERSDESNORV().get(i).getNOMB50().trim());
				empleado.setPerfil(respuesta.getResponseBansefi().getOTRCONLISTAEMPLTRNO().getTRCONLISTAEMPLEVTZ().getEPEMPLPERFILTXE().get(i).getNOMPERFILEN().trim());
				result.add(empleado);
			}
			
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				LOGGER.debug("No se han encontrado resultados para la busqueda");
				return result;
			}
		}		
		return result;				
	}
		
}
