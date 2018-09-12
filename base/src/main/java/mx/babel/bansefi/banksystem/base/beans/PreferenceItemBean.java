package mx.babel.bansefi.banksystem.base.beans;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


public class PreferenceItemBean implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = -3677416470010988679L;

    private boolean preferente;
    private String desc;
    private String id;

    /**
     * @return the preferente
     */
    public boolean isPreferente() {
        return preferente;
    }

    /**
     * @param preferente the preferente to set
     */
    public void setPreferente(final boolean preferente) {
        this.preferente = preferente;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }


    /**
     * @param desc the desc to set
     */
    public void setDesc(final String desc) {
        this.desc = desc;
    }


    /**
     * @return the id
     */
    public String getId() {
        return id;
    }


    /**
     * @param id the id to set
     */
    public void setId(final String id) {
        this.id = id;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(id).append(desc).append(preferente).toHashCode();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PreferenceItemBean)) {
            return false;
        }
        final PreferenceItemBean castObj = (PreferenceItemBean) obj;
        return new EqualsBuilder().append(id, castObj.id)
                .append(desc, castObj.desc)
                .append(preferente, castObj.preferente).isEquals();

    }
}
