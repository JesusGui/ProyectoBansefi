package mx.babel.bansefi.banksystem.base.utils;

import java.util.Comparator;

import mx.babel.bansefi.banksystem.base.beans.domain.AnotacionBean;

/**
 * Comparador de anotaciones por prioridad
 * @author mario.montesdeoca
 *
 */
public class AnotacionPrioridadComparator implements Comparator<AnotacionBean>{

	@Override
    public int compare(AnotacionBean a, AnotacionBean b){
		try{
			Integer prioridadA = Integer.valueOf(a.getPrioridad());
			Integer prioridadB = Integer.valueOf(b.getPrioridad());
			if(prioridadA.equals(prioridadB)){
				return a.getTipo().compareTo(b.getTipo());
			}
			return prioridadA.compareTo(prioridadB);
		}catch(NumberFormatException | NullPointerException nfe){
			return -1;
		}
	}
}
