package mx.babel.bansefi.banksystem.cuentas.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.model.SelectItem;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.bansefi.banksystem.base.backends.ConsultaCuentasClienteBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaDatosIPFBackend;
import mx.babel.bansefi.banksystem.base.backends.ConsultaDatosPeticionIPFBackend;
import mx.babel.bansefi.banksystem.base.beans.DatosDetalleIPFBean;
import mx.babel.bansefi.banksystem.base.beans.DepositoIPFBean;
import mx.babel.bansefi.banksystem.base.beans.PreferenceItemBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClienteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaClienteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaRelacionPlazoBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.CondicionBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.ProductoSimpleBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionComisionBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionInteresBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionListaBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionRangoBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionTramoBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionValorListaBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionValorRangoBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.tramos.CabeceraTramoBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.tramos.SubTramoBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.BeanBackUpUtils;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;
import mx.babel.bansefi.banksystem.cuentas.backends.ActivacionPlazoBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.AltaRelacionCuentaPlazoBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.AprobacionPlazoBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.BajaRelacionCuentaPlazoBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaCatalogoValorListaBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaConceptoApunteBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaCondicionComisionPlazoBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaCondicionInteresPlazoBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaCondicionListaBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaCondicionListaPlazoBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaCondicionRangoBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaCondicionRangoPlazoBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaCondicionTramoPlazoBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaCondicionValorListaPlazoBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaCondicionValorRangoPlazoBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaCuentasRelacionablesBackEnd;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaDatosDetalleIPFBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaDetalleTramoPlazoBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaListaBaseCalculoCondicionesBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaListaDominioCondicionesBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaNombreCondicionesBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaRelacionCuentaPlazoBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ModificaCondicionInteresPlazoBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ModificaCondicionValorListaPlazoBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ModificaCondicionValorRangoPlazoBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ModificacionRelacionCuentaPlazoBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.PeticionActivacionIPFBackend;
import mx.babel.bansefi.banksystem.cuentas.beans.BaseCalculoCondicionesBean;
import mx.babel.bansefi.banksystem.cuentas.beans.ConceptoApunteBean;
import mx.babel.bansefi.banksystem.cuentas.beans.DominioCondicionBean;
import mx.babel.bansefi.banksystem.cuentas.beans.TarifaBean;
import mx.babel.bansefi.banksystem.cuentas.beans.TarifaRelacionBean;
import mx.babel.bansefi.banksystem.cuentas.enums.TipoCargoEnum;
import mx.babel.bansefi.banksystem.cuentas.tasks.RelacionTarifasLoaderTask;
import mx.babel.bansefi.banksystem.cuentas.utils.AltaIPFUtils;
import mx.babel.bansefi.banksystem.cuentas.utils.TarifasUtils;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.accordionpanel.AccordionPanel;
import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@ManagedBean(name = "altaIPFController")
@Component
@Scope("view")
public class AltaIPFController {

	private final static String DEPOSITO_IPF = "depositoIPF";
	private final static String ABONO_INTERES_MODIFICADO = "abonoInteresModificado";
	private final static String ESTADO_SOLICITADO = "S";
	private final static String ESTADO_APROBADO = "B";
	private final static String GRUPO_CONDICION_TIEMPO = "029";
	private final static String DURACION_IPF_TARFIA = "A38";
	private final static String FRECUENCIA_IPF_TARIFA = "C37";
	
	private CuentaBean acuerdoPlazo = null;
	private Long cuentaOP = null;
	private BigDecimal importeMinimo = null;
	private ClienteBean cliente;
	private DepositoIPFBean depositoIPF;
	private boolean modificacion;
	private boolean origenFichaCuenta;
	private boolean abonoInteresModificado;
	private Integer selectedIPF = null;
	private List<DepositoIPFBean> ipfs;
	
	private String messageError;

	private String flujoContinuar;
	// Mapa con las condiciones cargadas que permiten
	// comprobar si han sido modificadas en front
	private Map<String, CondicionBean> initialConditionMap;

	private Map<Integer, CuentaRelacionPlazoBean> initialRelacionesCuentaMap;

	@Autowired
	ManagedBeanStateRecover managedBeanStateRecover;
	@Autowired
	AltaIPFUtils altaIPFUtils;
	@Autowired
	RelacionTarifasLoaderTask relacionTarifasLoaderTask;
	@Autowired
	ConsultaDatosPeticionIPFBackend consultaDatosPeticionIPFBackend;
	@Autowired
	ConsultaDatosIPFBackend consultaDatosIPFBackend;

	@Autowired
	ConsultaDatosDetalleIPFBackend consultaDatosDetalleIPFBackend;

	// Consulta los nombres de las condiciones a partir del producto Simple
	@Autowired
	ConsultaNombreCondicionesBackend consultaNombreCondicionesBackend;

	@Autowired
	PeticionActivacionIPFBackend peticionActivacionIPFBackend;

	@Autowired
	ActivacionPlazoBackend activacionPlazoBackend;

	@Autowired
	AprobacionPlazoBackend aprobacionPlazoBackend;

	// Consultas para el detalle de condiciones que se muestran al
	// desplegar los tabs contenidos en los productos simples
	@Autowired
	ConsultaCondicionComisionPlazoBackend consultaCondicionComisionPlazoBackend;
	@Autowired
	ConsultaCondicionInteresPlazoBackend consultaCondicionInteresPlazoBackend;
	@Autowired
	ConsultaCondicionListaPlazoBackend consultaCondicionListaPlazoBackend;
	@Autowired
	ConsultaCondicionRangoPlazoBackend consultaCondicionRangoPlazoBackend;
	@Autowired
	ConsultaCondicionTramoPlazoBackend consultaCondicionTramoPlazoBackend;
	@Autowired
	ConsultaCondicionValorListaPlazoBackend consultaCondicionValorListaPlazoBackend;
	@Autowired
	ConsultaCondicionValorRangoPlazoBackend consultaCondicionValorRangoPlazoBackend;
	@Autowired
	ConsultaDetalleTramoPlazoBackend consultaDetalleTramoPlazoBackend;
	@Autowired
	ConsultaCondicionListaBackend consultaCondicionListaBackend;
	@Autowired
	ConsultaCondicionRangoBackend consultaCondicionRangoBackend;

	@Autowired
	ModificaCondicionInteresPlazoBackend modificaCondicionInteresPlazoBackend;
	@Autowired
	ModificaCondicionValorListaPlazoBackend modificaCondicionValorListaPlazoBackend;
	@Autowired
	ModificaCondicionValorRangoPlazoBackend modificaCondicionValorRangoPlazoBackend;

	// Consulta la descripcion para la base de calculo que debe mostrarse
	// en algunas condiciones
	@Autowired
	ConsultaListaBaseCalculoCondicionesBackend consultaListaBaseCalculoCondicionesBackend;
	// Consulta una lista de valores asociada a una condicion
	// para obtener descripciones
	@Autowired
	ConsultaListaDominioCondicionesBackend consultaListaDominioCondicionesBackend;
	// Consulta una lista de valores asociada a una condicion
	// indicando ids y cual es la opcion preferente
	@Autowired
	ConsultaCatalogoValorListaBackend consultaCatalogoValorListaBackend;

	@Autowired
	CatalogoUtils catalogoUtils;
	@Autowired
	BeanBackUpUtils backUpUtils;

	@Autowired
	ConsultaCuentasClienteBackEnd consultaCuentasClienteBackEnd;

	@Autowired
	ConsultaCuentasRelacionablesBackEnd consultaCuentasRelacionablesBackEnd;
	@Autowired
	AltaRelacionCuentaPlazoBackend altaRelacionCuentaPlazoBackend;
	@Autowired
	ModificacionRelacionCuentaPlazoBackend modificacionRelacionCuentaPlazoBackend;
	@Autowired
	BajaRelacionCuentaPlazoBackend bajaRelacionCuentaPlazoBackend;

