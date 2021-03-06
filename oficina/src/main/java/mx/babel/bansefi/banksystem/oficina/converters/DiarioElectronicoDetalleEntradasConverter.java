package mx.babel.bansefi.banksystem.oficina.converters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.babel.bansefi.banksystem.oficina.webservices.consultadetallediarioelectronico.ResponseBansefi.OCONDIA02O.CAMPOENTRADA;

import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;

/**
 * Convertidor especial para wrappear la respuseta del detalle del diario electronico
 * @author manuel.nieto
 *
 */
public class DiarioElectronicoDetalleEntradasConverter extends
		DozerConverter<List, Map> implements MapperAware {

	private Mapper mapper;

	public DiarioElectronicoDetalleEntradasConverter() {
		super(List.class, Map.class);
	}

	@Override
	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public Map convertTo(List source, Map destination) {
		
		List<CAMPOENTRADA> fuente = source;
		Map<String, String> destino = new HashMap<String, String>();
		
		for(CAMPOENTRADA campo: fuente){
			if(!StringUtils.isBlank(campo.getCAMPOENTRADA())){
				destino.put(campo.getCAMPOENTRADA().trim(), campo.getVALORENTRADA().trim());
			}			
		}
		
		return destino;
		
	}

	@Override
	public List convertFrom(Map source, List destination) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
