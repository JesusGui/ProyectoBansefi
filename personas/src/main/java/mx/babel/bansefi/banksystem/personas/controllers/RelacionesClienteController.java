package mx.babel.bansefi.banksystem.personas.controllers;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.bansefi.banksystem.base.backends.AltaRelacionClientePersonaBackEnd2;
import mx.babel.bansefi.banksystem.base.backends.ConsultaAccionistasFuncionariosBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaPersonasRelacionadasBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaReferenciasBancariasComercialesBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaTiposRelacionClienteBackEnd;
import mx.babel.bansefi.banksystem.base.backends.busquedas.BusquedaGrupoBackEnd;
import mx.babel.bansefi.banksystem.base.backends.busquedas.BusquedaPersonaFisicaBackEnd;
import mx.babel.bansefi.banksystem.base.beans.RelacionesClienteBean;
import mx.babel.bansefi.banksystem.base.beans.busqueda.GrupoBusquedaBean;
import mx.babel.bansefi.banksystem.base.beans.busqueda.PersonasClienteBusquedaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClienteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaFisicaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.GrupoRelacionadoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.PersonaMoralAccionistaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.PersonaMoralFuncionarioBean;
import mx.babel.bansefi.banksystem.base.beans.domain.PersonaMoralRBancariaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.PersonaMoralRComercialBean;
import mx.babel.bansefi.banksystem.base.beans.domain.PersonaRelacionadaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.TipoCliente;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.BusquedaEnum;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.enums.TipoRelacionPersonaFisicaEnum;
import mx.babel.bansefi.banksystem.base.enums.TipoRelacionPersonaMoralEnum;
import mx.babel.bansefi.banksystem.base.utils.BeanBackUpUtils;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;
import mx.babel.bansefi.banksystem.base.utils.SelectItemStringValueComparator;
import mx.babel.bansefi.banksystem.personas.backend.AltaPersonaMoralAccionistaBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.AltaPersonaMoralFuncionarioBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.AltaPersonaMoralRBancariaBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.AltaPersonaMoralRComercialBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.AltaRelacionClienteGestorBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.AltaRelacionClienteGrupoBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.BajaRelacionClienteAccionistaBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.BajaRelacionClienteFuncionarioBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.BajaRelacionClienteGestorBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.BajaRelacionClienteGrupoBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.BajaRelacionClientePersonaBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.BajaRelacionClienteRefBancariaBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.BajaRelacionClienteRefComercialBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.CatalogoRelacionesBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.ConsultaGruposRelacionadosBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.ModificaRelacionClienteAccionistaBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.ModificaRelacionClienteFuncionarioBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.ModificaRelacionClienteGestorBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.ModificaRelacionClientePersonaBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.ModificaRelacionClienteRefBancariaBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.ModificaRelacionClienteRefComercialBackEnd;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.component.panel.Panel;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Controlador para el flujo de relaciones de un cliente (consultas y ABM de
 * relaciones).
 * 
 * @author omar.marquez
 * 
 */
@ManagedBean
@ViewScoped
@Component
@Scope("view")
public class RelacionesClienteController implements Serializable {

	private static final long serialVersionUID = -5111690703265860575L;

	private static final Logger LOGGER = LogManager
			.getLogger(RelacionesClienteController.class);

	// Constantes para controlar el scroll vertical de la página.
	private static final String ID_CONTENEDOR_ACCIONISTA = "#frmRelacionesCliente\\\\:panelAccionistaAgregado";
	private static final String ID_CONTENEDOR_FUNCIONARIO = "#frmRelacionesCliente\\\\:panelFuncionarioAgregado";
	private static final String ID_CONTENEDOR_GRUPO = "#frmRelacionesCliente\\\\:panelGrupoAgregado";
	private static final String ID_CONTENEDOR_PERSONA = "#frmRelacionesCliente\\\\:panelPersonaAgregada";
	private static final String ID_CONTENEDOR_REFBAN = "#frmRelacionesCliente\\\\:panelRefBancariaAgregada";
	private static final String ID_CONTENEDOR_REFCOM = "#frmRelacionesCliente\\\\:panelRefComercialAgregada";

	// Constantes para el buscador y retorno de datos.
	private static final String CLAVE_LISTADO_PERSONA = "PE";
	private static final String CLAVE_LISTADO_GRUPO = "GR";

	// Constantes para la búsqueda de personas y grupos.
	private static final String PERSONA_NO_ENCONTRADA = "PERSONA NO ENCONTRADA";
	private static final String GRUPO_NO_ENCONTRADO = "GRUPO NO ENCONTRADO";
	private static final String ERROR_BUSQUEDA = "ERROR EN BUSQUEDA";

	private RelacionesClienteBean relacionesCliente;
	private List<Object> relacionesAgregadas;
	private List<Object> relacionesModificadas;
	private List<Object> relacionesEliminadas;
	private List<SelectItem> elementos;
	private List<CatalogoBean> tiposRelaciones;
	private int tipoRelacion;
	private String idElementoVistaActualizar;
	private String idContenedorScroll;
	private String mensajeOperacionFallida;
	private String mensajeRelDuplicado;

	private String claveRelacionInvalida;

	// Variables para el buscador y el retorno de datos.
	private PersonaRelacionadaBean personaABuscar;
	private GrupoRelacionadoBean grupoABuscar;
	private NavegacionEnum navegacionEnumOrigen;
	private Object controladorOrigen;
	private String tipoListado;
	private int posicionPersona;
	private int posicionGrupo;
	private int posicionGestor;

	// Parámetro de entrada.
	private ClienteBean clienteBean;

	@Autowired
	CatalogoUtils catalogoUtils;

	@Autowired
	ManagedBeanStateRecover managedBeanStateRecover;

	@Autowired
	BeanBackUpUtils beanBackupUtils;

	@Autowired
	BusquedaPersonaFisicaBackEnd busquedaPersonaFisicaBackEnd;

	@Autowired
	BusquedaGrupoBackEnd busquedaGrupoBackEnd;

	@Autowired
	ConsultaTiposRelacionClienteBackEnd consultaTiposRelacionClienteBackEnd;

	@Autowired
	ConsultaAccionistasFuncionariosBackEnd consultaAccionistasFuncionariosBackEnd;

	@Autowired
	ConsultaReferenciasBancariasComercialesBackEnd consultaReferenciasBancariasComercialesBackEnd;

	@Autowired
	ConsultaPersonasRelacionadasBackEnd consultaPersonasRelacionadasBackEnd;

	@Autowired
	ConsultaGruposRelacionadosBackEnd consultaGruposRelacionadosBackEnd;

	@Autowired
	AltaPersonaMoralAccionistaBackEnd altaPersonaMoralAccionistaBackEnd;

	@Autowired
	AltaPersonaMoralFuncionarioBackEnd altaPersonaMoralFuncionarioBackEnd;

	@Autowired
	AltaPersonaMoralRComercialBackEnd altaPersonaMoralRComercialBackEnd;

	@Autowired
	AltaPersonaMoralRBancariaBackEnd altaPersonaMoralRBancariaBackEnd;

	@Autowired
	AltaRelacionClientePersonaBackEnd2 altaRelacionClientePersonaBackEnd;
	// AltaRelacionClientePersonaBackEnd altaRelacionClientePersonaBackEnd;

	@Autowired
	AltaRelacionClienteGrupoBackEnd altaRelacionClienteGrupoBackEnd;

	@Autowired
	AltaRelacionClienteGestorBackEnd altaRelacionClienteGestorBackEnd;

	@Autowired
	BajaRelacionClienteAccionistaBackEnd bajaRelacionClienteAccionistaBackEnd;

	@Autowired
	BajaRelacionClienteFuncionarioBackEnd bajaRelacionClienteFuncionarioBackEnd;

	@Autowired
	BajaRelacionClienteRefComercialBackEnd bajaRelacionClienteRefComercialBackEnd;

	@Autowired
	BajaRelacionClienteRefBancariaBackEnd bajaRelacionClienteRefBancariaBackEnd;

	@Autowired
	BajaRelacionClientePersonaBackEnd bajaRelacionClientePersonaBackEnd;

	@Autowired
	BajaRelacionClienteGrupoBackEnd bajaRelacionClienteGrupoBackEnd;

	@Autowired
	BajaRelacionClienteGestorBackEnd bajaRelacionClienteGestorBackEnd;

	@Autowired
	ModificaRelacionClienteAccionistaBackEnd modificaRelacionClienteAccionistaBackEnd;

	@Autowired
	ModificaRelacionClienteFuncionarioBackEnd modificaRelacionClienteFuncionarioBackEnd;

	@Autowired
	ModificaRelacionClienteRefComercialBackEnd modificaRelacionClienteRefComercialBackEnd;

	@Autowired
	ModificaRelacionClienteRefBancariaBackEnd modificaRelacionClienteRefBancariaBackEnd;

	@Autowired
	ModificaRelacionClientePersonaBackEnd modificaRelacionClientePersonaBackEnd;

	@Autowired
	ModificaRelacionClienteGestorBackEnd modificaRelacionClienteGestorBackEnd;

	// Solución relaciones erroneas
	@Autowired
	CatalogoRelacionesBackEnd catalogoRelacionesBackEnd;

	/**
	 * Constructor.
	 */
	public RelacionesClienteController() {
		this.relacionesCliente = new RelacionesClienteBean();
		this.relacionesAgregadas = new ArrayList<>();
		this.relacionesModificadas = new ArrayList<>();
		this.relacionesEliminadas = new ArrayList<>();
		this.elementos = new ArrayList<>();
		this.tiposRelaciones = new ArrayList<>();
		this.tipoRelacion = 0;
		this.idElementoVistaActualizar = new String();
		this.idContenedorScroll = new String();
		this.personaABuscar = new PersonaRelacionadaBean();
		this.grupoABuscar = new GrupoRelacionadoBean();
		this.clienteBean = new ClienteBean();
	}

