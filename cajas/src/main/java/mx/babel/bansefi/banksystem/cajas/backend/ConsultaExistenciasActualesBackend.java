package mx.babel.bansefi.banksystem.cajas.backend;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;
import mx.babel.bansefi.banksystem.cajas.beans.AjusteExistenciasActualesBean;
import mx.babel.bansefi.banksystem.cajas.webservice.consultaexistenciasactuales.ConsultaExistenciasActualesServicio;
import mx.babel.bansefi.banksystem.cajas.webservice.consultaexistenciasactuales.Ejecutar;
import mx.babel.bansefi.banksystem.cajas.webservice.consultaexistenciasactuales.EjecutarResult;
import mx.babel.bansefi.banksystem.cajas.webservice.consultaexistenciasactuales.ResponseBansefi;
import mx.babel.bansefi.banksystem.cajas.webservice.consultaexistenciasactuales.ResponseBansefi.OTREXAJUSTELSOBJTRN.TREXAJUSTELSOBJEVTZ.EXAJUSTELS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaExistenciasActualesBackend extends BackEndBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1827409402497799686L;
	
	private List<ExistenciaDenominacionBean> listaExistencias;
	
	private AjusteExistenciasActualesBean ajusteExistenciasActualesBean;
	
	public List<ExistenciaDenominacionBean> getListaExistencias() {
		return listaExistencias;
	}

	public void setListaExistencias(
			List<ExistenciaDenominacionBean> listaExistencias) {
		this.listaExistencias = listaExistencias;
	}

	public AjusteExistenciasActualesBean getAjusteExistenciasActualesBean() {
		return ajusteExistenciasActualesBean;
	}

	public void setAjusteExistenciasActualesBean(
			AjusteExistenciasActualesBean ajusteExistenciasActualesBean) {
		this.ajusteExistenciasActualesBean = ajusteExistenciasActualesBean;
	}

	@Autowired
	ServicioWebUtils servicioWebUtils;

	public void ejecutarTRN(){
		Ejecutar.ITREXAJUSTELSOBJTRN contexto = initPeticion();
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if(ce.getRtncod() != RETURN_COD_OK){
				throw ce;
			}
		}
		
		if(verificaResponseBansefi(respuesta)
				&& verificaRespuesta(respuesta.getResponseBansefi())){
			getParrilla(respuesta);
			getDatosSucursal(respuesta);
		}
	}
	
	private Ejecutar.ITREXAJUSTELSOBJTRN initPeticion(){
		
		Ejecutar.ITREXAJUSTELSOBJTRN contexto = new Ejecutar.ITREXAJUSTELSOBJTRN();
		
		Ejecutar.ITREXAJUSTELSOBJTRN.TRCONSEXISTCTRLEXEVTY nivel1_2 = new Ejecutar.ITREXAJUSTELSOBJTRN.TRCONSEXISTCTRLEXEVTY();
		Ejecutar.ITREXAJUSTELSOBJTRN.STDTRNIPARMV nivel1_1 = new Ejecutar.ITREXAJUSTELSOBJTRN.STDTRNIPARMV();
		
		contexto.setEVENTCD(0);
		contexto.setELEVATORPOSITION(0);
		contexto.setSCROLLABLEOCCURS(50);
		contexto.setNUMBEROFRECORDS(50);
		
		nivel1_1.setIDINTERNOTERMTN(super.getTerminal());
		nivel1_1.setNUMSEC(0);
		nivel1_1.setCODTXDI("CC60");
		nivel1_1.setCODTX("VCC60COU");
		
		nivel1_2.setCODNRBEEN(super.getEntidad());
		nivel1_2.setCODINTERNOUO(super.getSucursal());
		nivel1_2.setCODNUMRCOMONEDA("MXN");
		
		contexto.setSTDTRNIPARMV(nivel1_1);
		contexto.setTRCONSEXISTCTRLEXEVTY(nivel1_2);
		
		super.initialize(contexto);
		
		return contexto;
	}
	
	private EjecutarResult ejecutarWS(Ejecutar.ITREXAJUSTELSOBJTRN contexto){
		
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaExistenciasActualesServicio.class, contexto);
		}catch (NoControlableException nce){
			throw new NoControlableException("No se puede invocar el servicio de"
					+ " consulta de existencias actuales.", nce);
		}
		
		return respuesta;
		
	}
	
	private Boolean verificaResponseBansefi(EjecutarResult respuesta){
		Boolean noNulo = false;
		if(respuesta != null
				&& respuesta.getResponseBansefi() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
	private Boolean verificaRespuesta(ResponseBansefi response){
		Boolean noNulo = false;
		if(response.getOTREXAJUSTELSOBJTRN() != null
				&& response.getOTREXAJUSTELSOBJTRN().getSTDTRNOPARMV() != null
				&& response.getOTREXAJUSTELSOBJTRN().getSTDMSJPARMV() != null
				&& response.getOTREXAJUSTELSOBJTRN().getTREXAJUSTELSOBJEVTZ() != null
				&& response.getOTREXAJUSTELSOBJTRN().getTREXAJUSTELSOBJEVTZ().getEXAJUSTELSDS() != null
				&& response.getOTREXAJUSTELSOBJTRN().getTREXAJUSTELSOBJEVTZ().getEXAJUSTELS() != null
				&& response.getOTREXAJUSTELSOBJTRN().getTREXAJUSTELSOBJEVTZ().getEXEXISTDESGLSE() != null){
			noNulo = true;
		}
		
		return noNulo;
	}
	
	private void getParrilla(EjecutarResult respuesta){
		
		this.listaExistencias = new ArrayList<ExistenciaDenominacionBean>();
		ExistenciaDenominacionBean existenciaDenominacionBean = null;		
		
		for(EXAJUSTELS ajusteExistencia : respuesta.getResponseBansefi().getOTREXAJUSTELSOBJTRN().getTREXAJUSTELSOBJEVTZ().getEXAJUSTELS()){
			if(ajusteExistencia.getCODDSTN() != null
					&& ajusteExistencia.getIMPEXISTEX() != null
					&& ajusteExistencia.getVALORMONEDA().compareTo(BigDecimal.ZERO) > 0){
				
				existenciaDenominacionBean = new ExistenciaDenominacionBean();
				
				existenciaDenominacionBean.setValorFacial(ajusteExistencia.getCODVALORFACIAL());
				existenciaDenominacionBean.setValor(ajusteExistencia.getVALORMONEDA());
				existenciaDenominacionBean.setMoneda(ajusteExistencia.getSOPORTE().equals("M"));
				existenciaDenominacionBean.setFormato(ajusteExistencia.getFORMATO());
				existenciaDenominacionBean.setOrigen(ajusteExistencia.getCODDSTN());
				existenciaDenominacionBean.setExistencias(ajusteExistencia.getIMPEXISTEX());
				
				this.listaExistencias.add(existenciaDenominacionBean);
			}
		}
	}
	
	private void getDatosSucursal(EjecutarResult respuesta){
		 
		 if(respuesta.getResponseBansefi().getOTREXAJUSTELSOBJTRN().getTREXAJUSTELSOBJEVTZ().getEXAJUSTELSDS() != null
				 && respuesta.getResponseBansefi().getOTREXAJUSTELSOBJTRN().getTREXAJUSTELSOBJEVTZ().getEXAJUSTELSDS().getTOTEFCT() != null){
			 this.ajusteExistenciasActualesBean = new AjusteExistenciasActualesBean();
			 this.ajusteExistenciasActualesBean.setSucursal(respuesta.getResponseBansefi().getOTREXAJUSTELSOBJTRN().getTREXAJUSTELSOBJEVTZ().getEXAJUSTELSDS().getCODINTERNOUO());
			 this.ajusteExistenciasActualesBean.setTotalExistencias(respuesta.getResponseBansefi().getOTREXAJUSTELSOBJTRN().getTREXAJUSTELSOBJEVTZ().getEXAJUSTELSDS().getTOTEFCT());
			 this.ajusteExistenciasActualesBean.setCodigoMoneda(respuesta.getResponseBansefi().getOTREXAJUSTELSOBJTRN().getTREXAJUSTELSOBJEVTZ().getEXAJUSTELSDS().getCODNUMRCOMONEDA());
		 }
	}
}
