package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.Date;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionInteresBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionkfinteres.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionkfinteres.Ejecutar.ITRMODIFICARKFINTTRN.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionkfinteres.Ejecutar.ITRMODIFICARKFINTTRN.TRMODIFICARKFINTEVTY;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionkfinteres.Ejecutar.ITRMODIFICARKFINTTRN.TRMODIFICARKFINTEVTY.KFRESULTFILAE;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionkfinteres.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionkfinteres.ModificaCondicionKFInteresServicio;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ModificaCondicionKFInteresWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModificaCondicionKFInteresBackend extends BackEndBean{

	private static final long serialVersionUID = 3978861998027239356L;

	@Autowired
    ModificaCondicionKFInteresWrapper modificaCondicionKFInteresWrapper;

    @Autowired
    ServicioWebUtils servicioWebUtils;

	public void ejecutarTRN(final Long numCuenta,final String idPds, final CondicionInteresBean condicionBean){

		final Ejecutar.ITRMODIFICARKFINTTRN trmodificarkfinttrni = initPeticion(condicionBean);

		final EjecutarResult respuesta = ejecutarWS(trmodificarkfinttrni);

		super.verificaRespuesta(respuesta);
	}



	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRMODIFICARKFINTTRN initPeticion(final CondicionInteresBean condicionBean){
		final Ejecutar.ITRMODIFICARKFINTTRN trmodificarkfinttrni = new Ejecutar.ITRMODIFICARKFINTTRN();

        condicionBean.setFechaEstadoActual(new Date());
	    final TRMODIFICARKFINTEVTY trmodificarkfintevty = modificaCondicionKFInteresWrapper.wrappCondicionInteres(condicionBean);
    	trmodificarkfinttrni.setTRMODIFICARKFINTEVTY(trmodificarkfintevty);
		super.initialize(trmodificarkfinttrni);

		final KFRESULTFILAE kfresultfilae = trmodificarkfinttrni.getTRMODIFICARKFINTEVTY().getKFRESULTFILAE();
		kfresultfilae.setCODNRBEEN(this.getEntidad());
		//TODO sustituir por el numero de acuerdo
		kfresultfilae.setNUMSECAC(69210532l);
        //TODO sustituir por datos del acuerdo
		kfresultfilae.setIDPDS("003");
        //TODO sustituir por datos del acuerdo
		kfresultfilae.setIDPARMCD("A55");

        final STDTRNIPARMV stdtrniparmv = trmodificarkfinttrni.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_MODIFICAR_KA);
        stdtrniparmv.setNUMSEC(0);
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());

		return trmodificarkfinttrni;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRMODIFICARKFINTTRN contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ModificaCondicionKFInteresServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de modifica "
					+ " de condicion tipo Interes.", e);
		}
		return respuesta;
	}
	
}
