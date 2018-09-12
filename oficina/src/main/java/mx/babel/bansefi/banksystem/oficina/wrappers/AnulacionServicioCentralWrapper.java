package mx.babel.bansefi.banksystem.oficina.wrappers;

import java.io.Serializable;

import mx.babel.bansefi.banksystem.oficina.beans.AnulacionServiciosCentralesRespuestaBean;
import mx.babel.bansefi.banksystem.oficina.beans.DiarioElectronicoDetalleRespuestaBean;
import mx.babel.bansefi.banksystem.oficina.webservices.anulacionservicioscentrales.Ejecutar;
import mx.babel.bansefi.banksystem.oficina.webservices.anulacionservicioscentrales.ResponseBansefi;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnulacionServicioCentralWrapper implements Serializable{

	private static final long serialVersionUID = -1010392378990514431L;
	
	@Autowired
	DozerBeanMapper dozerBeanMapper;
	
	/**
	 * Funcion que wrappea la salida despues de la anulacion de un servicio central
	 * @param response
	 * @return
	 */
	public AnulacionServiciosCentralesRespuestaBean wrappRespuesta(ResponseBansefi response){
		Mapper mapper = dozerBeanMapper;
		return mapper.map(response, AnulacionServiciosCentralesRespuestaBean.class, "anulacionServicioRespuesta");
	}
	
	/**
	 * 
	 * @param diarioBean
	 * @return
	 */
	public Ejecutar wrappEjecutarAnulacion(DiarioElectronicoDetalleRespuestaBean diarioBean){
		Mapper mapper = dozerBeanMapper;
		return mapper.map(diarioBean, Ejecutar.class, "anularServicioCentral");
	}
	
	

}
