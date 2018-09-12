package mx.babel.bansefi.banksystem.cajas.controllers;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ColumnaParrillaEnum;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ParrillaBean;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.cajas.utils.FechaUtils;
import mx.babel.bansefi.banksystem.base.utils.CatalogoCentrosLoaderTask;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.PdfUtils;
import mx.babel.bansefi.banksystem.cajas.backend.ConsultaEnvioEntreOficinasBackEnd;
import mx.babel.bansefi.banksystem.cajas.backend.ConsultaParillaBilletesBackend;
import mx.babel.bansefi.banksystem.cajas.backend.RecepcionEntreOficinasBackend;
import mx.babel.bansefi.banksystem.cajas.beans.CajaUtilsBean;
import mx.babel.bansefi.banksystem.cajas.beans.RecepcionEntreOficinasBean;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@ManagedBean(name = "recepcionEntreOficinasController")
@Component
@Scope("view")
public class RecepcionEntreOficinasController implements Serializable {

	private static final long serialVersionUID = 2529179460042774013L;
	
	@Autowired
	CajaUtilsBean cajaUtilsBean;
	@Autowired
	CatalogoUtils catalogoUtils;
	@Autowired
	ContextoUtils contextoUtils;
	@Autowired
	ConsultaParillaBilletesBackend consultaParillaBilletesBackend;
	@Autowired
	ConsultaEnvioEntreOficinasBackEnd consultaEnvioEntreOficinasBackEnd;
	@Autowired
	RecepcionEntreOficinasBackend recepcionEntreOficinasBackend;
	@Autowired
	FechaUtils fechaUtils;
	@Autowired
	CatalogoCentrosLoaderTask catalogoCentrosLoaderTask;
	@Autowired
	PdfUtils pdfUtils;
	
	private ParrillaBean parrilla;

	private String mensajeError;
	private String mensajePrecaucion;
	private String mensajeInfo;
	
	private Date fechaSistema;
	private Date fechaSistemaSiguiente;
	
	private List<CatalogoBean> listaCentros;
	
	private RecepcionEntreOficinasBean recepcionEntreOficinasBean;
	
	private Boolean estatusVisible = false;
	private Boolean editable = true;
	private Boolean filtro = true;
	private Boolean filtoDisabled = true;
	private Boolean precinto = false;
	private Boolean unidadesDisabled = true;
	private Boolean importeRecibidoDisabled = true;
	private Boolean observacionesDisabled = true;
	private Boolean mostrarBotones = false;
	private Boolean fechaDisabled = false;
	
	private BigDecimal zero = BigDecimal.ZERO;

	public RecepcionEntreOficinasBean getRecepcionEntreOficinasBean() {
		return recepcionEntreOficinasBean;
	}

	public void setRecepcionEntreOficinasBean(
			RecepcionEntreOficinasBean recepcionEntreOficinasBean) {
		this.recepcionEntreOficinasBean = recepcionEntreOficinasBean;
	}
	
	public RecepcionEntreOficinasController(){
		this.recepcionEntreOficinasBean = new RecepcionEntreOficinasBean();
		this.recepcionEntreOficinasBean.setParrilla(new ParrillaBean());
		this.recepcionEntreOficinasBean.setTotalEnviado(new BigDecimal(0.00));
		this.recepcionEntreOficinasBean.setTotalRecibido(new BigDecimal(0.00));
		this.recepcionEntreOficinasBean.setDiferencias(new BigDecimal(0.0));
		this.recepcionEntreOficinasBean.setCentroOrigen("");
		recepcionEntreOficinasBean.setEstatusC("");
		recepcionEntreOficinasBean.setEstatusL("");
	}

