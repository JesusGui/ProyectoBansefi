/**
 *
 */
package mx.babel.arq.comun.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.primefaces.util.Base64;

/**
 * Clase de utilidades para hasheado y deshasheado de datos.
 * @author joseluis.pena
 *
 */
public class HashUtils {



    private HashUtils() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Devuelve la cadena de entrada hasheada con SHA256 y codificada en B64.
     *
     * @param convertme Input String
     * @return Transformed output string
     * @throws NoSuchAlgorithmException
     */
    public static String toB64Hash(final String convertme) throws NoSuchAlgorithmException {
        final MessageDigest md = MessageDigest.getInstance("SHA-256");

        return HashUtils.encodeB64(md.digest(convertme.getBytes()));
    }

    /**
     * Devuelve la cadena de entrada decodificada en B64.
     * @param decodeMe cadena codificada en B64
     * @return array de bytes
     */
    public static byte[] decodeB64(final String decodeMe){
        return Base64.decode(decodeMe);
    }

    /**
     *Devuelve la cadena de entrada codificada en B64.
     * @param encodeMe array de bytes a codificar
     * @return cadena codificada en B64
     */
    public static String encodeB64(final byte[] encodeMe){
        return Base64.encodeToString(encodeMe,false);
    }

}
