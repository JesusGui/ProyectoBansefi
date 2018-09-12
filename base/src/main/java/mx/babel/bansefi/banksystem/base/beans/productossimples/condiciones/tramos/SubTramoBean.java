package mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.tramos;

import java.io.Serializable;

import mx.babel.bansefi.banksystem.base.beans.productossimples.CondicionBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionRangoBean;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class SubTramoBean implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer numTramo;
    private LimiteTramoBean limiteTramo;
    private CondicionBean condicionBean;
    private Integer numOrdCol;
    private Integer numOrdPres;
    private String udsMedicion;
    private String descripcionCol;
    private String value;

    public SubTramoBean() {
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(final String value) {
        this.value = value;
    }

    /**
     * @return the numTramo
     */
    public Integer getNumTramo() {
        return numTramo;
    }

    /**
     * @param numTramo the numTramo to set
     */
    public void setNumTramo(final Integer numTramo) {
        this.numTramo = numTramo;
    }


    /**
     * @return the condicionBean
     */
    public CondicionBean getCondicionBean() {
        return condicionBean;
    }

    /**
     * @param condicionBean the condicionBean to set
     */
    public void setCondicionBean(final CondicionBean condicionBean) {
        this.condicionBean = condicionBean;
    }

    /**
     * @return the numOrdCol
     */
    public Integer getNumOrdCol() {
        return numOrdCol;
    }

    /**
     * @param numOrdCol the numOrdCol to set
     */
    public void setNumOrdCol(final Integer numOrdCol) {
        this.numOrdCol = numOrdCol;
    }

    /**
     * @return the numOrdPres
     */
    public Integer getNumOrdPres() {
        return numOrdPres;
    }

    /**
     * @param numOrdPres the numOrdPres to set
     */
    public void setNumOrdPres(final Integer numOrdPres) {
        this.numOrdPres = numOrdPres;
    }

    /**
     * @return the udsMedicion
     */
    public String getUdsMedicion() {
        return udsMedicion;
    }

    /**
     * @param udsMedicion the udsMedicion to set
     */
    public void setUdsMedicion(final String udsMedicion) {
        this.udsMedicion = udsMedicion;
    }

    /**
     * @return the descripcionCol
     */
    public String getDescripcionCol() {
        return descripcionCol;
    }

    /**
     * @param descripcionCol the descripcionCol to set
     */
    public void setDescripcionCol(final String descripcionCol) {
        this.descripcionCol = descripcionCol;
    }

    /**
     * @return the limiteTramo
     */
    public LimiteTramoBean getLimiteTramo() {
        return limiteTramo;
    }

    /**
     * @param limiteTramo the limiteTramo to set
     */
    public void setLimiteTramo(final LimiteTramoBean limiteTramo) {
        this.limiteTramo = limiteTramo;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().appendSuper(super.hashCode())
                .append(numTramo).append(condicionBean).append(limiteTramo).toHashCode();
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
        final SubTramoBean castObj = (SubTramoBean) obj;
        return new EqualsBuilder().appendSuper(super.equals(obj))
                .append(numTramo, castObj.numTramo)
                .append(condicionBean, castObj.condicionBean)
                .append(limiteTramo, castObj.limiteTramo).isEquals();
    }

}
