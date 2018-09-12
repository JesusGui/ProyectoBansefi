package mx.babel.bansefi.banksystem.personas.utils;

import java.util.ArrayList;
import java.util.List;

import mx.babel.bansefi.banksystem.base.enums.TipoDomicilioEnum;
import mx.babel.bansefi.banksystem.base.utils.TipoDomicilioUtils;
import mx.babel.bansefi.banksystem.base.webservices.altadomicilio.Ejecutar.ITRPEALTADOMICTRNI.TRPEALTADOMICEVTY.CODPERSRLDIRV;

import org.dozer.ConfigurableCustomConverter;
import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("altaTipoDomicilioConverter")
public class AltaTipoDomicilioConverter extends DozerConverter<List, List>
		implements MapperAware,	ConfigurableCustomConverter  {

	private Mapper mapper;

	@Autowired
	TipoDomicilioUtils tipoDomicilioUtils;

	public AltaTipoDomicilioConverter() {
		super(List.class, List.class);
	}

	@Override
	public List<CODPERSRLDIRV> convertTo(List origen, List destino) {
//		public String convertTo(String origen, String destino) {
		return null;
	}

	@Override
	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List convertFrom(List source, List destination) {
		destination = new ArrayList<CODPERSRLDIRV>();
		for (Object object : source) {
			CODPERSRLDIRV codigoPersona = new CODPERSRLDIRV();
			TipoDomicilioEnum tipo = (TipoDomicilioEnum) object;
			String tipoStr = tipoDomicilioUtils.getClave(tipo);
			codigoPersona.setCODPERSRLDIR(tipoStr);
			destination.add(codigoPersona);
		}
		return destination;
//		return null;
	}

}
