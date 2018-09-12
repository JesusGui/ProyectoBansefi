package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.eliminaciongastoexterno.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.eliminaciongastoexterno.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.eliminaciongastoexterno.EliminacionGastoExternoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EliminacionGastoExternoBackend extends BackEndBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1545632251360053382L;
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	
	/**
	 * Método para inicializar objeto de petición
	 * @return Objeto de petición al ws
	 */
	private Ejecutar.ITRBAJAGASTOSEXTTRNI initPeticion(long acuerdo, int secuenciaGasto)
	{
		Ejecutar.ITRBAJAGASTOSEXTTRNI contexto= new Ejecutar.ITRBAJAGASTOSEXTTRNI();
		
		
		Ejecutar.ITRBAJAGASTOSEXTTRNI.TRBAJAGASTOSEXTEVTY  tRBAJAGASTOSEXTEVTY = new Ejecutar.ITRBAJAGASTOSEXTTRNI.TRBAJAGASTOSEXTEVTY();
		tRBAJAGASTOSEXTEVTY.setCODNRBEEN(super.getEntidad());
		tRBAJAGASTOSEXTEVTY.setNUMSECAC(acuerdo);
		tRBAJAGASTOSEXTEVTY.setCODNUMRCOMONEDA("MXN");
		tRBAJAGASTOSEXTEVTY.setNUMSEC(secuenciaGasto);
		
		Ejecutar.ITRBAJAGASTOSEXTTRNI.STDTRNIPARMV sTDTRNIPARMV = new Ejecutar.ITRBAJAGASTOSEXTTRNI.STDTRNIPARMV();
		sTDTRNIPARMV.setIDINTERNOTERMTN(super.getTerminal());
		sTDTRNIPARMV.setNUMSEC(0);
		sTDTRNIPARMV.setCODTX("GAC90OOU");
		
		contexto.setSTDTRNIPARMV(sTDTRNIPARMV);
		contexto.setTRBAJAGASTOSEXTEVTY(tRBAJAGASTOSEXTEVTY);
		
		return contexto;
		
	}
	
	/**
	 * Método para obtener la respuesta del ws a partir de un objeto de petición
	 * @param contexto Objeto de petición
	 * @return respuesta del ws
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRBAJAGASTOSEXTTRNI contexto)
	{
		EjecutarResult respuesta = null;
		
		try
		{
			respuesta= (EjecutarResult) servicioWebUtils.ejecutarWS(EliminacionGastoExternoServicio.class,contexto);
	}catch(NoControlableException e){
		throw new NoControlableException("Error al invocar servicio web de alta "
				+ "de peticion de efectivo.", e);
	}
	
	return respuesta;
		
	}
	
	/**
	 * Método que inserta un registro de gastos externos
	 */
	public void ejecutarTRN(long acuerdo, int secuenciaGasto)
	{
		Ejecutar.ITRBAJAGASTOSEXTTRNI contexto = initPeticion(acuerdo,secuenciaGasto);
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
