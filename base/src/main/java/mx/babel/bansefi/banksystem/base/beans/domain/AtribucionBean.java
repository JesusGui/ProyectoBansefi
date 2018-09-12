package mx.babel.bansefi.banksystem.base.beans.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Clase para definir el modelo de Atribuciones.
 * @author javier.martinnino
 * 
 */
public class AtribucionBean implements Serializable {
	
	private static final long serialVersionUID = -6733603819722958573L;
	
	private BigDecimal porcPasivo;
	private BigDecimal porcActivo;
	private BigDecimal porcDesintermediacion;
	private BigDecimal porcServicios;
	private BigDecimal porcRiesgoProducto;
	private BigDecimal porcRiesgoCliente;
	private BigDecimal porcRiesgoGarantiaReal;
	private BigDecimal porcRiesgoGarantiaPersonal;
	
	private Boolean autorizacionPerfBR;
	private Boolean autorizacionPerfMR;
	private Boolean autorizacionPerfAR;
	
	private Boolean autorizacionSE;
	private Boolean autorizacionMP;
	
	private String nivelAtribucion;
	private Integer diasValor;
	
	private Date fechaInicio;
	private Date fechaFin;
	
	private EstadoListadosEnum estado;
    private String respaldo;
	
	public BigDecimal getPorcPasivo() {
		return porcPasivo;
	}
	public void setPorcPasivo(BigDecimal porcPasivo) {
		this.porcPasivo = porcPasivo;
	}
	public BigDecimal getPorcActivo() {
		return porcActivo;
	}
	public void setPorcActivo(BigDecimal porcActivo) {
		this.porcActivo = porcActivo;
	}
	public BigDecimal getPorcDesintermediacion() {
		return porcDesintermediacion;
	}
	public void setPorcDesintermediacion(BigDecimal porcDesintermediacion) {
		this.porcDesintermediacion = porcDesintermediacion;
	}
	public BigDecimal getPorcServicios() {
		return porcServicios;
	}
	public void setPorcServicios(BigDecimal porcServicios) {
		this.porcServicios = porcServicios;
	}
	public BigDecimal getPorcRiesgoProducto() {
		return porcRiesgoProducto;
	}
	public void setPorcRiesgoProducto(BigDecimal porcRiesgoProducto) {
		this.porcRiesgoProducto = porcRiesgoProducto;
	}
	public BigDecimal getPorcRiesgoCliente() {
		return porcRiesgoCliente;
	}
	public void setPorcRiesgoCliente(BigDecimal porcRiesgoCliente) {
		this.porcRiesgoCliente = porcRiesgoCliente;
	}
	public BigDecimal getPorcRiesgoGarantiaReal() {
		return porcRiesgoGarantiaReal;
	}
	public void setPorcRiesgoGarantiaReal(BigDecimal porcRiesgoGarantiaReal) {
		this.porcRiesgoGarantiaReal = porcRiesgoGarantiaReal;
	}
	public BigDecimal getPorcRiesgoGarantiaPersonal() {
		return porcRiesgoGarantiaPersonal;
	}
	public void setPorcRiesgoGarantiaPersonal(BigDecimal porcRiesgoGarantiaPersonal) {
		this.porcRiesgoGarantiaPersonal = porcRiesgoGarantiaPersonal;
	}
	public Boolean getAutorizacionPerfBR() {
		return autorizacionPerfBR;
	}
	public void setAutorizacionPerfBR(Boolean autorizacionPerfBR) {
		this.autorizacionPerfBR = autorizacionPerfBR;
	}
	public Boolean getAutorizacionPerfMR() {
		return autorizacionPerfMR;
	}
	public void setAutorizacionPerfMR(Boolean autorizacionPerfMR) {
		this.autorizacionPerfMR = autorizacionPerfMR;
	}
	public Boolean getAutorizacionPerfAR() {
		return autorizacionPerfAR;
	}
	public void setAutorizacionPerfAR(Boolean autorizacionPerfAR) {
		this.autorizacionPerfAR = autorizacionPerfAR;
	}
	public Boolean getAutorizacionSE() {
		return autorizacionSE;
	}
	public void setAutorizacionSE(Boolean autorizacionSE) {
		this.autorizacionSE = autorizacionSE;
	}
	public Boolean getAutorizacionMP() {
		return autorizacionMP;
	}
	public void setAutorizacionMP(Boolean autorizacionMP) {
		this.autorizacionMP = autorizacionMP;
	}
	public String getNivelAtribucion() {
		return nivelAtribucion;
	}
	public void setNivelAtribucion(String nivelAtribucion) {
		this.nivelAtribucion = nivelAtribucion;
	}
	public Integer getDiasValor() {
		return diasValor;
	}
	public void setDiasValor(Integer diasValor) {
		this.diasValor = diasValor;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}	
	public EstadoListadosEnum getEstado() {
        return estado;
    }
    public void setEstado(EstadoListadosEnum estado) {
        this.estado = estado;
    }
    public String getRespaldo() {
        return respaldo;
    }
    public void setRespaldo(String respaldo) {
        this.respaldo = respaldo;
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
		if (!(obj instanceof AtribucionBean)) {
			return false;
		}
		final AtribucionBean castObj = (AtribucionBean) obj;
		final EqualsBuilder eq = new EqualsBuilder()
				.append(autorizacionPerfBR, castObj.autorizacionPerfBR)
				.append(autorizacionPerfMR, castObj.autorizacionPerfMR)
				.append(autorizacionPerfAR, castObj.autorizacionPerfAR)
				.append(autorizacionSE, castObj.autorizacionSE)
				.append(autorizacionMP, castObj.autorizacionMP)
				.append(nivelAtribucion, castObj.nivelAtribucion)
				.append(diasValor, castObj.diasValor)
				.append(estado, castObj.estado)
				.append(respaldo, castObj.respaldo);	
		
		 this.compareBigDecimal(eq, porcPasivo, castObj.porcPasivo);
		 this.compareBigDecimal(eq, porcActivo, castObj.porcActivo);
		 this.compareBigDecimal(eq, porcDesintermediacion, castObj.porcDesintermediacion);
		 this.compareBigDecimal(eq, porcServicios, castObj.porcServicios);
		 this.compareBigDecimal(eq, porcRiesgoProducto, castObj.porcRiesgoProducto);
		 this.compareBigDecimal(eq, porcRiesgoCliente, castObj.porcRiesgoCliente);
		 this.compareBigDecimal(eq, porcRiesgoGarantiaReal, castObj.porcRiesgoGarantiaReal);
		 this.compareBigDecimal(eq, porcRiesgoGarantiaPersonal, castObj.porcRiesgoGarantiaPersonal);
 		return eq.isEquals();
		
	}
	
	@Override
	public int hashCode(){
		return HashCodeBuilder.reflectionHashCode(this,
				new String[] { "serialVersionUID" });
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
