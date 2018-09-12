package mx.babel.bansefi.banksystem.cuentas.beans;

import java.math.BigDecimal;

/**
 * Define las propiedades de Dato Adicional
 * 
 * @author Aramis Oswaldo Gadea Gerónimo &lt;agadea@uanlmexico.mx&gt;
 * @category Beans
 */
public class DatoAdicionalBean {
	private String informacionAdicional;
	private String valor;
	private String obligatorio;
	private String entidad;
	private String codigoLinea;
	private String idGrpPd;
	private String codigoOtroIdExtAc;
	private String indicadorEstructura;
	private String codInfAdicAc;
	private String codRlaCuo;
	private String codOtrsPerSac;
	private String codInfAdiOtPeAc;
	private String entityType;
	private String oldValue;
	private Boolean inputSwitch;
	private Boolean deshabilitado;
	private Boolean requerido;
	private BigDecimal importe;

	/**
	 * Obtiene el valor de la propiedad Información Adicional
	 * 
	 * @return Descripción del dato adicional
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;agadea@uanlmexico.mx&gt;
	 */
	public String getInformacionAdicional() {
		return informacionAdicional;
	}

	/**
	 * Establece el valor de la propiedad Información Adicional
	 * 
	 * @param informacionAdicional
	 *            Descripción del dato adicional
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;agadea@uanlmexico.mx&gt;
	 */
	public void setInformacionAdicional(String informacionAdicional) {
		this.informacionAdicional = informacionAdicional;
	}

	/**
	 * Obtiene el valor de la propiedad Valor
	 * 
	 * @return El valor del dato adicional
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;agadea@uanlmexico.mx&gt;
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * Establece el valor de la propiedad Valor
	 * 
	 * @param valor
	 * 
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;agadea@uanlmexico.mx&gt;
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	/**
	 * Obtiene el valor de la propiedad obligatorio
	 * 
	 * @return {@link String} con la descripción del campo
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt;
	 */
	public String getObligatorio() {
		return obligatorio;
	}

	/**
	 * Establece el valor de la propiedad obligatorio
	 * 
	 * @param value
	 *            {@link String} con la descripción del campo
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt;
	 */
	public void setObligatorio(String value) {
		this.obligatorio = value;
	}

	/**
	 * Obtiene el valor de la propiedad entidad
	 * 
	 * @return {@link String} con la descripción del campo
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt;
	 */
	public String getEntidad() {
		return entidad;
	}

	/**
	 * Establece el valor de la propiedad entidad
	 * 
	 * @param entidad
	 *            {@link String} con la descripción del campo
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt;
	 */
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	/**
	 * Obtiene el valor de la propiedad codigoLinea
	 * 
	 * @return {@link String} con la descripción del campo
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt;
	 */
	public String getCodigoLinea() {
		return codigoLinea;
	}

	/**
	 * Establece el valor de la propiedad codigoLinea
	 * 
	 * @param codigoLinea
	 *            {@link String} con la descripción del campo
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt;
	 */
	public void setCodigoLinea(String codigoLinea) {
		this.codigoLinea = codigoLinea;
	}

	/**
	 * Obtiene el valor de la propiedad idGrpPd
	 * 
	 * @return {@link String} con la descripción del campo
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt;
	 */
	public String getIdGrpPd() {
		return idGrpPd;
	}

	/**
	 * Establece el valor de la propiedad idGrpPd
	 * 
	 * @param idGrpPd
	 *            {@link String} con la descripción del campo
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt;
	 */
	public void setIdGrpPd(String idGrpPd) {
		this.idGrpPd = idGrpPd;
	}

	/**
	 * Obtiene el valor de la propiedad codigoOtroIdExtAc
	 * 
	 * @return {@link String} con la descripción del campo
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt;
	 */
	public String getCodigoOtroIdExtAc() {
		return codigoOtroIdExtAc;
	}

	/**
	 * Establece el valor de la propiedad codigoOtroIdExtAc
	 * 
	 * @param codigoOtroIdExtAc
	 *            {@link String} con la descripción del campo
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt;
	 */
	public void setCodigoOtroIdExtAc(String codigoOtroIdExtAc) {
		this.codigoOtroIdExtAc = codigoOtroIdExtAc;
	}

	/**
	 * Obtiene el valor de la propiedad indicadorEstructura
	 * 
	 * @return {@link String} con la descripción del campo
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt;
	 */
	public String getIndicadorEstructura() {
		return indicadorEstructura;
	}

