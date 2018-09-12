package mx.babel.bansefi.banksystem.base.beans.domain;

import java.io.Serializable;
import java.util.Date;

public class DatosGeneralesBienBean implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String descripcion;

    private Double valor;

    private Date fecha;

    private Date fechaAntiguedad;

    private Date fechaInicio;

    private Date fechaFin;

    private Double ingresoGastoMensual;

    private Double ingresoGastoAnual;

    private Boolean justificado;

    private Boolean fijo;

    private String estado;

    private Boolean verificado;

    private String matricula;

    private String marca;

    private String modelo;

    private String fabricante;

    private String numeroSerie;

    private Double tonelaje;

    private String numeroLicPesca;

    private String puerto;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaAntiguedad() {
        return fechaAntiguedad;
    }

    public void setFechaAntiguedad(Date fechaAntiguedad) {
        this.fechaAntiguedad = fechaAntiguedad;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Double getIngresoGastoMensual() {
        return ingresoGastoMensual;
    }

    public void setIngresoGastoMensual(Double ingresoGastoMensual) {
        this.ingresoGastoMensual = ingresoGastoMensual;
    }

    public Double getIngresoGastoAnual() {
        return ingresoGastoAnual;
    }

    public void setIngresoGastoAnual(Double ingresoGastoAnual) {
        this.ingresoGastoAnual = ingresoGastoAnual;
    }

    public Boolean getJustificado() {
        return justificado;
    }

    public void setJustificado(Boolean justificado) {
        this.justificado = justificado;
    }

    public Boolean getFijo() {
        return fijo;
    }

    public void setFijo(Boolean fijo) {
        this.fijo = fijo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Boolean getVerificado() {
        return verificado;
    }

    public void setVerificado(Boolean verificado) {
        this.verificado = verificado;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public Double getTonelaje() {
        return tonelaje;
    }

    public void setTonelaje(Double tonelaje) {
        this.tonelaje = tonelaje;
    }

    public String getNumeroLicPesca() {
        return numeroLicPesca;
    }

    public void setNumeroLicPesca(String numeroLicPesca) {
        this.numeroLicPesca = numeroLicPesca;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }
}
