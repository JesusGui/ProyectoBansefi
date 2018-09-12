package mx.babel.bansefi.banksystem.cajas.backend;

import java.math.BigDecimal;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.cajas.beans.DiferenciaBean;
import mx.babel.bansefi.banksystem.cajas.webservice.corregirdiferencias.CorregirDiferenciasServicio;
import mx.babel.bansefi.banksystem.cajas.webservice.corregirdiferencias.Ejecutar;
import mx.babel.bansefi.banksystem.cajas.webservice.corregirdiferencias.EjecutarResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CorregirDiferenciasBackend extends BackEndBean{
	
	private static final long serialVersionUID = -1517289734823366841L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	public int ejecutarTRN(DiferenciaBean diferenciaBean){
		
		int codigoResultado = 0;
		
		Ejecutar.ITRTRATAMDIFOFSMTRN contexto = initPeticion(diferenciaBean);
		EjecutarResult resultado = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(resultado);
		}catch (ControlableException ce){
			if(ce.getRtncod() != RETURN_COD_OK
					&& ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}
		}
		
		codigoResultado = RETURN_COD_OK;
		
		return codigoResultado;
	}
	
	private Ejecutar.ITRTRATAMDIFOFSMTRN initPeticion(DiferenciaBean diferenciaBean){
		
		Ejecutar.ITRTRATAMDIFOFSMTRN contexto = new Ejecutar.ITRTRATAMDIFOFSMTRN();
		Ejecutar.ITRTRATAMDIFOFSMTRN.TRTRATAMDIFOFSMEVTY nivel1_1 = new Ejecutar.ITRTRATAMDIFOFSMTRN.TRTRATAMDIFOFSMEVTY();
		Ejecutar.ITRTRATAMDIFOFSMTRN.SMSIGNOCTBLE nivel1_2 = new Ejecutar.ITRTRATAMDIFOFSMTRN.SMSIGNOCTBLE();
		Ejecutar.ITRTRATAMDIFOFSMTRN.SMDIFEFCTV nivel1_3 = new Ejecutar.ITRTRATAMDIFOFSMTRN.SMDIFEFCTV();
		Ejecutar.ITRTRATAMDIFOFSMTRN.STDTRNIPARMV nivel1_4 = new Ejecutar.ITRTRATAMDIFOFSMTRN.STDTRNIPARMV();
		
		Ejecutar.ITRTRATAMDIFOFSMTRN.TRTRATAMDIFOFSMEVTY.SMSOLCTMONEDAE nivel2_1 = new Ejecutar.ITRTRATAMDIFOFSMTRN.TRTRATAMDIFOFSMEVTY.SMSOLCTMONEDAE();
		
		contexto.setELEVATORPOSITION(1);
		contexto.setSCROLLABLEOCCURS(50);
		
		nivel2_1.setCODNRBEEN(super.getEntidad());
		nivel2_1.setCODINTERNOUO(diferenciaBean.getCentrOrigen());
		nivel2_1.setFECHASOLCTSM(diferenciaBean.getFechaPeticion());
		nivel2_1.setCODPPL("");
		nivel2_1.setCODDISTRIB(diferenciaBean.getCodigoDistribucion().getClaveFila());
		nivel2_1.setINDURG(diferenciaBean.getNumeroUrgente());
		nivel2_1.setCODINTERNOUO1(diferenciaBean.getCentroDesino());
		nivel2_1.setIMPAUTV(diferenciaBean.getTotalAutorizado());
		nivel2_1.setIMPPEDIDOV(BigDecimal.ZERO);
		nivel2_1.setIMPRECBDOV(diferenciaBean.getTotalRecibido());
		nivel2_1.setIMPCERTFDOV(BigDecimal.ZERO);
		nivel1_1.setSMSOLCTMONEDAE(nivel2_1);
		
		contexto.setTRTRATAMDIFOFSMEVTY(nivel1_1);
		
		nivel1_2.setSTDCHAR02("CD");
		
		contexto.setSMSIGNOCTBLE(nivel1_2);
		
		final BigDecimal diferenciaTotal = diferenciaBean.getTotalAutorizado().subtract(diferenciaBean.getTotalRecibido());
		nivel1_3.setSTDDEC15Y2(diferenciaTotal);
		contexto.setSMDIFEFCTV(nivel1_3);
		
		nivel1_4.setIDINTERNOTERMTN(super.getTerminal());
		nivel1_4.setIDEMPLAUT("");
		nivel1_4.setNUMSEC(0);
		nivel1_4.setCODTX("VCO21OOU");
		nivel1_4.setCODTXDI("CO21");
		contexto.setSTDTRNIPARMV(nivel1_4);
		
		return contexto;
	}

	private EjecutarResult ejecutarWS(Ejecutar.ITRTRATAMDIFOFSMTRN contexto){
		EjecutarResult resultado = null;
		
		try{
			resultado = (EjecutarResult) servicioWebUtils.ejecutarWS(CorregirDiferenciasServicio.class, contexto);
		}catch(NoControlableException nce){
			throw new NoControlableException("No se pudo invocar el servicio de"
					+ " corregir diferencias.", nce);
		}
		return resultado;
	}
}
