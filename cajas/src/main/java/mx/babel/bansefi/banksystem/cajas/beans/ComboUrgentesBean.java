package mx.babel.bansefi.banksystem.cajas.beans;

import java.io.Serializable;
import java.util.List;

public class ComboUrgentesBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7776161046609746801L;

	private List<PeticionUrgenteBean> listaIndicadoresUrgentes;
	
	public List<PeticionUrgenteBean> getListaIndicadoresUrgentes() {
		return listaIndicadoresUrgentes;
	}

	public void setListaIndicadoresUrgentes(
			List<PeticionUrgenteBean> listaIndicadoresUrgentes) {
		this.listaIndicadoresUrgentes = listaIndicadoresUrgentes;
	}
}
