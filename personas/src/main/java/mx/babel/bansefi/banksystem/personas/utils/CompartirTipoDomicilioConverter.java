package mx.babel.bansefi.banksystem.personas.utils;

import java.util.ArrayList;
import java.util.List;

import mx.babel.bansefi.banksystem.base.enums.TipoDomicilioEnum;
import mx.babel.bansefi.banksystem.base.utils.TipoDomicilioUtils;
import mx.babel.bansefi.banksystem.personas.webservices.compartirdomicilio.Ejecutar.ITRPECOMPARTIRDOMICTR.TRPECOMPARTIRDOMICEVT.CODPERSRLDIRV;

import org.dozer.ConfigurableCustomConverter;
import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("compartirTipoDomicilioConverter")
public class CompartirTipoDomicilioConverter extends
DozerConverter<List, List> implements MapperAware,
ConfigurableCustomConverter {
	
	private Mapper mapper;
	
	@Autowired
	TipoDomicilioUtils tipoDomicilioUtils;
	
	public CompartirTipoDomicilioConverter(){
		super(List.class, List.class);
	}
	
	@Override
	public List<CODPERSRLDIRV> convertFrom (List origen, List destino){
		destino = new ArrayList<CODPERSRLDIRV>();
		for(Object object: origen){
			CODPERSRLDIRV codigoPersona = new CODPERSRLDIRV();			
			codigoPersona.setCODPERSRLDIR(tipoDomicilioUtils.getClave((TipoDomicilioEnum) object));
//			codigoPersona.setCODPERSRLDIR(((TipoDomicilioEnum) object).getClave());
			destino.add(codigoPersona);
		}
		return destino;
	}

	@Override
	public void setMapper(Mapper mapper) {
		// TODO Auto-generated method stub
		this.mapper = mapper;
	}

	@Override
	public List convertTo(List source, List destination) {
		// TODO Auto-generated method stub
		return null;
	}

}
