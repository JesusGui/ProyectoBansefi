package mx.babel.bansefi.banksystem.oficina.backends;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.babel.arq.comun.utils.EstadoEnum;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.ApuntesBean;
import mx.babel.bansefi.banksystem.base.utils.FechaUtils;
import mx.babel.bansefi.banksystem.oficina.webservices.busquedaapunte.BusquedaApunteServicio;
import mx.babel.bansefi.banksystem.oficina.webservices.busquedaapunte.Ejecutar;
import mx.babel.bansefi.banksystem.oficina.webservices.busquedaapunte.EjecutarResult;
import mx.babel.bansefi.banksystem.oficina.webservices.busquedaapunte.ResponseBansefi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BusquedaApunteBackEnd extends BackEndBean{

	private static final long serialVersionUID = 6097883678765614202L;

	@Autowired
	ContextoUtils contextoUtils;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
    
	/**
	 * Método que ejecuta el servicio de búsqueda de un apunte.
	 * 
	 * @param datos del apunte a busca
	 * @return ApuntesBean con los datos recuperados.
	 */
	public ApuntesBean ejecutarWS(ApuntesBean apunte){
		
		Ejecutar contextoEntrada = new Ejecutar();
		
		contextoEntrada.setUSERHEADER(super.getUsuarioId());
		contextoEntrada.setPASSHEADER(super.getPassword());
		contextoEntrada.setIPHEADER(super.getIp());
		contextoEntrada.setENTIDAD(super.getEntidad());
		
		contextoEntrada.setCENTRO(apunte.getCentro());
		contextoEntrada.setTERMIN(super.getTerminal());
		contextoEntrada.setIDTASK(apunte.getNumTransaccion());
		
		// Se ejecuta el WebService correspondiente
		ApuntesBean response = new ApuntesBean();
		
		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
				BusquedaApunteServicio.class, this.formatearFecha(apunte.getFechaValor()),
				contextoEntrada.getIDTASK(),
				contextoEntrada.getENTIDAD(), contextoEntrada.getCENTRO(),
				contextoEntrada.getTERMIN(), null);

		int codigo = Integer.parseInt(respuesta.getESTATUS());

		if (codigo != 0) {
			if ("ARQE160".equals(respuesta.getCODIGO().trim())) {
				response.setStatus(1);
			} else {
				EstadoEnum.mensajesError("trn", codigo);
			}
			
		} else {
			if (respuesta.getResponseBansefi().getResponseBansefi().size() == 1) {
				 List<ResponseBansefi> responseBansefi = respuesta.getResponseBansefi().getResponseBansefi();
               
               boolean asignado = false;
               if(responseBansefi.size() > 1){
               	
               	for (ResponseBansefi elemento : responseBansefi) {
                   	
                   	response.setCentro(elemento.getCENTRO());
                   	response.setCodigoOperacion(elemento.getCODIGO());
                   	response.setConcepto(elemento.getSALIDA());
                   	response.setFechaOperacion(FechaUtils.formateaFecha(elemento.getFECHA(), "yyyy-mm-dd"));
                   	response.setHoraOperacion(elemento.getHORAINICIO());
                   	response.setImporte(BigDecimal.valueOf(Double.valueOf(elemento.getIMPORTE())));
                   	response.setNumCuenta(Long.valueOf(elemento.getACUERDO()));
                   	response.setNumTransaccion(elemento.getIDTASK());
                   	response.setPuesto(elemento.getCENTRO());
                   	response.setTerminal(elemento.getTERMIN());
                   	response.setTipoApunte(elemento.getSERVICIO());
                   	response.setTipoOperacion("Tipo de operación");
                   	response.setTitular(elemento.getUSUARIO());
                       asignado = true;
               	}               	
               }
               	
               if(!asignado){
               	response.setCentro(responseBansefi.get(0).getCENTRO());
               	response.setCodigoOperacion(responseBansefi.get(0).getCODIGO());
               	response.setConcepto(responseBansefi.get(0).getSALIDA());
               	response.setFechaOperacion(FechaUtils.formateaFecha(responseBansefi.get(0).getFECHA(), "yyyy-mm-dd"));
               	response.setHoraOperacion(responseBansefi.get(0).getHORAINICIO());
               	response.setImporte(BigDecimal.valueOf(Double.valueOf(responseBansefi.get(0).getIMPORTE())));
               	response.setNumCuenta(Long.valueOf(responseBansefi.get(0).getACUERDO()));
               	response.setNumTransaccion(responseBansefi.get(0).getIDTASK());
               	response.setPuesto(responseBansefi.get(0).getCENTRO());
               	response.setTerminal(responseBansefi.get(0).getTERMIN());
               	response.setTipoApunte(responseBansefi.get(0).getSERVICIO());
               	response.setTipoOperacion("Tipo de operación");
               	response.setTitular(responseBansefi.get(0).getUSUARIO());
               	
               }                	
			}
		}

		return response;
	}

	private String formatearFecha(Date fecha) {
		if (fecha != null) {
			Calendar cal = FechaUtils.dateACalendar(fecha);
			String dia = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
			String mes = String.valueOf(cal.get(Calendar.MONTH));
			String ano = String.valueOf(cal.get(Calendar.YEAR));
			
			String fechaTexto = ano + "-" + mes + "-" + dia;

			return fechaTexto;
		}
		
		return null;
		
	}
   
}
