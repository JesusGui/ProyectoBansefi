package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.PanBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.relacionapancuenta.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.relacionapancuenta.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.relacionapancuenta.RelacionaPanCuentaServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RelacionaPanCuentaBackEnd extends BackEndBean{

	private static final long serialVersionUID = 3374878380989350407L;

	private static final String SITUACION_TARJETA_ENTREGADA = "E";
	
	private static final String SITUACION_TARJETA_RECIBIDO = "R";
	
	private static final String INTEGRANTE_ID_CON_PAN = "RB";
	
	private static final String INTEGRANTE_ID_SIN_PAN = "UPD";
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	@Autowired
	AltaRelacionPanBackEnd altaRelacionPanBackEnd;
	
	public Boolean ejecutarTRN(PanBean pan, CuentaBean cuenta, Long numeroCuentaOperativa, Integer idTitular){
		Ejecutar.ITRACTSTOCKTARTRNI contexto = initPeticion(pan, cuenta, numeroCuentaOperativa, idTitular, SITUACION_TARJETA_ENTREGADA);
		
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		super.verificaRespuesta(respuesta);		
		
		Boolean resultado = false;
		
		try{
			resultado = altaRelacionPanBackEnd.ejecutarTRN(pan, cuenta, numeroCuentaOperativa, idTitular);
		}catch(ControlableException ce){
			contexto = initPeticion(pan, cuenta, numeroCuentaOperativa, idTitular, SITUACION_TARJETA_RECIBIDO);
			respuesta = ejecutarWS(contexto);
			super.verificaRespuesta(respuesta);
			throw ce;
		}
		
		return resultado;
	}
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param anotacion anotacion a consultar
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITRACTSTOCKTARTRNI initPeticion(PanBean pan, CuentaBean cuenta, 
			Long numeroCuentaOperativa, Integer idTitular, String situacion){
		Ejecutar.ITRACTSTOCKTARTRNI contexto = new Ejecutar.ITRACTSTOCKTARTRNI();
		Ejecutar.ITRACTSTOCKTARTRNI.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRACTSTOCKTARTRNI.STDTRNIPARMV();
		Ejecutar.ITRACTSTOCKTARTRNI.TRACTSTOCKTAREVTY cuerpoContexto =
				new Ejecutar.ITRACTSTOCKTARTRNI.TRACTSTOCKTAREVTY();
		Ejecutar.ITRACTSTOCKTARTRNI.TRACTSTOCKTAREVTY.MPALTASATCB stock =
				new Ejecutar.ITRACTSTOCKTARTRNI.TRACTSTOCKTAREVTY.MPALTASATCB();
		contexto.setTRACTSTOCKTAREVTY(cuerpoContexto);
		cuerpoContexto.setMPALTASATCB(stock);
		contexto.setSTDTRNIPARMV(contextoEntrada);	
		super.initialize(contexto);
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_ACT_STOCK_TAR_TRN);
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());	
		
		contexto.setSCROLLABLEOCCURS(BackEndBean.SCROLLABLE_OCCURS);
		
		stock.setCODNRBEEN(super.getEntidad());
		stock.setNUMSECAC(cuenta.getNumeroCuenta());
		
		stock.getMPSATPERSV().setIDINTERNOPE(idTitular);
		stock.getMPSATACUEPASV().setNUMSECAC1(numeroCuentaOperativa);
		stock.getMPSATDESTINOV().setSTDCHAR10(pan.getTipoPan());
		stock.getMPSATTIPOV().setSTDCHAR15(pan.getTipoTarjeta());
		stock.getMPSATESTAMPAV().setSTDCHAR30(pan.getNombreCliente());
		stock.getMPSATPANV().setSTDCHAR40(pan.getNumeroPan());
		stock.getMPSATCONDECOV().setSTDCHAR03(pan.getCondicionEconomica());
		stock.getMPSATINDDCV().setSTDCHAR08(pan.getDebitoCredito());
		stock.getMPSATPRDTCBV().setSTDCHAR02(pan.getProductoTCB());
		stock.setSTDCHAR11(pan.getBin());
		
		cuerpoContexto.getMPSTOCKTARJETASP().setCODNRBEEN(super.getEntidad());
		if(pan.getNumeroPan() != null && !pan.getNumeroPan().isEmpty()){
			cuerpoContexto.getMPSTOCKTARJETASP().setPAN(pan.getNumeroPan());
			cuerpoContexto.getMPSTOCKTARJETASP().setINTEGRANTEID(INTEGRANTE_ID_CON_PAN);
		}else{
			cuerpoContexto.getMPSTOCKTARJETASP().setINTEGRANTEID(INTEGRANTE_ID_SIN_PAN);
		}
		
		IntegerToDateConverter itdc = new IntegerToDateConverter();
		cuerpoContexto.getMPSTOCKTARJETASE().setFECHAENTRGDOTJ(itdc.convertFrom(super.getFechaSistema()));
		cuerpoContexto.getMPSTOCKTARJETASE().setFECHASITACTUAL(itdc.convertFrom(super.getFechaSistema()));
		cuerpoContexto.getMPSTOCKTARJETASE().setECVSTOCKTJ(situacion);
		cuerpoContexto.getMPSTOCKTARJETASE().setSITUACACTUALTJ(situacion);
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRACTSTOCKTARTRNI contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(RelacionaPanCuentaServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de relacionar "
					+ "cuenta con pan.", e);
		}
		return respuesta;
	}
	
}
