package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.cuentas.beans.TarifaBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultanivelacuerdo.ConsultaNivelAcuerdoServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultanivelacuerdo.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultanivelacuerdo.ResponseBansefi;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class ConsultaNivelAcuerdoBackend extends BackEndBean{

    private static final long serialVersionUID = 5483410108622890788L;

    @Autowired
    ServicioWebUtils servicioWebUtils;

    public String ejecutarWS(final TarifaBean tarifa){

        //Controlamos los parametros de entrada
        if(!this.controlaParametroEntrada(tarifa)){
            throw new ControlableException(
                    "No se puede realizar la consulta",
                    "Alguno de los parametros de entrada es vacio");
        }

        String response = null;

        EjecutarResult respuesta = null;
        try{
            //Realizamos la llamada al servicio

            respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaNivelAcuerdoServicio.class, tarifa.getLinea(),
                    tarifa.getGrupo(), tarifa.getProducto(), tarifa.getId());
        }catch (final NumberFormatException nfe){
            throw new ControlableException(
                    "No se puede realizar la consulta",
                    nfe.getCause());
        }catch (final IllegalArgumentException iae){
            throw new ControlableException(
                "No se puede realizar la consulta",
                iae.getCause());
        }
        if (respuesta !=null) {

            if(respuesta.getResponseBansefi()!=null){
                final ResponseBansefi responseBansefi = respuesta.getResponseBansefi();
                if(StringUtils.isNotBlank(responseBansefi.getSALNIVEL())){
                    response = responseBansefi.getSALNIVEL().trim();
                }else{
                    response = "NIVEL 4";
                }
            }else{
                throw new ControlableException(
                        "No se puede realizar la consulta",
                        "Alguno de los parametros de salida es vacio");
            }
        }else{
            throw new NoControlableException(
                    "No hay respuesta al servicio",
                    "La respuesta es null");
        }

         return response;
    }

    private boolean controlaParametroEntrada(final TarifaBean tarifa){
        if(tarifa == null ||
                !StringUtils.isNotBlank(tarifa.getLinea()) ||
                !StringUtils.isNotBlank(tarifa.getGrupo())||
                !StringUtils.isNotBlank(tarifa.getProducto()) ||
                !StringUtils.isNotBlank(tarifa.getGrupo())||
                !StringUtils.isNotBlank(super.getIp()) ||
                !StringUtils.isNotBlank(super.getPassword()) ||
                !StringUtils.isNotBlank(super.getUsuarioId())){
            return false;
        }else{
            return true;
        }
    }

}
