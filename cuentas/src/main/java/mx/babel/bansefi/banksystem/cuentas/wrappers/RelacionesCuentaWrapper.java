package mx.babel.bansefi.banksystem.cuentas.wrappers;

import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.CuentaRelacionadaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.RelacionadoBean;
import mx.babel.bansefi.banksystem.cuentas.beans.HistoricoRelacionadoBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.altarelacioncuentacuenta.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.altarelacioncuentacuenta.Ejecutar.ITRALTARXPANTTRNI.TRALTARXPANTEVTY;
import mx.babel.bansefi.banksystem.cuentas.webservices.altarelacionpersonacuenta.Ejecutar.ITRALTARPPANTTRNI;
import mx.babel.bansefi.banksystem.cuentas.webservices.bajarelacionpersonacuenta.Ejecutar.ITRBAJARPPANTTRNI;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacuentasrelacionables.ResponseBansefi.OTRCONSUSELECTIV2ACTR.TRCONSUSELECTIV2ACEVT;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultarelacionescuentacuentas.ResponseBansefi.OTRCONSULTARXPANTTRN.TRCONSULTARXPANTEVTZ.TRCONSULTARXAC1V.RXACRLACV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultarelacionescuentacuentas.ResponseBansefi.OTRCONSULTARXPANTTRN.TRCONSULTARXPANTEVTZ.TRCONSULTARXAC1V.RXACRLACE;
import mx.babel.bansefi.banksystem.cuentas.webservices.historicorelacioncuentacuenta.ResponseBansefi.OTRRXCONSMASIVAECVTR.TRRXCONSMASIVAECVEVT;
import mx.babel.bansefi.banksystem.cuentas.webservices.historicorelacionpersonacuenta.ResponseBansefi.OTRCONSULTAECVRPTRNO.TRCONSULTAECVRPEVTZ;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificarelacioncuentacuenta.Ejecutar.ITRMODRLRXPANTTRNI.TRMODRLRXPANTEVTY;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificarelacioncuentapersona.Ejecutar.ITRMODRLRPPANTTRNI;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RelacionesCuentaWrapper {

	@Autowired
	DozerBeanMapper dozerBeanMapper;
	
	/**
	 * Wrapper para alta de relaciones persona cuenta.
	 * @param cuentaBean Bean cuenta a la que se relacionara la persona
	 * @param relacionadoBean Bean persona a ser relacionada en la cuenta
	 * @return Objeto de requisición a servicio web de alta de relación persona-cuenta
	 */
	public ITRALTARPPANTTRNI wrappBean(CuentaBean cuentaBean, RelacionadoBean relacionadoBean){
		Mapper mapper = dozerBeanMapper;
		ITRALTARPPANTTRNI requisicion = mapper.map(relacionadoBean, ITRALTARPPANTTRNI.class,
				"altaRelacionPersonaCuentaRelacionado");
		mapper.map(cuentaBean,requisicion,"altaRelacionPersonaCuentaCuenta");
		return requisicion;
	}
	
	/**
	 * Wrapper para respuesta de ws de cuentas relacionables.
	 * @param objeto respuesta ws de consulta de cuentas relacionables.
	 * @return Bean Cuenta Bean.
	 */
	public CuentaBean wrappBean(TRCONSUSELECTIV2ACEVT cuenta){
		Mapper mapper = dozerBeanMapper;
		return mapper.map(cuenta, CuentaBean.class,"cuentasRelacionables");
	}
	
	/**
	 * Wrapper para petición de ws de alta de relación de cuentas.
	 * @param cuenta Bean con detalles de la cuenta
	 * @return Objeto petición al ws
	 */
	public Ejecutar.ITRALTARXPANTTRNI.DATOSACUERDOV wrappBean(CuentaBean cuenta){
		Mapper mapper = dozerBeanMapper;
		return mapper.map(cuenta, Ejecutar.ITRALTARXPANTTRNI.DATOSACUERDOV.class,"altaRelacionCuentaCuenta");
	}
	
	/**
	 * Wrapper para resultado de ws de consulta relaciones cuenta-cuenta.
	 * @param relacion datos de la relación entre cuentas
	 * @param detalles datos de la cuenta relacionada
	 * @return bean de cuenta relacionada.
	 */
	public CuentaRelacionadaBean wrappBean(RXACRLACE relacion, RXACRLACV detalles){
		Mapper mapper = dozerBeanMapper;
		CuentaRelacionadaBean cuentaRelacionadaBean =  mapper.map(relacion, CuentaRelacionadaBean.class,"cuentasRelacionadas");
		mapper.map(detalles, cuentaRelacionadaBean,"cuentasRelacionadasDetalles");
		return cuentaRelacionadaBean;
	}
	
	/**
	 * Wrapper para mapear los datos del obejto de peticion para el alta de relaciòn cuenta- cuenta
	 * @param cuenta Cuenta principal
	 * @param relacionada Cuenta a relacionar
	 * @param peticion Objeto de peticiòn al ws
	 */
	public void wrappBean(CuentaBean cuenta, CuentaRelacionadaBean relacionada, TRALTARXPANTEVTY peticion){
		Mapper mapper = dozerBeanMapper;
		mapper.map(cuenta, peticion ,"detallePeticionCuentasRelacionadas");
		mapper.map(relacionada, peticion, "peticionCuentasRelacionadas");
	}
	
	/**
	 * Wrapper para mapear los datos para la modificacion del relacionado.
	 * @param relacionado a la cuenta
	 * @return objeto de peticiòn al ws
	 */
	public void wrappBean(ITRMODRLRPPANTTRNI contexto, RelacionadoBean relacionado){
		Mapper mapper = dozerBeanMapper;
		mapper.map(relacionado, contexto,"modificacionRelacionCuentaPersona");
	}
	
	/**
	 * Wrapper para mapear los datos para la modificacion del relacionado.
	 * @param relacionado a la cuenta
	 * @return objeto de peticiòn al ws
	 */
	public void wrappBean(ITRBAJARPPANTTRNI contexto, RelacionadoBean relacionado, CuentaBean cuenta){
		Mapper mapper = dozerBeanMapper;
		mapper.map(relacionado, contexto,"bajaRelacionCuentaPersona");
		mapper.map(cuenta, contexto, "bajaRelacionCuentaPersonaCuenta");
	}
	
	/**
	 * Wrapper para mapear los datos para la modificacion de cuenta relacionada.
	 * @param contexto Peticiòn para el ws
	 * @param cuenta cuenta a relacionarle otra cuenta
	 * @param relacionadaBean detalles de la relaciòn
	 */
	public void wrappBean(TRMODRLRXPANTEVTY contexto, CuentaBean cuenta, CuentaRelacionadaBean relacionadaBean){
		Mapper mapper = dozerBeanMapper;
		mapper.map(relacionadaBean, contexto,"modificacionRelacionCuentaCuenta");
		mapper.map(cuenta, contexto, "modificacionRelacionCuenta");
	}
	
	/**
	 * 
	 * @param estadoRelacion
	 * @return
	 */
	public HistoricoRelacionadoBean wrappBean(TRCONSULTAECVRPEVTZ estadoRelacion){
		Mapper mapper = dozerBeanMapper;
		HistoricoRelacionadoBean historico =  mapper.map(estadoRelacion, HistoricoRelacionadoBean.class,"historicoRelacionPersona");
		mapper.map(estadoRelacion.getRPECVPERSACV().get(0), historico,"historicoRelacionPersonaTransaccion");
		return historico;
	}
	
	/**
	 * 
	 * @param estadoRelacion
	 * @return
	 */
	public HistoricoRelacionadoBean wrappBean(TRRXCONSMASIVAECVEVT estadoRelacion){
		Mapper mapper = dozerBeanMapper;
		HistoricoRelacionadoBean historico =  mapper.map(estadoRelacion, HistoricoRelacionadoBean.class,"historicoRelacionCuenta");
		mapper.map(estadoRelacion.getRXECVACACV().get(0), historico,"historicoRelacionCuentaTransaccion");
		return historico;
	}
}