package mx.babel.bansefi.banksystem.cajas.backend;

import java.math.BigDecimal;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.babel.bansefi.banksystem.cajas.beans.PeticionEfectivoBean;
import mx.babel.bansefi.banksystem.cajas.webservice.modificapeticionefectivo.*;

@Component
public class ModificaPeticionEfectivoBackEnd extends BackEndBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4156982081758546014L;
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	/**
	 * Método para inicializar objeto de petición
	 * @return Objeto de petición al ws
	 */
	private Ejecutar.ITRMDFSOLCTEFCTSMTRN initPeticion(PeticionEfectivoBean  peticion,int fechaSolic){
		
		
		Ejecutar.ITRMDFSOLCTEFCTSMTRN contexto= new Ejecutar.ITRMDFSOLCTEFCTSMTRN();
		
		contexto.setELEVATORPOSITION(0);
		contexto.setSCROLLABLEOCCURS(0);
		
		Ejecutar.ITRMDFSOLCTEFCTSMTRN.TRMDFSOLCTEFCTSMEVT tRMDFSOLCTEFCTSMEVT = new Ejecutar.ITRMDFSOLCTEFCTSMTRN.TRMDFSOLCTEFCTSMEVT();
		
		Ejecutar.ITRMDFSOLCTEFCTSMTRN.TRMDFSOLCTEFCTSMEVT.SMSOLCTMONEDAE  sMSOLCTMONEDAE = new Ejecutar.ITRMDFSOLCTEFCTSMTRN.TRMDFSOLCTEFCTSMEVT.SMSOLCTMONEDAE();
		
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
		
		sMSOLCTMONEDAE.setCODECVSM(peticion.getEstatus());
		sMSOLCTMONEDAE.setFECHAABASTREC(0);
		sMSOLCTMONEDAE.setHORAABASTREC(0);
		sMSOLCTMONEDAE.setFECHAPROCESOSM(0);
		sMSOLCTMONEDAE.setIMPAUTV(BigDecimal.ZERO);
		sMSOLCTMONEDAE.setIMPPEDIDOV(peticion.getTotalPeticion());
		sMSOLCTMONEDAE.setIMPRECBDOV(BigDecimal.ZERO);
		sMSOLCTMONEDAE.setIMPCERTFDOV(BigDecimal.ZERO);
		sMSOLCTMONEDAE.setVALOROBSERSM(peticion.getObservacion());
		
		tRMDFSOLCTEFCTSMEVT.setSMSOLCTMONEDAE(sMSOLCTMONEDAE);
		for(ExistenciaDenominacionBean denominacion : peticion.getParillaBean().getListaDenominaciones())
		{
			
			Ejecutar.ITRMDFSOLCTEFCTSMTRN.TRMDFSOLCTEFCTSMEVT.SMSOLCTDESGLSE sMSOLCTDESGLSE = new Ejecutar.ITRMDFSOLCTEFCTSMTRN.TRMDFSOLCTEFCTSMEVT.SMSOLCTDESGLSE();
			
sMSOLCTDESGLSE.setFECHASOLCTSM(0);
			
			
			if(peticion.getTipoPeticion()==1)
			{
				sMSOLCTDESGLSE.setINDURG(0);
			}
			else
			{
				sMSOLCTDESGLSE.setINDURG(peticion.getIndicadorUrgencia());	
			}
			sMSOLCTDESGLSE.setCODDSTN(denominacion.getOrigen());
			
					
			
			sMSOLCTDESGLSE.setCODVALORFACIAL(denominacion.getValorFacial());
			sMSOLCTDESGLSE.setIMPAUT(BigDecimal.ZERO);
			sMSOLCTDESGLSE.setIMPPEDIDO(denominacion.getImportePedido());
			sMSOLCTDESGLSE.setIMPRECBDO(BigDecimal.ZERO);
		
			tRMDFSOLCTEFCTSMEVT.getSMSOLCTDESGLSE().add(sMSOLCTDESGLSE);
			
		}
		contexto.setTRMDFSOLCTEFCTSMEVT(tRMDFSOLCTEFCTSMEVT);
		Ejecutar.ITRMDFSOLCTEFCTSMTRN.STDTRNIPARMV sTDTRNIPARMV = new Ejecutar.ITRMDFSOLCTEFCTSMTRN.STDTRNIPARMV();
		sTDTRNIPARMV.setIDINTERNOTERMTN(super.getTerminal());
		sTDTRNIPARMV.setNUMSEC(0);
		sTDTRNIPARMV.setCODTX("VCM22MOU");
		sTDTRNIPARMV.setCODTXDI("CO03");
		
		contexto.setSTDTRNIPARMV(sTDTRNIPARMV);
		
		return contexto;
		
		
		
	}

	/**
	 * Método para obtener la respuesta del ws a partir de un objeto de petición
	 * @param contexto Objeto de petición
	 * @return respuesta del ws
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRMDFSOLCTEFCTSMTRN contexto)
	{
		EjecutarResult respuesta= null;
		
		try
		{
			respuesta=(EjecutarResult) servicioWebUtils.ejecutarWS(ModificaPeticionEfectivoServicio.class,contexto);
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
	public void ejecutarTRN(PeticionEfectivoBean  peticion,int fechaSolic)
	{
		Ejecutar.ITRMDFSOLCTEFCTSMTRN contexto= initPeticion(peticion,fechaSolic);
		
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
