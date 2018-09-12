package mx.babel.arq.banksystem.components;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.FacesException;
import javax.faces.context.FacesContext;

import org.primefaces.component.calendar.converter.DatePatternConverter;
import org.primefaces.component.calendar.converter.PatternConverter;
import org.primefaces.component.calendar.converter.TimePatternConverter;

/**
 * Clase de utilidades requeridas componente para selección de rangos de fechas por mes
 * @author gerard.chavez
 *
 */
public class CalendarMonthRangeUtils {
  private final static List<PatternConverter> PATTERN_CONVERTERS;
    
    static {
        PATTERN_CONVERTERS = new ArrayList<PatternConverter>();
        PATTERN_CONVERTERS.add(new TimePatternConverter());
        PATTERN_CONVERTERS.add(new DatePatternConverter());
    }
    
    public static String getValueAsString(FacesContext context, CalendarMonthRange calendar) {
        Object submittedValue = calendar.getSubmittedValue();
        if(submittedValue != null) {
            return submittedValue.toString();
        }
        
        Object value = calendar.getValue();
        if(value == null) {
            return null;
        } else {
            //first ask the converter
            if(calendar.getConverter() != null) {
                return calendar.getConverter().getAsString(context, calendar, value);
            }
            //Use built-in converter
            else {
                SimpleDateFormat dateFormat = new SimpleDateFormat(calendar.calculatePattern(), calendar.calculateLocale(context));
                dateFormat.setTimeZone(calendar.calculateTimeZone());
                
                return dateFormat.format(value);
            }
        }
    }
    
    public static String getValueAsString(FacesContext context, CalendarMonthRange calendar, Object value) {      
        if(value == null) {
            return null;
        }
        
        if(value instanceof String){
            return (String) value;
        } 
        else if(value instanceof Date) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(calendar.calculatePattern(), calendar.calculateLocale(context)); 
            dateFormat.setTimeZone(calendar.calculateTimeZone());
            
            return dateFormat.format((Date) value);
        } 
        else {
            throw new FacesException("Value could be either String or java.util.Date");
        }
    }
    
    public static String getTimeOnlyValueAsString(FacesContext context, CalendarMonthRange calendar) {
        Object value = calendar.getValue();
        if(value == null) {
            return null;
        }
        
        if(value instanceof String){
            return (String) value;
        } else if(value instanceof Date) {
            SimpleDateFormat format = new SimpleDateFormat(calendar.calculateTimeOnlyPattern(), calendar.calculateLocale(context));
            format.setTimeZone(calendar.calculateTimeZone());

            return format.format(calendar.getValue());
        }
        else {
            throw new FacesException("Value could be either String or java.util.Date");
        }
    }
        
    /**
     * Converts a java date pattern to a jquery date pattern
     * 
     * @param pattern Pattern to be converted
     * @return converted pattern
     */
    public static String convertPattern(String pattern) {
        if(pattern == null) {
            return null;
        }
        else {
            String convertedPattern = pattern;
            for (PatternConverter converter : PATTERN_CONVERTERS) {
                convertedPattern = converter.convert(convertedPattern);
            }

            return convertedPattern;
        }
    }
}
