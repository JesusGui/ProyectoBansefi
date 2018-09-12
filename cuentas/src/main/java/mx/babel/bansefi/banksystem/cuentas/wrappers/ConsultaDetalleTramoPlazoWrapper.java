package mx.babel.bansefi.banksystem.cuentas.wrappers;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.bansefi.banksystem.base.beans.PreferenceItemBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.CondicionBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionComisionBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionInteresBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionListaBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionRangoBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionValorListaBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionValorRangoBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.tramos.CabeceraTramoBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.tramos.LimiteTramoBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.tramos.SubTramoBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramoplazo.ResponseBansefi.OTRCONSVALMSVKLTRNO.TRCONSVALMSVKLEVTZ.KLRESULTFILA1V;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramoplazo.ResponseBansefi.OTRCONSVALMSVKLTRNO.TRCONSVALMSVKLEVTZ.KLRESULTFILA1V.IDCCPS1V;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramoplazo.ResponseBansefi.OTRCONSVALMSVKLTRNO.TRCONSVALMSVKLEVTZ.KLRESULTFILA1V.KLCMSNFIJV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramoplazo.ResponseBansefi.OTRCONSVALMSVKLTRNO.TRCONSVALMSVKLEVTZ.KLRESULTFILA1V.KLCMSNPRCTLV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramoplazo.ResponseBansefi.OTRCONSVALMSVKLTRNO.TRCONSVALMSVKLEVTZ.KLRESULTFILA1V.KLESTRCTCMSN1V;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramoplazo.ResponseBansefi.OTRCONSVALMSVKLTRNO.TRCONSVALMSVKLEVTZ.KLRESULTFILA1V.KLESTRCTINT1V;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramoplazo.ResponseBansefi.OTRCONSVALMSVKLTRNO.TRCONSVALMSVKLEVTZ.KLRESULTFILA1V.KLESTRCTINTFIJV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramoplazo.ResponseBansefi.OTRCONSVALMSVKLTRNO.TRCONSVALMSVKLEVTZ.KLRESULTFILA1V.KLESTRCTINTVARV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramoplazo.ResponseBansefi.OTRCONSVALMSVKLTRNO.TRCONSVALMSVKLEVTZ.KLRESULTFILA1V.KLESTRCTRNGV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramoplazo.ResponseBansefi.OTRCONSVALMSVKLTRNO.TRCONSVALMSVKLEVTZ.KLRESULTFILA1V.KLESTRCTVLISTAV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramoplazo.ResponseBansefi.OTRCONSVALMSVKLTRNO.TRCONSVALMSVKLEVTZ.KLRESULTFILA1V.KLESTRCTVRNGV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramoplazo.ResponseBansefi.OTRCONSVALMSVKLTRNO.TRCONSVALMSVKLEVTZ.KLRESULTFILA1V.KLKLRLPKDOMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramoplazo.ResponseBansefi.OTRCONSVALMSVKLTRNO.TRCONSVALMSVKLEVTZ.KLRESULTFILA1V.KLRESULTFILAE;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramoplazo.ResponseBansefi.OTRCONSVALMSVKLTRNO.TRCONSVALMSVKLEVTZ.TDTDRLCOLV.TDTDRLCOLCDE;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramoplazo.ResponseBansefi.OTRCONSVALMSVKLTRNO.TRCONSVALMSVKLEVTZ.TDTRAMO1V;

