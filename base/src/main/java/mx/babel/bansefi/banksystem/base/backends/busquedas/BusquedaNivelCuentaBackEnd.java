package mx.babel.bansefi.banksystem.base.backends.busquedas;

import java.io.Serializable;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.webservices.buscanivelcuenta.BuscaNivelCuentaServicio;
import mx.babel.bansefi.banksystem.base.webservices.buscanivelcuenta.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.buscanivelcuenta.EjecutarResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BusquedaNivelCuentaBackEnd extends BackEndBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2642998793329027174L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	public void EjecutarTRN(String numeroCuenta){
		Ejecutar.ITRBUSCANIVELCTATRNI contexto = this.initPeticion(numeroCuenta);
		
		EjecutarResult respuesta = this.getResponse(contexto);
		
		super.verificaResponseBansefi(respuesta.getResponseBansefi());
	}
	
	public String ejecutarTRN(Long numeroCuenta){
		Ejecutar.ITRBUSCANIVELCTATRNI contexto = new Ejecutar.ITRBUSCANIVELCTATRNI();
		
		super.initialize(contexto);
		
		contexto.getTRBUSCANIVELCTAV().getNUMSECACV().setSTDCHAR10(String.format("%010d", numeroCuenta));
		
		contexto.getSTDTRNIPARMV().setCODTX(CodTxConstants.COD_TX_TR_BUSCA_NIVEL_CTA_TRN);
		contexto.getSTDTRNIPARMV().setIDINTERNOTERMTN(super.getTerminal());
		
		EjecutarResult respuesta = this.getResponse(contexto);
		super.verificaRespuesta(respuesta);
		
		return respuesta.getResponseBansefi().getOTRBUSCANIVELCTATRNO().getSTDCHAR02().trim();
	}
	
	private Ejecutar.ITRBUSCANIVELCTATRNI initPeticion(String numeroCuenta){
		Ejecutar.ITRBUSCANIVELCTATRNI contexto = new Ejecutar.ITRBUSCANIVELCTATRNI();
		Ejecutar.ITRBUSCANIVELCTATRNI.TRBUSCANIVELCTAV trbuscanivelctav = new Ejecutar.ITRBUSCANIVELCTATRNI.TRBUSCANIVELCTAV();
		Ejecutar.ITRBUSCANIVELCTATRNI.TRBUSCANIVELCTAV.NUMSECACV numsecacv = new Ejecutar.ITRBUSCANIVELCTATRNI.TRBUSCANIVELCTAV.NUMSECACV(); 
		Ejecutar.ITRBUSCANIVELCTATRNI.STDTRNIPARMV stdtrniparmv = new Ejecutar.ITRBUSCANIVELCTATRNI.STDTRNIPARMV();
		
		numsecacv.setSTDCHAR10(String.format("%010d", Integer.parseInt(numeroCuenta)));
		
		trbuscanivelctav.setNUMSECACV(numsecacv);
		
		stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_BUSCA_NIVEL_CTA_TRN);
		stdtrniparmv.setIDINTERNOTERMTN(super.getTerminal());
		
		contexto.setTRBUSCANIVELCTAV(trbuscanivelctav);
		contexto.setSTDTRNIPARMV(stdtrniparmv);
		
		return contexto;
	}
	
	private EjecutarResult getResponse(Ejecutar.ITRBUSCANIVELCTATRNI contexto){
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
											BuscaNivelCuentaServicio.class, 
											contexto);
		}
		catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta de informacion derivada.", e);
		}
		
		return respuesta;
	}

}
