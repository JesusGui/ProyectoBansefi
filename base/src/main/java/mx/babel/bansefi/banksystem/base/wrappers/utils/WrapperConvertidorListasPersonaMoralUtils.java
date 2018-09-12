package mx.babel.bansefi.banksystem.base.wrappers.utils;

import java.util.ArrayList;
import java.util.List;

import mx.babel.bansefi.banksystem.base.beans.busqueda.PersonaMoralBusquedaBean;
import mx.babel.bansefi.banksystem.base.webservices.busquedanombre.ResponseBansefi.OTRPECBNOMBRECNSTRN.TRPECBNOMBRECNSEVTZ.TRPECBNOMBRECNSEVTV;

import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;

/**
 * Convertidor de Listas de Wrapper para Respuesta de WebService
 * de búsqueda por Nombre para Personas Morales.
 * @author alejandro.pineda
 *
 */
public class WrapperConvertidorListasPersonaMoralUtils extends
		DozerConverter<List, List> implements MapperAware {

	private Mapper mapper;

	public WrapperConvertidorListasPersonaMoralUtils() {
		super(List.class, List.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Object> convertFrom(List origen, List destino) {
		List<Object> resultados = new ArrayList<>();
		for (Object object : origen) {
			//Por cada iteración, realiza el mapeo del wrapper
			resultados.add(mapper.map((TRPECBNOMBRECNSEVTV) object,
					PersonaMoralBusquedaBean.class,
					"mapeoPersonaMoral"));
		}
		return resultados;
	}

	@Override
	public List<TRPECBNOMBRECNSEVTV> convertTo(List origen, List destino) {
		List<TRPECBNOMBRECNSEVTV> originalToMapped = new ArrayList<TRPECBNOMBRECNSEVTV>();
		for (Object object : origen) {
			originalToMapped.add(mapper.map((Object) origen,
					TRPECBNOMBRECNSEVTV.class));
		}
		return originalToMapped;
	}

	@Override
	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}
}
