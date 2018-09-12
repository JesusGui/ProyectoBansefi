package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.beans.PlanificacionBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalleplanificacion.ConsultaDetallePlanificacionServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalleplanificacion.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalleplanificacion.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalleplanificacion.ResponseBansefi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la consulta de planificaciones
 * @author gerard.chavez
 *
 */
@Component
public class ConsultaDetallePlanificacionBackend extends BackEndBean{

	private static final long serialVersionUID = 4308712218258049700L;
	
	@Autowired
    ServicioWebUtils servicioWebUtils;
    
  
    public List<PlanificacionBean> ejecutarTRN(Long numCuenta, Date fechaPlanif){
        
        Ejecutar.ITRCONSUADICPLACTRN contexto = initPeticion(numCuenta, fechaPlanif);
        
        EjecutarResult respuesta = (EjecutarResult)servicioWebUtils.ejecutarWS(ConsultaDetallePlanificacionServicio.class, contexto);
        
        super.verificaRespuesta(respuesta);
        
        return getPlanificaciones(respuesta); 
    }
    
    /**
     * Función que inicializa el objeto para realizar la petición al servicio web.
     * 
     * @return Objeto a ser enviado al servicio web.
     */
    private Ejecutar.ITRCONSUADICPLACTRN initPeticion(Long numCuenta, Date fechaPlanif){
        Ejecutar.ITRCONSUADICPLACTRN contexto = new Ejecutar.ITRCONSUADICPLACTRN();
        Ejecutar.ITRCONSUADICPLACTRN.STDTRNIPARMV contextoEntrada = new Ejecutar.ITRCONSUADICPLACTRN.STDTRNIPARMV();
        Ejecutar.ITRCONSUADICPLACTRN.TRCONSUADICPLACEVTY cuerpoContexto = new Ejecutar.ITRCONSUADICPLACTRN.TRCONSUADICPLACEVTY();
        
        contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_CONSU_ADIC_PL_AC_TRN);
        contextoEntrada.setIDINTERNOTERMTN(getTerminal());
        contextoEntrada.setCODTXDI("");
        contextoEntrada.setIDEMPLAUT("");
        contextoEntrada.setNUMSEC(0);
        
        Ejecutar.ITRCONSUADICPLACTRN.TRCONSUADICPLACEVTY.ACPLNFCNP acplnfcnp = new Ejecutar.ITRCONSUADICPLACTRN.TRCONSUADICPLACEVTY.ACPLNFCNP();
        acplnfcnp.setCODNRBEEN(getEntidad());
        acplnfcnp.setNUMSECAC(numCuenta);
        
        
        Ejecutar.ITRCONSUADICPLACTRN.TRCONSUADICPLACEVTY.ACPLNFCNP.TIPOPLNFCNSTDP tipoPlanificacion = new Ejecutar.ITRCONSUADICPLACTRN.TRCONSUADICPLACEVTY.ACPLNFCNP.TIPOPLNFCNSTDP();
        tipoPlanificacion.setCODTX(CodTxConstants.COD_TX_TR_CONSU_ADIC_PL_AC_TRN);
        
        IntegerToDateConverter converter = new IntegerToDateConverter();
        String valorConCero = "%02d";
        Calendar cal = Calendar.getInstance();
        cal.setTime(fechaPlanif);
        tipoPlanificacion.setFECHAPLANIF(converter.convertFrom(fechaPlanif));
        tipoPlanificacion.setHORAPLANIF(Integer.valueOf(String.format(valorConCero,cal.get(Calendar.HOUR_OF_DAY))+""+String.format(valorConCero,cal.get(Calendar.MINUTE))+""+String.format(valorConCero,cal.get(Calendar.SECOND))));
        
        acplnfcnp.setTIPOPLNFCNSTDP(tipoPlanificacion);
        cuerpoContexto.setACPLNFCNP(acplnfcnp);
        
        contexto.setSTDTRNIPARMV(contextoEntrada);
        contexto.setSCROLLABLEOCCURS(50);
        contexto.setELEVATORPOSITION(1);
        contexto.setTRCONSUADICPLACEVTY(cuerpoContexto);
        super.initialize(contexto);
        
        return contexto;
    }
    

    
    /**
     * Función encargada de obtener una lista de planifcaciones a partir de la respuesta del servicio.
     * 
     * @param idInterno El id interno de la persona moral
     * @param response El objeto de reultado enviado por el servicio web.
     * @return
     */
    private List<PlanificacionBean> getPlanificaciones(EjecutarResult respuesta)
            throws NoControlableException, ControlableException{
        List<PlanificacionBean> planificaciones = new ArrayList<PlanificacionBean>();
        if(verificaRespuesta(respuesta)){
            for (ResponseBansefi.OTRCONSUADICPLACTRN.TRCONSUADICPLACEVTZ detallePlanificacion : 
            	respuesta.getResponseBansefi().getOTRCONSUADICPLACTRN().getTRCONSUADICPLACEVTZ()) {
                
                //convertir objeto de respuesta a un PlanificacionBean
            	if(!"".equals(detallePlanificacion.getCODINFADIPLNFCN().trim())){
	                PlanificacionBean planificacionResult = new PlanificacionBean();
	                planificacionResult.setCodInfoAdicional(detallePlanificacion.getCODINFADIPLNFCN());
	                planificacionResult.setInfoAdicional(detallePlanificacion.getVALACINFADICPL());
	                planificaciones.add(planificacionResult);            
            	}
            }
        }
        return planificaciones;
    }
    
    /**
     * Función que valida que la respuesta Bansefi contenga un objeto con los datos
     * del alta de realción.
     * 
     * @param response Respuesta Bansefi con datos del alta
     * @return <code>true</code> si la respuesta Bansefi contiene los datos de alta
     */
    private Boolean verificaRespuesta(EjecutarResult respuesta){
	   Boolean noNulo = false;
	   if(respuesta != null && respuesta.getResponseBansefi().getOTRCONSUADICPLACTRN() != null && 
	   		respuesta.getResponseBansefi().getOTRCONSUADICPLACTRN().getTRCONSUADICPLACEVTZ() != null &&
	           !respuesta.getResponseBansefi().getOTRCONSUADICPLACTRN().getTRCONSUADICPLACEVTZ().isEmpty()){
	       noNulo = true;
	   }
	   return noNulo;
    }
    

}
