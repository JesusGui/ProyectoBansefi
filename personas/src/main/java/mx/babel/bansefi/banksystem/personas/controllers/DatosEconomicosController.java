package mx.babel.bansefi.banksystem.personas.controllers;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.bansefi.banksystem.base.beans.domain.ClienteBean;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.DialogoListadoEnum;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.BeanBackUpUtils;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.personas.utils.DialogoPersonasListadoUtils;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;
import mx.babel.bansefi.banksystem.personas.backend.AltaActividadProfesionalBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.AltaOrigenIngresosBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.BajaActividadProfesionalBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.BajaOrigenIngresosBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.ConsultaDetalleDatoProfesionalBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.ConsultaListaDatosProfesionalesBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.ConsultaOrigenesIngresosBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.ModificacionActividadProfesionalBackEnd;
import mx.babel.bansefi.banksystem.personas.beans.ActividadProfesionalBean;
import mx.babel.bansefi.banksystem.personas.beans.OrigenIngresosBean;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Clase controladora para la ventana de Datos Económicos.
 * 
 * @author alejandro.pineda
 * 
 */
@ManagedBean
@ViewScoped
@Component
@Scope("view")
public class DatosEconomicosController implements Serializable {

	public static final long serialVersionUID = 1L;

	private List<String> listaOrigenesSeleccionado;

	private List<ActividadProfesionalBean> listaActividadesProfesionales;

	private List<OrigenIngresosBean> listaOrigenIngresos;

	private List<String> origenesSeleccionados;

	private ClienteBean cliente;

	private String nombreCliente;

	private String codActivProf;

	private boolean datosAgregados;

	private boolean datosEliminados;

	private boolean datosGuardados;

	private int idInterno;

	private int eliminadosDatosProf;

	private int numeroDatos;

	private int numModif;

	/**
	 * Enum para definir a donde se redireccionará al seleccionar un objeto de
	 * la tabla de resultados.
	 */
	private NavegacionEnum destino;

	private Object destinoController;

	private DialogoPersonasListadoUtils dialogoUtils;

	private DialogoListadoEnum dialogoGuardado;

	private DialogoListadoEnum mensajeEliminados;

	@Autowired
	private ManagedBeanStateRecover managedBeanStateRecover;

	@Autowired
	private AltaActividadProfesionalBackEnd altaActividadProfesionalBackEnd;

	@Autowired
	private AltaOrigenIngresosBackEnd altaOrigenIngresosBackEnd;

	@Autowired
	private BajaActividadProfesionalBackEnd bajaActividadProfesionalBackEnd;

	@Autowired
	private BajaOrigenIngresosBackEnd bajaOrigenIngresosBackEnd;

	@Autowired
	private ModificacionActividadProfesionalBackEnd modificacionActividadProfesionalBackEnd;

	@Autowired
	private ConsultaOrigenesIngresosBackEnd consultaOrigenesIngresosBackEnd;

	@Autowired
	private ConsultaListaDatosProfesionalesBackEnd consultaListaDatosProfesionalesBackEnd;

	@Autowired
	private ConsultaDetalleDatoProfesionalBackEnd consultaDetalleDatoProfesionalBackEnd;

	@Autowired
	private CatalogoUtils catalogos;

	@Autowired
	private BeanBackUpUtils beanBackupUtils;

	public DatosEconomicosController() {
		this.listaOrigenesSeleccionado = new ArrayList<>();
	}

	/**
	 * Método para inicializar la ventana de Datos Económicos.
	 */
	@PostConstruct
	public void init() {
		refreshEnums();
		this.cliente = (ClienteBean) this.obtieneFlash().get(
				ParametrosFlashEnum.CLIENTE.getParamFlash());

		if (this.obtieneFlash().get(
				ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash()) != null) {
			if ((Boolean) this.obtieneFlash()
					.get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN
							.getParamFlash())) {
				destino = managedBeanStateRecover.getDestino();
				destinoController = managedBeanStateRecover.getController();
			}
		}

