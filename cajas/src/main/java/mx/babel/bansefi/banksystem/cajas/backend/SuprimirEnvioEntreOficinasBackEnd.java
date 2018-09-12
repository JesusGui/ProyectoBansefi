package mx.babel.bansefi.banksystem.cajas.backend;

import java.math.BigDecimal;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.cajas.beans.EnvioEntreOficinasBean;
import mx.babel.bansefi.banksystem.cajas.webservice.suprimirenvioentreoficinas.Ejecutar;
import mx.babel.bansefi.banksystem.cajas.webservice.suprimirenvioentreoficinas.EjecutarResult;
import mx.babel.bansefi.banksystem.cajas.webservice.suprimirenvioentreoficinas.SuprimirEnvioEntreOficinasServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SuprimirEnvioEntreOficinasBackEnd extends BackEndBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 805526830085850035L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	public Integer ejecutarTRN(EnvioEntreOficinasBean envioEntreOficinasBean){
		Ejecutar.ITRBAJAENVIOOFCNASMT contexto = initPeticion(envioEntreOficinasBean);
		EjecutarResult respuesta = ejecutarWS(contexto);
		Integer codigoRetorno = 0;
		
		try{
			super.verificaRespuesta(respuesta);
		} catch (ControlableException ce){
			if(ce.getRtncod() != RETURN_COD_OK){
				throw ce;
			}
		}
		
		if(verificiaRespuesta(respuesta)){
			codigoRetorno = respuesta.getResponseBansefi().getOTRBAJAENVIOOFCNASMT().getRTRNCD();
		}
		return codigoRetorno;
	}
	
	private Ejecutar.ITRBAJAENVIOOFCNASMT initPeticion(EnvioEntreOficinasBean envioEntreOficinasBean){
		Ejecutar.ITRBAJAENVIOOFCNASMT contexto = new Ejecutar.ITRBAJAENVIOOFCNASMT();
		
		Ejecutar.ITRBAJAENVIOOFCNASMT.TRBAJAENVIOOFCNASMEV nivel1_1 = new Ejecutar.ITRBAJAENVIOOFCNASMT.TRBAJAENVIOOFCNASMEV();
		Ejecutar.ITRBAJAENVIOOFCNASMT.SMSIGNOCTBLE nivel1_2 = new Ejecutar.ITRBAJAENVIOOFCNASMT.SMSIGNOCTBLE();
		Ejecutar.ITRBAJAENVIOOFCNASMT.STDTRNIPARMV nivel1_3 = new Ejecutar.ITRBAJAENVIOOFCNASMT.STDTRNIPARMV();
		
		Ejecutar.ITRBAJAENVIOOFCNASMT.TRBAJAENVIOOFCNASMEV.SMSOLCTMONEDAE nivel2_1 = new Ejecutar.ITRBAJAENVIOOFCNASMT.TRBAJAENVIOOFCNASMEV.SMSOLCTMONEDAE();
		
		nivel2_1.setCODNRBEEN(super.getEntidad());
		nivel2_1.setCODINTERNOUO(super.getSucursal());
		nivel2_1.setFECHASOLCTSM(envioEntreOficinasBean.getFechaContableEnvioInteger());
		nivel2_1.setCODPPL("");
		nivel2_1.setCODDISTRIB("");
		nivel2_1.setINDURG(0);
		nivel2_1.setCODINTERNOUO1(envioEntreOficinasBean.getCentroDestino());
		nivel2_1.setCODINTERNOUO2("");
		//nivel2_1.setCODECVSM(envioEntreOficinasBean.getEstatusC());
		nivel2_1.setCODECVSM("");
		nivel2_1.setFECHAABASTREC(0);
		nivel2_1.setHORAABASTREC(0);
		nivel2_1.setFECHAPROCESOSM(0);
		nivel2_1.setIMPAUTV(BigDecimal.ZERO);
		nivel2_1.setIMPPEDIDOV(envioEntreOficinasBean.getTotalAEnviar());
		nivel2_1.setIMPRECBDOV(BigDecimal.ZERO);
		nivel2_1.setIMPCERTFDOV(BigDecimal.ZERO);
		nivel2_1.setINDSMCUADRE("");
		nivel2_1.setINDMOTIVOCUADRE("");
		nivel2_1.setIDINTERNOEMPLEP("");
		nivel2_1.setVALOROBSERSM("");
		
		nivel1_1.setSMSOLCTMONEDAE(nivel2_1);
		
		contexto.setTRBAJAENVIOOFCNASMEV(nivel1_1);
		
		nivel1_2.setSTDCHAR02("");
		
		contexto.setSMSIGNOCTBLE(nivel1_2);
		
		nivel1_3.setIDINTERNOTERMTN(super.getTerminal());
		nivel1_3.setIDEMPLAUT("");
		nivel1_3.setNUMSEC(0);
		nivel1_3.setCODTX("VCO16MOU");
		nivel1_3.setCODTXDI("CO16");
		
		contexto.setSTDTRNIPARMV(nivel1_3);
		
		return contexto;
	}
	
	private EjecutarResult ejecutarWS(Ejecutar.ITRBAJAENVIOOFCNASMT contexto){
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(SuprimirEnvioEntreOficinasServicio.class, contexto);
		}catch (NoControlableException nce){
			throw new NoControlableException("No se pudo invocar el servicio de"
					+ " suprimir envío entre oficinas.", nce);
		}
		return respuesta;
	}
	
	private Boolean verificiaRespuesta(EjecutarResult respuesta){
		Boolean noNulo = false;
		if(respuesta != null
				&& respuesta.getResponseBansefi() != null){
			noNulo = true;
		}
		return noNulo;
	}

}
