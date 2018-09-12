package mx.babel.bansefi.banksystem.base.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import mx.babel.arq.comun.beans.PaginacionBean;

/**
 * Bean que se utiliza para el autocomplete de cuentas contables
 * @author manuel.nieto
 *
 */
public class CuentaContableBean implements Serializable{

	private static final long serialVersionUID = -1183122735047311413L;
	
	private String query;
	private String idCuentaContable;
	private String codigoCuentaContable;
	private String nombreCuentaContable;
	private BigDecimal saldo;
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getIdCuentaContable() {
		return idCuentaContable;
	}
	public void setIdCuentaContable(String idCuentaContable) {
		this.idCuentaContable = idCuentaContable;
	}
	public String getCodigoCuentaContable() {
		return codigoCuentaContable;
	}
	public void setCodigoCuentaContable(String codigoCuentaContable) {
		this.codigoCuentaContable = codigoCuentaContable;
	}
	public String getNombreCuentaContable() {
		return nombreCuentaContable;
	}
	public void setNombreCuentaContable(String nombreCuentaContable) {
		this.nombreCuentaContable = nombreCuentaContable;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	
}
