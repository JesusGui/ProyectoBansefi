package mx.babel.bansefi.banksystem.base.utils;

import java.util.ArrayList;
import java.util.List;

import mx.babel.bansefi.banksystem.base.webservices.validaidinterna.ResponseBansefi.OTRCONSMINIMAPERSONAT.TRCONSMINIMAPERSONAEV.STDTLFNOSCONSMINIMAV;

import org.dozer.ConfigurableCustomConverter;
import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;

/**
 * Convertidor de listas de wrapper para repuesta de Webservice de Consulta
 * Minima de Persona
 * 
 * @author manuel.nieto
 * 
 */
public class ConsultaMinimaPersonaConverter extends
DozerConverter<List, List> implements MapperAware,
ConfigurableCustomConverter {

	private Mapper mapper;

	public ConsultaMinimaPersonaConverter() {
		super(List.class, List.class);
	}

	/**
	 * Funcion que convierte la lista de entrada en una lista predefinida en el
	 * aplicativo
	 */
	@Override
	public List<String> convertFrom(List origen, List destino) {
		destino = new ArrayList<String>();
		for (Object object : origen) {
			destino.add(((STDTLFNOSCONSMINIMAV) object).getVALORELCTRDR());
		}
		return destino;
	}

	/**
	 * Funcion que toma una lista predefinida en la aplicacion y la convierte a
	 * una necesaria para el webservice.
	 */
	@Override
	public List<STDTLFNOSCONSMINIMAV> convertTo(List origen, List destino) {
		destino = new ArrayList<STDTLFNOSCONSMINIMAV>();
		for (Object object : origen) {
			STDTLFNOSCONSMINIMAV trnTel = new STDTLFNOSCONSMINIMAV();
			trnTel.setVALORELCTRDRLEN(((String) object).length());
			trnTel.setVALORELCTRDR((String) object);
			destino.add(trnTel);
		}
		return destino;
	}

	@Override
	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}
}
