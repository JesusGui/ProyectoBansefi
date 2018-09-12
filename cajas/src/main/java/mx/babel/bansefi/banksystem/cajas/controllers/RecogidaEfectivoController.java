package mx.babel.bansefi.banksystem.cajas.controllers;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ColumnaParrillaEnum;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ParrillaBean;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.cajas.backend.AltaRecogidaEfectivoBackEnd;
import mx.babel.bansefi.banksystem.cajas.backend.ConfirmarRecogidaBackEnd;
import mx.babel.bansefi.banksystem.cajas.backend.ConsultaHoraLimiteBackend;
import mx.babel.bansefi.banksystem.cajas.backend.ConsultaParillaBilletesBackend;
import mx.babel.bansefi.banksystem.cajas.backend.ConsultaPeticionEfectivoBackEnd;
import mx.babel.bansefi.banksystem.cajas.backend.ConsultaPeticionPorFechasBackEnd;
import mx.babel.bansefi.banksystem.cajas.backend.ModificarRecogidaEfectivoBackEnd;
import mx.babel.bansefi.banksystem.cajas.backend.SuprimirRecogidaEfectivoBackEnd;
import mx.babel.bansefi.banksystem.cajas.beans.CajaUtilsBean;
import mx.babel.bansefi.banksystem.cajas.beans.ComboUrgentesBean;
import mx.babel.bansefi.banksystem.cajas.beans.HoraBean;
import mx.babel.bansefi.banksystem.cajas.beans.RecogidaEfectivoBean;
import mx.babel.bansefi.banksystem.cajas.utils.FechaUtils;

import org.joda.time.DateTime;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Controlador de vistas para flujo de recogida de efectivo
 * 
 * @author aaron.lopez
 * 
 */
@ManagedBean(name = "recogidaEfectivoController")
@Component
@Scope("view")
public class RecogidaEfectivoController implements Serializable {

	private static final long serialVersionUID = 6899587247246325546L;

	@Autowired
	ContextoUtils contextoUtils;
	@Autowired
	CatalogoUtils catalogoUtils;
	@Autowired
	CajaUtilsBean cajasUtilsBean;
	@Autowired
	ConsultaHoraLimiteBackend consultaHoraLimiteBackend;
	@Autowired
	ConsultaPeticionEfectivoBackEnd consultaPeticionEfectivoBackEnd;
	@Autowired
	ConsultaParillaBilletesBackend consultaParillaBilletesBackend;
	@Autowired
	ConsultaPeticionPorFechasBackEnd consultaPeticionPorFechasBackEnd;
	@Autowired
	SuprimirRecogidaEfectivoBackEnd suprimirRecogidaEfectivoBackEnd;
	@Autowired
	AltaRecogidaEfectivoBackEnd altaRecogidaEfectivoBackEnd;
	@Autowired
	ModificarRecogidaEfectivoBackEnd modificarRecogidaEfectivoBackEnd;
	@Autowired
	ConfirmarRecogidaBackEnd confirmarRecogidaBackEnd;

	private RecogidaEfectivoBean recogidaEfectivoBean;

	private ParrillaBean parrilla;

	private Boolean filtro;
	private Boolean editable;
	private Boolean precinto;
	private Boolean botonSolicitarDisabled = true;
	private Boolean botonModificarDisabled = true;
	private Boolean botonSuprimirDisabled = true;
	private Boolean botonConfirmarDisabled = true;
	private Boolean botonLimpiarDisabled = false;
	private Boolean muestraBotonSolicitar = true;
	private Boolean muestraBotonModificar = true;
	private Boolean muestraBotonSuprimir = true;
	private Boolean muestraBotonConfirmar = true;
	private Boolean muestraComboUrgente;
	private Boolean elementosVisibles;
	private Boolean tipoPeticionDisabled;
	private Boolean observacionDisabled = false;
	private Boolean muestraUrgentePopUp;
	private Boolean muestraNuevoUrgente;

	private String mensajeError;
	private String mensajeAdvertencia;

	private Integer CodRetorno;

	public RecogidaEfectivoBean getRecogidaEfectivoBean() {
		return recogidaEfectivoBean;
	}

	public void setRecogidaEfectivoBean(
			RecogidaEfectivoBean recogidaEfectivoBean) {
		this.recogidaEfectivoBean = recogidaEfectivoBean;
	}

	public ParrillaBean getParrilla() {
		return parrilla;
	}

	public void setParrilla(ParrillaBean parrilla) {
		this.parrilla = parrilla;
	}

	public Boolean getFiltro() {
		return filtro;
	}

	public void setFiltro(Boolean filtro) {
		this.filtro = filtro;
	}

	public Boolean getEditable() {
		return editable;
	}

	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	public Boolean getPrecinto() {
		return precinto;
	}

	public void setPrecinto(Boolean precinto) {
		this.precinto = precinto;
	}

