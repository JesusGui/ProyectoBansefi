package mx.babel.bansefi.banksystem.personas.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
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
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.arq.storage.annotations.StoreStep;
import mx.babel.arq.storage.beans.StorageMgrBean;
import mx.babel.bansefi.banksystem.base.beans.RelacionesClienteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaMoralBean;
import mx.babel.bansefi.banksystem.base.beans.domain.PersonaMoralAccionistaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.PersonaMoralFuncionarioBean;
import mx.babel.bansefi.banksystem.base.beans.domain.PersonaMoralRBancariaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.PersonaMoralRComercialBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.BeanBackUpUtils;
import mx.babel.bansefi.banksystem.base.utils.CatalogoMonedaUtils;
import mx.babel.bansefi.banksystem.base.utils.CatalogoPaisesUtils;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.personas.backend.AltaPersonaMoralAccionistaBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.AltaPersonaMoralFuncionarioBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.AltaPersonaMoralRBancariaBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.AltaPersonaMoralRComercialBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.BajaRelacionClienteAccionistaBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.BajaRelacionClienteFuncionarioBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.BajaRelacionClienteRefBancariaBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.BajaRelacionClienteRefComercialBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.ModificaRelacionClienteAccionistaBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.ModificaRelacionClienteFuncionarioBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.ModificaRelacionClienteRefBancariaBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.ModificaRelacionClienteRefComercialBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.ModificacionPerfilTransaccionalMoralBackEnd;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.component.autocomplete.AutoComplete;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.panel.Panel;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Controlador para introducir y modificar datos de riesgo en una persona moral
 * @author alejandro.perez
 */

@ManagedBean(name = "personaMoralRiesgoController")
@ViewScoped
@Component
@Scope("view")
public class PersonaMoralRiesgoController extends StorageMgrBean  implements Serializable {

	private static final long serialVersionUID = -312687270398509276L;
	
	private static final Logger LOGGER = LogManager.getLogger(PersonaMoralRiesgoController.class);
	
	// Constantes para controlar el scroll vertical de la página.
	private static final String ID_CONTENEDOR_ACCIONISTA = "#formAnadirRelacion\\\\:panelAccionista";
	private static final String ID_CONTENEDOR_FUNCIONARIO = "#formAnadirRelacion\\\\:panelFuncionario";	
	private static final String ID_CONTENEDOR_REFBAN = "#formAnadirRelacion\\\\:panelRefBancaria";
	private static final String ID_CONTENEDOR_REFCOM = "#formAnadirRelacion\\\\:panelRefComercial";
		
	/**
	 * Utilidades
	 */
	@Autowired
	ContextoUtils contextoUtils;
	
	@Autowired
    BeanBackUpUtils backUpUtils;
	
	@Autowired
	CatalogoUtils catalogoUtils;
			
	@Autowired
	CatalogoMonedaUtils catalogoMonedaUtils;

	@Autowired
	CatalogoPaisesUtils catalogoPaisesUtils;
		
	/**
	 * BackEnds 
	 */
	
	@Autowired
	private ModificacionPerfilTransaccionalMoralBackEnd modificacionPerfilTransaccionalMoralBackEnd;
		
	// Altas Personas
	@Autowired
	private AltaPersonaMoralAccionistaBackEnd altaAccionista;

	@Autowired
	private AltaPersonaMoralFuncionarioBackEnd altaFuncionario;

	@Autowired
	private AltaPersonaMoralRBancariaBackEnd altaRBancaria;

	@Autowired
	private AltaPersonaMoralRComercialBackEnd altaRComercial;

	// Bajas Personas
	@Autowired
	private BajaRelacionClienteAccionistaBackEnd bajaAccionista;

	@Autowired
	private BajaRelacionClienteFuncionarioBackEnd bajaFuncionario;
	
	@Autowired
	private BajaRelacionClienteRefBancariaBackEnd bajaBancaria;
	
	@Autowired
	private BajaRelacionClienteRefComercialBackEnd bajaRComercial;

	// Modificación personas
	@Autowired
	private ModificaRelacionClienteAccionistaBackEnd modificacionAccionista;

	@Autowired
	private ModificaRelacionClienteFuncionarioBackEnd modificacionFuncionario;

	@Autowired
	private ModificaRelacionClienteRefBancariaBackEnd modificacionRBancaria;

	@Autowired
	private ModificaRelacionClienteRefComercialBackEnd modificacionRComercial;
		
	/**
	 * Beans
	 */
	private ClientePersonaMoralBean personaMoralBean;
		
	/**
	 * Catálogos
	 */			
	private CatalogoBean monedaSelected;
	
	private CatalogoBean monedaOtraSelected;
	
	private CatalogoBean paisNacionalidadSelected;
	
	private CatalogoBean paisResidenciaSelected;
	
	private List<String> selectedFrecuencia;

	private List<String> selectedEntrega;

