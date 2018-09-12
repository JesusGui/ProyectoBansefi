package mx.babel.bansefi.banksystem.cuentas.beans;

import java.io.Serializable;

import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;

/**
 * Bean que se utilizara para almacenar los datos necesarios para la cancelacion
 * de cuentas/acuerdos
 * 
 * @author manuel.nieto
 * 
 */
public class CancelacionCuentaBean implements Serializable {

	private static final long serialVersionUID = 430141026381498630L;
	private String destinoImporte;
	private Long otraCuenta;
	private CuentaBean cuentaBean;
	private String razonCancelacion;
	private EsperaBean esperaBean;
	/**
	 * S o N
	 */
	private String respuestaSincrona;
	private String nLlamada;

	public String getDestinoImporte() {
		return destinoImporte;
	}

	public void setDestinoImporte(String destinoImporte) {
		this.destinoImporte = destinoImporte;
	}

	public CuentaBean getCuentaBean() {
		return cuentaBean;
	}

	public void setCuentaBean(CuentaBean cuentaBean) {
		this.cuentaBean = cuentaBean;
	}

	public Long getOtraCuenta() {
		return otraCuenta;
	}

	public void setOtraCuenta(Long otraCuenta) {
		this.otraCuenta = otraCuenta;
	}

	public String getRazonCancelacion() {
		return razonCancelacion;
	}

	public void setRazonCancelacion(String razonCancelacion) {
		this.razonCancelacion = razonCancelacion;
	}

	public String getRespuestaSincrona() {
		return respuestaSincrona;
	}

	public void setRespuestaSincrona(String respuestaSincrona) {
		this.respuestaSincrona = respuestaSincrona;
	}

	public String getnLlamada() {
		return nLlamada;
	}

	public void setnLlamada(String nLlamada) {
		this.nLlamada = nLlamada;
	}

	public EsperaBean getEsperaBean() {
		return esperaBean;
	}

	public void setEsperaBean(EsperaBean esperaBean) {
		this.esperaBean = esperaBean;
	}
	

}
