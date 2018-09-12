package mx.babel.bansefi.banksystem.cajas.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class FechaUtils {
	
	/**
	 * Método que realiza el formato de fecha
	 * 
	 * @param Fecha a formatear
	 * @return Fecha en siguiente formato yyyyMMdd
	 * */
	public int formateoFecha(Date fecha)throws ParseException{
		
		String formatoFecha = "yyyyMMdd";
		SimpleDateFormat sdf = new SimpleDateFormat(formatoFecha);
		String fechaFormateada = sdf.format(fecha);
		
		return Integer.parseInt(fechaFormateada);
		
	}
	
	public Boolean validaFecha(Integer fecha){
		
		Boolean isValida = false;
		
		if(fecha.toString().matches("[0-9]{4}((0[1-9]|1[012])(0[1-9]|1[0-9]|2[0-9])|(0[13456789]|1[012])(29|30)|(0[13578]|1[02])31)")){
			isValida = true;
			
		}
		return isValida;
	}
}
