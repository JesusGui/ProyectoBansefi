package mx.babel.bansefi.banksystem.cuentas.backends;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.cuentas.beans.GastosExternosBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.imputargastoexterno.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.imputargastoexterno.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.imputargastoexterno.ImputarGastoExternoServicio;
@Component
public class ImputarGastoExternoBackend extends BackEndBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8954428136722823606L;
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	
	/**
	 * Método para inicializar objeto de petición
	 * @return Objeto de petición al ws
	 */
	private Ejecutar.ITRACIMPUTARGASTOEXT initPeticion(long acuerdo,String codigoLinea,String grupo, BigDecimal importe, GastosExternosBean gastos,long acuedoAsociado,String imputacion)
	{
		Ejecutar.ITRACIMPUTARGASTOEXT contexto= new Ejecutar.ITRACIMPUTARGASTOEXT();
		
		Ejecutar.ITRACIMPUTARGASTOEXT.TRACIMPUTARGASTOEXTE tRACIMPUTARGASTOEXTE = new Ejecutar.ITRACIMPUTARGASTOEXT.TRACIMPUTARGASTOEXTE();
		
		Ejecutar.ITRACIMPUTARGASTOEXT.TRACIMPUTARGASTOEXTE.ACACP aCACP = new Ejecutar.ITRACIMPUTARGASTOEXT.TRACIMPUTARGASTOEXTE.ACACP();
		aCACP.setCODNRBEEN(super.getEntidad());
		aCACP.setNUMSECAC(acuerdo);
		
		tRACIMPUTARGASTOEXTE.setACACP(aCACP);
		
		Ejecutar.ITRACIMPUTARGASTOEXT.TRACIMPUTARGASTOEXTE.ACAFGASTOEXTV aCAFGASTOEXTV = new Ejecutar.ITRACIMPUTARGASTOEXT.TRACIMPUTARGASTOEXTE.ACAFGASTOEXTV();
		aCAFGASTOEXTV.setCODNRBEEN(super.getEntidad());
		aCAFGASTOEXTV.setCODLINEA(codigoLinea);
		aCAFGASTOEXTV.setCODNUMRCOMONEDA("MXN");
		aCAFGASTOEXTV.setCODORGNAPNTE("AC");
		aCAFGASTOEXTV.setIDGRPPD(grupo);
		aCAFGASTOEXTV.setIMPAPNTE(importe);
		aCAFGASTOEXTV.setNUMSECAC(acuerdo);
		aCAFGASTOEXTV.setNUMSEC(gastos.getGastoSeleccionado().getNumSecuencia());
		aCAFGASTOEXTV.setFECHACTBLE(0);
		aCAFGASTOEXTV.setFECHAVALOR(0);
		aCAFGASTOEXTV.setIND1(gastos.getGastoSeleccionado().getInd1());
		aCAFGASTOEXTV.setIND2(gastos.getGastoSeleccionado().getInd2());
		aCAFGASTOEXTV.setIND3(gastos.getGastoSeleccionado().getInd3());
		aCAFGASTOEXTV.setCODCTA("10");
		
		tRACIMPUTARGASTOEXTE.setACAFGASTOEXTV(aCAFGASTOEXTV);
		
		Ejecutar.ITRACIMPUTARGASTOEXT.TRACIMPUTARGASTOEXTE.ACIMPUTCARGOACV aCIMPUTCARGOACV = new Ejecutar.ITRACIMPUTARGASTOEXT.TRACIMPUTARGASTOEXTE.ACIMPUTCARGOACV();
		aCIMPUTCARGOACV.setCODNRBEEN(super.getEntidad());
		aCIMPUTCARGOACV.setNUMSECAC(acuedoAsociado);
		
		tRACIMPUTARGASTOEXTE.setACIMPUTCARGOACV(aCIMPUTCARGOACV);
		
		Ejecutar.ITRACIMPUTARGASTOEXT.TRACIMPUTARGASTOEXTE.ACIMPUTABONOACV aCIMPUTABONOACV = new Ejecutar.ITRACIMPUTARGASTOEXT.TRACIMPUTARGASTOEXTE.ACIMPUTABONOACV();
		aCIMPUTABONOACV.setCODNRBEEN(super.getEntidad());
		aCIMPUTABONOACV.setNUMSECAC(0);
		
		tRACIMPUTARGASTOEXTE.setACIMPUTABONOACV(aCIMPUTABONOACV);
		
		Ejecutar.ITRACIMPUTARGASTOEXT.TRACIMPUTARGASTOEXTE.ACTPIMPUTACV aCTPIMPUTACV = new Ejecutar.ITRACIMPUTARGASTOEXT.TRACIMPUTARGASTOEXTE.ACTPIMPUTACV();
		aCTPIMPUTACV.setSTDCHAR01(imputacion);
		
		tRACIMPUTARGASTOEXTE.setACTPIMPUTACV(aCTPIMPUTACV);
		
		Ejecutar.ITRACIMPUTARGASTOEXT.STDTRNIPARMV sTDTRNIPARMV = new Ejecutar.ITRACIMPUTARGASTOEXT.STDTRNIPARMV();
		sTDTRNIPARMV.setIDINTERNOTERMTN(super.getTerminal());
		sTDTRNIPARMV.setNUMSEC(0);
		sTDTRNIPARMV.setCODTX("GAC33OOU");
		
		contexto.setSTDTRNIPARMV(sTDTRNIPARMV);
		contexto.setTRACIMPUTARGASTOEXTE(tRACIMPUTARGASTOEXTE);
		
		return contexto;
		
	}

	
	/**
	 * Método para obtener la respuesta del ws a partir de un objeto de petición
	 * @param contexto Objeto de petición
	 * @return respuesta del ws
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRACIMPUTARGASTOEXT contexto)
	{
		EjecutarResult respuesta= null;
		try{
			respuesta =(EjecutarResult) servicioWebUtils.ejecutarWS(ImputarGastoExternoServicio.class,contexto);
		
	}catch(NoControlableException e){
		throw new NoControlableException("Error al invocar servicio web de alta "
				+ "de peticion de efectivo.", e);
	}
	
	return respuesta;
	}
	
	/**
	 * Método que inserta un registro de gastos externos
	 */
	public void ejecutarTRN(long acuerdo,String codigoLinea,String grupo, BigDecimal importe, GastosExternosBean gastos,long acuedoAsociado,String imputacion)
	{
		Ejecutar.ITRACIMPUTARGASTOEXT contexto= initPeticion(acuerdo,codigoLinea,grupo, importe,  gastos,acuedoAsociado,imputacion);
		EjecutarResult respuesta=ejecutarWS(contexto);
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return;
			}
		}
		
		
	}
	
}
