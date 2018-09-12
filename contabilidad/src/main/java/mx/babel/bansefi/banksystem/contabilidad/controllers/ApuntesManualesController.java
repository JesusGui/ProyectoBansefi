package mx.babel.bansefi.banksystem.contabilidad.controllers;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.backends.ConsultaCuentasContablesBackEnd;
import mx.babel.bansefi.banksystem.base.backends.consultatitular.ConsultaTitularBackend;
import mx.babel.bansefi.banksystem.base.backends.consultatitular.ConsultaTitularRes;
import mx.babel.bansefi.banksystem.base.beans.ApunteManualBean;
import mx.babel.bansefi.banksystem.base.beans.ApuntesBean;
import mx.babel.bansefi.banksystem.base.beans.CuentaContableBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.BusquedaEnum;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoCentrosLoaderTask;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;
import mx.babel.bansefi.banksystem.base.utils.PdfUtils;
import mx.babel.bansefi.banksystem.base.utils.StringToDateConverter;
import mx.babel.bansefi.banksystem.contabilidad.backends.AltaApunteManualCajaBackEnd;
import mx.babel.bansefi.banksystem.contabilidad.backends.AltaApunteManualCuentaBackEnd;
import mx.babel.bansefi.banksystem.contabilidad.backends.AltaApunteManualIntervencionBackEnd;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Controlador para el flujo de alta de apuntes manuales
 * 
 * @author manuel.nieto
 * 
 */
@ManagedBean(name = "apuntesManualesController")
@ViewScoped
@Component
@Scope("view")
public class ApuntesManualesController implements Serializable {

	private static final long serialVersionUID = -3905678194247662715L;

	private static final Logger LOGGER = LogManager
			.getLogger(ApuntesManualesController.class);

	// StateRecover
	@Autowired
	ManagedBeanStateRecover managedBeanStateRecover;
	private NavegacionEnum destino;
	private Object destinoController;

	private ApunteManualBean altaApunteBean;

	private BigDecimal zero = BigDecimal.ZERO;
	
	private boolean muestraTitularError = false;

	/**
	 * BackEnds
	 */
	@Autowired
	private ConsultaTitularBackend consultaTitular;

	@Autowired
	private AltaApunteManualCajaBackEnd altaApunteManualCajaBackEnd;

	@Autowired
	private AltaApunteManualIntervencionBackEnd altaApunteManualIntervencionBackEnd;

	@Autowired
	private AltaApunteManualCuentaBackEnd altaApunteManualCuentaBackEnd;

	@Autowired
	private ConsultaCuentasContablesBackEnd consultaCuentasOperativasBackEnd;

	private String textoTitular;

	private List<CuentaContableBean> listaCuentasContables;

	@Autowired
	private PdfUtils pdfUtils;

	@Autowired
	private ContextoUtils contextoUtils;

	@Autowired
	private CatalogoCentrosLoaderTask catalogoCentros;

	private boolean habilitaImpresion;

	@Autowired
	CatalogoUtils catalogoUtils;

	private boolean checksDeshabilitados;

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
				managedBeanStateRecover.recuperaController(this);

