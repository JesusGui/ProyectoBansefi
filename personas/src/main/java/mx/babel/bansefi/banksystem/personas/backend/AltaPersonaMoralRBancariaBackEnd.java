package mx.babel.bansefi.banksystem.personas.backend;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.PersonaMoralRBancariaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.personas.webservices.altareferenciabancaria.AltaReferenciaBancariaServicio;
import mx.babel.bansefi.banksystem.personas.webservices.altareferenciabancaria.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.altareferenciabancaria.Ejecutar.IPEALTAORGREFBANTRN;
import mx.babel.bansefi.banksystem.personas.webservices.altareferenciabancaria.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.altareferenciabancaria.ResponseBansefi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AltaPersonaMoralRBancariaBackEnd extends BackEndBean{

	private static final long serialVersionUID = -8299289651345841643L;
	
	@Autowired
    ServicioWebUtils servicioWebUtils;

	
	public Integer ejecutarTRN(PersonaMoralRBancariaBean rBancariaBean, int idInterno){
		Ejecutar.IPEALTAORGREFBANTRN contexto = initPeticion(rBancariaBean, idInterno);
		EjecutarResult respuesta = ejecutarWS(contexto);
		super.verificaRespuesta(respuesta);
		if(verificaResponseBansefi(respuesta)){
			return altaRbancaria(respuesta.getResponseBansefi());
		}
		return null;
	}


	private Integer altaRbancaria(ResponseBansefi response) {
		Integer resultado = null;
		if(verificaRespuestaCliente(response)){
			resultado = response.getOPEALTAORGREFBANTRN().getRTRNCD();
		}		
		return resultado;
	}


	private boolean verificaRespuestaCliente(ResponseBansefi response) {
		Boolean noNulo = false;
		if(response!= null && response.getOPEALTAORGREFBANTRN() != null){
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


	private EjecutarResult ejecutarWS(IPEALTAORGREFBANTRN contexto) {
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					AltaReferenciaBancariaServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de alta de perfil"
					+ "transaccional de clientes.", e);
		}
		return respuesta;
	}


	private Ejecutar.IPEALTAORGREFBANTRN initPeticion(
			PersonaMoralRBancariaBean rBancariaBean, int idInterno) {
		Ejecutar.IPEALTAORGREFBANTRN.STDTRNIPARMV contextoEntrada =
				new Ejecutar.IPEALTAORGREFBANTRN.STDTRNIPARMV();
		
		Ejecutar.IPEALTAORGREFBANTRN.PEALTAORGREFBANEVTY contexto = 
				new Ejecutar.IPEALTAORGREFBANTRN.PEALTAORGREFBANEVTY();
		
		Ejecutar.IPEALTAORGREFBANTRN.PEALTAORGREFBANEVTY.PEPERSRLREFBANE datosRBancaria = 
				new Ejecutar.IPEALTAORGREFBANTRN.PEALTAORGREFBANEVTY.PEPERSRLREFBANE();
		
		Ejecutar.IPEALTAORGREFBANTRN.PEALTAORGREFBANEVTY.PEPERSRLREFBANP altaRBancaria = 
				new Ejecutar.IPEALTAORGREFBANTRN.PEALTAORGREFBANEVTY.PEPERSRLREFBANP();
		
		IPEALTAORGREFBANTRN rBancaria = new Ejecutar.IPEALTAORGREFBANTRN();
		super.initialize(rBancaria);

		datosRBancaria.setIDINTERNOPE(idInterno);
		datosRBancaria.setCODNRBEEN(super.getEntidad());
		datosRBancaria.setNUMCTACHAR(rBancariaBean.getNumCuenta());
		datosRBancaria.setTIPOCUENTA(rBancariaBean.getTipoCuenta());
		datosRBancaria.setBANCO(rBancariaBean.getBanco());
		
		
		altaRBancaria.setIDINTERNOPE(idInterno);
		altaRBancaria.setCODNRBEEN(super.getEntidad());
		
		contexto.setPEPERSRLREFBANE(datosRBancaria);
		contexto.setPEPERSRLREFBANP(altaRBancaria);
		
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_PE_ALTA_INDV_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());

		rBancaria.setPEALTAORGREFBANEVTY(contexto);
		rBancaria.setSTDTRNIPARMV(contextoEntrada);
		
		return rBancaria;
	}
}
