package mx.babel.bansefi.banksystem.personas.wrappers;

import mx.babel.bansefi.banksystem.personas.beans.ActividadProfesionalBean;
import mx.babel.bansefi.banksystem.personas.beans.OrigenIngresosBean;
import mx.babel.bansefi.banksystem.personas.webservices.altadatosprofesionales.Ejecutar.ITRPEALTACOMPLPROFTR.TRPEALTACOMPLPROFEVT;
import mx.babel.bansefi.banksystem.personas.webservices.altaorigeningreso.Ejecutar.ITRPEALTAORGNINGTRN.TRPEALTAORGNINGEVTY;
import mx.babel.bansefi.banksystem.personas.webservices.bajadatoprofesional.Ejecutar.ITRPEBAJACOMPLPROFTR.TRPEBAJACOMPLPROFEVT.PEACTPROFINDVP;
import mx.babel.bansefi.banksystem.personas.webservices.bajaorigeningresos.Ejecutar.ITRPEBAJAORGNINGTRN.TRPEBAJAORGNINGEVTY.PEORGNINGRINDVP;
import mx.babel.bansefi.banksystem.personas.webservices.consultadetalledatoprofesional.ResponseBansefi.OTRPECONSCOMPLPROFTR.TRPECONSCOMPLPROFEVT;
import mx.babel.bansefi.banksystem.personas.webservices.consultaorigenesingresos.ResponseBansefi.OTRPECONSORGNINGTRN.TRPECONSORGNINGEVTZ.TRPECONSORGNINGEVTV;
import mx.babel.bansefi.banksystem.personas.webservices.modificaciondatoprofesional.Ejecutar.ITRPEMODIFCOMPLPROFT.TRPEMODIFCOMPLPROFEV;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase Wrapper para la ventana de Datos Económicos.
 * 
 * @author alejandro.pineda
 * 
 */
@Component
public class DatosEconomicosWrapper {

	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	public DatosEconomicosWrapper() {

	}

	/**
	 * Método para realizar el wrapper para la consulta de Origen Ingresos.
	 * 
	 * @param origenIngresosBean
	 *            Bean con el resultado de la consulta de Origen de Ingreso.
	 * @return OrigenIngresosBean Bean con los datos mapeados.
	 */
	public OrigenIngresosBean wrapperConsultaOrigenIngresos(
			TRPECONSORGNINGEVTV origenIngresosBean) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(origenIngresosBean, OrigenIngresosBean.class,
				"consultaOrigenIngresos");
	}

	/**
	 * Método para realizar el wrapper para la consulta detalle de un Dato
	 * Profesional.
	 * 
	 * @param datoProfesional
	 *            Bean con el resultado de la consulta de Dato Profesional.
	 * 
	 * @param actividadProfesionalBean
	 *            Bean con el resultado de la consulta de Dato Profesional.
	 */
	public void wrapperConsultaDetalleDatoProf(
			TRPECONSCOMPLPROFEVT datoProfesional,
			ActividadProfesionalBean actividadProfesionalBean) {
		Mapper mapper = dozerBeanMapper;
		mapper.map(datoProfesional, actividadProfesionalBean,
				"consultaDetalleDatosProfesionales");
	}

	/**
	 * Método para realizar el wrapper para el alta de Origen Ingresos.
	 * 
	 * @param origenIngresosBean
	 *            Bean con el Origen de Ingreso.
	 * @return TRPEALTAORGNINGEVTY Bean con los datos mapeados.
	 */
	public TRPEALTAORGNINGEVTY wrapperAltaOrigenIngresos(
			OrigenIngresosBean origenIngresosBean) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(origenIngresosBean, TRPEALTAORGNINGEVTY.class,
				"altaOrigenIngresos");
	}

	/**
	 * Método para realizar el wrapper para el alta de Datos Profesionales.
	 * 
	 * @param actividadProfesionalBean
	 *            Bean con los datos profesionales.
	 * @return TRPEALTACOMPLPROFEVTY Bean con los datos mapeados.
	 */
	public TRPEALTACOMPLPROFEVT wrapperAltaDatosProfesional(
			ActividadProfesionalBean actividadProfesionalBean) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(actividadProfesionalBean,
				TRPEALTACOMPLPROFEVT.class, "altaDatosProfesionales");
	}

	/**
	 * Método para realizar el wrapper para la baja de un Origen Ingresos.
	 * 
	 * @param origenIngresosBean
	 *            Bean con el Origen de Ingreso.
	 * @return PEORGNINGRINDVP Bean con los datos mapeados.
	 */
	public PEORGNINGRINDVP wrapperBajaOrigenIngresos(
			OrigenIngresosBean origenIngresosBean) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(origenIngresosBean, PEORGNINGRINDVP.class,
				"bajaOrigenIngresos");
	}

	/**
	 * Método para realizar el wrapper para la baja de Datos Profesionales.
	 * 
	 * @param actividadProfesionalBean
	 *            Bean con los datos profesionales.
	 * @return PEACTPROFINDVP Bean con los datos mapeados.
	 */
	public PEACTPROFINDVP wrapperBajaDatosProfesional(
			ActividadProfesionalBean actividadProfesionalBean) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(actividadProfesionalBean, PEACTPROFINDVP.class,
				"bajaDatosProfesionales");
	}

	/**
	 * Método para realizar el wrapper para la modifcación de Datos
	 * Profesionales.
	 * 
	 * @param actividadProfesionalBean
	 *            Bean con los datos profesionales.
	 * @return TRPEMODIFCOMPLPROFEVTY Bean con los datos mapeados.
	 */
	public TRPEMODIFCOMPLPROFEV wrapperModificacionDatosProfesional(
			ActividadProfesionalBean actividadProfesionalBean) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(actividadProfesionalBean,
				TRPEMODIFCOMPLPROFEV.class, "modificacionDatosProfesionales");
	}
}