	public Boolean getBotonSolicitarDisabled() {
		return botonSolicitarDisabled;
	}

	public void setBotonSolicitarDisabled(Boolean botonSolicitarDisabled) {
		this.botonSolicitarDisabled = botonSolicitarDisabled;
	}

	public Boolean getBotonModificarDisabled() {
		return botonModificarDisabled;
	}

	public void setBotonModificarDisabled(Boolean botonModificarDisabled) {
		this.botonModificarDisabled = botonModificarDisabled;
	}

	public Boolean getBotonSuprimirDisabled() {
		return botonSuprimirDisabled;
	}

	public void setBotonSuprimirDisabled(Boolean botonSuprimirDisabled) {
		this.botonSuprimirDisabled = botonSuprimirDisabled;
	}

	public Boolean getBotonConfirmarDisabled() {
		return botonConfirmarDisabled;
	}

	public void setBotonConfirmarDisabled(Boolean botonConfirmarDisabled) {
		this.botonConfirmarDisabled = botonConfirmarDisabled;
	}

	public Boolean getBotonLimpiarDisabled() {
		return botonLimpiarDisabled;
	}

	public void setBotonLimpiarDisabled(Boolean botonLimpiarDisabled) {
		this.botonLimpiarDisabled = botonLimpiarDisabled;
	}

	public Boolean getMuestraBotonSolicitar() {
		return muestraBotonSolicitar;
	}

	public void setMuestraBotonSolicitar(Boolean muestraBotonSolicitar) {
		this.muestraBotonSolicitar = muestraBotonSolicitar;
	}

	public Boolean getMuestraBotonModificar() {
		return muestraBotonModificar;
	}

	public void setMuestraBotonModificar(Boolean muestraBotonModificar) {
		this.muestraBotonModificar = muestraBotonModificar;
	}

	public Boolean getMuestraBotonSuprimir() {
		return muestraBotonSuprimir;
	}

	public void setMuestraBotonSuprimir(Boolean muestraBotonSuprimir) {
		this.muestraBotonSuprimir = muestraBotonSuprimir;
	}

	public Boolean getMuestraBotonConfirmar() {
		return muestraBotonConfirmar;
	}

	public void setMuestraBotonConfirmar(Boolean muestraBotonConfirmar) {
		this.muestraBotonConfirmar = muestraBotonConfirmar;
	}

	public Boolean getMuestraComboUrgente() {
		return muestraComboUrgente;
	}

	public void setMuestraComboUrgente(Boolean muestraComboUrgente) {
		this.muestraComboUrgente = muestraComboUrgente;
	}

	public Boolean getElementosVisibles() {
		return elementosVisibles;
	}

	public void setElementosVisibles(Boolean elementosVisibles) {
		this.elementosVisibles = elementosVisibles;
	}

	public Boolean getTipoPeticionDisabled() {
		return tipoPeticionDisabled;
	}

	public void setTipoPeticionDisabled(Boolean tipoPeticionDisabled) {
		this.tipoPeticionDisabled = tipoPeticionDisabled;
	}

	public Boolean getObservacionDisabled() {
		return observacionDisabled;
	}

	public void setObservacionDisabled(Boolean observacionDisabled) {
		this.observacionDisabled = observacionDisabled;
	}

	public Boolean getMuestraUrgentePopUp() {
		return muestraUrgentePopUp;
	}

	public void setMuestraUrgentePopUp(Boolean muestraUrgentePopUp) {
		this.muestraUrgentePopUp = muestraUrgentePopUp;
	}

	public Boolean getMuestraNuevoUrgente() {
		return muestraNuevoUrgente;
	}

	public void setMuestraNuevoUrgente(Boolean muestraNuevoUrgente) {
		this.muestraNuevoUrgente = muestraNuevoUrgente;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	public String getMensajeAdvertencia() {
		return mensajeAdvertencia;
	}

	public void setMensajeAdvertencia(String mensajeAdvertencia) {
		this.mensajeAdvertencia = mensajeAdvertencia;
	}

	public String inicio() {
		return NavegacionEnum.RECOGIDA_EFECTIVO.getRuta();
	}

	/**
	 * Se encarga de obtener el flash.
	 * 
	 * @return Flash con los datos de la pagina
	 */
	public Flash obtieneFlash() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
	}
	
	public RecogidaEfectivoController(){
		this.recogidaEfectivoBean = new RecogidaEfectivoBean();
		this.recogidaEfectivoBean.setEstatusC(new String());
		this.recogidaEfectivoBean.setEstatusL(new String());
		this.recogidaEfectivoBean.setTotalRecogida(new BigDecimal(0.00));
		this.recogidaEfectivoBean.setHoraBean(new HoraBean());
		this.recogidaEfectivoBean.setComboUrgentesBean(new ComboUrgentesBean());
		this.parrilla = new ParrillaBean();
		setFiltro(true);
		setEditable(true);
		setPrecinto(false);
		setMuestraComboUrgente(false);
		setElementosVisibles(true);
		setObservacionDisabled(false);
		
	}

