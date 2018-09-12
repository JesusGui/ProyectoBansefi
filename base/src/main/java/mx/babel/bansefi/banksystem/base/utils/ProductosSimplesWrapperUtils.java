package mx.babel.bansefi.banksystem.base.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.bansefi.banksystem.base.beans.productossimples.CondicionBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.ProductoSimpleBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionComisionBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionInteresBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionListaBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionRangoBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionTramoBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionValorListaBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionValorRangoBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.webservices.consultacuenta.ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V;
import mx.babel.bansefi.banksystem.base.webservices.consultadatosipf.ResponseBansefi.OTRDATOSRENIMPSCNPAG.TRDATOSRENIMPSCNPAGE.TRDATOSRENIMPSCNPAGE1;
import mx.babel.bansefi.banksystem.base.webservices.consultadatospeticionipf.ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductosSimplesWrapperUtils {

    @Autowired
    DozerBeanMapper dozerBeanMapper;

    public List<ProductoSimpleBean> wrappCondicionesDeConsultaCuenta(
            final List<KACDAC1V> kacdac1vList) {
        return this.mapPDS("consultaCuenta", "KACDACE", new ArrayList<Object>(kacdac1vList));
    }

    public List<ProductoSimpleBean> wrappCondicionesDeConsultaProdSimplesPorCuenta(
            final List<mx.babel.bansefi.banksystem.base.webservices.consultaproductossimplesporcuenta.ResponseBansefi
                .OTRPRESENTACIONACTRNO.TRPRESENTACIONACEVTZ.TRCONSVALMSVKPEVTZ.KACDAC1V> kacdac1v) {

        return this.mapPDS("consultaProdSimplesPorCuenta", "KACDACE", new ArrayList<Object>(kacdac1v));
    }

    public List<ProductoSimpleBean> wrappCondicionesDeCuenta(
    		final List<mx.babel.bansefi.banksystem.base.webservices.consultacondiciones.ResponseBansefi
    		.OTRCONSVALMSVKATRNO.TRCONSVALMSVKAEVTZ.KACDAC1V> kacdac1v){
    	return this.mapPDS("consultaCondicionesCuenta", "KACDACE", new ArrayList<Object>(kacdac1v));
    }

    public List<ProductoSimpleBean> mapPDS(final String mapId, final String objPrincipal, final List<Object> listaCondiciones) {
        final HashMap<String,ProductoSimpleBean> mapaProdSimples = new HashMap<String, ProductoSimpleBean>();

        for(final Object condicion : listaCondiciones){
            if(condicion != null){
                final Object detalleCondicion = this.leerPropiedad(condicion, objPrincipal);
                if(detalleCondicion != null){
                    final String idpds = (String)this.leerPropiedad(detalleCondicion, "IDPDS");
                    final String idparmcd = (String)this.leerPropiedad(detalleCondicion, "IDPARMCD");
                    if(StringUtils.isNoneBlank(idpds, idparmcd)){
                        if(!mapaProdSimples.containsKey(idpds)){
                            mapaProdSimples.put(idpds, new ProductoSimpleBean(idpds));
                        }
                        //FIXME pasamos el objeto entero por si es necesario parsear
                        //algun dato en concreto que no es generico. Actualmente, condicionRango para los plazos
                        final CondicionBean condicionBean = this.getCondicion(detalleCondicion, mapId, condicion);
                        final List<Object> idccps1VList = (List<Object>)this.leerPropiedad(condicion, "IDCCPS1V");
                        if(null != idccps1VList && !idccps1VList.isEmpty()){
                            final String idccps = (String)this.leerPropiedad(idccps1VList.get(0), "IDCCPS");
                            if(StringUtils.isNotBlank(idccps)){
                                condicionBean.setIdCcps(idccps.trim());
                        }
                            }
                        mapaProdSimples.get(idpds).getCondiciones().add(condicionBean);
                    }
                }
            }
        }
        return new ArrayList<ProductoSimpleBean>(mapaProdSimples.values());
    }

    private CondicionBean getCondicion(final Object detalleCondHost, final String origen, final Object condicionHost) {
        CondicionBean condicion = null;
        final String codOrgCd = (String)this.leerPropiedad(detalleCondHost, "CODORGCD");

        if(StringUtils.equals(codOrgCd, "M")){
            condicion = this.wrappService(CondicionTramoBean.class, detalleCondHost, origen+"Tramo");
        }else{
            final String codEstrctCd = (String)this.leerPropiedad(detalleCondHost, "CODESTRCTCD");
            if(StringUtils.isBlank(codEstrctCd)){
                condicion = this.wrappService(CondicionBean.class, detalleCondHost, origen+"Default");
            }else{
                final ConstantesFuncionales.CATALOGO_TIPO_CONDICION tipo =
                        ConstantesFuncionales.CATALOGO_TIPO_CONDICION.getTipo(codEstrctCd);
                switch (tipo) {
                case INTERES:
                    condicion = this.wrappService(CondicionInteresBean.class, detalleCondHost, origen+"Interes");
                    break;
                case COMISION:
                    condicion = this.wrappService(CondicionComisionBean.class, detalleCondHost, origen+"Comision");
                    break;
                case LISTA:
                    condicion = this.wrappService(CondicionListaBean.class, detalleCondHost, origen+"Lista");
                    break;
                case RANGO:
                    condicion = this.wrappService(CondicionRangoBean.class, detalleCondHost, origen+"Rango");
                    //FIXME a partir del objeto kacdac1v que contiene todo sobre la condicion, obtenemos un dato
                    //especifico como es el minimo para la contratación de plazos.
                    final CondicionRangoBean rangoTemp = this.wrappService(CondicionRangoBean.class, condicionHost, origen+"RangoValorMinimo");
                    if(null!= rangoTemp && rangoTemp.getMinimo()!=null){
                        ((CondicionRangoBean)condicion).setMinimo(rangoTemp.getMinimo());
                    }
                    break;
                case VALOR_LISTA:
                    condicion = this.wrappService(CondicionValorListaBean.class, detalleCondHost, origen+"ValorLista");
                    break;
                case VALOR_RANGO:
                    condicion = this.wrappService(CondicionValorRangoBean.class, detalleCondHost, origen+"ValorRango");
                    //FIXME a partir del objeto kacdac1v que contiene todo sobre la condicion, obtenemos un dato
                    //especifico como es el valor para el pago de cuota de IPFS.
                    final CondicionValorRangoBean valorRangoTemp = this.wrappService(CondicionValorRangoBean.class, condicionHost, origen+"ValorRangoValor");
                    if(null!= valorRangoTemp && valorRangoTemp.getValor()!=null){
                        ((CondicionValorRangoBean)condicion).setValor(valorRangoTemp.getValor());
                    }
                    break;
                default:
                     throw new NoControlableException("Error al wrappear los datos de la condición.",
                        this.getClass().getName()+": Tipo de comision desconocida ("+tipo.name()+").");
                }
            }
        }

        return condicion;
    }

    /**
     * @param kacdace
     * @param codOrgCd
     * @return
     */
    private Object leerPropiedad(final Object objeto, final String propiedad) {
        try {
            return PropertyUtils.getProperty(objeto, propiedad);
        } catch (IllegalAccessException | InvocationTargetException
                | NoSuchMethodException e) {
            throw new NoControlableException("Ha ocurrido un error al leer las condiciones de la cuenta", e);
        }
    }

    private <T, U> T wrappService(final Class<T> entityClass, final U documento, final String mapId) {
        final Mapper mapper = dozerBeanMapper;
        return mapper.map(documento, entityClass, mapId);
    }

    public List<ProductoSimpleBean> wrappCondicionesDeConsultaDatosPeticionIPF(
            final List<TRDATOSPETIMPSCNPAGE1> trdatospetimpscnpage) {
        return this.mapPDS("consultaDatosPeticionIPF", "IPDATOSCONDV", new ArrayList<Object>(trdatospetimpscnpage));
    }

    public List<ProductoSimpleBean> wrappCondicionesDeConsultaDatosIPF(
            final List<TRDATOSRENIMPSCNPAGE1> trdatosrenimpscnpage) {
        return this.mapPDS("consultaDatosIPF", "IPDATOSCONDV", new ArrayList<Object>(trdatosrenimpscnpage));
    }


}
