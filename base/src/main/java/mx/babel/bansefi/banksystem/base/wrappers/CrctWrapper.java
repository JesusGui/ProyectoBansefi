package mx.babel.bansefi.banksystem.base.wrappers;

import java.io.Serializable;

import mx.babel.bansefi.banksystem.base.beans.domain.BienBean;
import mx.babel.bansefi.banksystem.base.webservices.consultalistacrct.ResponseBansefi.OTRCRCTLSTTRNO.TRCRCTLSTEVTZ;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CrctWrapper implements Serializable{

    @Autowired
    DozerBeanMapper dozerBeanMapper;
    
    private static final long serialVersionUID = -7153144278730974472L;
 
    public void wrappCrctBean(TRCRCTLSTEVTZ crct, BienBean bien){
        Mapper mapper = dozerBeanMapper;
        mapper.map(crct, bien,"crct");
    }

}
