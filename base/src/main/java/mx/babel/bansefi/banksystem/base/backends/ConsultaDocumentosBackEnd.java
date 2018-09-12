package mx.babel.bansefi.banksystem.base.backends;

import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.ClienteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DocumentoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.webservices.consultadocumentos.ConsultaDocumentosServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultadocumentos.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultadocumentos.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultadocumentos.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.wrappers.ClienteWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para consulta de documentos asignados a un cliente.
 * 
 * @author mario.montesdeoca
 *
 */
@Component
public class ConsultaDocumentosBackEnd extends BackEndBean{

	private static final long serialVersionUID = -3455571158831776046L;

	@Autowired
	ClienteWrapper clienteWrapper;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Metódo para definir la lista de documentos de un cliente.
	 * 
	 * @param cliente cliente al cual están asignados los documentos.
	 * @throws NoControlableException Excepción controlada de errores en front end
	 * @throws ControlableException Excepción no controlada de errores en host
	 */
	public void ejecutarTRN(ClienteBean cliente){
		if(cliente != null){
			Ejecutar.ITRDCLSCNSTRNI contexto = initPeticion(cliente);		
			EjecutarResult respuesta = ejecutarWS(contexto);
			try{
				super.verificaRespuesta(respuesta);
			}catch (ControlableException ce){
				if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
					throw ce;
				}else{
					return;
				}
			}
			if(verificaResponseBansefi(respuesta)){
				setDocumentos(respuesta.getResponseBansefi(), cliente);
			}	
		}
	}
	
	/**
	 * Método que transforma la respuesta del servicio web y 
	 * define la lista de documentos que tiene asignado un cliente.
	 * 
	 * @param response Respuesta Bansefi del servicio web 
	 * @param cliente cliente al cual están asignados los documentos
	 */
	private void setDocumentos(ResponseBansefi response, ClienteBean cliente){		
		if(verificaRespuestaDocumentos(response)){
			List<ResponseBansefi.OTRDCLSCNSTRNO.TRDCLSCNSEVTZ.TRDCLSCNSEVTV> documentos = 
					response.getOTRDCLSCNSTRNO().getTRDCLSCNSEVTZ().getTRDCLSCNSEVTV();
			for (int i = 0; i< response.getOTRDCLSCNSTRNO().getNUMBEROFRECORDS();i++){
				ResponseBansefi.OTRDCLSCNSTRNO.TRDCLSCNSEVTZ.TRDCLSCNSEVTV documento = documentos.get(i);
				DocumentoBean doc = clienteWrapper.wrappBean(documento);
				cliente.getDocumentos().add(doc);
			}			
		}
	}
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param idInterno Id del cliente de tipo persona física
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITRDCLSCNSTRNI initPeticion(ClienteBean cliente) {
		Ejecutar.ITRDCLSCNSTRNI contexto = new Ejecutar.ITRDCLSCNSTRNI();
		Ejecutar.ITRDCLSCNSTRNI.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRDCLSCNSTRNI.STDTRNIPARMV();
		Ejecutar.ITRDCLSCNSTRNI.TRDCLSCNSEVTY cuerpoContexto =
				new Ejecutar.ITRDCLSCNSTRNI.TRDCLSCNSEVTY();
		Ejecutar.ITRDCLSCNSTRNI.TRDCLSCNSEVTY.PEPERSRLDOCP persona = 
				new Ejecutar.ITRDCLSCNSTRNI.TRDCLSCNSEVTY.PEPERSRLDOCP();
		Ejecutar.ITRDCLSCNSTRNI.TRDCLSCNSEVTY.DCLSCB contextoBusqueda =
				new Ejecutar.ITRDCLSCNSTRNI.TRDCLSCNSEVTY.DCLSCB();
		Ejecutar.ITRDCLSCNSTRNI.TRDCLSCNSEVTY.DCLSCB.DCCBV tipoBusqueda =
				new Ejecutar.ITRDCLSCNSTRNI.TRDCLSCNSEVTY.DCLSCB.DCCBV();
		contexto.setSTDTRNIPARMV(contextoEntrada);
		contexto.setTRDCLSCNSEVTY(cuerpoContexto);
		cuerpoContexto.setPEPERSRLDOCP(persona);
		cuerpoContexto.setDCLSCB(contextoBusqueda);
		contextoBusqueda.setDCCBV(tipoBusqueda);
		super.initialize(contexto);
		
		persona.setCODNRBEEN(super.getEntidad());
		if(cliente.getIdInterna() != null){
			persona.setIDINTERNOPE(Integer.parseInt(Long.toString(cliente.getIdInterna())));
		}

		tipoBusqueda.setSTDCHAR08("PERSONAS");
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_DC_LS_CNS_TRN);
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		
		contexto.setELEVATORPOSITION(0);
		contexto.setSCROLLABLEOCCURS(50);
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRDCLSCNSTRNI contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaDocumentosServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta de "
					+ "documentos.", e);
		}
		return respuesta;
	}
	
	/**
	 * Función que valida que la respuesta del servidor no este vacía. 
	 *  
	 * @param respuesta Objeto respuesta del servicio web
	 * @return <code>true</code> si la respuesta no esta vacía.
	 */
	private Boolean verificaResponseBansefi(EjecutarResult respuesta){
		Boolean noNulo = false;
		if(respuesta != null && respuesta.getResponseBansefi() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con la 
	 * lista de docuemntos. 
	 * 
	 * @param response Respuesta Bansefi con lista de documentos
	 * @return <code>true</code> si la respuesta Bansefi contiene una lista de documentos
	 */
	private Boolean verificaRespuestaDocumentos(ResponseBansefi response){
		Boolean noNulo = false;
		if(response != null && response.getOTRDCLSCNSTRNO() != null && 
				response.getOTRDCLSCNSTRNO().getTRDCLSCNSEVTZ() != null &&
				response.getOTRDCLSCNSTRNO().getTRDCLSCNSEVTZ().getTRDCLSCNSEVTV() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
}
