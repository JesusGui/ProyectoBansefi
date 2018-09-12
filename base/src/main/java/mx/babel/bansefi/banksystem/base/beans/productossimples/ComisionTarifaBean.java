package mx.babel.bansefi.banksystem.base.beans.productossimples;

import java.io.Serializable;
import java.math.BigDecimal;

public class ComisionTarifaBean implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 111472700694489848L;
    private BigDecimal impFijoMin;
    private BigDecimal impFijoMax;
    private BigDecimal impFijoIncremento;
    private BigDecimal impFijoPreferente;
    private String formaCalculoComFija;
    private BigDecimal porcentajeMin;
    private BigDecimal porcentajeMax;
    private BigDecimal porcentajePreferente;
    private BigDecimal porcentajeIncremento;
    private BigDecimal importeFranquicia;
    private BigDecimal comisionMin;
    private BigDecimal comisionMax;
    private String agruClop;
    private String baseCalculoComVar;
    private String baseCalculoComVarDesc;
    /**
     * @return the impFijoMin
     */
    public BigDecimal getImpFijoMin() {
        return impFijoMin;
    }
    /**
     * @param impFijoMin the impFijoMin to set
     */
    public void setImpFijoMin(final BigDecimal impFijoMin) {
        this.impFijoMin = impFijoMin;
    }
    /**
     * @return the impFijoMax
     */
    public BigDecimal getImpFijoMax() {
        return impFijoMax;
    }
    /**
     * @param impFijoMax the impFijoMax to set
     */
    public void setImpFijoMax(final BigDecimal impFijoMax) {
        this.impFijoMax = impFijoMax;
    }
    /**
     * @return the impFijoIncremento
     */
    public BigDecimal getImpFijoIncremento() {
        return impFijoIncremento;
    }
    /**
     * @param impFijoIncremento the impFijoIncremento to set
     */
    public void setImpFijoIncremento(final BigDecimal impFijoIncremento) {
        this.impFijoIncremento = impFijoIncremento;
    }
    /**
     * @return the impFijoPreferente
     */
    public BigDecimal getImpFijoPreferente() {
        return impFijoPreferente;
    }
    /**
     * @param impFijoPreferente the impFijoPreferente to set
     */
    public void setImpFijoPreferente(final BigDecimal impFijoPreferente) {
        this.impFijoPreferente = impFijoPreferente;
    }
    /**
     * @return the formaCalculoComFija
     */
    public String getFormaCalculoComFija() {
        return formaCalculoComFija;
    }
    /**
     * @param formaCalculoComFija the formaCalculoComFija to set
     */
    public void setFormaCalculoComFija(final String formaCalculoComFija) {
        this.formaCalculoComFija = formaCalculoComFija;
    }
    /**
     * @return the porcentajeMin
     */
    public BigDecimal getPorcentajeMin() {
        return porcentajeMin;
    }
    /**
     * @param porcentajeMin the porcentajeMin to set
     */
    public void setPorcentajeMin(final BigDecimal porcentajeMin) {
        this.porcentajeMin = porcentajeMin;
    }
    /**
     * @return the porcentajeMax
     */
    public BigDecimal getPorcentajeMax() {
        return porcentajeMax;
    }
    /**
     * @param porcentajeMax the porcentajeMax to set
     */
    public void setPorcentajeMax(final BigDecimal porcentajeMax) {
        this.porcentajeMax = porcentajeMax;
    }
    /**
     * @return the porcentajePreferente
     */
    public BigDecimal getPorcentajePreferente() {
        return porcentajePreferente;
    }
    /**
     * @param porcentajePreferente the porcentajePreferente to set
     */
    public void setPorcentajePreferente(final BigDecimal porcentajePreferente) {
        this.porcentajePreferente = porcentajePreferente;
    }
    /**
     * @return the porcentajeIncremento
     */
    public BigDecimal getPorcentajeIncremento() {
        return porcentajeIncremento;
    }
    /**
     * @param porcentajeIncremento the porcentajeIncremento to set
     */
    public void setPorcentajeIncremento(final BigDecimal porcentajeIncremento) {
        this.porcentajeIncremento = porcentajeIncremento;
    }
    /**
     * @return the importeFranquicia
     */
    public BigDecimal getImporteFranquicia() {
        return importeFranquicia;
    }
    /**
     * @param importeFranquicia the importeFranquicia to set
     */
    public void setImporteFranquicia(final BigDecimal importeFranquicia) {
        this.importeFranquicia = importeFranquicia;
    }
    /**
     * @return the comisionMin
     */
    public BigDecimal getComisionMin() {
        return comisionMin;
    }
    /**
     * @param comisionMin the comisionMin to set
     */
    public void setComisionMin(final BigDecimal comisionMin) {
        this.comisionMin = comisionMin;
    }
    /**
     * @return the comisionMax
     */
    public BigDecimal getComisionMax() {
        return comisionMax;
    }
    /**
     * @param comisionMax the comisionMax to set
     */
    public void setComisionMax(final BigDecimal comisionMax) {
        this.comisionMax = comisionMax;
    }
    /**
     * @return the agruClop
     */
    public String getAgruClop() {
        return agruClop;
    }
    /**
     * @param agruClop the agruClop to set
     */
    public void setAgruClop(final String agruClop) {
        this.agruClop = agruClop;
    }
    /**
     * @return the baseCalculoComVar
     */
    public String getBaseCalculoComVar() {
        return baseCalculoComVar;
    }
    /**
     * @param baseCalculoComVar the baseCalculoComVar to set
     */
    public void setBaseCalculoComVar(final String baseCalculoComVar) {
        this.baseCalculoComVar = baseCalculoComVar;
    }
    /**
     * @return the baseCalculoComVarDesc
     */
    public String getBaseCalculoComVarDesc() {
        return baseCalculoComVarDesc;
    }
    /**
     * @param baseCalculoComVarDesc the baseCalculoComVarDesc to set
     */
    public void setBaseCalculoComVarDesc(final String baseCalculoComVarDesc) {
        this.baseCalculoComVarDesc = baseCalculoComVarDesc;
    }



}
