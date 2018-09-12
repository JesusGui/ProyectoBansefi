package mx.babel.bansefi.banksystem.oficina.beans;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.beans.PaginacionBean;

public class ConsultaSaldosBean extends PaginacionBean{

	private static final long serialVersionUID = 6336962085282761437L;
	
	private String centro;
	private List<SaldoTerminalBean> saldos;
	/**
	 * @return Atributo centro
	 */
	public String getCentro() {
		return centro;
	}
	/**
	 * @param centro Atributo centro a definir
	 */
	public void setCentro(String centro) {
		this.centro = centro;
	}
	/**
	 * @return Atributo saldos
	 */
	public List<SaldoTerminalBean> getSaldos() {
		if(saldos == null){
			saldos = new ArrayList<SaldoTerminalBean>();
		}
		return saldos;
	}
	/**
	 * @param saldos Atributo saldos a definir
	 */
	public void setSaldos(List<SaldoTerminalBean> saldos) {
		this.saldos = saldos;
	}
}