	private List<String> selectedPersonas;
			
	private boolean esModificacion;
		
	private String idContenedorScroll;
	
	private int tipoRelacion;
	
	/**
	 * Variable para saber si se recupero informacion para renderizar en la vista.
	 */
	private boolean elementosVisibles;
	
	@PostConstruct
	void init() {
		super.restoreflash();
		
		this.selectedFrecuencia = new ArrayList<>();
		this.selectedEntrega = new ArrayList<>();
		this.selectedPersonas = new ArrayList<>();
		
		this.selectedFrecuencia.add(0, "Esporádica");
		this.selectedFrecuencia.add(1, "Anual");
		this.selectedFrecuencia.add(2, "Mensual");
		this.selectedFrecuencia.add(3, "Semanal");
		this.selectedFrecuencia.add(4, "Diaria");

		this.selectedEntrega.add(0, "Efectivo");
		this.selectedEntrega.add(1, "Cheque");
		this.selectedEntrega.add(2, "Cargo con tarjeta de crédito");
		this.selectedEntrega.add(3, "Transferencias");
		this.selectedEntrega.add(4, "En especie");

		this.selectedPersonas.add(0, "Público en general");
				
		if (obtieneFlash().get(ParametrosFlashEnum.CLIENTE_PM_BEAN.getParamFlash()) != null) {
			this.personaMoralBean = (ClientePersonaMoralBean) obtieneFlash().get(ParametrosFlashEnum.CLIENTE_PM_BEAN.getParamFlash());
			
			if (obtieneFlash().get(ParametrosFlashEnum.MODIFICACION_CLIENTE.getParamFlash()) != null) {
				this.esModificacion = (Boolean) obtieneFlash().get(ParametrosFlashEnum.MODIFICACION_CLIENTE.getParamFlash());
			}
			
			if (this.personaMoralBean.getRelacionesClienteRiesgo().getAccionistas() !=null && !this.personaMoralBean.getRelacionesClienteRiesgo().getAccionistas().isEmpty()
					&& this.personaMoralBean.getRelacionesClienteRiesgo().getAccionistas().get(0).getRespaldo() == null
					&& this.personaMoralBean.getRelacionesClienteRiesgo().getAccionistas().get(0).getEstado() == EstadoListadosEnum.CONSULTADO){
				this.backUpUtils.respaldaArray((ArrayList<PersonaMoralAccionistaBean>) this.personaMoralBean.getRelacionesClienteRiesgo().getAccionistas());
			}
			
			if (this.personaMoralBean.getRelacionesClienteRiesgo().getFuncionarios() !=null && !this.personaMoralBean.getRelacionesClienteRiesgo().getFuncionarios().isEmpty()
					&& this.personaMoralBean.getRelacionesClienteRiesgo().getFuncionarios().get(0).getRespaldo() == null
					&& this.personaMoralBean.getRelacionesClienteRiesgo().getFuncionarios().get(0).getEstado() == EstadoListadosEnum.CONSULTADO){
				this.backUpUtils.respaldaArray((ArrayList<PersonaMoralFuncionarioBean>) this.personaMoralBean.getRelacionesClienteRiesgo().getFuncionarios());
			}
			
			if (this.personaMoralBean.getRelacionesClienteRiesgo().getReferenciasBancarias() !=null && !this.personaMoralBean.getRelacionesClienteRiesgo().getReferenciasBancarias().isEmpty()
					&& this.personaMoralBean.getRelacionesClienteRiesgo().getReferenciasBancarias().get(0).getRespaldo() == null
					&& this.personaMoralBean.getRelacionesClienteRiesgo().getReferenciasBancarias().get(0).getEstado() == EstadoListadosEnum.CONSULTADO){
				this.backUpUtils.respaldaArray((ArrayList<PersonaMoralRBancariaBean>) this.personaMoralBean.getRelacionesClienteRiesgo().getReferenciasBancarias());
			}
			
			if (this.personaMoralBean.getRelacionesClienteRiesgo().getReferenciasComerciales() !=null && !this.personaMoralBean.getRelacionesClienteRiesgo().getReferenciasComerciales().isEmpty()
					&& this.personaMoralBean.getRelacionesClienteRiesgo().getReferenciasComerciales().get(0).getRespaldo() == null
					&& this.personaMoralBean.getRelacionesClienteRiesgo().getReferenciasComerciales().get(0).getEstado() == EstadoListadosEnum.CONSULTADO){
				this.backUpUtils.respaldaArray((ArrayList<PersonaMoralRComercialBean>) this.personaMoralBean.getRelacionesClienteRiesgo().getReferenciasComerciales());
			}
			
			this.elementosVisibles = true;
		}else{
			
			LOGGER.debug("Objeto ClientePersonaFisicaBean no encontrado: Procedemos a mostrar mensaje de persona moral de riesgo no disponible");	
			FacesContext.getCurrentInstance() .addMessage( "messages", new FacesMessage(FacesMessage.SEVERITY_WARN,
									"¡Atención!","No se pudo recuperar la persona moral, no es posible dar de alta o modificar una persona moral de riesgo en "
											+ "este momento."));
		}
				
	}
	
