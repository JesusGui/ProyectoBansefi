package mx.babel.bansefi.banksystem.personas.backend;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.PersonaMoralFuncionarioBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.personas.webservices.altaaccionistasfuncionarios.AltaAccionistasFuncionariosServicio;
import mx.babel.bansefi.banksystem.personas.webservices.altaaccionistasfuncionarios.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.altaaccionistasfuncionarios.Ejecutar.IPEALTAORGACCFUNTRN;
import mx.babel.bansefi.banksystem.personas.webservices.altaaccionistasfuncionarios.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.altaaccionistasfuncionarios.ResponseBansefi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AltaPersonaMoralFuncionarioBackEnd extends BackEndBean{

	private static final long serialVersionUID = 1068821821288200906L;
	
	@Autowired
    ServicioWebUtils servicioWebUtils;

	
	public Integer ejecutarTRN(PersonaMoralFuncionarioBean funcionarioBean, int idInterno)
			throws NoControlableException, ControlableException{
			Ejecutar.IPEALTAORGACCFUNTRN contexto = initPeticion(funcionarioBean, idInterno);
			EjecutarResult respuesta = ejecutarWS(contexto);
			super.verificaRespuesta(respuesta);
			if(verificaResponseBansefi(respuesta)){
				return altafuncionario(respuesta.getResponseBansefi());
			}
			return null;
		}


	private Integer altafuncionario(ResponseBansefi response) {
		Integer resultado = null;
		if(verificaRespuestaCliente(response)){
			resultado = response.getOPEALTAORGACCFUNTRN().getRTRNCD();
		}		
		return resultado;
	}


	private boolean verificaRespuestaCliente(ResponseBansefi response) {
		Boolean noNulo = false;
		if(response!= null && response.getOPEALTAORGACCFUNTRN() != null){
			noNulo = true;
		}
		return noNulo;
	}

	private boolean verificaResponseBansefi(EjecutarResult respuesta) {
		Boolean noNulo = false;
		if(respuesta != null && respuesta.getResponseBansefi() != null ){
			noNulo = true;
		}
		return noNulo;
	}


	private EjecutarResult ejecutarWS(IPEALTAORGACCFUNTRN contexto) {
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(AltaAccionistasFuncionariosServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de alta de perfil transaccional de clientes.", e);
		}
		return respuesta;
	}


	private Ejecutar.IPEALTAORGACCFUNTRN initPeticion(
			PersonaMoralFuncionarioBean funcionarioBean, int idInterno) {
		Ejecutar.IPEALTAORGACCFUNTRN.STDTRNIPARMV contextoEntrada =
				new Ejecutar.IPEALTAORGACCFUNTRN.STDTRNIPARMV();
		
		Ejecutar.IPEALTAORGACCFUNTRN.PEALTAORGACCFUNEVTY.PEPERSRLACCFUNP contexto = 
				new Ejecutar.IPEALTAORGACCFUNTRN.PEALTAORGACCFUNEVTY.PEPERSRLACCFUNP();
		Ejecutar.IPEALTAORGACCFUNTRN.PEALTAORGACCFUNEVTY.PEPERSRLACCFUNE datosFuncionario = 
				new Ejecutar.IPEALTAORGACCFUNTRN.PEALTAORGACCFUNEVTY.PEPERSRLACCFUNE();
		
		Ejecutar.IPEALTAORGACCFUNTRN.PEALTAORGACCFUNEVTY funcionarioAlta = 
				new Ejecutar.IPEALTAORGACCFUNTRN.PEALTAORGACCFUNEVTY();
		IPEALTAORGACCFUNTRN funcionario = 
				new Ejecutar.IPEALTAORGACCFUNTRN();
		super.initialize(funcionario);
		
		datosFuncionario.setCODNRBEEN(super.getEntidad());
		datosFuncionario.setIDRFC(funcionarioBean.getRfc());
		datosFuncionario.setNOMBINAPE1IN(funcionarioBean.getApellidoPaterno());
		datosFuncionario.setNOMBINAPE2IN(funcionarioBean.getApellidoMaterno());
		datosFuncionario.setNOMBINNOMBPILA(funcionarioBean.getNombre());
		datosFuncionario.setPUESTO(funcionarioBean.getPuesto());
		datosFuncionario.setIDINTERNOPE(idInterno);
		
		contexto.setCODNRBEEN(super.getEntidad());
		contexto.setIDINTERNOPE(idInterno);
		funcionarioAlta.setPEPERSRLACCFUNP(contexto);
		funcionarioAlta.setPEPERSRLACCFUNE(datosFuncionario);
			
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_PE_ALTA_INDV_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		
		funcionario.setPEALTAORGACCFUNEVTY(funcionarioAlta);
		funcionario.setSTDTRNIPARMV(contextoEntrada);
		
		return funcionario;
	}

}