	@PostConstruct
	public void init() {
		if(this.obtieneFlash().get(ParametrosFlashEnum.RECOGIDA_EFECTIVO_BEAN.getParamFlash()) != null){
			this.recogidaEfectivoBean = 
					(RecogidaEfectivoBean) this.obtieneFlash().get(ParametrosFlashEnum.RECOGIDA_EFECTIVO_BEAN.getParamFlash());
			
			this.parrilla.iniciaParrilla(true);
			consultaParillaBilletesBackend.ejecutarTRN(this.parrilla.getListaDenominaciones());
			this.recogidaEfectivoBean.setListaDenominaciones(consultaParillaBilletesBackend.getListaExistencias());
			this.parrilla.setListaDenominaciones(
					this.recogidaEfectivoBean.getListaDenominaciones());
			
			if(recogidaEfectivoBean.getTipoPeticion() == 1){
				this.recogidaEfectivoBean.setTotalRecogida(new BigDecimal(0.00));
				this.recogidaEfectivoBean.setEstatusC("");
				this.recogidaEfectivoBean.setEstatusL("");
				this.recogidaEfectivoBean.setObservacion("");
				consultaPeticionOrdinaria();
			}else{
				if(!recogidaEfectivoBean.getTituloResumen().contains("ELIMIN")){
					this.recogidaEfectivoBean.setObservacion("");
					setMuestraComboUrgente(true);
					bloqueaBotones();
					consultaListaPeticionesUrgentes();
					consultaPeticionUrgente();
				}else{
					setMuestraComboUrgente(true);
					bloqueaBotones();
					setBotonLimpiarDisabled(false);
					setBotonSolicitarDisabled(false);
					setMuestraNuevoUrgente(false);
					this.recogidaEfectivoBean.setTotalRecogida(new BigDecimal(0.00));
					this.recogidaEfectivoBean.setEstatusC("");
					this.recogidaEfectivoBean.setEstatusL("");
					this.recogidaEfectivoBean.setObservacion("");
					consultaListaPeticionesUrgentes();
				}
			}
		}else{
			this.recogidaEfectivoBean.setCentro(contextoUtils.getSucursal());
			
			this.parrilla.setImporteAEditar(ColumnaParrillaEnum.IMPORTE_A_ENVIAR);
			this.parrilla.iniciaParrilla(true);
	
			consultaParillaBilletesBackend.ejecutarTRN(this.parrilla.getListaDenominaciones());
			this.recogidaEfectivoBean.setListaDenominaciones(consultaParillaBilletesBackend.getListaExistencias());
	
			this.parrilla.setListaDenominaciones(this.recogidaEfectivoBean.getListaDenominaciones());
			this.recogidaEfectivoBean.setFechaRecogida(cajasUtilsBean.getFechaContableSiguiente());
			this.recogidaEfectivoBean.getHoraBean().setHoraLimiteRecogida(consultaHoraLimiteBackend.ejecutarTRN());
	
			consultaPeticionOrdinaria();
		}
	}

	public String irInicio() {
		return NavegacionEnum.INICIO.getRuta();
	}

	public void cambiaTipoPeticion() {
		actualizaFechaRecogida();
		if (this.recogidaEfectivoBean.getTipoPeticion() == 1) {
			this.recogidaEfectivoBean.setEstatusC("");
			this.recogidaEfectivoBean.setEstatusL("");
			setMuestraComboUrgente(false);
			setMuestraNuevoUrgente(false);
			this.recogidaEfectivoBean.setIndicadorUrgencia(0);
			setEditable(true);
			this.parrilla.getColumnas().clear();
			limpiar();
			if (this.recogidaEfectivoBean.getEstatusC().equals("S")) {
				setBotonSolicitarDisabled(true);
				habilitaBotones();
				setObservacionDisabled(false);
			} else if(!recogidaEfectivoBean.getEstatusC().equals("")){
				bloqueaBotones();
				setObservacionDisabled(true);
			}else{
				bloqueaBotones();
				setBotonLimpiarDisabled(false);
				setObservacionDisabled(false);
			}
		} else {
			setMuestraNuevoUrgente(true);
			setMuestraBotonSolicitar(true);
			setBotonSolicitarDisabled(false);
			setMuestraComboUrgente(true);
			setMuestraNuevoUrgente(false);
			setObservacionDisabled(false);
			bloqueaBotones();
			limpiar();
			setObservacionDisabled(false);
			setBotonLimpiarDisabled(false);
			this.parrilla.getColumnas().clear();
			this.parrilla.setImporteAEditar(ColumnaParrillaEnum.IMPORTE_A_ENVIAR);
			setEditable(true);
			consultaListaPeticionesUrgentes();
		}
	}

