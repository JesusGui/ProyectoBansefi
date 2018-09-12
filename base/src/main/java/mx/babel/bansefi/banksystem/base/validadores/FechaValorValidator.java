package mx.babel.bansefi.banksystem.base.validadores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ProveedorMensajeSpringUtils;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.validadores.DateRangeValidator;



/**
 * Valida que la fecha valor que se ingresa
 * no sea mayor a la fecha del sistema
 * @author elena.orozco.siliceo
 */

@FacesValidator("fechaValorValidator")
public class FechaValorValidator implements Validator{
	
	@Autowired
    private ContextoUtils contextoUtils;
	
	private final SimpleDateFormat formatter = new SimpleDateFormat(
			"dd/MM/yyyy");
	private static final Logger LOG = LogManager
			.getLogger(DateRangeValidator.class);

    
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
			LOG.debug("Se omite validación de fecha desde la vista.");
		} else {
			// Leave the null handling of startDate to required="true"
			Object beginDateValue = null;
      	  	beginDateValue = component.getAttributes().get("beginDate");
			
      	  	Object endDateValue = null;
			endDateValue = component.getAttributes().get("endDate");
			if (endDateValue == null) {
				if (beginDateValue == null) {
					return;
				}
			}

			final Date dateToBeChecked = this.parseDate(value);
			if (beginDateValue != null && endDateValue != null) {
				final Date beginDate = this.parseDate(beginDateValue);
				final Date endDate = this.parseDate(endDateValue);
				isDateBetween(dateToBeChecked, beginDate, endDate);
			} 
		}
	}
	
 	private void isDateBetween(final Date dateToBeChecked,
			final Date beginDate, final Date endDate) {
		if (dateToBeChecked.after(endDate)) {
			final String facesError = ProveedorMensajeSpringUtils
					.getValoresMensajesError(
							"mx.babel.bansefi.banksystem.base.validadores.isDateAfter.default",
   							formatter.format(endDate));
			throw new ValidatorException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "", facesError));
		}

		if (dateToBeChecked.before(beginDate)) {
			final String facesError = ProveedorMensajeSpringUtils
					.getValoresMensajesError(
							"mx.babel.bansefi.banksystem.base.validadores.isDateBefore.default",
							formatter.format(beginDate));
			throw new ValidatorException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "", facesError));
		}

	}

	private Date parseDate(final Object date) {
		Date result = null;
		if (date instanceof Date) {
			result = (Date) date;
		} else if (date instanceof String) {
			try {
				result = formatter.parse((String) date);
			} catch (final ParseException e) {
				throw new NoControlableException("Ha ocurrido un error", this
						.getClass().getName()
						+ ".parseDate(): el dato no ha podido ser parseado.");
			}
		} else {
			final String msg = this.getClass().getName()
					+ ".parseDate(): el dato no ha podido ser parseado.";
			throw new NoControlableException(msg, msg);
		}
		return result;
	}
}
