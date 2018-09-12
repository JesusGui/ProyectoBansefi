package mx.babel.bansefi.banksystem.cuentas.wrappers;

import mx.babel.bansefi.banksystem.base.beans.ApunteChequeBean;
import mx.babel.bansefi.banksystem.base.beans.AuditoriaBean;
import mx.babel.bansefi.banksystem.base.beans.ChequeBancarioBean;
import mx.babel.bansefi.banksystem.base.beans.LiquidacionBean;
import mx.babel.bansefi.banksystem.base.beans.movimientos.MovimientoBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaapunteporcheque.ResponseBansefi.OTRCPCONSULTADETALLET.TRCPCONSULTADETALLEEV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaapuntepornaturaleza.ResponseBansefi.OTRCONSCTAFTRNO.TRCONSCTAFEVTZ.AFAPUNTESDV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaauditorialiquidacion.ResponseBansefi.OTRTXCONSAUDITTRNO.TRTXCONSAUDITEVTZ.AFAUDITE;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultageneralapunte.ResponseBansefi.OTRCONSGENAPNTETRNO.TRCONSGENAPNTEEVTZ.AFAPNTEE;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalleapunte.ResponseBansefi.OTRAFCONSAPUNTETRNO.TRAFCONSAPUNTEEVTZ;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalleliquidacion.ResponseBansefi.OTRLIQCONSBASICATRNO.TRLIQCONSBASICAEVTZ.HLHCOLIQE;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultachequebancario.ResponseBansefi.OTRCONSULTACHEQUE2TRN.TRCONSULTACHEQUE2EVT;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author omar.marquez
 * 
 */
@Component
public class ConsultaApunteWrapper {

	@Autowired
	DozerBeanMapper dozerBeanMapper;

	public void consultaGeneralApunteWrapper(final AFAPNTEE afapntee,
			MovimientoBean movimientoBean) {
		final Mapper mapper = dozerBeanMapper;
		mapper.map(afapntee, movimientoBean, "resultadoConsultaGeneralApuntes");
	}

	public void consultaApuntePorNaturalezaWrapper(
			final AFAPUNTESDV afapuntesdv, MovimientoBean movimientoBean) {
		final Mapper mapper = dozerBeanMapper;
		mapper.map(afapuntesdv, movimientoBean,
				"resultadoConsultaApuntePorNaturaleza");
	}

	public void consultaDetalleApunte(
			final TRAFCONSAPUNTEEVTZ trafconsapunteevtz,
			MovimientoBean movimientoBean) {
		final Mapper mapper = dozerBeanMapper;
		mapper.map(trafconsapunteevtz, movimientoBean, "resultadoDetalleApunte");
	}

	public AuditoriaBean consultaAuditoriaApunteWrapper(final AFAUDITE afaudite) {
		final Mapper mapper = dozerBeanMapper;
		return mapper.map(afaudite, AuditoriaBean.class,
				"resultadoConsultaAuditoriaApunte");
	}

	public LiquidacionBean consultaOrigenApunteWrapper(final HLHCOLIQE hlhcoliqe) {
		final Mapper mapper = dozerBeanMapper;
		return mapper.map(hlhcoliqe, LiquidacionBean.class,
				"resultadoConsultaOrigenApunte");
	}

	public ChequeBancarioBean consultaOrigenChequeBancarioWrapper(
			final TRCONSULTACHEQUE2EVT trconsultacheque2evt) {
		final Mapper mapper = dozerBeanMapper;
		return mapper.map(trconsultacheque2evt, ChequeBancarioBean.class,
				"resultadoConsultaOrigenChequeBancario");
	}
	
	public ApunteChequeBean consultaOrigenChequeWrapper(
			final TRCPCONSULTADETALLEEV trconsultacheque2evt) {
		final Mapper mapper = dozerBeanMapper;
		return mapper.map(trconsultacheque2evt, ApunteChequeBean.class,
				"consultaApuntePorCheque");
	}

}