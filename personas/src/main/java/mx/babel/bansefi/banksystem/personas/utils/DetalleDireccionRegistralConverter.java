package mx.babel.bansefi.banksystem.personas.utils;

import java.util.ArrayList;

import mx.babel.bansefi.banksystem.personas.webservices.consultadetalledirecregistralpersona.ResponseBansefi.OTRDRRGSTROCNSTRNO.TRDRRGSTROCNSEVTZ.DRCOMPRGSTROV;
import mx.babel.bansefi.banksystem.personas.webservices.mantenimientodireccionesregistrales.Ejecutar.ITRDRREGMNTTRNI.TRDRREGMNTTRNY.DRCOMPRGSTROV2;

import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;

/**
 * Converter para obtener detalle de Dirección Registral.
 * 
 * @author alejandro.pineda
 * 
 */
public class DetalleDireccionRegistralConverter extends
		DozerConverter<String, ArrayList> implements MapperAware {

	private Mapper mapper;

	public DetalleDireccionRegistralConverter() {
		super(String.class, ArrayList.class);
	}

	@Override
	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public ArrayList convertTo(String source, ArrayList destination) {
		String codigo = super.getParameter();
		DRCOMPRGSTROV2 direccion = new DRCOMPRGSTROV2();
		direccion.setCODCOMPRGSTRO(codigo);
		direccion.setVALCOMPRGSTRODRLEN(Integer.valueOf(source.toString()
				.length()));
		direccion.setVALCOMPRGSTRODR(source.toString());

		destination.add(direccion);

		return destination;
	}

	@Override
	public String convertFrom(ArrayList source, String destination) {
		String tipoAtributo = getParameter();
		for (int i = 0; i < source.size(); i++) {
			DRCOMPRGSTROV compdomicv = (DRCOMPRGSTROV) source.get(i);
			if (tipoAtributo.equals(compdomicv.getCODCOMPRGSTRO())) {
				return compdomicv.getVALCOMPRGSTRODR();
			}
		}
		return null;
	}

}
