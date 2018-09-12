package mx.babel.bansefi.banksystem.cajas.backend;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;
import mx.babel.bansefi.banksystem.cajas.beans.CambioDenominacionBean;
import mx.babel.bansefi.banksystem.cajas.webservice.simulaautorizacionpeticion.Ejecutar;
import mx.babel.bansefi.banksystem.cajas.webservice.simulaautorizacionpeticion.EjecutarResult;
import mx.babel.bansefi.banksystem.cajas.webservice.simulaautorizacionpeticion.ResponseBansefi.OTRAUTSMLCNPETCNSMTR.SMSOLCTDESGLSE;
import mx.babel.bansefi.banksystem.cajas.webservice.simulaautorizacionpeticion.SimulaAutorizacionPeticionServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimulaAutorizacionPeticionBackend extends BackEndBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3780178650073693780L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	private List<ExistenciaDenominacionBean> listaSimulacion;
	
	public List<ExistenciaDenominacionBean> getListaSimulacion() {
		return listaSimulacion;
	}

	public void setListaSimulacion(List<ExistenciaDenominacionBean> listaSimulacion) {
		this.listaSimulacion = listaSimulacion;
	}

	public List<ExistenciaDenominacionBean> ejecutarTRN(int fechaSolicitud, List<CambioDenominacionBean> listaSimulaciones){
		
		Ejecutar.ITRAUTSMLCNPETCNSMTR contexto = initPeticion(fechaSolicitud, listaSimulaciones);
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			throw ce;
		}
		if(verificaResponseBansefi(respuesta)){
			return getLista(respuesta);
		}else{
			return null;
		}
	}
	
	public Ejecutar.ITRAUTSMLCNPETCNSMTR initPeticion(int fechaSolicitud, List<CambioDenominacionBean> listaSimulaciones){
		 
		Ejecutar.ITRAUTSMLCNPETCNSMTR contexto = new Ejecutar.ITRAUTSMLCNPETCNSMTR();

		Ejecutar.ITRAUTSMLCNPETCNSMTR.SMSOLCTMONEDAE nivel1_1 = new Ejecutar.ITRAUTSMLCNPETCNSMTR.SMSOLCTMONEDAE();
		Ejecutar.ITRAUTSMLCNPETCNSMTR.SMINTV nivel1_2 = new Ejecutar.ITRAUTSMLCNPETCNSMTR.SMINTV();
		Ejecutar.ITRAUTSMLCNPETCNSMTR.STDTRNIPARMV nivel1_3 = new Ejecutar.ITRAUTSMLCNPETCNSMTR.STDTRNIPARMV();
		
		Ejecutar.ITRAUTSMLCNPETCNSMTR.SMINTV.SMCENTROOFCNAV nivel2_1 = new Ejecutar.ITRAUTSMLCNPETCNSMTR.SMINTV.SMCENTROOFCNAV();
		Ejecutar.ITRAUTSMLCNPETCNSMTR.SMINTV.SMIMPMINV nivel2_2 = new Ejecutar.ITRAUTSMLCNPETCNSMTR.SMINTV.SMIMPMINV();
		Ejecutar.ITRAUTSMLCNPETCNSMTR.SMINTV.SMIMPCAMBIOV nivel2_3 = new Ejecutar.ITRAUTSMLCNPETCNSMTR.SMINTV.SMIMPCAMBIOV();
		Ejecutar.ITRAUTSMLCNPETCNSMTR.SMINTV.SMDSTNINIV nivel2_4 = new Ejecutar.ITRAUTSMLCNPETCNSMTR.SMINTV.SMDSTNINIV();
		Ejecutar.ITRAUTSMLCNPETCNSMTR.SMINTV.SMDSTNFINV nivel2_5 = new Ejecutar.ITRAUTSMLCNPETCNSMTR.SMINTV.SMDSTNFINV();
		Ejecutar.ITRAUTSMLCNPETCNSMTR.SMINTV.SMDESGLSINIV nivel2_6 = new Ejecutar.ITRAUTSMLCNPETCNSMTR.SMINTV.SMDESGLSINIV();
		Ejecutar.ITRAUTSMLCNPETCNSMTR.SMINTV.SMDESGLSFINV nivel2_7 = new Ejecutar.ITRAUTSMLCNPETCNSMTR.SMINTV.SMDESGLSFINV();
		
		nivel1_1.setCODNRBEEN(super.getEntidad());
		nivel1_1.setFECHASOLCTSM(fechaSolicitud);
		nivel1_1.setINDURG(0);
		nivel1_1.setCODINTERNOUO1(super.getSucursal());
		nivel1_1.setFECHAABASTREC(00000000);
		nivel1_1.setHORAABASTREC(000000);
		nivel1_1.setIMPAUTV(new BigDecimal(0.00));
		nivel1_1.setIMPPEDIDOV(new BigDecimal(0.00));
		nivel1_1.setIMPRECBDOV(new BigDecimal(0.00));
		nivel1_1.setIMPCERTFDOV(new BigDecimal(0.00));
		
		contexto.setSMSOLCTMONEDAE(nivel1_1);
		
		nivel2_1.setCODINTERNOUO("INIC");
		nivel2_2.setSTDDEC15Y2(new BigDecimal(0.00));
		nivel2_3.setSTDDEC15Y2(new BigDecimal(0.00));
		
		nivel1_2.setSMCENTROOFCNAV(nivel2_1);
		nivel1_2.getSMIMPMINV().add(nivel2_2);
		nivel1_2.getSMIMPCAMBIOV().add(nivel2_3);
		nivel1_2.getSMDSTNINIV().add(nivel2_4);
		nivel1_2.getSMDSTNFINV().add(nivel2_5);
		nivel1_2.getSMDESGLSINIV().add(nivel2_6);
		nivel1_2.getSMDESGLSFINV().add(nivel2_7);
		
		contexto.getSMINTV().add(nivel1_2);
		
		for(CambioDenominacionBean cambioBean : listaSimulaciones){
		
			nivel1_2 = new Ejecutar.ITRAUTSMLCNPETCNSMTR.SMINTV();
			nivel2_1 = new Ejecutar.ITRAUTSMLCNPETCNSMTR.SMINTV.SMCENTROOFCNAV();
			nivel2_2 = new Ejecutar.ITRAUTSMLCNPETCNSMTR.SMINTV.SMIMPMINV();
			nivel2_3 = new Ejecutar.ITRAUTSMLCNPETCNSMTR.SMINTV.SMIMPCAMBIOV();
			nivel2_4 = new Ejecutar.ITRAUTSMLCNPETCNSMTR.SMINTV.SMDSTNINIV();
			nivel2_5 = new Ejecutar.ITRAUTSMLCNPETCNSMTR.SMINTV.SMDSTNFINV();
			nivel2_6 = new Ejecutar.ITRAUTSMLCNPETCNSMTR.SMINTV.SMDESGLSINIV();
			nivel2_7 = new Ejecutar.ITRAUTSMLCNPETCNSMTR.SMINTV.SMDESGLSFINV();
			
			nivel2_2.setSTDDEC15Y2(new BigDecimal(0.00));
			nivel2_3.setSTDDEC15Y2(cambioBean.getImporteACambiar());
			nivel2_4.setCODDSTN(cambioBean.getOrigenDe());
			nivel2_5.setCODDSTN(cambioBean.getOrigenA());
			nivel2_6.setCODVALORFACIAL(cambioBean.getValorFacialDe());
			nivel2_7.setCODVALORFACIAL(cambioBean.getValorFacialA());
			
			nivel1_2.getSMIMPMINV().add(nivel2_2);
			nivel1_2.getSMIMPCAMBIOV().add(nivel2_3);
			nivel1_2.getSMDSTNINIV().add(nivel2_4);
			nivel1_2.getSMDSTNFINV().add(nivel2_5);
			nivel1_2.getSMDESGLSINIV().add(nivel2_6);
			nivel1_2.getSMDESGLSFINV().add(nivel2_7);
			
			contexto.getSMINTV().add(nivel1_2);
		}
		
		nivel1_3.setIDINTERNOTERMTN(super.getTerminal());
		nivel1_3.setIDEMPLAUT("0");
		nivel1_3.setCODTX("VCC13SMU");
		nivel1_3.setCODTXDI("CC13");
		
		contexto.setSTDTRNIPARMV(nivel1_3);
		
		super.initialize(contexto);
		
		return contexto;
	}
	
	/**
	 * Función que valida que la respues del servidor no este vacía. 
	 *  
	 * @param respuesta Objeto respuesta del servicio web
	 * @return <code>true</code> si la respuesta no esta vacía.
	 */
	private Boolean verificaResponseBansefi(EjecutarResult respuesta){
		Boolean noNulo = false;
		if(respuesta != null && respuesta.getResponseBansefi() != null){
			noNulo = true;
		}
		
		return noNulo;
	}
	
	/**
	 * Método para obtener la respuesta del ws a partir de un objeto de petición
	 * @param contexto Objeto de petición
	 * @return respuesta del ws
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRAUTSMLCNPETCNSMTR contexto) {
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(SimulaAutorizacionPeticionServicio.class, contexto);
		}catch (NoControlableException nce){
			throw new NoControlableException("Error al invocar servicio web de simulación "
					+ "de peticiones de efectivo.", nce);
		}
		
		return respuesta;
	}
	
	
	
	private List<ExistenciaDenominacionBean> getLista(EjecutarResult respuesta){
		listaSimulacion = new ArrayList<ExistenciaDenominacionBean>();
		ExistenciaDenominacionBean existenciaBean = null;
		for(SMSOLCTDESGLSE resultado : respuesta.getResponseBansefi().getOTRAUTSMLCNPETCNSMTR().getSMSOLCTDESGLSE()){
			if(!resultado.getCODDSTN().trim().isEmpty()){
				existenciaBean = new ExistenciaDenominacionBean(); 
				existenciaBean.setValorFacial(resultado.getCODVALORFACIAL().trim());
				existenciaBean.setOrigen(resultado.getCODDSTN().trim());
				existenciaBean.setImportePedido(resultado.getIMPPEDIDO());
				existenciaBean.setImporteAutorizado(resultado.getIMPAUT());
				getListaSimulacion().add(existenciaBean);
			}
		}
		
		return this.listaSimulacion;
	}

}
