package mx.babel.bansefi.banksystem.base.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Util para fechas.
 * @author eliot.vasquez
 *
 */
public class FechaUtils {
	
    private static final Logger LOGGER = LogManager.getLogger(FechaUtils.class.getName());
    
	/**
	 * Contructor.
	 */
	public FechaUtils(){
		super();
	}

    /**
     * Diferencias entre dos fechas.
     * @param fechaInicial La fecha de inicio
     * @param fechaFinal La fecha de fin
     * @return Retorna el numero de dias entre dos fechas
     */
    public static int diferenciaDeFechas(Date fechaInicial, Date fechaFinal) throws ControlableException{

        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String fechaInicioString = df.format(fechaInicial);
        try {
            fechaInicial = df.parse(fechaInicioString);
        } catch (ParseException ex) {
            LOGGER.error("Se ha generado una excepción al formatear la fecha: "+ex.getMessage());
            throw new ControlableException("Se ha generado una excepción al formatear la fecha inicial:",ex);
        }

        String fechaFinalString = df.format(fechaFinal);
        try {
            fechaFinal = df.parse(fechaFinalString);
        } catch (ParseException ex) {
            LOGGER.error("Se ha generado una excepción al formatear la fecha: "+ex.getMessage());
            throw new ControlableException("Se ha generado una excepción al formatear la fecha final:",ex);
        }

        long fechaInicialMs = fechaInicial.getTime();
        long fechaFinalMs = fechaFinal.getTime();
        long diferencia = fechaFinalMs - fechaInicialMs;
        double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
        return ((int) dias);
    }    
    
    /**
     * Convierte una fecha en una cadena de texto con el formato 
     * especificado.
     * @param fecha
     * @param formato
     * @return Cadena de texto con fecha formateada.
     */
    public static String formatFecha(Date fecha, String formato){
        return new SimpleDateFormat(formato).format(fecha);
    }
    
    /**
     * Convierte una String en un date con el formato especificado
     * especificado.
     * @param fecha
     * @param formato
     * @return Date.
     */
    public static Date formateaFecha(String fecha, String formato) throws ControlableException{
        try {
            return new SimpleDateFormat(formato).parse(fecha);
        } catch (ParseException e) {
            LOGGER.error("Se ha generado una excepción al formatear la fecha: "+e.getMessage());
            throw new ControlableException("Se ha generado una excepción al formatear la fecha:",e);
        }
    }
    
    /**
     * @param fecha
     * @return boolean
     * Metodo que es usado para saber si una fecha es habil (Considerando que sea diferente a sabado y domingo)
     */
    public static boolean esDiaHabil(Calendar fecha){
        if(fecha.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY && fecha.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * @param listaFechasFestivas
     * @param diaConsultar
     * @return boolean
     * Metodo usado para saber si una fecha esta entre los dias festivos.
     */
    public static boolean esDiaFestivo(List<Date> listaFechasFestivas, Calendar diaConsultar){
        for(Date fecha : listaFechasFestivas){
            //Si las fechas son iguales retorna verdadero
            if(diaConsultar.compareTo(dateACalendar(fecha)) == 0){
                return true;
            }
        }
        
        return false;
    }
    
    public static Calendar dateACalendar(Date fecha){
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        return cal;
    }
}
