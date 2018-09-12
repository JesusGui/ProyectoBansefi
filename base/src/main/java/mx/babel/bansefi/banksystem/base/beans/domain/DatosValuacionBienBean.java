package mx.babel.bansefi.banksystem.base.beans.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class DatosValuacionBienBean implements Serializable{

    private static final long serialVersionUID = -7475709923027111430L;

    private Double valorDeclarado;
    
    private Date fechaDeclaracion;
    
    private Double valorValuado;
    
    private Date fechaValuacion;
    
    private Date fechaExpiracion;
    
    private String entidadValuadora;
    
    private String usoBien;
    
    private String origenAdquisicion;
    
    private Date fechaAdquisicion;
    
    private BigInteger cantidad;
    
    private Double valor;
    
    private String clase;
    
    private Double domiciliacion;
    
    private String razonAlta;

    public Double getValorDeclarado() {
        return valorDeclarado;
    }

    public void setValorDeclarado(Double valorDeclarado) {
        this.valorDeclarado = valorDeclarado;
    }

    public Date getFechaDeclaracion() {
        return fechaDeclaracion;
    }

    public void setFechaDeclaracion(Date fechaDeclaracion) {
        this.fechaDeclaracion = fechaDeclaracion;
    }

    public Double getValorValuado() {
        return valorValuado;
    }

    public void setValorValuado(Double valorValuado) {
        this.valorValuado = valorValuado;
    }

    public Date getFechaValuacion() {
        return fechaValuacion;
    }

    public void setFechaValuacion(Date fechaValuacion) {
        this.fechaValuacion = fechaValuacion;
    }

    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public String getEntidadValuadora() {
        return entidadValuadora;
    }

    public void setEntidadValuadora(String entidadValuadora) {
        this.entidadValuadora = entidadValuadora;
    }

    public String getUsoBien() {
        return usoBien;
    }

    public void setUsoBien(String usoBien) {
        this.usoBien = usoBien;
    }

    public String getOrigenAdquisicion() {
        return origenAdquisicion;
    }

    public void setOrigenAdquisicion(String origenAdquisicion) {
        this.origenAdquisicion = origenAdquisicion;
    }

    public Date getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(Date fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public BigInteger getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigInteger cantidad) {
        this.cantidad = cantidad;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public Double getDomiciliacion() {
        return domiciliacion;
    }

    public void setDomiciliacion(Double domiciliacion) {
        this.domiciliacion = domiciliacion;
    }

    public String getRazonAlta() {
        return razonAlta;
    }

    public void setRazonAlta(String razonAlta) {
        this.razonAlta = razonAlta;
    }
    
}