	public void consultaPeticionOrdinaria() {
		if (validaHoraLimite()) {
			try {
				FechaUtils utils = new FechaUtils();
				consultaPeticionEfectivoBackEnd.ejecutarTRN(utils.formateoFecha(this.recogidaEfectivoBean.getFechaRecogida()), this.recogidaEfectivoBean.getIndicadorUrgencia());

				final RecogidaEfectivoBean recogidaEfectivoBean = consultaPeticionEfectivoBackEnd.getRecogidaEfectivoBean();

				this.recogidaEfectivoBean.setEntidad(recogidaEfectivoBean.getEntidad());
				this.recogidaEfectivoBean.setCodigoMoneda(recogidaEfectivoBean.getCodigoMoneda());
				this.recogidaEfectivoBean.setCodigoDistribucion(recogidaEfectivoBean.getCodigoDistribucion());
				this.recogidaEfectivoBean.setCentroControlador(recogidaEfectivoBean.getCentroControlador());
				this.recogidaEfectivoBean.setEstatusC(recogidaEfectivoBean.getEstatusC());

				final CatalogoBean catalogo = catalogoUtils.getCatalogoBean(CatalogoEnum.ESTATUS_PETICION, this.recogidaEfectivoBean.getEstatusC());
				if (catalogo != null) {
					this.recogidaEfectivoBean.setEstatusL(catalogo.getDescripcionL());
				} else {
					this.recogidaEfectivoBean.setEstatusL("");
				}

				this.recogidaEfectivoBean.setTotalAutorizado(recogidaEfectivoBean.getTotalAutorizado());
				this.recogidaEfectivoBean.setTotalPedido(recogidaEfectivoBean.getTotalPedido());
				this.recogidaEfectivoBean.setTotalRecibido(recogidaEfectivoBean.getTotalRecibido());
				this.recogidaEfectivoBean.setTotalCertificado(recogidaEfectivoBean.getTotalCertificado());
				this.recogidaEfectivoBean.setIdEmpleado(recogidaEfectivoBean.getIdEmpleado());
				this.recogidaEfectivoBean.setObservacion(recogidaEfectivoBean.getObservacion());
				this.recogidaEfectivoBean.setFechaAbastecimiento(recogidaEfectivoBean.getFechaAbastecimiento());
				this.recogidaEfectivoBean.setFechaProceso(recogidaEfectivoBean.getFechaProceso());
				this.recogidaEfectivoBean.setFechaSolicitud(recogidaEfectivoBean.getFechaSolicitud());
				this.recogidaEfectivoBean.setTotalRecogida(recogidaEfectivoBean.getTotalAutorizado());

				obtenDatosConsultaParrilla();

				setMuestraBotonSolicitar(false);

				if (this.recogidaEfectivoBean.getEstatusC().equals("S")) {
					setBotonLimpiarDisabled(false);
					setBotonSolicitarDisabled(true);
					getParrilla().getColumnas().clear();
					getParrilla().setImporteAEditar(ColumnaParrillaEnum.IMPORTE_A_ENVIAR);
					setEditable(true);
					habilitaBotones();
					setObservacionDisabled(false);
				} else {
					setBotonLimpiarDisabled(true);
					setBotonSolicitarDisabled(true);
					setEditable(false);
					this.parrilla.setImporteAEditar(null);
					this.parrilla.getColumnas().clear();
					this.parrilla.getColumnas().add(ColumnaParrillaEnum.UNIDADES);
					this.parrilla.getColumnas().add(ColumnaParrillaEnum.IMPORTE_A_ENVIAR);
					setObservacionDisabled(true);
					bloqueaBotones();
				}

			} catch (ParseException pe) {
				muestraMensajeAdvertencia("La fecha no es valida");
			} catch (ControlableException ce) {
				if (ce.getRtncod() == 7) {
					setBotonSolicitarDisabled(false);
				} else if (ce.getRtncod() != 1 && ce.getRtncod() != 7) {
					muestraMensajeErrorTRN(ce);
				}
			}
		} else {
			bloqueaBotones();
		}
	}

