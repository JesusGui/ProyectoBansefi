package mx.babel.bansefi.banksystem.cajas.backend;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.babel.bansefi.banksystem.cajas.webservice.altapeticionefectivo.*;
import mx.babel.bansefi.banksystem.cajas.beans.PeticionEfectivoBean;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;
@Component
public class AltaPeticionEfectivoBackEnd extends BackEndBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 156122575775271087L;
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	
	

	/**
	 * Método para inicializar objeto de petición
	 * @return Objeto de petición al ws
	 */
	private Ejecutar.ITRALTASOLCTEFCTSMTR initPeticion(PeticionEfectivoBean  peticion,int fechaSolic){
		String facial;
		
		Ejecutar.ITRALTASOLCTEFCTSMTR contexto= new Ejecutar.ITRALTASOLCTEFCTSMTR();
		
		contexto.setELEVATORPOSITION(0);
		contexto.setSCROLLABLEOCCURS(0);
		Ejecutar.ITRALTASOLCTEFCTSMTR.TRALTASOLCTEFCTSMEVT tRALTASOLCTEFCTSMEVT= new Ejecutar.ITRALTASOLCTEFCTSMTR.TRALTASOLCTEFCTSMEVT();
		
		Ejecutar.ITRALTASOLCTEFCTSMTR.TRALTASOLCTEFCTSMEVT.SMURGENTEV sMURGENTEV = new Ejecutar.ITRALTASOLCTEFCTSMTR.TRALTASOLCTEFCTSMEVT.SMURGENTEV();

		if(peticion.getTipoPeticion()==2)
		{
				sMURGENTEV.setSTDCHAR01("X");
		}
		else{sMURGENTEV.setSTDCHAR01("");}
		
		tRALTASOLCTEFCTSMEVT.setSMURGENTEV(sMURGENTEV);
		
		Ejecutar.ITRALTASOLCTEFCTSMTR.TRALTASOLCTEFCTSMEVT.SMSOLCTMONEDAE sMSOLCTMONEDAE = new Ejecutar.ITRALTASOLCTEFCTSMTR.TRALTASOLCTEFCTSMEVT.SMSOLCTMONEDAE();
		
		
		
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
		
		sMSOLCTMONEDAE.setFECHAABASTREC(0);
		sMSOLCTMONEDAE.setHORAABASTREC(0);
		sMSOLCTMONEDAE.setFECHAPROCESOSM(0);
		sMSOLCTMONEDAE.setIMPAUTV(BigDecimal.ZERO);
		sMSOLCTMONEDAE.setIMPPEDIDOV(peticion.getTotalPeticion());
		sMSOLCTMONEDAE.setIMPRECBDOV(BigDecimal.ZERO);
		sMSOLCTMONEDAE.setIMPCERTFDOV(BigDecimal.ZERO);
		sMSOLCTMONEDAE.setVALOROBSERSM(peticion.getObservacion());
		
		
		
		
		
		tRALTASOLCTEFCTSMEVT.setSMSOLCTMONEDAE(sMSOLCTMONEDAE);
		
		
		for(ExistenciaDenominacionBean denominacion : peticion.getParillaBean().getListaDenominaciones())
		{
			
			if(!denominacion.getImportePedido().equals(BigDecimal.ZERO))
			{
				
				Ejecutar.ITRALTASOLCTEFCTSMTR.TRALTASOLCTEFCTSMEVT.SMSOLCTDESGLSE sMSOLCTDESGLSE = new Ejecutar.ITRALTASOLCTEFCTSMTR.TRALTASOLCTEFCTSMEVT.SMSOLCTDESGLSE();
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
				
				tRALTASOLCTEFCTSMEVT.getSMSOLCTDESGLSE().add(sMSOLCTDESGLSE);		
				
			}
			
			
			
		}
		
		contexto.setTRALTASOLCTEFCTSMEVT(tRALTASOLCTEFCTSMEVT);
		Ejecutar.ITRALTASOLCTEFCTSMTR.STDTRNIPARMV sTDTRNIPARMV = new Ejecutar.ITRALTASOLCTEFCTSMTR.STDTRNIPARMV();
		sTDTRNIPARMV.setIDINTERNOTERMTN(super.getTerminal());
		sTDTRNIPARMV.setNUMSEC(0);
		sTDTRNIPARMV.setCODTX("VCM03MOU");
		sTDTRNIPARMV.setCODTXDI("CO06");
		
		
		contexto.setSTDTRNIPARMV(sTDTRNIPARMV);
		
		return contexto;
	}
	
	/**
	 * Método para obtener la respuesta del ws a partir de un objeto de petición
	 * @param contexto Objeto de petición
	 * @return respuesta del ws
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRALTASOLCTEFCTSMTR contexto)
	{
		EjecutarResult respuesta= null;
		try{
			
			respuesta=(EjecutarResult) servicioWebUtils.ejecutarWS(AltaPeticionEfectivoServicio.class,contexto);
		}catch(NoControlableException e){
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
		Ejecutar.ITRALTASOLCTEFCTSMTR contexto=initPeticion(peticion,fechaSolic);
		EjecutarResult respuesta =ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return;
			}
		}
		
	peticion.setIndicadorUrgencia(respuesta.getResponseBansefi().getOTRALTASOLCTEFCTSMTR().getTRALTASOLCTEFCTSMEVT().getSMSOLCTMONEDAE().getINDURG());
	
	}
}
