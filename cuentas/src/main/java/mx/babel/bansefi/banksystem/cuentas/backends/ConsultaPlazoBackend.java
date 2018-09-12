package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.DepositoIPFBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaplazo.ConsultaPlazoServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaplazo.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaplazo.Ejecutar.ITRCONSULTAPLAZOTRNI.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaplazo.Ejecutar.ITRCONSULTAPLAZOTRNI.TRCONSULTAPLAZOEVTY;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaplazo.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaplazo.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaPlazoWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class ConsultaPlazoBackend extends BackEndBean{

	private static final long serialVersionUID = 6564921993348101257L;

    @Autowired
    ServicioWebUtils servicioWebUtils;

    @Autowired
    ConsultaPlazoWrapper consultaPlazoWrapper;

	public List<DepositoIPFBean> ejecutarTRN(final Long numCuenta, final String estado){
	    List<DepositoIPFBean> response = new ArrayList<>();
		final Ejecutar.ITRCONSULTAPLAZOTRNI request = initPeticion(numCuenta, estado);

		final EjecutarResult respuesta = ejecutarWS(request);
		
		try{
            super.verificaRespuesta(respuesta);
        }catch (final ControlableException ce){
            if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
                throw ce;
            }else{
                return response;
            }
        }
		
		return getResponse(respuesta.getResponseBansefi());
	}


	private List<DepositoIPFBean> getResponse(final ResponseBansefi response)
			throws NoControlableException, ControlableException{
	    List<DepositoIPFBean> respuesta = new ArrayList<>();
		
		if(verificaRespuesta(response)){
		    respuesta = consultaPlazoWrapper.wrappPlazo( response.getOTRCONSULTAPLAZOTRNO().getTRCONSULTAPLAZOEVTZ().getTRCONSULTAPLAZOEVTV());
		}
		return respuesta;
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRCONSULTAPLAZOTRNI initPeticion(final Long numCuenta, final String estado){
		final Ejecutar.ITRCONSULTAPLAZOTRNI peticion = new Ejecutar.ITRCONSULTAPLAZOTRNI();

		super.initialize(peticion);
		peticion.setSCROLLABLEOCCURS(50);

		final TRCONSULTAPLAZOEVTY trconsultaplazoecvty = peticion.getTRCONSULTAPLAZOEVTY();
		trconsultaplazoecvty.getIPIMPSCNP().setCODNRBEEN(this.getEntidad());
		trconsultaplazoecvty.getIPIMPSCNP().setNUMSECAC(numCuenta);
		//TODO harcodeado
		trconsultaplazoecvty.getIPPRIMERACCESOV().setSTDCHAR01("S");

		trconsultaplazoecvty.getIPESTADOSPOSIBLESV().setSTDCHAR15(estado);

        final STDTRNIPARMV stdtrniparmv = peticion.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_CONSULTA_PLAZO_TRN);

        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());
		return peticion;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRCONSULTAPLAZOTRNI contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
			        ConsultaPlazoServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "de plazo.", e);
		}
		return respuesta;
	}

	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con los datos
	 * del alta de realción.
	 *
	 * @param response Respuesta Bansefi con datos del alta
	 * @return <code>true</code> si la respuesta Bansefi contiene los datos de alta
	 */
	private Boolean verificaRespuesta(final ResponseBansefi response){
		Boolean noNulo = false;
		if(response != null && response.getOTRCONSULTAPLAZOTRNO() != null &&
			response.getOTRCONSULTAPLAZOTRNO().getTRCONSULTAPLAZOEVTZ()!= null &&
			response.getOTRCONSULTAPLAZOTRNO().getTRCONSULTAPLAZOEVTZ().getTRCONSULTAPLAZOEVTV() != null &&
		    !response.getOTRCONSULTAPLAZOTRNO().getTRCONSULTAPLAZOEVTZ().getTRCONSULTAPLAZOEVTV().isEmpty()){
			noNulo = true;
		}
		return noNulo;
	}

}
