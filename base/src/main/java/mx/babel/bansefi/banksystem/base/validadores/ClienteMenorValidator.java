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
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;

@FacesValidator("clienteMenorValidator")
public class ClienteMenorValidator implements Validator {

	
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return;
        }
         
        Object date = component.getAttributes().get("date");
        if(date==null){
    		return;
    	}
        
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        
        if (params.get("disableValidation") != null
				&& "true".equalsIgnoreCase(params.get("disableValidation"))) {
		} else {
        
	        Date fechaNacimiento = this.parseDate(date);
	        Date fechaMayorEdad = Calendar.getInstance().getTime();
	        fechaMayorEdad.setYear(fechaMayorEdad.getYear() -18);
	        if (fechaNacimiento.after(fechaMayorEdad)){
	        	Object tag = component.getAttributes().get("tag");
	        	if (tag == null){
	        		return;
	        	}else{
	        		if (("tipoidentificacion").equals(tag)){
	        			if (!value.equals(ConstantesFuncionales.TP_ID_EXT_PERS_MENOR)){
	        				String facesError = ProveedorMensajeSpringUtils
	        						.getValoresMensajesError("mx.babel.bansefi.banksystem.base.validadores.menor."+tag);
	        				throw new ValidatorException(
	        						new FacesMessage(FacesMessage.SEVERITY_ERROR, "", facesError));
	        			}
	        		}else if (("estadolaboral").equals(tag)){
	        			if (!value.equals(ConstantesFuncionales.TP_EST_LAB_INDV_MENOR)){
	        				String facesError = ProveedorMensajeSpringUtils
	        						.getValoresMensajesError("mx.babel.bansefi.banksystem.base.validadores.menor."+tag);
	        				throw new ValidatorException(
	        						new FacesMessage(FacesMessage.SEVERITY_ERROR, "", facesError));
	        			}
	        		}else if (("cno").equals(tag)){
	        			if (!value.equals(ConstantesFuncionales.TP_CNO_INDV_MENOR)){
	        				String facesError = ProveedorMensajeSpringUtils
	        						.getValoresMensajesError("mx.babel.bansefi.banksystem.base.validadores.menor."+tag);
	        				throw new ValidatorException(
	        						new FacesMessage(FacesMessage.SEVERITY_ERROR, "", facesError));
	        			}
	        		}else if (("capacidadactuar").equals(tag) || ("situacioneconomica").equals(tag)){
	        			if (value !=null){
	        				String facesError = ProveedorMensajeSpringUtils
	        						.getValoresMensajesError("mx.babel.bansefi.banksystem.base.validadores.menor."+tag);
	        				throw new ValidatorException(
	        						new FacesMessage(FacesMessage.SEVERITY_ERROR, "", facesError));
	        			}
	        		}
	        	}
	        }
		}
    }

	private Date parseDate(Object date) {
		Date result = null;
		if(date instanceof Date){
			result = (Date)date;
		}else if (date instanceof String){
			try {
				result = formatter.parse((String)date);
			} catch (ParseException e) {
				throw new NoControlableException("Ha ocurrido un error", this.getClass().getName()
					+".parseDate(): el dato no ha podido ser parseado.");
			}
		}else{
			String msg = this.getClass().getName()+".parseDate(): el dato no ha podido ser parseado.";
			throw new NoControlableException(msg, msg);
		}
		return result;
	}
}
