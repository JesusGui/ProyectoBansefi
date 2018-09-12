package mx.babel.bansefi.banksystem.personas.controllers;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.bansefi.banksystem.base.beans.domain.ClienteBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.DialogoListadoEnum;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.BeanBackUpUtils;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.personas.utils.DialogoPersonasListadoUtils;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;
import mx.babel.bansefi.banksystem.personas.backend.AltaDatosEmpresarialesBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.AltaMercadosOrganizadosBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.AltaObjetoSocialCnaesBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.BajaMercadosOrganizadosBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.BajaObjetoSocialCnaeBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.ConsultaDatosEmpresarialesBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.ConsultaDetalleDireccionRegBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.ConsultaDireccionesRegistralesBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.ConsultaMercadosOrganizadosBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.ConsultaObjetoSocialCnaesBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.MantenimientoDireccionRegistralBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.ModificacionDatosEmpresarialesBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.ModificacionObjetoSocialCnaesBackEnd;
import mx.babel.bansefi.banksystem.personas.beans.ActividadEmpresarialBean;
import mx.babel.bansefi.banksystem.personas.beans.CnaeBean;
import mx.babel.bansefi.banksystem.personas.beans.DireccionRegistralBean;
import mx.babel.bansefi.banksystem.personas.beans.MercadoOrganizadoBean;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.primefaces.component.panel.Panel;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Clase controladora para la ventana de Datos de Actividad Empresarial.
 * 
 * @author alejandro.pineda
 * 
 */
@ManagedBean
@ViewScoped
@Component
@Scope("view")
public class ActividadEmpresarialController implements Serializable {

	public static final long serialVersionUID = 1L;

	private boolean datosVacios;

	private boolean botonEliminarCnae;

	private boolean datosGuardados;

	private boolean datosAgregados;

	private boolean datosEliminados;

	private ClienteBean cliente;

	private String tipoRegistro;

	private String objetoSocial;

	private String objetoSocialRespaldo;

	private String nombre;

	private String idElementoVistaActualizar;

	private ActividadEmpresarialBean actividadEmpresarial;

	private ActividadEmpresarialBean actividadEmpresarialTmp;

	private List<MercadoOrganizadoBean> mercados;

	private List<CnaeBean> listaCnaes;

	private List<DireccionRegistralBean> listaDirecciones;

	private List<DireccionRegistralBean> listaDireccionesRespaldo;

	private List<DireccionRegistralBean> listaDireccionesAgregadas;

	private List<String> mercadosSeleccionados = new ArrayList<String>();

	private List<String> listaMercadosSeleccionado;

	private List<Object> eliminados = new ArrayList<>();

	private int idInterno;

	private int registrosEliminados;

	private int registrosModif;

	private List<CatalogoBean> datosSeleccionados;

	private List<CatalogoBean> cnaesAgregados;

	private List<CatalogoBean> cnaesAgregadosTmp;

	private List<CatalogoBean> cnaesTotales;

	private DialogoPersonasListadoUtils dialogoUtils;

	private DialogoListadoEnum dialogoGuardado;

	private DialogoListadoEnum mensajeEliminados;

	/**
	 * Enum para definir a donde se redireccionará al seleccionar un objeto de
	 * la tabla de resultados.
	 */
	private NavegacionEnum destino;

	private Object destinoController;

	@Autowired
	private BeanBackUpUtils beanBackupUtils;

	@Autowired
	private ManagedBeanStateRecover managedBeanStateRecover;

	@Autowired
	private CatalogoUtils catalogos;

	@Autowired
	private ConsultaDatosEmpresarialesBackEnd consultaDatosEmpresarialesBackEnd;

	@Autowired
	private ConsultaMercadosOrganizadosBackEnd consultaMercadosOrganizadosBackEnd;

	@Autowired
	private ConsultaObjetoSocialCnaesBackEnd consultaObjetoSocialCnaesBackEnd;

	@Autowired
	private AltaDatosEmpresarialesBackEnd altaDatosEmpresarialesBackEnd;

	@Autowired
	private AltaMercadosOrganizadosBackEnd altaMercadosOrganizadosBackEnd;

	@Autowired
	private AltaObjetoSocialCnaesBackEnd altaObjetoSocialCnaesBackEnd;

	@Autowired
	private ModificacionDatosEmpresarialesBackEnd modificacionDatosEmpresarialesBackEnd;

	@Autowired
	private BajaMercadosOrganizadosBackEnd bajaMercadosOrganizadosBackEnd;

	@Autowired
	private BajaObjetoSocialCnaeBackEnd bajaObjetoSocialCnaesBackEnd;

	@Autowired
	private ModificacionObjetoSocialCnaesBackEnd modificacionObjetoSocialCnaesBackEnd;

	@Autowired
	private ConsultaDireccionesRegistralesBackEnd consultaDireccionesRegistralesBackEnd;

	@Autowired
	private ConsultaDetalleDireccionRegBackEnd consultaDetalleDireccionRegBackEnd;

	@Autowired
	private MantenimientoDireccionRegistralBackEnd mantenimientoDireccionRegistralBackEnd;

	public ActividadEmpresarialController() {
		this.listaMercadosSeleccionado = new ArrayList<>();
	}

