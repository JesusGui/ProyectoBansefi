package mx.babel.bansefi.banksystem.base.wrappers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.babel.bansefi.banksystem.base.beans.busqueda.CentroBusquedaBean;
import mx.babel.bansefi.banksystem.base.beans.busqueda.DomiciliosClienteBusquedaBean;
import mx.babel.bansefi.banksystem.base.beans.busqueda.EmpleadoBusquedaBean;
import mx.babel.bansefi.banksystem.base.beans.busqueda.GrupoBusquedaBean;
import mx.babel.bansefi.banksystem.base.beans.busqueda.PersonaGestorBusquedaBean;
import mx.babel.bansefi.banksystem.base.beans.busqueda.PersonaMoralBusquedaBean;
import mx.babel.bansefi.banksystem.base.beans.busqueda.PersonasClienteBusquedaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioTipoBean;
import mx.babel.bansefi.banksystem.base.controllers.BusquedaController;
import mx.babel.bansefi.banksystem.base.webservices.busquedacodigocentro.ResponseBansefi.OTRCONMINCENTRO2TRN.TRCONMINCENTROEVTZ.UOCENTROE;
import mx.babel.bansefi.banksystem.base.webservices.busquedacuenta.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.webservices.busquedaidexterna.ResponseBansefi.OTRPECBIDEXTERNACNS.TRPECBIDEXTERNACNSE;
import mx.babel.bansefi.banksystem.base.webservices.busquedaidinterna.ResponseBansefi.OTRPECLCBDACNSTRNO.TRPECLCBDACNSEVTZ;
import mx.babel.bansefi.banksystem.base.webservices.busquedanombre.Ejecutar.ITRPECBNOMBRECNSTRN.TRPECBNOMBRECNSEVTY.PEINDVCBNOMBREV;
import mx.babel.bansefi.banksystem.base.webservices.busquedanombre.ResponseBansefi.OTRPECBNOMBRECNSTRN.TRPECBNOMBRECNSEVTZ;
import mx.babel.bansefi.banksystem.base.webservices.consultaempleado.ResponseBansefi.OTRCONSEMPLTRNO.TRCONSEMPLEVTZ;
import mx.babel.bansefi.banksystem.base.webservices.consultagrupoporidexterna.ResponseBansefi.OTRGRLSTTRNO.TRGRLSTEVTZ.TRGRLSTEVTV;
import mx.babel.bansefi.banksystem.base.webservices.listacentros.ResponseBansefi.OTRLISTNOMBCENTTRNO.TRLISTNOMBCENTEVTZ.UOLAV2;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Utilería para realizar wrapper de campos de búsqueda.
 * 
 * @author alejandro.pineda
 * 
 */
@Component
public class WrapperBusquedasUtils implements Serializable {

	private static final long serialVersionUID = -5262546790737450195L;

	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	/**
	 * Constructor.
	 */
	public WrapperBusquedasUtils() {
		super();
	}

