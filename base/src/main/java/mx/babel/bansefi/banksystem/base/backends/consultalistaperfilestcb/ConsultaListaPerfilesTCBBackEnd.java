package mx.babel.bansefi.banksystem.base.backends.consultalistaperfilestcb;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import mx.babel.arq.cache.annotation.Cache;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.utils.ProveedorMensajeSpringUtils;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.webservices.consultalistaperfilesTCB.ConsultaListaPerfilesTCBServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultalistaperfilesTCB.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultalistaperfilesTCB.EjecutarResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para consulta de perfiles de TCB
 * 
 * @author javier.martinnino
 *
 */
@Component
public class ConsultaListaPerfilesTCBBackEnd extends BackEndBean implements Serializable {
	

	private static final long serialVersionUID = 3957124410460011599L;
	
	private static final Logger LOGGER = LogManager.getLogger(ConsultaListaPerfilesTCBBackEnd.class.getName());
	
		
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	/**
	 * Función encargada de consultar un empleado por medio de servicios web para el uso como catalogo.
	 * 
	 * @param entidad La entidad a Consultar
	 * @return List<CatalogoBean> de Empleado con los datos recuperados
	 */
	@Cache
	public List<String> ejecutarTRN (String entidad){
		
		LOGGER.debug("Accedemos al servicio de consulta de lista de perfiles de TCB: ENTRADA");
		
		final List<String> result = new CopyOnWriteArrayList<String>();
		
		Ejecutar.ITRLISTPERFILENTTRNI contexto = new Ejecutar.ITRLISTPERFILENTTRNI();
		
		Ejecutar.ITRLISTPERFILENTTRNI.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRLISTPERFILENTTRNI.STDTRNIPARMV();
		
		Ejecutar.ITRLISTPERFILENTTRNI.TRLISTPERFILENTEVTY datosEntrada =
				new Ejecutar.ITRLISTPERFILENTTRNI.TRLISTPERFILENTEVTY();
		
		Ejecutar.ITRLISTPERFILENTTRNI.TRLISTPERFILENTEVTY.CRENTPERFILTXP datosCentro =
				new Ejecutar.ITRLISTPERFILENTTRNI.TRLISTPERFILENTEVTY.CRENTPERFILTXP();
				
		super.initialize(contexto);		
		
		contexto.setELEVATORPOSITION(1);
		contexto.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_LIST_PERFIL_ENT_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		
		contexto.setSTDTRNIPARMV(contextoEntrada);		
		
		datosCentro.setCODNRBEEN(entidad);		
		datosEntrada.setCRENTPERFILTXP(datosCentro);
		contexto.setTRLISTPERFILENTEVTY(datosEntrada);
		
		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaListaPerfilesTCBServicio.class, contexto);
		
		try{
			super.verificaRespuesta(respuesta);
			for (int i=0;i<respuesta.getResponseBansefi().getOTRLISTPERFILENTTRNO().getNUMBEROFRECORDS();i++){
				String perfil = respuesta.getResponseBansefi().getOTRLISTPERFILENTTRNO().getTRLISTPERFILENTEVTZ().getCRENTPERFILTXE().get(i).getNOMPERFILEN();
				if (perfil !=null){
					perfil=perfil.trim();
					result.add(perfil);
				}
				
			}
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return result;
			}
		}
		
		LOGGER.debug("Salimos del servicio de consulta de lista de perfiles de TCB: SALIDA");
		return result;
	}
			
}
