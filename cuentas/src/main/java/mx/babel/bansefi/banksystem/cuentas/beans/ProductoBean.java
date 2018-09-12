package mx.babel.bansefi.banksystem.cuentas.beans;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class ProductoBean implements Serializable{

	private static final long serialVersionUID = 6872179643596217379L;

	private String linea;

	 private String grupo;

	/**
	 * @return Atributo linea
	 */
	public String getLinea() {
		return linea;
	}

	/**
	 * @param linea Atributo linea a definir
	 */
	public void setLinea(final String linea) {
		this.linea = linea;
	}

	/**
	 * @return Atributo grupo
	 */
	public String getGrupo() {
		return grupo;
	}

	/**
	 * @param grupo Atributo grupo a definir
	 */
	public void setGrupo(final String grupo) {
		this.grupo = grupo;
	}

	/*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        return new HashCodeBuilder().append(this.linea)
                .append(this.grupo).toHashCode();
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
        if (!(obj instanceof ProductoBean)) {
            return false;
        }
        final ProductoBean castObj = (ProductoBean) obj;
        return new EqualsBuilder()
            .append(this.linea, castObj.linea)
            .append(this.grupo, castObj.grupo)
            .isEquals();
    }
}
