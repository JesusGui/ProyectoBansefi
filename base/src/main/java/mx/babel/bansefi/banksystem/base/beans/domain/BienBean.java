package mx.babel.bansefi.banksystem.base.beans.domain;

import java.io.Serializable;

import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;

/**
 * Bean para guardar los datos relativos a un Bien
 * @author luis.gcastellano
 *
 */
public class BienBean implements Serializable{

    private static final long serialVersionUID = 3893225440815030574L;

    private String idInterno;
    
    private int idInternoPersona;
    
    private Integer clase;
    
    private int tipo;
    
    private String tipoDesc;
    
    private String tipoCodigo;
    
    private DatosGeneralesBienBean datosGenerales;
    
    private DatosTerrenoBienBean datosTerreno;
    
    private DireccionRegistralBienBean direccionRegistral;
    
    private DatosValuacionBienBean datosValuacion;
    
    private DatosSeguroBienBean datosSeguro;
    
    private DatosDeudaBienBean datosDeuda;

    private boolean muestraDatosGenerales;
    
    private boolean muestraDatosTerreno;
    
    private boolean muestraDireccionRegistral;
    
    private boolean muestraDatosValuacion;
    
    private boolean muestraDatosSeguro;
    
    private boolean muestraDatosDeuda;
    
    private EstadoListadosEnum estado;
    
    private String respaldo;
    
    public String getIdInterno() {
        return idInterno;
    }

    public void setIdInterno(String idInterno) {
        this.idInterno = idInterno;
    }
 
    public DatosGeneralesBienBean getDatosGenerales() {
        return datosGenerales;
    }

    public void setDatosGenerales(DatosGeneralesBienBean datosGenerales) {
        this.datosGenerales = datosGenerales;
    }

    public DatosTerrenoBienBean getDatosTerreno() {
        return datosTerreno;
    }

    public void setDatosTerreno(DatosTerrenoBienBean datosTerreno) {
        this.datosTerreno = datosTerreno;
    }

    public DireccionRegistralBienBean getDireccionRegistral() {
        return direccionRegistral;
    }

    public void setDireccionRegistral(DireccionRegistralBienBean direccionRegistral) {
        this.direccionRegistral = direccionRegistral;
    }

    public DatosValuacionBienBean getDatosValuacion() {
        return datosValuacion;
    }

    public void setDatosValuacion(DatosValuacionBienBean datosValuacion) {
        this.datosValuacion = datosValuacion;
    }

    public DatosSeguroBienBean getDatosSeguro() {
        return datosSeguro;
    }

    public void setDatosSeguro(DatosSeguroBienBean datosSeguro) {
        this.datosSeguro = datosSeguro;
    }

    public DatosDeudaBienBean getDatosDeuda() {
        return datosDeuda;
    }

    public void setDatosDeuda(DatosDeudaBienBean datosDeuda) {
        this.datosDeuda = datosDeuda;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getTipoCodigo() {
        return tipoCodigo;
    }

    public void setTipoCodigo(String tipoCodigo) {
        this.tipoCodigo = tipoCodigo;
    }

    public boolean isMuestraDatosGenerales() {
        return muestraDatosGenerales;
    }

    public void setMuestraDatosGenerales(boolean muestraDatosGenerales) {
        this.muestraDatosGenerales = muestraDatosGenerales;
    }

    public boolean isMuestraDatosTerreno() {
        return muestraDatosTerreno;
    }

    public void setMuestraDatosTerreno(boolean muestraDatosTerreno) {
        this.muestraDatosTerreno = muestraDatosTerreno;
    }

    public boolean isMuestraDireccionRegistral() {
        return muestraDireccionRegistral;
    }

    public void setMuestraDireccionRegistral(boolean muestraDireccionRegistral) {
        this.muestraDireccionRegistral = muestraDireccionRegistral;
    }

    public boolean isMuestraDatosValuacion() {
        return muestraDatosValuacion;
    }

    public void setMuestraDatosValuacion(boolean muestraDatosValuacion) {
        this.muestraDatosValuacion = muestraDatosValuacion;
    }

    public boolean isMuestraDatosSeguro() {
        return muestraDatosSeguro;
    }

    public void setMuestraDatosSeguro(boolean muestraDatosSeguro) {
        this.muestraDatosSeguro = muestraDatosSeguro;
    }

    public boolean isMuestraDatosDeuda() {
        return muestraDatosDeuda;
    }

    public void setMuestraDatosDeuda(boolean muestraDatosDeuda) {
        this.muestraDatosDeuda = muestraDatosDeuda;
    }
    
    public Integer getClase() {
        return clase;
    }

    public void setClase(Integer clase) {
        this.clase = clase;
    }

    public void tipoBien(){
        //Tipo ingreso y gastos
        if(ConstantesFuncionales.INICIO_BIEN_INGRESO <= this.getTipo() && this.getTipo() <= ConstantesFuncionales.FIN_BIEN_INGRESO){
            this.muestraDatosGenerales=true;
            this.muestraDatosTerreno=false;
            this.muestraDireccionRegistral=false;
            this.muestraDatosValuacion=false;
            this.muestraDatosSeguro=false;
            this.muestraDatosDeuda=false;
            
        }//Tipo inmueble
        else if(ConstantesFuncionales.INICIO_BIEN_INMUEBLE <= this.getTipo() && this.getTipo() <= ConstantesFuncionales.FIN_BIEN_INMUEBLE){
            this.muestraDatosGenerales=true;
            this.muestraDatosTerreno=true;
            this.muestraDireccionRegistral=true;
            this.muestraDatosValuacion=true;
            this.muestraDatosSeguro=true;
            this.muestraDatosDeuda=false;
            
        }//Tipo vehiculos
        else if(ConstantesFuncionales.INICIO_BIEN_VEHICULOS <= this.getTipo() && this.getTipo() <= ConstantesFuncionales.FIN_BIEN_VEHICULOS){
            this.muestraDatosGenerales=true;
            this.muestraDatosTerreno=false;
            this.muestraDireccionRegistral=false;
            this.muestraDatosValuacion=false;
            this.muestraDatosSeguro=true;
            this.muestraDatosDeuda=false;
        } 
        else if(ConstantesFuncionales.INICIO_BIEN_DEUDA <= this.getTipo()){
            this.muestraDatosGenerales=true;
            this.muestraDatosTerreno=false;
            this.muestraDireccionRegistral=false;
            this.muestraDatosValuacion=false;
            this.muestraDatosSeguro=false;
            
            if(ConstantesFuncionales.INICIO_BIEN_DEUDA_DATOS <= this.getTipo() && this.getTipo() <= ConstantesFuncionales.FIN_BIEN_DEUDA_DATOS){
                this.muestraDatosDeuda=true;
            }else{
                this.muestraDatosDeuda=false;
            }
            
        }
    }

    public int getIdInternoPersona() {
        return idInternoPersona;
    }

    public void setIdInternoPersona(int idInternoPersona) {
        this.idInternoPersona = idInternoPersona;
    }
   
    public EstadoListadosEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoListadosEnum estado) {
        this.estado = estado;
    }

    public String getTipoDesc() {
        return tipoDesc;
    }

    public void setTipoDesc(String tipoDesc) {
        this.tipoDesc = tipoDesc;
    }

    public String getRespaldo() {
        return respaldo;
    }

    public void setRespaldo(String respaldo) {
        this.respaldo = respaldo;
    }
 
}
