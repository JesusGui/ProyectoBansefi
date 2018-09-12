package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionRangoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.beans.DatosCondicionTarifaBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultarangotarifa.ConsultaRangoTarifaServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultarangotarifa.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultarangotarifa.Ejecutar.ITRKPCNSRNGSMPTRNI.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultarangotarifa.Ejecutar.ITRKPCNSRNGSMPTRNI.TRKPCNSRNGSMPEVTY.KPCODCLAVEV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultarangotarifa.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaCondicionRangoWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class ConsultaRangoTarifaBackend extends BackEndBean{

	private static final long serialVersionUID = 475639838627890503L;

	@Autowired
    ConsultaCondicionRangoWrapper consultaCondicionRangoWrapper;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	public CondicionRangoBean ejecutarTRN(final DatosCondicionTarifaBean datosCondicionTarifa){
		final Ejecutar.ITRKPCNSRNGSMPTRNI itrkpcnsrngsmptrni = initPeticion(datosCondicionTarifa);

		final EjecutarResult respuesta = ejecutarWS(itrkpcnsrngsmptrni);

		super.verificaRespuesta(respuesta);

		return consultaCondicionRangoWrapper
	            .wrappRangoTarifa(respuesta.getResponseBansefi().getOTRKPCNSRNGSMPTRNO().getTRKPCNSRNGSMPEVTZ().getKPRNGSMPLV());
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRKPCNSRNGSMPTRNI initPeticion(final DatosCondicionTarifaBean datosCondicionTarifa){
		final Ejecutar.ITRKPCNSRNGSMPTRNI trconsvalkarngtrni = new Ejecutar.ITRKPCNSRNGSMPTRNI();

		super.initialize(trconsvalkarngtrni);
        final IntegerToDateConverter itdConverter = new IntegerToDateConverter();

		final KPCODCLAVEV kpcodclavev = trconsvalkarngtrni.getTRKPCNSRNGSMPEVTY().getKPCODCLAVEV();
		kpcodclavev.setCODNRBEEN(this.getEntidad());

		kpcodclavev.setIDPDS(datosCondicionTarifa.getIdProductoSimple());

		kpcodclavev.setIDCCPS(datosCondicionTarifa.getIdCcps());

		kpcodclavev.setIDPARMCD(datosCondicionTarifa.getIdParmCd());


		kpcodclavev.setFECHAINICVAL(itdConverter.convertFrom(datosCondicionTarifa.getFechaInicioValidez()));

        kpcodclavev.setCODESTRCTPARMCD(ConstantesFuncionales.CATALOGO_TIPO_CONDICION.RANGO.getId());


        final STDTRNIPARMV stdtrniparmv = trconsvalkarngtrni.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_KP_CNS_RNG_SMP_TRN);
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());
		return trconsvalkarngtrni;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRKPCNSRNGSMPTRNI contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
			        ConsultaRangoTarifaServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "de rango para tarifas.", e);
		}
		return respuesta;
	}

}
