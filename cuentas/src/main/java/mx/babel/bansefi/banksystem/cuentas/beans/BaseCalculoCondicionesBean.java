package mx.babel.bansefi.banksystem.cuentas.beans;

import java.io.Serializable;

public class BaseCalculoCondicionesBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5025475294895565737L;
    private String idPds;
    private String parmCd;
    private String nombrePds;
    private String categoriaUM;
    private String codEstructuraParmCd;
    private String codRelacionPKPS;
    private String nomParmCd;
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
     * @return the parmCd
     */
    public String getParmCd() {
        return parmCd;
    }
    /**
     * @param parmCd the parmCd to set
     */
    public void setParmCd(final String parmCd) {
        this.parmCd = parmCd;
    }
    /**
     * @return the nombrePds
     */
    public String getNombrePds() {
        return nombrePds;
    }
    /**
     * @param nombrePds the nombrePds to set
     */
    public void setNombrePds(final String nombrePds) {
        this.nombrePds = nombrePds;
    }
    /**
     * @return the categoriaUM
     */
    public String getCategoriaUM() {
        return categoriaUM;
    }
    /**
     * @param categoriaUM the categoriaUM to set
     */
    public void setCategoriaUM(final String categoriaUM) {
        this.categoriaUM = categoriaUM;
    }
    /**
     * @return the codEstructuraParmCd
     */
    public String getCodEstructuraParmCd() {
        return codEstructuraParmCd;
    }
    /**
     * @param codEstructuraParmCd the codEstructuraParmCd to set
     */
    public void setCodEstructuraParmCd(final String codEstructuraParmCd) {
        this.codEstructuraParmCd = codEstructuraParmCd;
    }
    /**
     * @return the codRelacionPKPS
     */
    public String getCodRelacionPKPS() {
        return codRelacionPKPS;
    }
    /**
     * @param codRelacionPKPS the codRelacionPKPS to set
     */
    public void setCodRelacionPKPS(final String codRelacionPKPS) {
        this.codRelacionPKPS = codRelacionPKPS;
    }
    /**
     * @return the nomParmCd
     */
    public String getNomParmCd() {
        return nomParmCd;
    }
    /**
     * @param nomParmCd the nomParmCd to set
     */
    public void setNomParmCd(final String nomParmCd) {
        this.nomParmCd = nomParmCd;
    }


}
