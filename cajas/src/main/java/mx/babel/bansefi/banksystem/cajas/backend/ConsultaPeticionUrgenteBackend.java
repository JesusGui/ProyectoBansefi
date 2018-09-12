package mx.babel.bansefi.banksystem.cajas.backend;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.backends.ConsultaCatalogoBackend;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.cajas.beans.AutorizacionPeticionUrgenteBean;
import mx.babel.bansefi.banksystem.cajas.beans.ConsultaPeticionUrgenteBean;
import mx.babel.bansefi.banksystem.cajas.webservice.consultapeticionurgente.ConsultaPeticionUrgenteServicio;
import mx.babel.bansefi.banksystem.cajas.webservice.consultapeticionurgente.Ejecutar;
import mx.babel.bansefi.banksystem.cajas.webservice.consultapeticionurgente.EjecutarResult;
import mx.babel.bansefi.banksystem.cajas.webservice.consultapeticionurgente.ResponseBansefi;
import mx.babel.bansefi.banksystem.cajas.webservice.consultapeticionurgente.ResponseBansefi.OTRCONSSOLCTINDSMTRN.TRCONSSOLCTINDSMEVT.SMSOLCTDESGLSE;

@Component
public class ConsultaPeticionUrgenteBackend extends BackEndBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2865184071373851416L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	@Autowired
	ConsultaCatalogoBackend catalogoBackend;
	
	private AutorizacionPeticionUrgenteBean autorizacionPeticionUrgenteBean;
	
	private List<ConsultaPeticionUrgenteBean> listaPeticiones;
	
	public AutorizacionPeticionUrgenteBean getAutorizacionPeticionUrgenteBean() {
		return autorizacionPeticionUrgenteBean;
	}

	public void setAutorizacionPeticionUrgenteBean(
			AutorizacionPeticionUrgenteBean autorizacionPeticionUrgenteBean) {
		this.autorizacionPeticionUrgenteBean = autorizacionPeticionUrgenteBean;
	}

	public List<ConsultaPeticionUrgenteBean> getListaPeticiones() {
		return listaPeticiones;
	}

	public void setListaPeticiones(List<ConsultaPeticionUrgenteBean> listaPeticiones) {
		this.listaPeticiones = listaPeticiones;
	}

	public void ejecutarTRN(int fechaSolicitud, String centro, int noUrgente){
		Ejecutar.ITRCONSSOLCTINDSMTRN contexto = initPeticion(fechaSolicitud, centro, noUrgente);
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if(ce.getRtncod() == RETURN_COD_SIN_DATOS){
				throw ce;
			}
		}
		
		if(verificaResponseBansefi(respuesta)){
			getListaDatos(respuesta.getResponseBansefi());
		}
	}
	
	private Ejecutar.ITRCONSSOLCTINDSMTRN initPeticion(int fechaSolicitud, String centro, int noUrgente){
		
		Ejecutar.ITRCONSSOLCTINDSMTRN contexto = new Ejecutar.ITRCONSSOLCTINDSMTRN();
		
		contexto.setELEVATORPOSITION(0);
		contexto.setSCROLLABLEOCCURS(0);
		
		Ejecutar.ITRCONSSOLCTINDSMTRN.TRCONSSOLCTINDSMEVT nivel1_1 = new Ejecutar.ITRCONSSOLCTINDSMTRN.TRCONSSOLCTINDSMEVT();
		Ejecutar.ITRCONSSOLCTINDSMTRN.STDTRNIPARMV nivel1_2 = new Ejecutar.ITRCONSSOLCTINDSMTRN.STDTRNIPARMV();
		
		Ejecutar.ITRCONSSOLCTINDSMTRN.TRCONSSOLCTINDSMEVT.SMSOLCTMONEDAP nivel2_1 = new Ejecutar.ITRCONSSOLCTINDSMTRN.TRCONSSOLCTINDSMEVT.SMSOLCTMONEDAP(); 
		
		nivel2_1.setCODNRBEEN(super.getEntidad());
		nivel2_1.setCODINTERNOUO(centro);
		nivel2_1.setFECHASOLCTSM(fechaSolicitud);
		nivel2_1.setCODPPL("M");
		nivel2_1.setCODDISTRIB("U");
		nivel2_1.setINDURG(noUrgente);
		nivel2_1.setCODINTERNOUO1(super.getSucursal());
		
		nivel1_1.setSMSOLCTMONEDAP(nivel2_1);
		
		nivel1_2.setIDINTERNOTERMTN(super.getTerminal());
		nivel1_2.setNUMSEC(0);
		nivel1_2.setCODTX("VCC68COU");
		nivel1_2.setCODTXDI("CO04");
		
		contexto.setTRCONSSOLCTINDSMEVT(nivel1_1);
		contexto.setSTDTRNIPARMV(nivel1_2);
		
		super.initialize(contexto);
		
		return contexto;
	}
	
	private EjecutarResult ejecutarWS(Ejecutar.ITRCONSSOLCTINDSMTRN contexto){
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaPeticionUrgenteServicio.class, contexto);
		}catch (NoControlableException nce){
			throw new NoControlableException("Error al invocar servicio web de consulta"
					+ " de petición urgente.", nce);
		}
		
		return respuesta;
	}
	
	/**
	 * Función que valida que la respues del servidor no este vacía. 
	 *  
	 * @param respuesta Objeto respuesta del servicio web
	 * @return <code>true</code> si la respuesta no esta vacía.
	 */
	private Boolean verificaResponseBansefi(EjecutarResult respuesta){
		Boolean noNulo = false;
		if(respuesta != null && respuesta.getResponseBansefi() != null){
			noNulo = true;
		}
		
		return noNulo;
	}
	
	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con los datos
	 * de cuentas.
	 * 
	 * @param response Respuesta Bansefi con datos de cuentas
	 * @return <code>true</code> si la respuesta Bansefi contiene los datos de alta
	 */
	private Boolean verificaRespuesta(ResponseBansefi respuesta){
		Boolean noNulo = false;
		
		if(respuesta != null && respuesta.getOTRCONSSOLCTINDSMTRN() != null
				&& respuesta.getOTRCONSSOLCTINDSMTRN().getSTDTRNMSJPARMV() != null
				&& respuesta.getOTRCONSSOLCTINDSMTRN().getSTDTRNOPARMV() != null
				&& respuesta.getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT() != null
				&& respuesta.getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTDESGLSE() != null
				&& respuesta.getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTDESGLSP() != null
				&& respuesta.getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTMONEDAE() != null){
			noNulo = true;
		}
		
		return noNulo;
	}
	
	private void getListaDatos(ResponseBansefi respuesta){
		
		this.autorizacionPeticionUrgenteBean = new AutorizacionPeticionUrgenteBean();
		this.listaPeticiones = new ArrayList<ConsultaPeticionUrgenteBean>();
		
		if(verificaRespuesta(respuesta)){
			autorizacionPeticionUrgenteBean.setEntidad(respuesta.getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTMONEDAE().getCODNRBEEN().trim());
			autorizacionPeticionUrgenteBean.setCentro(respuesta.getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTMONEDAE().getCODINTERNOUO().trim());
			autorizacionPeticionUrgenteBean.setFechaSolicitud(respuesta.getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTMONEDAE().getFECHASOLCTSM());
			autorizacionPeticionUrgenteBean.setTipoMoneda(respuesta.getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTMONEDAE().getCODPPL().trim());
			autorizacionPeticionUrgenteBean.setCodigoDistribucion(respuesta.getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTMONEDAE().getCODDISTRIB().trim());
			autorizacionPeticionUrgenteBean.setNoUrgente(respuesta.getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTMONEDAE().getINDURG());
			autorizacionPeticionUrgenteBean.setCentroControlador(respuesta.getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTMONEDAE().getCODINTERNOUO1().trim());
			autorizacionPeticionUrgenteBean.setFechaAbastecimiento(respuesta.getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTMONEDAE().getFECHAABASTREC());
			autorizacionPeticionUrgenteBean.setFechaProceso(respuesta.getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTMONEDAE().getFECHAPROCESOSM());
			autorizacionPeticionUrgenteBean.setImporteAutorizado(respuesta.getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTMONEDAE().getIMPAUTV());
			autorizacionPeticionUrgenteBean.setImportePedido(respuesta.getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTMONEDAE().getIMPPEDIDOV());
			autorizacionPeticionUrgenteBean.setImporteRecibido(respuesta.getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTMONEDAE().getIMPRECBDOV());
			autorizacionPeticionUrgenteBean.setImporteCertificado(respuesta.getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTMONEDAE().getIMPCERTFDOV());
			autorizacionPeticionUrgenteBean.setIdEmpleado(respuesta.getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTMONEDAE().getIDINTERNOEMPLEP().trim());
			autorizacionPeticionUrgenteBean.setObservaciones(respuesta.getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTMONEDAE().getVALOROBSERSM().trim());
			
			for(CatalogoBean catalogoBean : catalogoBackend.ejecutarTRN(CatalogoEnum.ESTATUS_PETICION)){
				if(catalogoBean.getClaveFila().trim().equals(respuesta.getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTMONEDAE().getCODECVSM().trim())){
					autorizacionPeticionUrgenteBean.setEstatusPeticionL(catalogoBean.getDescripcionL().trim());
					autorizacionPeticionUrgenteBean.setEstatusPeticionC(catalogoBean.getClaveFila().trim());
					break;
				}
			}
			
			for(SMSOLCTDESGLSE desglose : respuesta.getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTDESGLSE()){
				
				if(!desglose.getCODVALORFACIAL().trim().isEmpty()
						&& !desglose.getCODDSTN().trim().isEmpty()){
					
					ConsultaPeticionUrgenteBean consultaPeticionUrgenteBean = new ConsultaPeticionUrgenteBean();
					
					consultaPeticionUrgenteBean.setEntidad(desglose.getCODNRBEEN().trim());
					consultaPeticionUrgenteBean.setCentro(desglose.getCODINTERNOUO().trim());
					consultaPeticionUrgenteBean.setFechaSolicitud(desglose.getFECHASOLCTSM());
					consultaPeticionUrgenteBean.setCodigoMoneda(desglose.getCODPPL().trim());
					consultaPeticionUrgenteBean.setCodigoDistribucion(desglose.getCODDISTRIB().trim());
					consultaPeticionUrgenteBean.setNoUrgente(desglose.getINDURG());
					consultaPeticionUrgenteBean.setCentroControlador(desglose.getCODINTERNOUO1().trim());
					consultaPeticionUrgenteBean.setOrigen(desglose.getCODDSTN().trim());
					consultaPeticionUrgenteBean.setCodigoValorFacial(desglose.getCODVALORFACIAL().trim());
					consultaPeticionUrgenteBean.setImporteAutorizado(desglose.getIMPAUT());
					consultaPeticionUrgenteBean.setImportePedido(desglose.getIMPPEDIDO());
					consultaPeticionUrgenteBean.setImporteRecibido(desglose.getIMPRECBDO());
										
					this.listaPeticiones.add(consultaPeticionUrgenteBean);
				}
			}
		}
	}
}
