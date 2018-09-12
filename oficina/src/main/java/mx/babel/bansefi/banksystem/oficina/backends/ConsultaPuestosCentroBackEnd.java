package mx.babel.bansefi.banksystem.oficina.backends;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.beans.PaginacionBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.oficina.webservices.consultapuestoscentro.ConsultaPuestosCentroServicio;
import mx.babel.bansefi.banksystem.oficina.webservices.consultapuestoscentro.Ejecutar;
import mx.babel.bansefi.banksystem.oficina.webservices.consultapuestoscentro.EjecutarResult;
import mx.babel.bansefi.banksystem.oficina.webservices.consultapuestoscentro.ResponseBansefi;
import mx.babel.bansefi.banksystem.oficina.webservices.consultapuestoscentro.ResponseBansefi.OTRCONLISTATERMCENTT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back End para consultar los cuadres de los puestos en un centro
 * @author mario.montesdeoca	
 *
 */
@Component
public class ConsultaPuestosCentroBackEnd extends BackEndBean{
	
	private static final long serialVersionUID = -400178975538896980L;

	@Autowired
	ServicioWebUtils servicioWebUtils;
	
    /**
	 * Método que consulta los cuadres de los puestos en un centro y los devuelve en una lista
	 * @return Lista de cuadres en puestos
	 */
	public List<String> ejecutarTRN(PaginacionBean paginacion){
		List<String> puestos = new ArrayList<String>();
		Ejecutar.ITRCONLISTATERMCENTT contexto = initPeticion(paginacion);
		
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return puestos;
			}
		}
		
		if(verificaResponseBansefi(respuesta)){
			puestos = getPuestos(respuesta.getResponseBansefi(), paginacion);
		}
		
		return puestos;
	}
	
	/**
	 * Método encargado de construir una lista de cuadres de puesta a partir de la respuesta del ws.
	 * @param respuesta Respuesta del ws
	 * @return lista de cuadres
	 */
	private List<String> getPuestos(ResponseBansefi respuesta, PaginacionBean paginacion){
		List<String> puestos = new ArrayList<String>();		
		if(verificaRespuesta(respuesta)){
			paginacion.adicionaNumeroDatos(respuesta.getOTRCONLISTATERMCENTT().getNUMBEROFRECORDS());
			paginacion.setMasDatos(respuesta.getOTRCONLISTATERMCENTT().getMOREDATAIN() == 1);
			List<OTRCONLISTATERMCENTT.TRCONLISTATERMCENTEVLIST.TRCONLISTATERMCENTEV> terminales = 
					respuesta.getOTRCONLISTATERMCENTT().getTRCONLISTATERMCENTEV().getTRCONLISTATERMCENTEV();
			paginacion.setUltimoDatoConsultaAnterior(terminales.get(respuesta.getOTRCONLISTATERMCENTT().getNUMBEROFRECORDS()-1).getIDINTERNOTERMTN());
			for (int i=0;i < respuesta.getOTRCONLISTATERMCENTT().getNUMBEROFRECORDS(); i++){
				puestos.add(terminales.get(i).getIDINTERNOTERMTN());
			}
		}
		return puestos;
	}
	
	/**
	 * Método para inicializar el objeto de petición al ws.
	 * @return Objeto de petición al ws.
	 */
	private Ejecutar.ITRCONLISTATERMCENTT initPeticion(PaginacionBean paginacion){
		Ejecutar.ITRCONLISTATERMCENTT contexto = new Ejecutar.ITRCONLISTATERMCENTT();
		Ejecutar.ITRCONLISTATERMCENTT.STDTRNIPARMV cuerpoContexto =
				new Ejecutar.ITRCONLISTATERMCENTT.STDTRNIPARMV();
		Ejecutar.ITRCONLISTATERMCENTT.TRCONLISTATERMCENTEV detalle =
				new Ejecutar.ITRCONLISTATERMCENTT.TRCONLISTATERMCENTEV();
		Ejecutar.ITRCONLISTATERMCENTT.TRCONLISTATERMCENTEV.TNTERMINALI1 centro = 
				new Ejecutar.ITRCONLISTATERMCENTT.TRCONLISTATERMCENTEV.TNTERMINALI1();
		contexto.setSTDTRNIPARMV(cuerpoContexto);
		contexto.setTRCONLISTATERMCENTEV(detalle);
		detalle.setTNTERMINALI1(centro);
		super.initialize(contexto);
		
		centro.setCODNRBEENFSC(super.getEntidad());
		centro.setCODINTERNOUOFSC(super.getSucursal());
		if(paginacion.getUltimoDatoConsultaAnterior() != null && 
				!"".equals(paginacion.getUltimoDatoConsultaAnterior())){
			centro.setIDINTERNOTERMTN((String)paginacion.getUltimoDatoConsultaAnterior());
		}
		
		cuerpoContexto.setIDINTERNOTERMTN(super.getTerminal());
		cuerpoContexto.setCODTX(CodTxConstants.COD_TX_TR_CON_LISTA_TERM_CENT_TRN);
		
		contexto.setELEVATORPOSITION(1);
		contexto.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRCONLISTATERMCENTT contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaPuestosCentroServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "de puestos de centro.", e);
		}
		return respuesta;
	}
	
	/**
	 * Función que valida que la respues del servidor no este vacía. 
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
	 * de cuentas.
	 * 
	 * @param response Respuesta Bansefi con datos de cuentas
	 * @return <code>true</code> si la respuesta Bansefi contiene los datos de alta
	 */
	private Boolean verificaRespuesta(ResponseBansefi response){
		Boolean noNulo = false;
		if(response != null && response.getOTRCONLISTATERMCENTT() != null && 
				response.getOTRCONLISTATERMCENTT().getTRCONLISTATERMCENTEV() != null &&
				response.getOTRCONLISTATERMCENTT().getTRCONLISTATERMCENTEV().getTRCONLISTATERMCENTEV() != null &&
				!response.getOTRCONLISTATERMCENTT().getTRCONLISTATERMCENTEV().getTRCONLISTATERMCENTEV().isEmpty()){
			noNulo = true;
		}
		return noNulo;
	}
}
