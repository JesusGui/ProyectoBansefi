package mx.babel.bansefi.banksystem.cajas.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class NumberUtils {
	
	public String formateoNumeros(BigDecimal numero) throws NumberFormatException{
		
		NumberFormat formato = NumberFormat.getNumberInstance();
		String numeroFormateado = "";
		
		numeroFormateado = formato.format(new BigDecimal(numero.toString())) + " MXN";
		
		return numeroFormateado;
	}
}
