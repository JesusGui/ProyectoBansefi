package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.RelacionadoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificarelacioncuentapersona.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificarelacioncuentapersona.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificarelacioncuentapersona.ModificaRelacionCuentaPersonaServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificarelacioncuentapersona.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.RelacionesCuentaWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModificaRelacionCuentaPersonaBackEnd extends BackEndBean{
	
	private static final long serialVersionUID = -1095246144974969362L;
	
	private static final int FECHA_INICIO_X = 11111111;

	@Autowired
	RelacionesCuentaWrapper relacionesCuentaWrapper;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;

	public Boolean ejecutarTRN(RelacionadoBean relacionadoBean, CuentaBean cuenta){
		Ejecutar.ITRMODRLRPPANTTRNI contexto = initPeticion(relacionadoBean, cuenta);
		
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
			return getResultado(respuesta.getResponseBansefi(), relacionadoBean);
		}
		return false;
	}
	
	private Boolean getResultado(ResponseBansefi respuesta, RelacionadoBean relacionadoBean){
		Boolean exito = false;		
		if(verificaRespuestaModificacion(respuesta)){
			relacionadoBean.setEstadoListado(EstadoListadosEnum.CONSULTADO);
			exito = true;
		}
		return exito;
	}
	
	private Ejecutar.ITRMODRLRPPANTTRNI initPeticion(RelacionadoBean relacionadoBean, CuentaBean cuenta){
		Ejecutar.ITRMODRLRPPANTTRNI contexto = new Ejecutar.ITRMODRLRPPANTTRNI();
		Ejecutar.ITRMODRLRPPANTTRNI.TRMODRLRPPANTEVTY cuerpoContexto = 
				new Ejecutar.ITRMODRLRPPANTTRNI.TRMODRLRPPANTEVTY();
		Ejecutar.ITRMODRLRPPANTTRNI.TRMODRLRPPANTEVTY.DATOSRLSINMODV original = 
				new Ejecutar.ITRMODRLRPPANTTRNI.TRMODRLRPPANTEVTY.DATOSRLSINMODV();
		Ejecutar.ITRMODRLRPPANTTRNI.TRMODRLRPPANTEVTY.DATOSRLMODV modificado =
				new Ejecutar.ITRMODRLRPPANTTRNI.TRMODRLRPPANTEVTY.DATOSRLMODV();
		Ejecutar.ITRMODRLRPPANTTRNI.DATOSACUERDOV acuerdo =
				new Ejecutar.ITRMODRLRPPANTTRNI.DATOSACUERDOV();
		Ejecutar.ITRMODRLRPPANTTRNI.STDTRNIPARMV terminal = 
				new Ejecutar.ITRMODRLRPPANTTRNI.STDTRNIPARMV();
		Ejecutar.ITRMODRLRPPANTTRNI.RPPERSRLACP relacion =
				new Ejecutar.ITRMODRLRPPANTTRNI.RPPERSRLACP();
		contexto.setTRMODRLRPPANTEVTY(cuerpoContexto);
		contexto.setDATOSACUERDOV(acuerdo);
		contexto.setSTDTRNIPARMV(terminal);
		contexto.setRPPERSRLACP(relacion);
		cuerpoContexto.setDATOSRLMODV(modificado);
		cuerpoContexto.setDATOSRLSINMODV(original);
		super.initialize(contexto);
		
		relacionesCuentaWrapper.wrappBean(contexto, relacionadoBean);
		
		terminal.setIDINTERNOTERMTN(super.getTerminal());
		terminal.setCODTX(CodTxConstants.COD_TX_TR_MOD_RL_RP_PANT_TRN);
		
		acuerdo.setCODNRBEEN(super.getEntidad());
		acuerdo.setNUMSECAC(cuenta.getNumeroCuenta());
		acuerdo.setCODNUMRCOMONEDA(ConstantesFuncionales.COD_MONEDA);
		
		relacion.setCODNRBEEN(super.getEntidad());
		relacion.setNUMSECAC(cuenta.getNumeroCuenta());
		
		original.getRPPERSRLACE().setCODNRBEEN(super.getEntidad());
		original.getRPPERSRLACE().setNUMSECAC(cuenta.getNumeroCuenta());
		
		original.getRPPERSRLACV().setCODLINEA(cuenta.getCodLinea());
		original.getRPPERSRLACV().setIDGRPPD(cuenta.getIdGrupoProducto());
		original.getRPPERSRLACV().setIDPDV(cuenta.getIdProducto());
		original.getRPPERSRLACV().setCODNUMRCOMONEDA(ConstantesFuncionales.COD_MONEDA);
		original.getRPPERSRLACV().setFECHAINICIOI(FECHA_INICIO_X);
		original.getRPPERSRLACV().setFECHAINICIOR(FECHA_INICIO_X);
		
		modificado.getRPPERSRLACE().setCODNRBEEN(super.getEntidad());
		modificado.getRPPERSRLACE().setNUMSECAC(cuenta.getNumeroCuenta());
		
		modificado.getRPPERSRLACV().setCODLINEA(cuenta.getCodLinea());
		modificado.getRPPERSRLACV().setIDGRPPD(cuenta.getIdGrupoProducto());
		modificado.getRPPERSRLACV().setIDPDV(cuenta.getIdProducto());
		modificado.getRPPERSRLACV().setFECHAINICIOI(FECHA_INICIO_X);
		modificado.getRPPERSRLACV().setFECHAINICIOR(FECHA_INICIO_X);
		
		if(relacionadoBean.getGarantia() != null){
			modificado.getRPPERSRLACV().setIMPGRTZAI(relacionadoBean.getGarantia());
		}
		
		if(relacionadoBean.getPorcentaje() != null){
			modificado.getPCTV().setPCTCOEF(relacionadoBean.getPorcentaje());
		}
		
		if(ConstantesFuncionales.COD_ECV_PERS_AC_ACTIVA.equals(relacionadoBean.getEstado())){
			modificado.getRPPERSRLACE().setFECHACRRE(99991231);
		}
		
		if(ConstantesFuncionales.COD_ECV_PERS_AC_INACTIVA.equals(relacionadoBean.getEstado())){
			modificado.getRPPERSRLACE().setCODECVPERSAC(ConstantesFuncionales.COD_ECV_PERS_AC_SOLICITADA);
		}
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRMODRLRPPANTTRNI contexto){
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ModificaRelacionCuentaPersonaServicio.class, contexto);
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
		if(response != null && response.getOTRMODRLRPPANTTRNO() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
}
