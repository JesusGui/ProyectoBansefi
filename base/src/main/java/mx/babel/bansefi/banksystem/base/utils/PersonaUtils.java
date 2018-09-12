package mx.babel.bansefi.banksystem.base.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import mx.babel.bansefi.banksystem.base.enums.TipoPersonaEnum;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class PersonaUtils {

	private static final Logger log = LogManager.getLogger(PersonaUtils.class);

	public boolean validaFormatoRFC(String rfc, TipoPersonaEnum tipoPersona) {
		boolean rfcFormatoValido = false;
		StringBuilder sb = null;
		String homoclaveFisica = "([A-Z,0-9][A-Z,0-9][0-9,A-Z])?";
		String homoclaveMoral = "[A-Z,0-9]{3}";

		sb = new StringBuilder();
		sb.append("[A-Z,Ñ,&]{");

		if (TipoPersonaEnum.FISICA.equals(tipoPersona)) {
			sb.append("4");
		} else if (TipoPersonaEnum.MORAL.equals(tipoPersona)) {
			sb.append("3");
		}

		sb.append("}[0-9]{2}[0-9]{2}[0-9]{2}");

		if (TipoPersonaEnum.FISICA.equals(tipoPersona)) {
			sb.append(homoclaveFisica);
		} else if (TipoPersonaEnum.MORAL.equals(tipoPersona)) {
			sb.append(homoclaveMoral);
		}

		rfc = rfc.trim();
		rfc = rfc.toUpperCase();
		rfcFormatoValido = rfc.matches(sb.toString());

		return rfcFormatoValido;
	}

	public boolean validaFechaRFC(String rfc, TipoPersonaEnum tipoPersona) {
		SimpleDateFormat sdf = null;
		boolean fechaRfcValida = true;
		Date fechaRfc = null;
		Calendar hoy = Calendar.getInstance();
		Calendar fechaDelRFC = null;

		sdf = new SimpleDateFormat("yyMMdd");
		sdf.setLenient(false);

		// si la fecha no es válida lanza ParseException
		try {
			if (TipoPersonaEnum.FISICA.equals(tipoPersona)) {
				fechaRfc = sdf.parse(rfc.substring(4, 10));
			} else if (TipoPersonaEnum.MORAL.equals(tipoPersona)) {
				fechaRfc = sdf.parse(rfc.substring(3, 9));
			}

			fechaDelRFC = Calendar.getInstance();
			fechaDelRFC.setTimeInMillis(fechaRfc.getTime());

			if (fechaDelRFC.after(hoy)) {
				fechaRfcValida = false;
			}

		} catch (ParseException e) {
			fechaRfcValida = false;
			log.info(e.getMessage());
		}
		return fechaRfcValida;
	}

	public static String[] palabrasInconvenientes = new String[] { "BACA",
			"BAKA", "BUEI", "BUEY", "CACA", "CACO", "CAGA", "CAGO", "CAKA",
			"CAKO", "COGE", "COGI", "COJA", "COJI", "COJO", "COLA", "CULO",
			"FALO", "FETO", "GETA", "GUEI", "GUEY", "JETA", "JOTO", "KACA",
			"KACO", "KAGA", "KAGO", "KAKA", "KAKO", "KOGE", "KOGI", "KOJA",
			"KOJE", "KOJI", "KOJO", "KOLA", "KULO", "LILO", "LOCA", "LOCO",
			"LOKA", "LOKO", "MAME", "MAMO", "MEAR", "MEAS", "MEON", "MIAR",
			"MION", "MOCO", "MOKO", "MULA", "MULO", "NACA", "NACO", "PEDA",
			"PEDO", "PENE", "PIPI", "PUTO", "QULO", "RATA", "ROBA", "ROBE",
			"RUIN", "SENO", "TETA", "VACA", "VAGA", "VAGO", "VAKA", "VUEI",
			"VUEY", "WUEI", "WUEY" };

	/**
	 * Funcion que valida si la cadena ingresada corresponde a un formato de
	 * CURP bien formado
	 * @param curp
	 * @return boolean
	 * @autor manuel.nieto
	 */
	public boolean validaFormatoCURP(String curp) {
		
		if(curp.length() != 18){
			return false;
		}
		
		boolean formatoValido;
				
		StringBuilder expresion = new StringBuilder(
				"[A-Z][AEIOU][A-Z]{2}[0-9]{6}[HM](AS|BC|BS|CC|CL|CM|CS|CH|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS)[A-Z]{3}[A-Z0-9][0-9]");
		formatoValido = curp.matches(expresion.toString());
		return formatoValido;
	}

	/**
	 * Funcion que valida la fecha de la CURP:
	 * 1. Que el formato sea yyMMdd
	 * 2. Que la fecha indicada no sea posterior a la fecha actual del sistema
	 * @author manuel.nieto
	 * @param curp
	 * @return
	 */
	public boolean validaFechaCURP(String curp) {
		SimpleDateFormat simpleDateFormat = null;
		boolean fechaCurpValida = true;
		Date curpDate = null;
		Calendar actualCalendar = Calendar.getInstance();
		Calendar curpCalendar = null;

		simpleDateFormat = new SimpleDateFormat("yyMMdd");
		simpleDateFormat.setLenient(false);

		// si la fecha no es válida lanza ParseException
		try {
			// Verificar formato correcto
			curpDate = simpleDateFormat.parse(curp.substring(4, 10));

			// Verificar que la fecha de la curp no sea mayor a la fecha actual
			curpCalendar = Calendar.getInstance();
			curpCalendar.setTimeInMillis(curpDate.getTime());

			if (curpCalendar.after(actualCalendar)) {
				fechaCurpValida = false;
			}

		} catch (ParseException e) {
			fechaCurpValida = false;
			log.info(e.getMessage());
		}
		return fechaCurpValida;
	}

	/**
	 * Funcion que valida que la CURP no contena ninguna palabra inconveniente
	 * 
	 * @param curp
	 * @return
	 */
	public boolean validaPalabrasInconvenientesCURP(String curp) {
		for (String palabra : palabrasInconvenientes) {
			if (curp.startsWith(palabra)) {
				return false;
			}
		}
		return true;
	}

}
