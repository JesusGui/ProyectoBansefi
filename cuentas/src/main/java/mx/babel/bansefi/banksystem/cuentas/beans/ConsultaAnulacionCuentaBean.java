package mx.babel.bansefi.banksystem.cuentas.beans;

import java.io.Serializable;
import java.util.List;

import mx.babel.bansefi.banksystem.base.beans.ApuntesBean;

/**
 * Bean que se utiliza para almacenar la informacion proveniente de la 
 * consulta de anulacion de cuentas: TR_CONSU_ANULAR_AC_TRN
 * @author manuel.nieto
 *
 */
public class ConsultaAnulacionCuentaBean implements Serializable{
	
	private static final long serialVersionUID = -7658133330885961882L;
	List<ApuntesBean> listaApuntes;

	public List<ApuntesBean> getListaApuntes() {
		return listaApuntes;
	}

	public void setListaApuntes(List<ApuntesBean> listaApuntes) {
		this.listaApuntes = listaApuntes;
	}	
	
}
