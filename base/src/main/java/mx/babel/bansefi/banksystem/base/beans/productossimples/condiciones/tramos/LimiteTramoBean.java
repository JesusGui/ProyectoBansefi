package mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.tramos;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class LimiteTramoBean implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String limInferior1;
    private String limSuperior1;
    private String limInferior2;
    private String limSuperior2;
    private String limInferior3;
    private String limSuperior3;
    private String idParmCd1;
    private String codDomParmCd1;
    private String descDomParmCd1;
    private String idParmCd2;
    private String codDomParmCd2;
    private String descDomParmCd2;
    private String idParmCd3;
    private String codDomParmCd3;
    private String descDomParmCd3;
    private String idParmCd4;
    private String codDomParmCd4;
    private String descDomParmCd4;

    public String getLimInferior(final int pos){
        String lim = null;
        switch (pos) {
        case 1:
            lim = this.getLimInferior1();
            break;
        case 2:
            lim = this.getLimInferior2();
            break;
        case 3:
            lim = this.getLimInferior3();
            break;

        default:
            break;
        }
        return lim;
    }

    public String getLimSuperior(final int pos){
        String lim = null;
        switch (pos) {
        case 1:
            lim = this.getLimSuperior1();
            break;
        case 2:
            lim = this.getLimSuperior2();
            break;
        case 3:
            lim = this.getLimSuperior3();
            break;

        default:
            break;
        }
        return lim;
    }

    public String getCodDomParmCd(final int pos){
        String codDomParmCd = null;
        switch (pos) {
        case 4:
            codDomParmCd = this.getCodDomParmCd1();
            break;
        case 5:
            codDomParmCd = this.getCodDomParmCd2();
            break;
        case 6:
            codDomParmCd = this.getCodDomParmCd3();
            break;
        case 7:
            codDomParmCd = this.getCodDomParmCd4();
            break;
        default:
            break;
        }
        return codDomParmCd;
    }

    public String getDescDomParmCd(final int pos){
        String descDomParmCd = null;
        switch (pos) {
        case 4:
            descDomParmCd = this.getDescDomParmCd1();
            break;
        case 5:
            descDomParmCd = this.getDescDomParmCd2();
            break;
        case 6:
            descDomParmCd = this.getDescDomParmCd3();
            break;
        case 7:
            descDomParmCd = this.getDescDomParmCd4();
            break;
        default:
            break;
        }
        return descDomParmCd;
    }

    public void setDescDomParmCd(final int pos, final String descDomParmCd){
        switch (pos) {
        case 4:
            this.setDescDomParmCd1(descDomParmCd);
            break;
        case 5:
            this.setDescDomParmCd2(descDomParmCd);
            break;
        case 6:
            this.setDescDomParmCd3(descDomParmCd);
            break;
        case 7:
            this.setDescDomParmCd4(descDomParmCd);
            break;
        default:
            break;
        }
    }

    public String getIdParmCd(final int pos){
        String idParmCd = null;
        switch (pos) {
        case 4:
            idParmCd = this.getIdParmCd1();
            break;
        case 5:
            idParmCd = this.getIdParmCd2();
            break;
        case 6:
            idParmCd = this.getIdParmCd3();
            break;
        case 7:
            idParmCd = this.getIdParmCd4();
            break;
        default:
            break;
        }
        return idParmCd;
    }
    /**
     * @return the limInferior1
     */
    public String getLimInferior1() {
        return limInferior1;
    }

    /**
     * @param limInferior1 the limInferior1 to set
     */
    public void setLimInferior1(final String limInferior1) {
        this.limInferior1 = limInferior1;
    }

    /**
     * @return the limSuperior1
     */
    public String getLimSuperior1() {
        return limSuperior1;
    }

    /**
     * @param limSuperior1 the limSuperior1 to set
     */
    public void setLimSuperior1(final String limSuperior1) {
        this.limSuperior1 = limSuperior1;
    }

    /**
     * @return the limInferior2
     */
    public String getLimInferior2() {
        return limInferior2;
    }

    /**
     * @param limInferior2 the limInferior2 to set
     */
    public void setLimInferior2(final String limInferior2) {
        this.limInferior2 = limInferior2;
    }

    /**
     * @return the limSuperior2
     */
    public String getLimSuperior2() {
        return limSuperior2;
    }

    /**
     * @param limSuperior2 the limSuperior2 to set
     */
    public void setLimSuperior2(final String limSuperior2) {
        this.limSuperior2 = limSuperior2;
    }

    /**
     * @return the limInferior3
     */
    public String getLimInferior3() {
        return limInferior3;
    }

    /**
     * @param limInferior3 the limInferior3 to set
     */
    public void setLimInferior3(final String limInferior3) {
        this.limInferior3 = limInferior3;
    }

    /**
     * @return the limSuperior3
     */
    public String getLimSuperior3() {
        return limSuperior3;
    }

    /**
     * @param limSuperior3 the limSuperior3 to set
     */
    public void setLimSuperior3(final String limSuperior3) {
        this.limSuperior3 = limSuperior3;
    }

    /**
     * @return the idParmCd1
     */
    public String getIdParmCd1() {
        return idParmCd1;
    }

    /**
     * @param idParmCd1 the idParmCd1 to set
     */
    public void setIdParmCd1(final String idParmCd1) {
        this.idParmCd1 = idParmCd1;
    }

    /**
     * @return the codDomParmCd1
     */
    public String getCodDomParmCd1() {
        return codDomParmCd1;
    }

    /**
     * @param codDomParmCd1 the codDomParmCd1 to set
     */
    public void setCodDomParmCd1(final String codDomParmCd1) {
        this.codDomParmCd1 = codDomParmCd1;
    }

    /**
     * @return the descDomParmCd1
     */
    public String getDescDomParmCd1() {
        return descDomParmCd1;
    }

    /**
     * @param descDomParmCd1 the descDomParmCd1 to set
     */
    public void setDescDomParmCd1(final String descDomParmCd1) {
        this.descDomParmCd1 = descDomParmCd1;
    }

    /**
     * @return the idParmCd2
     */
    public String getIdParmCd2() {
        return idParmCd2;
    }

    /**
     * @param idParmCd2 the idParmCd2 to set
     */
    public void setIdParmCd2(final String idParmCd2) {
        this.idParmCd2 = idParmCd2;
    }

    /**
     * @return the codDomParmCd2
     */
    public String getCodDomParmCd2() {
        return codDomParmCd2;
    }

    /**
     * @param codDomParmCd2 the codDomParmCd2 to set
     */
    public void setCodDomParmCd2(final String codDomParmCd2) {
        this.codDomParmCd2 = codDomParmCd2;
    }

    /**
     * @return the descDomParmCd2
     */
    public String getDescDomParmCd2() {
        return descDomParmCd2;
    }

    /**
     * @param descDomParmCd2 the descDomParmCd2 to set
     */
    public void setDescDomParmCd2(final String descDomParmCd2) {
        this.descDomParmCd2 = descDomParmCd2;
    }

    /**
     * @return the idParmCd3
     */
    public String getIdParmCd3() {
        return idParmCd3;
    }

    /**
     * @param idParmCd3 the idParmCd3 to set
     */
    public void setIdParmCd3(final String idParmCd3) {
        this.idParmCd3 = idParmCd3;
    }

    /**
     * @return the codDomParmCd3
     */
    public String getCodDomParmCd3() {
        return codDomParmCd3;
    }

    /**
     * @param codDomParmCd3 the codDomParmCd3 to set
     */
    public void setCodDomParmCd3(final String codDomParmCd3) {
        this.codDomParmCd3 = codDomParmCd3;
    }

    /**
     * @return the descDomParmCd3
     */
    public String getDescDomParmCd3() {
        return descDomParmCd3;
    }

    /**
     * @param descDomParmCd3 the descDomParmCd3 to set
     */
    public void setDescDomParmCd3(final String descDomParmCd3) {
        this.descDomParmCd3 = descDomParmCd3;
    }

    /**
     * @return the idParmCd4
     */
    public String getIdParmCd4() {
        return idParmCd4;
    }

    /**
     * @param idParmCd4 the idParmCd4 to set
     */
    public void setIdParmCd4(final String idParmCd4) {
        this.idParmCd4 = idParmCd4;
    }

    /**
     * @return the codDomParmCd4
     */
    public String getCodDomParmCd4() {
        return codDomParmCd4;
    }

    /**
     * @param codDomParmCd4 the codDomParmCd4 to set
     */
    public void setCodDomParmCd4(final String codDomParmCd4) {
        this.codDomParmCd4 = codDomParmCd4;
    }

    /**
     * @return the descDomParmCd4
     */
    public String getDescDomParmCd4() {
        return descDomParmCd4;
    }

    /**
     * @param descDomParmCd4 the descDomParmCd4 to set
     */
    public void setDescDomParmCd4(final String descDomParmCd4) {
        this.descDomParmCd4 = descDomParmCd4;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().appendSuper(super.hashCode())
                .append(limInferior1).append(limSuperior1)
                .append(limInferior2).append(limSuperior2)
                .append(limInferior3).append(limSuperior3).toHashCode();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LimiteTramoBean)) {
            return false;
        }
        final LimiteTramoBean castObj = (LimiteTramoBean) obj;

        return new EqualsBuilder().appendSuper(super.equals(obj))
                .append(limInferior1, castObj.limInferior1)
                .append(limSuperior1, castObj.limSuperior1)
                .append(limInferior2, castObj.limInferior2)
                .append(limSuperior2, castObj.limSuperior2)
                .append(limInferior3, castObj.limInferior3)
                .append(limSuperior3, castObj.limSuperior3).isEquals();
    }

}
