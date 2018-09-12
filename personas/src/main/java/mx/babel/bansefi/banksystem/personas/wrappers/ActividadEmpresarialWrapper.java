package mx.babel.bansefi.banksystem.personas.wrappers;

import mx.babel.bansefi.banksystem.personas.beans.ActividadEmpresarialBean;
import mx.babel.bansefi.banksystem.personas.beans.CnaeBean;
import mx.babel.bansefi.banksystem.personas.beans.DireccionRegistralBean;
import mx.babel.bansefi.banksystem.personas.beans.MercadoOrganizadoBean;
import mx.babel.bansefi.banksystem.personas.webservices.altadatosempresarialespersona.Ejecutar.ITRPEALTACOMPLEMPRTR.TRPEALTACOMPLEMPREVT;
import mx.babel.bansefi.banksystem.personas.webservices.altamercadosorganizados.Ejecutar.ITRPEALTAMERCADOSORG.TRPEALTAMERCADOSORGE;
import mx.babel.bansefi.banksystem.personas.webservices.altaobjetosocialcnae.Ejecutar.ITRPEAMPLICNAEOBJSOC.TRPEAMPLICNAEOBJSOCE;
import mx.babel.bansefi.banksystem.personas.webservices.bajamercadosorganizados.Ejecutar.ITRPEBAJAMERCADOSORG.TRPEBAJAMERCADOSORGE;
import mx.babel.bansefi.banksystem.personas.webservices.bajaobjetosocialcnae.Ejecutar.ITRPEBAJACNAEOBJSOCT.TRPEBAJACNAEOBJSOCEV.PEOTROCNAEP;
import mx.babel.bansefi.banksystem.personas.webservices.consultadatosempresarialespersona.ResponseBansefi.OTRPECONSCOMPLEMPRTR.TRPECONSCOMPLEMPREVT;
import mx.babel.bansefi.banksystem.personas.webservices.consultadetalledirecregistralpersona.ResponseBansefi.OTRDRRGSTROCNSTRNO.TRDRRGSTROCNSEVTZ;
import mx.babel.bansefi.banksystem.personas.webservices.consultamercadosorganizados.ResponseBansefi.OTRPECONSMERCADOSORG.TRPECONSMERCADOSORGE.TRPECONSMERCADOSORGE1;
import mx.babel.bansefi.banksystem.personas.webservices.consultaobjetosocialcnae.ResponseBansefi.OTRPECONSCNAEOBJSOCT.TRPECONSCNAEOBJSOCEV1.TRPECONSCNAEOBJSOCEV;
import mx.babel.bansefi.banksystem.personas.webservices.listadireccionesregistralespersona.ResponseBansefi.OTRDRREGLSCNSTRNO.TRDRREGLSCNSEVTZ.TRDRREGLSCNSEVTV;
import mx.babel.bansefi.banksystem.personas.webservices.mantenimientodireccionesregistrales.Ejecutar.ITRDRREGMNTTRNI.TRDRREGMNTTRNY;
import mx.babel.bansefi.banksystem.personas.webservices.mantenimientodireccionesregistrales.Ejecutar.ITRDRREGMNTTRNI.TRDRREGMNTTRNY.DRRGSTROV2;
import mx.babel.bansefi.banksystem.personas.webservices.modificaciondatosempresarialespersona.Ejecutar.ITRPEMODIFCOMPLEMPRT.TRPEMODIFCOMPLEMPREV;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase para wrappers para la ventana de Actividad Empresarial.
 * 
 * @author alejandro.pineda
 * 
 */
@Component
public class ActividadEmpresarialWrapper {

	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	public ActividadEmpresarialWrapper() {

	}

