package mx.babel.bansefi.banksystem.cuentas.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import mx.babel.bansefi.banksystem.cuentas.enums.TipoCargoEnum;

public class CuotaIPFBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String descripcion;
    private Long numAcuerdo;
    private Integer numSubAc;
    private BigDecimal importeMinimo;
    private BigDecimal importeDeposito;
    private TipoCargoEnum tipoCargo;
    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
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
     * @return the numSubAc
     */
    public Integer getNumSubAc() {
        return numSubAc;
    }
    /**
     * @param numSubAc the numSubAc to set
     */
    public void setNumSubAc(final Integer numSubAc) {
        this.numSubAc = numSubAc;
    }
    /**
     * @return the importeMinimo
     */
    public BigDecimal getImporteMinimo() {
        return importeMinimo;
    }
    /**
     * @param importeMinimo the importeMinimo to set
     */
    public void setImporteMinimo(final BigDecimal importeMinimo) {
        this.importeMinimo = importeMinimo;
    }
    /**
     * @return the importeDeposito
     */
    public BigDecimal getImporteDeposito() {
        return importeDeposito;
    }
    /**
     * @param importeDeposito the importeDeposito to set
     */
    public void setImporteDeposito(final BigDecimal importeDeposito) {
        this.importeDeposito = importeDeposito;
    }
    /**
     * @return the tipoCargo
     */
    public TipoCargoEnum getTipoCargo() {
        return tipoCargo;
    }
    /**
     * @param tipoCargo the tipoCargo to set
     */
    public void setTipoCargo(final TipoCargoEnum tipoCargo) {
        this.tipoCargo = tipoCargo;
    }

}
