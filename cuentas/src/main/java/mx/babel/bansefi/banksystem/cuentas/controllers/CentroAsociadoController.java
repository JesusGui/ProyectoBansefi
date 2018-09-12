package mx.babel.bansefi.banksystem.cuentas.controllers;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.EstadosCuentaEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoCentrosLoaderTask;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;
import mx.babel.bansefi.banksystem.cuentas.backends.AgregarCentroAsociadoBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaCentroAsociadoBackend;
import mx.babel.bansefi.banksystem.cuentas.beans.AgregarCentroAsociadoBean;
import mx.babel.bansefi.banksystem.cuentas.beans.CentroAsociadoBean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Define las acciones que pueden realizar con Centro Asociado
 * 
 * @author Natalio Iván Mendoza Sánchez &lt;nmendoza@uanlmexico.mx&gt;
 * @category Controller
 */
@ManagedBean
@ViewScoped
@Component
@Scope("view")
public class CentroAsociadoController implements Serializable {

	private static final long serialVersionUID = 106226089873338800L;
	private static final Logger LOGGER = LogManager.getLogger(CentroAsociadoController.class);
	
	private String numeroCuenta, centroAuto,codRel;
	private String centroAnterior;
	private Date fechaAnterior;

	private CuentaBean cuentaBean;
	private List<CentroAsociadoBean> centroAsociado;
	private AgregarCentroAsociadoBean agregarCentroAsociadoBean = new AgregarCentroAsociadoBean();

	/**
	 * Variable para recuperar el catalogBean de CENTRO elegido en la
	 * modificacion de cliente
	 */
	private CatalogoBean centroSelected;

	@Autowired
	ContextoUtils contextoUtils;

	@Autowired
	ConsultaCentroAsociadoBackend consultaCentroAsociadoBackend;

	@Autowired
	AgregarCentroAsociadoBackend agregarCentroAsociadoBackend;

	@Autowired
	CatalogoUtils catalogoUtils;

	@Autowired
	CatalogoCentrosLoaderTask catalogoCentros;
	@Autowired
	ManagedBeanStateRecover managedBeanStateRecover;

	@PostConstruct
	public void init() {

		centroAsociado = new ArrayList<>();

		try {
			if (obtieneFlash().get(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash()) == null){
				throw new NoControlableException(
						"Error al llamar a TRN de consulta de centro asociado con cuenta vacía",
						"");
			}

			cuentaBean = (CuentaBean) obtieneFlash().get(
					ParametrosFlashEnum.CUENTA_BEAN.getParamFlash());
				numeroCuenta=""+cuentaBean.getNumeroCuenta();
				centroAsociado = consultaCentroAsociadoBackend.ejecutarTRN(
						Long.parseLong(numeroCuenta), codRel, "03");

				for (CentroAsociadoBean fechas : centroAsociado) {
					fechaAnterior = fechas.getFechaInicio();
					centroAnterior = fechas.getCentro();
					codRel=fechas.getCodRel();
				}
			

		} catch (Exception ex){
			LOGGER.error("Error al iniciar el controller de centro asociado.",ex);
		}
	}

	/**
	 * Obtiene el centro asociado que fue pasado como parámentro Flash
	 * 
	 * @return <code>String</code> con el centro asociado que será consultada
	 * @author Natalio Iván Mendoza Sánchez &lt;nmendoza@uanlmexico.mx&gt;
	 */
	public String getCentroAsociadoTipoRelacion(String structureId) {
		structureId = "01";
		String description = "N/D";
		List<CatalogoBean> catBalance = catalogoUtils
				.getCatalogo(CatalogoEnum.TP_RL_AC_UO);
		for (CatalogoBean dato : catBalance) {
			if (dato.getClaveFila().toLowerCase()
					.equals(structureId.replace('0', ' ').trim()))
				return dato.getDescripcionC();
		}

		return description;
	}

	/**
	 * Obtiene la fecha actual del sistema
	 * 
	 * @return <code>Date</code> con la fecha actual del sistema
	 * @author Natalio Iván Mendoza Sánchez &lt;nmendoza@uanlmexico.mx&gt;
	 */
	public String getCambioEfectivo() {

		Date cambioefectivo = contextoUtils.getFechaContableSiguiente();
		return new SimpleDateFormat("dd/MM/yyyy").format(cambioefectivo);
	}