	@PostConstruct
	public void init() throws ParseException {
		setFechaSistema(cajaUtilsBean.getFechaSistema());
		setFechaSistemaSiguiente(cajaUtilsBean.getFechaContableSiguiente());
		if(this.obtieneFlash().get(ParametrosFlashEnum.RECEPCION_ENTRE_OFICINAS_BEAN.getParamFlash()) != null){
			this.recepcionEntreOficinasBean = (RecepcionEntreOficinasBean) this.obtieneFlash().get(ParametrosFlashEnum.RECEPCION_ENTRE_OFICINAS_BEAN.getParamFlash());
			if(recepcionEntreOficinasBean.getTituloDetalle() == null
					|| recepcionEntreOficinasBean.getTituloDetalle().equals("")){
				consultarEnvioEntreOficinas();
			}else{
				RecepcionEntreOficinasBean recepcion = consultaEnvioEntreOficinasBackEnd.ejecutarTRN2(recepcionEntreOficinasBean.getFechaEnvioInteger(), recepcionEntreOficinasBean.getCentroOrigen());
				this.recepcionEntreOficinasBean.setEstatusC(recepcion.getEstatusC());
				this.recepcionEntreOficinasBean.setEstatusL(recepcion.getEstatusL());
				
				this.parrilla = new ParrillaBean();
				this.parrilla.setListaDenominaciones(new ArrayList<ExistenciaDenominacionBean>());
				this.parrilla.getColumnas().add(ColumnaParrillaEnum.IMPORTE_A_ENVIAR);
				this.parrilla.getColumnas().add(ColumnaParrillaEnum.UNIDADES);
				this.parrilla.getColumnas().add(ColumnaParrillaEnum.IMPORTE_RECIBIDO);
				for(ExistenciaDenominacionBean existencia : recepcionEntreOficinasBean.getParrilla().getListaDenominaciones()){
					if(existencia.getImporteRecibido() != null
							&& existencia.getImporteRecibido().compareTo(BigDecimal.ZERO) > 0){
						existencia.setImporteModificable(existencia.getImporteRecibido());
						this.parrilla.getListaDenominaciones().add(existencia);
					}
				}
				
			}
		}else{
			this.listaCentros = new ArrayList<CatalogoBean>();
			this.recepcionEntreOficinasBean.getParrilla().iniciaParrilla(true);
			consultaParillaBilletesBackend.ejecutarTRN(this.recepcionEntreOficinasBean.getParrilla().getListaDenominaciones());
			this.recepcionEntreOficinasBean.getParrilla().setListaDenominaciones(consultaParillaBilletesBackend.getListaExistencias());
			this.recepcionEntreOficinasBean.setFechaEnvio(cajaUtilsBean.getFechaSistema());
			this.recepcionEntreOficinasBean.setFechaEnvioInteger(cajaUtilsBean.getFechaSistemaInteger());
		}
		
		this.acomodarParrilla();
	}

	public String inicio() {
		return NavegacionEnum.RECEPCION_ENTRE_OFICINAS.getRuta();
	}

	public Flash obtieneFlash() {
		return FacesContext.getCurrentInstance().getExternalContext().getFlash();
	}

	public String irInicio() {
		return NavegacionEnum.INICIO.getRuta();
	}
	
	public void actualizarFechaEnvio() throws ParseException{
		this.recepcionEntreOficinasBean.setFechaEnvioInteger(fechaUtils.formateoFecha(recepcionEntreOficinasBean.getFechaEnvio()));
	}
	
	public String irDetalle(){
		this.obtieneFlash().put(ParametrosFlashEnum.RECEPCION_ENTRE_OFICINAS_BEAN.getParamFlash(), this.recepcionEntreOficinasBean);
		return NavegacionEnum.DETALLE_RECEPCION_ENTRE_OFICINAS.getRuta();
	}
	
	public String regresarDetalle(){
		this.recepcionEntreOficinasBean.setTituloDetalle("");
		this.obtieneFlash().put(ParametrosFlashEnum.RECEPCION_ENTRE_OFICINAS_BEAN.getParamFlash(), this.recepcionEntreOficinasBean);
		return NavegacionEnum.RECEPCION_ENTRE_OFICINAS.getRuta();
	}