	/**
	 * Lista de cuentas que cumplen con los requisitos para relacionarse con la
	 * cuenta.
	 */
	private List<SelectItem> cuentasRelacionables;
	private List<CuentaRelacionPlazoBean> relacionesCuenta;

	@Autowired
	ConsultaRelacionCuentaPlazoBackend consultaRelacionCuentaPlazoBackend;

	@Autowired
	ConsultaConceptoApunteBackend consultaConceptoApunteBackend;
	@Autowired
	TarifasUtils tarifasUtils;

	public void iniciaPaso1() {

		final Flash flash = obtieneFlash();
		if (flash.get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN
				.getParamFlash()) != null) {
			if (!(Boolean) flash
					.get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN
							.getParamFlash())) {
				managedBeanStateRecover.recuperaController(this);
				cuentaOP = altaIPFUtils.getCuentaOperativa(acuerdoPlazo);
				depositoIPF = consultaDatosPeticionIPFBackend
						.ejecutarTRN(this.acuerdoPlazo.getNumeroCuenta(),
								this.importeMinimo);
				depositoIPF.setEstado("S");
				try {
					depositoIPF.setNumSubAc(peticionActivacionIPFBackend
							.ejecutarTRN(depositoIPF.getNumAcuerdo(),
									depositoIPF.getImporte()));
				} catch (final ControlableException ex) {
					this.messageError = ex.getMensajeDetalle();
				}
				depositoIPF
						.setProductosSimples(this.getCondicionesConDescripcion(
								depositoIPF.getProductosSimples(), acuerdoPlazo));
				depositoIPF.setTipoCargo(TipoCargoEnum.ACUERDO.getTipo());
			}
		} else {
			// TODO cambiar cuando empezemos con consulta/modificacion
			if (null != flash.get(ParametrosFlashEnum.MODIFICACION_CUENTA
					.getParamFlash())) {
				this.modificacion = (Boolean) flash
						.get(ParametrosFlashEnum.MODIFICACION_CUENTA
								.getParamFlash());
			} else {
				this.modificacion = false;
			}
			if (null != flash.get(ParametrosFlashEnum.CUENTA_BEAN
					.getParamFlash())) {
				acuerdoPlazo = (CuentaBean) flash
						.get(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash());
			} else {
				throw new NoControlableException("Ha ocurrido un error:",
						"Acuerdo no disponible");
			}

			if (null != flash.get(AltaIPFController.DEPOSITO_IPF)) {
				depositoIPF = (DepositoIPFBean) flash
						.get(AltaIPFController.DEPOSITO_IPF);
				if (null != flash
						.get(ParametrosFlashEnum.NUMERO_CUENTA_OPERATIVA
								.getParamFlash())) {
					cuentaOP = (Long) flash
							.get(ParametrosFlashEnum.NUMERO_CUENTA_OPERATIVA
									.getParamFlash());
				} else {
					throw new NoControlableException("Ha ocurrido un error:",
							"Cuenta Operativa no disponible");
				}
			} else {
				if (null != flash.get(ParametrosFlashEnum.IMPORTE_MINIMO_IPF
						.getParamFlash())) {
					importeMinimo = (BigDecimal) flash
							.get(ParametrosFlashEnum.IMPORTE_MINIMO_IPF
									.getParamFlash());
				}
				if (this.isModificacion()) {
					depositoIPF = consultaDatosPeticionIPFBackend.ejecutarTRN(
							this.acuerdoPlazo.getNumeroCuenta(),
							this.importeMinimo);
					depositoIPF.setEstado("S");
					try {
						depositoIPF.setNumSubAc(peticionActivacionIPFBackend
								.ejecutarTRN(depositoIPF.getNumAcuerdo(),
										depositoIPF.getImporte()));
					} catch (final ControlableException ex) {
						this.messageError = ex.getMensajeDetalle();
					}
					depositoIPF.setProductosSimples(this
							.getCondicionesConDescripcion(
									depositoIPF.getProductosSimples(),
									acuerdoPlazo));
				} else {
					this.ipfs = (List<DepositoIPFBean>) flash
							.get(ParametrosFlashEnum.IPFS_TODAS.getParamFlash());
					if (null != this.ipfs && !this.ipfs.isEmpty()) {
						this.selectedIPF = this.ipfs.get(0).getNumSubAc();
						recalculaDatosIPF();
					} else {
						throw new NoControlableException(
								"Ha ocurrido un error:",
								"Datos del Subacuerdo no disponibles");
					}
				}
				depositoIPF.setTipoCargo(TipoCargoEnum.ACUERDO.getTipo());

				cuentaOP = altaIPFUtils.getCuentaOperativa(acuerdoPlazo);
			}

			if (null != flash.get(ParametrosFlashEnum.CLIENTE.getParamFlash())) {
				cliente = (ClienteBean) flash.get(ParametrosFlashEnum.CLIENTE
						.getParamFlash());
			} else {
				throw new NoControlableException("Ha ocurrido un error:",
						"Cliente seleccionado no disponible");
			}

			if (null != flash.get(ParametrosFlashEnum.ORIGEN_FICHA_CUENTA
					.getParamFlash())) {
				origenFichaCuenta = (boolean) flash
						.get(ParametrosFlashEnum.ORIGEN_FICHA_CUENTA
								.getParamFlash());
			} else {
				origenFichaCuenta = false;
			}
		}

		if (null != flash.get(AltaIPFController.ABONO_INTERES_MODIFICADO)) {
			abonoInteresModificado = (boolean) flash
					.get(AltaIPFController.ABONO_INTERES_MODIFICADO);
		} else {
			abonoInteresModificado = false;
		}
		initialConditionMap = new HashMap<String, CondicionBean>();

	}

	public String irAPaso2() {
		this.flujoContinuar = NavegacionEnum.ALTA_IPF_2.getRuta();
		if (this.hayCondicionesModificadas()) {
			RequestContext.getCurrentInstance().execute(
					"PF('dlgNoGuardar').show()");
			return null;
		}
		return this.irAFlujo();
	}

	public String irAFlujo() {
		final Flash flash = obtieneFlash();
		flash.put(AltaIPFController.DEPOSITO_IPF, depositoIPF);
		flash.put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(), acuerdoPlazo);
		flash.put(ParametrosFlashEnum.CLIENTE.getParamFlash(), cliente);
		flash.put(ParametrosFlashEnum.NUMERO_CUENTA_OPERATIVA.getParamFlash(),
				cuentaOP);
		flash.put(ParametrosFlashEnum.ORIGEN_FICHA_CUENTA.getParamFlash(),
				origenFichaCuenta);
		flash.put(AltaIPFController.ABONO_INTERES_MODIFICADO,
				abonoInteresModificado);
		flash.put(ParametrosFlashEnum.MODIFICACION_CUENTA.getParamFlash(),
				modificacion);
		return this.flujoContinuar;
	}

	public String irAPaso1() {
		this.flujoContinuar = NavegacionEnum.ALTA_IPF_1.getRuta();
		if (this.hayRelacionesModificadas()) {
			RequestContext.getCurrentInstance().execute(
					"PF('dlgNoGuardar').show()");
			return null;
		}
		return this.irAFlujo();
	}

	public void iniciaPaso2() {
		// TODO cambiar cuando empezemos con consulta/modificacion
		// TODO cambiar cuando empezemos con consulta/modificacion
		this.modificacion = true;
		final Flash flash = obtieneFlash();
		if (flash.get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN
				.getParamFlash()) != null) {
			if (!(Boolean) flash
					.get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN
							.getParamFlash())) {
				managedBeanStateRecover.recuperaController(this);
				cuentaOP = altaIPFUtils.getCuentaOperativa(acuerdoPlazo);
			}
		} else {
			if (null != flash.get(ParametrosFlashEnum.MODIFICACION_CUENTA
					.getParamFlash())) {
				this.modificacion = (Boolean) flash
						.get(ParametrosFlashEnum.MODIFICACION_CUENTA
								.getParamFlash());
			} else {
				this.modificacion = false;
			}
			if (null != flash.get(AltaIPFController.DEPOSITO_IPF)) {
				depositoIPF = (DepositoIPFBean) flash
						.get(AltaIPFController.DEPOSITO_IPF);
			} else {
				throw new NoControlableException("Ha ocurrido un error:",
						"Acuerdo IPF no disponible");
			}
			if (null != flash.get(ParametrosFlashEnum.CUENTA_BEAN
					.getParamFlash())) {
				acuerdoPlazo = (CuentaBean) flash
						.get(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash());
			} else {
				throw new NoControlableException("Ha ocurrido un error:",
						"Acuerdo no disponible");
			}
			if (null != flash.get(ParametrosFlashEnum.NUMERO_CUENTA_OPERATIVA
					.getParamFlash())) {
				cuentaOP = (Long) flash
						.get(ParametrosFlashEnum.NUMERO_CUENTA_OPERATIVA
								.getParamFlash());
			} else {
				throw new NoControlableException("Ha ocurrido un error:",
						"Cuenta Operativa no disponible");
			}
			if (null != flash.get(ParametrosFlashEnum.CLIENTE.getParamFlash())) {
				cliente = (ClienteBean) flash.get(ParametrosFlashEnum.CLIENTE
						.getParamFlash());
			} else {
				throw new NoControlableException("Ha ocurrido un error:",
						"Cliente seleccionado no disponible");
			}
			if (null != flash.get(ParametrosFlashEnum.ORIGEN_FICHA_CUENTA
					.getParamFlash())) {
				origenFichaCuenta = (boolean) flash
						.get(ParametrosFlashEnum.ORIGEN_FICHA_CUENTA
								.getParamFlash());
			} else {
				origenFichaCuenta = false;
			}

			this.loadRelacionesCuenta();
		}
		this.cuentasRelacionables = consultaCuentasRelacionables();
		if (null != flash.get(AltaIPFController.ABONO_INTERES_MODIFICADO)) {
			abonoInteresModificado = (boolean) flash
					.get(AltaIPFController.ABONO_INTERES_MODIFICADO);
		} else {
			abonoInteresModificado = false;
		}
	}

	private void loadRelacionesCuenta() {
		if (this.depositoIPF != null
				&& this.depositoIPF.getNumAcuerdo() != null
				&& this.depositoIPF.getNumSubAc() != null) {
			initialRelacionesCuentaMap = new HashMap<Integer, CuentaRelacionPlazoBean>();
			this.relacionesCuenta = consultaRelacionCuentaPlazoBackend
					.ejecutarTRN(this.depositoIPF.getNumAcuerdo(),
							this.depositoIPF.getNumSubAc());

			backUpUtils
					.respaldaArray((ArrayList<CuentaRelacionPlazoBean>) this.relacionesCuenta);
			for (final CuentaRelacionPlazoBean crpBean : this.relacionesCuenta) {
				if (StringUtils.equals(crpBean.getTipoRelacion(),
						ConstantesFuncionales.REL_AC_AC_CUENTA_OPERATIVA)) {
					this.relacionesCuenta.remove(crpBean);
					this.relacionesCuenta.add(0, crpBean);
					break;
				}
			}

			for (int i = 0; i < this.relacionesCuenta.size(); i++) {
				this.relacionesCuenta.get(i)
						.setTipoRelacionDesc(
								catalogoUtils.getCatalogoDesc(
										CatalogoEnum.TP_RL_AC_AC,
										this.relacionesCuenta.get(i)
												.getTipoRelacion()));
				initialRelacionesCuentaMap.put(i,
						SerializationUtils.clone(this.relacionesCuenta.get(i)));
			}
		}

	}

	private List<SelectItem> consultaCuentasRelacionables() {
		final List<SelectItem> resultado = new ArrayList<>();
		if (this.cliente != null && this.cliente.getIdInterna() != null) {

			final List<CuentaClienteBean> cuentaClienteBeanList = consultaCuentasClienteBackEnd
					.ejecutarTRN(this.cliente.getIdInterna());
			if (null != cuentaClienteBeanList) {
				for (final CuentaClienteBean ccb : cuentaClienteBeanList) {
					if (null != ccb && null != ccb.getCuenta()) {
						final String linea = ccb.getCuenta().getCodLinea();
						final String grupo = ccb.getCuenta()
								.getIdGrupoProducto();
						if (StringUtils.equals("03", linea)
								&& StringUtils.equals("11", grupo)) {
							resultado.add(new SelectItem(ccb.getCuenta()
									.getNumeroCuenta()));
						}
					}
				}
			}
		}

		return resultado;
	}

	private boolean containsCuentaOperativa(
			final List<CuentaRelacionPlazoBean> relaciones) {
		for (final CuentaRelacionPlazoBean relacion : relaciones) {
			if (StringUtils.equals(
					ConstantesFuncionales.REL_AC_AC_CUENTA_OPERATIVA,
					relacion.getTipoRelacion())) {
				return true;
			}
		}
		return false;
	}

	private List<ProductoSimpleBean> getCondicionesConDescripcion(
			final List<ProductoSimpleBean> productosSimples,
			final CuentaBean cuenta) {
		final TarifaBean tarifa = new TarifaBean();
		tarifa.setLinea(cuenta.getCodLinea());
		tarifa.setGrupo(cuenta.getIdGrupoProducto());
		tarifa.setProducto(cuenta.getIdProducto());
		tarifa.setId(cuenta.getIdTarifaProducto());
		return tarifasUtils.fillPdsCondDescriptions(productosSimples, tarifa);
	}

	public void cargarCondicion(final TabChangeEvent event) {

		if (!(event.getSource() instanceof AccordionPanel)
				|| !(event.getData() instanceof CondicionBean)) {
			throw new NoControlableException(
					"Ha ocurrido un error al cargar las condiciones",
					this.getClass().getName()
							+ ".cargarCondicion(): El evento cargado no pertenece a un AccordionPanel");
		}
		final String pdsId = ((AccordionPanel) event.getSource())
				.getWidgetVar().substring(5);
		final String condicionId = ((CondicionBean) event.getData()).getClave();

		final List<CondicionBean> condicionList = this
				.getConditionOfSimpleProduct(pdsId);
		if (!condicionList.isEmpty()) {
			for (int i = 0; i < condicionList.size(); i++) {
				if (StringUtils.equals(condicionList.get(i).getClave(),
						condicionId)) {
					if (!condicionList.get(i).isLoaded()) {
						final CondicionBean condicionCargada = this
								.loadCondition(pdsId, condicionList.get(i));
						condicionList.set(i, condicionCargada);
						initialConditionMap.put(pdsId + "-" + condicionId,
								SerializationUtils.clone(condicionCargada));
					}
					break;
				}
			}
		}
	}

	private CondicionBean loadCondition(final String idPds,
			final CondicionBean condicion) {
		CondicionBean resultado = null;
		if (condicion instanceof CondicionComisionBean) {
			resultado = consultaCondicionComisionPlazoBackend.ejecutarTRN(
					this.depositoIPF.getNumAcuerdo(),
					this.depositoIPF.getNumSubAc(), idPds,
					(CondicionComisionBean) condicion);
			if (!((CondicionComisionBean) resultado).getTipo()
					.equalsIgnoreCase("F")) {
				((CondicionComisionBean) resultado)
						.setBaseCalculoComVarDesc(this.getBaseCalculoDesc(
								idPds, condicion.getClave(),
								((CondicionComisionBean) resultado)
										.getEstructuraIdPds(),
								((CondicionComisionBean) resultado)
										.getEstructuraParmCd()));
			}
		} else if (condicion instanceof CondicionInteresBean) {
			resultado = consultaCondicionInteresPlazoBackend.ejecutarTRN(
					this.depositoIPF.getNumAcuerdo(),
					this.depositoIPF.getNumSubAc(), idPds,
					(CondicionInteresBean) condicion);
			((CondicionInteresBean) resultado).setBaseCalculoDesc(this
					.getBaseCalculoDesc(idPds, condicion.getClave(),
							((CondicionInteresBean) resultado)
									.getEstructuraIdPds(),
							((CondicionInteresBean) resultado)
									.getEstructuraParmCd()));

		} else if (condicion instanceof CondicionListaBean) {
			// Carga Done
			resultado = consultaCondicionListaPlazoBackend.ejecutarTRN(
					this.depositoIPF.getNumAcuerdo(),
					this.depositoIPF.getNumSubAc(), idPds,
					(CondicionListaBean) condicion);
			((CondicionListaBean) resultado)
					.setListaNoSeleccionados(getStarryDominioCondiciones(condicion
							.getClave()));
		} else if (condicion instanceof CondicionRangoBean) {
			// Carga Done
			resultado = consultaCondicionRangoPlazoBackend.ejecutarTRN(
					this.depositoIPF.getNumAcuerdo(),
					this.depositoIPF.getNumSubAc(), idPds,
					(CondicionRangoBean) condicion);
		} else if (condicion instanceof CondicionTramoBean) {
			resultado = loadCondicionTramoBean(idPds,
					(CondicionTramoBean) condicion);
		} else if (condicion instanceof CondicionValorListaBean) {
			// Carga Done
			resultado = consultaCondicionValorListaPlazoBackend.ejecutarTRN(
					this.depositoIPF.getNumAcuerdo(),
					this.depositoIPF.getNumSubAc(), idPds,
					(CondicionValorListaBean) condicion);
			((CondicionValorListaBean) resultado).setItems(this
					.getValorListaPlazoItems(idPds,
							(CondicionValorListaBean) condicion,
							((CondicionValorListaBean) resultado)
									.getCodDomParmcd()));

		} else if (condicion instanceof CondicionValorRangoBean) {
			// Carga Done
			resultado = getCondicionValorRangoPlazo(idPds,
					(CondicionValorRangoBean) condicion);
		} else {
			throw new NoControlableException("Ha ocurrido un error:",
					"Tipo de condicion desconocida.");
		}
		if (resultado == null) {
			return condicion;
		}
		resultado.setLoaded(true);
		resultado.setClave(condicion.getClave());
		resultado.setDescripcion(condicion.getDescripcion());
		resultado.setEstado(condicion.getEstado());
		resultado.setFechaInicioValidez(condicion.getFechaInicioValidez());
		resultado.setCodEstrctCd(condicion.getCodEstrctCd());
		resultado.setCodProfCd(condicion.getCodProfCd());
		resultado.setIndCdAc(condicion.getIndCdAc());
		return resultado;
	}

	/**
	 * @param condicion
	 * @return
	 */
	private CondicionValorRangoBean getCondicionValorRangoPlazo(
			final String idPds, final CondicionValorRangoBean condicion) {
		CondicionValorRangoBean resultado;
		resultado = consultaCondicionValorRangoPlazoBackend.ejecutarTRN(
				this.depositoIPF.getNumAcuerdo(),
				this.depositoIPF.getNumSubAc(), idPds, condicion);
		final CondicionRangoBean condicionRango = consultaCondicionRangoBackend
				.ejecutarTRN(this.depositoIPF.getNumAcuerdo(), idPds,
						condicion.getClave());
		resultado.setMinimo(condicionRango.getMinimo());
		resultado.setMaximo(condicionRango.getMaximo());
		resultado.setIncremento(condicionRango.getIncremento());
		resultado.setPreferente(condicionRango.getPreferente());

		final ConceptoApunteBean caBean = consultaConceptoApunteBackend
				.ejecutarTRN(condicion.getClave());
		if (StringUtils
				.equalsIgnoreCase(ConstantesFuncionales.CONCEPTO_APUNTE_MXN,
						caBean.getUnidades())) {
			resultado.setAlternateUnits(true);
		}
		return resultado;
	}

	private List<PreferenceItemBean> getValorListaPlazoItems(
			final String idPds, final CondicionValorListaBean condicion,
			final String codCdParam) {
		final List<PreferenceItemBean> preferenceItemList = getStarryDominioCondiciones(condicion
				.getClave());
		final CondicionListaBean condPadre = this.consultaCondicionListaBackend
				.ejecutarTRN(this.depositoIPF.getNumAcuerdo(), idPds,
						condicion.getClave());

		final List<PreferenceItemBean> resultado = new ArrayList<PreferenceItemBean>();
		for (final PreferenceItemBean preferenceItem : preferenceItemList) {
			if (StringUtils.equals(codCdParam, preferenceItem.getId())) {
				resultado.add(preferenceItem);
			} else {
				for (final PreferenceItemBean padreItem : condPadre.getLista()) {
					if (StringUtils.isNoneBlank(padreItem.getId(),
							preferenceItem.getId())
							&& StringUtils.equals(padreItem.getId(),
									preferenceItem.getId())) {
						resultado.add(preferenceItem);
						break;
					}
				}
			}
		}
		final List<PreferenceItemBean> itemsToBeShown = consultaCatalogoValorListaBackend
				.ejecutarTRN(idPds, condicion.getIdCcps(), condicion.getClave());
		if (itemsToBeShown != null) {
			for (final PreferenceItemBean itemTBS : itemsToBeShown) {
				if (itemTBS.isPreferente()) {
					for (final PreferenceItemBean item : resultado) {
						if (StringUtils.equals(item.getId(), itemTBS.getId())) {
							item.setPreferente(true);
							break;
						}
					}
					break;
				}
			}
		}
		return resultado;
	}

	public boolean hayCondicionesModificadas() {
		if (null != this.initialConditionMap
				&& !this.initialConditionMap.isEmpty()) {
			if (null != this.depositoIPF
					&& null != this.depositoIPF.getProductosSimples()) {
				for (final ProductoSimpleBean pdsBean : this.depositoIPF
						.getProductosSimples()) {
					if (null != pdsBean && null != pdsBean.getCondiciones()) {
						for (final CondicionBean condicion : pdsBean
								.getCondiciones()) {
							final String key = pdsBean.getId() + "-"
									+ condicion.getClave();
							if (this.initialConditionMap.containsKey(key)
									&& !condicion
											.equals(this.initialConditionMap
													.get(key))) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	public boolean hayRelacionesModificadas() {
		if (this.initialRelacionesCuentaMap != null
				&& !this.initialRelacionesCuentaMap.isEmpty()) {
			if (null != this.relacionesCuenta) {
				for (int i = 0; i < this.relacionesCuenta.size(); i++) {
					if (!this.initialRelacionesCuentaMap.containsKey(i)
							|| !this.relacionesCuenta.get(i).equals(
									this.initialRelacionesCuentaMap.get(i))) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public void guardarCondiciones() {
		// TODO a donde vamos al pulsar guardar
		if (!this.initialConditionMap.isEmpty()) {
			if (null != this.depositoIPF
					&& null != this.depositoIPF.getProductosSimples()) {
				for (final ProductoSimpleBean pdsBean : this.depositoIPF
						.getProductosSimples()) {
					if (null != pdsBean && null != pdsBean.getCondiciones()) {
						final String idPds = pdsBean.getId();
						//TODO verificar
						verificaDatosDuracion(pdsBean);
						for (final CondicionBean condicion : pdsBean
								.getCondiciones()) {
							final String key = idPds + "-"
									+ condicion.getClave();
							if (condicion.isEditable()
									&& this.initialConditionMap
											.containsKey(key)
									&& !condicion
											.equals(this.initialConditionMap
													.get(key))) {
								this.modificaCondicion(
										this.depositoIPF.getNumAcuerdo(),
										this.depositoIPF.getNumSubAc(), idPds,
										condicion);
								if (TarifasUtils.isImporteMinimoIPF(idPds,
										condicion)) {
									// actualizamos el valor del importe minimo
									this.depositoIPF
											.setImporte(((CondicionValorRangoBean) condicion)
													.getValor());
								}
								// Actualizamos el mapa de condiciones iniciales
								this.initialConditionMap.put(key,
										SerializationUtils.clone(condicion));
							}
						}
					}
				}
			}
		}

		RequestContext.getCurrentInstance().execute("PF('dlgSuccess').show()");

	}
	
	private void verificaDatosDuracion(ProductoSimpleBean producto){
		if(altaIPFUtils.isPRLVS(acuerdoPlazo)){
			BigDecimal duracion = new BigDecimal(0);
			BigDecimal frecuencia = new BigDecimal(0);
			if(GRUPO_CONDICION_TIEMPO.equals(producto.getId())){
				for (CondicionBean condicion : producto.getCondiciones()) {
					if(DURACION_IPF_TARFIA.equals(condicion.getClave())){
						duracion = ((CondicionValorRangoBean) condicion).getValor();
					}
					if(FRECUENCIA_IPF_TARIFA.equals(condicion.getClave())){
						frecuencia = ((CondicionValorRangoBean) condicion).getValor();
					}
				}
			}
			if(duracion.compareTo(frecuencia) != 0){
				throw new ControlableException("Error en condiciones. ",
						"DURACION debe ser igual a FRECUENCIA LIQUIDACION.");
			}
		}
	}

	private void guardarCuentas() {
		// TODO a donde vamos al pulsar guardar
		if (this.initialRelacionesCuentaMap != null
				&& !this.initialRelacionesCuentaMap.isEmpty()) {
			if (null != this.relacionesCuenta) {

				for (int i = 0; i < this.relacionesCuenta.size(); i++) {
					final CuentaRelacionPlazoBean crpBean = this.relacionesCuenta
							.get(i);
					if (!this.initialRelacionesCuentaMap.containsKey(i)
							|| !crpBean.equals(this.initialRelacionesCuentaMap
									.get(i))) {
						if (StringUtils
								.equals(crpBean.getTipoRelacion(),
										ConstantesFuncionales.REL_AC_AC_CUENTA_OPERATIVA)) {
							this.altaRelacionCuentaPlazoBackend.ejecutarTRN(
									this.depositoIPF.getNumAcuerdo(),
									this.depositoIPF.getNumSubAc(),
									crpBean.getNumAcuerdo(),
									crpBean.getTipoRelacion(),
									crpBean.getPorcentaje());
							this.cuentaOP = crpBean.getNumAcuerdo();
						} else {
							if (!abonoInteresModificado) {
								if (crpBean.getNumAcuerdo().longValue() == this.cuentaOP
										.longValue()
										&& crpBean.getPorcentaje() != BigDecimal.valueOf(100D)) {
									throw new ControlableException(
											"¡Atención! El porcentaje de abono de intereses es inferior al 100%.",
											"Por favor, revise el valor asignado.");
								}
								this.altaRelacionCuentaPlazoBackend
										.ejecutarTRN(this.depositoIPF
												.getNumAcuerdo(),
												this.depositoIPF.getNumSubAc(),
												crpBean.getNumAcuerdo(),
												crpBean.getTipoRelacion(),
												crpBean.getPorcentaje());
								abonoInteresModificado = true;
							} else {
								if (this.relacionesCuenta.size() == 2
										&& this.initialRelacionesCuentaMap
												.get(i).getNumAcuerdo()
												.longValue() == this.cuentaOP
												.longValue()) {
									this.altaRelacionCuentaPlazoBackend
											.ejecutarTRN(this.depositoIPF
													.getNumAcuerdo(),
													this.depositoIPF
															.getNumSubAc(),
													crpBean.getNumAcuerdo(),
													crpBean.getTipoRelacion(),
													crpBean.getPorcentaje());
								} else {
									switch (crpBean.getEstadoListado()) {
									case NUEVO:
										this.altaRelacionCuentaPlazoBackend
												.ejecutarTRN(
														this.depositoIPF
																.getNumAcuerdo(),
														this.depositoIPF
																.getNumSubAc(),
														crpBean.getNumAcuerdo(),
														crpBean.getTipoRelacion(),
														crpBean.getPorcentaje());
										break;
									case ELIMINADO:
										this.bajaRelacionCuentaPlazoBackend
												.ejecutarTRN(
														this.depositoIPF
																.getNumAcuerdo(),
														this.depositoIPF
																.getNumSubAc(),
														crpBean.getNumAcuerdo(),
														crpBean.getTipoRelacion(),
														crpBean.getPorcentaje());
										break;
									case MODIFICADO:
										this.modificacionRelacionCuentaPlazoBackend
												.ejecutarTRN(
														this.depositoIPF
																.getNumAcuerdo(),
														this.depositoIPF
																.getNumSubAc(),
														crpBean.getNumAcuerdo(),
														crpBean.getTipoRelacion(),
														crpBean.getPorcentaje());
										break;
									default:
										throw new ControlableException(
												"Ha ocurrido un error al guardar las relaciones",
												"Ha ocurrido un error al guardar las relaciones: estado de relacion \""
														+ crpBean
																.getEstadoListado()
																.name()
														+ "\" no tiene operacion de guardado asignada");
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private void modificaCondicion(final Long numAcuerdo,
			final Integer numSubAc, final String idPds,
			final CondicionBean condicion) {
		if (condicion instanceof CondicionInteresBean) {
			this.modificaCondicionInteresPlazoBackend.ejecutarTRN(numAcuerdo,
					numSubAc, idPds, (CondicionInteresBean) condicion);
		} else if (condicion instanceof CondicionValorListaBean) {
			this.modificaCondicionValorListaPlazoBackend.ejecutarTRN(
					numAcuerdo, numSubAc, idPds,
					(CondicionValorListaBean) condicion);
		} else if (condicion instanceof CondicionValorRangoBean) {
			this.modificaCondicionValorRangoPlazoBackend.ejecutarTRN(
					numAcuerdo, numSubAc, idPds,
					(CondicionValorRangoBean) condicion);
		}
	}

	private String getBaseCalculoDesc(final String idPds,
			final String idParmcd, final String estructuraIdPds,
			final String estructuraIdParmcd) {
		final List<BaseCalculoCondicionesBean> respuesta = this.consultaListaBaseCalculoCondicionesBackend
				.ejecutarTRN(idPds, idParmcd);
		for (final BaseCalculoCondicionesBean datos : respuesta) {
			if (StringUtils.isNoneBlank(datos.getIdPds(), datos.getParmCd(),
					datos.getNomParmCd())
					&& StringUtils.equals(datos.getIdPds(), estructuraIdPds)
					&& StringUtils
							.equals(datos.getParmCd(), estructuraIdParmcd)) {
				return datos.getNomParmCd();
			}
		}
		return null;
	}

	/**
	 * @param condicion
	 * @param resultado
	 */
	private List<PreferenceItemBean> getStarryDominioCondiciones(
			final String idParmCd) {
		final List<PreferenceItemBean> listItems = new ArrayList<PreferenceItemBean>();
		final List<DominioCondicionBean> listaDominionCondiciones = consultaListaDominioCondicionesBackend
				.ejecutarTRN(idParmCd);
		if (null != listaDominionCondiciones) {
			for (final DominioCondicionBean dcb : listaDominionCondiciones) {
				final PreferenceItemBean ssi = new PreferenceItemBean();
				ssi.setId(dcb.getCodDomParmCd());
				ssi.setDesc(dcb.getDescDomParmCd());
				listItems.add(ssi);
			}
		}
		return listItems;
	}

	/**
	 * @param pdsId
	 * @param condicion
	 * @return
	 */
	private CondicionTramoBean loadCondicionTramoBean(final String idPds,
			final CondicionTramoBean condicion) {
		final CondicionTramoBean resultado = consultaCondicionTramoPlazoBackend
				.ejecutarTRN(this.depositoIPF.getNumAcuerdo(),
						this.depositoIPF.getNumSubAc(), idPds, condicion);
		resultado.setBaseCalculoDesc(this.getBaseCalculoDesc(idPds,
				condicion.getClave(), resultado.getIdPds(),
				resultado.getIdParamcd()));
		final CondicionTramoBean tempTramo = consultaDetalleTramoPlazoBackend
				.ejecutarTRN(this.depositoIPF.getNumAcuerdo(),
						this.depositoIPF.getNumSubAc(), idPds,
						condicion.getClave(), resultado.getIdTrameado(),
						condicion.getFechaInicioValidez(), null);
		resultado.setMasDatos(tempTramo.isMasDatos());
		resultado.setUltimoDatoConsultaAnterior(tempTramo.getSubtramoList()
				.get(tempTramo.getSubtramoList().size() - 1).getNumTramo());
		resultado.setSubtramoList(tempTramo.getSubtramoList());
		resultado.setCabeceraList(tempTramo.getCabeceraList());
		if (null != resultado.getCabeceraList()) {
			for (final CabeceraTramoBean cabecera : resultado.getCabeceraList()) {
				cabecera.setDescripcion(this.getBaseCalculoDesc(idPds,
						condicion.getClave(), cabecera.getIdPds(),
						cabecera.getIdParamCd()));
				if (null == cabecera.getUdMedidas()) {
					this.loadCondicionTramoColumnas(cabecera.getIdParamCd(),
							cabecera.getPosCol(), resultado.getSubtramoList());
				} else if (!cabecera.getUdMedidas().equalsIgnoreCase("U")) {
					cabecera.setDescripcion(cabecera.getDescripcion()
							+ " ("
							+ catalogoUtils.getCatalogoDesc(CatalogoEnum.TP_UM,
									cabecera.getUdMedidas()) + ")");
				}
			}

		}
		if (null != resultado.getSubtramoList()) {
			final List<String> listIdParmCd = new ArrayList<>();
			for (final SubTramoBean stBean : resultado.getSubtramoList()) {
				if (null != stBean.getCondicionBean()
						&& (stBean.getCondicionBean() instanceof CondicionValorListaBean || stBean
								.getCondicionBean() instanceof CondicionListaBean)) {
					listIdParmCd.add(stBean.getCondicionBean().getClave());
				}
			}
			if (!listIdParmCd.isEmpty()) {
				final List<DominioCondicionBean> lista = new ArrayList<>();
				for (final String idParmCd : listIdParmCd) {
					lista.addAll(consultaListaDominioCondicionesBackend
							.ejecutarTRN(idParmCd));
				}
				if (!lista.isEmpty()) {
					for (final SubTramoBean stBean : resultado
							.getSubtramoList()) {
						for (final DominioCondicionBean dcBean : lista) {
							if (StringUtils.isNoneBlank(stBean.getValue(),
									dcBean.getCodDomParmCd())
									&& StringUtils.equals(stBean.getValue(),
											dcBean.getCodDomParmCd())) {
								stBean.setValue(dcBean.getDescDomParmCd());
								if (stBean.getCondicionBean() instanceof CondicionValorListaBean) {
									final PreferenceItemBean piBean = new PreferenceItemBean();
									piBean.setId(dcBean.getCodDomParmCd());
									piBean.setDesc(dcBean.getDescDomParmCd());
									final List<PreferenceItemBean> listaPIBean = new ArrayList<>(
											1);
									listaPIBean.add(piBean);
									((CondicionValorListaBean) stBean
											.getCondicionBean())
											.setItems(listaPIBean);
								}
								break;
							}
						}
						if (stBean.getCondicionBean() instanceof CondicionListaBean) {
							if (null != ((CondicionListaBean) stBean
									.getCondicionBean()).getLista()) {
								for (final PreferenceItemBean piBean : ((CondicionListaBean) stBean
										.getCondicionBean()).getLista()) {
									for (final DominioCondicionBean dcBean : lista) {
										if (StringUtils.isNoneBlank(
												piBean.getId(),
												dcBean.getCodDomParmCd())
												&& StringUtils.equals(piBean
														.getId(), dcBean
														.getCodDomParmCd())) {
											piBean.setDesc(dcBean
													.getDescDomParmCd());
											break;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return resultado;
	}

	private void loadCondicionTramoColumnas(final String parmCd,
			final Integer posCol, final List<SubTramoBean> subtramoList) {
		final List<DominioCondicionBean> lista = consultaListaDominioCondicionesBackend
				.ejecutarTRN(parmCd);
		if (null != subtramoList && lista != null) {
			for (final SubTramoBean stBean : subtramoList) {
				final String codParmCdEstr = stBean.getLimiteTramo()
						.getCodDomParmCd(posCol);
				for (final DominioCondicionBean dcBean : lista) {
					if (StringUtils.isNoneBlank(codParmCdEstr,
							dcBean.getCodDomParmCd())
							&& StringUtils.equals(codParmCdEstr,
									dcBean.getCodDomParmCd())) {
						stBean.getLimiteTramo().setDescDomParmCd(posCol,
								dcBean.getDescDomParmCd());
						break;
					}
				}
			}
		}

	}

	public void masDatosTramos(final String pdsId, final String paramCd) {
		if (StringUtils.isNotBlank(pdsId) && StringUtils.isNotBlank(paramCd)) {
			final List<CondicionBean> condicionList = this
					.getConditionOfSimpleProduct(pdsId);
			if (!condicionList.isEmpty()) {
				for (int i = 0; i < condicionList.size(); i++) {
					if (StringUtils.equals(condicionList.get(i).getClave(),
							paramCd)) {
						final CondicionTramoBean condicionAPaginar = (CondicionTramoBean) condicionList
								.get(i);
						if (condicionAPaginar.cargarMasDatos()) {
							final CondicionTramoBean condicionCargada = consultaDetalleTramoPlazoBackend
									.ejecutarTRN(
											this.depositoIPF.getNumAcuerdo(),
											this.depositoIPF.getNumSubAc(),
											pdsId,
											condicionAPaginar.getClave(),
											condicionAPaginar.getIdTrameado(),
											condicionAPaginar
													.getFechaInicioValidez(),
											condicionAPaginar
													.getUltimoDatoConsultaAnterior());
							condicionAPaginar.setMasDatos(condicionCargada
									.isMasDatos());
							condicionAPaginar
									.setUltimoDatoConsultaAnterior(condicionCargada
											.getSubtramoList()
											.get(condicionCargada
													.getSubtramoList().size() - 1)
											.getNumTramo());

							condicionAPaginar.getSubtramoList().addAll(
									condicionCargada.getSubtramoList().subList(
											1,
											condicionCargada.getSubtramoList()
													.size()));
							if (null != condicionAPaginar.getCabeceraList()) {
								for (final CabeceraTramoBean cabecera : condicionAPaginar
										.getCabeceraList()) {
									if (null == cabecera.getUdMedidas()) {
										this.loadCondicionTramoColumnas(
												cabecera.getIdParamCd(),
												cabecera.getPosCol(),
												condicionAPaginar
														.getSubtramoList());
									}
								}
							}
							condicionList.set(i, condicionAPaginar);
							initialConditionMap
									.put(pdsId + "-" + paramCd,
											SerializationUtils
													.clone(condicionAPaginar));
						} else {
							((CondicionTramoBean) initialConditionMap.get(pdsId
									+ "-" + paramCd)).avanzarIndice();
						}
						condicionAPaginar.avanzarIndice();
						break;
					}
				}
			}
		}
	}

	/**
	 * @param pdsId
	 * @return
	 */
	private List<CondicionBean> getConditionOfSimpleProduct(final String pdsId) {
		List<CondicionBean> condicionList = null;
		if (this.depositoIPF != null
				&& this.depositoIPF.getProductosSimples() != null) {
			final List<ProductoSimpleBean> pdsList = this.depositoIPF
					.getProductosSimples();
			for (final ProductoSimpleBean producto : pdsList) {
				if (StringUtils.equals(producto.getId(), pdsId)) {
					condicionList = producto.getCondiciones();
					break;
				}
			}

		}

		if (condicionList == null) {
			throw new NoControlableException(
					"Producto Simple no encontrado",
					this.getClass().getName()
							+ ": "
							+ pdsId
							+ " No encontrado en el listado de productos simples");
		}
		return condicionList;
	}

	public String activarPlazo() {
		if (this.depositoIPF != null
				&& StringUtils.isNotBlank(this.depositoIPF.getEstado())) {
			this.guardarCuentas();
			if (depositoIPF.getEstado().equals(ESTADO_SOLICITADO)) {
				depositoIPF
						.setEstado(aprobacionPlazoBackend.ejecutarTRN(
								depositoIPF.getNumAcuerdo(),
								depositoIPF.getNumSubAc()));
			}
			final Date fechaVtoCondicion = TarifasUtils
					.getCondicionFechaVencimientoIPF(depositoIPF
							.getProductosSimples());
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (final InterruptedException e) {
			}
			activacionPlazoBackend.ejecutarTRN(depositoIPF,
					altaIPFUtils.getFechaVencimientoValida(fechaVtoCondicion));
			RequestContext.getCurrentInstance().execute(
					"PF('dlgSuccessActivation').show()");
		}
		return null;

	}

	public String guardarSoloCuentas() {
		this.guardarCuentas();
		this.loadRelacionesCuenta();
		RequestContext.getCurrentInstance().execute("PF('dlgSuccess').show()");
		return null;
	}

	public String irAFichaCuenta() {
		this.obtieneFlash().put(
				ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				this.acuerdoPlazo);
		return NavegacionEnum.FICHA_CUENTA.getRuta();
	}

	public void editarRelacionado(final CuentaRelacionPlazoBean relacionadaBean) {
		if (relacionadaBean != null) {
			relacionadaBean.setEstadoListado(EstadoListadosEnum.MODIFICADO);
		}
	}

	public void recuperarDatosRelacionado(
			final CuentaRelacionPlazoBean relacionadaBean) {
		if (relacionadaBean != null) {
			backUpUtils.recuperaBean(relacionadaBean);
		}
	}

	public void removerRelacionado(final CuentaRelacionPlazoBean relacionadaBean) {
		if (relacionadaBean != null) {
			if (relacionadaBean.getEstadoListado() == EstadoListadosEnum.NUEVO) {
				this.relacionesCuenta.remove(relacionadaBean);
			} else {
				relacionadaBean.setEstadoListado(EstadoListadosEnum.ELIMINADO);
			}
		}
	}

	public void adicionarRelacion() {
		if (this.relacionesCuenta != null) {
			final CuentaRelacionPlazoBean relacionOperativa = new CuentaRelacionPlazoBean();
			relacionOperativa
					.setTipoRelacion(ConstantesFuncionales.REL_AC_AC_ABONO_INTERES);
			relacionOperativa.setTipoRelacionDesc(catalogoUtils
					.getCatalogoDesc(CatalogoEnum.TP_RL_AC_AC,
							ConstantesFuncionales.REL_AC_AC_ABONO_INTERES));
			relacionOperativa.setEstadoListado(EstadoListadosEnum.NUEVO);
			relacionOperativa.setPorcentaje(new BigDecimal(0));
			this.relacionesCuenta.add(relacionOperativa);
		}
	}

	/**
	 * Método para obtener la ruta a la vista de alta de cuentas.
	 * 
	 * @param bean
	 *            Bean que recibira el resultado del alta
	 * @return ruta de la vista de alta de cuentas.
	 */
	public String altaCuenta(final CuentaRelacionPlazoBean acc) {
		final TarifaBean tarifaBean = new TarifaBean();
		tarifaBean.setLinea(this.acuerdoPlazo.getCodLinea());
		tarifaBean.setGrupo(this.acuerdoPlazo.getIdGrupoProducto());
		TarifaRelacionBean tarifaRelacionBean = null;
		for (final TarifaRelacionBean relacion : relacionTarifasLoaderTask
				.getCatalogo(tarifaBean)) {
			if (relacion.getCodigoRelacion().equals(acc.getTipoRelacion())) {
				tarifaRelacionBean = relacion;
			}
		}
		obtieneFlash().put(
				ParametrosFlashEnum.TARIFA_RELACIONADA.getParamFlash(),
				tarifaRelacionBean);
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(),
				this.cliente);
		managedBeanStateRecover
				.enviaController(this, NavegacionEnum.ALTA_IPF_2);
		this.obtieneFlash().put(
				ParametrosFlashEnum.SUBFLUJO_ALTA_CUENTA.getParamFlash(), true);
		return NavegacionEnum.ALTA_CUENTA1.getRuta();
	}

	public boolean isRelacionesCuentaAbonoNoEliminadosMayor2() {
		if (this.relacionesCuenta != null) {
			int i = 0;
			for (final CuentaRelacionPlazoBean crpBean : this.relacionesCuenta) {
				if (StringUtils.equals(crpBean.getTipoRelacion(),
						ConstantesFuncionales.REL_AC_AC_ABONO_INTERES)
						&& crpBean.getEstadoListado() != EstadoListadosEnum.ELIMINADO) {
					i++;
				}
			}
			return i > 1;
		}
		;
		return false;
	}

	public String irAHome() {
		this.obtieneFlash().put(
				ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				this.acuerdoPlazo);
		return NavegacionEnum.FICHA_CUENTA.getRuta();
	}

	public void recalculaDatosIPF() {
		if (this.acuerdoPlazo != null
				&& this.acuerdoPlazo.getNumeroCuenta() != null
				&& this.selectedIPF != null) {

			depositoIPF = consultaDatosIPFBackend.ejecutarTRN(
					this.acuerdoPlazo.getNumeroCuenta(), this.selectedIPF);
			depositoIPF.setNumSubAc(this.selectedIPF);

			final DatosDetalleIPFBean datosDetalle = consultaDatosDetalleIPFBackend
					.ejecutarTRN(this.acuerdoPlazo.getNumeroCuenta(),
							this.selectedIPF);
			depositoIPF.setImporte(datosDetalle.getImporte());
			depositoIPF.setFechaApertura(datosDetalle.getFechaApertura());
			depositoIPF.setFechaProxLiq(datosDetalle.getFechaProxLiq());
			depositoIPF.setFechaUltLiq(datosDetalle.getFechaUltLiq());
			depositoIPF.setFechaVencimiento(datosDetalle.getFechaVencimiento());
			depositoIPF.setDuracion(new Double(datosDetalle.getDuracion()).intValue()
					+ " "
					+ catalogoUtils.getCatalogoDesc(CatalogoEnum.TP_UM,
							datosDetalle.getDuracionUds()));
			depositoIPF.setFreqLiquidacion(new Double(datosDetalle.getFreqLiquidacion()).intValue()
					+ " "
					+ catalogoUtils.getCatalogoDesc(CatalogoEnum.TP_UM,
							datosDetalle.getFreqLiquidacionUds()));
			if (null != datosDetalle.getInteresVIntValor()) {
				if (datosDetalle.getInteresVIntValor().intValue() == 0
						&& StringUtils.isNoneEmpty(datosDetalle.getTrameado())
						&& (!"V".equalsIgnoreCase(datosDetalle.getEstado()))
						&& (!"A".equalsIgnoreCase(datosDetalle.getEstado()))) {
					depositoIPF.setTipoInteres("Trm. "
							+ datosDetalle.getTrameado());
				} else if (null != datosDetalle.getInteresVCodRefInt()
						&& datosDetalle.getInteresVIntValor().intValue() == 0) {
					depositoIPF.setTipoInteres(catalogoUtils.getCatalogoDesc(
							CatalogoEnum.TP_REF_INT,
							datosDetalle.getInteresVCodRefInt())
							+ datosDetalle.getInteresVIntIncrem());
				} else {
					depositoIPF.setTipoInteres(""
							+ datosDetalle.getInteresVIntValor());
				}
			}

			depositoIPF.setProductosSimples(this.getCondicionesConDescripcion(
					depositoIPF.getProductosSimples(), acuerdoPlazo));

		}
	}

	public String calculaNombreEstadoIPF() {
		if (this.depositoIPF != null
				&& StringUtils.isNotBlank(this.depositoIPF.getEstado())) {
			return catalogoUtils.getCatalogoDesc(CatalogoEnum.SIT_BLOQUEO,
					depositoIPF.getEstado());
		} else {
			return null;
		}
	}

	public Boolean esEstadoModificable() {
		if (this.ipfs != null && this.depositoIPF != null
				&& this.depositoIPF.getNumSubAc() != null
				&& StringUtils.isNotBlank(this.depositoIPF.getEstado())) {

			final TarifaBean tarifa = new TarifaBean();
			tarifa.setLinea(acuerdoPlazo.getCodLinea());
			tarifa.setGrupo(acuerdoPlazo.getIdGrupoProducto());
			tarifa.setProducto(acuerdoPlazo.getIdProducto());
			tarifa.setId(acuerdoPlazo.getIdTarifaProducto());
			if (TarifasUtils.esPlazoConCuota(tarifa)) {
				// Las tarifas de pago cuota no son multiImposicion y por lo
				// tanto
				// solo podemos modificar la última
				if (this.ipfs.get(this.ipfs.size() - 1).getNumSubAc()
						.intValue() != this.depositoIPF.getNumSubAc()) {
					return false;
				}
			}
			return StringUtils.equals(depositoIPF.getEstado(),
					AltaIPFController.ESTADO_APROBADO)
					|| StringUtils.equals(depositoIPF.getEstado(),
							AltaIPFController.ESTADO_SOLICITADO);
		} else {
			return false;
		}
	}

	public void solicitaCambioEstadoIPF() {
		if (this.hayCondicionesModificadas()) {
			RequestContext.getCurrentInstance().execute(
					"PF('dlgNoGuardar').show()");
		} else {
			this.cambioEstadoIPF();
		}
	}

	public void cambioEstadoIPF() {
		if (this.depositoIPF != null
				&& this.depositoIPF.getProductosSimples() != null) {
			final Date fechaVtoCondicion = TarifasUtils
					.getCondicionFechaVencimientoIPF(depositoIPF
							.getProductosSimples());
			if (StringUtils.equals(depositoIPF.getEstado(),
					AltaIPFController.ESTADO_SOLICITADO)) {
				aprobacionPlazoBackend.ejecutarTRN(depositoIPF.getNumAcuerdo(),
						depositoIPF.getNumSubAc());
				try {
					activacionPlazoBackend
							.ejecutarTRN(
									depositoIPF,
									altaIPFUtils
											.getFechaVencimientoValida(fechaVtoCondicion));
				} catch (final ControlableException ex) {
					this.messageError = ex.getMensajeDetalle();
				}
			} else {
				activacionPlazoBackend.ejecutarTRN(depositoIPF, altaIPFUtils
						.getFechaVencimientoValida(fechaVtoCondicion));
			}
			this.recalculaDatosIPF();
		}
	}

	public Boolean esCondicionModificable() {
		Boolean result = false;
		if (modificacion) {
			result = true;
		} else {
			final TarifaBean tarifa = new TarifaBean();
			tarifa.setLinea(acuerdoPlazo.getCodLinea());
			tarifa.setGrupo(acuerdoPlazo.getIdGrupoProducto());
			tarifa.setProducto(acuerdoPlazo.getIdProducto());
			tarifa.setId(acuerdoPlazo.getIdTarifaProducto());
			if (TarifasUtils.esPlazoConCuota(tarifa)) {
				// Las tarifas de pago cuota no son multiImposicion y por lo
				// tanto solo podemos modificar la última
				// si esta se encuenta en estado solicitado
				if (this.ipfs.get(this.ipfs.size() - 1).getNumSubAc()
						.intValue() == this.depositoIPF.getNumSubAc()) {
					result = StringUtils.equals(depositoIPF.getEstado(),
							AltaIPFController.ESTADO_SOLICITADO);
				}
			} else {
				result = StringUtils.equals(depositoIPF.getEstado(),
						AltaIPFController.ESTADO_SOLICITADO);
			}
		}

		return result;
	}

	/**
	 * @return Metodo utilizado para recuperar una instancia de Flash
	 */
	public Flash obtieneFlash() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
	}

	/**
	 * @return the acuerdoPlazo
	 */
	public CuentaBean getAcuerdoPlazo() {
		return acuerdoPlazo;
	}

	/**
	 * @param acuerdoPlazo
	 *            the acuerdoPlazo to set
	 */
	public void setAcuerdoPlazo(final CuentaBean acuerdoPlazo) {
		this.acuerdoPlazo = acuerdoPlazo;
	}

	/**
	 * @return the importeMinimo
	 */
	public BigDecimal getImporteMinimo() {
		return importeMinimo;
	}

	/**
	 * @param importeMinimo
	 *            the importeMinimo to set
	 */
	public void setImporteMinimo(final BigDecimal importeMinimo) {
		this.importeMinimo = importeMinimo;
	}

	/**
	 * @return the cliente
	 */
	public ClienteBean getCliente() {
		return cliente;
	}

	/**
	 * @param cliente
	 *            the cliente to set
	 */
	public void setCliente(final ClienteBean cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return the depositoIPF
	 */
	public DepositoIPFBean getDepositoIPF() {
		return depositoIPF;
	}

	/**
	 * @param tarifaIPF
	 *            the depositoIPF to set
	 */
	public void setDepositoIPF(final DepositoIPFBean depositoIPF) {
		this.depositoIPF = depositoIPF;
	}

	/**
	 * @return the modificacion
	 */
	public boolean isModificacion() {
		return modificacion;
	}

	/**
	 * @param modificacion
	 *            the modificacion to set
	 */
	public void setModificacion(final boolean modificacion) {
		this.modificacion = modificacion;
	}

	/**
	 * @return the relacionesCuenta
	 */
	public List<CuentaRelacionPlazoBean> getRelacionesCuenta() {
		return relacionesCuenta;
	}

	/**
	 * @param relacionesCuenta
	 *            the relacionesCuenta to set
	 */
	public void setRelacionesCuenta(
			final List<CuentaRelacionPlazoBean> relacionesCuenta) {
		this.relacionesCuenta = relacionesCuenta;
	}

	/**
	 * @param cuentasRelacionables
	 *            the cuentasRelacionables to set
	 */
	public void setCuentasRelacionables(
			final List<SelectItem> cuentasRelacionables) {
		this.cuentasRelacionables = cuentasRelacionables;
	}

	/**
	 * @return the cuentasRelacionables
	 */
	public List<SelectItem> getCuentasRelacionables() {
		return this.cuentasRelacionables;
	}

	/**
	 * @return the origenFichaCuenta
	 */
	public boolean isOrigenFichaCuenta() {
		return origenFichaCuenta;
	}

	/**
	 * @param origenFichaCuenta
	 *            the origenFichaCuenta to set
	 */
	public void setOrigenFichaCuenta(final boolean origenFichaCuenta) {
		this.origenFichaCuenta = origenFichaCuenta;
	}

	/**
	 * @return the messageError
	 */
	public String getMessageError() {
		return messageError;
	}

	/**
	 * @param messageError
	 *            the messageError to set
	 */
	public void setMessageError(final String messageError) {
		this.messageError = messageError;
	}

	/**
	 * @return the selectedIPF
	 */
	public Integer getSelectedIPF() {
		return selectedIPF;
	}

	/**
	 * @param selectedIPF
	 *            the selectedIPF to set
	 */
	public void setSelectedIPF(final Integer selectedIPF) {
		this.selectedIPF = selectedIPF;
	}

	/**
	 * @return the ipfs
	 */
	public List<DepositoIPFBean> getIpfs() {
		return ipfs;
	}

	/**
	 * @param ipfs
	 *            the ipfs to set
	 */
	public void setIpfs(final List<DepositoIPFBean> ipfs) {
		this.ipfs = ipfs;
	}

}
