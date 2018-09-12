package mx.babel.bansefi.banksystem.cuentas.utils;

import java.io.Serializable;

import mx.babel.bansefi.banksystem.cuentas.enums.DialogoListadoBean;
import mx.babel.bansefi.banksystem.cuentas.enums.DialogoRelacionesCuentaListadoEnum;

/**
 * Clase para lógica de mensajes de guardado de listados
 * @author mario.montesdeoca
 *
 */
public class DialogoRelacionesCuentaListadoUtils implements Serializable{

	private static final long serialVersionUID = 4444987835864507715L;
	
	private static final String  CANCELAR = "¡Atención! Si continua perderá todos los cambios. ¿Desea continuar de todos modos?";

	private Integer elementos;
	
	private Integer nuevos;
	
	private Integer eliminados;
	
	private Integer editados;
	
	private Boolean validacion;
	
	public DialogoRelacionesCuentaListadoUtils(Integer elementos){
		this.elementos = elementos;
		this.eliminados = 0;
		this.editados = 0;
		this.nuevos = 0;
		this.validacion = false;
	}
	
	/**
	 * Mensajes desplegados en dialogo
	 * @param cosas nombre de elementos de vista
	 * @param numero numero de elementos
	 * @return mensaje a desplegar
	 */
	private static String mensajePorEliminar(String cosas, Integer numero){
    	return  numero + " " + cosas ;
    }
	
	/**
	 * Mensajes desplegados en dialogo
	 * @param cosas nombre de elementos de vista
	 * @return mensaje a desplegar
	 */
	private static String mensajeModificacion(String cosas){
    	return "Las "+ cosas +" se modificaron correctamente.";
    }
	
	/**
	 * Mensajes desplegados en dialogo
	 * @param cosas nombre de elementos de vista
	 * @return mensaje a desplegar
	 */
	private static String mensajeAlta(String cosas){
    	return "Las "+ cosas +" se dieron de alta correctamente.";
    }
	
	/**
	 * Mensajes desplegados en dialogo
	 * @param cosas nombre de elementos de vista
	 * @return mensaje a desplegar
	 */
	private static String mensajeEliminar(String cosas){
    	return "Las "+ cosas +" se eliminaron correctamente.";
    }
	
	/**
	 * Mensajes desplegados en dialogo
	 * @return mensaje a desplegar
	 */
	private static String mensajeSinCambios(){
    	return "No se han realizado cambios.";
    }
	
	/**
	 * Mensajes desplegados en dialogo
	 * @return mensaje a desplegar
	 */
	private static String mensajeFalla(){
    	return "";
    }
	
	/**
	 * @return Atributo elementos
	 */
	public Integer getElementos() {
		return elementos;
	}

	/**
	 * @param elementos Atributo elementos a definir
	 */
	public void setElementos(Integer elementos) {
		this.elementos = elementos;
	}

	/**
	 * @return Atributo nuevos
	 */
	public Integer getNuevos() {
		return nuevos;
	}

	/**
	 * @param nuevos Atributo nuevos a definir
	 */
	public void setNuevos(Integer nuevos) {
		this.nuevos = nuevos;
	}

	/**
	 * @return Atributo eliminados
	 */
	public Integer getEliminados() {
		return eliminados;
	}

	/**
	 * @param eliminados Atributo eliminados a definir
	 */
	public void setEliminados(Integer eliminados) {
		this.eliminados = eliminados;
	}

	/**
	 * @return Atributo editados
	 */
	public Integer getEditados() {
		return editados;
	}

	/**
	 * @param editados Atributo editados a definir
	 */
	public void setEditados(Integer editados) {
		this.editados = editados;
	}
	
	/**
	 * @return Atributo validacion
	 */
	public Boolean getValidacion() {
		return validacion;
	}
	
	/**
	 * @param validacion Atributo validacion a definir
	 */
	public void setValidacion(Boolean validacion) {
		this.validacion = validacion;
	}

	public void adicionaEliminado(){
		eliminados++;
	}
	
