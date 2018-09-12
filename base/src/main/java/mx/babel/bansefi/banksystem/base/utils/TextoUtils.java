package mx.babel.bansefi.banksystem.base.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

/**
 * Clase de utilidad que permite aplicar formatos a las cadenas de datos
 * recibidas y enviadas por HOST.
 * 
 * @author omar.marquez
 * 
 */

public class TextoUtils {
	
	/**
	 * Este método convierte una cadena de texto a MAYÚSCULAS.
	 * 
	 * @param texto .
	 * @return texto en mayúsculas
	 */
	public static String textoAMayusculas(String texto) {
		return StringUtils.upperCase(texto);
	}

	/**
	 * Este método convierte una cadena de texto a MINÚSCULAS.
	 * 
	 * @param texto .
	 * @return texto en minúsculas
	 */
	public static String textoAMinusculas(String texto) {
		return StringUtils.lowerCase(texto);
	}

	/**
	 * Este método capitaliza la primera letra de cada palabra que se encuentre
	 * precedida por los delimitadores recibidos como parámetros.
	 * 
	 * @param texto .
	 * @param delimitadores .
	 * @return texto con formato de título
	 */
	public static String textoATitulo(String texto, char...delimitadores){
		return WordUtils.capitalizeFully(texto, delimitadores);
	}
	
	/**
	 * Este método capitaliza la primera letra de cada palabra que se encuentre
	 * precedida por un punto o un espacio.
	 * 
	 * @param texto
	 * @return texto con formato de título
	 */
	public static String textoATitulo(String texto){
		return WordUtils.capitalizeFully(texto, '.', ' ');
	}
	
	/**
	 * Este método cambia las MAYÚSCULAS por MINÚSCULAS y viceversa. Por
	 * ejemplo: AsuNto > aSUnTO
	 * 
	 * @param texto .
	 * @return texto con formato opuesto
	 */
	public static String textoAFormatoOpuesto(String texto) {
		return WordUtils.swapCase(texto);
	}

	/**
	 * Este método elimina los acentos y carácteres especiales de
	 * una cadena de texto.
	 * @param texto .
	 * @return cadena de texto formateada
	 */
	public static String textoAFormatoHost(String texto) {
		texto = texto.toUpperCase();
		texto = texto.replaceAll("N~", "N").replaceAll("Ñ", "N~");
		String textoFormateado = Normalizer.normalize(texto,
				Normalizer.Form.NFD);
		textoFormateado = textoFormateado.replaceAll("N~", "Ñ");
		Pattern patron = Pattern.compile("[^\\w Ñ]");
		return patron.matcher(textoFormateado).replaceAll("");		
	}
	
}