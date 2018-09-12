package mx.babel.bansefi.banksystem.base.wrappers.utils;

import java.util.ArrayList;
import java.util.List;

import mx.babel.bansefi.banksystem.base.beans.busqueda.PersonaGestorBusquedaBean;
import mx.babel.bansefi.banksystem.base.webservices.busquedanombre.ResponseBansefi.OTRPECBNOMBRECNSTRN.TRPECBNOMBRECNSEVTZ.TRPECBNOMBRECNSEVTV;

import org.dozer.ConfigurableCustomConverter;
import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;

/**
 * Convertidor de Listas de Wrapper para Respuesta de WebServices de busqueda
 * por Nombre para Clientes y Gestor.
 * 
 * @author alejandro.pineda
 * 
 */
public class WrapperConvertidorListasClienteGestorUtils extends
		DozerConverter<List, List> implements MapperAware,
		ConfigurableCustomConverter {

	private Mapper mapper;

	public WrapperConvertidorListasClienteGestorUtils() {
		super(List.class, List.class);
	}

	@Override
	public List<Object> convertFrom(List origen, List destino) {
		List<Object> resultados = new ArrayList<>();
		for (Object object : origen) {
			TRPECBNOMBRECNSEVTV resultado = (TRPECBNOMBRECNSEVTV) object;
			if(resultado.getIDINTERNOPE() != 0 && !("").equals(resultado.getCODIDEXTPERS().trim())){
				//Por cada iteración, realiza el mapeo del wrapper
				resultados.add(mapper.map((TRPECBNOMBRECNSEVTV) object,
						PersonaGestorBusquedaBean.class,
						"mapeoGestor"));
			}
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
