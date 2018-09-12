package mx.babel.bansefi.banksystem.cajas.beans;

import java.io.Serializable;
import java.util.Date;

import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CajaUtilsBean implements Serializable{
	@Autowired
	ContextoUtils contexto;
	
	
	public Date getFechaSistema() {
		return contexto.getFechaContableActual();
	}

	public int getFechaSistemaInteger() {
		final IntegerToDateConverter itdConverter = new IntegerToDateConverter();
		return itdConverter.convertFrom(this.getFechaSistema());
	}
	
	public Date getFechaContableSiguiente()
	{
		return contexto.getFechaContableSiguiente();
	}
	
	public int getFechaContableSiguienteInteger() {
		final IntegerToDateConverter itdConverter = new IntegerToDateConverter();
		return itdConverter.convertFrom(this.getFechaContableSiguiente());
	}
	

}
