package mx.babel.bansefi.banksystem.integracionesAppWhere.controllers;

import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;
import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.UrlModuloEnum;
import mx.babel.bansefi.banksystem.integracionesAppWhere.services.IIntegracionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.ServletContext;
import java.io.Serializable;

@ManagedBean
@Component
@Scope("view")
public class AcuerdosController implements Serializable {

	/**
	 * Variables de clase.
	 */
	private static final long serialVersionUID = -9182841585023316075L;
	private ContextoUtils contextoUtils;
	private IIntegracionService integracionService;
	private	ManagedBeanStateRecover managedBeanStateRecover;
	private CuentaBean cuenta;

	/**
	 * Enum para definir a donde se redireccionará al seleccionar un objeto de
	 * la tabla de resultados.
	 */
	private NavegacionEnum destino;
	private Object destinoController;

	/**
     * Inyeccion de dependencias.
     */
	@Autowired
	public void setContextoUtils(ContextoUtils contextoUtils) {
		this.contextoUtils = contextoUtils;
	}

	@Autowired
	public void setIntegracionService(IIntegracionService integracionService) {
		this.integracionService = integracionService;
	}

	@Autowired
	public void setManagedBeanStateRecover(ManagedBeanStateRecover managedBeanStateRecover) {
		this.managedBeanStateRecover = managedBeanStateRecover;
	}

	/**
	 * Iniciador del contexto manejado en Faces para poder recuperarlo.
	 */
	@PostConstruct
	private void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		ServletContext servletContext = (ServletContext) externalContext.getContext();
		WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).
				getAutowireCapableBeanFactory().
				autowireBean(this);
	}

	/**
	 * Metodo para inicializar parámetros de acuerdos mediante el flash
	 */
	public void inicioAcuerdos() {
		final Flash flash = obtenerFlash();
		if (flash.get(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash()) != null) {
			cuenta = (CuentaBean) flash
					.get(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash());
		}
 		if (flash.get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN
				.getParamFlash()) != null) {
			destino = managedBeanStateRecover.getDestino();
			destinoController = managedBeanStateRecover.getController();
		} else {
			if (flash.get(ParametrosFlashEnum.DESTINO.getParamFlash()) != null) {
				destino = (mx.babel.bansefi.banksystem.base.enums.NavegacionEnum) flash
						.get(ParametrosFlashEnum.DESTINO.getParamFlash());
			}
			if (flash.get(ParametrosFlashEnum.DESTINO_CONTROLLER
					.getParamFlash()) != null) {
				destinoController = flash
						.get(ParametrosFlashEnum.DESTINO_CONTROLLER
								.getParamFlash());
			}
		}

	}

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
	 * Metodos para obtener url de Reportes Retiros.
	 *
	 * @return String
	 */
	public String urlCambioTarifa() {
		return UrlModuloEnum.CAMBIOTARIFA.getUrl();
	}

	/**
     * Metodo para generacion de bsfoperador.
     */
	public String generarBsfOperadorMovimientos() {
		return integracionService.getBsfOperadorCambioTarifa(
				contextoUtils,
				cuenta.getNumeroCuenta().toString(),
				cuenta.getTipoCuenta());
	}

	/**
	 * Metodo para regresar a la ficha de la cuenta
	 *
	 * @return String
	 */
	public String regresarFichaCuenta() {
		String ruta = null;
		if (destino == null) {
			ruta = NavegacionEnum.INICIO.getRuta();
		} else {
			ruta = destino.getRuta();
			managedBeanStateRecover.finNavegacion(destinoController);
		}
		return ruta;
	}
}
