package mx.babel.bansefi.banksystem.transacciones.backends;

import java.math.BigInteger;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.transacciones.beans.RetiroReq;
import mx.babel.bansefi.banksystem.transacciones.beans.RetiroRes;
import mx.babel.bansefi.banksystem.webservices.retiro.EjecutarResult;
import mx.babel.bansefi.banksystem.webservices.retiro.ResponseBansefi;
import mx.babel.bansefi.banksystem.webservices.retiro.RetiroServicio;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Implementación de la interface de retiro.
 * @author alejandro.perez
 *
 */

@Component
public class RetiroBackEnd extends BackEndBean{
	
	private static final long serialVersionUID = 441385530880742785L;
	@Autowired
	ServicioWebUtils servicioWebUtils;

	public RetiroRes realizaRetiro(RetiroReq request){
		//Controlamos los parametros de entrada
        if(!this.controlaParametroEntrada(request)){
            throw new ControlableException(
                    "No se puede realizar la consulta",
                    "Alguno de los parametros de entrada es vacio");
        }
        
        EjecutarResult respuesta = null;
        RetiroRes response = null;
        try{
        	
        	respuesta = (EjecutarResult) servicioWebUtils
        			.ejecutarWS(RetiroServicio.class,super.getEntidad(), BigInteger.valueOf(Long.parseLong(request.getCuenta())),
        					request.getImporte(), request.getFecvalor(),request.getCodope(), request.getCveidof(), request.getIfe(),request.getConcepto());
        	
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
        	//SE VALIDA LA RESPUESTA RECIBIDA POR HOST
            super.verificaRespuestaWS(respuesta);
            
            response = new RetiroRes();
            response.setEstatus(respuesta.getESTATUS());
            response.setCodigo(respuesta.getCODIGO());
            response.setMensaje(respuesta.getMENSAJE());
            response.setNumeroTarea(respuesta.getNUMTASK());
            response.setTransaccionId(respuesta.getTRANID());
        
            if(respuesta.getResponseBansefi()!=null){
                ResponseBansefi responseBansefi = respuesta.getResponseBansefi();
                response.setAcuerdo(responseBansefi.getACUERDO());
                response.setCentro(responseBansefi.getCENTRO());
                response.setDigito(responseBansefi.getDIGITO());
                response.setImporte(responseBansefi.getIMPORTE());
                response.setMovimiento(responseBansefi.getMOVIMIENTO());
                response.setPlaza(responseBansefi.getPLAZA());
                response.setSecuencia(responseBansefi.getSECUENCIA());
                response.setTerminal(responseBansefi.getTERMINAL());
                response.setTitular(responseBansefi.getTITULAR());
                response.setFechaoprcn(responseBansefi.getFECHAOPRCN());
                response.setHoraoprcn(responseBansefi.getHORAOPRCN());
                response.setSaldo(responseBansefi.getSALDO());
                
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

    private boolean controlaParametroEntrada(final RetiroReq request){
        if(request==null || 
                !StringUtils.isNotBlank(request.getEntidad()) ||
                !StringUtils.isNotBlank(request.getIpheader()) ||
                !StringUtils.isNotBlank(request.getPassheader()) ||
                !StringUtils.isNotBlank(request.getUsuarioId())||
                !StringUtils.isNoneBlank(request.getCodope()) ||
                !StringUtils.isNoneBlank(request.getFecvalor()) ||
                !StringUtils.isNoneBlank(request.getImporte().toString()) ||
                !StringUtils.isNoneBlank(request.getCveidof())
        	)
        {
            return false;
        }else{
            return true;
        }
	}
    
    
    
}
