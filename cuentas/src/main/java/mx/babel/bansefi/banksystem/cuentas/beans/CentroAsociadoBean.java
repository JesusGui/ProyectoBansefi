package mx.babel.bansefi.banksystem.cuentas.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CentroAsociadoBean implements Serializable {

	private String tipoRelacion;
	private String centro;
	private Date fechaInicio, FechaFin, cambioEfectivo;
	private String centroActualizar,codRel;
	
	

	private static final long serialVersionUID = -8076243779647217474L;

	/**
	 * Obtiene el valor de la propiedad tipo_relacion
	 * 
	 * @return tipo_relacion
	 * @author Natalio Iván Mendoza Sánchez &lt;nmendoza@uanlmexico.mx&gt;
	 */
	public String getCentro() {
		return centro;
	}

	/**
	 * @return the cambioEfectivo
	 */
	public Date getCambioEfectivo() {
		return cambioEfectivo;
	}

	/**
	 * @param cambioEfectivo
	 *            the cambioEfectivo to set
	 */
	public void setCambioEfectivo(Date cambioEfectivo) {
		this.cambioEfectivo = cambioEfectivo;
	}

	/**
	 * Obtiene el valor de la propiedad tipo de relacion
	 * 
	 * @param centro
	 *            the centro to set
	 * @author Natalio Iván Mendoza Sánchez &lt;nmendoza@uanlmexico.mx&gt;
	 */
	public void setCentro(String centro) {
		this.centro = centro;
	}

	/**
	 * Obtiene el valor de la propiedad tipo_relacion
	 * 
	 * @return tipo_relacion
	 * @author Natalio Iván Mendoza Sánchez &lt;nmendoza@uanlmexico.mx&gt;
	 */
	public String getTipoRelacion() {
		return tipoRelacion;
	}

	/**
	 * @return the fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio
	 *            the fechaInicio to set
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechaFin
	 */
	public Date getFechaFin() {
		return FechaFin;
	}

	/**
	 * @param fechaFin
	 *            the fechaFin to set
	 */
	public void setFechaFin(Date fechaFin) {
		FechaFin = fechaFin;
	}

	public void setTipoRelacion(String tipoRelacion) {
		this.tipoRelacion = tipoRelacion;
	}

	/**
	 * @return the centroActualizar
	 */
	public String getCentroActualizar() {
		return centroActualizar;
	}

	/**
	 * @param centroActualizar
	 *            the centroActualizar to set
	 */
	public void setCentroActualizar(String centroActualizar) {
		this.centroActualizar = centroActualizar;
	}
	
	
	/**
	 * @return the fechainicioString
	 */
	public String getFechainicioString() {
		return  new SimpleDateFormat("dd/MM/yyyy").format(fechaInicio);
	}

	/**
	 * @return the fechafinString
	 */
	public String getFechafinString() {
		return  new SimpleDateFormat("dd/MM/yyyy").format(FechaFin);
	}

	/**
	 * @return the codRel
	 */
	public String getCodRel() {
		return codRel;
	}

	/**
	 * @param codRel the codRel to set
	 */
	public void setCodRel(String codRel) {
		this.codRel = codRel;
	}

}
