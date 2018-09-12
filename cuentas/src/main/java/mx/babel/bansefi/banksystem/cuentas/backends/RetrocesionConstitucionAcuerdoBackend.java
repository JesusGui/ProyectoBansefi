package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.webservices.retrocesionconstitucionacuerdo.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.retrocesionconstitucionacuerdo.Ejecutar.ITRRETCONSTTOTALTRNI.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.retrocesionconstitucionacuerdo.Ejecutar.ITRRETCONSTTOTALTRNI.TRRETCONSTTOTALEVTY.ACACP;
import mx.babel.bansefi.banksystem.cuentas.webservices.retrocesionconstitucionacuerdo.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.retrocesionconstitucionacuerdo.RetrocesionConstitucionAcuerdoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class RetrocesionConstitucionAcuerdoBackend extends BackEndBean{

	private static final long serialVersionUID = 6564921993348101257L;

    @Autowired
    ServicioWebUtils servicioWebUtils;

	public void ejecutarTRN(CuentaBean cuentaBean) throws ControlableException, NoControlableException{
		final Ejecutar.ITRRETCONSTTOTALTRNI request = initPeticion(cuentaBean);

		final EjecutarResult respuesta = ejecutarWS(request);
		
		super.verificaRespuesta(respuesta);
		
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRRETCONSTTOTALTRNI initPeticion(CuentaBean cuentaBean){
		final Ejecutar.ITRRETCONSTTOTALTRNI peticion = new Ejecutar.ITRRETCONSTTOTALTRNI();

		super.initialize(peticion);


		final ACACP acacp = peticion.getTRRETCONSTTOTALEVTY().getACACP();
		acacp.setCODNRBEEN(this.getEntidad());
		acacp.setNUMSECAC(cuentaBean.getNumeroCuenta());
//		acacp.setCODCENTUO(cuentaBean.getCentro());

        final STDTRNIPARMV stdtrniparmv = peticion.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_RET_CONST_TOTAL_TRN);
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());

		return peticion;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRRETCONSTTOTALTRNI contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
			        RetrocesionConstitucionAcuerdoServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de retrocesion "
					+ " de constitucion de acuerdo.", e);
		}
		return respuesta;
	}
	
}
