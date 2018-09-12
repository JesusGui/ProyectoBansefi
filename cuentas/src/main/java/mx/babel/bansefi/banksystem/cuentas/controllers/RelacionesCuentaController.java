package mx.babel.bansefi.banksystem.cuentas.controllers;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ProveedorMensajeSpringUtils;
import mx.babel.arq.comun.utils.VariablePlantillaUtils;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.arq.storage.annotations.StoreStep;
import mx.babel.arq.storage.beans.StorageMgrBean;
import mx.babel.bansefi.banksystem.base.backends.ConsultaCuentaBackend;
import mx.babel.bansefi.banksystem.base.backends.ConsultaCuentasClienteBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaDocumentosBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaMinimaPersonaBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaPersonaFisicaBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaRelacionPersonaCuentaBackEnd;
import mx.babel.bansefi.banksystem.base.backends.busquedas.BusquedaIdExternaBackEnd;
import mx.babel.bansefi.banksystem.base.beans.domain.ClienteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaFisicaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaClienteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.TipoCliente;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.CuentaRelacionadaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.CuentaRelacionadaBeanTipoComparator;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.PanBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.RelacionadoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.RelacionadoBeanPorcentajeComparator;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.RelacionadoBeanTipoComparator;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.TipoAltaEnum;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.TipoRelacionPersonaCuenta;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.BusquedaEnum;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.cuentas.enums.DialogoListadoBean;
import mx.babel.bansefi.banksystem.cuentas.enums.DialogoRelacionesCuentaListadoEnum;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;
import mx.babel.bansefi.banksystem.base.enums.EstadosCuentaEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.BeanBackUpUtils;
import mx.babel.bansefi.banksystem.base.utils.CatalogoGruposAcuerdosInstrumentalesLoaderTask;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.cuentas.utils.DialogoRelacionesCuentaListadoUtils;
import mx.babel.bansefi.banksystem.base.utils.EstadosCuentaEnumUtils;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;
import mx.babel.bansefi.banksystem.base.utils.WordUtils;
import mx.babel.bansefi.banksystem.cuentas.backends.AltaRelacionCuentaCuentaBackEnd;
import mx.babel.bansefi.banksystem.cuentas.backends.AltaRelacionPersonaCuentaBackEnd;
import mx.babel.bansefi.banksystem.cuentas.backends.AnulaDocumentoBackEnd;
import mx.babel.bansefi.banksystem.cuentas.backends.AprobarCuentaBackEnd;
import mx.babel.bansefi.banksystem.cuentas.backends.BajaRelacionCuentaCuentaBackEnd;
import mx.babel.bansefi.banksystem.cuentas.backends.BajaRelacionPersonaCuentaBackEnd;
import mx.babel.bansefi.banksystem.cuentas.backends.ConstituirCuentaBackEnd;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaCamposDocumentoBackEnd;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaDatosAdicionalesBackEnd;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaDocumentosAEmitirBackEnd;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaEstadosDocumentoBackEnd;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaNiveladoraCuentaBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaRelacionesCuentaCuentasBackEnd;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaRelacionesTarifaBackEnd;
import mx.babel.bansefi.banksystem.cuentas.backends.EmisionDocumentosBackEnd;
import mx.babel.bansefi.banksystem.cuentas.backends.FormalizaDocumentoBackEnd;
import mx.babel.bansefi.banksystem.cuentas.backends.ModificaCampoDocumentoBackEnd;
import mx.babel.bansefi.banksystem.cuentas.backends.ModificaRelacionCuentaCuentaBackEnd;
import mx.babel.bansefi.banksystem.cuentas.backends.ModificaRelacionCuentaPersonaBackEnd;
import mx.babel.bansefi.banksystem.cuentas.backends.RelacionaPanCuentaBackEnd;
import mx.babel.bansefi.banksystem.cuentas.beans.CampoDocumentoBean;
import mx.babel.bansefi.banksystem.cuentas.beans.DatoAdicionalBean;
import mx.babel.bansefi.banksystem.cuentas.beans.EmisionDocumentosBean;
import mx.babel.bansefi.banksystem.cuentas.beans.TarifaBean;
import mx.babel.bansefi.banksystem.cuentas.beans.TarifaRelacionBean;
import mx.babel.bansefi.banksystem.cuentas.tasks.RelacionTarifasLoaderTask;
import mx.babel.bansefi.banksystem.cuentas.utils.AltaIPFUtils;
import mx.babel.bansefi.banksystem.cuentas.utils.CamposGenericosDocumentoUtils;
import mx.babel.bansefi.banksystem.cuentas.utils.GeneradorDocumentosPdf;
import mx.babel.bansefi.banksystem.cuentas.utils.TarifasUtils;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Controlador encargado de gestionar la vistas de relaciones de cuentas y
 * emisiòn de documentos.
 *
 * @author mario.montesdeoca
 *
 */
