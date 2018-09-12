package mx.babel.bansefi.banksystem.cajas.backend;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;
import mx.babel.bansefi.banksystem.cajas.beans.RecepcionEfectivoBean;
import mx.babel.bansefi.banksystem.cajas.webservice.recepcionefectivo.*;

@Component
public class RecepcionEfectivoBackEnd extends BackEndBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 288468534791541921L;
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	/**
	 * Método para inicializar objeto de petición
	 * @return Objeto de petición al ws
	 */
	private Ejecutar.ITRRECPEFCTOFCNASMTR initPeticion(RecepcionEfectivoBean recepcion,int fechaSolic)
	{
		Ejecutar.ITRRECPEFCTOFCNASMTR contexto= new Ejecutar.ITRRECPEFCTOFCNASMTR();
		contexto.setELEVATORPOSITION(0);
		contexto.setSCROLLABLEOCCURS(0);
		
		Ejecutar.ITRRECPEFCTOFCNASMTR.TRRECPEFCTOFCNASMEVT tRRECPEFCTOFCNASMEVT= new Ejecutar.ITRRECPEFCTOFCNASMTR.TRRECPEFCTOFCNASMEVT();
		
		Ejecutar.ITRRECPEFCTOFCNASMTR.TRRECPEFCTOFCNASMEVT.SMSOLCTMONEDAE sMSOLCTMONEDAE = new  Ejecutar.ITRRECPEFCTOFCNASMTR.TRRECPEFCTOFCNASMEVT.SMSOLCTMONEDAE();
		sMSOLCTMONEDAE.setCODNRBEEN(super.getEntidad());
		sMSOLCTMONEDAE.setCODINTERNOUO(super.getSucursal());
		sMSOLCTMONEDAE.setFECHASOLCTSM(fechaSolic);
		sMSOLCTMONEDAE.setCODDISTRIB("U");
		sMSOLCTMONEDAE.setCODPPL("M");
		sMSOLCTMONEDAE.setINDURG(recepcion.getIndicadorUrgencia());
		sMSOLCTMONEDAE.setCODECVSM("F");
		sMSOLCTMONEDAE.setFECHAABASTREC(0);
		sMSOLCTMONEDAE.setHORAABASTREC(0);
		sMSOLCTMONEDAE.setFECHAPROCESOSM(0);
		sMSOLCTMONEDAE.setIMPAUTV(recepcion.getTotalAutorizado());
		sMSOLCTMONEDAE.setIMPPEDIDOV(BigDecimal.ZERO);
		sMSOLCTMONEDAE.setIMPRECBDOV(recepcion.getTotalRecibido());
		sMSOLCTMONEDAE.setIMPCERTFDOV(BigDecimal.ZERO);
		sMSOLCTMONEDAE.setIDINTERNOEMPLEP(super.getUsuarioId());
		sMSOLCTMONEDAE.setVALOROBSERSM(recepcion.getObservacion());
		
		tRRECPEFCTOFCNASMEVT.setSMSOLCTMONEDAE(sMSOLCTMONEDAE);
		
		for(ExistenciaDenominacionBean denominacion : recepcion.getParillaBean().getListaDenominaciones())
		{
			Ejecutar.ITRRECPEFCTOFCNASMTR.TRRECPEFCTOFCNASMEVT.SMSOLCTDESGLSE sMSOLCTDESGLSE = new Ejecutar.ITRRECPEFCTOFCNASMTR.TRRECPEFCTOFCNASMEVT.SMSOLCTDESGLSE();
			sMSOLCTDESGLSE.setFECHASOLCTSM(0);
			sMSOLCTDESGLSE.setINDURG(0);
			sMSOLCTDESGLSE.setCODDSTN(denominacion.getOrigen());
			sMSOLCTDESGLSE.setCODVALORFACIAL(denominacion.getValorFacial());
			sMSOLCTDESGLSE.setIMPAUT(denominacion.getImporteAutorizado());
			sMSOLCTDESGLSE.setIMPPEDIDO(denominacion.getImportePedido());
			sMSOLCTDESGLSE.setIMPRECBDO(denominacion.getImporteRecibido());
			tRRECPEFCTOFCNASMEVT.getSMSOLCTDESGLSE().add(sMSOLCTDESGLSE);
		}
		
		contexto.setTRRECPEFCTOFCNASMEVT(tRRECPEFCTOFCNASMEVT);
		Ejecutar.ITRRECPEFCTOFCNASMTR.SMINICIALV  sMINICIALV = new Ejecutar.ITRRECPEFCTOFCNASMTR.SMINICIALV();
		sMINICIALV.setIMPRECBDOV(recepcion.getTotalRecibido());
		
		contexto.setSMINICIALV(sMINICIALV);
		Ejecutar.ITRRECPEFCTOFCNASMTR.SMSIGNOCTBLE sMSIGNOCTBLE = new Ejecutar.ITRRECPEFCTOFCNASMTR.SMSIGNOCTBLE();
		sMSIGNOCTBLE.setSTDCHAR02("CD");
		
		contexto.setSMSIGNOCTBLE(sMSIGNOCTBLE);
		
		Ejecutar.ITRRECPEFCTOFCNASMTR.STDTRNIPARMV sTDTRNIPARMV = new  Ejecutar.ITRRECPEFCTOFCNASMTR.STDTRNIPARMV();
		sTDTRNIPARMV.setIDINTERNOTERMTN(super.getTerminal());
		sTDTRNIPARMV.setNUMSEC(0);
		sTDTRNIPARMV.setCODTX("VCO05OOU");
		sTDTRNIPARMV.setCODTXDI("CO05");
		
		contexto.setSTDTRNIPARMV(sTDTRNIPARMV);
		
		Ejecutar.ITRRECPEFCTOFCNASMTR.STDAUTORIZAV sTDAUTORIZAV = new Ejecutar.ITRRECPEFCTOFCNASMTR.STDAUTORIZAV();
		sTDAUTORIZAV.setIMPAUT(BigDecimal.ZERO);
		sTDAUTORIZAV.setIMPORTEAR(BigDecimal.ZERO);
		
		contexto.setSTDAUTORIZAV(sTDAUTORIZAV);
		
		return contexto;
		
	}

	
	/**
	 * Método para obtener la respuesta del ws a partir de un objeto de petición
	 * @param contexto Objeto de petición
	 * @return respuesta del ws
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRRECPEFCTOFCNASMTR contexto)
	{
		EjecutarResult respuesta= null;
		
try{
			
			respuesta=(EjecutarResult) servicioWebUtils.ejecutarWS(RecepcionEfectivoServicio.class,contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de recepcion "
					+ "de efectivo.", e);
		}
		
		return respuesta;
	}
	
	/**
	 * Método que inserta una peticion de efectivo
	 */
	
	public void ejecutarTRN(RecepcionEfectivoBean recepcion,int fechaSolic)
	{
		Ejecutar.ITRRECPEFCTOFCNASMTR contexto= initPeticion(recepcion,fechaSolic);
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
