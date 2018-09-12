package mx.babel.bansefi.banksystem.integracionesAppWhere.controllers;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.utils.ProveedorMensajeSpringUtils;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.ClienteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.TipoCliente;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.integracionAppWhere.UrlModuloEnum;
import mx.babel.bansefi.banksystem.cuentas.beans.TarifaBean;
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
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import java.io.Serializable;

@ManagedBean
@Component
@Scope("view")
public class ClientesCuentasController implements Serializable {

	/*
	 * Variables de clase.
	 */
	private static final long serialVersionUID = -7296407556462419732L;
	private ContextoUtils contextoUtils;
	private IIntegracionService integracionService;
	private ClienteBean cliente;

	public ClienteBean getCliente() {
		return cliente;
	}

	/*
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

	public void iniciaCliente() {
		this.cliente = (ClienteBean) obtieneFlash().get(ParametrosFlashEnum.CLIENTE.getParamFlash());
	}

	/*
	 * Metodos para renderizar vistas de Clientes y Cuentas.
	 */
	public String altaPersonaMoral() { return NavegacionEnum.ALTAPERSONAMORAL.getRuta(); }
	public String altaPersonaFisica() { return NavegacionEnum.ALTAPERSONAFISICA.getRuta(); }
	public String anotacion() { return NavegacionEnum.ANOTACION.getRuta(); }
	public String gestionAnotacion() { return NavegacionEnum.GESTIONANOTACION.getRuta(); }

	/*
	 * Metodos para obtener url de Clientes y Cuentas.
	 */
	public String urlAltaPersonaMoral() {
		return UrlModuloEnum.ALTAPERSONAMORAL.getUrl();
	}
	public String urlFichaPersonaMoral() { return UrlModuloEnum.FICHAPERSONAMORAL.getUrl(); }
	public String urlAltaPersonaFisica() { return UrlModuloEnum.ALTAPERSONAFISICA.getUrl(); }
	public String urlFichaPersonaFisica() { return UrlModuloEnum.FICHAPERSONAFISICA.getUrl(); }
	public String urlAltaCuenta() { return UrlModuloEnum.ALTACUENTA.getUrl(); }
	public String urlAnotacion() { return UrlModuloEnum.ANOTACION.getUrl(); }
	public String urlGestionAnotacion() { return UrlModuloEnum.GESTIONANOTACION.getUrl(); }
	public String urlModificarDatos() { return UrlModuloEnum.PERSONASMODIFICARDATOS.getUrl(); }
	public String urlModificarDatosMorales() { return UrlModuloEnum.PERSONASMODIFICARDATOSMORALES.getUrl(); }
	public String urlRelacionarPersonas() { return UrlModuloEnum.PERSONASRELACIONARPERSONA.getUrl(); }
	public String urlRelacionarPersonasMorales() { return UrlModuloEnum.PERSONASRELACIONARPERSONAMORALES.getUrl(); }
	public String urlAgregarDocumentos() { return UrlModuloEnum.PERSONASDOCUMENTOS.getUrl(); }
	public String urlAgregarDocumentosMorales() { return UrlModuloEnum.PERSONASDOCUMENTOSMORALES.getUrl(); }
	public String urlActividadEmpresarial() { return UrlModuloEnum.PERSONASACTIVIDADEMPRESARIAL.getUrl(); }
	public String urlOtrosDatos() { return UrlModuloEnum.PERSONASOTROSDATOS.getUrl(); }
	public String urlAnadirDomicilios() { return UrlModuloEnum.PERSONASANADIRDOMICILIOS.getUrl(); }
	public String urlDatosEconomicos() { return UrlModuloEnum.PERSONASDATOSECONOMICOS.getUrl(); }
	public String urlAmpliarBienes() { return UrlModuloEnum.PERSONASAMPLIARBIENES.getUrl(); }

	/**
	 * Metodo para obtener bsfoperador de alta de cuenta.
	 */
	public String generarBsfOperadorAltaCuentaPlazo() {
		ClienteBean clienteBean = (ClienteBean) obtieneFlash().get(ParametrosFlashEnum.CLIENTE.getParamFlash());
		TarifaBean tarifaBean = (TarifaBean) obtieneFlash().get(ParametrosFlashEnum.TARIFA_SELECCIONADA.getParamFlash());
		return integracionService.getBsfOperadorAltaCuentaPlazo(contextoUtils, clienteBean, tarifaBean);
	}

