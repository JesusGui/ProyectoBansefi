package mx.babel.bansefi.banksystem.base.backends;

import java.util.ArrayList;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.TransaccionalidadBean;
import mx.babel.bansefi.banksystem.base.webservices.consultaperfiltransaccionalmoralestim.ConsultaPerfilTransaccionalMoralEstimServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultaperfiltransaccionalmoralestim.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultaperfiltransaccionalmoralestim.Ejecutar.IPECONSTRANESTIMTRNI;
import mx.babel.bansefi.banksystem.base.webservices.consultaperfiltransaccionalmoralestim.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultaperfiltransaccionalmoralestim.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.wrappers.ClienteWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaPerfilTransaccionalMoralEstimBackEnd extends BackEndBean{

	private static final long serialVersionUID = 4627195348376940874L;
	@Autowired
	ClienteWrapper clienteWrapper;
		
	@Autowired
	ServicioWebUtils servicioWebUtils;

	public TransaccionalidadBean ejecutarTRN(Integer idInterno){
		Ejecutar.IPECONSTRANESTIMTRNI contexto = initPeticion(idInterno);
		EjecutarResult respuesta = ejecutarWS(contexto);
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				TransaccionalidadBean resultado = new TransaccionalidadBean();
				resultado.setTransaccionalidad(new ArrayList<String>());
				return resultado;
			}
		}
		return consultaPerfilTrsnsaccionalMoral(respuesta.getResponseBansefi());
	}

	private TransaccionalidadBean consultaPerfilTrsnsaccionalMoral(ResponseBansefi response) {
		TransaccionalidadBean resultado = new TransaccionalidadBean();		
		if(verificaRespuestaCliente(response)){
			resultado  = this.clienteWrapper.wrappConsultarPerfilTransaccionalEstim(response.getOPECONSTRANESTIMTRNO().getPECONSTRANESTIMEVTZ());
		}
		
		resultado.setMontoAnualIngresos(response.getOPECONSTRANESTIMTRNO().getPECONSTRANESTIMEVTZ().getPEPERSFNTEINGRE().getANUINGRESOS());
		
		return resultado;
	}

	private boolean verificaRespuestaCliente(ResponseBansefi response) {
		Boolean noNulo = false;
		if(response!= null && response.getOPECONSTRANESTIMTRNO() != null){
			noNulo = true;
		}
		return noNulo;
	}

	private EjecutarResult ejecutarWS(IPECONSTRANESTIMTRNI contexto) {
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaPerfilTransaccionalMoralEstimServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de alta de perfil"
					+ "transaccional de clientes.", e);
		}
		return respuesta;
	}

	private IPECONSTRANESTIMTRNI initPeticion(Integer idInterno) {
		
		Ejecutar.IPECONSTRANESTIMTRNI.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.IPECONSTRANESTIMTRNI.STDTRNIPARMV();
		
		Ejecutar.IPECONSTRANESTIMTRNI.PECONSTRANESTIMEVTY perfTransIds = 
				new Ejecutar.IPECONSTRANESTIMTRNI.PECONSTRANESTIMEVTY();
		
		Ejecutar.IPECONSTRANESTIMTRNI perfTrans = new Ejecutar.IPECONSTRANESTIMTRNI();
		
		super.initialize(perfTrans);
		
		contextoEntrada.setCODTX("PGE17CON");
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT(idInterno.toString());
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		perfTrans.setSTDTRNIPARMV(contextoEntrada);
		
		perfTransIds.setCODNRBEEN(super.getEntidad());
		perfTransIds.setIDINTERNOPE(idInterno);
		
		perfTrans.setPECONSTRANESTIMEVTY(perfTransIds);
		return perfTrans;
	}
	

}
