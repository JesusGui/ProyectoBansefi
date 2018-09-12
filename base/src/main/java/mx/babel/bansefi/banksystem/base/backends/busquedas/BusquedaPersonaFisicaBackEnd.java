package mx.babel.bansefi.banksystem.base.backends.busquedas;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.EstadoEnum;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.busqueda.PersonasClienteBusquedaBean;
import mx.babel.bansefi.banksystem.base.webservices.busquedapersonasfisicas.BusquedaPersonaFisicaServicio;
import mx.babel.bansefi.banksystem.base.webservices.busquedapersonasfisicas.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.busquedapersonasfisicas.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.busquedapersonasfisicas.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.wrappers.WrapperBusquedasUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd para la búsqueda de de Personas Físicas.
 * 
 * @author alejandro.pineda
 * 
 */
@Component
public class BusquedaPersonaFisicaBackEnd extends BackEndBean {

	private static final long serialVersionUID = 4307988633328877684L;

	@Autowired
	private WrapperBusquedasUtils wrapperBusquedasUtils;

	@Autowired
	private ServicioWebUtils servicioWebUtils;

	public BusquedaPersonaFisicaBackEnd() {

	}

	/**
	 * Método que ejecuta el servicio de búsqueda de Persona Física.
	 * 
	 * @param busquedaPersona
	 *            Bean con el PAN a buscar
	 * @return String con la ruta de la ficha de cuenta.
	 * @throws ControlableException
	 *             Excepción Controlable del servicio.
	 * @throws NoControlableException
	 *             Excepción No Controlable del servicio.
	 */
	public List<Object> ejecutarWS(Object busquedaPersona)
			throws ControlableException, NoControlableException {
		PersonasClienteBusquedaBean personaFisica = (PersonasClienteBusquedaBean) busquedaPersona;
		if (personaFisica.getNombre() == null && personaFisica.getApellidoPaterno() == null && personaFisica.getApellidoMaterno() == null && personaFisica.getNoIdentificador() == null && personaFisica.getIdInterna() == 0) {
			return new ArrayList<Object>();
		}
		Ejecutar contextoEntrada = new Ejecutar();
		contextoEntrada.setUSERHEADER(super.getUsuarioId());
		contextoEntrada.setPASSHEADER(super.getPassword());
		contextoEntrada.setIPHEADER(super.getIp());
		contextoEntrada.setENTIDAD(super.getEntidad());

		if (personaFisica.getNombre() != null) {
			contextoEntrada.setNOMBRE(personaFisica.getNombre().toUpperCase());
		}

		if (personaFisica.getApellidoPaterno() != null) {
			contextoEntrada.setAPPATERNO(personaFisica.getApellidoPaterno()
					.toUpperCase());
		}

		if (personaFisica.getApellidoMaterno() != null) {
			contextoEntrada.setAPMATERNO(personaFisica.getApellidoMaterno()
					.toUpperCase());
		}

		if (personaFisica.getNoIdentificador() != null) {
			contextoEntrada.setIDEXTERNO(personaFisica.getNoIdentificador()
					.toUpperCase());
		}

		contextoEntrada.setIDINTERNO(BigInteger.valueOf(Long.parseLong(Integer
				.toString(personaFisica.getIdInterna()))));
		
		contextoEntrada.setNUMIFE("000000000000000000");

		return this.ejecutarServicio(contextoEntrada, personaFisica);
	}

	private List<Object> ejecutarServicio(Ejecutar contextoEntrada,
			PersonasClienteBusquedaBean personaFisica)
			throws ControlableException, NoControlableException {
		EjecutarResult respuesta = null;
		List<Object> listaDatos = new ArrayList<>();
		int codigo = 0;
		// Se ejecuta el WebService correspondiente
		respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
				BusquedaPersonaFisicaServicio.class,
				contextoEntrada.getENTIDAD(), contextoEntrada.getNUMIFE(),
				contextoEntrada.getIDINTERNO(), contextoEntrada.getIDEXTERNO(),
				contextoEntrada.getNOMBRE(), contextoEntrada.getAPPATERNO(),
				contextoEntrada.getAPMATERNO());

		codigo = Integer.parseInt(respuesta.getESTATUS());

		if (codigo != 0) {
			if (!("ARQE153").equals(respuesta.getCODIGO().trim())
					&& !("ARQE118").equals(respuesta.getCODIGO().trim())) {
				EstadoEnum.mensajesError("trn", codigo);
			}
		} else {
			for (ResponseBansefi salida : respuesta.getResponseBansefi()
					.getResponseBansefi()) {
				PersonasClienteBusquedaBean dato = wrapperBusquedasUtils
						.wrappBusquedaPersonaFisica(salida);
				String nombreCompleto = salida.getNOMBRE() + " "
						+ salida.getAPPATERNO() + " " + salida.getAPMATERNO();
				dato.setNombreCompleto(nombreCompleto);
				listaDatos.add(dato);
			}
		}

		return listaDatos;
	}

}