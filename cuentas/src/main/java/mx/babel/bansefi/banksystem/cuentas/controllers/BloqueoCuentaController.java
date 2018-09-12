package mx.babel.bansefi.banksystem.cuentas.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.backends.ConsultaBloqueosBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaCuentaBackend;
import mx.babel.bansefi.banksystem.base.backends.busquedas.BusquedaCuentaBackEnd;
import mx.babel.bansefi.banksystem.base.backends.consultatitular.ConsultaTitularBackend;
import mx.babel.bansefi.banksystem.base.beans.busqueda.CuentaBusquedaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.movimientos.BloqueoBean;
import mx.babel.bansefi.banksystem.base.beans.movimientos.MovimientoBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.BusquedaEnum;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;
import mx.babel.bansefi.banksystem.cuentas.backends.AltaBloqueoCuentaBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.BajaBloqueoCuentaBackend;
import mx.babel.bansefi.banksystem.cuentas.beans.TarifaBean;
import mx.babel.bansefi.banksystem.cuentas.utils.TarifasUtils;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 * 
 */
@ManagedBean
@Component
@Scope("view")
public class BloqueoCuentaController {

	private static final String TITULAR = "titular";
	private List<MovimientoBean> bloqueos;
	private List<MovimientoBean> bloqueosPaginacion;
	private String nombreTitular;
	private Long numCuenta;
	private String centroCuenta;
	private String situacion;
	private MovimientoBean bloqueoSeleccionado;
	private boolean paginacionActiva;
	private boolean masBloqueos;
	private MovimientoBean nuevoBloqueo;
	private boolean busquedaActiva;
	private TarifaBean tarifa;
	private CuentaBean cuenta;
	private boolean isTodos;

	private String msgBloqueoManual;

	@Autowired
	ConsultaBloqueosBackEnd consultaBloqueosBackEnd;

	@Autowired
	ConsultaTitularBackend consultaTitularBackend;

	@Autowired
	AltaBloqueoCuentaBackend altaBloqueoCuentaBackend;

	@Autowired
	BajaBloqueoCuentaBackend bajaBloqueoCuentaBackend;

	@Autowired
	CatalogoUtils catalogoUtils;

	@Autowired
	ContextoUtils contextoUtils;

	@Autowired
	ManagedBeanStateRecover managedBeanStateRecover;

	@Autowired
	ConsultaCuentaBackend consultaCuentaBackend;

	@Autowired
	TarifasUtils tarifaUtils;

	@Autowired
	BusquedaCuentaBackEnd busquedaCuentaBackEnd;
	/**
	 * Enum para definir a donde se redireccionará al seleccionar un objeto de
	 * la tabla de resultados.
	 */
	private NavegacionEnum destino;
	private Object destinoController;

