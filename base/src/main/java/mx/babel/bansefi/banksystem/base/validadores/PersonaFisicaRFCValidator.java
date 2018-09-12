package mx.babel.bansefi.banksystem.base.validadores;

import java.util.Map;

import mx.babel.bansefi.banksystem.base.enums.TipoPersonaEnum;
import mx.babel.bansefi.banksystem.base.utils.PersonaUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created by gerardo.pucheta on 18/05/2015.
 */
@Component
@Scope("request")
public class PersonaFisicaRFCValidator implements Validator {

	private static final Logger LOG = LogManager
			.getLogger(PersonaFisicaRFCValidator.class);
	private static final String FORMAT_MSG_SUMMARY = "Formato inválido";
	private static final String FORMAT_MSG_DETAIL = "RFC inválido. Se esperaba formato: AAAA999999 ó AAAA999999XXX.";
	private static final String DATE_MSG_SUMMARY = "Fecha inválida";
	private static final String DATE_MSG_DETAIL = "Fecha RFC inválida. Se esperaba una fecha válida con formato: yyMMdd.";

	@Autowired
	private PersonaUtils personaUtils;

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		boolean formatoValido = false;
		boolean fechaValida = false;

		String rfc = null;
		FacesMessage msg = null;

		rfc = (String) value;
		rfc = rfc.trim();
		rfc = rfc.toUpperCase();

		LOG.debug("RFC: " + rfc);

		Map<String, String> params = context.getExternalContext()
				.getRequestParameterMap();

		if (params.get("disableValidation") != null
				&& "true".equalsIgnoreCase(params.get("disableValidation"))) {
			LOG.debug("Se omite validación de RFC desde la vista.");
		} else {
			if (rfc != null && !rfc.isEmpty()) {
				formatoValido = personaUtils.validaFormatoRFC(rfc,
						TipoPersonaEnum.FISICA);

				LOG.debug("Formato válido?: " + formatoValido);

				if (!formatoValido) {
					msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
							FORMAT_MSG_SUMMARY, FORMAT_MSG_DETAIL);
					throw new ValidatorException(msg);
				} else {
					fechaValida = personaUtils.validaFechaRFC(rfc,
							TipoPersonaEnum.FISICA);

					LOG.debug("Fecha válida?: " + fechaValida);

					if (!fechaValida) {
						msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
								DATE_MSG_SUMMARY, DATE_MSG_DETAIL);
						throw new ValidatorException(msg);
					}
				}
			} else {
				LOG.debug("RFC nulo o vacío no puede ser validado.");
			}
		}
	}

}