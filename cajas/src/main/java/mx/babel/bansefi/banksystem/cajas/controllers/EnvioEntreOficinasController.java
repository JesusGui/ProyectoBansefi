package mx.babel.bansefi.banksystem.cajas.controllers;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
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
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.cajas.backend.AltaEnvioEntreOficinasBackEnd;
import mx.babel.bansefi.banksystem.cajas.backend.ConfirmarEnvioEntreOficinasBackEnd;
import mx.babel.bansefi.banksystem.cajas.backend.ConsultaEnvioEntreOficinasBackEnd;
import mx.babel.bansefi.banksystem.cajas.backend.ConsultaParillaBilletesBackend;
import mx.babel.bansefi.banksystem.cajas.backend.ModificarEnvioEntreOficinasBackEnd;
import mx.babel.bansefi.banksystem.cajas.backend.SuprimirEnvioEntreOficinasBackEnd;
import mx.babel.bansefi.banksystem.cajas.beans.CajaUtilsBean;
import mx.babel.bansefi.banksystem.cajas.beans.EnvioEntreOficinasBean;
import mx.babel.bansefi.banksystem.cajas.enums.EstadoPeticionEnum;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@ManagedBean(name="envioEntreOficinasController")
@Component
@Scope("view")
public class EnvioEntreOficinasController implements Serializable {
	
	private static final long serialVersionUID = -3756923947671132928L;
	
	@Autowired
	CajaUtilsBean cajaUtilsBean;
	@Autowired
	ContextoUtils contextoUtils;
	@Autowired
	ConsultaParillaBilletesBackend consultaParillaBilletesBackend;
	@Autowired
	ConsultaEnvioEntreOficinasBackEnd consultaEnvioEntreOficinasBackEnd;
	@Autowired
	AltaEnvioEntreOficinasBackEnd altaEnvioEntreOficinasBackEnd;
	@Autowired
	ModificarEnvioEntreOficinasBackEnd modificarEnvioEntreOficinasBackEnd;
	@Autowired
	SuprimirEnvioEntreOficinasBackEnd suprimirEnvioEntreOficinasBackEnd;
	@Autowired
	ConfirmarEnvioEntreOficinasBackEnd confirmarEnvioEntreOficinasBackEnd;
	@Autowired
	CatalogoUtils catalogoUtils;
	
	private EnvioEntreOficinasBean envioEntreOficinasBean;
	
	private Integer codigoRetorno;
	
	private String mensajeDialogo;
	private String mensajeError;
	private String mensajeAdvertencia;
	
	private BigDecimal ZERO = BigDecimal.ZERO; 

	public EnvioEntreOficinasBean getEnvioEntreOficinasBean() {
		return envioEntreOficinasBean;
	}

	public void setEnvioEntreOficinasBean(
			EnvioEntreOficinasBean envioEntreOficinasBean) {
		this.envioEntreOficinasBean = envioEntreOficinasBean;
	}

	public String getMensajeDialogo() {
		return mensajeDialogo;
	}

