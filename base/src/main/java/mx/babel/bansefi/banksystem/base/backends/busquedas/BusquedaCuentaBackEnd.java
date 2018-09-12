package mx.babel.bansefi.banksystem.base.backends.busquedas;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.EstadoEnum;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.backends.ConsultaCuentaBackend;
import mx.babel.bansefi.banksystem.base.beans.busqueda.CuentaBusquedaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.enums.CuentaEnum;
import mx.babel.bansefi.banksystem.base.webservices.busquedacuenta.BusquedaCuentaServicio;
import mx.babel.bansefi.banksystem.base.webservices.busquedacuenta.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.busquedacuenta.EjecutarResult;
import mx.babel.bansefi.banksystem.base.wrappers.WrapperBusquedasUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd para ejecutar el servicio de búsqueda por PAN de una Cuenta
 * 
 * @author alejandro.pineda
 * 
 */
@Component
public class BusquedaCuentaBackEnd extends BackEndBean {

	private static final long serialVersionUID = 4307988633328877684L;

	@Autowired
	private WrapperBusquedasUtils wrapperBusquedasUtils;

	@Autowired
	private ServicioWebUtils servicioWebUtils;
	
	@Autowired
	ConsultaCuentaBackend consultaCuentaBackend;
	

	/**
	 * Método que ejecuta el servicio de búsqueda de una cuenta.
	 * 
	 * @param numeroCuenta
	 *            a buscar
	 * @return CuentaBean con los datos recuperados.
	 * @throws ControlableException
	 *             Excepción Controlable del servicio.
	 * @throws NoControlableException
	 *             Excepción No Controlable del servicio.
	 */
	public CuentaBean ejecutarWS(long numeroCuenta)
			throws ControlableException, NoControlableException {

		Ejecutar contextoEntrada = new Ejecutar();

		contextoEntrada.setUSERHEADER(super.getUsuarioId());
		contextoEntrada.setPASSHEADER(super.getPassword());
		contextoEntrada.setIPHEADER(super.getIp());
		contextoEntrada.setENTIDAD(super.getEntidad());

		contextoEntrada.setPAN(BigInteger.valueOf(0L));

		contextoEntrada.setACUERDO(BigInteger.valueOf(numeroCuenta));

		// Se ejecuta el WebService correspondiente
		CuentaBean cuenta = null;

		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils
				.ejecutarWS(BusquedaCuentaServicio.class,
						contextoEntrada.getENTIDAD(), contextoEntrada.getPAN(),
						contextoEntrada.getACUERDO());

		int codigo = Integer.parseInt(respuesta.getESTATUS());

		if (codigo != 0) {
			if (!("ARQE153").equals(respuesta.getCODIGO().trim())
					&& !("ARQE118").equals(respuesta.getCODIGO().trim())
					&& !("ARQE0092").equals(respuesta.getCODIGO().trim())) {
				EstadoEnum.mensajesError("trn", codigo);
			}
		} else {
			if (respuesta.getResponseBansefi().getResponseBansefi().size() == 1) {
				cuenta = wrapperBusquedasUtils.wrappBusquedaCuenta(respuesta
						.getResponseBansefi().getResponseBansefi().get(0));
				String cuentaClabe = getEntidad() + cuenta.getPlazaBancaria()
						+ cuenta.getNumeroCuenta()
						+ cuenta.getDigitoVerificador();
				cuenta.setCuentaClabe(cuentaClabe);
				
				//Definir el tipo de cuenta enum
				if(StringUtils.isNotBlank(cuenta.getCodLinea()) && StringUtils.isNotBlank(cuenta.getIdGrupoProducto())){
					CuentaEnum tipoCuenta = CuentaEnum.getTipoCuenta(cuenta.getCodLinea(), cuenta.getIdGrupoProducto());
					cuenta.setTipoCuentaEnum(tipoCuenta);
				}
			}
		}
		
		return cuenta;
	}

	/**
	 * Método que ejecuta el servicio de búsqueda de PAN de una cuenta.
	 * 
	 * @param busquedaCuenta
	 *            Bean con el PAN a buscar
	 * @return String con la ruta de la ficha de cuenta.
	 * @throws ControlableException
	 *             Excepción Controlable del servicio.
	 * @throws NoControlableException
	 *             Excepción No Controlable del servicio.
	 */
	public List<Object> ejecutarWS(Object busquedaCuenta)
			throws ControlableException, NoControlableException {
		CuentaBusquedaBean cuentaBusqueda = (CuentaBusquedaBean) busquedaCuenta;
		Ejecutar contextoEntrada = new Ejecutar();
		contextoEntrada.setUSERHEADER(super.getUsuarioId());
		contextoEntrada.setPASSHEADER(super.getPassword());
		contextoEntrada.setIPHEADER(super.getIp());
		contextoEntrada.setENTIDAD(super.getEntidad());
		contextoEntrada.setPAN(BigInteger.valueOf(cuentaBusqueda.getNumPan()));

		contextoEntrada.setACUERDO(BigInteger.valueOf(cuentaBusqueda
				.getNumeroCuenta()));

		return this.ejecutarServicio(contextoEntrada, cuentaBusqueda);
	}

	private List<Object> ejecutarServicio(Ejecutar contextoEntrada,
			CuentaBusquedaBean cuentaBusqueda) throws ControlableException,
			NoControlableException {
		List<Object> cuentas = new ArrayList<Object>();
		EjecutarResult respuesta = null;
		int codigo = 0;
		// Se ejecuta el WebService correspondiente
		respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
				BusquedaCuentaServicio.class, contextoEntrada.getENTIDAD(),
				contextoEntrada.getPAN(), contextoEntrada.getACUERDO());

		codigo = Integer.parseInt(respuesta.getESTATUS());

		if (codigo != 0) {
			if (!("ARQE153").equals(respuesta.getCODIGO().trim())
					&& !("ARQE118").equals(respuesta.getCODIGO().trim())
					&& !("ARQE0092").equals(respuesta.getCODIGO().trim())) {
				EstadoEnum.mensajesError("trn", codigo);
			}
			cuentaBusqueda.setMasDatos(false);
		} else {
			if (respuesta.getResponseBansefi().getResponseBansefi().size() == 1) {
				CuentaBean cuenta = wrapperBusquedasUtils
						.wrappBusquedaCuenta(respuesta.getResponseBansefi()
								.getResponseBansefi().get(0));
				String cuentaClabe = getEntidad() + cuenta.getPlazaBancaria()
						+ cuenta.getNumeroCuenta()
						+ cuenta.getDigitoVerificador();
				cuenta.setCuentaClabe(cuentaClabe);
				
				cuenta.setTipoCuenta(this.consultaCuentaBackend.ejecutarTRN(cuenta.getNumeroCuenta()).getTipoCuenta());
				
				cuentas.add(cuenta);
			}
			cuentaBusqueda.setMasDatos(false);
		}

		return cuentas;
	}

}
