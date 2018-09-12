package mx.babel.bansefi.banksystem.cajas.backend;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;
import mx.babel.bansefi.banksystem.cajas.beans.PeticionEfectivoBean;
import mx.babel.bansefi.banksystem.cajas.beans.RecepcionEfectivoBean;
import mx.babel.bansefi.banksystem.cajas.beans.RecogidaEfectivoBean;
import mx.babel.bansefi.banksystem.cajas.webservice.consultapeticionefectivo.*;
import mx.babel.bansefi.banksystem.cajas.webservice.consultapeticionefectivo.ResponseBansefi.OTRCONSSOLCTINDSMTRN.TRCONSSOLCTINDSMEVT.SMSOLCTDESGLSE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaPeticionEfectivoBackEnd extends BackEndBean
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5682144274607509433L;
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	private List<ExistenciaDenominacionBean> listaExistencias;
	
	private RecogidaEfectivoBean recogidaEfectivoBean;
	
	public List<ExistenciaDenominacionBean> getListaExistencias() {
		return listaExistencias;
	}

	public void setListaExistencias(
			List<ExistenciaDenominacionBean> listaExistencias) {
		this.listaExistencias = listaExistencias;
	}

	public RecogidaEfectivoBean getRecogidaEfectivoBean() {
		return recogidaEfectivoBean;
	}

	public void setRecogidaEfectivoBean(RecogidaEfectivoBean recogidaEfectivoBean) {
		this.recogidaEfectivoBean = recogidaEfectivoBean;
	}

	private Ejecutar.ITRCONSSOLCTINDSMTRN initPeticion(int fechaSolic, int indicadorUrgente){
		
		Ejecutar.ITRCONSSOLCTINDSMTRN contexto= new Ejecutar.ITRCONSSOLCTINDSMTRN();
		
		Ejecutar.ITRCONSSOLCTINDSMTRN.TRCONSSOLCTINDSMEVT trCONSSOLCTINDSMEVT = new Ejecutar.ITRCONSSOLCTINDSMTRN.TRCONSSOLCTINDSMEVT();
		
		Ejecutar.ITRCONSSOLCTINDSMTRN.TRCONSSOLCTINDSMEVT.SMSOLCTMONEDAP sMSOLCTMONEDAP = new  Ejecutar.ITRCONSSOLCTINDSMTRN.TRCONSSOLCTINDSMEVT.SMSOLCTMONEDAP();
		
		sMSOLCTMONEDAP.setCODNRBEEN(super.getEntidad());
		sMSOLCTMONEDAP.setCODINTERNOUO(super.getSucursal());
		sMSOLCTMONEDAP.setFECHASOLCTSM(fechaSolic);
		sMSOLCTMONEDAP.setCODPPL("M");
		sMSOLCTMONEDAP.setCODDISTRIB("U");
		sMSOLCTMONEDAP.setINDURG(indicadorUrgente);
		
		trCONSSOLCTINDSMEVT.setSMSOLCTMONEDAP(sMSOLCTMONEDAP);
		
		Ejecutar.ITRCONSSOLCTINDSMTRN.STDTRNIPARMV sTDTRNIPARMV= new  Ejecutar.ITRCONSSOLCTINDSMTRN.STDTRNIPARMV();
		sTDTRNIPARMV.setIDINTERNOTERMTN(super.getTerminal());
		sTDTRNIPARMV.setCODTX("VCM13COU");
		sTDTRNIPARMV.setCODTXDI("CO04");
		
		
		contexto.setTRCONSSOLCTINDSMEVT(trCONSSOLCTINDSMEVT);
		contexto.setELEVATORPOSITION(0);
		contexto.setSCROLLABLEOCCURS(50);
		contexto.setSTDTRNIPARMV(sTDTRNIPARMV);
		
		super.initialize(contexto);
		
		return contexto;
	}
	
	/**
	 * Método para obtener la respuesta del ws a partir de un objeto de petición
	 * @param contexto Objeto de petición
	 * @return respuesta del ws
	 */
	
	private EjecutarResult ejecutarWS(Ejecutar.ITRCONSSOLCTINDSMTRN contexto)
	{
		EjecutarResult respuesta= null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaPeticionEfectivoServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "de petecion de efectivo.", e);
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
	 * Método que consulta las existencias de denominaciones en un centro
	 * @return Lista de existencias de denominaciones
	 */
	public Boolean ejecutarTRN(List<ExistenciaDenominacionBean> denominaciones,int fechaSolic,  PeticionEfectivoBean  peticion){
		
		Ejecutar.ITRCONSSOLCTINDSMTRN contexto=initPeticion(fechaSolic,peticion.getIndicadorUrgencia());
		EjecutarResult respuesta = ejecutarWS(contexto);
	
		try{
			super.verificaRespuesta(respuesta);
			
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				
				return false;
			}
		}
		
		if(verificaResponseBansefi(respuesta)){
			getParrilla(respuesta.getResponseBansefi(), denominaciones);
			
			peticion.setEstatus(respuesta.getResponseBansefi().getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTMONEDAE().getCODECVSM());
			peticion.setCod_INTERNO_UO_1(respuesta.getResponseBansefi().getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTMONEDAE().getCODINTERNOUO1());
			String obs=respuesta.getResponseBansefi().getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTMONEDAE().getVALOROBSERSM();
			String obs2= obs.trim();
			if(obs2.length()>0)
			{
			peticion.setObservacion(respuesta.getResponseBansefi().getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTMONEDAE().getVALOROBSERSM().trim());
			}
			else
			{
				peticion.setObservacion("");
			}
		}
		
		return true;
		
	}
	
	
	/**
	 * Método que consulta las existencias de denominaciones en un centro
	 * @return Lista de existencias de denominaciones
	 */
	public Boolean ejecutarTRN(int fechaSolic,  RecepcionEfectivoBean  recepcion){
		
		Ejecutar.ITRCONSSOLCTINDSMTRN contexto=initPeticion(fechaSolic,recepcion.getIndicadorUrgencia());
		EjecutarResult respuesta = ejecutarWS(contexto);
	
		try{
			super.verificaRespuesta(respuesta);
			
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				
				return false;
			}
		}
		if(verificaResponseBansefi(respuesta)){
	    	getParrillaRecepcion(respuesta.getResponseBansefi(),recepcion.getParillaBean().getListaDenominaciones());
			recepcion.setEstatus(respuesta.getResponseBansefi().getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTMONEDAE().getCODECVSM());
			recepcion.setCod_INTERNO_UO_1(respuesta.getResponseBansefi().getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTMONEDAE().getCODINTERNOUO1());
			
			
			String obs=respuesta.getResponseBansefi().getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTMONEDAE().getVALOROBSERSM();
			String obs2= obs.trim();
			if(obs2.length()>0)
			{
				recepcion.setObservacion(respuesta.getResponseBansefi().getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTMONEDAE().getVALOROBSERSM().trim());
			}
			else
			{
				recepcion.setObservacion("");
			}
			
						
			
			recepcion.setTotalAutorizado(respuesta.getResponseBansefi().getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTMONEDAE().getIMPAUTV());
			
		}
		return true;
	}
	
	public int ejecutarTRN(int fechaRecogida, int indicadorUrgencia){
		int codigoRetorno = 0;
		Ejecutar.ITRCONSSOLCTINDSMTRN contexto = initPeticion2(fechaRecogida, indicadorUrgencia);
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if(ce.getRtncod() != RETURN_COD_OK){
				throw ce;
			}
		}
		
		if(verificaResponseBansefi(respuesta)){
			getDatos(respuesta);
			codigoRetorno = 1;
		}
		return codigoRetorno;
	}
	
