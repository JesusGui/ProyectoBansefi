package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.Date;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionTramoBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.tramos.CabeceraTramoBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.tramos.SubTramoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramoplazo.ConsultaDetalleTramoPlazoServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramoplazo.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramoplazo.Ejecutar.ITRCONSVALMSVKLTRNI.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramoplazo.Ejecutar.ITRCONSVALMSVKLTRNI.TRCONSVALMSVKLEVTY.KLRESULTFILAP;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramoplazo.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramoplazo.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaDetalleTramoPlazoWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class ConsultaDetalleTramoPlazoBackend extends BackEndBean{

	private static final long serialVersionUID = 6564921993348101257L;

	@Autowired
	ConsultaDetalleTramoPlazoWrapper consultaDetalleTramoPlazoWrapper;

    @Autowired
    ServicioWebUtils servicioWebUtils;

	public CondicionTramoBean ejecutarTRN(final Long numCuenta, final Integer numSubAc,final String idPds, final String idParmcd,
	        final String idTrameado, final Date fechaInicioValidez, final Integer ultimoTramo){
		final Ejecutar.ITRCONSVALMSVKLTRNI request = initPeticion(numCuenta, numSubAc, idPds, idParmcd,
		        idTrameado, fechaInicioValidez, ultimoTramo);

		final EjecutarResult respuesta = ejecutarWS(request);
		
		try{
            super.verificaRespuesta(respuesta);
        }catch (final ControlableException ce){
            if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
                throw ce;
            }else{
                return null;
            }
        }
		
		return getResponse(respuesta.getResponseBansefi());
	}


	private CondicionTramoBean getResponse(final ResponseBansefi response){
	    CondicionTramoBean respuesta = null;
		if(verificaRespuesta(response)){
		    respuesta = new CondicionTramoBean();
		    respuesta.setMasDatos(response.getOTRCONSVALMSVKLTRNO().getMOREDATAIN() == 1);
		    final List<SubTramoBean> subTramoList = consultaDetalleTramoPlazoWrapper.wrappDetalleTramoPlazo(
		            response.getOTRCONSVALMSVKLTRNO().getTRCONSVALMSVKLEVTZ().getKLRESULTFILA1V(),
		            response.getOTRCONSVALMSVKLTRNO().getTRCONSVALMSVKLEVTZ().getTDTRAMO1V());
		    final List<CabeceraTramoBean> cabeceraList = consultaDetalleTramoPlazoWrapper.wrappCabecerasTramoPlazo(
                    response.getOTRCONSVALMSVKLTRNO().getTRCONSVALMSVKLEVTZ().getTDTDRLCOLV().getTDTDRLCOLCDE());
		    respuesta.setSubtramoList(subTramoList);
            respuesta.setCabeceraList(cabeceraList);
		}
		return respuesta;
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 * @param ultimoTramo
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRCONSVALMSVKLTRNI initPeticion(final long numCuenta, final int numSubAc, final String idPds, final String idParmcd,
            final String idTrameado, final Date fechaInicioValidez, final Integer ultimoTramo){
		final Ejecutar.ITRCONSVALMSVKLTRNI peticion = new Ejecutar.ITRCONSVALMSVKLTRNI();

		super.initialize(peticion);
		final IntegerToDateConverter itdConverter = new IntegerToDateConverter();

		peticion.setSCROLLABLEOCCURS(50);

		final KLRESULTFILAP klresultfilap = peticion.getTRCONSVALMSVKLEVTY().getKLRESULTFILAP();
		klresultfilap.setCODNRBEEN(this.getEntidad());
		klresultfilap.setNUMSECAC(numCuenta);
        klresultfilap.setNUMSUBAC(numSubAc);
		klresultfilap.setIDPDS(idPds);
		klresultfilap.setIDPARMCD(idParmcd);
        klresultfilap.setIDTRAMEADO(idTrameado);
        klresultfilap.setFECHAINICVAL(itdConverter.convertFrom(fechaInicioValidez));

        if(null!=ultimoTramo){
            peticion.getTRCONSVALMSVKLEVTY().getKLREPOSV().setNUMTRAMO(ultimoTramo);
        }

        final STDTRNIPARMV stdtrniparmv = peticion.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_CONS_VAL_MSV_KL_TRN);

        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());
		return peticion;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRCONSVALMSVKLTRNI contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaDetalleTramoPlazoServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "de detalle de tramo de plazo.", e);
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
		if(response != null && response.getOTRCONSVALMSVKLTRNO() != null &&
			response.getOTRCONSVALMSVKLTRNO().getTRCONSVALMSVKLEVTZ()!= null &&
			response.getOTRCONSVALMSVKLTRNO().getTRCONSVALMSVKLEVTZ().getKLRESULTFILA1V() != null &&
			!response.getOTRCONSVALMSVKLTRNO().getTRCONSVALMSVKLEVTZ().getKLRESULTFILA1V().isEmpty() &&
            response.getOTRCONSVALMSVKLTRNO().getTRCONSVALMSVKLEVTZ().getTDTRAMO1V() != null &&
            !response.getOTRCONSVALMSVKLTRNO().getTRCONSVALMSVKLEVTZ().getTDTRAMO1V().isEmpty() &&
            response.getOTRCONSVALMSVKLTRNO().getTRCONSVALMSVKLEVTZ().getTDTDRLCOLV() != null &&
            response.getOTRCONSVALMSVKLTRNO().getTRCONSVALMSVKLEVTZ().getTDTDRLCOLV().getTDTDRLCOLCDE() != null &&
            !response.getOTRCONSVALMSVKLTRNO().getTRCONSVALMSVKLEVTZ().getTDTDRLCOLV().getTDTDRLCOLCDE().isEmpty()){
			noNulo = true;
		}
		return noNulo;
	}

}