	public void adicionaEditado(){
		editados++;
	}
	
	public void adicionaNuevo(){
		nuevos++;
	}
	
	public void restaEliminado(){
		eliminados--;
	}
	
	public void restaEditado(){
		editados--;
	}
	
	public void restaNuevo(){
		nuevos--;
	}
	
	/**
	 * Método para seleccionar el mensaje apropiado a los movimientos
	 * @param cosas nombre de elementos en la vista
	 * @return enum con propiedades del dialogo
	 */
	public DialogoListadoBean getMensaje(String cosas){
		DialogoListadoBean dialogoListadoBean = new DialogoListadoBean();
		if(getEditados() == 0 && getEliminados() == 0 && getNuevos() == 0){
			dialogoListadoBean.setEstado(DialogoRelacionesCuentaListadoEnum.SIN_CAMBIOS);
			dialogoListadoBean.setMensaje(mensajeSinCambios());
		}else{
			dialogoListadoBean.setEstado(DialogoRelacionesCuentaListadoEnum.MODIFICACION);
			dialogoListadoBean.setMensaje(mensajeModificacion(cosas));
			if(getEditados() == 0 && getEliminados() == 0 && getNuevos() >0){
				dialogoListadoBean.setEstado(DialogoRelacionesCuentaListadoEnum.ALTA);
				dialogoListadoBean.setMensaje(mensajeAlta(cosas));
			}
			if(getEditados() == 0 && getNuevos() == 0 && getEliminados() >0){
				dialogoListadoBean.setEstado(DialogoRelacionesCuentaListadoEnum.ELIMINAR);
				dialogoListadoBean.setMensaje(mensajeEliminar(cosas));
			}
		}
		dialogoListadoBean.setMostrar(true);
		return dialogoListadoBean;
	}
	
	/**
	 * Mensaje para confimarción de eliminar cosas en listado
	 * @param cosas nombre de elementos desplegados en la vista
	 * @return enum con detalles de la vista
	 */
	public DialogoListadoBean getMensajePorEliminar(String cosas){
		DialogoListadoBean dialogoListadoBean = new DialogoListadoBean();
		dialogoListadoBean.setEstado(DialogoRelacionesCuentaListadoEnum.CONFIRMA_ELIMINAR);
		dialogoListadoBean.setMensaje(mensajePorEliminar(cosas, getEliminados()));
		dialogoListadoBean.setMostrar(true);
		return dialogoListadoBean;
	}
	
	/**
	 * Mensaje para cenviar error en transacción 
	 * @return enum con detalles de la vista
	 */
	public DialogoListadoBean getMensajeFalla(){
		DialogoListadoBean dialogoListadoBean = new DialogoListadoBean();
		dialogoListadoBean.setEstado(DialogoRelacionesCuentaListadoEnum.ERROR);
		dialogoListadoBean.setMensaje(mensajeFalla());
		dialogoListadoBean.setMostrar(true);
		return dialogoListadoBean;
	}
	
	/**
	 * Método para saber si el listado tiene cambios.
	 * @return
	 */
	public Boolean tieneCambios(){
		if(getEditados() > 0 || getEliminados() > 0 || getNuevos() > 0){ 
			return true;
		}
		return false;
	}
	
	/**
	 * Método para saber si el listado tiene cambios.
	 * @return
	 */
	public Boolean listadoCorrecto(Boolean validacionListado){
		if(this.validacion){
			return validacionListado;
		}
		return true;
	}
	
	/**
	 * Mensajes desplegados en dialogo
	 * @return mensaje a desplegar
	 */
	public static DialogoListadoBean mensajeCancelar(){
		DialogoListadoBean dialogoListadoBean = new DialogoListadoBean();
		dialogoListadoBean.setEstado(DialogoRelacionesCuentaListadoEnum.ALERTA);
		dialogoListadoBean.setMensaje(CANCELAR);
		dialogoListadoBean.setMostrar(true);
    	return dialogoListadoBean;
    }
}