	/**
	 * Método para mapear datos de entrada del webService de búsqueda por nombre
	 * de Gestor.
	 * 
	 * @param obj
	 *            Objeto que se mapeara
	 * @return PEINDVCBNOMBREV mapeado del webservice de búsqueda.
	 */
	public PEINDVCBNOMBREV wrappBusquedaNombre(PersonaGestorBusquedaBean obj) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(obj, PEINDVCBNOMBREV.class, "busquedaPorNombre");
	}

	/**
	 * Método que mapea los resultados del webservice de búsqueda por Nombre.
	 * 
	 * @param beanOrigen
	 *            Bean que obtiene los resultados.
	 * @param mapId
	 *            Id del mapeo en el xml
	 * @return Bean que tiene los datos de salida mapeados.
	 */
	public BusquedaController wrappResultadosBusquedaNombre(
			TRPECBNOMBRECNSEVTZ beanOrigen) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(beanOrigen, BusquedaController.class,
				"resultadosBusquedaNombreGestor");
	}

	/**
	 * Método que mapea los resultados del webservice de búsqueda por Id
	 * Externa.
	 * 
	 * @param beanOrigen
	 *            Bean que obtiene los resultados.
	 * @return Bean que tiene los datos de salida mapeados.
	 */
	public BusquedaController wrappResultadosIdExternaGestores(
			TRPECBIDEXTERNACNSE beanOrigen) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(beanOrigen, BusquedaController.class,
				"resultadosBusquedaExternaGestor");
	}

	/**
	 * Método que mapea los resultados del webservice de búsqueda por Id Interna
	 * en Gestor.
	 * 
	 * @param beanOrigen
	 *            Bean que obtiene los resultados.
	 * @return Bean que tiene los datos de salida mapeados.
	 */
	public PersonaGestorBusquedaBean wrappBeanResultadoIdInterna(
			TRPECLCBDACNSEVTZ beanOrigen) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(beanOrigen, PersonaGestorBusquedaBean.class,
				"resultadoBusquedaIdInternaGestor");
	}

	/**
	 * Método que mapea los resultados del webservice de búsqueda de Grupo
	 * 
	 * @param beanOrigen
	 *            Bean que obtiene los resultados.
	 * @return Bean que tiene los datos de salida mapeados.
	 */
	public GrupoBusquedaBean wrappBeanResultadosGrupo(TRGRLSTEVTV beanOrigen) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(beanOrigen, GrupoBusquedaBean.class,
				"resultadosGrupo");
	}

	/**
	 * Método que mapea los datos de salida del ws.
	 * 
	 * @param centroBusqueda
	 *            Bean que obtiene el dato de salida del ws.
	 * @return CentroBusquedaBean Bean que obtiene los datos mapeado.
	 */
	public CentroBusquedaBean wrappBeanBusquedaCentroCodigo(
			UOCENTROE centroBusqueda) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(centroBusqueda, CentroBusquedaBean.class,
				"centroBusquedaCodigo");
	}

	/**
	 * Método que mapea los datos de salida del ws.
	 * 
	 * @param centroBusqueda
	 *            Bean que obtiene el dato de salida del ws.
	 * @return CentroBusquedaBean Bean que obtiene los datos mapeado.
	 */
	public CentroBusquedaBean wrappBeanBusquedaCentroNombre(
			UOLAV2 centroBusqueda) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(centroBusqueda, CentroBusquedaBean.class,
				"centroBusquedaNombre");
	}

	/**
	 * Método que mapea los resultados del webservice de búsqueda por Id
	 * Externa.
	 * 
	 * @param beanOrigen
	 *            Bean que obtiene los resultados.
	 * @param mapId
	 *            Id del mapeo en el xml
	 * @return Bean que tiene los datos de salida mapeados.
	 */
	public EmpleadoBusquedaBean wrappBean(TRCONSEMPLEVTZ beanOrigen,
			String mapId) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(beanOrigen, EmpleadoBusquedaBean.class, mapId);
	}

	/**
	 * Método que mapea los resultados del servicio de búsqueda por numero de
	 * cuenta.
	 * 
	 * @param beanOrigen
	 *            Bean que obtiene los resultados
	 * @return CuentaBean Bean que tiene los datos de salida mapeados.
	 */
	public CuentaBean wrappBusquedaCuenta(ResponseBansefi beanOrigen) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(beanOrigen, CuentaBean.class, "busquedaCuenta");
	}

	/**
	 * Método que mapea los resultados del servicio de búsqueda por numero de
	 * cuenta.
	 * 
	 * @param beanOrigen
	 *            Bean que obtiene los resultados
	 * @return PersonasClienteGestorBusquedaBean Bean que tiene los datos de
	 *         salida mapeados.
	 */
	public PersonasClienteBusquedaBean wrappBusquedaPersonaFisica(
			mx.babel.bansefi.banksystem.base.webservices.busquedapersonasfisicas.ResponseBansefi beanOrigen) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(beanOrigen, PersonasClienteBusquedaBean.class,
				"resultadosPersonaFisica");
	}

	/**
	 * Método que mapea los resultados del servicio de búsqueda por numero de
	 * cuenta.
	 * 
	 * @param beanOrigen
	 *            Bean que obtiene los resultados
	 * @return PersonasClienteGestorBusquedaBean Bean que tiene los datos de
	 *         salida mapeados.
	 */
	public PersonaMoralBusquedaBean wrappBusquedaPersonaMoral(
			mx.babel.bansefi.banksystem.base.webservices.busquedapersonasmorales.ResponseBansefi beanOrigen) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(beanOrigen, PersonaMoralBusquedaBean.class,
				"resultadosPersonaMoral");
	}

	/**
	 * Método para convertir el resultado de búsqueda de domicilios por persona
	 * para bean de búsqueda de domicilios con anotaciones.
	 * 
	 * @param domiciliosTipo
	 *            Lista de domicilio por persona
	 * @return Lista de domicilios con anotaciones.
	 */
	public List<DomiciliosClienteBusquedaBean> wrappBusquedaDomiciliosBean(
			List<DomicilioTipoBean> domiciliosTipo) {
		Mapper mapper = dozerBeanMapper;
		List<DomiciliosClienteBusquedaBean> domiciliosBusqueda = new ArrayList<DomiciliosClienteBusquedaBean>();
		for (DomicilioTipoBean domicilioTipoBean : domiciliosTipo) {
			domiciliosBusqueda.add(mapper.map(domicilioTipoBean,
					DomiciliosClienteBusquedaBean.class, "busquedaDomicilio"));
		}
		return domiciliosBusqueda;
	}

}