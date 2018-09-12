package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.beans.EstadoDocumentoBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaestadosdocumento.ConsultaEstadosDocumentoServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaestadosdocumento.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaestadosdocumento.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaestadosdocumento.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaestadosdocumento.ResponseBansefi.OTRDECONSECVSTRNO.TRDECONSECVSEVTZ;
import mx.babel.bansefi.banksystem.cuentas.wrappers.EmisionDocumentosWrapper;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back End para consulta de estados de un docuemnto
 * @author mario.montesdeoca
 *
 */
@Component
public class ConsultaEstadosDocumentoBackEnd extends BackEndBean{

	private static final long serialVersionUID = 6635586084028138726L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	@Autowired
	EmisionDocumentosWrapper emisioDocumentosWrapper;

	/**
	 * Método para consultar los estados de un documento
	 * @param idDocumento id del documento para consultar sus estados
	 * @return lista de estados asociados a un documento
	 */
	public List<EstadoDocumentoBean> ejecutarTRN(Integer idDocumento){
		
		if(idDocumento != null){
			Ejecutar.ITRDECONSECVSTRNI contexto = initPeticion(idDocumento);
			
			EjecutarResult respuesta = ejecutarWS(contexto);
			
			try{
				super.verificaRespuesta(respuesta);
			}catch(ControlableException ce){
				if(ce.getRtncod() != RETURN_COD_SIN_DATOS){
					throw ce;
				}else{
					return new ArrayList<EstadoDocumentoBean>();
				}
				
			}			
			
			return getEstados(respuesta.getResponseBansefi());
		}
		
		return new ArrayList<EstadoDocumentoBean>();
	}
	
	/**
	 * Método para crear una lista de estados para un documento
	 * @param response respuesta de servicio web
	 * @return lista de estados asociados a un documento
	 */
	private List<EstadoDocumentoBean> getEstados(ResponseBansefi response){
		List<EstadoDocumentoBean> estados = new ArrayList<EstadoDocumentoBean>();		
		if(verificaRespuestaDocumento(response)){
			for (TRDECONSECVSEVTZ estado : response.getOTRDECONSECVSTRNO().getTRDECONSECVSEVTZ()) {
				if(!StringUtils.isEmpty(estado.getCODECVDOCEMTDO().trim())){
					estados.add(emisioDocumentosWrapper.wrappBean(estado));
				}
			}
		}		
		return estados;
	}
	
	/**
	 * Método para inicializar el objeto de petición al servicio web
	 * @param idDocumento id del documento para consultar sus estados
	 * @return objeto para petición al servicio web
	 */
	private Ejecutar.ITRDECONSECVSTRNI initPeticion(Integer idDocumento){
		Ejecutar.ITRDECONSECVSTRNI contexto = new Ejecutar.ITRDECONSECVSTRNI();
		Ejecutar.ITRDECONSECVSTRNI.TRDECONSECVSEVTY documento = 
				new Ejecutar.ITRDECONSECVSTRNI.TRDECONSECVSEVTY();
		Ejecutar.ITRDECONSECVSTRNI.STDTRNIPARMV cuerpoContexto = 
				new Ejecutar.ITRDECONSECVSTRNI.STDTRNIPARMV();
		contexto.setSTDTRNIPARMV(cuerpoContexto);
		contexto.setTRDECONSECVSEVTY(documento);
		super.initialize(contexto);
		
		documento.setIDINTERNODC(idDocumento);
		documento.setCODNRBEEN(super.getEntidad());
		
		cuerpoContexto.setCODTX(CodTxConstants.COD_TX_TR_DE_CONS_ECVS_TRN);
		cuerpoContexto.setIDINTERNOTERMTN(super.getTerminal());
		
		contexto.setELEVATORPOSITION(1);
		contexto.setSCROLLABLEOCCURS(50);
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRDECONSECVSTRNI contexto){
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaEstadosDocumentoServicio.class,contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "de estados de documentos a emitir.", e);
		}
		return respuesta;
	}
	
	
	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con los datos
	 * de cuentas.
	 * 
	 * @param response Respuesta Bansefi con datos de cuentas
	 * @return <code>true</code> si la respuesta Bansefi contiene los datos de alta
	 */
	private Boolean verificaRespuestaDocumento(ResponseBansefi response){
		Boolean noNulo = false;
		if(response != null && response.getOTRDECONSECVSTRNO() != null && 
				response.getOTRDECONSECVSTRNO().getTRDECONSECVSEVTZ() != null &&
				!response.getOTRDECONSECVSTRNO().getTRDECONSECVSEVTZ().isEmpty()){
			noNulo = true;
		}
		return noNulo;
	}
}
