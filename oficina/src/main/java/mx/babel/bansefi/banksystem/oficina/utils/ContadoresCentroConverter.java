package mx.babel.bansefi.banksystem.oficina.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import mx.babel.bansefi.banksystem.oficina.beans.ContadoresCentroRegistrosDetalleBean;
import mx.babel.bansefi.banksystem.oficina.webservices.contadorescentro.ResponseBansefi.OTROBTECONTTRNO.TRCONSUOTNCONTEVTZ.UOCONTTERMINALESV.TNCONTHOSTV;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dozer.ConfigurableCustomConverter;
import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;

public class ContadoresCentroConverter extends DozerConverter<List, List>
		implements MapperAware, ConfigurableCustomConverter {

	private Mapper mapper;

	private static final Logger LOGGER = LogManager
			.getLogger(ContadoresCentroConverter.class);

	public ContadoresCentroConverter() {
		super(List.class, List.class);
	}

	@Override
	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List convertTo(List source, List destination) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List convertFrom(List source, List destination) {
		List<ContadoresCentroRegistrosDetalleBean> destino = new ArrayList<ContadoresCentroRegistrosDetalleBean>();
		for (Object object : source) {
			TNCONTHOSTV registro = (TNCONTHOSTV) object;

			if (!StringUtils.isBlank(registro.getCODCONTTERM().trim())
					&& !StringUtils.isBlank(registro.getIDINTERNOTERMTN()
							.trim())
					&& !registro.getIMPNOMINAL().equals(BigDecimal.ZERO)) {
				int posicion = buscaTerminal(destino,
						registro.getIDINTERNOTERMTN());

				/**
				 * La terminal no se encuentra en la lista, agregarla
				 */
				if (posicion == -1) {
					ContadoresCentroRegistrosDetalleBean contadores = new ContadoresCentroRegistrosDetalleBean();
					switch (registro.getCODCONTTERM()) {
					case "CH":
						contadores.setPagos(registro.getIMPNOMINAL());
						break;
					case "CD":
						contadores.setCobros(registro.getIMPNOMINAL());
						break;
					case "IH":
						contadores.setHaber(registro.getIMPNOMINAL());
						break;
					case "ID":
						contadores.setDebe(registro.getIMPNOMINAL());
						break;
					}
					// Set terminal y puesto
					contadores.setTerminal(registro.getIDINTERNOTERMTN());
					contadores.setPuesto(registro.getIDINTERNOTERMTN()
							.substring(6));
					destino.add(contadores);
				} else {// La terminal ya se encuentra en la lista, reutilizarla
					switch (registro.getCODCONTTERM()) {
					case "CH":
						destino.get(posicion)
								.setPagos(registro.getIMPNOMINAL());
						break;
					case "CD":
						destino.get(posicion).setCobros(
								registro.getIMPNOMINAL());
						break;
					case "IH":
						destino.get(posicion)
								.setHaber(registro.getIMPNOMINAL());
						break;
					case "ID":
						destino.get(posicion).setDebe(registro.getIMPNOMINAL());
						break;
					}
					// Set terminal y puesto
					destino.get(posicion).setTerminal(
							registro.getIDINTERNOTERMTN());
					destino.get(posicion).setPuesto(
							registro.getIDINTERNOTERMTN().substring(6));
				}

			}
		}

		return destino;
	}

	/**
	 * Funcion que busca si la terminal ya existe en algun otro registro de la
	 * lista y retorna el indice donde se encuentra, de los contrario retorna un
	 * -1
	 * 
	 * @param lista
	 * @param terminal
	 * @return
	 */
	public int buscaTerminal(List<ContadoresCentroRegistrosDetalleBean> lista,
			String terminal) {
		LOGGER.debug("--buscarTerminal, terminal:" + terminal);
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i) != null && lista.get(i).getTerminal() != null
					&& terminal != null)
				if (lista.get(i).getTerminal().equals(terminal)) {
					return i;
				}
		}
		return -1;
	}

}
