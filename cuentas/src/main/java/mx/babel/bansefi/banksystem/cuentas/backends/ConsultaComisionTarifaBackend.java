package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.ComisionTarifaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.beans.DatosCondicionTarifaBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacomisiontarifa.ConsultaComisionTarifaServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacomisiontarifa.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacomisiontarifa.Ejecutar.ITRKPCNSCMSSMPTRNI.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacomisiontarifa.Ejecutar.ITRKPCNSCMSSMPTRNI.TRKPCNSCMSSMPEVTY.KPCODCLAVEV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacomisiontarifa.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacomisiontarifa.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaCondicionComisionWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class ConsultaComisionTarifaBackend extends BackEndBean{

	private static final long serialVersionUID = 475639838627890503L;

	@Autowired
    ConsultaCondicionComisionWrapper consultaCondicionComisionWrapper;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	public ComisionTarifaBean ejecutarTRN(final DatosCondicionTarifaBean datosCondicionTarifa){
		final Ejecutar.ITRKPCNSCMSSMPTRNI itrkpcnscmssmptrni = initPeticion(datosCondicionTarifa);

		final EjecutarResult respuesta = ejecutarWS(itrkpcnscmssmptrni);

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
	private ComisionTarifaBean getCondiciones(final ResponseBansefi response){
	    ComisionTarifaBean condicion = null;
		if(verificaRespuesta(response)){
		    condicion = consultaCondicionComisionWrapper
		            .wrappComisionTarifa(response.getOTRKPCNSCMSSMPTRNO().getTRKPCNSCMSSMPEVTZ().getKPCMSNSMPLV());
		}
		return condicion;
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRKPCNSCMSSMPTRNI initPeticion(final DatosCondicionTarifaBean datosCondicionTarifa){
		final Ejecutar.ITRKPCNSCMSSMPTRNI itrkpcnscmssmptrni = new Ejecutar.ITRKPCNSCMSSMPTRNI();

		super.initialize(itrkpcnscmssmptrni);
        final IntegerToDateConverter itdConverter = new IntegerToDateConverter();

		final KPCODCLAVEV kpcodclavev = itrkpcnscmssmptrni.getTRKPCNSCMSSMPEVTY().getKPCODCLAVEV();
		kpcodclavev.setCODNRBEEN(this.getEntidad());

        kpcodclavev.setIDPDS(datosCondicionTarifa.getIdProductoSimple());

        kpcodclavev.setIDCCPS(datosCondicionTarifa.getIdCcps());

        kpcodclavev.setIDPARMCD(datosCondicionTarifa.getIdParmCd());

        kpcodclavev.setFECHAINICVAL(itdConverter.convertFrom(datosCondicionTarifa.getFechaInicioValidez()));

        kpcodclavev.setCODESTRCTPARMCD(ConstantesFuncionales.CATALOGO_TIPO_CONDICION.COMISION.getId());


        final STDTRNIPARMV stdtrniparmv = itrkpcnscmssmptrni.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_KP_CNS_CMS_SMP_TRN);
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());
		return itrkpcnscmssmptrni;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRKPCNSCMSSMPTRNI contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
			        ConsultaComisionTarifaServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "de comision para tarifas.", e);
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
		if(response != null && response.getOTRKPCNSCMSSMPTRNO() != null &&
				response.getOTRKPCNSCMSSMPTRNO().getTRKPCNSCMSSMPEVTZ() != null &&
				response.getOTRKPCNSCMSSMPTRNO().getTRKPCNSCMSSMPEVTZ().getKPCMSNSMPLV()!= null ){
			noNulo = true;
		}
		return noNulo;
	}

}
