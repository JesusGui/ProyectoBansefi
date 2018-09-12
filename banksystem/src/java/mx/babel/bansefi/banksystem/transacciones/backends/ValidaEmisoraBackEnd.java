package mx.babel.bansefi.banksystem.transacciones.backends;

import java.io.Serializable;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.ReciboBean;
import mx.babel.bansefi.banksystem.transacciones.webservices.validaemisora.EjecutarResult;
import mx.babel.bansefi.banksystem.transacciones.webservices.validaemisora.ValidaEmisoraServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd que valida una emisora, y recupera los datos asociados a esta
 * 
 * @author manuel.nieto
 * 
 */
@Component
public class ValidaEmisoraBackEnd extends BackEndBean implements Serializable {
	
	private static final long serialVersionUID = -3951459195554460665L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Funcion principal que descencadena la ejecución de la TRN
	 * 
	 * @param idEmisora
	 * @return ReciboBean
	 */
	public ReciboBean ejecutarWS(String idEmisora){
				
		EjecutarResult respuesta = null;
		try {
			respuesta = (EjecutarResult) this.servicioWebUtils.ejecutarWS(ValidaEmisoraServicio.class, idEmisora);

		} catch (NoControlableException e) {
			throw new NoControlableException("Error al invocar servicio web de consulta de informacion de emisoras.", e);
		}
		
		if (verificaResponseBansefi(respuesta)) {
			return validaEmisora(respuesta,idEmisora);
		}
		return null;
	}

	/**
	 * Funcion que recibe la respuesta del WS.
	 * 
	 * @param response respuesta de la transacción
	 * @return ValidaEmisoraBean
	 */
	private ReciboBean validaEmisora(EjecutarResult response, String idEmisora){
				
		ReciboBean recibo = new ReciboBean();
		
		recibo.setEmisora(idEmisora);
		
		recibo.setLongitudReferencia(response.getResponseBansefi().getLONREFER().trim());
		
		recibo.setTipoComision(response.getResponseBansefi().getCOMITIPO().trim());
		recibo.setComisionConCuenta(response.getResponseBansefi().getCOMISCTE().trim());
		recibo.setComisionSinCuenta(response.getResponseBansefi().getCOMINCTE().trim());
		
		return recibo;
	}

	/**
	 * Función que valida que la respuesta del servidor no este vacía.
	 * 
	 * @param respuesta Objeto respuesta del servicio web
	 * @return <code>true</code> si la respuesta no esta vacía.
	 */
	private Boolean verificaResponseBansefi(EjecutarResult respuesta) {
		Boolean noNulo = false;
		if (respuesta != null && respuesta.getResponseBansefi() != null) {
			noNulo = true;
		}
		return noNulo;
	}

}
