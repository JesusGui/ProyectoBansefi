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
import mx.babel.bansefi.banksystem.cuentas.webservices.altarelacioncuentacuenta.AltaRelacionCuentaCuentaServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.altarelacioncuentacuenta.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.altarelacioncuentacuenta.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.altarelacioncuentacuenta.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.RelacionesCuentaWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * BackEnd para alta de relaciòn cuenta-cuenta
 * @author mario.montesdeoca
 *
 */
@Component
public class AltaRelacionCuentaCuentaBackEnd extends BackEndBean{
	
	private static final long serialVersionUID = 2205514729046840951L;
	
	private static final int EVENT_CD = 1;
	private static final String COD_ECV_AC_AC = "2";
	private static final int FECHA_CRRE = 99991231;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	@Autowired
	RelacionesCuentaWrapper relacionesCuentaWrapper;
	
	/**
	 * Mètodo para dar de alta una relaciòn de una cuenta con otra cuenta
	 * @param cuentaBean cuenta a la que se le relacionarà la cuenta
	 * @param cuentaRelacionadaBean Bean con los datos de la relaciòn
	 * @return <code>true</code> en caso de que el alta sea exitosa
	 */
	public Boolean ejecutarTRN(CuentaBean cuentaBean, CuentaRelacionadaBean cuentaRelacionadaBean){
		Ejecutar.ITRALTARXPANTTRNI contexto = initPeticion(cuentaBean, cuentaRelacionadaBean);
		
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
		
		return isAltaCorrecta(respuesta.getResponseBansefi(), cuentaRelacionadaBean);
	}
	
	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 * 
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRALTARXPANTTRNI initPeticion(CuentaBean cuentaBean, CuentaRelacionadaBean cuentaRelacionadaBean){
		Ejecutar.ITRALTARXPANTTRNI contexto = new Ejecutar.ITRALTARXPANTTRNI();
		Ejecutar.ITRALTARXPANTTRNI.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRALTARXPANTTRNI.STDTRNIPARMV();
		Ejecutar.ITRALTARXPANTTRNI.TRALTARXPANTEVTY cuerpoContexto =
				new Ejecutar.ITRALTARXPANTTRNI.TRALTARXPANTEVTY();
		
		contexto.setTRALTARXPANTEVTY(cuerpoContexto);
		contexto.setSTDTRNIPARMV(contextoEntrada);
		
		Ejecutar.ITRALTARXPANTTRNI.DATOSACUERDOV detalleCuenta = relacionesCuentaWrapper.wrappBean(cuentaBean);
		detalleCuenta.setCODNRBEEN(super.getEntidad());
		detalleCuenta.setCODNUMRCOMONEDA(ConstantesFuncionales.COD_MONEDA);
		super.initialize(detalleCuenta);
		contexto.getDATOSACUERDOV().add(detalleCuenta);
		
		Ejecutar.ITRALTARXPANTTRNI.DATOSACUERDOV detalleCuentaRelacionada = relacionesCuentaWrapper.wrappBean(cuentaRelacionadaBean.getCuenta());
		detalleCuentaRelacionada.setCODNRBEEN(super.getEntidad());		
		detalleCuentaRelacionada.setCODNUMRCOMONEDA(ConstantesFuncionales.COD_MONEDA);
		super.initialize(detalleCuentaRelacionada);		
		contexto.getDATOSACUERDOV().add(detalleCuentaRelacionada);
				
		relacionesCuentaWrapper.wrappBean(cuentaBean, cuentaRelacionadaBean, cuerpoContexto);
		cuerpoContexto.getRXACRLACE().setCODNRBEEN(getEntidad());
		cuerpoContexto.getRXACRLACE().setFECHACRRE(AltaRelacionCuentaCuentaBackEnd.FECHA_CRRE);
		cuerpoContexto.getRXACRLACE().setCODECVACAC(AltaRelacionCuentaCuentaBackEnd.COD_ECV_AC_AC);
		cuerpoContexto.getRXACRLACV().setCODNUMRCOMONEDA(ConstantesFuncionales.COD_MONEDA);
		
		if(ConstantesFuncionales.REL_AC_AC_ABONO_INTERES.equals(cuentaRelacionadaBean.getTipoRelacion())){
			cuerpoContexto.getRXACRLACV().setPCT(cuentaRelacionadaBean.getAbono());
		}
		if(ConstantesFuncionales.REL_AC_AC_CARGO_INTERES.equals(cuentaRelacionadaBean.getTipoRelacion())){
			cuerpoContexto.getRXACRLACV().setPCT(cuentaRelacionadaBean.getCargo());
		}
		if(ConstantesFuncionales.REL_AC_AC_GARANTIA.equals(cuentaRelacionadaBean.getTipoRelacion())){
			cuerpoContexto.getRXACRLACV().setIMPGRTZAI(cuentaRelacionadaBean.getGarantia());
		}
		
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_ALTA_RX_PANT_TRN);
		contexto.setEVENTCD(AltaRelacionCuentaCuentaBackEnd.EVENT_CD);
		
		super.initialize(contexto);
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRALTARXPANTTRNI contexto) 
			throws NoControlableException{
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					AltaRelacionCuentaCuentaServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de alta de "
					+ "relación cuenta-cuenta.", e);
		}
		return respuesta;
	}
	
	/**
	 * Función encargada de obtener el cliente a partir de la respuesta del servicio web
	 * 
	 * @param idInterno El id interno de la persona moral
	 * @param response El objeto de reultado enviado por el servicio web.
	 * @return El cliente con los datos recibidos del ser web
	 */
	private Boolean isAltaCorrecta(ResponseBansefi response, CuentaRelacionadaBean cuentaRelacionadaBean){
		Boolean correcta = false;		
		if(verificaRespuestaAlta(response)){
			ResponseBansefi.OTRALTARXPANTTRNO.TRALTARXPANTEVTZ.RXACRLACE relacion = 
					response.getOTRALTARXPANTTRNO().getTRALTARXPANTEVTZ().getRXACRLACE();
			if(relacion.getCODRLACAC() != null){
				cuentaRelacionadaBean.setEstadoListado(EstadoListadosEnum.CONSULTADO);
				correcta = true;
			}
		}
		return correcta;
	}
	
	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con los datos
	 * del alta de realción.
	 * 
	 * @param response Respuesta Bansefi con datos del alta
	 * @return <code>true</code> si la respuesta Bansefi contiene los datos de alta
	 */
	private Boolean verificaRespuestaAlta(ResponseBansefi response){
		Boolean noNulo = false;
		if(response != null && response.getOTRALTARXPANTTRNO() != null && 
				response.getOTRALTARXPANTTRNO().getTRALTARXPANTEVTZ() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
}
