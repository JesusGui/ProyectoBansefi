package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultanivelcliente.ConsultaNivelClienteServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultanivelcliente.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultanivelcliente.Ejecutar.ITRBUSCANIVELPERTRNI.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultanivelcliente.Ejecutar.ITRBUSCANIVELPERTRNI.TRBUSCANIVELPERV.PEPERSP;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultanivelcliente.EjecutarResult;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class ConsultaNivelClienteBackend extends BackEndBean{

	private static final long serialVersionUID = 6564921993348101257L;


    @Autowired
    ServicioWebUtils servicioWebUtils;

	public String ejecutarTRN(final int idInterno){
		final Ejecutar.ITRBUSCANIVELPERTRNI request = initPeticion(idInterno);

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
		
		return StringUtils.trimToNull(respuesta.getResponseBansefi().getOTRBUSCANIVELPERTRNO().getSTDCHAR02());
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRBUSCANIVELPERTRNI initPeticion(final int idInterno){
		final Ejecutar.ITRBUSCANIVELPERTRNI peticion = new Ejecutar.ITRBUSCANIVELPERTRNI();

		super.initialize(peticion);
		final PEPERSP pepersp = peticion.getTRBUSCANIVELPERV().getPEPERSP();
		pepersp.setCODNRBEEN(this.getEntidad());
        pepersp.setIDINTERNOPE(idInterno);

        final STDTRNIPARMV stdtrniparmv = peticion.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_BUSCA_NIVEL_PER_TRN);

        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());
		return peticion;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRBUSCANIVELPERTRNI contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaNivelClienteServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "de nivel cliente.", e);
		}
		return respuesta;
	}

}
