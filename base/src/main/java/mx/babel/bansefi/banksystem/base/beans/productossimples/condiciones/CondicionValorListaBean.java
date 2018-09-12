package mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones;

import java.util.List;

import mx.babel.bansefi.banksystem.base.beans.PreferenceItemBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.CondicionBean;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;



public class CondicionValorListaBean extends CondicionBean {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String codDomParmcd;
    private List<PreferenceItemBean> items;


    public CondicionValorListaBean() {
        super("valorLista");
    }

    /**
     * @return the codDomParmcd
     */
    public String getCodDomParmcd() {
        return codDomParmcd;
    }
    /**
     * @param codDomParmcd the codDomParmcd to set
     */
    public void setCodDomParmcd(final String codDomParmcd) {
        this.codDomParmcd = codDomParmcd;
    }



    /**
     * @return the items
     */
    public List<PreferenceItemBean> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(final List<PreferenceItemBean> items) {
        this.items = items;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().appendSuper(super.hashCode()).append(codDomParmcd).toHashCode();
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
        if (!(obj instanceof CondicionValorListaBean)) {
            return false;
        }
        final CondicionValorListaBean castObj = (CondicionValorListaBean) obj;
        return new EqualsBuilder().appendSuper(super.equals(obj))
                .append(codDomParmcd, castObj.codDomParmcd).isEquals();

    }

}
