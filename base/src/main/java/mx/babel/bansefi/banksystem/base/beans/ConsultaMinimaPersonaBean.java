package mx.babel.bansefi.banksystem.base.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import mx.babel.bansefi.banksystem.base.utils.FechaUtils;

public class ConsultaMinimaPersonaBean implements Serializable {

	private static final long serialVersionUID = -2527137898511195809L;
	private int idInternoPersona;
	private String tipoIdentificacion;
	private String numIdentificacion;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String nombrePila;
	private String codigoCNO;
	private String denominacionLegal;
	private String codigoEstructuraLegal;
	private String codigoCNAE;
	private String nombreLocalidad;
	private String nombreProvincia;
	private String nombrePais;
	private String codigoOficina;
	private String codigoPostal;
	private String domicilio;
	private String sexo;
	private Date fechaNacimientoConstitucion;
	private List<String> telefonos;

	/**
	 * Funcion que devuelve los telefonos en un solo String
	 * 
	 * @return
	 */
	public String getTelefonosToString() {
		boolean primero = false;
		String telefonoStr = "";
		for (String telefono : this.telefonos) {
			if (!StringUtils.isBlank(telefono)) {
				if (!primero) {
					primero = true;
					telefonoStr += telefono;
				} else {
					telefonoStr += " / " + telefono;
				}
			}
		}
		return telefonoStr;
	}

	/**
	 * Funcion que devuelve la fecha del año de nacimiento
	 * 
	 * @return
	 */
	public String getFechaNacCons() {
		return FechaUtils.formatFecha(this.fechaNacimientoConstitucion, "dd/MM/yyyy");
	}

	/**
	 * Funcion que dependiendo de la inicial que tiene la variable de sexo
	 * retorna el sexo completo.
	 * 
	 * @return
	 */
	public String getSexoToString() {
		if (("M").equals(this.sexo)) {
			return "Mujer";
		} else {
			return "Hombre";
		}
	}

	public int getIdInternoPersona() {
		return idInternoPersona;
	}

	public void setIdInternoPersona(int idInternoPersona) {
		this.idInternoPersona = idInternoPersona;
	}

	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public String getNumIdentificacion() {
		return numIdentificacion;
	}

	public void setNumIdentificacion(String numIdentificacion) {
		this.numIdentificacion = numIdentificacion;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getNombrePila() {
		return nombrePila;
	}

	public void setNombrePila(String nombrePila) {
		this.nombrePila = nombrePila;
	}

	public String getCodigoCNO() {
		return codigoCNO;
	}

	public void setCodigoCNO(String codigoCNO) {
		this.codigoCNO = codigoCNO;
	}

	public String getDenominacionLegal() {
		return denominacionLegal;
	}

	public void setDenominacionLegal(String denominacionLegal) {
		this.denominacionLegal = denominacionLegal;
	}

	public String getCodigoEstructuraLegal() {
		return codigoEstructuraLegal;
	}

	public void setCodigoEstructuraLegal(String codigoEstructuraLegal) {
		this.codigoEstructuraLegal = codigoEstructuraLegal;
	}

	public String getCodigoCNAE() {
		return codigoCNAE;
	}

	public void setCodigoCNAE(String codigoCNAE) {
		this.codigoCNAE = codigoCNAE;
	}

	public String getNombreLocalidad() {
		return nombreLocalidad;
	}

	public void setNombreLocalidad(String nombreLocalidad) {
		this.nombreLocalidad = nombreLocalidad;
	}

	public String getNombreProvincia() {
		return nombreProvincia;
	}

	public void setNombreProvincia(String nombreProvincia) {
		this.nombreProvincia = nombreProvincia;
	}

	public String getNombrePais() {
		return nombrePais;
	}

	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}

	public String getCodigoOficina() {
		return codigoOficina;
	}

	public void setCodigoOficina(String codigoOficina) {
		this.codigoOficina = codigoOficina;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getFechaNacimientoConstitucion() {
		return fechaNacimientoConstitucion;
	}

	public void setFechaNacimientoConstitucion(Date fechaNacimientoConstitucion) {
		this.fechaNacimientoConstitucion = fechaNacimientoConstitucion;
	}

	public List<String> getTelefonos() {
		return telefonos;
	}

	public void setTelefonos(List<String> telefonos) {
		this.telefonos = telefonos;
	}

}