	public void consultarEnvioEntreOficinas() {
		try {
			RecepcionEntreOficinasBean beanEntrada = consultaEnvioEntreOficinasBackEnd.ejecutarTRN2(recepcionEntreOficinasBean.getFechaEnvioInteger(), recepcionEntreOficinasBean.getCentroOrigen());
			if(beanEntrada.getEstatusC() != null
					&& beanEntrada.getEstatusC().equals("F")
					|| beanEntrada.getEstatusC().equals("R")){
				obtenerDatosConsulta(beanEntrada);
				obtenerDatosParrilla(beanEntrada);
				setUnidadesDisabled(false);
				setImporteRecibidoDisabled(false);
				setEstatusVisible(true);
				setFiltoDisabled(false);
				this.acomodarParrilla();
				setMostrarBotones(true);
				setFechaDisabled(true);
			}else{
				setFechaDisabled(true);
				obtenerDatosConsulta(beanEntrada);
				obtenerDatosParrilla(beanEntrada);
				setMostrarBotones(false);
				parrillaDisabled();
				setEstatusVisible(true);
			}
		} catch (ControlableException ce) {
			if (ce.getRtncod() == 7) {
				setFechaDisabled(false);
				parrillaDisabled();
				mostrarMensajeInfo("No existen envíos con los datos ingresados.");
				limpiarParrilla();
				this.recepcionEntreOficinasBean.setTotalAutorizado(new BigDecimal(0.00));
				this.recepcionEntreOficinasBean.setTotalCertificado(new BigDecimal(0.00));
				this.recepcionEntreOficinasBean.setTotalEnviado(new BigDecimal(0.00));
				this.recepcionEntreOficinasBean.setTotalRecibido(new BigDecimal(0.00));
				this.recepcionEntreOficinasBean.setTotalRecibidoAbsoluto(new BigDecimal(0.00));
				this.recepcionEntreOficinasBean.setDiferencias(new BigDecimal(0.00));
				this.recepcionEntreOficinasBean.setObservaciones("");
				setMostrarBotones(false);
			} else if (ce.getRtncod() != 1 && ce.getRtncod() != 7) {
				mostrarMensajeError(ce.getMensajeUsuario() + " " + ce.getMensajeDetalle());
			}
		} catch (NoControlableException nce) {
			mostrarMensajeError(nce.getMensajeUsuario() + " " + nce.getMensajeDetalle());
		}
	}

	private void obtenerDatosConsulta(RecepcionEntreOficinasBean recepcionEntreOficinasBean) {
		
		this.recepcionEntreOficinasBean.setEntidad(recepcionEntreOficinasBean.getEntidad());
		this.recepcionEntreOficinasBean.setCodigoMoneda(recepcionEntreOficinasBean.getCodigoMoneda());
		this.recepcionEntreOficinasBean.setCodigoDistribucion(recepcionEntreOficinasBean.getCodigoDistribucion());
		this.recepcionEntreOficinasBean.setIndicadorUrgente(recepcionEntreOficinasBean.getIndicadorUrgente());
		this.recepcionEntreOficinasBean.setCentroControlador(recepcionEntreOficinasBean.getCentroControlador());
		this.recepcionEntreOficinasBean.setEstatusC(recepcionEntreOficinasBean.getEstatusC());
		this.recepcionEntreOficinasBean.setEstatusL(recepcionEntreOficinasBean.getEstatusL());
		this.recepcionEntreOficinasBean.setFechaAbastecimiento(recepcionEntreOficinasBean.getFechaAbastecimiento());
		this.recepcionEntreOficinasBean.setFechaProceso(recepcionEntreOficinasBean.getFechaProceso());
		this.recepcionEntreOficinasBean.setTotalAutorizado(recepcionEntreOficinasBean.getTotalAutorizado());
		this.recepcionEntreOficinasBean.setTotalEnviado(recepcionEntreOficinasBean.getTotalEnviado());
		this.recepcionEntreOficinasBean.setTotalRecibido(recepcionEntreOficinasBean.getTotalRecibido());
		this.recepcionEntreOficinasBean.setTotalRecibidoAbsoluto(recepcionEntreOficinasBean.getTotalRecibidoAbsoluto());
		this.recepcionEntreOficinasBean.setTotalCertificado(recepcionEntreOficinasBean.getTotalCertificado());
		this.recepcionEntreOficinasBean.setIdEmpleado(recepcionEntreOficinasBean.getIdEmpleado().trim());
		this.recepcionEntreOficinasBean.setObservaciones(recepcionEntreOficinasBean.getObservaciones().trim());
	}
	
	private void obtenerDatosParrilla(RecepcionEntreOficinasBean recepcionEntreOficinasBean){
		for(ExistenciaDenominacionBean existenciaDenominacionBean : this.recepcionEntreOficinasBean.getParrilla().getListaDenominaciones()){
			for(ExistenciaDenominacionBean existenciaDenominacionBean2 : recepcionEntreOficinasBean.getListaDenominaciones()){
				if(existenciaDenominacionBean.getOrigen().equals(existenciaDenominacionBean2.getOrigen())
						&& existenciaDenominacionBean.getValorFacial().trim().equals(existenciaDenominacionBean2.getValorFacial())){
					
					existenciaDenominacionBean.setImporteEnviado(existenciaDenominacionBean2.getImporteEnviado());
					existenciaDenominacionBean.setImporteRecibido(existenciaDenominacionBean2.getImporteRecibido());
					
					actualizaUnidades(existenciaDenominacionBean);
				}
			}
		}
		limpiarParrillaOtrosDatos();
	}
	
