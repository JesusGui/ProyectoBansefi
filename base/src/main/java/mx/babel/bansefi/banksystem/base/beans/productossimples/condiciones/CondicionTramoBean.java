package mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones;

import java.util.List;

import mx.babel.bansefi.banksystem.base.beans.productossimples.CondicionBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.tramos.CabeceraTramoBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.tramos.SubTramoBean;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class CondicionTramoBean extends CondicionBean {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String idTrameado;
    private String codForAplcTr;
    private String idPds;
    private String idParamcd;
    private String codAgruClop;
    private String baseCalculoDesc;
    private List<SubTramoBean> subtramoList;
    private List<CabeceraTramoBean> cabeceraList;

    //Logica de paginacion
    private boolean masDatos;
    private Integer ultimoDatoConsultaAnterior;
    private int indice;

    public CondicionTramoBean() {
        super("tramo");
        indice = 10;
    }

    /**
     * @return the idTrameado
     */
    public String getIdTrameado() {
        return idTrameado;
    }

    /**
     * @param idTrameado
     *            the idTrameado to set
     */
    public void setIdTrameado(final String idTrameado) {
        this.idTrameado = idTrameado;
    }

    /**
     * @return the codForAplcTr
     */
    public String getCodForAplcTr() {
        return codForAplcTr;
    }

    /**
     * @param codForAplcTr
     *            the codForAplcTr to set
     */
    public void setCodForAplcTr(final String codForAplcTr) {
        this.codForAplcTr = codForAplcTr;
    }

    /**
     * @return the idPds
     */
    public String getIdPds() {
        return idPds;
    }

    /**
     * @param idPds
     *            the idPds to set
     */
    public void setIdPds(final String idPds) {
        this.idPds = idPds;
    }

    /**
     * @return the idParamcd
     */
    public String getIdParamcd() {
        return idParamcd;
    }

    /**
     * @param idParamcd
     *            the idParamcd to set
     */
    public void setIdParamcd(final String idParamcd) {
        this.idParamcd = idParamcd;
    }

    /**
     * @return the codAgruClop
     */
    public String getCodAgruClop() {
        return codAgruClop;
    }

    /**
     * @param codAgruClop
     *            the codAgruClop to set
     */
    public void setCodAgruClop(final String codAgruClop) {
        this.codAgruClop = codAgruClop;
    }



    /**
     * @return the subtramoList
     */
    public List<SubTramoBean> getSubtramoList() {
        return subtramoList;
    }

    /**
     * Metodo que nos devuelve la lista con el numero de elementos indicado por la paginacion
     * @return the subtramoList
     */
    public List<SubTramoBean> getSubtramoListPaginada() {
        if(subtramoList.size()>this.indice){
            return subtramoList.subList(0, this.indice);
        }
        return subtramoList;
    }

    /**
     * @param subtramoList the subtramoList to set
     */
    public void setSubtramoList(final List<SubTramoBean> subtramoList) {
        this.subtramoList = subtramoList;
    }

    /**
     * @return the cabeceraList
     */
    public CabeceraTramoBean getCabeceraBean(final int i) {
        CabeceraTramoBean resultado = null;
        if(null != this.cabeceraList){
            for(final CabeceraTramoBean cabeceraTramo : cabeceraList){
                if(null != cabeceraTramo && null != cabeceraTramo.getPosCol()
                   && cabeceraTramo.getPosPres().intValue() == i){
                    resultado = cabeceraTramo;
                }
            }
        }
        if(resultado == null){
            resultado = new CabeceraTramoBean();
            resultado.setDescripcion("Columna "+i);
        }
        return resultado;
    }

    /**
     * @return the cabeceraList
     */
    public List<CabeceraTramoBean> getCabeceraList() {
        return cabeceraList;
    }

    /**
     * @param cabeceraList the cabeceraList to set
     */
    public void setCabeceraList(final List<CabeceraTramoBean> cabeceraList) {
        this.cabeceraList = cabeceraList;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final HashCodeBuilder hashCode = new HashCodeBuilder().appendSuper(super.hashCode())
                .append(idTrameado).append(codForAplcTr).append(idPds).append(idParamcd)
                .append(codAgruClop).append(baseCalculoDesc);
        if(null != subtramoList){
            for(final SubTramoBean bean : subtramoList){
                hashCode.append(bean);
            }
        }
         return hashCode.toHashCode();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CondicionTramoBean)) {
            return false;
        }
        final CondicionTramoBean castObj = (CondicionTramoBean) obj;
        final EqualsBuilder eq = new EqualsBuilder().appendSuper(super.equals(obj))
                .append(idTrameado, castObj.idTrameado)
                .append(codForAplcTr, castObj.codForAplcTr)
                .append(idPds, castObj.idPds)
                .append(idParamcd, castObj.idParamcd)
                .append(codAgruClop, castObj.codAgruClop)
                .append(baseCalculoDesc, castObj.baseCalculoDesc);

        return eq.isEquals();
    }

    /**
     * @return the masDatos
     */
    public boolean isMasDatos() {
        return masDatos;
    }

    /**
     * @param masDatos the masDatos to set
     */
    public void setMasDatos(final boolean masDatos) {
        this.masDatos = masDatos;
    }

    /**
     * @return the ultimoDatoConsultaAnterior
     */
    public Integer getUltimoDatoConsultaAnterior() {
        return ultimoDatoConsultaAnterior;
    }

    /**
     * @param ultimoDatoConsultaAnterior the ultimoDatoConsultaAnterior to set
     */
    public void setUltimoDatoConsultaAnterior(final Integer ultimoDatoConsultaAnterior) {
        this.ultimoDatoConsultaAnterior = ultimoDatoConsultaAnterior;
    }


    public void avanzarIndice() {
        if(this.subtramoList.size() > this.indice+10){
            this.indice+=10;
        }else{
            this.indice=this.subtramoList.size();
        }

    }

    public boolean cargarMasDatos() {
        return this.subtramoList.size() == this.indice && this.masDatos;
    }

    public boolean isPaginacionActiva() {
        return this.subtramoList.size() > this.indice || this.masDatos;
    }

    /**
     * @return the baseCalculoDesc
     */
    public String getBaseCalculoDesc() {
        return baseCalculoDesc;
    }

    /**
     * @param baseCalculoDesc the baseCalculoDesc to set
     */
    public void setBaseCalculoDesc(final String baseCalculoDesc) {
        this.baseCalculoDesc = baseCalculoDesc;
    }


}
