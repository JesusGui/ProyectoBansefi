package mx.babel.bansefi.banksystem.base.beans;

import java.io.Serializable;
import java.util.Date;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.catalogo.beans.CatalogoGeoBean;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;

/**
 * Bean con los atributos para un documento asociado a un cliente.
 * @author luis.gcastellano
 *
 */
public class DocumentoPersonaBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private String id;
    
    private String tipo;
    
    private String tipoDesc;
    
    private String descripcion;
    
    private boolean original;
    
    private boolean copia;
    
    private boolean duplicado;
    
    private boolean noEstandar;
    
    private String soporte;
    
    private String emisor;
    
    private Date fecha;
    
    private String pais;
    
    private CatalogoBean oficina;
    
    private String observaciones;
    
    private String motivo;
    
    private String frecuencia;
    
    private String frecuenciaUnidad;
    
    private Date fechaEntrega;
    
    private Date fechaProxima;
    
    private EstadoListadosEnum estado;
    
    private CatalogoGeoBean municipioCatGeo;

    private String respaldo;
    
    private Date fechaCaducidad;
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isOriginal() {
        return original;
    }

    public void setOriginal(boolean original) {
        this.original = original;
    }

    public boolean isCopia() {
        return copia;
    }

    public void setCopia(boolean copia) {
        this.copia = copia;
    }

    public boolean isDuplicado() {
        return duplicado;
    }

    public void setDuplicado(boolean duplicado) {
        this.duplicado = duplicado;
    }

    public boolean isNoEstandar() {
        return noEstandar;
    }

    public void setNoEstandar(boolean noEstandar) {
        this.noEstandar = noEstandar;
    }

    public String getSoporte() {
        return soporte;
    }

    public void setSoporte(String soporte) {
        this.soporte = soporte;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public CatalogoBean getOficina() {
        return oficina;
    }

    public void setOficina(CatalogoBean oficina) {
        this.oficina = oficina;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getFrecuenciaUnidad() {
        return frecuenciaUnidad;
    }

    public void setFrecuenciaUnidad(String frecuenciaUnidad) {
        this.frecuenciaUnidad = frecuenciaUnidad;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Date getFechaProxima() {
        return fechaProxima;
    }

    public void setFechaProxima(Date fechaProxima) {
        this.fechaProxima = fechaProxima;
    }

    public String getTipoDesc() {
        return tipoDesc;
    }

    public void setTipoDesc(String tipoDesc) {
        this.tipoDesc = tipoDesc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EstadoListadosEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoListadosEnum estado) {
        this.estado = estado;
    }

    public CatalogoGeoBean getMunicipioCatGeo() {
        return municipioCatGeo;
    }

    public void setMunicipioCatGeo(CatalogoGeoBean municipioCatGeo) {
        this.municipioCatGeo = municipioCatGeo;
    }

    public String getRespaldo() {
        return respaldo;
    }

    public void setRespaldo(String respaldo) {
        this.respaldo = respaldo;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

}
