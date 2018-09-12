package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.HashMap;
import java.util.Map;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultarelacionestarifa.ConsultaRelacionesTarifaServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultarelacionestarifa.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultarelacionestarifa.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultarelacionestarifa.ResponseBansefi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back End para consulta de relaciones de un producto.
 * @author mario.montesdeoca
 *
 */
@Component
public class ConsultaRelacionesTarifaBackEnd extends BackEndBean{
	
	private static final long serialVersionUID = 4377569785458318933L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;

	public Map<String,Boolean> ejecutarTRN(CuentaBean cuentaBean){
		Map<String,Boolean> relacionesObligatorias = new HashMap<String,Boolean>();
		Ejecutar.ITRLISTALGRLPERSTRN contexto = initPeticion(cuentaBean);
		
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return relacionesObligatorias;
			}
		}
		
		return getRelaciones(respuesta.getResponseBansefi());
	}
	
	/**
	 * Función encargada de obtener el cliente a partir de la respuesta del servicio web
	 * 
	 * @param idInterno El id interno de la persona moral
	 * @param response El objeto de reultado enviado por el servicio web.
	 * @return
	 */
	private Map<String,Boolean> getRelaciones(ResponseBansefi response){
		Map<String,Boolean> relacionesObligatorias = new HashMap<String,Boolean>();
		if(verificaRespuesta(response)){
			for (ResponseBansefi.OTRLISTALGRLPERSTRN.TRLISTALGRLPERSEVTZ.LGPERSACE relacion : 
				response.getOTRLISTALGRLPERSTRN().getTRLISTALGRLPERSEVTZ().getLGPERSACE()) {
				if("S".equals(relacion.getINDOBLIG())){
					relacionesObligatorias.put(relacion.getCODRLPERSAC(),true);
				}else{
					relacionesObligatorias.put(relacion.getCODRLPERSAC(),false);
				}
			}
		}
		
		return relacionesObligatorias;
	}
	
	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 * 
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRLISTALGRLPERSTRN initPeticion(CuentaBean cuentaBean){
		Ejecutar.ITRLISTALGRLPERSTRN contexto = new Ejecutar.ITRLISTALGRLPERSTRN();
		Ejecutar.ITRLISTALGRLPERSTRN.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRLISTALGRLPERSTRN.STDTRNIPARMV();
		Ejecutar.ITRLISTALGRLPERSTRN.TRLISTALGRLPERSEVTY cuerpoContexto =
				new Ejecutar.ITRLISTALGRLPERSTRN.TRLISTALGRLPERSEVTY();
		Ejecutar.ITRLISTALGRLPERSTRN.TRLISTALGRLPERSEVTY.LGPERSACP tarifa =
				new Ejecutar.ITRLISTALGRLPERSTRN.TRLISTALGRLPERSEVTY.LGPERSACP();
		cuerpoContexto.setLGPERSACP(tarifa);
		contexto.setTRLISTALGRLPERSEVTY(cuerpoContexto);
		contexto.setSTDTRNIPARMV(contextoEntrada);
		super.initialize(contexto);
		
		tarifa.setCODLINEA(cuentaBean.getCodLinea());
		tarifa.setIDGRPPD(cuentaBean.getIdGrupoProducto());
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_LISTA_LG_RL_PERS_TRN);
		contextoEntrada.setIDINTERNOTERMTN(getTerminal());
		
		contexto.setSCROLLABLEOCCURS(BackEndBean.SCROLLABLE_OCCURS);
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRLISTALGRLPERSTRN contexto) 
			throws NoControlableException{
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaRelacionesTarifaServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de alta de "
					+ "relación persona-cuenta.", e);
		}
		return respuesta;
	}
	
	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con los datos
	 * del alta de realción.
	 * 
	 * @param response Respuesta Bansefi con datos del alta
	 * @return <code>true</code> si la respuesta Bansefi contiene los datos de alta
	 */
	private Boolean verificaRespuesta(ResponseBansefi response){
		Boolean noNulo = false;
		if(response != null && response.getOTRLISTALGRLPERSTRN() != null && 
				response.getOTRLISTALGRLPERSTRN().getTRLISTALGRLPERSEVTZ() != null &&
				response.getOTRLISTALGRLPERSTRN().getTRLISTALGRLPERSEVTZ().getLGPERSACE() != null &&
				!response.getOTRLISTALGRLPERSTRN().getTRLISTALGRLPERSEVTZ().getLGPERSACE().isEmpty()){
			noNulo = true;
		}
		return noNulo;
	}
	
}
