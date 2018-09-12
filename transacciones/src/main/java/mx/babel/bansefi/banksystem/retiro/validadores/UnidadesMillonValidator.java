package mx.babel.bansefi.banksystem.retiro.validadores;

import java.math.BigDecimal;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Validador para las unidades de millón.
 * @author alejandro.perez
 *
 */

@FacesValidator("unidadesMillonValidatorRetiro")
public class UnidadesMillonValidator implements Validator{

    private static final BigDecimal CTE_MILLON = new BigDecimal("1000000");
    
    /**
     * Constructor (lo pide el checkStyle).
     */
    public UnidadesMillonValidator(){}
    
    
    @Override
    public void validate(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {
        
        //Obtenemos el importe del ingreso que debe
        //ser pasado mediante un param en la vista
        Object importeObject = component.getAttributes().get("importe");
        //Si no se ha introducido importe se sale
        if (importeObject==null) {
            return;
        }
        
        BigDecimal importe = (BigDecimal) importeObject;
        
        //Si no hay unidades de millon y el importe
        //es mayor de un millon mostramos mensaje de error
        int compare = importe.compareTo(UnidadesMillonValidator.CTE_MILLON);
        if (value == null && compare >= 0) {
            throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, ""
                            , "Las unidades de millón no coinciden"));
            
        //Si es nullo el valor y el importe no llega al millon salimos
        }else if(value == null && compare == -1){
            return;
        }
        
        BigDecimal unidadesMillon = new BigDecimal(value.toString());
        
        if(compare >= 0){
            BigDecimal unidadesMillonCalculadas = 
            		importe.divide(UnidadesMillonValidator.CTE_MILLON);
            
            //Sino coinciden las unidades de millon
            //con las del importe lanzamos excepcion
            if(unidadesMillon.intValue()!=unidadesMillonCalculadas.intValue()){
                throw new ValidatorException(
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, ""
                                , "Las unidades de millón no coinciden"));
            }
        }
    }
}
