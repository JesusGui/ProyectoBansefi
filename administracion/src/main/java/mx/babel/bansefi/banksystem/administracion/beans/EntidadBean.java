package mx.babel.bansefi.banksystem.administracion.beans;
        
import java.io.Serializable;
import java.util.Date;

import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioTipoBean;

public class EntidadBean implements Serializable{

	private static final long serialVersionUID = -8225235440601279960L;

	private String id;
    
    private Date fechaAlta;
    
    private String codigo;
    
    private String nombre;
    
    private String nombreCorto;
    
    private String idOficial;
    
    private String representado;
    
    private String ambito;
    
    private String responsable;
    
    private String coordinador;
    
    private int codDomicilio;
    
    private DomicilioTipoBean domicilio;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    public String getIdOficial() {
        return idOficial;
    }

    public void setIdOficial(String idOficial) {
        this.idOficial = idOficial;
    }

    public String getRepresentado() {
        return representado;
    }

    public void setRepresentado(String representado) {
        this.representado = representado;
    }

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getCoordinador() {
        return coordinador;
    }

    public void setCoordinador(String coordinador) {
        this.coordinador = coordinador;
    }

    public DomicilioTipoBean getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(DomicilioTipoBean domicilio) {
        this.domicilio = domicilio;
    }

    public int getCodDomicilio() {
        return codDomicilio;
    }

    public void setCodDomicilio(int codDomicilio) {
        this.codDomicilio = codDomicilio;
    }
    
    
    
}
