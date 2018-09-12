package mx.babel.bansefi.banksystem.base.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.enums.CuentaEnum;
import mx.babel.bansefi.banksystem.base.utils.EstadosCuentaEnumUtils;
import mx.babel.bansefi.banksystem.base.utils.ProductosSimplesWrapperUtils;
import mx.babel.bansefi.banksystem.base.webservices.consultacuenta.ConsultaCuentaServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultacuenta.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultacuenta.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultacuenta.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.webservices.consultacuenta.ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ;
import mx.babel.bansefi.banksystem.base.wrappers.ConsultaCuentaWrapper;
import mx.babel.bansefi.banksystem.base.wrappers.CuentaWrapper;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la consulta de cuentas.
 *
 * @author omar.marquez
 *
 */
@Component
public class ConsultaCuentaBackend extends BackEndBean {

	private static final long serialVersionUID = 3504496455027424869L;
	private static final String CTE_CERO = "0";
	private static final String CTE_ESPACIO = " ";

	@Autowired
	ConsultaCuentaWrapper consultaCuentaWrapper;
	
	@Autowired
	CuentaWrapper cuentaWrapper;

	@Autowired
	ProductosSimplesWrapperUtils productosSimplesWrapperUtils;

    @Autowired
    ServicioWebUtils servicioWebUtils;
    
    @Autowired
    EstadosCuentaEnumUtils estadosCuentaEnumUtils;
    
    /**
	 * Método que ejecuta la transacción de consulta de cuentas a partir de una cuenta.
	 *
	 * @param cuentaBean con valores iniciales
	 * @return cuentaBean con valores de atributos recuperados
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
    public void ejecutarTRN(CuentaBean cuentaBean){
    	if(cuentaBean != null && cuentaBean.getNumeroCuenta() != null){
	    	CuentaBean cuenta = this.ejecutarTRN(cuentaBean.getNumeroCuenta());
	    	if(cuenta != null){
	    		cuentaWrapper.wrappBean(cuentaBean, cuenta);
	    	}
    	}
    }

	/**
	 * Método que ejecuta la transacción de consulta de cuentas a partir de un numero de cuenta.
	 *
	 * @param numCuenta
	 * @return cuentaBean con valores de atributos recuperados
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	public CuentaBean ejecutarTRN(final Long numCuenta){
		final Ejecutar.ITRCONSULTAACUERDOTRN contexto = initPeticion(numCuenta);
		final EjecutarResult respuesta = ejecutarWS(contexto);
		try{
			super.verificaRespuesta(respuesta);
		}catch (final ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return null;
			}
		}
		return consultaCuenta(respuesta.getResponseBansefi());
	}

	/**
	 * Función que construye el bean de cuenta a devolver.
	 *
	 * @param response
	 * @return
	 */
	private CuentaBean consultaCuenta(final ResponseBansefi response){
		CuentaBean cuenta = null;		
		if(verificaRespuestaCliente(response)){//
			// Se recuperan y wrappean los datos de la cuenta
		    final TRCONSULTAACUERDOEVTZ trConsultaAcuerdoEvtZ = response.getOTRCONSULTAACUERDOTRN().getTRCONSULTAACUERDOEVTZ();
			cuenta = consultaCuentaWrapper.wrappConsultaCuenta(trConsultaAcuerdoEvtZ);
			if(null != trConsultaAcuerdoEvtZ.getTRCONSVALMSVKAEVTZ()
			   && null != trConsultaAcuerdoEvtZ.getTRCONSVALMSVKAEVTZ().getKACDAC1V()
	           && !trConsultaAcuerdoEvtZ.getTRCONSVALMSVKAEVTZ().getKACDAC1V().isEmpty()){
			    cuenta.setProductosSimples(productosSimplesWrapperUtils.wrappCondicionesDeConsultaCuenta(
			            trConsultaAcuerdoEvtZ.getTRCONSVALMSVKAEVTZ().getKACDAC1V()));
			}
			final String cuentaClabe = this.creaClabe(super.getEntidad(),cuenta.getPlazaBancaria(),String.valueOf(cuenta.getNumeroCuenta()),cuenta.getDigitoVerificador());
			cuenta.setCuentaClabe(cuentaClabe);
			
			//Adapta el estado a su enum correspondiente
			if(!StringUtils.isBlank(cuenta.getEstado())){
				cuenta.setEstadoEnum(estadosCuentaEnumUtils.getEstado(cuenta.getEstado()));
			}
			
			//Set TipoCuentaEnum
			if(!StringUtils.isBlank(cuenta.getCodLinea()) && !StringUtils.isBlank(cuenta.getIdGrupoProducto())){
				cuenta.setTipoCuentaEnum(CuentaEnum.getTipoCuenta(cuenta.getCodLinea(), cuenta.getIdGrupoProducto()));
			}
		}
		return cuenta;
	}

