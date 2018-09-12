package mx.babel.bansefi.banksystem.oficina.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.arq.serviciosAppwhere.clients.oficina.OficinaClient;
import mx.babel.arq.serviciosAppwhere.dto.DatosSesionDTO;
import mx.babel.arq.serviciosAppwhere.dto.request.oficina.ReqActualizarArqueoCentroDTO;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.oficina.beans.ArqueoCentroBean;
import mx.babel.bansefi.banksystem.oficina.webservices.arqueocentro.ArqueoCentroServicio;
import mx.babel.bansefi.banksystem.oficina.webservices.arqueocentro.Ejecutar;
import mx.babel.bansefi.banksystem.oficina.webservices.arqueocentro.EjecutarResult;
import mx.babel.bansefi.banksystem.oficina.webservices.arqueocentro.ResponseBansefi;
import mx.babel.bansefi.banksystem.oficina.wrappers.ArqueoCentroWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Back End para realizar el arqueo de un centro
 * @author mario.montesdeoca
 *
 */
@Component
public class ArqueoCentroBackEnd extends BackEndBean {

	private static final long serialVersionUID = -3068241935165687203L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	@Autowired
	ArqueoCentroWrapper arqueoCentroWrapper;

	@Autowired
	OficinaClient oficinaClient;

	/**
	 * Método que inserta en diario de la tabla intermdia el arqueo del centro
	 *
	 * @return Lista de cuadres en puestos
	 */
	public void actualizarArqueoCentro(ArqueoCentroBean arqueoCentro, Date fechaContable) {
		//Consumir  (JJVC)
		SimpleDateFormat formatoLocalFecha = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
		HttpServletRequest requestObj = (HttpServletRequest)
				FacesContext.getCurrentInstance().getExternalContext().getRequest();
		DatosSesionDTO datosSesion =
				(DatosSesionDTO) requestObj.getSession().getAttribute("datosSesion");
		ReqActualizarArqueoCentroDTO req = new ReqActualizarArqueoCentroDTO();
		req.setUsuario(datosSesion.getUsuario());
		req.setPassword(datosSesion.getPassword());
		req.setEntidad(datosSesion.getEntidad());
		req.setTerminal(datosSesion.getVentanilla());

		req.setCajaCobrosOn(arqueoCentro.getCobrosOn().toString());
		req.setCajaPagosOn(arqueoCentro.getPagosOn().toString());
		req.setFechaCtble(formatoLocalFecha.format(fechaContable));
		req.setFechaOprcn(formatoLocalFecha.format(arqueoCentro.getFechaArqueo()));
		req.setHoraOprcn(formatoHora.format(arqueoCentro.getFechaArqueo()));
		req.setSaldoCajaAct(arqueoCentro.getSaldoContableCajaActual().toString());
		req.setSaldoCajaAnt(arqueoCentro.getSaldoContableCajaAnterior().toString());
		req.setTotalArqueo(arqueoCentro.getTotalArqueo().toString());

		oficinaClient.actualizarArqueoCentro(req);
	}
	
