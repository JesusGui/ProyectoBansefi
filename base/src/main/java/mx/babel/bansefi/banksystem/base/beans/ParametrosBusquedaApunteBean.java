package mx.babel.bansefi.banksystem.base.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.movimientos.MovimientoBean;

/**
 * Clase que define los parámetros de entrada-salida utilizados para la busqueda
 * de apuntes.
 * 
 * @author omar.marquez
 * 
 */
public class ParametrosBusquedaApunteBean implements Serializable {

	private static final long serialVersionUID = 8459983193660687557L;

	// Variables de entrada.
	private CuentaBean cuentaBean;
	private String filtroNaturaleza;
	private String filtroTipoCuenta;
	private Date filtroFechaDesde;
	private Date filtroFechaHasta;

	// Variables de salida.
	private List<MovimientoBean> apuntes;
	private MovimientoBean apunteSeleccionado;
	private boolean masDatos;
	private int pagina;

	/**
	 * Constructor
	 */
	public ParametrosBusquedaApunteBean() {
		super();
	}

	/**
	 * Método que devuelve un objeto tipo CuentaBean.
	 * 
	 * @return cuentaBean
	 */
	public CuentaBean getCuentaBean() {
		return cuentaBean;
	}

	/**
	 * Método que establece un objeto tipo CuentaBean.
	 * 
	 * @param cuentaBean
	 */
	public void setCuentaBean(CuentaBean cuentaBean) {
		this.cuentaBean = cuentaBean;
	}

	/**
	 * Método que devuelve la clave correspondiente a la naturaleza de la
	 * cuenta.
	 * 
	 * @return filtroNaturaleza
	 */
	public String getFiltroNaturaleza() {
		return filtroNaturaleza;
	}

	/**
	 * Método que establece la clave de la naturaleza de la cuenta.
	 * 
	 * @param filtroNaturaleza
	 */
	public void setFiltroNaturaleza(String filtroNaturaleza) {
		this.filtroNaturaleza = filtroNaturaleza;
	}

	/**
	 * Método que devuelve la clave con el tipo de cuenta seleccionada
	 * (cboTipoCuenta referenciado en la vista).
	 * 
	 * @return filtroTipoCuenta
	 */
	public String getFiltroTipoCuenta() {
		return filtroTipoCuenta;
	}

	/**
	 * Método que establece la clave con el tipo de cuenta seleccionada.
	 * 
	 * @param filtroTipoCuenta
	 */
	public void setFiltroTipoCuenta(String filtroTipoCuenta) {
		this.filtroTipoCuenta = filtroTipoCuenta;
	}

	/**
	 * Método que devuelve la fecha de inicio para la búsqueda
	 * (calFiltroFechaDesde referenciado en la vista).
	 * 
	 * @return filtroFechaDesde
	 */
	public Date getFiltroFechaDesde() {
		return filtroFechaDesde;
	}

	/**
	 * Método que establece la fecha de inicio para la búsqueda.
	 * 
	 * @param filtroFechaDesde
	 */
	public void setFiltroFechaDesde(Date filtroFechaDesde) {
		this.filtroFechaDesde = filtroFechaDesde;
	}

	/**
	 * Método que devuelve la fecha de término para la búsqueda
	 * (calFiltroFechaHasta referenciado en la vista).
	 * 
	 * @return filtroFechaHasta
	 */
	public Date getFiltroFechaHasta() {
		return filtroFechaHasta;
	}

	/**
	 * Método que establece la fecha de término para la búsqueda.
	 * 
	 * @param filtroFechaHasta
	 */
	public void setFiltroFechaHasta(Date filtroFechaHasta) {
		this.filtroFechaHasta = filtroFechaHasta;
	}

	/**
	 * Método que devuelve una lista de apuntes / movimientos.
	 * 
	 * @return apuntes
	 */
	public List<MovimientoBean> getApuntes() {
		return apuntes;
	}

	/**
	 * Método que establece una lista de apuntes / movimientos.
	 * 
	 * @param apuntes
	 */
	public void setApuntes(List<MovimientoBean> apuntes) {
		this.apuntes = apuntes;
	}

	/**
	 * Método que devuelve un objeto tipo MovimientoBean de acuerdo a la
	 * selección del usuario.
	 * 
	 * @return apunteSeleccionado
	 */
	public MovimientoBean getApunteSeleccionado() {
		return apunteSeleccionado;
	}

	/**
	 * Método que establece un objeto tipo MovimientoBean de acuerdo a la
	 * selección del usuario.
	 * 
	 * @param apunteSeleccionado
	 */
	public void setApunteSeleccionado(MovimientoBean apunteSeleccionado) {
		this.apunteSeleccionado = apunteSeleccionado;
	}

	/**
	 * Método que devuelve un indicador booleano para determinar sí existen más
	 * datos.
	 * 
	 * @return indicador booleano.
	 */
	public boolean isMasDatos() {
		return masDatos;
	}

	/**
	 * Método que establece el valor del indicador para determinar sí existen
	 * más datos.
	 * 
	 * @param masDatos
	 */
	public void setMasDatos(boolean masDatos) {
		this.masDatos = masDatos;
	}

	/**
	 * Método que devuelve el número de elementos a mostrar por página.
	 * 
	 * @return pagina
	 */
	public int getPagina() {
		return pagina;
	}

	/**
	 * Método que establece el número de elementos a mostrar por página.
	 * 
	 * @param pagina
	 */
	public void setPagina(int pagina) {
		this.pagina = pagina;
	}

}