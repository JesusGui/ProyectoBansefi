package mx.babel.bansefi.banksystem.base.validadores;

import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.component.outputlabel.OutputLabel;
import org.springframework.context.annotation.Scope;

/**
 * 
 * @author alejandro.pineda
 * 
 */
@FacesValidator("camposValidator")
@Scope("request")
public class CamposValidator implements Validator {

	private static final String AUTOCOMPLETE_PATTERN = "^[0-9a-zA-ZÑñ\\- ]*$";

	private static final String ALFANUMERICO_PATTERN = "^[0-9a-zA-ZÑñ ]*$";

	private static final String NUMERICO_PATTERN = "^[0-9]*$";

	private static final String ALFA_PATTERN = "^[a-zA-ZÑñ ]*$";

	private Pattern pattern;

	private Matcher matcher;

	private static final Logger LOG = LogManager
			.getLogger(CamposValidator.class);

	public CamposValidator() {

	}

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		Map<String, String> params = context.getExternalContext()
				.getRequestParameterMap();

		if (params.get("disableValidation") != null
				&& "true".equalsIgnoreCase(params.get("disableValidation"))) {
			LOG.debug("Se omite validación de campos desde la vista.");
		} else {
			if (value != null && !"".equals(value)) {
				String tipoValidacion = (String) component.getAttributes().get(
						"tipoValidacion");
				switch (tipoValidacion) {
				case "A":
					this.validarAlfanumerico(context, component, value);
					break;
				case "B":
					this.validarAlfabeto(context, component, value);
					break;
				case "C":
					this.validarNumerico(context, component, value);
					break;
				case "D":
					this.validarAutocompletes(context, component, value);
					break;
				default:
					break;
				}
			}
		}
	}

	private void validarAutocompletes(FacesContext context,
			UIComponent component, Object value) throws ValidatorException {

		pattern = Pattern.compile(AUTOCOMPLETE_PATTERN);
		matcher = pattern.matcher(value.toString());
		if (!matcher.matches()) {
			String label = valorOutput(component, component.getParent(),
					component.getParent().getParent());
			FacesMessage msg = new FacesMessage(label + ": "
					+ "No se permiten carácteres especiales");
			((UIInput) component).setValid(false);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("formCliente", msg);

		}
	}

	private void validarAlfanumerico(FacesContext context,
			UIComponent component, Object value) throws ValidatorException {

		pattern = Pattern.compile(ALFANUMERICO_PATTERN);
		matcher = pattern.matcher(value.toString());
		if (!matcher.matches()) {
			String label = valorOutput(component, component.getParent(),
					component.getParent().getParent());
			FacesMessage msg = new FacesMessage(label + ": "
					+ "No se permiten carácteres especiales");
			((UIInput) component).setValid(false);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("formCliente", msg);

		}
	}

	private void validarNumerico(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		pattern = Pattern.compile(NUMERICO_PATTERN);
		matcher = pattern.matcher(value.toString());
		if (!matcher.matches()) {
			String label = valorOutput(component, component.getParent(),
					component.getParent().getParent());
			FacesMessage msg = new FacesMessage(label + ": "
					+ "Solo se permiten números.");
			((UIInput) component).setValid(false);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("formCliente", msg);

		}
	}

	private void validarAlfabeto(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		pattern = Pattern.compile(ALFA_PATTERN);
		matcher = pattern.matcher(value.toString());
		if (!matcher.matches()) {
			String label = valorOutput(component, component.getParent(),
					component.getParent().getParent());
			FacesMessage msg = new FacesMessage(label + ": "
					+ "No se permiten números ni caracteres especiales.");
			((UIInput) component).setValid(false);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("formCliente", msg);
		}
	}

	private String valorOutput(UIComponent component, UIComponent parent,
			UIComponent padreRaiz) {

		if (parent.getChildCount() > 1) {
			for (UIComponent componente : parent.getChildren()) {
				if (componente instanceof OutputLabel) {
					String output = "";
					try {
						output = ((OutputLabel) componente).getAttributes()
								.get("for").toString();
					} catch (NullPointerException e) {
						output = "";
					}
					if (output != null) {
						if (output.equals(component.getId())) {
							return ((OutputLabel) componente).getAttributes()
									.get("value").toString();
						}
					}

				}
			}
			if (!parent.equals(padreRaiz)) {
				return valorOutput(component, parent.getParent(), padreRaiz);
			} else {
				return null;
			}
		} else {
			return valorOutput(component, parent.getParent(), padreRaiz);
		}

	}

}
