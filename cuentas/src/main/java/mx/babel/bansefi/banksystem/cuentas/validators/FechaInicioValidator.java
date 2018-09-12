package mx.babel.bansefi.banksystem.cuentas.validators;

import java.io.IOException;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.CuentaRelacionadaBean;

import org.joda.time.DateTimeComparator;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@FacesValidator("fechaInicioValidator")
public class FechaInicioValidator implements Validator {
	
	@Autowired
	ContextoUtils contexto;

	@Override
	public void validate(final FacesContext context,
			final UIComponent component, final Object value)
			throws ValidatorException {
		if (value == null) {
			return;
		}
		Date inicio = (Date) value;
		
		CuentaRelacionadaBean cuentaRelacionada = (CuentaRelacionadaBean) component.getAttributes().get("cuentaRelacionada");
		CuentaRelacionadaBean cuentaRelacionadaOld = getOldBean(cuentaRelacionada);
		
		if(cuentaRelacionadaOld.getInicio() == null){
			return;
		}
		if(DateTimeComparator.getDateOnlyInstance().compare(inicio, cuentaRelacionadaOld.getInicio()) == 0){
			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, ""
							, "No se han realizado cambios en la fecha de actividad."));
		}
	}
	
	private CuentaRelacionadaBean getOldBean(CuentaRelacionadaBean relacionada) {
		CuentaRelacionadaBean oldObject = new CuentaRelacionadaBean();
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,	false);
			oldObject =	mapper.readValue(relacionada.getRespaldo(),CuentaRelacionadaBean.class);
		} catch (IOException e) {
		}
		return oldObject;
	}

}