	@PostConstruct
	public void init() {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			if (this.obtenerFlash().get(
					ParametrosFlashEnum.ID_INTERNA.getParamFlash()) != null) {
				LOGGER.debug("Se recibe Id. Interna: "
						+ this.obtenerFlash().get(
								ParametrosFlashEnum.ID_INTERNA.getParamFlash()));
				this.managedBeanStateRecover.recuperaController(this);
				if (this.obtenerFlash().get(
						ParametrosFlashEnum.CLIENTE.getParamFlash()) != null) {
					LOGGER.debug("El llamado proviene del alta del cliente.");
					this.recuperarBeanDesdeAltaCliente();
				} else {
					LOGGER.debug("El llamado proviene del buscador.");
					this.recuperarBeanDesdeBuscador();
				}
			} else {
				LOGGER.debug("El llamado proviene de la ficha del cliente.");
				if (this.obtenerFlash().get(
						ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN
								.getParamFlash()) != null) {
					if ((Boolean) this.obtenerFlash().get(
							ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN
									.getParamFlash())) {
						this.navegacionEnumOrigen = this.managedBeanStateRecover
								.getDestino();
						this.controladorOrigen = this.managedBeanStateRecover
								.getController();
						this.initData();
					} else {
						this.managedBeanStateRecover.recuperaController(this);
					}
				} else {
					this.initData();
				}
			}
		}
	}

	/**
	 * Método privado que recupera un bean de datos proveniente desde el
	 * buscador.
	 */
	private void recuperarBeanDesdeAltaCliente() {
		ClientePersonaFisicaBean cliente = (ClientePersonaFisicaBean) this
				.obtenerFlash()
				.get(ParametrosFlashEnum.CLIENTE.getParamFlash());
		if (cliente != null && cliente.getIdInterna() != null
				&& cliente.getNumIdentificacion() != null
				&& cliente.getNombreCompleto() != null) {
			((PersonaRelacionadaBean) this.relacionesAgregadas
					.get(this.posicionPersona)).setIdInterna(cliente
					.getIdInterna());
			((PersonaRelacionadaBean) this.relacionesAgregadas
					.get(this.posicionPersona)).setIdExterna(cliente
					.getNumIdentificacion().trim());
			((PersonaRelacionadaBean) this.relacionesAgregadas
					.get(this.posicionPersona)).setNombre(cliente
					.getNombreCompleto().trim());
		}
	}

	/**
	 * Método privado que recupera un bean de datos proveniente desde el
	 * buscador.
	 */
	private void recuperarBeanDesdeBuscador() {
		if (this.tipoListado.equals(CLAVE_LISTADO_PERSONA)) {
			Integer idInterna = (Integer) this.obtenerFlash().get(
					ParametrosFlashEnum.ID_INTERNA.getParamFlash());
			if (idInterna.equals(this.clienteBean.getIdInterna())) {
				RequestContext.getCurrentInstance().execute(
						"PF('dlgMismoCliente').show()");
				((PersonaRelacionadaBean) this.relacionesAgregadas
						.get(this.posicionPersona)).setIdInterna(0);
				((PersonaRelacionadaBean) this.relacionesAgregadas
						.get(this.posicionPersona)).setIdExterna("");
				((PersonaRelacionadaBean) this.relacionesAgregadas
						.get(this.posicionPersona)).setNombre("");;
			} else {
				String idExterna = (String) this.obtenerFlash().get(
						ParametrosFlashEnum.IDENTIFICACION.getParamFlash());
				String nombre = (String) this.obtenerFlash().get(
						ParametrosFlashEnum.NOMBRE_COMPLETO_PERSONA
								.getParamFlash());
				if (idInterna != null && idExterna != null && nombre != null) {
					((PersonaRelacionadaBean) this.relacionesAgregadas
							.get(this.posicionPersona)).setIdInterna(idInterna);
					((PersonaRelacionadaBean) this.relacionesAgregadas
							.get(this.posicionPersona)).setIdExterna(idExterna
							.trim());
					((PersonaRelacionadaBean) this.relacionesAgregadas
							.get(this.posicionPersona)).setIdExterna(idExterna
							.trim());
					((PersonaRelacionadaBean) this.relacionesAgregadas
							.get(this.posicionPersona))
							.setNombre(nombre.trim());
				}
			}
		}
		if (this.tipoListado.equals(CLAVE_LISTADO_GRUPO)) {
			String tipoGrupo = (String) this.obtenerFlash().get(
					ParametrosFlashEnum.COD_TIPO_GRUPO.getParamFlash());
			String idExterna = (String) this.obtenerFlash().get(
					ParametrosFlashEnum.ID_INTERNA.getParamFlash());
			String nombre = (String) this.obtenerFlash().get(
					ParametrosFlashEnum.NOMBRE_GRUPO.getParamFlash());
			if (tipoGrupo != null && idExterna != null && nombre != null) {
				((GrupoRelacionadoBean) this.relacionesAgregadas
						.get(this.posicionGrupo))
						.setTipoGrupo(tipoGrupo.trim());
				((GrupoRelacionadoBean) this.relacionesAgregadas
						.get(this.posicionGrupo))
						.setIdExterna(idExterna.trim());
				((GrupoRelacionadoBean) this.relacionesAgregadas
						.get(this.posicionGrupo)).setNombre(nombre.trim());
			}
		}
	}

	/**
	 * Método privado que inicializa la carga de datos.
	 */
	private void initData() {
		if (this.obtenerFlash()
				.get(ParametrosFlashEnum.CLIENTE.getParamFlash()) != null) {
			this.clienteBean = (ClienteBean) this.obtenerFlash().get(
					ParametrosFlashEnum.CLIENTE.getParamFlash());
			if (this.obtenerFlash().get(
					ParametrosFlashEnum.IND_ALTA_REPRESENTANTE.getParamFlash()) != null) {
				this.precargarDatosRepresentante();
			}
		} else {
			throw new NoControlableException(
					"Error al intentar recuperar la información del cliente.",
					this.getClass().getName()
							+ ":"
							+ "Se requiere un cliente para acceder a sus relaciones.");
		}
		this.elementos = obtenerValoresTipoRelacion();
		this.consultarAccionistasFuncionariosRelacionados();
		this.consultarReferenciasBancariasComercialesRelacionadas();
		this.consultarPersonasRelacionadas();
		this.consultarGruposRelacionados();
		this.cargaCatalogo();
	}

	/**
	 * Método privado que realiza la carga automática de un representante. Este
	 * método se ejecuta luego de que el usuario haya pulsado el botón
	 * "Alta de Representante".
	 */
	private void precargarDatosRepresentante() {
		boolean indAltaRepresentante = Boolean.valueOf((String) this
				.obtenerFlash().get(
						ParametrosFlashEnum.IND_ALTA_REPRESENTANTE
								.getParamFlash()));
		if (indAltaRepresentante) {
			LOGGER.debug("El usuario hizo clic en el botón 'Alta de Representante'");
			this.idContenedorScroll = ID_CONTENEDOR_PERSONA
					+ this.obtenerListaPersonasAgregadas().size();
			PersonaRelacionadaBean persona = new PersonaRelacionadaBean();
			if (this.clienteBean.getTipoClienteEnum().equals(
					TipoCliente.PERSONA_MORAL)) {
				persona.setRelacionSeleccionada(this.catalogoUtils
						.getCatalogoBean(CatalogoEnum.TP_PERS_RL_PERS, "451"));
			} else {
				persona.setRelacionSeleccionada(this.catalogoUtils
						.getCatalogoBean(CatalogoEnum.TP_PERS_RL_PERS,
								ConstantesFuncionales.REPRESENTANTE_MENOR));
			}
			this.relacionesAgregadas.add(persona);
			RequestContext.getCurrentInstance().execute(
					"$('body').animate({scrollTop: $('" + idContenedorScroll
							+ "').offset().top}, 1000);");
		}
	}

	// INICIA DECLARACIÓN DE GETTERS Y SETTERS.

	/**
	 * Método que devuelve un objeto tipo RelacionesClienteBean con las
	 * relaciones que fueron consultadas.
	 * 
	 * @return relacionesCliente
	 */
	public RelacionesClienteBean getRelacionesCliente() {
		return relacionesCliente;
	}

	/**
	 * Método que establece un objeto tipo RelacionesClienteBean.
	 * 
	 * @param relacionesCliente
	 */
	public void setRelacionesCliente(RelacionesClienteBean relacionesCliente) {
		this.relacionesCliente = relacionesCliente;
	}

	/**
	 * Método que devuelve una lista con las relaciones agregadas.
	 * 
	 * @return relacionesAgregadas
	 */
	public List<Object> getRelacionesAgregadas() {
		return relacionesAgregadas;
	}

	/**
	 * Método que devuelve una lista con las relaciones agregadas.
	 * 
	 * @param relacionesAgregadas
	 */
	public void setRelacionesAgregadas(List<Object> relacionesAgregadas) {
		this.relacionesAgregadas = relacionesAgregadas;
	}

	/**
	 * Método que devuelve una lista con las relaciones modificadas.
	 * 
	 * @return relacionesModificadas
	 */
	public List<Object> getRelacionesModificadas() {
		return relacionesModificadas;
	}

	/**
	 * Método que establece una lista con las relaciones modificadas.
	 * 
	 * @param relacionesModificadas
	 */
	public void setRelacionesModificadas(List<Object> relacionesModificadas) {
		this.relacionesModificadas = relacionesModificadas;
	}

	/**
	 * Método que devuelve una lista con las relaciones eliminadas.
	 * 
	 * @return relacionesEliminadas.
	 */
	public List<Object> getRelacionesEliminadas() {
		return relacionesEliminadas;
	}

	/**
	 * Método que establece una lista con las relaciones eliminadas.
	 * 
	 * @param relacionesEliminadas
	 */
	public void setRelacionesEliminadas(List<Object> relacionesEliminadas) {
		this.relacionesEliminadas = relacionesEliminadas;
	}

	/**
	 * Método que devuelve una lista con las opciones que se cargarán en la
	 * combo: tipo de relación.
	 * 
	 * @return elementos (opciones a mostrar en tipo de relación)
	 */
	public List<SelectItem> getElementos() {
		return elementos;
	}

	/**
	 * Método que establece una lista con las opciones que se cargarán en la
	 * combo: tipo de relación.
	 * 
	 * @param elementos
	 */
	public void setElementos(List<SelectItem> elementos) {
		this.elementos = elementos;
	}

	/**
	 * Método que devuelve una lista con los tipos de relaciones que se cargarán
	 * en la combo: tipo de relación.
	 * 
	 * @return tiposRelaciones (opciones a mostrar en tipo de relación)
	 */
	public List<CatalogoBean> getTiposRelaciones() {
		return tiposRelaciones;
	}

	/**
	 * Método que establece una lista con los tipos de relaciones que se
	 * cargarán en la combo: tipo de relación.
	 * 
	 * @param tiposRelaciones
	 */

	public void setTiposRelaciones(List<CatalogoBean> tiposRelaciones) {
		this.tiposRelaciones = tiposRelaciones;
	}

	/**
	 * Método que devuelve un valor entero que representa el tipo de relación
	 * seleccionada. Los valores van desde el 1 al 7 para persona moral y del 1
	 * al 3 para persona física.
	 * 
	 * @return tipoRelacion
	 */
	public int getTipoRelacion() {
		return tipoRelacion;
	}

	/**
	 * Método que establece el valor del tipo de relación seleccionada.
	 * 
	 * @param tipoRelacion
	 */
	public void setTipoRelacion(int tipoRelacion) {
		this.tipoRelacion = tipoRelacion;
	}

	/**
	 * Método que devuelve el ID del elemento de la vista que será actualizado.
	 * Los estados pueden ser: activo, inactivo y modificado.
	 * 
	 * @return idElementoVistaActualizar
	 */
	public String getIdElementoVistaActualizar() {
		return idElementoVistaActualizar;
	}

	/**
	 * Método que establece el ID del elemento de la vista que será actualizado.
	 * 
	 * @param idElementoVistaActualizar
	 */
	public void setIdElementoVistaActualizar(String idElementoVistaActualizar) {
		this.idElementoVistaActualizar = idElementoVistaActualizar;
	}

	/**
	 * Método que devuelve un objeto tipo PersonaRelacionadaBean.
	 * 
	 * @return personaABuscar
	 */
	public PersonaRelacionadaBean getPersonaABuscar() {
		return personaABuscar;
	}

	/**
	 * Método que establece un objeto tipo PersonaRelacionadaBean.
	 * 
	 * @param personaABuscar
	 */
	public void setPersonaABuscar(PersonaRelacionadaBean personaABuscar) {
		this.personaABuscar = personaABuscar;
	}

	/**
	 * Método que devuelve un objeto tipo GrupoRelacionadoBean.
	 * 
	 * @return grupoABuscar
	 */
	public GrupoRelacionadoBean getGrupoABuscar() {
		return grupoABuscar;
	}

	/**
	 * Método que establece un objeto tipo GrupoRelacionadoBean.
	 * 
	 * @param grupoABuscar
	 */
	public void setGrupoABuscar(GrupoRelacionadoBean grupoABuscar) {
		this.grupoABuscar = grupoABuscar;
	}

	/**
	 * Método que devuelve un NavegacionEnum para volver al origen.
	 * 
	 * @return navegacionEnumOrigen
	 */
	public NavegacionEnum getNavegacionEnumOrigen() {
		return navegacionEnumOrigen;
	}

	/**
	 * Método que establece un NavegacionEnum para volver al origen.
	 * 
	 * @param navegacionEnumOrigen
	 */
	public void setNavegacionEnumOrigen(NavegacionEnum navegacionEnumOrigen) {
		this.navegacionEnumOrigen = navegacionEnumOrigen;
	}

	/**
	 * Método que devuelve un objeto con el controlador de origen.
	 * 
	 * @return controladorOrigen
	 */
	public Object getControladorOrigen() {
		return controladorOrigen;
	}

	/**
	 * Método que establece un objeto con el controlador de origen.
	 * 
	 * @param controladorOrigen
	 */
	public void setControladorOrigen(Object controladorOrigen) {
		this.controladorOrigen = controladorOrigen;
	}

	/**
	 * Método que devuelve el tipo de listado (PE = PERSONA, GR = GRUPO).
	 * 
	 * @return tipoListado
	 */
	public String getTipoListado() {
		return tipoListado;
	}

	/**
	 * Método que establece el tipo de listado.
	 * 
	 * @param tipoListado
	 */
	public void setTipoListado(String tipoListado) {
		this.tipoListado = tipoListado;
	}

	/**
	 * Método que devuelve la posición de la persona buscada.
	 * 
	 * @return posicionPersona
	 */
	public int getPosicionPersona() {
		return posicionPersona;
	}

	/**
	 * Método que establece la posición de la persona buscada.
	 * 
	 * @param posicionPersona
	 */
	public void setPosicionPersona(int posicionPersona) {
		this.posicionPersona = posicionPersona;
	}

	/**
	 * Método que devuelve la posición del grupo buscado.
	 * 
	 * @return posicionGrupo
	 */
	public int getPosicionGrupo() {
		return posicionGrupo;
	}

	/**
	 * Método que establece la posición del grupo buscado.
	 * 
	 * @param posicionGrupo
	 */
	public void setPosicionGrupo(int posicionGrupo) {
		this.posicionGrupo = posicionGrupo;
	}

	/**
	 * Método que devuelve la posición del gestor buscado.
	 * 
	 * @return posicionGestor
	 */
	public int getPosicionGestor() {
		return posicionGestor;
	}

	/**
	 * Método que establece la posición del gestor buscado.
	 * 
	 * @param posicionGestor
	 */
	public void setPosicionGestor(int posicionGestor) {
		this.posicionGestor = posicionGestor;
	}

	/**
	 * Método que devuelve el ID del contenedor que será utilizado para el
	 * scroll vertical.
	 * 
	 * @return idContenedorScroll
	 */
	public String getIdContenedorScroll() {
		return idContenedorScroll;
	}

	/**
	 * Método que establece el ID del panel que será utilizado para el scroll
	 * vertical.
	 * 
	 * @param idContenedorScroll
	 */
	public void setIdContenedorScroll(String idContenedorScroll) {
		this.idContenedorScroll = idContenedorScroll;
	}

	/**
	 * Método que devuelve una cadena con un mensaje alusive a la operación
	 * fallida.
	 * 
	 * @return mensajeOperacionFallida
	 */
	public String getMensajeOperacionFallida() {
		return mensajeOperacionFallida;
	}

	/**
	 * Método que establece una cadena con un mensaje alusive a la operación
	 * fallida.
	 * 
	 * @param mensajeOperacionFallida
	 */
	public void setMensajeOperacionFallida(String mensajeOperacionFallida) {
		this.mensajeOperacionFallida = mensajeOperacionFallida;
	}

	public String getMensajeRelDuplicado() {
		return mensajeRelDuplicado;
	}

	public void setMensajeRelDuplicado(String mensajeRelDuplicado) {
		this.mensajeRelDuplicado = mensajeRelDuplicado;
	}

	public String getClaveRelacionInvalida() {
		return claveRelacionInvalida;
	}

	public void setClaveRelacionInvalida(String claveRelacionInvalida) {
		this.claveRelacionInvalida = claveRelacionInvalida;
	}

	/**
	 * Método que devuelve un objeto tipo ClienteBean.
	 * 
	 * @return clienteBean
	 */
	public ClienteBean getClienteBean() {
		return clienteBean;
	}

	/**
	 * Método que establece un objeto tipo ClienteBean.
	 * 
	 * @param clienteBean
	 */
	public void setClienteBean(ClienteBean clienteBean) {
		this.clienteBean = clienteBean;
	}

	// INICIA DECLARACIÓN DE MÉTODOS PRINCIPALES.

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
	 * Método privado que obtiene los valores del enumerado correspondiente a
	 * una persona moral o a una física.
	 * 
	 * @return elementos
	 */
	private List<SelectItem> obtenerValoresTipoRelacion() {
		if (clienteBean.getTipoCliente().equals(
				TipoCliente.PERSONA_MORAL.getTipo())) {
			for (TipoRelacionPersonaMoralEnum valor : TipoRelacionPersonaMoralEnum
					.values()) {
				elementos.add(new SelectItem(valor.getPosicion(), valor
						.getNombre()));
			}
		} else {
			for (TipoRelacionPersonaFisicaEnum valor : TipoRelacionPersonaFisicaEnum
					.values()) {
				elementos.add(new SelectItem(valor.getPosicion(), valor
						.getNombre()));
			}
		}
		Collections.sort(elementos, new SelectItemStringValueComparator());
		return elementos;
	}

	/**
	 * Método que a partir de la fecha actual adiciona 1 día y devuelve la fecha
	 * fin mínima para una PersonaRelacionadaBean.
	 * 
	 * @return fechaActual + 1 día
	 */
	public String obtenerFechaFinMinima(Date fechaInicio) {
		DateFormat formatter = new SimpleDateFormat(
				ConstantesFuncionales.GENERAL_DATE_FORMATTER);
		if (fechaInicio != null) {
			return formatter.format(DateUtils.addDays(fechaInicio, 1));
		} else {
			return formatter.format(DateUtils.addDays(Calendar.getInstance()
					.getTime(), 1));
		}
	}

	/**
	 * Método que devuelve la descripción del tipo de grupo a partir de la clave
	 * de la fila.
	 * 
	 * @param claveFila
	 * @return descripcion larga del catálogo
	 */
	public String obtenerDescripcionTipoGrupo(String claveFila) {
		if (claveFila != null && !claveFila.trim().isEmpty()) {
			CatalogoBean catalogo = catalogoUtils.getCatalogoBean(
					CatalogoEnum.TP_GRP, claveFila);
			return catalogo.getDescripcionL();
		}
		return null;
	}

	/**
	 * Método que devuelve una lista de objetos tipo PersonaMoralAccionistaBean.
	 * 
	 * @return lista de accionistas a relacionar
	 */
	public List<PersonaMoralAccionistaBean> obtenerListaAccionistasAgregados() {
		List<PersonaMoralAccionistaBean> accionistas = new ArrayList<>();
		for (Object objeto : relacionesAgregadas) {
			if (objeto instanceof PersonaMoralAccionistaBean) {
				accionistas.add((PersonaMoralAccionistaBean) objeto);
			}
		}
		return accionistas;
	}

	/**
	 * Método que devuelve una lista de objetos tipo
	 * PersonaMoralFuncionarioBean.
	 * 
	 * @return lista de funcionarios a relacionar
	 */
	public List<PersonaMoralFuncionarioBean> obtenerListaFuncionariosAgregados() {
		List<PersonaMoralFuncionarioBean> funcionarios = new ArrayList<>();
		for (Object objeto : relacionesAgregadas) {
			if (objeto instanceof PersonaMoralFuncionarioBean) {
				funcionarios.add((PersonaMoralFuncionarioBean) objeto);
			}
		}
		return funcionarios;
	}

	/**
	 * Método que devuelve una lista de objetos tipo PersonaMoralRComercialBean.
	 * 
	 * @return lista de referencias comerciales a relacionar
	 */
	public List<PersonaMoralRComercialBean> obtenerListaRefComercialesAgregadas() {
		List<PersonaMoralRComercialBean> refComerciales = new ArrayList<>();
		for (Object objeto : relacionesAgregadas) {
			if (objeto instanceof PersonaMoralRComercialBean) {
				refComerciales.add((PersonaMoralRComercialBean) objeto);
			}
		}
		return refComerciales;
	}

	/**
	 * Método que devuelve una lista de objetos tipo PersonaMoralRBancariaBean.
	 * 
	 * @return lista de referencias bancarias a relacionar
	 */
	public List<PersonaMoralRBancariaBean> obtenerListaRefBancariasAgregadas() {
		List<PersonaMoralRBancariaBean> refBancarias = new ArrayList<>();
		for (Object objeto : relacionesAgregadas) {
			if (objeto instanceof PersonaMoralRBancariaBean) {
				refBancarias.add((PersonaMoralRBancariaBean) objeto);
			}
		}
		return refBancarias;
	}

	/**
	 * Método que devuelve una lista de objetos tipo PersonaRelacionadaBean.
	 * 
	 * @return lista de personas a relacionar
	 */
	public List<PersonaRelacionadaBean> obtenerListaPersonasAgregadas() {
		List<PersonaRelacionadaBean> personas = new ArrayList<>();
		for (Object objeto : relacionesAgregadas) {
			if (objeto instanceof PersonaRelacionadaBean) {
				personas.add((PersonaRelacionadaBean) objeto);
			}
		}
		return personas;
	}

	/**
	 * Método que devuelve una lista de objetos tipo GrupoRelacionadoBean.
	 * 
	 * @return lista de grupos a relacionar
	 */
	public List<GrupoRelacionadoBean> obtenerListaGruposAgregados() {
		List<GrupoRelacionadoBean> grupos = new ArrayList<>();
		for (Object objeto : relacionesAgregadas) {
			if (objeto instanceof GrupoRelacionadoBean) {
				grupos.add((GrupoRelacionadoBean) objeto);
			}
		}
		return grupos;
	}

	/**
	 * Método que ejecuta la TRN de consulta de accionistas y funcionarios
	 * relacionados a un cliente.
	 */
	public void consultarAccionistasFuncionariosRelacionados() {
		try {

			RelacionesClienteBean relacionesAccFunConsultadas = consultaAccionistasFuncionariosBackEnd
					.ejecutarTRN(clienteBean.getIdInterna());

			// Se incluye la lista de accionistas consultados
			relacionesCliente.getAccionistas().addAll(
					relacionesAccFunConsultadas.getAccionistas());
			beanBackupUtils
					.respaldaArray((ArrayList<PersonaMoralAccionistaBean>) relacionesCliente
							.getAccionistas());

			// Se incluye la lista de funcionarios consultados
			relacionesCliente.getFuncionarios().addAll(
					relacionesAccFunConsultadas.getFuncionarios());
			beanBackupUtils
					.respaldaArray((ArrayList<PersonaMoralFuncionarioBean>) relacionesCliente
							.getFuncionarios());

		} catch (NullPointerException | ControlableException
				| NoControlableException e) {
			LOGGER.error("Error al ejecutar la TRN de consulta de accionistas y funcionarios relacionados."
					+ this.getClass().getName() + ":" + e.getMessage());
		}
	}

	/**
	 * Método que ejecuta la TRN de consulta de referencias bancarias y
	 * comerciales relacionadas a un cliente.
	 */
	public void consultarReferenciasBancariasComercialesRelacionadas() {
		try {

			RelacionesClienteBean relacionesBancComerConsultadas = this.consultaReferenciasBancariasComercialesBackEnd
					.ejecutarTRN(clienteBean.getIdInterna());

			// Se incluye la lista de referencias Bancarias consultadas
			relacionesCliente.getReferenciasBancarias().addAll(
					relacionesBancComerConsultadas.getReferenciasBancarias());
			beanBackupUtils
					.respaldaArray((ArrayList<PersonaMoralRBancariaBean>) relacionesCliente
							.getReferenciasBancarias());

			// Se incluye la lista de referencias Bancarias consultadas
			relacionesCliente.getReferenciasComerciales().addAll(
					relacionesBancComerConsultadas.getReferenciasComerciales());
			beanBackupUtils
					.respaldaArray((ArrayList<PersonaMoralRComercialBean>) relacionesCliente
							.getReferenciasComerciales());

		} catch (NullPointerException | ControlableException
				| NoControlableException e) {
			LOGGER.error("Error al ejecutar la TRN de consulta de referencias comerciales y bancarias relacionadas."
					+ this.getClass().getName() + ":" + e.getMessage());
		}
	}

	/**
	 * Método que ejecuta la TRN de consulta de personas relacionadas.
	 */
	public void consultarPersonasRelacionadas() {
		try {
			relacionesCliente.setPersonas(new ArrayList<PersonaRelacionadaBean>());
			relacionesCliente.getPersonas().addAll(
					consultaPersonasRelacionadasBackEnd.ejecutarTRN(clienteBean
							.getIdInterna()));
			beanBackupUtils
					.respaldaArray((ArrayList<PersonaRelacionadaBean>) relacionesCliente
							.getPersonas());
		} catch (NullPointerException | ControlableException
				| NoControlableException e) {
			LOGGER.error("Error al ejecutar la TRN de consulta de personas relacionadas."
					+ this.getClass().getName() + ":" + e.getMessage());
		}
		if(relacionesAgregadas.size() > 1){
			for(Object objeto : relacionesAgregadas){
				if(objeto instanceof PersonaRelacionadaBean){
					PersonaRelacionadaBean persona = (PersonaRelacionadaBean) objeto;
					for(PersonaRelacionadaBean personaRel : relacionesCliente.getPersonas()){
						if(personaRel.getIdInterna().equals(persona.getIdInterna())){
							eliminarPersona(persona);
						}
					}
				}
			}
		}
	}

	/**
	 * Método que ejecuta la TRN de consulta de grupos relacionados.
	 */
	public void consultarGruposRelacionados() {
		try {
			relacionesCliente.getGrupos().addAll(
					consultaGruposRelacionadosBackEnd.ejecutarTRN(clienteBean
							.getIdInterna()));
		} catch (NullPointerException | ControlableException
				| NoControlableException e) {
			LOGGER.error("Error al ejecutar la TRN de consulta de grupos relacionados."
					+ this.getClass().getName() + ":" + e.getMessage());
		}
	}

	/**
	 * Método privado que permite recuperar los datos serializados en cada bean
	 * de datos dentro del listado.
	 * 
	 * @param objeto
	 */
	private void recuperarBeanDatos(Object objeto) {
		if (objeto != null) {
			beanBackupUtils.recuperaBean(objeto);
		}
	}

	/**
	 * Método que obtiene la ID interna y el nombre de la persona a partir de su
	 * ID externa. Sí el servicio devuelve más de 1 resultado, se redirige al
	 * usuario al buscador.
	 * 
	 * @param persona
	 * @param inputId
	 */
	public void buscarPersona(PersonaRelacionadaBean persona, String inputId) {
		if (persona.getIdExterna() != null
				&& !persona.getIdExterna().trim().isEmpty()) {
			UIComponent componente = FacesContext.getCurrentInstance()
					.getViewRoot().findComponent(inputId);
			if (componente != null) {
				((UIInput) componente).setValid(true);
				RequestContext.getCurrentInstance().update(inputId);
			}
			try {
				PersonasClienteBusquedaBean beanEntrada = new PersonasClienteBusquedaBean();
				PersonasClienteBusquedaBean beanSalida = new PersonasClienteBusquedaBean();
				beanEntrada.setNoIdentificador(persona.getIdExterna());
				List<Object> listaResultados = busquedaPersonaFisicaBackEnd
						.ejecutarWS(beanEntrada);
				if (listaResultados != null && listaResultados.size() > 0) {
					if (listaResultados.size() == 1) {
						beanSalida = (PersonasClienteBusquedaBean) listaResultados
								.get(0);
						if (beanSalida.getIdInterna().equals(
								this.clienteBean.getIdInterna())) {
							persona.setIdExterna("");
							RequestContext.getCurrentInstance().execute(
									"PF('dlgMismoCliente').show()");
							if (componente != null) {
								((UIInput) componente).setValid(true);
								RequestContext.getCurrentInstance().update(
										inputId);
							}
						} else {
							persona.setNombre(beanSalida.getNombreCompleto());
							persona.setIdInterna(beanSalida.getIdInterna());
							if (componente != null) {
								((UIInput) componente).setValid(true);
								RequestContext.getCurrentInstance().update(
										inputId);
							}
						}
					} else {
						this.personaABuscar = persona;
						RequestContext.getCurrentInstance().execute(
								"PF('dlgBusqueda').show()");
					}
				} else {
					persona.setNombre(PERSONA_NO_ENCONTRADA);
					if (componente != null) {
						((UIInput) componente).setValid(false);
						RequestContext.getCurrentInstance().update(inputId);
					}
				}
			} catch (ControlableException e) {
				persona.setNombre(PERSONA_NO_ENCONTRADA);
				if (componente != null) {
					((UIInput) componente).setValid(false);
					RequestContext.getCurrentInstance().update(inputId);
				}
			} catch (IndexOutOfBoundsException | NullPointerException
					| NoControlableException e) {
				persona.setNombre(ERROR_BUSQUEDA);
				if (componente != null) {
					((UIInput) componente).setValid(false);
					RequestContext.getCurrentInstance().update(inputId);
				}
			}
		} else {
			persona.setNombre(null);
		}
	}

	/**
	 * Método que obtiene el nombre del grupo a partir de su código de
	 * identificación y tipo.
	 * 
	 * @param grupo
	 * @param inputId
	 */
	public void buscarGrupo(GrupoRelacionadoBean grupo, String inputId) {
		if (grupo.getIdExterna() != null
				&& !grupo.getIdExterna().trim().isEmpty()) {
			UIComponent componente = FacesContext.getCurrentInstance()
					.getViewRoot().findComponent(inputId);
			if (componente != null) {
				((UIInput) componente).setValid(true);
				RequestContext.getCurrentInstance().update(inputId);
			}
			try {
				GrupoBusquedaBean beanEntrada = new GrupoBusquedaBean();
				GrupoBusquedaBean beanSalida = new GrupoBusquedaBean();
				beanEntrada.setIdInterna(grupo.getIdExterna().trim());
				beanEntrada.setTipoGrupo(grupo.getTipoGrupo());
				List<Object> listaResultados = busquedaGrupoBackEnd
						.ejecutarTRN(beanEntrada);
				if (listaResultados != null && listaResultados.size() > 0) {
					if (listaResultados.size() == 1) {
						beanSalida = (GrupoBusquedaBean) listaResultados.get(0);
						grupo.setIdExterna(beanSalida.getIdInterna().trim());
						grupo.setNombre(beanSalida.getNombre().trim());
						if (componente != null) {
							((UIInput) componente).setValid(true);
							RequestContext.getCurrentInstance().update(inputId);
						}
					} else {
						this.grupoABuscar = grupo;
						RequestContext.getCurrentInstance().execute(
								"PF('dlgBusquedaGrupo').show()");
					}
				} else {
					grupo.setNombre(GRUPO_NO_ENCONTRADO);
					if (componente != null) {
						((UIInput) componente).setValid(false);
						RequestContext.getCurrentInstance().update(inputId);
					}
				}
			} catch (ControlableException e) {
				grupo.setNombre(GRUPO_NO_ENCONTRADO);
				if (componente != null) {
					((UIInput) componente).setValid(false);
					RequestContext.getCurrentInstance().update(inputId);
				}
			} catch (IndexOutOfBoundsException | NullPointerException
					| NoControlableException e) {
				grupo.setNombre(ERROR_BUSQUEDA);
				if (componente != null) {
					((UIInput) componente).setValid(false);
					RequestContext.getCurrentInstance().update(inputId);
				}
			}
		} else {
			grupo.setNombre(null);
		}
	}

	/**
	 * Método encargado de limpiar los campos: identificación y nombre cada vez
	 * que el usuario seleccione un tipo de grupo.
	 * 
	 * @param grupo
	 */
	public void vaciarCamposGrupo(GrupoRelacionadoBean grupo) {
		grupo.setIdExterna(null);
		grupo.setNombre(null);
	}

	/**
	 * Método que permite insertar un registro a partir de la selección del
	 * usuario (lista con los tipos de relación).
	 */
	public void crearRelacion() {
		this.idContenedorScroll = new String();
		if (clienteBean.getTipoCliente().equals(
				(TipoCliente.PERSONA_MORAL.getTipo()))) {
			switch (tipoRelacion) {
			case 1:
				insertarAccionista();
				break;
			case 2:
				insertarFuncionario();
				break;
			case 3:
				insertarReferenciaComercial();
				break;
			case 4:
				insertarReferenciaBancaria();
				break;
			case 5:
				insertarPersona();
				break;
			case 6:
				insertarGrupo();
				break;
			}
		} else {
			switch (tipoRelacion) {
			case 1:
				insertarPersona();
				break;
			case 2:
				insertarGrupo();
				break;
			}
		}
		// Efectuamos el desplazamiento vertical según el elemento insertado.
		RequestContext.getCurrentInstance().execute(
				"$('body').animate({scrollTop: $('" + idContenedorScroll
						+ "').offset().top}, 1000);");
	}

	/**
	 * Método que permite insertar un objeto tipo PersonaMoralAccionistaBean.
	 */
	public void insertarAccionista() {
		idContenedorScroll = ID_CONTENEDOR_ACCIONISTA
				+ obtenerListaAccionistasAgregados().size();
		PersonaMoralAccionistaBean accionista = new PersonaMoralAccionistaBean();
		relacionesAgregadas.add(accionista);
	}

	/**
	 * Método que permite insertar un objeto tipo PersonaMoralFuncionarioBean.
	 */
	public void insertarFuncionario() {
		idContenedorScroll = ID_CONTENEDOR_FUNCIONARIO
				+ obtenerListaFuncionariosAgregados().size();
		PersonaMoralFuncionarioBean funcionario = new PersonaMoralFuncionarioBean();
		relacionesAgregadas.add(funcionario);
	}

	/**
	 * Método que permite insertar un objeto tipo PersonaMoralRComercialBean.
	 */
	public void insertarReferenciaComercial() {
		idContenedorScroll = ID_CONTENEDOR_REFCOM
				+ obtenerListaRefComercialesAgregadas().size();
		PersonaMoralRComercialBean referenciaComercial = new PersonaMoralRComercialBean();
		relacionesAgregadas.add(referenciaComercial);
	}

	/**
	 * Método que permite insertar un objeto tipo PersonaMoralRBancariaBean.
	 */
	public void insertarReferenciaBancaria() {
		idContenedorScroll = ID_CONTENEDOR_REFBAN
				+ obtenerListaRefBancariasAgregadas().size();
		PersonaMoralRBancariaBean referenciaBancaria = new PersonaMoralRBancariaBean();
		relacionesAgregadas.add(referenciaBancaria);
	}

	/**
	 * Método que permite inserta un objeto tipo PersonaRelacionadaBean.
	 */
	public void insertarPersona() {
		idContenedorScroll = ID_CONTENEDOR_PERSONA
				+ obtenerListaPersonasAgregadas().size();
		PersonaRelacionadaBean persona = new PersonaRelacionadaBean();
		relacionesAgregadas.add(persona);
	}

	/**
	 * Método que permite insertar un objeto tipo GrupoRelacionadoBean.
	 */
	public void insertarGrupo() {
		idContenedorScroll = ID_CONTENEDOR_GRUPO
				+ obtenerListaGruposAgregados().size();
		GrupoRelacionadoBean grupo = new GrupoRelacionadoBean();
		relacionesAgregadas.add(grupo);
	}

	/**
	 * Método que permite modificar un objeto tipo PersonaMoralAccionistaBean.
	 * 
	 * @param accionista
	 * @param idContenedor
	 */
	public void modificarAccionista(PersonaMoralAccionistaBean accionista,
			String idContenedor) {
		relacionesModificadas.add(accionista);
		accionista.setEstado(EstadoListadosEnum.MODIFICADO);
		aplicarEstiloModificado(idContenedor);
	}

	/**
	 * Método que permite modificar un objeto tipo PersonaMoralFuncionarioBean.
	 * 
	 * @param funcionario
	 * @param idContenedor
	 */
	public void modificarFuncionario(PersonaMoralFuncionarioBean funcionario,
			String idContenedor) {
		relacionesModificadas.add(funcionario);
		funcionario.setEstado(EstadoListadosEnum.MODIFICADO);
		aplicarEstiloModificado(idContenedor);
	}

	/**
	 * Método que permite modificar un objeto tipo PersonaMoralRComercialBean.
	 * 
	 * @param referenciaComercial
	 * @param idContenedor
	 */
	public void modificarReferenciaComercial(
			PersonaMoralRComercialBean referenciaComercial, String idContenedor) {
		relacionesModificadas.add(referenciaComercial);
		referenciaComercial.setEstado(EstadoListadosEnum.MODIFICADO);
		aplicarEstiloModificado(idContenedor);
	}

	/**
	 * Método que permite modificar un objeto tipo PersonaMoralRBancariaBean.
	 * 
	 * @param referenciaBancaria
	 * @param idContenedor
	 */
	public void modificarReferenciaBancaria(
			PersonaMoralRBancariaBean referenciaBancaria, String idContenedor) {
		relacionesModificadas.add(referenciaBancaria);
		referenciaBancaria.setEstado(EstadoListadosEnum.MODIFICADO);
		aplicarEstiloModificado(idContenedor);
	}

	/**
	 * Método que permite modificar un objeto tipo PersonaRelacionadaBean.
	 * 
	 * @param persona
	 * @param idContenedor
	 */
	public void modificarPersona(PersonaRelacionadaBean persona,
			String idContenedor) {
		consultarDetallePersona(persona);
		relacionesModificadas.add(persona);
		persona.setEstado(EstadoListadosEnum.MODIFICADO);
		aplicarEstiloModificado(idContenedor);
	}

	/**
	 * Método privado que ejecuta la TRN de consulta tipos de relación de un
	 * cliente para poder visualizar el detalle de una persona.
	 * 
	 * @param persona
	 */
	private void consultarDetallePersona(PersonaRelacionadaBean persona) {
		try {
			PersonaRelacionadaBean beanDatos = consultaTiposRelacionClienteBackEnd
					.ejecutarTRN(clienteBean.getIdInterna(), persona
							.getIdInterna(), persona.getRelacionSeleccionada()
							.getClaveFila());
			if (beanDatos != null) {

				persona.setObservaciones(beanDatos.getObservaciones());
				persona.setFechaInicio(beanDatos.getFechaInicio());
				persona.setFechaFin(beanDatos.getFechaFin());
			}
		} catch (NullPointerException | ControlableException
				| NoControlableException e) {
			LOGGER.error("Error al ejecutar la TRN de consulta de detalle de una relación. "
					+ this.getClass().getName() + ":" + e.getMessage());
		}
	}

	/**
	 * Método que devuelve a su estado original un accionista modificado.
	 * 
	 * @param accionista
	 * @param idContenedor
	 */
	public void restaurarAccionistaModificado(
			PersonaMoralAccionistaBean accionista, String idContenedor) {
		accionista.setEstado(EstadoListadosEnum.CONSULTADO);
		relacionesModificadas.remove(accionista);
		aplicarEstiloConsultado(idContenedor);
		recuperarBeanDatos(accionista);
	}

	/**
	 * Método que devuelve a su estado original un funcionario modificado.
	 * 
	 * @param funcionario
	 * @param idContenedor
	 */
	public void restaurarFuncionarioModificado(
			PersonaMoralFuncionarioBean funcionario, String idContenedor) {
		funcionario.setEstado(EstadoListadosEnum.CONSULTADO);
		relacionesModificadas.remove(funcionario);
		aplicarEstiloConsultado(idContenedor);
		recuperarBeanDatos(funcionario);
	}

	/**
	 * Método que devuelve a su estado original una referencia comercial
	 * modificada.
	 * 
	 * @param referenciaComercial
	 * @param idContenedor
	 */
	public void restaurarRefComercialModificada(
			PersonaMoralRComercialBean referenciaComercial, String idContenedor) {
		referenciaComercial.setEstado(EstadoListadosEnum.CONSULTADO);
		relacionesModificadas.remove(referenciaComercial);
		aplicarEstiloConsultado(idContenedor);
		recuperarBeanDatos(referenciaComercial);
	}

	/**
	 * Método que devuelve a su estado original una referencia bancaria
	 * modificada.
	 * 
	 * @param referenciaBancaria
	 * @param idContenedor
	 */
	public void restaurarRefBancariaModificada(
			PersonaMoralRBancariaBean referenciaBancaria, String idContenedor) {
		referenciaBancaria.setEstado(EstadoListadosEnum.CONSULTADO);
		relacionesModificadas.remove(referenciaBancaria);
		aplicarEstiloConsultado(idContenedor);
		recuperarBeanDatos(referenciaBancaria);
	}

	/**
	 * Método que devuelve a su estado original una persona modificada.
	 * 
	 * @param persona
	 * @param idContenedor
	 */
	public void restaurarPersonaModificada(PersonaRelacionadaBean persona,
			String idContenedor) {
		persona.setEstado(EstadoListadosEnum.CONSULTADO);
		relacionesModificadas.remove(persona);
		aplicarEstiloConsultado(idContenedor);
		recuperarBeanDatos(persona);
	}

	/**
	 * Método que permite eliminar un accionista consultado y aplicar un estilo
	 * al panel de la vista.
	 * 
	 * @param accionista
	 * @param idContenedor
	 */
	public void eliminarAccionista(PersonaMoralAccionistaBean accionista,
			String idContenedor) {
		relacionesEliminadas.add(accionista);
		accionista.setEstado(EstadoListadosEnum.ELIMINADO);
		aplicarEstiloEliminado(idContenedor);
	}

	/**
	 * Método que permite eliminar un accionista.
	 * 
	 * @param accionista
	 */
	public void eliminarAccionista(PersonaMoralAccionistaBean accionista) {
		relacionesAgregadas.remove(accionista);
	}

	/**
	 * Método que permite eliminar un funcionario consultado y aplicar un estilo
	 * al panel de la vista.
	 * 
	 * @param funcionario
	 * @param idContenedor
	 */
	public void eliminarFuncionario(PersonaMoralFuncionarioBean funcionario,
			String idContenedor) {
		relacionesEliminadas.add(funcionario);
		funcionario.setEstado(EstadoListadosEnum.ELIMINADO);
		aplicarEstiloEliminado(idContenedor);
	}

	/**
	 * Método que permite eliminar un funcionario.
	 * 
	 * @param funcionario
	 */
	public void eliminarFuncionario(PersonaMoralFuncionarioBean funcionario) {
		relacionesAgregadas.remove(funcionario);
	}

	/**
	 * Método que permite eliminar una referencia comercial consultada y aplicar
	 * un estilo al panel de la vista.
	 * 
	 * @param referenciaComercial
	 * @param idContenedor
	 */
	public void eliminarReferenciaComercial(
			PersonaMoralRComercialBean referenciaComercial, String idContenedor) {
		relacionesEliminadas.add(referenciaComercial);
		referenciaComercial.setEstado(EstadoListadosEnum.ELIMINADO);
		aplicarEstiloEliminado(idContenedor);
	}

	/**
	 * Método que permite eliminar una referencia comercial.
	 * 
	 * @param referenciaComercial
	 */
	public void eliminarReferenciaComerial(
			PersonaMoralRComercialBean referenciaComercial) {
		relacionesAgregadas.remove(referenciaComercial);
	}

	/**
	 * Método que permite eliminar una referencia bancaria consultada y aplicar
	 * un estilo al panel de la vista.
	 * 
	 * @param referenciaBancaria
	 * @param idContenedor
	 */
	public void eliminarReferenciaBancaria(
			PersonaMoralRBancariaBean referenciaBancaria, String idContenedor) {
		relacionesEliminadas.add(referenciaBancaria);
		referenciaBancaria.setEstado(EstadoListadosEnum.ELIMINADO);
		aplicarEstiloEliminado(idContenedor);
	}

	/**
	 * Método que permite eliminar una referencia bancaria.
	 * 
	 * @param referenciaBancaria
	 */
	public void eliminarReferenciaBancaria(
			PersonaMoralRBancariaBean referenciaBancaria) {
		relacionesAgregadas.remove(referenciaBancaria);
	}

	/**
	 * Método que permite eliminar una persona consultada y aplicar un estilo al
	 * panel de la vista.
	 * 
	 * @param persona
	 * @param idContenedor
	 */
	public void eliminarPersona(PersonaRelacionadaBean persona,
			String idContenedor) {
		relacionesEliminadas.add(persona);
		persona.setEstado(EstadoListadosEnum.ELIMINADO);
		aplicarEstiloEliminado(idContenedor);
	}

	/**
	 * Método que permite eliminar una persona.
	 * 
	 * @param persona
	 */
	public void eliminarPersona(PersonaRelacionadaBean persona) {
		relacionesAgregadas.remove(persona);
	}

	/**
	 * Método que permite eliminar un grupo consultado y aplicar un estilo al
	 * panel de la vista.
	 * 
	 * @param grupo
	 * @param idContenedor
	 */
	public void eliminarGrupo(GrupoRelacionadoBean grupo, String idContenedor) {
		relacionesEliminadas.add(grupo);
		grupo.setEstado(EstadoListadosEnum.ELIMINADO);
		aplicarEstiloEliminado(idContenedor);
	}

	/**
	 * Método que permite eliminar un grupo.
	 * 
	 * @param grupo
	 */
	public void eliminarGrupo(GrupoRelacionadoBean grupo) {
		relacionesAgregadas.remove(grupo);
	}

	/**
	 * Método que permite restaurar un accionista previamente eliminado.
	 * 
	 * @param accionista
	 * @param idContenedor
	 */
	public void restaurarAccionista(PersonaMoralAccionistaBean accionista,
			String idContenedor) {
		relacionesEliminadas.remove(accionista);
		accionista.setEstado(EstadoListadosEnum.CONSULTADO);
		aplicarEstiloConsultado(idContenedor);
	}

	/**
	 * Método que permite restaurar un funcionario previamente eliminado.
	 * 
	 * @param funcionario
	 * @param idContenedor
	 */
	public void restaurarFuncionario(PersonaMoralFuncionarioBean funcionario,
			String idContenedor) {
		relacionesEliminadas.remove(funcionario);
		funcionario.setEstado(EstadoListadosEnum.CONSULTADO);
		aplicarEstiloConsultado(idContenedor);
	}

	/**
	 * Método que permite restaurar una referencia comercial previamente
	 * eliminada.
	 * 
	 * @param referenciaComercial
	 * @param idContenedor
	 */
	public void restaurarReferenciaComercial(
			PersonaMoralRComercialBean referenciaComercial, String idContenedor) {
		relacionesEliminadas.remove(referenciaComercial);
		referenciaComercial.setEstado(EstadoListadosEnum.CONSULTADO);
		aplicarEstiloConsultado(idContenedor);
	}

	/**
	 * Método que permite restaurar una referencia bancaria previamente
	 * eliminada.
	 * 
	 * @param referenciaBancaria
	 * @param idContenedor
	 */
	public void restaurarReferenciaBancaria(
			PersonaMoralRBancariaBean referenciaBancaria, String idContenedor) {
		relacionesEliminadas.remove(referenciaBancaria);
		referenciaBancaria.setEstado(EstadoListadosEnum.CONSULTADO);
		aplicarEstiloConsultado(idContenedor);
	}

	/**
	 * Método que permite restaurar una persona previamente eliminada.
	 * 
	 * @param persona
	 * @param idContenedor
	 */
	public void restaurarPersona(PersonaRelacionadaBean persona,
			String idContenedor) {
		relacionesEliminadas.remove(persona);
		persona.setEstado(EstadoListadosEnum.CONSULTADO);
		aplicarEstiloConsultado(idContenedor);
	}

	/**
	 * Método que permite restaurar un grupo previamente eliminado.
	 * 
	 * @param grupo
	 * @param idContenedor
	 */
	public void restaurarGrupo(GrupoRelacionadoBean grupo, String idContenedor) {
		relacionesEliminadas.remove(grupo);
		grupo.setEstado(EstadoListadosEnum.CONSULTADO);
		aplicarEstiloConsultado(idContenedor);
	}

	/**
	 * Método que con base al idContenedor, permite actualizar un elemento único
	 * de la vista.
	 * 
	 * @param idContenedor
	 */
	private void aplicarEstiloConsultado(String idContenedor) {
		String bloques[] = idContenedor.split(":");
		idElementoVistaActualizar = bloques[0] + ":" + bloques[1];
		Panel panel = (Panel) FacesContext.getCurrentInstance().getViewRoot()
				.findComponent(idElementoVistaActualizar);
		panel.setStyleClass(EstadoListadosEnum.CONSULTADO.getStyleClass());
	}

	/**
	 * Método que con base al idContenedor, permite actualizar un elemento único
	 * de la vista.
	 * 
	 * @param idContenedor
	 */
	private void aplicarEstiloEliminado(String idContenedor) {
		String bloques[] = idContenedor.split(":");
		idElementoVistaActualizar = bloques[0] + ":" + bloques[1];
		Panel panel = (Panel) FacesContext.getCurrentInstance().getViewRoot()
				.findComponent(idElementoVistaActualizar);
		panel.setStyleClass(EstadoListadosEnum.ELIMINADO.getStyleClass());
	}

	/**
	 * Método que con base al idContenedor, permite actualizar un elemento único
	 * de la vista.
	 * 
	 * @param idContenedor
	 */
	private void aplicarEstiloModificado(String idContenedor) {
		String bloques[] = idContenedor.split(":");
		idElementoVistaActualizar = bloques[0] + ":" + bloques[1];
		Panel panel = (Panel) FacesContext.getCurrentInstance().getViewRoot()
				.findComponent(idElementoVistaActualizar);
		panel.setStyleClass(EstadoListadosEnum.MODIFICADO.getStyleClass());
	}

	/**
	 * Método que redirige al usuario a la ficha del cliente o en su defecto al
	 * inicio.
	 * 
	 * @return ruta del recurso a mostrar
	 */
	public String volver() {
		obtenerFlash().put(ParametrosFlashEnum.ID_INTERNA.getParamFlash(),
				this.clienteBean.getIdInterna());
		obtenerFlash().put(ParametrosFlashEnum.TIPO_CLIENTE.getParamFlash(),
				this.clienteBean.getTipo());
		String destino = NavegacionEnum.FICHA_CLIENTE.getRuta();
		if (this.navegacionEnumOrigen != null) {
			destino = navegacionEnumOrigen.getRuta();
		}
		return destino;
	}

	/**
	 * Método que refresca la vista para volver a ejecutar las consultas.
	 * 
	 * @return ruta del recurso a mostrar
	 */
	public String refrescarPagina() {
		obtenerFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(),
				this.clienteBean);
		if (this.navegacionEnumOrigen.equals(NavegacionEnum.ALTA_CUENTA1)) {
			managedBeanStateRecover.enviaControllerMap(controladorOrigen,
					navegacionEnumOrigen);
		} else {
			obtenerFlash()
					.put(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN
							.getParamFlash(),
							this);
			managedBeanStateRecover.enviaControllerMap(controladorOrigen,
					NavegacionEnum.FICHA_CLIENTE);
		}
		return NavegacionEnum.RELACIONES_CLIENTE.getRuta();
	}

	/**
	 * Método que redirige al usuario al buscador genérico enviando como
	 * parámetro la búsqueda de un cliente (sólo PF).
	 * 
	 * @return ruta del recurso a mostrar
	 */
	public String redirigirABusquedaPersona(PersonaRelacionadaBean persona) {
		PersonasClienteBusquedaBean beanDatosBusqueda = new PersonasClienteBusquedaBean();
		beanDatosBusqueda.setNoIdentificador(persona.getIdExterna());
		obtenerFlash().put(ParametrosFlashEnum.TIPO_BUSQUEDA.getParamFlash(),
				BusquedaEnum.PERSONA_CLIENTE.getBusquedaValor());
		obtenerFlash().put(
				ParametrosFlashEnum.BEAN_DATOS_BUSQUEDA.getParamFlash(),
				beanDatosBusqueda);
		tipoListado = CLAVE_LISTADO_PERSONA;
		posicionPersona = relacionesAgregadas.indexOf(persona);
		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.RELACIONES_CLIENTE);
		return NavegacionEnum.BUSQUEDA.getRuta();
	}

	/**
	 * Método que redirige al usuario al buscador genérico enviando como
	 * parámetro la búsqueda de un grupo.
	 * 
	 * @return ruta del recurso a mostrar
	 */
	public String redirigirABusquedaGrupo(GrupoRelacionadoBean grupo) {
		GrupoBusquedaBean beanDatosBusqueda = new GrupoBusquedaBean();
		beanDatosBusqueda.setIdInterna(grupo.getIdExterna());
		beanDatosBusqueda.setTipoGrupo(grupo.getTipoGrupo());
		obtenerFlash().put(ParametrosFlashEnum.TIPO_BUSQUEDA.getParamFlash(),
				BusquedaEnum.PERSONA_GRUPO.getBusquedaValor());
		obtenerFlash().put(
				ParametrosFlashEnum.BEAN_DATOS_BUSQUEDA.getParamFlash(),
				beanDatosBusqueda);
		tipoListado = CLAVE_LISTADO_GRUPO;
		posicionGrupo = relacionesAgregadas.indexOf(grupo);
		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.RELACIONES_CLIENTE);
		return NavegacionEnum.BUSQUEDA.getRuta();
	}

	/**
	 * Método que redirige al usuario al alta de personas.
	 * 
	 * @return ruta del recurso a mostrar
	 */
	public String redirigirAAltaPersona(PersonaRelacionadaBean persona) {
		posicionPersona = relacionesAgregadas.indexOf(persona);
		this.obtenerFlash()
				.put(ParametrosFlashEnum.SUBFLUJO_ALTA_CLIENTE.getParamFlash(),
						true);
		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.RELACIONES_CLIENTE);
		return NavegacionEnum.ALTA_CLIENTE1.getRuta();
	}

	/**
	 * Método que cancela la operación de relaciones del cliente. Este método
	 * simplemente realiza el llamado al método volver().
	 * 
	 * @return ruta del recurso a mostrar
	 */
	public String cancelar() {
		return volver();
	}

	/**
	 * Método que aplica las siguientes reglas de validación antes de ejecutar
	 * cualquier transacción:
	 * 
	 * 1) Si existen relaciones duplicadas muestra mensaje de relaciones
	 * duplicadas.
	 * 
	 * 2) Si algún cliente a relacionar no contiene datos muestra un mensaje que
	 * debe realizar la busqueda del mismo.
	 * 
	 * 3) Sí el porcentaje de accionistas no suma el 100%, muestra mensaje de
	 * error.
	 * 
	 * 4) Sí no existen relaciones agregadas, modificadas ni eliminadas, muestra
	 * diálogo indicando que no existen cambios a guardar.
	 * 
	 * 5) Sí se van a eliminar relaciones, muestra mensaje de confirmación.
	 * 
	 * 6) Sí no se eliminan relaciones pero sí se crean o modifican, ejecuta
	 * directamente el método guardar().
	 */
	public void verificarCambios() {
		if (verificaPersonasRelacionadas() == 0) {
			if (verificarPorcentajeParticipacion()) {
				if (relacionesAgregadas.size() == 0
						&& relacionesModificadas.size() == 0
						&& relacionesEliminadas.size() == 0) {
					RequestContext.getCurrentInstance().execute(
							"PF('dlgNotificacionSinCambios').show()");
				} else {
					if (relacionesEliminadas.size() > 0) {
						RequestContext.getCurrentInstance().execute(
								"PF('dlgConfirmarEliminacion').show()");
					} else {
						if (!verificaClientesDuplicados()) {
							if (relacionesAgregadas.size() > 0
								|| relacionesModificadas.size() > 0) {
								guardar();
							}
						}else{
							RequestContext.getCurrentInstance().execute("PF('dlgDuplicados').show()");
							RequestContext.getCurrentInstance().update("dlgDuplicados");
						}
					}
				}
			} else {
				RequestContext.getCurrentInstance().execute(
						"PF('dlgErrorPorcentaje').show()");
			}
		} else {
			RequestContext.getCurrentInstance().execute("PF('dlgFaltaDatos').show()");
		}
	}

	/**
	 * Método que valida las relaciones seleccionadas con las relaciones ya
	 * existentes del cliente, en caso de que no existan se validaran las
	 * relaciones en la lista de relaciones agregadas
	 * 
	 * @return isDuplicado 'true' si se encontraron duplicados, 'false' si no se
	 *         encontraron duplicados.
	 * */
	private Boolean verificaClientesDuplicados() {
		Boolean isDuplicado = false;
		if(relacionesCliente.getPersonas().size() >= 1){
			for (Object object : relacionesAgregadas) {
				if (object instanceof PersonaRelacionadaBean) {
					PersonaRelacionadaBean personaRelacionadaBean = (PersonaRelacionadaBean) object;
					for (PersonaRelacionadaBean persona : relacionesCliente.getPersonas()) {
						if (personaRelacionadaBean.getIdInterna().equals(persona.getIdInterna())) {
							if(personaRelacionadaBean.getRelacionSeleccionada().getClaveFila().equals(persona.getRelacionSeleccionada().getClaveFila())){
								isDuplicado = true;
								setMensajeRelDuplicado("Existen relaciones duplicadas con " + personaRelacionadaBean.getNombre());
							}
						}
					}
				}
			}
		}
		if (isDuplicado == false) {
			isDuplicado = validaDuplicadosMismaLista();
		}
		return isDuplicado;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Boolean validaDuplicadosMismaLista() {
		Boolean duplicado = false;
		List<Integer> lista1 = new ArrayList<Integer>();
		List<Integer> lista2 = new ArrayList<Integer>();
		List<CatalogoBean> lista3 = new ArrayList<CatalogoBean>();
		List<CatalogoBean> lista4 = new ArrayList<CatalogoBean>();
		List<PersonaRelacionadaBean> listaPersonas = new ArrayList<PersonaRelacionadaBean>();
		for (Object object : relacionesAgregadas) {
			if (object instanceof PersonaRelacionadaBean) {
				PersonaRelacionadaBean personaRelacionadaBean = (PersonaRelacionadaBean) object;
				lista1.add(personaRelacionadaBean.getIdInterna());
				lista2.add(personaRelacionadaBean.getIdInterna());
				lista3.add(personaRelacionadaBean.getRelacionSeleccionada());
				lista4.add(personaRelacionadaBean.getRelacionSeleccionada());
				listaPersonas.add(personaRelacionadaBean);
			}
		}

		HashSet hs1 = new HashSet();
		HashSet hs2 = new HashSet();
		hs1.addAll(lista1);
		hs2.addAll(lista3);
		if (hs1.size() != lista2.size()
				&& hs2.size() != lista4.size()) {
			duplicado = true;
			for(Integer idDuplicado : new ArrayList<Integer>(hs1)){
				for(PersonaRelacionadaBean persona : listaPersonas){
					if(idDuplicado.equals(persona.getIdInterna())){
						setMensajeRelDuplicado("No se pueden agregar relaciones nuevas. Existen relaciones duplicadas con la persona " + persona.getNombre());
						break;
					}
				}
			}
		}
		return duplicado;
	}

	/**
	 * Método que realiza una busqueda en las relaciones agregadas para validar
	 * que se haya seleccionado una persona a relacionar
	 * 
	 * @return noValidos Número de personas no validas.
	 * */
	private Integer verificaPersonasRelacionadas() {
		Integer noValidos = 0;
		for (Object object : relacionesAgregadas) {
			if (object instanceof PersonaRelacionadaBean) {
				PersonaRelacionadaBean personaRelacionadaBean = (PersonaRelacionadaBean) object;
				if (personaRelacionadaBean.getIdInterna() == null) {
					noValidos++;
				}
			}
		}
		return noValidos;
	}

	/**
	 * Método privado que devuelve un indicador booleano para determinar si el
	 * porcentaje de participación de todas las relaciones sumariza el 100%.
	 * 
	 * @return indicador booleano
	 */
	private boolean verificarPorcentajeParticipacion() {
		double sumatoria = 0.00;
		double sustraccion = 0.00;
		Map<Integer, Double> mapa = new HashMap<>();
		List<Object> lista = new ArrayList<>();
		lista.addAll(relacionesModificadas);
		lista.addAll(relacionesCliente.getAccionistas());
		lista.addAll(relacionesAgregadas);
		// Establecemos un mapa a partir de los accionistas relacionados.
		for (Object objeto : lista) {
			if (objeto instanceof PersonaMoralAccionistaBean) {
				PersonaMoralAccionistaBean accionista = (PersonaMoralAccionistaBean) objeto;
				mapa.put(accionista.hashCode(), accionista.getPorcentaje()
						.doubleValue());
			}
		}
		// Iteramos las relaciones eliminadas para obtener el % a restar.
		for (Object objeto : relacionesEliminadas) {
			if (objeto instanceof PersonaMoralAccionistaBean) {
				PersonaMoralAccionistaBean accionista = (PersonaMoralAccionistaBean) objeto;
				sustraccion += accionista.getPorcentaje().doubleValue();
			}
		}
		// Iteramos el mapa para obtener el % a sumar.
		for (Map.Entry<Integer, Double> objeto : mapa.entrySet()) {
			sumatoria += objeto.getValue();
		}
		if ((sumatoria - sustraccion) <= 100.00) {
			return true;
		} else {
			// De manera recursiva marcamos los campos de porcentaje.
			FacesContext.getCurrentInstance().validationFailed();
			List<UIComponent> componentes = FacesContext.getCurrentInstance()
					.getViewRoot().getChildren();
			setValidationFalseRecursively(componentes);
			return false;
		}
	}

	/**
	 * Método privado que recorre de manera RECURSIVA todo el UIViewRoot y
	 * establece la validación de los campos de porcentaje a falso.
	 * 
	 * @param componentes
	 */
	private void setValidationFalseRecursively(List<UIComponent> componentes) {
		if (componentes != null && !componentes.isEmpty()) {
			for (UIComponent componente : componentes) {
				if (componente.getClientId().contains("porcentajeAccionista")) {
					((UIInput) componente).setValid(false);
				}
				this.setValidationFalseRecursively(componente.getChildren());
			}
		}
	}

	/**
	 * Método principal que ejecuta TRN's de alta, baja y modificación de
	 * relaciones de un cliente.
	 */
	public void guardar() {
		try {
			if (!isRelacionInvalida()) {
				if (!isListaPersonasInvalida()) {
					if (!isListaGruposInvalida()) {
						eliminarRelaciones();
						guardarRelacionesModificadas();
						guardarRelacionesNuevas();
						RequestContext.getCurrentInstance().execute(
								"PF('dlgOperacionExitosa').show()");
					} else {
						RequestContext.getCurrentInstance().execute(
								"PF('dlgGrupoNoEncontrado').show()");
					}
				} else {
					RequestContext.getCurrentInstance().execute(
							"PF('dlgPersonaNoEncontrada').show()");
				}
			} else {
				RequestContext.getCurrentInstance().execute(
						"PF('dlgRelacionInvalida').show()");
			}
		} catch (ControlableException e) {
			mensajeOperacionFallida = e.getMensajeUsuario() + " "
					+ e.getMensajeDetalle();
			RequestContext.getCurrentInstance().execute(
					"PF('dlgOperacionFallidaSinRefresh').show()");
		}
	}

	/**
	 * Método privado que devuelve un indicador booleano para determinar sí el
	 * cliente es menor de edad y sí éste puede tener una relación 120 -
	 * REPRESENTANTE DE PERSONA asociada.
	 * 
	 * @return indicador booleano
	 */
	private boolean isRelacionInvalida() {
		for (Object objeto : relacionesAgregadas) {
			if (objeto instanceof PersonaRelacionadaBean) {
				PersonaRelacionadaBean persona = (PersonaRelacionadaBean) objeto;
				boolean condicion1 = persona.getRelacionSeleccionada()
						.getClaveFila()
						.equals(ConstantesFuncionales.REPRESENTANTE_DE);
				boolean condicion2 = clienteBean.getTipoCliente().equals(
						(TipoCliente.MENOR_EDAD_DISCAPAZ.getTipo()));
				boolean condicion3 = !clienteBean.getTipoCliente().equals(
						(TipoCliente.PERSONA_MORAL.getTipo()))
						&& ConstantesFuncionales.isMenorEdad(this.clienteBean
								.getFechaNacimiento());
				/*
				 * char opcion =
				 * persona.getRelacionSeleccionada().getContenido()
				 * .charAt(persona
				 * .getRelacionSeleccionada().getContenido().length() - 1);
				 */

				if (condicion1 && condicion2 && condicion3) {
					this.claveRelacionInvalida = "La relación 120 no es correcta para el representante legal. Deberá relacionar la 121.";
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Método privado que devuelve un indicador booleano para determinar sí la
	 * lista de personas contiene algún elemento inválido. Dicho de otra forma,
	 * sí la persona a relacionar NO FUE ENCONTRADA o sí ocurrió un ERROR EN LA
	 * BÚSQUEDA, este método devuelve true y en caso contrario false.
	 * 
	 * @return indicador booleano
	 */
	private boolean isListaPersonasInvalida() {
		for (Object objeto : relacionesAgregadas) {
			if (objeto instanceof PersonaRelacionadaBean) {
				PersonaRelacionadaBean persona = (PersonaRelacionadaBean) objeto;
				if (persona.getNombre().equalsIgnoreCase(PERSONA_NO_ENCONTRADA)
						|| persona.getNombre().equalsIgnoreCase(ERROR_BUSQUEDA)) {
					mensajeOperacionFallida = persona.getIdExterna();
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Método privado que devuelve un indicador booleano para determinar sí la
	 * lista de grupos contiene algún elemento inválido. Dicho de otra forma, sí
	 * el grupo a relacionar NO FUE ENCONTRADO o sí ocurrió un ERROR EN LA
	 * BÚSQUEDA, este método devuelve true y en caso contrario false.
	 * 
	 * @return indicador booleano
	 */
	private boolean isListaGruposInvalida() {
		for (Object objeto : relacionesAgregadas) {
			if (objeto instanceof GrupoRelacionadoBean) {
				GrupoRelacionadoBean grupo = (GrupoRelacionadoBean) objeto;
				if (grupo.getNombre().equalsIgnoreCase(GRUPO_NO_ENCONTRADO)
						|| grupo.getNombre().equalsIgnoreCase(ERROR_BUSQUEDA)) {
					mensajeOperacionFallida = grupo.getIdExterna();
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Método principal que ejecuta las transacciones correspondientes a la baja
	 * de relaciones de un cliente.
	 * 
	 * @throws NullPointerException
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	public void eliminarRelaciones() throws NullPointerException,
			ControlableException, NoControlableException {
		for (Object objeto : relacionesEliminadas) {
			if (objeto instanceof PersonaMoralAccionistaBean) {
				ejecutarBajaAccionista((PersonaMoralAccionistaBean) objeto);
			}
			if (objeto instanceof PersonaMoralFuncionarioBean) {
				ejecutarBajaFuncionario((PersonaMoralFuncionarioBean) objeto);
			}
			if (objeto instanceof PersonaMoralRComercialBean) {
				ejecutarBajaRefComercial((PersonaMoralRComercialBean) objeto);
			}
			if (objeto instanceof PersonaMoralRBancariaBean) {
				ejecutarBajaRefBancaria((PersonaMoralRBancariaBean) objeto);
			}
			if (objeto instanceof PersonaRelacionadaBean) {
				ejecutarBajaPersona((PersonaRelacionadaBean) objeto);
			}
			if (objeto instanceof GrupoRelacionadoBean) {
				ejecutarBajaGrupo((GrupoRelacionadoBean) objeto);
			}
		}
	}

	/**
	 * Método privado que invoca al backend de baja de relaciones tipo
	 * cliente-accionista.
	 * 
	 * @param accionista
	 * @throws NullPointerException
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	private void ejecutarBajaAccionista(PersonaMoralAccionistaBean accionista)
			throws NullPointerException, ControlableException,
			NoControlableException {
		bajaRelacionClienteAccionistaBackEnd.ejecutarTRN(
				clienteBean.getIdInterna(), accionista.getIdInterno());
	}

	/**
	 * Método privado que invoca al backend de baja de relaciones tipo
	 * cliente-funcionario.
	 * 
	 * @param funcionario
	 * @throws NullPointerException
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	private void ejecutarBajaFuncionario(PersonaMoralFuncionarioBean funcionario)
			throws NullPointerException, ControlableException,
			NoControlableException {
		bajaRelacionClienteFuncionarioBackEnd.ejecutarTRN(
				clienteBean.getIdInterna(), funcionario.getIdInterno());
	}

	/**
	 * Método privado que invoca al backend de baja de relaciones tipo
	 * cliente-referencia comercial.
	 * 
	 * @param referenciaComercial
	 * @throws NullPointerException
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	private void ejecutarBajaRefComercial(
			PersonaMoralRComercialBean referenciaComercial)
			throws NullPointerException, ControlableException,
			NoControlableException {
		bajaRelacionClienteRefComercialBackEnd.ejecutarTRN(
				clienteBean.getIdInterna(), referenciaComercial.getIdInterno());
	}

	/**
	 * Método privado que invoca al backend de baja de relaciones tipo
	 * cliente-referencia bancaria.
	 * 
	 * @param referenciaBancaria
	 * @throws NullPointerException
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	private void ejecutarBajaRefBancaria(
			PersonaMoralRBancariaBean referenciaBancaria)
			throws NullPointerException, ControlableException,
			NoControlableException {
		bajaRelacionClienteRefBancariaBackEnd.ejecutarTRN(
				clienteBean.getIdInterna(), referenciaBancaria.getIdInterno());
	}

	/**
	 * Método privado que invoca al backend de baja de relaciones tipo
	 * cliente-persona.
	 * 
	 * @param persona
	 * @throws NullPointerException
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	private void ejecutarBajaPersona(PersonaRelacionadaBean persona)
			throws NullPointerException, ControlableException,
			NoControlableException {
		bajaRelacionClientePersonaBackEnd.ejecutarTRN(
				clienteBean.getIdInterna(), persona.getIdInterna(),
				persona.getRelacionSeleccionada());
	}

	/**
	 * Método privado que invoca al backend de baja de relaciones tipo
	 * cliente-grupo.
	 * 
	 * @param grupo
	 * @throws NullPointerException
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	private void ejecutarBajaGrupo(GrupoRelacionadoBean grupo)
			throws NullPointerException, ControlableException,
			NoControlableException {
		bajaRelacionClienteGrupoBackEnd.ejecutarTRN(clienteBean.getIdInterna(),
				grupo.getIdExterna(), grupo.getTipoGrupo(),
				grupo.getMotivoBaja());
	}

	/**
	 * Método principal que ejecuta las transacciones correspondientes a la
	 * modificación de relaciones de un cliente.
	 * 
	 * @throws NullPointerException
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	public void guardarRelacionesModificadas() throws NullPointerException,
			ControlableException, NoControlableException {
		for (Object objeto : relacionesModificadas) {
			if (objeto instanceof PersonaMoralAccionistaBean) {
				ejecutarModificacionAccionista((PersonaMoralAccionistaBean) objeto);
			}
			if (objeto instanceof PersonaMoralFuncionarioBean) {
				ejecutarModificacionFuncionario((PersonaMoralFuncionarioBean) objeto);
			}
			if (objeto instanceof PersonaMoralRComercialBean) {
				ejecutarModificacionRefComercial((PersonaMoralRComercialBean) objeto);
			}
			if (objeto instanceof PersonaMoralRBancariaBean) {
				ejecutarModificacionRefBancaria((PersonaMoralRBancariaBean) objeto);
			}
			if (objeto instanceof PersonaRelacionadaBean) {
				ejecutarModificacionPersona((PersonaRelacionadaBean) objeto);
			}
		}
	}

	/**
	 * Método privado que invoca al backend de modificación de relaciones tipo
	 * cliente-accionista.
	 * 
	 * @param accionista
	 * @throws NullPointerException
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	private void ejecutarModificacionAccionista(
			PersonaMoralAccionistaBean accionista) throws NullPointerException,
			ControlableException, NoControlableException {
		modificaRelacionClienteAccionistaBackEnd.ejecutarTRN(
				clienteBean.getIdInterna(), accionista);
	}

	/**
	 * Método privado que invoca al backend de modificación de relaciones tipo
	 * cliente-funcionario.
	 * 
	 * @param funcionario
	 * @throws NullPointerException
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	private void ejecutarModificacionFuncionario(
			PersonaMoralFuncionarioBean funcionario)
			throws NullPointerException, ControlableException,
			NoControlableException {
		modificaRelacionClienteFuncionarioBackEnd.ejecutarTRN(
				clienteBean.getIdInterna(), funcionario);
	}

	/**
	 * Método privado que invoca al backend de modificación de relaciones tipo
	 * cliente-referencia comercial.
	 * 
	 * @param referenciaComercial
	 * @throws NullPointerException
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	private void ejecutarModificacionRefComercial(
			PersonaMoralRComercialBean referenciaComercial)
			throws NullPointerException, ControlableException,
			NoControlableException {
		modificaRelacionClienteRefComercialBackEnd.ejecutarTRN(
				clienteBean.getIdInterna(), referenciaComercial);
	}

	/**
	 * Método privado que invoca al backend de modificación de relaciones tipo
	 * cliente-referencia bancaria.
	 * 
	 * @param referenciaBancaria
	 * @throws NullPointerException
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	private void ejecutarModificacionRefBancaria(
			PersonaMoralRBancariaBean referenciaBancaria)
			throws NullPointerException, ControlableException,
			NoControlableException {
		modificaRelacionClienteRefBancariaBackEnd.ejecutarTRN(
				clienteBean.getIdInterna(), referenciaBancaria);
	}

	/**
	 * Método privado que invoca al backend de modificación de relaciones tipo
	 * cliente-persona.
	 * 
	 * @param persona
	 * @throws NullPointerException
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	private void ejecutarModificacionPersona(PersonaRelacionadaBean persona)
			throws NullPointerException, ControlableException,
			NoControlableException {
		modificaRelacionClientePersonaBackEnd.ejecutarTRN(
				clienteBean.getIdInterna(), persona.getIdInterna(),
				persona.getRelacionSeleccionada(), persona.getObservaciones(),
				persona.getFechaInicio(), persona.getFechaFin());
	}

	/**
	 * Método principal que ejecuta las transacciones correspondientes a la alta
	 * de relaciones de un cliente.
	 * 
	 * @throws NullPointerException
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	public void guardarRelacionesNuevas() throws NullPointerException,
			ControlableException, NoControlableException {
		for (Object objeto : relacionesAgregadas) {
			if (objeto instanceof PersonaMoralAccionistaBean) {
				ejecutarAltaAccionista((PersonaMoralAccionistaBean) objeto);
			}
			if (objeto instanceof PersonaMoralFuncionarioBean) {
				ejecutarAltaFuncionario((PersonaMoralFuncionarioBean) objeto);
			}
			if (objeto instanceof PersonaMoralRComercialBean) {
				ejecutarAltaRefComercial((PersonaMoralRComercialBean) objeto);
			}
			if (objeto instanceof PersonaMoralRBancariaBean) {
				ejecutarAltaRefBancaria((PersonaMoralRBancariaBean) objeto);
			}
			if (objeto instanceof PersonaRelacionadaBean) {
				ejecutarAltaPersona((PersonaRelacionadaBean) objeto);
			}
			if (objeto instanceof GrupoRelacionadoBean) {
				ejecutarAltaGrupo((GrupoRelacionadoBean) objeto);
			}
		}
	}

	/**
	 * Método privado que invoca al backend de alta de relaciones tipo
	 * cliente-accionista.
	 * 
	 * @param accionista
	 * @throws NullPointerException
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	private void ejecutarAltaAccionista(PersonaMoralAccionistaBean accionista)
			throws NullPointerException, ControlableException,
			NoControlableException {
		altaPersonaMoralAccionistaBackEnd.ejecutarTRN(accionista,
				clienteBean.getIdInterna());
	}

	/**
	 * Método privado que invoca al backend de alta de relaciones tipo
	 * cliente-funcionario.
	 * 
	 * @param funcionario
	 * @throws NullPointerException
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	private void ejecutarAltaFuncionario(PersonaMoralFuncionarioBean funcionario)
			throws NullPointerException, ControlableException,
			NoControlableException {
		altaPersonaMoralFuncionarioBackEnd.ejecutarTRN(funcionario,
				clienteBean.getIdInterna());
	}

	/**
	 * Método privado que invoca al backend de alta de relaciones tipo
	 * cliente-referencia comercial.
	 * 
	 * @param referenciaComercial
	 * @throws NullPointerException
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	private void ejecutarAltaRefComercial(
			PersonaMoralRComercialBean referenciaComercial)
			throws NullPointerException, ControlableException,
			NoControlableException {
		altaPersonaMoralRComercialBackEnd.ejecutarTRN(referenciaComercial,
				clienteBean.getIdInterna());
	}

	/**
	 * Método privado que invoca al backend de alta de relaciones tipo
	 * cliente-referencia bancaria.
	 * 
	 * @param referenciaBancaria
	 * @throws NullPointerException
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	private void ejecutarAltaRefBancaria(
			PersonaMoralRBancariaBean referenciaBancaria)
			throws NullPointerException, ControlableException,
			NoControlableException {
		altaPersonaMoralRBancariaBackEnd.ejecutarTRN(referenciaBancaria,
				clienteBean.getIdInterna());
	}

	/**
	 * Método privado que invoca al backend de alta de relaciones tipo
	 * cliente-persona 2.
	 * 
	 * @param persona
	 * @throws NullPointerException
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	/*
	 * private void ejecutarAltaPersona(PersonaRelacionadaBean persona) throws
	 * NullPointerException, ControlableException, NoControlableException {
	 * altaRelacionClientePersonaBackEnd.ejecutarTRN(clienteBean
	 * .getIdInterna(), persona.getIdInterna(), persona
	 * .getRelacionSeleccionada(), persona.getObservaciones(),
	 * persona.getFechaInicio(), persona.getFechaFin()); }
	 */
	private void ejecutarAltaPersona(PersonaRelacionadaBean persona)
			throws NullPointerException, ControlableException,
			NoControlableException {
		altaRelacionClientePersonaBackEnd.ejecutarTRN(clienteBean, persona);
	}

	/**
	 * Método privado que invoca al backend de alta de relaciones tipo
	 * cliente-grupo.
	 * 
	 * @param grupo
	 * @throws NullPointerException
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	private void ejecutarAltaGrupo(GrupoRelacionadoBean grupo)
			throws NullPointerException, ControlableException,
			NoControlableException {
		altaRelacionClienteGrupoBackEnd.ejecutarTRN(clienteBean.getIdInterna(),
				grupo.getIdExterna(), grupo.getTipoGrupo());
	}

	public String getCurrentDate() {
		return new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance()
				.getTime());
	}

	/**
	 * Método que consulta los servicios de catalogo y cataloge en base al tipo
	 * de persona
	 * 
	 * */
	public void cargaCatalogo() {
		if (this.clienteBean.getTipo().getCodPe().equals("F")
				|| this.clienteBean.getTipo().getCodPe().equals("J")) {
			final List<CatalogoBean> listaRelaciones = catalogoRelacionesBackEnd
					.ejecutaBusquedaCatalogo(this.clienteBean.getTipo()
							.getCodPe());
			this.tiposRelaciones = listaRelaciones;
		}
	}

	/**
	 * Método que completa la frase que el usuario haya ingresado en el
	 * componente de autocompletar
	 * 
	 * @param query
	 *            Palabra ingresada por el usuario
	 * @return filtro Lista con los filtros que se encontraron
	 * */
	public List<CatalogoBean> completarCatalogo(String query) {
		List<CatalogoBean> filtro = new ArrayList<CatalogoBean>();
		for (int i = 0; i < this.tiposRelaciones.size(); i++) {
			CatalogoBean catalogo = this.tiposRelaciones.get(i);
			if (catalogo.getDescripcionL().toUpperCase()
					.contains(query.toUpperCase())) {
				filtro.add(catalogo);
			}
		}
		return filtro;
	}

	public Converter getConversor() {
		return new Converter() {
			@Override
			public Object getAsObject(final FacesContext fc,
					final UIComponent uic, final String value) {
				if (value != null && value.trim().length() > 0) {
					return getSelectedValue(value);

				} else {
					return null;
				}
			}

			@Override
			public String getAsString(final FacesContext fc,
					final UIComponent uic, final Object object) {
				if (object != null) {
					return ((CatalogoBean) object).getClaveFila();
				} else {
					return null;
				}
			}
		};
	}

	private CatalogoBean getSelectedValue(final String submittedItemValue) {
		final List<CatalogoBean> catalogoDataList = this.tiposRelaciones;
		if (catalogoDataList != null) {
			for (final CatalogoBean bean : catalogoDataList) {
				if (StringUtils.endsWithIgnoreCase(bean.getClaveFila(),
						submittedItemValue)) {
					return bean;
				}
			}
		}
		return null;
	}
}