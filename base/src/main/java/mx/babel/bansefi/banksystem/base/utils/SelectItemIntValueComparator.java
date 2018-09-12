package mx.babel.bansefi.banksystem.base.utils;

import java.util.Comparator;

import javax.faces.model.SelectItem;

/**
 * Clase que permite comparar dos objetos tipo SelectItem por su valor. Esta
 * clase sirve para poder ordenar una colección numérica.
 * 
 * @author gerard.chavez
 * 
 */
public class SelectItemIntValueComparator implements Comparator<SelectItem> {

	@Override
	public int compare(SelectItem s1, SelectItem s2) {
		return ((Integer) s1.getValue()).compareTo((Integer) s2.getValue());
	}

}