	public void consultaPeticionUrgente() {
		if (validaHoraLimite()) {
			if (this.recogidaEfectivoBean.getIndicadorUrgencia() != 0) {
				limpiarParrilla();
				try {
					FechaUtils utils = new FechaUtils();

					consultaPeticionEfectivoBackEnd.ejecutarTRN(
							utils.formateoFecha(this.recogidaEfectivoBean.getFechaRecogida()), this.recogidaEfectivoBean.getIndicadorUrgencia());

					final RecogidaEfectivoBean recogidaEfectivoBean = consultaPeticionEfectivoBackEnd.getRecogidaEfectivoBean();

					this.recogidaEfectivoBean.setEntidad(recogidaEfectivoBean.getEntidad());
					this.recogidaEfectivoBean.setCodigoMoneda(recogidaEfectivoBean.getCodigoMoneda());
					this.recogidaEfectivoBean.setCodigoDistribucion(recogidaEfectivoBean.getCodigoDistribucion());
					this.recogidaEfectivoBean.setCentroControlador(recogidaEfectivoBean.getCentroControlador());
					this.recogidaEfectivoBean.setEstatusC(recogidaEfectivoBean.getEstatusC());

					final CatalogoBean catalogo = catalogoUtils.getCatalogoBean(CatalogoEnum.ESTATUS_PETICION,this.recogidaEfectivoBean.getEstatusC());

					if (catalogo != null) {
						this.recogidaEfectivoBean.setEstatusL(catalogo.getDescripcionL());
					} else {
						this.recogidaEfectivoBean.setEstatusL("");
					}

					this.recogidaEfectivoBean.setTotalAutorizado(recogidaEfectivoBean.getTotalAutorizado());
					this.recogidaEfectivoBean.setTotalPedido(recogidaEfectivoBean.getTotalPedido());
					this.recogidaEfectivoBean.setTotalRecibido(recogidaEfectivoBean.getTotalRecibido());
					this.recogidaEfectivoBean.setTotalCertificado(recogidaEfectivoBean.getTotalCertificado());
					this.recogidaEfectivoBean.setIdEmpleado(recogidaEfectivoBean.getIdEmpleado());
					this.recogidaEfectivoBean.setObservacion(recogidaEfectivoBean.getObservacion());
					this.recogidaEfectivoBean.setFechaAbastecimiento(recogidaEfectivoBean.getFechaAbastecimiento());
					this.recogidaEfectivoBean.setFechaProceso(recogidaEfectivoBean.getFechaProceso());
					this.recogidaEfectivoBean.setFechaSolicitud(recogidaEfectivoBean.getFechaSolicitud());
					this.recogidaEfectivoBean.setTotalRecogida(recogidaEfectivoBean.getTotalAutorizado());

					obtenDatosConsultaParrilla();

					setMuestraBotonSolicitar(true);
					setMuestraNuevoUrgente(true);

					if (this.recogidaEfectivoBean.getEstatusC().equals("S")) {
						setBotonLimpiarDisabled(false);
						setBotonSolicitarDisabled(true);
						setEditable(true);
						this.parrilla.setImporteAEditar(null);
						this.parrilla.getColumnas().clear();
						this.parrilla.setImporteAEditar(ColumnaParrillaEnum.IMPORTE_A_ENVIAR);
						setObservacionDisabled(false);
						habilitaBotones();
					} else {
						setBotonLimpiarDisabled(true);
						setBotonSolicitarDisabled(true);
						setEditable(false);
						this.parrilla.setImporteAEditar(null);
						this.parrilla.getColumnas().clear();
						this.parrilla.getColumnas().add(ColumnaParrillaEnum.UNIDADES);
						this.parrilla.getColumnas().add(ColumnaParrillaEnum.IMPORTE_A_ENVIAR);
						setObservacionDisabled(true);
						bloqueaBotones();
					}
				} catch (ParseException pe) {
					muestraMensajeAdvertencia("La fecha no es una fecha valida.");
				} catch (ControlableException ce) {
					if (ce.getRtncod() != 7) {
						muestraMensajeErrorTRN(ce);
					}
				}
			} else {
				setBotonSolicitarDisabled(false);
				setObservacionDisabled(false);
				setBotonLimpiarDisabled(false);
				setMuestraNuevoUrgente(false);
				this.parrilla.getColumnas().clear();
				this.parrilla.setImporteAEditar(ColumnaParrillaEnum.IMPORTE_A_ENVIAR);
				setEditable(true);
				limpiar();
			}
		} else {
			bloqueaBotones();
		}
	}