	private void limpiarParrillaOtrosDatos(){
		for(ExistenciaDenominacionBean existenciaDenominacionBean : this.recepcionEntreOficinasBean.getParrilla().getListaDenominaciones()){
			if(existenciaDenominacionBean.getImporteRecibido() != null
					&& existenciaDenominacionBean.getImporteEnviado() != null
					&& existenciaDenominacionBean.getImporteRecibido().compareTo(BigDecimal.ZERO) == 0
					&& existenciaDenominacionBean.getImporteEnviado().compareTo(BigDecimal.ZERO) == 0){
				existenciaDenominacionBean.setImporteEnviado(null);
				existenciaDenominacionBean.setImporteRecibido(null);
				existenciaDenominacionBean.setUnidades(null);
			}
		}
	}

	public void actualizaUnidades(ExistenciaDenominacionBean denominacion) {
		if (denominacion.getValor() != null
				&& denominacion.getImporteRecibido() != null) {
			Long unidades = denominacion.getImporteRecibido()
					.divide(denominacion.getValor()).longValue();
			denominacion.setUnidades(unidades);
		}
		actualizaTotalRecibido();
		calculaDiferencia();
	}

	public void actualizaImporte(ExistenciaDenominacionBean denominacion) {
		if (denominacion.getValor() != null
				&& denominacion.getUnidades() != null) {
			BigDecimal importe = denominacion.getValor().multiply(
					new BigDecimal(denominacion.getUnidades()));
			denominacion.setImporteRecibido(importe);
		}
		actualizaTotalRecibido();
		calculaDiferencia();
	}

	public void actualizaTotalRecibido() {
		BigDecimal total = new BigDecimal(0.00);
		for (ExistenciaDenominacionBean existenciaDenominacionBean : this.recepcionEntreOficinasBean
				.getParrilla().getListaDenominaciones()) {
			if (existenciaDenominacionBean.getImporteRecibido() != null
					&& existenciaDenominacionBean.getImporteRecibido()
							.compareTo(BigDecimal.ZERO) > 0) {
				total = total.add(existenciaDenominacionBean
						.getImporteRecibido());
			}
		}
		this.recepcionEntreOficinasBean.setTotalRecibido(total);
		calculaDiferencia();
	}
	
	private void calculaDiferencia(){
		BigDecimal diferencia = new BigDecimal(0.00);
		diferencia = this.recepcionEntreOficinasBean.getTotalEnviado().subtract(this.recepcionEntreOficinasBean.getTotalRecibido());
		this.recepcionEntreOficinasBean.setDiferencias(diferencia);
	}
	
	public void realizarRecepcion() {
		if(recepcionEntreOficinasBean.getTotalRecibido().compareTo(BigDecimal.ZERO) > 0){
			try{
				if(!recepcionEntreOficinasBean.getEstatusC().trim().equals("R")){
					if(recepcionEntreOficinasBean.getEstatusC().trim().equals("F")){
						
						RecepcionEntreOficinasBean recepcionOficinas = recepcionEntreOficinasBackend.ejecutarTRN(recepcionEntreOficinasBean);
						
						if(recepcionOficinas != null){
							this.recepcionEntreOficinasBean.setTituloDetalle("RESUMEN DE RECEPCIÓN DE ");
							RequestContext.getCurrentInstance().execute("PF('dlgOperacionCorrecta').show();");
						}
					}else{
						mostrarMensajeAdvertencia("Para poder realizar la recepción de efectivo, el envío debe estar en estatus 'Confirmado'");
					}
					
				}else{
					if(recepcionEntreOficinasBean.getTotalRecibido().compareTo(recepcionEntreOficinasBean.getTotalRecibidoAbsoluto()) != 0){
						mostrarMensajeAdvertencia("El envío entre oficinas se encuentra en estatus 'Recibido, "
								+ "por lo que el importe total recibido no puede ser mayor o menor. "
								+ "Solamente se pueden cambiar las denominaciones de los valores.'");
					}else{
						RecepcionEntreOficinasBean recepcionOficinas = recepcionEntreOficinasBackend.ejecutarTRN(recepcionEntreOficinasBean);
						
						if(recepcionOficinas != null){
							this.recepcionEntreOficinasBean.setTituloDetalle("RESUMEN DE RECEPCIÓN DE ");
							RequestContext.getCurrentInstance().execute("PF('dlgOperacionCorrecta').show();");
						}
					}
				}
			}catch (ControlableException e){
				mostrarMensajeError(e.getMensajeUsuario() + " " + e.getMensajeDetalle());
			}
		}else{
			mostrarMensajeAdvertencia("El importe total recibido no puede ser cero.");
		}
	}
	
