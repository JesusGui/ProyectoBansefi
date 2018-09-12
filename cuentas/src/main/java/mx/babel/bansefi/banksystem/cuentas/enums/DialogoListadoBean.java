package mx.babel.bansefi.banksystem.cuentas.enums;

import java.io.Serializable;

public class DialogoListadoBean implements Serializable{
	
	private static final long serialVersionUID = -4285465310766516221L;
	private DialogoRelacionesCuentaListadoEnum estado;
	private String mensaje;
	private Boolean mostrar;
	private String detalles;

	/**
	 * @return Atributo estado
	 */
	public DialogoRelacionesCuentaListadoEnum getEstado() {
		return estado;
	}

	/**
	 * @param estado Atributo estado a definir
	 */
	public void setEstado(DialogoRelacionesCuentaListadoEnum estado) {
		this.estado = estado;
	}

	/**
	 * @return Atributo mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje Atributo mensaje a definir
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return Atributo mostrar
	 */
	public Boolean getMostrar() {
		return mostrar;
	}

	/**
	 * @param mostrar Atributo mostrar a definir
	 */
	public void setMostrar(Boolean mostrar) {
		this.mostrar = mostrar;
	}

	/**
	 * @return Atributo detalles
	 */
	public String getDetalles() {
		return detalles;
	}

	/**
	 * @param detalles Atributo detalles a definir
	 */
	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}
	
	public String getIcono(){
		if(estado != null){
			return estado.getIcono();
		}
		return "";
	}
	
}
