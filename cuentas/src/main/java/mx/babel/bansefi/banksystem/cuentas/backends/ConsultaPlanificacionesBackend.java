package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.beans.PlanificacionBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaplanificaciones.ConsultaPlanificacionesServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaplanificaciones.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaplanificaciones.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaplanificaciones.ResponseBansefi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la consulta de planificaciones
 * @author gerard.chavez
 *
 */
@Component
public class ConsultaPlanificacionesBackend extends BackEndBean{

	private static final long serialVersionUID = -5597612907362898289L;
	
	@Autowired
    ServicioWebUtils servicioWebUtils;
	
    
    public List<PlanificacionBean> ejecutarTRN(Long numCuenta, String codigoTipoPlanificaciones){        
        
    	Ejecutar.ITRCONSUPLACYZTRNI contexto = initPeticion(numCuenta,codigoTipoPlanificaciones);
        
        EjecutarResult respuesta = (EjecutarResult)servicioWebUtils.
        		ejecutarWS(ConsultaPlanificacionesServicio.class, contexto);
        
        try{
        	super.verificaRespuesta(respuesta);
    	}catch(ControlableException ce){
    		if(ce.getRtncod()==RETURN_COD_SIN_DATOS){
    			return new ArrayList<>();
    		}
    	}        
        
        return getPlanificaciones(respuesta); 
    }
    
    /**
     * Función que inicializa el objeto para realizar la petición al servicio web.
     * 
     * @return Objeto a ser enviado al servicio web.
     */
    private Ejecutar.ITRCONSUPLACYZTRNI initPeticion(Long numCuenta, String codigoTipoPlanificaciones){
        Ejecutar.ITRCONSUPLACYZTRNI contexto = new Ejecutar.ITRCONSUPLACYZTRNI();
        Ejecutar.ITRCONSUPLACYZTRNI.STDTRNIPARMV contextoEntrada = new Ejecutar.ITRCONSUPLACYZTRNI.STDTRNIPARMV();
        Ejecutar.ITRCONSUPLACYZTRNI.TRCONSUPLACYZEVTY cuerpoContexto = new Ejecutar.ITRCONSUPLACYZTRNI.TRCONSUPLACYZEVTY();
        
        contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_CONSU_PL_AC_YZ_TRN);
        contextoEntrada.setIDINTERNOTERMTN(getTerminal());
        
        Ejecutar.ITRCONSUPLACYZTRNI.TRCONSUPLACYZEVTY.ACPLNFCNE acplnfcne = new Ejecutar.ITRCONSUPLACYZTRNI.TRCONSUPLACYZEVTY.ACPLNFCNE();
        acplnfcne.setCODNRBEEN(getEntidad());
        acplnfcne.setNUMSECAC(numCuenta);
        acplnfcne.setFECHAPLANIF(0);
        acplnfcne.setHORAPLANIF(0);
        acplnfcne.setECVACTUALPLNFCN(codigoTipoPlanificaciones);
        cuerpoContexto.setACPLNFCNE(acplnfcne);
        
        Ejecutar.ITRCONSUPLACYZTRNI.TRCONSUPLACYZEVTY.ACPLNFCNBUSCARV acplnfbuscarv = new Ejecutar.ITRCONSUPLACYZTRNI.TRCONSUPLACYZEVTY.ACPLNFCNBUSCARV();
        acplnfbuscarv.setFECHAPLANIF(0);
        acplnfbuscarv.setNUMSECAC(numCuenta);
        cuerpoContexto.setACPLNFCNBUSCARV(acplnfbuscarv);
        
        
        contexto.setTRCONSUPLACYZEVTY(cuerpoContexto);
        contexto.setSTDTRNIPARMV(contextoEntrada);
        contexto.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);
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
            for (ResponseBansefi.OTRCONSUPLACYZTRNO.TRCONSUPLACYZEVTZ.TRCONSUPLACV planificacion : 
            	respuesta.getResponseBansefi().getOTRCONSUPLACYZTRNO().getTRCONSUPLACYZEVTZ().getTRCONSUPLACV()) {
                if(!"".equals(planificacion.getACPLNFCNE().getCODNRBEEN().trim())){
	                //convertir objeto de respuesta a un PlanificacionBean
	                PlanificacionBean planificacionResult = new PlanificacionBean();
	                IntegerToDateConverter converter = new IntegerToDateConverter();
	                planificacionResult.setFechaPlanificacion(converter.convertTo(planificacion.getACPLNFCNE().getFECHAPLANIF()));
	                
	                planificacionResult.setCodEstado(planificacion.getACPLNFCNE().getECVACTUALPLNFCN());
	                planificacionResult.setCodEvento(planificacion.getACPLNFCNE().getCODTX());
	                if(respuesta.getResponseBansefi().getOTRCONSUPLACYZTRNO().getMOREDATAIN()!=0){
	                	planificacionResult.setMasDatos(true);
	                	planificacionResult.setUltimoDatoConsultaAnterior(planificacionResult);
	                }
	                planificacionResult.setNumeroDatos(respuesta.getResponseBansefi().getOTRCONSUPLACYZTRNO().getNUMBEROFRECORDS());
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
	   if(respuesta != null && respuesta.getResponseBansefi().getOTRCONSUPLACYZTRNO() != null && 
	   		respuesta.getResponseBansefi().getOTRCONSUPLACYZTRNO().getTRCONSUPLACYZEVTZ() != null &&
	   				respuesta.getResponseBansefi().getOTRCONSUPLACYZTRNO().getTRCONSUPLACYZEVTZ().getTRCONSUPLACV() != null &&
	           !respuesta.getResponseBansefi().getOTRCONSUPLACYZTRNO().getTRCONSUPLACYZEVTZ().getTRCONSUPLACV().isEmpty()){
	       noNulo = true;
	   }
	   return noNulo;
    }

}
