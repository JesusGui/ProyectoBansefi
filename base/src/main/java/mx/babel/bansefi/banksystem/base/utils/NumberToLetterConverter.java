package mx.babel.bansefi.banksystem.base.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Locale;

/**
 * Esta clase provee la funcionalidad de convertir un numero representado en
 * digitos a una representacion en letras. Mejorado para leer centavos
 *
 * @author BABEL
 */
public abstract class NumberToLetterConverter {

    private static final String CTE_PARENTISIS = "(";
    private static final String CTE_CIERRE = "/100)";
    private static final String CTE_CIERRE_MN = "/100 M.N.)";

    private static final String[] UNIDADES = { "", "UN ", "DOS ", "TRES ",
            "CUATRO ", "CINCO ", "SEIS ", "SIETE ", "OCHO ", "NUEVE ", "DIEZ ",
            "ONCE ", "DOCE ", "TRECE ", "CATORCE ", "QUINCE ", "DIECISEIS",
            "DIECISIETE", "DIECIOCHO", "DIECINUEVE", "VEINTE" };

    private static final String[] DECENAS = { "VENTI", "TREINTA ", "CUARENTA ",
            "CINCUENTA ", "SESENTA ", "SETENTA ", "OCHENTA ", "NOVENTA ",
            "CIEN " };

    private static final String[] CENTENAS = { "CIENTO ", "DOSCIENTOS ",
            "TRESCIENTOS ", "CUATROCIENTOS ", "QUINIENTOS ", "SEISCIENTOS ",
            "SETECIENTOS ", "OCHOCIENTOS ", "NOVECIENTOS " };

    /**
     * Convierte a letras un numero de la forma $123,456.32
     *
     * @param number
     *            Numero en representacion texto
     * @throws NumberFormatException
     *             Si valor del numero no es valido (fuera de rango o )
     * @return Numero en letras
     */
    public static String convertNumberToLetter(final String number)
            throws NumberFormatException {
        return convertNumberToLetter(Double.parseDouble(number));
    }

    /**
     * Convierte un numero en representacion numerica a uno en representacion de
     * texto. El numero es valido si esta entre 0 y 999'999.999
     *
     * @param number
     *            Numero a convertir
     * @return Numero convertido a texto
     * @throws NumberFormatException
     *             Si el numero esta fuera del rango
     */
    public static String convertNumberToLetter(double doubleNumber)
            throws NumberFormatException {

        java.util.Locale.setDefault(new Locale("es","MX"));

        final StringBuilder converted = new StringBuilder();

        final String patternThreeDecimalPoints = "#.###";

        final DecimalFormat format = new DecimalFormat(patternThreeDecimalPoints);
        format.setRoundingMode(RoundingMode.DOWN);

        // formateamos el numero, para ajustarlo a el formato de tres puntos
        // decimales
        final String formatedDouble = format.format(doubleNumber);
        doubleNumber = Double.parseDouble(formatedDouble);


     // Validamos que sea un numero legal
        /*if (doubleNumber > 999999999) {
            throw new NumberFormatException(
                    "El numero es mayor de 999'999.999, "
                            + "no es posible convertirlo");
        }*/
        
        // Validamos que sea un numero legal
        if (doubleNumber > 128000000000d) {
            throw new NumberFormatException(
            		"El numero es mayor de 128,000,000,000.000"
            				  + "no es posible convertirlo");
        }

        if (doubleNumber < 0) {
            throw new NumberFormatException("El numero debe ser positivo");
        }

        final String splitNumber[] = String.valueOf(formatedDouble).replace('.', '#')
                .split("#");
        
     // Descompone el trio de miles de millones
        int milMillones = 0;
        if(splitNumber[0].length() > 9){
        	milMillones = Integer.parseInt(String.valueOf(getDigitAt(splitNumber[0],
                    11))
                    + String.valueOf(getDigitAt(splitNumber[0], 10))
                    + String.valueOf(getDigitAt(splitNumber[0], 9)));
            if (milMillones == 1) {
                converted.append("UN MIL MILLONES ");
            } else if (milMillones > 1) {
                converted.append(convertNumber(String.valueOf(milMillones))
                        + "MIL MILLONES ");
            }
        }

        // Descompone el trio de millones
        final int millon = Integer.parseInt(String.valueOf(getDigitAt(splitNumber[0],
                8))
                + String.valueOf(getDigitAt(splitNumber[0], 7))
                + String.valueOf(getDigitAt(splitNumber[0], 6)));
        if (millon == 1) {
            converted.append("UN MILLON ");
        } else if (millon > 1) {
            converted.append(convertNumber(String.valueOf(millon))
                    + "MILLONES ");
        }

        // Descompone el trio de miles
        final int miles = Integer.parseInt(String.valueOf(getDigitAt(splitNumber[0],
                5))
                + String.valueOf(getDigitAt(splitNumber[0], 4))
                + String.valueOf(getDigitAt(splitNumber[0], 3)));
        if (miles == 1) {
            converted.append("MIL ");
        } else if (miles > 1) {
            converted.append(convertNumber(String.valueOf(miles)) + "MIL ");
        }

        // Descompone el ultimo trio de unidades
        final int cientos = Integer.parseInt(String.valueOf(getDigitAt(
                splitNumber[0], 2))
                + String.valueOf(getDigitAt(splitNumber[0], 1))
                + String.valueOf(getDigitAt(splitNumber[0], 0)));
        if (cientos == 1) {
            converted.append("UN");
        }

        if (milMillones + millon + miles + cientos == 0) {
            converted.append("CERO");
        }
        if (cientos > 1) {
            converted.append(convertNumber(String.valueOf(cientos)));
        }
        
        if(milMillones > 0
        		&& millon == 0 && miles == 0 && cientos == 0){
        	converted.append("DE");
        }
        
        if(millon > 0 && miles == 0 && cientos == 0){
        	converted.append("DE");
        }

        converted.append(" PESOS");

        return converted.toString();


    }

