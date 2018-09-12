package mx.babel.bansefi.banksystem.base.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.babel.bansefi.banksystem.base.beans.productossimples.ProductoSimpleBean;

/**
 * @author joseluis.pena
 *
 */
public class DepositoIPFBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2661694079571534584L;
    private Long numAcuerdo;
    private Integer numSubAc;
    private String linea;
    private String grupo;
    private String nombre;
    private String descripcion;
    private String estado;
    private BigDecimal importe;
    private String moneda;
    private String tipoCargo;
    private BigDecimal saldo;
    private Date fechaApertura;
    private Date fechaVencimiento;
    private Date fechaProxLiq;
    private Date fechaUltLiq;
    private String tipoInteres;
    private String duracion;
    private String freqLiquidacion;


    private List<ProductoSimpleBean> productosSimples;


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
     * @return the linea
     */
    public String getLinea() {
        return linea;
    }


    /**
     * @param linea the linea to set
     */
    public void setLinea(final String linea) {
        this.linea = linea;
    }


    /**
     * @return the grupo
     */
    public String getGrupo() {
        return grupo;
    }


    /**
     * @param grupo the grupo to set
     */
    public void setGrupo(final String grupo) {
        this.grupo = grupo;
    }


    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }


    /**
     * @param nombre the nombre to set
     */
    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }


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
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }


    /**
     * @param estado the estado to set
     */
    public void setEstado(final String estado) {
        this.estado = estado;
    }


    /**
     * @return the productosSimples
     */
    public List<ProductoSimpleBean> getProductosSimples() {
        return productosSimples;
    }


    /**
     * @param productosSimples the productosSimples to set
     */
    public void setProductosSimples(final List<ProductoSimpleBean> productosSimples) {
        this.productosSimples = productosSimples;
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


    /**
     * @return the moneda
     */
    public String getMoneda() {
        return moneda;
    }


    /**
     * @param moneda the moneda to set
     */
    public void setMoneda(final String moneda) {
        this.moneda = moneda;
    }


    /**
     * @return the tipoCargo
     */
    public String getTipoCargo() {
        return tipoCargo;
    }


    /**
     * @param tipoCargo the tipoCargo to set
     */
    public void setTipoCargo(final String tipoCargo) {
        this.tipoCargo = tipoCargo;
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
     * @return the fechaApertura
     */
    public Date getFechaApertura() {
        return fechaApertura;
    }


    /**
     * @param fechaApertura the fechaApertura to set
     */
    public void setFechaApertura(final Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }


    /**
     * @return the fechaVencimiento
     */
    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }


    /**
     * @param fechaVencimiento the fechaVencimiento to set
     */
    public void setFechaVencimiento(final Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }


    /**
     * @return the fechaProxLiq
     */
    public Date getFechaProxLiq() {
        return fechaProxLiq;
    }


    /**
     * @param fechaProxLiq the fechaProxLiq to set
     */
    public void setFechaProxLiq(final Date fechaProxLiq) {
        this.fechaProxLiq = fechaProxLiq;
    }


    /**
     * @return the fechaUltLiq
     */
    public Date getFechaUltLiq() {
        return fechaUltLiq;
    }


    /**
     * @param fechaUltLiq the fechaUltLiq to set
     */
    public void setFechaUltLiq(final Date fechaUltLiq) {
        this.fechaUltLiq = fechaUltLiq;
    }


    /**
     * @return the tipoInteres
     */
    public String getTipoInteres() {
        return tipoInteres;
    }


    /**
     * @param tipoInteres the tipoInteres to set
     */
    public void setTipoInteres(final String tipoInteres) {
        this.tipoInteres = tipoInteres;
    }


    /**
     * @return the duracion
     */
    public String getDuracion() {
        return duracion;
    }


    /**
     * @param duracion the duracion to set
     */
    public void setDuracion(final String duracion) {
        this.duracion = duracion;
    }


    /**
     * @return the freqLiquidacion
     */
    public String getFreqLiquidacion() {
        return freqLiquidacion;
    }


    /**
     * @param freqLiquidacion the freqLiquidacion to set
     */
    public void setFreqLiquidacion(final String freqLiquidacion) {
        this.freqLiquidacion = freqLiquidacion;
    }


}
