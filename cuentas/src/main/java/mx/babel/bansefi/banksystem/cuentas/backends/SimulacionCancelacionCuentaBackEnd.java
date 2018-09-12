package mx.babel.bansefi.banksystem.cuentas.backends;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.beans.SimulacionCancelacionCuentaBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.simulacioncancelacioncuenta.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.simulacioncancelacioncuenta.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.simulacioncancelacioncuenta.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.webservices.simulacioncancelacioncuenta.SimulacionCancelacionServicio;
import mx.babel.bansefi.banksystem.cuentas.wrappers.SimulacionCancelacionCuentaWrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.babel.bansefi.banksystem.cuentas.beans.DesgloseApuntesBean;

/**
 * 
 * @author manuel.nieto
 * 
 */
@Component
public class SimulacionCancelacionCuentaBackEnd extends BackEndBean implements
		Serializable {

	private static final long serialVersionUID = -7497052856341844835L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	@Autowired
	SimulacionCancelacionCuentaWrapper simulacionCancelacionCuentaWrapper;

	private static final Logger LOGGER = LogManager
			.getLogger(SimulacionCancelacionCuentaBackEnd.class);

	/**
	 * Función principal que descencadena todo el llamado de la TRN/WebService
	 * 
	 * @param contadores
	 *            Bean generico para la consulta de Contadores de Centro y
	 *            Puesto
	 * @return ContadoresCentroBean
	 */
	public SimulacionCancelacionCuentaBean ejecutarTRN(CuentaBean cuentaBean,
			String tipoCobro){
		Ejecutar.TRSIMULACTRNI contexto = initPeticion(cuentaBean, tipoCobro);
		EjecutarResult respuesta = ejecutarWS(contexto);
		super.verificaRespuesta(respuesta);
		
		if (verificaResponseBansefi(respuesta)) {
			SimulacionCancelacionCuentaBean simulacionBean = null;
			//return respuestaDomicilioCompartido(respuesta.getResponseBansefi());
			simulacionBean=respuestaDomicilioCompartido(respuesta.getResponseBansefi());
			simulacionBean.setCodigoLinea(respuesta.getResponseBansefi().getTRSIMULACTRNO().getTRSIMULACEVTZ().getACACE().getCODLINEA());
			simulacionBean.setIdGRPPD(respuesta.getResponseBansefi().getTRSIMULACTRNO().getTRSIMULACEVTZ().getACACE().getIDGRPPD());
			
			List<DesgloseApuntesBean> listaDesglose = new ArrayList<>();
			simulacionBean.setDesgloseConceptos(listaDesglose);
			
			
		for(ResponseBansefi.TRSIMULACTRNO.TRSIMULACEVTZ.TRSIMULCANCV.ACVAFAPUNTESV desglose:respuesta.getResponseBansefi().getTRSIMULACTRNO().getTRSIMULACEVTZ().getTRSIMULCANCV().getACVAFAPUNTESV())
		{
			
			if(desglose.getCODNRBEEN().trim().length()>0)
			{
				DesgloseApuntesBean  apuntes= new DesgloseApuntesBean();
				apuntes.setCodigoCuenta(desglose.getCODCTA());
				apuntes.setInd1(desglose.getIND1());
				apuntes.setInd2(desglose.getIND2());
				apuntes.setInd3(desglose.getIND3());
				apuntes.setInd4(desglose.getIND4());
				apuntes.setInd5(desglose.getIND5());
				apuntes.setInd6(desglose.getIND6());
				apuntes.setInd7(desglose.getIND7());
				apuntes.setInd8(desglose.getIND8());
				apuntes.setInd9(desglose.getIND9());
				apuntes.setInd10(desglose.getIND10());
				apuntes.setCodigoOrigen(desglose.getCODORIGEN());
				apuntes.setIdPds(desglose.getIDPDS());
				apuntes.setImporte(desglose.getIMPAPNTE());
				apuntes.setSigno(desglose.getSGN());
				simulacionBean.getDesgloseConceptos().add(apuntes);
				
			}
		}
			
			
			return simulacionBean;
		}
		return null;
	}

	/**
	 * Funcion que recibe la respuesta de la TRN y Wrappea a un objeto propio
	 * del sistema
	 * 
	 * @param response
	 * @return ContadoresCentroBean
	 */
	private SimulacionCancelacionCuentaBean respuestaDomicilioCompartido(ResponseBansefi response){
		SimulacionCancelacionCuentaBean simulacionBean = null;
		if (verificaRespuestaCliente(response)) {
			simulacionBean = simulacionCancelacionCuentaWrapper
					.wrappRespuestaSimulacion(response.getTRSIMULACTRNO()
							.getTRSIMULACEVTZ().getTRSIMULCANCV());
		}

		return simulacionBean;
	}

	/**
	 * Función que inicializa los parametros de entrada que necesita el servicio
	 * web para ser ejecutado
	 * 
	 * @param contadoresCentroBean
	 * @return Ejecutar.ITROBTECONTTRNI
	 */
	private Ejecutar.TRSIMULACTRNI initPeticion(CuentaBean cuentaBean,
			String tipoCobro) {
		Ejecutar.TRSIMULACTRNI contexto = new Ejecutar.TRSIMULACTRNI();
		Ejecutar.TRSIMULACTRNI.STDTRNIPARMV contextoEntrada = new Ejecutar.TRSIMULACTRNI.STDTRNIPARMV();
		Ejecutar.TRSIMULACTRNI.TRSIMULACEVTY datosEntrada = new Ejecutar.TRSIMULACTRNI.TRSIMULACEVTY();

		initialize(contexto);
		initialize(datosEntrada);

		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_SIMUL_AC_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(getTerminal());

		Ejecutar.TRSIMULACTRNI.TRSIMULACEVTY.ACACP subEntrada = datosEntrada.getACACP();
		subEntrada.setCODNRBEEN(super.getEntidad());
		subEntrada.setNUMSECAC(cuentaBean.getNumeroCuenta());
		
		// OTRA CUENTA
		if (tipoCobro != null && tipoCobro.equals("null")) {
			datosEntrada.getACVTIPOCOBROV().setSTDCHAR02(" ");
		} else {
			datosEntrada.getACVTIPOCOBROV().setSTDCHAR02(tipoCobro);
		}

		IntegerToDateConverter itdc = new IntegerToDateConverter();
		datosEntrada.getFECHAV().setSTDFECHA(
				itdc.convertFrom(super.getFechaSistema()));

		datosEntrada.setACTNCD("CANCELACION");
		datosEntrada.getACCUADROIRREGV().setSTDSMALLINT(0);
		datosEntrada.getACVLIMCONCEDIDOV().setIMPLIMITE(BigDecimal.ZERO);
		contexto.setEVENTCD(1);
		
		contexto.setTRSIMULACEVTY(datosEntrada);
		contexto.setSTDTRNIPARMV(contextoEntrada);

		initialize(contexto);

		return contexto;

	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto
	 *            Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.TRSIMULACTRNI contexto) {
		EjecutarResult respuesta = null;
		try {
			respuesta = (EjecutarResult) this.servicioWebUtils.ejecutarWS(
					SimulacionCancelacionServicio.class, contexto);
		} catch (NoControlableException e) {
			LOGGER.error("error", e);
			throw new NoControlableException(
					"Error al invocar servicio web de consulta de "
							+ "domicilios compartidos.", e);
		}
		return respuesta;
	}

	/**
	 * Función que valida que la respuesta del servidor no este vacía.
	 * 
	 * @param respuesta
	 *            Objeto respuesta del servicio web
	 * @return <code>true</code> si la respuesta no esta vacía.
	 */
	private Boolean verificaResponseBansefi(EjecutarResult respuesta) {
		Boolean noNulo = false;
		if (respuesta != null && respuesta.getResponseBansefi() != null) {
			noNulo = true;
		}
		return noNulo;
	}

	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con los
	 * datos de la consulta.
	 * 
	 * @param response
	 *            Respuesta Bansefi con datos de la consulta
	 * @return <code>true</code> si la respuesta Bansefi contiene elemento no
	 *         nulos
	 */
	private Boolean verificaRespuestaCliente(ResponseBansefi response) {
		Boolean noNulo = false;
		if (response != null
				&& response.getTRSIMULACTRNO() != null
				&& response.getTRSIMULACTRNO().getTRSIMULACEVTZ()
						.getTRSIMULCANCV() != null) {
			noNulo = true;
		}
		return noNulo;
	}

}
