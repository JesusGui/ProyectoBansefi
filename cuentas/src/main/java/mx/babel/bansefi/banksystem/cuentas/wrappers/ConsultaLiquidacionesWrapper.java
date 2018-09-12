package mx.babel.bansefi.banksystem.cuentas.wrappers;

import mx.babel.bansefi.banksystem.base.beans.AuditoriaBean;
import mx.babel.bansefi.banksystem.base.beans.LiquidacionBean;
import mx.babel.bansefi.banksystem.base.beans.LiquidacionConceptoBean;
import mx.babel.bansefi.banksystem.base.beans.LiquidacionConceptoDemoraBean;
import mx.babel.bansefi.banksystem.base.beans.LiquidacionNumerosDetalleBean;
import mx.babel.bansefi.banksystem.base.beans.movimientos.MovimientoBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.cargaauditoria.ResponseBansefi.OTRCARGARAUDITTRNO.TRCARGARAUDITEVTZ;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaauditorialiquidacion.ResponseBansefi.OTRTXCONSAUDITTRNO.TRTXCONSAUDITEVTZ.HLAUDITE;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacomisionliquidacion.ResponseBansefi.OTRCONSKTTRNO.TRCONSKTEVTZ.KTDATOSLST.KTDATOSV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalleliquidacion.ResponseBansefi.OTRLIQCONSBASICATRNO.TRLIQCONSBASICAEVTZ;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaliquidaciones.ResponseBansefi.OTRLIQCONSMASIVAYZTR.TRLIQCONSMASIVAYZEVT.HLHCOLIQE;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultamovsliquidacion.ResponseBansefi.OTRCONSHLRLAFTRNO.TRCONSHLRLAFEVTZ.HLHCOLIQRLAFELST;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultanumsliquidacion.ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQNUMEROSLST.LIQNUMEROSV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultatramoliquidacion.ResponseBansefi.OTRCONSHLTRAMOTRNO.TRCONSHLTRAMOEVTZ.HLTRAMOE;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalleliquidacion.ResponseBansefi.OTRLIQCONSBASICATRNO.TRLIQCONSBASICAEVTZ.LIQCONSDEMORAV.HLTRAMOV;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaLiquidacionesWrapper {

	@Autowired
	DozerBeanMapper dozerBeanMapper;

	/**
	 * Método encargado de mapear los atributos de la respuesta en un
	 * LiquidacionBean.
	 * 
	 * @param resultado
	 * @return LiquidacionBean
	 */
	public LiquidacionBean wrappConsultaLiquidacion(HLHCOLIQE resultado) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(resultado, LiquidacionBean.class,
				"resultadoConsultaLiquidaciones");
	}

	/**
	 * Método encargado de mapear los atributos de la respuesta en un
	 * LiquidacionBean de entrada.
	 * 
	 * @param detalleLiquidacion
	 *            resultado de la consulta
	 * @param liquidacion
	 *            bean al cual mappear el resultado
	 * @return LiquidacionBean
	 */
	public void wrappDetalleLiquidacion(TRLIQCONSBASICAEVTZ detalleLiquidacion,
			LiquidacionBean liquidacion) {
		Mapper mapper = dozerBeanMapper;
		mapper.map(detalleLiquidacion, liquidacion,
				"resultadoDetalleLiquidacion");
	}

	/**
	 * Método encargado de mapear los atributos de la respuesta en un
	 * LiquidacionBean de entrada.
	 * 
	 * @param AFAUDITE
	 *            resultado de la consulta
	 * @return HistoricoEstadoBean
	 */
	public AuditoriaBean wrappDetalleAuditoriaLiq(HLAUDITE resultado) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(resultado, AuditoriaBean.class,
				"resultadoConsultaAuditoriaLiq");
	}

	/**
	 * Método encargado de mapear los atributos de la respuesta en un
	 * LiquidacionBean de entrada.
	 * 
	 * @param detalleLiquidacion
	 *            resultado de la consulta
	 */
	public void wrappDetalleInfoAuditoriaLiq(TRCARGARAUDITEVTZ respuesta,
			AuditoriaBean auditoriaBean) {
		Mapper mapper = dozerBeanMapper;
		mapper.map(respuesta, auditoriaBean,
				"resultadoConsultaAuditoriaInfoLiq");
	}

	/**
	 * Método encargado de mapear los atributos de la respuesta en un
	 * LiquidacionBean.
	 * 
	 * @param resultado
	 * @return LiquidacionBean
	 */
	public void wrappSimulacionLiquidacion(
			mx.babel.bansefi.banksystem.cuentas.webservices.simularliquidaciones.ResponseBansefi.OTRLIQSIMULARLIQTRNO.TRLIQSIMULARLIQEVTZ.HLHCOLIQE resultado,
			LiquidacionBean liquidacion) {
		Mapper mapper = dozerBeanMapper;
		mapper.map(resultado, liquidacion, "resultadoSimularLiquidacion");
	}

	/**
	 * Método encargado de mapear los atributos de la respuesta en un
	 * MovimientoBean de entrada.
	 * 
	 * @param AFAUDITE
	 *            resultado de la consulta
	 * @return HistoricoEstadoBean
	 */
	public MovimientoBean wrappMovimientoLiq(HLHCOLIQRLAFELST resultado) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(resultado, MovimientoBean.class,
				"resultadoMovimientoLiq");
	}

	/**
	 * Método encargado de mapear los atributos de la respuesta en un
	 * MovimientoBean de entrada.
	 * 
	 * @param AFAUDITE
	 *            resultado de la consulta
	 * @return HistoricoEstadoBean
	 */
	public LiquidacionNumerosDetalleBean wrappNumsLiq(LIQNUMEROSV resultado) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(resultado, LiquidacionNumerosDetalleBean.class,
				"resultadoNumerosLiq");
	}

	/**
	 * Método encargado de mapear los atributos de la respuesta en un
	 * MovimientoBean de entrada.
	 * 
	 * @param AFAUDITE
	 *            resultado de la consulta
	 * @return HistoricoEstadoBean
	 */
	public LiquidacionConceptoBean wrappTramoLiq(HLTRAMOE resultado) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(resultado, LiquidacionConceptoBean.class,
				"resultadoTramoLiq");
	}

	/**
	 * Método encargado de mapear los atributos de la respuesta en un
	 * LiquidacionConceptoBean.
	 * 
	 * @author omar.marquez
	 * @param KTDATOSV
	 *            resultado de la consulta
	 * @return LiquidacionConceptoBean
	 */
	public LiquidacionConceptoBean wrappComisionLiq(KTDATOSV resultado) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(resultado, LiquidacionConceptoBean.class,
				"resultadoComisionLiq");
	}

	/**
	 * Método encargado de mapear los atributos de la respuesta en un
	 * LiquidacionConceptoDemoraBean de entrada.
	 * 
	 * @author omar.marquez
	 * @param resultado
	 * @param liquidacionDemoraBean
	 */
	public void wrappLiquidacionDemora(HLTRAMOV resultado,
			LiquidacionConceptoDemoraBean liquidacionDemoraBean) {
		Mapper mapper = dozerBeanMapper;
		mapper.map(resultado, liquidacionDemoraBean,
				"resultadoLiquidacionDemora");
	}

	/**
	 * Método encargado de mapear los atributos de un
	 * LiquidacionConceptoDemoraBean a un LiquidacionConceptoBean.
	 * 
	 * @author omar.marquez
	 * @param liquidacionDemoraBean
	 * @param liquidacionConceptoBean
	 */
	public void wrappConceptosLiquidacion(
			LiquidacionConceptoDemoraBean liquidacionDemoraBean,
			LiquidacionConceptoBean liquidacionConceptoBean) {
		Mapper mapper = dozerBeanMapper;
		mapper.map(liquidacionDemoraBean, liquidacionConceptoBean,
				"mapeoConceptosLiquidacion");
	}

	/**
	 * Método sobrecargado que permite mapear los atributos de un
	 * LiquidacionConceptoDemoraBean a un LiquidacionConceptoBean.
	 * 
	 * @param liquidacionDemoraBean
	 * @return liquidacionConceptoBean
	 */
	public LiquidacionConceptoBean wrappConceptosLiquidacion(
			LiquidacionConceptoDemoraBean liquidacionDemoraBean) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(liquidacionDemoraBean, LiquidacionConceptoBean.class,
				"mapeoConceptosLiquidacion");
	}

}