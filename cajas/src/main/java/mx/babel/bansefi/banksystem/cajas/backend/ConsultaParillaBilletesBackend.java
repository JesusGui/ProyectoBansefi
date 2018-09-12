package mx.babel.bansefi.banksystem.cajas.backend;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;
import mx.babel.bansefi.banksystem.cajas.webservice.consultaparillabilletes.Ejecutar;
import mx.babel.bansefi.banksystem.cajas.webservice.consultaparillabilletes.ConsultaParrillaBilletesServicio;
import mx.babel.bansefi.banksystem.cajas.webservice.consultaparillabilletes.EjecutarResult;
import mx.babel.bansefi.banksystem.cajas.webservice.consultaparillabilletes.ResponseBansefi;
import mx.babel.bansefi.banksystem.cajas.webservice.consultaparillabilletes.ResponseBansefi.OTRSOLICVFSMTRNO.TRSOLICVFSMEVTZ.SMPETCNEFCTDPLSV;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class ConsultaParillaBilletesBackend  extends BackEndBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3124682477126222005L;
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	private static final String MONEDA = "M";
	private static final String BILLETE = "B";
	private List<ExistenciaDenominacionBean> listaExistencias;
	    
    public List<ExistenciaDenominacionBean> getListaExistencias() {
		return listaExistencias;
	}

	public void setListaExistencias(
			List<ExistenciaDenominacionBean> listaExistencias) {
		this.listaExistencias = listaExistencias;
	}

	/**
	 * Método que consulta las existencias de denominaciones en un centro
	 * @return Lista de existencias de denominaciones
	 */
	public void ejecutarTRN(List<ExistenciaDenominacionBean> denominaciones){
		Ejecutar.ITRSOLICVFSMTRNI contexto = initPeticion();
		EjecutarResult respuesta = ejecutarWS(contexto);
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}
		}
		if(verificaResponseBansefi(respuesta)){
			getParrilla(respuesta.getResponseBansefi(), denominaciones);
		}
	}
	
	public void ejecutarTRN2(List<ExistenciaDenominacionBean> denominaciones)
	{
		Ejecutar.ITRSOLICVFSMTRNI contexto = initPeticion();
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
		
		if(verificaResponseBansefi(respuesta)){
			getParrilla2(respuesta.getResponseBansefi(), denominaciones);
		}
		
	}
	
	
	/**
	 * Método para inicializar objeto de petición
	 * @return Objeto de petición al ws
	 */
	private Ejecutar.ITRSOLICVFSMTRNI initPeticion(){
		Ejecutar.ITRSOLICVFSMTRNI contexto = 
				new Ejecutar.ITRSOLICVFSMTRNI();
		
		
		Ejecutar.ITRSOLICVFSMTRNI.TRSOLICVFSMEVTY nivel1_1=
				new Ejecutar.ITRSOLICVFSMTRNI.TRSOLICVFSMEVTY();


		Ejecutar.ITRSOLICVFSMTRNI.TRSOLICVFSMEVTY.SMAUTTOTV nivel1_1_1= new Ejecutar.ITRSOLICVFSMTRNI.TRSOLICVFSMEVTY.SMAUTTOTV();
		
		nivel1_1_1.setCODINTERNOUO(super.getSucursal());
		
		nivel1_1.setSMAUTTOTV(nivel1_1_1);
		
		
		Ejecutar.ITRSOLICVFSMTRNI.STDTRNIPARMV nivel1_2 = new Ejecutar.ITRSOLICVFSMTRNI.STDTRNIPARMV();
		
		nivel1_2.setIDINTERNOTERMTN(super.getTerminal());
		nivel1_2.setCODTX("VCC02COU");
		
		contexto.setTRSOLICVFSMEVTY(nivel1_1);
		contexto.setSTDTRNIPARMV(nivel1_2);
		contexto.setELEVATORPOSITION(0);
		contexto.setSCROLLABLEOCCURS(50);
		
		super.initialize(contexto);
		
		
		return contexto;
	}
	

	/**
	 * Método para obtener la respuesta del ws a partir de un objeto de petición
	 * @param contexto Objeto de petición
	 * @return respuesta del ws
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRSOLICVFSMTRNI contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaParrillaBilletesServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "de parrilla de puesto.", e);
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
		if(response != null && response.getOTRSOLICVFSMTRNO() != null &&
				response.getOTRSOLICVFSMTRNO().getSTDTRNOPARMV() != null &&
				response.getOTRSOLICVFSMTRNO().getTRSOLICVFSMEVTZ() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
	/**
	 * Método para obtener una denominación a partir de un objeto de respuesta
	 * @param denominacion Denominación obtenida a través del ws
	 * @param denominaciones lista de denominaciones generada por la aplicación
	 * @return denominación correspondiente
	 */
	public ExistenciaDenominacionBean getDenominacion(SMPETCNEFCTDPLSV denominacion, List<ExistenciaDenominacionBean> denominaciones){
		for (ExistenciaDenominacionBean existenciaDenominacionBean : denominaciones) {
			if(existenciaDenominacionBean.getValor().compareTo(denominacion.getVALORMONEDA())==0){
				if(MONEDA.equals(denominacion.getSOPORTE()) && existenciaDenominacionBean.getMoneda()){
					return existenciaDenominacionBean;
				}
				if(BILLETE.equals(denominacion.getSOPORTE()) && !existenciaDenominacionBean.getMoneda()){
					return existenciaDenominacionBean;
				}
			}
		}
		return null;
	}
	
	
	/**
	 * Método que construye la lista de existencias de denominaciones a partir de la respuesta del ws
	 * @param respuesta del ws
	 * @return lista de existencia de denominaciones
	 */
	private void getParrilla(ResponseBansefi respuesta, List<ExistenciaDenominacionBean> denominaciones){
		
		List<ExistenciaDenominacionBean> listaDenominaciones = new ArrayList<>();
		
		if(verificaRespuesta(respuesta)){
			for (SMPETCNEFCTDPLSV denominacion : respuesta.getOTRSOLICVFSMTRNO().getTRSOLICVFSMEVTZ().getSMPETCNEFCTDPLSV()) {
				if(BigDecimal.ZERO.compareTo(denominacion.getVALORMONEDA()) < 0){
					ExistenciaDenominacionBean denominacionBean = new ExistenciaDenominacionBean();
					if(denominacionBean != null){
						denominacionBean.setValor(denominacion.getVALORMONEDA());
						denominacionBean.setFormato(denominacion.getFORMATO().trim());
						denominacionBean.setValorFacial(denominacion.getCODVALORFACIAL().trim());
						denominacionBean.setOrigen(denominacion.getCODDSTN().trim());
						denominacionBean.setMoneda(denominacion.getSOPORTE().equals("M"));
						
						listaDenominaciones.add(denominacionBean);
					}
				}
			}
		}
		setListaExistencias(listaDenominaciones);
	}
	
	private void getParrilla2(ResponseBansefi respuesta, List<ExistenciaDenominacionBean> denominaciones)
	{
		if(verificaRespuesta(respuesta)){
			for (SMPETCNEFCTDPLSV denominacion : respuesta.getOTRSOLICVFSMTRNO().getTRSOLICVFSMEVTZ().getSMPETCNEFCTDPLSV())
			{
				if(BigDecimal.ZERO.compareTo(denominacion.getVALORMONEDA()) < 0){
					
					ExistenciaDenominacionBean existenciaDenominacionBean= new ExistenciaDenominacionBean(); 
					existenciaDenominacionBean.setValor(denominacion.getVALORMONEDA());
					
					if(denominacion.getSOPORTE().equals("B"))
					{
						existenciaDenominacionBean.setMoneda(false);
					}
					else
					{
						existenciaDenominacionBean.setMoneda(true);
					}
					
					existenciaDenominacionBean.setFormato(denominacion.getFORMATO());
					existenciaDenominacionBean.setOrigen(denominacion.getCODDSTN());
					existenciaDenominacionBean.setValorFacial(denominacion.getCODVALORFACIAL());
					//existenciaDenominacionBean.setImportePedido(new BigDecimal("0"));
					denominaciones.add(existenciaDenominacionBean);
				}
			}
		}
	}
	
}
