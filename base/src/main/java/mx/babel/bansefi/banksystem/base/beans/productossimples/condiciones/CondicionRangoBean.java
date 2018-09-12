package mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones;

import java.math.BigDecimal;

import mx.babel.bansefi.banksystem.base.beans.productossimples.CondicionBean;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class CondicionRangoBean extends CondicionBean {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private BigDecimal preferente;
    private BigDecimal minimo;
    private BigDecimal maximo;
    private BigDecimal incremento;
    private String unidades;

    public CondicionRangoBean() {
        super("rango");
    }

    /**
     * @return the preferente
     */
    public BigDecimal getPreferente() {
        return preferente;
    }

    /**
     * @param preferente
     *            the preferente to set
     */
    public void setPreferente(final BigDecimal preferente) {
        this.preferente = preferente;
    }

    /**
     * @return the minimo
     */
    public BigDecimal getMinimo() {
        return minimo;
    }

    /**
     * @param minimo
     *            the minimo to set
     */
    public void setMinimo(final BigDecimal minimo) {
        this.minimo = minimo;
    }

    /**
     * @return the maximo
     */
    public BigDecimal getMaximo() {
        return maximo;
    }

    /**
     * @param maximo
     *            the maximo to set
     */
    public void setMaximo(final BigDecimal maximo) {
        this.maximo = maximo;
    }

    /**
     * @return the incremento
     */
    public BigDecimal getIncremento() {
        return incremento;
    }

    /**
     * @param incremento
     *            the incremento to set
     */
    public void setIncremento(final BigDecimal incremento) {
        this.incremento = incremento;
    }

    /**
     * @return the unidades
     */
    public String getUnidades() {
        return unidades;
    }

    /**
     * @param unidades
     *            the unidades to set
     */
    public void setUnidades(final String unidades) {
        this.unidades = unidades;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().appendSuper(super.hashCode())
                .append(incremento).append(maximo).append(minimo)
                .append(preferente).append(unidades).toHashCode();
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
        if (!(obj instanceof CondicionRangoBean)) {
            return false;
        }
        final CondicionRangoBean castObj = (CondicionRangoBean) obj;
        final EqualsBuilder eq = new EqualsBuilder()
        .appendSuper(super.equals(obj)).append(unidades, castObj.unidades);
        super.compareBigDecimal(eq, incremento, castObj.incremento);
        super.compareBigDecimal(eq, maximo, castObj.maximo);
        super.compareBigDecimal(eq, minimo, castObj.minimo);
        super.compareBigDecimal(eq, preferente, castObj.preferente);
        return eq.isEquals();
    }

}
