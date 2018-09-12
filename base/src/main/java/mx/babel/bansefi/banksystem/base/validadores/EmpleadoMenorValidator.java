package mx.babel.bansefi.banksystem.base.validadores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ProveedorMensajeSpringUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTimeComparator;

/**
 * Variante del ClienteMenorValidator encargado de comprobar que el empleado no
 * sea menor de edad sin importar el tipo de identificación, estado laboral,
 * CNO, situación económica o capacidad de actuar.
 * 
 * @author omar.marquez
 */
@FacesValidator("empleadoMenorValidator")
public class EmpleadoMenorValidator implements Validator {

	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	private static final Logger LOG = LogManager
			.getLogger(EmpleadoMenorValidator.class);

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		if (value == null) {
			return;
		}

		Object showMsgForBirthdayValidation = component.getAttributes().get(
				"showMsgForBirthdayValidation");

		Map<String, String> params = context.getExternalContext()
				.getRequestParameterMap();

		if (params.get("disableValidation") != null
				&& "true".equalsIgnoreCase(params.get("disableValidation"))) {
			LOG.debug("Se omite validación de la fecha de nacimiento desde la vista.");
		} else {
			Date fechaNacimiento = this.parseDate(value);
			Calendar rightNow = Calendar.getInstance();
			for (int i = 1; i <= 18; i++) {
				rightNow.roll(Calendar.YEAR, false);
			}
			Calendar birthdayDate = Calendar.getInstance();
			birthdayDate.setTime(fechaNacimiento);
			if (DateTimeComparator.getDateOnlyInstance().compare(
					birthdayDate.getTime(), rightNow.getTime()) > 0) {
				if (showMsgForBirthdayValidation != null
						&& "true"
								.equalsIgnoreCase((String) showMsgForBirthdayValidation)) {
					String facesError = ProveedorMensajeSpringUtils
							.getValoresMensajesError("mx.babel.bansefi.banksystem.base.validadores.menor.empleado");
					throw new ValidatorException(new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "", facesError));
				}
			}
		}

	}

	/**
	 * Método privado que devuelve un objeto Date con formato dd/MM/yyyy.
	 * 
	 * @param date
	 * @return date con formato dd/MM/yyyy
	 */
	private Date parseDate(Object date) {
		Date result = null;
		if (date instanceof Date) {
			result = (Date) date;
		} else if (date instanceof String) {
			try {
				result = formatter.parse((String) date);
			} catch (ParseException e) {
				throw new NoControlableException("Ha ocurrido un error", this
						.getClass().getName()
						+ ".parseDate(): el dato no ha podido ser parseado.");
			}
		} else {
			String msg = this.getClass().getName()
					+ ".parseDate(): el dato no ha podido ser parseado.";
			throw new NoControlableException(msg, msg);
		}
		return result;
	}

}