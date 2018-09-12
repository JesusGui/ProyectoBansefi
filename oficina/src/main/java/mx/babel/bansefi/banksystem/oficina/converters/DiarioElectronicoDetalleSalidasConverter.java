package mx.babel.bansefi.banksystem.oficina.converters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.babel.bansefi.banksystem.oficina.webservices.consultadetallediarioelectronico.ResponseBansefi.OCONDIA02O.CAMPOSSALIDA;

import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;

/**
 * Convertidor especial para wrappear la respuesta del detalle del diario electronico
 * @author manuel.nieto
 *
 */
public class DiarioElectronicoDetalleSalidasConverter extends DozerConverter<List, Map> implements MapperAware {

	private Mapper mapper;

	public DiarioElectronicoDetalleSalidasConverter() {
		super(List.class, Map.class);
	}

	@Override
	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public Map convertTo(List source, Map destination) {
		List<CAMPOSSALIDA> fuente = source;
		Map<String, String> destino = new HashMap<String, String>();
		
		for(CAMPOSSALIDA campo: fuente){
			if(!StringUtils.isBlank(campo.getCAMPOSALIDA())){
				destino.put(campo.getCAMPOSALIDA(), campo.getVALORSALIDA());
			}			
		}
		
		return destino;
		
	}

	@Override
	public List convertFrom(Map source, List destination) {
		return null;
	}


}
