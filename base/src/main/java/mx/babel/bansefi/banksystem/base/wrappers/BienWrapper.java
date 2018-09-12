package mx.babel.bansefi.banksystem.base.wrappers;

import mx.babel.bansefi.banksystem.base.beans.domain.BienBean;
import mx.babel.bansefi.banksystem.base.webservices.altabieninmueble.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultabienes.ResponseBansefi.OTRPERLBILSTTRNO.TRPERLBILSTEVTZ.BIINGGASLSV;
import mx.babel.bansefi.banksystem.base.webservices.consultabienes.ResponseBansefi.OTRPERLBILSTTRNO.TRPERLBILSTEVTZ.BIOTRBIENLSV;
import mx.babel.bansefi.banksystem.base.webservices.consultadetallebien.ResponseBansefi.OTRBIENCNSTRNO.TRBIENCNSEVTZ;
import mx.babel.bansefi.banksystem.base.webservices.consultadetallebieningresogasto.ResponseBansefi.OTRCONSBIENTRNO.TRCONSBIENEVTZ;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Bean encargado de realizar mapeos entre respuestas de webservices hacia beans relacionados a bienes.
 * 
 * @author luis.gonzalez
 *
 */
@Component
public class BienWrapper {

    /**
     * Asignación de bean de mapeo
     */
    @Autowired
    DozerBeanMapper dozerBeanMapper;
        
    /**
     * Función responsable de  mapeo entre objeto bien y la entrada del webservice TR_BIEN_ALTA_TRN 
     * 
     * 
     * @param bien El bean bien relleno en la vista
     * @return bean El objeto para realizar el alta de bien llamando al WS
     */
    public Ejecutar.ITRBIENALTATRNI.TRBIENALTAEVTY wrappAltaBienInmuebleBean(BienBean bien){
        Mapper mapper = dozerBeanMapper;
        return mapper.map(bien, Ejecutar.ITRBIENALTATRNI.TRBIENALTAEVTY.class,"altaBienInmueble");
    }
    
    /**
     * Función responsable de  mapeo entre objeto bien y la entrada del webservice TR_ALTA_BIEN_ING_GAS_TRN 
     * 
     * 
     * @param bien El bean bien relleno en la vista
     * @return bean El objeto para realizar el alta de bien llamando al WS
     */
    public  mx.babel.bansefi.banksystem.base.webservices.altabieningresogasto.Ejecutar.ITRALTABIENINGGASTRN.TRALTABIENINGGASEVT wrappAltaBienIngresoGastoBean(BienBean bien){
        Mapper mapper = dozerBeanMapper;
        return mapper.map(bien, mx.babel.bansefi.banksystem.base.webservices.altabieningresogasto.Ejecutar.ITRALTABIENINGGASTRN.TRALTABIENINGGASEVT.class,"altaBienIngresoGasto");
    }
    
    /**
     * Función responsable de  mapeo entre objeto de salida del WS de consulta Lista bienes y el bean bienBean 
     * 
     * 
     * @param bien objeto respuesta del webservice TR_PE_RL_BI_LST_TRN
     * @return bean BienBean
     */
    public BienBean wrappBienInmuebleBean(BIOTRBIENLSV bien){
        Mapper mapper = dozerBeanMapper;
        return mapper.map(bien, BienBean.class,"bienInmuebleLista");
    }
    
    /**
     * Función responsable de  mapeo entre objeto de salida del WS de consulta Lista bienes y el bean bienBean 
     * 
     * @param bien objeto respuesta del webservice TR_PE_RL_BI_LST_TRN
     * @return bean BienBean
     */
    public BienBean wrappBienIngresoBean(BIINGGASLSV bien){
        Mapper mapper = dozerBeanMapper;
        return mapper.map(bien, BienBean.class,"bienIngresoLista");
    }
    
    
    public void wrappBienDetalleBean(TRBIENCNSEVTZ bienDetalle, BienBean bien){
        Mapper mapper = dozerBeanMapper;
        mapper.map(bienDetalle, bien,"bienDetalle");
    }
    
    
    
    public void wrappBienDetalleIngresoGastoBean(TRCONSBIENEVTZ bienDetalle, BienBean bien){
        Mapper mapper = dozerBeanMapper;
        mapper.map(bienDetalle, bien,"bienDetalleIngresoGasto");
    }
    
    /**
     * Función responsable de  mapeo entre un bien y el objeto de entrada para la llamada al WS de baja de bien inmueble 
     * 
     * @param BienBean
     * @return PEPERSRLBIENP Objeto para dar de baja un bien
     */
    public mx.babel.bansefi.banksystem.base.webservices.bajabieninmueble.Ejecutar.ITRBIENBAJATRNI.TRBIENBAJAEVTY.PEPERSRLBIENP wrappBajaBienInmuebleBean(BienBean bien){
        Mapper mapper = dozerBeanMapper;
        return mapper.map(bien, mx.babel.bansefi.banksystem.base.webservices.bajabieninmueble.Ejecutar.ITRBIENBAJATRNI.TRBIENBAJAEVTY.PEPERSRLBIENP.class,"bajaBienInmueble");
    }
    
    /**
     * Función responsable de  mapeo entre un bien y el objeto de entrada para la llamada al WS de baja de bien ingreso gasto 
     * 
     * @param BienBean
     * @return PEPERSRLBIENP Objeto para dar de baja un bien
     */
    public mx.babel.bansefi.banksystem.base.webservices.bajabieningresogasto.Ejecutar.ITRBAJABIENINGGASTRN.TRBAJABIENINGGASEVT.PEPERSRLBIENP wrappBajaBienIngresoGastoBean(BienBean bien){
        Mapper mapper = dozerBeanMapper;
        return mapper.map(bien, mx.babel.bansefi.banksystem.base.webservices.bajabieningresogasto.Ejecutar.ITRBAJABIENINGGASTRN.TRBAJABIENINGGASEVT.PEPERSRLBIENP.class,"bajaBienIngresoGasto");
    }
    
    /**
     * Función responsable de  mapeo entre un bien y el objeto de entrada para la llamada al WS de modificacion de bien inmueble 
     * 
     * @param BienBean
     * @return TRBIENMODIEVTY Objeto para modificar un bien inmueble
     */
    public mx.babel.bansefi.banksystem.base.webservices.modificabieninmueble.Ejecutar.ITRBIENMODITRNI.TRBIENMODIEVTY wrappModificaBienInmuebleBean(BienBean bien){
        Mapper mapper = dozerBeanMapper;
        return mapper.map(bien, mx.babel.bansefi.banksystem.base.webservices.modificabieninmueble.Ejecutar.ITRBIENMODITRNI.TRBIENMODIEVTY.class,"modificaBienInmueble");
    }
    
    /**
     * Función responsable de  mapeo entre un bien y el objeto de entrada para la llamada al WS de modificacion de bien ingreso gasto 
     * 
     * @param BienBean
     * @return PEPERSRLBIENP Objeto para dar de baja un bien
     */
    public mx.babel.bansefi.banksystem.base.webservices.modificabieningresogasto.Ejecutar.ITRMODIBIENINGGASTRN.TRMODIBIENINGGASEVT wrappModificaBienIngresoGastoBean(BienBean bien){
        Mapper mapper = dozerBeanMapper;
        return mapper.map(bien, mx.babel.bansefi.banksystem.base.webservices.modificabieningresogasto.Ejecutar.ITRMODIBIENINGGASTRN.TRMODIBIENINGGASEVT.class,"modificaBienIngresGasto");
    }
    
    
    
}
