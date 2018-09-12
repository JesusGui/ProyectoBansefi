package mx.babel.bansefi.banksystem.cuentas.wrappers;

import mx.babel.bansefi.banksystem.cuentas.beans.CampoDocumentoBean;
import mx.babel.bansefi.banksystem.cuentas.beans.EmisionDocumentosBean;
import mx.babel.bansefi.banksystem.cuentas.beans.EstadoDocumentoBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.anuladocumento.Ejecutar.ITRDECANCELARTRNI.TRDECANCELAREVTY;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacamposdocumento.ResponseBansefi.OTRDECONSDATOSENTTRN.TRDECONSDATOSENTEVT.DEDATOSRLAPV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadocumentosaemitir.ResponseBansefi.OTRDETODOSDOCUACTRN.TRDETODOSDOCUACEVTZ;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaestadosdocumento.ResponseBansefi.OTRDECONSECVSTRNO.TRDECONSECVSEVTZ;
import mx.babel.bansefi.banksystem.cuentas.webservices.emisiondocumentos.ResponseBansefi.OTRDEEMITIRCDOTRNO.TRDEEMITIRCDOEVTZ;
import mx.babel.bansefi.banksystem.cuentas.webservices.formalizadocumento.Ejecutar.ITRDEFORMALIZARTRNI.TRDEFORMALIZAREVTY;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase para mapear objetos relacionados a emisón de documentos.
 * @author mario.montesdeoca
 *
 */
@Component
public class EmisionDocumentosWrapper {

	@Autowired
	DozerBeanMapper dozerBeanMapper;
	
	/**
	 * Wrapper para consulta de documentos a emitir.
	 * @param TRDETODOSDOCUACEVTZ con datos de la respuesta del ws
	 * @return Bean de emision de documentos
	 */
	public EmisionDocumentosBean wrappBean(TRDETODOSDOCUACEVTZ documento){
		Mapper mapper = dozerBeanMapper;
		return mapper.map(documento, EmisionDocumentosBean.class,"consultaDocumentosAEmitir");
	}
	
	/**
	 * Wrapper para consulta de campos de documentos a emitir.
	 * @param TRDETODOSDOCUACEVTZ con datps del campo
	 * @return Bean campo de documento
	 */
	public CampoDocumentoBean wrappBean(DEDATOSRLAPV campo){
		Mapper mapper = dozerBeanMapper;
		return mapper.map(campo, CampoDocumentoBean.class,"consultaCamposDocumento");
	}
	
	/**
	 * Wrapper para estados de documento a emitir.
	 * @param TRDETODOSDOCUACEVTZ con datos del estado
	 * @return Bean estado del documento
	 */
	public EstadoDocumentoBean wrappBean(TRDECONSECVSEVTZ estado){
		Mapper mapper = dozerBeanMapper;
		return mapper.map(estado, EstadoDocumentoBean.class,"consultaEstadosDocumento");
	}
	
	/**
	 * Método para mapear campos de un documento después de ser emitido.
	 * @param emision respuesta de servicio web de emisión
	 * @param documento documento que se emitió
	 */
	public void wrappBean(TRDEEMITIRCDOEVTZ emision, EmisionDocumentosBean documento){
		Mapper mapper = dozerBeanMapper;
		mapper.map(emision, documento, "documentoEmitido");
	}
	
	/**
	 * Método para mapear campos de un documento hacia una peticiòn de 
	 * formalizar documento.
	 * @param documento bean a ser formalizarlo
	 * @param peticion que serà enviada a ws
	 */
	public void wrappBean(EmisionDocumentosBean documento, TRDEFORMALIZAREVTY.DEDOCEMTDOE peticion){
		Mapper mapper = dozerBeanMapper;
		mapper.map(documento, peticion,"peticionFormalizaDocumento");
	}
	
	/**
	 * Método para mapear campos de un documento hacia una peticiòn de 
	 * anular documento.
	 * @param documento bean a ser anulado
	 * @param peticion que serà enviada a ws
	 */
	public void wrappBean(EmisionDocumentosBean documento, TRDECANCELAREVTY.DEDOCEMTDOE peticion){
		Mapper mapper = dozerBeanMapper;
		mapper.map(documento, peticion,"peticionAnularDocumento");
	}
}
