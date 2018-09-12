package mx.babel.bansefi.banksystem.transacciones.controllers;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.backends.ConsultaReciboNoDomiciliadoBackEnd;
import mx.babel.bansefi.banksystem.base.backends.busquedas.BusquedaCuentaBackEnd;
import mx.babel.bansefi.banksystem.base.beans.ReciboBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.enums.BusquedaEnum;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoCentrosLoaderTask;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;
import mx.babel.bansefi.banksystem.base.utils.NumberToLetterConverter;
import mx.babel.bansefi.banksystem.transacciones.backends.AltaReciboNoDomiciliadoBackEnd;
import mx.babel.bansefi.banksystem.transacciones.backends.ValidaEmisoraBackEnd;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Clase que se encarga de ingresar recibos no domiciliados
 * 
 * @author manuel.nieto
 * 
 */
@ManagedBean(name = "altaReciboNoDomiciliadoController")
@ViewScoped
@Component
@Scope("view")
public class AltaReciboNoDomiciliadoController implements Serializable {

	private static final long serialVersionUID = 2387650192779157574L;

	@Autowired
	ContextoUtils contextoUtils;

	@Autowired
	CatalogoUtils catalogoUtils;

	@Autowired
	CatalogoCentrosLoaderTask catalogoCentros;

	@Autowired
	private ManagedBeanStateRecover managedBeanStateRecover;

	@Autowired
	private AltaReciboNoDomiciliadoBackEnd altaReciboNoDomiciliadoBackEnd;

	@Autowired
	private ConsultaReciboNoDomiciliadoBackEnd consultaReciboNoDomiciliadoBackEnd;

	@Autowired
	private ValidaEmisoraBackEnd validaEmisoraBackEnd;

	@Autowired
	private BusquedaCuentaBackEnd busquedaCuentaBackEnd;

	private ReciboBean reciboBean;

	private CatalogoBean emisoraSeleccionada;

	private static final Logger LOGGER = LogManager
			.getLogger(AltaReciboNoDomiciliadoController.class);

	/**
	 * Metodo que inicializa la vista
	 */
	@PostConstruct
	public void init() {

		// Accedemos a la consulta del resumen de la transaccion en recibos no
		// domiciliados
		if (this.obtieneFlash().get(
				ParametrosFlashEnum.RECIBO_BEAN.getParamFlash()) != null) {

			this.reciboBean = (ReciboBean) this.obtieneFlash().get(
					ParametrosFlashEnum.RECIBO_BEAN.getParamFlash());

			// Recuperamos la cuenta buscada a traves del buscador en el alta de
			// recibos no domiciliados
		} else if (this.obtieneFlash().get(
				ParametrosFlashEnum.NUMERO_CUENTA.getParamFlash()) != null) {

			managedBeanStateRecover.recuperaController(this);

			this.reciboBean
					.setNumeroCuenta(String.valueOf((Long) this.obtieneFlash()
							.get(ParametrosFlashEnum.NUMERO_CUENTA
									.getParamFlash())));
			try {
				this.reciboBean.setCentro(catalogoCentros
						.getCatalogoDescripcion(
								contextoUtils.getEntidad(),
								(String) this.obtieneFlash().get(
										ParametrosFlashEnum.CENTRO
												.getParamFlash())));
			} catch (ControlableException ce) {
				LOGGER.debug(
						"Error al intentar obtener la descripcion del centro a partir del codigo: "
								+ (String) this.obtieneFlash().get(
										ParametrosFlashEnum.CENTRO
												.getParamFlash()), ce);
				this.reciboBean.setCentro("");
			}
			
			
		} else {

			this.reciboBean = new ReciboBean();
			this.reciboBean.setImporteEntregado(new BigDecimal(0));
			this.reciboBean.setImporteComision(new BigDecimal(0));
			this.reciboBean.setImporteIva(new BigDecimal(0));
			this.reciboBean.setImporteTotal(new BigDecimal(0));

			this.managedBeanStateRecover.recuperaController(this);
		}

	}

	/**
	 * Funcion que se ejecuta al pulsar el boton para validar la referencia
	 */
	public void validarEmisora() {
		if (this.reciboBean.getEmisora() != null) {
			this.reciboBean = this.validaEmisoraBackEnd
					.ejecutarWS(this.reciboBean.getEmisora());
		}
	}

	/**
	 * Funcion que se ejecuta al incluir la referencia para obtener el tipo de
	 * concepto de catalogo
	 */
	public void calcularConcepto() {
		try {
			if (this.reciboBean.getReferencia() != null
					&& this.reciboBean.getReferencia().length() > 1) {
				this.reciboBean.setConcepto(this.catalogoUtils.getCatalogoDesc(
						CatalogoEnum.TP_CONCEPTOS_PAGO, this.getReciboBean()
								.getReferencia().substring(0, 2)));
			} else {
				this.reciboBean.setConcepto(null);
			}
		} catch (ControlableException ce) {
			this.reciboBean.setConcepto(null);
			LOGGER.debug("Error consultado el catalogo de concepto o concepto no encontrado para la clave: "
					+ this.getReciboBean().getReferencia().substring(0, 2));
		}
	}

