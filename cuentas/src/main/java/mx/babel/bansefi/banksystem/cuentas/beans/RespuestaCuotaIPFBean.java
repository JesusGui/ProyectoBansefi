package mx.babel.bansefi.banksystem.cuentas.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class RespuestaCuotaIPFBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String fechaOperacion;
    private String horaOperacion;
    private String plaza;
    private String sucursal;
    private String titular;
    private Long cuentaDeposito;
    private Integer imposicion;
    private String digito;
    private Integer numCuotas;
    private BigDecimal mensualidad;
    private BigDecimal metaAhorro;
    private BigDecimal total;
    private BigDecimal interes;
    private BigDecimal isr;
    private BigDecimal neto;
    private BigDecimal deposito;
    private BigDecimal saldo;
    private BigDecimal importe;
    /**
     * @return the fechaOperacion
     */
    public String getFechaOperacion() {
        return fechaOperacion;
    }
    /**
     * @param fechaOperacion the fechaOperacion to set
     */
    public void setFechaOperacion(final String fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }
    /**
     * @return the horaOperacion
     */
    public String getHoraOperacion() {
        return horaOperacion;
    }
    /**
     * @param horaOperacion the horaOperacion to set
     */
    public void setHoraOperacion(final String horaOperacion) {
        this.horaOperacion = horaOperacion;
    }
    /**
     * @return the plaza
     */
    public String getPlaza() {
        return plaza;
    }
    /**
     * @param plaza the plaza to set
     */
    public void setPlaza(final String plaza) {
        this.plaza = plaza;
    }
    /**
     * @return the sucursal
     */
    public String getSucursal() {
        return sucursal;
    }
    /**
     * @param sucursal the sucursal to set
     */
    public void setSucursal(final String sucursal) {
        this.sucursal = sucursal;
    }
    /**
     * @return the titular
     */
    public String getTitular() {
        return titular;
    }
    /**
     * @param titular the titular to set
     */
    public void setTitular(final String titular) {
        this.titular = titular;
    }
    /**
     * @return the cuentaDeposito
     */
    public Long getCuentaDeposito() {
        return cuentaDeposito;
    }
    /**
     * @param cuentaDeposito the cuentaDeposito to set
     */
    public void setCuentaDeposito(final Long cuentaDeposito) {
        this.cuentaDeposito = cuentaDeposito;
    }
    /**
     * @return the digito
     */
    public String getDigito() {
        return digito;
    }
    /**
     * @param digito the digito to set
     */
    public void setDigito(final String digito) {
        this.digito = digito;
    }
    /**
     * @return the numCuotas
     */
    public Integer getNumCuotas() {
        return numCuotas;
    }
    /**
     * @param bigDecimal the numCuotas to set
     */
    public void setNumCuotas(final Integer bigDecimal) {
        this.numCuotas = bigDecimal;
    }
    /**
     * @return the mensualidad
     */
    public BigDecimal getMensualidad() {
        return mensualidad;
    }
    /**
     * @param mensualidad the mensualidad to set
     */
    public void setMensualidad(final BigDecimal mensualidad) {
        this.mensualidad = mensualidad;
    }
    /**
     * @return the metaAhorro
     */
    public BigDecimal getMetaAhorro() {
        return metaAhorro;
    }
    /**
     * @param metaAhorro the metaAhorro to set
     */
    public void setMetaAhorro(final BigDecimal metaAhorro) {
        this.metaAhorro = metaAhorro;
    }
    /**
     * @return the interes
     */
    public BigDecimal getInteres() {
        return interes;
    }
    /**
     * @param interes the interes to set
     */
    public void setInteres(final BigDecimal interes) {
        this.interes = interes;
    }
    /**
     * @return the isr
     */
    public BigDecimal getIsr() {
        return isr;
    }
    /**
     * @param isr the isr to set
     */
    public void setIsr(final BigDecimal isr) {
        this.isr = isr;
    }
    /**
     * @return the neto
     */
    public BigDecimal getNeto() {
        return neto;
    }
    /**
     * @param neto the neto to set
     */
    public void setNeto(final BigDecimal neto) {
        this.neto = neto;
    }
    /**
     * @return the deposito
     */
    public BigDecimal getDeposito() {
        return deposito;
    }
    /**
     * @param deposito the deposito to set
     */
    public void setDeposito(final BigDecimal deposito) {
        this.deposito = deposito;
    }
    /**
     * @return the saldo
     */
    public BigDecimal getSaldo() {
        return saldo;
    }
    /**
     * @param saldo the saldo to set
     */
    public void setSaldo(final BigDecimal saldo) {
        this.saldo = saldo;
    }
    /**
     * @return the imposicion
     */
    public Integer getImposicion() {
        return imposicion;
    }
    /**
     * @param imposicion the imposicion to set
     */
    public void setImposicion(final Integer imposicion) {
        this.imposicion = imposicion;
    }
    /**
     * @return the total
     */
    public BigDecimal getTotal() {
        return total;
    }
    /**
     * @param total the total to set
     */
    public void setTotal(final BigDecimal total) {
        this.total = total;
    }
    /**
     * @return the importe
     */
    public BigDecimal getImporte() {
        return importe;
    }
    /**
     * @param importe the importe to set
     */
    public void setImporte(final BigDecimal importe) {
        this.importe = importe;
    }



}