@ManagedBean(name = "relacionesCuentaController")
@Component
@Scope("view")
public class RelacionesCuentaController extends StorageMgrBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String TIPO_TARJETA = "DEBITO";

	@Autowired
	ContextoUtils contextoUtils;
	@Autowired
	EstadosCuentaEnumUtils estadosCuentaEnumUtils;
	@Autowired
	ManagedBeanStateRecover managedBeanStateRecover;

	@Autowired
	GeneradorDocumentosPdf generadorDocumentosPdf;
	@Autowired
	WordUtils wordUtils;
	@Autowired
	CamposGenericosDocumentoUtils camposGenericosDocumentoUtils;
	@Autowired
	CatalogoUtils catalogoUtils;
	@Autowired
	BeanBackUpUtils backUpUtils;
	@Autowired
	RelacionTarifasLoaderTask relacionTarifasLoaderTask;
	@Autowired
	CatalogoGruposAcuerdosInstrumentalesLoaderTask catalogoGrupos;


	@Autowired
	ConsultaRelacionesTarifaBackEnd consultaRelacionesObligatoriasBackEnd;
	@Autowired
	BusquedaIdExternaBackEnd busquedaIdExternaBackEnd;
	@Autowired
	AltaRelacionPersonaCuentaBackEnd altaRelacionPersonaCuentaBackEnd;
	@Autowired
	AltaRelacionCuentaCuentaBackEnd altaRelacionCuentaCuentaBackEnd;
	@Autowired
	ConsultaRelacionPersonaCuentaBackEnd consultaRelacionPersonaCuentaBackEnd;
	@Autowired
	ConsultaCuentasClienteBackEnd consultaCuentasClienteBackEnd;
	@Autowired
	ConsultaDocumentosAEmitirBackEnd consultaDocumentosAEmitirBackEnd;
	@Autowired
	ConsultaCamposDocumentoBackEnd consultaCamposDocumentoBackEnd;
	@Autowired
	ConsultaEstadosDocumentoBackEnd consultaEstadosDocumentoBackEnd;
	@Autowired
	EmisionDocumentosBackEnd emisionDocumentosBackEnd;
	@Autowired
	ModificaCampoDocumentoBackEnd modificaCampoDocumentoBackEnd;
	@Autowired
	FormalizaDocumentoBackEnd formalizaDocumentoBackEnd;
	@Autowired
	AnulaDocumentoBackEnd anulaDocumentoBackEnd;
	@Autowired
	ConsultaPersonaFisicaBackEnd consultaPersonaFisicaBackEnd;
	@Autowired
	ConsultaRelacionesCuentaCuentasBackEnd consultaRelacionesCuentaCuentasBackEnd;
	@Autowired
	ModificaRelacionCuentaPersonaBackEnd modificaRelacionCuentaPersonaBackEnd;
	@Autowired
	BajaRelacionPersonaCuentaBackEnd bajaRelacionPersonaCuentaBackEnd;
	@Autowired
	ModificaRelacionCuentaCuentaBackEnd modificaRelacionCuentaCuentaBackEnd;
	@Autowired
	BajaRelacionCuentaCuentaBackEnd bajaRelacionCuentaCuentaBackEnd;
	@Autowired
	ConsultaDocumentosBackEnd consultaDocumentosBackEnd;

	@Autowired
	RelacionaPanCuentaBackEnd relacionaPanCuentaBackEnd;
	@Autowired
	ConsultaDatosAdicionalesBackEnd consultaDatosAdicionalesBackEnd;

	@Autowired
	AprobarCuentaBackEnd aprobarCuentaBackEnd;
	@Autowired
	ConstituirCuentaBackEnd constituirCuentaBackEnd;

	@Autowired
	ConsultaCuentaBackend consultaCuentaBackend;

    @Autowired
    AltaIPFUtils altaIPFUtils;

    @Autowired
	ConsultaMinimaPersonaBackEnd consultaMinimaPersonaBackEnd;
    //TRN que cambia el nivel de la persona al contratar una cuenta de nivel 4
    @Autowired
    ConsultaNiveladoraCuentaBackend consultaNiveladoraCuentaBackend;

	/**
	 * Cliente titular de la cuenta que se está dando de alta
	 */
	private ClienteBean clienteBean;
	/**
	 * Cuenta a la cual se le asignarán nuevas relaciones
	 */
	private CuentaBean cuentaBean;
	/**
	 * Bean de Tarifa asignado a la cuenta
	 */
	private TarifaBean tarifaSeleccionada;
	/**
	 * Cliente que recibira la persona relacionada desde la búsqueda
	 */
	private RelacionadoBean personaBuscada;
	/**
	 * Cuenta que recibira la cuenta relacionada desde la búsqueda
	 */
	private CuentaRelacionadaBean cuentaBuscada;
	/**
	 * Tipo de relación de la cuenta con personas a adicionarse.
	 */
	private String tipoRelacion;
	/**
	 * Tipo de relación de la cuenta con cuentas a adicionarse.
	 */
	private TarifaRelacionBean tipoRelacionCuenta;
	/**
	 * Tipo de alta de sat
	 */
	private TipoAltaEnum tipoAltaPan;
	/**
	 * Lista de relaciones de persona-cuenta obligatorias para la tarifa.
	 */
	private Map<String, Boolean> relacionesTarifa;
	/**
	 * Lista de cuentas que cumplen con los requisitos para relacionarse con la
	 * cuenta.
	 */
	private List<CuentaClienteBean> cuentasRelacionables;
	/**
	 * Lista de relaciones de cuenta que se pueden adicionar a la tarifa.
	 */
	private List<TarifaRelacionBean> relacionesCuentasTarifa;
	/**
	 * Lista de codigos de relación persona-cuenta especificos para la tarifa.
	 */
	private List<CatalogoBean> codigosRelaciones;
	/**
	 * Lista de codigos de relación persona-cuenta especificos para la tarifa.
	 */
	private List<CatalogoBean> razonesDeCambio;
	/**
	 * Lista de codigos de relación persona-cuenta especificos para la tarifa.
	 */
	private List<CatalogoBean> catalogoBin;
	/**
	 * Lista de documentos a emitir
	 */
	private List<EmisionDocumentosBean> documentosAEmitir;
	/**
	 * Boolean para verificar si estamos en flujo de alta o de modificación.
	 */
	private Boolean isAlta = true;

	private Integer estadoAlta;

	private Boolean busqueda = false;

	private Boolean mostrarCancelar = false;

	private Boolean faltantes = false;

	private Boolean sinBines = false;
	
	private Boolean sinCuentaOperativa = false;
	
	private Boolean centrosDiferentes = false;

	private NavegacionEnum cancelarAction;

	public static final String COSAS = "relaciones";

	public static final String COSA = "relación";

	private DialogoRelacionesCuentaListadoUtils dialogoUtils;

	private DialogoListadoBean dialogoGuardado;

	private DialogoListadoBean mensajeEliminados;

	private DialogoListadoBean mensajeBusqueda;

	private String mensajePersonaFaltantes;

	private String mensajeCuentasFaltantes;

	private String messageError;

	private String mensajeObligatorias;

	/**
	 * Enum para definir a donde se redireccionará al seleccionar un objeto de
	 * la tabla de resultados.
	 */
	private NavegacionEnum destino;
	private Object destinoController;

	/**
   	 * Variable para saber si el alta es un subflujo
   	 */
   	private Boolean subflujo = false;


   	/**
   	 * Variable para identificar un alta de medios de pago
   	 */
   	private Boolean altaSat = false;

   	private Boolean sinBeneficiarios = false;

    /**
     * Variable que indica si la cuenta y el cliente deben nivelarse.
     * Lo cual ocurre si la cuenta es nivel 2 y el cliente tiene documentacion
     */
    private Boolean nivelarCuenta;

	/**
	 * Clase para incializar bean de CuentaBean, ClienteBean e historico.
	 */
	@PostConstruct
	public void init() {
		super.restoreflash();
		initDialogs();
		if(this.obtieneFlash().get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash())!=null){
			if ((Boolean) this.obtieneFlash().get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash())) {
				destino = managedBeanStateRecover.getDestino();
				destinoController = managedBeanStateRecover.getController();
				initializeData();
			}else{
				managedBeanStateRecover.recuperaController(this);
				if(getPersonaBuscada() != null){
					busqueda = true;
					if (this.obtieneFlash().get(ParametrosFlashEnum.ID_INTERNA.getParamFlash()) != null) {
						ClientePersonaFisicaBean persona = (ClientePersonaFisicaBean) consultaPersonaFisicaBackEnd
								.ejecutarTRN((Integer) this.obtieneFlash().get(ParametrosFlashEnum.ID_INTERNA.getParamFlash()));
						if(persona != null){
							getPersonaBuscada().setPersona(persona);
						}else{
							getPersonaBuscada().getPersona().setIdInterna(
									(Integer)this.obtieneFlash().get(ParametrosFlashEnum.ID_INTERNA.getParamFlash()));
							getPersonaBuscada().getPersona().setNumIdentificacion(
									(String)this.obtieneFlash().get(ParametrosFlashEnum.IDENTIFICACION.getParamFlash()));
							getPersonaBuscada().getPersona().setNombre(
									(String)this.obtieneFlash().get(ParametrosFlashEnum.NOMBRE_COMPLETO_PERSONA.getParamFlash()));
						}
					}
					if (this.obtieneFlash().get(ParametrosFlashEnum.CLIENTE.getParamFlash()) != null) {
						getPersonaBuscada().setPersona((ClientePersonaFisicaBean)
								this.obtieneFlash().get(ParametrosFlashEnum.CLIENTE.getParamFlash()));
					}
				}
				if(getCuentaBuscada() != null){
					busqueda = true;
					if (obtieneFlash().get(ParametrosFlashEnum.CUENTA_SUBFLUJO_ALTA.getParamFlash()) != null) {
						final CuentaBean cuenta  = (CuentaBean) obtieneFlash().get(ParametrosFlashEnum.CUENTA_SUBFLUJO_ALTA.getParamFlash());
						if(ConstantesFuncionales.ESTADO_CUENTA_ACTIVO.equals(cuenta.getEstado())){
							final CuentaClienteBean cuentaARelacionar = new CuentaClienteBean();
							cuentaARelacionar.setCuenta(cuenta);
							getCuentaBuscada().getCuentasRelacionables().add(cuentaARelacionar);
							getCuentaBuscada().setCuenta(cuenta);
						}
					}
				}
			}
		}else{
			initializeData();
		}
	}
	
	private void initDialogs(){
		dialogoGuardado = new DialogoListadoBean();
		mensajeEliminados = new DialogoListadoBean();
		mensajeBusqueda = new DialogoListadoBean();
	}

	public void initializeData(){
		if(obtieneFlash().get(ParametrosFlashEnum.SUBFLUJO_ALTA_CUENTA.getParamFlash()) != null){
			this.subflujo = (Boolean) obtieneFlash().get(ParametrosFlashEnum.SUBFLUJO_ALTA_CUENTA.getParamFlash());
		}
		if (this.obtieneFlash().get(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash()) != null) {
			this.cuentaBean = (CuentaBean) this.obtieneFlash().get(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash());
		}
		if(this.cuentaBean == null){
			this.cuentaBean = new CuentaBean();
		}
		if (this.obtieneFlash().get(ParametrosFlashEnum.CLIENTE.getParamFlash()) != null) {
			this.clienteBean = (ClienteBean) this.obtieneFlash().get(ParametrosFlashEnum.CLIENTE.getParamFlash());
		} else {
			if(this.cuentaBean != null &&!this.cuentaBean.getPersonasRelacionadas().isEmpty()){
				this.clienteBean = encontrarTitular();
			}
		}
		if (this.obtieneFlash().get(ParametrosFlashEnum.TARIFA_SELECCIONADA.getParamFlash()) != null) {
			this.tarifaSeleccionada = (TarifaBean) this.obtieneFlash().get(ParametrosFlashEnum.TARIFA_SELECCIONADA.getParamFlash());
			this.altaSat = ConstantesFuncionales.isMediosPago(this.tarifaSeleccionada.getLinea(), this.tarifaSeleccionada.getGrupo());
			this.sinBeneficiarios = ConstantesFuncionales.cuentaSinBeneficiarios(
						this.tarifaSeleccionada.getLinea(), this.tarifaSeleccionada.getGrupo());
		}
		if(this.obtieneFlash().get(ParametrosFlashEnum.MODIFICACION_CUENTA.getParamFlash()) != null){
			setIsAlta(!(Boolean)this.obtieneFlash().get(ParametrosFlashEnum.MODIFICACION_CUENTA.getParamFlash()));
			this.altaSat = ConstantesFuncionales.isMediosPago(this.cuentaBean.getCodLinea(), this.cuentaBean.getIdGrupoProducto());
			this.sinBeneficiarios = ConstantesFuncionales.cuentaSinBeneficiarios(
					this.cuentaBean.getCodLinea(), this.cuentaBean.getIdGrupoProducto());
		}
		if(this.obtieneFlash().get(ParametrosFlashEnum.NAVEGACION_ALTA_CUENTA.getParamFlash()) != null){
        	estadoAlta = (Integer) this.obtieneFlash().get(ParametrosFlashEnum.NAVEGACION_ALTA_CUENTA.getParamFlash());
        }
        if(this.obtieneFlash().get(ParametrosFlashEnum.NIVELAR_CUENTA.getParamFlash()) != null){
            this.nivelarCuenta = (Boolean)obtieneFlash().get(ParametrosFlashEnum.NIVELAR_CUENTA.getParamFlash());
        }
	}

	/**
	 * @return Atributo contextoUtils
	 */
	public ContextoUtils getContextoUtils() {
		return contextoUtils;
	}

	/**
	 * @param contextoUtils Atributo contextoUtils a definir
	 */
	public void setContextoUtils(final ContextoUtils contextoUtils) {
		this.contextoUtils = contextoUtils;
	}

	/**
	 * @return Atributo dialogoUtils
	 */
	public DialogoRelacionesCuentaListadoUtils getDialogoUtils() {
		return dialogoUtils;
	}

	/**
	 * @param dialogoUtils Atributo dialogoUtils a definir
	 */
	public void setDialogoUtils(final DialogoRelacionesCuentaListadoUtils dialogoUtils) {
		this.dialogoUtils = dialogoUtils;
	}

	/**
	 * @return Atributo clienteBean
	 */
	public ClienteBean getClienteBean() {
		return clienteBean;
	}

	/**
	 * @param clienteBean
	 *            Atributo clienteBean a definir
	 */
	public void setClienteBean(final ClienteBean clienteBean) {
		this.clienteBean = clienteBean;
	}

	/**
	 * @return Atributo cuentaBean
	 */
	public CuentaBean getCuentaBean() {
		return cuentaBean;
	}

	/**
	 * @param cuentaBean
	 *            Atributo cuentaBean a definir
	 */
	public void setCuentaBean(final CuentaBean cuentaBean) {
		this.cuentaBean = cuentaBean;
	}

	/**
	 * @return Atributo tarifaSeleccionada
	 */
	public TarifaBean getTarifaSeleccionada() {
		return tarifaSeleccionada;
	}

	/**
	 * @param tarifaSeleccionada
	 *            Atributo tarifaSeleccionada a definir
	 */
	public void setTarifaSeleccionada(final TarifaBean tarifaSeleccionada) {
		this.tarifaSeleccionada = tarifaSeleccionada;
	}

	/**
	 * @return Atributo personaBuscada
	 */
	public RelacionadoBean getPersonaBuscada() {
		return personaBuscada;
	}

	/**
	 * @param personaBuscada
	 *            Atributo personaBuscada a definir
	 */
	public void setPersonaBuscada(final RelacionadoBean personaBuscada) {
		this.personaBuscada = personaBuscada;
	}

	/**
	 * @return Atributo cuentaBuscada
	 */
	public CuentaRelacionadaBean getCuentaBuscada() {
		return cuentaBuscada;
	}

	/**
	 * @param cuentaBuscada Atributo cuentaBuscada a definir
	 */
	public void setCuentaBuscada(final CuentaRelacionadaBean cuentaBuscada) {
		this.cuentaBuscada = cuentaBuscada;
	}

	/**
	 * @return Atributo tipoRelacion
	 */
	public String getTipoRelacion() {
		return tipoRelacion;
	}

	/**
	 * @param tipoRelacion
	 *            Atributo tipoRelacion a definir
	 */
	public void setTipoRelacion(final String tipoRelacion) {
		this.tipoRelacion = tipoRelacion;
	}

	/**
	 * @return Atributo tipoRelacionCuenta
	 */
	public TarifaRelacionBean getTipoRelacionCuenta() {
		return tipoRelacionCuenta;
	}

	/**
	 * @param tipoRelacionCuenta
	 *            Atributo tipoRelacionCuenta a definir
	 */
	public void setTipoRelacionCuenta(
			final TarifaRelacionBean tipoRelacionCuenta) {
       this.tipoRelacionCuenta = tipoRelacionCuenta;
	}

	/**
	 * @return Atributo tipoAltaPan
	 */
	public TipoAltaEnum getTipoAltaPan() {
		return tipoAltaPan;
	}

	/**
	 * @param tipoAltaPan Atributo tipoAltaPan a definir
	 */
	public void setTipoAltaPan(final TipoAltaEnum tipoAltaPan) {
		this.tipoAltaPan = tipoAltaPan;
	}

	/**
	 * @return Atributo relacionesPersonas
	 */
	public List<RelacionadoBean> getRelacionesPersonas() {
		if(this.cuentaBean != null){
			Collections.sort(this.cuentaBean.getPersonasRelacionadas(), new RelacionadoBeanTipoComparator());
			return this.cuentaBean.getPersonasRelacionadas();
		}else{
			return new ArrayList<RelacionadoBean>();
		}
	}

	/**
	 * @return Atributo relacionesPersonas
	 */
	public List<CuentaRelacionadaBean> getRelacionesCuentas() {
		if(this.cuentaBean != null){
			Collections.sort(this.cuentaBean.getCuentasRelacionadas(), new CuentaRelacionadaBeanTipoComparator());
			return this.cuentaBean.getCuentasRelacionadas();
		}else{
			return new ArrayList<CuentaRelacionadaBean>();
		}
	}

	/**
	 * @return Atributo cuentasARelacionar
	 */
	public List<CuentaClienteBean> getCuentasRelacionables() {
		if (cuentasRelacionables == null) {
			cuentasRelacionables = new ArrayList<CuentaClienteBean>();
		}
		return cuentasRelacionables;
	}

	/**
	 * @param "cuentasARelacionar"
	 *            Atributo cuentasARelacionar a definir
	 */
	public void setCuentasRelacionables(
			final List<CuentaClienteBean> cuentasRelacionables) {
		this.cuentasRelacionables = cuentasRelacionables;
	}

	/**
	 * @return Atributo relacionesCuentasTarifa
	 */
	public List<TarifaRelacionBean> getRelacionesCuentasTarifa() {
		if (relacionesCuentasTarifa == null) {
			relacionesCuentasTarifa = new ArrayList<TarifaRelacionBean>();
		}
		return relacionesCuentasTarifa;
	}

	/**
	 * @param relacionesCuentasTarifa
	 *            Atributo relacionesCuentasTarifa a definir
	 */
	public void setRelacionesCuentasTarifa(
			final List<TarifaRelacionBean> relacionesCuentasTarifa) {
		this.relacionesCuentasTarifa = relacionesCuentasTarifa;
	}

	/**
	 * @return Atributo relacionesObligatorias
	 */
	public Map<String, Boolean> getRelacionesTarifa() {
		if (relacionesTarifa == null) {
			relacionesTarifa = new HashMap<String, Boolean>();
		}
		return relacionesTarifa;
	}

	/**
	 * @param "relacionesObligatorias"
	 *            Atributo relacionesObligatorias a definir
	 */
	public void setRelacionesTarifa(final Map<String, Boolean> relacionesTarifa) {
		this.relacionesTarifa = relacionesTarifa;
	}

	/**
	 * @return Atributo isAlta
	 */
	public Boolean getIsAlta() {
		return isAlta;
	}

	/**
	 * @param isAlta
	 *            Atributo isAlta a definir
	 */
	public void setIsAlta(final Boolean isAlta) {
		this.isAlta = isAlta;
	}

	/**
	 * @return Atributo estadoAlta
	 */
	public Integer getEstadoAlta() {
		return estadoAlta;
	}

	/**
	 * @param estadoAlta Atributo estadoAlta a definir
	 */
	public void setEstadoAlta(final Integer estadoAlta) {
		this.estadoAlta = estadoAlta;
	}

	/**
	 * @return Atributo mostrarCancelar
	 */
	public Boolean getMostrarCancelar() {
		return mostrarCancelar;
	}

	/**
	 * @param mostrarCancelar Atributo mostrarCancelar a definir
	 */
	public void setMostrarCancelar(final Boolean mostrarCancelar) {
		this.mostrarCancelar = mostrarCancelar;
	}

	/**
	 * @return Atributo faltantes
	 */
	public Boolean getFaltantes() {
		return faltantes;
	}

	/**
	 * @param faltantes Atributo faltantes a definir
	 */
	public void setFaltantes(final Boolean faltantes) {
		this.faltantes = faltantes;
	}

	/**
	 * @return Atributo sinBines
	 */
	public Boolean getSinBines() {
		return sinBines;
	}

	/**
	 * @param sinBines Atributo sinBines a definir
	 */
	public void setSinBines(final Boolean sinBines) {
		this.sinBines = sinBines;
	}

	/**
	 * @return Atributo sinCuentaOperativa
	 */
	public Boolean getSinCuentaOperativa() {
		return sinCuentaOperativa;
	}

	/**
	 * @param sinCuentaOperativa Atributo sinCuentaOperativa a definir
	 */
	public void setSinCuentaOperativa(Boolean sinCuentaOperativa) {
		this.sinCuentaOperativa = sinCuentaOperativa;
	}

	/**
	 * @return Atributo centrosDiferentes
	 */
	public Boolean getCentrosDiferentes() {
		return centrosDiferentes;
	}

	/**
	 * @param centrosDiferentes Atributo centrosDiferentes a definir
	 */
	public void setCentrosDiferentes(Boolean centrosDiferentes) {
		this.centrosDiferentes = centrosDiferentes;
	}

	/**
	 * @return Atributo cancelarAction
	 */
	public NavegacionEnum getCancelarAction() {
		return cancelarAction;
	}

	/**
	 * @param cancelarAction Atributo cancelarAction a definir
	 */
	public void setCancelarAction(final NavegacionEnum cancelarAction) {
		this.cancelarAction = cancelarAction;
	}

	/**
	 * @return Atributo codigosRelaciones
	 */
	public List<CatalogoBean> getCodigosRelaciones() {
		if(codigosRelaciones == null){
			codigosRelaciones = new ArrayList<CatalogoBean>();
		}
		return codigosRelaciones;
	}

	/**
	 * @param codigosRelaciones
	 *            Atributo codigosRelaciones a definir
	 */
	public void setCodigosRelaciones(final List<CatalogoBean> codigosRelaciones) {
		this.codigosRelaciones = codigosRelaciones;
	}

	/**
	 * @return Atributo razonesDeCambio
	 */
	public List<CatalogoBean> getRazonesDeCambio() {
		return razonesDeCambio;
	}

	/**
	 * @param razonesDeCambio
	 *            Atributo razonesDeCambio a definir
	 */
	public void setRazonesDeCambio(final List<CatalogoBean> razonesDeCambio) {
		this.razonesDeCambio = razonesDeCambio;
	}

	/**
	 * @return Atributo catalogoBin
	 */
	public List<CatalogoBean> getCatalogoBin() {
		return catalogoBin;
	}

	/**
	 * @param catalogoBin Atributo catalogoBin a definir
	 */
	public void setCatalogoBin(final List<CatalogoBean> catalogoBin) {
		this.catalogoBin = catalogoBin;
	}

	/**
	 * @return Atributo documentosAEmitir
	 */
	public List<EmisionDocumentosBean> getDocumentosAEmitir() {
		return documentosAEmitir;
	}

	/**
	 * @param documentosAEmitir
	 *            Atributo documentosAEmitir a definir
	 */
	public void setDocumentosAEmitir(
			final List<EmisionDocumentosBean> documentosAEmitir) {
		this.documentosAEmitir = documentosAEmitir;
	}

	/**
	 * @return Atributo mensajeGuardado
	 */
	public DialogoListadoBean getDialogoGuardado() {
		if(dialogoGuardado == null){
			dialogoGuardado = new DialogoListadoBean();
			dialogoGuardado.setEstado(DialogoRelacionesCuentaListadoEnum.SIN_CAMBIOS);
		}
		return dialogoGuardado;
	}

	/**
	 * @param "mensajeGuardado" Atributo mensajeGuardado a definir
	 */
	public void setDialogoGuardado(final DialogoListadoBean dialogoGuardado) {
		this.dialogoGuardado = dialogoGuardado;
	}

	/**
	 * @return Atributo mensajeEliminados
	 */
	public DialogoListadoBean getMensajeEliminados() {
		if(mensajeEliminados == null){
			mensajeEliminados = new DialogoListadoBean();
			mensajeEliminados.setEstado(DialogoRelacionesCuentaListadoEnum.ELIMINAR);
		}
		return mensajeEliminados;
	}

	/**
	 * @param mensajeEliminados Atributo mensajeEliminados a definir
	 */
	public void setMensajeEliminados(final DialogoListadoBean mensajeEliminados) {
		this.mensajeEliminados = mensajeEliminados;
	}

	/**
	 * @return Atributo mensajeBusqueda
	 */
	public DialogoListadoBean getMensajeBusqueda() {
		if(mensajeBusqueda == null){
			mensajeBusqueda = new DialogoListadoBean();
			mensajeBusqueda.setEstado(DialogoRelacionesCuentaListadoEnum.SIN_CAMBIOS);
		}
		return mensajeBusqueda;
	}

	/**
	 * @param mensajeBusqueda Atributo mensajeBusqueda a definir
	 */
	public void setMensajeBusqueda(final DialogoListadoBean mensajeBusqueda) {
		this.mensajeBusqueda = mensajeBusqueda;
	}

	/**
	 * @return Atributo mensajePersonaFaltantes
	 */
	public String getMensajePersonaFaltantes() {
		return mensajePersonaFaltantes;
	}

	/**
	 * @param mensajePersonaFaltantes Atributo mensajePersonaFaltantes a definir
	 */
	public void setMensajePersonaFaltantes(final String mensajePersonaFaltantes) {
		this.mensajePersonaFaltantes = mensajePersonaFaltantes;
	}

	/**
	 * @return Atributo mensajeCuentasFaltantes
	 */
	public String getMensajeCuentasFaltantes() {
		return mensajeCuentasFaltantes;
	}

	/**
	 * @param mensajeCuentasFaltantes Atributo mensajeCuentasFaltantes a definir
	 */
	public void setMensajeCuentasFaltantes(final String mensajeCuentasFaltantes) {
		this.mensajeCuentasFaltantes = mensajeCuentasFaltantes;
	}

	/**
	 * @return Atributo altaSat
	 */
	public Boolean getAltaSat() {
		return altaSat;
	}

	/**
	 * @param altaSat Atributo altaSat a definir
	 */
	public void setAltaSat(final Boolean altaSat) {
		this.altaSat = altaSat;
	}

	/**
	 * @return Atributo messageError
	 */
	public String getMessageError() {
		return messageError;
	}

	public void concatMessageError(final String mensaje) {
		if(messageError == null){
			messageError = "";
		}
		messageError = messageError + "<span>"+mensaje + "</span><br/>";
	}

	/**
	 * @param messageError Atributo messageError a definir
	 */
	public void setMessageError(final String messageError) {
		this.messageError = messageError;
	}

	/**
	 * @return Atributo mensajeObligatorias
	 */
	public String getMensajeObligatorias() {
		return mensajeObligatorias;
	}

	public String initMensajeobligatorias(){
		mensajeObligatorias = "Para esta tarifa es necesario dar de alta";
		return mensajeObligatorias;
	}

	/**
	 * @param mensajeObligatorias Atributo mensajeObligatorias a definir
	 */
	public void setMensajeObligatorias(final String mensajeObligatorias) {
		this.mensajeObligatorias = mensajeObligatorias;
	}

	/**
	 * @return Atributo destino
	 */
	public NavegacionEnum getDestino() {
		return destino;
	}

	/**
	 * @param destino Atributo destino a definir
	 */
	public void setDestino(final NavegacionEnum destino) {
		this.destino = destino;
	}

	/**
	 * @return Atributo destinoController
	 */
	public Object getDestinoController() {
		return destinoController;
	}

	/**
	 * @param destinoController Atributo destinoController a definir
	 */
	public void setDestinoController(final Object destinoController) {
		this.destinoController = destinoController;
	}

	public List<PanBean> obtenerPanesRelacionados(){
		if(this.cuentaBean.getPanesRelacionados() == null){
			this.cuentaBean.setPanesRelacionados(new ArrayList<PanBean>());
		}
		return this.cuentaBean.getPanesRelacionados();
	}
	
	public List<PanBean> obtenerPanesCombo(){
		List<PanBean> panesCombo = new ArrayList<PanBean>();
		if(this.cuentaBean.getPanesRelacionados() != null){
			for (PanBean panBean : this.cuentaBean.getPanesRelacionados()) {
				if(EstadoListadosEnum.CONSULTADO.equals(panBean.getEstadoListado())){
					panesCombo.add(panBean);
				}
			}
		}
		return panesCombo;
	}

	private void resetDialogs(){
		getDialogoGuardado().setMostrar(false);
		getMensajeEliminados().setMostrar(false);
		getMensajeBusqueda().setMostrar(false);
	}

	private ClienteBean encontrarTitular(){
		for (final RelacionadoBean relacionado : this.cuentaBean.getPersonasRelacionadas()) {
			if(isTitular(relacionado)){
				if(relacionado.getNumero() == 1){
					final ClienteBean titular = relacionado.getPersona();
					titular.setTipoClienteEnum(TipoCliente.codPeDe(relacionado.getCodPe()));
					return titular;
				}
			}
		}
		return null;
	}

	private void preparaFlash(){
		this.obtieneFlash().put(ParametrosFlashEnum.NAVEGACION_ALTA_CUENTA.getParamFlash(),
				this.estadoAlta);
		this.obtieneFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				this.cuentaBean);
		this.obtieneFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(),
				this.clienteBean);
		this.obtieneFlash().put(ParametrosFlashEnum.TARIFA_SELECCIONADA.getParamFlash(),
				this.tarifaSeleccionada);
		this.obtieneFlash().put(ParametrosFlashEnum.NIVELAR_CUENTA.getParamFlash(),
		        this.nivelarCuenta);
	}

	/**
	 * Método para obtener la ruta a la vista de relaciones de cuenta con
	 * personas, enviando el bean de cuenta.
	 *
	 * @return ruta de la vista de relaciones de cuenta-persona.
	 */
	@StoreStep
	public String irNegociarTarifa() {
		if(this.dialogoUtils != null && this.dialogoUtils.tieneCambios()){
			setMostrarCancelar(true);
			cancelarAction = NavegacionEnum.ALTA_CUENTA3;
			return "";
		}else{
			if(this.dialogoUtils == null || this.dialogoUtils.listadoCorrecto(validaBeneficiarios())){
				preparaFlash();
				parametrosSubFlujo();
				return NavegacionEnum.ALTA_CUENTA3.getRuta();
			}
		}
		return "";
	}

	/**
	 * Método para obtener la ruta a la vista de relaciones de cuenta con
	 * personas, enviando el bean de cuenta.
	 *
	 * @return ruta de la vista de relaciones de cuenta-persona.
	 */
	@StoreStep
	public String irRelacionPersonas() {
		if(this.dialogoUtils != null && this.dialogoUtils.tieneCambios()){
			setMostrarCancelar(true);
			this.cancelarAction = NavegacionEnum.RELACIONES_CUENTA_PERSONAS;
			return "";
		}else{
			if(this.dialogoUtils == null || this.dialogoUtils.listadoCorrecto(validaBeneficiarios())){
				preparaFlash();
				parametrosSubFlujo();
				return NavegacionEnum.RELACIONES_CUENTA_PERSONAS.getRuta();
			}
		}
		return "";
	}

	/**
	 * Método para obtener la ruta a la vista de relaciones de cuenta con
	 * cuentas, enviando el bean de cuenta.
	 *
	 * @return ruta de la vista de relaciones de cuenta-cuentas.
	 */
	@StoreStep
	public String irRelacionCuentas() {
		if(this.dialogoUtils != null && this.dialogoUtils.tieneCambios()){
			if(!altaSat && personasFaltantes()){
				setFaltantes(true);
				return "";
			}
			setMostrarCancelar(true);
			this.cancelarAction = NavegacionEnum.RELACIONES_CUENTA_CUENTAS;
			return "";
		}else{
			if(this.dialogoUtils == null || this.dialogoUtils.listadoCorrecto(validaBeneficiarios())){
				preparaFlash();
				parametrosSubFlujo();
				return NavegacionEnum.RELACIONES_CUENTA_CUENTAS.getRuta();
			}
		}
		return "";
	}

	/**
	 * Método para obtener la ruta a la vista de emisión de documentos, enviando
	 * el bean de cuenta.
	 *
	 * @return ruta de la vista de emisión de documentos.
	 */
	@StoreStep
	public String irEmisionDocumentos() {
		if(this.dialogoUtils != null && this.dialogoUtils.tieneCambios()){
			if(cuentasFaltantes()){
				setFaltantes(true);
				return "";
			}
			setMostrarCancelar(true);
			this.cancelarAction = NavegacionEnum.EMITIR_DOCUMENTOS;
			return "";
		}else{
			if(this.dialogoUtils == null || this.dialogoUtils.listadoCorrecto(validaBeneficiarios())){
				preparaFlash();
				parametrosSubFlujo();
				return NavegacionEnum.EMITIR_DOCUMENTOS.getRuta();
			}
		}
		return "";
	}

	/**
	 * Método para obtener la ruta a la vista de relación pan, enviando
	 * el bean de cuenta.
	 *
	 * @return ruta de la vista de emisión de documentos.
	 */
	@StoreStep
	public String irRelacionPan(){
		CuentaBean cuenta = null;
		try{
			cuenta = consultaCuentaBackend.ejecutarTRN(this.cuentaBean.getNumeroCuenta());
		}catch(ControlableException | NoControlableException e){
		}
		if(cuenta != null){
			this.cuentaBean.setEstado(cuenta.getEstado());
		}
		if(this.dialogoUtils != null && this.dialogoUtils.tieneCambios()){
			if(cuentasFaltantes()){
				setFaltantes(true);
				return "";
			} 
			setMostrarCancelar(true);
			this.cancelarAction = NavegacionEnum.RELACIONES_CUENTA_PAN;
			return "";
		}else{
			if(this.dialogoUtils == null || this.dialogoUtils.listadoCorrecto(validaBeneficiarios())){
				preparaFlash();
				parametrosSubFlujo();
				return NavegacionEnum.RELACIONES_CUENTA_PAN.getRuta();
			}
		}
		return "";
	}

	/**
	 * Método para obtener la ruta a la vista de històrico de relacion,
	 * enviando el bean del relacionado.
	 *
	 * @param "documento"
	 *            al cual se consultarà su historico
	 * @return ruta de la vista de històrico de documentos.
	 */
	public String getHistorico(final RelacionadoBean relacionado) {
		preparaFlash();
		this.obtieneFlash().put(ParametrosFlashEnum.HISTORICO_PERSONA_RELACIONADA.getParamFlash(),
				relacionado);
		managedBeanStateRecover.enviaController(this,NavegacionEnum.RELACIONES_CUENTA_PERSONAS);
		return NavegacionEnum.HISTORICO_RELACION_CUENTA_PERSONA.getRuta();
	}

	/**
	 * Método para obtener la ruta a la vista de històrico de relacion,
	 * enviando el bean del cuenta relacionada.
	 *
	 * @param "documento"
	 *            al cual se consultarà su historico
	 * @return ruta de la vista de històrico de documentos.
	 */
	public String getHistorico(final CuentaRelacionadaBean relacionada) {
		preparaFlash();
		this.obtieneFlash().put(ParametrosFlashEnum.HISTORICO_CUENTA_RELACIONADA.getParamFlash(),
				relacionada);
		managedBeanStateRecover.enviaController(this,NavegacionEnum.RELACIONES_CUENTA_CUENTAS);
		return NavegacionEnum.HISTORICO_RELACION_CUENTA_CUENTA.getRuta();
	}

	/**
	 * Método para obtener la ruta a la vista de històrico de documentos,
	 * enviando el bean del documento.
	 *
	 * @param documento
	 *            al cual se consultarà su historico
	 * @return ruta de la vista de històrico de documentos.
	 */
	public String getHistorico(final EmisionDocumentosBean documento) {
		preparaFlash();
		this.obtieneFlash().put(ParametrosFlashEnum.HISTORICO_DOCUMENTO.getParamFlash(),
				documento);
		managedBeanStateRecover.enviaController(this,NavegacionEnum.EMITIR_DOCUMENTOS);
		return NavegacionEnum.HISTORICO_DOCUMENTOS.getRuta();
	}

	/**
	 * Método para verificar si existen cambios no transaccionados en la vista
	 *
	 * @return la ruta a donde redirigir
	 */
	@StoreStep
	public String cancelar(){
		this.cancelarAction = null;
		if(this.dialogoUtils != null && this.dialogoUtils.tieneCambios()){
			setMostrarCancelar(true);
			return "";
		}else{
			if(this.dialogoUtils == null || this.dialogoUtils.listadoCorrecto(validaBeneficiarios())){
				return irAHome();
			}
		}
		return "";
	}

	@StoreStep
	public String cancelarAlta(){
		if(this.dialogoUtils != null && !this.dialogoUtils.listadoCorrecto(validaBeneficiarios())){
			return "";
		}
		this.cancelarAction = null;
		return irAHome();
	}

	// RAV 20160307 ini
		@StoreStep
		public String sicancelarAlta(){
		        int FlagOut = 0;
		        RelacionadoBean persElim = null;
		        for (int itemRel=1;itemRel<999;itemRel++){
				for (final RelacionadoBean persona : getRelacionesPersonas()) 
				    {
						if (EstadoListadosEnum.NUEVO.equals(persona.getEstadoListado()))
								{
								FlagOut ++;
								persElim = persona;
								}
				    }
		        	if (FlagOut != 0)
		        			{
						this.cuentaBean.getPersonasRelacionadas().remove(persElim);
						FlagOut = 0;
		        			}else
		        			{
		        				itemRel=1000;
		        			}
		        }
				return irAHome();
			}
	// RAV 20160307 fin
	
	
	/**
	 * Método para obtener la ruta a la vista de relaciones de cuenta con
	 * documentos, enviando el bean de cuenta.
	 *
	 * @return ruta de la vista de relaciones de cuenta-documentos.
	 */
	@StoreStep
	public String irAHome() {
		String ruta = "";
		if(this.cancelarAction != null){
			preparaFlash();
			parametrosSubFlujo();
            ruta = this.cancelarAction.getRuta();
        }else if (this.destino == null) {
			ruta =NavegacionEnum.INICIO.getRuta();
		}else{
			ruta = destino.getRuta();
			managedBeanStateRecover.finNavegacion(destinoController);
		}
		return ruta;
	}

	/**
	 * Método para obtener la ruta a la vista de búsqueda de clientes.
	 *
	 * @param bean
	 *            Bean que recibira el resultado de la búsqueda
	 * @return ruta de la vista de búsqueda.
	 */
	@StoreStep
	public String buscarPersona(final RelacionadoBean bean) {
		resetDialogs();
		setPersonaBuscada(bean);
		this.obtieneFlash().put(ParametrosFlashEnum.TIPO_BUSQUEDA.getParamFlash(),BusquedaEnum.PERSONA_CLIENTE.getBusquedaValor());
		managedBeanStateRecover.enviaController(this,NavegacionEnum.RELACIONES_CUENTA_PERSONAS);
		return NavegacionEnum.BUSQUEDA.getRuta();
	}

	/**
	 * Método para obtener la ruta a la vista de alta de clientes.
	 *
	 * @param bean
	 *            Bean que recibira el resultado del alta
	 * @return ruta de la vista de alta de cliente.
	 */
	@StoreStep
	public String altaPersona(final RelacionadoBean bean) {
		resetDialogs();
		setPersonaBuscada(bean);
		this.obtieneFlash().put(ParametrosFlashEnum.SUBFLUJO_ALTA_CLIENTE.getParamFlash(),true);
		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.RELACIONES_CUENTA_PERSONAS);
		return NavegacionEnum.ALTA_CLIENTE1.getRuta();
	}

	/**
	 * Método para obtener la ruta a la vista de búsqueda de cuentas.
	 *
	 * @param bean
	 *            Bean que recibira el resultado de la búsqueda
	 * @return ruta de la vista de búsqueda.
	 */
	@StoreStep
	public String buscarCuenta(final CuentaRelacionadaBean bean) {
		resetDialogs();
		setCuentaBuscada(bean);
		this.obtieneFlash().put(ParametrosFlashEnum.TIPO_BUSQUEDA.getParamFlash(),
				BusquedaEnum.CUENTAS.getBusquedaValor());
		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.RELACIONES_CUENTA_CUENTAS);
		return NavegacionEnum.BUSQUEDA.getRuta();
	}

	/**
	 * Método para obtener la ruta a la vista de alta de cuentas.
	 *
	 * @param bean
	 *            Bean que recibira el resultado del alta
	 * @return ruta de la vista de alta de cuentas.
	 */
	@StoreStep
	public String altaCuenta(final CuentaRelacionadaBean bean) {
		resetDialogs();
		setCuentaBuscada(bean);
		obtieneFlash().put(ParametrosFlashEnum.TARIFA_RELACIONADA.getParamFlash(), getTarifaRelacionBean(bean));
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(), this.clienteBean);
		managedBeanStateRecover.enviaController(this,NavegacionEnum.RELACIONES_CUENTA_CUENTAS);
		this.obtieneFlash().put(ParametrosFlashEnum.SUBFLUJO_ALTA_CUENTA.getParamFlash(),true);
		return NavegacionEnum.ALTA_CUENTA1.getRuta();
	}

	private TarifaRelacionBean getTarifaRelacionBean(final CuentaRelacionadaBean bean){
		for (final TarifaRelacionBean relacion : getRelacionesCuentasTarifa()) {
			if(relacion.getCodigoRelacion().equals(bean.getTipoRelacion())){
				return relacion;
			}
		}
		return null;
	}

	/**
	 * Método para verificar si una relación es de tipo beneficiario.
	 *
	 * @param "bean"
	 *            Bean de la relación a verificar
	 * @return <code>true</code>. si la relación es de tipo beneficiario.
	 */
	public Boolean isBeneficiario(final TipoRelacionPersonaCuenta tipoRelacion) {
		if (tipoRelacion != null) {
			if (tipoRelacion.getTipo() == 1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Método para verificar si una relación es de tipo aval.
	 *
	 * @param "bean"
	 *            Bean de la relación a verificar
	 * @return <code>true</code>. si la relación es de tipo aval.
	 */
	public Boolean isAval(final TipoRelacionPersonaCuenta tipoRelacion) {
		if (tipoRelacion.getTipo() == 2) {
			return true;
		}
		return false;
	}

	/**
	 * Método para verificar si una relación es de tipo titular.
	 *
	 * @param "bean"
	 *            Bean de la relación a verificar
	 * @return <code>true</code>. si la relación es de tipo titular.
	 */
	public Boolean isTitular(final RelacionadoBean relacionado) {
		Boolean esTitular = false;
		if (TipoRelacionPersonaCuenta.TITULAR.equals(relacionado.getTipo())) {
			esTitular = true;
		}
		return esTitular;
	}

	public String nombreTipo(final String tipoRelacion) {
		for (final CatalogoBean catalogoBean : codigosRelaciones) {
			if (catalogoBean.getClaveFila().equals(tipoRelacion)) {
				return catalogoBean.getDescripcionL();
			}
		}
		return "";
	}

	/**
	 * Método adicionar las relaciones obligatorias a la cuenta.
	 */
	public void actualizaRelaciones() {
		for (final Map.Entry<String, Boolean> entry : relacionesTarifa
				.entrySet()) {
			final TipoRelacionPersonaCuenta tipo = TipoRelacionPersonaCuenta
					.codigoDe(entry.getKey());
			if (tipo != null && entry.getValue() && countTipo(tipo, true) == 0) {
				crearNuevaRelacionPersona(tipo);
				mensajeObligatorias = initMensajeobligatorias() + " 1 " + tipo.getNombre() + ", ";
			}
		}
		if(mensajeObligatorias != null && mensajeObligatorias.length() > 0){
			mensajeObligatorias = mensajeObligatorias.substring(0, mensajeObligatorias.length()-2);
		}
	}

	/**
	 * Método que adiciona relaciones a la cuenta a partir del valor del combo
	 * de relaciones.
	 */
	public void adicionarRelacion() {
		if (tipoRelacion != null) {
			crearNuevaRelacionPersona(TipoRelacionPersonaCuenta
					.codigoDe(tipoRelacion));
		}
		if (tipoRelacionCuenta != null) {
			crearNuevaRelacionCuenta(tipoRelacionCuenta);
		}
	}

	public void adicionarPan(){
		if(this.cuentaBean.getPanesRelacionados().size() < 10){
			final PanBean panBean = new PanBean();
			panBean.setEstadoListado(EstadoListadosEnum.NUEVO);
			panBean.setTipoAlta(tipoAltaPan);
			panBean.setDebitoCredito(TIPO_TARJETA);
			panBean.setTitular(false);
			this.cuentaBean.getPanesRelacionados().add(panBean);
			this.dialogoUtils.adicionaNuevo();
		}




	}

	/**
	 * Método que crear un bean de relación para ser adicionado a la cuenta.
	 */
	public void crearNuevaRelacionPersona(final TipoRelacionPersonaCuenta tipo) {
		if (tipo != null) {
			final RelacionadoBean nuevoRelacionado = new RelacionadoBean();
			nuevoRelacionado.setEstadoListado(EstadoListadosEnum.NUEVO);
			nuevoRelacionado.setTipo(tipo);
			nuevoRelacionado.setFechaAlta(contextoUtils.getFechaContableActual());
			nuevoRelacionado.setFechaInicio(contextoUtils.getFechaContableActual());
			nuevoRelacionado.setNumero(getNumeroRelacion(tipo));

			final ClientePersonaFisicaBean cliente = new ClientePersonaFisicaBean();
			nuevoRelacionado.setPersona(cliente);

			this.cuentaBean.getPersonasRelacionadas().add(nuevoRelacionado);
			this.dialogoUtils.adicionaNuevo();
		}
	}

	/**
	 * Método que remueve una relación de persona-cuenta de la lista de
	 * realaciones.
	 *
	 * @param relacionado
	 *            Relacion a ser eliminada
	 */
	public void removerRelacionPersona(final RelacionadoBean relacionado) {
		if (relacionado != null) {
			for (final RelacionadoBean persona : getRelacionesPersonas()) {
				if (EstadoListadosEnum.NUEVO.equals(relacionado.getEstadoListado()) &&
						relacionado.getTipo().equals(persona.getTipo())) {
					if (persona.getNumero() > relacionado.getNumero()) {
						persona.setNumero(persona.getNumero() - 1);
					}
				}
			}
			if(EstadoListadosEnum.NUEVO.equals(relacionado.getEstadoListado())){
				this.cuentaBean.getPersonasRelacionadas().remove(relacionado);
				this.dialogoUtils.restaNuevo();
			}else{
				relacionado.setEstadoListado(EstadoListadosEnum.ELIMINADO);
				this.dialogoUtils.adicionaEliminado();
			}
		}
	}

	public void reactivarRelacionado(final RelacionadoBean relacionadoBean){
		relacionadoBean.setEstadoListado(EstadoListadosEnum.POR_REACTIVAR);
		this.dialogoUtils.adicionaEditado();
	}

	public void reactivarRelacionado(final CuentaRelacionadaBean relacionadaBean){
		relacionadaBean.setEstadoListado(EstadoListadosEnum.POR_REACTIVAR);
		this.dialogoUtils.adicionaEditado();
	}

	public void editarRelacionado(final RelacionadoBean relacionadoBean){
		if(relacionadoBean.getEstadoListado().equals(EstadoListadosEnum.ELIMINADO)){
			this.dialogoUtils.restaEliminado();
		}
		relacionadoBean.setEstadoListado(EstadoListadosEnum.MODIFICADO);
		this.dialogoUtils.adicionaEditado();
	}

	public void editarRelacionado(final CuentaRelacionadaBean relacionadaBean){
		if(relacionadaBean.getEstadoListado().equals(EstadoListadosEnum.ELIMINADO)){
			this.dialogoUtils.restaEliminado();
		}
		relacionadaBean.setEstadoListado(EstadoListadosEnum.MODIFICADO);
		this.dialogoUtils.adicionaEditado();
	}

	public void cancelarEliminacion(final RelacionadoBean relacionadoBean){
		relacionadoBean.setEstadoListado(EstadoListadosEnum.CONSULTADO);
		this.dialogoUtils.restaEliminado();
	}

	public void cancelarEliminacion(final CuentaRelacionadaBean relacionadaBean){
		relacionadaBean.setEstadoListado(EstadoListadosEnum.CONSULTADO);
		this.dialogoUtils.restaEliminado();
	}

	public void cancelarEliminacion(final PanBean panBean){
		panBean.setEstadoListado(EstadoListadosEnum.CONSULTADO);
		this.dialogoUtils.restaEliminado();
	}


	/**
	 * Mètodo que verifica si una relaciòn es
	 *
	 * @param "relacionado"
	 * @return
	 */
	public Boolean verificaTitular(final RelacionadoBean relacionado) {
		if (TipoRelacionPersonaCuenta.TITULAR.equals(relacionado.getTipo())
				&& relacionado.getNumero() == 1) {
			return false;
		}
		return true;
	}

	/**
	 * Método que remueve una relación de cuenta-cuenta de la lista de
	 * relaciones.
	 *
	 * @param "relacionado" Relacion a ser eliminada
	 */
	public void removerRelacionCuenta(final CuentaRelacionadaBean relacionada) {
		if (EstadoListadosEnum.NUEVO.equals(relacionada.getEstadoListado())) {
			cuentaBean.getCuentasRelacionadas().remove(relacionada);
			this.dialogoUtils.restaNuevo();
		}else{
			relacionada.setEstadoListado(EstadoListadosEnum.ELIMINADO);
			this.dialogoUtils.adicionaEliminado();
		}
	}

	/**
	 * Método que remueve una relación de cuenta-pan de la lista de
	 * relaciones.
	 *
	 * @param "relacionado" Relacion a ser eliminada
	 */
	public void removerRelacionCuenta(final PanBean relacionada) {
		if (EstadoListadosEnum.NUEVO.equals(relacionada.getEstadoListado())) {
			cuentaBean.getPanesRelacionados().remove(relacionada);
			this.dialogoUtils.restaNuevo();
		}else{
			relacionada.setEstadoListado(EstadoListadosEnum.ELIMINADO);
			this.dialogoUtils.adicionaEliminado();
		}
	}

	/**
	 * Método que verifica que una relación obligatoria tenga por lo menos una
	 * ocurrencia.
	 *
	 * @param "relacionado"
	 *            Tipo de relación a ser verificada
	 * @return <code>true</code> en caso de tener un bean del tipo consultado
	 */
	public Boolean verificaObligatorias(final TipoRelacionPersonaCuenta tipo) {
		if (getRelacionesTarifa().get(tipo.getCodigo())!=null && getRelacionesTarifa().get(tipo.getCodigo())) {
			if (countTipo(tipo, true) == 1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Método para verificar las tarifas obligatorias como relación de cuenta
	 * @param tipo tipo de producto
	 * @return <code>true</code> en caso de ser obligatoria
	 */
	public Boolean verificaCuentasObligatorias(final String tipo) {
		for (final TarifaRelacionBean tarifa : getRelacionesCuentasTarifa()) {
			if (tarifa.getCodigoRelacion().equals(tipo)) {
				if (tarifa.getRequerido()) {
					if (contarTarifas(tarifa, true) == 1) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Método que cuenta el número de ocurrencias de relaciones de cierto tipo
	 *
	 * @param tipo
	 *            Tipo de relación a ser contabilizada
	 * @return número de ocurrencias de beans del tipo consultado
	 */
	public Integer countTipo(final TipoRelacionPersonaCuenta tipo, final Boolean incluirNuevas) {
		int count = 0;
		for (final RelacionadoBean relacionado : getRelacionesPersonas()) {
			if (tipo.equals(relacionado.getTipo())) {
				if(incluirNuevas){
					count++;
				}else{
					if(!EstadoListadosEnum.NUEVO.equals(relacionado.getEstadoListado())){
						count++;
					}
				}

			}
		}
		return count;
	}

	public Integer contarTarifas(final TarifaRelacionBean tarifa, final Boolean incluirNuevas) {
		int count = 0;
		for (final CuentaRelacionadaBean cuenta : this.cuentaBean.getCuentasRelacionadas()) {
			if (cuenta.getTipoRelacion().equals(tarifa.getCodigoRelacion())) {
				if(!EstadoListadosEnum.INACTIVO.equals(cuenta.getEstadoListado())){
					if(incluirNuevas){
						count++;
					}else{
						if(!EstadoListadosEnum.NUEVO.equals(cuenta.getEstadoListado())){
							count++;
						}
					}
				}
			}
		}
		return count;
	}

	/**
	 * Método que calcula el número que tendrá el beneficiario para la relación
	 * con la cuenta.
	 *
	 * @return Número de orden de relación.
	 */
	private Integer getNumeroRelacion(final TipoRelacionPersonaCuenta tipo) {
		Integer numRelacion = 0;
		for (final RelacionadoBean relacionado : getRelacionesPersonas()) {
			if (tipo.equals(relacionado.getTipo())) {
				numRelacion++;
			}
		}
		return numRelacion + 1;
	}

	public Boolean numeroEditable(final RelacionadoBean relacionado){
		if(getNumeroRelacion(relacionado.getTipo()) > 2){
			return true;
		}
		return false;
	}

	/**
	 * Método que calcula el número que tendrá la cuenta para la relación con la
	 * cuenta.
	 *
	 * @return Número de orden de relación.
	 */
	private Integer getNumeroRelacion(final TarifaRelacionBean tarifa) {
		Integer numRelacion = 0;
		for (final CuentaRelacionadaBean relacionada : this.cuentaBean.getCuentasRelacionadas()) {
			if (tarifa.getCodigoRelacion()
					.equals(relacionada.getTipoRelacion())) {
				numRelacion++;
			}
		}
		return numRelacion + 1;
	}

	/**
	 * Método que consulta los datos de un cliente a partir de su id externa.
	 *
	 * @param relacionado
	 *            bean de relación que contiene los datos del cliente.
	 */
	public void verificaIdExterna(final RelacionadoBean relacionado) {
		try {
			relacionado.getPersona().setNumIdentificacion(relacionado.getPersona().getNumIdentificacion().trim());
			final List<ClientePersonaFisicaBean> clientes = busquedaIdExternaBackEnd
					.ejecutarTRN(relacionado.getPersona()
							.getNumIdentificacion().toUpperCase());
			for (int i=clientes.size();i>0;i--){
				if (TipoCliente.PERSONA_MORAL.getTipo().equals(clientes.get(i-1).getTipoCliente())){
					clientes.remove(i-1);
				}
			}

			if(clientes.size() > 1){
				final DialogoListadoBean dialogoBuscar = new DialogoListadoBean();
				dialogoBuscar.setEstado(DialogoRelacionesCuentaListadoEnum.SIN_CAMBIOS);
				dialogoBuscar.setMostrar(true);
				setMensajeBusqueda(dialogoBuscar);
				setPersonaBuscada(relacionado);
			}else{
				if (!clientes.isEmpty()) {
					relacionado.setPersona(clientes.get(0));
				} else {
					resetRelacionado(relacionado, relacionado.getPersona()
							.getNumIdentificacion());
				}
			}
		} catch (final NoControlableException nce) {
			resetRelacionado(relacionado, relacionado.getPersona()
					.getNumIdentificacion());
		} catch (final ControlableException ce) {
			resetRelacionado(relacionado, relacionado.getPersona()
					.getNumIdentificacion());
		} catch (final NullPointerException npe) {
			resetRelacionado(relacionado, relacionado.getPersona()
					.getNumIdentificacion());
		}
	}

	/**
	 * Método que reinicia un bean relacionado en caso de consultar un id
	 * externo no registrado
	 *
	 * @param relacionadoBean
	 *            Bean con datos de la relacion persona-cuenta
	 */
	public void resetRelacionado(final RelacionadoBean relacionadoBean, final String identificacionExterna) {
		if (relacionadoBean.getPersona() == null) {
			relacionadoBean.setPersona(new ClientePersonaFisicaBean());
		}
		relacionadoBean.getPersona().setNombre(null);
		relacionadoBean.getPersona().setIdInterna(null);
		relacionadoBean.getPersona().setNumIdentificacion(identificacionExterna);
	}

	/**
	 * Método que verifica si el bean de relacion persona-cuenta tiene un id
	 * externo registrado.
	 *
	 * @param relacionado
	 *            Bean a verificar
	 * @return <code>true</code> En caso de que el id externa no sea válida.
	 */
	public Boolean relacionadoInvalido(final RelacionadoBean relacionado) {
		if (relacionado.getPersona() != null
				&& relacionado.getPersona().getNumIdentificacion() != null) {
			if (!StringUtils.isEmpty(relacionado.getPersona()
					.getNumIdentificacion())) {
				if (relacionado.getPersona().getIdInterna() == null) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Método encargado de consultar las relaciones obligatorias de la cuenta a
	 * partir de los datos de la tarifa.
	 */
	public void consultaRelacionesTarifa() {
		initRelacionesTarifa();
		initCodigosRelaciones();
		actualizaRelaciones();
	}

	private void initRelacionesTarifa(){
		setRelacionesTarifa(consultaRelacionesObligatoriasBackEnd
				.ejecutarTRN(this.cuentaBean));
		if(getRelacionesTarifa().containsKey(TipoRelacionPersonaCuenta.BENEFICIARIO.getCodigo()) && this.sinBeneficiarios){
			getRelacionesTarifa().remove(TipoRelacionPersonaCuenta.BENEFICIARIO.getCodigo());
		}
	}

	/**
	 * Método para filtrar las relaciones persona-cuenta que pueden ser
	 * adicionadas a la cuenta.
	 */
	public void initCodigosRelaciones() {
		codigosRelaciones = SerializationUtils.clone((ArrayList<CatalogoBean>)catalogoUtils.getCatalogo(CatalogoEnum.TP_RL_PERS_AC));
		razonesDeCambio = catalogoUtils.getCatalogo(CatalogoEnum.TP_RZN_ECV_RP);
		final Iterator<CatalogoBean> i = codigosRelaciones.iterator();
		while (i.hasNext()) {
			final CatalogoBean catalogoBean = i.next();
			if (!relacionesTarifa.containsKey(catalogoBean.getClaveFila())) {
				i.remove();
			}
		}
	}

	public String tipoDocumento(final String tipo){
		String tipoDocumento = "";
		try{
			final CatalogoBean catalogo = catalogoUtils.getCatalogoBean(CatalogoEnum.TP_DOC, tipo);
			if(catalogo != null){
				tipoDocumento = catalogo.getDescripcionL();
			}else{
				tipoDocumento = "";
			}
		}catch(final Exception e){
			tipoDocumento = "";
		}
		return tipoDocumento;
	}

	public String estadoRelacionado(final String estado){
		String estadoRelacionado = "";
		try{
			final CatalogoBean catalogo = catalogoUtils.getCatalogoBean(CatalogoEnum.TP_ECV_PERS_AC, estado);
			if(catalogo != null){
				estadoRelacionado = catalogo.getDescripcionL();
			}else{
				estadoRelacionado = "";
			}
		}catch(final Exception e){
			estadoRelacionado = "";
		}
		return estadoRelacionado;
	}

	/**
	 * Método para filtrar las relaciones persona-cuenta que pueden ser
	 * adicionadas a la cuenta.
	 */
	public void initCodigosRelacionesCuentas() {
		razonesDeCambio = catalogoUtils.getCatalogo(CatalogoEnum.TP_RZN_ECV_AC_AC);
		initRelacionesCuentaTarifa();
		verificaObligatorias();
		if(mensajeObligatorias != null && mensajeObligatorias.length() > 0){
			mensajeObligatorias = mensajeObligatorias.substring(0, mensajeObligatorias.length()-2);
		}
		if(!getRelacionesCuentasTarifa().isEmpty()){
			this.tipoRelacionCuenta = getRelacionesCuentasTarifa().get(0);
		}
	}

	private void verificaObligatorias(){
		for (final TarifaRelacionBean tarifaRelacionBean : getRelacionesCuentasTarifa()) {
			if (tarifaRelacionBean.getRequerido()) {
				if(contarTarifas(tarifaRelacionBean, true) == 0){
					crearNuevaRelacionCuenta(tarifaRelacionBean);
				}
				mensajeObligatorias = initMensajeobligatorias() + " 1 " + tarifaRelacionBean.getNombreRelacion() + ", ";
			}
		}
	}

	private void initRelacionesCuentaTarifa(){
		if(relacionesCuentasTarifa == null){
			codigosRelaciones = SerializationUtils.clone((ArrayList<CatalogoBean>)catalogoUtils.getCatalogo(CatalogoEnum.TP_RL_AC_AC));
			final TarifaBean tarifaBean = new TarifaBean();
			tarifaBean.setLinea(this.cuentaBean.getCodLinea());
			tarifaBean.setGrupo(this.cuentaBean.getIdGrupoProducto());
			getRelacionesCuentasTarifa().addAll(relacionTarifasLoaderTask.getCatalogo(tarifaBean));
		}
	}

	/**
	 * Método que consulta las relaciones preexistentes de la cuenta.
	 *
	 * @throws NoControlableException
	 * @throws ControlableException
	 */
	public void consultaRelacionesPersonas() throws NoControlableException,
			ControlableException {
		if (!busqueda) {
			actualizarEstadoAltaCuenta(0);
			this.tipoRelacion = "01";
			this.cuentaBean.setPersonasRelacionadas(consultaRelacionPersonaCuentaBackEnd.ejecutarTRN(this.cuentaBean.getNumeroCuenta(),true));
			backUpUtils.respaldaArray((ArrayList<RelacionadoBean>) this.cuentaBean.getPersonasRelacionadas());
			this.dialogoUtils = new DialogoRelacionesCuentaListadoUtils(this.cuentaBean.getPersonasRelacionadas().size());
			this.dialogoUtils.setValidacion(true);
			verificaRepresentante();
			consultaRelacionesTarifa();
		}
	}

	/**
	 * Método para dar de alta las relaciones entre personas y cuentas.
	 *
	 * @return La ruta hacia la próxima vista.
	 * @throws NoControlableException
	 * @throws ControlableException
	 */
	public Boolean relacionarPersonas() throws NoControlableException {
			this.getDialogoGuardado().setMostrar(false);
			List<RelacionadoBean> beneficiariosModificados = new ArrayList<RelacionadoBean>();
			List<RelacionadoBean> eliminados = new ArrayList<RelacionadoBean>();
			for(final RelacionadoBean relacionadoBean : this.cuentaBean.getPersonasRelacionadas()){
				if(EstadoListadosEnum.ELIMINADO.equals(relacionadoBean.getEstadoListado())){
					if(bajaRelacionPersonaCuentaBackEnd.ejecutarTRN(relacionadoBean, this.cuentaBean)){
						eliminados.add(relacionadoBean);
						this.dialogoUtils.restaEliminado();
					}
				}
			}
			this.cuentaBean.getPersonasRelacionadas().removeAll(eliminados);
			for(final RelacionadoBean relacionadoBean : this.cuentaBean.getPersonasRelacionadas()){
				if(EstadoListadosEnum.MODIFICADO.equals(relacionadoBean.getEstadoListado())){
					if(TipoRelacionPersonaCuenta.BENEFICIARIO.equals(relacionadoBean.getTipo())){
						beneficiariosModificados.add(relacionadoBean);
					}else{
						modificaRelacionCuentaPersonaBackEnd.ejecutarTRN(relacionadoBean, this.cuentaBean);
						this.dialogoUtils.restaEditado();
					}
				}
			}
			Collections.sort(beneficiariosModificados, new RelacionadoBeanPorcentajeComparator());
			for(final RelacionadoBean relacionadoBean : beneficiariosModificados){
				modificaRelacionCuentaPersonaBackEnd.ejecutarTRN(relacionadoBean, this.cuentaBean);
				this.dialogoUtils.restaEditado();
			}
			for(final RelacionadoBean relacionadoBean : this.cuentaBean.getPersonasRelacionadas()){
				if(EstadoListadosEnum.NUEVO.equals(relacionadoBean.getEstadoListado())){
					altaRelacionPersonaCuentaBackEnd.ejecutarTRN(this.cuentaBean, relacionadoBean);
					this.dialogoUtils.restaNuevo();
				}
			}
	
			for(final RelacionadoBean relacionadoBean : this.cuentaBean.getPersonasRelacionadas()){
				if(EstadoListadosEnum.POR_REACTIVAR.equals(relacionadoBean.getEstadoListado())){
					modificaRelacionCuentaPersonaBackEnd.ejecutarTRN(relacionadoBean, this.cuentaBean);
					this.dialogoUtils.restaEditado();
				}
			}
			
			this.busqueda = false;
			consultaRelacionesPersonas();
			this.getDialogoGuardado().setMostrar(true);
			return true;
	}
	
	public void confirmacionGuardado(){
		getMensajeEliminados().setMostrar(false);
		confirmacionGuardado(true);
	}

	public void confirmacionGuardado(final Boolean confirmado){
		getMensajeEliminados().setMostrar(false);
		if (validaBeneficiarios()) {
			if(!confirmado && this.dialogoUtils.getEliminados() > 0){
				if(this.dialogoUtils.getEliminados() == 1){
					setMensajeEliminados(this.dialogoUtils.getMensajePorEliminar(COSA));
				}else{
					setMensajeEliminados(this.dialogoUtils.getMensajePorEliminar(COSAS));
				}
			}else{
				setDialogoGuardado(this.dialogoUtils.getMensaje(COSAS));
				if(!DialogoRelacionesCuentaListadoEnum.SIN_CAMBIOS.equals(this.dialogoGuardado.getEstado())){
					relacionarPersonas();
				}
			}
		}
	}

	public void guardadoCuentas(){
		getMensajeEliminados().setMostrar(false);
		guardadoCuentas(true);
	}

	public void guardadoCuentas(final Boolean confirmado){
		if(!validaDatosCuentasARelacionar()){
			if(verificaCentrosCuenta()){
				return;
			}
			if(!confirmado && this.dialogoUtils.getEliminados() > 0){
				if(this.dialogoUtils.getEliminados() == 1){
					setMensajeEliminados(this.dialogoUtils.getMensajePorEliminar(COSA));
				}else{
					setMensajeEliminados(this.dialogoUtils.getMensajePorEliminar(COSAS));
				}
			}else{
				setDialogoGuardado(this.dialogoUtils.getMensaje(COSAS));
				if(!DialogoRelacionesCuentaListadoEnum.SIN_CAMBIOS.equals(this.dialogoGuardado.getEstado())){
					relacionaCuentas();
				}
			}
		}else{
			RequestContext.getCurrentInstance().execute("PF('dlgCuentaDuplicada').show()");
		}
	}
	
	private Boolean validaDatosCuentasARelacionar(){
		Boolean relacionDuplicada = false;
		for(CuentaRelacionadaBean cuentaClienteBean : this.cuentaBean.getCuentasRelacionadas()){
			if(this.cuentaBean.getNumeroCuenta().equals(cuentaClienteBean.getCuenta().getNumeroCuenta())){
				relacionDuplicada = true;
			}
		}
		return relacionDuplicada;
	}

	@StoreStep
	public String guardadoSat(){
		getMensajeEliminados().setMostrar(false);
		return guardadoSat(true);
	}

	@StoreStep
	public String guardadoSat(final Boolean confirmado){
		if(!confirmado && this.dialogoUtils.getEliminados() > 0){
			if(this.dialogoUtils.getEliminados() == 1){
				setMensajeEliminados(this.dialogoUtils.getMensajePorEliminar(COSA));
			}else{
				setMensajeEliminados(this.dialogoUtils.getMensajePorEliminar(COSAS));
			}
		}else{
			setDialogoGuardado(this.dialogoUtils.getMensaje(COSAS));
			if(!DialogoRelacionesCuentaListadoEnum.SIN_CAMBIOS.equals(this.dialogoGuardado.getEstado())){
				return relacionaSat();
			}
		}
		return "";
	}

	@StoreStep
	private String relacionaSat(){
			this.getDialogoGuardado().setMostrar(false);
			preparaCuenta();
			for (final PanBean pan : this.cuentaBean.getPanesRelacionados()) {
				if(EstadoListadosEnum.NUEVO.equals(pan.getEstadoListado())){
					pan.setNombreCliente(pan.getNombreCliente().replace("#", ""));
					relacionaPanCuentaBackEnd.ejecutarTRN(pan, this.cuentaBean,
							obtenerCuentaOperativa().getCuenta().getNumeroCuenta(),
							this.clienteBean.getIdInterna());
					this.dialogoUtils.restaNuevo();
				}
			}
			
			this.initPans();
			
			this.dialogoUtils = new DialogoRelacionesCuentaListadoUtils(this.obtenerPanesRelacionados().size());
			return null;
	}

	/**
	 * Mètodo para verificar si la suma de porcentajes de beneficiarios es igual
	 * a 100%
	 *
	 * @return <code>true</code> en caso de que la suma sea 100%
	 */
	private Boolean validaBeneficiarios() {
		BigDecimal totalPorcentaje = new BigDecimal(0);
		Boolean tieneBeneficiarios = false;
		List<Integer> beneficiarios = new ArrayList<Integer>();
		for (final RelacionadoBean relacionadoBean : getRelacionesPersonas()) {
			if (TipoRelacionPersonaCuenta.BENEFICIARIO.equals(relacionadoBean
					.getTipo()) && (EstadoListadosEnum.CONSULTADO.equals(relacionadoBean.getEstadoListado())
					|| EstadoListadosEnum.NUEVO.equals(relacionadoBean.getEstadoListado())
					|| EstadoListadosEnum.INACTIVO.equals(relacionadoBean.getEstadoListado())
					|| EstadoListadosEnum.MODIFICADO.equals(relacionadoBean.getEstadoListado()))) {
				tieneBeneficiarios = true;
				if(relacionadoBean.getPorcentaje() != null){
					totalPorcentaje = totalPorcentaje.add(relacionadoBean
						.getPorcentaje());
				}
				if(relacionadoBean.getPersona() != null && relacionadoBean.getPersona().getIdInterna() != null){
					if(beneficiarios.contains(relacionadoBean.getPersona().getIdInterna())){
						throw new ControlableException("Error al relacionar beneficiarios. ","Existen datos duplicados.");
					}else{
						beneficiarios.add(relacionadoBean.getPersona().getIdInterna());
					}
				}
			}
		}
		if (!tieneBeneficiarios	|| (tieneBeneficiarios && totalPorcentaje.intValue() == 100)) {
			return true;
		} else {
			String error = "menor";
			if(totalPorcentaje.intValue() > 100){
				error = "mayor";
			}
			final DialogoListadoBean dialogoError = new DialogoListadoBean();
			dialogoError.setEstado(DialogoRelacionesCuentaListadoEnum.ALERTA);
			dialogoError.setMensaje(mensajeBeneficiario(error));
			dialogoError.setMostrar(true);
			setDialogoGuardado(dialogoError);
			RequestContext.getCurrentInstance().update("dlgGuardado");
		}
		return false;
	}

	private static String mensajeBeneficiario(final String error){
		return "La suma de los porcentajes de beneficiario es "+ error +" al 100%. Por favor, revise los valores asignados";
	}

	public List<Integer> obtenerNumerosRelacion(final RelacionadoBean relacionado){
		final List<Integer> numeros = new ArrayList<Integer>();
		int inicio = 1;
		if(TipoRelacionPersonaCuenta.TITULAR.equals(relacionado.getTipo())){
			 inicio = 2;
		}
		for(int i = inicio; i < getNumeroRelacion(relacionado.getTipo()); i++){
			numeros.add(i);
		}
		return numeros;
	}
	/**
	 * Método para consultar los documentos asociados a la cuenta y los
	 * documentos que se pueden emitir para este tipo de producto.
	 */
	public void consultaDocumentosAEmitir() {
		actualizarEstadoAltaCuenta(2);
		try {
			setDocumentosAEmitir(consultaDocumentosAEmitirBackEnd
					.ejecutarTRN(this.cuentaBean.getNumeroCuenta()));
			organizarDocumentos();
		} catch (final NumberFormatException nfe) {
			throw new ControlableException(
					"Error al ejecutar servicio consulta de documentos a emitir.",
					"Número de acuerdo inválido.");
		}
	}

	public void consultaDocumentosRelacionados(){
		this.cuentaBean.setPersonasRelacionadas(consultaRelacionPersonaCuentaBackEnd.ejecutarTRN(this.cuentaBean.getNumeroCuenta(),true));
		for (final RelacionadoBean relacionado : this.cuentaBean.getPersonasRelacionadas()) {
			consultaDocumentosBackEnd.ejecutarTRN(relacionado.getPersona());
		}
	}

	/**
	 * Mètodo que consulta los campos asociados a un documento
	 *
	 * @param documento
	 *            bean con detalles del documento consultado
	 */
	public void initCamposDocumentos(final EmisionDocumentosBean documento) {
		documento.setMuestraCampos(false);
		if (documento.getIdInterno() != null) {
			documento.setCampos(consultaCamposDocumentoBackEnd
					.ejecutarTRN(documento.getIdInterno()));
		}
	}

	public Boolean tieneInactivas(){
		for (final RelacionadoBean relacionadoBean : this.cuentaBean.getPersonasRelacionadas()) {
			if(EstadoListadosEnum.INACTIVO.equals(relacionadoBean.getEstadoListado()) ||
					EstadoListadosEnum.POR_REACTIVAR.equals(relacionadoBean.getEstadoListado())){
				return true;
			}

		}
		return false;
	}

	public Boolean tieneCuentasInactivas(){
		if(this.cuentaBean != null){
			for (final CuentaRelacionadaBean relacionadaBean : this.cuentaBean.getCuentasRelacionadas()) {
				if(EstadoListadosEnum.INACTIVO.equals(relacionadaBean.getEstadoListado()) ||
						EstadoListadosEnum.POR_REACTIVAR.equals(relacionadaBean.getEstadoListado())){
					return true;
				}

			}
		}
		return false;
	}
	
	public Boolean altaSatSinConstituir(){
		Boolean altaSatPorConstituir = false;
		if(this.altaSat && this.isAlta && 
				!this.cuentaBean.getEstado().equals(ConstantesFuncionales.ESTADO_CUENTA_ACTIVO)){
			altaSatPorConstituir = true;
		}
		return altaSatPorConstituir;
	}

	/**
	 * Método que organiza los documentos para identificar si es un documento
	 * que se puede emitir/formalizar ò si es un documento para el histórico.
	 */
	public void organizarDocumentos() {
		final Map<Integer, List<EmisionDocumentosBean>> historial = new HashMap<Integer, List<EmisionDocumentosBean>>();
		final Iterator<EmisionDocumentosBean> i = documentosAEmitir.iterator();
		while (i.hasNext()) {
			final EmisionDocumentosBean documentoBean = i.next();
			if (documentoBean.getIdInterno() != 0) {
				documentoBean.setEstados(consultaEstadosDocumentoBackEnd
						.ejecutarTRN(documentoBean.getIdInterno()));
			}
			if ("A".equals(documentoBean.getEstado())) {
				if (!historial.containsKey(documentoBean.getFormulario())) {
					final List<EmisionDocumentosBean> historicoDocumento = new ArrayList<EmisionDocumentosBean>();
					historicoDocumento.add(documentoBean);
					historial.put(documentoBean.getFormulario(),
							historicoDocumento);
				} else {
					historial.get(documentoBean.getFormulario()).add(
							documentoBean);
				}
				i.remove();
			}
		}
		for (final EmisionDocumentosBean documento : documentosAEmitir) {
			documento.setHistorico(historial.get(documento.getFormulario()));
			documento.setMuestraCampos(false);
			if (!"".equals(documento.getEstado())
					&& documento.getCampos() == null) {
				initCamposDocumentos(documento);
			}
		}
	}

	/**
	 * Método que consulta las cuentas que pueden relacionarse a la cuenta en
	 * cuestión.
	 *
	 * @throws NoControlableException
	 * @throws ControlableException
	 */
	public void consultaCuentasARelacionar() throws NoControlableException,
			ControlableException {
		try{
			if(!busqueda){
				actualizarEstadoAltaCuenta(1);
				addMessage(FacesMessage.SEVERITY_INFO,"","Para cada tipo de relación se mostrarán las cuentas que cumplan los requisitos.");
				if(cuentasRelacionables == null){
					setCuentasRelacionables(consultaCuentasClienteBackEnd.ejecutarTRN(this.clienteBean.getIdInterna(), true));
				}
				this.cuentaBean.setCuentasRelacionadas(consultaRelacionesCuentaCuentasBackEnd.ejecutarTRN(this.cuentaBean.getNumeroCuenta()));
				backUpUtils.respaldaArray((ArrayList<CuentaRelacionadaBean>) this.cuentaBean.getCuentasRelacionadas());
				this.dialogoUtils = new DialogoRelacionesCuentaListadoUtils(this.cuentaBean.getCuentasRelacionadas().size());
				initCodigosRelacionesCuentas();
			}
		}catch(NullPointerException npe){
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "¡Atención!",
						npe.getMessage());
				RequestContext.getCurrentInstance()
					.showMessageInDialog(message);
		}
	}

	public void consultaPanRelacionar(){
		actualizarEstadoAltaCuenta(2);
		tipoAltaPan = TipoAltaEnum.NUEVA;
		initPans();
		this.dialogoUtils = new DialogoRelacionesCuentaListadoUtils(this.obtenerPanesRelacionados().size());
		if(this.obtenerPanesRelacionados().isEmpty()){
			final PanBean panBean = new PanBean();
			panBean.setEstadoListado(EstadoListadosEnum.NUEVO);
			panBean.setTipoAlta(tipoAltaPan);
			panBean.setNombreCliente(this.clienteBean.getNombreCompleto());
			panBean.setTitular(true);
			panBean.setDebitoCredito(TIPO_TARJETA);
			this.cuentaBean.getPanesRelacionados().add(panBean);
			this.dialogoUtils.adicionaNuevo();
		}
		try{
			String filtroBin = obtenerFiltroBin();
			if(!StringUtils.isEmpty(filtroBin)){
				catalogoBin = catalogoUtils.filtraCatalogoOrdenado(CatalogoEnum.TP_VMP_BIN_TJ,filtroBin);
			}else{
				catalogoBin = new ArrayList<CatalogoBean>();
			}
		}catch(final Exception e){
			catalogoBin = new ArrayList<CatalogoBean>();
		}
		if(catalogoBin.isEmpty() && !getSinCuentaOperativa()){
			setSinBines(true);
		}
	}

	private void initPans(){
		this.cuentaBean.setPanesRelacionados(null);
		final List<DatoAdicionalBean> datosAdicionales = consultaDatosAdicionalesBackEnd.ejecutarTRN(this.cuentaBean);
		for (final DatoAdicionalBean datoAdicionalBean : datosAdicionales) {
			if(datoAdicionalBean.getInformacionAdicional().startsWith("PAN")
					&& datoAdicionalBean.getValor() != null &&
					!datoAdicionalBean.getValor().trim().equals("")){
				final PanBean pan = new PanBean();
				pan.setEstadoListado(EstadoListadosEnum.CONSULTADO);
				pan.setNumeroPan(datoAdicionalBean.getValor());
				pan.setTipoTarjeta(datoAdicionalBean.getInformacionAdicional());
				this.obtenerPanesRelacionados().add(pan);
			}
		}
	}

	private String obtenerFiltroBin(){
		String filtroBin = null;
		final CuentaRelacionadaBean cuentaRelacionadaBean = obtenerCuentaOperativa();
		if(cuentaRelacionadaBean != null && cuentaRelacionadaBean.getCuenta() != null){
			cuentaRelacionadaBean.setCuenta(
					consultaCuentaBackend.ejecutarTRN(
							(long)cuentaRelacionadaBean.getCuenta().getNumeroCuenta()));
			filtroBin = cuentaRelacionadaBean.getCuenta().getIdProducto().trim() +
					cuentaRelacionadaBean.getCuenta().getIdTarifaProducto().trim();
		}else{
			setSinCuentaOperativa(true);
		}
		return filtroBin;
	}

	private CuentaRelacionadaBean obtenerCuentaOperativa(){
		CuentaRelacionadaBean cuentaRelacionadaBean = null;
		if(this.cuentaBean.getCuentasRelacionadas().isEmpty()){
			this.cuentaBean.setCuentasRelacionadas(consultaRelacionesCuentaCuentasBackEnd.ejecutarTRN(this.cuentaBean.getNumeroCuenta()));
		}
		for (final CuentaRelacionadaBean cuenta : this.cuentaBean.getCuentasRelacionadas()) {
			if(ConstantesFuncionales.REL_AC_AC_CUENTA_OPERATIVA.equals(cuenta.getTipoRelacion()) &&
					!ConstantesFuncionales.COD_ECV_AC_AC_INACTIVA.equals(cuenta.getEstadoRelacion()) &&
					ConstantesFuncionales.ESTADO_CUENTA_ACTIVO.equals(cuenta.getCuenta().getEstado())){

				cuentaRelacionadaBean = cuenta;
				cuentaRelacionadaBean.setCuenta(
						consultaCuentaBackend.ejecutarTRN(
								(long)cuentaRelacionadaBean.getCuenta().getNumeroCuenta()));
				
				try {
					catalogoBin = catalogoUtils.filtraCatalogoOrdenado(CatalogoEnum.TP_VMP_BIN_TJ, 
							cuenta.getCuenta().getIdProducto().trim() +
							cuenta.getCuenta().getIdTarifaProducto().trim());
				} catch (ControlableException | NoControlableException ex){
					//continuo buscando bines con resto de cuentas
				}
				// Nos quedamos con la cuenta que tenga bines y en su defecto con una de las operativas
				if (catalogoBin!=null && !catalogoBin.isEmpty()) {
					break;
				}
			}
		}
		return cuentaRelacionadaBean;
	}

	public void actualizaCatalogoPan(final PanBean pan){
		if(pan.getBin() != null && !pan.getBin().equals("")){
			try{
				pan.setCatalogoCondicionEconomica(catalogoUtils.filtraCatalogoOrdenado(CatalogoEnum.TP_VMP_COND_ECONOM,pan.getBin().trim()));
			}catch(final Exception e){
				pan.setCatalogoCondicionEconomica(new ArrayList<CatalogoBean>());
			}
			try{
				pan.setCatalogoProductosTcb(catalogoUtils.filtraCatalogoOrdenado(CatalogoEnum.TP_VMP_PRDTO_TCB,pan.getBin().trim()));
			}catch(final Exception e){
				pan.setCatalogoProductosTcb(new ArrayList<CatalogoBean>());
			}
		}
	}

	/**
	 * Método que generá un nuevo bean de relacion de cuenta-cuenta.
	 *
	 * @param "tipo" El tipo de relación a ser adicionado.
	 */
	public void crearNuevaRelacionCuenta(final TarifaRelacionBean tarifa) {
		if (tarifa != null) {
			if(altaSat!= null && altaSat
					&& ConstantesFuncionales.REL_AC_AC_CUENTA_OPERATIVA.equals(tarifa.getCodigoRelacion())){
				if(contarTarifas(tarifa,true) == 1){
					RequestContext.getCurrentInstance().execute("PF('dlgUnaCuentaOperativa').show()");
					return;
				}
			}
			final CuentaRelacionadaBean cuentaRelacionadaBean = new CuentaRelacionadaBean();
			cuentaRelacionadaBean.setTipoRelacion(tarifa.getCodigoRelacion());
			cuentaRelacionadaBean.setEstadoListado(EstadoListadosEnum.NUEVO);;
			cuentaRelacionadaBean.setNumero(getNumeroRelacion(tarifa));
			cuentaRelacionadaBean.setAlta(contextoUtils.getFechaContableActual());
			cuentaRelacionadaBean.setInicio(contextoUtils.getFechaContableActual());
			cuentaRelacionadaBean.setInicioIf(contextoUtils.getFechaContableActual());
			for (final CuentaClienteBean cuenta : getCuentasRelacionables()) {
				if (tarifa.tarifaValida(cuenta.getCuenta()) && !this.cuentaBean.getNumeroCuenta().equals(cuenta.getCuenta().getNumeroCuenta())) {
					cuentaRelacionadaBean.getCuentasRelacionables().add(cuenta);
				}
			}
			this.cuentaBean.getCuentasRelacionadas().add(cuentaRelacionadaBean);
			this.dialogoUtils.adicionaNuevo();
		}
	}

	public void recuperDatosRelacionado(final RelacionadoBean relacionadoBean) {
		backUpUtils.recuperaBean(relacionadoBean);
		this.dialogoUtils.restaEditado();
	}

	public void recuperDatosRelacionado(final CuentaRelacionadaBean relacionadaBean) {
		backUpUtils.recuperaBean(relacionadaBean);
		this.dialogoUtils.restaEditado();
	}

	/**
	 * Método que da de alta las relaciones de una cuenta con otras cuentas.
	 */
	@StoreStep
	public String relacionaCuentas() throws NoControlableException {
			this.getDialogoGuardado().setMostrar(false);
			actualizaDatosCuenta();
			List<CuentaRelacionadaBean> eliminadas = new ArrayList<CuentaRelacionadaBean>();
			for (final CuentaRelacionadaBean cuentaRelacionada : this.cuentaBean.getCuentasRelacionadas()) {
				if (EstadoListadosEnum.ELIMINADO.equals(cuentaRelacionada.getEstadoListado())) {
					if(bajaRelacionCuentaCuentaBackEnd.ejecutarTRN(cuentaRelacionada, this.cuentaBean)){
						eliminadas.add(cuentaRelacionada);
						this.dialogoUtils.restaEliminado();
					}
				}
			}
			this.cuentaBean.getCuentasRelacionadas().removeAll(eliminadas);
			for (final CuentaRelacionadaBean cuentaRelacionada : this.cuentaBean.getCuentasRelacionadas()) {
				if (EstadoListadosEnum.MODIFICADO.equals(cuentaRelacionada.getEstadoListado())) {
					modificaRelacionCuentaCuentaBackEnd.ejecutarTRN(cuentaRelacionada, this.cuentaBean);
					this.dialogoUtils.restaEditado();
				}
			}
			for (final CuentaRelacionadaBean cuentaRelacionada : this.cuentaBean.getCuentasRelacionadas()) {
				if (EstadoListadosEnum.NUEVO.equals(cuentaRelacionada.getEstadoListado())
						&& cuentaRelacionada.getCuenta() != null) {
					altaRelacionCuentaCuentaBackEnd.ejecutarTRN(this.cuentaBean, cuentaRelacionada);
					this.dialogoUtils.restaNuevo();
				}	
			}
			
			for(final CuentaRelacionadaBean cuentaRelacionada : this.cuentaBean.getCuentasRelacionadas()){
				if(EstadoListadosEnum.POR_REACTIVAR.equals(cuentaRelacionada.getEstadoListado())){
					modificaRelacionCuentaCuentaBackEnd.ejecutarTRN(cuentaRelacionada, this.cuentaBean, true);
					this.dialogoUtils.restaEditado();
				}
			}
	
			this.cuentaBean.setCuentasRelacionadas(consultaRelacionesCuentaCuentasBackEnd.ejecutarTRN(this.cuentaBean.getNumeroCuenta()));
			backUpUtils.respaldaArray((ArrayList<CuentaRelacionadaBean>) this.cuentaBean.getCuentasRelacionadas());
			this.dialogoUtils = new DialogoRelacionesCuentaListadoUtils(this.cuentaBean.getCuentasRelacionadas().size());
			verificaObligatorias();
			this.getDialogoGuardado().setMostrar(true);
			return "";
	}
	
	private Boolean verificaCentrosCuenta(){
		Boolean centroDiferente = false;
		if(isAlta && this.cuentaBean.getCentro()== null){
			 this.cuentaBean.setCentro(this.contextoUtils.getSucursal());
		}
		for (final CuentaRelacionadaBean cuentaRelacionada : this.cuentaBean.getCuentasRelacionadas()) {
			if (EstadoListadosEnum.NUEVO.equals(cuentaRelacionada.getEstadoListado())
					&& cuentaRelacionada.getCuenta() != null) {
				CuentaBean cuenta = cuentaRelacionada.getCuenta();
				if(cuenta.getCentro() == null || StringUtils.isEmpty(cuenta.getCentro().trim())){
					try{
						cuenta =  consultaCuentaBackend.ejecutarTRN(cuenta.getNumeroCuenta());
					}catch(ControlableException ce){
					}
					centroDiferente = !this.cuentaBean.getCentro().equals(cuenta.getCentro());
					if(centroDiferente){
						break;
					}
				}
			}
		}
		if(centroDiferente){
			setCentrosDiferentes(true);
		}
		return centroDiferente;
	}

	/**
	 * Método que verifica si la cuenta tiene cuentas por relacionar.
	 *
	 * @return <code>true</code> si la cuenta tiene relaciones por dar de alta.
	 */
	public Boolean getTieneRelaciones() {
		if (this.cuentaBean.getCuentasRelacionadas().isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * Mètodo para obtener la cadena de caracteres que define al estado del
	 * documento.
	 *
	 * @param estado
	 *            cadena de caracteres con el estado del docuemnto
	 * @return nombre del estado
	 */
	public String getEstadoDocumento(final String estado) {
		if ("A".equals(estado)) {
			return "Anulado";
		}
		if ("E".equals(estado)) {
			return "Emitido";
		}
		if ("F".equals(estado)) {
			return "Formalizado";
		}
		return "";
	}

	public Boolean cuentaOperable(){
		if(cuentaBean != null && ConstantesFuncionales.ESTADOS_CUENTA_OPERACION.contains(cuentaBean.getEstado())){
			return true;
		}
		return false;
	}

	/**
	 * Método para mostrar o colapsar el panel de campos del documento
	 *x|x
	 * @param documento
	 */
	public void muestraCamposDocumento(final EmisionDocumentosBean documento) {
		if (documento != null) {
			if (documento.getCampos() == null) {
				initCamposDocumentos(documento);
			}
			if (documento.getMuestraCampos()) {
				documento.setMuestraCampos(false);
			} else {
				documento.setMuestraCampos(true);
			}
		}
	}

	/**
	 * Mètodo para verificar si un documento tiene una emisiòn completa o no.
	 *
	 * @param documento
	 * @return <code>true</code> en caso de estar en estado emitido y con todos
	 *         los campos inicializados.
	 */
	public Boolean emisionCompleta(final EmisionDocumentosBean documento) {
		if (documento != null) {
			if (documento.getCampos() != null) {
				for (final CampoDocumentoBean campo : documento.getCampos()) {
					if (campo.getValor() != null) {
						if ("".equals(campo.getValor().trim())
								|| !campo.getOriginal()
										.equals(campo.getValor())) {
							return false;
						}
					} else {
						return false;
					}
				}
				if(documento.getCampos().size() == 0){
					return false;
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * Mètodo para emitir un documento e imprimirlo.
	 *
	 * @param documento
	 *            a emitir e imprimir
	 */
	public void emitirDocumento(final EmisionDocumentosBean documento) {
		if (emisionDocumentosBackEnd.ejecutarTRN(documento,
				cuentaBean)) {
			for (CampoDocumentoBean campo : documento.getCampos()) {
				campo.setValorLen(campo.getLengthValor() + "");
				if (!modificaCampoDocumentoBackEnd
						.ejecutarTRN(documento, campo)) {
					campo.setValor("");
				} else {
					campo.setOriginal(campo.getValor());
				}
			}
			imprimeDocumento(documento);
		}
	}

	/**
	 * Mètodo para formalizar un documento.
	 *
	 * @param documento
	 *            a formalizar
	 */
	public void formalizaDocumento(final EmisionDocumentosBean documento) {
		documento.setEstado("F");
		if (!formalizaDocumentoBackEnd.ejecutarTRN(documento,
				cuentaBean.getNumeroCuenta())) {
			documento.setEstado("E");
		}
	}

	/**
	 * Mètodo para anular un documento.
	 *
	 * @param documento
	 *            a anular
	 */
	public void anulaDocumento(final EmisionDocumentosBean documento) {
		documento.setEstado("A");
		if (!anulaDocumentoBackEnd.ejecutarTRN(documento,
				cuentaBean.getNumeroCuenta())) {
			documento.setEstado("F");
		} else {
			consultaDocumentosAEmitir();
		}
	}

	/**
	 * Mètodo para generar un documento.
	 *
	 * @param documento
	 *            a generar
	 */
	public void generarDocumento(final EmisionDocumentosBean documento) {
		documento.setNumCopias(1);
		documento.setFichero("00000000");
		documento.setFechaFormalizacion(contextoUtils.getFechaContableActual());
		documento.setIdioma("01");
		try {
			if (emisionDocumentosBackEnd.ejecutarTRN(documento,	cuentaBean)) {
				muestraCamposDocumento(documento);
				documento.setEstado("E");
			}
		} catch (final NoControlableException | ControlableException ce) {
			addMessage(FacesMessage.SEVERITY_ERROR,"","No se puede generar el documento.<br/>"
							+ ce.getMessage());
		}
	}

	/**
	 * Mètodo para imprimir un documento
	 * @param documento a imprimir
	 */
	public void imprimeDocumento(final EmisionDocumentosBean documento) {
		if(existePlantilla(documento, true)){
			generadorDocumentosPdf.generarDocumento(documento, cuentaBean, clienteBean, tarifaSeleccionada);
		}else{
			final Map<String, VariablePlantillaUtils> variables = new HashMap<String, VariablePlantillaUtils>();
			camposGenericosDocumentoUtils.setCamposGenericos(documento, cuentaBean,
					clienteBean, variables);
			for (final CampoDocumentoBean campo : documento.getCampos()) {
				final VariablePlantillaUtils variable = new VariablePlantillaUtils(
						campo.getValor());
				variables.put("«" + campo.getDescripcionVariable() + "»", variable);
			}
			if(documento.getFormulario() != null){
				String plantilla = documento.getFormulario().toString();
				while (plantilla.length() < 7) {
					plantilla = "0" + plantilla;
				}
				try{
					wordUtils.generaWord("P" + plantilla + ".docx", variables);
				}catch(NoControlableException nc){
					throw new ControlableException(nc.getMensajeUsuario(),nc.getMensajeDetalle());
				}
			}
		}
	}

	public Boolean existePlantilla(final EmisionDocumentosBean documento){
		Boolean existe = false;
		if(existePlantilla(documento, true)){
			existe = true;
		}else{
			existe = existePlantilla(documento, false);
		}
		return existe;
	}

	public Boolean existePlantilla(final EmisionDocumentosBean documento, final Boolean jasper){
		String plantilla = documento.getFormulario().toString();
		while (plantilla.length() < 7) {
			plantilla = "0" + plantilla;
		}
		if(jasper){
			final String rutaJasper = ProveedorMensajeSpringUtils.getValorConfiguracion("ruta.plantillas")+contextoUtils.getEntidad()+
					ProveedorMensajeSpringUtils.getValorConfiguracion("ruta.plantillas.jasper");
			final File file = new File(rutaJasper +"P" + plantilla + ".jrxml");
			return file.exists();
		}else{
			final String rutaWord = ProveedorMensajeSpringUtils.getValorConfiguracion("ruta.plantillas")+contextoUtils.getEntidad()+
					ProveedorMensajeSpringUtils.getValorConfiguracion("ruta.plantillas.word");
			final File file = new File(rutaWord +"P" + plantilla + ".docx");
			return file.exists();
		}
	}
	
	public String constituirAcuerdoSat(){
		String retorno = "";
		try{
			retorno =  constituirAcuerdo(0);
		}catch(ControlableException e){
			this.messageError = e.getMensajeDetalle();
		}
		preparaCuenta();
		if(!this.cuentaBean.getEstado().equals(ConstantesFuncionales.ESTADO_CUENTA_ACTIVO)){
			if(this.cuentaBean.getEstado().equals(EstadosCuentaEnum.APROBADO.getEstado())){
				RequestContext.getCurrentInstance().execute("PF('dlgErrorConstituir').show()");
				RequestContext.getCurrentInstance().update("dlgErrorConstituir");
			}else{
				RequestContext.getCurrentInstance().execute("PF('dlgErrorAprobacion').show()");
				RequestContext.getCurrentInstance().update("dlgErrorAprobacion");
			}
			return "";
		}
		return retorno;
	}

	public String constituirAcuerdo(){
		return constituirAcuerdo(0);
	}

	@StoreStep
	public String constituirAcuerdo(Integer llamadas) {
		if(!altaSat && faltanRelaciones()){
			setFaltantes(true);
			return "";
		}else{
			if(llamadas == null){
				llamadas = 0;
			}
			if (estadosCuentaEnumUtils.verificaEstado(EstadosCuentaEnum.SOLICITADO, this.cuentaBean.getEstado()) ||
			        estadosCuentaEnumUtils.verificaEstado(EstadosCuentaEnum.PROPUESTO, this.cuentaBean.getEstado())) {
					this.cuentaBean.setEstado(aprobarCuentaBackEnd.ejecutarTRN(this.cuentaBean.getNumeroCuenta()));
			}
			if (estadosCuentaEnumUtils.verificaEstado(EstadosCuentaEnum.APROBADO, this.cuentaBean.getEstado())) {
				try{
					TimeUnit.SECONDS.sleep(1);
				}catch(final InterruptedException e){
				}
				try{
					this.cuentaBean.setEstado(constituirCuentaBackEnd.ejecutarTRN(
							this.cuentaBean.getNumeroCuenta(), this.cuentaBean.getFechaEstado(), altaSat,
							ConstantesFuncionales.isCredito(this.tarifaSeleccionada.getLinea(),
									this.tarifaSeleccionada.getGrupo())));
				}catch(final ControlableException ex){
					if(ex.getRtncod() == 18){
						if(llamadas < 2){
							return constituirAcuerdo(llamadas+1);
						}else{
							this.messageError = "No se ha podido constituir el acuerdo por favor inténtelo más tarde.";
						}
					}else{
						this.messageError = ex.getMensajeDetalle();
					}
	                return null;
				}
				//Ejecutamos la nivelacion del cliente y la cuenta
				if(null != this.nivelarCuenta && this.nivelarCuenta){
				    consultaNiveladoraCuentaBackend.ejecutarTRN(this.cuentaBean.getNumeroCuenta(),
				        this.clienteBean.getIdInterna());
				}
			}
			if(TarifasUtils.esPlazo(this.tarifaSeleccionada)){
			    return verificarAltaIPF();
			}
			if(altaSat ==null || !altaSat || destino ==null || !destino.equals(NavegacionEnum.FICHA_CUENTA)){
				RequestContext.getCurrentInstance().execute("PF('dlgFinalizacionAlta').show()");
			}
			return null;
		}
	}

	@StoreStep
	private String verificarAltaIPF() {
	    String navegacion = null;
	    if(altaIPFUtils.noSuperaImporteMinimoIPF(cuentaBean, this.tarifaSeleccionada.getProductosSimples())){
	        RequestContext.getCurrentInstance().execute("PF('dlgFaltaImporteMinimoIPF').show()");
	    }else{
	        navegacion = irAAltaIPF();
	    }
        return navegacion;
    }

	@StoreStep
	public String irAAltaIPF() {
        preparaCuenta();
        return altaIPFUtils.logicaRedireccionAltaIPF(cuentaBean, this.tarifaSeleccionada.getProductosSimples(), false);
    }

	@StoreStep
	public String irADepositoEnCuenta(){
        preparaCuenta();
	    return altaIPFUtils.logicaRedireccionRealizarDesposito(cuentaBean, this.tarifaSeleccionada.getProductosSimples(), false);
	}

	@StoreStep
    public String irAFichaCuenta() {
        String ruta = "";
        preparaCuenta();
        if (destino == null) {
            ruta = NavegacionEnum.FICHA_CUENTA.getRuta();
        } else {
        	if (destino.equals(NavegacionEnum.FICHA_CLIENTE)){
        		this.obtieneFlash().put(ParametrosFlashEnum.ID_INTERNA.getParamFlash(), this.clienteBean.getIdInterna());
        		this.obtieneFlash().put(ParametrosFlashEnum.TIPO_CLIENTE.getParamFlash(), this.clienteBean.getTipo());
        		ruta = destino.getRuta();
        	}else{
        		ruta = destino.getRuta();
                managedBeanStateRecover.finNavegacion(destinoController);
        	}

        }
        if(subflujo){
        	this.obtieneFlash().put(ParametrosFlashEnum.CUENTA_SUBFLUJO_ALTA.getParamFlash(), this.cuentaBean);
        }else{
        	this.obtieneFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(), this.cuentaBean);
        }
		return ruta;
	}

	public String cancelaSat(){
		if(this.dialogoUtils != null && this.dialogoUtils.tieneCambios()){
			setMostrarCancelar(true);
			return "";
		}else{
			preparaFlash();
			parametrosSubFlujo();
			return irAFichaCuenta();
		}
	}
	
	private Boolean faltanRelaciones(){
		Boolean faltantes = false;
		if(personasFaltantes()){
			faltantes = true;
		}
		if(cuentasFaltantes()){
			faltantes = true;
		}
		return faltantes;
	}

	private Boolean personasFaltantes(){
		Boolean faltantes = false;
		if(getRelacionesTarifa().isEmpty()){
			initRelacionesTarifa();
		}
		setMensajePersonaFaltantes("");
		for (final Map.Entry<String, Boolean> relacion : getRelacionesTarifa().entrySet()){
			final TipoRelacionPersonaCuenta tipo = TipoRelacionPersonaCuenta.codigoDe(relacion.getKey());
			if (tipo != null && relacion.getValue() && countTipo(tipo, false) == 0) {
				mensajePersonaFaltantes = mensajePersonaFaltantes + tipo.getNombre() + ", ";
				faltantes = true;
			}
		}
		if(mensajePersonaFaltantes.length() > 0){
			mensajePersonaFaltantes = mensajePersonaFaltantes.substring(0,mensajePersonaFaltantes.length()-2);
		}
		return faltantes;
	}

	private Boolean cuentasFaltantes(){
		Boolean faltantes = false;
		if(getRelacionesCuentasTarifa().isEmpty()){
			initRelacionesCuentaTarifa();
		}
		setMensajeCuentasFaltantes("");
		for (final TarifaRelacionBean tarifa : getRelacionesCuentasTarifa()) {
			if(tarifa.getRequerido()){
				if(contarTarifas(tarifa, false) == 0){
					mensajeCuentasFaltantes = mensajeCuentasFaltantes + tarifa.getNombreRelacion() + ",";
					faltantes = true;
				}
			}
		}
		if(mensajeCuentasFaltantes.length() > 0){
			mensajeCuentasFaltantes = mensajeCuentasFaltantes.substring(0,mensajeCuentasFaltantes.length()-2);
		}
		return faltantes;
	}

	private void preparaCuenta(){
		if(cuentaBean != null){
			consultaCuentaBackend.ejecutarTRN(cuentaBean);
			if(this.tarifaSeleccionada != null){
				cuentaBean.setTipoCuenta(this.tarifaSeleccionada.getNombre());
			}
			cuentaBean.setCentro(contextoUtils.getSucursal());
		}
	}

	private void actualizaDatosCuenta(){
		if(this.cuentaBean.getCodLinea() == null){
			if(this.tarifaSeleccionada != null){
				this.cuentaBean.setCodLinea(this.tarifaSeleccionada.getLinea());
			}
		}
		if(this.cuentaBean.getIdGrupoProducto() == null){
			if(this.tarifaSeleccionada != null){
				this.cuentaBean.setIdGrupoProducto(this.tarifaSeleccionada.getGrupo());
			}
		}
		if(this.cuentaBean.getIdProducto() == null){
			if(this.tarifaSeleccionada != null){
				this.cuentaBean.setIdProducto(this.tarifaSeleccionada.getProducto());
			}
		}
	}

	private void actualizarEstadoAltaCuenta(final Integer estadoIncial){
    	if(this.estadoAlta == estadoIncial){
    		this.estadoAlta++;
    	}
    }

	private void parametrosSubFlujo(){
		if(isAlta){
			this.obtieneFlash().put(ParametrosFlashEnum.CONTROLLER_DESTINO.getParamFlash(), destinoController);
			this.obtieneFlash().put(ParametrosFlashEnum.NAVEGACION_BUSQUEDA.getParamFlash(),destino);
			this.obtieneFlash().put(ParametrosFlashEnum.SUBFLUJO_ALTA_CUENTA.getParamFlash(),subflujo);
			if(subflujo){
				this.obtieneFlash().put(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash(),true);
			}
		}
	}

	public Date getFechaMinimaConstitucion(){
		final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try{
			return sdf.parse(ConstantesFuncionales.MIN_FECHA_INICIO);
		}catch(final ParseException e){
			return null;
		}
	}

	/**
	 * Se encarga de obtener el flash.
	 * @return Flash con los datos de la pagina
	 */
	public Flash obtieneFlash() {
		return FacesContext.getCurrentInstance().getExternalContext().getFlash();
	}

	/**
	 * Función para adicionar alertas globales en la vista
	 *
	 * @param severity Severidad de la alerta.
	 * @param title Titulo de la alerta.
	 * @param message Mensaje desplegado en la alerta.
	 */
	private void addMessage(final FacesMessage.Severity severity,
			final String title, final String message) {
		final FacesMessage facesMessage = new FacesMessage(severity, title,	message);
		FacesContext.getCurrentInstance().addMessage("relacionCuenta", facesMessage);
	}

	private void verificaRepresentante(){
		if(this.clienteBean.getTipoClienteEnum().equals(TipoCliente.PERSONA_FISICA)
				&& ConstantesFuncionales.isMenorEdad((consultaMinimaPersonaBackEnd.
						ejecutarTRN(this.clienteBean.getIdInterna()).getFechaNacimientoConstitucion()))){
			if( countTipo(TipoRelacionPersonaCuenta.REPRESENTANTE_LEGAL, true) == 0){
				crearNuevaRelacionPersona(TipoRelacionPersonaCuenta.REPRESENTANTE_LEGAL);
			}
		}
		if(this.clienteBean.getTipoClienteEnum().equals(TipoCliente.PERSONA_MORAL)){
			if( countTipo(TipoRelacionPersonaCuenta.APODERADO, true) ==0){
				crearNuevaRelacionPersona(TipoRelacionPersonaCuenta.APODERADO);
			}
		}
	}

	public Boolean verificaTipoPan(final PanBean pan){
		if(pan.getTipoTarjeta()!= null && pan.getTipoTarjeta().equals("PERSONALIZADA")){
			pan.setNumeroPan("");
			return false;
		}
		return true;
	}

	@Override
	protected Map<String, Object> getBeanList() {
	    final Map<String, Object> beanList = new HashMap<String, Object>();
        beanList.put(ClienteBean.class.getName(), clienteBean);
        beanList.put(CuentaBean.class.getName(), cuentaBean);
        beanList.put(TarifaBean.class.getName(), tarifaSeleccionada);
        beanList.put(Integer.class.getName(), estadoAlta);
        beanList.put(ParametrosFlashEnum.NIVELAR_CUENTA.getParamFlash(), nivelarCuenta);
		return beanList;
	}

	@Override
	protected void setBeanList(final Map<String, Object> beanList) {
	    if (null != beanList) {
            final Flash flash = obtieneFlash();
            final ClienteBean clienteB = (ClienteBean) beanList.get(ClienteBean.class.getName());
            if(null != clienteB){
                flash.put(ParametrosFlashEnum.CLIENTE.getParamFlash(), clienteB);
            }
            final CuentaBean cuentaB = (CuentaBean) beanList.get(CuentaBean.class.getName());
            if(null != cuentaB){
            	verificaEstadoCuenta(cuentaB);
                flash.put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(), cuentaB);
            }
            final TarifaBean tarifaB = (TarifaBean) beanList.get(TarifaBean.class.getName());
            if(null != tarifaB){
                flash.put(ParametrosFlashEnum.TARIFA_SELECCIONADA.getParamFlash(), tarifaB);
            }
            final Integer estadoB = (Integer) beanList.get(Integer.class.getName());
            if(null != estadoB){
                flash.put(ParametrosFlashEnum.NAVEGACION_ALTA_CUENTA.getParamFlash(), estadoB);
            }
            final Boolean nivelarAcc = (Boolean)beanList.get(ParametrosFlashEnum.NIVELAR_CUENTA.getParamFlash());
            if(null != nivelarAcc){
                flash.put(ParametrosFlashEnum.NIVELAR_CUENTA.getParamFlash(), nivelarAcc);
            }
        }
	}

	@Override
	protected String getNombreFlujo() {
		return "Alta de Cuenta: " + this.tarifaSeleccionada.getNombre() + " (" +
				this.cuentaBean.getNumeroCuenta() + ")";
	}
	
	public void verificaEstadoCuenta(CuentaBean cuentaB){
		CuentaBean cuenta = consultaCuentaBackend.ejecutarTRN(cuentaB.getNumeroCuenta());
		if(!EstadosCuentaEnum.SOLICITADO.getEstado().equals(cuenta.getEstado())){
			cuentaB.setEstado(cuenta.getEstado());
			obtieneFlash().put(ParametrosFlashEnum.MODIFICACION_CUENTA.getParamFlash(), true);
		}
	}

    /**
     * @return the nivelarCuenta
     */
    public Boolean getNivelarCuenta() {
        return nivelarCuenta;
    }

    /**
     * @param nivelarCuenta the nivelarCuenta to set
     */
    public void setNivelarCuenta(final Boolean nivelarCuenta) {
        this.nivelarCuenta = nivelarCuenta;
    }



}
