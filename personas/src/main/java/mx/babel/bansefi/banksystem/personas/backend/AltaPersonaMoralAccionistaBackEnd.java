package mx.babel.bansefi.banksystem.personas.backend;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.PersonaMoralAccionistaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.personas.webservices.altaaccionistasfuncionarios.AltaAccionistasFuncionariosServicio;
import mx.babel.bansefi.banksystem.personas.webservices.altaaccionistasfuncionarios.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.altaaccionistasfuncionarios.Ejecutar.IPEALTAORGACCFUNTRN;
import mx.babel.bansefi.banksystem.personas.webservices.altaaccionistasfuncionarios.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.altaaccionistasfuncionarios.ResponseBansefi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AltaPersonaMoralAccionistaBackEnd extends BackEndBean{

	private static final long serialVersionUID = -7444313893583975671L;
	
	@Autowired
    ServicioWebUtils servicioWebUtils;

	
	public Integer ejecutarTRN(PersonaMoralAccionistaBean accionistaBean, int idInterno)
			throws NoControlableException, ControlableException{
			Ejecutar.IPEALTAORGACCFUNTRN contexto = initPeticion(accionistaBean, idInterno);
			EjecutarResult respuesta = ejecutarWS(contexto);
			super.verificaRespuesta(respuesta);
			if(verificaResponseBansefi(respuesta)){
				return altaAccionista(respuesta.getResponseBansefi());
			}
			return null;
		}


	private Integer altaAccionista(ResponseBansefi response) {
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
		if(respuesta != null && respuesta.getResponseBansefi() != null){
			noNulo = true;
		}
		return noNulo;
	}


	private EjecutarResult ejecutarWS(IPEALTAORGACCFUNTRN contexto) {
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					AltaAccionistasFuncionariosServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de alta de accionistas"
					+ " y funcionarios.", e);
		}
		return respuesta;
	}


	private Ejecutar.IPEALTAORGACCFUNTRN initPeticion(
			PersonaMoralAccionistaBean accionistaBean, int idInterno) {
		Ejecutar.IPEALTAORGACCFUNTRN.STDTRNIPARMV contextoEntrada =
				new Ejecutar.IPEALTAORGACCFUNTRN.STDTRNIPARMV();
		
		Ejecutar.IPEALTAORGACCFUNTRN.PEALTAORGACCFUNEVTY.PEPERSRLACCFUNP contexto = 
				new Ejecutar.IPEALTAORGACCFUNTRN.PEALTAORGACCFUNEVTY.PEPERSRLACCFUNP();
		Ejecutar.IPEALTAORGACCFUNTRN.PEALTAORGACCFUNEVTY.PEPERSRLACCFUNE datosAccionista = 
				new Ejecutar.IPEALTAORGACCFUNTRN.PEALTAORGACCFUNEVTY.PEPERSRLACCFUNE();
		
		Ejecutar.IPEALTAORGACCFUNTRN.PEALTAORGACCFUNEVTY accionistaAlta = 
				new Ejecutar.IPEALTAORGACCFUNTRN.PEALTAORGACCFUNEVTY();
		IPEALTAORGACCFUNTRN accionista = 
				new Ejecutar.IPEALTAORGACCFUNTRN();
		
		super.initialize(contexto);
		
		datosAccionista.setCODNRBEEN(super.getEntidad());
		datosAccionista.setIDRFC(accionistaBean.getRfc());
		datosAccionista.setNOMBINAPE1IN(accionistaBean.getApellidoPaterno());
		datosAccionista.setNOMBINAPE2IN(accionistaBean.getApellidoMaterno());
		datosAccionista.setNOMBINNOMBPILA(accionistaBean.getNombre());
		datosAccionista.setPCTACCNRIO(accionistaBean.getPorcentaje());
		datosAccionista.setIDINTERNOPE(idInterno);
		
		contexto.setCODNRBEEN(super.getEntidad());
		contexto.setIDINTERNOPE(idInterno);
		accionistaAlta.setPEPERSRLACCFUNP(contexto);
		accionistaAlta.setPEPERSRLACCFUNE(datosAccionista);
			
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_PE_ALTA_INDV_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		
		accionista.setPEALTAORGACCFUNEVTY(accionistaAlta);
		accionista.setSTDTRNIPARMV(contextoEntrada);
		
		return accionista;
	}

}