private Ejecutar.ITRCONSSOLCTINDSMTRN initPeticion2(int fechaSolic, int indicadorUrgente){
		
		Ejecutar.ITRCONSSOLCTINDSMTRN contexto= new Ejecutar.ITRCONSSOLCTINDSMTRN();
		
		Ejecutar.ITRCONSSOLCTINDSMTRN.TRCONSSOLCTINDSMEVT trCONSSOLCTINDSMEVT = new Ejecutar.ITRCONSSOLCTINDSMTRN.TRCONSSOLCTINDSMEVT();
		
		Ejecutar.ITRCONSSOLCTINDSMTRN.TRCONSSOLCTINDSMEVT.SMSOLCTMONEDAP sMSOLCTMONEDAP = new  Ejecutar.ITRCONSSOLCTINDSMTRN.TRCONSSOLCTINDSMEVT.SMSOLCTMONEDAP();
		
		sMSOLCTMONEDAP.setCODNRBEEN(super.getEntidad());
		sMSOLCTMONEDAP.setCODINTERNOUO(super.getSucursal());
		sMSOLCTMONEDAP.setFECHASOLCTSM(fechaSolic);
		sMSOLCTMONEDAP.setCODPPL("M");
		sMSOLCTMONEDAP.setCODDISTRIB("T");
		sMSOLCTMONEDAP.setINDURG(indicadorUrgente);
		
		trCONSSOLCTINDSMEVT.setSMSOLCTMONEDAP(sMSOLCTMONEDAP);
		
		Ejecutar.ITRCONSSOLCTINDSMTRN.STDTRNIPARMV sTDTRNIPARMV= new  Ejecutar.ITRCONSSOLCTINDSMTRN.STDTRNIPARMV();
		sTDTRNIPARMV.setIDINTERNOTERMTN(super.getTerminal());
		sTDTRNIPARMV.setCODTX("VCM13COU");
		sTDTRNIPARMV.setCODTXDI("CO04");
		
		
		contexto.setTRCONSSOLCTINDSMEVT(trCONSSOLCTINDSMEVT);
		contexto.setELEVATORPOSITION(0);
		contexto.setSCROLLABLEOCCURS(50);
		contexto.setSTDTRNIPARMV(sTDTRNIPARMV);
		
		super.initialize(contexto);
		
		return contexto;
	}
	
	public void getDatos(EjecutarResult respuesta){
		
		this.recogidaEfectivoBean = new RecogidaEfectivoBean();
		this.listaExistencias = new ArrayList<ExistenciaDenominacionBean>();
		
		this.recogidaEfectivoBean.setEntidad(respuesta.getResponseBansefi().getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTMONEDAE().getCODNRBEEN().trim());
		this.recogidaEfectivoBean.setCodigoMoneda(respuesta.getResponseBansefi().getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTMONEDAE().getCODPPL().trim());
		this.recogidaEfectivoBean.setCodigoDistribucion(respuesta.getResponseBansefi().getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTMONEDAE().getCODDISTRIB().trim());
		this.recogidaEfectivoBean.setCentroControlador(respuesta.getResponseBansefi().getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTMONEDAE().getCODINTERNOUO1().trim());
		this.recogidaEfectivoBean.setEstatusC(respuesta.getResponseBansefi().getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTMONEDAE().getCODECVSM().trim());
		this.recogidaEfectivoBean.setTotalAutorizado(respuesta.getResponseBansefi().getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTMONEDAE().getIMPAUTV());
		this.recogidaEfectivoBean.setTotalRecibido(respuesta.getResponseBansefi().getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTMONEDAE().getIMPRECBDOV());
		this.recogidaEfectivoBean.setTotalCertificado(respuesta.getResponseBansefi().getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTMONEDAE().getIMPCERTFDOV());
		this.recogidaEfectivoBean.setIdEmpleado(respuesta.getResponseBansefi().getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTMONEDAE().getIDINTERNOEMPLEP().trim());
		this.recogidaEfectivoBean.setObservacion(respuesta.getResponseBansefi().getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTMONEDAE().getVALOROBSERSM().trim());
		this.recogidaEfectivoBean.setFechaSolicitud(respuesta.getResponseBansefi().getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTMONEDAE().getFECHASOLCTSM());
		this.recogidaEfectivoBean.setFechaProceso(respuesta.getResponseBansefi().getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTMONEDAE().getFECHAPROCESOSM());
		this.recogidaEfectivoBean.setFechaAbastecimiento(respuesta.getResponseBansefi().getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTMONEDAE().getFECHAABASTREC());
		this.recogidaEfectivoBean.setTotalPedido(respuesta.getResponseBansefi().getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTMONEDAE().getIMPPEDIDOV());
		
		for(SMSOLCTDESGLSE desglose : respuesta.getResponseBansefi().getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTDESGLSE()){
			if(desglose.getIMPAUT() != null){
				
				ExistenciaDenominacionBean existenciaDenominacionBean = new ExistenciaDenominacionBean();
				
				existenciaDenominacionBean.setOrigen(desglose.getCODDSTN().trim());
				existenciaDenominacionBean.setValorFacial(desglose.getCODVALORFACIAL().trim());
				existenciaDenominacionBean.setImporteAutorizado(desglose.getIMPAUT());
				existenciaDenominacionBean.setImporteAEnviar(desglose.getIMPAUT());
				existenciaDenominacionBean.setImportePedido(desglose.getIMPPEDIDO());
				existenciaDenominacionBean.setImporteRecibido(desglose.getIMPRECBDO());
				
				this.listaExistencias.add(existenciaDenominacionBean);
			}
		}
	}
	
	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con los datos
	 * de cuentas.
	 * 
	 * @param response Respuesta Bansefi con datos de cuentas
	 * @return <code>true</code> si la respuesta Bansefi contiene los datos de alta
	 */
	private Boolean verificaRespuesta(ResponseBansefi response){
		Boolean noNulo = false;
		if(response != null && response.getOTRCONSSOLCTINDSMTRN() != null &&
				response.getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT()  != null &&
						response.getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTMONEDAE() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
	
	
	/**
	 * Función que se encarga de obtener el desglose de la consulta de recepcion
	 * 
	 * 
	 * @param response Respuesta Bansefi con datos de cuentas
	 * @param List<ExistenciaDenominacionBean> lista para almacenar los datos del desglose
	 */
	private void getDesglose(ResponseBansefi respuesta, List<ExistenciaDenominacionBean> denominaciones)
	{
		
		
		
		if(verificaRespuesta(respuesta)){
			for(SMSOLCTDESGLSE desglose:respuesta.getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTDESGLSE())
			{
				
				ExistenciaDenominacionBean existenciaDesglose= new ExistenciaDenominacionBean();
				
				existenciaDesglose.setImporteAutorizado(desglose.getIMPAUT());
				existenciaDesglose.setImporteRecibido(desglose.getIMPRECBDO());
				existenciaDesglose.setValorFacial(desglose.getCODVALORFACIAL());
				existenciaDesglose.setOrigen(desglose.getCODDSTN());
				
				denominaciones.add(existenciaDesglose);
				
			}
		
		}
	}
	
	private void getParrilla(ResponseBansefi respuesta, List<ExistenciaDenominacionBean> denominaciones)
	{
		
		if(verificaRespuesta(respuesta)){
			
			
			for(ExistenciaDenominacionBean item:denominaciones)
			{
				item.setImportePedido(null);
				item.setUnidades(null);
			}
			
			
			
				for(SMSOLCTDESGLSE desglose:respuesta.getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTDESGLSE())
				{
				  String valorFacial=	 desglose.getCODVALORFACIAL();
				  
				  valorFacial= valorFacial.trim();
				  
				  if(valorFacial.length() >0)
				  {
					  
					  for(ExistenciaDenominacionBean denominacion: denominaciones)
				      {
						  String valorFacial2=	 denominacion.getValorFacial();
						  
						  valorFacial2= valorFacial2.trim();
						  
						  if( valorFacial.equals(valorFacial2) && desglose.getCODDSTN().equals(denominacion.getOrigen())  )
			    		  {
							  denominacion.setImportePedido(desglose.getIMPPEDIDO());
			    			  Long unidades = denominacion.getImportePedido().divide(denominacion.getValor()).longValue();
			    			  denominacion.setUnidades(unidades);
			    		  }
				      }
					  
				  }
				  
				  
				  
		
				  
				}
		}
	
	}
	
	private void getParrillaRecepcion(ResponseBansefi respuesta, List<ExistenciaDenominacionBean> denominaciones)
	{
		
		if(verificaRespuesta(respuesta)){
			
				for(SMSOLCTDESGLSE desglose:respuesta.getOTRCONSSOLCTINDSMTRN().getTRCONSSOLCTINDSMEVT().getSMSOLCTDESGLSE())
				{
				  String valorFacial=	 desglose.getCODVALORFACIAL();
				  
				  valorFacial= valorFacial.trim();
				  
				  if(valorFacial.length() >0)
				  {
					  
					  for(ExistenciaDenominacionBean denominacion: denominaciones)
				      {
						  String valorFacial2=	 denominacion.getValorFacial();
						  
						  valorFacial2= valorFacial2.trim();
						  
						  if( valorFacial.equals(valorFacial2) && desglose.getCODDSTN().equals(denominacion.getOrigen())  )
			    		  {
							  denominacion.setImporteAutorizado(desglose.getIMPAUT());
							  denominacion.setImporteRecibido(desglose.getIMPRECBDO());
							  denominacion.setImportePedido(desglose.getIMPPEDIDO());
							  Long unidades = denominacion.getImporteRecibido().divide(denominacion.getValor()).longValue();
			    			  denominacion.setUnidades(unidades);
			    		  }
				      }
					  
				  }
				  
				  
				  
		
				  
				}
		}
	
	}
	
}
