package mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones;

import java.math.BigDecimal;
import java.util.Date;

import mx.babel.bansefi.banksystem.base.beans.productossimples.CondicionBean;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class CondicionInteresBean extends CondicionBean {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String tipo;
    private String baseCalculoDesc;
    private BigDecimal valorIntFijo;
    private BigDecimal diferencialIntVar;
    private BigDecimal interesMinIntVar;
    private BigDecimal interesMaxIntVar;
    private String frecuenciaRevIntVar;
    private BigDecimal fraccionRevIntVar;
    private BigDecimal porcentajeMinRevIntVar;
    private String codReferenciaIntVar;
    private String codCriterioRevIntVar;
    private String formaRevIntVar;
    private BigDecimal valorActualVar;
    private Date fechaRevVar;

    private String estructuraIdPds;
    private String estructuraParmCd;


    private BigDecimal intFijoMin;
    private BigDecimal intFijoMax;
    private BigDecimal intFijoIncremento;
    private BigDecimal intFijoPreferente;

    public CondicionInteresBean() {
        super("interes");
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

    /**
     * @return the valorIntFijo
     */
    public BigDecimal getValorIntFijo() {
        return valorIntFijo;
    }

    /**
     * @param valorIntFijo
     *            the valorIntFijo to set
     */
    public void setValorIntFijo(final BigDecimal valorIntFijo) {
        this.valorIntFijo = valorIntFijo;
    }

    /**
     * @return the diferencialIntVar
     */
    public BigDecimal getDiferencialIntVar() {
        return diferencialIntVar;
    }

    /**
     * @param diferencialIntVar
     *            the diferencialIntVar to set
     */
    public void setDiferencialIntVar(final BigDecimal diferencialIntVar) {
        this.diferencialIntVar = diferencialIntVar;
    }

    /**
     * @return the interesMinIntVar
     */
    public BigDecimal getInteresMinIntVar() {
        return interesMinIntVar;
    }

    /**
     * @param interesMinIntVar
     *            the interesMinIntVar to set
     */
    public void setInteresMinIntVar(final BigDecimal interesMinIntVar) {
        this.interesMinIntVar = interesMinIntVar;
    }

    /**
     * @return the interesMaxIntVar
     */
    public BigDecimal getInteresMaxIntVar() {
        return interesMaxIntVar;
    }

    /**
     * @param interesMaxIntVar
     *            the interesMaxIntVar to set
     */
    public void setInteresMaxIntVar(final BigDecimal interesMaxIntVar) {
        this.interesMaxIntVar = interesMaxIntVar;
    }

    /**
     * @return the frecuenciaRevIntVar
     */
    public String getFrecuenciaRevIntVar() {
        return frecuenciaRevIntVar;
    }

    /**
     * @param frecuenciaRevIntVar
     *            the frecuenciaRevIntVar to set
     */
    public void setFrecuenciaRevIntVar(final String frecuenciaRevIntVar) {
        this.frecuenciaRevIntVar = frecuenciaRevIntVar;
    }

    /**
     * @return the fraccionRevIntVar
     */
    public BigDecimal getFraccionRevIntVar() {
        return fraccionRevIntVar;
    }

    /**
     * @param fraccionRevIntVar
     *            the fraccionRevIntVar to set
     */
    public void setFraccionRevIntVar(final BigDecimal fraccionRevIntVar) {
        this.fraccionRevIntVar = fraccionRevIntVar;
    }

    /**
     * @return the porcentajeMinRevIntVar
     */
    public BigDecimal getPorcentajeMinRevIntVar() {
        return porcentajeMinRevIntVar;
    }

    /**
     * @param porcentajeMinRevIntVar
     *            the porcentajeMinRevIntVar to set
     */
    public void setPorcentajeMinRevIntVar(
            final BigDecimal porcentajeMinRevIntVar) {
        this.porcentajeMinRevIntVar = porcentajeMinRevIntVar;
    }

    /**
     * @return the codReferenciaIntVar
     */
    public String getCodReferenciaIntVar() {
        return codReferenciaIntVar;
    }

    /**
     * @param codReferenciaIntVar
     *            the codReferenciaIntVar to set
     */
    public void setCodReferenciaIntVar(final String codReferenciaIntVar) {
        this.codReferenciaIntVar = codReferenciaIntVar;
    }

    /**
     * @return the codCriterioRevIntVar
     */
    public String getCodCriterioRevIntVar() {
        return codCriterioRevIntVar;
    }

    /**
     * @param codCriterioRevIntVar
     *            the codCriterioRevIntVar to set
     */
    public void setCodCriterioRevIntVar(final String codCriterioRevIntVar) {
        this.codCriterioRevIntVar = codCriterioRevIntVar;
    }

    /**
     * @return the formaRevIntVar
     */
    public String getFormaRevIntVar() {
        return formaRevIntVar;
    }

    /**
     * @param formaRevIntVar
     *            the formaRevIntVar to set
     */
    public void setFormaRevIntVar(final String formaRevIntVar) {
        this.formaRevIntVar = formaRevIntVar;
    }

    /**
     * @return the valorActualVar
     */
    public BigDecimal getValorActualVar() {
        return valorActualVar;
    }

    /**
     * @param valorActualVar the valorActualVar to set
     */
    public void setValorActualVar(final BigDecimal valorActualVar) {
        this.valorActualVar = valorActualVar;
    }

    /**
     * @return the fechaRevVar
     */
    public Date getFechaRevVar() {
        return fechaRevVar;
    }

    /**
     * @param fechaRevVar the fechaRevVar to set
     */
    public void setFechaRevVar(final Date fechaRevVar) {
        this.fechaRevVar = fechaRevVar;
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
     * @return the intFijoMin
     */
    public BigDecimal getIntFijoMin() {
        return intFijoMin;
    }

    /**
     * @param intpFijoMin the intpFijoMin to set
     */
    public void setIntFijoMin(final BigDecimal intFijoMin) {
        this.intFijoMin = intFijoMin;
    }

    /**
     * @return the intFijoMax
     */
    public BigDecimal getIntFijoMax() {
        return intFijoMax;
    }

    /**
     * @param intFijoMax the intFijoMax to set
     */
    public void setIntFijoMax(final BigDecimal intFijoMax) {
        this.intFijoMax = intFijoMax;
    }

    /**
     * @return the intFijoIncremento
     */
    public BigDecimal getIntFijoIncremento() {
        return intFijoIncremento;
    }

    /**
     * @param intFijoIncremento the intFijoIncremento to set
     */
    public void setIntFijoIncremento(final BigDecimal intFijoIncremento) {
        this.intFijoIncremento = intFijoIncremento;
    }

    /**
     * @return the intFijoPreferente
     */
    public BigDecimal getIntFijoPreferente() {
        return intFijoPreferente;
    }

    /**
     * @param intFijoPreferente the intFijoPreferente to set
     */
    public void setIntFijoPreferente(final BigDecimal intFijoPreferente) {
        this.intFijoPreferente = intFijoPreferente;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().appendSuper(super.hashCode()).append(tipo)
                .append(valorIntFijo).append(diferencialIntVar)
                .append(interesMinIntVar).append(interesMaxIntVar)
                .append(frecuenciaRevIntVar).append(fraccionRevIntVar)
                .append(porcentajeMinRevIntVar).append(codReferenciaIntVar)
                .append(codCriterioRevIntVar).append(formaRevIntVar)
                .append(valorActualVar).append(fechaRevVar)
                .toHashCode();
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
        if (!(obj instanceof CondicionInteresBean)) {
            return false;
        }
        final CondicionInteresBean castObj = (CondicionInteresBean) obj;
        final EqualsBuilder eq = new EqualsBuilder().appendSuper(
                super.equals(obj)).append(tipo, castObj.tipo)
                .append(frecuenciaRevIntVar, castObj.frecuenciaRevIntVar)
                .append(codReferenciaIntVar, castObj.codReferenciaIntVar)
                .append(codCriterioRevIntVar, castObj.codCriterioRevIntVar)
                .append(formaRevIntVar, castObj.formaRevIntVar)
                .append(fechaRevVar, castObj.fechaRevVar);
        super.compareBigDecimal(eq, valorIntFijo, castObj.valorIntFijo);
        super.compareBigDecimal(eq, diferencialIntVar,
                castObj.diferencialIntVar);
        super.compareBigDecimal(eq, interesMinIntVar, castObj.interesMinIntVar);
        super.compareBigDecimal(eq, interesMaxIntVar, castObj.interesMaxIntVar);
        super.compareBigDecimal(eq, fraccionRevIntVar,
                castObj.fraccionRevIntVar);
        super.compareBigDecimal(eq, porcentajeMinRevIntVar,
                castObj.porcentajeMinRevIntVar);
        super.compareBigDecimal(eq, valorActualVar,
                castObj.valorActualVar);
        return eq.isEquals();
    }

}
