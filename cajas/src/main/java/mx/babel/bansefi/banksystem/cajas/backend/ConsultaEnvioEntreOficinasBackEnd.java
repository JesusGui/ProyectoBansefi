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
import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.cajas.beans.EnvioEntreOficinasBean;
import mx.babel.bansefi.banksystem.cajas.beans.RecepcionEntreOficinasBean;
import mx.babel.bansefi.banksystem.cajas.webservice.consultaenvioentreoficinas.Ejecutar;
import mx.babel.bansefi.banksystem.cajas.webservice.consultaenvioentreoficinas.EjecutarResult;
import mx.babel.bansefi.banksystem.cajas.webservice.consultaenvioentreoficinas.ConsultaEnvioEntreOficinasServicio;
import mx.babel.bansefi.banksystem.cajas.webservice.consultaenvioentreoficinas.ResponseBansefi;
import mx.babel.bansefi.banksystem.cajas.webservice.consultaenvioentreoficinas.ResponseBansefi.OTRSOLCTCONTINDSMTRN.TRSOLCTCONTINDSMEVT.SMSOLCTDESGLSE;

@Component
public class ConsultaEnvioEntreOficinasBackEnd extends BackEndBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9196560538448121741L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	@Autowired
	CatalogoUtils catalogoUtils;
	
	public void ejecutarTRN(EnvioEntreOficinasBean envioEntreOficinasBean){
		Ejecutar.ITRSOLCTCONTINDSMTRN contexto = initPeticion(envioEntreOficinasBean);
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch(ControlableException ce){
			if(ce.getRtncod() != 1){
				throw ce;
			}
		}
		
		if(verificaRespuesta(respuesta)){
			getDatosParrilla(respuesta.getResponseBansefi(), envioEntreOficinasBean);
			getDatosEnvio(respuesta.getResponseBansefi(), envioEntreOficinasBean);
		}
		
	}
	
	public EnvioEntreOficinasBean ejecutarTRN1(EnvioEntreOficinasBean envioEntreOficinasBean){
		Ejecutar.ITRSOLCTCONTINDSMTRN contexto = initPeticion(envioEntreOficinasBean);
		EjecutarResult respuesta = ejecutarWS(contexto);
		EnvioEntreOficinasBean envio = new EnvioEntreOficinasBean();
		try{
			super.verificaRespuesta(respuesta);
		}catch(ControlableException ce){
			if(ce.getRtncod() != 1){
				throw ce;
			}
		}
		
		if(verificaRespuesta(respuesta)){
			envio = getDatosEnvio(respuesta.getResponseBansefi());
		}
		return envio;
	}
	
	public RecepcionEntreOficinasBean ejecutarTRN2(final Integer fechaEnvio, final String centro){
		RecepcionEntreOficinasBean bean = null;
		Ejecutar.ITRSOLCTCONTINDSMTRN contexto = initPeticion2(fechaEnvio, centro);
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch(ControlableException ce){
			if(ce.getRtncod() != RETURN_COD_OK){
				throw ce;
			}
		}
		
		if(verificaRespuesta(respuesta)){
			bean = new RecepcionEntreOficinasBean();
			bean.setListaDenominaciones(getDatosParrilla2(respuesta.getResponseBansefi()));
			getDatosEnvio2(respuesta.getResponseBansefi(), bean);
		}
		
		return bean;
	}
	
	private Ejecutar.ITRSOLCTCONTINDSMTRN initPeticion(EnvioEntreOficinasBean envioEntreOficinasBean){
		Ejecutar.ITRSOLCTCONTINDSMTRN contexto = new Ejecutar.ITRSOLCTCONTINDSMTRN();
		
		Ejecutar.ITRSOLCTCONTINDSMTRN.TRSOLCTCONTINDSMEVT nivel1_1 = new Ejecutar.ITRSOLCTCONTINDSMTRN.TRSOLCTCONTINDSMEVT();
		Ejecutar.ITRSOLCTCONTINDSMTRN.STDTRNIPARMV nivel1_2 = new Ejecutar.ITRSOLCTCONTINDSMTRN.STDTRNIPARMV();
		
		Ejecutar.ITRSOLCTCONTINDSMTRN.TRSOLCTCONTINDSMEVT.SMSOLCTMONEDAE nivel2_1 = new Ejecutar.ITRSOLCTCONTINDSMTRN.TRSOLCTCONTINDSMEVT.SMSOLCTMONEDAE();
		
		nivel2_1.setCODNRBEEN(super.getEntidad());
		nivel2_1.setCODINTERNOUO(super.getSucursal());
		nivel2_1.setFECHASOLCTSM(envioEntreOficinasBean.getFechaContableEnvioInteger());
		nivel2_1.setCODPPL("M");
		nivel2_1.setCODDISTRIB("V");
		nivel2_1.setINDURG(0);
		nivel2_1.setCODINTERNOUO1(envioEntreOficinasBean.getCentroDestino());
		
		nivel1_1.setSMSOLCTMONEDAE(nivel2_1);
		
		nivel1_2.setIDINTERNOTERMTN(super.getTerminal());
		nivel1_2.setNUMSEC(0);
		nivel1_2.setCODTX("VCO19COU");
		nivel1_2.setCODTXDI("CO19");
		
		contexto.setELEVATORPOSITION(0);
		contexto.setSCROLLABLEOCCURS(50);
		contexto.setTRSOLCTCONTINDSMEVT(nivel1_1);
		contexto.setSTDTRNIPARMV(nivel1_2);
		
		super.initialize(contexto);
		
		return contexto;
	}
	
	private Ejecutar.ITRSOLCTCONTINDSMTRN initPeticion2(final Integer fechaEnvio, final String centro){
		Ejecutar.ITRSOLCTCONTINDSMTRN contexto = new Ejecutar.ITRSOLCTCONTINDSMTRN();
		
		Ejecutar.ITRSOLCTCONTINDSMTRN.TRSOLCTCONTINDSMEVT nivel1_1 = new Ejecutar.ITRSOLCTCONTINDSMTRN.TRSOLCTCONTINDSMEVT();
		Ejecutar.ITRSOLCTCONTINDSMTRN.STDTRNIPARMV nivel1_2 = new Ejecutar.ITRSOLCTCONTINDSMTRN.STDTRNIPARMV();
		
		Ejecutar.ITRSOLCTCONTINDSMTRN.TRSOLCTCONTINDSMEVT.SMSOLCTMONEDAE nivel2_1 = new Ejecutar.ITRSOLCTCONTINDSMTRN.TRSOLCTCONTINDSMEVT.SMSOLCTMONEDAE();
		
		nivel2_1.setCODNRBEEN(super.getEntidad());
		nivel2_1.setCODINTERNOUO1(super.getSucursal());
		nivel2_1.setFECHASOLCTSM(fechaEnvio);
		nivel2_1.setCODPPL("M");
		nivel2_1.setCODDISTRIB("V");
		nivel2_1.setINDURG(0);
		nivel2_1.setCODINTERNOUO(centro);
		
		nivel1_1.setSMSOLCTMONEDAE(nivel2_1);
		
		nivel1_2.setIDINTERNOTERMTN(super.getTerminal());
		nivel1_2.setNUMSEC(0);
		nivel1_2.setCODTX("VCO19COU");
		nivel1_2.setCODTXDI("CO19");
		
		contexto.setELEVATORPOSITION(0);
		contexto.setSCROLLABLEOCCURS(50);
		contexto.setTRSOLCTCONTINDSMEVT(nivel1_1);
		contexto.setSTDTRNIPARMV(nivel1_2);
		
		super.initialize(contexto);
		
		return contexto;
	}
	
	private EjecutarResult ejecutarWS(Ejecutar.ITRSOLCTCONTINDSMTRN contexto){
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaEnvioEntreOficinasServicio.class, contexto);
		}catch(NoControlableException nce){
			throw new NoControlableException("No se pudo invocar el servicio de "
					+ " consulta envío entre oficinas.", nce);
		}
		
		return respuesta;
	}
	
	private Boolean verificaRespuesta(EjecutarResult respuesta){
		Boolean noNulo = false;
		
		if(respuesta != null
				&& respuesta.getResponseBansefi() != null){
			noNulo = true;
		}
		
		return noNulo;
	}
	
	private Boolean verificaResponseBansefi(ResponseBansefi response){
		Boolean noNulo = false;
		
		if(response.getOTRSOLCTCONTINDSMTRN() != null
				&& response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT() != null
				&& response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTDESGLSE() != null
				&& response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTMONEDAE() != null){
			noNulo = true;
		}
		
		return noNulo;
	}
	
	private void getDatosParrilla(ResponseBansefi response, EnvioEntreOficinasBean envioEntreOficinasBean){
		if(verificaResponseBansefi(response)){
			for(SMSOLCTDESGLSE desglose : response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTDESGLSE()){
				for(ExistenciaDenominacionBean existenciaDenominacionBean : envioEntreOficinasBean.getParrilla().getListaDenominaciones()){
					if(desglose.getCODDSTN().trim().equals(existenciaDenominacionBean.getOrigen())
							&& desglose.getCODVALORFACIAL().trim().equals(existenciaDenominacionBean.getValorFacial())){
						
						existenciaDenominacionBean.setImporteAEnviar(desglose.getIMPPEDIDO());
						existenciaDenominacionBean.setPrecinto(desglose.getNUMPRECINTO().trim());
						existenciaDenominacionBean.setImporteRecibido(desglose.getIMPRECBDO());
						existenciaDenominacionBean.setPrecinto(desglose.getNUMPRECINTO().trim());
						
						envioEntreOficinasBean.getParrilla().actualizaUnidades(existenciaDenominacionBean, existenciaDenominacionBean.getImporteAEnviar());
						
						break;
					}
				}
			}
		}
	}
	
	private void getDatosEnvio(ResponseBansefi response, EnvioEntreOficinasBean envioEntreOficinasBean){
		if(verificaResponseBansefi(response)){
			envioEntreOficinasBean.setEntidad(super.getEntidad());
			envioEntreOficinasBean.setCentroOrigen(super.getSucursal());
			envioEntreOficinasBean.setCodigoMoneda(response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTMONEDAE().getCODPPL());
			envioEntreOficinasBean.setCodigoDistribucion(response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTMONEDAE().getCODDISTRIB());
			envioEntreOficinasBean.setIndicadorUgente(response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTMONEDAE().getINDURG());
			envioEntreOficinasBean.setCentroControlador(response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTMONEDAE().getCODINTERNOUO2());
			envioEntreOficinasBean.setEstatusC(response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTMONEDAE().getCODECVSM());
			
			final CatalogoBean catalogo = catalogoUtils.getCatalogoBean(CatalogoEnum.ESTATUS_PETICION, envioEntreOficinasBean.getEstatusC());
			
			if (catalogo != null) {
				envioEntreOficinasBean.setEstatusL(catalogo
						.getDescripcionL());
			} else {
				envioEntreOficinasBean.setEstatusL("");
			}
			
			envioEntreOficinasBean.setFechaAbastecimiento(response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTMONEDAE().getFECHAABASTREC());
			envioEntreOficinasBean.setFechaProceso(response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTMONEDAE().getFECHAPROCESOSM());
			envioEntreOficinasBean.setTotalAutorizado(response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTMONEDAE().getIMPAUTV());
			envioEntreOficinasBean.setTotalPedido(response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTMONEDAE().getIMPPEDIDOV());
			envioEntreOficinasBean.setTotalRecibido(response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTMONEDAE().getIMPRECBDOV());
			envioEntreOficinasBean.setTotalCertificado(response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTMONEDAE().getIMPCERTFDOV());
			envioEntreOficinasBean.setIdEmpleado(response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTMONEDAE().getIDINTERNOEMPLEP());
			envioEntreOficinasBean.setObservaciones(response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTMONEDAE().getVALOROBSERSM().trim());
			
		}
	}
	
	private EnvioEntreOficinasBean getDatosEnvio(ResponseBansefi response){
		EnvioEntreOficinasBean envioEntreOficinasBean = new EnvioEntreOficinasBean();
		envioEntreOficinasBean.setEntidad(super.getEntidad());
		envioEntreOficinasBean.setCentroOrigen(super.getSucursal());
		envioEntreOficinasBean.setCodigoMoneda(response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTMONEDAE().getCODPPL());
		envioEntreOficinasBean.setCodigoDistribucion(response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTMONEDAE().getCODDISTRIB());
		envioEntreOficinasBean.setIndicadorUgente(response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTMONEDAE().getINDURG());
		envioEntreOficinasBean.setCentroControlador(response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTMONEDAE().getCODINTERNOUO2());
		envioEntreOficinasBean.setEstatusC(response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTMONEDAE().getCODECVSM());
		
		final CatalogoBean catalogo = catalogoUtils.getCatalogoBean(CatalogoEnum.ESTATUS_PETICION, envioEntreOficinasBean.getEstatusC());
		
		if (catalogo != null) {
			envioEntreOficinasBean.setEstatusL(catalogo
					.getDescripcionL());
		} else {
			envioEntreOficinasBean.setEstatusL("");
		}
		
		envioEntreOficinasBean.setFechaAbastecimiento(response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTMONEDAE().getFECHAABASTREC());
		envioEntreOficinasBean.setFechaProceso(response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTMONEDAE().getFECHAPROCESOSM());
		envioEntreOficinasBean.setTotalAutorizado(response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTMONEDAE().getIMPAUTV());
		envioEntreOficinasBean.setTotalPedido(response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTMONEDAE().getIMPPEDIDOV());
		envioEntreOficinasBean.setTotalRecibido(response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTMONEDAE().getIMPRECBDOV());
		envioEntreOficinasBean.setTotalCertificado(response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTMONEDAE().getIMPCERTFDOV());
		envioEntreOficinasBean.setIdEmpleado(response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTMONEDAE().getIDINTERNOEMPLEP());
		envioEntreOficinasBean.setObservaciones(response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTMONEDAE().getVALOROBSERSM().trim());
		
		return envioEntreOficinasBean;
	}
	
	private List<ExistenciaDenominacionBean> getDatosParrilla2(ResponseBansefi response){
		List<ExistenciaDenominacionBean> listaDenominaciones = new ArrayList<ExistenciaDenominacionBean>();
		if(verificaResponseBansefi(response)){
			for(SMSOLCTDESGLSE desglose : response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTDESGLSE()){
				
				ExistenciaDenominacionBean denominacion = new ExistenciaDenominacionBean();
				
				denominacion.setOrigen(desglose.getCODDSTN());
				denominacion.setValorFacial(desglose.getCODVALORFACIAL());
				denominacion.setImporteEnviado(desglose.getIMPPEDIDO());
				denominacion.setImporteAEnviar(desglose.getIMPPEDIDO());
				denominacion.setImporteRecibido(desglose.getIMPRECBDO());
				denominacion.setPrecinto(desglose.getNUMPRECINTO().trim());
				
				listaDenominaciones.add(denominacion);
			}
		}
		return listaDenominaciones;
	}
	
	private void getDatosEnvio2(ResponseBansefi response, RecepcionEntreOficinasBean recepcionEntreOficinasBean){
		if(verificaResponseBansefi(response)){
			recepcionEntreOficinasBean.setEntidad(super.getEntidad());
			recepcionEntreOficinasBean.setCentroOrigen(super.getSucursal());
			recepcionEntreOficinasBean.setCodigoMoneda(response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTMONEDAE().getCODPPL());
			recepcionEntreOficinasBean.setCodigoDistribucion(response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTMONEDAE().getCODDISTRIB());
			recepcionEntreOficinasBean.setIndicadorUrgente(response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTMONEDAE().getINDURG());
			recepcionEntreOficinasBean.setCentroControlador(response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTMONEDAE().getCODINTERNOUO2());
			recepcionEntreOficinasBean.setEstatusC(response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTMONEDAE().getCODECVSM());
			
			final CatalogoBean catalogo = catalogoUtils.getCatalogoBean(CatalogoEnum.ESTATUS_PETICION, recepcionEntreOficinasBean.getEstatusC());
			
			if (catalogo != null) {
				recepcionEntreOficinasBean.setEstatusL(catalogo
						.getDescripcionL());
			} else {
				recepcionEntreOficinasBean.setEstatusL("");
			}
			
			recepcionEntreOficinasBean.setFechaAbastecimiento(response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTMONEDAE().getFECHAABASTREC());
			recepcionEntreOficinasBean.setFechaProceso(response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTMONEDAE().getFECHAPROCESOSM());
			recepcionEntreOficinasBean.setTotalAutorizado(response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTMONEDAE().getIMPAUTV());
			recepcionEntreOficinasBean.setTotalEnviado(response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTMONEDAE().getIMPPEDIDOV());
			recepcionEntreOficinasBean.setTotalRecibido(response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTMONEDAE().getIMPRECBDOV());
			recepcionEntreOficinasBean.setTotalRecibidoAbsoluto(response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTMONEDAE().getIMPRECBDOV());
			recepcionEntreOficinasBean.setTotalCertificado(response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTMONEDAE().getIMPCERTFDOV());
			recepcionEntreOficinasBean.setIdEmpleado(response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTMONEDAE().getIDINTERNOEMPLEP());
			recepcionEntreOficinasBean.setObservaciones(response.getOTRSOLCTCONTINDSMTRN().getTRSOLCTCONTINDSMEVT().getSMSOLCTMONEDAE().getVALOROBSERSM().trim());
			
		}
	}
}
