package mx.babel.bansefi.banksystem.cuentas.wrappers;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import mx.babel.bansefi.banksystem.base.beans.AuditoriaBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.base.utils.IntegerToStringHourConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultahistoricocuenta.ResponseBansefi;

@Component
public class ConsultaHistoricoCuentaWrapper implements Serializable {

	private static final long serialVersionUID = -8551682880439393453L;

	private DateFormat df = new SimpleDateFormat(ConstantesFuncionales.GENERAL_DATE_FORMATTER);

	public AuditoriaBean wrappConsultaAuditoriaHistorico(ResponseBansefi.OTRCONSUESTADOSACTRN.TRCONSUESTADOSACEVTZ.ACECVACE itemFromTRN){
		AuditoriaBean bean = null;
		IntegerToDateConverter itdc = null;
		IntegerToStringHourConverter itshc = null;
		
		Date fechaOpDate = null;
		Date fechaIniEcvDate = null;
		Date fechaFinEcvDate = null;
		Integer fechaInvalida = 99991231;
		
		bean = new AuditoriaBean();
		itdc = new IntegerToDateConverter();
		itshc = new IntegerToStringHourConverter();
		
		bean.setEntidad(itemFromTRN.getCODNRBEEN());
		bean.setCentroOperativo(itemFromTRN.getCODCENTUO());
		bean.setCuenta( String.valueOf(itemFromTRN.getNUMSECAC()) );
		
		fechaOpDate = itdc.convertTo( itemFromTRN.getFECHAOPRCN(), null );
		if(fechaOpDate!=null){
			bean.setFechaOperacion( fechaOpDate );
			bean.setFechaOperacionStr( df.format(fechaOpDate) );
		}
			
		bean.setHoraOperacion(itshc.convertTo(itemFromTRN.getHORAOPRCN(), null));
		if(itemFromTRN.getFECHAINICECV() != fechaInvalida){
			fechaIniEcvDate = itdc.convertTo(itemFromTRN.getFECHAINICECV(), null);
			if( fechaIniEcvDate!=null ){
				bean.setFechaInicioEstadoCuenta(fechaIniEcvDate);
				bean.setFechaInicioEstadoCuentaStr(df.format(fechaIniEcvDate));
			}
		}else{
			bean.setFechaInicioEstadoCuentaStr("");
		}
		
		if(itemFromTRN.getFECHAFINECV()!=fechaInvalida){
			fechaFinEcvDate = itdc.convertTo(itemFromTRN.getFECHAFINECV(), null);
			if( fechaFinEcvDate!=null ){
				bean.setFechaFinEstadoCuenta(fechaFinEcvDate);
				bean.setFechaFinEstadoCuentaStr(df.format(fechaFinEcvDate));
			}
		}else{
			bean.setFechaFinEstadoCuentaStr("");
		}
		
		bean.setFechaContable(bean.getFechaInicioEstadoCuenta());
		bean.setFechaContableStr(bean.getFechaInicioEstadoCuentaStr());
		
		bean.setCodigoEstadoAcuerdo(itemFromTRN.getCODECVAC());
		bean.setCodigoRazonEstadoAcuerdo(itemFromTRN.getCODRZNECVAC());
		
		return bean;
	}

	public void cargaDatosEmpleadoPorEstado(AuditoriaBean bean, ResponseBansefi.OTRCONSUESTADOSACTRN.TRCONSUESTADOSACEVTZ.ACECVACV itemFromTRN){
		bean.setCentroOperativo(itemFromTRN.getCODINTERNOUO());
		bean.setTransaccion(itemFromTRN.getCODTX());
		bean.setEmpleadoAutorizado(itemFromTRN.getIDEMPLAUT());
		bean.setEmpleadoOrigen(itemFromTRN.getIDINTERNOEMPLEP());
		bean.setTerminal(itemFromTRN.getIDINTERNOTERMTN());
	}

}
