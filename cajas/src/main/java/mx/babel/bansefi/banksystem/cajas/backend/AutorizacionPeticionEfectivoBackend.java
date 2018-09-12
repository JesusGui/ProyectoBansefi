package mx.babel.bansefi.banksystem.cajas.backend;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.cajas.beans.CambioDenominacionBean;
import mx.babel.bansefi.banksystem.cajas.webservice.autorizacionpeticionefectivo.AutorizacionPeticionEfectivoServicio;
import mx.babel.bansefi.banksystem.cajas.webservice.autorizacionpeticionefectivo.Ejecutar;
import mx.babel.bansefi.banksystem.cajas.webservice.autorizacionpeticionefectivo.EjecutarResult;

@Component
public class AutorizacionPeticionEfectivoBackend extends BackEndBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8081767589717503182L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	public int ejecutarTRN(int fechaAbastecimiento, BigDecimal totalPedido, BigDecimal totalAutorizado, List<CambioDenominacionBean> listaCambiosDenominacion){
		
		int resultado = 0;
		Ejecutar.ITRAUTCONFPETCNSMTRN contexto = initPeticion(fechaAbastecimiento, totalPedido, totalAutorizado, listaCambiosDenominacion);
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
			resultado = 1;
		}catch (ControlableException ce){
			if(ce.getRtncod() != RETURN_COD_OK){
				throw ce;
			}
		}
		return resultado;
	}
	
	private Ejecutar.ITRAUTCONFPETCNSMTRN initPeticion(int fechaAbastecimiento, BigDecimal totalPedido, BigDecimal totalAutorizado, List<CambioDenominacionBean> listaCambiosDenominacion){
		Ejecutar.ITRAUTCONFPETCNSMTRN contexto = new Ejecutar.ITRAUTCONFPETCNSMTRN();
		
		Ejecutar.ITRAUTCONFPETCNSMTRN.SMSOLCTMONEDAE nivel1_1 = new Ejecutar.ITRAUTCONFPETCNSMTRN.SMSOLCTMONEDAE();
		Ejecutar.ITRAUTCONFPETCNSMTRN.SMINTV nivel1_2 = new Ejecutar.ITRAUTCONFPETCNSMTRN.SMINTV();
		Ejecutar.ITRAUTCONFPETCNSMTRN.STDTRNIPARMV nivel1_3 = new Ejecutar.ITRAUTCONFPETCNSMTRN.STDTRNIPARMV();
		
		Ejecutar.ITRAUTCONFPETCNSMTRN.SMINTV.SMCENTROOFCNAV nivel2_1 = new Ejecutar.ITRAUTCONFPETCNSMTRN.SMINTV.SMCENTROOFCNAV(); 
		Ejecutar.ITRAUTCONFPETCNSMTRN.SMINTV.SMIMPMINV nivel2_2 = new Ejecutar.ITRAUTCONFPETCNSMTRN.SMINTV.SMIMPMINV();
		Ejecutar.ITRAUTCONFPETCNSMTRN.SMINTV.SMIMPCAMBIOV nivel2_3 = new Ejecutar.ITRAUTCONFPETCNSMTRN.SMINTV.SMIMPCAMBIOV();
		Ejecutar.ITRAUTCONFPETCNSMTRN.SMINTV.SMDSTNINIV nivel2_4 = new Ejecutar.ITRAUTCONFPETCNSMTRN.SMINTV.SMDSTNINIV();
		Ejecutar.ITRAUTCONFPETCNSMTRN.SMINTV.SMDSTNFINV nivel2_5 = new Ejecutar.ITRAUTCONFPETCNSMTRN.SMINTV.SMDSTNFINV();
		Ejecutar.ITRAUTCONFPETCNSMTRN.SMINTV.SMDESGLSINIV nivel2_6 = new Ejecutar.ITRAUTCONFPETCNSMTRN.SMINTV.SMDESGLSINIV();
		Ejecutar.ITRAUTCONFPETCNSMTRN.SMINTV.SMDESGLSFINV nivel2_7 = new Ejecutar.ITRAUTCONFPETCNSMTRN.SMINTV.SMDESGLSFINV();
		
		nivel1_1.setFECHASOLCTSM(fechaAbastecimiento);
		nivel1_1.setINDURG(0);
		nivel1_1.setFECHAABASTREC(00000000);
		nivel1_1.setHORAABASTREC(000000);
		nivel1_1.setFECHAPROCESOSM(00000000);
		
		nivel1_1.setIMPPEDIDOV(totalPedido);
		nivel1_1.setIMPAUTV(totalAutorizado);
		
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
		
		for(CambioDenominacionBean cambioDenominacionBean : listaCambiosDenominacion){
		
			nivel1_2 = new Ejecutar.ITRAUTCONFPETCNSMTRN.SMINTV();
			nivel2_2 = new Ejecutar.ITRAUTCONFPETCNSMTRN.SMINTV.SMIMPMINV();
			nivel2_3 = new Ejecutar.ITRAUTCONFPETCNSMTRN.SMINTV.SMIMPCAMBIOV();
			nivel2_4 = new Ejecutar.ITRAUTCONFPETCNSMTRN.SMINTV.SMDSTNINIV();
			nivel2_5 = new Ejecutar.ITRAUTCONFPETCNSMTRN.SMINTV.SMDSTNFINV();
			nivel2_6 = new Ejecutar.ITRAUTCONFPETCNSMTRN.SMINTV.SMDESGLSINIV();
			nivel2_7 = new Ejecutar.ITRAUTCONFPETCNSMTRN.SMINTV.SMDESGLSFINV();
			
			nivel2_2.setSTDDEC15Y2(new BigDecimal(0.00));
			nivel2_3.setSTDDEC15Y2(cambioDenominacionBean.getImporteACambiar());
			nivel2_4.setCODDSTN(cambioDenominacionBean.getOrigenDe());
			nivel2_5.setCODDSTN(cambioDenominacionBean.getOrigenA());
			nivel2_6.setCODVALORFACIAL(cambioDenominacionBean.getValorFacialDe());
			nivel2_7.setCODVALORFACIAL(cambioDenominacionBean.getValorFacialA());
			
			nivel1_2.getSMIMPMINV().add(nivel2_2);
			nivel1_2.getSMIMPCAMBIOV().add(nivel2_3);
			nivel1_2.getSMDSTNINIV().add(nivel2_4);
			nivel1_2.getSMDSTNFINV().add(nivel2_5);
			nivel1_2.getSMDESGLSINIV().add(nivel2_6);
			nivel1_2.getSMDESGLSFINV().add(nivel2_7);
			
			contexto.getSMINTV().add(nivel1_2);
		}
				
		nivel1_3.setIDINTERNOTERMTN(super.getTerminal());
		nivel1_3.setNUMSEC(0);
		nivel1_3.setCODTX("VCC14MOU");
		nivel1_3.setCODTXDI("CC14");
		
		contexto.setSMSOLCTMONEDAE(nivel1_1);
		contexto.setSTDTRNIPARMV(nivel1_3);
		
		super.initialize(contexto);
		
		return contexto;
	}
	
	private EjecutarResult ejecutarWS(Ejecutar.ITRAUTCONFPETCNSMTRN contexto){
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(AutorizacionPeticionEfectivoServicio.class, contexto);
		}catch (NoControlableException nce){
			throw new NoControlableException("Error al invocar el servicio web de "
					+ "autorización de petición de efectivo.", nce);
		}
		
		return respuesta;
	}

}
