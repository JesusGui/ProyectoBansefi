package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionComisionBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicioncomision.ConsultaCondicionComisionServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicioncomision.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicioncomision.Ejecutar.ITRCONSVALKACMSTRNI.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicioncomision.Ejecutar.ITRCONSVALKACMSTRNI.TRCONSVALKACMSEVTY.KACDACP;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicioncomision.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacondicioncomision.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaCondicionComisionWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class ConsultaCondicionComisionBackend extends BackEndBean{

	private static final long serialVersionUID = 4912153024712338834L;

	@Autowired
    ConsultaCondicionComisionWrapper consultaCondicionComisionWrapper;

    @Autowired
    ServicioWebUtils servicioWebUtils;

	public CondicionComisionBean ejecutarTRN(final long numCuenta, final String idPds, final String idParmCd){
		
		final Ejecutar.ITRCONSVALKACMSTRNI trconsvalkacmstrni = initPeticion(numCuenta, idPds, idParmCd);

		final EjecutarResult respuesta = ejecutarWS(trconsvalkacmstrni);
		
		super.verificaRespuesta(respuesta);
				
		return getCondiciones(respuesta.getResponseBansefi());
	}

	private CondicionComisionBean getCondiciones(final ResponseBansefi response){
		CondicionComisionBean condicionComision = null;        
		if(verificaRespuesta(response)){
		    condicionComision = consultaCondicionComisionWrapper
		            .wrappCondicionComision(response.getOTRCONSVALKACMSTRNO().getTRCONSVALKACMSEVTZ());
		}
		return condicionComision;
	}

	private Ejecutar.ITRCONSVALKACMSTRNI initPeticion(final long numCuenta, final String idPds, final String idParmCd){
		final Ejecutar.ITRCONSVALKACMSTRNI trconsvalkacmstrni = new Ejecutar.ITRCONSVALKACMSTRNI();

		super.initialize(trconsvalkacmstrni);

		final KACDACP kacdacp = trconsvalkacmstrni.getTRCONSVALKACMSEVTY().getKACDACP();
		kacdacp.setCODNRBEEN(this.getEntidad());

		kacdacp.setNUMSECAC(numCuenta);
		kacdacp.setIDPDS(idPds);
        kacdacp.setIDPARMCD(idParmCd);

        final STDTRNIPARMV stdtrniparmv = trconsvalkacmstrni.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_CONS_VAL_KA);
        stdtrniparmv.setNUMSEC(0);

        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());
		return trconsvalkacmstrni;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRCONSVALKACMSTRNI contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaCondicionComisionServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "de condicion tipo Comision.", e);
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
		if(response != null && response.getOTRCONSVALKACMSTRNO() != null &&
				response.getOTRCONSVALKACMSTRNO().getTRCONSVALKACMSEVTZ() != null &&
				response.getOTRCONSVALKACMSTRNO().getTRCONSVALKACMSEVTZ().getKACOMUNV() != null &&
				response.getOTRCONSVALKACMSTRNO().getTRCONSVALKACMSEVTZ().getKAESTRCTCMSN1V() != null&&
                (response.getOTRCONSVALKACMSTRNO().getTRCONSVALKACMSEVTZ().getKACMSNFIJV() != null||
                response.getOTRCONSVALKACMSTRNO().getTRCONSVALKACMSEVTZ().getKACMSNPRCTLV() != null)){
			noNulo = true;
		}
		return noNulo;
	}

}
