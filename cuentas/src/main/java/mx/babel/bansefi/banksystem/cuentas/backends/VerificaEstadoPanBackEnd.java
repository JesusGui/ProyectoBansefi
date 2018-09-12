package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.webservices.verificaestadopan.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.verificaestadopan.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.verificaestadopan.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.webservices.verificaestadopan.VerificaEstadoPanServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VerificaEstadoPanBackEnd extends BackEndBean{

	private static final long serialVersionUID = -3766057705937779509L;

	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	public String[] ejecutarTRN(String numeroPan){
		String[] estadoCentro = null;
		Ejecutar.ITRLSTSTOCKTARJETASTR contexto = initPeticion(numeroPan);
		
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return null;
			}
		}
		
		if(verificaResponseBansefi(respuesta)){
			estadoCentro = getEstadoPan(respuesta.getResponseBansefi());
		}
		return estadoCentro;
	}
	
	private String[] getEstadoPan(ResponseBansefi respuesta){
		String estado = null;
		String centro = null;
		if(verificaRespuestaCliente(respuesta)){
			estado = respuesta.getOTRLSTSTOCKTARJETASTR().getTRLSTSTOCKTARJETASEVTZ().getMPSTOCKTARJETASE().get(0).getECVSTOCKTJ();
			centro = respuesta.getOTRLSTSTOCKTARJETASTR().getTRLSTSTOCKTARJETASEVTZ().getMPSTOCKTARJETASE().get(0).getCODINTERNOUO();
		}
		return new String[] {estado,centro};
	}
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param numeroPan numero de pan a consultar
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITRLSTSTOCKTARJETASTR initPeticion(String numeroPan){
		Ejecutar.ITRLSTSTOCKTARJETASTR contexto = new Ejecutar.ITRLSTSTOCKTARJETASTR();
		Ejecutar.ITRLSTSTOCKTARJETASTR.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRLSTSTOCKTARJETASTR.STDTRNIPARMV();
		Ejecutar.ITRLSTSTOCKTARJETASTR.TRLSTSTOCKTARJETASEVTY cuerpoContexto =
				new Ejecutar.ITRLSTSTOCKTARJETASTR.TRLSTSTOCKTARJETASEVTY();
		Ejecutar.ITRLSTSTOCKTARJETASTR.TRLSTSTOCKTARJETASEVTY.MPSTOCKTARJETASP stock =
				new Ejecutar.ITRLSTSTOCKTARJETASTR.TRLSTSTOCKTARJETASEVTY.MPSTOCKTARJETASP();
		contexto.setTRLSTSTOCKTARJETASEVTY(cuerpoContexto);
		cuerpoContexto.setMPSTOCKTARJETASP(stock);
		contexto.setSTDTRNIPARMV(contextoEntrada);	
		super.initialize(contexto);
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_LST_STOCK_TARJETAS_TRN);
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());	
		
		contexto.setSCROLLABLEOCCURS(BackEndBean.SCROLLABLE_OCCURS);
		
		stock.setCODNRBEEN(super.getEntidad());
		stock.setPAN(numeroPan);
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRLSTSTOCKTARJETASTR contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(VerificaEstadoPanServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta de "
					+ "estado de pan.", e);
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
	 * del pan
	 * 
	 * @param response Respuesta Bansefi con datos del pan
	 * @return <code>true</code> si la respuesta Bansefi contiene elemento no nulos
	 */
	private Boolean verificaRespuestaCliente(ResponseBansefi response){
		Boolean noNulo = false;
		if(response!= null && response.getOTRLSTSTOCKTARJETASTR() != null &&
				response.getOTRLSTSTOCKTARJETASTR().getTRLSTSTOCKTARJETASEVTZ() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
}
