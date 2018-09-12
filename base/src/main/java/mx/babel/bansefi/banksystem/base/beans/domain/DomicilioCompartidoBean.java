package mx.babel.bansefi.banksystem.base.beans.domain;

import java.io.Serializable;
import java.util.Date;

import mx.babel.bansefi.banksystem.base.utils.FechaUtils;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Clase para almacenar la respuesta de los domicilios compartidos
 * 
 * @author manuel.nieto
 * 
 */
public class DomicilioCompartidoBean implements Serializable {

	private static final long serialVersionUID = -1935761946125434494L;
	
	private String nombre;
	private String idInternoPersona;
	private String idExternoPersona;
	private Date fechaNacimientoConstitucion;
	private String codigoOficina;
	private String codigoPersona;
	private String codigoIdExternoPersona;
	private ClienteBean clienteBean;

	private static final Logger LOGGER = LogManager
			.getLogger(DomicilioCompartidoBean.class);

	/**
	 * Funcion que regresa la fecha en un formato yy/mm/yyyy
	 * 
	 * @return String fecha
	 */
	public String fechaToString() {
		return FechaUtils.formatFecha(this.fechaNacimientoConstitucion,
				"dd/MM/yyyy");
	}	

	/**
	 * Funcion que dependiendo del codigo de persona retorna un string
	 * especificando el tipo de persona
	 * 
	 * @return
	 */
	public String getTipoPersonaToString() {
		if (this.codigoPersona != null) {
			switch (this.codigoPersona) {
			case "F":
				return "FÍSICA";
			case "J":
				return "MORAL";
			default:
				return null;
			}
		} else {
			return null;
		}
	}
	
	/**
	 * Funcion que especifica si es una persona fisica o no
	 * 
	 * @return
	 */
	public boolean isPersonaFisica() {
		if (this.codigoPersona != null) {
			if (("F").equals(this.codigoPersona)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Funcion que especifica si es una persona moral o no
	 * 
	 * @return
	 */
	public boolean isPersonaMoral() {
		if (this.codigoPersona != null) {
			if (("J").equals(this.codigoPersona)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIdInternoPersona() {
		return idInternoPersona;
	}

	public void setIdInternoPersona(String idInternoPersona) {
		this.idInternoPersona = idInternoPersona;
	}

	public String getIdExternoPersona() {
		return idExternoPersona;
	}

	public void setIdExternoPersona(String idExternoPersona) {
		this.idExternoPersona = idExternoPersona;
	}

	public Date getFechaNacimientoConstitucion() {
		return fechaNacimientoConstitucion;
	}

	public void setFechaNacimientoConstitucion(Date fechaNacimientoConstitucion) {
		this.fechaNacimientoConstitucion = fechaNacimientoConstitucion;
	}

	public String getCodigoOficina() {
		return codigoOficina;
	}

	public void setCodigoOficina(String codigoOficina) {
		this.codigoOficina = codigoOficina;
	}

	public String getCodigoPersona() {
		return codigoPersona;
	}

	public void setCodigoPersona(String codigoPersona) {
		this.codigoPersona = codigoPersona;
	}

	public String getCodigoIdExternoPersona() {
		return codigoIdExternoPersona;
	}

	public void setCodigoIdExternoPersona(String codigoIdExternoPersona) {
		this.codigoIdExternoPersona = codigoIdExternoPersona;
	}

	public ClienteBean getClienteBean() {
		return clienteBean;
	}

	public void setClienteBean(ClienteBean clienteBean) {
		this.clienteBean = clienteBean;
	}

	@Override
    public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj,
				new String[] { "serialVersionUID" });
	}
    
    @Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this,
				new String[] { "serialVersionUID" });
	}
}