				if (this.obtieneFlash().get(
						ParametrosFlashEnum.NUMERO_CUENTA.getParamFlash()) != null) {
					Long numCuenta = (Long) this.obtieneFlash().get(
							ParametrosFlashEnum.NUMERO_CUENTA.getParamFlash());
					this.altaApunteBean.setCuenta(String.valueOf(numCuenta));
					this.textoTitular = (String) this.obtieneFlash().get(
							ParametrosFlashEnum.TITULAR_CUENTA.getParamFlash());
				}
			}
		} else {
			initializeData();
		}
	}

	/**
	 * Mètodo para incializar los datos de la vista cuando no se tiene un estado
	 * previo.
	 */
	@SuppressWarnings("unchecked")
	private void initializeData() {
		altaApunteBean = new ApunteManualBean();

		// Deshabilita los checks si no se encuentra en el centro
		// controlador/parametrizador
		if (ConstantesFuncionales.CENTRO_CONTROLADOR.equals(this.contextoUtils
				.getSucursal())) {
			this.checksDeshabilitados = false;
		} else {
			this.checksDeshabilitados = true;
			altaApunteBean.setApunteManual(true);
			// Pre seleccionar el centro destino
			CatalogoBean centroDestino = this.catalogoCentros.getCatalogoBean(
					contextoUtils.getEntidad(), contextoUtils.getSucursal());
			altaApunteBean.setOficinaDestino(centroDestino);
		}

		// Resumen despues de alta
		if (obtieneFlash().get(
				ParametrosFlashEnum.ALTA_APUNTE_BEAN.getParamFlash()) != null) {
			this.altaApunteBean = (ApunteManualBean) obtieneFlash().get(
					ParametrosFlashEnum.ALTA_APUNTE_BEAN.getParamFlash());

			this.habilitaImpresion = true;
			printReport();

			if (StringUtils.isBlank(this.altaApunteBean.getCuentaContableBean()
					.getNombreCuentaContable())
					&& !StringUtils.isBlank(this.altaApunteBean
							.getCuentaContableBean().getIdCuentaContable())) {
				listaCuentasContables = consultaCuentasOperativasBackEnd
						.ejecutarTRN();
				this.altaApunteBean
						.setCuentaContableBean(getCuentaContableBean(this.altaApunteBean
								.getCuentaContableBean().getIdCuentaContable()));

			}
		} else {// Alta
			listaCuentasContables = consultaCuentasOperativasBackEnd
					.ejecutarTRN();

			this.altaApunteBean.setCentro(this.contextoUtils.getSucursal()
					+ this.catalogoCentros.getCatalogoDescripcion(
							this.contextoUtils.getEntidad(),
							this.contextoUtils.getSucursal()));

			validaFlujoBusqueda();

		}
	}

	/**
	 * Funcion que valida si se proviene del flujo de la busqueda
	 * 
	 * @return
	 */
	public void validaFlujoBusqueda() {

		if (obtieneFlash().get("fechaContable") != null
				&& obtieneFlash().get("fechaOperacion") != null
				&& obtieneFlash().get("noApunte") != null
				&& obtieneFlash().get("origen") != null
				&& obtieneFlash().get("destino") != null
				&& obtieneFlash().get("importe") != null
				&& obtieneFlash().get("debeHaber") != null
				&& obtieneFlash().get("hora") != null
				&& obtieneFlash().get("concepto") != null
				&& obtieneFlash().get("contraPartida") != null
				&& obtieneFlash().get("idCuentaContable") != null) {

			// Concepto
			this.altaApunteBean.setConcepto((String) obtieneFlash().get(
					"concepto"));
			CatalogoBean centroDestino = this.catalogoCentros.getCatalogoBean(
					contextoUtils.getEntidad(),
					(String) obtieneFlash().get("destino"));
			// Centro destino
			altaApunteBean.setOficinaDestino(centroDestino);
			// No. Apunte
			this.altaApunteBean.setNumeroApunte((long) obtieneFlash().get(
					"noApunte"));
			// Fecha operacion
			StringToDateConverter converter = new StringToDateConverter();
			if (obtieneFlash().get("fechaOperacion") != null) {
				this.altaApunteBean.setFechaOperacion(converter.convertTo(
						(String) obtieneFlash().get("fechaOperacion"), null));
			}
			// Hora operacion
			this.altaApunteBean.setHoraOperacion2(converter.convertTo(
					(String) obtieneFlash().get("hora"), null));
			// Clave operacion
			try {
				this.altaApunteBean.setClaveOperacion(this.catalogoUtils
						.getCatalogoBean(CatalogoEnum.TP_CLOP,
								(String) obtieneFlash().get("claveOperacion")));
			} catch (ControlableException e) {
				CatalogoBean claveOperacion = new CatalogoBean();
				claveOperacion.setClaveFila((String) obtieneFlash().get(
						"claveOperacion"));
				claveOperacion.setDescripcionL("");
				this.altaApunteBean.setClaveOperacion(claveOperacion);
			}
			// Id contable
			this.altaApunteBean
					.setCuentaContableBean(getCuentaContableBean((String) obtieneFlash()
							.get("idCuentaContable")));

			// Importe
			this.altaApunteBean.setImporte((BigDecimal) obtieneFlash().get(
					"importe"));
			// Tipo operacion
			this.altaApunteBean.setTipoOperacion((String) obtieneFlash().get(
					"debeHaber"));
			// Contrapartida
			this.altaApunteBean.setDatosContrapartida((String) obtieneFlash()
					.get("contraPartida"));
			// Cuenta
			this.altaApunteBean
					.setCuenta((String) obtieneFlash().get("cuenta"));

		}

	}

	/**
	 * Método para crear un archivo pdf con los datos desplegados en pantalla.
	 * 
	 */
	public void printReport() {

		if (this.altaApunteBean != null
				&& StringUtils.isNotBlank(this.altaApunteBean
						.getDatosContrapartida())
				&& this.altaApunteBean.getCuentaContableBean() != null
				&& StringUtils.isNoneBlank(this.altaApunteBean
						.getCuentaContableBean().getIdCuentaContable())) {
			final Map<String, Object> params = new HashMap<String, Object>();

			params.put("FECHA_REPORTE", new Date());
			params.put("OFICINA", this.altaApunteBean.getCentro());

			String plazaBancaria = "";
			try {
				plazaBancaria = catalogoUtils.getCatalogoBean(
						CatalogoEnum.TP_PLAZA_BANCARIA,
						contextoUtils.getPlazaBancaria()).getDescripcionL();
			} catch (Exception e) {
			}
			params.put("PLAZA", plazaBancaria);

			params.put("DESTINO", this.altaApunteBean.getOficinaDestino()
					.getDescripcionL());

			params.put("CONCEPTO", this.altaApunteBean.getConcepto());
			params.put("CUENTA_CONTABLE", this.altaApunteBean
					.getCuentaContableBean().getIdCuentaContable()
					+ " "
					+ this.altaApunteBean.getCuentaContableBean()
							.getNombreCuentaContable());

			// Datos Contrapartida
			if (("04").equals(this.altaApunteBean.getDatosContrapartida())) {
				params.put("DATOS_CONTRAPARTIDA", "CAJA MXN");
			} else if (("03").equals(this.altaApunteBean
					.getDatosContrapartida())) {
				params.put("DATOS_CONTRAPARTIDA", "CUENTA DE INTERVENCIÓN");
			} else if (("CT").equals(this.altaApunteBean
					.getDatosContrapartida())
					&& ("D").equals(this.altaApunteBean.getTipoOperacion())) {
				params.put("DATOS_CONTRAPARTIDA", "ABONO A CUENTA "
						+ this.altaApunteBean.getCuenta());
			} else if (("CT").equals(this.altaApunteBean
					.getDatosContrapartida())
					&& ("H").equals(this.altaApunteBean.getTipoOperacion())) {
				params.put("DATOS_CONTRAPARTIDA", "CARGO A CUENTA "
						+ this.altaApunteBean.getCuenta());
			}

			// IMPORTES
			if (("D").equals(this.altaApunteBean.getTipoOperacion())) {
				params.put("IMPORTE_DEBE_CUENTA_CONTABLE",
						this.altaApunteBean.getImporte());
				params.put("IMPORTE_DEBE_CONTRAPARTIDA", zero);
				params.put("IMPORTE_HABER_CUENTA_CONTABLE", zero);
				params.put("IMPORTE_HABER_CONTRAPARTIDA",
						this.altaApunteBean.getImporte());
			} else if (("H").equals(this.altaApunteBean.getTipoOperacion())) {
				params.put("IMPORTE_DEBE_CUENTA_CONTABLE", zero);
				params.put("IMPORTE_DEBE_CONTRAPARTIDA",
						this.altaApunteBean.getImporte());
				params.put("IMPORTE_HABER_CUENTA_CONTABLE",
						this.altaApunteBean.getImporte());
				params.put("IMPORTE_HABER_CONTRAPARTIDA", zero);
			}

			params.put("IMPORTE", this.altaApunteBean.getImporte());

			params.put("NO_APUNTE", this.altaApunteBean.getNumeroApunte());

			final Map<String, String> images = new HashMap<String, String>();
			images.put("Logo_bsfi_bn.png", "LOGO");

			pdfUtils.generaPdf("ApunteManualReporte.jrxml", params, images,
					null, "ApunteManual", new ArrayList<ApuntesBean>());
		}
	}

	/**
	 * Funcion que redirige al inicio del flujo
	 * 
	 * @return
	 */
	public String inicio() {
		return NavegacionEnum.ALTA_APUNTE_MANUAL.getRuta();
	}

	/**
	 * Funcion que se ejecuta al pulsar el botón de cancelar
	 * 
	 * @return
	 */
	public String cancelar() {
		return NavegacionEnum.INICIO.getRuta();
	}

	/**
	 * Funcion que genera la consulta para llenar el autocomplete de numero de
	 * cuenta contables
	 * 
	 * @param query
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CuentaContableBean> getCuentasContables(String query) {
		LOGGER.debug("Consulta cuentas contables con: " + query);
		List<CuentaContableBean> listaCuentas = new ArrayList<CuentaContableBean>();

		if (StringUtils.isBlank(query)) {
			return listaCuentas;
		}

		for (CuentaContableBean cuentaContable : listaCuentasContables) {
			if (cuentaContable.getNombreCuentaContable().toUpperCase()
					.contains(query.toUpperCase())
					|| cuentaContable.getIdCuentaContable().contains(
							query.toUpperCase())) {
				listaCuentas.add(cuentaContable);
			}
		}

		return listaCuentas;
	}

	/**
	 * Función para guardar el apunte
	 * 
	 * @return
	 */
	public String guardar() {

		if (this.altaApunteBean != null
				&& StringUtils.isNotBlank(this.altaApunteBean
						.getDatosContrapartida())
				&& this.altaApunteBean.getCuentaContableBean() != null
				&& StringUtils.isNoneBlank(this.altaApunteBean
						.getCuentaContableBean().getIdCuentaContable()) && !this.muestraTitularError) {
			switch (this.altaApunteBean.getDatosContrapartida()) {
			// Caja
			case "04":
				this.altaApunteBean
						.setNumeroApunte(this.altaApunteManualCajaBackEnd
								.ejecutarTRN(altaApunteBean));
				break;
			// Intervencion
			case "03":
				this.altaApunteBean
						.setNumeroApunte(this.altaApunteManualIntervencionBackEnd
								.ejecutarTRN(altaApunteBean));
				break;
			// Cuenta
			case "CT":
				this.altaApunteBean
						.setNumeroApunte(this.altaApunteManualCuentaBackEnd
								.ejecutarTRN(altaApunteBean));
				break;
			}
			obtieneFlash().put(
					ParametrosFlashEnum.ALTA_APUNTE_BEAN.getParamFlash(),
					this.altaApunteBean);

			return NavegacionEnum.RESUMEN_APUNTE_MANUAL.getRuta();
		}

		return null;

	}

	/**
	 * Funcion que apartir de un id buscar la cuenta contable
	 * 
	 * @param id
	 * @return
	 */
	public CuentaContableBean getCuentaContableBean(String id) {
		if (listaCuentasContables != null && !listaCuentasContables.isEmpty()
				&& StringUtils.isNotBlank(id)) {
			for (CuentaContableBean cuenta : listaCuentasContables) {
				if (cuenta.getIdCuentaContable().equals(id)) {
					return cuenta;
				}
			}
		}
		return null;
	}

	/**
	 * Comprueba que el número de cuenta introducido corresponde a un cliente y
	 * en caso afirmativo muestra el nombre del cliente.
	 */
	public void comprobarCuentaRetiro() {

		if (this.altaApunteBean != null
				&& StringUtils.isNotBlank(this.altaApunteBean.getCuenta())) {
			ConsultaTitularRes respuesta = this.consultaTitular
					.ejecutarWS(this.altaApunteBean.getCuenta());

			if (respuesta != null && "0".equals(respuesta.getEstatus())) {
				this.muestraTitularError = false;
				this.textoTitular = parseText(respuesta.getNombre());
			} else {
				this.muestraTitularError = true;
				this.textoTitular = parseText(respuesta.getMensaje());
			}
		}
	}
	
	/**
	 * Funcion que cambia el texto ACUERDO por LA CUENTA
	 * @param mensaje
	 * @return
	 */
	public String parseText(String mensaje){
		if(StringUtils.isNotBlank(mensaje)){
			mensaje = mensaje.replace("ACUERDO", "LA CUENTA");
		}
		
		return mensaje;
	}

	/**
	 * Método para buscar mediante el buscador el número de cuenta
	 * 
	 * @return
	 */
	public String buscarPersona() {
		obtieneFlash().put(ParametrosFlashEnum.TIPO_BUSQUEDA.getParamFlash(),
				BusquedaEnum.CUENTAS.getBusquedaValor());
		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.ALTA_APUNTE_MANUAL);
		return NavegacionEnum.BUSQUEDA.getRuta();

	}

	/**
	 * @return Metodo utilizado para recuperar una instancia de Flash
	 */
	public Flash obtieneFlash() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
	}

	public ApunteManualBean getAltaApunteBean() {
		return altaApunteBean;
	}

	public void setAltaApunteBean(ApunteManualBean altaApunteBean) {
		this.altaApunteBean = altaApunteBean;
	}

	public ManagedBeanStateRecover getManagedBeanStateRecover() {
		return managedBeanStateRecover;
	}

	public void setManagedBeanStateRecover(
			ManagedBeanStateRecover managedBeanStateRecover) {
		this.managedBeanStateRecover = managedBeanStateRecover;
	}

	public NavegacionEnum getDestino() {
		return destino;
	}

	public void setDestino(NavegacionEnum destino) {
		this.destino = destino;
	}

	public Object getDestinoController() {
		return destinoController;
	}

	public void setDestinoController(Object destinoController) {
		this.destinoController = destinoController;
	}

	public String getTextoTitular() {
		return textoTitular;
	}

	public void setTextoTitular(String textoTitular) {
		this.textoTitular = textoTitular;
	}

	public BigDecimal getZero() {
		return zero;
	}

	public void setZero(BigDecimal zero) {
		this.zero = zero;
	}

	public List<CuentaContableBean> getListaCuentasContables() {
		return listaCuentasContables;
	}

	public void setListaCuentasContables(
			List<CuentaContableBean> listaCuentasContables) {
		this.listaCuentasContables = listaCuentasContables;
	}

	public boolean isHabilitaImpresion() {
		return habilitaImpresion;
	}

	public void setHabilitaImpresion(boolean habilitaImpresion) {
		this.habilitaImpresion = habilitaImpresion;
	}

	public boolean isChecksDeshabilitados() {
		return checksDeshabilitados;
	}

	public void setChecksDeshabilitados(boolean checksDeshabilitados) {
		this.checksDeshabilitados = checksDeshabilitados;
	}

	public boolean isMuestraTitularError() {
		return muestraTitularError;
	}

	public void setMuestraTitularError(boolean muestraTitularError) {
		this.muestraTitularError = muestraTitularError;
	}

}