	/**
	 * Método para obtener el tipo de documento de la id externa de la persona Moral
	 */
	public String obtenerDescripcionTpDoc(){
		try{
			return this.catalogoUtils.getCatalogoBean(CatalogoEnum.TP_ID_EXT_PERS, this.personaMoralBean.getTipoIdentificacion()).getDescripcionC();			
		}catch(ControlableException | NoControlableException nce){
			LOGGER.debug("Error al intentar obtener la descripcion del tipo de documento a partir del codigo: "+ this.personaMoralBean.getTipoIdentificacion(), nce);
			return "";
		}
	}
	
	/**
	 * @return Metodo para el complete de los autocomplete de plazas 
	 *         
	 */
	public List<String> consultarDescripcionesPlazas(String query){
		List<String> descripciones = new ArrayList<String>();
		try{
			List<CatalogoBean> resultados = this.catalogoUtils.getCatalogo(CatalogoEnum.TP_PLAZA_BANCARIA);
		    for (int i=0;i<resultados.size() && descripciones.size()<ConstantesFuncionales.MAX_RESULTS_AUTOCOMPLETE;i++){
		    	if (StringUtils.containsIgnoreCase(resultados.get(i).getDescripcionL(), query.toUpperCase()) 
		    			|| StringUtils.containsIgnoreCase(resultados.get(i).getClaveFila(), query.toUpperCase())){
		    		descripciones.add(resultados.get(i).getDescripcionL());
		    	}	    		
		    }
		}catch(NoControlableException nce){
			LOGGER.debug("Error al consultar el catalogo de plazas bancarias ",nce);	
			return descripciones;
		}catch(ControlableException ce){
			LOGGER.debug("Error al consultar el catalogo de plazas bancarias ",ce);
			return descripciones;
		}
	    
	    return descripciones;	    
	}
	
	
	/**
	 * @return Metodo utilizado para limpiar los datos si es filial
	 *         
	 */
	public void limpiarCamposFilial(){
		if (!this.personaMoralBean.getGrupoFilialBean().isPerteneceGrupoFiliar()){
			this.personaMoralBean.getGrupoFilialBean().setNombre(null);
			this.personaMoralBean.getGrupoFilialBean().setRfc(null);
			this.personaMoralBean.getGrupoFilialBean().setCoberturaAmbito(null);
			this.personaMoralBean.getGrupoFilialBean().setProducto(null);
			this.personaMoralBean.getGrupoFilialBean().setPais(null);
			this.setPaisNacionalidadSelected(null);
			this.personaMoralBean.getGrupoFilialBean().setPaisResidencia(null);
			this.setPaisResidenciaSelected(null);
			this.personaMoralBean.getGrupoFilialBean().setParticipacion(null);
			this.personaMoralBean.getGrupoFilialBean().setEmpleados(null);
			this.personaMoralBean.getGrupoFilialBean().setOficinas(null);
			this.personaMoralBean.getGrupoFilialBean().setVentasAnuales(null);
			this.personaMoralBean.getGrupoFilialBean().setTelefono(null);
			this.personaMoralBean.getGrupoFilialBean().setEmail(null);
		}
	}
	
	/**
	 * @return Metodo utilizado para actualizar el valor del campo Otros en uso de cuenta y transaccionalidad
	 *         
	 */
	public void actualizaDatosPersonas(){
		if (!this.personaMoralBean.getDonativosBean().getPersonas().contains("personas")){
			this.personaMoralBean.getDonativosBean().setPersonasEspecificas(null);
		}
	}
	
	/**
	 * Método privado que devuelve un indicador booleano para determinar si el
	 * porcentaje de participación de todas las relaciones sumariza el 100%.
	 * 
	 * @return indicador booleano
	 */
	private boolean verificarPorcentajeParticipacion() {
		double porcentajeTotal = 0.00;
		
		for (int i=0;i<this.personaMoralBean.getRelacionesClienteRiesgo().getAccionistas().size();i++){
			if (!this.personaMoralBean.getRelacionesClienteRiesgo().getAccionistas().get(i).getEstado().equals(EstadoListadosEnum.ELIMINADO)){
				porcentajeTotal += this.personaMoralBean.getRelacionesClienteRiesgo().getAccionistas().get(i).getPorcentaje().doubleValue();
			}else{
				porcentajeTotal -= this.personaMoralBean.getRelacionesClienteRiesgo().getAccionistas().get(i).getPorcentaje().doubleValue();
			}
		}
				
		if (porcentajeTotal <= 100.00) {
			return true;
		} else {
			// De manera recursiva marcamos los campos de porcentaje.
			FacesContext.getCurrentInstance().validationFailed();
			List<UIComponent> componentes = FacesContext.getCurrentInstance().getViewRoot().getChildren();
			setValidationFalseRecursively(componentes);
			RequestContext.getCurrentInstance().execute("PF('dlgPorcentaje').show()");
			return false;
		}
	}
	
