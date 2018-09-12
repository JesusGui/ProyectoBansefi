package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.ProductoSimpleBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.enums.EstadosCuentaEnum;
import mx.babel.bansefi.banksystem.base.utils.ProductosSimplesWrapperUtils;
import mx.babel.bansefi.banksystem.base.webservices.consultacondiciones.ConsultaCondicionesServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultacondiciones.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultacondiciones.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultacondiciones.ResponseBansefi.OTRCONSVALMSVKATRNO.TRCONSVALMSVKAEVTZ.KACDAC1V;
import mx.babel.bansefi.banksystem.base.webservices.consultacondiciones.ResponseBansefi.OTRCONSVALMSVKATRNO.TRCONSVALMSVKAEVTZ.KACDAC1V.KACDACE;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaCondicionesCuentaBackend extends BackEndBean {

	private static final long serialVersionUID = -6813915274926120509L;
	private static final Logger LOGGER = LogManager.getLogger(ConsultaProductosSimplesBackend.class.getName());
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	@Autowired
	ProductosSimplesWrapperUtils productosSimplesWrapperUtils;
	
	public List<ProductoSimpleBean> EjecutarTRN(CuentaBean cuentaBean) throws ControlableException, NoControlableException{
		
		List<KACDAC1V> listaRespuesta = new ArrayList<KACDAC1V>();
		
		listaRespuesta = this.Ejecutar(cuentaBean, listaRespuesta);
		
		return this.wrappProductosSimples(listaRespuesta);
	}
	
	/**
	 * Método que ejecuta la TRN
	 * @param cuentaBean
	 * @param lista
	 * @return Lista de TODOS los productos simples de la cuenta
	 */
	public List<KACDAC1V> Ejecutar(CuentaBean cuentaBean, List<KACDAC1V> lista){
		Ejecutar.ITRCONSVALMSVKATRNI contexto = null;
		
		if(lista.isEmpty()){
			contexto = initPeticion(cuentaBean, null);
		} else {
			contexto = initPeticion(cuentaBean, lista.get(lista.size()-1).getKACDACE());
		}
		
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return lista;
			}
		}
		//SE VERIFICA SI HAY MÁS DATOS QUE CONSULTAR
		if(respuesta.getResponseBansefi().getOTRCONSVALMSVKATRNO().getMOREDATAIN() > 0){
			lista.addAll(respuesta.getResponseBansefi().getOTRCONSVALMSVKATRNO().getTRCONSVALMSVKAEVTZ().getKACDAC1V());
			this.Ejecutar(cuentaBean, lista);
		} else{
			lista.addAll(respuesta.getResponseBansefi().getOTRCONSVALMSVKATRNO().getTRCONSVALMSVKAEVTZ().getKACDAC1V());
		}
		
		return lista;
	}
	
	/**
	 * Función que inicializa los datos de consulta de la TRN
	 * @param cuentaBean
	 * @return
	 */
	private Ejecutar.ITRCONSVALMSVKATRNI initPeticion(CuentaBean cuentaBean, KACDACE kacdace){
		Ejecutar.ITRCONSVALMSVKATRNI contexto = new Ejecutar.ITRCONSVALMSVKATRNI();
		Ejecutar.ITRCONSVALMSVKATRNI.TRCONSVALMSVKAEVTY trconsvalmsvkaevty = new Ejecutar.ITRCONSVALMSVKATRNI.TRCONSVALMSVKAEVTY();
		Ejecutar.ITRCONSVALMSVKATRNI.STDTRNIPARMV stdtrniparmv = new Ejecutar.ITRCONSVALMSVKATRNI.STDTRNIPARMV();
		Ejecutar.ITRCONSVALMSVKATRNI.TRCONSVALMSVKAEVTY.KACDACP kacdacp = new Ejecutar.ITRCONSVALMSVKATRNI.TRCONSVALMSVKAEVTY.KACDACP();
		Ejecutar.ITRCONSVALMSVKATRNI.TRCONSVALMSVKAEVTY.KAREPOSV karespov = new Ejecutar.ITRCONSVALMSVKATRNI.TRCONSVALMSVKAEVTY.KAREPOSV();
		Ejecutar.ITRCONSVALMSVKATRNI.TRCONSVALMSVKAEVTY.KACDACODLSTV kacdaccodlstv = new Ejecutar.ITRCONSVALMSVKATRNI.TRCONSVALMSVKAEVTY.KACDACODLSTV();
		
		stdtrniparmv.setIDINTERNOTERMTN(super.getTerminal());
		stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_CONS_VAL_MSV_KA_TRN);
		try{
			if(cuentaBean != null && cuentaBean.getEstadoEnum() != null){
				if(cuentaBean.getEstadoEnum().equals(EstadosCuentaEnum.CANCELADO)){
					kacdaccodlstv.setCODECVKA("");
				}else{
					kacdaccodlstv.setCODECVKA("6");
				}
			}			
		}catch(NullPointerException e){
			kacdaccodlstv.setCODECVKA("6");
		}
		
		
		kacdacp.setCODNRBEEN(super.getEntidad());
		kacdacp.setNUMSECAC(cuentaBean.getNumeroCuenta());
		
		//PARA EL CASO DE PAGINACIÓN
		if(kacdace != null){
			karespov.setIDPARMCD(kacdace.getIDPARMCD().trim());
			karespov.setIDPDS(kacdace.getIDPDS().trim());
			
			trconsvalmsvkaevty.setKAREPOSV(karespov);
		}
		
		
		trconsvalmsvkaevty.setKACDACODLSTV(kacdaccodlstv);
		trconsvalmsvkaevty.setKACDACP(kacdacp);
		
		contexto.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);
		contexto.setTRCONSVALMSVKAEVTY(trconsvalmsvkaevty);
		contexto.setSTDTRNIPARMV(stdtrniparmv);
		
		super.initialize(contexto);
		
		return contexto;
	}
	
	/**
	 * Función que ejecuta la consulta a la TRN 
	 * @param contexto
	 * @return
	 * @throws NoControlableException
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRCONSVALMSVKATRNI contexto)
				throws NoControlableException{
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
	                ConsultaCondicionesServicio.class, contexto);
		} catch(NoControlableException e){
			LOGGER.error("Error al invocar servicio web de consulta de "
                    + "productos simples.", e);
            throw new NoControlableException("Error al invocar servicio web de consulta de "
                    + "productos simples.", e);
		}
		
		return respuesta;
	}
	
	/**
	 * Método para realizar el mapeo de condiciones
	 * @param lista
	 * @return lista de productos simples
	 */
	private List<ProductoSimpleBean> wrappProductosSimples(List<KACDAC1V> lista){
		List<ProductoSimpleBean> listaProdSimples = new ArrayList<ProductoSimpleBean>();
		
		if(!lista.isEmpty()){
			listaProdSimples = productosSimplesWrapperUtils.wrappCondicionesDeCuenta(lista);
		}
		
		return listaProdSimples;
	}
	
}
