package mx.babel.bansefi.banksystem.base.utils;

import java.io.Serializable;
import java.util.List;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.EstadosCuentaEnum;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Utilidad para utilizar el Enum de Estados de cuenta verificados con el
 * catalogo en host
 * 
 * @author manuel.nieto
 * 
 */
@Component
@Qualifier("EstadosCuentaEnumUtils")
public class EstadosCuentaEnumUtils implements Serializable {

	private static final long serialVersionUID = 1674995672364576040L;
	private static final Logger LOGGER = LogManager
			.getLogger(EstadosCuentaEnumUtils.class.getName());

	@Autowired
	private CatalogoUtils catalogoUtils;

	/**
	 * Funcion que de acuerdo a un elemento de la clase enum de estados de
	 * cuenta devuelve el codigo correspondiente en el catalogo en host
	 * 
	 * @param estadosCuentaEnum
	 * @return
	 */
	public String getClave(EstadosCuentaEnum estadosCuentaEnum) {
		try {
			List<CatalogoBean> catalogo = catalogoUtils
					.getCatalogo(CatalogoEnum.TP_ECV_AC);

			for (CatalogoBean cat : catalogo) {
				if (cat.getDescripcionC().toUpperCase()
						.contains(estadosCuentaEnum.getEstado())) {
					return cat.getClaveFila();
				}
			}

		} catch (NullPointerException | ControlableException e) {
			LOGGER.debug("Error al consultar el catalogo de estados de cuentas");
			LOGGER.debug(e);
			return null;
		}
		return null;
	}

	/**
	 * Funcion que apartir de un codigo de estado de cuenta, duelve el Enum
	 * correspondiente
	 * 
	 * @param codigo
	 * @return
	 */
	public EstadosCuentaEnum getEstado(String codigo) {
		try {
			CatalogoBean catalogo = catalogoUtils.getCatalogoBean(
					CatalogoEnum.TP_ECV_AC, codigo);

			String nombreEstado = catalogo.getDescripcionC();

			for (EstadosCuentaEnum e : EstadosCuentaEnum.values()) {
				if (nombreEstado.toUpperCase().contains(e.getNombre())) {
					e.setEstado(codigo);
					return e;
				}
			}
		} catch (NullPointerException | ControlableException e) {
			LOGGER.debug("Error al consultar el catalogo de estados de cuenta");
			LOGGER.debug(e);
			return null;
		}

		return null;
	}

	/**
	 * Funcion que determina si a partir de un codigo de estado es solicitado
	 * 
	 * @param codigoEstado
	 * @return
	 */
	public boolean isSolicitado(String codigoEstado) {
		if (!StringUtils.isBlank(codigoEstado)
				&& getEstado(codigoEstado).equals(EstadosCuentaEnum.SOLICITADO)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Funcion que determina si a partir de un codigo de estado coincide
	 * con un enum esperado	 
	 * @param codigoEstado
	 * @return
	 */
	public boolean verificaEstado(EstadosCuentaEnum estadoCuentaEnum,
			String codigoEstado) {
		if (!StringUtils.isBlank(codigoEstado) && estadoCuentaEnum != null
				&& getEstado(codigoEstado).equals(estadoCuentaEnum)) {
			return true;
		} else {
			return false;
		}
	}

}
