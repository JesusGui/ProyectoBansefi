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
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramo.ResponseBansefi.OTRCONSVALMSVKFTRNO.TRCONSVALMSVKFEVTZ.KFRESULTFILA1V;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramo.ResponseBansefi.OTRCONSVALMSVKFTRNO.TRCONSVALMSVKFEVTZ.KFRESULTFILA1V.IDCCPS1V;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramo.ResponseBansefi.OTRCONSVALMSVKFTRNO.TRCONSVALMSVKFEVTZ.KFRESULTFILA1V.KFCMSNFIJV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramo.ResponseBansefi.OTRCONSVALMSVKFTRNO.TRCONSVALMSVKFEVTZ.KFRESULTFILA1V.KFCMSNPRCTLV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramo.ResponseBansefi.OTRCONSVALMSVKFTRNO.TRCONSVALMSVKFEVTZ.KFRESULTFILA1V.KFESTRCTCMSN1V;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramo.ResponseBansefi.OTRCONSVALMSVKFTRNO.TRCONSVALMSVKFEVTZ.KFRESULTFILA1V.KFESTRCTINT1V;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramo.ResponseBansefi.OTRCONSVALMSVKFTRNO.TRCONSVALMSVKFEVTZ.KFRESULTFILA1V.KFESTRCTINTFIJV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramo.ResponseBansefi.OTRCONSVALMSVKFTRNO.TRCONSVALMSVKFEVTZ.KFRESULTFILA1V.KFESTRCTINTVARV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramo.ResponseBansefi.OTRCONSVALMSVKFTRNO.TRCONSVALMSVKFEVTZ.KFRESULTFILA1V.KFESTRCTRNGV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramo.ResponseBansefi.OTRCONSVALMSVKFTRNO.TRCONSVALMSVKFEVTZ.KFRESULTFILA1V.KFESTRCTVLISTAV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramo.ResponseBansefi.OTRCONSVALMSVKFTRNO.TRCONSVALMSVKFEVTZ.KFRESULTFILA1V.KFESTRCTVRNGV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramo.ResponseBansefi.OTRCONSVALMSVKFTRNO.TRCONSVALMSVKFEVTZ.KFRESULTFILA1V.KFKFRLPKDOMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramo.ResponseBansefi.OTRCONSVALMSVKFTRNO.TRCONSVALMSVKFEVTZ.KFRESULTFILA1V.KFRESULTFILAE;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramo.ResponseBansefi.OTRCONSVALMSVKFTRNO.TRCONSVALMSVKFEVTZ.TDTDRLCOLV.TDTDRLCOLCDE;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalletramo.ResponseBansefi.OTRCONSVALMSVKFTRNO.TRCONSVALMSVKFEVTZ.TDTRAMO1V;

