package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.CuentaRelacionadaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.beans.HistoricoRelacionadoBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.historicorelacioncuentacuenta.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.historicorelacioncuentacuenta.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.historicorelacioncuentacuenta.HistoricoRelacionCuentaCuentaServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.historicorelacioncuentacuenta.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.RelacionesCuentaWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HistoricoRelacionCuentaCuentaBackEnd extends BackEndBean{
	
	private static final long serialVersionUID = -5349549337723877576L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	@Autowired
	RelacionesCuentaWrapper relacionesCuentaWrapper;
	
	public List<HistoricoRelacionadoBean> ejecutarTRN(CuentaRelacionadaBean cuentaRelacionadaBean, Long numeroCuenta){
		List<HistoricoRelacionadoBean> historico = new ArrayList<HistoricoRelacionadoBean>();
		
		Ejecutar.ITRRXCONSMASIVAECVTR contexto = initPeticion(cuentaRelacionadaBean, numeroCuenta);
		
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return historico;
			}
		}
		
		if(verificaResponseBansefi(respuesta)){
			historico = getResultado(respuesta.getResponseBansefi());
		}
		return historico;
	}
	
	private List<HistoricoRelacionadoBean> getResultado(ResponseBansefi respuesta){
		List<HistoricoRelacionadoBean> historico = new ArrayList<HistoricoRelacionadoBean>();
		
		if(verificaRespuesta(respuesta)){
			for (int i = 0; i < respuesta.getOTRRXCONSMASIVAECVTR().getNUMBEROFRECORDS(); i++) {
				HistoricoRelacionadoBean estado = 
						relacionesCuentaWrapper.wrappBean(
								respuesta.getOTRRXCONSMASIVAECVTR().getTRRXCONSMASIVAECVEVT().get(i));
				historico.add(estado);
			}
		}
		return historico;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRRXCONSMASIVAECVTR contexto){
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					HistoricoRelacionCuentaCuentaServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta histórico "
					+ "relación cuenta-cuenta.", e);
		}
		return respuesta;
	}
	
	private Ejecutar.ITRRXCONSMASIVAECVTR initPeticion(CuentaRelacionadaBean relacionadaBean, Long numeroCuenta){
		Ejecutar.ITRRXCONSMASIVAECVTR contexto = new Ejecutar.ITRRXCONSMASIVAECVTR();
		Ejecutar.ITRRXCONSMASIVAECVTR.TRRXCONSMASIVAECVEVT detalles =
				new Ejecutar.ITRRXCONSMASIVAECVTR.TRRXCONSMASIVAECVEVT();
		Ejecutar.ITRRXCONSMASIVAECVTR.STDTRNIPARMV cuerpoContexto = 
				new Ejecutar.ITRRXCONSMASIVAECVTR.STDTRNIPARMV();
		Ejecutar.ITRRXCONSMASIVAECVTR.TRRXCONSMASIVAECVEVT.RXECVACACP relacion =
				new Ejecutar.ITRRXCONSMASIVAECVTR.TRRXCONSMASIVAECVEVT.RXECVACACP();
		contexto.setTRRXCONSMASIVAECVEVT(detalles);
		contexto.setSTDTRNIPARMV(cuerpoContexto);
		detalles.setRXECVACACP(relacion);
		super.initialize(contexto);
		
		
		cuerpoContexto.setCODTX(CodTxConstants.COD_TX_TR_RX_CONS_MASIVA_ECV_TRN);
		cuerpoContexto.setIDINTERNOTERMTN(super.getTerminal());
		
		relacion.setCODNRBEEN(super.getEntidad());
		relacion.setNUMSECAC1(numeroCuenta);
		relacion.setCODRLACAC(relacionadaBean.getTipoRelacion());
		relacion.setNUMSECAC2(relacionadaBean.getCuenta().getNumeroCuenta());
		
		return contexto;
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
	 * de la relación.
	 * 
	 * @param response Respuesta Bansefi con datos de la relación
	 * @return <code>true</code> si la respuesta Bansefi contiene un histórico de la relación
	 */
	private Boolean verificaRespuesta(ResponseBansefi response){
		Boolean noNulo = false;
		if(response != null && response.getOTRRXCONSMASIVAECVTR() != null){
			noNulo = true;
		}
		return noNulo;
	}

}
