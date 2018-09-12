package mx.babel.bansefi.banksystem.personas.controllers;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.bansefi.banksystem.base.backends.ConsultaMinimaPersonaBackEnd;
import mx.babel.bansefi.banksystem.base.beans.ConsultaMinimaPersonaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioCompartidoBean;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Clase controlador para mostrar el detalle de una persona fisica o moral.
 * Flujo proveniente de Domicilio de Personas
 * 
 * @author manuel.nieto
 * 
 */
@ManagedBean(name = "detallePersonaController")
@ViewScoped
@Component
@Scope("view")
public class DetallePersonaController implements Serializable {

	private static final long serialVersionUID = 109665727078669308L;

	/**
	 * Parametros de entrada
	 */
	private DomicilioCompartidoBean domicilioCompartidoBean;

	/**
	 * Bean
	 */
	private ConsultaMinimaPersonaBean consultaMinimaPersonaBean;

	/**
	 * Backend
	 */
	@Autowired
	private ConsultaMinimaPersonaBackEnd consultaMinimaPersonaBackEnd;

	@Autowired
	CatalogoUtils catalogoUtils;

	/**
	 * Enum para definir a donde se redireccionará al seleccionar un objeto de
	 * la tabla de resultados.
	 */
	private NavegacionEnum destino;
	private Object destinoController;

	@Autowired
	ManagedBeanStateRecover managedBeanStateRecover;

	private static final Logger LOGGER = LogManager
			.getLogger(DomicilioPersonaController.class);

