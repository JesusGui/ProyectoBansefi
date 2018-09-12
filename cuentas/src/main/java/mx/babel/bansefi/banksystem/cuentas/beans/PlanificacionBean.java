package mx.babel.bansefi.banksystem.cuentas.beans;

import java.util.Date;

import mx.babel.arq.comun.beans.PaginacionBean;

/**
 * Bean que representa un registro de planificación
 * @author gerard.chavez
 *
 */
public class PlanificacionBean extends PaginacionBean{
    private Date fechaPlanificacion;
    private String codEstado;
    private String descEstado;
    private String codEvento;
    private String descEvento;
    private String codInfoAdicional;
    private String infoAdicional;
 
    public Date getFechaPlanificacion() {
        return fechaPlanificacion;
    }
    public void setFechaPlanificacion(Date fechaPlanificacion) {
        this.fechaPlanificacion = fechaPlanificacion;
    }
    public String getCodEstado() {
        return codEstado;
    }
    public void setCodEstado(String codEstado) {
        this.codEstado = codEstado;
    }
    public String getDescEstado() {
        return descEstado;
    }
    public void setDescEstado(String descEstado) {
        this.descEstado = descEstado;
    }
    public String getCodEvento() {
        return codEvento;
    }
    public void setCodEvento(String codEvento) {
        this.codEvento = codEvento;
    }
    public String getDescEvento() {
        return descEvento;
    }
    public void setDescEvento(String descEvento) {
        this.descEvento = descEvento;
    }
    public String getCodInfoAdicional() {
        return codInfoAdicional;
    }
    public void setCodInfoAdicional(String codInfoAdicional) {
        this.codInfoAdicional = codInfoAdicional;
    }
    public String getInfoAdicional() {
        return infoAdicional;
    }
    public void setInfoAdicional(String infoAdicional) {
        this.infoAdicional = infoAdicional;
    }
  
    
}
