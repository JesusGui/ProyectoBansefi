package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.Date;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionComisionBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicioncomisionplazo.ConsultaCondicionComisionPlazoServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicioncomisionplazo.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicioncomisionplazo.Ejecutar.ITRCONSVALKSCMSTRNI.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicioncomisionplazo.Ejecutar.ITRCONSVALKSCMSTRNI.TRCONSVALKSCMSEVTY.KSCDSBP;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicioncomisionplazo.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicioncomisionplazo.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaCondicionComisionWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class ConsultaCondicionComisionPlazoBackend extends BackEndBean{

	private static final long serialVersionUID = 4912153024712338834L;

	@Autowired
    ConsultaCondicionComisionWrapper consultaCondicionComisionWrapper;

    @Autowired
    ServicioWebUtils servicioWebUtils;

	public CondicionComisionBean ejecutarTRN(final long numCuenta, final int numSubAc,final String idPds, final CondicionComisionBean condicion){
		final Ejecutar.ITRCONSVALKSCMSTRNI trconsvalkacmstrni = initPeticion(numCuenta, numSubAc,
                idPds, condicion.getClave(), condicion.getFechaInicioValidez());

		final EjecutarResult respuesta = ejecutarWS(trconsvalkacmstrni);

		super.verificaRespuesta(respuesta);

		return getCondiciones(respuesta.getResponseBansefi());
	}

	private CondicionComisionBean getCondiciones(final ResponseBansefi response){
		CondicionComisionBean condicionComision = null;
		if(verificaRespuesta(response)){
		    condicionComision = consultaCondicionComisionWrapper
		            .wrappCondicionComisionPlazo(response.getOTRCONSVALKSCMSTRNO().getTRCONSVALKSCMSEVTZ());
		}
		return condicionComision;
	}

	private Ejecutar.ITRCONSVALKSCMSTRNI initPeticion(final long numCuenta, final int numSubAc, final String idPds,
            final String idParmCd, final Date fechaInicio){
		final Ejecutar.ITRCONSVALKSCMSTRNI trconsvalkscmstrni = new Ejecutar.ITRCONSVALKSCMSTRNI();

		super.initialize(trconsvalkscmstrni);
        final IntegerToDateConverter itdConverter = new IntegerToDateConverter();

		final KSCDSBP kscdsbp = trconsvalkscmstrni.getTRCONSVALKSCMSEVTY().getKSCDSBP();
		kscdsbp.setCODNRBEEN(this.getEntidad());

		kscdsbp.setNUMSECAC(numCuenta);
		kscdsbp.setNUMSUBAC(numSubAc);
		kscdsbp.setIDPDS(idPds);
		kscdsbp.setIDPARMCD(idParmCd);

        kscdsbp.setFECHAINICVAL(itdConverter.convertFrom(fechaInicio));

        final STDTRNIPARMV stdtrniparmv = trconsvalkscmstrni.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_CONS_VAL_KS);
        stdtrniparmv.setNUMSEC(0);

        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());
		return trconsvalkscmstrni;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRCONSVALKSCMSTRNI contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaCondicionComisionPlazoServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "de condicion tipo Comision plazo.", e);
		}
		return respuesta;
	}

	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con los datos
	 *
	 * @param response Respuesta Bansefi con datos
	 * @return <code>true</code> si la respuesta Bansefi contiene los datos
	 */
	private Boolean verificaRespuesta(final ResponseBansefi response){
		Boolean noNulo = false;
		if(response != null && response.getOTRCONSVALKSCMSTRNO() != null &&
				response.getOTRCONSVALKSCMSTRNO().getTRCONSVALKSCMSEVTZ() != null &&
				response.getOTRCONSVALKSCMSTRNO().getTRCONSVALKSCMSEVTZ().getKSCOMUNV() != null &&
				response.getOTRCONSVALKSCMSTRNO().getTRCONSVALKSCMSEVTZ().getKSESTRCTCMSN1V() != null&&
                (response.getOTRCONSVALKSCMSTRNO().getTRCONSVALKSCMSEVTZ().getKSCMSNFIJV() != null||
                response.getOTRCONSVALKSCMSTRNO().getTRCONSVALKSCMSEVTZ().getKSCMSNPRCTLV() != null)){
			noNulo = true;
		}
		return noNulo;
	}

}
