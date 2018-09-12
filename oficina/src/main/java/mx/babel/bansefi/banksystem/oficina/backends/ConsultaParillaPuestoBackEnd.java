package mx.babel.bansefi.banksystem.oficina.backends;

import java.math.BigDecimal;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;
import mx.babel.bansefi.banksystem.oficina.webservices.consultaparrillapuesto.ConsultaParrillaPuestoServicio;
import mx.babel.bansefi.banksystem.oficina.webservices.consultaparrillapuesto.Ejecutar;
import mx.babel.bansefi.banksystem.oficina.webservices.consultaparrillapuesto.EjecutarResult;
import mx.babel.bansefi.banksystem.oficina.webservices.consultaparrillapuesto.ResponseBansefi;
import mx.babel.bansefi.banksystem.oficina.webservices.consultaparrillapuesto.ResponseBansefi.OTRCUADREOBTEDATOSTRN.CUADRELP.TNCUADRELP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back End para la consulta de parrilla asiganda a un puesto
 * @author mario.montesdeoca
 *
 */
@Component
public class ConsultaParillaPuestoBackEnd extends BackEndBean{

	private static final long serialVersionUID = 1152944066586476107L;

	@Autowired
    ServicioWebUtils servicioWebUtils;

    private static final String MONEDA = "M";
    private static final String BILLETE = "B";
    
    /**
	 * Método que consulta las existencias de denominaciones en un centro
	 * @return Lista de existencias de denominaciones
	 */
	public void ejecutarTRN(List<ExistenciaDenominacionBean> denominaciones){
		Ejecutar.ITRCUADREOBTEDATOSTRN contexto = initPeticion();
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
			getParrilla(respuesta.getResponseBansefi(), denominaciones);
		}
	}
	
	/**
	 * Método que construye la lista de existencias de denominaciones a partir de la respuesta del ws
	 * @param respuesta del ws
	 * @return lista de existencia de denominaciones
	 */
	private void getParrilla(ResponseBansefi respuesta, List<ExistenciaDenominacionBean> denominaciones){
		if(verificaRespuesta(respuesta)){
			for (TNCUADRELP denominacion : respuesta.getOTRCUADREOBTEDATOSTRN().getCUADRELP().getTNCUADRELP()) {
				if(BigDecimal.ZERO.compareTo(denominacion.getVALORMONEDA()) < 0){
					ExistenciaDenominacionBean denominacionBean = getDenominacion(denominacion, denominaciones);
					if(denominacionBean != null){
						if(denominacion.getTOTAL().get(0).getIMPNOMINAL().compareTo(BigDecimal.ZERO) != 0){
							denominacionBean.setImporteModificable(denominacion.getTOTAL().get(0).getIMPNOMINAL());
						}
						denominacionBean.setValorFacial(denominacion.getCODVALORFACIAL());
						denominacionBean.setOrigen(denominacion.getCODDSTN());
					}
				}
			}
		}
	}
	
	/**
	 * Método para obtener una denominación a partir de un objeto de respuesta
	 * @param denominacion Denominación obtenida a través del ws
	 * @param denominaciones lista de denominaciones generada por la aplicación
	 * @return denominación correspondiente
	 */
	private ExistenciaDenominacionBean getDenominacion(TNCUADRELP denominacion, List<ExistenciaDenominacionBean> denominaciones){
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
	 * Método para inicializar objeto de petición
	 * @return Objeto de petición al ws
	 */
	private Ejecutar.ITRCUADREOBTEDATOSTRN initPeticion(){
		Ejecutar.ITRCUADREOBTEDATOSTRN contexto = 
				new Ejecutar.ITRCUADREOBTEDATOSTRN();
		Ejecutar.ITRCUADREOBTEDATOSTRN.TNCUADRECBV cuerpoContexto=
				new Ejecutar.ITRCUADREOBTEDATOSTRN.TNCUADRECBV();
		contexto.setTNCUADRECBV(cuerpoContexto);
		super.initialize(contexto);
		
		cuerpoContexto.setCODNRBEEN(super.getEntidad());
		cuerpoContexto.setCODINTERNOUO(super.getSucursal());
		cuerpoContexto.setIDINTERNOTERMTN(super.getTerminal());
		
		return contexto;
	}
	
	/**
	 * Método para obtener la respuesta del ws a partir de un objeto de petición
	 * @param contexto Objeto de petición
	 * @return respuesta del ws
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRCUADREOBTEDATOSTRN contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaParrillaPuestoServicio.class, contexto);
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
		if(response != null && response.getOTRCUADREOBTEDATOSTRN() != null &&
				response.getOTRCUADREOBTEDATOSTRN().getCUADRELP() != null &&
				response.getOTRCUADREOBTEDATOSTRN().getCUADRELP().getTNCUADRELP() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
}
