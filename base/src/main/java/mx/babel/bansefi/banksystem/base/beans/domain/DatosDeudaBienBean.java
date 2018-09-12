package mx.babel.bansefi.banksystem.base.beans.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class DatosDeudaBienBean implements Serializable{

    private static final long serialVersionUID = -2116147209087705803L;

    private String entidad;

    private Double negocio;

    private String tipoDeuda;

    private Double concedido;

    private Double tipoInteres;

    private String frecuenciaAmortizacion;

    private String duracion;

    private Date fechaVencimiento;

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(final String entidad) {
        this.entidad = entidad;
    }

    public Double getNegocio() {
        return negocio;
    }

    public void setNegocio(final Double negocio) {
        this.negocio = negocio;
    }

    public String getNegocioString() {
        if(this.negocio!=null ){
            final java.text.DecimalFormat decimalFormat=new java.text.DecimalFormat("#.#");
            return decimalFormat.format(negocio*1000);
        }
        return null;
    }

    public void setNegocioString(final String negocioString) {
        if(StringUtils.isNotEmpty(negocioString)){
            this.negocio = Double.valueOf(negocioString)/1000;
        }else{
            this.negocio = null;
        }
    }

    public String getTipoDeuda() {
        return tipoDeuda;
    }

    public void setTipoDeuda(final String tipoDeuda) {
        this.tipoDeuda = tipoDeuda;
    }

    public Double getConcedido() {
        return concedido;
    }

    public void setConcedido(final Double concedido) {
        this.concedido = concedido;
    }

    public String getConcedidoString() {
        if(this.concedido!=null ){
            final java.text.DecimalFormat decimalFormat=new java.text.DecimalFormat("#.#");
            return decimalFormat.format(concedido*100);
        }
        return null;
    }

    public void setConcedidoString(final String concedidoString) {
        if(StringUtils.isNotEmpty(concedidoString)){
            this.concedido = Double.valueOf(concedidoString)/100;//cambio de oscar
        }else{
            this.concedido = null;
        }
    }

    public Double getTipoInteres() {
        return tipoInteres;
    }

    public void setTipoInteres(final Double tipoInteres) {
        this.tipoInteres = tipoInteres;
    }

    public String getTipoInteresString() {
        if(this.tipoInteres!=null ){
            final java.text.DecimalFormat decimalFormat=new java.text.DecimalFormat("#.#");
            return decimalFormat.format(tipoInteres*1000);
        }
        return null;
    }

    public void setTipoInteresString(final String tipoInteresString) {
        if(StringUtils.isNotEmpty(tipoInteresString)){
            this.tipoInteres = Double.valueOf(tipoInteresString)/1000;
        }else{
            this.tipoInteres = null;
        }
    }


    public String getFrecuenciaAmortizacion() {
        return frecuenciaAmortizacion;
    }

    public void setFrecuenciaAmortizacion(final String frecuenciaAmortizacion) {
        this.frecuenciaAmortizacion = frecuenciaAmortizacion;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(final String duracion) {
        this.duracion = duracion;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(final Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }


}
