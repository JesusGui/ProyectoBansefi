package mx.babel.bansefi.banksystem.base.validadores;

import java.math.BigDecimal;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@FacesValidator("porcentajeValidator")
public class PorcentajeValidator implements Validator {

	private static final Logger LOG = LogManager
			.getLogger(PorcentajeValidator.class);

	@Override
	public void validate(final FacesContext context,
			final UIComponent component, final Object value)
			throws ValidatorException {
		if (value == null) {
			return;
		}

		Map<String, String> params = context.getExternalContext()
				.getRequestParameterMap();

		if (params.get("disableValidation") != null
				&& "true".equalsIgnoreCase(params.get("disableValidation"))) {
			LOG.debug("Se omite validación de porcentaje desde la vista.");
		} else {
			BigDecimal valor = (BigDecimal) value;
			if (valor.compareTo(BigDecimal.ZERO) <= 0) {
				throw new ValidatorException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "",
						"El porcentaje debe ser mayor a 0."));
			}
			return;
		}
	}

}