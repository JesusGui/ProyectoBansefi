package mx.babel.bansefi.banksystem.base.wrappers;

import java.io.Serializable;

import mx.babel.bansefi.banksystem.base.beans.domain.TransaccionalidadBean;
import mx.babel.bansefi.banksystem.base.beans.domain.UsoCuentaBean;
import mx.babel.bansefi.banksystem.base.webservices.consultaperfiltransaccional.ResponseBansefi.OPECONSTRANESTIMTRNO.PECONSTRANESTIMEVTZ;
import mx.babel.bansefi.banksystem.base.webservices.consultausocuenta.ResponseBansefi.OPECONSINDVUSOCTATRN.PECONSINDVUSOCTAEVT.PEPERSUSOCTAE;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Bean encargado de realizar mapeos entre respuestas de webservices hacia beans relacionados con cedula de conocimiento.
 * 
 * @author javier.martinnino
 *
 */
@Component
public class CedulaConocimientoWrapper implements Serializable{

	private static final long serialVersionUID = 8156081694957395850L;
	
	/**
     * Asignación de bean de mapeo
     */
    @Autowired
    DozerBeanMapper dozerBeanMapper;
    
    /**
     * Función responsable de mapeo entre respuesta de webservice PE_CONS_TRAN_ESTIM_TRN
     * y TransaccionalidadBean
     * 
     * @param transaccionalidad objeto respuesta del webservice PE_CONS_TRAN_ESTIM_TRN
     * @return bean TransaccionalidadBean
     */
    public TransaccionalidadBean wrappTransaccionalidad(PECONSTRANESTIMEVTZ transaccionalidad){
        Mapper mapper = dozerBeanMapper;
        return mapper.map(transaccionalidad, TransaccionalidadBean.class,"transaccionalidad");
    }
    
    /**
     * Función responsable de mapeo entre respuesta de webservice PE_CONS_INDV_USO_CTA_TRN
     * y UsoCuentaBean
     * 
     * @param usoCuenta objeto respuesta del webservice PE_CONS_INDV_USO_CTA_TRN
     * @return bean UsoCuentaBean
     */
    public UsoCuentaBean wrappUsoCuenta(PEPERSUSOCTAE usoCuenta){
        Mapper mapper = dozerBeanMapper;
        return mapper.map(usoCuenta, UsoCuentaBean.class,"usocuenta");
    }
}
