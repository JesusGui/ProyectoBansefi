package mx.babel.bansefi.banksystem.base.backends.busquedas;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.busqueda.PersonaMoralBusquedaBean;
import mx.babel.bansefi.banksystem.base.webservices.busquedapersonasmorales.BusquedaPersonaMoralServicio;
import mx.babel.bansefi.banksystem.base.webservices.busquedapersonasmorales.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.busquedapersonasmorales.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.busquedapersonasmorales.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.wrappers.WrapperBusquedasUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd para la ejecución del servicio de busqueda de Persona Moral
 * 
 * @author alejandro.pineda
 * 
 */
@Component
public class BusquedaPersonaMoralBackEnd extends BackEndBean {

	private static final long serialVersionUID = 4307988633328877684L;

	@Autowired
	private WrapperBusquedasUtils wrapperBusquedasUtils;

	@Autowired
	private ServicioWebUtils servicioWebUtils;

	/**
	 * Constructor.
	 */
	public BusquedaPersonaMoralBackEnd() {
		super();
	}

	/**
	 * Método que ejecuta el servicio de búsqueda de Persona Moral.
	 * 
	 * @param busquedaPersona
	 *            Bean con los datos a buscar
	 * @return String con la ruta de la ficha de cuenta.
	 * @throws ControlableException
	 *             Excepción Controlable del servicio.
	 * @throws NoControlableException
	 *             Excepción No Controlable del servicio.
	 */
	public List<Object> ejecutarWS(Object busquedaPersona)
			throws ControlableException, NoControlableException {
		PersonaMoralBusquedaBean personaMoral = (PersonaMoralBusquedaBean) busquedaPersona;
		Ejecutar contextoEntrada = new Ejecutar();
		contextoEntrada.setUSERHEADER(super.getUsuarioId());
		contextoEntrada.setPASSHEADER(super.getPassword());
		contextoEntrada.setIPHEADER(super.getIp());
		contextoEntrada.setENTIDAD(super.getEntidad());

		if (personaMoral.getRazonSocial() != null) {
			contextoEntrada.setDENLEGAL(personaMoral.getRazonSocial()
					.toUpperCase());
		}

		if (personaMoral.getActaConstitutiva() != null) {
			contextoEntrada.setIDEXTERNO(personaMoral.getActaConstitutiva()
					.toUpperCase());
		}

		contextoEntrada.setIDINTERNO(BigInteger.valueOf(Long.parseLong(Integer
				.toString(personaMoral.getIdInterna()))));

		contextoEntrada.setNUMIFE("000000000000000000");

		return this.ejecutarServicio(contextoEntrada, personaMoral);
	}

	private List<Object> ejecutarServicio(Ejecutar contextoEntrada,
			PersonaMoralBusquedaBean personaMoral) throws ControlableException,
			NoControlableException {
		EjecutarResult respuesta = null;
		List<Object> listaDatos = new ArrayList<>();
		int codigo = 0;
		// Se ejecuta el WebService correspondiente
		respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
				BusquedaPersonaMoralServicio.class,
				contextoEntrada.getENTIDAD(), contextoEntrada.getNUMIFE(),
				contextoEntrada.getIDINTERNO(), contextoEntrada.getIDEXTERNO(),
				contextoEntrada.getDENLEGAL());

		codigo = Integer.parseInt(respuesta.getESTATUS());

		if (codigo != 0) {
			if (!("ARQE153").equals(respuesta.getCODIGO().trim())
					&& !("ARQE118").equals(respuesta.getCODIGO().trim())) {
				throw new ControlableException("ERROR DE HOST: "
						+ respuesta.getCODIGO() + " " + respuesta.getMENSAJE(),
						"ERROR DE HOST " + respuesta.getCODIGO() + " "
								+ respuesta.getMENSAJE());
			}
		} else {
			for (ResponseBansefi salida : respuesta.getResponseBansefi()
					.getResponseBansefi()) {
				PersonaMoralBusquedaBean dato = wrapperBusquedasUtils
						.wrappBusquedaPersonaMoral(salida);
				listaDatos.add(dato);
			}
			personaMoral.setMasDatos(false);
		}

		return listaDatos;
	}

}