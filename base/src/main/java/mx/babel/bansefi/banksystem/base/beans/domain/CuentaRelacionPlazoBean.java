package mx.babel.bansefi.banksystem.base.beans.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class CuentaRelacionPlazoBean implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 6662463381756919125L;
    private Long numAcuerdo;
	private String tipoRelacion;
    private String tipoRelacionDesc;
	private BigDecimal porcentaje;
	private Date inicio;
	private Date cierre;
	private String codInternoUO;
	private String codOrgApunte;

    private EstadoListadosEnum estadoListado;
    private List<String> cuentasRelacionables;
    private String respaldo;


    public CuentaRelacionPlazoBean() {
        super();
        this.estadoListado = EstadoListadosEnum.INACTIVO;
    }
    /**
     * @return the numAcuerdo
     */
    public Long getNumAcuerdo() {
        return numAcuerdo;
    }
    /**
     * @param numAcuerdo the numAcuerdo to set
     */
    public void setNumAcuerdo(final Long numAcuerdo) {
        this.numAcuerdo = numAcuerdo;
    }
    /**
     * @return the tipoRelacion
     */
    public String getTipoRelacion() {
        return tipoRelacion;
    }
    /**
     * @param tipoRelacion the tipoRelacion to set
     */
    public void setTipoRelacion(final String tipoRelacion) {
        this.tipoRelacion = tipoRelacion;
    }

    /**
     * @return the tipoRelacionDesc
     */
    public String getTipoRelacionDesc() {
        return tipoRelacionDesc;
    }
    /**
     * @param tipoRelacionDesc the tipoRelacionDesc to set
     */
    public void setTipoRelacionDesc(final String tipoRelacionDesc) {
        this.tipoRelacionDesc = tipoRelacionDesc;
    }
    /**
     * @return the porcentaje
     */
    public BigDecimal getPorcentaje() {
        return porcentaje;
    }
    /**
     * @param porcentaje the porcentaje to set
     */
    public void setPorcentaje(final BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }
    /**
     * @return the inicio
     */
    public Date getInicio() {
        return inicio;
    }
    /**
     * @param inicio the inicio to set
     */
    public void setInicio(final Date inicio) {
        this.inicio = inicio;
    }
    /**
     * @return the cierre
     */
    public Date getCierre() {
        return cierre;
    }
    /**
     * @param cierre the cierre to set
     */
    public void setCierre(final Date cierre) {
        this.cierre = cierre;
    }
    /**
     * @return the codInternoUO
     */
    public String getCodInternoUO() {
        return codInternoUO;
    }
    /**
     * @param codInternoUO the codInternoUO to set
     */
    public void setCodInternoUO(final String codInternoUO) {
        this.codInternoUO = codInternoUO;
    }

    /**
     * @return the codOrgApunte
     */
    public String getCodOrgApunte() {
        return codOrgApunte;
    }
    /**
     * @param codOrgApunte the codOrgApunte to set
     */
    public void setCodOrgApunte(final String codOrgApunte) {
        this.codOrgApunte = codOrgApunte;
    }
    /**
     * @return the estadoListado
     */
    public EstadoListadosEnum getEstadoListado() {
        return estadoListado;
    }
    /**
     * @param estadoListado the estadoListado to set
     */
    public void setEstadoListado(final EstadoListadosEnum estadoListado) {
        this.estadoListado = estadoListado;
    }
    /**
     * @return the cuentasRelacionables
     */
    public List<String> getCuentasRelacionables() {
        return cuentasRelacionables;
    }
    /**
     * @param cuentasRelacionables the cuentasRelacionables to set
     */
    public void setCuentasRelacionables(final List<String> cuentasRelacionables) {
        this.cuentasRelacionables = cuentasRelacionables;
    }
    /**
     * @return the respaldo
     */
    public String getRespaldo() {
        return respaldo;
    }
    /**
     * @param respaldo the respaldo to set
     */
    public void setRespaldo(final String respaldo) {
        this.respaldo = respaldo;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
        .append(this.tipoRelacion)
        .append(this.numAcuerdo)
        .append(this.porcentaje)
        .append(this.estadoListado)
        .toHashCode();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CuentaRelacionPlazoBean)) {
            return false;
        }
        final CuentaRelacionPlazoBean castObj = (CuentaRelacionPlazoBean) obj;
        final EqualsBuilder eq = new EqualsBuilder()
        .append(tipoRelacion, castObj.tipoRelacion)
        .append(numAcuerdo, castObj.numAcuerdo)
        .append(estadoListado, castObj.estadoListado);
        this.compareBigDecimal(eq, porcentaje, castObj.porcentaje);
        return eq.isEquals();
    }

    private void compareBigDecimal(final EqualsBuilder eq, final BigDecimal obj1, final BigDecimal obj2){
        if (obj1 != null && obj2 != null) {
            eq.appendSuper(obj1.compareTo(obj2) == 0);
        } else {
            eq.append(obj1, obj2);
        }
    }

}
