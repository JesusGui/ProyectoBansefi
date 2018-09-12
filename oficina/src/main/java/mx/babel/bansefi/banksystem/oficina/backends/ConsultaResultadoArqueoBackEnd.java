package mx.babel.bansefi.banksystem.oficina.backends;

import java.math.BigDecimal;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.oficina.beans.ArqueoCentroBean;
import mx.babel.bansefi.banksystem.oficina.webservices.consultaresultadoarqueo.ConsultaResultadoArqueoServicio;
import mx.babel.bansefi.banksystem.oficina.webservices.consultaresultadoarqueo.Ejecutar;
import mx.babel.bansefi.banksystem.oficina.webservices.consultaresultadoarqueo.EjecutarResult;
import mx.babel.bansefi.banksystem.oficina.webservices.consultaresultadoarqueo.ResponseBansefi;
import mx.babel.bansefi.banksystem.oficina.wrappers.ArqueoCentroWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back End para la consulta de resultado de un arqueo
 * @author mario.montesdeoca
 *
 */
@Component
public class ConsultaResultadoArqueoBackEnd extends BackEndBean{

	private static final long serialVersionUID = -1023595824752299557L;
    
	private static final String CTE_ARQUEO_REALIZADO = "Q"; 
	
    @Autowired
	ArqueoCentroWrapper arqueoCentroWrapper;
	
    @Autowired
    ServicioWebUtils servicioWebUtils;
    
	/**
	 * Método que consulta la respuesta de un arqueo a partir de un ws
	 * @param arqueoCentro Bean con detalles del arqueo de centro
	 * @return <code>true</code> si la consulta fue exitosa
	 */
	public Boolean ejecutarTRN(ArqueoCentroBean arqueoCentro){
		if(arqueoCentro != null){
			Ejecutar.ITROBTEDATOSARQUEOTRN contexto = initPeticion();
			
			EjecutarResult respuesta = ejecutarWS(contexto);
			
			try{
				super.verificaRespuesta(respuesta);
			}catch (ControlableException ce){
				if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
					throw ce;
				}else{
					return false;
				}
			}
			
			if(verificaResponseBansefi(respuesta)){
				return initArqueo(respuesta.getResponseBansefi(), arqueoCentro);
			}
		}
		return false;
	}
	
	/**
	 * método para incializar los datos de un arqueo de centro a partir de la respuesta del ws
	 * @param response respuesta del ws
	 * @param arqueoCentro bean con detalles del arqueo
	 * @return <code>true</code> si la consulta fue exitosa
	 */
	private Boolean initArqueo(ResponseBansefi response, ArqueoCentroBean arqueoCentro){		
		if(verificaRespuesta(response)){
			arqueoCentroWrapper.wrappBean(response.getOTROBTEDATOSARQUEOTRN().getTROBTEDATOSARQUEOEVT(), arqueoCentro);
			if((ConsultaResultadoArqueoBackEnd.CTE_ARQUEO_REALIZADO.equals(response.getOTROBTEDATOSARQUEOTRN().getTROBTEDATOSARQUEOEVT().getEXEXISTMONEDAE().getCODECVEX()))){
				arqueoCentro.setArqueada(true);
			}else{
				arqueoCentro.setArqueada(false);
			}
			BigDecimal saldoCajaActual = new BigDecimal(0);
			if(arqueoCentro.getSaldoContableCajaAnterior() != null &&
					arqueoCentro.getPagosOn() != null && arqueoCentro.getCobrosOn() != null){
				saldoCajaActual = arqueoCentro.getSaldoContableCajaAnterior().add(arqueoCentro.getCobrosOn());
				saldoCajaActual = saldoCajaActual.add(arqueoCentro.getPagosOn().negate());
				arqueoCentro.setSaldoContableCajaActual(saldoCajaActual);
			}
			arqueoCentro.setSaldoContableCajaActual(saldoCajaActual);
			return true;
		}
		return false;
	}
	
	/**
	 * Método para incializar el objeto de petición al ws
	 * @return Objeto de petición al ws
	 */
	private Ejecutar.ITROBTEDATOSARQUEOTRN initPeticion(){
		Ejecutar.ITROBTEDATOSARQUEOTRN contexto = new Ejecutar.ITROBTEDATOSARQUEOTRN();
		Ejecutar.ITROBTEDATOSARQUEOTRN.STDTRNIPARMV cuerpoContexto =
				new Ejecutar.ITROBTEDATOSARQUEOTRN.STDTRNIPARMV();
		Ejecutar.ITROBTEDATOSARQUEOTRN.TROBTEDATOSARQUEOEVT moneda =
				new Ejecutar.ITROBTEDATOSARQUEOTRN.TROBTEDATOSARQUEOEVT();
		contexto.setSTDTRNIPARMV(cuerpoContexto);
		contexto.setTROBTEDATOSARQUEOEVT(moneda);
		super.initialize(contexto);
		
		cuerpoContexto.setCODTX(CodTxConstants.COD_TX_TR_OBTE_DATOS_ARQUEO_TRN);
		cuerpoContexto.setIDINTERNOTERMTN(super.getTerminal());
		
		moneda.setCODNUMRCOMONEDA("MXN");
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	public EjecutarResult ejecutarWS(Ejecutar.ITROBTEDATOSARQUEOTRN contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaResultadoArqueoServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "de arqueo de centro.", e);
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
		if(response != null && response.getOTROBTEDATOSARQUEOTRN() != null && 
				response.getOTROBTEDATOSARQUEOTRN().getTROBTEDATOSARQUEOEVT() != null &&
				response.getOTROBTEDATOSARQUEOTRN().getTROBTEDATOSARQUEOEVT().getEXEXISTMONEDAE() != null &&
				response.getOTROBTEDATOSARQUEOTRN().getTROBTEDATOSARQUEOEVT().getCCSDOPNTALCTBLEE() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
}