	public void printReport(){
		Map<String, String> images = new HashMap<String, String>();
		images.put("Logo_bsfi_bn.png", "LOGO");
		 
		Map<String, Object> params = new HashMap<String, Object>();
		
		final CatalogoBean oficina = catalogoCentrosLoaderTask.getCatalogoBean(contextoUtils.getEntidad(), contextoUtils.getSucursal());
		final CatalogoBean plaza = catalogoUtils.getCatalogoBean(CatalogoEnum.TP_PLAZA_BANCARIA, contextoUtils.getPlazaBancaria());
		
		params.put("FECHA_REPORTE", new Date());
		params.put("OFICINA", oficina.getDescripcionL());
		params.put("PLAZA", plaza.getDescripcionL());
		params.put("USUARIO", contextoUtils.getNombre());
		params.put("TERMINAL", contextoUtils.getTerminal());
		params.put("TOTAL_RECIBIDO", recepcionEntreOficinasBean.getTotalRecibido());
		params.put("CENTRO_ORIGEN", recepcionEntreOficinasBean.getCentroOrigen());
		params.put("FECHA_ENVIO", recepcionEntreOficinasBean.getFechaEnvio());
		params.put("ESTATUS", recepcionEntreOficinasBean.getEstatusL());
		params.put("TITULO", recepcionEntreOficinasBean.getTituloDetalle() + " " + formateaNumero(recepcionEntreOficinasBean.getTotalRecibido()));
		
		final List<ParrillaBean> listaBeans = new ArrayList<ParrillaBean>();
		listaBeans.add(parrilla);
		
		final Map<String, String> subReportes = new HashMap<String, String>();
		subReportes.put("Denominacion.jrxml", "SUBREPORT_DENOMINACION");
			
		pdfUtils.generaPdf("DetalleRecepcionEntreOficinas.jrxml", params, images, subReportes, "DetalleRecepcionEntreOficinas", listaBeans);
	}
	
	public String formateaNumero(BigDecimal valor){
		DecimalFormat formateador = new DecimalFormat("#,##0.00");
		return formateador.format(valor) + " MXN";
	}

	private void acomodarParrilla(){
		RequestContext.getCurrentInstance().execute("resizeParrillaColumns();");
	}
	
	public ParrillaBean getParrilla() {
		return parrilla;
	}

