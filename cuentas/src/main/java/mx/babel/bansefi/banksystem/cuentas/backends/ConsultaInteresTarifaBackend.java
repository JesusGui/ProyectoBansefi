package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionInteresBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.beans.DatosCondicionTarifaBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultainterestarifa.ConsultaInteresTarifaServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultainterestarifa.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultainterestarifa.Ejecutar.ITRKPCNSINTSMPTRNI.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultainterestarifa.Ejecutar.ITRKPCNSINTSMPTRNI.TRKPCNSINTSMPEVTY.KPCODCLAVEV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultainterestarifa.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultainterestarifa.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaCondicionInteresWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class ConsultaInteresTarifaBackend extends BackEndBean{

	private static final long serialVersionUID = 475639838627890503L;

	@Autowired
    ConsultaCondicionInteresWrapper consultaCondicionInteresWrapper;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	public CondicionInteresBean ejecutarTRN(final DatosCondicionTarifaBean datosCondicionTarifa){
		final Ejecutar.ITRKPCNSINTSMPTRNI itrkpcnsintsmptrni = initPeticion(datosCondicionTarifa);

		final EjecutarResult respuesta = ejecutarWS(itrkpcnsintsmptrni);

        super.verificaRespuesta(respuesta);

		return getCondiciones(respuesta.getResponseBansefi());
	}

	/**
	 * Función encargada de obtener el cliente a partir de la respuesta del servicio web
	 *
	 * @param idInterno El id interno de la persona moral
	 * @param response El objeto de reultado enviado por el servicio web.
	 * @return
	 */
	private CondicionInteresBean getCondiciones(final ResponseBansefi response){
	    CondicionInteresBean condicion = null;
		if(verificaRespuesta(response)){
		    condicion = consultaCondicionInteresWrapper
		            .wrappInteresTarifa(response.getOTRKPCNSINTSMPTRNO().getTRKPCNSINTSMPEVTZ().getKPINTSMPLV());
		}
		return condicion;
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRKPCNSINTSMPTRNI initPeticion(final DatosCondicionTarifaBean datosCondicionTarifa){
		final Ejecutar.ITRKPCNSINTSMPTRNI itrkpcnsintsmptrni = new Ejecutar.ITRKPCNSINTSMPTRNI();

		super.initialize(itrkpcnsintsmptrni);
        final IntegerToDateConverter itdConverter = new IntegerToDateConverter();

		final KPCODCLAVEV kpcodclavev = itrkpcnsintsmptrni.getTRKPCNSINTSMPEVTY().getKPCODCLAVEV();
		kpcodclavev.setCODNRBEEN(this.getEntidad());

        kpcodclavev.setIDPDS(datosCondicionTarifa.getIdProductoSimple());

		kpcodclavev.setIDCCPS(datosCondicionTarifa.getIdCcps());

		kpcodclavev.setIDPARMCD(datosCondicionTarifa.getIdParmCd());

        kpcodclavev.setFECHAINICVAL(itdConverter.convertFrom(datosCondicionTarifa.getFechaInicioValidez()));

        kpcodclavev.setCODESTRCTPARMCD(ConstantesFuncionales.CATALOGO_TIPO_CONDICION.INTERES.getId());

        itrkpcnsintsmptrni.getTRKPCNSINTSMPEVTY().setNOMBCCPS("");

        final STDTRNIPARMV stdtrniparmv = itrkpcnsintsmptrni.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_KP_CNS_INT_SMP_TRN);
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());
		return itrkpcnsintsmptrni;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRKPCNSINTSMPTRNI contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
			        ConsultaInteresTarifaServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "de interes para tarifas.", e);
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
		if(response != null && response.getOTRKPCNSINTSMPTRNO() != null &&
				response.getOTRKPCNSINTSMPTRNO().getTRKPCNSINTSMPEVTZ() != null &&
				response.getOTRKPCNSINTSMPTRNO().getTRKPCNSINTSMPEVTZ().getKPINTSMPLV()!= null ){
			noNulo = true;
		}
		return noNulo;
	}

}