		if (this.obtieneFlash().get(
				ParametrosFlashEnum.ID_EMPLEADO.getParamFlash()) != null
				&& this.obtieneFlash().get(
						ParametrosFlashEnum.NOMBRE_CLIENTE.getParamFlash()) != null) {
			this.idInterno = (int) this.obtieneFlash().get(
					ParametrosFlashEnum.ID_EMPLEADO.getParamFlash());
			this.nombreCliente = (String) this.obtieneFlash().get(
					ParametrosFlashEnum.NOMBRE_CLIENTE.getParamFlash());
			try {
				this.listaOrigenIngresos = this.consultaOrigenesIngresos();
				this.listaActividadesProfesionales = this
						.consultaListaDatosProfesionales();

				if (this.listaOrigenIngresos != null) {
					this.origenesSeleccionados = new ArrayList<>();
					for (OrigenIngresosBean origenConsultado : this.listaOrigenIngresos) {
						this.origenesSeleccionados.add(origenConsultado
								.getCodOrigenIngresos());
						CatalogoBean catalogo = catalogos.getCatalogoBean(
								CatalogoEnum.TP_ORGN_INGR_INDV,
								origenConsultado.getCodOrigenIngresos());
						listaOrigenesSeleccionado.add(origenConsultado
								.getCodOrigenIngresos()
								+ " - "
								+ catalogo.getDescripcionL());
					}
				} else {
					this.listaOrigenIngresos = new ArrayList<>();
					this.origenesSeleccionados = new ArrayList<>();
				}

				if (this.listaActividadesProfesionales != null) {
					for (ActividadProfesionalBean datoProfesional : this.listaActividadesProfesionales) {
						if (datoProfesional.getNumActProfIn() != null
								|| datoProfesional.getNumActProfIn() != 0) {
							CatalogoBean catalogo = catalogos.getCatalogoBean(
									CatalogoEnum.TP_CNO_INDV,
									datoProfesional.getCodActividadProf());
							datoProfesional.setDescripcionActividad(catalogo
									.getDescripcionL());
							datoProfesional.setConsultaDetalle(false);
							datoProfesional
									.setEstado(EstadoListadosEnum.CONSULTADO);
						}
					}
					beanBackupUtils
							.respaldaArray((ArrayList<ActividadProfesionalBean>) this.listaActividadesProfesionales);
					this.numeroDatos = this.listaActividadesProfesionales
							.size();

				} else {
					this.listaActividadesProfesionales = new ArrayList<>();
					this.numeroDatos = 0;
				}

				this.eliminadosDatosProf = 0;
				this.datosAgregados = false;
				this.datosEliminados = false;
				this.datosGuardados = false;
			} catch (ControlableException c) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Datos Económicos",
						"Se ha producido un error al consultar datos.\n"
								+ c.getMensajeDetalle());
				RequestContext.getCurrentInstance()
						.showMessageInDialog(message);
			}

		} else {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Datos Económicos",
					"Se ha producido un error al obtener datos de la ficha cliente.");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
		}

	}

	private void refreshEnums() {
		DialogoListadoEnum.CONFIRMA_ELIMINAR.setMostrar(false);
		DialogoListadoEnum.ALERTA.setMostrar(false);
		DialogoListadoEnum.ALTA.setMostrar(false);
		DialogoListadoEnum.ELIMINAR.setMostrar(false);
		DialogoListadoEnum.ERROR.setMostrar(false);
		DialogoListadoEnum.MODIFICACION.setMostrar(false);
		DialogoListadoEnum.SIN_CAMBIOS.setMostrar(false);
		DialogoListadoEnum.CONFIRMA_ELIMINAR.setMensaje("");
		DialogoListadoEnum.ALERTA.setMensaje("");
		DialogoListadoEnum.ALTA.setMensaje("");
		DialogoListadoEnum.ELIMINAR.setMensaje("");
		DialogoListadoEnum.ERROR.setMensaje("");
		DialogoListadoEnum.MODIFICACION.setMensaje("");
		DialogoListadoEnum.SIN_CAMBIOS.setMensaje("");
	}

	public List<String> getListaOrigenesSeleccionado() {
		return listaOrigenesSeleccionado;
	}

	public void setListaOrigenesSeleccionado(
			List<String> listaOrigenesSeleccionado) {
		this.listaOrigenesSeleccionado = listaOrigenesSeleccionado;
	}

	public List<ActividadProfesionalBean> getListaActividadesProfesionales() {
		return listaActividadesProfesionales;
	}

	public void setListaActividadesProfesionales(
			List<ActividadProfesionalBean> listaActividadesProfesionales) {
		this.listaActividadesProfesionales = listaActividadesProfesionales;
	}

	public List<OrigenIngresosBean> getListaOrigenIngresos() {
		return listaOrigenIngresos;
	}

	public void setListaOrigenIngresos(
			List<OrigenIngresosBean> listaOrigenIngresos) {
		this.listaOrigenIngresos = listaOrigenIngresos;
	}

	public List<String> getOrigenesSeleccionados() {
		return origenesSeleccionados;
	}

	public void setOrigenesSeleccionados(List<String> origenesSeleccionados) {
		this.origenesSeleccionados = origenesSeleccionados;
	}

	public ClienteBean getCliente() {
		return cliente;
	}

	public void setCliente(ClienteBean cliente) {
		this.cliente = cliente;
	}

	public int getIdInterno() {
		return idInterno;
	}

	public void setIdInterno(int idInterno) {
		this.idInterno = idInterno;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getCodActivProf() {
		return codActivProf;
	}

	public void setCodActivProf(String codActivProf) {
		this.codActivProf = codActivProf;
	}

	public int getEliminadosDatosProf() {
		return eliminadosDatosProf;
	}

	public void setEliminadosDatosProf(int eliminadosDatosProf) {
		this.eliminadosDatosProf = eliminadosDatosProf;
	}

	public DialogoPersonasListadoUtils getDialogoUtils() {
		return dialogoUtils;
	}

	public void setDialogoUtils(DialogoPersonasListadoUtils dialogoUtils) {
		this.dialogoUtils = dialogoUtils;
	}

	public DialogoListadoEnum getDialogoGuardado() {
		return dialogoGuardado;
	}

	public void setDialogoGuardado(DialogoListadoEnum dialogoGuardado) {
		this.dialogoGuardado = dialogoGuardado;
	}

	public DialogoListadoEnum getMensajeEliminados() {
		return mensajeEliminados;
	}

	public void setMensajeEliminados(DialogoListadoEnum mensajeEliminados) {
		this.mensajeEliminados = mensajeEliminados;
	}

	/**
	 * Obtiene la lista de los valores seleccionados en el menu de origenes de
	 * ingresos.
	 * 
	 * @return
	 */
	public void obtenerOrigenesSeleccionados() {
		listaOrigenesSeleccionado = new ArrayList<>();
		for (String origen : this.origenesSeleccionados) {
			CatalogoBean catalogo = catalogos.getCatalogoBean(
					CatalogoEnum.TP_ORGN_INGR_INDV, origen);
			listaOrigenesSeleccionado.add(origen + " - "
					+ catalogo.getDescripcionL());
		}
	}

	/**
	 * Método para insertar una Actividad Seleccionada.
	 */
	public void insertarActividadProfesional() {
		if (!("").equals(codActivProf)) {
			ActividadProfesionalBean actividad = new ActividadProfesionalBean();
			actividad.setCodActividadProf(codActivProf);
			actividad.setDescripcionActividad(this
					.getNombreActividadSeleccionada());
			actividad.setEstado(EstadoListadosEnum.NUEVO);
			//En TCB no presenta ningún radioButton seleccionado
			/*actividad.setDuracion("1");
			actividad.setContinuidad("1");
			actividad.setJornada("1");
			actividad.setDedicacion("1");*/
			this.listaActividadesProfesionales.add(actividad);
		}
	}

	/**
	 * Método para obtener el nombre de la actividad seleccionada en el combo.
	 * 
	 * @return String con el nombre de la actividad.
	 */
	public String getNombreActividadSeleccionada() {
		CatalogoBean catalogo = catalogos.getCatalogoBean(
				CatalogoEnum.TP_CNO_INDV, codActivProf);
		return catalogo.getDescripcionL();
	}

	/**
	 * Método que se ejecuta al presionar el botón de eliminar una Actividad.
	 * 
	 * @param actividad
	 *            Bean de Actividad profesional a restaurar.
	 */
	public void accionEliminar(ActividadProfesionalBean actividad) {
		if (actividad.getEstado() == EstadoListadosEnum.NUEVO) {
			this.listaActividadesProfesionales.remove(actividad);
		} else {
			actividad.setEstado(EstadoListadosEnum.ELIMINADO);
			this.eliminadosDatosProf++;
		}
	}

	/**
	 * Método que se ejecuta al presionar el botón de restaurar.
	 * 
	 * @param actividad
	 *            Bean de Actividad profesional a restaurar.
	 */
	public void accionRestaurar(ActividadProfesionalBean actividad) {
		actividad.setEstado(EstadoListadosEnum.CONSULTADO);
		this.eliminadosDatosProf--;
	}

	/**
	 * Método que permite recuperar los datos serializados en cada bean de datos
	 * dentro del listado.
	 * 
	 * @param objeto
	 */
	public void recuperarBeanDatos(Object objeto) {
		if (objeto != null) {
			beanBackupUtils.recuperaBean(objeto);
		}
	}

	/**
	 * Método que devuelve a su estado original un accionista modificado.
	 * 
	 * @param actividad
	 */
	public void restaurarModificado(ActividadProfesionalBean actividad) {
		actividad.setEstado(EstadoListadosEnum.CONSULTADO);
		recuperarBeanDatos(actividad);
		this.numModif--;
	}

	/**
	 * Método para ejecutar el servicio de alta de un origen de ingresos.
	 * 
	 * @param origenIngresosBean
	 *            Un origen de ingresos.
	 * @return int Código de retorno del servicio.
	 * @throws ControlableException
	 *             Excepcion controlable que lance el servicio.
	 * @throws NoControlableException
	 *             Excepcion no controlable que lance el servicio.
	 */
	private int altaOrigenIngreso(OrigenIngresosBean origenIngresosBean)
			throws ControlableException, NoControlableException {
		origenIngresosBean.setIdInterno(idInterno);
		return this.altaOrigenIngresosBackEnd.ejecutarTRN(origenIngresosBean);
	}

	/**
	 * Método para ejecutar el servicio de alta de actividad profesional.
	 * 
	 * @param actividadProfesionalBean
	 *            Una actividad profesional.
	 * @return int Código de retorno del servicio.
	 * @throws ControlableException
	 *             Excepcion controlable que lance el servicio.
	 * @throws NoControlableException
	 *             Excepcion no controlable que lance el servicio.
	 */
	private int altaActividadProfesional(
			ActividadProfesionalBean actividadProfesionalBean)
			throws ControlableException, NoControlableException {
		actividadProfesionalBean.setIdInterno(idInterno);
		return this.altaActividadProfesionalBackEnd
				.ejecutarTRN(actividadProfesionalBean);
	}

	/**
	 * Método para ejecutar el servicio de alta de un origen de ingresos.
	 * 
	 * @param origenIngresosBean
	 *            Un origen de ingresos.
	 * @return int Código de retorno del servicio.
	 * @throws ControlableException
	 *             Excepcion controlable que lance el servicio.
	 * @throws NoControlableException
	 *             Excepcion no controlable que lance el servicio.
	 */
	private int bajaOrigenIngreso(OrigenIngresosBean origenIngresosBean)
			throws ControlableException, NoControlableException {
		origenIngresosBean.setIdInterno(idInterno);
		return this.bajaOrigenIngresosBackEnd.ejecutarTRN(origenIngresosBean);
	}

	/**
	 * Método para ejecutar el servicio de alta de actividad profesional.
	 * 
	 * @param actividadProfesionalBean
	 *            Una actividad profesional.
	 * @return int Código de retorno del servicio.
	 * @throws ControlableException
	 *             Excepcion controlable que lance el servicio.
	 * @throws NoControlableException
	 *             Excepcion no controlable que lance el servicio.
	 */
	private int bajaActividadProfesional(
			ActividadProfesionalBean actividadProfesionalBean)
			throws ControlableException, NoControlableException {
		actividadProfesionalBean.setIdInterno(idInterno);
		return this.bajaActividadProfesionalBackEnd
				.ejecutarTRN(actividadProfesionalBean);
	}

	/**
	 * Método para ejecutar el servicio de alta de actividad profesional.
	 * 
	 * @param actividadProfesionalBean
	 *            Una actividad profesional.
	 * @return int Código de retorno del servicio.
	 * @throws ControlableException
	 *             Excepcion controlable que lance el servicio.
	 * @throws NoControlableException
	 *             Excepcion no controlable que lance el servicio.
	 */
	private int modificacionActividadProfesional(
			ActividadProfesionalBean actividadProfesionalBean)
			throws ControlableException, NoControlableException {
		actividadProfesionalBean.setIdInterno(idInterno);
		return this.modificacionActividadProfesionalBackEnd
				.ejecutarTRN(actividadProfesionalBean);
	}

	/**
	 * Método para ejecutar el servicio de consulta de origenes de ingresos.
	 * 
	 * @return Lista de Origenes de Ingresos.
	 * @throws ControlableException
	 *             Excepcion controlable que lance el servicio.
	 * @throws NoControlableException
	 *             Excepcion no controlable que lance el servicio.
	 */
	private List<OrigenIngresosBean> consultaOrigenesIngresos()
			throws ControlableException, NoControlableException {
		return this.consultaOrigenesIngresosBackEnd.ejecutarTRN(this.idInterno);
	}

	/**
	 * Método para ejecutar el servicio de alta de actividad profesional.
	 * 
	 * @return Lista de Datos Profesionales.
	 * @throws ControlableException
	 *             Excepcion controlable que lance el servicio.
	 * @throws NoControlableException
	 *             Excepcion no controlable que lance el servicio.
	 */
	private List<ActividadProfesionalBean> consultaListaDatosProfesionales()
			throws ControlableException, NoControlableException {
		return this.consultaListaDatosProfesionalesBackEnd
				.ejecutarTRN(this.idInterno);
	}

	/**
	 * Método para ejecutar el servicio de consulta de un detalle de Datos
	 * Profesionales.
	 * 
	 * @param actividadProfesionalBean
	 *            Un dato profesional.
	 * @param indice
	 *            Indice de la lista donde se encuentra el campo.
	 * @throws NoControlableException
	 *             Excepcion no controlable que lance el servicio.
	 */
	public void consultaDetalleDatoProf(
			ActividadProfesionalBean actividadProfesionalBean, int indice)
			throws NoControlableException {
		try {
			if (actividadProfesionalBean.getNumActProfIn() != null
					|| actividadProfesionalBean.getNumActProfIn() != 0) {
				actividadProfesionalBean.setIdInterno(this.idInterno);
				actividadProfesionalBean
						.setEstado(EstadoListadosEnum.MODIFICADO);
				this.consultaDetalleDatoProfesionalBackEnd
						.ejecutarTRN(actividadProfesionalBean);
				this.listaActividadesProfesionales.set(indice,
						actividadProfesionalBean);
				this.numModif++;
			}
		} catch (ControlableException c) {
			if (c.getRtncod() != 7) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Datos Económicos",
						"Se ha producido un error al consultar detalle./n"
								+ c.getMensajeDetalle());
				RequestContext.getCurrentInstance()
						.showMessageInDialog(message);
			}
		}

	}

	/**
	 * Método para ejecutar el alta de datos de actividad empresarial.
	 * 
	 * @throws NoControlableException
	 *             Excepcion no controlable que lance el servicio.
	 */
	public int altaDatosEconomicos(int resultadoActividad)
			throws NoControlableException, ControlableException {
		for (ActividadProfesionalBean dato : this.listaActividadesProfesionales) {
			if (dato.getEstado() == EstadoListadosEnum.NUEVO) {
				resultadoActividad = this.altaActividadProfesional(dato);
				this.datosAgregados = true;
			}
		}
		return resultadoActividad;
	}

	/**
	 * Método para ejecutar la baja de datos de actividad empresarial.
	 * 
	 * @throws NoControlableException
	 *             Excepcion no controlable que lance el servicio.
	 */
	public int bajaDatosEconomicos(int resultadoActividad)
			throws NoControlableException, ControlableException {
		List<ActividadProfesionalBean> eliminados = new ArrayList<>();
		for (ActividadProfesionalBean dato : this.listaActividadesProfesionales) {
			if (dato.getEstado() == EstadoListadosEnum.ELIMINADO) {
				resultadoActividad = this.bajaActividadProfesional(dato);
				this.datosEliminados = true;
				eliminados.add(dato);
			}
		}
		this.listaActividadesProfesionales.removeAll(eliminados);
		return resultadoActividad;
	}

	/**
	 * Método para ejecutar la modificacion de datos de actividad empresarial.
	 * 
	 * @throws NoControlableException
	 *             Excepcion no controlable que lance el servicio.
	 */
	public int modificarDatosEconomicos(int resultadoActividad)
			throws NoControlableException, ControlableException {
		for (ActividadProfesionalBean datoProfesional : this.listaActividadesProfesionales) {
			if (datoProfesional.getEstado() == EstadoListadosEnum.MODIFICADO) {
				resultadoActividad = this
						.modificacionActividadProfesional(datoProfesional);
				this.datosGuardados = true;
			}
		}
		return resultadoActividad;
	}

	/**
	 * Método que agrega los nuevos origenes de ingreso.
	 */
	public int agregarOrigenesIngresos(int resultadoActividad) {
		for (String origenSeleccionado : this.origenesSeleccionados) {
			boolean encontrado = false;
			for (OrigenIngresosBean origen : this.listaOrigenIngresos) {
				if (origenSeleccionado.equals(origen.getCodOrigenIngresos())) {
					encontrado = true;
				}
			}
			if (!encontrado) {
				OrigenIngresosBean origen = new OrigenIngresosBean();
				origen.setCodOrigenIngresos(origenSeleccionado);
				resultadoActividad = this.altaOrigenIngreso(origen);
				this.listaOrigenIngresos.add(origen);
				this.datosGuardados = true;
			}
		}
		return resultadoActividad;
	}

	/**
	 * Método que elimina los nuevos origenes de ingreso.
	 */
	public int eliminaOrigenesIngresos(int resultadoActividad)
			throws NoControlableException, ControlableException {
		List<OrigenIngresosBean> eliminados = new ArrayList<>();
		for (Iterator iterator = this.listaOrigenIngresos.iterator(); iterator
				.hasNext();) {
			OrigenIngresosBean origen = (OrigenIngresosBean) iterator.next();
			boolean encontrado = false;
			for (String origenSeleccionado : this.origenesSeleccionados) {
				if (origenSeleccionado.equals(origen.getCodOrigenIngresos())) {
					encontrado = true;
				}
			}
			if (!encontrado) {
				resultadoActividad = this.bajaOrigenIngreso(origen);
				this.datosGuardados = true;
				eliminados.add(origen);
			}
		}
		this.listaOrigenIngresos.removeAll(eliminados);
		return resultadoActividad;
	}

	/**
	 * Valida si muestra el dialogo de cancelar.
	 */
	public String validarAccionCancelar() {
		boolean cambiosOrigenes = false;

		for (String origenSeleccionado : this.origenesSeleccionados) {
			boolean encontrado = false;
			for (OrigenIngresosBean origen : this.listaOrigenIngresos) {
				if (origenSeleccionado.equals(origen.getCodOrigenIngresos())) {
					encontrado = true;
				}
			}
			if (!encontrado) {
				cambiosOrigenes = true;
			}
		}

		for (Iterator iterator = this.listaOrigenIngresos.iterator(); iterator
				.hasNext();) {
			OrigenIngresosBean origen = (OrigenIngresosBean) iterator.next();
			boolean encontrado = false;
			for (String origenSeleccionado : this.origenesSeleccionados) {
				if (origenSeleccionado.equals(origen.getCodOrigenIngresos())) {
					encontrado = true;
				}
			}
			if (!encontrado) {
				cambiosOrigenes = true;
			}
		}

		if (this.eliminadosDatosProf > 0
				|| this.listaActividadesProfesionales.size() > this.numeroDatos
				|| cambiosOrigenes || this.numModif > 0) {
			RequestContext.getCurrentInstance().execute(
					"PF('dlgCancelar').show();");
			return "";
		} else {
			return redirigirInicio();
		}
	}

	/**
	 * Valida si muestra el dialogo de datos a eliminar.
	 */
	public void validarAccionGuardar() {
		if (this.eliminadosDatosProf == 0) {
			this.guardar();
		} else {
			this.mensajeEliminados = DialogoListadoEnum.ELIMINAR;
			this.mensajeEliminados.setMensaje(" " + eliminadosDatosProf
					+ " actividades profesionales");
			this.mensajeEliminados.setMostrar(true);
		}
	}

	/**
	 * Método que ejecuta la acción del botón guardar.
	 * 
	 * @throws NoControlableException
	 *             Excepcion no controlable que lance el servicio.
	 */
	public void guardar() throws NoControlableException {
		try {
			Integer resultadoActividad = -1;
			resultadoActividad = this
					.agregarOrigenesIngresos(resultadoActividad);
			resultadoActividad = this.altaDatosEconomicos(resultadoActividad);
			resultadoActividad = this.bajaDatosEconomicos(resultadoActividad);
			resultadoActividad = this
					.eliminaOrigenesIngresos(resultadoActividad);
			resultadoActividad = this
					.modificarDatosEconomicos(resultadoActividad);
			if (resultadoActividad.intValue() == 1) {
				if (this.datosAgregados && !this.datosEliminados
						&& !this.datosGuardados) {
					this.dialogoGuardado = DialogoListadoEnum.ALTA;
					this.dialogoGuardado
							.setMensaje("Las actividades profesionales se dieron de alta correctamente.");
					this.dialogoGuardado.setMostrar(true);
				} else if (!this.datosAgregados && this.datosEliminados
						&& !this.datosGuardados) {
					this.dialogoGuardado = DialogoListadoEnum.ALTA;
					this.dialogoGuardado
							.setMensaje("Las actividades profesionales se eliminaron correctamente.");
					this.dialogoGuardado.setMostrar(true);
				} else {
					this.dialogoGuardado = DialogoListadoEnum.ALTA;
					this.dialogoGuardado
							.setMensaje("Los cambios se guardaron correctamente.");
					this.dialogoGuardado.setMostrar(true);
				}
				this.eliminadosDatosProf = 0;
				beanBackupUtils
					.respaldaArray((ArrayList<ActividadProfesionalBean>) this.listaActividadesProfesionales);
				this.numeroDatos = this.listaActividadesProfesionales
						.size();
			} else {
				this.dialogoGuardado = DialogoListadoEnum.SIN_CAMBIOS;
				this.dialogoGuardado.setMensaje("No se han realizado cambios");
				this.dialogoGuardado.setMostrar(true);
			}
		} catch (ControlableException c) {
			this.dialogoGuardado = DialogoListadoEnum.SIN_CAMBIOS;
			this.dialogoGuardado.setMensaje(c.getMensajeUsuario() + " "
					+ c.getMensajeDetalle());
			this.dialogoGuardado.setMostrar(true);
		}
	}

	/**
	 * Método que recarga la pagina de actividad Empresarial.
	 * 
	 * @return String ruta de Actividad Empresarial
	 */