import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaDetalleTramoWrapper {


    @Autowired
    DozerBeanMapper dozerBeanMapper;

    private IntegerToDateConverter itdConverter;

    public List<CabeceraTramoBean> wrappCabecerasTramo(
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

    public List<SubTramoBean> wrappDetalleTramo(final List<KFRESULTFILA1V> kfresultfila1vList,
            final List<TDTRAMO1V> tdtramo1vList) {
        final LinkedHashMap<Integer,SubTramoBean> resultadoMap = new LinkedHashMap<Integer, SubTramoBean>();
        itdConverter = new IntegerToDateConverter();
        final DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(0);
        df.setGroupingUsed(false);
        for(final KFRESULTFILA1V fila1v : kfresultfila1vList){
            final KFRESULTFILAE kfresultfilae = fila1v.getKFRESULTFILAE();

            if(null != kfresultfilae && StringUtils.isNotBlank(kfresultfilae.getCODESTRCTCD())){
                CondicionBean condicion = null;
                final SubTramoBean subTramoBean = new SubTramoBean();

                switch (ConstantesFuncionales.CATALOGO_TIPO_CONDICION.getTipo(kfresultfilae.getCODESTRCTCD())) {
                case INTERES:
                    condicion = getCondicionInteres(fila1v.getKFESTRCTINT1V(),
                            fila1v.getKFESTRCTINTFIJV(), fila1v.getKFESTRCTINTVARV());
                    if(null != fila1v.getKFESTRCTINTFIJV() && null != fila1v.getKFESTRCTINTFIJV().get(0)){

                        subTramoBean.setValue(df.format(fila1v.getKFESTRCTINTFIJV().get(0).getINTVALOR())+"%");
                    }
                    break;
                case COMISION:
                    condicion = getCondicionComision(fila1v.getKFESTRCTCMSN1V(),
                            fila1v.getKFCMSNFIJV(), fila1v.getKFCMSNPRCTLV());

                    if(null != fila1v.getKFCMSNFIJV() && null != fila1v.getKFCMSNFIJV().get(0)){
                        subTramoBean.setValue(df.format(fila1v.getKFCMSNFIJV().get(0).getIMPCMSNFIJ()));
                    }
                    break;
                case LISTA:
                    condicion = getCondicionLista(fila1v.getKFKFRLPKDOMV());
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
                    condicion = getCondicionRango(fila1v.getKFESTRCTRNGV());
                    break;
                case VALOR_LISTA:
                    //TODO recuerda que hay que parsear esto
                    condicion = getCondicionValorLista(fila1v.getKFESTRCTVLISTAV());
                    if(null != condicion && StringUtils.isNotBlank(((CondicionValorListaBean)condicion).getCodDomParmcd())){
                        subTramoBean.setValue(((CondicionValorListaBean)condicion).getCodDomParmcd());
                    }
                    break;
                case VALOR_RANGO:
                    condicion = getCondicionValorRango(fila1v.getKFESTRCTVRNGV());
                    break;
                default:
                    throw new NoControlableException("Ha ocurrido un error:",
                            "Tipo de comision desconocida.");
                }
                //Set condition common data
                condicion = setDatosComunes(fila1v.getIDCCPS1V(), kfresultfilae, condicion);



                subTramoBean.setNumTramo(fila1v.getKFRESULTFILAE().getNUMTRAMO());
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
     * @param kfestrctvrngv
     * @return
     */
    private CondicionBean getCondicionValorRango(final List<KFESTRCTVRNGV> kfestrctvrngv) {
        final CondicionValorRangoBean valorRangoBean = new CondicionValorRangoBean();
        //TODO cambiar por la descripcion buena
        valorRangoBean.setDescripcion("Valor_rango");
        if(null != kfestrctvrngv && null != kfestrctvrngv.get(0)){
            valorRangoBean.setValor(kfestrctvrngv.get(0).getRNGVALOR());
            valorRangoBean.setUnidades(StringUtils.trim(kfestrctvrngv.get(0).getCODUM()));
        }
        return valorRangoBean;
    }

    /**
     * @param kfestrctvlistav
     * @return
     */
    private CondicionBean getCondicionValorLista(final List<KFESTRCTVLISTAV> kfestrctvlistav) {
        final CondicionValorListaBean valorListaBean = new CondicionValorListaBean();
        //TODO cambiar por la descripcion buena
        valorListaBean.setDescripcion("Valor_lista");
        if(null != kfestrctvlistav && null != kfestrctvlistav.get(0)
                && StringUtils.isNotBlank( kfestrctvlistav.get(0).getCODDOMPARMCD())){
            valorListaBean.setCodDomParmcd( StringUtils.trim(kfestrctvlistav.get(0).getCODDOMPARMCD()));
        }
        return valorListaBean;
    }

    /**
     * @param kfestrctrngv
     * @return
     */
    private CondicionBean getCondicionRango(final List<KFESTRCTRNGV> kfestrctrngv) {
        final CondicionRangoBean rangoBean = new CondicionRangoBean();
        //TODO cambiar por la descripcion buena
        rangoBean.setDescripcion("Rango");
        if(null != kfestrctrngv && null != kfestrctrngv.get(0)){
            final KFESTRCTRNGV item = kfestrctrngv.get(0);
            rangoBean.setPreferente(item.getRNGPREF());
            rangoBean.setMinimo(item.getRNGMIN());
            rangoBean.setMaximo(item.getRNGMAX());
            rangoBean.setIncremento(item.getRNGINCREM());
            rangoBean.setUnidades(StringUtils.trim(item.getCODUM()));
        }
        return rangoBean;
    }

    /**
     * @param kfkfrlpkdomv
     * @return
     */
    private CondicionBean getCondicionLista(final List<KFKFRLPKDOMV> kfkfrlpkdomv) {
        final CondicionListaBean listaBean = new CondicionListaBean();
        //TODO cambiar por la descripcion buena
        listaBean.setDescripcion("Lista");
        final List<PreferenceItemBean> lista = new ArrayList<PreferenceItemBean>();
        if(kfkfrlpkdomv != null){
            for(final KFKFRLPKDOMV data : kfkfrlpkdomv){
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
     * @param kfestrctcmsn1v
     * @param kfcmsnfijv
     * @param kfcmsnprctlv
     * @return
     */
    private CondicionBean getCondicionComision(final List<KFESTRCTCMSN1V> kfestrctcmsn1v,
            final List<KFCMSNFIJV> kfcmsnfijv, final List<KFCMSNPRCTLV> kfcmsnprctlv) {
        final CondicionComisionBean comisionBean = new CondicionComisionBean();
        //TODO cambiar por la descripcion buena
        comisionBean.setDescripcion("Comision");
        if(null != kfestrctcmsn1v && null != kfestrctcmsn1v.get(0)
                && StringUtils.isNotBlank(kfestrctcmsn1v.get(0).getCODCMSN())){
            comisionBean.setTipo(StringUtils.trim(kfestrctcmsn1v.get(0).getCODCMSN()));
        }
        if(null != kfcmsnfijv && null != kfcmsnfijv.get(0)){
            comisionBean.setImporteComFija(kfcmsnfijv.get(0).getIMPCMSNFIJ());
            comisionBean.setFormaCalculoComFija(StringUtils.trim(kfcmsnfijv.get(0).getFORCALPERPCAL()));
        }
        if(null != kfcmsnprctlv && null != kfcmsnprctlv.get(0)){
            final KFCMSNPRCTLV item = kfcmsnprctlv.get(0);
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
    private CondicionBean getCondicionInteres(final List<KFESTRCTINT1V> kfestrctint1v,
            final List<KFESTRCTINTFIJV> kfestrctintfijv, final List<KFESTRCTINTVARV> kfestrctintvarv) {
        final CondicionInteresBean interesBean = new CondicionInteresBean();
      //TODO cambiar por la descripcion buena
        interesBean.setDescripcion("Interes");
        if(null != kfestrctint1v && null != kfestrctint1v.get(0)
                && StringUtils.isNotBlank(kfestrctint1v.get(0).getCODINT())){
            interesBean.setTipo(StringUtils.trim(kfestrctint1v.get(0).getCODINT()));
        }
        if(null != kfestrctintfijv && null != kfestrctintfijv.get(0)){
            interesBean.setValorIntFijo(kfestrctintfijv.get(0).getINTVALOR());
        }
        if(null != kfestrctintvarv && null != kfestrctintvarv.get(0)){
            final KFESTRCTINTVARV datosIntVar = kfestrctintvarv.get(0);
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
     * @param kfresultfilae
     * @param condicion
     */
    private CondicionBean setDatosComunes(final List<IDCCPS1V> idCcps,
            final KFRESULTFILAE kfresultfilae, final CondicionBean condicion) {
        condicion.setFechaInicioValidez(itdConverter.convertTo(kfresultfilae.getFECHAINICVAL()));
        condicion.setFechaFinValidez(itdConverter.convertTo(kfresultfilae.getFECHACRREVAL()));
        condicion.setFechaEstadoActual(itdConverter.convertTo(kfresultfilae.getFECHAECVACT()));
        condicion.setClave(StringUtils.trim(kfresultfilae.getIDPARMCD()));
        condicion.setEstado(kfresultfilae.getCODECVKF());
        if(null !=  idCcps && null != idCcps.get(0)
                && StringUtils.isNotBlank(idCcps.get(0).getIDCCPS())){
            condicion.setIdCcps(idCcps.get(0).getIDCCPS());
        }
        return condicion;
    }




}
