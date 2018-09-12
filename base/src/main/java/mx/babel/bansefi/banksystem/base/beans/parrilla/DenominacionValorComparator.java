package mx.babel.bansefi.banksystem.base.beans.parrilla;

import java.util.Comparator;

/**
 * Calse para realizar ordenamiento de listas con denominaciones y valores. 
 * @author mario.montesdeoca
 *
 */
public class DenominacionValorComparator implements Comparator<ExistenciaDenominacionBean>{

	/**
	 * Mètodo que compara dos beans de donominaciones por su valor y el tipo.
	 */
	@Override
	public int compare(ExistenciaDenominacionBean a, ExistenciaDenominacionBean b) {
		if(b.getValor().equals(a.getValor())){
			if(b.getMoneda() != a.getMoneda()){
				if(!a.getMoneda()){
					return -1;
				}else{
					return 1;
				}
			}
		}
		return b.getValor().compareTo(a.getValor());
	}

}
