package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.cuentas.beans.GastosExternosBean;
import mx.babel.bansefi.banksystem.cuentas.beans.TipoGastosBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultatipogasto.ConsultaTipoGastoServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultatipogasto.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultatipogasto.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultatipogasto.ResponseBansefi.OTRYFGASTOHSTCNSTRN.TRYFGASTOHSTCNSEVTZ.YFGASTOEXTLA;

@Component
public class ConsultaTiposGastosBackend extends BackEndBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7139756984844976463L;

	/**
	 * 
	 */
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	/**
	 * Método para inicializar objeto de petición
	 * @return Objeto de petición al ws
	 */
	private Ejecutar.ITRYFGASTOHSTCNSTRN initPeticion( String codLinea, String grupo){
		
		Ejecutar.ITRYFGASTOHSTCNSTRN contexto = new Ejecutar.ITRYFGASTOHSTCNSTRN();
		
		Ejecutar.ITRYFGASTOHSTCNSTRN.TRYFGASTOHSTCNSEVTY tRYFGASTOHSTCNSEVTY = new Ejecutar.ITRYFGASTOHSTCNSTRN.TRYFGASTOHSTCNSEVTY();
		
		Ejecutar.ITRYFGASTOHSTCNSTRN.TRYFGASTOHSTCNSEVTY.YFGASTOEXTP yFGASTOEXTP = new Ejecutar.ITRYFGASTOHSTCNSTRN.TRYFGASTOHSTCNSEVTY.YFGASTOEXTP();
	
		yFGASTOEXTP.setCODNRBEEN(super.getSucursal());
		
		yFGASTOEXTP.setCODLINEA(codLinea);
		
		yFGASTOEXTP.setIDGRPPD(grupo);
		
		tRYFGASTOHSTCNSEVTY.setYFGASTOEXTP(yFGASTOEXTP);
		
		Ejecutar.ITRYFGASTOHSTCNSTRN.STDTRNIPARMV sTDTRNIPARMV = new  Ejecutar.ITRYFGASTOHSTCNSTRN.STDTRNIPARMV();
		
		sTDTRNIPARMV.setIDINTERNOTERMTN(super.getTerminal());
		sTDTRNIPARMV.setCODTX("GAC99MOU");
		
		contexto.setSCROLLABLEOCCURS(200);
		contexto.setSTDTRNIPARMV(sTDTRNIPARMV);
		contexto.setTRYFGASTOHSTCNSEVTY(tRYFGASTOHSTCNSEVTY);
		
		super.initialize(contexto);
		
		return contexto;
		
	}
	
	
	/**
	 * Método para obtener la respuesta del ws a partir de un objeto de petición
	 * @param contexto Objeto de petición
	 * @return respuesta del ws
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRYFGASTOHSTCNSTRN contexto)
	{
		EjecutarResult respuesta= null;
		try{
			
			respuesta= (EjecutarResult)servicioWebUtils.ejecutarWS(ConsultaTipoGastoServicio.class,contexto);
		
	}catch(NoControlableException e){
		throw new NoControlableException("Error al invocar servicio web de consulta "
				+ "de tipo de gastos.", e);
	}
	return respuesta;
	}
	
	
	public void ejecutarTRN(String codLinea, String grupo , GastosExternosBean gastosEx)
	{
		List<TipoGastosBean> lista= new ArrayList<TipoGastosBean>();
		Ejecutar.ITRYFGASTOHSTCNSTRN contexto= initPeticion(  codLinea, grupo);
		EjecutarResult respuesta= ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return;
			}
		}
		
		if (verificaResponseBansefi(respuesta)) {
		
			for (YFGASTOEXTLA item : respuesta.getResponseBansefi().getOTRYFGASTOHSTCNSTRN().getTRYFGASTOHSTCNSEVTZ().getYFGASTOEXTLA())
			{
				if(item.getCODGASTOEXT().trim().length()>0)
				{				
				TipoGastosBean tipoGasto= new TipoGastosBean();
				
				tipoGasto.setCodigoGasto( item.getCODGASTOEXT() );
				tipoGasto.setDescripcion(item.getTXTGASTOEXT());
	                
				 lista.add(tipoGasto);
				}
			}
			
			gastosEx.setlTipoGasto(new ArrayList<TipoGastosBean>());
			gastosEx.setlTipoGasto(lista);
		}
		
		
		
	}
	/**
	 * Función que valida que la respues del servidor no este vacía.
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