	/**
	 * Método para inicializar el Controller.
	 * 
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
			this.nombre = (String) this.obtieneFlash().get(
					ParametrosFlashEnum.NOMBRE_CLIENTE.getParamFlash());
			try {
				this.actividadEmpresarial = this.consultaDatosEmpresariales();
				this.actividadEmpresarialTmp = this.actividadEmpresarial;
				this.actividadEmpresarialTmp = SerializationUtils
						.clone(this.actividadEmpresarialTmp);
				this.datosVacios = false;

			} catch (ControlableException c) {
				if (c.getRtncod() == 7) {
					this.actividadEmpresarial = new ActividadEmpresarialBean();
					this.datosVacios = true;
				} else {
					throw c;
				}
			}

			this.mercados = this.consultaMercadoOrganizado();
			this.listaCnaes = this.consultaObjetoSocialCnae();
			this.listaDirecciones = this.consultaDireccionesRegistrales();
			if (null != this.mercados) {
				for (MercadoOrganizadoBean mercado : this.mercados) {
					this.mercadosSeleccionados.add(mercado.getCodigoMercado());
					CatalogoBean catalogo = catalogos.getCatalogoBean(
							CatalogoEnum.TP_MERCADOS_ORG,
							mercado.getCodigoMercado());
					this.listaMercadosSeleccionado.add(mercado
							.getCodigoMercado()
							+ " - "
							+ catalogo.getDescripcionL());
				}
			} else {
				this.mercados = new ArrayList<>();
			}

			this.datosSeleccionados = new ArrayList<>();
			if (null != this.listaCnaes && this.listaCnaes.size() != 0) {
				this.objetoSocialRespaldo = this.listaCnaes.get(0)
						.getObjSocial();
				this.setObjetoSocial(this.listaCnaes.get(0).getObjSocial());
				int contador = 0;
				for (Iterator iterator = this.listaCnaes.iterator(); iterator
						.hasNext();) {
					CnaeBean cnae = (CnaeBean) iterator.next();
					if (!("").equals(cnae.getCodCnae().trim())) {
						CatalogoBean catalogo = catalogos.getCatalogoBean(
								CatalogoEnum.TP_CNAE_PERS,
								cnae.getCodCnae());
						if (cnae.getCodCnae().equals(this.cliente.getCnae())) {
							contador++;
							if (contador > 1) {
								this.bajaObjetoSocialCnae(cnae);
							} else {
								this.datosSeleccionados.add(catalogo);
							}
						} else {
							this.datosSeleccionados.add(catalogo);
						}
					}
				}
			} else {
				this.listaCnaes = new ArrayList<>();
			}

			if (null != this.listaDirecciones) {
				for (DireccionRegistralBean direccionRegistralBean : this.listaDirecciones) {
					direccionRegistralBean
							.setEstado(EstadoListadosEnum.CONSULTADO);
					direccionRegistralBean
							.setDescripcionDato(this
									.getNombreRegistroSeleccionado(direccionRegistralBean
											.getCodDatoResgistral()));
				}
				beanBackupUtils
						.respaldaArray((ArrayList<DireccionRegistralBean>) listaDirecciones);
			} else {
				this.listaDirecciones = new ArrayList<>();
				this.listaDireccionesRespaldo = new ArrayList<>();
			}
			this.botonEliminarCnae = true;
			this.listaDireccionesAgregadas = new ArrayList<>();
			this.cnaesAgregados = new ArrayList<>();
			this.datosAgregados = false;
			this.datosEliminados = false;
			this.datosGuardados = false;
		}

		if (this.obtieneFlash().get("recarga") != null) {
			if ((Boolean) this.obtieneFlash().get("recarga")) {
				if ((Boolean) this.obtieneFlash().get("recarga")) {
					FacesMessage message = null;
					if ((Boolean) this.obtieneFlash().get("datosAgregados")
							&& !(Boolean) this.obtieneFlash().get(
									"datosEliminados")
							&& !(Boolean) this.obtieneFlash().get(
									"datosGuardados")) {
						this.dialogoGuardado = DialogoListadoEnum.ALTA;
						this.dialogoGuardado
								.setMensaje("Los registros se dieron de alta correctamente.");
						this.dialogoGuardado.setMostrar(true);
					} else if (!(Boolean) this.obtieneFlash().get(
							"datosAgregados")
							&& (Boolean) this.obtieneFlash().get(
									"datosEliminados")
							&& !(Boolean) this.obtieneFlash().get(
									"datosGuardados")) {
						this.dialogoGuardado = DialogoListadoEnum.ALTA;
						this.dialogoGuardado
								.setMensaje("Los registros se eliminaron correctamente.");
						this.dialogoGuardado.setMostrar(true);
					} else {
						this.dialogoGuardado = DialogoListadoEnum.ALTA;
						this.dialogoGuardado
								.setMensaje("Los cambios se guardaron correctamente.");
						this.dialogoGuardado.setMostrar(true);
					}
				}
			}
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

	public boolean isDatosGuardados() {
		return datosGuardados;
	}

	public void setDatosGuardados(boolean datosGuardados) {
		this.datosGuardados = datosGuardados;
	}

	public boolean isDatosAgregados() {
		return datosAgregados;
	}

	public void setDatosAgregados(boolean datosAgregados) {
		this.datosAgregados = datosAgregados;
	}

	public boolean isDatosEliminados() {
		return datosEliminados;
	}

	public void setDatosEliminados(boolean datosEliminados) {
		this.datosEliminados = datosEliminados;
	}

	public String getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public ClienteBean getCliente() {
		return cliente;
	}

	public void setCliente(ClienteBean cliente) {
		this.cliente = cliente;
	}

	public String getObjetoSocial() {
		return objetoSocial;
	}

	public void setObjetoSocial(String objetoSocial) {
		this.objetoSocial = objetoSocial;
	}

	public int getIdInterno() {
		return idInterno;
	}

	public void setIdInterno(int idInterno) {
		this.idInterno = idInterno;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIdElementoVistaActualizar() {
		return idElementoVistaActualizar;
	}

	public void setIdElementoVistaActualizar(String idElementoVistaActualizar) {
		this.idElementoVistaActualizar = idElementoVistaActualizar;
	}

	public ActividadEmpresarialBean getActividadEmpresarial() {
		return actividadEmpresarial;
	}

	public void setActividadEmpresarial(
			ActividadEmpresarialBean actividadEmpresarial) {
		this.actividadEmpresarial = actividadEmpresarial;
	}

	public List<DireccionRegistralBean> getListaDirecciones() {
		return listaDirecciones;
	}

	public void setListaDirecciones(
			List<DireccionRegistralBean> listaDirecciones) {
		this.listaDirecciones = listaDirecciones;
	}

	public List<DireccionRegistralBean> getListaDireccionesRespaldo() {
		return listaDireccionesRespaldo;
	}

	public void setListaDireccionesRespaldo(
			List<DireccionRegistralBean> listaDireccionesRespaldo) {
		this.listaDireccionesRespaldo = listaDireccionesRespaldo;
	}

	public List<DireccionRegistralBean> getListaDireccionesAgregadas() {
		return listaDireccionesAgregadas;
	}

	public void setListaDireccionesAgregadas(
			List<DireccionRegistralBean> listaDireccionesAgregadas) {
		this.listaDireccionesAgregadas = listaDireccionesAgregadas;
	}

	public List<String> getMercadosSeleccionados() {
		return mercadosSeleccionados;
	}

	public void setMercadosSeleccionados(List<String> mercadosSeleccionados) {
		this.mercadosSeleccionados = mercadosSeleccionados;
	}

	public List<String> getListaMercadosSeleccionado() {
		return listaMercadosSeleccionado;
	}

	public void setListaMercadosSeleccionado(
			List<String> listaMercadosSeleccionado) {
		this.listaMercadosSeleccionado = listaMercadosSeleccionado;
	}

	public int getRegistrosEliminados() {
		return registrosEliminados;
	}

	public void setRegistrosEliminados(int registrosEliminados) {
		this.registrosEliminados = registrosEliminados;
	}

	/**
	 * Método para obtener los datos seleccionados en el campo de Cnae.
	 * 
	 * @return Lista de Catalogos con los datos predefinidos.
	 */
	public List<CatalogoBean> getDatosSeleccionados() {
		return datosSeleccionados;
	}

