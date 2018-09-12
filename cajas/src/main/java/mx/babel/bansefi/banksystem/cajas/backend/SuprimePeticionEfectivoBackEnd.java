package mx.babel.bansefi.banksystem.cajas.backend;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.cajas.beans.PeticionEfectivoBean;
import mx.babel.bansefi.banksystem.cajas.webservice.suprimepeticionefectivo.*;

@Component
public class SuprimePeticionEfectivoBackEnd extends BackEndBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3553994518394690204L;

	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	/**
	 * Método para inicializar objeto de petición
	 * @return Objeto de petición al ws
	 */
	private Ejecutar.ITRBAJAPETCNEFCTSMTR initPeticion(PeticionEfectivoBean  peticion,int fechaSolic, int fechaProceso){
		
		Ejecutar.ITRBAJAPETCNEFCTSMTR contexto = new Ejecutar.ITRBAJAPETCNEFCTSMTR();
		
		Ejecutar.ITRBAJAPETCNEFCTSMTR.TRBAJAPETCNEFCTSMEVT  tRBAJAPETCNEFCTSMEVT = new Ejecutar.ITRBAJAPETCNEFCTSMTR.TRBAJAPETCNEFCTSMEVT();
		
		Ejecutar.ITRBAJAPETCNEFCTSMTR.TRBAJAPETCNEFCTSMEVT.SMSOLCTMONEDAE sMSOLCTMONEDAE  =  new Ejecutar.ITRBAJAPETCNEFCTSMTR.TRBAJAPETCNEFCTSMEVT.SMSOLCTMONEDAE();
		
		sMSOLCTMONEDAE.setCODNRBEEN(super.getEntidad());
		sMSOLCTMONEDAE.setCODINTERNOUO(super.getSucursal());
		sMSOLCTMONEDAE.setFECHASOLCTSM(fechaSolic);
		sMSOLCTMONEDAE.setCODPPL("M");
		sMSOLCTMONEDAE.setCODDISTRIB("U");
		
		if(peticion.getTipoPeticion()==1)
		{
		sMSOLCTMONEDAE.setINDURG(0);
		}
		else
		{
			sMSOLCTMONEDAE.setINDURG(peticion.getIndicadorUrgencia());	
		}
		sMSOLCTMONEDAE.setCODINTERNOUO1(peticion.getCod_INTERNO_UO_1());
		sMSOLCTMONEDAE.setCODECVSM(peticion.getEstatus());
		sMSOLCTMONEDAE.setFECHAABASTREC(fechaSolic);
		sMSOLCTMONEDAE.setHORAABASTREC(0);
		sMSOLCTMONEDAE.setFECHAPROCESOSM(fechaProceso);
		sMSOLCTMONEDAE.setIMPAUTV(BigDecimal.ZERO);
		sMSOLCTMONEDAE.setIMPPEDIDOV(peticion.getTotalPeticion());
		sMSOLCTMONEDAE.setIMPRECBDOV(BigDecimal.ZERO);
		sMSOLCTMONEDAE.setIMPCERTFDOV(BigDecimal.ZERO);
		
		tRBAJAPETCNEFCTSMEVT.setSMSOLCTMONEDAE(sMSOLCTMONEDAE);
		tRBAJAPETCNEFCTSMEVT.setCODNUMRCOMONEDA("MXN");
		
		contexto.setTRBAJAPETCNEFCTSMEVT(tRBAJAPETCNEFCTSMEVT);
		Ejecutar.ITRBAJAPETCNEFCTSMTR.STDTRNIPARMV  sTDTRNIPARMV = new Ejecutar.ITRBAJAPETCNEFCTSMTR.STDTRNIPARMV();
		
		sTDTRNIPARMV.setIDINTERNOTERMTN(super.getTerminal());
		sTDTRNIPARMV.setNUMSEC(0);
		sTDTRNIPARMV.setCODTX("VCM05MOU");
		sTDTRNIPARMV.setCODTXDI("CO02");
		
		contexto.setSTDTRNIPARMV(sTDTRNIPARMV);
		
		return contexto;
		
		
		
	}

	/**
	 * Método para obtener la respuesta del ws a partir de un objeto de petición
	 * @param contexto Objeto de petición
	 * @return respuesta del ws
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRBAJAPETCNEFCTSMTR contexto)
	{
		
		EjecutarResult respuesta=null;
		try
		{
			respuesta=(EjecutarResult) servicioWebUtils.ejecutarWS(SuprimePeticionEfectivoServicio.class,contexto);
			
		}
		catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de alta "
					+ "de peticion de efectivo.", e);
		}
		
		return respuesta;
	}

	/**
	 * Método que inserta una peticion de efectivo
	 */
	public void ejecutarTRN(PeticionEfectivoBean  peticion,int fechaSolic,int fechaProceso)
	{
		
		Ejecutar.ITRBAJAPETCNEFCTSMTR contexto= initPeticion(peticion,fechaSolic,fechaProceso);
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
		
	}
}