	public void obtenDatosConsultaParrilla() {

		BigDecimal total = new BigDecimal(0.00);
		if (this.recogidaEfectivoBean.getEstatusC().equals("S")) {
			for (ExistenciaDenominacionBean existencias : consultaPeticionEfectivoBackEnd.getListaExistencias()) {
				for (ExistenciaDenominacionBean existenciaDenominacionBean : this.parrilla.getListaDenominaciones()) {
					if (existencias.getValorFacial() != null 
							&& existencias.getImportePedido() != null
							&& existencias.getImportePedido().compareTo(BigDecimal.ZERO) > 0) {
						if (existencias.getValorFacial().trim().equals(existenciaDenominacionBean.getValorFacial())
								&& existencias.getOrigen().trim().equals(existenciaDenominacionBean.getOrigen())) {

							existenciaDenominacionBean.setImportePedido(existencias.getImportePedido());
							existenciaDenominacionBean.setImporteAEnviar(existencias.getImportePedido());
							existenciaDenominacionBean.setImporteRecibido(existencias.getImporteRecibido());

							total = total.add(existencias.getImportePedido());

							this.parrilla.actualizaUnidades(existenciaDenominacionBean,existenciaDenominacionBean.getImportePedido());

							break;
						}
					}
				}
			}
		} else {
			for (ExistenciaDenominacionBean existencias : consultaPeticionEfectivoBackEnd.getListaExistencias()) {
				for (ExistenciaDenominacionBean existenciaDenominacionBean : this.parrilla.getListaDenominaciones()) {
					if (existencias.getValorFacial() != null
							&& existencias.getImporteAEnviar() != null
							&& existencias.getImporteAEnviar().compareTo(BigDecimal.ZERO) > 0) {
						if (existencias.getValorFacial().trim().equals(existenciaDenominacionBean.getValorFacial())
								&& existencias.getOrigen().trim().equals(existenciaDenominacionBean.getOrigen())) {

							existenciaDenominacionBean.setImportePedido(existencias.getImportePedido());
							existenciaDenominacionBean.setImporteAEnviar(existencias.getImporteAEnviar());
							existenciaDenominacionBean.setImporteRecibido(existencias.getImporteRecibido());

							total = total.add(existencias.getImporteAEnviar());

							this.parrilla.actualizaUnidades(existenciaDenominacionBean,existenciaDenominacionBean.getImporteAEnviar());

							break;
						}
					}
				}
			}
		}
		this.recogidaEfectivoBean.setTotalRecogida(total);
	}

	public void consultaListaPeticionesUrgentes() {
		try {
			FechaUtils utils = new FechaUtils();
			consultaPeticionPorFechasBackEnd.ejecutarTRN(utils.formateoFecha(this.recogidaEfectivoBean.getFechaRecogida()));

			this.recogidaEfectivoBean.getComboUrgentesBean().setListaIndicadoresUrgentes(consultaPeticionPorFechasBackEnd.getListaPeticionesUrgentes());
			
		} catch (ParseException pe) {
			muestraMensajeAdvertencia("La fecha no es una fecha valida.");
		} catch (ControlableException ce) {
			if (ce.getRtncod() != 1 && ce.getRtncod() != 7) {
				muestraMensajeErrorTRN(ce);
			}
		}

		if (validaHoraLimite()) {

		} else {
			setBotonSolicitarDisabled(true);
			bloqueaBotones();
		}
	}

	public String irResumen() {
		if (this.CodRetorno.equals(1)) {
			insertarBeanFlash();
			return NavegacionEnum.DETALLE_RECOGIDA_EFECTIVO.getRuta();
		} else {
			return "";
		}
	}

	public void altaRecogida() {
		if (validaHoraLimite()) {
			try {
				FechaUtils utils = new FechaUtils();

				this.CodRetorno = altaRecogidaEfectivoBackEnd.ejecutarTRN(
						this.recogidaEfectivoBean, 
						this.recogidaEfectivoBean.getTipoPeticion(), 
						utils.formateoFecha(this.recogidaEfectivoBean.getFechaRecogida()), 
						this.parrilla);
				
				if(CodRetorno == 1){
					this.recogidaEfectivoBean.setTituloResumen("RESUMEN DE ALTA DE RECOGIDA DE EFECTIVO POR VALOR DE");
					RequestContext.getCurrentInstance().execute("PF('dlgOperacionExitosa').show();");
				}
			} catch (ParseException pe) {
				muestraMensajeAdvertencia("Fecha invalida");
			} catch (ControlableException ce) {
				muestraMensajeErrorTRN(ce);
			}
		} else {
			muestraMensajeLimiteHora();
		}
	}

	public void modificarRecogida() {
		if (validaHoraLimite()) {
			try {
				this.CodRetorno = modificarRecogidaEfectivoBackEnd.ejecutarTRN(this.recogidaEfectivoBean, this.parrilla.getListaDenominaciones());

				if(CodRetorno == 1){
					this.recogidaEfectivoBean.setTituloResumen("RESUMEN DE MODIFICACIÓN DE RECOGIDA DE EFECTIVO POR VALOR DE");
					RequestContext.getCurrentInstance().execute("PF('dlgOperacionExitosa').show();");
				}
			} catch (ControlableException ce) {
				if (ce.getRtncod() != 1) {
					muestraMensajeErrorTRN(ce);
				}
			}
		} else {
			muestraMensajeLimiteHora();
		}
	}

