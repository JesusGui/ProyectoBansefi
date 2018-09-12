package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.beans.EmisionDocumentosBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.emisiondocumentos.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.emisiondocumentos.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.emisiondocumentos.EmisionDocumentosServicio;
import mx.babel.bansefi.banksystem.cuentas.wrappers.EmisionDocumentosWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back End para emitir un documento
 * @author mario.montesdeoca
 *
 */
@Component
public class EmisionDocumentosBackEnd extends BackEndBean{
	
	private static final long serialVersionUID = -3979053319137218370L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	@Autowired
	EmisionDocumentosWrapper emisioDocumentosWrapper;
	
	/**
	 * Método que emite un documento
	 * @param documento a emitir
	 * @param numeroCuenta poseedora del documento
	 * @return <code>true</code> si se emitío el documento con exito
	 */
	public Boolean ejecutarTRN(EmisionDocumentosBean documento, CuentaBean cuenta){
		try{
			Ejecutar.ITRDEEMITIRCDOTRNI contexto = initPeticion(documento, cuenta);
		
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
			
			emisioDocumentosWrapper.wrappBean(respuesta.getResponseBansefi().getOTRDEEMITIRCDOTRNO().getTRDEEMITIRCDOEVTZ(),documento);
			return true;
			
		}catch(NoControlableException nce){
			return false;
		}
	}	
	
	/**
	 * Método para inicializar el objeto de petición para el servicio web
	 * @param documento bean con detalles del documento
	 * @param numeroCuenta poseedora del documento
	 * @return objeto de petición para el servicio web
	 */
	public Ejecutar.ITRDEEMITIRCDOTRNI initPeticion(EmisionDocumentosBean documento, CuentaBean cuenta) throws NoControlableException{
		Ejecutar.ITRDEEMITIRCDOTRNI contexto = new Ejecutar.ITRDEEMITIRCDOTRNI();
		Ejecutar.ITRDEEMITIRCDOTRNI.STDTRNIPARMV cuerpoContexto = 
				new Ejecutar.ITRDEEMITIRCDOTRNI.STDTRNIPARMV();
		Ejecutar.ITRDEEMITIRCDOTRNI.TRDEEMITIRCDOEVTY datosDocumento = 
				new Ejecutar.ITRDEEMITIRCDOTRNI.TRDEEMITIRCDOEVTY();
		Ejecutar.ITRDEEMITIRCDOTRNI.TRDEEMITIRCDOEVTY.ACACP acuerdo =
				new Ejecutar.ITRDEEMITIRCDOTRNI.TRDEEMITIRCDOEVTY.ACACP();
		Ejecutar.ITRDEEMITIRCDOTRNI.TRDEEMITIRCDOEVTY.DEDOCEMTDOP datos =
				new Ejecutar.ITRDEEMITIRCDOTRNI.TRDEEMITIRCDOEVTY.DEDOCEMTDOP();
		Ejecutar.ITRDEEMITIRCDOTRNI.TRDEEMITIRCDOEVTY.CODECVACV estadoAcuerdo = 
				new Ejecutar.ITRDEEMITIRCDOTRNI.TRDEEMITIRCDOEVTY.CODECVACV();
		Ejecutar.ITRDEEMITIRCDOTRNI.TRDEEMITIRCDOEVTY.CODNUMRCOMONEDAV moneda =
				new Ejecutar.ITRDEEMITIRCDOTRNI.TRDEEMITIRCDOEVTY.CODNUMRCOMONEDAV();
		Ejecutar.ITRDEEMITIRCDOTRNI.TRDEEMITIRCDOEVTY.FOFORMLRP formulario =
				new Ejecutar.ITRDEEMITIRCDOTRNI.TRDEEMITIRCDOEVTY.FOFORMLRP();
		Ejecutar.ITRDEEMITIRCDOTRNI.TRDEEMITIRCDOEVTY.FECHAFORMALIZV formalizacion = 
				new Ejecutar.ITRDEEMITIRCDOTRNI.TRDEEMITIRCDOEVTY.FECHAFORMALIZV();
		contexto.setSTDTRNIPARMV(cuerpoContexto);
		contexto.setTRDEEMITIRCDOEVTY(datosDocumento);
		datosDocumento.setACACP(acuerdo);
		datosDocumento.setCODECVACV(estadoAcuerdo);
		datosDocumento.setCODNUMRCOMONEDAV(moneda);
		datosDocumento.setDEDOCEMTDOP(datos);
		datosDocumento.setFOFORMLRP(formulario);
		datosDocumento.setFECHAFORMALIZV(formalizacion);
		super.initialize(contexto);
		
		acuerdo.setCODNRBEEN(super.getEntidad());
		acuerdo.setNUMSECAC(cuenta.getNumeroCuenta());
		
		datos.setCODNRBEEN(super.getEntidad());
		try{
			datos.setIDINTERNODC(documento.getIdInterno());
		}catch(NullPointerException npe){
			throw new NoControlableException("Error al invocar servicio web de emisión "
					+ "de documentos.","Id de documento no debe ser null.");
		}
		estadoAcuerdo.setCODECVAC(cuenta.getEstado());
		
		moneda.setCODNUMRCOMONEDA(ConstantesFuncionales.COD_MONEDA);
		
		formulario.setCODNRBEEN(super.getEntidad());
		formulario.setCODDOC(documento.getCodigo());
		formulario.setCODIDIOMPERS(documento.getIdioma());
		formulario.setNUMSECFO(documento.getFormulario());
		
		IntegerToDateConverter converter = new IntegerToDateConverter();
		formalizacion.setFECHAFORMALIZ(converter.convertFrom(documento.getFechaFormalizacion(), null));
		
		datosDocumento.setNUMCOPIAS(documento.getNumCopias());
		datosDocumento.setNOMBREFICHERODO(documento.getFichero());
		
		cuerpoContexto.setIDINTERNOTERMTN(super.getTerminal());
		cuerpoContexto.setCODTX(CodTxConstants.COD_TX_TR_DE_EMITIR_CDO_TRN);
		return contexto;		
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRDEEMITIRCDOTRNI contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					EmisionDocumentosServicio.class,contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de emisión "
					+ "de documentos.", e);
		}
		return respuesta;
	}
}