	public void setMensajeDialogo(String mensajeDialogo) {
		this.mensajeDialogo = mensajeDialogo;
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
	
	public BigDecimal getZERO() {
		return ZERO;
	}

	public void setZERO(BigDecimal zERO) {
		ZERO = zERO;
	}

	public EnvioEntreOficinasController(){
		this.envioEntreOficinasBean = new EnvioEntreOficinasBean();
		this.envioEntreOficinasBean.setParrilla(new ParrillaBean());
		this.envioEntreOficinasBean.getParrilla().iniciaParrilla(true);
		this.envioEntreOficinasBean.getParrilla().setImporteAEditar(ColumnaParrillaEnum.IMPORTE_A_ENVIAR);
		this.envioEntreOficinasBean.setTotalAEnviar(new BigDecimal(0.00));
		this.envioEntreOficinasBean.setTipoFechaEnvio(1);
		this.envioEntreOficinasBean.setCentroDestino("");
	}

	@PostConstruct
	public void init(){
		if(this.obtieneFlash().get(ParametrosFlashEnum.ENVIO_ENTRE_OFICINAS_BEAN.getParamFlash()) != null){
			this.envioEntreOficinasBean = (EnvioEntreOficinasBean) this.obtieneFlash().get(ParametrosFlashEnum.ENVIO_ENTRE_OFICINAS_BEAN.getParamFlash());
			if(envioEntreOficinasBean.getEstadoPeticionEnum() == EstadoPeticionEnum.ELIMINADO){
				regresarModoConsulta();
			}else if(this.envioEntreOficinasBean.getEstadoPeticionEnum() == EstadoPeticionEnum.MODIFICADO
					|| this.envioEntreOficinasBean.getEstadoPeticionEnum() == EstadoPeticionEnum.NUEVO
					|| this.envioEntreOficinasBean.getEstadoPeticionEnum() == EstadoPeticionEnum.CONFIRMADO){
				regresarModoConsulta();
				consultarEnvio();
			}
		}else{
			consultaParillaBilletesBackend.ejecutarTRN(this.envioEntreOficinasBean.getParrilla().getListaDenominaciones());
			this.envioEntreOficinasBean.getParrilla().setListaDenominaciones(consultaParillaBilletesBackend.getListaExistencias());
			this.envioEntreOficinasBean.setFechaContableEnvio(cajaUtilsBean.getFechaSistema());
			this.envioEntreOficinasBean.setFechaContableEnvioInteger(cajaUtilsBean.getFechaSistemaInteger());
		}
		this.envioEntreOficinasBean.setCentroOrigen(contextoUtils.getSucursal());
	}
	
	public String inicio(){
		return NavegacionEnum.ENVIO_ENTRE_OFICINAS.getRuta();
	}
	
	/**
     * Se encarga de obtener el flash.
     * 
     * @return Flash con los datos de la pagina
     */
	public Flash obtieneFlash() {
		 return FacesContext.getCurrentInstance().getExternalContext().getFlash();
	}
	 
	public String irInicio(){
		return NavegacionEnum.INICIO.getRuta();
	}
	
	public String irDetalle(){
		String ruta = "";
		if(codigoRetorno.equals(1)){
			insertaDatosFlash();
			ruta = NavegacionEnum.DETALLE_ENVIO_ENTRE_OFICINAS.getRuta();
		}
		return ruta;
	}
	
	public void cambiaFechaEnvio(){
		ocultarBotones();
		if(envioEntreOficinasBean.getTipoFechaEnvio() == 1){
			envioEntreOficinasBean.setFechaContableEnvio(cajaUtilsBean.getFechaSistema());
			envioEntreOficinasBean.setFechaContableEnvioInteger(cajaUtilsBean.getFechaSistemaInteger());
			if(envioEntreOficinasBean.getEstatusC() == null){
				limpiarSinParrilla();
			}else{
				limpiarSinCentro();
			}
		}else{
			envioEntreOficinasBean.setFechaContableEnvio(cajaUtilsBean.getFechaContableSiguiente());
			envioEntreOficinasBean.setFechaContableEnvioInteger(cajaUtilsBean.getFechaContableSiguienteInteger());
			limpiarSinCentro();
		}
	}
	
	public void consultarEnvio(){
		try{
			
			limpiarParrilla();
			envioEntreOficinasBean.setTotalAEnviar(new BigDecimal(0.00));
			envioEntreOficinasBean.setObservaciones("");
			envioEntreOficinasBean.setEstatusC("");
			envioEntreOficinasBean.setEstatusL("");
			envioEntreOficinasBean.getParrilla().setFiltro(0);
			
			consultarEnvioEntreOficinas();
			
			actualizaTotalEnvio();
			
			if(envioEntreOficinasBean.getEstatusC() != null){
				if(envioEntreOficinasBean.getEstatusC().equals("S")){
					envioEntreOficinasBean.setTituloBoton("Modificar");
					envioEntreOficinasBean.setUnidadesDisabled(false);
					envioEntreOficinasBean.setImporteDisabled(false);
					envioEntreOficinasBean.setPrecintoDisabled(false);
					envioEntreOficinasBean.setFiltroDisabled(false);
					envioEntreOficinasBean.setObservacionDisabled(false);
					envioEntreOficinasBean.setBotonConfirmarvisible(true);
					envioEntreOficinasBean.setBotonGuardarVisible(true);
					envioEntreOficinasBean.setBotonSuprimirVisible(true);
					envioEntreOficinasBean.setBotonLimpiarVisible(true);
					if(envioEntreOficinasBean.getEstadoPeticionEnum() == EstadoPeticionEnum.MODIFICADO
						|| envioEntreOficinasBean.getEstadoPeticionEnum() == EstadoPeticionEnum.NUEVO
						&& envioEntreOficinasBean.getEditable() == false){
							envioEntreOficinasBean.setEditable(true);
					}
				}else if(envioEntreOficinasBean.getEstatusC().equals("F")){
					envioEntreOficinasBean.setUnidadesDisabled(false);
					envioEntreOficinasBean.setImporteDisabled(false);
					envioEntreOficinasBean.setPrecintoDisabled(false);
					envioEntreOficinasBean.setFiltroDisabled(false);
					envioEntreOficinasBean.setObservacionDisabled(false);
					envioEntreOficinasBean.setBotonConfirmarvisible(true);
					envioEntreOficinasBean.setBotonGuardarVisible(false);
					envioEntreOficinasBean.setBotonSuprimirVisible(true);
					envioEntreOficinasBean.setBotonLimpiarVisible(true);
					if(envioEntreOficinasBean.getEstadoPeticionEnum() == EstadoPeticionEnum.MODIFICADO
						|| envioEntreOficinasBean.getEstadoPeticionEnum() == EstadoPeticionEnum.NUEVO
						|| envioEntreOficinasBean.getEstadoPeticionEnum() == EstadoPeticionEnum.CONFIRMADO
						&& envioEntreOficinasBean.getEditable() == false){
							envioEntreOficinasBean.setEditable(true);
					}
				}else{
					envioEntreOficinasBean.setBotonSuprimirVisible(false);
					envioEntreOficinasBean.setBotonConfirmarvisible(false);
					envioEntreOficinasBean.setBotonGuardarVisible(false);
					envioEntreOficinasBean.setBotonLimpiarVisible(false);
					envioEntreOficinasBean.setBotonSuprimirVisible(false);
					envioEntreOficinasBean.setObservacionDisabled(true);
					envioEntreOficinasBean.setUnidadesDisabled(true);
					envioEntreOficinasBean.setImporteDisabled(true);
					envioEntreOficinasBean.setPrecintoDisabled(true);
				}
			}else{
				envioEntreOficinasBean.setUnidadesDisabled(true);
				envioEntreOficinasBean.setImporteDisabled(true);
				envioEntreOficinasBean.setPrecintoDisabled(true);
				envioEntreOficinasBean.setFiltroDisabled(false);
				envioEntreOficinasBean.setObservacionDisabled(true);
				envioEntreOficinasBean.setBotonGuardarVisible(false);
				envioEntreOficinasBean.setBotonLimpiarVisible(false);
			}
			for(ExistenciaDenominacionBean existencia : envioEntreOficinasBean.getParrilla().getListaDenominaciones()){
				if(existencia.getImporteAEnviar() != null
						&& existencia.getImporteAEnviar().compareTo(BigDecimal.ZERO) <= 0){
					existencia.setImporteAEnviar(null);
					existencia.setUnidades(null);
					existencia.setPrecinto("");
				}
			}
			
			if(envioEntreOficinasBean.getEstatusC().equals("R")){
				BigDecimal total = new BigDecimal(0.00);
				for(ExistenciaDenominacionBean existencia : envioEntreOficinasBean.getParrilla().getListaDenominaciones()){
					if(existencia.getImporteRecibido() != null && existencia.getImporteRecibido().compareTo(BigDecimal.ZERO) > 0){
						existencia.setImporteAEnviar(existencia.getImporteRecibido());
						envioEntreOficinasBean.getParrilla().actualizaUnidades(existencia, existencia.getImporteAEnviar());
						total = total.add(existencia.getImporteAEnviar());
					}
				}
				this.envioEntreOficinasBean.setTotalAEnviar(total);
				actualizaTotalEnvio();
			}
		}catch(ControlableException ce){
			if(ce.getRtncod() == 7){
				limpiarSinCentro();
				envioEntreOficinasBean.setTituloBoton("Guardar");
				envioEntreOficinasBean.setUnidadesDisabled(false);
				envioEntreOficinasBean.setImporteDisabled(false);
				envioEntreOficinasBean.setPrecintoDisabled(false);
				envioEntreOficinasBean.setFiltroDisabled(false);
				envioEntreOficinasBean.setBotonConfirmarvisible(false);
				envioEntreOficinasBean.setBotonGuardarVisible(true);
				envioEntreOficinasBean.setBotonSuprimirVisible(false);
				envioEntreOficinasBean.setObservacionDisabled(false);
				envioEntreOficinasBean.setBotonLimpiarVisible(true);
			}else if(ce.getRtncod() != 1 && ce.getRtncod() != 7){
				muestraMensajeError(ce.getMensajeDetalle());
			}
		}
	}
	
	private void consultarEnvioEntreOficinas() throws NoControlableException, ControlableException{	
		consultaEnvioEntreOficinasBackEnd.ejecutarTRN(envioEntreOficinasBean);
	}
	
	private Boolean validaMismoCentro(){
		Boolean mismoCentro = false;
		if(envioEntreOficinasBean.getCentroOrigen().equals(envioEntreOficinasBean.getCentroDestino())){
			mismoCentro = true;
		}
		return mismoCentro;
	}
	
	public void altaEnvioEntreOficinas(){
		if(validaMismoCentro()){
				setMensajeDialogo("No se pueden realizar envíos entre la misma oficina.");
				RequestContext.getCurrentInstance().execute("PF('dlgMismoCentro').show()");
		}else{
			try{
				codigoRetorno = this.ejecutarTRNAlta();
				envioEntreOficinasBean.setTituloResumen("RESUMEN DE ALTA DE ENVÍO ENTRE OFICINAS POR VALOR DE");
				envioEntreOficinasBean.setEstadoPeticionEnum(EstadoPeticionEnum.NUEVO);
				RequestContext.getCurrentInstance().execute("PF('dlgOperacionExitosa').show()");
				
			}catch (ControlableException ce){
				if(ce.getRtncod() != 1){
					muestraMensajeError(ce.getMensajeUsuario() + " " + ce.getMensajeDetalle());
				}
			}
		}
	}
	
	public void modificaEnvioEntreOficinas(){
		try{
			codigoRetorno = this.ejecutarTRNModificacion();
			
			envioEntreOficinasBean.setTituloResumen("RESUMEN DE MODIFICACIÓN DE ENVÍO ENTRE OFICINAS POR VALOR DE");
			envioEntreOficinasBean.setEstadoPeticionEnum(EstadoPeticionEnum.MODIFICADO);
			RequestContext.getCurrentInstance().execute("PF('dlgOperacionExitosa').show()");
			
		}catch (ControlableException ce){
			muestraMensajeError(ce.getMensajeUsuario() + " " + ce.getMensajeDetalle());
		}
	}
	
	public void suprimirEnvioEntreOficinas(){
		try{
			codigoRetorno = this.ejecutarTRNSuprimir();
			
			envioEntreOficinasBean.setTituloResumen("RESUMEN DE ELIMINACIÓN DE ENVÍO ENTRE OFICINAS POR VALOR DE");
			envioEntreOficinasBean.setEstadoPeticionEnum(EstadoPeticionEnum.ELIMINADO);
			RequestContext.getCurrentInstance().execute("PF('dlgOperacionExitosa').show()");
			
		}catch (ControlableException ce){
			if(ce.getRtncod() != 1){
				muestraMensajeError(ce.getMensajeUsuario() + " " + ce.getMensajeDetalle());
			}
		}
	}
	
	public void confirmarEnvioEntreOficinas(){
		try{
			codigoRetorno = this.ejecutarTRNConfirmacion();
			
			envioEntreOficinasBean.setTituloResumen("RESUMEN DE CONFIRMACIÓN DE ENVÍO ENTRE OFICINAS POR VALOR DE");
			envioEntreOficinasBean.setEstadoPeticionEnum(EstadoPeticionEnum.CONFIRMADO);
			
			final CatalogoBean catalogo = catalogoUtils.getCatalogoBean(CatalogoEnum.ESTATUS_PETICION, envioEntreOficinasBean.getEstatusC());
			if (catalogo != null) {
				envioEntreOficinasBean.setEstatusL(catalogo.getDescripcionL());
			} else {
				envioEntreOficinasBean.setEstatusL("");
			}
			RequestContext.getCurrentInstance().execute("PF('dlgOperacionExitosa').show()");
			
		}catch (ControlableException ce){
			if(ce.getRtncod() != 1){
				muestraMensajeError(ce.getMensajeUsuario() + " " + ce.getMensajeDetalle());
			}
		}
	}
	
	public Boolean validaCamposParrilla(){
		Boolean valido = false;
		if(envioEntreOficinasBean.getTotalAEnviar().compareTo(BigDecimal.ZERO) == 1){
			valido = true;
		}
		return valido;
	}
	
	public void validaTipoGuardado(){
		if(null == envioEntreOficinasBean.getEstatusC()
				|| envioEntreOficinasBean.getEstatusC().equals("")){
			if(validaCamposParrilla()){
				RequestContext.getCurrentInstance().execute("PF('dlgAlta').show()");
			}else{
				muestraMensajeAlerta("Debes ingresar al menos un dato en el campo unidades o importe a enviar.");
			}
		}else{
			if(validaCamposParrilla()){
				RequestContext.getCurrentInstance().execute("PF('dlgModificar').show()");
			}else{
				muestraMensajeAlerta("Debes ingresar al menos un dato en el campo unidades o importe a enviar.");
			}
		}
	}

	public void actualizaUnidades(ExistenciaDenominacionBean denominacion) {
		if (denominacion.getValor() != null && denominacion.getImporteAEnviar() != null) {
			Long unidades = denominacion.getImporteAEnviar().divide(denominacion.getValor()).longValue();
			denominacion.setUnidades(unidades);
		}
		actualizaTotalEnvio();
	}

	public void actualizaImporte(ExistenciaDenominacionBean denominacion) {
		if (denominacion.getValor() != null
				&& denominacion.getUnidades() != null) {
			BigDecimal importe = denominacion.getValor().multiply(
					new BigDecimal(denominacion.getUnidades()));
			denominacion.setImporteAEnviar(importe);
		}
		actualizaTotalEnvio();
	}
	
	public void actualizaTotalEnvio(){
		BigDecimal total = new BigDecimal(0.00);
		for(ExistenciaDenominacionBean existenciaDenominacionBean : envioEntreOficinasBean.getParrilla().getListaDenominaciones()){
			if(existenciaDenominacionBean.getImporteAEnviar() != null
					&& existenciaDenominacionBean.getImporteAEnviar().compareTo(BigDecimal.ZERO) > 0){
				total = total.add(existenciaDenominacionBean.getImporteAEnviar());
			}
		}
		
		this.envioEntreOficinasBean.setTotalAEnviar(total);
	}
	
	private void muestraMensajeError(String mensaje) {
		setMensajeError(mensaje);
		RequestContext.getCurrentInstance().execute("PF('dlgError1').show();");
		RequestContext.getCurrentInstance().update("dlgError1");
	}
	
	private void muestraMensajeAlerta(String mensaje) {
		setMensajeDialogo(mensaje);
		RequestContext.getCurrentInstance().execute("PF('dlgMismoCentro').show();");
		RequestContext.getCurrentInstance().update("dlgMismoCentro");
	}
	
	@SuppressWarnings("unused")
	private void muestraMensajeInformativo(String mensaje) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"", mensaje);
		RequestContext.getCurrentInstance().showMessageInDialog(message);
	}
	
	private Integer ejecutarTRNAlta() throws NoControlableException, ControlableException{
		Integer codigoRetorno = altaEnvioEntreOficinasBackEnd.ejecutarTRN(envioEntreOficinasBean);
		return codigoRetorno;
	}
	
	private Integer ejecutarTRNModificacion() throws NoControlableException, ControlableException{
		Integer codigoRetorno = modificarEnvioEntreOficinasBackEnd.ejecutarTRN(envioEntreOficinasBean);
		return codigoRetorno;
	}
	
	private Integer ejecutarTRNSuprimir() throws NoControlableException, ControlableException{
		Integer codigoRetorno = suprimirEnvioEntreOficinasBackEnd.ejecutarTRN(envioEntreOficinasBean);
		return codigoRetorno;
	}
	
	private Integer ejecutarTRNConfirmacion() throws NoControlableException, ControlableException{
		Integer codigoRetorno = confirmarEnvioEntreOficinasBackEnd.ejecutarTRN(envioEntreOficinasBean);
		return codigoRetorno;
	}
	
	private void regresarModoConsulta(){
		if(envioEntreOficinasBean.getEstadoPeticionEnum() == EstadoPeticionEnum.ELIMINADO){
			envioEntreOficinasBean = new EnvioEntreOficinasBean();
			envioEntreOficinasBean.setTipoFechaEnvio(1);
			cambiaFechaEnvio();
		}
		envioEntreOficinasBean.setParrilla(new ParrillaBean());
		envioEntreOficinasBean.getParrilla().iniciaParrilla(true);
		envioEntreOficinasBean.getParrilla().setImporteAEditar(ColumnaParrillaEnum.IMPORTE_A_ENVIAR);
		envioEntreOficinasBean.setTotalAEnviar(new BigDecimal(0.00));
		
		consultaParillaBilletesBackend.ejecutarTRN(envioEntreOficinasBean.getParrilla().getListaDenominaciones());
		envioEntreOficinasBean.getParrilla().setListaDenominaciones(consultaParillaBilletesBackend.getListaExistencias());
	}
	
	public void limpiar() {
		limpiarParrilla();
		envioEntreOficinasBean.setCentroDestino("");
		envioEntreOficinasBean.setTotalAEnviar(new BigDecimal(0.00));
		envioEntreOficinasBean.setObservaciones(null);
		envioEntreOficinasBean.setObservaciones("");
		envioEntreOficinasBean.setObservaciones(new String());
		envioEntreOficinasBean.setEstatusC("");
		envioEntreOficinasBean.setEstatusL("");
		envioEntreOficinasBean.setTipoFechaEnvio(1);
		envioEntreOficinasBean.getParrilla().setFiltro(0);
		envioEntreOficinasBean.setFechaContableEnvio(cajaUtilsBean.getFechaSistema());
		envioEntreOficinasBean.setFechaContableEnvioInteger(cajaUtilsBean.getFechaSistemaInteger());
		envioEntreOficinasBean.setFiltroDisabled(true);
		envioEntreOficinasBean.setUnidadesDisabled(true);
		envioEntreOficinasBean.setImporteDisabled(true);
		envioEntreOficinasBean.setPrecintoDisabled(true);
		envioEntreOficinasBean.setObservacionDisabled(true);
		ocultarBotones();
		RequestContext.getCurrentInstance().update("formEnvioEntreOficinas");
		RequestContext.getCurrentInstance().update("fechaContableEnvio");
		RequestContext.getCurrentInstance().execute("resizeParrillaColumns()");
	}
	
	public void limpiarSinCentro(){
		limpiarParrilla();
		envioEntreOficinasBean.setTotalAEnviar(new BigDecimal(0.00));
		envioEntreOficinasBean.setObservaciones("");
		envioEntreOficinasBean.setObservaciones(null);
		envioEntreOficinasBean.setObservaciones(new String());
		envioEntreOficinasBean.setEstatusC("");
		envioEntreOficinasBean.setEstatusL("");
		envioEntreOficinasBean.getParrilla().setFiltro(0);
		envioEntreOficinasBean.setUnidadesDisabled(true);
		envioEntreOficinasBean.setImporteDisabled(true);
		envioEntreOficinasBean.setPrecintoDisabled(true);
		RequestContext.getCurrentInstance().execute("resizeParrillaColumns()");
		RequestContext.getCurrentInstance().update("fechaContableEnvio");
		RequestContext.getCurrentInstance().update("formEnvioEntreOficinas");
	}
	
	public void limpiarSinParrilla(){
		envioEntreOficinasBean.setTotalAEnviar(new BigDecimal(0.00));
		envioEntreOficinasBean.setObservaciones("");
		envioEntreOficinasBean.setObservaciones(null);
		envioEntreOficinasBean.setObservaciones(new String());
		envioEntreOficinasBean.setEstatusC("");
		envioEntreOficinasBean.setEstatusL("");
	}
	
	private void limpiarParrilla(){
		for(ExistenciaDenominacionBean existenciaDenominacionBean : this.envioEntreOficinasBean.getParrilla().getListaDenominaciones()){
			existenciaDenominacionBean.setImporteAEnviar(null);
			existenciaDenominacionBean.setUnidades(null);
			existenciaDenominacionBean.setPrecinto(null);
		}
	}
	
	public void ocultarBotones(){
		this.envioEntreOficinasBean.setBotonConfirmarvisible(false);
		this.envioEntreOficinasBean.setBotonGuardarVisible(false);
		this.envioEntreOficinasBean.setBotonSuprimirVisible(false);
		this.envioEntreOficinasBean.setBotonLimpiarVisible(false);
	}
	
	public void mostrarBotones(){
		this.envioEntreOficinasBean.setBotonConfirmarvisible(true);
		this.envioEntreOficinasBean.setBotonGuardarVisible(true);
		this.envioEntreOficinasBean.setBotonSuprimirVisible(true);
		this.envioEntreOficinasBean.setBotonLimpiarVisible(true);
	}
	
	private void insertaDatosFlash(){
		this.obtieneFlash().put(ParametrosFlashEnum.ENVIO_ENTRE_OFICINAS_BEAN.getParamFlash(), this.envioEntreOficinasBean);
	}
}