	/**
	 * Método privado que recorre de manera RECURSIVA todo el UIViewRoot y
	 * establece la validación de los campos de porcentaje a falso.
	 * 
	 * @param componentes
	 */
	private void setValidationFalseRecursively(List<UIComponent> componentes) {
		if (componentes != null && !componentes.isEmpty()) {
			for (UIComponent componente : componentes) {
				if (componente.getClientId().contains("porcentajeAccionista")) {
					((UIInput) componente).setValid(false);
				}
				this.setValidationFalseRecursively(componente.getChildren());
			}
		}
	}
	
	/**
	 * Método que permite insertar un registro a partir de la selección del usuario (lista con los tipos de relación).
	 */
	public void adicionarRelacion() {
		
		this.idContenedorScroll = new String();
		
		// Cambiamos el foco al nuevo elemento y se crea el elemento del tipo seleccionado
		
		switch (tipoRelacion) {
		case 1:
			
			idContenedorScroll = ID_CONTENEDOR_ACCIONISTA + this.personaMoralBean.getRelacionesClienteRiesgo().getAccionistas().size();
			
			PersonaMoralAccionistaBean accionista = new PersonaMoralAccionistaBean();
			accionista.setEstado(EstadoListadosEnum.NUEVO);
			this.personaMoralBean.getRelacionesClienteRiesgo().getAccionistas().add(accionista);
			break;
		case 2:
			
			idContenedorScroll = ID_CONTENEDOR_FUNCIONARIO + this.personaMoralBean.getRelacionesClienteRiesgo().getFuncionarios().size();
			
			PersonaMoralFuncionarioBean funcionario = new PersonaMoralFuncionarioBean();
			funcionario.setEstado(EstadoListadosEnum.NUEVO);
			this.personaMoralBean.getRelacionesClienteRiesgo().getFuncionarios().add(funcionario);
			break;
		case 3:
			
			idContenedorScroll = ID_CONTENEDOR_REFCOM + this.personaMoralBean.getRelacionesClienteRiesgo().getReferenciasComerciales().size();
			
			PersonaMoralRComercialBean referenciaComercial = new PersonaMoralRComercialBean();
			referenciaComercial.setEstado(EstadoListadosEnum.NUEVO);
			this.personaMoralBean.getRelacionesClienteRiesgo().getReferenciasComerciales().add(referenciaComercial);
			break;
		case 4:
			
			idContenedorScroll = ID_CONTENEDOR_REFBAN + this.personaMoralBean.getRelacionesClienteRiesgo().getReferenciasBancarias().size();
			
			PersonaMoralRBancariaBean referenciaBancaria = new PersonaMoralRBancariaBean();
			referenciaBancaria.setEstado(EstadoListadosEnum.NUEVO);
			this.personaMoralBean.getRelacionesClienteRiesgo().getReferenciasBancarias().add(referenciaBancaria);
			break;
			
		default:
			break;	

		}
		// Efectuamos el desplazamiento vertical según el elemento insertado.
		RequestContext.getCurrentInstance().execute("$('#main').animate({scrollTop: $('" + idContenedorScroll + "').offset().top}, 1000);");
	}	