    public static String convertirImporteAImporteEnletra(final double importe){
        final StringBuffer resultado = new StringBuffer().append(NumberToLetterConverter.CTE_PARENTISIS).append(NumberToLetterConverter.convertNumberToLetter(importe)).append(" ").append(NumberToLetterConverter.calculaCentavos(importe)).append(NumberToLetterConverter.CTE_CIERRE);
        return resultado.toString();
    }

    public static String convertirImporteAImporteEnletraMN(final double importe){
        final StringBuffer resultado = new StringBuffer().append(NumberToLetterConverter.CTE_PARENTISIS).append(NumberToLetterConverter.convertNumberToLetter(importe)).append(" ").append(NumberToLetterConverter.calculaCentavos(importe)).append(NumberToLetterConverter.CTE_CIERRE_MN);
        return resultado.toString();
    }

    private static String calculaCentavos(final double importe){
        java.util.Locale.setDefault(new Locale("es","MX"));
        final String patternTwoDecimalPoints = "#.00";
        final String importeStr = new DecimalFormat(patternTwoDecimalPoints).format(importe);
        // centavosStr
        return importeStr.substring(importeStr.lastIndexOf(".")+1, importeStr.length());
    }

    /**
     * Convierte los trios de numeros que componen las unidades, las decenas y
     * las centenas del numero.
     *
     * @param number
     *            Numero a convetir en digitos
     * @return Numero convertido en letras
     */
    private static String convertNumber(final String number) {

        if (number.length() > 3) {
            throw new NumberFormatException(
                    "La longitud maxima debe ser 3 digitos");
        }

        // Caso especial con el 100
        if (number.equals("100")) {
            return "CIEN";
        }

        final StringBuilder output = new StringBuilder();
        if (getDigitAt(number, 2) != 0) {
            output.append(CENTENAS[getDigitAt(number, 2) - 1]);
        }

        final int k = Integer.parseInt(String.valueOf(getDigitAt(number, 1))
                + String.valueOf(getDigitAt(number, 0)));

        if (k <= 20) {
            output.append(UNIDADES[k]);
        } else if (k > 30 && getDigitAt(number, 0) != 0) {
            output.append(DECENAS[getDigitAt(number, 1) - 2] + "Y "
                    + UNIDADES[getDigitAt(number, 0)]);
        } else {
            output.append(DECENAS[getDigitAt(number, 1) - 2]
                    + UNIDADES[getDigitAt(number, 0)]);
        }

        return output.toString();
    }

    /**
     * Retorna el digito numerico en la posicion indicada de derecha a izquierda
     *
     * @param origin
     *            Cadena en la cual se busca el digito
     * @param position
     *            Posicion de derecha a izquierda a retornar
     * @return Digito ubicado en la posicion indicada
     */
    private static int getDigitAt(final String origin, final int position) {
        if (origin.length() > position && position >= 0) {
            return origin.charAt(origin.length() - position - 1) - 48;
        }
        return 0;
    }

}