package mx.babel.bansefi.banksystem.base.backends;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.movimientos.ConsultaMovimientosBean;
import mx.babel.bansefi.banksystem.base.beans.movimientos.MovimientoBean;
import mx.babel.bansefi.banksystem.base.utils.MovimientosWrapper;
import mx.babel.bansefi.banksystem.base.webservices.consultamovimientos.ConsultaMovimientosServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultamovimientos.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultamovimientos.ResponseBansefi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para consultas de movimientos en una cuenta
 * 
 * @author mario.montesdeoca
 *
 */
@Component
public class ConsultaMovimientosBackEnd extends BackEndBean{
	
	private static final long serialVersionUID = -3787043631611707277L;

	@Autowired
	MovimientosWrapper movimientosWrapper;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	private static final DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	/**
	 * Función que obtiene los movimientos de una cuenta a través de un servicio web.
	 * 
	 * @param consultaMovimientosBean Bean con detalles para la consulta de movimentos.
	 * @return Lista de movimientos de la cuenta.
	 * @throws NoControlableException
	 * @throws ControlableException
	 */
	public List<MovimientoBean> ejecutarWS(ConsultaMovimientosBean consultaMovimientosBean)
			throws NoControlableException, ControlableException{
		List<MovimientoBean> movimientos = new ArrayList<MovimientoBean>();
		EjecutarResult respuesta = getRespuesta(consultaMovimientosBean);
		
		if(verificaRespuesta(respuesta)){
			if(respuesta.getResponseBansefi() != null && 
					respuesta.getResponseBansefi().getResponseBansefi() != null &&
					respuesta.getResponseBansefi().getResponseBansefi().size() != 0){
				consultaMovimientosBean.getPaginacionMovimientos()
					.setMasDatos(respuesta.getCODIGO().trim().equals("ARQP005"));
				for(ResponseBansefi movimiento : 
					respuesta.getResponseBansefi().getResponseBansefi()){
					MovimientoBean movimientoBean = movimientosWrapper.wrappMovimiento(movimiento);
					movimientos.add(movimientoBean);
				}
				consultaMovimientosBean.getPaginacionMovimientos()
					.setUltimoDatoConsultaAnterior(movimientos.get(movimientos.size()-1).getNumSec());
			}
		}else{
            throw new NoControlableException("No hay respuesta al servicio","Respuesta null.");
        }
		return movimientos;
	}
	
	/**
	 * Función que ejecuta el servicio web a partir de los datos recibidos por medio del bean de consulta de movimientos.
	 * @param consultaMovimientosBean
	 * @return objeto con la respuesta del servicio web
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	private EjecutarResult getRespuesta(ConsultaMovimientosBean consultaMovimientosBean)
			throws ControlableException, NoControlableException{
		EjecutarResult respuesta = null;
		BigInteger num_sec;
		
		if(consultaMovimientosBean.getPaginacionMovimientos().getUltimoDatoConsultaAnterior() != null){
			num_sec = new BigInteger(consultaMovimientosBean.getPaginacionMovimientos().getUltimoDatoConsultaAnterior().toString());
		}
		else{
			num_sec = new BigInteger("0");
		}
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils
	                .ejecutarWS(ConsultaMovimientosServicio.class, super.getEntidad(), 
	                		new BigInteger(consultaMovimientosBean.getDetalleCuentaUtils().getNumeroCuenta().toString()),
	                        formatter.format(consultaMovimientosBean.getFechaDesde()),
	                        formatter.format(consultaMovimientosBean.getFechaHasta()),
	                        num_sec,
	                        consultaMovimientosBean.getDetalleCuentaUtils().getCodigoDocumento(),
	                        consultaMovimientosBean.getDetalleCuentaUtils().getIdExterno());			
		}catch(NumberFormatException e){
			throw new ControlableException("No se puede realizar la consulta", e.getCause());
		}catch(IllegalArgumentException e) {
			throw new ControlableException("No se puede realizar la consulta", e.getCause());
		}catch(NullPointerException e){
			throw new ControlableException("No se puede realizar la consulta", "Faltan datos para realizar la consulta.");
		}
		
		return respuesta;
	}
	
	private Boolean verificaRespuesta(EjecutarResult respuesta){
		if(respuesta != null && ("0").equals(respuesta.getESTATUS())){
			return true;
		}
		return false;
	}
	
}
