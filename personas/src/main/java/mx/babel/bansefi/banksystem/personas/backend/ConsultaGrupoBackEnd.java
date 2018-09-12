package mx.babel.bansefi.banksystem.personas.backend;

import java.util.ArrayList;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.backends.ConsultaPosicionBackEnd;
import mx.babel.bansefi.banksystem.base.beans.domain.ClienteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClienteGrupoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.webservices.consultagrupo.ConsultaGrupoServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultagrupo.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultagrupo.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultagrupo.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.wrappers.ClienteWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para consultas de clientes de tipo grupo.
 * 
 * @author mario.montesdeoca
 *
 */
@Component
public class ConsultaGrupoBackEnd extends BackEndBean{
	
	private static final long serialVersionUID = -3721756606148496129L;

	@Autowired
	ClienteWrapper clienteWrapper;
	
	@Autowired
	ConsultaPosicionBackEnd clienteConsultaIntegrantesGrupo;
	
    @Autowired
    ServicioWebUtils servicioWebUtils;

	/**
	 * Función para obtener los datos de un cliente de tipo grupo invocando un 
	 * servicio web.
	 * 
	 * @param idExterno Id externo del cliente de tipo grupo.
	 * @return Objeto con atributos del grupo consultado.
	 */
	public ClienteBean ejecutarTRN(String idExterno, String codigoGrupo){
		Ejecutar.ITRGRCONSTRNI contexto = initPeticion(idExterno, codigoGrupo);
		
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		ClienteGrupoBean clienteGrupo  = null;
		
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return clienteGrupo;
			}
		}
		
		if(verificaResponseBansefi(respuesta)){
			clienteGrupo = getCliente(respuesta.getResponseBansefi());
		}
		return clienteGrupo;
	}
	
	/**
	 * Funciòn encargada de crear un objeto de tipo grupo a través de la 
	 * respuesta obetenida del servicio web.
	 * 
	 * @param response Respuesta del servicio web
	 * @return Objeto de tipo grupo
	 */
	private ClienteGrupoBean getCliente(ResponseBansefi response){
		ClienteGrupoBean clienteGrupo  = null;		
		if(verificaRespuestaGrupo(response)){
			ResponseBansefi.OTRGRCONSTRNO.TRGRCONSEVTZ cliente = 
				response.getOTRGRCONSTRNO().getTRGRCONSEVTZ();
			
			clienteGrupo = clienteWrapper.wrappBean(cliente);
			try{
				clienteConsultaIntegrantesGrupo.ejecutarTRN(clienteGrupo,null);
			}catch(ControlableException| NoControlableException ce){
				clienteGrupo.setIntegrantes(new ArrayList<ClienteBean>());
			}
		}
		return clienteGrupo;
	}
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param idExterno Id del cliente de tipo grupo
	 * @return Objeto de petición al servicio web
	 */
	private Ejecutar.ITRGRCONSTRNI initPeticion(String idExterno, String codigoGrupo){
		Ejecutar.ITRGRCONSTRNI contexto = new Ejecutar.ITRGRCONSTRNI();
		Ejecutar.ITRGRCONSTRNI.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRGRCONSTRNI.STDTRNIPARMV();
		Ejecutar.ITRGRCONSTRNI.TRGRCONSEVTY cuerpoContexto =
				new Ejecutar.ITRGRCONSTRNI.TRGRCONSEVTY();
		Ejecutar.ITRGRCONSTRNI.TRGRCONSEVTY.GRGRPP grupo = 
				new Ejecutar.ITRGRCONSTRNI.TRGRCONSEVTY.GRGRPP();
		
		grupo.setCODNRBEEN(super.getEntidad());
		grupo.setCODGRP(codigoGrupo);
		grupo.setIDEXTGR(idExterno);
		
		cuerpoContexto.setGRGRPP(grupo);
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_GR_CONS_TRN);
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		
		contexto.setSTDTRNIPARMV(contextoEntrada);
		contexto.setTRGRCONSEVTY(cuerpoContexto);
		super.initialize(contexto);
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRGRCONSTRNI contexto){
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaGrupoServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta de "
					+ "grupos.", e);
		}
		return respuesta;
	}
	
	/**
	 * Función que valida que la respues del servidor no este vacía. 
	 *  
	 * @param respuesta Objeto respuesta del servicio web
	 * @return <code>true</code> si la respuesta no esta vacía.
	 */
	private Boolean verificaResponseBansefi(EjecutarResult respuesta){
		Boolean noNulo = false;
		if(respuesta != null && respuesta.getResponseBansefi() != null ){
			noNulo = true;
		}
		return noNulo;
	}
	
	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con los datos
	 * del grupo.
	 * 
	 * @param response Respuesta Bansefi con datos del grupo
	 * @return <code>true</code> si la respuesta Bansefi contiene un objeto de tipo grupo
	 */
	private Boolean verificaRespuestaGrupo(ResponseBansefi response){
		Boolean noNulo = false;
		if(response!= null && response.getOTRGRCONSTRNO() != null &&
				response.getOTRGRCONSTRNO().getTRGRCONSEVTZ() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
}
