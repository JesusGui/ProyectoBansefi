package mx.babel.bansefi.banksystem.oficina.wrappers;

import java.io.Serializable;
import java.util.List;

import mx.babel.bansefi.banksystem.oficina.beans.ContadoresCentroBean;
import mx.babel.bansefi.banksystem.oficina.webservices.contadorespuestohost.Ejecutar.ITRTNCONTHOSTCNSTRN.TRCONSCONTHOSTEVTY;
import mx.babel.bansefi.banksystem.oficina.webservices.contadorespuestohost.ResponseBansefi.OTRTNCONTHOSTCNSTRN.TRCONSCONTHOSTEVTZ.TNCONTHOSTE;

import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase encargada de realizar los mapeos entre la clase
 * ContadoresPuestoServicio y ContadoresCentroBean
 * 
 * @author manuel.nieto
 * 
 */
@Component
public class ContadoresPuestoWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4817725458917085146L;
	@Autowired
	DozerBeanMapper dozerBeanMapper;

	/**
	 * Función Wrapper encargada de convertir la clase ContadoresCentroBean
	 * ejecutarTRN
	 * 
	 * @param ejecutarTRN
	 * @return
	 */
	public TRCONSCONTHOSTEVTY.TNCONTHOSTP wrappConsultaContadoresPuestoHost(
			ContadoresCentroBean ejecutarTRN) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(ejecutarTRN, TRCONSCONTHOSTEVTY.TNCONTHOSTP.class,
				"consultaContadoresPuesto");
	}

	/**
	 * Funcion que convierte la respuesta de la TRN de tipo una lista TNCONTHOSTE en un objeto ContadoresCentroBean,
	 * Para este caso no se hizo un wrappeo xml como tal, ya que cada item de la lista
	 * en realidad traia un parámetro diferente que podia ser CD, CH, ID, o IH. 
	 * Esta lista siempre traerá 4 registros
	 * @param respuestaTRN, Lista de tipo TNCONTHOSTE
	 * @return ContadoresCentroBean, objeto con valores generico de contadores Centro/Puesto
	 */
	public ContadoresCentroBean wrappRespuestaContadoresPuestoHost(
			List<TNCONTHOSTE> respuestaTRN) {
		ContadoresCentroBean contadores = new ContadoresCentroBean();

		//TODO Sacar estos literales a una clase de constantes
		for (TNCONTHOSTE data : respuestaTRN) {
			if (data != null
					&& (!StringUtils.isBlank(data.getCODCONTTERM()) && data
							.getIMPNOMINAL() != null)) {
				switch (data.getCODCONTTERM()) {
				case "CD":
					contadores.setCobrosOnCaja(data.getIMPNOMINAL());
					break;
				case "CH":
					contadores.setPagosOnCaja(data.getIMPNOMINAL());
					break;
				case "ID":
					contadores.setDeberOnIntervencion(data.getIMPNOMINAL());
					break;
				case "IH":
					contadores.setHaberOnIntervencion(data.getIMPNOMINAL());
					break;
				}
			}
		}

		return contadores;
	}
}
