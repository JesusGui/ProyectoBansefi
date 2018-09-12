package mx.babel.bansefi.banksystem.cuentas.backends;

import java.io.Serializable;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.beans.SuspenderCuentaBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.suspendercuenta.*;
import mx.babel.bansefi.banksystem.cuentas.webservices.suspendercuenta.Ejecutar.ITRSUSPENDERACTRNI.STDTRNIPARMV;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SuspenderCuentaBackEnd extends BackEndBean implements Serializable{

	private static final long serialVersionUID = 8204409120894795070L;
	
    @Autowired
    ServicioWebUtils servicioWebUtils;

	public void ejecutarTRN(SuspenderCuentaBean suspenderCuentaBean) throws ControlableException, NoControlableException{
		final Ejecutar.ITRSUSPENDERACTRNI request = initPeticion(suspenderCuentaBean);

		final EjecutarResult respuesta = ejecutarWS(request);

		 super.verificaRespuesta(respuesta);
		
		if(!verificaResponseBansefi(respuesta)){
		    throw new NoControlableException("Ha ocurrido un error en la retrocesion de la constitucion de un acuerdo."
		            , "La respuesta de la retrocesion de la constitucion de un acuerdo es nula.");
		}
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRSUSPENDERACTRNI initPeticion(SuspenderCuentaBean suspenderCuentaBean){
		final Ejecutar.ITRSUSPENDERACTRNI peticion = new Ejecutar.ITRSUSPENDERACTRNI();

		super.initialize(peticion);

        final STDTRNIPARMV stdtrniparmv = peticion.getSTDTRNIPARMV();
        
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_SUSPENDER_AC_TRN);
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());

		return peticion;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRSUSPENDERACTRNI contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
			        SuspenderCuentaServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de retrocesion "
					+ " de constitucion de acuerdo.", e);
		}
		return respuesta;
	}

	/**
	 * Función que valida que la respuesta del servidor no este vacía.
	 *
	 * @param respuesta Objeto respuesta del servicio web
	 * @return <code>true</code> si la respuesta no esta vacía.
	 */
	private Boolean verificaResponseBansefi(final EjecutarResult respuesta){
		return (respuesta != null && respuesta.getResponseBansefi() != null);
	}


}
