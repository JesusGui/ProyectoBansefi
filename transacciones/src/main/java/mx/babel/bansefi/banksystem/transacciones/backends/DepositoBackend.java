package mx.babel.bansefi.banksystem.transacciones.backends;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.transacciones.beans.DepositoBean;
import mx.babel.bansefi.banksystem.transacciones.beans.DepositoRes;
import mx.babel.bansefi.banksystem.webservices.realizadeposito.DepositoServicio;
import mx.babel.bansefi.banksystem.webservices.realizadeposito.Ejecutar;
import mx.babel.bansefi.banksystem.webservices.realizadeposito.EjecutarResult;
import mx.babel.bansefi.banksystem.webservices.realizadeposito.ResponseBansefi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend que se encarga de la llamada al servicio web de realizar deposito.
 * 
 * @author luis.gcastellano
 * 
 */
@Component
public class DepositoBackend extends BackEndBean {

	private static final long serialVersionUID = -5671949902421393206L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

    private static final Logger LOGGER = LogManager.getLogger(DepositoBackend.class
            .getName());
    
    private static final DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public DepositoRes ejecutarWS(DepositoBean req)
            throws NoControlableException, ControlableException {

        LOGGER.debug("Entra al metrodo ejectuarWS");

        DepositoRes res = this.getDepositoResponse(req);
        
       	LOGGER.debug("Sale del metrodo ejectuarWS");
        return res;
    }

    /**
     * Función para realizar el deposito invocando un servicio web.
     * 
     * @param req
     *            bean con los datos de entrada
     * @return Resultado del deposito.
     */
    public DepositoRes getDepositoResponse(DepositoBean req)
            throws NoControlableException, ControlableException {
        LOGGER.debug("Entra al metrodo getDepositoResponse");

        Ejecutar contexto = this.initPeticion(req);

        EjecutarResult respuesta = ejecutarWS(contexto);
        
        //SE VALIDA LA RESPUESTA RECIBIDA POR HOST
        super.verificaRespuestaWS(respuesta);

        DepositoRes depositoRes = null;

        if (verificaResponseBansefi(respuesta)) {
            depositoRes = getDepositoRes(respuesta);
        }else{
        	return null;
        }
        LOGGER.debug("Sale del metrodo getDepositoResponse");
        return depositoRes;
    }

    /**
     * Función encargada de obtener la lista de entidadBusqueda a partir de la
     * respuesta del servicio web
     * 
     * @param idInterno
     *            El id interno de la persona moral
     * @param response
     *            El objeto de reultado enviado por el servicio web.
     * @return La entidad con los datos recibidos del ser web
     */
    private DepositoRes getDepositoRes(EjecutarResult response)
            throws NoControlableException, ControlableException {
        DepositoRes depositoRes = null;
        verificaResultado(response);
        if (verificaRespuestaDeposito(response.getResponseBansefi()) && !this.codigoControlable(response)) {
            depositoRes = new DepositoRes();
            depositoRes.setAcuerdo(response.getResponseBansefi().getACUERDO());
            depositoRes.setSucursal(response.getResponseBansefi().getCENTRO());
            depositoRes.setDigito(response.getResponseBansefi().getDIGITO());
            depositoRes.setFechaOperacion(response.getResponseBansefi()
                    .getFECHAOPERACION());
            depositoRes.setHoraOperacion(response.getResponseBansefi()
                    .getHORAOPERACION());
            depositoRes.setImporte(response.getResponseBansefi().getIMPORTE());
            depositoRes.setMovimiento(response.getResponseBansefi()
                    .getMOVIMIENTO());
            depositoRes.setPlaza(response.getResponseBansefi().getPLAZA());
            depositoRes.setSecuencia(response.getResponseBansefi()
                    .getSECUENCIA());
            depositoRes
                    .setTerminal(response.getResponseBansefi().getTERMINAL());
            depositoRes.setTitular(response.getResponseBansefi().getTITULAR());
            depositoRes.setEstatus(response.getESTATUS());
        }        
        else if(this.codigoControlable(response)){
        	depositoRes = new DepositoRes();
        	depositoRes.setCodigo(response.getCODIGO().trim());
        	depositoRes.setMensaje(response.getMENSAJE());
        }
        return depositoRes;
    }

    /**
     * Función que valida que la respuesta Bansefi contenga un objeto con los
     * datos de la entidad
     * 
     * @param response
     *            Respuesta Bansefi con datos de la entidad
     * @return <code>true</code> si la respuesta Bansefi contiene una entidad
     */
    private Boolean verificaRespuestaDeposito(ResponseBansefi response) {
        Boolean noNulo = false;

        if (response != null) {
            noNulo = true;
        }
        return noNulo;
    }