	/**
	 * Funcion que se ejecuta al cambiar la forma de pago a Efectivo para dejar
	 * de almacenar el valor de cuenta y centro
	 */
	public void limpiarDatosCuenta() {
		if (this.reciboBean.getFormaCobro() != null
				&& "1".equals(this.reciboBean.getFormaCobro())) {
			this.reciboBean.setNumeroCuenta(null);
			this.reciboBean.setCentro(null);
		}
		this.calcularImportes();
	}

	/**
	 * Funcion que comprueba y consulta el numero de cuenta ingresado
	 */
	public void validarCuenta() {
		if (this.reciboBean.getNumeroCuenta() != null
				&& !("").equals(this.reciboBean.getNumeroCuenta())) {
			CuentaBean cuenta = this.busquedaCuentaBackEnd.ejecutarWS(Long
					.parseLong(this.reciboBean.getNumeroCuenta()));
			if (cuenta != null && cuenta.getNumeroCuenta() != 0) {
				LOGGER.debug("Se incluye el elemento a la lista");
				this.reciboBean.setNumeroCuenta(String.valueOf(cuenta
						.getNumeroCuenta()));

				try {
					this.reciboBean.setCentro(catalogoCentros
							.getCatalogoDescripcion(contextoUtils.getEntidad(),
									cuenta.getCentro()));
				} catch (ControlableException ce) {
					LOGGER.debug(
							"Error al intentar obtener la descripcion del centro a partir del codigo: "
									+ cuenta.getCentro(), ce);
					this.reciboBean.setCentro("");
				}
			} else {
				this.reciboBean.setNumeroCuenta("");
				this.reciboBean.setCentro("");
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Recibos No Domiciliados",
						"No se encontro la cuenta introducida en el sistema.");
				RequestContext.getCurrentInstance()
						.showMessageInDialog(message);
			}
		}
	}

	/**
	 * Método para buscar el numero de cuenta a traves del buscador
	 * 
	 * @return
	 */
	public String buscarCuenta() {
		obtieneFlash().put(ParametrosFlashEnum.TIPO_BUSQUEDA.getParamFlash(),
				BusquedaEnum.CUENTAS.getBusquedaValor());
		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.PAGO_RECIBOS_NO_DOMICILIADOS);
		return NavegacionEnum.BUSQUEDA.getRuta();
	}

	/**
	 * Método para calcular los importes de comision y de subtotal
	 * 
	 * @return
	 */
	public void calcularImportes() {
		if (this.reciboBean.getImporteEntregado() != null
				&& this.reciboBean.getTipoComision() != null
				&& this.reciboBean.getFormaCobro() != null) {
			// TODO : Quitar hardcodes a constantes funcionales
			// Si el tipo de comision es comision
			if (this.reciboBean.getTipoComision().equals("0")) {

				// Efectivo
				if (this.reciboBean.getFormaCobro().equals("1")) {
					this.reciboBean.setImporteComision(new BigDecimal(
							reciboBean.getComisionSinCuenta()));
					this.reciboBean.setImporteTotal(this.reciboBean
							.getImporteEntregado().add(
									this.reciboBean.getImporteComision()));
				}

				// Cargo A Cuenta
				if (this.reciboBean.getFormaCobro().equals("2")) {
					this.reciboBean.setImporteComision(new BigDecimal(
							reciboBean.getComisionConCuenta()));
					this.reciboBean.setImporteTotal(this.reciboBean
							.getImporteEntregado().add(
									this.reciboBean.getImporteComision()));
				}

				// Si el tipo de comision es porcentaje
			} else if (this.reciboBean.getTipoComision().equals("1")) {

				// Efectivo
				if (this.reciboBean.getFormaCobro().equals("1")) {
					this.reciboBean.setImporteComision(this.reciboBean
							.getImporteEntregado()
							.multiply(
									new BigDecimal(reciboBean
											.getComisionSinCuenta()))
							.divide(new BigDecimal(100)));
					this.reciboBean.setImporteTotal(this.reciboBean
							.getImporteEntregado().add(
									this.reciboBean.getImporteComision()));
				}

				// Cargo A Cuenta
				if (this.reciboBean.getFormaCobro().equals("2")) {
					this.reciboBean.setImporteComision(this.reciboBean
							.getImporteEntregado()
							.multiply(
									new BigDecimal(reciboBean
											.getComisionConCuenta()))
							.divide(new BigDecimal(100)));
					this.reciboBean.setImporteTotal(this.reciboBean
							.getImporteEntregado().add(
									this.reciboBean.getImporteComision()));
				}

			}
		}
	}

	/**
	 * Funcion que retorna parte del texto para especificar el tipo de pago en
	 * el dialogo de confirmacion
	 * 
	 * @return
	 */
	public String tipoPago() {
		if (this.reciboBean != null) {
			if (("1").equals(this.reciboBean.getFormaCobro())) {
				return "en EFECTIVO";
			} else {
				if (!StringUtils.isBlank(this.reciboBean.getNumeroCuenta())) {
					return "con CARGO A CUENTA "
							+ this.reciboBean.getNumeroCuenta() + "";
				}
			}
		}
		return null;
	}

	/**
	 * Funcion que se ejecuta al pulsar el boton de guardar para abrir el
	 * dialogo de confirmacion
	 * 
	 * @return
	 */
	public void confirmarAltaRecibo() {
		RequestContext.getCurrentInstance().execute(
				"PF('dlgAltaRecibo').show()");
	}

	/**
	 * Funcion que se ejecuta traz aceptar el cobro del recibo no domiciliado.
	 * Posteriormente si se ejecuta correctamente la TRN la pagina redirecciona
	 * al resumen de la transacción
	 * 
	 * @return
	 */
	public String guardar() {
		this.reciboBean = this.altaReciboNoDomiciliadoBackEnd
				.ejecutarTRN(reciboBean);
		obtieneFlash().put(ParametrosFlashEnum.RECIBO_BEAN.getParamFlash(),
				this.reciboBean);
		return NavegacionEnum.RESUMEN_TRANSACCION_RECIBO_NO_DOMICILIADO
				.getRuta();
	}

	/**
	 * Funcion que obtiene la descripcion de la emisora
	 */
	public String obtenerDescEmisora() {
		try {
			return catalogoUtils.getCatalogoBean(CatalogoEnum.TP_EMISORA_57,
					this.reciboBean.getEmisora()).getDescripcionL();
		} catch (final ControlableException ce) {
			LOGGER.debug(
					"Error al intentar obtener la descripcion de la emisora a partir del codigo: "
							+ this.reciboBean.getEmisora(), ce);
			return "";
		}
	}

	/**
	 * Funcion que llama a la utilidad de convertir importe a palabras para el
	 * importeTotal en la vista de resumen
	 */
	public String obtenerDescImporteTotal() {
		if (this.reciboBean != null
				&& this.reciboBean.getImporteTotal() != null) {
			return NumberToLetterConverter
					.convertNumberToLetter(this.reciboBean.getImporteTotal()
							.toString());
		}
		return "0 PESOS";
	}

	//
	// /**
	// * Funcion que calcula el subtotal de la operacion
	// *
	// * @return
	// */
	// public BigDecimal calculaSubtotal() {
	// if (reciboBean != null && reciboBean.getImporte() != null &&
	// reciboBean.getComision() != null) {
	// BigDecimal subtotal = reciboBean.getImporte();
	// subtotal = opBigDecimal(subtotal, reciboBean.getComision(), true);
	// return subtotal;
	// } else {
	// return BigDecimal.ZERO;
	// }
	// }
	//
	// /**
	// * Operacion BigDecimal Mètodo para realizar sumas y restas entre
	// * BigDecimals
	// *
	// * @param value Valor base
	// * @param augend Valor a sumar o restarse
	// * @param suma boolean para definir si es suma o resta
	// * @return BigDecimal con resultado de la operaciòn.
	// */
	// public BigDecimal opBigDecimal(BigDecimal value, BigDecimal augend,
	// Boolean suma) {
	// if (value == null) {
	// value = BigDecimal.ZERO;
	// }
	//
	// if (augend == null) {
	// augend = BigDecimal.ZERO;
	// }
	// if (suma) {
	// return value.add(augend);
	// } else {
	// return value.add(augend.negate());
	// }
	// }
	//

	/**
	 * Funcion que retorna la ruta de la vista del flujo
	 * 
	 * @return
	 */
	public String inicio() {
		return NavegacionEnum.PAGO_RECIBOS_NO_DOMICILIADOS.getRuta();
	}

	/**
	 * Método que redirige al inicio.
	 * 
	 * @return ruta del recurso a mostrar
	 */
	public String volver() {
		return NavegacionEnum.INICIO.getRuta();
	}

	/**
	 * Agregar mensaje para ventana de confirmación
	 * 
	 * @param summary
	 * @param detail
	 */
	public void addMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	/**
	 * Instancia el flash.
	 * 
	 * @return - Devuelve una instancia flash.
	 */
	public Flash obtieneFlash() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
	}

	public ReciboBean getReciboBean() {
		return reciboBean;
	}

	public void setReciboBean(ReciboBean reciboBean) {
		this.reciboBean = reciboBean;
	}

	public CatalogoBean getEmisoraSeleccionada() {
		if (this.reciboBean.getEmisora() != null
				&& !("").equals(this.reciboBean.getEmisora())) {
			emisoraSeleccionada = catalogoUtils.getCatalogoBean(
					CatalogoEnum.TP_EMISORA_57, this.reciboBean.getEmisora());
		}
		return emisoraSeleccionada;
	}

	public void setEmisoraSeleccionada(CatalogoBean emisoraSeleccionada) {
		this.emisoraSeleccionada = emisoraSeleccionada;
		if (this.emisoraSeleccionada != null) {
			this.reciboBean.setEmisora(this.emisoraSeleccionada.getClaveFila());
		}
	}
}
