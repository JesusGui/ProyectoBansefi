package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.cuentas.webservices.renegociacionacuerdo.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.renegociacionacuerdo.Ejecutar.ITRRENEGOCIARACTRNI.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.renegociacionacuerdo.Ejecutar.ITRRENEGOCIARACTRNI.TRRENEGOCIARACEVTY.ACACP;
import mx.babel.bansefi.banksystem.cuentas.webservices.renegociacionacuerdo.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.renegociacionacuerdo.RenegociacionAcuerdoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class RenegociacionAcuerdoBackend extends BackEndBean{

	private static final long serialVersionUID = 6564921993348101257L;

    @Autowired
    ServicioWebUtils servicioWebUtils;

	public void ejecutarTRN(final long numAcuerdo) throws ControlableException, NoControlableException{
		final Ejecutar.ITRRENEGOCIARACTRNI request = initPeticion(numAcuerdo);

		final EjecutarResult respuesta = ejecutarWS(request);

		super.verificaRespuesta(respuesta);
	}



	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRRENEGOCIARACTRNI initPeticion(final Long numAcuerdo){
		final Ejecutar.ITRRENEGOCIARACTRNI peticion = new Ejecutar.ITRRENEGOCIARACTRNI();

		super.initialize(peticion);


		final ACACP acacp = peticion.getTRRENEGOCIARACEVTY().getACACP();
		acacp.setCODNRBEEN(this.getEntidad());
		acacp.setNUMSECAC(numAcuerdo);
		//TODO constante
		 peticion.getTRRENEGOCIARACEVTY().getACINDNODENEGADAV().setSTDSMALLINT(ConstantesFuncionales.CTE_ONE);

        final STDTRNIPARMV stdtrniparmv = peticion.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_RENEGOCIAR_AC_TRN);
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());

		return peticion;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRRENEGOCIARACTRNI contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
			        RenegociacionAcuerdoServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de renegociacion "
					+ " de acuerdo.", e);
		}
		return respuesta;
	}

}
