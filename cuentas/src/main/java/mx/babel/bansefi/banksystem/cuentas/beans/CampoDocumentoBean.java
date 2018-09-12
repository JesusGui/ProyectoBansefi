package mx.babel.bansefi.banksystem.cuentas.beans;

public class CampoDocumentoBean {
	
	private String descripcionVariable;
	private String descripcion;
	private String codigo;
	private String apartado;
	private String valor;
	private String valorLen;
	private String codigoApartado;
	private Integer seccionApartado;
	private String original;

	public String getValorLen() {
		return valorLen;
	}

	public void setValorLen(String valorLen) {
		this.valorLen = valorLen;
	}

	/**
	 * @return Atributo descripcionVariable

	 */
	public String getDescripcionVariable() {
		return descripcionVariable;
	}
	/**
	 * @param descripcionVariable Atributo descripcionVariable a definir
	 */
	public void setDescripcionVariable(String descripcionVariable) {
		this.descripcionVariable = descripcionVariable;
	}
	/**
	 * @return Atributo codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo Atributo codigo a definir
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return Atributo apartado
	 */
	public String getApartado() {
		return apartado;
	}
	/**
	 * @param apartado Atributo apartado a definir
	 */
	public void setApartado(String apartado) {
		this.apartado = apartado;
	}
	/**
	 * @return Atributo valor
	 */
	public String getValor() {
		return valor.substring(0, Integer.parseInt(this.valorLen));
	}

	public int getLengthValor() {
		return valor.length();
	}
	/**
	 * @param valor Atributo valor a definir
	 */
	public void setValor(String valor) {
		if(valor != null){
			valor = valor.toUpperCase();
		}
		this.valor = valor;
	}
	/**
	 * @return Atributo codigoApartado
	 */
	public String getCodigoApartado() {
		return codigoApartado;
	}
	/**
	 * @param codigoApartado Atributo codigoApartado a definir
	 */
	public void setCodigoApartado(String codigoApartado) {
		this.codigoApartado = codigoApartado;
	}
	/**
	 * @return Atributo seccionApartado
	 */
	public Integer getSeccionApartado() {
		return seccionApartado;
	}
	/**
	 * @param seccionApartado Atributo seccionApartado a definir
	 */
	public void setSeccionApartado(Integer seccionApartado) {
		this.seccionApartado = seccionApartado;
	}
	/**
	 * @return Atributo tieneCambios
	 */
	public String getOriginal() {
		if(original == null){
			original = valor;
		}
		return original;
	}
	/**
	 * @param tieneCambios Atributo tieneCambios a definir
	 */
	public void setOriginal(String original) {
		this.original = original;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
