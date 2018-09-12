package mx.babel.bansefi.banksystem.base.utils;

import java.math.BigDecimal;

import mx.babel.bansefi.banksystem.base.beans.movimientos.MovimientoBean;
import mx.babel.bansefi.banksystem.base.webservices.consultaautorizaciones.ResponseBansefi.OTRCONSULTAGLOBALATTR;
import mx.babel.bansefi.banksystem.base.webservices.consultabloqueos.ResponseBansefi.OTRPETICIONCONSULTABP.TRPETICIONCONSULTABPE.BPLISTABLOQUEOV;
import mx.babel.bansefi.banksystem.base.webservices.consultamovimientos.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.webservices.consultaretenciones.ResponseBansefi.OTRCONSULTAGLOBALRTTR;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author mario.montesdeoca
 *
 */
@Component
public class MovimientosWrapper {

	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	public MovimientoBean wrappMovimiento(ResponseBansefi movimiento){
		Mapper mapper = dozerBeanMapper;
		MovimientoBean movimientoBean = mapper.map(movimiento, MovimientoBean.class,"movimientos");
		if(("R").equals(movimiento.getSIGNO())){
			movimientoBean.setImporte(movimientoBean.getImporte().multiply(new BigDecimal("-1")));
		}
		return movimientoBean;
	}
	
	public MovimientoBean wrappBloqueo(BPLISTABLOQUEOV bloqueo){
		Mapper mapper = dozerBeanMapper;
		return mapper.map(bloqueo, MovimientoBean.class,"bloqueos");
	}
	
	public MovimientoBean wrappRetencion(OTRCONSULTAGLOBALRTTR.TRCONSULTAGLOBALRTEVT.TRAFCONSEVTV.AFAPNTEE retencion){
		Mapper mapper = dozerBeanMapper;
		return mapper.map(retencion, MovimientoBean.class,"retenciones");
	}
	
	public MovimientoBean wrappAutorizacion(OTRCONSULTAGLOBALATTR.TRCONSULTAGLOBALATEVT.TRAFCONSEVTV.AFAPNTEE autorizacion){
		Mapper mapper = dozerBeanMapper;
		return mapper.map(autorizacion, MovimientoBean.class,"autorizaciones");
	}
	
	public MovimientoBean wrappAuditoria(OTRCONSULTAGLOBALRTTR.TRCONSULTAGLOBALRTEVT.TRAFCONSEVTV.AFAUDITE auditoria){
		Mapper mapper = dozerBeanMapper;
		return mapper.map(auditoria, MovimientoBean.class,"auditoria");
	}
}