	public void confirmarRecogida() {
		if (validaHoraLimite()) {
			try {
				this.CodRetorno = confirmarRecogidaBackEnd.ejecutarTRN(this.recogidaEfectivoBean, this.parrilla.getListaDenominaciones());
				
				if(CodRetorno == 1){
					this.recogidaEfectivoBean.setTituloResumen("RESUMEN DE CONFIRMACIÓN DE RECOGIDA DE EFECTIVO POR VALOR DE");
					this.recogidaEfectivoBean.setEstatusC("F");
					this.recogidaEfectivoBean.setEstatusC("CONFIRMADO");
					RequestContext.getCurrentInstance().execute("PF('dlgOperacionExitosa').show();");
				}
			} catch (ControlableException ce) {
				muestraMensajeErrorTRN(ce);
			}
		} else {
			muestraMensajeLimiteHora();
		}
	}

	public void suprimeRecogida() {
		if (validaHoraLimite()) {
			try {
				this.CodRetorno = suprimirRecogidaEfectivoBackEnd.ejecutarTRN(this.recogidaEfectivoBean);
				
				if(CodRetorno == 1){
					this.recogidaEfectivoBean.setTituloResumen("RESUMEN DE ELIMINACIÓN DE RECOGIDA DE EFECTIVO POR VALOR DE");
					RequestContext.getCurrentInstance().execute("PF('dlgOperacionExitosa').show();");
				}
			} catch (ControlableException ce) {
				if (ce.getRtncod() != 1) {
					muestraMensajeErrorTRN(ce);
				}
			}
		} else {
			setElementosVisibles(false);
			setTipoPeticionDisabled(true);
			muestraMensajeLimiteHora();
		}
	}

	public void validaDatosVacios(String tipoConfirmacion) {
		Boolean validado = false;
		for (ExistenciaDenominacionBean existenciaDenominacionBean : this.parrilla.getListaDenominaciones()) {
			if (existenciaDenominacionBean.getImporteAEnviar() != null
					&& existenciaDenominacionBean.getImporteAEnviar().compareTo(BigDecimal.ZERO) > 0) {
				validado = true;
				break;
			}
		}
		if (validado) {

			if (this.recogidaEfectivoBean.getTipoPeticion() == 1) {
				setMuestraUrgentePopUp(false);
			} else {
				setMuestraUrgentePopUp(true);
			}

			if (tipoConfirmacion.equals("A")) {
				RequestContext.getCurrentInstance().execute("PF('dlgSolicitar').show();");
			} else if (tipoConfirmacion.equals("U")) {
				RequestContext.getCurrentInstance().execute("PF('dlgModificar').show();");
			} else if (tipoConfirmacion.equals("C")) {
				RequestContext.getCurrentInstance().execute("PF('dlgConfirmarRecogida').show();");
			}
		} else {
			muestraMensajeAdvertencia("Debe ingresar al menos un valor en el campo unidad o en el campo importe a enviar.");
			limpiarParrilla();
		}
	}

	public void solicitaNuevaPeticionUrgente() {
		if (validaHoraLimite()) {
			limpiar();
			setMuestraNuevoUrgente(false);
			this.recogidaEfectivoBean.setTipoPeticion(2);
			this.recogidaEfectivoBean.setIndicadorUrgencia(0);
			setBotonLimpiarDisabled(false);
			setBotonSolicitarDisabled(false);
			setEditable(true);
			bloqueaBotones();
			this.parrilla.setImporteAEditar(null);
			this.parrilla.getColumnas().clear();
			this.parrilla.setImporteAEditar(ColumnaParrillaEnum.IMPORTE_A_ENVIAR);
			setObservacionDisabled(false);
		} else {
			bloqueaBotones();
			muestraMensajeLimiteHora();
		}
	}

	public void muestraMensajeSuprimir(String tipoConfirmacion) {

		if (this.recogidaEfectivoBean.getTipoPeticion() == 1) {
			setMuestraUrgentePopUp(false);
		} else {
			setMuestraUrgentePopUp(true);
		}

		if (tipoConfirmacion.equals("S")) {
			RequestContext.getCurrentInstance().execute("PF('dlgSuprimirRecogida').show();");
		}
	}