import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaDetalleTramoPlazoWrapper {


    @Autowired
    DozerBeanMapper dozerBeanMapper;

    private IntegerToDateConverter itdConverter;

    public List<CabeceraTramoBean> wrappCabecerasTramoPlazo(
            final List<TDTDRLCOLCDE> tdtdrlcolcdeList) {
        final List<CabeceraTramoBean> resultado = new ArrayList<CabeceraTramoBean>();
        if(null != tdtdrlcolcdeList){
                for(final TDTDRLCOLCDE tdtdrlcolcde : tdtdrlcolcdeList){
                    if(StringUtils.isNoneBlank(tdtdrlcolcde.getIDPDS1(), tdtdrlcolcde.getIDPARMCD1())){
                        final CabeceraTramoBean cabecera = new CabeceraTramoBean();
                        cabecera.setPosCol(tdtdrlcolcde.getNUMORDCOL());
                        cabecera.setPosPres(tdtdrlcolcde.getNUMORDPRES());
                        cabecera.setIdPds(tdtdrlcolcde.getIDPDS1().trim());
                        cabecera.setIdParamCd(tdtdrlcolcde.getIDPARMCD1().trim());
                        cabecera.setUdMedidas(StringUtils.trimToNull(tdtdrlcolcde.getCODUM()));
                        resultado.add(cabecera);
                    }
                }
            }
        return resultado;
    }

    public List<SubTramoBean> wrappDetalleTramoPlazo(final List<KLRESULTFILA1V> klresultfila1vList,
            final List<TDTRAMO1V> tdtramo1vList) {
        final LinkedHashMap<Integer,SubTramoBean> resultadoMap = new LinkedHashMap<Integer, SubTramoBean>();
        itdConverter = new IntegerToDateConverter();
        final DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(0);
        df.setGroupingUsed(false);
        for(final KLRESULTFILA1V fila1v : klresultfila1vList){
            final KLRESULTFILAE klresultfilae = fila1v.getKLRESULTFILAE();

            if(null != klresultfilae && StringUtils.isNotBlank(klresultfilae.getCODESTRCTCD())){
                CondicionBean condicion = null;
                final SubTramoBean subTramoBean = new SubTramoBean();

                switch (ConstantesFuncionales.CATALOGO_TIPO_CONDICION.getTipo(klresultfilae.getCODESTRCTCD())) {
                case INTERES:

                    condicion = getCondicionInteres(fila1v.getKLESTRCTINT1V(),
                            fila1v.getKLESTRCTINTFIJV(), fila1v.getKLESTRCTINTVARV());
                    if(null != fila1v.getKLESTRCTINTFIJV() && null != fila1v.getKLESTRCTINTFIJV().get(0)){

                        subTramoBean.setValue(df.format(fila1v.getKLESTRCTINTFIJV().get(0).getINTVALOR())+"%");
                    }
                    break;
                case COMISION:

                    condicion = getCondicionComision(fila1v.getKLESTRCTCMSN1V(),
                            fila1v.getKLCMSNFIJV(), fila1v.getKLCMSNPRCTLV());

                    if(null != fila1v.getKLCMSNFIJV() && null != fila1v.getKLCMSNFIJV().get(0)){
                        subTramoBean.setValue(df.format(fila1v.getKLCMSNFIJV().get(0).getIMPCMSNFIJ()));
                    }
                    break;
                case LISTA:

                    condicion = getCondicionLista(fila1v.getKLKLRLPKDOMV());
                    if(null != condicion && null!=((CondicionListaBean)condicion).getLista() ){
                        for(final PreferenceItemBean piBean: ((CondicionListaBean)condicion).getLista()){
                            if(piBean.isPreferente()){
                                subTramoBean.setValue(piBean.getId());
                                break;
                            }
                        }
                    }
                    break;
                case RANGO:

                    condicion = getCondicionRango(fila1v.getKLESTRCTRNGV());
                    break;
                case VALOR_LISTA:
                    //TODO recuerda que hay que parsear esto
                    condicion = getCondicionValorLista(fila1v.getKLESTRCTVLISTAV());
                    if(null != condicion && StringUtils.isNotBlank(((CondicionValorListaBean)condicion).getCodDomParmcd())){
                        subTramoBean.setValue(((CondicionValorListaBean)condicion).getCodDomParmcd());
                    }
                    break;
                case VALOR_RANGO:

                    condicion = getCondicionValorRango(fila1v.getKLESTRCTVRNGV());
                    break;
                default:
                    throw new NoControlableException("Ha ocurrido un error:",
                            "Tipo de comision desconocida.");
                }
                //Set condition common data
                condicion = setDatosComunes(fila1v.getIDCCPS1V(), klresultfilae, condicion);



                subTramoBean.setNumTramo(fila1v.getKLRESULTFILAE().getNUMTRAMO());
                subTramoBean.setCondicionBean(condicion);
                resultadoMap.put(subTramoBean.getNumTramo(), subTramoBean);
            }
        }
        for(final TDTRAMO1V tdtramo1v : tdtramo1vList){
            if(null != tdtramo1v.getTDTRAMOE() && resultadoMap.containsKey(tdtramo1v.getTDTRAMOE().getNUMTRAMO())){
                final LimiteTramoBean limTramo = new LimiteTramoBean();
                limTramo.setLimInferior1(df.format(tdtramo1v.getTDTRAMOE().getRNGINF1()));
                limTramo.setLimSuperior1(df.format(tdtramo1v.getTDTRAMOE().getRNGSUP1()));
                limTramo.setLimInferior2(df.format(tdtramo1v.getTDTRAMOE().getRNGINF2()));
                limTramo.setLimSuperior2(df.format(tdtramo1v.getTDTRAMOE().getRNGSUP2()));
                limTramo.setLimInferior3(df.format(tdtramo1v.getTDTRAMOE().getRNGINF3()));
                limTramo.setLimSuperior3(df.format(tdtramo1v.getTDTRAMOE().getRNGSUP3()));
                limTramo.setIdParmCd1(StringUtils.trim(tdtramo1v.getTDTRAMOE().getIDPARMCD1()));
                limTramo.setCodDomParmCd1(StringUtils.trim(tdtramo1v.getTDTRAMOE().getCODDOMPARMCD1()));
                limTramo.setIdParmCd2(StringUtils.trim(tdtramo1v.getTDTRAMOE().getIDPARMCD2()));
                limTramo.setCodDomParmCd2(StringUtils.trim(tdtramo1v.getTDTRAMOE().getCODDOMPARMCD2()));
                limTramo.setIdParmCd3(StringUtils.trim(tdtramo1v.getTDTRAMOE().getIDPARMCD3()));
                limTramo.setCodDomParmCd3(StringUtils.trim(tdtramo1v.getTDTRAMOE().getCODDOMPARMCD3()));
                limTramo.setIdParmCd4(StringUtils.trim(tdtramo1v.getTDTRAMOE().getIDPARMCD4()));
                limTramo.setCodDomParmCd4(StringUtils.trim(tdtramo1v.getTDTRAMOE().getCODDOMPARMCD4()));
                resultadoMap.get(tdtramo1v.getTDTRAMOE().getNUMTRAMO()).setLimiteTramo(limTramo);
            }
        }
        final List<SubTramoBean> resultList = new ArrayList<SubTramoBean>();
        for(final Integer entry : resultadoMap.keySet()){
            resultList.add(resultadoMap.get(entry));
        }
        return resultList;
    }

    /**
     * @param klestrctvrngv
     * @return
     */
    private CondicionBean getCondicionValorRango(final List<KLESTRCTVRNGV> klestrctvrngv) {
        final CondicionValorRangoBean valorRangoBean = new CondicionValorRangoBean();
        //TODO cambiar por la descripcion buena
        valorRangoBean.setDescripcion("Valor_rango");
        if(null != klestrctvrngv && null != klestrctvrngv.get(0)){
            valorRangoBean.setValor(klestrctvrngv.get(0).getRNGVALOR());
            valorRangoBean.setUnidades(StringUtils.trim(klestrctvrngv.get(0).getCODUM()));
        }
        return valorRangoBean;
    }

    /**
     * @param klestrctvlistav
     * @return
     */
    private CondicionBean getCondicionValorLista(final List<KLESTRCTVLISTAV> klestrctvlistav) {
        final CondicionValorListaBean valorListaBean = new CondicionValorListaBean();
        //TODO cambiar por la descripcion buena
        valorListaBean.setDescripcion("Valor_lista");
        if(null != klestrctvlistav && null != klestrctvlistav.get(0)
                && StringUtils.isNotBlank( klestrctvlistav.get(0).getCODDOMPARMCD())){
            valorListaBean.setCodDomParmcd( StringUtils.trim(klestrctvlistav.get(0).getCODDOMPARMCD()));
        }
        return valorListaBean;
    }

    /**
     * @param klestrctrngv
     * @return
     */
    private CondicionBean getCondicionRango(final List<KLESTRCTRNGV> klestrctrngv) {
        final CondicionRangoBean rangoBean = new CondicionRangoBean();
        //TODO cambiar por la descripcion buena
        rangoBean.setDescripcion("Rango");
        if(null != klestrctrngv && null != klestrctrngv.get(0)){
            final KLESTRCTRNGV item = klestrctrngv.get(0);
            rangoBean.setPreferente(item.getRNGPREF());
            rangoBean.setMinimo(item.getRNGMIN());
            rangoBean.setMaximo(item.getRNGMAX());
            rangoBean.setIncremento(item.getRNGINCREM());
            rangoBean.setUnidades(StringUtils.trim(item.getCODUM()));
        }
        return rangoBean;
    }

    /**
     * @param klklrlpkdomv
     * @return
     */
    private CondicionBean getCondicionLista(final List<KLKLRLPKDOMV> klklrlpkdomv) {
        final CondicionListaBean listaBean = new CondicionListaBean();
        //TODO cambiar por la descripcion buena
        listaBean.setDescripcion("Lista");
        final List<PreferenceItemBean> lista = new ArrayList<PreferenceItemBean>();
        if(klklrlpkdomv != null){
            for(final KLKLRLPKDOMV data : klklrlpkdomv){
                if(data != null && StringUtils.isNoneBlank(data.getCODDOMPARMCD(), data.getDOMINDPREF())){
                    final PreferenceItemBean pib = new PreferenceItemBean();
                    pib.setId(data.getCODDOMPARMCD().trim());
                    pib.setPreferente(StringUtils.equals("1", data.getDOMINDPREF().trim()));
                    lista.add(pib);
                }
            }
        }
        listaBean.setLista(lista);
        return listaBean;
    }

    /**
     * @param klestrctcmsn1v
     * @param klcmsnfijv
     * @param klcmsnprctlv
     * @return
     */
    private CondicionBean getCondicionComision(final List<KLESTRCTCMSN1V> klestrctcmsn1v,
            final List<KLCMSNFIJV> klcmsnfijv, final List<KLCMSNPRCTLV> klcmsnprctlv) {
        final CondicionComisionBean comisionBean = new CondicionComisionBean();
        //TODO cambiar por la descripcion buena
        comisionBean.setDescripcion("Comision");
        if(null != klestrctcmsn1v && null != klestrctcmsn1v.get(0)
                && StringUtils.isNotBlank(klestrctcmsn1v.get(0).getCODCMSN())){
            comisionBean.setTipo(StringUtils.trim(klestrctcmsn1v.get(0).getCODCMSN()));
        }
        if(null != klcmsnfijv && null != klcmsnfijv.get(0)){
            comisionBean.setImporteComFija(klcmsnfijv.get(0).getIMPCMSNFIJ());
            comisionBean.setFormaCalculoComFija(StringUtils.trim(klcmsnfijv.get(0).getFORCALPERPCAL()));
        }
        if(null != klcmsnprctlv && null != klcmsnprctlv.get(0)){
            final KLCMSNPRCTLV item = klcmsnprctlv.get(0);
            comisionBean.setBaseCalculoComVar(StringUtils.trim(item.getCODCOMSNPRCTL()));
            comisionBean.setPorcentajeComisionComVar(item.getCMSNPCT());
            comisionBean.setImporteMinComVar(item.getCMSNMIN());
            comisionBean.setImporteMaxComVar(item.getCMSNMAX());
            comisionBean.setImporteFranquiciaComVar(item.getIMPFRANQ());
        }
        return comisionBean;
    }

    /**
     * @param fila1v
     * @return
     */
    private CondicionBean getCondicionInteres(final List<KLESTRCTINT1V> klestrctint1v,
            final List<KLESTRCTINTFIJV> klestrctintfijv, final List<KLESTRCTINTVARV> klestrctintvarv) {
        final CondicionInteresBean interesBean = new CondicionInteresBean();
      //TODO cambiar por la descripcion buena
        interesBean.setDescripcion("Interes");
        if(null != klestrctint1v && null != klestrctint1v.get(0)
                && StringUtils.isNotBlank(klestrctint1v.get(0).getCODINT())){
            interesBean.setTipo(StringUtils.trim(klestrctint1v.get(0).getCODINT()));
        }
        if(null != klestrctintfijv && null != klestrctintfijv.get(0)){
            interesBean.setValorIntFijo(klestrctintfijv.get(0).getINTVALOR());
        }
        if(null != klestrctintvarv && null != klestrctintvarv.get(0)){
            final KLESTRCTINTVARV datosIntVar = klestrctintvarv.get(0);
            interesBean.setDiferencialIntVar(datosIntVar.getINTINCREM());
            interesBean.setInteresMinIntVar(datosIntVar.getINTMIN());
            interesBean.setInteresMaxIntVar(datosIntVar.getINTMAX());
            interesBean.setFrecuenciaRevIntVar(StringUtils.trim(datosIntVar.getINTFRECREV()));
            interesBean.setFraccionRevIntVar(datosIntVar.getFRACCIONREV());
            interesBean.setPorcentajeMinRevIntVar(datosIntVar.getPCTMINREV());
            interesBean.setCodReferenciaIntVar(StringUtils.trim(datosIntVar.getCODREFINT()));
            interesBean.setCodCriterioRevIntVar(StringUtils.trim(datosIntVar.getCODCRITREV()));
            interesBean.setFormaRevIntVar(StringUtils.trim(datosIntVar.getFORMAREV()));
        }

        return interesBean;
    }

    /**
     * @param fila1v
     * @param klresultfilae
     * @param condicion
     */
    private CondicionBean setDatosComunes(final List<IDCCPS1V> idCcps,
            final KLRESULTFILAE klresultfilae, final CondicionBean condicion) {
        condicion.setFechaInicioValidez(itdConverter.convertTo(klresultfilae.getFECHAINICVAL()));
        condicion.setFechaFinValidez(itdConverter.convertTo(klresultfilae.getFECHACRREVAL()));
        condicion.setFechaEstadoActual(itdConverter.convertTo(klresultfilae.getFECHAECVACT()));
        condicion.setClave(StringUtils.trim(klresultfilae.getIDPARMCD()));
        condicion.setEstado(klresultfilae.getCODECVKL());
        if(null !=  idCcps && null != idCcps.get(0)
                && StringUtils.isNotBlank(idCcps.get(0).getIDCCPS())){
            condicion.setIdCcps(StringUtils.trim(idCcps.get(0).getIDCCPS()));
        }
        return condicion;
    }




}
