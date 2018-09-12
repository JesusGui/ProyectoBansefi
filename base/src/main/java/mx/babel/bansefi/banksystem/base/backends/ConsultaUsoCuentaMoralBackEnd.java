package mx.babel.bansefi.banksystem.base.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.UsoCuentaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.webservices.consultausoscuentamoral.ConsultaUsosCuentaMoralServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultausoscuentamoral.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultausoscuentamoral.Ejecutar.IPECONSORGUSOCTATRN;
import mx.babel.bansefi.banksystem.base.webservices.consultausoscuentamoral.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultausoscuentamoral.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.wrappers.ClienteWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaUsoCuentaMoralBackEnd extends BackEndBean{

	private static final long serialVersionUID = -1103537414176253250L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	@Autowired
	private ClienteWrapper clienteWrapper; 
	
	/**
	 * Función encargada de consultar el uso de la cuenta de una persona
	 * por medio de servicios web.
	 * 
	 * @param idInterno id de la persona a consultar
	 * @return bean de uso de cuenta
	 */
	public UsoCuentaBean ejecutarTRN(Integer idInterna)
		throws NoControlableException, ControlableException{
		IPECONSORGUSOCTATRN contexto = initPeticion(idInterna);
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
		return getUsoCuenta(respuesta.getResponseBansefi());
	}
	
	/**
	 * Función que construye la lista de cuentas a partir de la respuesta al servidor.
	 * 
	 * @param response
	 * @return
	 */
	private UsoCuentaBean getUsoCuenta(ResponseBansefi response){		
		UsoCuentaBean resultado = null;
		if(verificaRespuestaCliente(response)){
			resultado = clienteWrapper.wrappUsoCuenta(response.getOPECONSORGUSOCTATRN().getPECONSORGUSOCTAEVTZ().getPEPERSUSOCTAE());
		}
		return resultado;
	}
	
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param idInterno Id del cliente de tipo persona física
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.IPECONSORGUSOCTATRN initPeticion(Integer idInterno){
		Ejecutar.IPECONSORGUSOCTATRN contexto = new Ejecutar.IPECONSORGUSOCTATRN();
		
		Ejecutar.IPECONSORGUSOCTATRN.STDTRNIPARMV contextoEntrada = new Ejecutar.IPECONSORGUSOCTATRN.STDTRNIPARMV();
		
		Ejecutar.IPECONSORGUSOCTATRN.PECONSORGUSOCTAEVTY cuerpoContexto = new Ejecutar.IPECONSORGUSOCTATRN.PECONSORGUSOCTAEVTY();
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_PE_CONS_INDV_USO_CTA_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());		
		
		cuerpoContexto.setIDINTERNOPE(idInterno);
		cuerpoContexto.setCODNRBEEN(super.getEntidad());
		contexto.setSTDTRNIPARMV(contextoEntrada);
		contexto.setPECONSORGUSOCTAEVTY(cuerpoContexto);
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.IPECONSORGUSOCTATRN contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaUsosCuentaMoralServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta de uso de cuenta.", e);
		}
		return respuesta;
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
		if(response!= null && response.getOPECONSORGUSOCTATRN() != null &&
				response.getOPECONSORGUSOCTATRN().getPECONSORGUSOCTAEVTZ() != null &&
						response.getOPECONSORGUSOCTATRN().getPECONSORGUSOCTAEVTZ().getPEPERSUSOCTAE() != null){
			noNulo = true;
		}
		return noNulo;
	}	
}
