package mx.babel.bansefi.banksystem.base.backends.consultapaises;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import mx.babel.arq.cache.annotation.Cache;
import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.utils.ProveedorMensajeSpringUtils;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.webservices.consultapaises.ConsultaPaisesServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultapaises.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultapaises.Ejecutar.ITRAGCNSNOMBRETRNI.TRAGCNSNOMBREEVTY.AGLACB;
import mx.babel.bansefi.banksystem.base.webservices.consultapaises.EjecutarResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para consulta de paises mediante servicio web para el uso como catalogo
 * 
 * @author javier.martinnino
 *
 */
@Component
public class ConsultaPaisesBackEnd extends BackEndBean implements Serializable {

	private static final long serialVersionUID = 9210925710596335186L;
	
	private static final Logger LOGGER = LogManager.getLogger(ConsultaPaisesBackEnd.class.getName());
	private static final String PAIS = "PAIS";
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	/**
	 * Función encargada de consultar todos los paises codificados mediante servicio web para el uso como catalogo.
	 * 
	 * @return List<CatalogoBean> de Paises con los datos recuperados
	 */
	@Cache
	public List<CatalogoBean> ejecutarTRN (){
		LOGGER.debug("Consulta del catalogo de paises : ENTRADA");
		final List<CatalogoBean> result = new CopyOnWriteArrayList<CatalogoBean>();
		
		Ejecutar.ITRAGCNSNOMBRETRNI contexto = new Ejecutar.ITRAGCNSNOMBRETRNI();
		
		Ejecutar.ITRAGCNSNOMBRETRNI.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRAGCNSNOMBRETRNI.STDTRNIPARMV();
		
		Ejecutar.ITRAGCNSNOMBRETRNI.TRAGCNSNOMBREEVTY datosEntrada =
				new Ejecutar.ITRAGCNSNOMBRETRNI.TRAGCNSNOMBREEVTY();
							
		contexto.setELEVATORPOSITION(1);
		contexto.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_AG_CNS_NOMBRE_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		
		contexto.setSTDTRNIPARMV(contextoEntrada);		
				
		datosEntrada.setNOMLOGICOTP(ConsultaPaisesBackEnd.PAIS);
		datosEntrada.setAGLACB(new AGLACB());
		contexto.setTRAGCNSNOMBREEVTY(datosEntrada);
				
		for(char alphabet = 'A'; alphabet <= 'Z';alphabet++) {
			LOGGER.debug("Buscamos los paises que comienzan por la letra: "+alphabet);
			contexto.getTRAGCNSNOMBREEVTY().getAGLACB().setDESCRLARGA(String.valueOf(alphabet));
			EjecutarResult respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaPaisesServicio.class, contexto);
			
			try{
				super.verificaRespuesta(respuesta);
				for (int i=0;i<respuesta.getResponseBansefi().getOTRAGCNSNOMBRETRNO().getNUMBEROFRECORDS();i++){
					CatalogoBean catalogo = new CatalogoBean();
					catalogo.setClaveFila(respuesta.getResponseBansefi().getOTRAGCNSNOMBRETRNO().getTRAGCNSNOMBREEVTZ().get(i).getCODPAISAG().trim());
					catalogo.setDescripcionC(respuesta.getResponseBansefi().getOTRAGCNSNOMBRETRNO().getTRAGCNSNOMBREEVTZ().get(i).getNOMBPAISAG().trim());
					catalogo.setDescripcionL(respuesta.getResponseBansefi().getOTRAGCNSNOMBRETRNO().getTRAGCNSNOMBREEVTZ().get(i).getNOMBPAISAG().trim());
					result.add(catalogo);
				}
			}catch (ControlableException ce){
				if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
					throw ce;
				}
			}
		}
		LOGGER.debug("Consulta del catalogo de paises : SALIDA");
		return result;
	}			
}
