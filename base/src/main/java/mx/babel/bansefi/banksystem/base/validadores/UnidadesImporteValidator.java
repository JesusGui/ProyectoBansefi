package mx.babel.bansefi.banksystem.base.validadores;

import java.math.BigDecimal;
import java.text.NumberFormat;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Validador para las unidades y el importe en Arqueo de Puesto
 * @author mario.montesdeoca
 *
 */
@FacesValidator("unidadesImporteValidator")
public class UnidadesImporteValidator implements Validator{

	/**
	 * Método que valida que un importe sea multiplo de la denominación que representa.
	 */
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		if (value == null) {
            return;
        }
		
		Object valorDenominacion = component.getAttributes().get("valor");
		if (valorDenominacion==null) {
			return;
		}
			
		BigDecimal valor = (BigDecimal) valorDenominacion;
		BigDecimal importe = new BigDecimal(value.toString());
		
		
		if(importe.compareTo(BigDecimal.ZERO) == 0){
			return;
		}
		
		if(valor != null && valor.compareTo(BigDecimal.ZERO) != 0){
			try{
				BigDecimal bg[] = importe.divideAndRemainder(valor);
				if(bg[1].compareTo(BigDecimal.ZERO) != 0){
					 throw new ValidatorException(
		                        new FacesMessage(FacesMessage.SEVERITY_ERROR, ""
		                               , "El importe no es un múltiplo de "
		                               + NumberFormat.getCurrencyInstance().format(valor)));
				}
				else
				{
					
					Long unidades = importe.divide(valor).longValue();
					if(Long.toString(unidades).length() > 6)
					{
						 throw new ValidatorException(
			                        new FacesMessage(FacesMessage.SEVERITY_ERROR, ""
			                               , "Las unidades: " + unidades + " excedederían de 6 dígitos."));
					}
					
				}
				
			}catch(ArithmeticException ae){
				throw new ValidatorException(
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, ""
                               , "El importe no es un múltiplo de "
                               + NumberFormat.getCurrencyInstance().format(valor)));
			}
		}
	}

}

	


