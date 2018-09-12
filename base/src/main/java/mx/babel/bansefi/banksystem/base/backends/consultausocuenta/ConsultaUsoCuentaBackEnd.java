package mx.babel.bansefi.banksystem.base.backends.consultausocuenta;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.UsoCuentaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.webservices.consultausocuenta.ConsultaUsoCuentaServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultausocuenta.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultausocuenta.Ejecutar.IPECONSINDVUSOCTATRN;
import mx.babel.bansefi.banksystem.base.webservices.consultausocuenta.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultausocuenta.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.wrappers.CedulaConocimientoWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para consulta del uso de la cuenta a una persona en especifico
 * 
 * @author javier.martinnino
 *
 */
@Component
public class ConsultaUsoCuentaBackEnd extends BackEndBean{

	private static final long serialVersionUID = -2553908605781732432L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	@Autowired
	private CedulaConocimientoWrapper cedulaConocimientoWrapper; 
	
	/**
	 * Función encargada de consultar el uso de la cuenta de una persona
	 * por medio de servicios web.
	 * 
	 * @param idInterno id de la persona a consultar
	 * @return bean de uso de cuenta
	 */
	public UsoCuentaBean ejecutarTRN(Integer idInterno)
		throws NoControlableException, ControlableException{
		IPECONSINDVUSOCTATRN contexto = initPeticion(idInterno);
		EjecutarResult respuesta = ejecutarWS(contexto);
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return null;
			}
		}
		if(verificaResponseBansefi(respuesta)){
			return getUsoCuenta(respuesta.getResponseBansefi());
		}
		return null;
	}
	
	/**
	 * Función que construye la consulta del uso de cuenta a partir de la respuesta al servidor.
	 * 
	 * @param response
	 * @return
	 */
	private UsoCuentaBean getUsoCuenta(ResponseBansefi response){
		UsoCuentaBean resultado = null;		
		if(verificaRespuestaCliente(response)){
			resultado= cedulaConocimientoWrapper.wrappUsoCuenta(response.getOPECONSINDVUSOCTATRN().getPECONSINDVUSOCTAEVT().getPEPERSUSOCTAE());
		}
		return resultado;
	}
	
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param idInterno Id del cliente de tipo persona física
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.IPECONSINDVUSOCTATRN initPeticion(Integer idInterno){
		Ejecutar.IPECONSINDVUSOCTATRN contexto = new Ejecutar.IPECONSINDVUSOCTATRN();
		
		Ejecutar.IPECONSINDVUSOCTATRN.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.IPECONSINDVUSOCTATRN.STDTRNIPARMV();
		
		Ejecutar.IPECONSINDVUSOCTATRN.PECONSINDVUSOCTAEVT cuerpoContexto =
				new Ejecutar.IPECONSINDVUSOCTATRN.PECONSINDVUSOCTAEVT();
		
		Ejecutar.IPECONSINDVUSOCTATRN.PECONSINDVUSOCTAEVT.PEPERSUSOCTAE usoCuentaE =
				new Ejecutar.IPECONSINDVUSOCTATRN.PECONSINDVUSOCTAEVT.PEPERSUSOCTAE();
		
		Ejecutar.IPECONSINDVUSOCTATRN.PECONSINDVUSOCTAEVT.PEPERSUSOCTAP usoCuentaP =
				new Ejecutar.IPECONSINDVUSOCTATRN.PECONSINDVUSOCTAEVT.PEPERSUSOCTAP();
		
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_PE_CONS_INDV_USO_CTA_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());		
		
		usoCuentaP.setCODNRBEEN(super.getEntidad());
		usoCuentaP.setIDINTERNOPE(idInterno);
				
		super.initialize(usoCuentaE);
		
		contexto.setPECONSINDVUSOCTAEVT(cuerpoContexto);
		contexto.getPECONSINDVUSOCTAEVT().setPEPERSUSOCTAE(usoCuentaE);
		contexto.getPECONSINDVUSOCTAEVT().setPEPERSUSOCTAP(usoCuentaP);
		
		contexto.setSTDTRNIPARMV(contextoEntrada);
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.IPECONSINDVUSOCTATRN contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaUsoCuentaServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta de "
					+ "uso de cuenta.", e);
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
	 * Función que valida que la respuesta Bansefi contenga un objeto con los datos
	 * de la persona física.
	 * 
	 * @param response Respuesta Bansefi con datos de la persona moral
	 * @return <code>true</code> si la respuesta Bansefi contiene elemento no nulos
	 */
	private Boolean verificaRespuestaCliente(ResponseBansefi response){
		Boolean noNulo = false;
		if(response!= null && response.getOPECONSINDVUSOCTATRN() != null &&
				response.getOPECONSINDVUSOCTATRN().getPECONSINDVUSOCTAEVT() != null &&
						response.getOPECONSINDVUSOCTATRN().getPECONSINDVUSOCTAEVT().getPEPERSUSOCTAE() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
}
