package mx.babel.bansefi.banksystem.base.validadores;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import mx.babel.bansefi.banksystem.base.utils.PersonaUtils;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Validador de formato y fechas para la CURP
 * 
 * @author manuel.nieto
 * 
 */
@Component
@Scope("request")
public class CurpValidator implements Validator {

	private static final Logger LOGGER = LogManager
			.getLogger(CurpValidator.class);
	private static final String FORMAT_MSG_SUMMARY = "Formato inválido";
	private static final String FORMAT_MSG_DETAIL = "CURP inválida. Se esperaba un formato: AAAA999999AAAAAA99.";
	private static final String DATE_MSG_SUMMARY = "Fecha inválida";
	private static final String DATE_MSG_DETAIL = "Fecha de la CURP inválida. Se esperaba una fecha con formato yyMMdd.";

	@Autowired
	private PersonaUtils personaUtils;

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		boolean formatoValido = false;
		boolean fechaValida = false;

		FacesMessage msg = null;
		String curp = (String) value;
		curp = curp.trim().toUpperCase();

		Map<String, String> params = context.getExternalContext()
				.getRequestParameterMap();

		if (params.get("omitirValidacion") != null
				&& "true".equalsIgnoreCase(params.get("omitirValidacion"))) {
			LOGGER.debug("Se omite validación de CURP desde la vista.");
		} else {
			if (!StringUtils.isBlank(curp)) {
				// Valida el formato de la fecha
				if (personaUtils.validaFormatoCURP(curp)
						&& personaUtils.validaPalabrasInconvenientesCURP(curp)) {
					formatoValido = true;
				}

				if (!formatoValido) {
					msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
							FORMAT_MSG_SUMMARY, FORMAT_MSG_DETAIL);
					throw new ValidatorException(msg);
				} else {
					fechaValida = personaUtils.validaFechaCURP(curp);

					if (!fechaValida) {
						msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
								DATE_MSG_SUMMARY, DATE_MSG_DETAIL);
						throw new ValidatorException(msg);
					}
				}
			} else {
				LOGGER.debug("Curp vacio no puede ser validado.");
			}
		}

	}

}
