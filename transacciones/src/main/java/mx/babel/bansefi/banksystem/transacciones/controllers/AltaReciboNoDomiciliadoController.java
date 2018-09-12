package mx.babel.bansefi.banksystem.transacciones.controllers;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.backends.ConsultaReciboNoDomiciliadoBackEnd;
import mx.babel.bansefi.banksystem.base.backends.busquedas.BusquedaCuentaBackEnd;
import mx.babel.bansefi.banksystem.base.beans.ReciboBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.BusquedaEnum;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;
import mx.babel.bansefi.banksystem.base.utils.NumberToLetterConverter;
import mx.babel.bansefi.banksystem.base.utils.PdfUtils;
import mx.babel.bansefi.banksystem.transacciones.backends.AltaReciboNoDomiciliadoBackEnd;
import mx.babel.bansefi.banksystem.transacciones.backends.ValidaEmisoraBackEnd;

import org.apache.commons.lang.NumberUtils;
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
	private PdfUtils pdfUtils;

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

	private NavegacionEnum destino;
	private Object destinoController;
	
	private boolean habilitaImpresion;

	private static final Logger LOGGER = LogManager
			.getLogger(AltaReciboNoDomiciliadoController.class);

	/**
	 * Metodo que inicializa la vista
	 */
	@PostConstruct
	public void init() {
		if (this.obtieneFlash().get(
				ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash()) != null) {
			if ((Boolean) this.obtieneFlash()
					.get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN
							.getParamFlash())) {
				destino = managedBeanStateRecover.getDestino();
				destinoController = managedBeanStateRecover.getController();
				initializeData();
			} else {
				// Regresa de un flujo
				managedBeanStateRecover.recuperaController(this);

				if (this.obtieneFlash().get(
						ParametrosFlashEnum.NUMERO_CUENTA.getParamFlash()) != null) {

					// managedBeanStateRecover.recuperaController(this);

					this.reciboBean.setNumeroCuenta(String.valueOf((Long) this
							.obtieneFlash().get(
									ParametrosFlashEnum.NUMERO_CUENTA
											.getParamFlash())));
					this.reciboBean.setTitular((String) this.obtieneFlash()
							.get(ParametrosFlashEnum.TITULAR_CUENTA
									.getParamFlash()));

				}

			}
		} else {
			initializeData();
		}

	}

	public void initializeData() {
		// Accedemos a la consulta del resumen de la transaccion en recibos no
		// domiciliados
		if (this.obtieneFlash().get(
				ParametrosFlashEnum.RECIBO_BEAN.getParamFlash()) != null) {

			this.reciboBean = (ReciboBean) this.obtieneFlash().get(
					ParametrosFlashEnum.RECIBO_BEAN.getParamFlash());
			
			// La emisora solo la tiene cuando proviene directo del alta
			if (!StringUtils.isBlank(this.reciboBean.getEmisora())) {
				this.habilitaImpresion = true;
				printReport();
			}

			// Recuperamos la cuenta buscada a traves del buscador en el alta de
			// recibos no domiciliados
		} else if (this.obtieneFlash().get(
				ParametrosFlashEnum.NUMERO_CUENTA.getParamFlash()) != null) {

			this.reciboBean
					.setNumeroCuenta(String.valueOf((Long) this.obtieneFlash()
							.get(ParametrosFlashEnum.NUMERO_CUENTA
									.getParamFlash())));
			this.reciboBean.setTitular((String) this.obtieneFlash().get(
					ParametrosFlashEnum.TITULAR_CUENTA.getParamFlash()));

		} else {

			this.reciboBean = new ReciboBean();
			this.reciboBean.setImporteEntregado(new BigDecimal(0));
			this.reciboBean.setImporteComision(new BigDecimal(0));
			this.reciboBean.setImporteIva(new BigDecimal(0));
			this.reciboBean.setImporteTotal(new BigDecimal(0));

		}

	}

	/**
	 * Funcion que se ejecuta al pulsar el boton para validar la referencia
	 */
	public void validarEmisora() {
		if (this.reciboBean.getEmisora() != null) {
			this.reciboBean = this.validaEmisoraBackEnd
					.ejecutarWS(this.reciboBean.getEmisora());
			// Validar respuesta valida de servicio
			if (StringUtils.isBlank(this.reciboBean.getLongitudReferencia())
					|| Integer.valueOf(this.reciboBean.getLongitudReferencia()) == 0) {
				this.reciboBean.setLongitudReferencia(null);
				this.emisoraSeleccionada = null;
				RequestContext
						.getCurrentInstance()
						.execute(
								"$('#form\\\\:emisora_input').val(null); PF('dlgErrorEmisora').show()");
			}
		}
	}

	/**
	 * Funcion que se ejecuta al incluir la referencia para obtener el tipo de
	 * concepto de catalogo para la emisora Gobierno DF
	 */
	public void calcularConcepto() {

		try {// Valida referencia de gobierno federal
			if (this.reciboBean.getReferencia() != null
					&& this.reciboBean.getReferencia().length() > 1
					&& ("01681141").equals(this.reciboBean.getEmisora())) {
				this.reciboBean.setConcepto(this.catalogoUtils.getCatalogoDesc(
						CatalogoEnum.TP_CONCEPTOS_PAGO, this.getReciboBean()
								.getReferencia().substring(0, 2)));
				// Valida referencia de comision federal de electricidad
			} else if (calculaImporteReferencia()) {				
//					this.calcularImportes();				
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
			this.reciboBean.setTitular(null);
		}
//		this.calcularImportes();
	}

	/**
	 * Funcion que comprueba y consulta el numero de cuenta ingresado
	 */
	public void validarCuenta() {
		try {
			if (this.reciboBean.getNumeroCuenta() != null
					&& !("").equals(this.reciboBean.getNumeroCuenta())) {
				
				if(isNumeric(this.reciboBean.getNumeroCuenta()))
				{
						
				
				CuentaBean cuenta = this.busquedaCuentaBackEnd.ejecutarWS(Long
						.parseLong(this.reciboBean.getNumeroCuenta()));
				if (cuenta != null && cuenta.getNumeroCuenta() != 0) {
					LOGGER.debug("Se incluye el elemento a la lista");
					this.reciboBean.setNumeroCuenta(String.valueOf(cuenta
							.getNumeroCuenta()));
					this.reciboBean.setTitular(cuenta.getNombreTitular());

				} else {
					this.reciboBean.setNumeroCuenta("");
					this.reciboBean.setTitular("");
					RequestContext.getCurrentInstance().execute(
							"PF('dlgValidaCuenta').show()");
					
					/**FacesMessage message = new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							"Recibos No Domiciliados",
							"No se encontro la cuenta introducida en el sistema.");
					RequestContext.getCurrentInstance().showMessageInDialog(
							message);*/
				
						}
				}
				else{
					this.reciboBean.setNumeroCuenta("");
					
					RequestContext.getCurrentInstance().execute(
							"PF('dlgValidaCuenta').show()");
					
					
				 /**	FacesMessage message = new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							"Recibos No Domiciliados",
							"La cuenta ingresada es errónea, verifique");
					RequestContext.getCurrentInstance().showMessageInDialog(
							message);*/
				
						}
					
			}
		} catch (ControlableException ce) {
			this.reciboBean.setNumeroCuenta("");
			this.reciboBean.setTitular("");
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Recibos No Domiciliados",
					"No se encontro la cuenta introducida en el sistema.");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
		} catch (NoControlableException nce) {
			this.reciboBean.setNumeroCuenta("");
			this.reciboBean.setTitular("");
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Recibos No Domiciliados",
					"No se encontro la cuenta introducida en el sistema.");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
		}

	}

	private boolean isNumeric(String numeroCuenta) {
		// TODO Auto-generated method stub
		if(NumberUtils.isNumber(numeroCuenta))
		{

		return true;
		}
		else
		{
			return false;
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
			
			//Vuelve a obtener el importe
			calculaImporteReferencia();
			BigDecimal impEntregadoAux = new BigDecimal(
					String.valueOf(this.reciboBean.getImporteEntregado()));
			// TODO : Quitar hardcodes a constantes funcionales
			// Si el tipo de comision es comision
			if (this.reciboBean.getTipoComision().equals("0")) {

				// Efectivo
				if (this.reciboBean.getFormaCobro().equals("1")) {
					// Calcula comision
					this.reciboBean.setImporteComision(new BigDecimal(
							reciboBean.getComisionSinCuenta()));

					// Comision federal de electricidad
					if (this.reciboBean.getEmisora().equals("01681142")) {

//						this.reciboBean.setImporteSubtotal(new BigDecimal(
//								String.valueOf(this.reciboBean
//										.getImporteEntregado())));

						this.reciboBean.setImporteEntregado(impEntregadoAux
								.add(this.reciboBean.getImporteComision()
										.negate()));
					} else {
						this.reciboBean.setImporteSubtotal(impEntregadoAux
								.add(this.reciboBean.getImporteComision()));
					}
				}

				// Cargo A Cuenta
				if (this.reciboBean.getFormaCobro().equals("2")) {
					// Calcula comision
					this.reciboBean.setImporteComision(new BigDecimal(
							reciboBean.getComisionConCuenta()));
					// Comision federal de electricidad
					if (this.reciboBean.getEmisora().equals("01681142")) {

//						this.reciboBean.setImporteSubtotal(new BigDecimal(
//								String.valueOf(this.reciboBean
//										.getImporteEntregado())));

//						this.reciboBean.setImporteEntregado(new BigDecimal(String.valueOf(impEntregadoAux
//								.add(this.reciboBean.getImporteComision()
//										.negate()))));
						BigDecimal newImpEntregado = this.reciboBean.getImporteEntregado().add(this.reciboBean.getImporteComision().negate());
						this.reciboBean.setImporteEntregado(newImpEntregado);
						LOGGER.debug("Negate");
					} else {
						this.reciboBean.setImporteSubtotal(impEntregadoAux
								.add(this.reciboBean.getImporteComision()));
					}
				}

				// Si el tipo de comision es porcentaje
			} else if (this.reciboBean.getTipoComision().equals("1")) {

				// Efectivo
				if (this.reciboBean.getFormaCobro().equals("1")) {
					// Calcula comision
					this.reciboBean.setImporteComision(impEntregadoAux
							.multiply(
									new BigDecimal(reciboBean
											.getComisionSinCuenta())).divide(
									new BigDecimal(100)));
					// Comision federal de electricidad
					if (this.reciboBean.getEmisora().equals("01681142")) {

						this.reciboBean.setImporteSubtotal(new BigDecimal(
								String.valueOf(this.reciboBean
										.getImporteEntregado())));

						this.reciboBean.setImporteEntregado(impEntregadoAux
								.add(this.reciboBean.getImporteComision()
										.negate()));
					} else {
						this.reciboBean.setImporteSubtotal(impEntregadoAux
								.add(this.reciboBean.getImporteComision()));
					}
				}

				// Cargo A Cuenta
				if (this.reciboBean.getFormaCobro().equals("2")) {
					// Calcula comision
					this.reciboBean.setImporteComision(impEntregadoAux
							.multiply(
									new BigDecimal(reciboBean
											.getComisionConCuenta())).divide(
									new BigDecimal(100)));
					// Comision federal de electricidad
					if (this.reciboBean.getEmisora().equals("01681142")) {

						this.reciboBean.setImporteSubtotal(new BigDecimal(
								String.valueOf(this.reciboBean
										.getImporteEntregado())));

						this.reciboBean.setImporteEntregado(impEntregadoAux
								.add(this.reciboBean.getImporteComision()
										.negate()));
					} else {
						this.reciboBean.setImporteSubtotal(impEntregadoAux
								.add(this.reciboBean.getImporteComision()));
					}
				}

			}
		}
	}
	
	/**
	 * Calcula importe a partir de la referencia para el caso de emisora 
	 * Comision Federal de electricidad
	 */
	public boolean calculaImporteReferencia() {
		if (this.reciboBean.getReferencia() != null
				&& this.reciboBean.getReferencia().length() == 30
				&& ("01681142").equals(this.reciboBean.getEmisora())) {
			try {
				BigDecimal importe = new BigDecimal(this.reciboBean
						.getReferencia().substring(20, 29));
				this.reciboBean.setImporteSubtotal(importe);
				this.reciboBean.setImporteEntregado(importe);
				return true;
			} catch (NumberFormatException nfe) {
			
			}
		}
		return false;
	}

	/**
	 * Funcion que imprime el comprobante de la transaccion
	 */
	public void printReport() {
		if (this.reciboBean != null) {
			final Map<String, Object> params = new HashMap<String, Object>();
			SimpleDateFormat sdf = new SimpleDateFormat(ConstantesFuncionales.GENERAL_DATE_FORMATTER);
			SimpleDateFormat sdfh = new SimpleDateFormat("HH:mm:ss");
			// Parametros dentro del reporte
			params.put("nombreEmisora", obtenerDescEmisora());
			params.put("plaza",
					this.buscarPlaza(this.contextoUtils.getPlazaBancaria()));
			params.put("oficina", this.reciboBean.getCentro());
			params.put("fechaOperacion", this.reciboBean.getFechaOperacion());
			params.put("horaOperacion", this.reciboBean.getHoraOperacion());
			params.put("referencia", this.reciboBean.getReferencia());
			params.put("noOperacion", this.reciboBean.getNumeroOperacion());
			params.put("formaPago", this.reciboBean.getFormaPago());
			this.reciboBean.getImporteEntregado().setScale(2);
			params.put("importe", this.reciboBean.getImporteEntregado());
			params.put("importeComision", this.reciboBean.getImporteComision());
			params.put("importeTotal", this.reciboBean.getImporteTotal());
			params.put("importeLetras", obtenerDescImporteTotal());
			params.put("importeIVA", this.reciboBean.getImporteIva());
			params.put("idTransaccion", this.reciboBean.getIdTransaccion());
			params.put("fechaCertificado",sdf.format(this.reciboBean.getFechaOperacion()).replaceAll("/", ""));
			params.put("horaCertificado", sdfh.format(this.reciboBean.getHoraOperacion()).replaceAll(":", ""));
			params.put("terminal", this.contextoUtils.getTerminal());
			params.put("entidad", this.contextoUtils.getEntidad());
			params.put("centro", this.contextoUtils.getSucursal());
			params.put("cuenta", this.reciboBean.getNumeroCuenta());

			Map<String, String> images = new HashMap<String, String>();
			images.put("Logo_bsfi_bn.png", "rutaImagen");
			pdfUtils.generaPdf("reciboNoDomiciliadoReporte.jrxml", params,
					images, null,
					"RESUMEN DE TRANSACCION " + reciboBean.getIdTransaccion(),
					null);

		}
	}

	public String buscarPlaza(String clave) {
		String descripcion = clave;
		List<CatalogoBean> catalogo = catalogoUtils
				.getCatalogo(CatalogoEnum.TP_PLAZA_BANCARIA);
		for (CatalogoBean elemento : catalogo) {
			if (elemento.getClaveFila().equals(clave.trim())) {
				descripcion = elemento.getDescripcionL();
			}
		}
		return descripcion;
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
			if (this.reciboBean != null
					&& !StringUtils.isBlank(this.reciboBean.getEmisora())) {
				return catalogoUtils.getCatalogoBean(
						CatalogoEnum.TP_EMISORA_57,
						this.reciboBean.getEmisora()).getDescripcionL();
			}
			return null;
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
			try {
				return NumberToLetterConverter
						.convertirImporteAImporteEnletraMN(this.reciboBean
								.getImporteTotal().doubleValue());
			} catch (NumberFormatException e) {
				return "0 PESOS";
			}
			// return NumberToLetterConverter
			// .convertNumberToLetter(this.reciboBean.getImporteTotal()
			// .toString());
		}
		return "0 PESOS";
	}

	/**
	 * Funcion que retorna la ruta de la vista del flujo
	 * 
	 * @return
	 */
	public String inicio() {
		return NavegacionEnum.PAGO_RECIBOS_NO_DOMICILIADOS.getRuta();
	}

	public String nuevoPago() {
		String rutaDestino = null;
		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.RESUMEN_TRANSACCION_RECIBO_NO_DOMICILIADO);

		rutaDestino = NavegacionEnum.PAGO_RECIBOS_NO_DOMICILIADOS.getRuta();

		return rutaDestino;
	}

	/**
	 * Método que redirige al inicio.
	 * 
	 * @return ruta del recurso a mostrar
	 */
	public String volver() {
//		managedBeanStateRecover.finNavegacion(destinoController);
//		if (destino != null) {
//			return destino.getRuta();
//		} else {
			return NavegacionEnum.INICIO.getRuta();
//		}
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
		if (this.reciboBean != null
				&& !StringUtils.isBlank(this.reciboBean.getEmisora())) {
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

	public boolean isHabilitaImpresion() {
		return habilitaImpresion;
	}

	public void setHabilitaImpresion(boolean habilitaImpresion) {
		this.habilitaImpresion = habilitaImpresion;
	}
	
}
