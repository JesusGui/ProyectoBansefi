package mx.babel.bansefi.banksystem.base.beans.parrilla;
 
public enum ColumnaParrillaEnum {
	IMPORTE_MODIFICABLE("importeModificable","Importe", true),
	IMPORTE_SOLICITADO("importeSolicitado","Importe solicitado", true),
	IMPORTE_ENVIADO("importeEnviado","Importe enviado",true),
	IMPORTE_PEDIDO("importePedido","Importe pedido",true),
	IMPORTE_AUTORIZADO("importeAutorizado","Importe autorizado",true),
	IMPORTE_A_ENVIAR("importeAEnviar","Importe a enviar",true),
	IMPORTE_RECIBIDO("importeRecibido","Importe recibido",true),
	IMPORTE_NUEVO("importeNuevo","Importe nuevo",true),
	EXISTENCIAS("existencias","Existencias",true),
	DIFERENCIA("diferencia","Diferencia",true),
	UNIDADES("unidades","Unidades",false);

	 private String nombreAtributo;
	 private String titulo;
	 private Boolean currency;
	 

	 private ColumnaParrillaEnum(final String nombreAtributo, 
			 final String titulo, final Boolean currency) {
		 this.nombreAtributo = nombreAtributo;
		 this.titulo = titulo;
		 this.currency = currency;
	 }


	/**
	 * @return Atributo nombreAtributo
	 */
	public String getNombreAtributo() {
		return nombreAtributo;
	}


	/**
	 * @param nombreAtributo Atributo nombreAtributo a definir
	 */
	public void setNombreAtributo(String nombreAtributo) {
		this.nombreAtributo = nombreAtributo;
	}


	/**
	 * @return Atributo titulo
	 */
	public String getTitulo() {
		return titulo;
	}


	/**
	 * @param titulo Atributo titulo a definir
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	/**
	 * @return Atributo currency
	 */
	public Boolean getCurrency() {
		return currency;
	}


	/**
	 * @param currency Atributo currency a definir
	 */
	public void setCurrency(Boolean currency) {
		this.currency = currency;
	}	
}
