package mx.babel.bansefi.banksystem.base.beans.domain;

import java.io.Serializable;
import java.text.DecimalFormat;

import org.apache.commons.lang3.StringUtils;

public class DatosTerrenoBienBean implements Serializable{

    private static final long serialVersionUID = -1226751431716239330L;

    private final static String CTE_FORMATO = "#,##0.00";

    private final static String CTE_PUNTO = ".";

    private final static String CTE_COMA = ",";

    private final static String CTE_GUION = "-";

    private final static Double CTE_100 = 100.0;

    private Double superficieConstruida;

    private String superficieConstruidaString;

    private String superficieConstruidaUnidad;

    private Double superficieTerreno;

    private String superficieTerrenoString;

    private String superficieTerrenoUnidad;

    private String carga;

    private Boolean primeraVivienda;

    private Boolean primeraHipoteca;

    private String tipoTerreno;

    private String tipoCultivo;

    private String partida;

    private Double horasRegadio;

    private Double otras;

    private Double superficieInvernadero;

    private String superficieInvernaderoString;

    private String superficieInvernaderoUnidad;

    private String nombreFinca;

    public Double getSuperficieConstruida() {

        if(StringUtils.isNotBlank(this.superficieConstruidaString)){
            final String[] superficie = this.superficieConstruidaString.split(DatosTerrenoBienBean.CTE_GUION);
            if(superficie!=null && superficie[0]!=null){
                final Double valor = Double.valueOf(superficie[0]);
                if(valor!=0){
                    return (Double.valueOf(superficie[0]))/DatosTerrenoBienBean.CTE_100;
                }else{
                    return 0.0;
                }
            }
            return null;
        }

        return null;
    }

    public void setSuperficieConstruida(final Double superficieConstruida) {
        this.superficieConstruida = superficieConstruida;
    }

    public String getSuperficieConstruidaUnidad() {
        if(StringUtils.isNotBlank(this.superficieConstruidaString)){
            final String[] superficie = this.superficieConstruidaString.split(DatosTerrenoBienBean.CTE_GUION);
            if(superficie!=null && superficie[1]!=null){
                return superficie[1];
            }
            return null;
        }

        return null;
    }

    public void setSuperficieConstruidaUnidad(final String superficieConstruidaUnidad) {
        this.superficieConstruidaUnidad = superficieConstruidaUnidad;
    }

    public Double getSuperficieTerreno() {
        if(StringUtils.isNotBlank(this.superficieTerrenoString)){
            final String[] superficie = this.superficieTerrenoString.split(DatosTerrenoBienBean.CTE_GUION);
            if(superficie!=null && superficie[0]!=null){
                final Double valor = Double.valueOf(superficie[0]);
                if(valor!=0){
                    return (Double.valueOf(superficie[0]))/DatosTerrenoBienBean.CTE_100;
                }else{
                    return 0.0;
                }
            }
            return null;
        }

        return null;
    }

    public void setSuperficieTerreno(final Double superficieTerreno) {
        this.superficieTerreno = superficieTerreno;
    }






    public String getSuperficieTerrenoUnidad() {
        if(StringUtils.isNotBlank(this.superficieTerrenoString)){
            final String[] superficie = this.superficieTerrenoString.split(DatosTerrenoBienBean.CTE_GUION);
            if(superficie!=null && superficie[1]!=null){
                return superficie[1];
            }
            return null;
        }

        return null;

    }

    public void setSuperficieTerrenoUnidad(final String superficieTerrenoUnidad) {
        this.superficieTerrenoUnidad = superficieTerrenoUnidad;
    }

    public String getCarga() {
        return carga;
    }

    public void setCarga(final String carga) {
        this.carga = carga;
    }

    public Boolean getPrimeraVivienda() {
        return primeraVivienda;
    }

    public void setPrimeraVivienda(final Boolean primeraVivienda) {
        this.primeraVivienda = primeraVivienda;
    }

    public Boolean getPrimeraHipoteca() {
        return primeraHipoteca;
    }

    public void setPrimeraHipoteca(final Boolean primeraHipoteca) {
        this.primeraHipoteca = primeraHipoteca;
    }

    public String getTipoTerreno() {
        return tipoTerreno;
    }

    public void setTipoTerreno(final String tipoTerreno) {
        this.tipoTerreno = tipoTerreno;
    }

    public String getTipoCultivo() {
        return tipoCultivo;
    }

    public void setTipoCultivo(final String tipoCultivo) {
        this.tipoCultivo = tipoCultivo;
    }

    public String getPartida() {
        return partida;
    }

    public void setPartida(final String partida) {
        this.partida = partida;
    }

    public Double getHorasRegadio() {
        return horasRegadio;
    }

    public void setHorasRegadio(final Double horasRegadio) {
        this.horasRegadio = horasRegadio;
    }

