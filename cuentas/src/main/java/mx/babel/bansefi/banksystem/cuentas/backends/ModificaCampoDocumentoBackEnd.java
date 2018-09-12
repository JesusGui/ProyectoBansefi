package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.beans.CampoDocumentoBean;
import mx.babel.bansefi.banksystem.cuentas.beans.EmisionDocumentosBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacampodocumento.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacampodocumento.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacampodocumento.ModificaCampoDocumentoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back End de actualización de campos
 * @author mario.montesdeoca
 *
 */
@Component
public class ModificaCampoDocumentoBackEnd extends BackEndBean{

	private static final long serialVersionUID = 7893772961847392820L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * método para modificar los campos de un documento
	 * @param documento documento poosedor del campo
	 * @param campo el cual se va a modificar
	 * @return <code>true</code>  si la actualización se realizó con exito
	 */
	public Boolean ejecutarTRN(EmisionDocumentosBean documento, CampoDocumentoBean campo){
		Ejecutar.ITRDEMODIDATOSENTTRN contexto = initPeticion(documento, campo);
		
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
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	public EjecutarResult ejecutarWS(Ejecutar.ITRDEMODIDATOSENTTRN contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ModificaCampoDocumentoServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de modifica "
					+ "campos de documento.", e);
		}
		return respuesta;
	}
	
	/**
	 * Método para inicializar el objeto de petición para el servicio web
	 * @param documento bean con detalles del documento
	 * @param campo bean con detalles del campo
	 * @return objeto de petición para el servicio web
	 */
	private Ejecutar.ITRDEMODIDATOSENTTRN initPeticion(EmisionDocumentosBean documento, CampoDocumentoBean campo){
		Ejecutar.ITRDEMODIDATOSENTTRN contexto = new Ejecutar.ITRDEMODIDATOSENTTRN();
		Ejecutar.ITRDEMODIDATOSENTTRN.STDTRNIPARMV cuerpoContexto =
				new Ejecutar.ITRDEMODIDATOSENTTRN.STDTRNIPARMV();
		Ejecutar.ITRDEMODIDATOSENTTRN.TRDEMODIDATOSENTEVT detalleCampo =
				new Ejecutar.ITRDEMODIDATOSENTTRN.TRDEMODIDATOSENTEVT();
		Ejecutar.ITRDEMODIDATOSENTTRN.TRDEMODIDATOSENTEVT.DERLAPP documentoCampo = 
				new Ejecutar.ITRDEMODIDATOSENTTRN.TRDEMODIDATOSENTEVT.DERLAPP();
		contexto.setSTDTRNIPARMV(cuerpoContexto);
		contexto.setTRDEMODIDATOSENTEVT(detalleCampo);
		detalleCampo.setDERLAPP(documentoCampo);
		super.initialize(contexto);
		
		documentoCampo.setCODNRBEEN(super.getEntidad());
		documentoCampo.setIDINTERNODC(documento.getIdInterno());
		documentoCampo.setCODAPARTADO(campo.getApartado());
		documentoCampo.setCODORGAPDO(campo.getCodigoApartado());
		documentoCampo.setNUMSECAPDO(campo.getSeccionApartado());
		documentoCampo.setCODCAMPO(campo.getCodigo());
		
		detalleCampo.setVALORASOCIADO(campo.getValor());
		detalleCampo.setVALORASOCIADOLEN(campo.getValor().length());
		detalleCampo.setCODOBTENCKD("ENT");
		
		cuerpoContexto.setIDINTERNOTERMTN(super.getTerminal());
		cuerpoContexto.setCODTX(CodTxConstants.COD_TX_TR_DE_MODI_DATOS_ENT_TRN);
		
		return contexto;
	}	
}
