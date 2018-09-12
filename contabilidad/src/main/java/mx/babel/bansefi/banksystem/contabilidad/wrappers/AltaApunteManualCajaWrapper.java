package mx.babel.bansefi.banksystem.contabilidad.wrappers;

import java.io.Serializable;

import mx.babel.bansefi.banksystem.base.beans.ApunteManualBean;
import mx.babel.bansefi.banksystem.contabilidad.webservices.altaapuntemanualcaja.Ejecutar.ITRAMINIAPNTEMANOTRN.TRAMINIAPNTEMANOEVT;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Wrapper para el tratamiento de datos en la TRN de alta de apuntes manuales
 * @author manuel.nieto
 *
 */
@Component
public class AltaApunteManualCajaWrapper implements Serializable {

	private static final long serialVersionUID = -1005910410572393737L;

	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	/**
	 * Funcion que wrappea los datos del objeto AltaApunteBean
	 * para preparlo para la TRN de alta de apuntes manuales
	 * @param altaApunteBean
	 * @return
	 */
	public TRAMINIAPNTEMANOEVT wrappAltaApunteManual(
			ApunteManualBean altaApunteBean) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(altaApunteBean, TRAMINIAPNTEMANOEVT.class,
				"altaApunteManualCaja");
	}
}
