package mx.babel.bansefi.banksystem.cajas.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import mx.babel.bansefi.banksystem.cajas.beans.AutorizacionPeticionEfectivoBean;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;
import mx.babel.bansefi.banksystem.cajas.webservice.consultapeticionefectivo2.ConsultaPeticionEfectivo2Servicio;
import mx.babel.bansefi.banksystem.cajas.webservice.consultapeticionefectivo2.Ejecutar;
import mx.babel.bansefi.banksystem.cajas.webservice.consultapeticionefectivo2.EjecutarResult;
import mx.babel.bansefi.banksystem.cajas.webservice.consultapeticionefectivo2.ResponseBansefi;
import mx.babel.bansefi.banksystem.cajas.webservice.consultapeticionefectivo2.ResponseBansefi.OTRAUTINICPETCNSMTRN.EXEXISTDESGLSE;
import mx.babel.bansefi.banksystem.cajas.webservice.consultapeticionefectivo2.ResponseBansefi.OTRAUTINICPETCNSMTRN.SMSOLCTDESGLSE;

@Component
public class ConsultaPeticionEfectivo2BackEnd  extends BackEndBean{


	/**
	 * 
	 */
	private static final long serialVersionUID = -2439971865452613337L;
	@Autowired
	ServicioWebUtils servicioWebUtils;
	@Autowired
	ConsultaParillaBilletesBackend consultaParrillaBilletesBackEnd;

	
	private List<AutorizacionPeticionEfectivoBean> listaPeticiones;
	private List<ExistenciaDenominacionBean> listaDenominaciones;

	public List<AutorizacionPeticionEfectivoBean> getListaPeticiones() {
		return listaPeticiones;
	}

	public void setListaPeticiones(
			List<AutorizacionPeticionEfectivoBean> listaPeticiones) {
		this.listaPeticiones = listaPeticiones;
	}
	
	public List<ExistenciaDenominacionBean> getListaDenominaciones() {
		return listaDenominaciones;
	}

	public void setListaDenominaciones(
			List<ExistenciaDenominacionBean> listaDenominaciones) {
		this.listaDenominaciones = listaDenominaciones;
	}

	public void ejecutarTRN(int fechaAbastecimiento, List<ExistenciaDenominacionBean> denominaciones){
		
		Ejecutar.ITRAUTINICPETCNSMTRN contexto = initPeticion(fechaAbastecimiento);
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_OK){
				throw ce;
			}
		}
		
		if(verificaResponseBansefi(respuesta)){
			getParrilla(respuesta.getResponseBansefi());
		}
	}
	
	/**
	 * Método para inicializar objeto de petición
	 * @return Objeto de petición al ws
	 */
	public Ejecutar.ITRAUTINICPETCNSMTRN initPeticion(int fechaAbastecimiento){
		
		Ejecutar.ITRAUTINICPETCNSMTRN contexto = new Ejecutar.ITRAUTINICPETCNSMTRN();
		Ejecutar.ITRAUTINICPETCNSMTRN.STDTRNIPARMV nivel1_1 = new Ejecutar.ITRAUTINICPETCNSMTRN.STDTRNIPARMV();
		Ejecutar.ITRAUTINICPETCNSMTRN.TRAUTINICPETCNSMEVT nivel1_2 = new Ejecutar.ITRAUTINICPETCNSMTRN.TRAUTINICPETCNSMEVT();
		
		nivel1_1.setIDINTERNOTERMTN(super.getTerminal());
		nivel1_1.setNUMSEC(0);
		nivel1_1.setCODTX("VCC12COU");
		nivel1_1.setCODTXDI("CC12");
		
		nivel1_2.setFECHASOLCTSM(fechaAbastecimiento);
		
		contexto.setSTDTRNIPARMV(nivel1_1);
		contexto.setTRAUTINICPETCNSMEVT(nivel1_2);
		
		super.initialize(contexto);
		
		return contexto;
	}
	
	/**
	 * Método para obtener la respuesta del ws a partir de un objeto de petición
	 * @param contexto Objeto de petición
	 * @return respuesta del ws
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRAUTINICPETCNSMTRN contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaPeticionEfectivo2Servicio.class, contexto);
		}catch (NoControlableException nce){
			throw new NoControlableException("Error al invocar servicio web de consulta"
					+ " de autorización de peticiones de efectivo.", nce);
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
	private Boolean verificaRespuesta(ResponseBansefi response){
		Boolean noNulo = false;
		
		if(response != null && response.getOTRAUTINICPETCNSMTRN() != null &&
				response.getOTRAUTINICPETCNSMTRN().getSTDTRNMSJPARMV() != null &&
				response.getOTRAUTINICPETCNSMTRN().getEXEXISTDESGLSE() != null &&
				response.getOTRAUTINICPETCNSMTRN().getSMSOLCTDESGLSE() != null &&
				response.getOTRAUTINICPETCNSMTRN().getSTDTRNOPARMV() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
private void getParrilla(ResponseBansefi respuesta){
		
		ExistenciaDenominacionBean existenciaDenominacionBean;
		AutorizacionPeticionEfectivoBean autorizacionPeticionEfectivoBean;
		List<AutorizacionPeticionEfectivoBean> listaPeticiones = new ArrayList<AutorizacionPeticionEfectivoBean>(); ;
		List<ExistenciaDenominacionBean> listaDenominaciones = new ArrayList<ExistenciaDenominacionBean>();
		
		if(verificaRespuesta(respuesta)){
			for (SMSOLCTDESGLSE denominacionDsglose : respuesta.getOTRAUTINICPETCNSMTRN().getSMSOLCTDESGLSE()) {
				if(denominacionDsglose.getIMPPEDIDO().compareTo(BigDecimal.ZERO) > 0
						|| denominacionDsglose.getIMPAUT().compareTo(BigDecimal.ZERO) > 0){
					autorizacionPeticionEfectivoBean = new AutorizacionPeticionEfectivoBean();
					
					autorizacionPeticionEfectivoBean.setCodigoDestino(denominacionDsglose.getCODDSTN().trim());
					autorizacionPeticionEfectivoBean.setCodigoFacial(denominacionDsglose.getCODVALORFACIAL().trim());
					autorizacionPeticionEfectivoBean.setImportePedido(denominacionDsglose.getIMPPEDIDO());
					autorizacionPeticionEfectivoBean.setImporteAutorizado(denominacionDsglose.getIMPAUT());
					
					listaPeticiones.add(autorizacionPeticionEfectivoBean);
				}
			}
			
			for (EXEXISTDESGLSE denominacion : respuesta.getOTRAUTINICPETCNSMTRN().getEXEXISTDESGLSE()) {
				
				if(denominacion.getIMPEXISTEX().compareTo(BigDecimal.ZERO) > 0){
					existenciaDenominacionBean = new ExistenciaDenominacionBean();
					
					existenciaDenominacionBean.setOrigen(denominacion.getCODDSTN().trim());
					existenciaDenominacionBean.setValorFacial(denominacion.getCODVALORFACIAL().trim());
					existenciaDenominacionBean.setExistencias(denominacion.getIMPEXISTEX());
					
					listaDenominaciones.add(existenciaDenominacionBean);					
				}
			}
		}
		setListaPeticiones(listaPeticiones);
		setListaDenominaciones(listaDenominaciones);
	}

	
	
	
	

}
