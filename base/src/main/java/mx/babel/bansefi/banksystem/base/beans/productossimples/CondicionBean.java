package mx.babel.bansefi.banksystem.base.beans.productossimples;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang3.StringUtils;

/**
 * @author joseluis.pena
 *
 */
public class CondicionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String clave;
	private String descripcion;

	private String estado;
	private Date fechaInicioValidez;
	private Date fechaFinValidez;
	private Date fechaEstadoActual;

	private final String tipoCondicion;
	private String idCcps;
	private String codProfCd;
	private String codEstrctCd;
	private String indCdAc;
	private boolean loaded;


	public CondicionBean(final String tipoCondicion) {
		super();
		this.tipoCondicion = tipoCondicion;
		this.loaded = false;
	}

	public CondicionBean() {
		super();
		this.tipoCondicion = null;
		this.loaded = false;
	}

	/**
	 * @return the tipoCondicion
	 */
	public String getTipoCondicion() {
		return tipoCondicion;
	}

	/**
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * @param clave
	 *            the clave to set
	 */
	public void setClave(final String clave) {
		this.clave = clave;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(final String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(final String estado) {
		this.estado = estado;
	}

	/**
	 * @return the fechaFinValidez
	 */
	public Date getFechaFinValidez() {
		return fechaFinValidez;
	}

	/**
	 * @param fechaFinValidez
	 *            the fechaFinValidez to set
	 */
	public void setFechaFinValidez(final Date fechaFinValidez) {
		this.fechaFinValidez = fechaFinValidez;
	}

	/**
	 * @return the fechaFinValidez
	 */
	public Date getFechaFinValidezFormateada() {
		final SimpleDateFormat format = new SimpleDateFormat(
				ConstantesFuncionales.GENERAL_DATE_FORMATTER);
		if(fechaFinValidez != null){
    		final String fechaFin = format.format(fechaFinValidez);
    		if (StringUtils.equals(ConstantesFuncionales.MAX_FECHA_FIN, fechaFin)) {
    			return null;
    		}
		}
		return fechaFinValidez;
	}

	/**
	 * @param fechaFinValidez
	 *            the fechaFinValidez to set
	 */
	public void setFechaFinValidezFormateada(final Date fechaFinValidez) {
	    if(fechaFinValidez != null){
			this.fechaFinValidez = fechaFinValidez;
	    }
	}

	/**
	 * @return the fechaEstadoActual
	 */
	public Date getFechaEstadoActual() {
		return fechaEstadoActual;
	}

	/**
	 * @param fechaEstadoActual
	 *            the fechaEstadoActual to set
	 */
	public void setFechaEstadoActual(final Date fechaEstadoActual) {
		this.fechaEstadoActual = fechaEstadoActual;
	}

	/**
	 * @return the loaded
	 */
	public boolean isLoaded() {
		return loaded;
	}

	/**
	 * @param loaded
	 *            the loaded to set
	 */
	public void setLoaded(final boolean loaded) {
		this.loaded = loaded;
	}

	/**
	 * @return the fechaInicioValidez
	 */
	public Date getFechaInicioValidez() {
		return fechaInicioValidez;
	}

	/**
	 * @param fechaInicioValidez
	 *            the fechaInicioValidez to set
	 */
	public void setFechaInicioValidez(final Date fechaInicioValidez) {
		this.fechaInicioValidez = fechaInicioValidez;
	}

	/**
	 * @return the idCcps
	 */
	public String getIdCcps() {
		return idCcps;
	}

	/**
	 * @param idCcps
	 *            the idCcps to set
	 */
	public void setIdCcps(final String idCcps) {
		this.idCcps = idCcps;
	}

	/**
	 * @return the codProfCd
	 */
	public String getCodProfCd() {
		return codProfCd;
	}

	/**
	 * @param codProfCd
	 *            the codProfCd to set
	 */
	public void setCodProfCd(final String codProfCd) {
		this.codProfCd = codProfCd;
	}

	/**
	 * @return the codEstrctCd
	 */
	public String getCodEstrctCd() {
		return codEstrctCd;
	}

	/**
	 * @param codEstrctCd
	 *            the codEstrctCd to set
	 */
	public void setCodEstrctCd(final String codEstrctCd) {
		this.codEstrctCd = codEstrctCd;
	}

	/**
	 * @return the indCdAc
	 */
	public String getIndCdAc() {
		return indCdAc;
	}

	/**
	 * @param indCdAc
	 *            the indCdAc to set
	 */
	public void setIndCdAc(final String indCdAc) {
		this.indCdAc = indCdAc;
	}

	public boolean isEditable() {
		return StringUtils.equals(ConstantesFuncionales.CONDICION_MODIFICABLE,
				this.codProfCd);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(clave).append(descripcion)
				.append(estado).append(fechaInicioValidez)
				.append(fechaFinValidez).append(fechaEstadoActual)
				.append(tipoCondicion).append(idCcps)
				.append(codProfCd).append(codEstrctCd).append(indCdAc)
				.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof CondicionBean)) {
			return false;
		}
		final CondicionBean castObj = (CondicionBean) obj;
		return new EqualsBuilder().append(clave, castObj.clave)
				.append(descripcion, castObj.descripcion)
				.append(estado, castObj.estado)
				.append(fechaInicioValidez, castObj.fechaInicioValidez)
				.append(fechaFinValidez, castObj.fechaFinValidez)
				.append(fechaEstadoActual, castObj.fechaEstadoActual)
				.append(tipoCondicion, castObj.tipoCondicion)
				.append(idCcps, castObj.idCcps)
				.append(codProfCd, castObj.codProfCd)
				.append(codEstrctCd, castObj.codEstrctCd)
				.append(indCdAc, castObj.indCdAc).isEquals();
	}

	protected void compareBigDecimal(final EqualsBuilder eq,
			final BigDecimal obj1, final BigDecimal obj2) {
		if (obj1 != null && obj2 != null) {
			eq.appendSuper(obj1.compareTo(obj2) == 0);
		} else {
			eq.append(obj1, obj2);
		}
	}

}
