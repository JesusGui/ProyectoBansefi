package mx.babel.bansefi.banksystem.cuentas.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.bansefi.banksystem.base.backends.busquedas.BusquedaCuentaBackEnd;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.enums.BusquedaEnum;
import mx.babel.bansefi.banksystem.base.enums.CuentaEnum;
import mx.babel.bansefi.banksystem.base.enums.EstadosCuentaEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaRelacionesCuentaCuentasBackEnd;
import mx.babel.bansefi.banksystem.cuentas.backends.DescripcionConceptoBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.SimulacionCancelacionCuentaBackEnd;
import mx.babel.bansefi.banksystem.cuentas.backends.TablaIndicadoresApunteTRDBackend;
import mx.babel.bansefi.banksystem.cuentas.beans.CancelacionCuentaBean;
import mx.babel.bansefi.banksystem.cuentas.beans.SimulacionCancelacionCuentaBean;
import mx.babel.bansefi.banksystem.cuentas.utils.AltaIPFUtils;
import mx.babel.bansefi.banksystem.cuentas.utils.CambiosEstadosCuentasUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.queryparser.flexible.core.util.StringUtils;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Controller utilizado para el manejo de la cancelacion de cuentas
 * 
 * @author manuel.nieto
 * 
 */
@ManagedBean(name = "cancelarCuentaController")
@ViewScoped
@Component
@Scope("view")
public class CancelarCuentaController implements Serializable {

	private static final long serialVersionUID = 2529282604839437952L;

	private static final Logger LOGGER = LogManager
			.getLogger(CancelarCuentaController.class.getName());

	@Autowired
	ManagedBeanStateRecover managedBeanStateRecover;

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
	 * Bean que almacena los datos de la simulacion de la cancelacion
	 */
	private SimulacionCancelacionCuentaBean simulacion;

	/**
	 * Bean que almacena los datos necesarios para la cancelacion de la cuenta
	 */
	private CancelacionCuentaBean cancelacionCuentaBean;

	@Autowired
	CambiosEstadosCuentasUtils cambiosEstadosCuentasUtils;

	@Autowired
	ConsultaRelacionesCuentaCuentasBackEnd consultaRelacionesCuentaCuentasBackEnd;

	@Autowired
	private TablaIndicadoresApunteTRDBackend indicadoresApuntes;

	@Autowired
	AltaIPFUtils altaIPFUtils;

	@Autowired
	SimulacionCancelacionCuentaBackEnd simulacionCancelacionCuentaBackEnd;
	
	@Autowired
	DescripcionConceptoBackend descripcionConceptoBackend; 

	// Mensaje de respuesta al cambio de estado en la cuenta
	private String mensaje;
	private Map<Boolean, String> respuestaCambioEstado;

	// Class dialogo respuesta cambio
	private String claseEstadoRespuesta;

	/**
	 * Variable que indica si la simulación se pudo realizar de forma correcta
	 */
	private boolean simulacionCorrecta;
	
	private Boolean muestraIncorporarGastos;

	public Boolean getMuestraIncorporarGastos() {
		return muestraIncorporarGastos;
	}

	public void setMuestraIncorporarGastos(Boolean muestraIncorporarGastos) {
		this.muestraIncorporarGastos = muestraIncorporarGastos;
	}

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

