package mx.babel.bansefi.banksystem.base.beans;

import java.io.Serializable;

public class CrctBienBean implements Serializable {

    private static final long serialVersionUID = 6292081915573941469L;
    
    private String codigoEntidad;
    
    private int idBien;
    
    private String codigoCrct;
    
    private String valorCrct;

    public String getCodigoEntidad() {
        return codigoEntidad;
    }

    public void setCodigoEntidad(String codigoEntidad) {
        this.codigoEntidad = codigoEntidad;
    }

    public int getIdBien() {
        return idBien;
    }

    public void setIdBien(int idBien) {
        this.idBien = idBien;
    }

    public String getCodigoCrct() {
        return codigoCrct;
    }

    public void setCodigoCrct(String codigoCrct) {
        this.codigoCrct = codigoCrct;
    }

    public String getValorCrct() {
        return valorCrct;
    }

    public void setValorCrct(String valorCrct) {
        this.valorCrct = valorCrct;
    }
    
}