	/**
	 * Metodo para obtener bsfoperador de ficha de persona fisica.
	 */
	public String generarBsfOperadorFichaPersonaFisica() {
		return integracionService.getBsfOperadorFichaPersonaFisica(contextoUtils, this.cliente.getIdInterna().toString());
	}

	/**
	 * Metodo para obtener bsfoperador de ficha de persona moral.
	 */
	public String generarBsfOperadorFichaPersonaMoral() {
		return integracionService.getBsfOperadorFichaPersonaMoral(contextoUtils, this.cliente.getIdInterna().toString());
	}

	public String generarBsfOperadorRelacionarPersonaMoral() {
		return integracionService.getBsfOperadorRelacionarPersonaMoral(
				contextoUtils, this.cliente.getIdInterna().toString(), this.cliente.getNombreCompleto());
	}

	/**
	 * Metodo para obtener bsfOperador de personas datos adicionales.
	 */
	public String generarBsfOperadorPersonasDatosAdicionales() {
		return integracionService.getBsfOperadorPersonas(
				contextoUtils,
				this.cliente.getIdInterna().toString(),
				this.cliente.getNombreCompleto(),
				ProveedorMensajeSpringUtils.getValoresServiciosWeb("clientes-cuentas.datos-adicionales.codtx"),
				this.cliente.getTipo().getCodPe().equals(TipoCliente.PERSONA_FISICA.getCodPe())
						? ProveedorMensajeSpringUtils.getValoresServiciosWeb("clientes-cuentas.datos-adicionales.tipo-persona-fisica")
						: ProveedorMensajeSpringUtils.getValoresServiciosWeb("clientes-cuentas.datos-adicionales.tipo-persona-moral"),
				ProveedorMensajeSpringUtils.getValoresServiciosWeb("clientes-cuentas.datos-adicionales.num-ord_emp_pe"));
	}

	/**
	 * @return Metodo utilizado para recuperar una instancia de Flash
	 */
	public Flash obtieneFlash() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
	}

	/**
	 * Método que redirige al usuario a la búsqueda genérica.
	 *
	 * @return ruta del recurso a mostrar
	 */
	public String regresar(){
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(), this.cliente);
		return mx.babel.bansefi.banksystem.base.enums.NavegacionEnum.FICHA_CLIENTE.getRuta();
	}

	/**
	 * Metodo que define el tipo de persona.
	 *
	 * @return
	 */
	public Integer getTipoPersona() {
		if (this.cliente.getTipoClienteEnum().getCodPe().equals(TipoCliente.PERSONA_FISICA.getCodPe())) {
			return 1;
		} else if (this.cliente.getTipoClienteEnum().getCodPe().equals(TipoCliente.PERSONA_MORAL.getCodPe())){
			return 2;
		} else {
			return 0;
		}
	}

	/**
	 * Metodo para ir a la ficha de cliente a partir del idInterno y el tipo de persona
	 *
	 * @param idInterno
	 * @param tipoPersona
	 * @return
	 */
	private String idInterno;
	private String tipoCliente;

	public String getIdInterno() {
		return idInterno;
	}

	public void setIdInterno(String idInterno) {
		this.idInterno = idInterno;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public String getTipoCliente() {
		return tipoCliente;
	}

	public String irFichaCliente() {
		if (tipoCliente.equals(TipoCliente.PERSONA_FISICA.getCodPe())) {
			obtieneFlash().put(ParametrosFlashEnum.TIPO_CLIENTE.getParamFlash(), TipoCliente.PERSONA_FISICA);
		} else if (tipoCliente.equals(TipoCliente.PERSONA_MORAL.getCodPe())){
			obtieneFlash().put(ParametrosFlashEnum.TIPO_CLIENTE.getParamFlash(), TipoCliente.PERSONA_MORAL);
		} else {
			throw new ControlableException("Tipo de persona erroneo", "");
		}
		obtieneFlash().put(ParametrosFlashEnum.ID_INTERNA.getParamFlash(),idInterno);
		return mx.babel.bansefi.banksystem.base.enums.NavegacionEnum.FICHA_CLIENTE.getRuta();
	}

}