	@SuppressWarnings("deprecation")
	public Boolean validaHoraLimite() {

		DateTime now = DateTime.now();
		int horaActual = now.getHourOfDay();
		int minutoActual = now.getMinuteOfHour();

		Date dHoraLimite = this.recogidaEfectivoBean.getHoraBean().getHoraLimiteRecogida();
		int horaLimite = dHoraLimite.getHours();
		int minutosLimite = dHoraLimite.getMinutes();

		if (horaActual < horaLimite) {
			return true;
		} else if (horaActual == horaLimite) {
			if (minutoActual > minutosLimite) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	public void actualizaFechaRecogida() {
		if (this.recogidaEfectivoBean.getTipoPeticion() == 1) {
			this.recogidaEfectivoBean.setFechaRecogida(cajasUtilsBean.getFechaContableSiguiente());
		} else {
			this.recogidaEfectivoBean.setFechaRecogida(cajasUtilsBean.getFechaSistema());
		}
	}

	public Integer cantidadMaxima(ExistenciaDenominacionBean denominacion) {
		if (denominacion.getValor() != null) {
			BigDecimal maxLength = new BigDecimal("100000000000.00");
			BigInteger maxResult = maxLength.divide(denominacion.getValor(), 0, RoundingMode.UP).toBigInteger();
			return maxResult.toString().length();
		}
		return null;
	}

	public void actualizaUnidades(ExistenciaDenominacionBean denominacion) {
		if (denominacion.getValor() != null && denominacion.getImporteAEnviar() != null) {
			Long unidades = denominacion.getImporteAEnviar().divide(denominacion.getValor()).longValue();
			denominacion.setUnidades(unidades);
		}
		actualizaTotalRecogida();
	}

	public void actualizaImporte(ExistenciaDenominacionBean denominacion) {
		if (denominacion.getValor() != null && denominacion.getUnidades() != null) {
			BigDecimal importe = denominacion.getValor().multiply(new BigDecimal(denominacion.getUnidades()));
			denominacion.setImporteAEnviar(importe);
		}
		actualizaTotalRecogida();
	}

	public void actualizaTotalRecogida() {
		BigDecimal total = new BigDecimal(0);
		for (ExistenciaDenominacionBean existenciaDenominacionBean : this.parrilla.getListaDenominaciones()) {
			if (existenciaDenominacionBean.getImporteAEnviar() != null) {
				total = total.add(existenciaDenominacionBean.getImporteAEnviar());
			}
		}
		this.recogidaEfectivoBean.setTotalRecogida(total);
	}

	public void limpiar() {
		if (this.recogidaEfectivoBean.getTipoPeticion() == 1) {
			if(this.recogidaEfectivoBean.getEstatusC() == null
					|| this.recogidaEfectivoBean.getEstatusC().equals("")){
				this.recogidaEfectivoBean.setTotalRecogida(new BigDecimal(0.00));
			}
			this.recogidaEfectivoBean.setObservacion("");
			this.recogidaEfectivoBean.setTipoPeticion(1);
			limpiarParrilla();
			consultaPeticionOrdinaria();
		} else {
			this.recogidaEfectivoBean.setEstatusC("");
			this.recogidaEfectivoBean.setEstatusL("");
			this.recogidaEfectivoBean.setObservacion("");
			this.recogidaEfectivoBean.setTotalAutorizado(new BigDecimal(0.00));
			this.recogidaEfectivoBean.setTotalCertificado(new BigDecimal(0.00));
			this.recogidaEfectivoBean.setTotalPedido(new BigDecimal(0.00));
			this.recogidaEfectivoBean.setTotalRecibido(new BigDecimal(0.00));
			this.recogidaEfectivoBean.setTotalRecogida(new BigDecimal(0.00));
			limpiarParrilla();
		}
	}

	public void limpiarParrilla() {
		for (ExistenciaDenominacionBean existenciaDenominacionBean : this.parrilla.getListaDenominaciones()) {
			existenciaDenominacionBean.setImporteAEnviar(null);
			existenciaDenominacionBean.setImportePedido(null);
			existenciaDenominacionBean.setImporteRecibido(null);
			existenciaDenominacionBean.setImporteAutorizado(null);
			existenciaDenominacionBean.setUnidades(null);
		}
	}

	private void habilitaBotones() {
		setBotonModificarDisabled(false);
		setBotonSuprimirDisabled(false);
		setBotonConfirmarDisabled(false);
	}

	private void bloqueaBotones() {
		setBotonModificarDisabled(true);
		setBotonSuprimirDisabled(true);
		setBotonConfirmarDisabled(true);
	}

	private void muestraMensajeLimiteHora() {
		RequestContext.getCurrentInstance().execute("PF('dlgHoraExcedida').show();");
	}

	private void muestraMensajeErrorTRN(ControlableException ce) {
		if(ce.getMensajeDetalle().contains("TCB")){
			setMensajeError(ce.getMensajeDetalle());
		}else{
			setMensajeError(ce.getMensajeUsuario() + " - " + ce.getMensajeDetalle());
		}
		RequestContext.getCurrentInstance().execute("PF('dlgError1').show();");
		RequestContext.getCurrentInstance().update("dlgError1");
	}
	
	private void muestraMensajeAdvertencia(String mensaje) {
		setMensajeAdvertencia(mensaje);
		RequestContext.getCurrentInstance().execute("PF('dlgAdvertencia').show();");
		RequestContext.getCurrentInstance().update("dlgAdvertencia");
	}

	private void insertarBeanFlash() {
		this.obtieneFlash().put(ParametrosFlashEnum.RECOGIDA_EFECTIVO_BEAN.getParamFlash(), this.recogidaEfectivoBean);
	}
}
