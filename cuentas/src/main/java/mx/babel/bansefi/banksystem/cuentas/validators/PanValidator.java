package mx.babel.bansefi.banksystem.cuentas.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.cuentas.backends.VerificaEstadoPanBackEnd;

@Component
@Scope("request")
public class PanValidator implements Validator{

	@Autowired
	VerificaEstadoPanBackEnd verificaEstadoPanBackEnd;
	
	@Autowired
	ContextoUtils contexto;
	
	/**
	 * Método que valida que un importe sea multiplo de la denominación que representa.
	 */
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		if (value == null || value.equals("")) {
            return;
        }
		try{
			String[] estadoCentroPan = verificaEstadoPanBackEnd.ejecutarTRN(value.toString().replaceAll("-", ""));
			if(estadoCentroPan != null && estadoCentroPan.length>1){
				
				//Se valida estado PAN
				String estadoPan = estadoCentroPan[0]!=null ? estadoCentroPan[0] : "";
				if(!ConstantesFuncionales.ECV_STOCK_TJ_RECIBIDO.equals(estadoPan.trim())){
					throw new ValidatorException(
							new FacesMessage(FacesMessage.SEVERITY_ERROR, ""
									, "Este PAN se encuentra en estado '"+ 
							ConstantesFuncionales.estadosPan().get(estadoPan)+ "'."));
				}
				
				//Se valida centro PAN
				String centroPan = estadoCentroPan[1]!=null ? estadoCentroPan[1] : "";
				if(!contexto.getSucursal().equals(centroPan.trim())){
					throw new ValidatorException(
							new FacesMessage(FacesMessage.SEVERITY_ERROR, ""
									, "Este PAN se encuentra en otro centro '"+ centroPan + "'."));
				}
			}else{
				if(!value.toString().contains("@")){
					throw new ValidatorException(
							new FacesMessage(FacesMessage.SEVERITY_ERROR, ""
									, "El número de PAN introducido no existe."));
				}
			}
			String bin = (String) component.getAttributes().get("binSeleccionado");
	        if(bin==null){
	    		return;
	    	}
			if(!value.toString().replace("-", "").startsWith(bin)){
				throw new ValidatorException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, ""
								, "El PAN no corresponde con el BIN seleccionado."));
			}
	       
		}catch(ControlableException | NoControlableException e){
			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, ""
							, "El número de PAN introducido no existe."));
		}
	}
}
