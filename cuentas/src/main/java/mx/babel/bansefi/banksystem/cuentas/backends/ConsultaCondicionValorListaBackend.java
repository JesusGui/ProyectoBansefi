package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionValorListaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionvalorlista.ConsultaCondicionValorListaServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionvalorlista.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionvalorlista.Ejecutar.ITRCONSVALKAVLSTRNI.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionvalorlista.Ejecutar.ITRCONSVALKAVLSTRNI.TRCONSVALKAVLSEVTY.KACDACP;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionvalorlista.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicionvalorlista.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaCondicionValorListaWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class ConsultaCondicionValorListaBackend extends BackEndBean{

	private static final long serialVersionUID = 6564921993348101257L;

	@Autowired
    ConsultaCondicionValorListaWrapper consultaCondicionValorListaWrapper;

    @Autowired
    ServicioWebUtils servicioWebUtils;

	public CondicionValorListaBean ejecutarTRN(final long numCuenta, final String idPds, final String idParmCd){
		
		final Ejecutar.ITRCONSVALKAVLSTRNI trconsvalkavlstrni = initPeticion(numCuenta, idPds, idParmCd);

		final EjecutarResult respuesta = ejecutarWS(trconsvalkavlstrni);

		super.verificaRespuesta(respuesta);
		
		return getCondiciones(respuesta.getResponseBansefi());
	}


	private CondicionValorListaBean getCondiciones(final ResponseBansefi response){
		CondicionValorListaBean condicion = null;
        
		if(verificaRespuesta(response)){
		    condicion = consultaCondicionValorListaWrapper
		            .wrappCondicionValorLista(response.getOTRCONSVALKAVLSTRNO().getTRCONSVALKAVLSEVTZ());
		}
		return condicion;
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRCONSVALKAVLSTRNI initPeticion(final long numCuenta, final String idPds, final String idParmCd){
		final Ejecutar.ITRCONSVALKAVLSTRNI trconsvalkavlstrni = new Ejecutar.ITRCONSVALKAVLSTRNI();

		super.initialize(trconsvalkavlstrni);

		final KACDACP kacdacp = trconsvalkavlstrni.getTRCONSVALKAVLSEVTY().getKACDACP();
		kacdacp.setCODNRBEEN(this.getEntidad());

		kacdacp.setNUMSECAC(numCuenta);

		kacdacp.setIDPDS(idPds);

        kacdacp.setIDPARMCD(idParmCd);

        final STDTRNIPARMV stdtrniparmv = trconsvalkavlstrni.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_CONS_VAL_KA);
        stdtrniparmv.setNUMSEC(0);

        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());
		return trconsvalkavlstrni;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRCONSVALKAVLSTRNI contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaCondicionValorListaServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "de condicion tipo Valor-Lista.", e);
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
		if(response != null && response.getOTRCONSVALKAVLSTRNO() != null &&
				response.getOTRCONSVALKAVLSTRNO().getTRCONSVALKAVLSEVTZ()!= null &&
				response.getOTRCONSVALKAVLSTRNO().getTRCONSVALKAVLSEVTZ().getKACOMUNV() != null &&
				response.getOTRCONSVALKAVLSTRNO().getTRCONSVALKAVLSEVTZ().getKAESTRCTVLISTAV()!= null){
			noNulo = true;
		}
		return noNulo;
	}

}
