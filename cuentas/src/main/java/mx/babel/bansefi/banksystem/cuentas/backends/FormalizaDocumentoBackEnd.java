package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.beans.EmisionDocumentosBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.formalizadocumento.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.formalizadocumento.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.formalizadocumento.FormalizaDocumentoServicio;
import mx.babel.bansefi.banksystem.cuentas.wrappers.EmisionDocumentosWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back End de formalizar documentos
 * @author mario.montesdeoca
 *
 */
@Component
public class FormalizaDocumentoBackEnd extends BackEndBean{
	
	private static final long serialVersionUID = 699929315213928992L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	@Autowired
	EmisionDocumentosWrapper emisioDocumentosWrapper;

	/**
	 * Método que formaliza un documento
	 * @param documento a formalizar
	 * @param numeroCuenta poseedora del documento
	 * @return <code>true</code> si se formalizó el documento con exito
	 */
	public Boolean ejecutarTRN(EmisionDocumentosBean documento, Long numeroCuenta){
		Ejecutar.ITRDEFORMALIZARTRNI contexto = initPeticion(documento, numeroCuenta);
		
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return false;
			}
		}	
		return true;
	}
		
	/**
	 * Método para inicializar el objeto de petición para el servicio web
	 * @param documento bean con detalles del documento
	 * @param numeroCuenta poseedora del documento
	 * @return objeto de petición para el servicio web
	 */
	public Ejecutar.ITRDEFORMALIZARTRNI initPeticion(EmisionDocumentosBean documento, Long numeroCuenta){
		Ejecutar.ITRDEFORMALIZARTRNI contexto = new Ejecutar.ITRDEFORMALIZARTRNI();
		Ejecutar.ITRDEFORMALIZARTRNI.STDTRNIPARMV cuerpoContexto = 
				new Ejecutar.ITRDEFORMALIZARTRNI.STDTRNIPARMV();
		Ejecutar.ITRDEFORMALIZARTRNI.TRDEFORMALIZAREVTY datosDocumento = 
				new Ejecutar.ITRDEFORMALIZARTRNI.TRDEFORMALIZAREVTY();
		Ejecutar.ITRDEFORMALIZARTRNI.TRDEFORMALIZAREVTY.DEDOCEMTDOE datos =
				new Ejecutar.ITRDEFORMALIZARTRNI.TRDEFORMALIZAREVTY.DEDOCEMTDOE();
		Ejecutar.ITRDEFORMALIZARTRNI.TRDEFORMALIZAREVTY.NUMACRLDEV acuerdo =
				new Ejecutar.ITRDEFORMALIZARTRNI.TRDEFORMALIZAREVTY.NUMACRLDEV();
		contexto.setSTDTRNIPARMV(cuerpoContexto);
		contexto.setTRDEFORMALIZAREVTY(datosDocumento);
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
		cuerpoContexto.setCODTX(CodTxConstants.COD_TX_TR_DE_FORMALIZAR_TRN);
		return contexto;		
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRDEFORMALIZARTRNI contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					FormalizaDocumentoServicio.class,contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de formalización "
					+ "de documentos.", e);
		}
		return respuesta;
	}
	
}