	public String enviaCentro() {
			int proximaFechaContable = Integer.parseInt(new SimpleDateFormat("yyyyMMdd").
					format(contextoUtils.getFechaContableSiguiente()));
			int fechaActivacion = Integer.parseInt(new SimpleDateFormat("yyyyMMdd").
					format(fechaAnterior));
			String agregarCentro = agregarCentroAsociadoBean.getCentroAgregado();

			if (agregarCentro.length() > 4) {
				FacesContext.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_FATAL,
										"Error!  ",
										"Centro Erroneo maximo 4 numeros "));
			}
			if (agregarCentro.length() == 3 || agregarCentro.length() == 4 && numeroCuenta!=null) {

				agregarCentroAsociadoBean.setCentroAgregado(agregarCentro);
				
				agregarCentroAsociadoBackend.ejecutarTRN(fechaActivacion,
						agregarCentro, Long.parseLong(numeroCuenta), proximaFechaContable, codRel,
						centroAnterior);
				
				centroAsociado = consultaCentroAsociadoBackend.ejecutarTRN(
						Long.parseLong(numeroCuenta), "11", "03");
				for (CentroAsociadoBean fechas : centroAsociado) {
					fechaAnterior = fechas.getFechaInicio();
					centroAnterior = fechas.getCentro();
					codRel=fechas.getCodRel();
				}
				} else if (agregarCentro.equalsIgnoreCase("")) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_FATAL,
								"Error!  ", "Campo Obligatorio ó falta numero de cuenta"));
			}
			if(agregarCentroAsociadoBean.getCentroAgregado().equals(agregarCentro)){
				RequestContext.getCurrentInstance().execute(
						"PF('dlgConfirmacion').show()");
				}
		
		
		return "";
	}

	public String regresarFichaCuenta() {
		obtieneFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				cuentaBean);
		
		return NavegacionEnum.FICHA_CUENTA.getRuta();
	}

	/**
	 * 
	 * @return Flash
	 */
	private Flash obtieneFlash() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
	}


	/**
	 * @return centroSelected
	 */
	public CatalogoBean getCentroSelected() {
		if (this.agregarCentroAsociadoBean.getCentroAgregado() != null
				&& !("").equals(this.agregarCentroAsociadoBean
						.getCentroAgregado())) {
			centroSelected = catalogoCentros.getCatalogoBean(
					contextoUtils.getEntidad(),
					this.agregarCentroAsociadoBean.getCentroAgregado());
		}
		return centroSelected;
	}

	/**
	 * @param centroSelected
	 */
	public void setCentroSelected(CatalogoBean centroSelected) {
		this.centroSelected = centroSelected;
		if (this.centroSelected != null) {
			this.agregarCentroAsociadoBean
					.setCentroAgregado(this.centroSelected.getClaveFila());
		}
	}
	
	/**
	 * Funcion que indica si la cuenta se encuentra
	 * en estado activo
	 * @return
	 */
	public boolean isCuentaActiva(){
		if(this.cuentaBean != null && this.cuentaBean.getEstadoEnum() != null){
			if(this.cuentaBean.getEstadoEnum().equals(EstadosCuentaEnum.ACTIVO)){
				return true;
			}
			
		}
		
		return false;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getCentroAuto() {
		return centroAuto;
	}

	public void setCentroAuto(String centroAuto) {
		this.centroAuto = centroAuto;
	}

	public String getCodRel() {
		return codRel;
	}

	public void setCodRel(String codRel) {
		this.codRel = codRel;
	}

	public String getCentroAnterior() {
		return centroAnterior;
	}

	public void setCentroAnterior(String centroAnterior) {
		this.centroAnterior = centroAnterior;
	}

	public Date getFechaAnterior() {
		return fechaAnterior;
	}

	public void setFechaAnterior(Date fechaAnterior) {
		this.fechaAnterior = fechaAnterior;
	}

	public CuentaBean getCuentaBean() {
		return cuentaBean;
	}

	public void setCuentaBean(CuentaBean cuentaBean) {
		this.cuentaBean = cuentaBean;
	}

	public List<CentroAsociadoBean> getCentroAsociado() {
		return centroAsociado;
	}

	public void setCentroAsociado(List<CentroAsociadoBean> centroAsociado) {
		this.centroAsociado = centroAsociado;
	}

	public AgregarCentroAsociadoBean getAgregarCentroAsociadoBean() {
		return agregarCentroAsociadoBean;
	}

	public void setAgregarCentroAsociadoBean(
			AgregarCentroAsociadoBean agregarCentroAsociadoBean) {
		this.agregarCentroAsociadoBean = agregarCentroAsociadoBean;
	}
	
	
	
	
	
	
	

}