	/**
	 * Método para realizar mappeo para alta de Datos empresariales.
	 * 
	 * @param actividadEmpresarial
	 *            Bean con los datos de alta.
	 * @return TRPEALTACOMPLEMPREVT bean con los datos mapeados.
	 */
	public TRPEALTACOMPLEMPREVT wrapperAltaDatosEmpresariales(
			ActividadEmpresarialBean actividadEmpresarial) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(actividadEmpresarial, TRPEALTACOMPLEMPREVT.class,
				"altaActividadEmpresarial");
	}

	/**
	 * Método para realizar mappeo para consulta de Datos empresariales.
	 * 
	 * @param resultConsultaDatosEmpresariales
	 *            Bean con los resultados de consulta.
	 * @return ActividadEmpresarialBean bean mapeado con los resultados del
	 *         servicio.
	 */
	public ActividadEmpresarialBean wrapperConsultaDatosEmpresariales(
			TRPECONSCOMPLEMPREVT resultConsultaDatosEmpresariales) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(resultConsultaDatosEmpresariales,
				ActividadEmpresarialBean.class, "consultaActividadEmpresarial");
	}

	/**
	 * Método para realizar mappeo para consulta de Datos empresariales.
	 * 
	 * @param actividadEmpresarial
	 *            Bean con los datos a modificar.
	 * @return TRPEMODIFCOMPLEMPREVTY bean mapeado con lo ingresado al servicio.
	 */
	public TRPEMODIFCOMPLEMPREV wrapperModificacionDatosEmpresariales(
			ActividadEmpresarialBean actividadEmpresarial) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(actividadEmpresarial, TRPEMODIFCOMPLEMPREV.class,
				"modificacionActividadEmpresarial");
	}

	/**
	 * Método para realizar mappeo para alta de Mercado organizado.
	 * 
	 * @param mercadoOrganizado
	 *            Bean con los datos de Alta.
	 * @return TRPEALTAMERCADOSORGEVTY bean con los datos mapeados.
	 */
	public TRPEALTAMERCADOSORGE wrapperAltaMercadosOrganizados(
			MercadoOrganizadoBean mercadoOrganizado) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(mercadoOrganizado, TRPEALTAMERCADOSORGE.class,
				"altaMercadosOrganizados");
	}

	/**
	 * Método para realizar mappeo para baja de Mercado organizado.
	 * 
	 * @param mercadoOrganizado
	 *            Bean con los datos de baja.
	 * @return TRPEBAJAMERCADOSORGEVTY bean con los datos mapeados.
	 */
	public TRPEBAJAMERCADOSORGE wrapperBajaMercadosOrganizados(
			MercadoOrganizadoBean mercadoOrganizado) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(mercadoOrganizado, TRPEBAJAMERCADOSORGE.class,
				"bajaMercadosOrganizados");
	}

	/**
	 * Método para realizar mappeo para consulta de Mercados organizados.
	 * 
	 * @param resultMercadoOrganizado
	 *            Bean con los datos de consulta.
	 * @return MercadoOrganizadoBean bean mapeado con los resultados del
	 *         servicio.
	 */
	public MercadoOrganizadoBean wrapperConsultaMercadosOrganizados(
			TRPECONSMERCADOSORGE1 resultMercadoOrganizado) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(resultMercadoOrganizado, MercadoOrganizadoBean.class,
				"consultaMercadosOrganizados");
	}

	/**
	 * Método para realizar mappeo para alta de CNAE.
	 * 
	 * @param cnae
	 *            Bean con los datos de alta.
	 * @return TRPEAMPLICNAEOBJSOCEVTY bean con los datos mapeados.
	 */
	public TRPEAMPLICNAEOBJSOCE wrapperAltaObjetoSocialCnae(CnaeBean cnae) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(cnae, TRPEAMPLICNAEOBJSOCE.class,
				"altaCnaesObjSocial");
	}

	/**
	 * Método para realizar mappeo para baja de CNAE.
	 * 
	 * @param cnae
	 *            Bean con los datos de baja
	 * @return PEOTROCNAEP bean con los datos mapeados.
	 */
	public PEOTROCNAEP wrapperBajaObjetoSocialCnae(CnaeBean cnae) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(cnae, PEOTROCNAEP.class, "bajaCnaesObjSocial");
	}

	/**
	 * Método para realizar mappeo para consulta de CNAE's.
	 * 
	 * @param resultCnae
	 *            Bean con los resultados de la consulta.
	 * @return CnaeBean bean mapeado con los resultados del servicio.
	 */
	public CnaeBean wrapperConsultaObjetoSocialCnae(
			TRPECONSCNAEOBJSOCEV resultCnae) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(resultCnae, CnaeBean.class, "consultaCnaesObjSocial");
	}

	/**
	 * Método para realizar mappeo para consulta de Direccion Registral.
	 * 
	 * @param resultDireccion
	 *            Bean con los resultados de la consulta.
	 * @return DireccionRegistralBean bean mapeado con los resultados del
	 *         servicio.
	 */
	public DireccionRegistralBean wrapperConsultaDireccionRegistral(
			TRDRREGLSCNSEVTV resultDireccion) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(resultDireccion, DireccionRegistralBean.class,
				"consultaDireccionReg");
	}

	/**
	 * Método para realizar mappeo para consulta detalle de Direccion Registral.
	 * 
	 * @param resultDetalleDireccion
	 *            Bean con los resultados de la consulta.
	 * @return DireccionRegistralBean bean mapeado con los resultados del
	 *         servicio.
	 */
	public void wrapperConsultaDetalleDireccion(
			TRDRRGSTROCNSEVTZ resultDetalleDireccion, DireccionRegistralBean direccion) {
		Mapper mapper = dozerBeanMapper;
		mapper.map(resultDetalleDireccion, direccion,
				"consultaDetalleDireccionReg");
	}

	/**
	 * Método para realizar mappeo para alta, baja o modificación del detalle de
	 * Dirección Registral.
	 * 
	 * @param direccionRegistral
	 *            Bean con los datos a enviar.
	 * @return TRDRREGMNTTRNY bean mapeado con los resultados del servicio.
	 */
	public TRDRREGMNTTRNY wrapperMantenimientoDetalleDireccion(
			DireccionRegistralBean direccionRegistral) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(direccionRegistral, TRDRREGMNTTRNY.class,
				"mantenimientoDetalleDireccionReg");
	}
	
	/**
	 * Método para realizar mappeo para alta, baja o modificación del detalle de
	 * Dirección Registral.
	 * 
	 * @param direccionRegistral
	 *            Bean con los datos a enviar.
	 * @return TRDRREGMNTTRNY bean mapeado con los resultados del servicio.
	 */
	public DRRGSTROV2 wrapperMantenimientoDireccion(
			DireccionRegistralBean direccionRegistral) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(direccionRegistral, DRRGSTROV2.class,
				"mantenimientoDireccionReg");
	}

}
