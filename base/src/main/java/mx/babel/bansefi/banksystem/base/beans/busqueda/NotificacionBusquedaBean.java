package mx.babel.bansefi.banksystem.base.beans.busqueda;

import java.io.Serializable;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import mx.babel.arq.comun.annotations.CampoBusquedaAnnotation;
import mx.babel.arq.comun.annotations.CampoResultadoAnnotation;
import mx.babel.arq.comun.annotations.NavegaAnnotation;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;

/**
 * Bean de notificación para la búsqueda.
 * 
 * @author omar.marquez
 * 
 */
@Component
@NavegaAnnotation(campoEnum = "DETALLE_NOTIFICACION")
public class NotificacionBusquedaBean implements Serializable {

	private static final long serialVersionUID = 7094433962918574212L;

	// Entrada
	@CampoBusquedaAnnotation(desplegar = -2, tituloCampo = "Entidad")
	private String entidad;

	@CampoBusquedaAnnotation(requerido = true, desplegar = 1, tituloCampo = "Centro", longitud = 45, nombreCatalogo = ConstantesFuncionales.CATALOGO_CENTROS, tipo = "autocompletarcentros")
	private List<SelectItem> centroCatalogo;

	@CampoBusquedaAnnotation(tituloCampo = "Mostrar sólo urgentes")
	private boolean filtroNotificacionUrgente;

	@CampoBusquedaAnnotation(tituloCampo = "Mostrar sólo activas")
	private boolean filtroNotificacionActiva;

	@CampoBusquedaAnnotation(tituloCampo = "Periodo de validez", placeHolder = "Desde")
	private Date filtroFechaDesde;

	@CampoBusquedaAnnotation(tituloCampo = ".", placeHolder = "Hasta")
	private Date filtroFechaHasta;

	// Salida
	@CampoResultadoAnnotation(posicion = 0, parametro = true, tituloColumna = "URGENTE")
	private String indUrgencia;

	@CampoResultadoAnnotation(posicion = 1, parametro = true, tituloColumna = "ACTIVA")
	private String indEstado;

	@CampoResultadoAnnotation(desplegar = -1, parametro = true)
	private Date fechaVigenteDesde;

	@CampoResultadoAnnotation(desplegar = -1, parametro = true)
	private Date fechaVigenteHasta;

	@CampoResultadoAnnotation(posicion = 2, parametro = true, tituloColumna = "PERIODO DE VALIDEZ")
	private String periodoValidez;

	@CampoResultadoAnnotation(posicion = 3, parametro = true, tituloColumna = "TEXTO")
	private String texto;

	@CampoResultadoAnnotation(posicion = 4, parametro = true, tituloColumna = "CENTRO")
	private String centro;

	@CampoResultadoAnnotation(posicion = 5, parametro = true, key = true, tituloColumna = "ID. NOTIFICACIÓN")
	private BigInteger clave;

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public List<SelectItem> getCentroCatalogo() {
		return centroCatalogo;
	}

	public void setCentroCatalogo(List<SelectItem> centroCatalogo) {
		this.centroCatalogo = centroCatalogo;
	}

	public boolean isFiltroNotificacionUrgente() {
		return filtroNotificacionUrgente;
	}

	public void setFiltroNotificacionUrgente(boolean filtroNotificacionUrgente) {
		this.filtroNotificacionUrgente = filtroNotificacionUrgente;
	}

	public boolean isFiltroNotificacionActiva() {
		return filtroNotificacionActiva;
	}

	public void setFiltroNotificacionActiva(boolean filtroNotificacionActiva) {
		this.filtroNotificacionActiva = filtroNotificacionActiva;
	}

	public Date getFiltroFechaDesde() {
		return filtroFechaDesde;
	}

	public void setFiltroFechaDesde(Date filtroFechaDesde) {
		this.filtroFechaDesde = filtroFechaDesde;
	}

	public void setFiltroFechaDesde() {
		try {
			this.filtroFechaDesde = DateUtils.parseDate(
					ConstantesFuncionales.MIN_FECHA_INICIO,
					ConstantesFuncionales.GENERAL_DATE_FORMATTER);
		} catch (ParseException e) {
			this.filtroFechaDesde = Calendar.getInstance().getTime();
		}
	}

	public Date getFiltroFechaHasta() {
		return filtroFechaHasta;
	}

	public void setFiltroFechaHasta(Date filtroFechaHasta) {
		this.filtroFechaHasta = filtroFechaHasta;
	}

	public void setFiltroFechaHasta() {
		try {
			this.filtroFechaHasta = DateUtils.parseDate(
					ConstantesFuncionales.MAX_FECHA_FIN,
					ConstantesFuncionales.GENERAL_DATE_FORMATTER);
		} catch (ParseException e) {
			this.filtroFechaHasta = new Date(Long.MAX_VALUE);
		}
	}

	public String getIndUrgencia() {
		return indUrgencia;
	}

	public void setIndUrgencia(String indUrgencia) {
		this.indUrgencia = indUrgencia;
	}

	public String getIndEstado() {
		return indEstado;
	}

	public void setIndEstado(String indEstado) {
		this.indEstado = indEstado;
	}

	public Date getFechaVigenteDesde() {
		return fechaVigenteDesde;
	}

	public void setFechaVigenteDesde(Date fechaVigenteDesde) {
		this.fechaVigenteDesde = fechaVigenteDesde;
	}

	public Date getFechaVigenteHasta() {
		return fechaVigenteHasta;
	}

	public void setFechaVigenteHasta(Date fechaVigenteHasta) {
		this.fechaVigenteHasta = fechaVigenteHasta;
	}

	public String getPeriodoValidez() {
		return periodoValidez;
	}

	public void setPeriodoValidez(String periodoValidez) {
		this.periodoValidez = periodoValidez;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getCentro() {
		return centro;
	}

	public void setCentro(String centro) {
		this.centro = centro;
	}

	public BigInteger getClave() {
		return clave;
	}

	public void setClave(BigInteger clave) {
		this.clave = clave;
	}

}