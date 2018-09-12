package mx.babel.bansefi.banksystem.cuentas.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.babel.bansefi.banksystem.base.beans.productossimples.ProductoSimpleBean;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author joseluis.pena
 *
 */
public class TarifaBean implements Serializable {

	/**
     *
     */
    private static final long serialVersionUID = 1L;
    private String id;
    private String linea;
    private String grupo;
    private String producto;
    private String nombre;
    private String descripcion;
    private Date fechaComercializacion;
    private Date fechaAlta;
    private String estado;
    private String tipoCuota;

    private List<ProductoSimpleBean> productosSimples;
    private List<TarifaRelacionBean> relaciones;


	public TarifaBean() {
        super();
        // TODO Auto-generated constructor stub
    }


	public TarifaBean(final String linea) {
        super();
        this.linea = linea;
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
	 * @return the linea
	 */
	public String getLinea() {
		return linea;
	}
	/**
	 * @param linea the linea to set
	 */
	public void setLinea(final String linea) {
		this.linea = linea;
	}
	/**
	 * @return the grupo
	 */
	public String getGrupo() {
		return grupo;
	}
	/**
	 * @param grupo the grupo to set
	 */
	public void setGrupo(final String grupo) {
		this.grupo = grupo;
	}
	/**
	 * @return the producto
	 */
	public String getProducto() {
		return producto;
	}
	/**
	 * @param producto the producto to set
	 */
	public void setProducto(final String producto) {
		this.producto = producto;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(final String nombre) {
		this.nombre = nombre;
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
     * @return the fechaComercializacion
     */
    public Date getFechaComercializacion() {
        return fechaComercializacion;
    }
    /**
     * @param fechaComercializacion the fechaComercializacion to set
     */
    public void setFechaComercializacion(final Date fechaComercializacion) {
        this.fechaComercializacion = fechaComercializacion;
    }
    /**
     * @return the fechaAlta
     */
    public Date getFechaAlta() {
        return fechaAlta;
    }
    /**
     * @param fechaAlta the fechaAlta to set
     */
    public void setFechaAlta(final Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }
    /**
     * @param estado the estado to set
     */
    public void setEstado(final String estado) {
        this.estado = estado;
    }
	/**
     * @return the productosSimples
     */
    public List<ProductoSimpleBean> getProductosSimples() {
        return productosSimples;
    }
    /**
     * @param productosSimples the productosSimples to set
     */
    public void setProductosSimples(final List<ProductoSimpleBean> productosSimples) {
        this.productosSimples = productosSimples;
    }
    /**
	 * @return Atributo relaciones
	 */
	public List<TarifaRelacionBean> getRelaciones() {
		if(relaciones == null){
			relaciones = new ArrayList<TarifaRelacionBean>();
		}
		return relaciones;
	}


	/**
	 * @param relaciones Atributo relaciones a definir
	 */
	public void setRelaciones(List<TarifaRelacionBean> relaciones) {
		this.relaciones = relaciones;
	}


	/**
     * @return the tipoCuota
     */
    public String getTipoCuota() {
        return tipoCuota;
    }
    /**
     * @param tipoCuota the tipoCuota to set
     */
    public void setTipoCuota(final String tipoCuota) {
        this.tipoCuota = tipoCuota;
    }
    /* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
		.append(id)
		.append(linea)
        .append(grupo)
		.append(nombre)
        .append(descripcion)
		.append(producto)
        .append(fechaComercializacion)
        .append(fechaAlta)
        .append(estado)
        .append(productosSimples)
        .append(tipoCuota)
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
		if (!(obj instanceof TarifaBean)) {
			return false;
		}
		final TarifaBean castObj = (TarifaBean) obj;
		return new EqualsBuilder()
				.append(id, castObj.id)
                .append(linea, castObj.linea)
				.append(grupo, castObj.grupo)
                .append(nombre, castObj.nombre)
				.append(descripcion, castObj.descripcion)
				.append(producto, castObj.producto)
                .append(fechaComercializacion, castObj.fechaComercializacion)
                .append(fechaAlta, castObj.fechaAlta)
                .append(estado, castObj.estado)
                .append(tipoCuota, castObj.tipoCuota)
                .append(productosSimples, castObj.productosSimples)
				.isEquals();
	}

}
