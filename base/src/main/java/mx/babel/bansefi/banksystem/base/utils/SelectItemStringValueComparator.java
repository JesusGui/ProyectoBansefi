package mx.babel.bansefi.banksystem.base.utils;

import java.util.Comparator;
import javax.faces.model.SelectItem;

/**
 * Clase que permite comparar dos objetos tipo SelectItem por su descripción.
 * Esta clase sirve para poder ordenar una colección alfabéticamente.
 * 
 * @author omar.marquez
 * 
 */
public class SelectItemStringValueComparator implements Comparator<SelectItem> {

	@Override
	public int compare(SelectItem s1, SelectItem s2) {
		return s1.getLabel().compareTo(s2.getLabel());
	}
	
}