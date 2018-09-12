package mx.babel.bansefi.banksystem.oficina.backends;

import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.oficina.beans.ArqueoPuestoBean;
import mx.babel.bansefi.banksystem.oficina.webservices.arqueopuesto.ArqueoPuestoServicio;
import mx.babel.bansefi.banksystem.oficina.webservices.arqueopuesto.Ejecutar;
import mx.babel.bansefi.banksystem.oficina.webservices.arqueopuesto.EjecutarResult;
import mx.babel.bansefi.banksystem.oficina.webservices.arqueopuesto.ResponseBansefi;
import mx.babel.bansefi.banksystem.oficina.webservices.arqueopuesto.ResponseBansefi.OTRTNCONTHOSTCNSTRN.TRCONSCONTHOSTEVTZ.TNCONTHOSTE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
* Back End para realizar el arqueo de un puesto
* @author mario.montesdeoca
*
*/
@Component
public class ArqueoPuestoBackEnd extends BackEndBean{

    private static final long serialVersionUID = -1837458352589788029L;

    @Autowired
    ServicioWebUtils servicioWebUtils;
    
    @Autowired
    CuadreArqueoPuestoBackEnd cuadreArqueoPuestoBackEnd;
    
    /**
	 * Mètodo que recibe un bean de arqueo de puesto y realiza su arqueo.
	 * @param puesto bean de arqueo de puesto
	 * @return <code>true</code> si la operaciòn fue realizada con èxito.
	 */
	public Boolean ejecutarTRN(ArqueoPuestoBean puesto){
		if(puesto != null){
			try{
				cuadreArqueoPuestoBackEnd.ejecutarTRN(puesto);
			}catch(ControlableException | NoControlableException ce){
				return false;
			}
			Ejecutar.ITRTNCONTHOSTCNSTRN contexto = initPeticion(puesto);
			
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
				return arqueoExitoso(respuesta.getResponseBansefi(), puesto);
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
	private Boolean arqueoExitoso(ResponseBansefi respuesta, ArqueoPuestoBean puesto){		
		if(verificaRespuesta(respuesta)){
			puesto.setArqueada(true);
			List<TNCONTHOSTE> contadores = respuesta.getOTRTNCONTHOSTCNSTRN().getTRCONSCONTHOSTEVTZ().getTNCONTHOSTE();
			for (TNCONTHOSTE contador : contadores) {
				if("CD".equals(contador.getCODCONTTERM())){
					puesto.setCobroOn(contador.getIMPNOMINAL());
				}
				if("CH".equals(contador.getCODCONTTERM())){
					puesto.setPagoOn(contador.getIMPNOMINAL());
				}
				if("ID".equals(contador.getCODCONTTERM())){
					puesto.setDebeOn(contador.getIMPNOMINAL());
				}
				if("IH".equals(contador.getCODCONTTERM())){
					puesto.setHaberOn(contador.getIMPNOMINAL());
				}
			}
			if(puesto.getCobroOn() != null && puesto.getPagoOn() != null ){
				puesto.setDifCajaOn(puesto.getCobroOn().add(puesto.getPagoOn().negate()));
			}
			if(puesto.getDebeOn() != null && puesto.getHaberOn() != null){
				puesto.setDifCuentaOn(puesto.getDebeOn().add(puesto.getHaberOn().negate()));
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Mètodo que inicializa la peticiòn hacie el ws
	 * @param centro bean de arqueo de centro
	 * @return objeto de peticiòn al ws
	 */
	private Ejecutar.ITRTNCONTHOSTCNSTRN initPeticion(ArqueoPuestoBean puesto){
		Ejecutar.ITRTNCONTHOSTCNSTRN contexto = new Ejecutar.ITRTNCONTHOSTCNSTRN();
		Ejecutar.ITRTNCONTHOSTCNSTRN.STDTRNIPARMV cuerpoContexto =
				new Ejecutar.ITRTNCONTHOSTCNSTRN.STDTRNIPARMV();
		Ejecutar.ITRTNCONTHOSTCNSTRN.TRCONSCONTHOSTEVTY arqueoPuesto = 
				new Ejecutar.ITRTNCONTHOSTCNSTRN.TRCONSCONTHOSTEVTY();
		Ejecutar.ITRTNCONTHOSTCNSTRN.TRCONSCONTHOSTEVTY.TNCONTHOSTP resultado = 
				new Ejecutar.ITRTNCONTHOSTCNSTRN.TRCONSCONTHOSTEVTY.TNCONTHOSTP();
		contexto.setSTDTRNIPARMV(cuerpoContexto);
		contexto.setTRCONSCONTHOSTEVTY(arqueoPuesto);
		arqueoPuesto.setTNCONTHOSTP(resultado);
		super.initialize(contexto);
		
		cuerpoContexto.setIDINTERNOTERMTN(super.getTerminal());
		cuerpoContexto.setCODTX(CodTxConstants.COD_TX_TR_TN_CONT_HOST_CNS_TRN);
		
		resultado.setIDINTERNOTERMTN(super.getTerminal());
		resultado.setCODNRBEEN(super.getEntidad());
		resultado.setCODINTERNOUO(super.getSucursal());
		resultado.setCODNUMRCOMONEDA(ConstantesFuncionales.COD_MONEDA);
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	public EjecutarResult ejecutarWS(Ejecutar.ITRTNCONTHOSTCNSTRN contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ArqueoPuestoServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de arqueo "
					+ "de puesto.", e);
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
		if(response != null && response.getOTRTNCONTHOSTCNSTRN() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
}
