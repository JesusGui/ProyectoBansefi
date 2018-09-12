package mx.babel.bansefi.banksystem.cuentas.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("TelefonoDAValidator")
public class TelefonoDAValidator implements Validator{

	public TelefonoDAValidator(){}
	
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		
		//Obtenemos el telefono introducido
		Object telefonoObject = value;
		
		if(telefonoObject == null ||
				telefonoObject.toString().length() < 1){
			return;
		}
		
		if(telefonoObject != null){
			if(telefonoObject.toString().length() < 6){
				throw new ValidatorException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
								"La longitud mínima es de 6 digitos"));
			} else if(telefonoObject.toString().length() > 10){
				throw new ValidatorException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
								"La longitud máxima es de 10 digitos"));
			} else {
				return;
			}
		}
		
	}
}
