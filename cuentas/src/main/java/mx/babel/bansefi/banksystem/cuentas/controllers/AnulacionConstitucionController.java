package mx.babel.bansefi.banksystem.cuentas.controllers;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.enums.EstadosCuentaEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;
import mx.babel.bansefi.banksystem.cuentas.beans.ConsultaAnulacionCuentaBean;
import mx.babel.bansefi.banksystem.cuentas.utils.CambiosEstadosCuentasUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Controlador para manejar el flujo de anulacion de constitucion de cuentas
 * 
 * @author manuel.nieto
 * 
 */
@ManagedBean(name = "anulacionConstitucionController")
@ViewScoped
@Component
@Scope("view")
public class AnulacionConstitucionController implements Serializable {

	private static final long serialVersionUID = 3667669577481231840L;
	private static final Logger LOGGER = LogManager
			.getLogger(AnulacionConstitucionController.class.getName());

	@Autowired
	ManagedBeanStateRecover managedBeanStateRecover;

	@Autowired
	CambiosEstadosCuentasUtils cambiosEstadosCuentasUtils;

	/**
	 * Variable utilizada para guardar la redireccion al flujo de entrada a la
	 * consulta de cancelacion de cuenta
	 */
	private NavegacionEnum destino;

	/**
	 * Variable utilizada para guardar el contenido del controller origen a la
	 * consulta de cancelacion de cuenta
	 */
	private Object destinoController;

	/**
	 * Variable para almacenar un cuentaBean.
	 */
	private CuentaBean cuentaBean;

	/**
	 * Variable que almacena la consulta de movimientos de una cuenta
	 */
	private ConsultaAnulacionCuentaBean consultaAnulacionCuentaBean;

	/**
	 * Mensaje que se muestra al usuario traz la ejecucion del servicio de
	 * anulacion de constitucion
	 */
	private String mensaje;

	/**
	 * Variable que guarda el estado de la transaccion.
	 */
	private boolean anulacionOk;

	@PostConstruct
	public void init() {
		if (this.obtieneFlash().get(
				ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash()) != null) {
			if ((Boolean) this.obtieneFlash()
					.get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN
							.getParamFlash())) {
				LOGGER.debug("Accedemos al metodo de inicio init(), iniciando la navegacion tras volver de otro flujo");
				this.destino = this.managedBeanStateRecover.getDestino();
				this.destinoController = this.managedBeanStateRecover
						.getController();
				this.initializeData();
			} else {
				LOGGER.debug("Accedemos al metodo de inicio init(), recuperando controller tras navegacion");
				this.managedBeanStateRecover.recuperaController(this);
			}
		} else {
			LOGGER.debug("Accedemos al metodo de inicio init() directamente sin acceso de navegacion");
			this.initializeData();
		}
	}

	public void initializeData() {
		if (obtieneFlash().get(ParametrosFlashEnum.APUNTE_BEAN.getParamFlash()) != null) {
			this.consultaAnulacionCuentaBean = (ConsultaAnulacionCuentaBean) obtieneFlash()
					.get(ParametrosFlashEnum.APUNTE_BEAN.getParamFlash());
		} else {
			throw new NoControlableException("Ha ocurrido un error:",
					"No se ha podido consultar la cuenta.");
		}
	}

	/**
	 * Método privado que obtiene el objeto Flash proveniente del contexto
	 * externo.
	 * 
	 * @return Flash
	 */
	private Flash obtieneFlash() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
	}

	/**
	 * @return Metodo utilizado para realizar la accion del boton de volver, a
	 *         la ficha de cuenta.
	 */
	public String volver() {
		String ruta = "";
		if (this.destino == null) {
			ruta = NavegacionEnum.INICIO.getRuta();
		} else {
			ruta = destino.getRuta();
			managedBeanStateRecover.finNavegacion(destinoController);
		}
		return ruta;
	}

	public void anularConstitucion() {
		if (this.cuentaBean != null) {
			Map<Boolean, String> respuesta = cambiosEstadosCuentasUtils
					.cambiarEstadoCuenta(this.cuentaBean.getEstadoEnum(),
							EstadosCuentaEnum.APROBADO, this.cuentaBean);

			// RequestContext.getCurrentInstance().execute(
			// "PF('dlgConfirmacionEstado').hide()");

			if (respuesta != null && !respuesta.isEmpty()) {
				// Repuesta correcta
				if (respuesta.get(Boolean.TRUE) != null) {
					this.mensaje = (String) respuesta.get(Boolean.TRUE);
				} else if (respuesta.get(Boolean.FALSE) != null) {
					this.mensaje = (String) respuesta.get(Boolean.FALSE);
				}

				RequestContext.getCurrentInstance().execute(
						"PF('dlgRespuestaCambio').show()");
			}
		}

	}

	public String confirmaRespuesta() {
		if (anulacionOk) {
			obtieneFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
					this.cuentaBean);
			return NavegacionEnum.FICHA_CUENTA.getRuta();
		} else {
			return null;
		}
	}

	public ManagedBeanStateRecover getManagedBeanStateRecover() {
		return managedBeanStateRecover;
	}

	public void setManagedBeanStateRecover(
			ManagedBeanStateRecover managedBeanStateRecover) {
		this.managedBeanStateRecover = managedBeanStateRecover;
	}

	public NavegacionEnum getDestino() {
		return destino;
	}

	public void setDestino(NavegacionEnum destino) {
		this.destino = destino;
	}

	public Object getDestinoController() {
		return destinoController;
	}

	public void setDestinoController(Object destinoController) {
		this.destinoController = destinoController;
	}

	public CuentaBean getCuentaBean() {
		return cuentaBean;
	}

	public void setCuentaBean(CuentaBean cuentaBean) {
		this.cuentaBean = cuentaBean;
	}

	public ConsultaAnulacionCuentaBean getConsultaAnulacionCuentaBean() {
		return consultaAnulacionCuentaBean;
	}

	public void setConsultaAnulacionCuentaBean(
			ConsultaAnulacionCuentaBean consultaAnulacionCuentaBean) {
		this.consultaAnulacionCuentaBean = consultaAnulacionCuentaBean;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