    /**
     * Método que verifica los códigos de respuesta entregados por el servicio
     * web.
     * 
     * @param response
     *            Respuesta Bansefi con mensajes
     * @throws NoControlableException
     *             Excepción controlada de errores en front end
     * @throws ControlableException
     *             Excepción no controlada de errores en host
     */
    private void verificaResultado(EjecutarResult response)
            throws NoControlableException, ControlableException {

        if (!"0".equals(response.getESTATUS()) && !this.codigoControlable(response)) {
            throw new NoControlableException(
                    "Se ha producido un error al invocar al servicio de realizar deposito",
                    response.getMENSAJE());
        }
    }
    
    /**
     * Función para controlar los codigos recibidos por el servicio
     * 
     * @return T / F dependiendo si se ha validado el código
     */
    private boolean codigoControlable(EjecutarResult response){
    	switch(response.getCODIGO().trim()){
    		case "ARQE159" : return true;
    		case "ARQE135" : return true;
    		case "ARQE114" : return true;
    		case "WNOT8545" : return true;
    		case "NTFE0092" : return true;
    		case "DOME0004" : return true;
    		case "CHEE021" : return true;
    		case "ARQE0092" : return true;
    		default : return false;
    	}
    }

    /**
     * Función que valida que la respuesta del servidor no este vacía.
     * 
     * @param respuesta
     *            Objeto respuesta del servicio web
     * @return <code>true</code> si la respuesta no esta vacía.
     */
    private Boolean verificaResponseBansefi(EjecutarResult respuesta) {
        Boolean noNulo = false;
        if (respuesta != null && respuesta.getResponseBansefi() != null) {
            noNulo = true;
        }
        return noNulo;
    }

    /**
     * Función para invocar al servicio web y obtener su respuesta.
     * 
     * @param contexto
     *            Objeto de petición al servicio web
     * @return La respuesta del servicio web.
     */
    private EjecutarResult ejecutarWS(Ejecutar contexto)
            throws NoControlableException {
        EjecutarResult respuesta = null;

        try {
            contexto.setACUERDO(contexto.getACUERDO());
            contexto.setCODOPERACION(contexto.getCODOPERACION());
            contexto.setCONCEPTO(contexto.getCONCEPTO());
            contexto.setENTIDAD(super.getEntidad());
            contexto.setFECHAVALOR(contexto.getFECHAVALOR());
            contexto.setIDREFE(contexto.getIDREFE());
            contexto.setIMPORTE(contexto.getIMPORTE());
            contexto.setIPHEADER(super.getIp());
            contexto.setPASSHEADER(super.getPassword());
            contexto.setREFERENCIA(contexto.getREFERENCIA());
            contexto.setUSERHEADER(super.getUsuarioId());
            
            respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
                    DepositoServicio.class, contexto.getENTIDAD(),
                    contexto.getACUERDO(), contexto.getIMPORTE(), contexto.getFECHAVALOR(), contexto.getCODOPERACION(), contexto.getCONCEPTO(),
                    contexto.getIDREFE(), contexto.getREFERENCIA());
            
        } catch (NoControlableException e) {
            throw new NoControlableException(
                    "Error al invocar servicio web de realizar deposito", e);
        }

        return respuesta;
    }

    /**
     * Función para inicializar los atributos del objeto de peticíon al servicio
     * web.
     * 
     * @param req
     *            bean con los datos de entrada
     * @return Objeto de petición al web service
     */
    private Ejecutar initPeticion(DepositoBean req) {

        Ejecutar contexto = new Ejecutar();

        try {
            contexto.setACUERDO(new BigInteger(req.getCuentaDeposito()
                    .toString()));
            contexto.setENTIDAD(super.getEntidad());
            contexto.setIPHEADER(super.getIp());

            contexto.setPASSHEADER(super.getPassword());
            contexto.setUSERHEADER(super.getUsuarioId());
            contexto.setIMPORTE(new BigDecimal(String.valueOf(req.getImporteDeposito())));
            contexto.setCODOPERACION(req.getOperacionDeposito());
            contexto.setCONCEPTO(req.getConceptoDeposito());
            contexto.setFECHAVALOR(formatter.format(req.getFechaValorDeposito()));
            contexto.setREFERENCIA(req.getNumeroIdentificacionClienteDeposito());
            contexto.setIDREFE(req.getIdentificacionClienteDeposito());
        } catch (NullPointerException npe) {
            throw new ControlableException("No se puede realizar el depósito",
                    npe);
        } catch (NumberFormatException nfe) {
            throw new ControlableException("No se puede realizar el depósito",
                    nfe);
        } catch (IllegalArgumentException iae) {
            throw new ControlableException("No se puede realizar el depósito",
                    iae);
        }

        return contexto;
    }

}
