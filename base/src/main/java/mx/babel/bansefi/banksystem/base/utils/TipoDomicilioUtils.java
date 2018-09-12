package mx.babel.bansefi.banksystem.base.utils;

import java.io.Serializable;
import java.util.List;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.TipoDomicilioEnum;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("TipoDomicilioUtils")
public class TipoDomicilioUtils implements Serializable {

	private static final long serialVersionUID = 8855336383636061213L;

	private static final Logger LOGGER = LogManager
			.getLogger(CatalogoUtils.class.getName());

	@Autowired
	private CatalogoUtils catalogoUtils;	
	
	/**
	 * Metodo que devuevle la clave del tipo de domicilio
	 * 
	 * @return
	 */
	public String getClave(TipoDomicilioEnum tipoDomicilioEnum) {
		try {
			List<CatalogoBean> catalogo = catalogoUtils
					.getCatalogo(CatalogoEnum.TP_DOMIC);

			for (CatalogoBean cat : catalogo) {
				// for (TipoDomicilioEnum e : TipoDomicilioEnum.values()) {
				if (cat.getDescripcionC().toUpperCase()
						.contains(tipoDomicilioEnum.getTipoDomicilio())) {
					return cat.getClaveFila();
					// }
				}
			}

		} catch (NullPointerException | ControlableException e) {
			LOGGER.debug("Error al consultar el catalogo de tipo de domicilios");
			LOGGER.debug(e);
			return null;
		}
		return null;
	}

	/**
	 * Método para obtener un tipo de domicilio por su código.
	 * 
	 * @param codigo
	 * @return
	 */
	public TipoDomicilioEnum getTipoDomicilio(String codigo) {
		try {
//			//Si el tipo de domicilio viene en un solo caracter
//			if(codigo.length() == 1 && Integer.valueOf(codigo) instanceof Integer){
//				codigo = "0" + codigo;
//			}
			
			CatalogoBean catalogo = catalogoUtils.getCatalogoBean(
					CatalogoEnum.TP_DOMIC, codigo);

			String tipoDomicilio = catalogo.getDescripcionC();

			for (TipoDomicilioEnum e : TipoDomicilioEnum.values()) {
				if (tipoDomicilio.toUpperCase().contains(e.getTipoDomicilio())) {
					e.setClave(codigo);
					return e;
				}
			}
		} catch (NullPointerException | ControlableException e) {
			LOGGER.debug("Error al consultar el catalogo de tipo de domicilios");
			LOGGER.debug(e);			
			return null;
		}

		return null;
	}
}