	/**
	 * Metodo para inicializar la información del flujo anterior
	 */
	@PostConstruct
	public void init() {

		if (this.obtieneFlash().get(
				ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash()) != null) {
			if ((Boolean) this.obtieneFlash()
					.get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN
							.getParamFlash())) {
				destino = managedBeanStateRecover.getDestino();
				destinoController = managedBeanStateRecover.getController();
			}
		}
		initializeData();
	}

	public void initializeData() {
		if (obtieneFlash().get(
						ParametrosFlashEnum.DOMICILIO_COMPARTIDO
								.getParamFlash()) != null) {
			
			this.domicilioCompartidoBean = (DomicilioCompartidoBean) obtieneFlash()
					.get(ParametrosFlashEnum.DOMICILIO_COMPARTIDO.getParamFlash());

			// Ejecuta TRN
			consultaMinimaPersonaBean = consultaMinimaPersonaBackEnd
					.ejecutarTRN(Integer
							.valueOf(this.getDomicilioCompartidoBean()
									.getIdInternoPersona()));

		} else {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Detalle de Persona",
					"Se ha producido un error al obtener datos de la persona.");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
		}
	}

	/**
	 * Funcion que concatena el tipo de identificacion oficial asi como su
	 * identificador
	 * 
	 * @return
	 */
	public String getDomicilioCompartidoIdExternoToString() {
		if (getDomicilioCompartidoBean() != null) {
			if (getDomicilioCompartidoBean().getCodigoIdExternoPersona() != null
					&& getDomicilioCompartidoBean().getIdExternoPersona() != null) {

				LOGGER.debug("/////////////getIdToString()");
				LOGGER.debug("codigoIdExternoPersona: "
						+ getDomicilioCompartidoBean()
								.getCodigoIdExternoPersona());
				LOGGER.debug("idExternoPersona: "
						+ getDomicilioCompartidoBean().getIdExternoPersona());

				try {
					CatalogoBean catalogo = catalogoUtils.getCatalogoBean(
							CatalogoEnum.TP_ID_EXT_PERS,
							getDomicilioCompartidoBean()
									.getCodigoIdExternoPersona());

					String tipoId = catalogo.getDescripcionC();

					return tipoId
							+ " "
							+ getDomicilioCompartidoBean()
									.getIdExternoPersona();
				} catch (NullPointerException | ControlableException e) {
					LOGGER.debug("Error al consultar el catalogo de id exterior personas");
					LOGGER.debug(e);
					return getDomicilioCompartidoBean().getIdExternoPersona();
				}
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * Funcion que de acuerdo al codigo del CNO de la persona fisica Devuelve el
	 * valor de catalogo
	 * 
	 * @return
	 */
	public String getCNO() {
		if (this.consultaMinimaPersonaBean != null) {
			try {
				CatalogoBean catalogo = catalogoUtils.getCatalogoBean(
						CatalogoEnum.TP_CNO_INDV,
						this.consultaMinimaPersonaBean.getCodigoCNO());

				String tipoCNO = catalogo.getDescripcionC();

				return this.consultaMinimaPersonaBean.getCodigoCNO() + " - "
						+ tipoCNO;
			} catch (ControlableException ex) {
				LOGGER.debug("No se encontró el CNO "
						+ this.consultaMinimaPersonaBean.getCodigoCNO()
						+ " en el catalogo.");
				return null;
			}
		} else {
			return null;
		}

	}

	/**
	 * Funcion que consulta la estructura legal en un catalogo Para las personas
	 * morales
	 * 
	 * @return
	 */
	public String getEstructuraLegal() {
		if (this.consultaMinimaPersonaBean != null) {
			try {
				CatalogoBean catalogo = catalogoUtils.getCatalogoBean(
						CatalogoEnum.TP_ESTRCT_LGL_ORG,
						this.consultaMinimaPersonaBean
								.getCodigoEstructuraLegal());

				String estructuraLegal = catalogo.getDescripcionC();

				return this.consultaMinimaPersonaBean
						.getCodigoEstructuraLegal() + " - " + estructuraLegal;
			} catch (ControlableException ex) {
				LOGGER.debug("No se ha encontrado la estructura legal "
						+ this.consultaMinimaPersonaBean
								.getCodigoEstructuraLegal()
						+ " en el catalogo.");
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * Funcion que consulta CNAE en el catalogo y devuelve su valor Para persona
	 * Moral
	 * 
	 * @return
	 */
	public String getCNAE() {
		if (this.consultaMinimaPersonaBean != null) {
			try {
				CatalogoBean catalogo = catalogoUtils.getCatalogoBean(
						CatalogoEnum.TP_CNAE_PERS,
						this.consultaMinimaPersonaBean.getCodigoCNAE());

				String cnae = catalogo.getDescripcionC();

				return this.consultaMinimaPersonaBean.getCodigoCNAE() + " - "
						+ cnae;
			} catch (ControlableException ex) {
				LOGGER.debug("No se ha encontrado el CNAE "
						+ this.consultaMinimaPersonaBean.getCodigoCNAE()
						+ " en el catalogo");
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * Funcion para regresar a la ventana anterior de Domicilio de personas Con
	 * los parametros necesarios
	 * 
	 * @return
	 */
	public String regresar() {
		try {
			managedBeanStateRecover.finNavegacion(destinoController);
			return NavegacionEnum.DOMICILIO_PERSONA.getRuta();
		} catch (NullPointerException e) {
			LOGGER.debug("No se pudieron cargar los parametros a la Flash");
			return NavegacionEnum.INICIO.getRuta();
		}

	}
	
	/**
	 * Funcion que dependiendo del tipo de persona retorna
	 * la cadena para describir el tipo de domicilio
	 * @return
	 */
	public String tipoDomicilio(){
		if(this.domicilioCompartidoBean != null){
			if(this.domicilioCompartidoBean.isPersonaFisica()){
				return "DOMICILIO HABITUAL"; 
			}else{
				return "DOMICILIO SOCIAL";
			}
		}else{
			return null;
		}
	}

	/**
	 * @return Metodo utilizado para recuperar una instancia de Flash
	 */
	public Flash obtieneFlash() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
	}

	public DomicilioCompartidoBean getDomicilioCompartidoBean() {
		return domicilioCompartidoBean;
	}

	public void setDomicilioCompartidoBean(
			DomicilioCompartidoBean domicilioCompartidoBean) {
		this.domicilioCompartidoBean = domicilioCompartidoBean;
	}

	public ConsultaMinimaPersonaBean getConsultaMinimaPersonaBean() {
		return consultaMinimaPersonaBean;
	}

	public void setConsultaMinimaPersonaBean(
			ConsultaMinimaPersonaBean consultaMinimaPersonaBean) {
		this.consultaMinimaPersonaBean = consultaMinimaPersonaBean;
	}	

	public ConsultaMinimaPersonaBackEnd getConsultaMinimaPersonaBackEnd() {
		return consultaMinimaPersonaBackEnd;
	}

	public void setConsultaMinimaPersonaBackEnd(
			ConsultaMinimaPersonaBackEnd consultaMinimaPersonaBackEnd) {
		this.consultaMinimaPersonaBackEnd = consultaMinimaPersonaBackEnd;
	}

}
