package mx.babel.bansefi.banksystem.base.beans.domain;

import java.util.Comparator;

/**
 * Comparator para ordenar una lista de bienes
 * En primer lugar compara si las clases son iguales, en ese caso ordena alfabeticamente
 * por el tipo de descripcion
 * En caso contrario ordena por clase
 * @author luis.gcastellano
 *
 */
public class BienBeanComparator implements Comparator<BienBean> {
    @Override
    public int compare(BienBean a, BienBean b) {
    	if(a.getClase().equals((b.getClase()))){
    		return a.getTipoDesc().compareTo(b.getTipoDesc());
    	}
    	
        return a.getClase().compareTo(b.getClase());
    }
}