	public void inicioConsultaBloqueos() {

		final Flash flash = obtenerFlash();
		if (flash.get(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash()) != null) {
			cuenta = (CuentaBean) flash
					.get(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash());
			this.numCuenta = cuenta.getNumeroCuenta();
			this.nombreTitular = cuenta.getNombreTitular();

		} else if (flash.get(ParametrosFlashEnum.NUMERO_CUENTA.getParamFlash()) != null
				&& flash.get(ParametrosFlashEnum.TITULAR_CUENTA.getParamFlash()) != null) {
			this.numCuenta = (Long) flash.get(ParametrosFlashEnum.NUMERO_CUENTA
					.getParamFlash());
			this.nombreTitular = (String) flash
					.get(ParametrosFlashEnum.TITULAR_CUENTA.getParamFlash());
			if(this.numCuenta != null && this.numCuenta != 0L){
				cuenta = consultaCuentaBackend.ejecutarTRN(this.numCuenta);
			}
		} else if (flash.get(ParametrosFlashEnum.NUMERO_CUENTA.getParamFlash()) != null
				&& flash.get(BloqueoCuentaController.TITULAR) != null) {
			this.numCuenta = (Long) flash.get(ParametrosFlashEnum.NUMERO_CUENTA
					.getParamFlash());
			this.nombreTitular = (String) flash
					.get(BloqueoCuentaController.TITULAR);
			if(this.numCuenta != null && this.numCuenta != 0L){
				cuenta = consultaCuentaBackend.ejecutarTRN(this.numCuenta);
			}
		}
		this.situacion = "0";
		this.consultaBloqueos();
		if (flash.get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN
				.getParamFlash()) != null) {
			destino = managedBeanStateRecover.getDestino();
			destinoController = managedBeanStateRecover.getController();
		} else {
			if (flash.get(ParametrosFlashEnum.DESTINO.getParamFlash()) != null) {
				destino = (NavegacionEnum) flash
						.get(ParametrosFlashEnum.DESTINO.getParamFlash());
			}
			if (flash.get(ParametrosFlashEnum.DESTINO_CONTROLLER
					.getParamFlash()) != null) {
				destinoController = flash
						.get(ParametrosFlashEnum.DESTINO_CONTROLLER
								.getParamFlash());
			}
		}

	}

	public void inicioNuevoBloqueo() {
		final Flash flash = obtenerFlash();
		if (flash.get(ParametrosFlashEnum.DESTINO.getParamFlash()) != null) {
			destino = (NavegacionEnum) flash.get(ParametrosFlashEnum.DESTINO
					.getParamFlash());
		}
		if (flash.get(ParametrosFlashEnum.DESTINO_CONTROLLER.getParamFlash()) != null) {
			destinoController = flash
					.get(ParametrosFlashEnum.DESTINO_CONTROLLER.getParamFlash());
		}
		this.numCuenta = (Long) flash.get(ParametrosFlashEnum.NUMERO_CUENTA
				.getParamFlash());
		this.nombreTitular = (String) flash
				.get(BloqueoCuentaController.TITULAR);
		this.nuevoBloqueo = new MovimientoBean();
		this.nuevoBloqueo.setFechaAlta(contextoUtils.getFechaContableActual());
	}

	/**
	 * Método privado que obtiene el objeto Flash proveniente del contexto
	 * externo.
	 * 
	 * @return Flash
	 */
	private Flash obtenerFlash() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
	}

	/**
	 * @return the bloqueos
	 */
	public List<MovimientoBean> getBloqueos() {
		return bloqueos;
	}

	/**
	 * @param bloqueos
	 *            the bloqueos to set
	 */
	public void setBloqueos(final List<MovimientoBean> bloqueos) {
		this.bloqueos = bloqueos;
	}

	/**
	 * @return the nombreTitular
	 */
	public String getNombreTitular() {
		return nombreTitular;
	}

	/**
	 * @param nombreTitular
	 *            the nombreTitular to set
	 */
	public void setNombreTitular(final String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}

	/**
	 * @return the numCuenta
	 */
	public Long getNumCuenta() {
		return numCuenta;
	}

	/**
	 * @param numCuenta
	 *            the numCuenta to set
	 */
	public void setNumCuenta(final Long numCuenta) {
		this.numCuenta = numCuenta;
	}

	/**
	 * @return Atributo centroCuenta
	 */
	public String getCentroCuenta() {
		return centroCuenta;
	}

	/**
	 * @param centroCuenta Atributo centroCuenta a definir
	 */
	public void setCentroCuenta(String centroCuenta) {
		this.centroCuenta = centroCuenta;
	}

	/**
	 * @return the situacion
	 */
	public String getSituacion() {
		return situacion;
	}

	/**
	 * @param situacion
	 *            the situacion to set
	 */
	public void setSituacion(final String situacion) {
		this.situacion = situacion;
	}

	public void consultaBloqueos() {
		if (StringUtils.isNotBlank(this.nombreTitular)) {
			if (("0").equals(this.situacion)) {
				this.setisTodos(true);
			} else {
				this.setisTodos(false);
			}
			this.bloqueos = new ArrayList<MovimientoBean>();
			final BloqueoBean bloqueoBean = consultaBloqueosBackEnd
					.ejecutarTRN(numCuenta.toString(), this.situacion, null,
							null);

			this.bloqueosPaginacion = bloqueoBean.getBloqueosList();

			for (final MovimientoBean bloqueo : this.bloqueosPaginacion) {
				bloqueo.setSubTipoDescription(this.traducirSubTipo(bloqueo
						.getSubTipo()));
			}
			if (this.bloqueosPaginacion.size() > 9) {
				this.bloqueos.addAll(this.bloqueosPaginacion.subList(0, 10));
			} else {
				this.bloqueos.addAll(this.bloqueosPaginacion);
			}

			this.paginacionActiva = bloqueoBean.isMasDatos();
			this.masBloqueos = bloqueoBean.isMasDatos();
			this.busquedaActiva = true;
		}
	}

	public void consultaMasBloqueos() {
		// Si el nombre del titular no esta, significa que la cuenta con la que
		// buscamos no es valida
		// y no podemos realizar la consulta
		if (StringUtils.isNotBlank(this.nombreTitular)) {
			// Si las dos listas de bloqueos tienen el mismo tamanyo y aun hay
			// mas registros
			// invocamos TRN para obtener mas resultados
			if (this.bloqueos.size() == this.bloqueosPaginacion.size()
					&& this.masBloqueos) {
				String codLastBloq = null;
				Integer numLastBloq = null;
				if (this.bloqueosPaginacion != null
						&& !this.bloqueosPaginacion.isEmpty()) {
					// Tomamos el codigo del ultimo registro para poder invocar
					// la paginacion de la TRN
					final MovimientoBean lastBloq = this.bloqueosPaginacion
							.get(this.bloqueosPaginacion.size() - 1);
					codLastBloq = lastBloq.getSubTipo();
					numLastBloq = lastBloq.getNumeroBloqueo();
				}
				final BloqueoBean bloqueoBean = consultaBloqueosBackEnd
						.ejecutarTRN(numCuenta.toString(), this.situacion,
								codLastBloq, numLastBloq);

				final int paginationInitialListSize = this.bloqueosPaginacion
						.size();

				this.bloqueosPaginacion.addAll(bloqueoBean.getBloqueosList());

				for (final MovimientoBean bloqueo : this.bloqueosPaginacion) {
					bloqueo.setSubTipoDescription(this.traducirSubTipo(bloqueo
							.getSubTipo()));
				}

				// Calculamos cuantos registros nuevos se han anyadido al
				// listado de paginacion
				final int sizeDifference = this.bloqueosPaginacion.size()
						- paginationInitialListSize;
				if (sizeDifference > 9) {
					// Si hemos obtenido 10 o mas registros nuevos, anyadimos
					// los siguientes 10
					// al listado que se visualiza en la vista
					this.bloqueos.addAll(this.bloqueosPaginacion.subList(
							paginationInitialListSize,
							paginationInitialListSize + 10));
				} else {
					// Si hemos obtenido menos de 10 registros nuevos, anyadimos
					// todos los registros obtenidos
					// al listado que se visualiza en la vista
					this.bloqueos.addAll(this.bloqueosPaginacion.subList(
							paginationInitialListSize,
							paginationInitialListSize + sizeDifference));
				}

				this.masBloqueos = bloqueoBean.isMasDatos();
				this.bloqueoSeleccionado = null;
			} else {
				final int size = this.bloqueos.size();
				final int sizeDifference = this.bloqueosPaginacion.size()
						- size;
				// Si la lista de bloqueo tiene un tamanyo menor que la lista de
				// paginacion
				// Cargamos mas datos para visualizar en la vista
				if (sizeDifference > 0) {
					// Si la diferencia entre las listas es mayor de 9
					// resultados
					// añadimos 10 registros nuevos para visualizar en la vista
					if (sizeDifference > 9) {
						this.bloqueos.addAll(this.bloqueosPaginacion.subList(
								size, size + 10));
					} else {
						// Si la diferencia entre las listas es menor o igual a
						// 9 resultados
						// añadimos los registros restantes
						this.bloqueos.addAll(this.bloqueosPaginacion.subList(
								size, size + sizeDifference));
					}
					this.bloqueoSeleccionado = null;
				}
			}
		}
	}

	private String traducirSubTipo(final String subTipo) {
		final CatalogoBean catalogo = catalogoUtils.getCatalogoBean(
				CatalogoEnum.TP_BLOQUEO, subTipo);
		if (null != catalogo
				&& StringUtils.isNotBlank(catalogo.getDescripcionL())) {
			return catalogo.getDescripcionL();
		}
		return subTipo;
	}

	public void consultaTitular() {
		this.bloqueos = null;
		this.bloqueosPaginacion = null;
		this.nombreTitular = null;
		this.bloqueoSeleccionado = null;
		this.situacion = "0";
		this.paginacionActiva = false;
		this.busquedaActiva = false;
		// final ConsultaTitularRes respuesta = consultaTitularBackend
		// .ejecutarWS(this.numCuenta.toString());
		CuentaBusquedaBean cuentaBusquedaBean = new CuentaBusquedaBean();
		cuentaBusquedaBean.setNumeroCuenta(this.numCuenta);

		List<Object> respuesta = busquedaCuentaBackEnd
				.ejecutarWS(cuentaBusquedaBean);

		if (respuesta != null
				&& !respuesta.isEmpty()
				&& respuesta.get(0) != null
				&& StringUtils.isNotBlank(((CuentaBean) respuesta.get(0))
						.getNombreTitular())) {
			this.nombreTitular = ((CuentaBean) respuesta.get(0))
					.getNombreTitular();
			this.cuenta = consultaCuentaBackend.ejecutarTRN(this.numCuenta);
		} else {
			this.limpiar();
			addMessage(FacesMessage.SEVERITY_FATAL, "¡Atención!",
					"El número de cuenta no corresponde a ningún cliente");
		}
	}

	public void setBloqueoSeleccionado(final MovimientoBean bloqueoSeleccionado) {
		this.bloqueoSeleccionado = bloqueoSeleccionado;
	}

	public MovimientoBean getBloqueoSeleccionado() {
		return this.bloqueoSeleccionado;
	}

	public void limpiar() {
		this.nombreTitular = null;
		this.numCuenta = null;
		this.bloqueoSeleccionado = null;
		this.situacion = "0";
		this.paginacionActiva = false;
		this.masBloqueos = false;
		this.bloqueos = null;
		this.bloqueosPaginacion = null;
		this.busquedaActiva = false;
	}

	public String irABuscador() {
		// TODO Probar cuando funcione la busqueda de cuentas
		obtenerFlash().put(ParametrosFlashEnum.TIPO_BUSQUEDA.getParamFlash(),
				BusquedaEnum.CUENTAS.getBusquedaValor());
		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.BLOQUEO_CUENTA_1);
		return NavegacionEnum.BUSQUEDA.getRuta();
	}

	public String irANuevoBloqueo() {
		// TODO Probar cuando funcione la busqueda de cuentas
		if(this.cuenta == null || this.cuenta.getNumeroCuenta().equals(0L)){
			cuenta = consultaCuentaBackend.ejecutarTRN(this.numCuenta);
		}
		if(this.cuenta != null && this.cuenta.getCentro() != null && 
				this.cuenta.getCentro().trim().equals(this.contextoUtils.getSucursal())){
			final Flash flash = obtenerFlash();
			flash.put(ParametrosFlashEnum.NUMERO_CUENTA.getParamFlash(),
					this.numCuenta);
			flash.put(BloqueoCuentaController.TITULAR, this.nombreTitular);
			if (null != destino && null != destinoController) {
				flash.put(ParametrosFlashEnum.DESTINO.getParamFlash(), destino);
				flash.put(ParametrosFlashEnum.DESTINO_CONTROLLER.getParamFlash(),
						destinoController);
			}
			return NavegacionEnum.BLOQUEO_CUENTA_2.getRuta();
		}else{
			RequestContext.getCurrentInstance().execute("PF('dlgCentroCuenta').show()");
		}
		return null;
	}

	public String inicio() {
		return NavegacionEnum.BLOQUEO_CUENTA_1.getRuta();
	}

	/**
	 * Función para adicionar alertas globales en la vista
	 * 
	 * @param severity
	 *            Severidad de la alerta.
	 * @param title
	 *            Titulo de la alerta.
	 * @param message
	 *            Mensaje desplegado en la alerta.
	 */
	private void addMessage(final FacesMessage.Severity severity,
			final String title, final String message) {
		final FacesMessage facesMessage = new FacesMessage(severity, title,
				message);
		FacesContext.getCurrentInstance().addMessage("noTitular", facesMessage);
	}
	
	public void verificarCentro(){
		if(this.cuenta == null || this.cuenta.getNumeroCuenta().equals(0L)){
			cuenta = consultaCuentaBackend.ejecutarTRN(this.numCuenta);
		}
		if(this.cuenta != null && this.cuenta.getCentro() != null && 
				this.cuenta.getCentro().trim().equals(this.contextoUtils.getSucursal())){
			RequestContext.getCurrentInstance().execute("PF('dlgDesbloqueoCuenta').show()");
		}else{
			RequestContext.getCurrentInstance().execute("PF('dlgCentroCuenta').show()");
		}
	}

	public void ejecutarDesbloqueo() {
		// TODO invocar el desbloqueo y recargar el listado de bloqueos
		this.bajaBloqueoCuentaBackend.ejecutarTRN(this.numCuenta,
				bloqueoSeleccionado);
		RequestContext.getCurrentInstance().execute("PF('dlgSuccess').show()");
		this.bloqueoSeleccionado = null;
		this.consultaBloqueos();

		// return null;
	}

	public void ejecutarBloqueo() {
		// TODO invocar el Bloqueo
		// Cuidado con el error 8630 que indica un error que debe mostrarse en
		// pantalla
		this.tarifa = new TarifaBean();
		if(this.cuenta == null || this.cuenta.getNumeroCuenta().equals(0L)){
			cuenta = consultaCuentaBackend.ejecutarTRN(this.numCuenta);
		}
		this.tarifa.setLinea(this.cuenta.getCodLinea());
		this.tarifa.setGrupo(this.cuenta.getIdGrupoProducto());
		this.tarifa.setProducto(this.cuenta.getIdProducto());
		this.tarifa.setId(this.cuenta.getIdTarifaProducto());
		if (this.isCreditoPermitido()) {
			this.nuevoBloqueo.setSubTipoDescription(this
					.traducirSubTipo(this.nuevoBloqueo.getSubTipo()));
			msgBloqueoManual = this.altaBloqueoCuentaBackend.ejecutarTRN(
					this.numCuenta, nuevoBloqueo);
			if (("12".equals(msgBloqueoManual))) {
				RequestContext.getCurrentInstance().execute(
						"PF('dlgErrorBloqueo').show()");
			} else {
				RequestContext.getCurrentInstance().execute(
						"PF('dlgSuccess').show()");
			}
		} else {
			RequestContext.getCurrentInstance().execute(
					"PF('dlgErrorCredito').show()");
		}

	}

	public boolean isCreditoPermitido() {
		if (("03").equals(this.tarifa.getLinea())
				&& ("11").equals(this.tarifa.getGrupo())) {
			return true;
		}
		return false;
	}

	public String irAConsultaBloqueos() {
		final Flash flash = obtenerFlash();
		flash.put(ParametrosFlashEnum.NUMERO_CUENTA.getParamFlash(),
				this.numCuenta);
		obtenerFlash().put(BloqueoCuentaController.TITULAR, this.nombreTitular);
		if (null != destino && null != destinoController) {
			flash.put(ParametrosFlashEnum.DESTINO.getParamFlash(), destino);
			flash.put(ParametrosFlashEnum.DESTINO_CONTROLLER.getParamFlash(),
					destinoController);
		}
		return NavegacionEnum.BLOQUEO_CUENTA_1.getRuta();
	}

	/**
	 * @return the nuevoBloqueo
	 */
	public MovimientoBean getNuevoBloqueo() {
		return nuevoBloqueo;
	}

	/**
	 * @param nuevoBloqueo
	 *            the nuevoBloqueo to set
	 */
	public void setNuevoBloqueo(final MovimientoBean nuevoBloqueo) {
		this.nuevoBloqueo = nuevoBloqueo;
	}

	/**
	 * @return the paginacionActiva
	 */
	public boolean isPaginacionActiva() {
		return paginacionActiva;
	}

	/**
	 * @param paginacionActiva
	 *            the paginacionActiva to set
	 */
	public void setPaginacionActiva(final boolean paginacionActiva) {
		this.paginacionActiva = paginacionActiva;
	}

	public boolean hayMasDatos() {
		if (this.bloqueosPaginacion == null || this.bloqueos == null) {
			return false;
		}
		return this.bloqueos.size() != this.bloqueosPaginacion.size()
				|| this.masBloqueos;
	}

	/**
	 * @return the msgBloqueoManual
	 */
	public String getMsgBloqueoManual() {
		return msgBloqueoManual;
	}

	/**
	 * @param msgBloqueoManual
	 *            the msgBloqueoManual to set
	 */
	public void setMsgBloqueoManual(final String msgBloqueoManual) {
		this.msgBloqueoManual = msgBloqueoManual;
	}

	public String getCatalogoDesc(final String situacion) {
		for (final CatalogoBean cBean : ConstantesFuncionales.CATALOGO_BLOQUEOS) {
			if (StringUtils.equals(situacion, cBean.getClaveFila())) {
				return cBean.getDescripcionL();
			}
		}
		return "";
	}

	public List<CatalogoBean> getCatalogoSitBloqueos() {
		return ConstantesFuncionales.CATALOGO_BLOQUEOS;
	}

	public String getRowStyle(final String estado) {
		String result = "";

		if (StringUtils.equals(
				ConstantesFuncionales.SIT_BLOQ_TODOS.getClaveFila(),
				this.situacion)) {
			if (StringUtils.equals(estado,
					ConstantesFuncionales.SIT_BLOQ_ACTIVO.getClaveFila())) {
				result = "celda-roja";
			} else if (StringUtils.equals(estado,
					ConstantesFuncionales.SIT_BLOQ_CANCELADO.getClaveFila())) {
				result = "celda-verde";
			} else if (StringUtils.equals(estado,
					ConstantesFuncionales.SIT_BLOQ_INACTIVO.getClaveFila())) {
				result = "celda-gris";
			} else if (StringUtils.equals(estado,
					ConstantesFuncionales.SIT_BLOQ_VENCIDO.getClaveFila())) {
				result = "celda-amarilla";
			}
		}
		return result;
	}

	/**
	 * @return the busquedaActiva
	 */
	public boolean isBusquedaActiva() {
		return busquedaActiva;
	}

	/**
	 * @param busquedaActiva
	 *            the busquedaActiva to set
	 */
	public void setBusquedaActiva(final boolean busquedaActiva) {
		this.busquedaActiva = busquedaActiva;
	}

	public boolean getisTodos() {
		return isTodos;
	}

	public void setisTodos(boolean isTodos) {
		this.isTodos = isTodos;
	}

	public String irAHome() {
		String ruta = "";
		if (destino == null) {
			ruta = NavegacionEnum.INICIO.getRuta();
		} else {
			ruta = destino.getRuta();
			managedBeanStateRecover.finNavegacion(destinoController);
		}
		return ruta;
	}

}
