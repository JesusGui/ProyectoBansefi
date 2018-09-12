package mx.babel.bansefi.banksystem.cuentas.backends;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.cuentas.beans.DesgloseApuntesBean;
import mx.babel.bansefi.banksystem.cuentas.beans.SimulacionCancelacionCuentaBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.descripcionconcepto.*;
import mx.babel.bansefi.banksystem.cuentas.webservices.descripcionconcepto.Ejecutar.IHLDESCRCONCEPTRNI.HLDESCRCONCEPEVTY.LIQAPUNTEDESCRV;
import mx.babel.bansefi.banksystem.cuentas.webservices.descripcionconcepto.ResponseBansefi.OHLDESCRCONCEPTRNO.HLDESCRCONCEPEVTZ.LIQCONCEPTODESCV;


@Component
public class DescripcionConceptoBackend extends BackEndBean implements
Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	ServicioWebUtils servicioWebUtils;
	/**
	 * Función que inicializa los parametros de entrada que necesita el servicio
	 * web para ser ejecutado
	 * 
	 * @param DesgloseApuntesBean
	 * @return Ejecutar.IHLDESCRCONCEPTRNI
	 */
	private Ejecutar.IHLDESCRCONCEPTRNI initPeticion(List<DesgloseApuntesBean> parametros, String codigoLinea,String idGrppd)
	{
		
		
		Ejecutar.IHLDESCRCONCEPTRNI contexto= new Ejecutar.IHLDESCRCONCEPTRNI();
		
		contexto.setEVENTCD(0);
		contexto.setNUMBEROFRECORDS(parametros.size());
		Ejecutar.IHLDESCRCONCEPTRNI.STDTRNIPARMV  sTDTRNIPARMV = new  Ejecutar.IHLDESCRCONCEPTRNI.STDTRNIPARMV();
		
		
		
		Ejecutar.IHLDESCRCONCEPTRNI.HLDESCRCONCEPEVTY  hLDESCRCONCEPEVTY = new Ejecutar.IHLDESCRCONCEPTRNI.HLDESCRCONCEPEVTY();
		
		
		for(DesgloseApuntesBean items:parametros)
		{
		
		LIQAPUNTEDESCRV lIQAPUNTEDESCRV= new LIQAPUNTEDESCRV();
		
		lIQAPUNTEDESCRV.setCODCTA(items.getCodigoCuenta());
		lIQAPUNTEDESCRV.setIND1(items.getInd1());
		lIQAPUNTEDESCRV.setIND2(items.getInd2());
		lIQAPUNTEDESCRV.setIND3(items.getInd3());
		lIQAPUNTEDESCRV.setIND4(items.getInd4());
		lIQAPUNTEDESCRV.setIND5(items.getInd5());
		lIQAPUNTEDESCRV.setIND6(items.getInd6());
		lIQAPUNTEDESCRV.setIND7(items.getInd7());
		lIQAPUNTEDESCRV.setIND8(items.getInd8());
		lIQAPUNTEDESCRV.setIND9(items.getInd9());
		lIQAPUNTEDESCRV.setIND10(items.getInd10());
		lIQAPUNTEDESCRV.setCODORIGEN(items.getCodigoOrigen());
		lIQAPUNTEDESCRV.setCODLINEA(codigoLinea);
		lIQAPUNTEDESCRV.setIDGRPPD(idGrppd);
		lIQAPUNTEDESCRV.setIDPDS(items.getIdPds());
		
		hLDESCRCONCEPEVTY.getLIQAPUNTEDESCRV().add(lIQAPUNTEDESCRV);
		}
		
		contexto.setHLDESCRCONCEPEVTY(hLDESCRCONCEPEVTY);
		
		sTDTRNIPARMV.setIDINTERNOTERMTN(super.getTerminal());
		sTDTRNIPARMV.setNUMSEC(0);
		sTDTRNIPARMV.setCODTX("GAE20COU");
		sTDTRNIPARMV.setCODTXDI("xxxx");
		
		contexto.setSTDTRNIPARMV(sTDTRNIPARMV);
		
		return contexto;
		
	}
	
	/**
	 * Método para obtener la respuesta del ws a partir de un objeto de petición
	 * @param contexto Objeto de petición
	 * @return respuesta del ws
	 */
	
	private EjecutarResult ejecutarWS(Ejecutar.IHLDESCRCONCEPTRNI contexto)
	{
		EjecutarResult respuesta= null;
		try
		{
			respuesta=(EjecutarResult) this.servicioWebUtils.ejecutarWS(DescripcionConceptoServicio.class,contexto);
		}
		catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "de descripción de concepto.", e);
		}
		return respuesta;
	}
	
	

	/**
	 * Función principal que descencadena todo el llamado de la TRN/WebService
	 * 
	 * @param contadores
	 *            Bean generico para la consulta de Contadores de Centro y
	 *            Puesto
	 * @return ContadoresCentroBean
	 */
	public void ejecutarTRN(SimulacionCancelacionCuentaBean simulacion)
	{
		
		Ejecutar.IHLDESCRCONCEPTRNI contexto=initPeticion(simulacion.getDesgloseConceptos(),simulacion.getCodigoLinea(),simulacion.getIdGRPPD());
		EjecutarResult respuesta= ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
			
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}
		}
		
		if(verificaResponseBansefi(respuesta)){
			getDescripciones(simulacion.getDesgloseConceptos(),respuesta);
		}
	}
	
	
	public void getDescripciones(List<DesgloseApuntesBean> parametros,EjecutarResult respuesta)
	{
		
		 int indice=0;
	 for(LIQCONCEPTODESCV descripcion:respuesta.getResponseBansefi().getOHLDESCRCONCEPTRNO().getHLDESCRCONCEPEVTZ().getLIQCONCEPTODESCV())
	 {
		
		 if(descripcion.getNOMBPARMCD().trim().length() >0)
		 {
		 parametros.get(indice).setDescripcion(descripcion.getNOMBPARMCD());
		 
		indice= indice +1;
		 }
		 
	 }
	}

	
	/**
	 * Función que valida que la respuesta del servidor no este vacía.
	 * 
	 * @param respuesta
	 *            Objeto respuesta del servicio web
	 * @return <code>true</code> si la respuesta no esta vacía.
	 */
	private Boolean verificaResponseBansefi(EjecutarResult respuesta) {
		Boolean noNulo = false;
		if (respuesta != null && respuesta.getResponseBansefi() != null) {
			noNulo = true;
		}
		return noNulo;
	}
}
