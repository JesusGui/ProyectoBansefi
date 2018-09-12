package mx.babel.bansefi.banksystem.personas.backend;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.PersonaMoralRComercialBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.personas.webservices.altareferenciacomercial.AltaReferenciaComercialServicio;
import mx.babel.bansefi.banksystem.personas.webservices.altareferenciacomercial.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.altareferenciacomercial.Ejecutar.IPEALTAORGREFCOMTRN;
import mx.babel.bansefi.banksystem.personas.webservices.altareferenciacomercial.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.altareferenciacomercial.ResponseBansefi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AltaPersonaMoralRComercialBackEnd extends BackEndBean{

	private static final long serialVersionUID = 5274769237580226517L;
	
	@Autowired
    ServicioWebUtils servicioWebUtils;

	
	public Integer ejecutarTRN(PersonaMoralRComercialBean rComercialBean, int idInterno){
		Ejecutar.IPEALTAORGREFCOMTRN contexto = initPeticion(rComercialBean, idInterno);
		EjecutarResult respuesta = ejecutarWS(contexto);
		super.verificaRespuesta(respuesta);
		if(verificaResponseBansefi(respuesta)){
			return altaRcomercial(respuesta.getResponseBansefi());
		}
		return null;
	}


	private Integer altaRcomercial(ResponseBansefi response) {
		Integer resultado = null;
		if(verificaRespuestaCliente(response)){
			resultado = response.getOPEALTAORGREFCOMTRN().getRTRNCD();
		}		
		return resultado;
	}


	private boolean verificaRespuestaCliente(ResponseBansefi response) {
		Boolean noNulo = false;
		if(response!= null && response.getOPEALTAORGREFCOMTRN() != null){
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


	private EjecutarResult ejecutarWS(IPEALTAORGREFCOMTRN contexto) {
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(AltaReferenciaComercialServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de alta de perfil transaccional de clientes.", e);
		}
		return respuesta;
	}


	private Ejecutar.IPEALTAORGREFCOMTRN initPeticion(
			PersonaMoralRComercialBean rComercialBean, int idInterno) {
		Ejecutar.IPEALTAORGREFCOMTRN.STDTRNIPARMV contextoEntrada = new Ejecutar.IPEALTAORGREFCOMTRN.STDTRNIPARMV();
		
		Ejecutar.IPEALTAORGREFCOMTRN.PEALTAORGREFCOMEVTY contexto = new Ejecutar.IPEALTAORGREFCOMTRN.PEALTAORGREFCOMEVTY();
		
		Ejecutar.IPEALTAORGREFCOMTRN.PEALTAORGREFCOMEVTY.PEPERSRLREFCOME datosRComercial = new Ejecutar.IPEALTAORGREFCOMTRN.PEALTAORGREFCOMEVTY.PEPERSRLREFCOME();
		
		Ejecutar.IPEALTAORGREFCOMTRN.PEALTAORGREFCOMEVTY.PEPERSRLREFCOMP altaRComercial = new Ejecutar.IPEALTAORGREFCOMTRN.PEALTAORGREFCOMEVTY.PEPERSRLREFCOMP();
		
		Ejecutar.IPEALTAORGREFCOMTRN rComercial = new Ejecutar.IPEALTAORGREFCOMTRN();
		
		super.initialize(rComercial);

		datosRComercial.setIDINTERNOPE(idInterno);
		datosRComercial.setNOMB50(rComercialBean.getNombre());
		datosRComercial.setCODNRBEEN(super.getEntidad());
		datosRComercial.setDOMIC50(rComercialBean.getDomicilio());
		datosRComercial.setGIRO(rComercialBean.getGiro());
		datosRComercial.setPERSRLTIT(rComercialBean.getRelacion());
		datosRComercial.setTELEFONO(rComercialBean.getTelefono());
		
		altaRComercial.setIDINTERNOPE(idInterno);
		altaRComercial.setCODNRBEEN(super.getEntidad());
		
		contexto.setPEPERSRLREFCOME(datosRComercial);
		contexto.setPEPERSRLREFCOMP(altaRComercial);
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_PE_ALTA_INDV_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());

		rComercial.setPEALTAORGREFCOMEVTY(contexto);
		rComercial.setSTDTRNIPARMV(contextoEntrada);
		
		return rComercial;
	}
}
