package mx.babel.bansefi.banksystem.base.wrappers.utils;

import java.util.ArrayList;
import java.util.List;

import mx.babel.bansefi.banksystem.base.beans.busqueda.PersonaGestorBusquedaBean;
import mx.babel.bansefi.banksystem.base.webservices.busquedaidexterna.ResponseBansefi.OTRPECBIDEXTERNACNS.TRPECBIDEXTERNACNSE.TRPECBIDEXTERNACNSE1;

import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;

/**
 * Clase utilería para mapear lista de salida del web service de Búsqueda por Id
 * Externa.
 * 
 * @author alejandro.pineda
 * 
 */
public class WrapperConvertidorListasExternaUtils extends
		DozerConverter<List, List> implements MapperAware {

	private Mapper mapper;

	public WrapperConvertidorListasExternaUtils() {
		super(List.class, List.class);
	}

	@Override
	public List<Object> convertFrom(List origen, List destino) {
		List<Object> resultados = new ArrayList<>();
		for (Object object : origen) {
			TRPECBIDEXTERNACNSE1 objeto = (TRPECBIDEXTERNACNSE1) object;
			if(objeto.getIDINTERNOPE() != 0){
				// Por cada iteración, realiza el mapeo del wrapper
				resultados.add(mapper.map(objeto,
						PersonaGestorBusquedaBean.class,
						"mapeoGestorExterna"));
			}
			
		}
		return resultados;
	}

	@Override
	public List<TRPECBIDEXTERNACNSE1> convertTo(List origen, List destino) {
		List<TRPECBIDEXTERNACNSE1> originalToMapped = new ArrayList<TRPECBIDEXTERNACNSE1>();
		for (Object object : origen) {
			originalToMapped.add(mapper.map((Object) origen,
					TRPECBIDEXTERNACNSE1.class));
		}
		return originalToMapped;
	}

	@Override
	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

}
