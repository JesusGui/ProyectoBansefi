package mx.babel.bansefi.banksystem.base.beans.parrilla;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ReflectionUtils;

import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;

/**
 * Bean para controlar una parrilla
 * @author mario.montesdeoca
 *
 */
public class ParrillaBean implements Serializable{
	
	private static final long serialVersionUID = 5142959217013723713L;
	
	private static final String SEPARADOR_DENOMINACIONES = ":";
	
	private static final String DENOMINACION_MONEDA ="m";
	
	private static final String FORMATO_MONEDA = "E";
	
	private static final String ORIGEN_VENTANILLA = "V";
	
	private static final String ORIGEN_DISPENSADOR = "D";
	
	private Integer filtro = 0;
	
	private List<ExistenciaDenominacionBean> listaDenominaciones;
	
	private List<ColumnaParrillaEnum> columnas;
	
	private ColumnaParrillaEnum importeAEditar = ColumnaParrillaEnum.IMPORTE_MODIFICABLE;

	/**
	 * @return Atributo filtro
	 */
	public Integer getFiltro() {
		return filtro;
	}

	/**
	 * @param filtro Atributo filtro a definir
	 */
	public void setFiltro(Integer filtro) {
		this.filtro = filtro;
	}

	/**
	 * @return Atributo listaDenominaciones
	 */
	public List<ExistenciaDenominacionBean> getListaDenominaciones() {
		if(listaDenominaciones == null){
			listaDenominaciones = new ArrayList<ExistenciaDenominacionBean>();
		}
		return listaDenominaciones;
	}

	/**
	 * @param listaDenominaciones Atributo listaDenominaciones a definir
	 */
	public void setListaDenominaciones(
			List<ExistenciaDenominacionBean> listaDenominaciones) {
		this.listaDenominaciones = listaDenominaciones;
	}
	
	/**
	 * @return Atributo columnas
	 */
	public List<ColumnaParrillaEnum> getColumnas() {
		if(columnas == null){
			columnas = new ArrayList<ColumnaParrillaEnum>();
		}
		return columnas;
	}

	/**
	 * @param columnas Atributo columnas a definir
	 */
	public void setColumnas(List<ColumnaParrillaEnum> columnas) {
		this.columnas = columnas;
	}

	/**
	 * @return Atributo importeAEditar
	 */
	public ColumnaParrillaEnum getImporteAEditar() {
		return importeAEditar;
	}

	/**
	 * @param importeAEditar Atributo importeAEditar a definir
	 */
	public void setImporteAEditar(ColumnaParrillaEnum importeAEditar) {
		this.importeAEditar = importeAEditar;
	}

	/**
	 * Método para incializar los valores en la parrilla.
	 * @param dispensador <code>true</code> en el caso de querer adicionar denominaciones de dispensador.
	 */
	public void iniciaParrilla(Boolean dispensador){
		this.listaDenominaciones = new ArrayList<ExistenciaDenominacionBean>();
		for (String denominacion : ConstantesFuncionales.getValorDenominaciones()) {
			String [] val = denominacion.split(SEPARADOR_DENOMINACIONES);
			Boolean moneda = val[1].equals(DENOMINACION_MONEDA);
			BigDecimal valor = new BigDecimal(val[0]);
			creaDenominacion(FORMATO_MONEDA, valor, null, 
					moneda, ORIGEN_VENTANILLA);
			if(dispensador && !moneda){
				creaDenominacion(FORMATO_MONEDA, valor, null, 
						moneda, ORIGEN_DISPENSADOR);
			}
		}
		this.ordenaParrilla();
	}
	
	/**
	 * Método para ordenar las denominaciones contenidas en la parrilla
	 */
	public void ordenaParrilla(){
		Collections.sort(this.getListaDenominaciones(),new DenominacionValorComparator());
	}
	
	/**
	 * Método utilizado para filtrar las denominaciones mostradas en la parrilla
	 * @param denominacion Denominación a evaluar
	 * @return <code>true</code> si la denominación debe ser mostrada en la parrilla
	 */
	public Boolean isVisible(ExistenciaDenominacionBean denominacion){
		switch (filtro) {
			case 0:
				return true;
			case 1:
				return ORIGEN_VENTANILLA.equals(denominacion.getOrigen());
			case 2:
				return ORIGEN_DISPENSADOR.equals(denominacion.getOrigen());
			case 3:
				return !denominacion.getMoneda();
			case 4:
				return denominacion.getMoneda();
		}
		return false;
	}
	
	/**
	 * Método que actualiza el importe de una denominación en función a las unidades ingresadas.
	 * @param denominacion Denomincación a evaluar
	 */
	public void actualizaImporte(ExistenciaDenominacionBean denominacion, String nombreImporte){ 	
		if(denominacion.getValor() != null && denominacion.getUnidades() != null){
			BigDecimal importe = denominacion.getValor().multiply(new BigDecimal(denominacion.getUnidades()));
			if(!StringUtils.isEmpty(nombreImporte)){
				try{
					Field field = ReflectionUtils.findField(ExistenciaDenominacionBean.class, nombreImporte);
					ReflectionUtils.makeAccessible(field);
					ReflectionUtils.setField(field, denominacion, importe);
				}catch(NullPointerException | IllegalArgumentException | IllegalStateException e){
				}
			}else{
				denominacion.setImporteModificable(importe);
			}
		}
	}
	
	/**
	 * Método que actualiza las unidades de una denominación en función al importe ingresado.
	 * @param denominacion Denomincación a evaluar
	 */
	public void actualizaUnidades(ExistenciaDenominacionBean denominacion, BigDecimal importe){
		if(denominacion.getValor() != null && importe != null){
			Long unidades = importe.divide(denominacion.getValor()).longValue();
			denominacion.setUnidades(unidades);
		}
	}
	
	/**
	 * Método para limpiar la parrilla
	 */
	public void limpiarParrilla(){
		for (ExistenciaDenominacionBean denominacion : this.getListaDenominaciones()) {
			denominacion.setImporteModificable(null);
			denominacion.setUnidades(null);
		}
	}
	
	/**
	 * Método para crear una denominación dentro de la lista de denominaciones.
	 * @param formato formato de la moneda
	 * @param valor valor de la denominación
	 * @param unidades unidades
	 * @param moneda <code>true</code> en caso de no ser billete
	 * @param origen Origen V para ventanilla o D para dispensador
	 */
	public ExistenciaDenominacionBean creaDenominacion(String formato, 
			BigDecimal valor, Long unidades, Boolean moneda, String origen){
		ExistenciaDenominacionBean denominacionBean = new ExistenciaDenominacionBean();
		denominacionBean.setFormato(formato);
		denominacionBean.setValor(valor);
		denominacionBean.setUnidades(unidades);
		denominacionBean.setMoneda(moneda);
		denominacionBean.setOrigen(origen);
		this.getListaDenominaciones().add(denominacionBean);
		return denominacionBean;
	}
	
}