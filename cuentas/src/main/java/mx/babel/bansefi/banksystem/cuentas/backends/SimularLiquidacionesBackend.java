package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.LiquidacionBean;
import mx.babel.bansefi.banksystem.base.beans.LiquidacionConceptoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.webservices.simularliquidaciones.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.simularliquidaciones.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.simularliquidaciones.ResponseBansefi.OTRLIQSIMULARLIQTRNO.TRLIQSIMULARLIQEVTZ.HLHCOLIQE;
import mx.babel.bansefi.banksystem.cuentas.webservices.simularliquidaciones.ResponseBansefi.OTRLIQSIMULARLIQTRNO.TRLIQSIMULARLIQEVTZ.LIQAFSIMULARLST.LIQAFSIMULARV;
import mx.babel.bansefi.banksystem.cuentas.webservices.simularliquidaciones.SimularLiquidacionesServicio;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaLiquidacionesWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimularLiquidacionesBackend extends BackEndBean{

	private static final long serialVersionUID = 955101551666146195L;

	@Autowired
    ServicioWebUtils servicioWebUtils;
	
	@Autowired
	ConsultaLiquidacionesWrapper consultaLiquidacionesWrapper;
	
	/**
	 * Método que ejecuta la transacción de consulta masiva de liquidaciones 
	 * 
	 * @param numeroCuenta el numero de la cuenta a consultar
	 * @param fechaLiquidacion la fecha de liquidacion
	 * @return List<LiquidacionInformacionBean> lista de bean LiquidacionInformacionBean obtenidos
	 */
	public void ejecutarTRN(CuentaBean cuentaBean, LiquidacionBean liquidacionBusqueda){
		
		Ejecutar.ITRLIQSIMULARLIQTRNI contexto = initPeticion(cuentaBean , liquidacionBusqueda);
		EjecutarResult respuesta = (EjecutarResult)servicioWebUtils.ejecutarWS(SimularLiquidacionesServicio.class, contexto);
		super.verificaRespuesta(respuesta);
		
		getLiquidacion(respuesta,liquidacionBusqueda);         
	}
	
	  /**
     * Función que inicializa el objeto para realizar la petición al servicio web.
     * 
     * @return Objeto a ser enviado al servicio web.
     */
    private Ejecutar.ITRLIQSIMULARLIQTRNI initPeticion(CuentaBean cuentaBean, LiquidacionBean liquidacionBusqueda){
        Ejecutar.ITRLIQSIMULARLIQTRNI contexto = new Ejecutar.ITRLIQSIMULARLIQTRNI();
        Ejecutar.ITRLIQSIMULARLIQTRNI.STDTRNIPARMV contextoEntrada = new Ejecutar.ITRLIQSIMULARLIQTRNI.STDTRNIPARMV();
        Ejecutar.ITRLIQSIMULARLIQTRNI.TRLIQSIMULARLIQEVTY cuerpoContexto = new Ejecutar.ITRLIQSIMULARLIQTRNI.TRLIQSIMULARLIQEVTY();
        
        contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_LIQ_SIMULAR_LIQ_TRN);
        contextoEntrada.setIDINTERNOTERMTN(getTerminal());
        
        Ejecutar.ITRLIQSIMULARLIQTRNI.TRLIQSIMULARLIQEVTY.TRLIQSIMULARV datosSimular = new Ejecutar.ITRLIQSIMULARLIQTRNI.TRLIQSIMULARLIQEVTY.TRLIQSIMULARV();
        datosSimular.setCODNRBEEN(getEntidad());
        datosSimular.setNUMSECAC(cuentaBean.getNumeroCuenta());
        datosSimular.setCODOPERLIQ(liquidacionBusqueda.getCodOperacionLiq());
        datosSimular.setFECHAHASTA(getFechaSistemaInteger());
        cuerpoContexto.setTRLIQSIMULARV(datosSimular);
        
        
        contexto.setTRLIQSIMULARLIQEVTY(cuerpoContexto);
        contexto.setSTDTRNIPARMV(contextoEntrada);
        super.initialize(contexto);
        
        return contexto;
    }

    /**
     * Función encargada de obtener una lista de liquidaciones a partir de la respuesta del servicio.
     * 
     * @param respusta El objeto de reultado enviado por el servicio web.
     * @return
     */
    private void getLiquidacion(EjecutarResult respuesta, LiquidacionBean liquidacionBusqueda)
            throws NoControlableException, ControlableException{
        if(verificaRespuesta(respuesta)){
            HLHCOLIQE liquidacionRes = respuesta.getResponseBansefi().getOTRLIQSIMULARLIQTRNO().getTRLIQSIMULARLIQEVTZ().getHLHCOLIQE();     
        	if(!"".equals(liquidacionRes.getCODNRBEEN().trim())){
        		consultaLiquidacionesWrapper.wrappSimulacionLiquidacion(liquidacionRes, liquidacionBusqueda);
        	}
        	
        	//mapear lista de conceptos
            if(respuesta.getResponseBansefi().getOTRLIQSIMULARLIQTRNO().getTRLIQSIMULARLIQEVTZ().getLIQAFSIMULARLST().getLIQAFSIMULARV() != null && 
            		respuesta.getResponseBansefi().getOTRLIQSIMULARLIQTRNO().getTRLIQSIMULARLIQEVTZ().getLIQAFSIMULARLST().getLIQAFSIMULARV().size()!=0){
            	List<LiquidacionConceptoBean> conceptos = new ArrayList<>();
            	int i = 0;
            	for(LIQAFSIMULARV conceptoRes : respuesta.getResponseBansefi().getOTRLIQSIMULARLIQTRNO().getTRLIQSIMULARLIQEVTZ().getLIQAFSIMULARLST().getLIQAFSIMULARV()){
            		if(conceptoRes.getCODCTA() != null && !"".equals(conceptoRes.getCODCTA().trim()) ){
            			LiquidacionConceptoBean concepto = new LiquidacionConceptoBean();
            			concepto.setCodCuenta(conceptoRes.getCODCTA());
            			concepto.setImporteFacturado(conceptoRes.getIMPAPNTE());
            			concepto.setSigno(conceptoRes.getCODORIGEN());
            			concepto.setRowKey(i);
            			concepto.setCodOrigen(conceptoRes.getCODORIGEN());            			
            			getListaIndicadores(concepto, conceptoRes);
            			conceptos.add(concepto);
            			i++;
            		}
            	}
            	liquidacionBusqueda.setListaConceptos(conceptos);
            }
        }
    }
    
    /**
	 * Método que se encarga de obtener todos los indicadores que hayan sido
	 * devueltos por la transacción.
	 * 
	 * @param parametrosBean
	 * @param elemento
	 * @throws NullPointerException
	 */
	private void getListaIndicadores(LiquidacionConceptoBean resultado,
			LIQAFSIMULARV elemento) throws NullPointerException {
		resultado.setIndicadores(new ArrayList<String>());
			if (elemento.getIND1() == null){
				resultado.getIndicadores().add("");

			}else{
				resultado.getIndicadores().add(elemento.getIND1().trim());

			}
			if (elemento.getIND2() == null){
				resultado.getIndicadores().add("");

			}else{
				resultado.getIndicadores().add(elemento.getIND2().trim());

			}
			if (elemento.getIND3() == null){
				resultado.getIndicadores().add("");

			}else{
				resultado.getIndicadores().add(elemento.getIND3().trim());

			}
			if (elemento.getIND4() == null){
				resultado.getIndicadores().add("");

			}else{
				resultado.getIndicadores().add(elemento.getIND4().trim());

			}
			if (elemento.getIND5() == null){
				resultado.getIndicadores().add("");

			}else{
				resultado.getIndicadores().add(elemento.getIND5().trim());

			}
			if (elemento.getIND6() == null){
				resultado.getIndicadores().add("");

			}else{
				resultado.getIndicadores().add(elemento.getIND6().trim());

			}
			if (elemento.getIND7() == null){
				resultado.getIndicadores().add("");

			}else{
				resultado.getIndicadores().add(elemento.getIND7().trim());

			}
			if (elemento.getIND8() == null){
				resultado.getIndicadores().add("");

			}else{
				resultado.getIndicadores().add(elemento.getIND8().trim());

			}
			if (elemento.getIND9() == null){
				resultado.getIndicadores().add("");

			}else{
				resultado.getIndicadores().add(elemento.getIND9().trim());

			}
			if (elemento.getIND10() == null){
				resultado.getIndicadores().add("");

			}else{
				resultado.getIndicadores().add(elemento.getIND10().trim());

			}
	}
    
    /**
     * Función que valida que la respuesta Bansefi contenga un objeto con los datos
     * 
     * @param response Respuesta Bansefi con datos del alta
     * @return <code>true</code> si la respuesta Bansefi contiene los datos de alta
     */
    private Boolean verificaRespuesta(EjecutarResult respuesta){
	   Boolean noNulo = false;
	   if(respuesta != null && respuesta.getResponseBansefi().getOTRLIQSIMULARLIQTRNO() != null && 
	   		respuesta.getResponseBansefi().getOTRLIQSIMULARLIQTRNO().getTRLIQSIMULARLIQEVTZ() != null &&
	   				respuesta.getResponseBansefi().getOTRLIQSIMULARLIQTRNO().getTRLIQSIMULARLIQEVTZ().getHLHCOLIQE() != null){
	       noNulo = true;
	   }
	   return noNulo;
    }
    
}