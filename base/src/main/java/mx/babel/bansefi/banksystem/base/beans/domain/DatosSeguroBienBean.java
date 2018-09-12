package mx.babel.bansefi.banksystem.base.beans.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class DatosSeguroBienBean implements Serializable{

    private static final long serialVersionUID = 3402872227716588410L;

    private String tipoSeguro;

    private String numeroSeguro;

    private String cif;

    private String ciaAseguradora;

    private String numeroPoliza;

    private String beneficiario;

    private String contingencia;

    private Double primaAnual;

    private Double cobertura;

    private Date fechaInicioCobertura;

    private Date fechaFinCobertura;

    private Date fechaAntiguedad;

    public String getTipoSeguro() {
        return tipoSeguro;
    }

    public void setTipoSeguro(final String tipoSeguro) {
        this.tipoSeguro = tipoSeguro;
    }

    public String getNumeroSeguro() {
        return numeroSeguro;
    }

    public void setNumeroSeguro(final String numeroSeguro) {
        this.numeroSeguro = numeroSeguro;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(final String cif) {
        this.cif = cif;
    }

    public String getCiaAseguradora() {
        return ciaAseguradora;
    }

    public void setCiaAseguradora(final String ciaAseguradora) {
        this.ciaAseguradora = ciaAseguradora;
    }

    public String getNumeroPoliza() {
        return numeroPoliza;
    }

    public void setNumeroPoliza(final String numeroPoliza) {
        this.numeroPoliza = numeroPoliza;
    }

    public String getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(final String beneficiario) {
        this.beneficiario = beneficiario;
    }

    public String getContingencia() {
        return contingencia;
    }

    public void setContingencia(final String contingencia) {
        this.contingencia = contingencia;
    }

    public Double getPrimaAnual() {
        return primaAnual;
    }

    public void setPrimaAnual(final Double primaAnual) {
        this.primaAnual = primaAnual;
    }

    public String getPrimaAnualString() {
        if(this.primaAnual!=null ){
            final java.text.DecimalFormat decimalFormat=new java.text.DecimalFormat("#.#");
            return decimalFormat.format(primaAnual*100);
        }
        return null;
    }

    public void setPrimaAnualString(final String primaAnualString) {
        if(StringUtils.isNotEmpty(primaAnualString)){
            this.primaAnual = Double.valueOf(primaAnualString)/100;
        }else{
            this.primaAnual = null;
        }
    }
    public Double getCobertura() {
        return cobertura;
    }

    public void setCobertura(final Double cobertura) {
        this.cobertura = cobertura;
    }

    public String getCoberturaString() {
        if(this.cobertura!=null ){
            final java.text.DecimalFormat decimalFormat=new java.text.DecimalFormat("#.#");
            return decimalFormat.format(cobertura*1000);
        }
        return null;
    }

    public void setCoberturaString(final String coberturaString) {
        if(StringUtils.isNotEmpty(coberturaString)){
            this.cobertura = Double.valueOf(coberturaString)/1000;
        }else{
            this.cobertura = null;
        }
    }

    public Date getFechaInicioCobertura() {
        return fechaInicioCobertura;
    }

    public void setFechaInicioCobertura(final Date fechaInicioCobertura) {
        this.fechaInicioCobertura = fechaInicioCobertura;
    }

    public Date getFechaFinCobertura() {
        return fechaFinCobertura;
    }

    public void setFechaFinCobertura(final Date fechaFinCobertura) {
        this.fechaFinCobertura = fechaFinCobertura;
    }

    public Date getFechaAntiguedad() {
        return fechaAntiguedad;
    }

    public void setFechaAntiguedad(final Date fechaAntiguedad) {
        this.fechaAntiguedad = fechaAntiguedad;
    }



}
