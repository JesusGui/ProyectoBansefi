package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.beans.ConversionCuentaBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.conversioncuenta.ConvertirCuentaService;
import mx.babel.bansefi.banksystem.cuentas.webservices.conversioncuenta.Ejecutar.ITRCNVTCTAVALENCIATRN;
import mx.babel.bansefi.banksystem.cuentas.webservices.conversioncuenta.Ejecutar.ITRCNVTCTAVALENCIATRN.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.conversioncuenta.Ejecutar.ITRCNVTCTAVALENCIATRN.TRCNVTCTAVALENCIAEVT;
import mx.babel.bansefi.banksystem.cuentas.webservices.conversioncuenta.Ejecutar.ITRCNVTCTAVALENCIATRN.TRCNVTCTAVALENCIAEVT.CANTCNUE;
import mx.babel.bansefi.banksystem.cuentas.webservices.conversioncuenta.Ejecutar.ITRCNVTCTAVALENCIATRN.TRCNVTCTAVALENCIAEVT.CXSCIMIGRACTAA1;
import mx.babel.bansefi.banksystem.cuentas.webservices.conversioncuenta.Ejecutar.ITRCNVTCTAVALENCIATRN.TRCNVTCTAVALENCIAEVT.CXSCIMIGRACTAP;
import mx.babel.bansefi.banksystem.cuentas.webservices.conversioncuenta.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.conversioncuenta.ResponseBansefi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * BackEnd para la consulta de conversion cuenta
 * 
 * @author Natalio Iván Mendoza Sánchez &lt;nmendoza@uanlmexico.mx&gt;
 * @category BackEnd
 */
@Component
public class ConversionCuentaBackEnd extends BackEndBean {

	private static final long serialVersionUID = 1L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Método para consultar los campos de un centro
	 * 
	 * @param idCentroAsociado id del centro poseedor de los campos
	 * @return lista de campos asociados a un centro
	 */
	public ConversionCuentaBean ejecutarTRN(long cuentaNueva, String bol,String cuentaAntigua,String centro){

		ITRCNVTCTAVALENCIATRN contexto = initPeticion(cuentaNueva, bol,cuentaAntigua,centro);

		EjecutarResult respuesta = ejecutarWS(contexto);

		super.verificaRespuesta(respuesta);
		
		return getCuentas(respuesta.getResponseBansefi(),bol);
	}

	/**
	 * Método para construir una lista de campos de un centro
	 * 
	 * @param response respuesta de servicio web
	 * @return lista de campos asociados a un centro
	 */
	public ConversionCuentaBean getCuentas(ResponseBansefi response,String bol){

		ConversionCuentaBean conversionCuentaBean = new ConversionCuentaBean();
		if(bol.equalsIgnoreCase("S")){
		conversionCuentaBean.setCuenta(""+response.getOTRCNVTCTAVALENCIATRN()
				.getTRCNVTCTAVALENCIAEVT().getCXSCIMIGRACTAT().getNUMSECAC1());
		conversionCuentaBean.setCentro(response.getOTRCNVTCTAVALENCIATRN()
				.getTRCNVTCTAVALENCIAEVT().getCXSCIMIGRACTAT().getCODINTERNOUO1());
		}
		if(bol.equalsIgnoreCase("N")){
		conversionCuentaBean.setCuenta(response
				.getOTRCNVTCTAVALENCIATRN().getTRCNVTCTAVALENCIAEVT()
				.getCXSCIMIGRACTAT().getIDCTAVIEJA());
		conversionCuentaBean.setCentro(response.getOTRCNVTCTAVALENCIATRN()
				.getTRCNVTCTAVALENCIAEVT().getCXSCIMIGRACTAT().getCODINTERNOUO1());
		}
		return conversionCuentaBean;
	}

	/**
	 * ------Método para inicializar el objeto de petición al servicio web
	 * 
	 * @param idcentro
	 *            id del centro poseedor de los campos
	 * @return objeto para petición al servicio web
	 */
	public ITRCNVTCTAVALENCIATRN initPeticion(long cuentaNueva, String bol,String cuentaAntigua,String centro) {

		ITRCNVTCTAVALENCIATRN contexto = new ITRCNVTCTAVALENCIATRN();
		STDTRNIPARMV cuerpoContexto = new STDTRNIPARMV();

		TRCNVTCTAVALENCIAEVT datosEntrada = new TRCNVTCTAVALENCIAEVT();
		CXSCIMIGRACTAA1 cuentaAnti = new CXSCIMIGRACTAA1();
		CXSCIMIGRACTAP cuentaNew =new CXSCIMIGRACTAP();
		cuentaAnti.setCODNRBEEN1(getEntidad());
		cuentaAnti.setCODINTERNOUO1(centro);
		cuentaAnti.setNUMSECAC1(cuentaNueva);
	
		cuentaNew.setCODINTERNOUO(centro);
		cuentaNew.setCODNRBEEN(getEntidad());
		cuentaNew.setIDCTAVIEJA(cuentaAntigua);
		cuerpoContexto.setCODTX(CodTxConstants.TR_CNVT_CTA_VALENCIA_TRN);
		cuerpoContexto.setIDINTERNOTERMTN(super.getTerminal());

		CANTCNUE std = new CANTCNUE();
		std.setSTDCHAR01(bol);

		datosEntrada.setCXSCIMIGRACTAA1(cuentaAnti);
		datosEntrada.setCANTCNUE(std);
		datosEntrada.setCXSCIMIGRACTAP(cuentaNew);
		// cuerpoContexto.setNUMSEC(58602);
		// cuerpoContexto.setCODTXDI("xxxx");
		// cuerpoContexto.setIDEMPLAUT(idemplaut);

		contexto.setTRCNVTCTAVALENCIAEVT(datosEntrada);
		contexto.setSTDTRNIPARMV(cuerpoContexto);

		super.initialize(contexto);

		return contexto;

	}

	/**
	 * -----Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto
	 *            Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	public EjecutarResult ejecutarWS(ITRCNVTCTAVALENCIATRN contexto) {
		EjecutarResult respuesta = null;

		try {
			respuesta = (EjecutarResult) this.servicioWebUtils.ejecutarWS(
					ConvertirCuentaService.class, contexto);
		} catch (NoControlableException ex) {
			throw new NoControlableException(
					"Error al invocar el servicio de consulta de Centros Asociados a Emitir. ",
					ex);
		}

		return respuesta;
	}
	
}
