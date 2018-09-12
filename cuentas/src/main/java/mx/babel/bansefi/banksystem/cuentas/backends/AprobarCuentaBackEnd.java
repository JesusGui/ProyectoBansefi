package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.webservices.aprobarcuenta.AprobarCuentaServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.aprobarcuenta.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.aprobarcuenta.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.aprobarcuenta.ResponseBansefi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AprobarCuentaBackEnd extends BackEndBean{

	private static final long serialVersionUID = -5897438472755281299L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	public String ejecutarTRN(Long numeroCuenta){
		Ejecutar.ITRAPROBARACTRNI contexto = initPeticion(numeroCuenta);
		
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return "";
			}
		}
		
		return getAprobacion(respuesta.getResponseBansefi());
	}
	
	/**
	 * Mètodo que devuelde el estado del acuerdo después de ser aporbado
	 * @param respuesta objeto respuesta del ws
	 * @return Estado del acuerdo
	 */
	private String getAprobacion(ResponseBansefi respuesta){
		String aprobacion = "";		
		if (respuesta.getOTRAPROBARACTRNO() != null && respuesta.getOTRAPROBARACTRNO().getTRAPROBARACEVTZ() != null){
			aprobacion = respuesta.getOTRAPROBARACTRNO().getTRAPROBARACEVTZ().getCODECVAC();
		}
		return aprobacion;
	}
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param numeroCuenta Numero de la cuenta que se quiere aprobar
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITRAPROBARACTRNI initPeticion(Long numeroCuenta) {
		Ejecutar.ITRAPROBARACTRNI contexto = new Ejecutar.ITRAPROBARACTRNI();
		Ejecutar.ITRAPROBARACTRNI.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRAPROBARACTRNI.STDTRNIPARMV();
		Ejecutar.ITRAPROBARACTRNI.TRAPROBARACEVTY cuerpoContexto =
				new Ejecutar.ITRAPROBARACTRNI.TRAPROBARACEVTY();
		Ejecutar.ITRAPROBARACTRNI.TRAPROBARACEVTY.ACACP acuerdo = 
				new Ejecutar.ITRAPROBARACTRNI.TRAPROBARACEVTY.ACACP();
		cuerpoContexto.setACACP(acuerdo);
		contexto.setSTDTRNIPARMV(contextoEntrada);
		contexto.setTRAPROBARACEVTY(cuerpoContexto);
		super.initialize(contexto);
		
		acuerdo.setCODNRBEEN(super.getEntidad());
		acuerdo.setNUMSECAC(numeroCuenta);
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_APROBAR_AC_TRN);
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRAPROBARACTRNI contexto){
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(AprobarCuentaServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de aprobación de "
					+ "cuenta.", e);
		}
		return respuesta;
	}	
	
}
