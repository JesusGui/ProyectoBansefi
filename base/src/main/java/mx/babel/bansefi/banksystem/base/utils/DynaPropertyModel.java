package mx.babel.bansefi.banksystem.base.utils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

/**
 * @author gerard.chavez
 * 
 */
public class DynaPropertyModel implements Serializable {

	private static final long serialVersionUID = 2932297094438858130L;

	private String nombre;
	private String tituloCampo;
	private boolean requerido;
	private Object valor;
	private Object longitud;
	private String validador;
	private String placeHolder;
	private List<SelectItem> selectItems;

	public DynaPropertyModel() {
	}

	public DynaPropertyModel(String nombre, boolean requerido) {
		this.nombre = nombre;
		this.requerido = requerido;
	}

	public DynaPropertyModel(String nombre, boolean requerido, Object valor,
			String validador) {
		this.nombre = nombre;
		this.requerido = requerido;
		this.valor = valor;
		this.validador = validador;
	}

	public Object getFormattedValue() {
		if (valor instanceof Date) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"d MMM yyyy");
			return simpleDateFormat.format(valor);
		}

		return valor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isRequerido() {
		return requerido;
	}

	public void setRequerido(boolean requerido) {
		this.requerido = requerido;
	}

	public Object getValor() {
		return valor;
	}

	public void setValor(Object valor) {
		this.valor = valor;
	}

	public Object getLongitud() {
		return longitud;
	}

	public void setLongitud(Object longitud) {
		this.longitud = longitud;
	}

	public String getValidador() {
		return validador;
	}

	public void setValidador(String validador) {
		this.validador = validador;
	}

	public List<SelectItem> getSelectItems() {
		return selectItems;
	}

	public void setSelectItems(List<SelectItem> selectItems) {
		this.selectItems = selectItems;
	}

	public String getTituloCampo() {
		return tituloCampo;
	}

	public void setTituloCampo(String tituloCampo) {
		this.tituloCampo = tituloCampo;
	}

	public String getPlaceHolder() {
		return placeHolder;
	}

	public void setPlaceHolder(String placeHolder) {
		this.placeHolder = placeHolder;
	}

}