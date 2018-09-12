package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.webservices.retrocesioncancelacionacuerdo.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.retrocesioncancelacionacuerdo.Ejecutar.ITRRETCANCTOTALTRNI.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.retrocesioncancelacionacuerdo.Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.ACACP;
import mx.babel.bansefi.banksystem.cuentas.webservices.retrocesioncancelacionacuerdo.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.retrocesioncancelacionacuerdo.RetrocesionCancelacionAcuerdoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class RetrocesionCancelacionAcuerdoBackend extends BackEndBean{

	private static final long serialVersionUID = 6564921993348101257L;

    @Autowired
    ServicioWebUtils servicioWebUtils;

	public void ejecutarTRN(final long numAcuerdo) throws ControlableException, NoControlableException{
		final Ejecutar.ITRRETCANCTOTALTRNI request = initPeticion(numAcuerdo);

		final EjecutarResult respuesta = ejecutarWS(request);

		super.verificaRespuesta(respuesta);		
		
	}



	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRRETCANCTOTALTRNI initPeticion(final Long numAcuerdo){
		final Ejecutar.ITRRETCANCTOTALTRNI peticion = new Ejecutar.ITRRETCANCTOTALTRNI();

		super.initialize(peticion);


		final ACACP acacp = peticion.getTRRETCANCTOTALEVTY().getACACP();
		acacp.setCODNRBEEN(this.getEntidad());
		acacp.setNUMSECAC(numAcuerdo);

        final STDTRNIPARMV stdtrniparmv = peticion.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_RET_CANC_TOTAL_TRN);
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());

		return peticion;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRRETCANCTOTALTRNI contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
			        RetrocesionCancelacionAcuerdoServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de retrocesion "
					+ " de cancelacion de acuerdo.", e);
		}
		return respuesta;
	}
	
}
