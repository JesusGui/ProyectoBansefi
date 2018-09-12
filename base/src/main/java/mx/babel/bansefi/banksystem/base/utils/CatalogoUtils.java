package mx.babel.bansefi.banksystem.base.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ProveedorMensajeSpringUtils;
import mx.babel.arq.task.ICatalogoLoaderTask;
import mx.babel.bansefi.banksystem.base.backends.ConsultaCatalogeBackend;
import mx.babel.bansefi.banksystem.base.backends.ConsultaCatalogoBackend;
import mx.babel.bansefi.banksystem.base.backends.login.LoginBackEnd;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.webservices.login.EjecutarResult;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Utilidad que enmascara las llamadas a los webservices de catálogos para poder
 * cachear su resultado.
 * 
 * @author joseluis.pena
 */
@Component
@Qualifier("CatalogoUtils")
public class CatalogoUtils implements ICatalogoLoaderTask, Serializable {

	private static final long serialVersionUID = 1985298280112285749L;

	private static final Logger LOGGER = LogManager
			.getLogger(CatalogoUtils.class.getName());

	@Autowired
	ConsultaCatalogoBackend consultaCatalogoBackend;

	@Autowired
	ConsultaCatalogeBackend consultaCatalogeBackend;
	
	@Autowired
	LoginBackEnd loginBackEnd;

	private Map<String, Integer> cantidadCatalogosMap = new HashMap<String, Integer>();

	public List<CatalogoBean> getCatalogo(final CatalogoEnum catalogo) {
		return consultaCatalogoBackend.ejecutarTRN(catalogo);
	}

	@Override
	public List<CatalogoBean> getCatalogo(final String catalogo) {
		return consultaCatalogoBackend.ejecutarTRN(CatalogoEnum
				.valueOf(catalogo));
	}

	/**
	 * Método encargado de recuperar un catálogo ordenado alfabéticamente a
	 * partir de su descripción.
	 * 
	 * @param catalogo
	 * @return lista de objetos tipo CatalogoBean
	 */
	public List<CatalogoBean> getCatalogoOrdenado(final CatalogoEnum catalogo) {
		final List<CatalogoBean> catalogoList = consultaCatalogoBackend
				.ejecutarTRN(catalogo);
		if (catalogoList != null && catalogoList.size() > 0) {
			Collections.sort(catalogoList, new Comparator<CatalogoBean>() {
				@Override
				public int compare(final CatalogoBean object1,
						final CatalogoBean object2) {
					return object1.getDescripcionL().compareTo(
							object2.getDescripcionL());
				}
			});
		}
		return catalogoList;
	}

	/**
	 * Método encargado de recuperar un catálogo ordenado alfabéticamente a
	 * partir de su descripción.
	 * 
	 * @param catalogo
	 * @return lista de objetos tipo CatalogoBean
	 */
	public List<CatalogoBean> getCatalogoOrdenado(final String catalogo) {
		final List<CatalogoBean> catalogoList = consultaCatalogoBackend
				.ejecutarTRN(CatalogoEnum.valueOf(catalogo));
		if (catalogoList != null && catalogoList.size() > 0) {
			Collections.sort(catalogoList, new Comparator<CatalogoBean>() {
				@Override
				public int compare(final CatalogoBean object1,
						final CatalogoBean object2) {
					return object1.getDescripcionL().compareTo(
							object2.getDescripcionL());
				}
			});
		}
		return catalogoList;
	}

	@Override
	public void loadTask() {
		
		this.login();
		
		List<CatalogoBean> resultados = null;

		for (final CatalogoEnum entry : CatalogoEnum.values()) {
			if (entry.isLoadOnServerStart()) {
				try {

					resultados = this.getCatalogo(entry);
					LOGGER.info("Catalog " + entry.getDescripcion() + ": "
							+ resultados.size());
					this.cantidadCatalogosMap.put(entry.getDescripcion(),
							resultados.size());
				} catch (final NoControlableException ex) {
					LOGGER.debug("Catalog " + entry.getDescripcion() + ": "
							+ ex.getMessage());
					this.cantidadCatalogosMap.put(entry.getDescripcion(), -3);
				}
			}
		}
	}
	
	public void login() {
		
		try {
		   
			String usuario = ProveedorMensajeSpringUtils.getValorConfiguracion("catalogo.usuario"); 
		    String contrasena = ProveedorMensajeSpringUtils.getValorConfiguracion("catalogo.password");
		    String ip = ProveedorMensajeSpringUtils.getValorConfiguracion("catalogo.ip");
		   
		    //EjecutarResult resultado = loginBackEnd.ejecutarWSCatalogo(usuario, contrasena, ip, "S");
		    EjecutarResult resultado = loginBackEnd.ejecutarWSCatalogo(usuario, contrasena, ip, "N");
		    
			LOGGER.info("Login OK en la carga de catálogos");

		} catch (Exception e) {
			LOGGER.info("Error haciendo login en la carga de catálogos");
		}
	
	}

