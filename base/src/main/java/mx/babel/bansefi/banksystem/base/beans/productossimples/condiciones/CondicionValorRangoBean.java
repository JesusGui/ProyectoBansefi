package mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import mx.babel.bansefi.banksystem.base.beans.productossimples.CondicionBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;



public class CondicionValorRangoBean extends CondicionBean {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private BigDecimal valor;
    private String unidades;
    private BigDecimal minimo;
    private BigDecimal maximo;
    private BigDecimal incremento;
    private BigDecimal preferente;
    private boolean alternateUnits;

    public CondicionValorRangoBean() {
        super("valorRango");
    }
    /**
     * @return the valor
     */
    public BigDecimal getValor() {
        return valor;
    }
    /**
     * @param valor the valor to set
     */
    public void setValor(final BigDecimal valor) {
        this.valor = valor;
    }
    /**
     * @return the valor
     */
    public Date getValorFecha() {
        if(valor == null){
            return null;
        }
        return formatBigDecimalToDate(valor);
    }
    /**
     * @param valor the valor to set
     */
    public void setValorFecha(final Date valorFecha) {
        final DateFormat formatter = new SimpleDateFormat(
                ConstantesFuncionales.GENERAL_DATE_FORMATTER_INT);
        try {
            this.valor = new BigDecimal(formatter.format(valorFecha));
        } catch (final NumberFormatException e) {
            this.valor = null;
        }
    }

    /**
     * @return the unidades
     */
    public String getUnidades() {
        return unidades;
    }
    /**
     * @param unidades the unidades to set
     */
    public void setUnidades(final String unidades) {
        this.unidades = unidades;
    }

    /**
     * @return the minimo
     */
    public BigDecimal getMinimo() {
        return minimo;
    }
    /**
     * @return the minimo
     */
    public Date getMinimoFecha() {
        if(minimo == null){
            return null;
        }
        return formatBigDecimalToDate(minimo);
    }
    /**
     * @param minimo the minimo to set
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
     * @return the maximo
     */
    public Date getMaximoFecha() {
        if(maximo == null){
            return null;
        }
        return formatBigDecimalToDate(maximo);
    }
    /**
     * @param maximo the maximo to set
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
     * @param incremento the incremento to set
     */
    public void setIncremento(final BigDecimal incremento) {
        this.incremento = incremento;
    }


    /**
     * @return the preferente
     */
    public BigDecimal getPreferente() {
        return preferente;
    }
    /**
     * @return the preferente
     */
    public Date getPreferenteFecha() {
        if(preferente == null){
            return null;
        }
        return formatBigDecimalToDate(preferente);
    }
    /**
     * @param preferente the preferente to set
     */
    public void setPreferente(final BigDecimal preferente) {
        this.preferente = preferente;
    }

    /**
     * @return the alternateUnits
     */
    public boolean isAlternateUnits() {
        return alternateUnits;
    }
    /**
     * @param alternateUnits the alternateUnits to set
     */
    public void setAlternateUnits(final boolean alternateUnits) {
        this.alternateUnits = alternateUnits;
    }

    private Date formatBigDecimalToDate(final BigDecimal value){
        final DateFormat formatter = new SimpleDateFormat(
                ConstantesFuncionales.GENERAL_DATE_FORMATTER_INT);
        try {
            return formatter.parse(Integer.toString(value.intValue()));
        } catch (final ParseException e) {
            return null;
        }
    }
    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().appendSuper(super.hashCode()).append(valor)
                .append(unidades).toHashCode();
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
        if (!(obj instanceof CondicionValorRangoBean)) {
            return false;
        }
        final CondicionValorRangoBean castObj = (CondicionValorRangoBean) obj;
        final EqualsBuilder eq =  new EqualsBuilder().appendSuper(super.equals(obj))
                .append(unidades, castObj.unidades);
        super.compareBigDecimal(eq, valor, castObj.valor);
        return eq.isEquals();

    }

}
