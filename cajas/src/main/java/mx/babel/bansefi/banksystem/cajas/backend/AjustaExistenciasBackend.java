package mx.babel.bansefi.banksystem.cajas.backend;

import java.math.BigDecimal;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;
import mx.babel.bansefi.banksystem.cajas.beans.AjusteExistenciasActualesBean;
import mx.babel.bansefi.banksystem.cajas.webservice.ajustaexistencias.AjustaExistenciasServicio;
import mx.babel.bansefi.banksystem.cajas.webservice.ajustaexistencias.Ejecutar;
import mx.babel.bansefi.banksystem.cajas.webservice.ajustaexistencias.EjecutarResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AjustaExistenciasBackend extends BackEndBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8468593159320988032L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;

	public int ejecutarTRN(AjusteExistenciasActualesBean ajusteExistenciasActualesBean){
		
		int codigoRetorno = 0;
		
		Ejecutar.ITRAJUSTEDESGLSEXTRN contexto = initPeticion(ajusteExistenciasActualesBean);
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if(ce.getRtncod() != RETURN_COD_OK){
				throw ce;
			}
		}
		
		if(verificaRespuesta(respuesta)){
			codigoRetorno = RETURN_COD_OK;
		}
		
		return codigoRetorno;
	}
	
	private Ejecutar.ITRAJUSTEDESGLSEXTRN initPeticion(AjusteExistenciasActualesBean ajusteExistenciasActualesBean){
		
		Ejecutar.ITRAJUSTEDESGLSEXTRN contexto = new Ejecutar.ITRAJUSTEDESGLSEXTRN();
		
		Ejecutar.ITRAJUSTEDESGLSEXTRN.TRAJUSTEDESGLSEXEVTY nivel1_1;
		Ejecutar.ITRAJUSTEDESGLSEXTRN.EXAJUSTELSDS nivel1_2 = new Ejecutar.ITRAJUSTEDESGLSEXTRN.EXAJUSTELSDS();
		Ejecutar.ITRAJUSTEDESGLSEXTRN.STDTRNIPARMV nivel1_3 = new Ejecutar.ITRAJUSTEDESGLSEXTRN.STDTRNIPARMV();
		
		Ejecutar.ITRAJUSTEDESGLSEXTRN.TRAJUSTEDESGLSEXEVTY.SMIMPV nivel2_1;
		
		Ejecutar.ITRAJUSTEDESGLSEXTRN.EXAJUSTELSDS.SMDIFV nivel3_1 = new Ejecutar.ITRAJUSTEDESGLSEXTRN.EXAJUSTELSDS.SMDIFV();
		Ejecutar.ITRAJUSTEDESGLSEXTRN.EXAJUSTELSDS.SMTOTV nivel3_2 = new Ejecutar.ITRAJUSTEDESGLSEXTRN.EXAJUSTELSDS.SMTOTV();
		Ejecutar.ITRAJUSTEDESGLSEXTRN.EXAJUSTELSDS.SMCUENTAV nivel3_3 = new Ejecutar.ITRAJUSTEDESGLSEXTRN.EXAJUSTELSDS.SMCUENTAV();
		
		contexto.setELEVATORPOSITION(1);
		contexto.setSCROLLABLEOCCURS(50);
		contexto.setTRANCD("AJUSTE");
		
		for(ExistenciaDenominacionBean existenciaDenominacionBean : ajusteExistenciasActualesBean.getExistenciaDenominacion()){
			
			if(existenciaDenominacionBean.getImporteNuevo().compareTo(BigDecimal.ZERO) > 0){
				nivel1_1 = new Ejecutar.ITRAJUSTEDESGLSEXTRN.TRAJUSTEDESGLSEXEVTY();
				nivel2_1 = new Ejecutar.ITRAJUSTEDESGLSEXTRN.TRAJUSTEDESGLSEXEVTY.SMIMPV();
				
				nivel1_1.setCODVALORFACIAL(existenciaDenominacionBean.getValorFacial());
				nivel1_1.setVALORMONEDA(existenciaDenominacionBean.getValor());
				nivel1_1.setSOPORTE(existenciaDenominacionBean.getMoneda().equals("M") ? "M" : "B");
				nivel1_1.setFORMATO(existenciaDenominacionBean.getFormato());
				nivel1_1.setCODDSTN(existenciaDenominacionBean.getOrigen());
				nivel1_1.setIMPEXISTEX(existenciaDenominacionBean.getExistencias());
				
				nivel2_1.setSTDDEC15Y2(existenciaDenominacionBean.getImporteNuevo());
				
				nivel1_1.getSMIMPV().add(nivel2_1);
				
				contexto.getTRAJUSTEDESGLSEXEVTY().add(nivel1_1);
			}
		}
		
		nivel1_2.setCODINTERNOUO(super.getSucursal());
		nivel1_2.setTOTEFCT(ajusteExistenciasActualesBean.getTotalExistencias());
		nivel1_2.setSTDFECHA(super.getFechaSistemaInteger());
		
		nivel3_1.setSTDDEC15Y2(ajusteExistenciasActualesBean.getDiferencia());
		
		nivel1_2.setSMDIFV(nivel3_1);
		
		nivel3_2.setSTDDEC15Y2(ajusteExistenciasActualesBean.getTotalEfectivo());
		
		nivel1_2.setSMTOTV(nivel3_2);
		
		nivel3_3.setSTDCHAR10("");
		
		nivel1_2.setSMCUENTAV(nivel3_3);
		
		nivel1_2.setCODNUMRCOMONEDA(ajusteExistenciasActualesBean.getCodigoMoneda());
		
		nivel1_3.setIDINTERNOTERMTN(super.getTerminal());
		nivel1_3.setNUMSEC(0);
		nivel1_3.setCODTX("VCC18MOU");
		nivel1_3.setCODTXDI("CC18");
		
		contexto.setEXAJUSTELSDS(nivel1_2);
		contexto.setSTDTRNIPARMV(nivel1_3);
		
		super.initialize(contexto);
		
		return contexto;
	}
	
	private Boolean verificaRespuesta(EjecutarResult respuesta){
		Boolean noNulo = false;
		
		if(respuesta != null
				&& respuesta.getResponseBansefi() != null){
			noNulo = true;
		}
		
		return noNulo;
	}
	
	private EjecutarResult ejecutarWS(Ejecutar.ITRAJUSTEDESGLSEXTRN contexto){
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(AjustaExistenciasServicio.class, contexto);
		}catch(NoControlableException nce){
			throw new NoControlableException("No se ha podido invocar el web service de"
					+ " ajuste de existencias.",nce);
		}
		return respuesta;
	}
}
