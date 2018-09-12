package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.Date;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionValorListaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionvalorlistaplazo.ConsultaCondicionValorListaPlazoServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionvalorlistaplazo.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionvalorlistaplazo.Ejecutar.ITRCONSVALKSVLSTRNI.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionvalorlistaplazo.Ejecutar.ITRCONSVALKSVLSTRNI.TRCONSVALKSVLSEVTY.KSCDSBP;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionvalorlistaplazo.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionvalorlistaplazo.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaCondicionValorListaWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class ConsultaCondicionValorListaPlazoBackend extends BackEndBean{

	private static final long serialVersionUID = 6564921993348101257L;

	@Autowired
    ConsultaCondicionValorListaWrapper consultaCondicionValorListaWrapper;

    @Autowired
    ServicioWebUtils servicioWebUtils;

	public CondicionValorListaBean ejecutarTRN(final long numCuenta, final int numSubAc, final String idPds, final CondicionValorListaBean condicion){

		final Ejecutar.ITRCONSVALKSVLSTRNI trconsvalksvlstrni = initPeticion(numCuenta, numSubAc,
		        idPds, condicion.getClave(), condicion.getFechaInicioValidez());

		final EjecutarResult respuesta = ejecutarWS(trconsvalksvlstrni);

		super.verificaRespuesta(respuesta);

		return getCondiciones(respuesta.getResponseBansefi());
	}


	private CondicionValorListaBean getCondiciones(final ResponseBansefi response){
		CondicionValorListaBean condicion = null;
		if(verificaRespuesta(response)){
		    condicion = consultaCondicionValorListaWrapper
		            .wrappCondicionValorListaPlazo(response.getOTRCONSVALKSVLSTRNO().getTRCONSVALKSVLSEVTZ());
		}
		return condicion;
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRCONSVALKSVLSTRNI initPeticion(final long numCuenta, final int numSubAc, final String idPds, final String idParmCd
	        , final Date fechaInicio){
		final Ejecutar.ITRCONSVALKSVLSTRNI trconsvalksvlstrni = new Ejecutar.ITRCONSVALKSVLSTRNI();

		super.initialize(trconsvalksvlstrni);

        final IntegerToDateConverter itdConverter = new IntegerToDateConverter();

		final KSCDSBP kscdsbp = trconsvalksvlstrni.getTRCONSVALKSVLSEVTY().getKSCDSBP();
		kscdsbp.setCODNRBEEN(this.getEntidad());

		kscdsbp.setNUMSECAC(numCuenta);

		kscdsbp.setNUMSUBAC(numSubAc);

		kscdsbp.setIDPDS(idPds);

		kscdsbp.setIDPARMCD(idParmCd);

        kscdsbp.setFECHAINICVAL(itdConverter.convertFrom(fechaInicio));

        final STDTRNIPARMV stdtrniparmv = trconsvalksvlstrni.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_CONS_VAL_KS);
        stdtrniparmv.setNUMSEC(0);

        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());
		return trconsvalksvlstrni;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRCONSVALKSVLSTRNI contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaCondicionValorListaPlazoServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "de condicion tipo Valor-Lista Plazo.", e);
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
		if(response != null && response.getOTRCONSVALKSVLSTRNO() != null &&
				response.getOTRCONSVALKSVLSTRNO().getTRCONSVALKSVLSEVTZ()!= null &&
				response.getOTRCONSVALKSVLSTRNO().getTRCONSVALKSVLSEVTZ().getKSCOMUNV() != null &&
				response.getOTRCONSVALKSVLSTRNO().getTRCONSVALKSVLSEVTZ().getKSESTRCTVLISTAV()!= null){
			noNulo = true;
		}
		return noNulo;
	}

}
