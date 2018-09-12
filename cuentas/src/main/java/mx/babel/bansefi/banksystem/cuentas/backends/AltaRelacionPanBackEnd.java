package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.Date;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.PanBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.altarelacionpan.AltaRelacionPanServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.altarelacionpan.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.altarelacionpan.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.altarelacionpan.ResponseBansefi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AltaRelacionPanBackEnd extends BackEndBean{

	private static final long serialVersionUID = -5862344282240025596L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	public Boolean ejecutarTRN(PanBean pan, CuentaBean cuenta, Long numeroCuentaOperativa, Integer idTitular){
		
		Ejecutar.IMPALTATCBSATTRNI contexto = initPeticion(pan, cuenta, numeroCuentaOperativa, idTitular);
		
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		super.verificaRespuesta(respuesta);		
		
		return altaCorrecta(respuesta.getResponseBansefi(), pan);
	}
	
	private Boolean altaCorrecta(ResponseBansefi respuesta, PanBean pan){		
		pan.setEstadoListado(EstadoListadosEnum.CONSULTADO);
		if(pan.getTitular()){
			pan.setTipoTarjeta("PAN TITULAR");
		}else{
			pan.setTipoTarjeta("PAN ADICIONAL");
		}
		pan.setNumeroPan(respuesta.getOMPALTATCBSATTRNO().getPANSATV().getSTDCHAR22().trim());
		return true;
	}
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param anotacion anotacion a consultar
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.IMPALTATCBSATTRNI initPeticion(PanBean pan,  CuentaBean cuenta, 
			Long numeroCuentaOperativa, Integer idTitular){
		Ejecutar.IMPALTATCBSATTRNI contexto = new Ejecutar.IMPALTATCBSATTRNI();
		Ejecutar.IMPALTATCBSATTRNI.STDAPPLPARMV contextoEntrada = 
				new Ejecutar.IMPALTATCBSATTRNI.STDAPPLPARMV();
		Ejecutar.IMPALTATCBSATTRNI.MPALTASAT cuerpoContexto =
				new Ejecutar.IMPALTATCBSATTRNI.MPALTASAT();
		Ejecutar.IMPALTATCBSATTRNI.MPALTASATCB stock =
				new Ejecutar.IMPALTATCBSATTRNI.MPALTASATCB();
		contexto.setMPALTASAT(cuerpoContexto);
		contexto.setMPALTASATCB(stock);
		contexto.setSTDAPPLPARMV(contextoEntrada);	
		super.initialize(contexto);
		
		contexto.setSCROLLABLEOCCURS(BackEndBean.SCROLLABLE_OCCURS);
		
		contextoEntrada.setCODNRBEEN(super.getEntidad());
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_ACT_STOCK_TAR_TRN);
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());	
		contextoEntrada.setCODISO(ConstantesFuncionales.COD_MONEDA);
		IntegerToDateConverter itdc = new IntegerToDateConverter();
		contextoEntrada.setFECHAOPRCN(itdc.convertFrom(new Date()));
		contextoEntrada.setFECHACTBLE(itdc.convertFrom(super.getFechaSistema()));
		
		cuerpoContexto.setCODLINEA(cuenta.getCodLinea());
		cuerpoContexto.setIDGRPPD(cuenta.getIdGrupoProducto());
		cuerpoContexto.setIDPDV(cuenta.getIdProducto());
		cuerpoContexto.setIDTRFAPDV(cuenta.getIdTarifaProducto());
		cuerpoContexto.setIDINTERNOPE(idTitular);
		cuerpoContexto.setCODINTERNOUO(super.getSucursal());
		
		stock.setCODNRBEEN(super.getEntidad());
		stock.setNUMSECAC(cuenta.getNumeroCuenta());
		
		stock.getMPSATPERSV().setIDINTERNOPE(idTitular);
		stock.getMPSATACUEPASV().setNUMSECAC1(numeroCuentaOperativa);
		stock.getMPSATDESTINOV().setSTDCHAR10(pan.getTipoPan());
		stock.getMPSATTIPOV().setSTDCHAR15(pan.getTipoTarjeta());
		stock.getMPSATESTAMPAV().setSTDCHAR30(pan.getNombreCliente());
		if(pan.getNumeroPan() != null && !pan.getNumeroPan().isEmpty()){
			stock.getMPSATPANV().setSTDCHAR40(pan.getNumeroPan());
		}
		stock.getMPSATCONDECOV().setSTDCHAR03(pan.getCondicionEconomica());
		stock.getMPSATINDDCV().setSTDCHAR08(pan.getDebitoCredito());
		stock.getMPSATPRDTCBV().setSTDCHAR02(pan.getProductoTCB());
		stock.setSTDCHAR11(pan.getBin());
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.IMPALTATCBSATTRNI contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(AltaRelacionPanServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de alta de relación "
					+ "cuenta con pan.", e);
		}
		return respuesta;
	}
	
}
