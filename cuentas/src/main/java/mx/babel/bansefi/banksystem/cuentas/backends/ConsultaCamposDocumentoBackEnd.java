package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.beans.CampoDocumentoBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacamposdocumento.ConsultaCamposDocumentoServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacamposdocumento.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacamposdocumento.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacamposdocumento.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacamposdocumento.ResponseBansefi.OTRDECONSDATOSENTTRN.TRDECONSDATOSENTEVT.DEDATOSRLAPV;
import mx.babel.bansefi.banksystem.cuentas.wrappers.EmisionDocumentosWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back End para consultar los campos asociados a un documento
 * @author mario.montesdeoca
 *
 */
@Component
public class ConsultaCamposDocumentoBackEnd extends BackEndBean{
	
	private static final long serialVersionUID = -840104269784531794L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	@Autowired
	EmisionDocumentosWrapper emisioDocumentosWrapper;
	
	/**
	 * Método para consultar los campos de un documento
	 * @param idDocumento id del documento poseedor de los campos
	 * @return lista de campos asociados a un documento
	 */
	public List<CampoDocumentoBean> ejecutarTRN(Integer idDocumento){
		
		if(idDocumento != null){
			Ejecutar.ITRDECONSDATOSENTTRN contexto = initPeticion(idDocumento);
			
			EjecutarResult respuesta = ejecutarWS(contexto);
			
			try{
	            super.verificaRespuesta(respuesta);
	        }catch (final ControlableException ce){
	            if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
	                throw ce;
	            }else{
	                return new ArrayList<CampoDocumentoBean>();
	            }
	        }
			
			return getCampos(respuesta.getResponseBansefi());
			
		}
		return new ArrayList<CampoDocumentoBean>();
	}
	
	/**
	 * Método para construir una lista de campos de un documento
	 * @param response respuesta de servicio web
	 * @return lista de campos asociados a un documento
	 */
	public List<CampoDocumentoBean> getCampos(ResponseBansefi response){
		List<CampoDocumentoBean> campos = new ArrayList<CampoDocumentoBean>();		
		if(verificaRespuestaCampos(response)){
			for(int i = 0;i<response.getOTRDECONSDATOSENTTRN().getNUMBEROFRECORDS(); i++){
				DEDATOSRLAPV campo = response.getOTRDECONSDATOSENTTRN().getTRDECONSDATOSENTEVT().getDEDATOSRLAPV().get(i);
				campos.add(emisioDocumentosWrapper.wrappBean(campo));
			}
		}		
		
		return campos;
	}
	
	/**
	 * Método para inicializar el objeto de petición al servicio web
	 * @param idDocumento id del documento poseedor de los campos
	 * @return objeto para petición al servicio web
	 */
	public Ejecutar.ITRDECONSDATOSENTTRN initPeticion(Integer idDocumento){
		Ejecutar.ITRDECONSDATOSENTTRN contexto = new Ejecutar.ITRDECONSDATOSENTTRN();
		Ejecutar.ITRDECONSDATOSENTTRN.STDTRNIPARMV cuerpoContexto = 
				new Ejecutar.ITRDECONSDATOSENTTRN.STDTRNIPARMV();
		Ejecutar.ITRDECONSDATOSENTTRN.TRDECONSDATOSENTEVT datosEntrada = 
				new Ejecutar.ITRDECONSDATOSENTTRN.TRDECONSDATOSENTEVT();
		Ejecutar.ITRDECONSDATOSENTTRN.TRDECONSDATOSENTEVT.DERLAPP documento = 
				new Ejecutar.ITRDECONSDATOSENTTRN.TRDECONSDATOSENTEVT.DERLAPP();
		contexto.setSTDTRNIPARMV(cuerpoContexto);
		contexto.setTRDECONSDATOSENTEVT(datosEntrada);
		datosEntrada.setDERLAPP(documento);
		super.initialize(contexto);
		
		documento.setIDINTERNODC(idDocumento);
		documento.setCODNRBEEN(super.getEntidad());
		
		cuerpoContexto.setCODTX(CodTxConstants.COD_TX_TR_DE_CONS_DATOS_ENT_TRN);
		cuerpoContexto.setIDINTERNOTERMTN(super.getTerminal());
		
		contexto.setSCROLLABLEOCCURS(50);
		contexto.setELEVATORPOSITION(1);
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	public EjecutarResult ejecutarWS(Ejecutar.ITRDECONSDATOSENTTRN contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaCamposDocumentoServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "de campos de documento.", e);
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
	private Boolean verificaRespuestaCampos(ResponseBansefi response){
		Boolean noNulo = false;
		if(response != null && response.getOTRDECONSDATOSENTTRN() != null && 
				response.getOTRDECONSDATOSENTTRN().getTRDECONSDATOSENTEVT() != null &&
				response.getOTRDECONSDATOSENTTRN().getTRDECONSDATOSENTEVT().getDEDATOSRLAPV() != null &&
				!response.getOTRDECONSDATOSENTTRN().getTRDECONSDATOSENTEVT().getDEDATOSRLAPV().isEmpty()){
			noNulo = true;
		}
		return noNulo;
	}
}
