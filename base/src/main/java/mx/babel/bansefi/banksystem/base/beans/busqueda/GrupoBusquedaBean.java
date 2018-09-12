package mx.babel.bansefi.banksystem.base.beans.busqueda;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.SelectItem;

import mx.babel.arq.comun.annotations.CampoBusquedaAnnotation;
import mx.babel.arq.comun.annotations.CampoResultadoAnnotation;
import mx.babel.arq.comun.annotations.NavegaAnnotation;

import org.springframework.stereotype.Component;

/**
 * Bean de Grupo para la búsqueda.
 * 
 * @author alejandro.pineda
 * 
 */
@Component
@NavegaAnnotation(campoEnum = "FICHA_CLIENTE")
public class GrupoBusquedaBean implements Serializable {

	private static final long serialVersionUID = -4118253879659629831L;

	@CampoBusquedaAnnotation(requerido = true, desplegar = 0, regex = "/^[a-zA-ZÑñ ]*$/", longitud = 30)
	@CampoResultadoAnnotation(posicion = 1, parametro = true)
	private String nombre;

	@CampoBusquedaAnnotation(requerido = true, desplegar = 1, regex = "/^[0-9a-zA-Z]*$/", longitud = 14)
	@CampoResultadoAnnotation(posicion = 4, parametro = true, key = true)
	private String idInterna;

	@CampoBusquedaAnnotation(requerido = true, desplegar = 1, tituloCampo = "Tipo Grupo", nombreCatalogo = "TP_GRP")
	private List<SelectItem> tipoGrupoCatalogo;

	@CampoResultadoAnnotation(posicion = 3)
	private String fechaConstitutiva;

	@CampoResultadoAnnotation(posicion = 2)
	private String tipoGrupo;

	@CampoResultadoAnnotation(desplegar = -1, parametro = true)
	private String codGrupo;

	public GrupoBusquedaBean() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIdInterna() {
		return idInterna;
	}

	public void setIdInterna(String idInterna) {
		this.idInterna = idInterna;
	}

	public List<SelectItem> getTipoGrupoCatalogo() {
		return tipoGrupoCatalogo;
	}

	public void setTipoGrupoCatalogo(List<SelectItem> tipoGrupoCatalogo) {
		this.tipoGrupoCatalogo = tipoGrupoCatalogo;
	}

	public String getTipoGrupo() {
		return tipoGrupo;
	}

	public void setTipoGrupo(String tipoGrupo) {
		this.tipoGrupo = tipoGrupo;
	}

	public String getFechaConstitutiva() {
		return fechaConstitutiva;
	}

	public void setFechaConstitutiva(String fechaConstitutiva) {
		this.fechaConstitutiva = fechaConstitutiva;
	}

	public String getCodGrupo() {
		return codGrupo;
	}

	public void setCodGrupo(String codGrupo) {
		this.codGrupo = codGrupo;
	}

}