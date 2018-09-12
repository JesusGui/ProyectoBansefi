package mx.babel.bansefi.banksystem.oficina.wrappers;

import java.io.Serializable;

import mx.babel.bansefi.banksystem.oficina.beans.DiarioElectronicoBusquedaBean;
import mx.babel.bansefi.banksystem.oficina.beans.DiarioElectronicoResultadoBean;
import mx.babel.bansefi.banksystem.oficina.webservices.consultadiarioelectronico.Ejecutar;
import mx.babel.bansefi.banksystem.oficina.webservices.consultadiarioelectronico.ResponseBansefi;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DiarioElectronicoWrapper implements Serializable {

	private static final long serialVersionUID = -2249175751753479494L;

	@Autowired
	DozerBeanMapper dozerBeanMapper;

	/**
	 * Funcion que prepara el objeto para la consulta de 
	 * movimientos en el diario electronico
	 * 
	 * @param diarioElectronico
	 * @return
	 */
	public Ejecutar wrappConsultaDiarioElectronico(
			DiarioElectronicoBusquedaBean diarioElectronicoBusquedaBean) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(diarioElectronicoBusquedaBean, Ejecutar.class,
				"consultaDiarioElectronico");
	}

	/**
	 * Funcion que convierte la respuesta del diario electronico
	 * y lo devuelve en un objeto propio del sistema
	 * @param response
	 * @return
	 */
	public DiarioElectronicoResultadoBean wrappRespuestaDiarioElectronico(
			ResponseBansefi response) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(response, DiarioElectronicoResultadoBean.class,
				"respuestaDiarioElectronico");
	}
}