//	public String recargarPagina() {
//		obtieneFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(),
//				this.cliente);
//		obtieneFlash().put(ParametrosFlashEnum.ID_EMPLEADO.getParamFlash(),
//				this.idInterno);
//		obtieneFlash().put(ParametrosFlashEnum.NOMBRE_CLIENTE.getParamFlash(),
//				this.nombreCliente);
//		obtieneFlash().put("datosGuardados", this.datosGuardados);
//		obtieneFlash().put("datosEliminados", this.datosEliminados);
//		obtieneFlash().put("datosAgregados", this.datosAgregados);
//		obtieneFlash().put("recarga", true);
//		this.obtieneFlash().put(
//				ParametrosFlashEnum.CONTROLLER_DESTINO.getParamFlash(),
//				destinoController);
//		this.obtieneFlash().put(
//				ParametrosFlashEnum.NAVEGACION_BUSQUEDA.getParamFlash(),
//				destino);
//		this.obtieneFlash().put(
//				ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash(),
//				true);
//		return NavegacionEnum.DATOS_ECONOMICOS.getRuta();
//	}

	/**
	 * Método que regresa ruta de ventana de Inicio.
	 * 
	 * @return String Ruta de Inicio
	 */
	public String redirigirInicio() {
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(),
				this.cliente);
		String rutaDestino = null;
		if (destino == null) {
			rutaDestino = NavegacionEnum.FICHA_CLIENTE.getRuta();
		} else {
			rutaDestino = destino.getRuta();
			managedBeanStateRecover.finNavegacion(destinoController);
		}
		return rutaDestino;
	}

	/**
	 * Obtiene memoria flash para variables en scope view.
	 * 
	 * @return Metodo utilizado para recuperar una instancia de Flash
	 */
	public Flash obtieneFlash() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
	}
	
	public String getCurrentDate() {
		return new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
	}
}