	/**
	 * Establece el valor de la propiedad indicadorEstructura
	 * 
	 * @param indicadorEstructura
	 *            {@link String} con la descripción del campo
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt;
	 */
	public void setIndicadorEstructura(String indicadorEstructura) {
		this.indicadorEstructura = indicadorEstructura;
	}

	/**
	 * Obtiene el valor de la propiedad codInfAdicAc
	 * 
	 * @return {@link String} con la descripción del campo
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt;
	 */
	public String getCodInfAdicAc() {
		return codInfAdicAc;
	}

	/**
	 * Establece el valor de la propiedad codInfAdicAc
	 * 
	 * @param codInfAdicAc
	 *            {@link String} con la descripción del campo
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt;
	 */
	public void setCodInfAdicAc(String codInfAdicAc) {
		this.codInfAdicAc = codInfAdicAc;
	}

	/**
	 * Obtiene el valor de la propiedad codRlaCuo
	 * 
	 * @return {@link String} con la descripción del campo
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt;
	 */
	public String getCodRlaCuo() {
		return codRlaCuo;
	}

	/**
	 * Establece el valor de la propiedad codRlaCuo
	 * 
	 * @param codRlaCuo
	 *            {@link String} con la descripción del campo
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt;
	 */
	public void setCodRlaCuo(String codRlaCuo) {
		this.codRlaCuo = codRlaCuo;
	}

	/**
	 * Obtiene el valor de la propiedad codOtrsPerSac
	 * 
	 * @return {@link String} con la descripción del campo
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt;
	 */
	public String getCodOtrsPerSac() {
		return codOtrsPerSac;
	}

	/**
	 * Establece el valor de la propiedad codOtrsPerSac
	 * 
	 * @param codOtrsPerSac
	 *            {@link String} con la descripción del campo
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt;
	 */
	public void setCodOtrsPerSac(String codOtrsPerSac) {
		this.codOtrsPerSac = codOtrsPerSac;
	}

	/**
	 * Obtiene el valor de la propiedad codInfAdiOtPeAc
	 * 
	 * @return {@link String} con la descripción del campo
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt;
	 */
	public String getCodInfAdiOtPeAc() {
		return codInfAdiOtPeAc;
	}

	/**
	 * Establece el valor de la propiedad codInfAdiOtPeAc
	 * 
	 * @param codInfAdiOtPeAc
	 *            {@link String} con la descripción del campo
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt;
	 */
	public void setCodInfAdiOtPeAc(String codInfAdiOtPeAc) {
		this.codInfAdiOtPeAc = codInfAdiOtPeAc;
	}

	/**
	 * Obtiene el valor de la propiedad entityType
	 * 
	 * @return nombre de la entidad con la cual se actualizará el valor
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt;
	 */
	public String getEntityType() {
		return entityType;
	}

	/**
	 * Establece el valor de la propiedad entityType
	 * 
	 * @param entityType
	 *            nombre de la entidad con la cual se actualizará el valor
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt;
	 */
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	/**
	 * Obitene el valor de la propiedad oldValue
	 * 
	 * @return oldValue último valor del dato adicional
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt;
	 */
	public String getOldValue() {
		return oldValue;
	}

	/**
	 * Establece el valor de la propiedad oldValue
	 * 
	 * @param oldValue
	 *            último valor del dato adicional
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt;
	 */
	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getInformacionAdicionalString() {
		if (informacionAdicional == null || informacionAdicional.equals(""))
			return "N/D";

		if (informacionAdicional.length() <= 20)
			return informacionAdicional;

		return informacionAdicional.substring(0, 19);
	}

	/**
	 * 
	 * @return Valor para emplear en el inputSwitch de la vista
	 */
	public Boolean getInputSwitch() {
		return inputSwitch;
	}

	/**
	 * 
	 * @param inputSwitch valor inputSwitch a asignar
	 */
	public void setInputSwitch(Boolean inputSwitch) {
		this.inputSwitch = inputSwitch;
	}

	/**
	 * @return the deshabilitado
	 */
	public Boolean getDeshabilitado() {
		return deshabilitado;
	}

	/**
	 * @param deshabilitado the deshabilitado to set
	 */
	public void setDeshabilitado(Boolean deshabilitado) {
		this.deshabilitado = deshabilitado;
	}

	/**
	 * @return the importe
	 */
	public BigDecimal getImporte() {
		return importe;
	}

	/**
	 * @param importe the importe to set
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	/**
	 * @return the requerido
	 */
	public Boolean getRequerido() {
		return requerido;
	}

	/**
	 * @param requerido the requerido to set
	 */
	public void setRequerido(Boolean requerido) {
		this.requerido = requerido;
	}
}