    public String getHorasRegadioString() {
        if(this.horasRegadio!=null && !(this.horasRegadio.compareTo(0.0)==0)){
            final java.text.DecimalFormat decimalFormat=new java.text.DecimalFormat("#.#");
            return decimalFormat.format(horasRegadio*100);
        }
        return null;
    }

    public void setHorasRegadioString(final String horasRegadioString) {
        if(StringUtils.isNotEmpty(horasRegadioString)){
            this.horasRegadio = Double.valueOf(horasRegadioString)/100;
        }else{
            this.horasRegadio = null;
        }
    }

    public Double getOtras() {
        return otras;
    }

    public void setOtras(final Double otras) {
        this.otras = otras;
    }

    public String getOtrasString() {
        if(this.otras!=null  && !(this.otras.compareTo(0.0)==0)){
            final java.text.DecimalFormat decimalFormat=new java.text.DecimalFormat("#.#");
            return decimalFormat.format(otras*100);
        }
        return null;
    }

    public void setOtrasString(final String otrasString) {
        if(StringUtils.isNotEmpty(otrasString)){
            this.otras = Double.valueOf(otrasString)/100;
        }else{
            this.otras = null;
        }
    }

    public Double getSuperficieInvernadero() {
        if(StringUtils.isNotBlank(this.superficieInvernaderoString)){
            final String[] superficie = this.superficieInvernaderoString.split(DatosTerrenoBienBean.CTE_GUION);
            if(superficie!=null && superficie[0]!=null){
                final Double valor = Double.valueOf(superficie[0]);
                if(valor!=0){
                    return (Double.valueOf(superficie[0]))/DatosTerrenoBienBean.CTE_100;
                }else{
                    return 0.0;
                }
            }
            return null;
        }

        return null;
    }

    public void setSuperficieInvernadero(final Double superficieInvernadero) {
        this.superficieInvernadero = superficieInvernadero;
    }

    public String getSuperficieInvernaderoUnidad() {
        if(StringUtils.isNotBlank(this.superficieInvernaderoString)){
            final String[] superficie = this.superficieInvernaderoString.split(DatosTerrenoBienBean.CTE_GUION);
            if(superficie!=null && superficie[1]!=null){
                return superficie[1];
            }
            return null;
        }

        return null;
    }

    public void setSuperficieInvernaderoUnidad(final String superficieInvernaderoUnidad) {
        this.superficieInvernaderoUnidad = superficieInvernaderoUnidad;
    }

    public String getNombreFinca() {
        return nombreFinca;
    }

    public void setNombreFinca(final String nombreFinca) {
        this.nombreFinca = nombreFinca;
    }

    public String getSuperficieConstruidaString() {
        if(this.superficieConstruida!=null && StringUtils.isNotBlank(this.superficieConstruidaUnidad)){
            final DecimalFormat decimalFormat = new DecimalFormat(DatosTerrenoBienBean.CTE_FORMATO);
            String superficie = StringUtils.leftPad(decimalFormat.format(superficieConstruida).replace(DatosTerrenoBienBean.CTE_PUNTO, "").replace(DatosTerrenoBienBean.CTE_COMA, ""),7,"0");
            return superficie + DatosTerrenoBienBean.CTE_GUION + this.superficieConstruidaUnidad;
        }
        return null;
    }

    public void setSuperficieConstruidaString(final String superficieConstruidaString) {
        this.superficieConstruidaString = superficieConstruidaString;
    }

    public String getSuperficieTerrenoString() {
        if(this.superficieTerreno!=null && StringUtils.isNotBlank(this.superficieTerrenoUnidad)){
            final DecimalFormat decimalFormat = new DecimalFormat(DatosTerrenoBienBean.CTE_FORMATO);
            String superficie = StringUtils.leftPad(decimalFormat.format(superficieTerreno).replace(DatosTerrenoBienBean.CTE_PUNTO, "").replace(DatosTerrenoBienBean.CTE_COMA, ""),7,"0");
            return superficie + DatosTerrenoBienBean.CTE_GUION + this.superficieTerrenoUnidad;
        }
        return null;
    }

    public void setSuperficieTerrenoString(final String superficieTerrenoString) {
        this.superficieTerrenoString = superficieTerrenoString;
    }

    public String getSuperficieInvernaderoString() {
        if(this.superficieInvernadero!=null && StringUtils.isNotBlank(this.superficieInvernaderoUnidad)){
            final DecimalFormat decimalFormat = new DecimalFormat(DatosTerrenoBienBean.CTE_FORMATO);
            String superficie = StringUtils.leftPad(decimalFormat.format(superficieInvernadero).replace(DatosTerrenoBienBean.CTE_PUNTO, "").replace(DatosTerrenoBienBean.CTE_COMA, ""),7,"0");
            return superficie + DatosTerrenoBienBean.CTE_GUION + this.superficieInvernaderoUnidad;
        }
        return null;
    }

    public void setSuperficieInvernaderoString(final String superficieInvernaderoString) {
        this.superficieInvernaderoString = superficieInvernaderoString;
    }






}
