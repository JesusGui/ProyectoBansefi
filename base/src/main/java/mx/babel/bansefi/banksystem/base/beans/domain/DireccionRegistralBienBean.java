package mx.babel.bansefi.banksystem.base.beans.domain;

import java.io.Serializable;
import java.util.Date;

import mx.babel.arq.catalogo.beans.CatalogoGeoBean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class DireccionRegistralBienBean implements Serializable{

    private static final long serialVersionUID = 2938963859669963965L;

    private String tipoRegistro;
    
    private String numeroRegistro;
    
    private CatalogoGeoBean municipioCatGeo;
    
    private String numeroRegistral;
    
    private String tomo;
    
    private String libro;
    
    private String folio;
    
    private String numeroInscripcion;
    
//    private String fechaExpedicion;
    
    private Date fechaExpedicion;

    public String getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(String tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public String getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(String numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public CatalogoGeoBean getMunicipioCatGeo() {
        return municipioCatGeo;
    }

    public void setMunicipioCatGeo(CatalogoGeoBean municipioCatGeo) {
        this.municipioCatGeo = municipioCatGeo;
    }

    public String getNumeroRegistral() {
        return numeroRegistral;
    }

    public void setNumeroRegistral(String numeroRegistral) {
        this.numeroRegistral = numeroRegistral;
    }

    public String getTomo() {
        return tomo;
    }

    public void setTomo(String tomo) {
        this.tomo = tomo;
    }

    public String getLibro() {
        return libro;
    }

    public void setLibro(String libro) {
        this.libro = libro;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getNumeroInscripcion() {
        return numeroInscripcion;
    }

    public void setNumeroInscripcion(String numeroInscripcion) {
        this.numeroInscripcion = numeroInscripcion;
    }

//    public String getFechaExpedicion() {
//        return fechaExpedicion;
//    }
//
//    public void setFechaExpedicion(String fechaExpedicion) {
//        if(StringUtils.isNotBlank(fechaExpedicion)){
//            @SuppressWarnings("deprecation")
//            Date date = new Date(fechaExpedicion);
////            fechaExpedicion = ConstantesFuncionales.GENERAL_DATE_FORMATTER.format(date);
//            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//            fechaExpedicion = df.format(date);
//        }
//        this.fechaExpedicion = fechaExpedicion;
//    } 
    
    
    
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public Date getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(Date fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }
    
}
