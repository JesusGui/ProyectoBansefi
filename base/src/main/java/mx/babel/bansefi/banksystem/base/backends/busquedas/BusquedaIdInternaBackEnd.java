package mx.babel.bansefi.banksystem.base.backends.busquedas;

import java.io.Serializable;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.busqueda.PersonaGestorBusquedaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClienteBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.webservices.busquedaidinterna.BusquedaIdPersonaServicio;
import mx.babel.bansefi.banksystem.base.webservices.busquedaidinterna.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.busquedaidinterna.Ejecutar.ITRPECLCBDACNSTRNI;
import mx.babel.bansefi.banksystem.base.webservices.busquedaidinterna.Ejecutar.ITRPECLCBDACNSTRNI.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.base.webservices.busquedaidinterna.Ejecutar.ITRPECLCBDACNSTRNI.TRPECLCBDACNSEVTY.PEPERSP;
import mx.babel.bansefi.banksystem.base.webservices.busquedaidinterna.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.busquedaidinterna.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.wrappers.ClienteWrapper;
import mx.babel.bansefi.banksystem.base.wrappers.WrapperBusquedasUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd para la busqueda de Id Interna de un Gestor.
 *
 * @author alejandro.pineda
 *
 */
@Component
public class BusquedaIdInternaBackEnd extends BackEndBean implements
		Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 9154621650540624444L;

	@Autowired
	private WrapperBusquedasUtils wrapperBusquedasUtils;

	@Autowired
    ClienteWrapper clienteWrapper;

	@Autowired
	private ServicioWebUtils servicioWebUtils;

	public BusquedaIdInternaBackEnd() {

	}

	/**
	 * Método para ejecutar el servicio de Consulta de Id Interna de Gestor.
	 *
	 * @param obj
	 *            Bean con el dato a buscar
	 * @return Resultado de la búsqueda
	 * @throws NoControlableException
	 *             Excepción no controlable.
	 * @throws ControlableException
	 *             Excepción Controlable.
	 */
	public PersonaGestorBusquedaBean ejecutarTRN(final Object obj)
			throws NoControlableException, ControlableException {
		final PersonaGestorBusquedaBean personaBusqueda = (PersonaGestorBusquedaBean) obj;


        final Ejecutar.ITRPECLCBDACNSTRNI contexto = initPeticion(personaBusqueda.getIdInterna());

		return this.ejecutarServicio(contexto, personaBusqueda);

	}

	private PersonaGestorBusquedaBean ejecutarServicio(
			final ITRPECLCBDACNSTRNI contexto,
			final PersonaGestorBusquedaBean personaBusqueda)
			throws NoControlableException, ControlableException {
		PersonaGestorBusquedaBean personaResultado = null;

		final EjecutarResult respuesta = ejecutarWS(contexto);
		super.verificaRespuesta(respuesta);
		
		personaBusqueda.setMasDatos(false);
		personaResultado = wrapperBusquedasUtils
				.wrappBeanResultadoIdInterna(respuesta.getResponseBansefi()
						.getOTRPECLCBDACNSTRNO().getTRPECLCBDACNSEVTZ());
		return personaResultado;
	}


	public ClienteBean ejecutarTRN(final int idInterna)
            throws ControlableException, NoControlableException{
        ClienteBean result = null;
        final Ejecutar.ITRPECLCBDACNSTRNI request = initPeticion(idInterna);
        
        final EjecutarResult respuesta = ejecutarWS(request);

        try{
            super.verificaRespuesta(respuesta);
        }catch (final ControlableException ce){
            if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
                throw ce;
            }else{
                return null;
            }
        }
        
        if(verificaResponseBansefi(respuesta)){
            result = getResponse(respuesta.getResponseBansefi());
        }
        return result;
    }

	private ClienteBean getResponse(final ResponseBansefi response)
            throws NoControlableException, ControlableException{
        ClienteBean respuesta = null;        
        if(verificaRespuesta(response)){
            respuesta = clienteWrapper.wrappBean(
                    response.getOTRPECLCBDACNSTRNO().getTRPECLCBDACNSEVTZ());
        }
        return respuesta;
    }

    /**
     * Función que inicializa el objeto para realizar la petición al servicio web.
     *
     * @return Objeto a ser enviado al servicio web.
     */
    private Ejecutar.ITRPECLCBDACNSTRNI initPeticion(final int idInterna){
        final Ejecutar.ITRPECLCBDACNSTRNI peticion = new Ejecutar.ITRPECLCBDACNSTRNI();

        super.initialize(peticion);

        final PEPERSP pepersp = peticion.getTRPECLCBDACNSEVTY().getPEPERSP();
        pepersp.setCODNRBEEN(this.getEntidad());
        pepersp.setIDINTERNOPE(idInterna);

        final STDTRNIPARMV stdtrniparmv = peticion.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TR_PE_CL_CB_DA_CNS_TRN);

        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());
        return peticion;
    }

    /**
     * Función para invocar al servicio web y obtener su respuesta.
     *
     * @param contexto Objeto de petición al servicio web
     * @return La respuesta del servicio web.
     */
    private EjecutarResult ejecutarWS(final Ejecutar.ITRPECLCBDACNSTRNI contexto)
            throws NoControlableException{
        EjecutarResult respuesta = null;

        try{
            respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
                    BusquedaIdPersonaServicio.class, contexto);
        }catch(final NoControlableException e){
            throw new NoControlableException("Error al invocar servicio web de consulta "
                    + " de existencia de Id interno.", e);
        }
        return respuesta;
    }

    /**
     * Función que valida que la respuesta del servidor no este vacía.
     *
     * @param respuesta Objeto respuesta del servicio web
     * @return <code>true</code> si la respuesta no esta vacía.
     */
    private Boolean verificaResponseBansefi(final EjecutarResult respuesta){
        return (respuesta != null && respuesta.getResponseBansefi() != null);
    }

    /**
     * Función que valida que la respuesta Bansefi contenga un objeto con los datos.
     *
     * @param response Respuesta Bansefi con datos
     * @return <code>true</code> si la respuesta Bansefi contiene los datos
     */
    private Boolean verificaRespuesta(final ResponseBansefi response){
        Boolean noNulo = false;
        if(response != null && response.getOTRPECLCBDACNSTRNO() != null &&
            response.getOTRPECLCBDACNSTRNO().getTRPECLCBDACNSEVTZ()!= null ){
            noNulo = true;
        }
        return noNulo;
    }

}
