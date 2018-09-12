package mx.babel.bansefi.banksystem.cuentas.backends;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.registrogasto.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.registrogasto.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.registrogasto.RegistroGastoServicio;

@Component
public class RegistroGastoExternoBackend extends BackEndBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -676783620901492992L;
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	
	/**
	 * Método para inicializar objeto de petición
	 * @return Objeto de petición al ws
	 */
	private Ejecutar.ITRALTAGASTOSEXTTRNI initPeticion(long acuerdo,BigDecimal importe, String claveGasto, String codigoLinea, String grupo)
	{
		Ejecutar.ITRALTAGASTOSEXTTRNI contexto= new Ejecutar.ITRALTAGASTOSEXTTRNI();
		
		Ejecutar.ITRALTAGASTOSEXTTRNI.TRALTAGASTOSEXTEVTY  tRALTAGASTOSEXTEVTY = new Ejecutar.ITRALTAGASTOSEXTTRNI.TRALTAGASTOSEXTEVTY();
		tRALTAGASTOSEXTEVTY.setCODNRBEEN(super.getEntidad());
		tRALTAGASTOSEXTEVTY.setNUMSECAC(acuerdo);
		tRALTAGASTOSEXTEVTY.setCODNUMRCOMONEDA("MXN");
		tRALTAGASTOSEXTEVTY.setIMPAPNTE(importe);
		tRALTAGASTOSEXTEVTY.setCODORIGEN(claveGasto);
		tRALTAGASTOSEXTEVTY.setCODINTERNOUO(super.getSucursal());
		tRALTAGASTOSEXTEVTY.setCODLINEA(codigoLinea);
		tRALTAGASTOSEXTEVTY.setIDGRPPD(grupo);
		
		Ejecutar.ITRALTAGASTOSEXTTRNI.STDTRNIPARMV sTDTRNIPARMV = new Ejecutar.ITRALTAGASTOSEXTTRNI.STDTRNIPARMV();
		sTDTRNIPARMV.setIDINTERNOTERMTN(super.getTerminal());
		sTDTRNIPARMV.setNUMSEC(0);
		sTDTRNIPARMV.setCODTX("GAC28MOU");
		
		contexto.setSTDTRNIPARMV(sTDTRNIPARMV);
		contexto.setTRALTAGASTOSEXTEVTY(tRALTAGASTOSEXTEVTY);
		
		return contexto;
		
		
		
	}
	
	/**
	 * Método para obtener la respuesta del ws a partir de un objeto de petición
	 * @param contexto Objeto de petición
	 * @return respuesta del ws
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRALTAGASTOSEXTTRNI contexto)
	{
		EjecutarResult respuesta= null;
		
		try
		{
			respuesta=(EjecutarResult) servicioWebUtils.ejecutarWS(RegistroGastoServicio.class,contexto);
	}catch(NoControlableException e){
		throw new NoControlableException("Error al invocar servicio web de alta "
				+ "de peticion de efectivo.", e);
	}
	
	return respuesta;
		
	}
	
	/**
	 * Método que inserta un registro de gastos externos
	 */
	public void ejecutarTRN(long acuerdo,BigDecimal importe, String claveGasto, String codigoLinea, String grupo)
	{
		Ejecutar.ITRALTAGASTOSEXTTRNI contexto = initPeticion(acuerdo,importe, claveGasto, codigoLinea, grupo);
		
		EjecutarResult respuesta = ejecutarWS(contexto);
		
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
