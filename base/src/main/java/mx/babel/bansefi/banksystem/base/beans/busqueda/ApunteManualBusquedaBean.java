package mx.babel.bansefi.banksystem.base.beans.busqueda;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import mx.babel.arq.comun.annotations.CampoBusquedaAnnotation;
import mx.babel.arq.comun.annotations.CampoResultadoAnnotation;
import mx.babel.arq.comun.annotations.NavegaAnnotation;
import mx.babel.arq.comun.beans.PaginacionBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;

/**
 * Clase que define el modelo para la busqueda
 * de Apuntes Manuales
 * @author manuel.nieto
 *
 */
@NavegaAnnotation(campoEnum = "RESUMEN_APUNTE_MANUAL")
public class ApunteManualBusquedaBean extends PaginacionBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2529268598923144747L;

	//Entrada
	@CampoBusquedaAnnotation(desplegar = -2, tituloCampo = "Apunte")
	private String entidad;
	
	@CampoResultadoAnnotation(parametro = true, desplegar= -3)
	@CampoBusquedaAnnotation(requerido = true, tituloCampo = "Cuenta Contable", longitud = 45, tipo = "autocompletarcuentascontables")
	private String idCuentaContable;

	@CampoBusquedaAnnotation(tituloCampo = "Rango de fechas", requerido = true)
	private Date fechaInicio;
	
	@CampoBusquedaAnnotation(requerido = true)
	private Date fechaFin;
	
	//Salida
	@CampoResultadoAnnotation(posicion = 1, parametro = true, tituloColumna = "FECHA OPERACIÓN")
	private String fechaOperacion;
	
	@CampoResultadoAnnotation(posicion = 2, parametro = true, tituloColumna = "FECHA CONTABLE")
	private String fechaContable;
	
	@CampoResultadoAnnotation(posicion = 3, parametro = true, key = true, tituloColumna = "NO. APUNTE")
	private long noApunte;
	
	@CampoResultadoAnnotation(posicion = 4, parametro = true, tituloColumna = "ORIGEN")
	private String origen;
	
	@CampoResultadoAnnotation(posicion = 5, parametro = true, tituloColumna = "DESTINO")
	private String destino;
	
	@CampoResultadoAnnotation(posicion = 6, parametro = true, tituloColumna = "IMPORTE")
	private BigDecimal importe;
	
	@CampoResultadoAnnotation(posicion = 7, parametro = true, tituloColumna = "D/H")
	private String debeHaber;
	
	@CampoResultadoAnnotation(posicion = 8, tituloColumna = "SITUACIÓN")
	private String situacion;
	
	@CampoResultadoAnnotation(posicion = 9, parametro = true, tituloColumna = "HORA")
	private String hora;
	
	@CampoResultadoAnnotation(posicion = 10, tituloColumna = "TERMINAL")
	private String terminal;
	
	@CampoResultadoAnnotation(posicion = 11, parametro = true, tituloColumna = "CONCEPTO")
	private String concepto;
	
	@CampoResultadoAnnotation(posicion = 12, parametro = true, tituloColumna = "CONTRAPARTIDA")
	private String contraPartida;
	
	@CampoResultadoAnnotation(posicion = 13, parametro = true, tituloColumna = "CUENTA")
	private String cuenta;
	
	@CampoResultadoAnnotation(posicion = 14, parametro = true, tituloColumna = "CLAVE OPERACIÓN")
	private String claveOperacion;
	
	@CampoResultadoAnnotation(posicion = 15, parametro = true, tituloColumna = "NO. SECUENCIA")
	private String noSecuencia;

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public String getIdCuentaContable() {
		return idCuentaContable;
	}

	public void setIdCuentaContable(String idCuentaContable) {
		this.idCuentaContable = idCuentaContable;
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

	public String getFechaOperacion() {
		return fechaOperacion;
	}

	public void setFechaOperacion(String fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}

	public String getFechaContable() {
		return fechaContable;
	}

	public void setFechaContable(String fechaContable) {
		this.fechaContable = fechaContable;
	}

	public long getNoApunte() {
		return noApunte;
	}

	public void setNoApunte(long noApunte) {
		this.noApunte = noApunte;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public String getDebeHaber() {
		return debeHaber;
	}

	public void setDebeHaber(String debeHaber) {
		this.debeHaber = debeHaber;
	}

	public String getSituacion() {
		return situacion;
	}

	public void setSituacion(String situacion) {
		this.situacion = situacion;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getContraPartida() {
		return contraPartida;
	}

	public void setContraPartida(String contraPartida) {
		this.contraPartida = contraPartida;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getClaveOperacion() {
		return claveOperacion;
	}

	public void setClaveOperacion(String claveOperacion) {
		this.claveOperacion = claveOperacion;
	}

	public String getNoSecuencia() {
		return noSecuencia;
	}

	public void setNoSecuencia(String noSecuencia) {
		this.noSecuencia = noSecuencia;
	}

}
