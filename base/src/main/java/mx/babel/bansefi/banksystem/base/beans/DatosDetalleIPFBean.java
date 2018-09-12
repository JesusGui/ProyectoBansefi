package mx.babel.bansefi.banksystem.base.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author joseluis.pena
 *
 */
public class DatosDetalleIPFBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2661694079571534584L;
    private String estado;
    private BigDecimal importe;
    private Date fechaApertura;
    private Date fechaVencimiento;
    private Date fechaProxLiq;
    private Date fechaUltLiq;
    private String trameado;
    private String duracion;
    private String duracionUds;
    private String freqLiquidacion;
    private String freqLiquidacionUds;
    private String interesVCodRefInt;
    private BigDecimal interesVIntValor;
    private BigDecimal interesVIntIncrem;
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
     * @return the trameado
     */
    public String getTrameado() {
        return trameado;
    }
    /**
     * @param trameado the trameado to set
     */
    public void setTrameado(final String trameado) {
        this.trameado = trameado;
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
     * @return the duracionUds
     */
    public String getDuracionUds() {
        return duracionUds;
    }
    /**
     * @param duracionUds the duracionUds to set
     */
    public void setDuracionUds(final String duracionUds) {
        this.duracionUds = duracionUds;
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
    /**
     * @return the freqLiquidacionUds
     */
    public String getFreqLiquidacionUds() {
        return freqLiquidacionUds;
    }
    /**
     * @param freqLiquidacionUds the freqLiquidacionUds to set
     */
    public void setFreqLiquidacionUds(final String freqLiquidacionUds) {
        this.freqLiquidacionUds = freqLiquidacionUds;
    }
    /**
     * @return the interesVCodRefInt
     */
    public String getInteresVCodRefInt() {
        return interesVCodRefInt;
    }
    /**
     * @param interesVCodRefInt the interesVCodRefInt to set
     */
    public void setInteresVCodRefInt(final String interesVCodRefInt) {
        this.interesVCodRefInt = interesVCodRefInt;
    }
    /**
     * @return the interesVIntValor
     */
    public BigDecimal getInteresVIntValor() {
        return interesVIntValor;
    }
    /**
     * @param interesVIntValor the interesVIntValor to set
     */
    public void setInteresVIntValor(final BigDecimal interesVIntValor) {
        this.interesVIntValor = interesVIntValor;
    }
    /**
     * @return the interesVIntIncrem
     */
    public BigDecimal getInteresVIntIncrem() {
        return interesVIntIncrem;
    }
    /**
     * @param interesVIntIncrem the interesVIntIncrem to set
     */
    public void setInteresVIntIncrem(final BigDecimal interesVIntIncrem) {
        this.interesVIntIncrem = interesVIntIncrem;
    }


}
