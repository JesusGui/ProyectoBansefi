package mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.tramos;

import java.io.Serializable;

public class CabeceraTramoBean implements Serializable{

    private String idPds;
    private String idParamCd;
    private String descripcion;
    private Integer posCol;
    private Integer posPres;
    private String udMedidas;
    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
    }
    /**
     * @return the posCol
     */
    public Integer getPosCol() {
        return posCol;
    }
    /**
     * @param posCol the posCol to set
     */
    public void setPosCol(final Integer posCol) {
        this.posCol = posCol;
    }
    /**
     * @return the udMedidas
     */
    public String getUdMedidas() {
        return udMedidas;
    }
    /**
     * @param udMedidas the udMedidas to set
     */
    public void setUdMedidas(final String udMedidas) {
        this.udMedidas = udMedidas;
    }
    /**
     * @return the idPds
     */
    public String getIdPds() {
        return idPds;
    }
    /**
     * @param idPds the idPds to set
     */
    public void setIdPds(final String idPds) {
        this.idPds = idPds;
    }
    /**
     * @return the idParamCd
     */
    public String getIdParamCd() {
        return idParamCd;
    }
    /**
     * @param idParamCd the idParamCd to set
     */
    public void setIdParamCd(final String idParamCd) {
        this.idParamCd = idParamCd;
    }
    /**
     * @return the posPres
     */
    public Integer getPosPres() {
        return posPres;
    }
    /**
     * @param posPres the posPres to set
     */
    public void setPosPres(final Integer posPres) {
        this.posPres = posPres;
    }



}
