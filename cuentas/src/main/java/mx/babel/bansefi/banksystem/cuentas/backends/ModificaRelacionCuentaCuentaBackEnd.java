package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.CuentaRelacionadaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificarelacioncuentacuenta.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificarelacioncuentacuenta.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificarelacioncuentacuenta.ModificaRelacionCuentaCuentaServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificarelacioncuentacuenta.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.RelacionesCuentaWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModificaRelacionCuentaCuentaBackEnd extends BackEndBean{

	private static final long serialVersionUID = 2674402462314232845L;

	@Autowired
	RelacionesCuentaWrapper relacionesCuentaWrapper;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	public Boolean ejecutarTRN(CuentaRelacionadaBean relacionadaBean, CuentaBean cuenta, Boolean reactivacion){
		Ejecutar.ITRMODRLRXPANTTRNI contexto = initPeticion(relacionadaBean, cuenta, reactivacion);
		
		EjecutarResult respuesta = ejecutarWS(contexto);
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			throw ce;
		}
		if(verificaResponseBansefi(respuesta)){
			return getResultado(respuesta.getResponseBansefi(), relacionadaBean);
		}
		return false;
	}
	
	
	public Boolean ejecutarTRN(CuentaRelacionadaBean relacionadaBean, CuentaBean cuenta){
		Ejecutar.ITRMODRLRXPANTTRNI contexto = initPeticion(relacionadaBean, cuenta, false);
		
		EjecutarResult respuesta = ejecutarWS(contexto);
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			throw ce;
		}
		if(verificaResponseBansefi(respuesta)){
			return getResultado(respuesta.getResponseBansefi(), relacionadaBean);
		}
		return false;
	}
	
	private Boolean getResultado(ResponseBansefi respuesta, CuentaRelacionadaBean relacionadaBean){
		Boolean exito = false;		
		if(verificaRespuestaModificacion(respuesta)){
			relacionadaBean.setEstadoListado(EstadoListadosEnum.CONSULTADO);
			exito = true;
		}
		return exito;
	}
	
	private Ejecutar.ITRMODRLRXPANTTRNI initPeticion(CuentaRelacionadaBean relacionadaBean, CuentaBean cuenta,
			Boolean reactivacion){
		Ejecutar.ITRMODRLRXPANTTRNI contexto = new Ejecutar.ITRMODRLRXPANTTRNI();
		Ejecutar.ITRMODRLRXPANTTRNI.TRMODRLRXPANTEVTY cuerpoContexto = 
				new Ejecutar.ITRMODRLRXPANTTRNI.TRMODRLRXPANTEVTY();
		Ejecutar.ITRMODRLRXPANTTRNI.TRMODRLRXPANTEVTY.DATOSRLRXSINMODV original = 
				new Ejecutar.ITRMODRLRXPANTTRNI.TRMODRLRXPANTEVTY.DATOSRLRXSINMODV();
		Ejecutar.ITRMODRLRXPANTTRNI.TRMODRLRXPANTEVTY.DATOSRLRXMODV modificado =
				new Ejecutar.ITRMODRLRXPANTTRNI.TRMODRLRXPANTEVTY.DATOSRLRXMODV();
		Ejecutar.ITRMODRLRXPANTTRNI.STDTRNIPARMV terminal = 
				new Ejecutar.ITRMODRLRXPANTTRNI.STDTRNIPARMV();
		contexto.setTRMODRLRXPANTEVTY(cuerpoContexto);
		contexto.setSTDTRNIPARMV(terminal);
		cuerpoContexto.setDATOSRLRXMODV(modificado);
		cuerpoContexto.setDATOSRLRXSINMODV(original);
		super.initialize(contexto);
		
		relacionesCuentaWrapper.wrappBean(cuerpoContexto, cuenta, relacionadaBean);
		
		terminal.setIDINTERNOTERMTN(super.getTerminal());
		terminal.setCODTX(CodTxConstants.COD_TX_TR_MOD_RL_RX_PANT_TRN);
		
		original.getRXACRLACE().setCODNRBEEN(super.getEntidad());
		original.getRXACRLACV().setCODNUMRCOMONEDA(ConstantesFuncionales.COD_MONEDA);
		
		modificado.getRXACRLACE().setCODNRBEEN(super.getEntidad());
		modificado.getRXACRLACV().setCODNUMRCOMONEDA(ConstantesFuncionales.COD_MONEDA);
		modificado.getRXACRLACV().setCODRZNECVACAC(relacionadaBean.getRazonCambio());
		
		if(relacionadaBean.getGarantia() != null){
			modificado.getRXACRLACV().setIMPGRTZAI(relacionadaBean.getGarantia());
		}
		
		if(relacionadaBean.getAbono() != null){
			modificado.getRXACRLACV().setPCT(relacionadaBean.getAbono());
		}
		
		if(relacionadaBean.getCargo() != null){
			modificado.getRXACRLACV().setPCT(relacionadaBean.getCargo());
		}
		
		if(ConstantesFuncionales.COD_ECV_AC_AC_INACTIVA.equals(relacionadaBean.getEstadoRelacion())){
			modificado.getRXACRLACE().setCODECVACAC(ConstantesFuncionales.COD_ECV_AC_AC_SOLICITADA);
		}
		
		if(reactivacion){
			modificado.getRXACRLACE().setFECHACRRE(ConstantesFuncionales.MAX_FECHA_FIN_INTEGER);
		}

		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRMODRLRXPANTTRNI contexto){
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ModificaRelacionCuentaCuentaServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta de "
					+ "relaciones persona-cuenta.", e);
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
	 * de la persona física.
	 * 
	 * @param response Respuesta Bansefi con datos de la persona moral
	 * @return <code>true</code> si la respuesta Bansefi contiene un cliente de tipo persona moral
	 */
	private Boolean verificaRespuestaModificacion(ResponseBansefi response){
		Boolean noNulo = false;
		if(response != null && response.getOTRMODRLRXPANTTRNO() != null){
			noNulo = true;
		}
		return noNulo;
	}
}
