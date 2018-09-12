package mx.babel.bansefi.banksystem.base.backends.consultasaldo;

import java.math.BigInteger;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.SaldoBean;
import mx.babel.bansefi.banksystem.base.webservices.consultasaldoservicio.ConsultaSaldoServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultasaldoservicio.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultasaldoservicio.ResponseBansefi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaSaldoBackEnd extends BackEndBean {

	private static final long serialVersionUID = 5483410108622890788L;
	@Autowired
	ServicioWebUtils servicioWebUtils;

	public SaldoBean consultaSaldo(BigInteger numeroCuenta,
			String codigoDocumento, String idExterna)
			throws ControlableException, NoControlableException {
		SaldoBean response = null;

		EjecutarResult respuesta = null;
		try {
			// Realizamos la llamada al servicio
			// TODO sustituir codigo de documento que va harcodeado con un "10"
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaSaldoServicio.class, super.getEntidad(),
					numeroCuenta, codigoDocumento, idExterna);
		} catch (NumberFormatException nfe) {
			throw new ControlableException("No se puede realizar la consulta",
					nfe.getCause());
		} catch (IllegalArgumentException iae) {
			throw new ControlableException("No se puede realizar la consulta",
					iae.getCause());
		}
		if (respuesta != null) {
			response = new SaldoBean();
			response.setEstatus(respuesta.getESTATUS());
			response.setCodigo(respuesta.getCODIGO());
			response.setMensaje(respuesta.getMENSAJE());
			response.setNumeroTarea(respuesta.getNUMTASK());
			response.setTransaccionId(respuesta.getTRANID());
			if (respuesta.getResponseBansefi() != null) {
				ResponseBansefi responseBansefi = respuesta
						.getResponseBansefi();
				response.setCentro(responseBansefi.getCENTRO());
				response.setDc(responseBansefi.getDC());
				response.setDisponible(responseBansefi.getDISPONIBLE()
						.movePointLeft(2));
				response.setTotalAutorizado(responseBansefi
						.getTOTALAUTORIZADO().movePointLeft(2));
				response.setSaldoContable(responseBansefi.getSALDOCONTABLE()
						.movePointLeft(2));
				response.setTotalRetenido(responseBansefi.getTOTALRETENIDO()
						.movePointLeft(2));
				response.setTitular(responseBansefi.getTITULAR());
				response.setMoneda(responseBansefi.getMONEDA());
			} else {
				throw new ControlableException(
						"No se puede realizar la consulta",
						"Alguno de los parametros de salida es vacio");
			}
		} else {
			throw new NoControlableException("No hay respuesta al servicio",
					"La respuesta es null");
		}

		return response;
	}

}