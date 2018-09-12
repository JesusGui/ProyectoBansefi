package mx.babel.bansefi.banksystem.cuentas.converters;

import java.util.ArrayList;
import java.util.List;

import mx.babel.bansefi.banksystem.base.beans.ApuntesBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.anularconstitucionacuerdo.ResponseBansefi.OTRCONSUANULARACTRNO.TRCONSUANULARACEVTZ.ACVAFAPUNTESV;

import org.apache.commons.lang3.StringUtils;
import org.dozer.ConfigurableCustomConverter;
import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;
import org.springframework.stereotype.Component;

@Component("consultaAnulacionCuentaConverter")
public class ConsultaAnulacionCuentaConverter extends
		DozerConverter<List, List> implements MapperAware,
		ConfigurableCustomConverter {

	private Mapper mapper;

	public ConsultaAnulacionCuentaConverter() {
		super(List.class, List.class);
	}

	@Override
	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List convertTo(List source, List destination) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List convertFrom(List source, List destination) {
		destination = new ArrayList<ApuntesBean>();
		if (source != null && !source.isEmpty()) {
			for (Object object : source) {
				ApuntesBean apunte = new ApuntesBean();
				ACVAFAPUNTESV apunteTRN = (ACVAFAPUNTESV) object;
				if (!StringUtils.isBlank(apunteTRN.getCONCPTAPNTE())
						|| !StringUtils.isBlank(apunteTRN.getSGN())
						|| apunteTRN.getIMPAPNTE() != null) {
					apunte.setConcepto(apunteTRN.getCONCPTAPNTE());
					apunte.setTipoOperacion(apunteTRN.getSGN());
					apunte.setImporte(apunteTRN.getIMPAPNTE());
					destination.add(apunte);
				}
			}
		}
		return destination;
	}
}