				if (this.obtieneFlash().get(
						ParametrosFlashEnum.NUMERO_CUENTA.getParamFlash()) != null) {
					Long numCuenta = (Long) this.obtieneFlash().get(
							ParametrosFlashEnum.NUMERO_CUENTA.getParamFlash());
					this.cancelacionCuentaBean.setOtraCuenta(numCuenta);
				}
			}
		} else {
			LOGGER.debug("Accedemos al metodo de inicio init() directamente sin acceso de navegacion");
			this.initializeData();
		}
	}

	public void initializeData() {
		this.cancelacionCuentaBean = new CancelacionCuentaBean();

		if (obtieneFlash().get(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash()) != null) {
			this.cuentaBean = (CuentaBean) obtieneFlash().get(
					ParametrosFlashEnum.CUENTA_BEAN.getParamFlash());
			
			
			if( (this.cuentaBean.getCodLinea().equals("05")) || ( this.cuentaBean.getCodLinea().equals("03") && this.cuentaBean.getIdGrupoProducto().equals("11") )  || ( this.cuentaBean.getCodLinea().equals("03") && this.cuentaBean.getIdGrupoProducto().equals("21")  || ( this.cuentaBean.getCodLinea().equals("03") && this.cuentaBean.getIdGrupoProducto().equals("18") )) )
			{
				this.setMuestraIncorporarGastos(false);
			}
			else
			{
				this.setMuestraIncorporarGastos(true);
			}

			// Si no tiene ninguna cuenta asociada consulta de nuevo en host
			if (!isCuentaAsociada()) {
				consultaCuentasRelacionadas();
			}
			this.cancelacionCuentaBean.setCuentaBean(this.cuentaBean);

			// Simulacion de cancelación de cuenta
			if (!isTarifaCancelacionRazon()) {
				try {
					simularCancelacion("CT");
					simulacionCorrecta = true;
				} catch (NoControlableException | ControlableException e) {
					try {
						simularCancelacion("CJ");
						simulacionCorrecta = true;
					} catch (NoControlableException | ControlableException e2) {
						try {
							simularCancelacion("IV");
							simulacionCorrecta = true;
						} catch (NoControlableException | ControlableException e3) {
							LOGGER.debug("No se ha podido llevar acabo la simulación de cancelación de cuenta.");
						}
					}
				}
			}

			// Pre seleccionar cuenta asociada para credito simple
			if (isCuentaCreditoSimple()) {
				this.cancelacionCuentaBean.setDestinoImporte("CT");
			}
		} else {
			throw new NoControlableException(
					"No se recibió la información de la cuenta a cancelar",
					"No se recibió la información de la cuenta a cancelar");
		}

	}

	/**
	 * Método utilizado para consultar el detalle de las cuentas relacionadas a
	 * la cuenta.
	 * 
	 */
	public void consultaCuentasRelacionadas() {
		if (this.cuentaBean != null) {
			this.cuentaBean
					.setCuentasRelacionadas(this.consultaRelacionesCuentaCuentasBackEnd
							.ejecutarTRN(this.cuentaBean.getNumeroCuenta()));
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
			ruta = NavegacionEnum.BUSQUEDA.getRuta();
		} else {
			ruta = destino.getRuta();
			managedBeanStateRecover.finNavegacion(destinoController);
		}
		return ruta;
	}
	

	/**
	 * Funcion que se ejectua traz la validacion correcta del formulario de
	 * cancelacion. Muestra el dialogo de confirmación de cancelación.
	 */
	public void solicitarCancelacion() {
		RequestContext.getCurrentInstance().execute(
				"PF('dlgConfirmacionEstado').show()");
	}

	/**
	 * Funcion que cancela la cuenta
	 * 
	 * @return
	 */
	public String cancelarCuenta() {
		if (this.cuentaBean != null && this.cuentaBean.getEstadoEnum() != null
				&& this.cancelacionCuentaBean != null) {
			Map<Boolean, String> respuesta = cambiosEstadosCuentasUtils
					.cambiarEstadoCuenta(this.cuentaBean.getEstadoEnum(),
							EstadosCuentaEnum.CANCELADO,
							this.cancelacionCuentaBean);

			RequestContext.getCurrentInstance().execute(
					"PF('dlgConfirmacionEstado').hide()");

			if (respuesta != null && !respuesta.isEmpty()) {
				respuestaCambioEstado = respuesta;
				// Repuesta correcta
				if (respuesta.get(Boolean.TRUE) != null) {
					this.mensaje = (String) respuesta.get(Boolean.TRUE);
					this.claseEstadoRespuesta = "ui-messages-exito-icon exito-dialog";
				} else if (respuesta.get(Boolean.FALSE) != null) {
					this.mensaje = (String) respuesta.get(Boolean.FALSE);
					this.claseEstadoRespuesta = "ui-messages-error-icon error-dialog";
				}

				RequestContext.getCurrentInstance().execute(
						"PF('dlgRespuestaCambio').show()");
			}
		}
		return null;
	}

	/**
	 * Funcion que ejecuta una simulacion de la cancelacion y actualiza la tabla
	 * con los datos de la simulación
	 */
	public void simularCancelacion(String destinoImporte) {
		if (this.cuentaBean != null && this.cancelacionCuentaBean != null) {
			if (!isSimulacionOtraCuenta()
					|| (isSimulacionOtraCuenta() && this.cancelacionCuentaBean
							.getOtraCuenta() != null)) {
				simulacion = simulacionCancelacionCuentaBackEnd.ejecutarTRN(
						cuentaBean, destinoImporte);
				
				if(simulacion.getDesgloseConceptos().size()>0)
				{
				descripcionConceptoBackend.ejecutarTRN(simulacion);
				}
			}

		}
	}

	/**
	 * Funcion que determina si se realizara una simulacion de cancelacion de
	 * cuenta con destino de importe a otra cuenta
	 * 
	 * @return
	 */
	public boolean isSimulacionOtraCuenta() {
		if (this.cancelacionCuentaBean != null
				&& this.cancelacionCuentaBean.getDestinoImporte() != null
				&& this.cancelacionCuentaBean.getDestinoImporte()
						.equals("null")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Funcion que indica si se muestra el resultado del salso
	 * 
	 * @return
	 */
	public boolean muestraSaldo() {
		if (this.cancelacionCuentaBean != null
				&& this.cancelacionCuentaBean.getDestinoImporte() != null) {
			switch (this.cancelacionCuentaBean.getDestinoImporte()) {
			case "CJ":
				return true;
			case "CT":
				return true;
			default:
				return false;
			}
		}

		return false;
	}

	/**
	 * Funcion que se ejecuta al confirmar la respuesta del cambio de estado de
	 * la cuenta Puede permanecer en la pagina o puede enviarse a otro flujo
	 * 
	 * @return
	 */
	public String confirmaRespuesta() {
		if (this.cuentaBean != null) {
			obtieneFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
					this.cuentaBean);
			return NavegacionEnum.FICHA_CUENTA.getRuta();
		}

		return null;

	}

	/**
	 * Método para buscar mediante el buscador el número de cuenta
	 * 
	 * @return
	 */
	public String buscarPersona() {
		obtieneFlash().put(ParametrosFlashEnum.TIPO_BUSQUEDA.getParamFlash(),
				BusquedaEnum.CUENTAS.getBusquedaValor());
		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.CANCELAR_CUENTA);
		return NavegacionEnum.BUSQUEDA.getRuta();

	}
	
	public String irNotacion()
	{
		obtieneFlash().put(ParametrosFlashEnum.NUMERO_CUENTA.getParamFlash(),
				this.getCuentaBean().getNumeroCuenta());
		
		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.CANCELAR_CUENTA);
		
		return NavegacionEnum.ALTA_ANOTACIONES_GASTOS.getRuta();
	}
	
	public String irGastos()
	{
		obtieneFlash().put(
				ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				this.cuentaBean);
		
		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.CANCELAR_CUENTA);
		return NavegacionEnum.GASTOS_EXTERNOS.getRuta();
	}

	/**
	 * Funcion que a traves de una utilidad retorna la cuenta operativa asociada
	 * a la cuenta.
	 * 
	 * @return
	 */
	public String getCuentaOperativa() {
		if (isCuentaAsociada()) {
			return StringUtils.toString(altaIPFUtils
					.getCuentaOperativa(this.cuentaBean));
		} else {
			return "NO SE HAN ENCONTRADO CUENTAS RELACIONADAS";
		}
	}

	/**
	 * Funcion que indica si se tiene una cuenta operativa
	 * 
	 * @return
	 */
	public boolean isCuentaOperativa() {
		if (isCuentaAsociada()) {
			String cuentaOperativa = StringUtils.toString(altaIPFUtils
					.getCuentaOperativa(this.cuentaBean));
			if (cuentaOperativa != null) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Funcion que determina si la cuenta es de tipo VISTA
	 * 
	 * @return
	 */
	public boolean isCuentaVista() {
		if (this.cuentaBean != null
				&& this.cuentaBean.getTipoCuentaEnum() != null) {
			if (this.cuentaBean.getTipoCuentaEnum().equals(CuentaEnum.VISTA)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Función que valida si se trata de una cuenta tipo crédito simple 01-71
	 * 
	 * @return
	 */
	public boolean isCuentaCreditoSimple() {
		if (this.cuentaBean != null
				&& this.cuentaBean.getTipoCuentaEnum() != null) {
			if (this.cuentaBean.getTipoCuentaEnum().equals(
					CuentaEnum.CREDITO_SIMPLE)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Función que valida si se trata de una cuenta tipo crédito 01-41
	 * 
	 * @return
	 */
	public boolean isCuentaCreditoRevolvente() {
		if (this.cuentaBean != null
				&& this.cuentaBean.getTipoCuentaEnum() != null) {
			if (this.cuentaBean.getTipoCuentaEnum().equals(
					CuentaEnum.CREDITO_REVOLVENTE)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Funcion que determina si se puede hacer el cargo a una cuenta
	 * asociada/operativa
	 * 
	 * @return
	 */
	public boolean permiteCuentaAsociada() {
		if (isCuentaOperativa() || !isCuentaVista()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Funcion que indica si hay al menos una cuenta asociada
	 * 
	 * @return
	 */
	public boolean isCuentaAsociada() {
		if (this.cuentaBean != null
				&& this.cuentaBean.getCuentasRelacionadas() != null
				&& !this.cuentaBean.getCuentasRelacionadas().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Funcion que verifica el tipo de tarifa para mostrar el formulario de
	 * cancelacion correspondiente
	 * 
	 * @return
	 */
	public boolean isTarifaCancelacionRazon() {
		if (this.cuentaBean != null) {
			CuentaEnum tipoCuenta = this.cuentaBean.getTipoCuentaEnum();
          
			if(this.cuentaBean.getEstadoEnum().equals(
					EstadosCuentaEnum.SOLICITADO))
					{
				return true;
					}
			else{
			
			
					if (tipoCuenta != null && this.cuentaBean.getEstadoEnum() != null) {
						if (tipoCuenta.isRazonCancelacion()
								|| this.cuentaBean.getEstadoEnum().equals(
										EstadosCuentaEnum.SOLICITADO)) {
							/**
							 * Para los casos en que se cancele por razon de cancelación
							 * el tipo de importe debe ser CT
							 */
							if (this.cancelacionCuentaBean != null) {
								this.cancelacionCuentaBean.setDestinoImporte("CT");
							}
							return true;
						}
					}
					// if (ConstantesFuncionales.isMediosPago(
					// this.cuentaBean.getCodLinea(),
					// this.cuentaBean.getIdGrupoProducto())
					// || ConstantesFuncionales.isPlazo(
					// this.cuentaBean.getCodLinea(),
					// this.cuentaBean.getIdGrupoProducto())
					// || (this.cuentaBean.getEstadoEnum()
					// .equals(EstadosCuentaEnum.SOLICITADO))) {
					//
					// } else {
					// return false;
					// }
				}
		}
		
		return false;
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

	public SimulacionCancelacionCuentaBean getSimulacion() {
		return simulacion;
	}

	public void setSimulacion(SimulacionCancelacionCuentaBean simulacion) {
		this.simulacion = simulacion;
	}

	public CancelacionCuentaBean getCancelacionCuentaBean() {
		return cancelacionCuentaBean;
	}

	public void setCancelacionCuentaBean(
			CancelacionCuentaBean cancelacionCuentaBean) {
		this.cancelacionCuentaBean = cancelacionCuentaBean;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Map<Boolean, String> getRespuestaCambioEstado() {
		return respuestaCambioEstado;
	}

	public void setRespuestaCambioEstado(
			Map<Boolean, String> respuestaCambioEstado) {
		this.respuestaCambioEstado = respuestaCambioEstado;
	}

	public boolean isSimulacionCorrecta() {
		return simulacionCorrecta;
	}

	public void setSimulacionCorrecta(boolean simulacionCorrecta) {
		this.simulacionCorrecta = simulacionCorrecta;
	}

	public String getClaseEstadoRespuesta() {
		return claseEstadoRespuesta;
	}

	public void setClaseEstadoRespuesta(String claseEstadoRespuesta) {
		this.claseEstadoRespuesta = claseEstadoRespuesta;
	}

}
