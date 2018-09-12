package mx.babel.bansefi.banksystem.base.beans;

import java.util.List;

import mx.babel.bansefi.banksystem.base.beans.domain.ClienteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;

/**
 * Bean que contiene los objetos para modelar una posición
 * 
 * @author babel
 *
 */
public class PosicionCuentaBean {

	private List<ClienteBean> intervinientes;
	
	private List<ConceptoPosicionBean> conceptos;
	
	private CuentaBean cuenta; // Se almacena información cuando sea posición de una cuenta
	
	private ClienteBean cliente;// Se almacena información cuando sea posición de un grupo

	/**
	 * @return the intervinientes
	 */
	public List<ClienteBean> getIntervinientes() {
		return intervinientes;
	}

	/**
	 * @param intervinientes the intervinientes to set
	 */
	public void setIntervinientes(List<ClienteBean> intervinientes) {
		this.intervinientes = intervinientes;
	}

	/**
	 * @return the conceptos
	 */
	public List<ConceptoPosicionBean> getConceptos() {
		return conceptos;
	}

	/**
	 * @param conceptos the conceptos to set
	 */
	public void setConceptos(List<ConceptoPosicionBean> conceptos) {
		this.conceptos = conceptos;
	}


	/**
	 * @return the cuenta
	 */
	public CuentaBean getCuenta() {
		return cuenta;
	}

	/**
	 * @param cuenta the cuenta to set
	 */
	public void setCuenta(CuentaBean cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * @return the cliente
	 */
	public ClienteBean getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(ClienteBean cliente) {
		this.cliente = cliente;
	}

	
	
	
}
