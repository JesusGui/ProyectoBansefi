package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.beans.EmisionDocumentosBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.anuladocumento.AnulaDocumentoServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.anuladocumento.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.anuladocumento.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.anuladocumento.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.EmisionDocumentosWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back End para anular documentos
 * @author mario.montesdeoca
 *
 */
@Component
public class AnulaDocumentoBackEnd extends BackEndBean{

	private static final long serialVersionUID = -3147414772953671516L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	@Autowired
	EmisionDocumentosWrapper emisioDocumentosWrapper;
	
	/**
	 * Método que anula un documento
	 * @param documento a anular
	 * @param numeroCuenta poseedora del documento
	 * @return <code>true</code> si se anuló el documento con exito
	 */
	public Boolean ejecutarTRN(EmisionDocumentosBean documento, Long numeroCuenta){
		Ejecutar.ITRDECANCELARTRNI contexto = initPeticion(documento, numeroCuenta);
		
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
            super.verificaRespuesta(respuesta);
        }catch (final ControlableException ce){
            if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
                throw ce;
            }else{
                return false;
            }
        }
		
		return getRespuesta(respuesta.getResponseBansefi(), documento);
	}
	
	/**
	 * Método que verifica que la respuesta sea de éxito o no
	 * @param respuesta respuesta del servicio web
	 * @param documento que se anuló
	 * @return <code>true</code> si se anuló el documento con exito
	 */
	public Boolean getRespuesta(ResponseBansefi respuesta, EmisionDocumentosBean documento){
		if(verificaRespuestaDocumento(respuesta)){
			return true;
		}
		return false;
	}
	
	/**
	 * Método para inicializar el objeto de petición para el servicio web
	 * @param documento bean con detalles del documento
	 * @param numeroCuenta poseedora del documento
	 * @return objeto de petición para el servicio web
	 */
	public Ejecutar.ITRDECANCELARTRNI initPeticion(EmisionDocumentosBean documento, Long numeroCuenta){
		Ejecutar.ITRDECANCELARTRNI contexto = new Ejecutar.ITRDECANCELARTRNI();
		Ejecutar.ITRDECANCELARTRNI.STDTRNIPARMV cuerpoContexto = 
				new Ejecutar.ITRDECANCELARTRNI.STDTRNIPARMV();
		Ejecutar.ITRDECANCELARTRNI.TRDECANCELAREVTY datosDocumento = 
				new Ejecutar.ITRDECANCELARTRNI.TRDECANCELAREVTY();
		Ejecutar.ITRDECANCELARTRNI.TRDECANCELAREVTY.DEDOCEMTDOE datos =
				new Ejecutar.ITRDECANCELARTRNI.TRDECANCELAREVTY.DEDOCEMTDOE();
		Ejecutar.ITRDECANCELARTRNI.TRDECANCELAREVTY.NUMACRLDEV acuerdo =
				new Ejecutar.ITRDECANCELARTRNI.TRDECANCELAREVTY.NUMACRLDEV();
		contexto.setSTDTRNIPARMV(cuerpoContexto);
		contexto.setTRDECANCELAREVTY(datosDocumento);
		datosDocumento.setDEDOCEMTDOE(datos);
		datosDocumento.setNUMACRLDEV(acuerdo);
		super.initialize(contexto);
		
		contexto.setSCROLLABLEOCCURS(50);
		contexto.setELEVATORPOSITION(1);
		
		acuerdo.setCODNRBEEN(super.getEntidad());
		acuerdo.setNUMSECAC(numeroCuenta);
		
		emisioDocumentosWrapper.wrappBean(documento, datos);
		datos.setCODNRBEEN(super.getEntidad());
		
		cuerpoContexto.setIDINTERNOTERMTN(super.getTerminal());
		cuerpoContexto.setCODTX(CodTxConstants.COD_TX_TR_DE_CANCELAR_TRN);
		return contexto;		
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRDECANCELARTRNI contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					AnulaDocumentoServicio.class,contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de anular "
					+ "documento.", e);
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
		if(response != null && response.getOTRDECANCELARTRNO() != null && 
				response.getOTRDECANCELARTRNO().getTRDECANCELAREVTZ() != null &&
				response.getOTRDECANCELARTRNO().getTRDECANCELAREVTZ().getDEDOCEMTDOE() != null){
			noNulo = true;
		}
		return noNulo;
	}	
}
