package mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones;

import java.math.BigDecimal;

import mx.babel.bansefi.banksystem.base.beans.productossimples.CondicionBean;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class CondicionComisionBean extends CondicionBean {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String tipo;
    private BigDecimal importeComFija;
    private String formaCalculoComFija;
    private BigDecimal importeMinComVar;
    private BigDecimal importeMaxComVar;
    private String baseCalculoComVar;
    private String baseCalculoComVarDesc;
    private BigDecimal porcentajeComisionComVar;
    private BigDecimal importeFranquiciaComVar;
    private String agruClop;


    private String estructuraIdPds;
    private String estructuraParmCd;


    private BigDecimal impFijoMin;
    private BigDecimal impFijoMax;
    private BigDecimal impFijoIncremento;
    private BigDecimal impFijoPreferente;

    public CondicionComisionBean() {
        super("comision");
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo
     *            the tipo to set
     */
    public void setTipo(final String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the importeComFija
     */
    public BigDecimal getImporteComFija() {
        return importeComFija;
    }

    /**
     * @param importeComFija
     *            the importeComFija to set
     */
    public void setImporteComFija(final BigDecimal importeComFija) {
        this.importeComFija = importeComFija;
    }

    /**
     * @return the formaCalculoComFija
     */
    public String getFormaCalculoComFija() {
        return formaCalculoComFija;
    }

    /**
     * @param formaCalculoComFija
     *            the formaCalculoComFija to set
     */
    public void setFormaCalculoComFija(final String formaCalculoComFija) {
        this.formaCalculoComFija = formaCalculoComFija;
    }

    /**
     * @return the importeMinComVar
     */
    public BigDecimal getImporteMinComVar() {
        return importeMinComVar;
    }

    /**
     * @param importeMinComVar
     *            the importeMinComVar to set
     */
    public void setImporteMinComVar(final BigDecimal importeMinComVar) {
        this.importeMinComVar = importeMinComVar;
    }

    /**
     * @return the importeMaxComVar
     */
    public BigDecimal getImporteMaxComVar() {
        return importeMaxComVar;
    }

    /**
     * @param importeMaxComVar
     *            the importeMaxComVar to set
     */
    public void setImporteMaxComVar(final BigDecimal importeMaxComVar) {
        this.importeMaxComVar = importeMaxComVar;
    }

    /**
     * @return the baseCalculoComVar
     */
    public String getBaseCalculoComVar() {
        return baseCalculoComVar;
    }

    /**
     * @param baseCalculoComVar
     *            the baseCalculoComVar to set
     */
    public void setBaseCalculoComVar(final String baseCalculoComVar) {
        this.baseCalculoComVar = baseCalculoComVar;
    }

    /**
     * @return the baseCalculoComVarDesc
     */
    public String getBaseCalculoComVarDesc() {
        return baseCalculoComVarDesc;
    }

    /**
     * @param baseCalculoComVarDesc the baseCalculoComVarDesc to set
     */
    public void setBaseCalculoComVarDesc(final String baseCalculoComVarDesc) {
        this.baseCalculoComVarDesc = baseCalculoComVarDesc;
    }

    /**
     * @return the porcentajeComisionComVar
     */
    public BigDecimal getPorcentajeComisionComVar() {
        return porcentajeComisionComVar;
    }

    /**
     * @param porcentajeComisionComVar
     *            the porcentajeComisionComVar to set
     */
    public void setPorcentajeComisionComVar(
            final BigDecimal porcentajeComisionComVar) {
        this.porcentajeComisionComVar = porcentajeComisionComVar;
    }

    /**
     * @return the importeFranquiciaComVar
     */
    public BigDecimal getImporteFranquiciaComVar() {
        return importeFranquiciaComVar;
    }

    /**
     * @param importeFranquiciaComVar
     *            the importeFranquiciaComVar to set
     */
    public void setImporteFranquiciaComVar(
            final BigDecimal importeFranquiciaComVar) {
        this.importeFranquiciaComVar = importeFranquiciaComVar;
    }

    /**
     * @return the agruClop
     */
    public String getAgruClop() {
        return agruClop;
    }

    /**
     * @param agruClop the agruClop to set
     */
    public void setAgruClop(final String agruClop) {
        this.agruClop = agruClop;
    }

    /**
     * @return the estructuraIdPds
     */
    public String getEstructuraIdPds() {
        return estructuraIdPds;
    }

    /**
     * @param estructuraIdPds the estructuraIdPds to set
     */
    public void setEstructuraIdPds(final String estructuraIdPds) {
        this.estructuraIdPds = estructuraIdPds;
    }

    /**
     * @return the estructuraParmCd
     */
    public String getEstructuraParmCd() {
        return estructuraParmCd;
    }

    /**
     * @param estructuraParmCd the estructuraParmCd to set
     */
    public void setEstructuraParmCd(final String estructuraParmCd) {
        this.estructuraParmCd = estructuraParmCd;
    }

    /**
     * @return the impFijoMin
     */
    public BigDecimal getImpFijoMin() {
        return impFijoMin;
    }

    /**
     * @param impFijoMin the impFijoMin to set
     */
    public void setImpFijoMin(final BigDecimal impFijoMin) {
        this.impFijoMin = impFijoMin;
    }

    /**
     * @return the impFijoMax
     */
    public BigDecimal getImpFijoMax() {
        return impFijoMax;
    }

    /**
     * @param impFijoMax the impFijoMax to set
     */
    public void setImpFijoMax(final BigDecimal impFijoMax) {
        this.impFijoMax = impFijoMax;
    }

    /**
     * @return the impFijoIncremento
     */
    public BigDecimal getImpFijoIncremento() {
        return impFijoIncremento;
    }

    /**
     * @param impFijoIncremento the impFijoIncremento to set
     */
    public void setImpFijoIncremento(final BigDecimal impFijoIncremento) {
        this.impFijoIncremento = impFijoIncremento;
    }

    /**
     * @return the impFijoPreferente
     */
    public BigDecimal getImpFijoPreferente() {
        return impFijoPreferente;
    }

    /**
     * @param impFijoPreferente the impFijoPreferente to set
     */
    public void setImpFijoPreferente(final BigDecimal impFijoPreferente) {
        this.impFijoPreferente = impFijoPreferente;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        return new HashCodeBuilder().appendSuper(super.hashCode()).append(tipo)
                .append(importeComFija).append(formaCalculoComFija)
                .append(importeMinComVar).append(importeMaxComVar)
                .append(baseCalculoComVar).append(porcentajeComisionComVar)
                .append(importeFranquiciaComVar).append(agruClop).toHashCode();
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
        if (!(obj instanceof CondicionComisionBean)) {
            return false;
        }
        final CondicionComisionBean castObj = (CondicionComisionBean) obj;
        final EqualsBuilder eq = new EqualsBuilder()
                .appendSuper(super.equals(obj)).append(tipo, castObj.tipo)
                .append(formaCalculoComFija, castObj.formaCalculoComFija)
                .append(baseCalculoComVar, castObj.baseCalculoComVar)
                .append(agruClop, castObj.agruClop);
        super.compareBigDecimal(eq, importeComFija, castObj.importeComFija);
        super.compareBigDecimal(eq, importeMinComVar, castObj.importeMinComVar);
        super.compareBigDecimal(eq, importeMaxComVar, castObj.importeMaxComVar);
        super.compareBigDecimal(eq, porcentajeComisionComVar, castObj.porcentajeComisionComVar);
        super.compareBigDecimal(eq, importeFranquiciaComVar, castObj.importeFranquiciaComVar);
        return eq.isEquals();
    }

}