	/**
	 * Método que permite eliminar un accionista.
	 * 
	 * @param accionista
	 */
	public void eliminarAccionista(PersonaMoralAccionistaBean accionista) {
		// Comprobamos que el numero de accionistas es mayor que dos para permitir eliminar
		if (this.personaMoralBean.getRelacionesClienteRiesgo().getAccionistas().size() >2){
			if(accionista.getEstado() == EstadoListadosEnum.NUEVO){
				this.personaMoralBean.getRelacionesClienteRiesgo().getAccionistas().remove(accionista);
	        }else{
	        	accionista.setEstado(EstadoListadosEnum.ELIMINADO);
	        }
		}else{
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Persona Moral",
					"Deben registrarse al menos dos accionistas para una persona moral de riesgo.");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
		}
	}

	/**
	 * Método que permite eliminar un funcionario.
	 * 
	 * @param funcionario
	 */
	public void eliminarFuncionario(PersonaMoralFuncionarioBean funcionario) {
		if(funcionario.getEstado() == EstadoListadosEnum.NUEVO){
			this.personaMoralBean.getRelacionesClienteRiesgo().getFuncionarios().remove(funcionario);
        }else{
        	funcionario.setEstado(EstadoListadosEnum.ELIMINADO);
        }
	}

	/**
	 * Método que permite eliminar una referencia comercial.
	 * 
	 * @param referenciaComercial
	 */
	public void eliminarReferenciaComercial(PersonaMoralRComercialBean referenciaComercial) {		
		if(referenciaComercial.getEstado() == EstadoListadosEnum.NUEVO){
			this.personaMoralBean.getRelacionesClienteRiesgo().getReferenciasComerciales().remove(referenciaComercial);
        }else{
        	referenciaComercial.setEstado(EstadoListadosEnum.ELIMINADO);
        }
	}

	/**
	 * Método que permite eliminar una referencia bancaria.
	 * 
	 * @param referenciaBancaria
	 */
	public void eliminarReferenciaBancaria(PersonaMoralRBancariaBean referenciaBancaria) {		
		if(referenciaBancaria.getEstado() == EstadoListadosEnum.NUEVO){
			this.personaMoralBean.getRelacionesClienteRiesgo().getReferenciasBancarias().remove(referenciaBancaria);
        }else{
        	referenciaBancaria.setEstado(EstadoListadosEnum.ELIMINADO);
        }
	}
	
	/**
	 * Método que permite modificar un objeto tipo PersonaMoralAccionistaBean.
	 * 
	 * @param accionista
	 */
	public void modificarAccionista(PersonaMoralAccionistaBean accionista) {
		accionista.setEstado(EstadoListadosEnum.MODIFICADO);
	}

	/**
	 * Método que permite modificar un objeto tipo PersonaMoralFuncionarioBean.
	 * 
	 * @param funcionario
	 */
	public void modificarFuncionario(PersonaMoralFuncionarioBean funcionario) {
		funcionario.setEstado(EstadoListadosEnum.MODIFICADO);
	}

	/**
	 * Método que permite modificar un objeto tipo PersonaMoralRComercialBean.
	 * 
	 * @param referenciaComercial
	 */
	public void modificarReferenciaComercial(PersonaMoralRComercialBean referenciaComercial) {
		referenciaComercial.setEstado(EstadoListadosEnum.MODIFICADO);
	}

	/**
	 * Método que permite modificar un objeto tipo PersonaMoralRBancariaBean.
	 * 
	 * @param referenciaBancaria
	 */
	public void modificarReferenciaBancaria(PersonaMoralRBancariaBean referenciaBancaria) {		
		referenciaBancaria.setEstado(EstadoListadosEnum.MODIFICADO);
	}
		
	/**
	 * Método que devuelve a su estado original un accionista.
	 * 
	 * @param accionista
	 */
	public void restaurarAccionista(PersonaMoralAccionistaBean accionista) {
		this.backUpUtils.recuperaBean(accionista);
	}

	/**
	 * Método que devuelve a su estado original un funcionario.
	 * 
	 * @param funcionario
	 */
	public void restaurarFuncionario(PersonaMoralFuncionarioBean funcionario) {
		this.backUpUtils.recuperaBean(funcionario);
	}

	/**
	 * Método que devuelve a su estado original una referencia comercial.
	 * 
	 * @param referenciaComercial
	 */
	public void restaurarReferenciaComercial(PersonaMoralRComercialBean referenciaComercial) {
		this.backUpUtils.recuperaBean(referenciaComercial);
	}

	/**
	 * Método que devuelve a su estado original una referencia bancaria.
	 * 
	 * @param referenciaBancaria
	 */
	public void restaurarReferenciaBancaria(PersonaMoralRBancariaBean referenciaBancaria) {
		this.backUpUtils.recuperaBean(referenciaBancaria);
	}
	
	/**
	 * @return Metodo utilizado para recuperar una instancia de Flash
	 */
	public Flash obtieneFlash() {
		return FacesContext.getCurrentInstance().getExternalContext().getFlash();
	}
	
	public String terminarFlujo() {
		Boolean requrido = false;
		if(this.personaMoralBean.getDonativosBean().getPersonas().contains("personas")){
			if(this.personaMoralBean.getDonativosBean().getPersonasEspecificas().equals("")){
				requrido = true;
			}
		}
		if(!requrido){
			// Se modifican los datos de riesgo de persona moral
			this.modificacionPerfilTransaccionalMoralBackEnd.ejecutarTRN(this.personaMoralBean);
			
			// Se guardan las relaciones de la persona moral
			this.guardarRelaciones(this.personaMoralBean.getRelacionesClienteRiesgo());
			
			if (this.isEsModificacion()) {
				RequestContext.getCurrentInstance().execute("PF('dlgModCorrecto').show()");
			}else{
				RequestContext.getCurrentInstance().execute("PF('dlgAltaCorrecto').show()");
				
			}
			LOGGER.debug("Salimos de la alta/modificacion del cliente con exito");
			
		}else{
			RequestContext.getCurrentInstance().execute("PF('dlgCampoPersonasRequerido').show()");
		}
		return null;
	}
	
	/**
     * Método utilizado para guardar las modificaciones en las referencias personales de clientes de riesgo
     * 
     * @param referencia
     */
    public void guardarRelaciones(RelacionesClienteBean relaciones) {
            	
    	// Se incluya la logica de actualizacion de accionistas
    	for (int i=0;i<relaciones.getAccionistas().size();i++){
        	if (relaciones.getAccionistas().get(i).getEstado().equals(EstadoListadosEnum.NUEVO)){
        		this.altaAccionista.ejecutarTRN(relaciones.getAccionistas().get(i), this.personaMoralBean.getIdInterna());
        	}else if (relaciones.getAccionistas().get(i).getEstado().equals(EstadoListadosEnum.MODIFICADO)){
        		this.modificacionAccionista.ejecutarTRN(this.personaMoralBean.getIdInterna(), relaciones.getAccionistas().get(i));
        	}else if (relaciones.getAccionistas().get(i).getEstado().equals(EstadoListadosEnum.ELIMINADO)){
        		this.bajaAccionista.ejecutarTRN(this.personaMoralBean.getIdInterna(), relaciones.getAccionistas().get(i).getIdInterno());
        	}
        }
    	
    	// Se incluya la logica de actualizacion de funcionarios
    	for (int i=0;i<relaciones.getFuncionarios().size();i++){
        	if (relaciones.getFuncionarios().get(i).getEstado().equals(EstadoListadosEnum.NUEVO)){
        		this.altaFuncionario.ejecutarTRN(relaciones.getFuncionarios().get(i), this.personaMoralBean.getIdInterna());
        	}else if (relaciones.getFuncionarios().get(i).getEstado().equals(EstadoListadosEnum.MODIFICADO)){
        		this.modificacionFuncionario.ejecutarTRN(this.personaMoralBean.getIdInterna(), relaciones.getFuncionarios().get(i));
        	}else if (relaciones.getFuncionarios().get(i).getEstado().equals(EstadoListadosEnum.ELIMINADO)){
        		this.bajaFuncionario.ejecutarTRN(this.personaMoralBean.getIdInterna(), relaciones.getFuncionarios().get(i).getIdInterno());
        	}
        }
    	
    	// Se incluya la logica de actualizacion de referencias bancarias
    	for (int i=0;i<relaciones.getReferenciasBancarias().size();i++){
        	if (relaciones.getReferenciasBancarias().get(i).getEstado().equals(EstadoListadosEnum.NUEVO)){
        		this.altaRBancaria.ejecutarTRN(relaciones.getReferenciasBancarias().get(i), this.personaMoralBean.getIdInterna());
        	}else if (relaciones.getReferenciasBancarias().get(i).getEstado().equals(EstadoListadosEnum.MODIFICADO)){
        		this.modificacionRBancaria.ejecutarTRN(this.personaMoralBean.getIdInterna(), relaciones.getReferenciasBancarias().get(i));
        	}else if (relaciones.getReferenciasBancarias().get(i).getEstado().equals(EstadoListadosEnum.ELIMINADO)){
        		this.bajaBancaria.ejecutarTRN(this.personaMoralBean.getIdInterna(), relaciones.getReferenciasBancarias().get(i).getIdInterno());
        	}
        }
    	
    	// Se incluya la logica de actualizacion de referencias comerciales
    	for (int i=0;i<relaciones.getReferenciasComerciales().size();i++){
        	if (relaciones.getReferenciasComerciales().get(i).getEstado().equals(EstadoListadosEnum.NUEVO)){
        		this.altaRComercial.ejecutarTRN(relaciones.getReferenciasComerciales().get(i), this.personaMoralBean.getIdInterna());
        	}else if (relaciones.getReferenciasComerciales().get(i).getEstado().equals(EstadoListadosEnum.MODIFICADO)){
        		this.modificacionRComercial.ejecutarTRN(this.personaMoralBean.getIdInterna(), relaciones.getReferenciasComerciales().get(i));
        	}else if (relaciones.getReferenciasComerciales().get(i).getEstado().equals(EstadoListadosEnum.ELIMINADO)){
        		this.bajaRComercial.ejecutarTRN(this.personaMoralBean.getIdInterna(), relaciones.getReferenciasComerciales().get(i).getIdInterno());
        	}
        }
    }
	
	public String irDocumentos() {
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE_PM_BEAN.getParamFlash(), this.personaMoralBean);
		if (this.esModificacion){
			obtieneFlash().put(ParametrosFlashEnum.MODIFICACION_CLIENTE.getParamFlash(), true);
		}else{
			obtieneFlash().put(ParametrosFlashEnum.MODIFICACION_CLIENTE.getParamFlash(), false);
		}
		return NavegacionEnum.DOCUMENTOS.getRuta();
	}
	
	/**
	 * @return Metodo utilizado para ir a la ficha de cliente
	 *         
	 */
	public String irFichaCliente() {						
		obtieneFlash().put(ParametrosFlashEnum.ID_INTERNA.getParamFlash(),this.personaMoralBean.getIdInterna());
        obtieneFlash().put(ParametrosFlashEnum.TIPO_CLIENTE.getParamFlash(),this.personaMoralBean.getTipo());
        return NavegacionEnum.FICHA_CLIENTE.getRuta();				
	}
	
	/**
	 * @return beanList
	 */
	@Override
	protected Map<String, Object> getBeanList() {
		Map<String, Object> beanList = new HashMap<String, Object>();
		beanList.put(ClientePersonaMoralBean.class.getName(), this.personaMoralBean);
		beanList.put(Boolean.class.getName(), this.esModificacion);
		return beanList;
	}

	/**
	 * @param beanList
	 */
	@Override
	protected void setBeanList(Map<String, Object> beanList) {
		if (null != beanList) {
			this.personaMoralBean = (ClientePersonaMoralBean) beanList.get(ClientePersonaMoralBean.class.getName());
			obtieneFlash().put(ParametrosFlashEnum.CLIENTE_PM_BEAN.getParamFlash(), this.personaMoralBean);
			this.esModificacion = (Boolean) beanList.get(Boolean.class.getName());
			obtieneFlash().put(ParametrosFlashEnum.MODIFICACION_CLIENTE.getParamFlash(), this.esModificacion);
		}

	}

	@Override
	protected String getNombreFlujo() {
		if (this.esModificacion){
			return "Modificacion de persona moral "+this.personaMoralBean.getRazonSocial()+" ("+this.obtenerDescripcionTpDoc()+" "+this.personaMoralBean.getNumIdentificacion()+")";
		}else{
			return "Alta de persona moral "+this.personaMoralBean.getRazonSocial()+" ("+this.obtenerDescripcionTpDoc()+" "+this.personaMoralBean.getNumIdentificacion()+")";			
		}
	}
	
	public String botonCancelar() {
		obtieneFlash().put(ParametrosFlashEnum.ID_INTERNA.getParamFlash(),this.personaMoralBean.getIdInterna());
		obtieneFlash().put(ParametrosFlashEnum.TIPO_CLIENTE.getParamFlash(),this.personaMoralBean.getTipo());
		return NavegacionEnum.FICHA_CLIENTE.getRuta();	
	}
	
	@StoreStep
	public String irPersonas() {
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE_PM_BEAN.getParamFlash(), this.personaMoralBean);
		obtieneFlash().put(ParametrosFlashEnum.MODIFICACION_CLIENTE.getParamFlash(), this.esModificacion);
		return NavegacionEnum.PERSONA_MORAL_PERSONAS.getRuta();
	}
	
	@StoreStep
	public String irGrupoFilialDesdePersonas() {
		
		if (verificarPorcentajeParticipacion()){
			obtieneFlash().put(ParametrosFlashEnum.CLIENTE_PM_BEAN.getParamFlash(), this.personaMoralBean);
			obtieneFlash().put(ParametrosFlashEnum.MODIFICACION_CLIENTE.getParamFlash(), this.esModificacion);
			return NavegacionEnum.PERSONA_MORAL_GRUPOFILIAL.getRuta();
		}
				
		return null;
	}
	
	@StoreStep
	public String irGrupoFilial() {
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE_PM_BEAN.getParamFlash(), this.personaMoralBean);
		obtieneFlash().put(ParametrosFlashEnum.MODIFICACION_CLIENTE.getParamFlash(), this.esModificacion);
		return NavegacionEnum.PERSONA_MORAL_GRUPOFILIAL.getRuta();
	}

	@StoreStep
	public String irDonativos() {
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE_PM_BEAN.getParamFlash(), this.personaMoralBean);
		obtieneFlash().put(ParametrosFlashEnum.MODIFICACION_CLIENTE.getParamFlash(), this.esModificacion);
		return NavegacionEnum.PERSONA_MORAL_DONATIVOS.getRuta();
	}
	
	/**
	 * @return the paiNacionalidadSelected
	 */
	public CatalogoBean getMonedaSelected() {
		if (this.personaMoralBean.getDonativosBean().getMoneda() != null && !("").equals(this.personaMoralBean.getDonativosBean().getMoneda())){
			this.monedaSelected = catalogoMonedaUtils.getCatalogoBean(this.personaMoralBean.getDonativosBean().getMoneda());
		}
		return this.monedaSelected;
	}

	/**
	 * @param paisNacionalidadSelected
	 */
	public void setMonedaSelected(CatalogoBean monedaSelected) {
		this.monedaSelected = monedaSelected;
		if (this.monedaSelected  != null){
			this.personaMoralBean.getDonativosBean().setMoneda(this.monedaSelected.getClaveFila());
		}
	}
	
	/**
	 * @return the paiNacionalidadSelected
	 */
	public CatalogoBean getMonedaOtraSelected() {
		if (this.personaMoralBean.getDonativosBean().getIndicarMoneda() != null && !("").equals(this.personaMoralBean.getDonativosBean().getIndicarMoneda())){
			this.monedaOtraSelected = catalogoMonedaUtils.getCatalogoBean(this.personaMoralBean.getDonativosBean().getIndicarMoneda());
		}
		return this.monedaOtraSelected;
	}

	/**
	 * @param paisNacionalidadSelected
	 */
	public void setMonedaOtraSelected(CatalogoBean monedaOtraSelected) {
		this.monedaOtraSelected = monedaOtraSelected;
		if (this.monedaOtraSelected  != null){
			this.personaMoralBean.getDonativosBean().setIndicarMoneda(this.monedaOtraSelected.getClaveFila());
		}
	}

	/**
	 * @return the paisNacionalidadSelected
	 */
	public CatalogoBean getPaisNacionalidadSelected() {
		if (this.personaMoralBean.getGrupoFilialBean().getPais() != null && !("").equals(this.personaMoralBean.getGrupoFilialBean().getPais())){
			paisNacionalidadSelected = catalogoPaisesUtils.getCatalogoBean(this.personaMoralBean.getGrupoFilialBean().getPais());
		}
		return paisNacionalidadSelected;
	}

	/**
	 * @param paisNacionalidadSelected
	 */
	public void setPaisNacionalidadSelected(CatalogoBean paisNacionalidadSelected) {
		this.paisNacionalidadSelected = paisNacionalidadSelected;
		if (this.paisNacionalidadSelected != null){
			this.personaMoralBean.getGrupoFilialBean().setPais(this.paisNacionalidadSelected.getClaveFila());
		}
	}

	/**
	 * @return the paisResidenciaSelected
	 */
	public CatalogoBean getPaisResidenciaSelected() {
		if (this.personaMoralBean.getGrupoFilialBean().getPaisResidencia() != null && !("").equals(this.personaMoralBean.getGrupoFilialBean().getPaisResidencia())){
			paisResidenciaSelected = catalogoPaisesUtils.getCatalogoBean(this.personaMoralBean.getGrupoFilialBean().getPaisResidencia());
		}
		return paisResidenciaSelected;
	}

	/**
	 * @param paisResidenciaSelected the paisResidenciaSelected to set
	 */
	public void setPaisResidenciaSelected(CatalogoBean paisResidenciaSelected) {
		this.paisResidenciaSelected = paisResidenciaSelected;
		if (this.paisNacionalidadSelected != null){
			this.personaMoralBean.getGrupoFilialBean().setPaisResidencia(this.paisResidenciaSelected.getClaveFila());
		}
	}
	
	public ClientePersonaMoralBean getPersonaMoralBean() {
		return personaMoralBean;
	}

	public void setPersonaMoralBean(ClientePersonaMoralBean personaMoralBean) {
		this.personaMoralBean = personaMoralBean;
	}
	
	public int getTipoRelacion() {
		return tipoRelacion;
	}

	public void setTipoRelacion(int tipoRelacion) {
		this.tipoRelacion = tipoRelacion;
	}

	/**
	 * @return the idContenedorScroll
	 */
	public String getIdContenedorScroll() {
		return idContenedorScroll;
	}

	/**
	 * @param idContenedorScroll the idContenedorScroll to set
	 */
	public void setIdContenedorScroll(String idContenedorScroll) {
		this.idContenedorScroll = idContenedorScroll;
	}

	public CatalogoUtils getCatalogoUtils() {
		return catalogoUtils;
	}

	public void setCatalogoUtils(CatalogoUtils catalogoUtils) {
		this.catalogoUtils = catalogoUtils;
	}

	public List<String> getSelectedFrecuencia() {
		return selectedFrecuencia;
	}

	public void setSelectedFrecuencia(List<String> selectedFrecuencia) {
		this.selectedFrecuencia = selectedFrecuencia;
	}

	public List<String> getSelectedEntrega() {
		return selectedEntrega;
	}

	public void setSelectedEntrega(List<String> selectedEntrega) {
		this.selectedEntrega = selectedEntrega;
	}

	public List<String> getSelectedPersonas() {
		return selectedPersonas;
	}

	public void setSelectedPersonas(List<String> selectedPersonas) {
		this.selectedPersonas = selectedPersonas;
	}

	public boolean isEsModificacion() {
		return esModificacion;
	}

	public void setEsModificacion(boolean esModificacion) {
		this.esModificacion = esModificacion;
	}

	/**
	 * @return the elementosVisibles
	 */
	public boolean isElementosVisibles() {
		return elementosVisibles;
	}

	/**
	 * @param elementosVisibles the elementosVisibles to set
	 */
	public void setElementosVisibles(boolean elementosVisibles) {
		this.elementosVisibles = elementosVisibles;
	}

}
