package mx.babel.bansefi.banksystem.administracion.utils;

import java.util.ArrayList;

import mx.babel.bansefi.banksystem.administracion.webservices.altacentro.Ejecutar.ITRALTACENTROTRNI.TRALTACENTROEVTY.DRDMDPOBJTRDV.COMPDOMICV;

import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;

/**
 * Clase para alta del domicilio de un Centro.
 * 
 * @author alejandro.pineda
 * 
 */
public class MantenimientoDomicilioCentroConverterUtils extends
		DozerConverter<String, ArrayList> implements MapperAware {

	private Mapper mapper;

	public MantenimientoDomicilioCentroConverterUtils() {
		super(String.class, ArrayList.class);
	}

	@Override
	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public ArrayList convertTo(String source, ArrayList destination) {
		String[] arrParam = getParameter().split(",");
		String longitud = arrParam[1];
		String codigo = arrParam[0];
		if (("alta").equals(arrParam[2])) {
			COMPDOMICV domicilio = new COMPDOMICV();
			if(!("").equals(source) && source != null){
				domicilio.setCODCOMPDOMIC(codigo);
				domicilio.setVALCOMPDOMICDRLEN(Integer.valueOf(longitud));
				domicilio.setVALCOMPDOMICDR(source);
				destination.add(domicilio);
			}
		} else {
			mx.babel.bansefi.banksystem.administracion.webservices.modificacioncentro.Ejecutar.ITRMODICENTROTRNI.TRMODICENTROEVTY.DRDMDPOBJTRDV.COMPDOMICV domicilio = new mx.babel.bansefi.banksystem.administracion.webservices.modificacioncentro.Ejecutar.ITRMODICENTROTRNI.TRMODICENTROEVTY.DRDMDPOBJTRDV.COMPDOMICV();
			if(!("").equals(source) && source != null){
				domicilio.setCODCOMPDOMIC(codigo);
				domicilio.setVALCOMPDOMICDRLEN(Integer.valueOf(longitud));
				domicilio.setVALCOMPDOMICDR(source);
				destination.add(domicilio);
			}
		}
		return destination;
	}

	@Override
	public String convertFrom(ArrayList source, String destination) {
		return null;
	}
	
}
