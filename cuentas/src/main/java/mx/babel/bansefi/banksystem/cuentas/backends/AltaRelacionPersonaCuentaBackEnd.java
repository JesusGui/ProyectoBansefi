package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.RelacionadoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;
import mx.babel.bansefi.banksystem.cuentas.webservices.altarelacionpersonacuenta.AltaRelacionPersonaCuentaServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.altarelacionpersonacuenta.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.altarelacionpersonacuenta.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.altarelacionpersonacuenta.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.webservices.altarelacionpersonacuenta.ResponseBansefi.OTRALTARPPANTTRNO.STDANAVMSJV.STDANAVMSJLS;
import mx.babel.bansefi.banksystem.cuentas.wrappers.RelacionesCuentaWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * BackEnd para alta de relaciòn persona-cuenta
 * @author mario.montesdeoca
 *
 */
@Component
public class AltaRelacionPersonaCuentaBackEnd extends BackEndBean{

	private static final long serialVersionUID = 758469545533692071L;
	
	private static final int FECHA_CIERRE = 99991231;
	
	private static final int NUM_ORDEN = 0;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	@Autowired
	RelacionesCuentaWrapper relacionesCuentaWrapper;
	
	/**
	 * Mètodo para dar de alta una relaciòn de una cuenta con una persona
	 * @param cuentaBean Cuenta a la que se le relacionarà la persona
	 * @param relacionadoBean Bean con los datos de la relaciòn
	 * @return <code>true</code> en caso de que el alta sea exitosa
	 */
	public Boolean ejecutarTRN(CuentaBean cuentaBean, RelacionadoBean relacionadoBean){
		Ejecutar.ITRALTARPPANTTRNI contexto = initPeticion(cuentaBean, relacionadoBean);
		
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				String error = "Error en alta de relación: ";
				List<STDANAVMSJLS> anotaciones = respuesta.getResponseBansefi().getOTRALTARPPANTTRNO().getSTDANAVMSJV().getSTDANAVMSJLS();
				for (int i = 0; i < respuesta.getResponseBansefi().getOTRALTARPPANTTRNO().getSTDANAVMSJV().getANNUMANOTACIONESV().getSTDDEC3(); i++) {
					error = error + anotaciones.get(i).getSTDDESCRCANTCNV().get(0).getSTDCHAR30() +";";
				}
				throw new ControlableException("Error en alta de relación",error);
			}
		}
				
		return isAltaCorrecta(respuesta.getResponseBansefi(), relacionadoBean);
	}
	
	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 * 
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRALTARPPANTTRNI initPeticion(CuentaBean cuentaBean, RelacionadoBean relacionadoBean){
		Ejecutar.ITRALTARPPANTTRNI contexto = new Ejecutar.ITRALTARPPANTTRNI();
		Ejecutar.ITRALTARPPANTTRNI.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRALTARPPANTTRNI.STDTRNIPARMV();
		contexto = relacionesCuentaWrapper.wrappBean(cuentaBean, relacionadoBean);
		contexto.setSTDTRNIPARMV(contextoEntrada);
		super.initialize(contexto);
		
		contexto.getTRALTARPPANTEVTY().getRPPERSRLACE().setCODNRBEEN(super.getEntidad());
		contexto.getTRALTARPPANTEVTY().getRPPERSRLACE().setFECHACRRE(FECHA_CIERRE);
		contexto.getTRALTARPPANTEVTY().getRPPERSRLACE().setNUMRLORDEN(NUM_ORDEN);
		
		super.initialize(contexto.getTRALTARPPANTEVTY().getRPPERSRLACV());
		contexto.getTRALTARPPANTEVTY().getRPPERSRLACV().setCODNUMRCOMONEDA(ConstantesFuncionales.COD_MONEDA);
		
		if(relacionadoBean.getPorcentaje() != null){
			contexto.getTRALTARPPANTEVTY().getPCTV().setPCTCOEF(relacionadoBean.getPorcentaje());;
		}
		
		contexto.getDATOSACUERDOV().setCODNRBEEN(super.getEntidad());
		contexto.getDATOSACUERDOV().setCODNUMRCOMONEDA(ConstantesFuncionales.COD_MONEDA);
		
		contexto.getCODINTERNOUOV().setCODINTERNOUO(super.getSucursal());
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_ALTA_RP_PANT_TRN);
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRALTARPPANTTRNI contexto) 
			throws NoControlableException{
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					AltaRelacionPersonaCuentaServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de alta de "
					+ "relación persona-cuenta.", e);
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
	private Boolean isAltaCorrecta(ResponseBansefi response, RelacionadoBean relacionado){
		Boolean correcta = false;		
		if(verificaRespuestaAlta(response)){
			ResponseBansefi.OTRALTARPPANTTRNO.TRALTARPPANTEVTZ.RPPERSRLACE persona = 
					response.getOTRALTARPPANTTRNO().getTRALTARPPANTEVTZ().getRPPERSRLACE();
			if(persona.getCODECVPERSAC() != null){
				correcta = true;
				relacionado.setEstadoListado(EstadoListadosEnum.CONSULTADO);
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
		if(response != null && response.getOTRALTARPPANTTRNO() != null && 
				response.getOTRALTARPPANTTRNO().getTRALTARPPANTEVTZ() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
}
