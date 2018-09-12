package mx.babel.bansefi.banksystem.transacciones.validadores;

import java.math.BigDecimal;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Se encarga de comprobar si las unidades de millon corresponden
 *  con la cantidad introducida en importe.
 * En caso de fallar la validación muestra un mensaje de error
 * @author luis.gcastellano
 */
@FacesValidator("unidadesMillonValidatorRetiro")
public class UnidadesMillonValidatorRetiro implements Validator{

    private static final double CTE_MILLON = 1000000;
    
    /**
     * Constructor (lo pide el checkStyle).
     */
    public UnidadesMillonValidatorRetiro(){}
    
    
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
        
        BigDecimal importe = (BigDecimal)importeObject;
        Double importeValor = importe.doubleValue();
        
        //Si no hay unidades de millon y el importe
        //es mayor de un millon mostramos mensaje de error
        if (value == null && importeValor>=UnidadesMillonValidatorRetiro.CTE_MILLON) {
            throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, ""
                            , "Las unidades de millón no coinciden"));
            
        //Si es nullo el valor y el importe no llega al millon salimos
        }else if(value == null && importeValor<UnidadesMillonValidatorRetiro.CTE_MILLON){
            return;
        }
        
        int unidadesMillon = (Integer) value;
        int unidadesMillonCalculadas = -1;
        
        if(importeValor>=UnidadesMillonValidatorRetiro.CTE_MILLON){
            unidadesMillonCalculadas =
                    (int)(importeValor/UnidadesMillonValidatorRetiro.CTE_MILLON);
            
            //Sino coinciden las unidades de millon
            //con las del importe lanzamos excepcion
            if(unidadesMillon!=unidadesMillonCalculadas){
                throw new ValidatorException(
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, ""
                                , "Las unidades de millón no coinciden"));
            }
        }
    }
}
