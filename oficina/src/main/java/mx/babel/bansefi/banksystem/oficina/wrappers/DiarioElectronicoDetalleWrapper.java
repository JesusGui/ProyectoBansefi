package mx.babel.bansefi.banksystem.oficina.wrappers;

import java.io.Serializable;

import mx.babel.bansefi.banksystem.oficina.beans.DiarioElectronicoDetalleRespuestaBean;
import mx.babel.bansefi.banksystem.oficina.beans.DiarioElectronicoResultadoBean;
import mx.babel.bansefi.banksystem.oficina.webservices.consultadetallediarioelectronico.Ejecutar;
import mx.babel.bansefi.banksystem.oficina.webservices.consultadetallediarioelectronico.ResponseBansefi;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DiarioElectronicoDetalleWrapper implements Serializable {

	private static final long serialVersionUID = -2249175751753479494L;

	@Autowired
	DozerBeanMapper dozerBeanMapper;

	/**
	 * Funcion que prepara el objeto para la consulta de detalle de un
	 * movimiento en el diario electronico
	 * 
	 * @param diarioElectronico
	 * @return
	 */
	public Ejecutar wrappConsultaDetalleDiarioElectronico(
			DiarioElectronicoResultadoBean diarioElectronicoBean) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(diarioElectronicoBean, Ejecutar.class,
				"consultaDetalleDiarioElectronico");
	}

	/**
	 * Funcion que convierte la respuesta del detalle de diario electronico
	 * y lo devuelve en un objeto propio del sistema
	 * @param response
	 * @return
	 */
	public DiarioElectronicoDetalleRespuestaBean wrappRespuestaDetalleDiarioElectronico(
			ResponseBansefi response) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(response, DiarioElectronicoDetalleRespuestaBean.class,
				"respuestaDetalleDiarioElectronico");
	}
	
	/**
	 * Funcion que traspasa datos de un bean a otro del mismo tipo
	 * @param entrada
	 * @param salida
	 * @return
	 */
	public void wrappTraspasoDatosDetalleBean(DiarioElectronicoDetalleRespuestaBean entrada, DiarioElectronicoDetalleRespuestaBean salida){
		Mapper mapper = dozerBeanMapper;
		mapper.map(entrada, salida, "traspasoTotalDatosDiarioElectronico");
	}
	
	/**
	 * Funcion que pasa los datos del bean DiarioElectronicoResultadoBean a DiarioElectronicoDettalleRespuestaBean
	 * @param entrada
	 * @return
	 */
	public DiarioElectronicoDetalleRespuestaBean wrappTraspasoDatosBean(DiarioElectronicoResultadoBean entrada){
		Mapper mapper = dozerBeanMapper;
		return mapper.map(entrada, DiarioElectronicoDetalleRespuestaBean.class, "traspasoInicialDatosDiarioElectronico");
	}
}
