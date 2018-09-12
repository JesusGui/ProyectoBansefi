package mx.babel.bansefi.banksystem.cuentas.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;

public class TarifaRelacionBean implements Serializable{
	
	private static final long serialVersionUID = -6877770706549215139L;

	private List<ProductoBean> productos;
	
	private Boolean requerido;
	
	private String codigoRelacion;
	
	private String nombreRelacion;

	/**
	 * @return Atributo productos
	 */
	public List<ProductoBean> getProductos() {
		if(productos == null){
			productos = new ArrayList<ProductoBean>();
		}
		return productos;
	}

	/**
	 * @param productos Atributo productos a definir
	 */
	public void setProductos(List<ProductoBean> productos) {
		this.productos = productos;
	}

	/**
	 * @return Atributo requerido
	 */
	public Boolean getRequerido() {
		return requerido;
	}

	/**
	 * @param requerido Atributo requerido a definir
	 */
	public void setRequerido(Boolean requerido) {
		this.requerido = requerido;
	}

	/**
	 * @return Atributo codigoRelacion
	 */
	public String getCodigoRelacion() {
		return codigoRelacion;
	}

	/**
	 * @param codigoRelacion Atributo codigoRelacion a definir
	 */
	public void setCodigoRelacion(String codigoRelacion) {
		this.codigoRelacion = codigoRelacion;
	}

	/**
	 * @return Atributo nombreRelacion
	 */
	public String getNombreRelacion() {
		return nombreRelacion;
	}

	/**
	 * @param nombreRelacion Atributo nombreRelacion a definir
	 */
	public void setNombreRelacion(String nombreRelacion) {
		this.nombreRelacion = nombreRelacion;
	}

	/**
	 * Método que verifica si una cuenta cumple con alguno de las tarifas de la relación
	 * @param cuenta bean de cuenta
	 * @return <code>true</code> si la cuenta cumple con alguna de las tarifas 
	 */
	public Boolean tarifaValida(CuentaBean cuenta){
		if(cuenta.getCodLinea() != null && cuenta.getIdGrupoProducto() != null){
			for (ProductoBean producto : getProductos()) {
				if(producto.getLinea().equals(cuenta.getCodLinea()) && producto.getGrupo().equals(cuenta.getIdGrupoProducto())){
					return true;
				}
			} 
		}
		return false;
	}
}
