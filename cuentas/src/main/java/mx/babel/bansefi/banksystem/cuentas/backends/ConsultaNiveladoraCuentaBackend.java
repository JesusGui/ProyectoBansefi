package mx.babel.bansefi.banksystem.cuentas.backends;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.base.utils.IntegerToStringHourConverter;
import mx.babel.bansefi.banksystem.cuentas.beans.NivelCuentaBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaniveladoracuenta.ConsultaNiveladoraCuentaServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaniveladoracuenta.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaniveladoracuenta.Ejecutar.ITRCONSUNIVELACTRNI.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaniveladoracuenta.Ejecutar.ITRCONSUNIVELACTRNI.TRCONSUNIVELACEVTY.ACECVACP;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaniveladoracuenta.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaniveladoracuenta.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaNiveladoraCuentaWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class ConsultaNiveladoraCuentaBackend extends BackEndBean{

	private static final long serialVersionUID = 6564921993348101257L;

	@Autowired
	ConsultaNiveladoraCuentaWrapper consultaNiveladoraCuentaWrapper;

    @Autowired
    ServicioWebUtils servicioWebUtils;

	public List<NivelCuentaBean> ejecutarTRN(final Long numCuenta, final Integer idInternaPe){
		final Ejecutar.ITRCONSUNIVELACTRNI request = initPeticion(numCuenta, idInternaPe);

		final EjecutarResult respuesta = ejecutarWS(request);


        super.verificaRespuesta(respuesta);

		return getResponse(respuesta.getResponseBansefi());
	}


	private List<NivelCuentaBean> getResponse(final ResponseBansefi response){
	    List<NivelCuentaBean> respuesta = null;
		if(verificaRespuesta(response)){
		    respuesta = consultaNiveladoraCuentaWrapper.wrappNivelCuentaPlazo(
		            response.getOTRCONSUNIVELACTRNO().getTRCONSUNIVELACEVTZ().getACNIVELACE());
		}
		return respuesta;
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 * @param ultimoTramo
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRCONSUNIVELACTRNI initPeticion(final long numCuenta, final int idInternoPe){
		final Ejecutar.ITRCONSUNIVELACTRNI peticion = new Ejecutar.ITRCONSUNIVELACTRNI();

		super.initialize(peticion);

		peticion.setSCROLLABLEOCCURS(50);
		final IntegerToDateConverter itdConverter = new IntegerToDateConverter();
        final IntegerToStringHourConverter itshConverter = new IntegerToStringHourConverter();
        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		final Calendar fechaOperacion = Calendar.getInstance();
        final int fechaInteger = itdConverter.convertFrom(fechaOperacion.getTime());
        final int horaInteger = itshConverter.convertFrom(sdf.format(fechaOperacion.getTime()));

		final ACECVACP acecvacp = peticion.getTRCONSUNIVELACEVTY().getACECVACP();
		acecvacp.setCODNRBEEN(this.getEntidad());
		acecvacp.setNUMSECAC(numCuenta);
        acecvacp.getTIPOECVSTDP().setFECHAOPRCN(fechaInteger);
        acecvacp.getTIPOECVSTDP().setHORAOPRCN(horaInteger);


        peticion.getTRCONSUNIVELACEVTY().setIDINTERNOPE(idInternoPe);
        peticion.getTRCONSUNIVELACEVTY().setSTDCHAR01("2");
        peticion.getTRCONSUNIVELACEVTY().getECVV().setFECHADESDE(fechaInteger);
        peticion.getTRCONSUNIVELACEVTY().getECVV().setFECHAHASTA(fechaInteger);


        final STDTRNIPARMV stdtrniparmv = peticion.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_CONSU_NIVEL_AC_TRN);

        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());
		return peticion;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRCONSUNIVELACTRNI contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
			        ConsultaNiveladoraCuentaServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "niveladora de cuenta.", e);
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
		if(response != null && response.getOTRCONSUNIVELACTRNO() != null &&
			response.getOTRCONSUNIVELACTRNO().getTRCONSUNIVELACEVTZ()!= null &&
			response.getOTRCONSUNIVELACTRNO().getTRCONSUNIVELACEVTZ().getACNIVELACE() != null &&
			!response.getOTRCONSUNIVELACTRNO().getTRCONSUNIVELACEVTZ().getACNIVELACE().isEmpty()){
			noNulo = true;
		}
		return noNulo;
	}

}
