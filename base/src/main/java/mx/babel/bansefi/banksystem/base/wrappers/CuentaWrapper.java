package mx.babel.bansefi.banksystem.base.wrappers;

import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Bean encargado de mapeo entre cuentas
 * @author mario.montesdeoca
 *
 */
@Component
public class CuentaWrapper {

    /**
     * Asignación de bean de mapeo
     */
    @Autowired
    DozerBeanMapper dozerBeanMapper;
        
    public void wrappBean(CuentaBean cuentaDestino, CuentaBean cuentaOrigen){
    	Mapper mapper = dozerBeanMapper;
		mapper.map(cuentaOrigen, cuentaDestino,"cuentaCuentaWrapper");
    }    
}
