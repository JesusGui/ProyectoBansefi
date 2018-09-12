package mx.babel.arq.catalogo.beans;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/** Bean que almacena los datos de la response del
 *  webservice de consulta de catalogos.
 * @author joseluis.pena
 *
 */
public class CatalogoBean implements Serializable, Cloneable {

	private static final long serialVersionUID = -6269266535643220689L;
	private String claveFila;
	private String descripcionC;
	private String descripcionL;
	private String contenido;


	public CatalogoBean() {
        super();
    }


	public CatalogoBean(final String claveFila, final String descripcionL) {
        super();
        this.claveFila = claveFila;
        this.descripcionL = descripcionL;
    }


    /**
     * @return the claveFila
     */
    public String getClaveFila() {
        return claveFila;
    }
    /**
     * @param claveFila the claveFila to set
     */
    public void setClaveFila(final String claveFila) {
        this.claveFila = claveFila;
    }
    /**
     * @return the descripcionC
     */
    public String getDescripcionC() {
        return descripcionC;
    }
    /**
     * @param descripcionC the descripcionC to set
     */
    public void setDescripcionC(final String descripcionC) {
        this.descripcionC = descripcionC;
    }
    /**
     * @return the descripcionL
     */
    public String getDescripcionL() {
        return descripcionL;
    }
    /**
     * @param descripcionL the descripcionL to set
     */
    public void setDescripcionL(final String descripcionL) {
        this.descripcionL = descripcionL;
    }
    /**
     * @return the contenido
     */
    public String getContenido() {
        return contenido;
    }
    /**
     * @param contenido the contenido to set
     */
    public void setContenido(final String contenido) {
        this.contenido = contenido;
    }
    
    @Override
    public Object clone(){
    	Object clone = null;
    	
    	 try {
             clone = super.clone();
         }catch(CloneNotSupportedException e) {
             // No debería ocurrir
         }
    	 
    	 ((CatalogoBean)clone).setClaveFila(new String(this.getClaveFila()));
    	 ((CatalogoBean)clone).setContenido(new String(this.getContenido()));
    	 ((CatalogoBean)clone).setDescripcionC(new String(this.getDescripcionC()));
    	 ((CatalogoBean)clone).setDescripcionL(new String(this.getDescripcionL()));
    	
    	return clone;
    }

    @Override
    public boolean equals(Object obj){
    	return EqualsBuilder.reflectionEquals(obj, this, new String[]{"serialVersionUID"});
    }
    
    @Override
    public int hashCode(){
    	return HashCodeBuilder.reflectionHashCode(this, new String[]{"serialVersionUID"});
    }

	@Override
	public String toString() {
		return "CatalogoBean [claveFila=" + claveFila + ", descripcionC="
				+ descripcionC + ", descripcionL=" + descripcionL
				+ ", contenido=" + contenido + "]";
	}
    
}
