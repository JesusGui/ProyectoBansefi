package mx.babel.arq.comun.utils;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * Utilidad para generar identificadores únicos mediante la clase UUID
 * (Universally Unique Identifier).
 * 
 * @author omar.marquez
 * 
 */
public class UUIDGeneradorUtils {

	/**
	 * Método que genera un identificador alfanumérico.
	 * 
	 * @return UUID original
	 */
	public String generarIDAlfanumerico() {
		return UUID.randomUUID().toString();
	}

	/**
	 * Método que genera un identificador numérico.
	 * 
	 * @return UUID convertido a BigInteger
	 */
	public BigInteger generarIDNumerico() {
		UUID uuid = UUID.randomUUID();
		long highbits = uuid.getMostSignificantBits();
		long lowbits = uuid.getLeastSignificantBits();
		byte bytes[] = ByteBuffer.allocate(16).putLong(highbits)
				.putLong(lowbits).array();
		return new BigInteger(bytes).abs();
	}

}