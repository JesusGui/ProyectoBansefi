package mx.babel.bansefi.banksystem.cajas.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;

public class ParrillaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5676695183308601630L;
    private static final String ORIGEN_VENTANILLA = "V";
	
	private static final String ORIGEN_DISPENSADOR = "D";
	
	private Integer filtro = 0;
	
	private List<ExistenciaDenominacionBean> listaDenominaciones;
	private List<ExistenciaDenominacionBean> listaDesglose;
	
	
   public List<ExistenciaDenominacionBean> getListaDesglose() {
		return listaDesglose;
	}

	public void setListaDesglose(List<ExistenciaDenominacionBean> listaDesglose) {
		this.listaDesglose = listaDesglose;
	}

public Integer getFiltro() {
		return filtro;
	}

	public void setFiltro(Integer filtro) {
		this.filtro = filtro;
	}

	public List<ExistenciaDenominacionBean> getListaDenominaciones() {
		return listaDenominaciones;
	}

	public void setListaDenominaciones(
			List<ExistenciaDenominacionBean> listaDenominaciones) {
		this.listaDenominaciones = listaDenominaciones;
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
			case 5:
				if(denominacion.getImportePedido()==null)
				{return false;}
				else
				{
				return validaValor(denominacion.getImportePedido());}
		}
		return false;
	}
	
	/**
	 * Método utilizado para filtrar las denominaciones mostradas en la parrilla
	 * @param denominacion Denominación a evaluar
	 * @return <code>true</code> si la denominación debe ser mostrada en la parrilla
	 */
	public Boolean isVisibleRecepcion(ExistenciaDenominacionBean denominacion){
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
			case 5:
				if(denominacion.getImporteAutorizado()==null)
				{return false;}
				else
				{
				return validaValor(denominacion.getImporteAutorizado());}
		}
		return false;
	}
	
	private boolean validaValor(BigDecimal importe)
	{
		if(BigDecimal.ZERO.compareTo(importe) < 0)
		{
			return true;
		}
		else
		{return false;}
		
	}


}
