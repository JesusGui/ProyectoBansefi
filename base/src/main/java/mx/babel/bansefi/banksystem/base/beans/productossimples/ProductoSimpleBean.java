package mx.babel.bansefi.banksystem.base.beans.productossimples;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author joseluis.pena
 *
 */
public class ProductoSimpleBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String id;
    private String descripcion;
    private Date fechaInicio;
    private Boolean obligatorio;
    private List<CondicionBean> condiciones;



    public ProductoSimpleBean() {
        super();
    }


    public ProductoSimpleBean(final String id) {
        super();
        this.id = id;
        this.condiciones = new ArrayList<CondicionBean>();
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
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }


    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(final Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }


    /**
     * @return the obligatorio
     */
    public Boolean getObligatorio() {
        return obligatorio;
    }


    /**
     * @param obligatorio the obligatorio to set
     */
    public void setObligatorio(final Boolean obligatorio) {
        this.obligatorio = obligatorio;
    }


    /**
     * @return the condiciones
     */
    public List<CondicionBean> getCondiciones() {
        return condiciones;
    }
    /**
     * @param condiciones the condiciones to set
     */
    public void setCondiciones(final List<CondicionBean> condiciones) {
        this.condiciones = condiciones;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
        .append(id)
        .append(descripcion)
        .append(condiciones)
        .append(fechaInicio)
        .append(obligatorio)
        .toHashCode();
    }
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProductoSimpleBean)) {
            return false;
        }
        final ProductoSimpleBean castObj = (ProductoSimpleBean) obj;
        final EqualsBuilder eq = new EqualsBuilder()
        .append(id, castObj.id)
        .append(descripcion, castObj.descripcion)
        .append(fechaInicio, castObj.fechaInicio)
        .append(obligatorio, castObj.obligatorio);
        if(condiciones == castObj.condiciones){
            eq.appendSuper(true);
        }else{
            if(condiciones != null && castObj.condiciones != null
               &&condiciones.size() == castObj.condiciones.size()){
                for(int i = 0; i<condiciones.size(); i++){
                    eq.append(condiciones.get(i), castObj.condiciones.get(i));
                }
            }else{
                eq.appendSuper(false);
            }
        }
        return eq.isEquals();
    }




}
