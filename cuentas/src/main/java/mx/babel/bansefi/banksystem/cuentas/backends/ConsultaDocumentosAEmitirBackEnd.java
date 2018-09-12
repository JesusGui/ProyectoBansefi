package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.beans.EmisionDocumentosBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadocumentosaemitir.ConsultaDocumentosAEmitirServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadocumentosaemitir.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadocumentosaemitir.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadocumentosaemitir.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadocumentosaemitir.ResponseBansefi.OTRDETODOSDOCUACTRN.TRDETODOSDOCUACEVTZ;
import mx.babel.bansefi.banksystem.cuentas.wrappers.EmisionDocumentosWrapper;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back End para consulta de documentos a emitir
 * @author mario.montesdeoca
 *
 */
@Component
public class ConsultaDocumentosAEmitirBackEnd extends BackEndBean{
	
	private static final long serialVersionUID = 2724369119921834566L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	@Autowired
	EmisionDocumentosWrapper emisioDocumentosWrapper;
	
	/**
	 * Mètodo para recuperar los documentos emitidos, formalizados y por emitir de una cuenta
	 * @param numeroCuenta nùmero de la cuenta poseedora de los documentos
	 * @return lista de documentos a emitir
	 */
	public List<EmisionDocumentosBean> ejecutarTRN(Long numeroCuenta){
		
		if(numeroCuenta != null){
			Ejecutar.ITRDETODOSDOCUACTRN contexto = initPeticion(numeroCuenta);
			
			EjecutarResult respuesta = ejecutarWS(contexto);
			
			try{
				super.verificaRespuesta(respuesta);
			}catch(ControlableException ce){
				if(ce.getRtncod() != RETURN_COD_SIN_DATOS){
					throw ce;
				}else{
					return new ArrayList<EmisionDocumentosBean>();
				}			
			}
			
			return getDocumentos(respuesta.getResponseBansefi());
			
		}
		return new ArrayList<EmisionDocumentosBean>();
	}
	
	/**
	 * Mètodo para construir una lista de documentos a emitir a travès de la respuesta del ws
	 * @param response respueta del ws
	 * @return lista de documentos a emitir
	 */
	private List<EmisionDocumentosBean> getDocumentos(ResponseBansefi response){
		List<EmisionDocumentosBean> documentosAEmitir = new ArrayList<EmisionDocumentosBean>();
		if(verificaRespuestaDocumento(response)){
			for (TRDETODOSDOCUACEVTZ documento : response.getOTRDETODOSDOCUACTRN().getTRDETODOSDOCUACEVTZ()) {
				if(!StringUtils.isEmpty(documento.getCODDOC().trim())){
					documentosAEmitir.add(emisioDocumentosWrapper.wrappBean(documento));
				}
			}
		}
		return documentosAEmitir;
	}
	
	/**
	 * Mètodo para inicializar la peticòn al servicio web de consulta de documentos a emitir
	 * @param numeroCuenta Nùmero de cuenta del acuerdo poseedor de los documentos
	 * @return objeto de peticiòn al servicio web
	 */
	private Ejecutar.ITRDETODOSDOCUACTRN initPeticion(Long numeroCuenta){
		Ejecutar.ITRDETODOSDOCUACTRN contexto = new Ejecutar.ITRDETODOSDOCUACTRN();
		Ejecutar.ITRDETODOSDOCUACTRN.TRDETODOSDOCUACEVTY cuerpoContexto = 
				new Ejecutar.ITRDETODOSDOCUACTRN.TRDETODOSDOCUACEVTY();
		Ejecutar.ITRDETODOSDOCUACTRN.TRDETODOSDOCUACEVTY.ACACP datosCuenta =
				new Ejecutar.ITRDETODOSDOCUACTRN.TRDETODOSDOCUACEVTY.ACACP();
		Ejecutar.ITRDETODOSDOCUACTRN.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRDETODOSDOCUACTRN.STDTRNIPARMV();
		contexto.setTRDETODOSDOCUACEVTY(cuerpoContexto);
		cuerpoContexto.setACACP(datosCuenta);
		contexto.setSTDTRNIPARMV(contextoEntrada);
		super.initialize(contexto);
		
		contexto.setSCROLLABLEOCCURS(50);
		contexto.setELEVATORPOSITION(1);
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_DE_TODOS_DOCU_AC_TRN);
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		
		datosCuenta.setCODNRBEEN(super.getEntidad());
		datosCuenta.setNUMSECAC(numeroCuenta);
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRDETODOSDOCUACTRN contexto){
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaDocumentosAEmitirServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "de documentos a emitir.", e);
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
		if(response != null && response.getOTRDETODOSDOCUACTRN() != null && 
				response.getOTRDETODOSDOCUACTRN().getTRDETODOSDOCUACEVTZ() != null &&
				!response.getOTRDETODOSDOCUACTRN().getTRDETODOSDOCUACEVTZ().isEmpty()){
			noNulo = true;
		}
		return noNulo;
	}
	
}