    /**
	 * Mètodo que recibe un bean de arqueo de centro y realiza su arqueo.
	 * @param centro bean de arqueo de centro
	 * @return <code>true</code> si la operaciòn fue realizada con èxito.
	 */
	public Boolean ejecutarTRN(ArqueoCentroBean centro){
		if(centro != null){
			Ejecutar.ITRARQUEOCAJAEXTRNI contexto = initPeticion(centro);
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
				return arqueoExitoso(respuesta.getResponseBansefi(), centro);
			}
		}
		return false;
	}
	
	/**
	 * Mètodo que verifica la respuesta del ws y actualiza el estado del arqueo de centro
	 * @param respuesta respuesta del ws
	 * @param centro bean de arqueo de centro
	 * @return <code>true</code> si el arqueo fue èxitoso
	 */
	private Boolean arqueoExitoso(ResponseBansefi respuesta, ArqueoCentroBean centro){		
		if(verificaRespuesta(respuesta)){
			if(respuesta.getOTRARQUEOCAJAEXTRNO().getRTRNCD() == BackEndBean.RETURN_COD_OK){
				centro.setArqueada(true);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Mètodo que inicializa la peticiòn hacie el ws
	 * @param centro bean de arqueo de centro
	 * @return objeto de peticiòn al ws
	 */
	private Ejecutar.ITRARQUEOCAJAEXTRNI initPeticion(ArqueoCentroBean centro){
		Ejecutar.ITRARQUEOCAJAEXTRNI contexto = new Ejecutar.ITRARQUEOCAJAEXTRNI();
		Ejecutar.ITRARQUEOCAJAEXTRNI.STDTRNIPARMV cuerpoContexto =
				new Ejecutar.ITRARQUEOCAJAEXTRNI.STDTRNIPARMV();
		Ejecutar.ITRARQUEOCAJAEXTRNI.TRARQUEOOFCNAEXEVTY arqueoCentro =
				new Ejecutar.ITRARQUEOCAJAEXTRNI.TRARQUEOOFCNAEXEVTY();
		Ejecutar.ITRARQUEOCAJAEXTRNI.TRARQUEOOFCNAEXEVTY.EXEXISTMONEDAE resultado =
				new Ejecutar.ITRARQUEOCAJAEXTRNI.TRARQUEOOFCNAEXEVTY.EXEXISTMONEDAE();
		centro.setFechaArqueo(super.getFechaSistema());
		contexto.setSTDTRNIPARMV(cuerpoContexto);
		contexto.setTRARQUEOOFCNAEXEVTY(arqueoCentro);
		arqueoCentro.setEXEXISTMONEDAE(resultado);
		super.initialize(contexto);
		
		cuerpoContexto.setCODTX(CodTxConstants.COD_TX_TR_ARQUEO_CAJA_EX_TRN);
		cuerpoContexto.setIDINTERNOTERMTN(super.getTerminal());
		
		arqueoCentroWrapper.wrappBean(centro, arqueoCentro);
		resultado.setCODNRBEEN(super.getEntidad());
		resultado.setCODNUMRCOMONEDA(ConstantesFuncionales.COD_MONEDA);
		resultado.setCODINTERNOUO(super.getSucursal());
		
		for (ExistenciaDenominacionBean denominacion : centro.getExistenciaDenominaciones()) {
			if(denominacion.getUnidades() > 0L){
				Ejecutar.ITRARQUEOCAJAEXTRNI.TRARQUEOOFCNAEXEVTY.EXEXISTDESGLSE denominacionArqueo =
						new Ejecutar.ITRARQUEOCAJAEXTRNI.TRARQUEOOFCNAEXEVTY.EXEXISTDESGLSE();
				denominacionArqueo.setCODNRBEEN(super.getEntidad());
				denominacionArqueo.setCODINTERNOUO(super.getSucursal());
				denominacionArqueo.setFECHAEX(resultado.getFECHAEX());
				denominacionArqueo.setCODNUMRCOMONEDA(ConstantesFuncionales.COD_MONEDA);
				denominacionArqueo.setCODVALORFACIAL(denominacion.getValorFacial());
				denominacionArqueo.setIMPEXISTEX(denominacion.getImporte());
				denominacionArqueo.setCODDSTN(denominacion.getOrigen());
				super.initialize(denominacionArqueo);
				arqueoCentro.getEXEXISTDESGLSE().add(denominacionArqueo);
			}
		}
		contexto.getTRARQUEOOFCNAEXEVTY().getEXEXISTMONEDAE().setTOTEFCT(centro.getTotalArqueo());
		contexto.setIMPCAJA(new BigDecimal("0"));

		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	public EjecutarResult ejecutarWS(Ejecutar.ITRARQUEOCAJAEXTRNI contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ArqueoCentroServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "de cuadre de puestos.", e);
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
		if(response != null && response.getOTRARQUEOCAJAEXTRNO() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
}
