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
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramo.ConsultaDetalleTramoServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramo.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramo.Ejecutar.ITRCONSVALMSVKFTRNI.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramo.Ejecutar.ITRCONSVALMSVKFTRNI.TRCONSVALMSVKFEVTY.KFRESULTFILAP;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramo.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramo.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaDetalleTramoWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class ConsultaDetalleTramoBackend extends BackEndBean{

	private static final long serialVersionUID = 6564921993348101257L;

	@Autowired
	ConsultaDetalleTramoWrapper consultaDetalleTramoWrapper;

    @Autowired
    ServicioWebUtils servicioWebUtils;

	public CondicionTramoBean ejecutarTRN(final Long numCuenta, final String idPds, final String idParmcd,
	        final String idTrameado, final Date fechaInicioValidez, final Integer ultimoTramo){
		
		final Ejecutar.ITRCONSVALMSVKFTRNI request = initPeticion(numCuenta, idPds, idParmcd,
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
		    respuesta.setMasDatos(response.getOTRCONSVALMSVKFTRNO().getMOREDATAIN() == 1);
		    final List<SubTramoBean> subTramoList = consultaDetalleTramoWrapper.wrappDetalleTramo(
		            response.getOTRCONSVALMSVKFTRNO().getTRCONSVALMSVKFEVTZ().getKFRESULTFILA1V(),
		            response.getOTRCONSVALMSVKFTRNO().getTRCONSVALMSVKFEVTZ().getTDTRAMO1V());
		    final List<CabeceraTramoBean> cabeceraList = consultaDetalleTramoWrapper.wrappCabecerasTramo(
                    response.getOTRCONSVALMSVKFTRNO().getTRCONSVALMSVKFEVTZ().getTDTDRLCOLV().getTDTDRLCOLCDE());
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
	private Ejecutar.ITRCONSVALMSVKFTRNI initPeticion(final long numCuenta, final String idPds, final String idParmcd,
            final String idTrameado, final Date fechaInicioValidez, final Integer ultimoTramo){
		final Ejecutar.ITRCONSVALMSVKFTRNI peticion = new Ejecutar.ITRCONSVALMSVKFTRNI();

		super.initialize(peticion);
		final IntegerToDateConverter itdConverter = new IntegerToDateConverter();

		peticion.setSCROLLABLEOCCURS(50);

		final KFRESULTFILAP kfresultfilap = peticion.getTRCONSVALMSVKFEVTY().getKFRESULTFILAP();
		kfresultfilap.setCODNRBEEN(this.getEntidad());
		kfresultfilap.setNUMSECAC(numCuenta);
        kfresultfilap.setIDPDS(idPds);
        kfresultfilap.setIDPARMCD(idParmcd);
        kfresultfilap.setIDTRAMEADO(idTrameado);
        kfresultfilap.setFECHAINICVAL(itdConverter.convertFrom(fechaInicioValidez));

        if(null!=ultimoTramo){
            peticion.getTRCONSVALMSVKFEVTY().getKFREPOSV().setNUMTRAMO(ultimoTramo);
        }

        final STDTRNIPARMV stdtrniparmv = peticion.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_CONS_VAL_MSV_KF_TRN);

        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());
		return peticion;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRCONSVALMSVKFTRNI contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaDetalleTramoServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "de detalle de tramo.", e);
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
		if(response != null && response.getOTRCONSVALMSVKFTRNO() != null &&
			response.getOTRCONSVALMSVKFTRNO().getTRCONSVALMSVKFEVTZ()!= null &&
			response.getOTRCONSVALMSVKFTRNO().getTRCONSVALMSVKFEVTZ().getKFRESULTFILA1V() != null &&
			!response.getOTRCONSVALMSVKFTRNO().getTRCONSVALMSVKFEVTZ().getKFRESULTFILA1V().isEmpty() &&
            response.getOTRCONSVALMSVKFTRNO().getTRCONSVALMSVKFEVTZ().getTDTRAMO1V() != null &&
            !response.getOTRCONSVALMSVKFTRNO().getTRCONSVALMSVKFEVTZ().getTDTRAMO1V().isEmpty() &&
            response.getOTRCONSVALMSVKFTRNO().getTRCONSVALMSVKFEVTZ().getTDTDRLCOLV() != null &&
            response.getOTRCONSVALMSVKFTRNO().getTRCONSVALMSVKFEVTZ().getTDTDRLCOLV().getTDTDRLCOLCDE() != null &&
            !response.getOTRCONSVALMSVKFTRNO().getTRCONSVALMSVKFEVTZ().getTDTDRLCOLV().getTDTDRLCOLCDE().isEmpty()){
			noNulo = true;
		}
		return noNulo;
	}

}
