package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.cuentas.beans.GastoBean;
import mx.babel.bansefi.banksystem.cuentas.beans.GastosExternosBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultagastosexternos.ConsultaGastosExternosServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultagastosexternos.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultagastosexternos.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultagastosexternos.ResponseBansefi.OTRCONSGASTOSEHSTRNO.TRCONSGASTOSEHSEVTZ.ACGASTOSEXTLD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaGastosExternosBackend  extends BackEndBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8777532814810265637L;
	/**
	 * 
	 */
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	/**
	 * Método para inicializar objeto de petición
	 * @return Objeto de petición al ws
	 */
	private Ejecutar.ITRCONSGASTOSEHSTRNI initPeticion(long acuerdo)
	{
		Ejecutar.ITRCONSGASTOSEHSTRNI contexto= new Ejecutar.ITRCONSGASTOSEHSTRNI();
		Ejecutar.ITRCONSGASTOSEHSTRNI.TRCONSGASTOSEHSEVTY  tRCONSGASTOSEHSEVTY = new Ejecutar.ITRCONSGASTOSEHSTRNI.TRCONSGASTOSEHSEVTY();
		tRCONSGASTOSEHSEVTY.setCODNRBEEN(super.getEntidad());
		tRCONSGASTOSEHSEVTY.setNUMSECAC(acuerdo);
		tRCONSGASTOSEHSEVTY.setCODNUMRCOMONEDA("MXN");
		
		Ejecutar.ITRCONSGASTOSEHSTRNI.STDTRNIPARMV  sTDTRNIPARMV = new Ejecutar.ITRCONSGASTOSEHSTRNI.STDTRNIPARMV();
		sTDTRNIPARMV.setIDINTERNOTERMTN(super.getTerminal());
		sTDTRNIPARMV.setNUMSEC(0);
		sTDTRNIPARMV.setCODTX("GAC15COU");
		
		contexto.setELEVATORPOSITION(0);
		contexto.setSCROLLABLEOCCURS(50);
		contexto.setTRCONSGASTOSEHSEVTY(tRCONSGASTOSEHSEVTY);
		contexto.setSTDTRNIPARMV(sTDTRNIPARMV);
		
		return contexto;
		
	}
	

	/**
	 * Método para obtener la respuesta del ws a partir de un objeto de petición
	 * @param contexto Objeto de petición
	 * @return respuesta del ws
	 */
	private EjecutarResult  ejecutarWS(Ejecutar.ITRCONSGASTOSEHSTRNI contexto)
	{
		EjecutarResult respuesta= null;
		
	try
	{
		respuesta= (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaGastosExternosServicio.class,contexto) ;
	}catch(NoControlableException e){
		throw new NoControlableException("Error al invocar servicio web de consulta "
				+ "de tipo de gastos.", e);
	}
	return respuesta;
		
	}
	
	public void ejecutarTRN(long acuerdo , GastosExternosBean gastosEx)
	{
		List<GastoBean> parrillaGasto= new ArrayList<GastoBean>();
		
		Ejecutar.ITRCONSGASTOSEHSTRNI contexto= initPeticion(acuerdo);
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
		
		if (verificaResponseBansefi(respuesta)) {
			
			Long cuentaAsociada = respuesta.getResponseBansefi().getOTRCONSGASTOSEHSTRNO().getTRCONSGASTOSEHSEVTZ().getNUMSECAC2();
			String plaza = respuesta.getResponseBansefi().getOTRCONSGASTOSEHSTRNO().getTRCONSGASTOSEHSEVTZ().getCODPLZBANCARIA();
			String digito= respuesta.getResponseBansefi().getOTRCONSGASTOSEHSTRNO().getTRCONSGASTOSEHSEVTZ().getCODDIGCRUO();
			
			for ( ACGASTOSEXTLD item : respuesta.getResponseBansefi().getOTRCONSGASTOSEHSTRNO().getTRCONSGASTOSEHSEVTZ().getACGASTOSEXTLD())
			{
				GastoBean gastos= new GastoBean();
				
				if(item.getDESCRGTOV().getSTDCHAR20().trim().length()>0)
				{
			    gastos.setDescripcionGasto(item.getDESCRGTOV().getSTDCHAR20());
			    gastos.setImporte(item.getIMPTEGTOV().get(0).getSTDDEC15Y2());
			    gastos.setCodigoDescripcion(item.getCODGTOV().get(0).getSTDCHAR01());
			    gastos.setEstatusGastos(item.getDESCRIND1V().get(0).getSTDCHAR15());
			    gastos.setNumSecuencia(item.getNUMSEC());
				gastos.setInd1(item.getIND1());
				gastos.setInd2(item.getIND2());
				gastos.setInd3(item.getIND3());
				gastos.setCuentaAsociada(cuentaAsociada);
				gastos.setPlazaBancaria(plaza);
				gastos.setCodDig(digito);
				gastos.setMoneda(item.getCODNUMRCOMONEDA());
				
			    
					  if(item.getDESCRIND1V().get(0).getSTDCHAR15().equals("IMPAGADO/PENDIE"))
					  {
						  gastos.setImputar(true);
						  gastos.setEliminar(true);
					  }
					  else
					  {
						  gastos.setImputar(false);
						  gastos.setEliminar(false);
					  }
					  parrillaGasto.add(gastos);
					 
				}
			}
			
			gastosEx.setParrillaGastos(parrillaGasto);
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
