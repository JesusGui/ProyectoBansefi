package mx.babel.bansefi.banksystem.base.validadores;

import java.math.BigDecimal;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ProveedorMensajeSpringUtils;

/** Validador de fechas para utilizarse en las vistas.
 *  Puede recibir por parametro fecha de inicio y fecha de fin, dependiendo de esos parametros
 *  realiza diferentes validaciones:
 *  +Fecha Inicio: comprueba que la fecha no sea menor que la fecha inicial
 *  +Fecha Fin: comprueba que la fecha no sea mayor que la fecha inicial
 *  +Fecha Inicio y Fecha Fin: comprueba que la fecha se encuentre dentro del rango de fechas indicado
 */
@FacesValidator("bigDecimalRangeValidator")
public class BigDecimalRangeValidator implements Validator {



	@Override
    public void validate(final FacesContext context, final UIComponent component, final Object value) throws ValidatorException {
        if (value == null) {
            return;
        }

        //Leave the null handling of startDate to required="true"
        final Object minValue = component.getAttributes().get("minRange");
        final Object maxValue = component.getAttributes().get("maxRange");
        if (minValue==null) {
        	if(maxValue==null){
        		return;
        	}
        }

    	final BigDecimal valueToCheck = this.parseBigDecimal(value);
        if(minValue!=null && maxValue!=null){
        	final BigDecimal minRange = this.parseBigDecimal(minValue);
        	final BigDecimal maxRange = this.parseBigDecimal(maxValue);
        	isBigDecimalBetween(valueToCheck,minRange, maxRange);
        }else if (minValue != null){
        	final BigDecimal minRange = this.parseBigDecimal(minValue);
        	isBigDecimalBefore(valueToCheck, minRange);
        }else{
        	final BigDecimal maxRange = this.parseBigDecimal(maxValue);
	        isBigDecimalAfter(valueToCheck, maxRange);
        }
    }

	/**
	 * @param startDate
	 * @param endDate
	 * @param tag
	 */
	private void isBigDecimalAfter(final BigDecimal valueToCheck, final BigDecimal max) {
	    if(max.compareTo(valueToCheck) < 0){
            final String facesError = ProveedorMensajeSpringUtils
                    .getValoresMensajesError("mx.babel.bansefi.banksystem.base.validadores.isBigDecimalAfter.default"
                        , max);
                throw new ValidatorException(
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "", facesError));
        }
	}

	private void isBigDecimalBefore(final BigDecimal valueToCheck, final BigDecimal min) {
		if(min.compareTo(valueToCheck) > 0){
            final String facesError = ProveedorMensajeSpringUtils
                    .getValoresMensajesError("mx.babel.bansefi.banksystem.base.validadores.isBigDecimalBefore.default"
                        , min);
                throw new ValidatorException(
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "", facesError));
		}

	}

	private void isBigDecimalBetween(final BigDecimal valueToCheck, final BigDecimal min,
			final BigDecimal max) {

	    if(!(max.compareTo(valueToCheck) >= 0 && min.compareTo(valueToCheck) <= 0)){
	        final String facesError = ProveedorMensajeSpringUtils
                    .getValoresMensajesError("mx.babel.bansefi.banksystem.base.validadores.isBigDecimalBetween.default"
                        , min
                        , max);
                throw new ValidatorException(
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "", facesError));
	    }


	}

	private BigDecimal parseBigDecimal(final Object value) {
	    BigDecimal result = null;
		if(value instanceof BigDecimal){
			result = (BigDecimal)value;
		}else if (value instanceof String){
            try {
                result = new BigDecimal((String)value);
            } catch (final NumberFormatException e) {
                throw new NoControlableException("Ha ocurrido un error", this.getClass().getName()
                    +".parseBigDecimal(): el dato no ha podido ser parseado.");
            }
        }else{
    		final String msg = this.getClass().getName()+".parseBigDecimal(): el dato no es un BigDecimal.";
    		throw new NoControlableException(msg, msg);
        }
		return result;
	}
}