	public CatalogoBean getCatalogoBean(final CatalogoEnum catalogo,
			final String claveFila) {
		final List<CatalogoBean> listado = this.getCatalogo(catalogo);
		if (null != listado) {
			for (final CatalogoBean catalogoBean : listado) {
				if (StringUtils.equalsIgnoreCase(catalogoBean.getClaveFila()
						.trim(), claveFila.trim())) {
					return catalogoBean;
				}
			}
			throw new ControlableException("Error al consultar el catalogo",
					claveFila
							+ " no encontrada entre los valores del catalogo "
							+ catalogo.getDescripcion());
		}
		throw new ControlableException("Error al consultar el catalogo",
				"Catalogo " + catalogo.getDescripcion() + " no encontrado.");
	}

	public CatalogoBean getCatalogoBeanNull(final CatalogoEnum catalogo,
			final String claveFila) {
		final List<CatalogoBean> listado = this.getCatalogo(catalogo);
		if (null != listado) {
			for (final CatalogoBean catalogoBean : listado) {
				if (StringUtils.equalsIgnoreCase(catalogoBean.getClaveFila()
						.trim(), claveFila.trim())) {
					return catalogoBean;
				}
			}
			return null;
		}
		throw new ControlableException("Error al consultar el catalogo",
				"Catalogo " + catalogo.getDescripcion() + " no encontrado.");
	}

	public String getCatalogoDesc(final CatalogoEnum catalogo,
			final String claveFila) {
		if (StringUtils.isNotBlank(claveFila)) {
			return this.getCatalogoBean(catalogo, claveFila).getDescripcionL();
		}
		return "";
	}

	/**
	 * Método que devuelve un catálogo filtrado y ordenado alfabéticamente por
	 * su descripción.
	 * 
	 * @param catalogo
	 * @param claveFilaNombre
	 * @return catálogo filtrado ordenado alfabéticamente
	 */
	public List<CatalogoBean> filtraCatalogoOrdenado(
			final CatalogoEnum catalogo, final String claveFilaNombre) {
		final List<CatalogoBean> catalogoList = consultaCatalogoBackend
				.ejecutarTRN(catalogo);

		final Map<String, CatalogoBean> mapaCatalogosEliminar = new HashMap<>();

		final List<CatalogoBean> catalogoListFiltros = consultaCatalogeBackend
				.ejecutarTRN(catalogo, claveFilaNombre);

		for (final CatalogoBean catalogoBean : catalogoList) {
			mapaCatalogosEliminar
					.put(catalogoBean.getClaveFila(), catalogoBean);
		}

		final List<CatalogoBean> listaFiltrada = new ArrayList<CatalogoBean>();

		for (final CatalogoBean catalogoBeanFiltro : catalogoListFiltros) {
			if (mapaCatalogosEliminar.containsKey(catalogoBeanFiltro
					.getClaveFila())) {
				listaFiltrada.add(mapaCatalogosEliminar.get(catalogoBeanFiltro
						.getClaveFila()));
			}
		}

		Collections.sort(listaFiltrada, new Comparator<CatalogoBean>() {
			@Override
			public int compare(final CatalogoBean object1,
					final CatalogoBean object2) {
				return object1.getDescripcionL().compareTo(
						object2.getDescripcionL());
			}
		});

		return listaFiltrada;
	}

	public Map<String, Integer> getCantidadCatalogosMap() {
		return cantidadCatalogosMap;
	}

	public void setCantidadCatalogosMap(
			Map<String, Integer> cantidadCatalogosMap) {
		this.cantidadCatalogosMap = cantidadCatalogosMap;
	}

	/**
	 * Método que a partir del atributo contenido de un CatalogoBean devuelve un
	 * CatalogoEnum.
	 * 
	 * @param contenido
	 *            (puede ser CatalogoBean.getContenido() o un String)
	 * @return catalogoEnum
	 */
	public CatalogoEnum getCatalogoEnum(final String contenido) {
		try {
			// Ejemplo: 100001GOT quedaría 00001 y GOT.
			String codTbl = contenido.substring(1, 6);
			String codApp = contenido.substring(6, 9);
			for (CatalogoEnum valor : CatalogoEnum.values()) {
				if (valor.getCodAplccnSubapl().equalsIgnoreCase(codApp)
						&& valor.getCodTblRef().equalsIgnoreCase(codTbl)) {
					return valor;
				}
			}
		} catch (IndexOutOfBoundsException | NullPointerException e) {
			LOGGER.debug("Error al obtener el CatalogoEnum. "
					+ this.getClass().getName() + ":" + e.getMessage());
		}
		return null;
	}
	
}