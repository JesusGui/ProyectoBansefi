package mx.babel.bansefi.banksystem.cuentas.backends;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultalimiteconcedido.ConsultaLimiteConcedidoServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultalimiteconcedido.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultalimiteconcedido.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultalimiteconcedido.ResponseBansefi;

@Component
public class ConsultaLimiteConcedidoBackEnd extends BackEndBean{

	private static final long serialVersionUID = -1634351914258364509L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	public BigDecimal ejecutarTRN(Long numeroCuenta){
		BigDecimal limite = new BigDecimal(0);
		Ejecutar.ITRCONSUOPERACACTRNI contexto = initPeticion(numeroCuenta);
		
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		if(verificaResponseBansefi(respuesta)){
			limite = getLimite(respuesta.getResponseBansefi());
		}
		return limite;
	}
		
	private BigDecimal getLimite(ResponseBansefi respuesta){
		BigDecimal limite = new BigDecimal(0);
		try{
			super.verificaResponseBansefi(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return limite;
			}
		}
		if(verificaRespuesta(respuesta)){
			limite = respuesta.getOTRCONSUOPERACACTRNO().getTRCONSUOPERACACEVTZ().getACVLIMCONCEDIDOV().getIMPLIMITE();
		}
		return limite;
	}
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param anotacion anotacion a consultar
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITRCONSUOPERACACTRNI initPeticion(Long numeroCuenta){
		Ejecutar.ITRCONSUOPERACACTRNI contexto = new Ejecutar.ITRCONSUOPERACACTRNI();
		
		Ejecutar.ITRCONSUOPERACACTRNI.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRCONSUOPERACACTRNI.STDTRNIPARMV();
		
		Ejecutar.ITRCONSUOPERACACTRNI.TRCONSUOPERACACEVTY cuerpoContexto =
				new Ejecutar.ITRCONSUOPERACACTRNI.TRCONSUOPERACACEVTY();
		contexto.setTRCONSUOPERACACEVTY(cuerpoContexto);
		contexto.setSTDTRNIPARMV(contextoEntrada);	
		super.initialize(contexto);
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_CONSU_OPERAC_AC_TRN);
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());	
		
		cuerpoContexto.getACACP().setCODNRBEEN(super.getEntidad());
		cuerpoContexto.getACACP().setNUMSECAC(numeroCuenta);
		cuerpoContexto.getCTXV().setCODTX(CodTxConstants.COD_TX_TR_CONSTI_TOTAL_AC_TRN);
		
		contexto.setSCROLLABLEOCCURS(BackEndBean.SCROLLABLE_OCCURS);
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRCONSUOPERACACTRNI contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaLimiteConcedidoServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta de "
					+ "límite concedido.", e);
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
	private Boolean verificaRespuesta(ResponseBansefi response){
		Boolean noNulo = false;
		if(response!= null && response.getOTRCONSUOPERACACTRNO() != null &&
				response.getOTRCONSUOPERACACTRNO().getTRCONSUOPERACACEVTZ() != null &&
				response.getOTRCONSUOPERACACTRNO().getTRCONSUOPERACACEVTZ().getACVLIMCONCEDIDOV() != null){
			noNulo = true;
		}
		return noNulo;
	}
}
