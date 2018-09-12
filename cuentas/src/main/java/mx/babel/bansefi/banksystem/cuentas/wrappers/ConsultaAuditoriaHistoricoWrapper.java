package mx.babel.bansefi.banksystem.cuentas.wrappers;

import java.io.Serializable;

import mx.babel.bansefi.banksystem.base.beans.AuditoriaBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaauditoriahistorico.Ejecutar.ITRCONSUAUDITECVACTR.TRCONSUAUDITECVACEVT;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaauditoriahistorico.ResponseBansefi;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaAuditoriaHistoricoWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4693012522563203054L;
	
	@Autowired
	DozerBeanMapper dozerBeanMapper;
	
	public AuditoriaBean wrappConsultaAuditoriaHistorico(TRCONSUAUDITECVACEVT respuestaTRN){
		return dozerBeanMapper.map(respuestaTRN, AuditoriaBean.class, "consultaAuditoriaHistoricoCuentas");
	}


	public AuditoriaBean wrappConsultaAuditoriaHistorico(ResponseBansefi responseBansefi){
		return dozerBeanMapper.map(responseBansefi.getOTRCONSUAUDITECVACTR().getTRCONSUAUDITECVACEVT(), 
				AuditoriaBean.class, "respuestaConsultaAuditoriaHistoricoCuentas");
	}
}