	public void setParrilla(ParrillaBean parrilla) {
		this.parrilla = parrilla;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	public String getMensajePrecaucion() {
		return mensajePrecaucion;
	}

	public void setMensajePrecaucion(String mensajePrecaucion) {
		this.mensajePrecaucion = mensajePrecaucion;
	}

	public String getMensajeInfo() {
		return mensajeInfo;
	}

	public void setMensajeInfo(String mensajeInfo) {
		this.mensajeInfo = mensajeInfo;
	}

	public Date getFechaSistema() {
		return fechaSistema;
	}

	public void setFechaSistema(Date fechaSistema) {
		this.fechaSistema = fechaSistema;
	}

	public Date getFechaSistemaSiguiente() {
		return fechaSistemaSiguiente;
	}

	public void setFechaSistemaSiguiente(Date fechaSistemaSiguiente) {
		this.fechaSistemaSiguiente = fechaSistemaSiguiente;
	}

	public List<CatalogoBean> getListaCentros() {
		return listaCentros;
	}

	public void setListaCentros(List<CatalogoBean> listaCentros) {
		this.listaCentros = listaCentros;
	}

	public Boolean getEstatusVisible() {
		return estatusVisible;
	}

	public void setEstatusVisible(Boolean estatusVisible) {
		this.estatusVisible = estatusVisible;
	}

	public Boolean getEditable() {
		return editable;
	}

	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	public Boolean getFiltro() {
		return filtro;
	}

	public void setFiltro(Boolean filtro) {
		this.filtro = filtro;
	}

	public Boolean getFiltoDisabled() {
		return filtoDisabled;
	}

	public void setFiltoDisabled(Boolean filtoDisabled) {
		this.filtoDisabled = filtoDisabled;
	}

	public Boolean getPrecinto() {
		return precinto;
	}

	public void setPrecinto(Boolean precinto) {
		this.precinto = precinto;
	}

	public Boolean getUnidadesDisabled() {
		return unidadesDisabled;
	}

	public void setUnidadesDisabled(Boolean unidadesDisabled) {
		this.unidadesDisabled = unidadesDisabled;
	}

	public Boolean getImporteRecibidoDisabled() {
		return importeRecibidoDisabled;
	}

	public void setImporteRecibidoDisabled(Boolean importeRecibidoDisabled) {
		this.importeRecibidoDisabled = importeRecibidoDisabled;
	}

	public Boolean getObservacionesDisabled() {
		return observacionesDisabled;
	}

	public void setObservacionesDisabled(Boolean observacionesDisabled) {
		this.observacionesDisabled = observacionesDisabled;
	}
	
	public Boolean getMostrarBotones() {
		return mostrarBotones;
	}

	public void setMostrarBotones(Boolean mostrarBotones) {
		this.mostrarBotones = mostrarBotones;
	}

	public Boolean getFechaDisabled() {
		return fechaDisabled;
	}

	public void setFechaDisabled(Boolean fechaDisabled) {
		this.fechaDisabled = fechaDisabled;
	}

	public BigDecimal getZero() {
		return zero;
	}

	public void setZero(BigDecimal zero) {
		this.zero = zero;
	}

	private void parrillaDisabled(){
		setEstatusVisible(false);
		setFiltoDisabled(true);
		setUnidadesDisabled(true);
		setImporteRecibidoDisabled(true);
		setObservacionesDisabled(true);
	}
	
	private void mostrarMensajeError(String mensaje){
		setMensajeError(mensaje);
		RequestContext.getCurrentInstance().execute("PF('dlgErrorRecep').show();");
		RequestContext.getCurrentInstance().update("dlgErrorRecep");
	}
	
	private void mostrarMensajeAdvertencia(String mensaje){
		setMensajePrecaucion(mensaje);
		RequestContext.getCurrentInstance().execute("PF('dlgAdvertenciaRecep').show();");
		RequestContext.getCurrentInstance().update("dlgAdvertenciaRecep");
	}
	
	private void mostrarMensajeInfo(String mensaje){
		setMensajeInfo(mensaje);
		RequestContext.getCurrentInstance().execute("PF('dlgInfoRecep').show();");
		RequestContext.getCurrentInstance().update("dlgInfoRecep");
	}
	
	public void mostrarMensajeConfirmacion(){
		RequestContext.getCurrentInstance().execute("PF('dlgRecepcion').show();");
		RequestContext.getCurrentInstance().update("dlgRecepcion");
	}

	public void limpiar() {
		recepcionEntreOficinasBean.setCentroOrigen("");
		recepcionEntreOficinasBean.setTotalAutorizado(new BigDecimal(0.00));
		recepcionEntreOficinasBean.setTotalCertificado(new BigDecimal(0.00));
		recepcionEntreOficinasBean.setTotalEnviado(new BigDecimal(0.00));
		recepcionEntreOficinasBean.setTotalRecibido(new BigDecimal(0.00));
		recepcionEntreOficinasBean.setTotalRecibidoAbsoluto(new BigDecimal(0.00));
		recepcionEntreOficinasBean.setDiferencias(new BigDecimal(0.00));
		recepcionEntreOficinasBean.setObservaciones("");
		recepcionEntreOficinasBean.setFechaEnvio(cajaUtilsBean.getFechaSistema());
		recepcionEntreOficinasBean.setFechaEnvioInteger(cajaUtilsBean.getFechaSistemaInteger());
		recepcionEntreOficinasBean.getParrilla().setFiltro(0);
		recepcionEntreOficinasBean.setEstatusC("");
		recepcionEntreOficinasBean.setEstatusL("");
		setUnidadesDisabled(true);
		setImporteRecibidoDisabled(true);
		setMostrarBotones(false);
		setEstatusVisible(false);
		setFiltoDisabled(true);
		setFechaDisabled(false);
		limpiarParrilla();
	}
	
	private void limpiarParrilla(){
		for(ExistenciaDenominacionBean existenciaDenominacionBean : this.recepcionEntreOficinasBean.getParrilla().getListaDenominaciones()){
			existenciaDenominacionBean.setImporteRecibido(null);
			existenciaDenominacionBean.setUnidades(null);
			existenciaDenominacionBean.setImporteAEnviar(null);
			existenciaDenominacionBean.setImporteEnviado(null);
		}
	}
	
	public void realizarOtraBusqueda(){
		limpiar();
		setFechaDisabled(false);
	}
}

