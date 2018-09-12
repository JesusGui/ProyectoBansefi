package mx.babel.bansefi.banksystem.cuentas.backends;

import java.io.Serializable;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.beans.EsperaBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.espera.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.espera.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.espera.EsperaServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.espera.ResponseBansefi.OTRCXSCIESPERAOBTETR.TRCXSCIESPERAOBTEEVT;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend que se ejecuta para generar un retardo en el Host
 * @author manuel.nieto
 *
 */
@Component
public class EsperaBackEnd extends BackEndBean implements Serializable{

	private static final long serialVersionUID = -7995198322861165795L;	
	
	private static final Logger LOGGER = LogManager
			.getLogger(EsperaBackEnd.class);
	
	@Autowired
	ServicioWebUtils servicioWebUtils;

	public void ejecutarTRN(final EsperaBean respuestaCancelacion){
		final Ejecutar.ITRCXSCIESPERAOBTETR request = initPeticion(respuestaCancelacion);

		final EjecutarResult respuesta = ejecutarWS(request);

		super.verificaRespuesta(respuesta);
		
		if(respuesta != null){
			TRCXSCIESPERAOBTEEVT respCorta = respuesta.getResponseBansefi().getOTRCXSCIESPERAOBTETR().getTRCXSCIESPERAOBTEEVT();
			respuestaCancelacion.setFsEstado(respCorta.getFSESTADO());
			respuestaCancelacion.setFsIntentos(respCorta.getFSINTENTOS());
			respuestaCancelacion.setFsIntervalo(respCorta.getFSINTERVALO());
		}
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio
	 * web.
	 * 
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRCXSCIESPERAOBTETR initPeticion(
			final EsperaBean respuestaCancelacion) {
		final Ejecutar.ITRCXSCIESPERAOBTETR peticion = new Ejecutar.ITRCXSCIESPERAOBTETR();

		super.initialize(peticion);
		
		peticion.getCXCONSULTATECNICA().setSTDCHAR01("X");
		peticion.getTRCXSCIESPERAOBTEEVT().getCXSCIESPERATAUP().setCODTX(CodTxConstants.COD_TX_TR_CX_SCI_ESPERA_OBTE_TRN);
		peticion.getTRCXSCIESPERAOBTEEVT().getCXSCIESPERATAUP().setFECHAOPRCN(respuestaCancelacion.getFechaOperacion());
		peticion.getTRCXSCIESPERAOBTEEVT().getCXSCIESPERATAUP().setHORAOPRCN(respuestaCancelacion.getHoraOperacion());
		peticion.getTRCXSCIESPERAOBTEEVT().getCXSCIESPERATAUP().setIDINTERNOTERMTN(respuestaCancelacion.getTerminal());
		peticion.getTRCXSCIESPERAOBTEEVT().getCXSCIESPERATAUP().setNUMSECUEN(respuestaCancelacion.getSecuencia());
		
		//STD
		peticion.getSTDTRNIPARMV().setCODTX(CodTxConstants.COD_TX_TR_CANCE_TOTAL_AC_TRN);
		peticion.getSTDTRNIPARMV().setIDINTERNOTERMTN(this.getTerminal());		

		return peticion;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto
	 *            Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRCXSCIESPERAOBTETR contexto)
			throws NoControlableException {
		EjecutarResult respuesta = null;
		try {
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					EsperaServicio.class, contexto);
		} catch (final NoControlableException e) {
			LOGGER.debug("Error cancelacion cuenta NoControlable--", e);
			throw new NoControlableException(
					"Error al invocar servicio web de cancelacion "
							+ " de acuerdo.", e);
		}
		return respuesta;
	}

}
