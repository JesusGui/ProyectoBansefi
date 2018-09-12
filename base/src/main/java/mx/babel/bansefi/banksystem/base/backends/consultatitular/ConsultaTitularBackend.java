package mx.babel.bansefi.banksystem.base.backends.consultatitular;

import java.math.BigInteger;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.webservices.consultatitularws.ConsultaTitularServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultatitularws.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultatitularws.ResponseBansefi;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Backend que se encarga de la llamada al servicio web de consulta de titular.
 * @author luis.gcastellano
 *
 */
@Component
public class ConsultaTitularBackend extends BackEndBean{

	private static final long serialVersionUID = 5257510569931374139L;

	@Autowired
	ContextoUtils contextoUtils;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
   
    public ConsultaTitularRes ejecutarWS(String cuenta)
            throws ControlableException, NoControlableException{
        
        //Controlamos los parametros de entrada
        if(!this.controlaParametroEntrada(cuenta)){
            throw new ControlableException(
                    "No se puede realizar la consulta",
                    "Alguno de los parametros de entrada es vacio");
        }
        
        ConsultaTitularRes response = null;
 
        EjecutarResult respuesta = null;
        try{
            //Realizamos la llamada al servicio
            respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaTitularServicio.class,super.getEntidad(), new BigInteger(cuenta));
        }catch (NullPointerException npe){
            throw new ControlableException(
                    "No se puede realizar la consulta",
                    npe);
        }catch (NumberFormatException nfe){
            throw new ControlableException(
                    "No se puede realizar la consulta",
                    nfe);
        }catch (IllegalArgumentException iae){
            throw new ControlableException(
                "No se puede realizar la consulta",
                iae);
        }
        if (respuesta !=null) {
            response = new ConsultaTitularRes();
            response.setEstatus(respuesta.getESTATUS());
            response.setCodigo(respuesta.getCODIGO());
            response.setMensaje(respuesta.getMENSAJE());
            response.setNumeroTarea(respuesta.getNUMTASK());
            response.setTransaccionId(respuesta.getTRANID());
            if(respuesta.getResponseBansefi()!=null
                    && respuesta.getResponseBansefi().getResponseBansefi()!=null
                    && respuesta.getResponseBansefi().getResponseBansefi().size()>0){
                List<ResponseBansefi> responseBansefi = respuesta.getResponseBansefi().getResponseBansefi();
                
                boolean asignado = false;
                if(responseBansefi.size() > 1){
                	
                	for (ResponseBansefi elemento : responseBansefi) {
                    	if(elemento.getNUMRLORDEN().compareTo(new BigInteger("0001")) == 0){
                    		response.setCurp(elemento.getIDCURP());
                            response.setRfc(elemento.getIDRFC());
                            response.setNombre(elemento.getNOMBRE());
                            response.setIdentificadorPersona(elemento.getIDINTERNOPE());
                            response.setOrdenRelacion(elemento.getNUMRLORDEN());
                            response.setNivelCuenta(elemento.getNIVELCUENTA());
                            asignado = true;
                    	}
                	}               	
                }
                	
                if(!asignado){
                	response.setCurp(responseBansefi.get(0).getIDCURP());
                	response.setRfc(responseBansefi.get(0).getIDRFC());
                	response.setNombre(responseBansefi.get(0).getNOMBRE());
                	response.setIdentificadorPersona(responseBansefi.get(0).getIDINTERNOPE());
                	response.setOrdenRelacion(responseBansefi.get(0).getNUMRLORDEN());
                	response.setNivelCuenta(responseBansefi.get(0).getNIVELCUENTA());
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
    
    private boolean controlaParametroEntrada(final String cuenta){
        if(!StringUtils.isNotBlank(cuenta) ||
                !StringUtils.isNotBlank(super.getEntidad()) ||
                !StringUtils.isNotBlank(super.getIp()) ||
                !StringUtils.isNotBlank(super.getPassword()) ||
                !StringUtils.isNotBlank(super.getUsuarioId())){
            return false;
        }else{
            return true;
        }
    }
    
}
    
