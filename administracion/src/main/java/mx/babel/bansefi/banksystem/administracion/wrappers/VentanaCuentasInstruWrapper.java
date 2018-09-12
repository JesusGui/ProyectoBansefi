package mx.babel.bansefi.banksystem.administracion.wrappers;

import java.io.Serializable;

import mx.babel.bansefi.banksystem.administracion.beans.centro.AcuerdoInstrumentalBean;
import mx.babel.bansefi.banksystem.administracion.webservices.altaacuerdosinst.Ejecutar.ITRALTAACINSTOFICTRN.TRALTAACINSTOFICEVTY.UOACINSTALTAV;
import mx.babel.bansefi.banksystem.administracion.webservices.consultaacuerdosinst.ResponseBansefi.OTRCONSACINSTOFICTRN.TRCONSACINSTOFICEVT.UOACINSTLAV.UOACINSTLGV;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase wrapper para la ventana de cuentas instrumentales.
 * 
 * @author alejandro.pineda
 * 
 */
@Component
public class VentanaCuentasInstruWrapper implements Serializable{

	private static final long serialVersionUID = -6876710693999847242L;
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	public VentanaCuentasInstruWrapper() {

	}

	/**
	 * Método que mapea los datos obtenidos del WS de consulta de cuentas
	 * instrumentales.
	 * 
	 * @param resultado
	 *            Dato de salida que obtiene del servicio
	 * @return AcuerdoInstrumentalBean Bean mapeado con el contenido del
	 *         servicio.
	 */
	public AcuerdoInstrumentalBean wrappBeanConsultaCuenta(UOACINSTLGV resultado) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(resultado, AcuerdoInstrumentalBean.class,
				"consultaCuentaInstrumental");

	}
	
	
	/**
	 * Método que mapea los datos para dar de alta una cuenta instrumental.
	 * 
	 * @param cuenta
	 *            Dato de entrada que ingresamos al servicio.
	 * @return UOACINSTALTAV Bean mapeado con el contenido del
	 *         servicio.
	 */
	public UOACINSTALTAV wrappBeanAltaCuenta(AcuerdoInstrumentalBean cuenta) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(cuenta, UOACINSTALTAV.class,
				"altaCuentaInstrumental");

	}

}
