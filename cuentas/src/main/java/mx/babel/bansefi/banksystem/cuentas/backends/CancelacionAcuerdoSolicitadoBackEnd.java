package mx.babel.bansefi.banksystem.cuentas.backends;

import java.io.Serializable;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.beans.CancelacionCuentaBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.cancelacionacuerdosolicitado.CancelacionAcuerdoSolicitadoServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.cancelacionacuerdosolicitado.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.cancelacionacuerdosolicitado.Ejecutar.ITRCANCETRAMIACTRNI.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.cancelacionacuerdosolicitado.Ejecutar.ITRCANCETRAMIACTRNI.TRCANCETRAMIACEVTY.ACACP;
import mx.babel.bansefi.banksystem.cuentas.webservices.cancelacionacuerdosolicitado.EjecutarResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Backend que realiza una cancelación de una cuenta que se encuentra en estado SOLICITADO
 * @author manuel.nieto
 *
 */
@Component
public class CancelacionAcuerdoSolicitadoBackEnd extends BackEndBean implements Serializable{
	
	private static final long serialVersionUID = -3290333611755388198L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;

	
	public void ejecutarTRN(final CancelacionCuentaBean cancelacionCuentaBean){
		final Ejecutar.ITRCANCETRAMIACTRNI request = initPeticion(cancelacionCuentaBean);

		final EjecutarResult respuesta = ejecutarWS(request);

		super.verificaRespuesta(respuesta);
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio
	 * web.
	 * 
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRCANCETRAMIACTRNI initPeticion(
			final CancelacionCuentaBean cancelacionCuentaBean) {
		final Ejecutar.ITRCANCETRAMIACTRNI peticion = new Ejecutar.ITRCANCETRAMIACTRNI();

		super.initialize(peticion);
		
		final ACACP acacp = peticion.getTRCANCETRAMIACEVTY().getACACP();
		acacp.setCODNRBEEN(this.getEntidad());
		acacp.setNUMSECAC(cancelacionCuentaBean.getCuentaBean()
				.getNumeroCuenta());

		//Razon de cancelacion
		peticion.getTRCANCETRAMIACEVTY().setCODRZNECVAC(cancelacionCuentaBean.getRazonCancelacion());

		final STDTRNIPARMV stdtrniparmv = peticion.getSTDTRNIPARMV();
		stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_CANCE_TRAMI_AC_TRN);
		stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());		

		return peticion;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto
	 *            Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRCANCETRAMIACTRNI contexto)
			throws NoControlableException {
		EjecutarResult respuesta = null;
		try {
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					CancelacionAcuerdoSolicitadoServicio.class, contexto);
		} catch (final NoControlableException e) {
			throw new NoControlableException(
					"Error al invocar servicio web de cancelacion "
							+ " de acuerdo.", e);
		}
		return respuesta;
	}

}