	public String creaClabe(final String entidad, final String plaza, String cuenta, final String digito){

	cuenta = StringUtils.leftPad(cuenta, 10, ConsultaCuentaBackend.CTE_CERO);

	final StringBuffer clabeBuffer = new StringBuffer().append(entidad.substring(1)).append(plaza.trim())
						.append(ConsultaCuentaBackend.CTE_ESPACIO)
						.append(ConsultaCuentaBackend.CTE_CERO)
						.append(cuenta).append(" ")
						.append(digito);

	return clabeBuffer.toString();
	}

	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 *
	 * @param numCuenta numero de cuenta a consultar
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITRCONSULTAACUERDOTRN initPeticion(final Long numCuenta){
		final Ejecutar.ITRCONSULTAACUERDOTRN contexto = new Ejecutar.ITRCONSULTAACUERDOTRN();
		final Ejecutar.ITRCONSULTAACUERDOTRN.STDTRNIPARMV contextoEntrada =
				new Ejecutar.ITRCONSULTAACUERDOTRN.STDTRNIPARMV();
		final Ejecutar.ITRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTY consultaAcuerdo =
				new Ejecutar.ITRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTY();
		final Ejecutar.ITRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTY.ACACP datosEntrada =
				new Ejecutar.ITRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTY.ACACP();
		final Ejecutar.ITRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTY.ACINDCONSUACV consultarDatosCuentaCompletos =
				new Ejecutar.ITRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTY.ACINDCONSUACV();

		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_CONSULTA_ACUERDO_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());

		datosEntrada.setNUMSECAC(numCuenta);
		datosEntrada.setCODNRBEEN(getEntidad());

		consultarDatosCuentaCompletos.setSTDSMALLINT(1);

		consultaAcuerdo.setACACP(datosEntrada);
		consultaAcuerdo.setACINDCONSUACV(consultarDatosCuentaCompletos);

		contexto.setTRCONSULTAACUERDOEVTY(consultaAcuerdo);
		contexto.setSTDTRNIPARMV(contextoEntrada);
		contexto.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);
		super.initialize(contexto);

		return contexto;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRCONSULTAACUERDOTRN contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaCuentaServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta de "
					+ "detalle de cuentas.", e);
		}
		return respuesta;
	}

	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con los datos
	 * de la consulta.
	 *
	 * @param response Respuesta Bansefi con datos de la consulta
	 * @return <code>true</code> si la respuesta Bansefi contiene elemento no nulos
	 */
	private Boolean verificaRespuestaCliente(final ResponseBansefi response){
		Boolean noNulo = false;
		if(response!= null && response.getOTRCONSULTAACUERDOTRN() != null &&
				response.getOTRCONSULTAACUERDOTRN().getTRCONSULTAACUERDOEVTZ() != null &&
						response.getOTRCONSULTAACUERDOTRN().getTRCONSULTAACUERDOEVTZ().getACACE() != null
						&& response.getOTRCONSULTAACUERDOTRN().getTRCONSULTAACUERDOEVTZ().getPERSONAACV() != null
						&& response.getOTRCONSULTAACUERDOTRN().getTRCONSULTAACUERDOEVTZ().getDESCRPDVV() != null){
			noNulo = true;
		}
		return noNulo;
	}

}