	/**
	 * Método para insertar los datos seleccionados del campo de Cnae.
	 * 
	 * @param datosSeleccionados
	 *            Lista con los catalogos seleccionados.
	 */
	public void setDatosSeleccionados(List<CatalogoBean> datosSeleccionados) {
		this.datosSeleccionados = datosSeleccionados;
	}

	public List<CatalogoBean> getCnaesAgregados() {
		return cnaesAgregados;
	}

	public void setCnaesAgregados(List<CatalogoBean> cnaesAgregados) {
		this.cnaesAgregados = cnaesAgregados;
	}

	public List<CatalogoBean> getCnaesEnVista() {
		List<CatalogoBean> cnaesEnVista = new ArrayList<CatalogoBean>();
		if (this.datosSeleccionados != null) {
			cnaesEnVista.addAll(datosSeleccionados);
		}
		if (this.cnaesAgregados != null) {
			cnaesEnVista.addAll(cnaesAgregados);
		}
		return cnaesEnVista;
	}

	public List<CatalogoBean> getCnaesTotales() {
		return cnaesTotales;
	}

	public void setCnaesTotales(List<CatalogoBean> cnaesTotales) {
		this.cnaesTotales = cnaesTotales;
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
	public void obtenerMercadosSeleccionados() {
		listaMercadosSeleccionado = new ArrayList<>();
		for (String origen : this.mercadosSeleccionados) {
			CatalogoBean catalogo = catalogos.getCatalogoBean(
					CatalogoEnum.TP_MERCADOS_ORG, origen);
			listaMercadosSeleccionado.add(origen + " - "
					+ catalogo.getDescripcionL());
		}
	}

	private void aplicarEstiloActivo(String idAccionador) {
		String bloques[] = idAccionador.split(":");
		idElementoVistaActualizar = bloques[0] + ":" + bloques[1];
		Panel panel = (Panel) FacesContext.getCurrentInstance().getViewRoot()
				.findComponent(idElementoVistaActualizar);

		panel.setStyleClass("panel-dinamico consultado");
	}

	private void aplicarEstiloEliminado(String idAccionador) {
		String bloques[] = idAccionador.split(":");
		idElementoVistaActualizar = bloques[0] + ":" + bloques[1];
		Panel panel = (Panel) FacesContext.getCurrentInstance().getViewRoot()
				.findComponent(idElementoVistaActualizar);

		panel.setStyleClass("panel-dinamico eliminado");

	}

	/**
	 * Método para agregar datos a lista "agregados" un Mercados Organizados.
	 */
	public int agregarMercadosOrganizados(int resultadoGuardar)
			throws NoControlableException, ControlableException {
		for (String mercadoSeleccionado : this.mercadosSeleccionados) {
			boolean encontrado = false;
			for (MercadoOrganizadoBean mercado : this.mercados) {
				if (mercadoSeleccionado.equals(mercado.getCodigoMercado())) {
					encontrado = true;
				}
			}
			if (!encontrado) {
				MercadoOrganizadoBean mercado = new MercadoOrganizadoBean();
				mercado.setCodigoMercado(mercadoSeleccionado);
				resultadoGuardar = this.altaMercadoOrganizado(mercado);
				this.datosGuardados = true;
			}
		}
		return resultadoGuardar;
	}

	/**
	 * Método para agregar datos a lista "eliminados" un Mercados Organizados.
	 */
	public int eliminaMercadosOrganizados(int resultadoGuardar) {
		for (MercadoOrganizadoBean mercado : this.mercados) {
			if (!("").equals(mercado.getCodigoMercado().trim())) {
				boolean encontrado = false;
				for (String mercadoSeleccionado : this.mercadosSeleccionados) {
					if (mercadoSeleccionado.equals(mercado.getCodigoMercado())) {
						encontrado = true;
					}
				}
				if (!encontrado) {
					resultadoGuardar = this.bajaMercadoOrganizado(mercado);
					this.datosGuardados = true;
				}
			}
		}
		return resultadoGuardar;
	}

	/**
	 * Método que devuelve un indicador booleano para determinar si una persona
	 * se encuentra dentro de la lista de relaciones eliminadas.
	 * 
	 * @param catalogo
	 *            Catalogo.
	 * @return indicador booleano
	 */
	public boolean cnaeExisteEnActividadesEliminadas(CatalogoBean catalogo) {
		for (Object objeto : eliminados) {
			if (objeto instanceof CnaeBean) {
				CnaeBean cnae = (CnaeBean) objeto;
				if (catalogo.getClaveFila().equals(cnae.getCodCnae())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Método que verifica si el cnae de la fila es el principal.
	 * 
	 * @return boolean
	 */
	public boolean cnaePrincipal(CatalogoBean catalogo) {
		if (catalogo.getClaveFila().equals(this.cliente.getCnae())) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Método para eliminar un CNAE.
	 * 
	 * @param catalogo
	 *            Bean de tipo Catalogo.
	 * @param idAccionador
	 *            Accionador.
	 */
	public void eliminarCnae(CatalogoBean catalogo, String idAccionador) {
		boolean encontrado = false;
		CnaeBean cnaeEliminado = null;
		this.insertarCnae(0);
		for (CnaeBean cnae : this.listaCnaes) {
			if (catalogo.getClaveFila().equals(cnae.getCodCnae())) {
				encontrado = true;
				cnaeEliminado = cnae;
			}
		}
		if (encontrado) {
			this.eliminados.add(cnaeEliminado);
		}
		this.aplicarEstiloEliminado(idAccionador);
	}

	public void eliminarCnaeNoGuardado(CatalogoBean catalogo) {
		this.cnaesAgregados.remove(catalogo);
	}

	/**
	 * Método que se ejecuta al presionar el botón de restaurar.
	 * 
	 * @param catalogo
	 *            Bean de Catalogo a restaurar.
	 * @param idAccionador
	 *            Componente padre para implementar estilos.
	 */
	public void accionRestaurar(CatalogoBean catalogo, String idAccionador) {
		if (!this.eliminados.isEmpty()) {
			for (CnaeBean cnae : this.listaCnaes) {
				if (catalogo.getClaveFila().equals(cnae.getCodCnae())) {
					this.eliminados.remove(cnae);
				}
			}
		}

		if (this.botonEliminarCnae) {
			this.botonEliminarCnae = false;
		} else {
			this.botonEliminarCnae = true;
		}
		this.aplicarEstiloActivo(idAccionador);
	}

	/**
	 * Método para insertar un CNAE.
	 * 
	 * @param param
	 *            Diferente de 1 si no se desea agregar CNAE.
	 */
	public void insertarCnae(int param) {
		String codigo;
		List<CatalogoBean> datosSeleccionadosTemp = this.getCnaesAgregados();
		if (datosSeleccionadosTemp.size() > 0) {
			for (int i = 0; i < datosSeleccionadosTemp.size(); i++) {
				codigo = FacesContext.getCurrentInstance().getExternalContext()
						.getRequestParameterMap()
						.get("datosGenerales:datoCNAEAg" + i + "_hinput");
				if (codigo != null) {
					if (!("").equals(codigo)) {
						CatalogoBean catalogo = catalogos.getCatalogoBean(
								CatalogoEnum.TP_CNAE_PERS, codigo);
						this.cnaesAgregados.set(i, catalogo);
					}
				}
			}
		}
		if (param == 1) {
			this.cnaesAgregados.add(new CatalogoBean());
		}

	}

	/**
	 * Metodo para agregar datos a lista "agregados" un CNAE.
	 */
	public int agregarCnaes(int resultadoGuardar)
			throws NoControlableException, ControlableException {
		for (CatalogoBean catalogo : this.cnaesAgregados) {
			CnaeBean cnae = new CnaeBean();
			cnae.setObjSocial(this.getObjetoSocial());
			cnae.setCodCnae(catalogo.getClaveFila());
			resultadoGuardar = this.altaObjetoSocialCnae(cnae);
			this.datosGuardados = true;
		}
		return resultadoGuardar;
	}

	/**
	 * Método para obtener el nombre del registro seleccionado en el combo.
	 * 
	 * @return String con el nombre del registro.
	 */
	public String getNombreRegistroSeleccionado(String tipoRegistro) {
		if (tipoRegistro != null && !("").equals(tipoRegistro)) {
			CatalogoBean catalogo = catalogos.getCatalogoBean(
					CatalogoEnum.TP_RGSTRO, tipoRegistro);
			return catalogo.getDescripcionL();
		} else {
			return "";
		}

	}

	/**
	 * Método para saber si existe una dirección registral.
	 * 
	 * @param direccion
	 *            Direccion Registral.
	 * @return boolean
	 */
	public boolean existeRegistro(DireccionRegistralBean direccion) {
		boolean existeRegistro = false;
		for (DireccionRegistralBean registro : this.listaDirecciones) {
			if (direccion.getCodDatoResgistral().equals(
					registro.getCodDatoResgistral())
					&& !"99".equals(direccion.getCodDatoResgistral())) {
				existeRegistro = true;
			}
		}
		for (DireccionRegistralBean registro : this.listaDireccionesAgregadas) {
			if (direccion.getCodDatoResgistral().equals(
					registro.getCodDatoResgistral())
					&& !"99".equals(direccion.getCodDatoResgistral())) {
				existeRegistro = true;
			}
		}
		return existeRegistro;
	}

	/**
	 * Método para agregar Dirección Registral a la lista de "agregados".
	 */
	public int agregarDireccionRegistral(int resultadoGuardar)
			throws NoControlableException, ControlableException {
		for (DireccionRegistralBean direccion : this.listaDireccionesAgregadas) {
			direccion
					.setActionType(ConstantesFuncionales.ACTION_TYPE_ALTA_DIRECCION_REGISTRAL);
			direccion.setIdInterno(this.idInterno);
			resultadoGuardar = this.mantenimientoDireccionRegistral(direccion);
			this.datosAgregados = true;
		}
		return resultadoGuardar;
	}

	/**
	 * Método para agregar Dirección Registral a la lista de "modificados".
	 */
	public int modificarDireccionRegistral(int resultadoGuardar)
			throws NoControlableException, ControlableException {
		for (int i = 0; i < this.listaDirecciones.size(); i++) {
			DireccionRegistralBean direccion = this.listaDirecciones.get(i);
			if (direccion.getEstado() == EstadoListadosEnum.MODIFICADO) {
				direccion
						.setActionType(ConstantesFuncionales.ACTION_TYPE_MODIFICAR_DIRECCION_REGISTRAL);
				resultadoGuardar = this
						.mantenimientoDireccionRegistral(direccion);
				this.datosGuardados = true;
			}
		}
		return resultadoGuardar;
	}

	/**
	 * Método para eliminar una Dirección Registral.
	 * 
	 * @param direccionRegistral
	 *            Bean de dirección registral.
	 * @param idAccionador
	 *            Id del componente.
	 */
	public void eliminarDireccionRegistral(
			DireccionRegistralBean direccionRegistral) {
		direccionRegistral.setEstado(EstadoListadosEnum.ELIMINADO);
		this.eliminados.add(direccionRegistral);
		this.registrosEliminados++;
	}

	/**
	 * Método que se ejecuta al presionar el botón de restaurar.
	 * 
	 * @param direccionRegistral
	 *            Bean de Direccion Registral a restaurar.
	 * @param idAccionador
	 *            Componente padre para implementar estilos.
	 */
	public void accionRestaurar(DireccionRegistralBean direccionRegistral) {
		direccionRegistral.setEstado(EstadoListadosEnum.CONSULTADO);
		if (!this.eliminados.isEmpty()) {
			this.eliminados.remove(direccionRegistral);
			this.registrosEliminados--;
		}
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
	 * @param idContenedor
	 */
	public void restaurarModificado(DireccionRegistralBean direccionRegistral) {
		direccionRegistral.setEstado(EstadoListadosEnum.CONSULTADO);
		recuperarBeanDatos(direccionRegistral);
		registrosModif--;
	}

	/**
	 * Método que se ejecuta al presionar el botón de eliminar una Actividad.
	 * 
	 * @param actividad
	 *            Bean de Actividad profesional a restaurar.
	 * @param idAccionador
	 *            Componente padre para implementar estilos.
	 */
	public void accionEliminarNoGuardado(
			DireccionRegistralBean direccionRegistral) {
		this.listaDireccionesAgregadas.remove(direccionRegistral);
	}

	/**
	 * Método que devuelve un indicador booleano para determinar si una persona
	 * se encuentra dentro de la lista de relaciones eliminadas.
	 * 
	 * @param direccion
	 *            Direccion Registral Bean.
	 * @return indicador booleano
	 */
	public boolean datoExisteEnActividadesEliminadas(
			DireccionRegistralBean direccion) {
		for (Object objeto : eliminados) {
			if (objeto instanceof DireccionRegistralBean) {
				if (direccion.equals((DireccionRegistralBean) objeto)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Método para insertar un Dirección Registral.
	 */
	public void insertarDireccionRegistral() {
		if (tipoRegistro != null && !("").equals(tipoRegistro)) {
			DireccionRegistralBean registro = new DireccionRegistralBean();
			registro.setCodDatoResgistral(tipoRegistro);
			registro.setDescripcionDato(this
					.getNombreRegistroSeleccionado(tipoRegistro));
			if (this.listaDirecciones.isEmpty() && !this.existeRegistro(registro)) {
				this.listaDireccionesAgregadas.add(registro);
			} else if (!this.existeRegistro(registro)) {
				this.listaDireccionesAgregadas.add(registro);
			} else {
				this.dialogoGuardado = DialogoListadoEnum.SIN_CAMBIOS;
				this.dialogoGuardado
						.setMensaje("No se puede añadir mas de un mismo tipo de registro.");
				this.dialogoGuardado.setMostrar(true);
			}
		}
	}

	/**
	 * Valida si muestra el dialogo de cancelar.
	 */
	public String validarAccionCancelar() {
		boolean cambiosBean = false;
		boolean cambiosMercados = false;

		for (MercadoOrganizadoBean mercado : this.mercados) {
			if (!("").equals(mercado.getCodigoMercado().trim())) {
				boolean encontrado = false;
				for (String mercadoSeleccionado : this.mercadosSeleccionados) {
					if (mercadoSeleccionado.equals(mercado.getCodigoMercado())) {
						encontrado = true;
					}
				}
				if (!encontrado) {
					cambiosMercados = true;
				}
			}
		}

		for (String mercadoSeleccionado : this.mercadosSeleccionados) {
			boolean encontrado = false;
			for (MercadoOrganizadoBean mercado : this.mercados) {
				if (mercadoSeleccionado.equals(mercado.getCodigoMercado())) {
					encontrado = true;
				}
			}
			if (!encontrado) {
				cambiosMercados = true;
			}
		}

		if (datosVacios) {
			if (!this.actividadEmpresarial.equals(inicializarActividadVacio())) {
				cambiosBean = true;
			}
		} else {
			if (!this.actividadEmpresarial.equals(this.actividadEmpresarialTmp)) {
				cambiosBean = true;
			}
		}

		if (this.registrosEliminados > 0 || this.registrosModif > 0
				|| this.listaDireccionesAgregadas.size() > 0
				|| !objetoSocial.equals(objetoSocialRespaldo)
				|| this.cnaesAgregados.size() > 0 || cambiosMercados
				|| this.eliminados.size() > 0 || cambiosBean) {
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
	public String validarAccionGuardar() throws NoControlableException {
		RequestContext context = RequestContext.getCurrentInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		try {
			if ((!"".equals(actividadEmpresarial.getDiaCierreEjercicio()) && actividadEmpresarial
					.getDiaCierreEjercicio() != null)
					|| (!"".equals(actividadEmpresarial.getMesCierreEjercicio()) && actividadEmpresarial
							.getMesCierreEjercicio() != null)) {
				DateUtils.parseDateStrictly(
						actividadEmpresarial.getDiaCierreEjercicio()
								+ "/"
								+ actividadEmpresarial.getMesCierreEjercicio()
								+ "/"
								+ dateFormat.format(Calendar.getInstance()
										.getTime()), "dd/MM/yyyy");
			}
			if (this.registrosEliminados == 0) {
				return guardar();
			} else {
				this.mensajeEliminados = DialogoListadoEnum.ELIMINAR;
				this.mensajeEliminados.setMensaje(" " + registrosEliminados
						+ " direcciones registrales");
				this.mensajeEliminados.setMostrar(true);
			}
		} catch (ParseException e) {
			String id = "input[id$=diaCierreEjercicio]";
			context.execute("scrollTo('" + id + "');");
			FacesContext.getCurrentInstance().validationFailed();
			List<UIComponent> componentes = FacesContext.getCurrentInstance()
					.getViewRoot().getChildren();
			this.setValidationFalseRecursively(componentes);
		}
		return null;
	}

	private void setValidationFalseRecursively(List<UIComponent> componentes) {
		if (componentes != null && !componentes.isEmpty()) {
			for (UIComponent componente : componentes) {
				if (componente.getClientId().contains("diaCierreEjercicio")) {
					((UIInput) componente).setValid(false);
				}
				if (componente.getClientId().contains("mesCierreEjercicio")) {
					((UIInput) componente).setValid(false);
				}
				this.setValidationFalseRecursively(componente.getChildren());
			}
		}
	}

	/**
	 * Método ejecutado al presionar el botón guardar y ejecutar los servicios.
	 * 
	 * @throws NoControlableException
	 *             Excepcion no controlable que lance el servicio.
	 */
	public String guardar() throws NoControlableException {
		String ruta = null;

		try {
			int resultadoGuardar = -1;
			this.insertarCnae(0);
			resultadoGuardar = this.altaDatosActividad(resultadoGuardar);
			resultadoGuardar = this.agregarMercadosOrganizados(resultadoGuardar);
			resultadoGuardar = this.agregarCnaes(resultadoGuardar);
			resultadoGuardar = this.agregarDireccionRegistral(resultadoGuardar);
			resultadoGuardar = this.modificarDatosActividad(resultadoGuardar);
			resultadoGuardar = this.modificarDireccionRegistral(resultadoGuardar);
			resultadoGuardar = this.bajaDatosActividad(resultadoGuardar);
			resultadoGuardar = this.eliminaMercadosOrganizados(resultadoGuardar);

			if (resultadoGuardar == 1) {
				return recargarPagina();
			} else if (resultadoGuardar == -1) {
				this.dialogoGuardado = DialogoListadoEnum.SIN_CAMBIOS;
				this.dialogoGuardado.setMensaje("No se han realizado cambios");
				this.dialogoGuardado.setMostrar(true);
			}
		} catch (ControlableException c) {
			this.dialogoGuardado = DialogoListadoEnum.SIN_CAMBIOS;
			this.dialogoGuardado
					.setMensaje(c.getMensajeUsuario() + c.getMensajeDetalle());
			this.dialogoGuardado.setMostrar(true);
		}
		return ruta;

	}

	/**
	 * Método para ejecutar el alta de datos de actividad empresarial.
	 * 
	 * @throws NoControlableException
	 *             Excepcion no controlable que lance el servicio.
	 */
	public int altaDatosActividad(int resultadoGuardar)
			throws ControlableException, NoControlableException {
		if (datosVacios) {
			if (!this.actividadEmpresarial.equals(inicializarActividadVacio())) {
				resultadoGuardar = this.altaDatosEmpresariales();
				this.datosGuardados = true;
			} else {
				inicializarActividadNulo();
			}
		}
		return resultadoGuardar;
	}

	/**
	 * Método para ejecutar los servicios de modificación de actividad
	 * empresarial.
	 * 
	 * @throws NoControlableException
	 *             Excepcion no controlable que lance el servicio.
	 */
	public int modificarDatosActividad(int resultadoGuardar)
			throws ControlableException, NoControlableException {
		if (!this.datosVacios) {
			actividadEmpresarial.setIdInterno(idInterno);
			if (this.actividadEmpresarial.getIdInterno().equals(
					this.actividadEmpresarialTmp.getIdInterno())
					&& !this.actividadEmpresarial
							.equals(this.actividadEmpresarialTmp)) {
				this.actividadEmpresarialTmp = SerializationUtils
						.clone(this.actividadEmpresarial);
				resultadoGuardar = this.modificacionDatosEmpresariales();
				this.datosGuardados = true;
			}
		}
		if (!objetoSocial.equals(objetoSocialRespaldo)) {
			objetoSocialRespaldo = objetoSocial;
			CnaeBean cnae = new CnaeBean();
			cnae.setObjSocial(objetoSocial);
			resultadoGuardar = this.modificacionObjetoSocialCnae(cnae);
			this.datosGuardados = true;
		}
		return resultadoGuardar;
	}

	/**
	 * Método para ejecutar el alta de datos de actividad empresarial.
	 * 
	 * @throws NoControlableException
	 *             Excepcion no controlable que lance el servicio.
	 */
	public int bajaDatosActividad(int resultadoGuardar)
			throws ControlableException, NoControlableException {

		if (!this.eliminados.isEmpty()) {
			for (Object dato : this.eliminados) {
				if (dato instanceof CnaeBean) {
					resultadoGuardar = this.bajaObjetoSocialCnae((CnaeBean) dato);
					this.datosGuardados = true;
				}
				if (dato instanceof DireccionRegistralBean) {
					DireccionRegistralBean direccion = (DireccionRegistralBean) dato;
					direccion.setActionType(ConstantesFuncionales.ACTION_TYPE_BORRAR_DIRECCION_REGISTRAL);
					this.consultaDetalleDireccion(direccion, -1);
					resultadoGuardar = this.mantenimientoDireccionRegistral(direccion);
					this.datosEliminados = true;
				}
			}
		}
		return resultadoGuardar;
	}

	private ActividadEmpresarialBean inicializarActividadVacio() {
		ActividadEmpresarialBean actividadComparable = new ActividadEmpresarialBean();
		actividadComparable.setCalificacion("");
		actividadComparable.setCuotaMercado(0.0);
		actividadComparable.setDiaCierreEjercicio("");
		actividadComparable.setJornadaLaboral("");
		actividadComparable.setMesCierreEjercicio("");
		actividadComparable.setNumEmpleados(0);
		actividadComparable.setNumSocios(0);
		actividadComparable.setNumSucursales(0);
		actividadComparable.setPresentado("");
		actividadComparable.setSectorEconomico("");
		actividadComparable.setSociedadCalif("");
		actividadComparable.setTipoEmpresa("");
		return actividadComparable;
	}

	private void inicializarActividadNulo() {
		actividadEmpresarial.setCalificacion(null);
		actividadEmpresarial.setCuotaMercado(null);
		actividadEmpresarial.setDiaCierreEjercicio(null);
		actividadEmpresarial.setJornadaLaboral(null);
		actividadEmpresarial.setMesCierreEjercicio(null);
		actividadEmpresarial.setNumEmpleados(null);
		actividadEmpresarial.setNumSocios(null);
		actividadEmpresarial.setNumSucursales(null);
		actividadEmpresarial.setPresentado(null);
		actividadEmpresarial.setSectorEconomico(null);
		actividadEmpresarial.setSociedadCalif(null);
		actividadEmpresarial.setTipoEmpresa(null);
	}

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
	 * Método que recarga la pagina de actividad Empresarial.
	 * 
	 * @return String ruta de Actividad Empresarial
	 */
	public String recargarPagina() {
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(),
				this.cliente);
		obtieneFlash().put(ParametrosFlashEnum.ID_EMPLEADO.getParamFlash(),
				this.idInterno);
		obtieneFlash().put(ParametrosFlashEnum.NOMBRE_CLIENTE.getParamFlash(),
				this.nombre);
		obtieneFlash().put("datosGuardados", this.datosGuardados);
		obtieneFlash().put("datosEliminados", this.datosEliminados);
		obtieneFlash().put("datosAgregados", this.datosAgregados);
		obtieneFlash().put("recarga", true);
		this.obtieneFlash().put(
				ParametrosFlashEnum.CONTROLLER_DESTINO.getParamFlash(),
				destinoController);
		this.obtieneFlash().put(
				ParametrosFlashEnum.NAVEGACION_BUSQUEDA.getParamFlash(),
				destino);
		this.obtieneFlash().put(
				ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash(),
				true);
		return NavegacionEnum.ACTIVIDAD_EMPRESARIAL.getRuta();
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

	/**
	 * Método para ejecutar el servicio de alta de datos empresariales.
	 * 
	 * @return int Código de retorno del servicio.
	 * @throws ControlableException
	 *             Excepcion controlable que lance el servicio.
	 * @throws NoControlableException
	 *             Excepcion no controlable que lance el servicio.
	 */
	public ActividadEmpresarialBean consultaDatosEmpresariales()
			throws ControlableException, NoControlableException {
		return this.consultaDatosEmpresarialesBackEnd
				.ejecutarTRN(this.idInterno);
	}

	/**
	 * Método para ejecutar el servicio de alta de mercado organizado.
	 * 
	 * @return int Código de retorno del servicio.
	 * @throws ControlableException
	 *             Excepcion controlable que lance el servicio.
	 * @throws NoControlableException
	 *             Excepcion no controlable que lance el servicio.
	 */
	public List<MercadoOrganizadoBean> consultaMercadoOrganizado()
			throws ControlableException, NoControlableException {
		return this.consultaMercadosOrganizadosBackEnd
				.ejecutarTRN(this.idInterno);
	}

	/**
	 * Método para ejecutar el servicio de alta de objeto social y cnae.
	 * 
	 * @return int Código de retorno del servicio.
	 * @throws ControlableException
	 *             Excepcion controlable que lance el servicio.
	 * @throws NoControlableException
	 *             Excepcion no controlable que lance el servicio.
	 */
	public List<CnaeBean> consultaObjetoSocialCnae()
			throws ControlableException, NoControlableException {
		return this.consultaObjetoSocialCnaesBackEnd
				.ejecutarTRN(this.idInterno);
	}

	/**
	 * Método para ejecutar el servicio de alta de datos empresariales.
	 * 
	 * @return int Código de retorno del servicio.
	 * @throws ControlableException
	 *             Excepcion controlable que lance el servicio.
	 * @throws NoControlableException
	 *             Excepcion no controlable que lance el servicio.
	 */
	public int altaDatosEmpresariales() throws ControlableException,
			NoControlableException {
		actividadEmpresarial.setIdInterno(idInterno);
		return this.altaDatosEmpresarialesBackEnd
				.ejecutarTRN(actividadEmpresarial);
	}

	/**
	 * Método para ejecutar el servicio de alta de mercado organizado.
	 * 
	 * @param mercado
	 *            Un mercado organizado.
	 * @return int Código de retorno del servicio.
	 * @throws ControlableException
	 *             Excepcion controlable que lance el servicio.
	 * @throws NoControlableException
	 *             Excepcion no controlable que lance el servicio.
	 */
	public int altaMercadoOrganizado(MercadoOrganizadoBean mercado)
			throws ControlableException, NoControlableException {
		mercado.setIdInterno(idInterno);
		return this.altaMercadosOrganizadosBackEnd.ejecutarTRN(mercado);
	}

	/**
	 * Método para ejecutar el servicio de alta de objeto social y cnae.
	 * 
	 * @param cnae
	 *            Bean de Cnae que se dará de alta
	 * @return int Código de retorno del servicio.
	 * @throws ControlableException
	 *             Excepcion controlable que lance el servicio.
	 * @throws NoControlableException
	 *             Excepcion no controlable que lance el servicio.
	 */
	public int altaObjetoSocialCnae(CnaeBean cnae) throws ControlableException,
			NoControlableException {
		cnae.setIdInterno(idInterno);
		return this.altaObjetoSocialCnaesBackEnd.ejecutarTRN(cnae);
	}

	/**
	 * Método para ejecutar el servicio de alta de datos empresariales.
	 * 
	 * @return int Código de retorno del servicio.
	 * @throws ControlableException
	 *             Excepcion controlable que lance el servicio.
	 * @throws NoControlableException
	 *             Excepcion no controlable que lance el servicio.
	 */
	public int modificacionDatosEmpresariales() throws ControlableException,
			NoControlableException {
		return this.modificacionDatosEmpresarialesBackEnd
				.ejecutarTRN(actividadEmpresarial);
	}

	/**
	 * Método para ejecutar el servicio de modificacion de Objeto Social.
	 * 
	 * @param cnae
	 *            Bean de cnae.
	 * @return int Código de retorno del servicio.
	 * @throws ControlableException
	 *             Excepcion controlable que lance el servicio.
	 * @throws NoControlableException
	 *             Excepcion no controlable que lance el servicio.
	 */
	public int modificacionObjetoSocialCnae(CnaeBean cnae)
			throws ControlableException, NoControlableException {
		cnae.setIdInterno(idInterno);
		return this.modificacionObjetoSocialCnaesBackEnd.ejecutarTRN(cnae);
	}

	/**
	 * Método para ejecutar el servicio de alta de mercado organizado.
	 * 
	 * @param mercado
	 *            Un mercado organizado.
	 * @return int Código de retorno del servicio.
	 * @throws ControlableException
	 *             Excepcion controlable que lance el servicio.
	 * @throws NoControlableException
	 *             Excepcion no controlable que lance el servicio.
	 */
	public int bajaMercadoOrganizado(MercadoOrganizadoBean mercado)
			throws ControlableException, NoControlableException {
		mercado.setIdInterno(idInterno);
		return this.bajaMercadosOrganizadosBackEnd.ejecutarTRN(mercado);
	}

	/**
	 * Método para ejecutar el servicio de alta de objeto social y cnae.
	 * 
	 * @param cnae
	 *            Bean de Cnae que se dará de alta
	 * @return int Código de retorno del servicio.
	 * @throws ControlableException
	 *             Excepcion controlable que lance el servicio.
	 * @throws NoControlableException
	 *             Excepcion no controlable que lance el servicio.
	 */
	public int bajaObjetoSocialCnae(CnaeBean cnae) throws ControlableException,
			NoControlableException {
		cnae.setIdInterno(idInterno);
		return this.bajaObjetoSocialCnaesBackEnd.ejecutarTRN(cnae);
	}

	/**
	 * Método para ejecutar el servicio de alta de mercado organizado.
	 * 
	 * @return int Código de retorno del servicio.
	 * @throws ControlableException
	 *             Excepcion controlable que lance el servicio.
	 * @throws NoControlableException
	 *             Excepcion no controlable que lance el servicio.
	 */
	public List<DireccionRegistralBean> consultaDireccionesRegistrales()
			throws ControlableException, NoControlableException {
		return this.consultaDireccionesRegistralesBackEnd
				.ejecutarTRN(this.idInterno);
	}

	/**
	 * Metodo que ejecuta la consulta de detalle de una Dirección registral.
	 * 
	 * @param registro
	 * @param indice
	 * @throws NoControlableException
	 */
	public void consultaDetalleDireccion(DireccionRegistralBean registro,
			int indice) throws NoControlableException {
		try {
			if (registro.getCodDatoResgistral() != null
					&& !("").equals(registro.getCodDatoResgistral())) {
				registro.setEstado(EstadoListadosEnum.MODIFICADO);
				this.consultaDetalleDireccionRegBackEnd.ejecutarTRN(registro);
				if (indice != -1) {
					this.listaDirecciones.set(indice, registro);
				}
				registrosModif++;
			}
		} catch (ControlableException c) {
			if (c.getRtncod() != 7) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Actividad Empresarial",
						"Se ha producido un error al consultar detalle./n"
								+ c.getMensajeDetalle());
				RequestContext.getCurrentInstance()
						.showMessageInDialog(message);
			}
		}

	}

	/**
	 * Método para ejecutar el servicio de alta de objeto social y cnae.
	 * 
	 * @param direccion
	 *            Bean de DireccionRegistral que se enviará
	 * @return int Código de retorno del servicio.
	 * @throws ControlableException
	 *             Excepcion controlable que lance el servicio.
	 * @throws NoControlableException
	 *             Excepcion no controlable que lance el servicio.
	 */
	public int mantenimientoDireccionRegistral(DireccionRegistralBean direccion)
			throws ControlableException, NoControlableException {
		direccion.setIdInterno(idInterno);
		return this.mantenimientoDireccionRegistralBackEnd
				.ejecutarTRN(direccion);
	}
	
	public String getCurrentDate() {
		return new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
	}
	
}
