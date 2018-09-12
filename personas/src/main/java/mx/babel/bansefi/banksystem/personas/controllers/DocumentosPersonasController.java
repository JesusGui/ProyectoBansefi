package mx.babel.bansefi.banksystem.personas.controllers;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.backends.ConsultaCuentasClienteBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaDomiciliosPersonaBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaListaDocumentosBackend;
import mx.babel.bansefi.banksystem.base.backends.ConsultaPersonaFisicaBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaPersonasRelacionadasBackEnd;
import mx.babel.bansefi.banksystem.base.backends.consultadomicilio.ConsultaDomicilioBackend;
import mx.babel.bansefi.banksystem.base.beans.DocumentoPersonaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClienteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaFisicaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaMoralBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaClienteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioTipoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.PersonaRelacionadaBean;
import mx.babel.bansefi.banksystem.base.beans.reportes.PlantillaBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.controllers.DomicilioController;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.enums.TipoDomicilioEnum;
import mx.babel.bansefi.banksystem.base.utils.BeanBackUpUtils;
import mx.babel.bansefi.banksystem.base.utils.CatalogoCentrosLoaderTask;
import mx.babel.bansefi.banksystem.base.utils.CatalogoPaisesUtils;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.DomicilioUtils;
import mx.babel.bansefi.banksystem.base.utils.DomicilioWrapper;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;
import mx.babel.bansefi.banksystem.base.utils.PdfUtils;
import mx.babel.bansefi.banksystem.base.utils.WordUtils;
import mx.babel.bansefi.banksystem.personas.backend.AltaDocumentoPersonaBackend;
import mx.babel.bansefi.banksystem.personas.backend.BajaDocumentoBackend;
import mx.babel.bansefi.banksystem.personas.backend.ConsultaDetalleDocumentoBackend;
import mx.babel.bansefi.banksystem.personas.backend.ModificaDocumentoPersonaBackend;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Controlador encargado de los documentos asociados a personas
 * 
 * @author luis.gonzalez
 * 
 */

@ManagedBean(name = "documentosPersonasController")
@ViewScoped
@Component
@Scope("view")
public class DocumentosPersonasController implements Serializable {

    private static final long serialVersionUID = 3597963734102295092L;
    
    private static final String SALTO_LINEA = " <br/> ";
    
    private static final Logger LOGGER = LogManager
            .getLogger(DocumentosPersonasController.class.getName());

    private static final Integer ALTA_DOCUMENTOS = 1;

    private static final Integer BAJA_DOCUMENTOS = 2;

    private static final Integer MODIFICACION_DOCUMENTOS = 3;

    private static final String CTE_IR_FICHA = "Ir a ficha cliente";
    
    private static final String CTE_CANCELAR = "Cancelar";

    private static final String CTE_VOLVER_FICHA = "Volver a ficha cliente";

    private static final String CTE_VOLVER_RELACION = "Volver a relacionar personas";

    private static final String CTE_RELACION_BENEFICIARIO = "123";

    private static final String CTE_RELACION_COTITULAR = "921";

    private static final String CTE_RELACION_APODERADO = "407";

    private static final String CTE_RELACION_PROPIETARIO = "619";

    private static final String CTE_RELACION_PROPIETARIO_BIS = "203";

    private CatalogoBean tipoDocumento;

    private Integer idInternaPersona;

    private Integer documentosEliminados;

    private ClientePersonaFisicaBean clientePersonaFisica;

    private ClientePersonaMoralBean clientePersonaMoral;
    
    private String valorBotonVolver;
    
    private boolean isAlta;

    // Lista con los nuevos documentos
    private List<DocumentoPersonaBean> documentosNuevos;

    // Lista con los documentos consultados
    private List<DocumentoPersonaBean> documentosConsultados;
    
    
    
    /**
     * Variable utilizada para guardar la redireccion al flujo de entrada a la
     * consulta de detalles de anotaciones.
     */
    private NavegacionEnum destino;

    /**
     * Variable utilizada para guardar el contenido del controller origen a la
     * consulta de detalles de anotaciones.
     */
    private Object destinoController;

    /**
     * Variable que se encarga de comprobar si se han producidos errores previos
     */
    private Boolean errorDocumentos;
    
    private ClienteBean cliente;

    @Autowired
    ContextoUtils contextoUtils;

    @Autowired
    CatalogoCentrosLoaderTask catalogoCentrosLoaderTask;

    @Autowired
    ManagedBeanStateRecover managedBeanStateRecover;

    @Autowired
    private WordUtils wordUtils;

    @Autowired
    private PdfUtils pdfUtils;

    @Autowired
    private CatalogoUtils catalogoUtils;

    @Autowired
    private ConsultaListaDocumentosBackend consultaListaDocumentos;

    @Autowired
    private ConsultaDetalleDocumentoBackend consultaDetalleDocumento;

    @Autowired
    private AltaDocumentoPersonaBackend altaDocumento;

    @Autowired
    private BajaDocumentoBackend bajaDocumento;

    @Autowired
    private ModificaDocumentoPersonaBackend modificaDocumento;
    
    @Autowired
    private ConsultaPersonasRelacionadasBackEnd consultaPersonasRelacionadas;

    @Autowired
    BeanBackUpUtils backUpUtils;

    @Autowired
    ConsultaPersonaFisicaBackEnd consultaPersonasFisica;
    
    @Autowired
    ConsultaCuentasClienteBackEnd consultaCuentasCliente;

    @Autowired
    private DomicilioController domicilioController;
    
    @Autowired
    DomicilioUtils domicilioUtils;
    
    @Autowired
    private ConsultaDomiciliosPersonaBackEnd consultaDomiciliosPersonaBackEnd;
    
    @Autowired
    private ConsultaDomicilioBackend consultaDetalleDomicilioBackEnd;
    
    @Autowired
    private DomicilioWrapper domicilioWrapper;
    
    @Autowired
    private CatalogoPaisesUtils catalogoPaises;
    
    @PostConstruct
    public void init() {
        LOGGER.debug("Entra el metodo init");
        this.destino = managedBeanStateRecover.getDestino();
        this.destinoController = managedBeanStateRecover.getController();
        // Opcion por defecto
        this.valorBotonVolver = "Cancelar";
        this.inicializate();
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

    /**
     * Se encarga de eliminar un documento nuevo de la lista
     * 
     * @param documento
     */
    public void eliminaNuevoDocumento(DocumentoPersonaBean documento) {
        this.documentosNuevos.remove(documento);
    }

    /**
     * Se encarga de eliminar un documento consultado de la lista
     * 
     * @param documento
     */
    public void eliminaDocumentoConsultado(DocumentoPersonaBean documento) {
        documento.setEstado(EstadoListadosEnum.ELIMINADO);
    }

    /**
     * Se encarga de editar un documento consultado de la lista
     * 
     * @param documento
     */
    public void editaDocumentoConsultado(DocumentoPersonaBean documento) {
        this.consultaDetalleDocumento.ejectuarTRN(documento);
        documento.setEstado(EstadoListadosEnum.MODIFICADO);
    }

    /**
     * Se encarga de procesar todos los cambios sobre los documentos
     */
    public String guardarDocumentos() {
        this.documentosEliminados = 0;
        Boolean eliminados = false;
        Boolean cambios = false;
        if (this.documentosNuevos != null && this.documentosNuevos.size() > 0) {
            cambios = true;
        }

        for (DocumentoPersonaBean documento : this.documentosConsultados) {
            if (documento.getEstado() == EstadoListadosEnum.ELIMINADO) {
                this.documentosEliminados++;
                cambios = true;
                eliminados = true;

            } else if (documento.getEstado() == EstadoListadosEnum.MODIFICADO) {
                cambios = true;
            }
        }

        if (eliminados) {
            RequestContext.getCurrentInstance().execute(
                    "PF('dlgConfirmacionModificacion').show()");
            return null;
        } else if (cambios) {

            return this.confirmarGuardar();

        } else {
            RequestContext.getCurrentInstance().execute(
                    "PF('dlgNoCambios').show()");
            return null;
        }

    }

    /**
     * Se encarga de procesar todos los cambios sobre los documentos
     */
    public String confirmarGuardar() {
        Integer tipoModificacionDocumentos = null;
        Boolean errorDocumentos = false;
        for (DocumentoPersonaBean documento : this.documentosNuevos) {
            int returnCode = this.altaDocumento.ejectuarTRN(documento,
                    this.idInternaPersona);
            tipoModificacionDocumentos = DocumentosPersonasController.ALTA_DOCUMENTOS;
            if (returnCode != BackEndBean.RETURN_COD_OK) {
                errorDocumentos = true;
            }else{
            	documento.setEstado(EstadoListadosEnum.CONSULTADO);
            }
        }
        for (DocumentoPersonaBean documento : this.documentosConsultados) {
            if (documento.getEstado() == EstadoListadosEnum.ELIMINADO) {
                int returnCode = this.bajaDocumento.ejectuarTRN(documento,
                        this.idInternaPersona);
                if (tipoModificacionDocumentos != null) {
                    tipoModificacionDocumentos = DocumentosPersonasController.MODIFICACION_DOCUMENTOS;
                } else {
                    tipoModificacionDocumentos = DocumentosPersonasController.BAJA_DOCUMENTOS;
                }
                if (returnCode != BackEndBean.RETURN_COD_OK) {
                    errorDocumentos = true;
                }

            } else if (documento.getEstado() == EstadoListadosEnum.MODIFICADO) {
                int returnCode = this.modificaDocumento.ejectuarTRN(documento);
                tipoModificacionDocumentos = DocumentosPersonasController.MODIFICACION_DOCUMENTOS;
                if (returnCode != BackEndBean.RETURN_COD_OK) {
                    errorDocumentos = true;
                }else{
                	documento.setEstado(EstadoListadosEnum.CONSULTADO);
                }
            }
        }
        this.obtieneFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(),
                this.cliente);
        this.obtieneFlash().put(
                ParametrosFlashEnum.ERROR_DOCUMENTOS.getParamFlash(),
                errorDocumentos);
        this.obtieneFlash().put(
                ParametrosFlashEnum.MODIFICACION_DOCUMENTOS.getParamFlash(),
                tipoModificacionDocumentos);
        managedBeanStateRecover.enviaControllerMap(this.destinoController,
                destino);
        return NavegacionEnum.DOCUMENTOS.getRuta();
    }

    /**
     * Método que con base al idContenedor, permite actualizar un elemento único
     * de la vista.
     * 
     * @param idContenedor
     */
    public void restauraDocumentoConsultado(DocumentoPersonaBean documento) {
        backUpUtils.recuperaBean(documento);
    }

    /**
     * Se encarga de mostrar los dialogs en caso de que se hayam producido
     * cambios
     * 
     */
    public String volver() {
        Boolean cambios = false;
        if (this.documentosNuevos != null && this.documentosNuevos.size() > 0) {
            cambios = true;
        }

        for (DocumentoPersonaBean documento : this.documentosConsultados) {
            if (documento.getEstado() == EstadoListadosEnum.ELIMINADO) {
                cambios = true;
            } else if (documento.getEstado() == EstadoListadosEnum.MODIFICADO) {
                cambios = true;
            }
        }

        if (cambios) {
            RequestContext.getCurrentInstance().execute(
                    "PF('dlgVolver').show()");
            return null;
        } else {
            return this.confirmarVolver();
        }
    }

    /**
     * Se encarga de determinar la vuelta al flujo de ficha de cliente
     * 
     * @return
     */
    public String confirmarVolver() {

        String ruta = StringUtils.EMPTY;

        try 
        {
	        // En caso de que vengamos del alta o de la ficha de cliente
	        if (destino == null || NavegacionEnum.FICHA_CLIENTE.equals(destino) ) {
	            obtieneFlash().put(ParametrosFlashEnum.ID_INTERNA.getParamFlash(),
	                    this.cliente.getIdInterna());
	            obtieneFlash().put(
	                    ParametrosFlashEnum.TIPO_CLIENTE.getParamFlash(),
	                    this.cliente.getTipo());
	            return NavegacionEnum.FICHA_CLIENTE.getRuta();
	
	        }
	        // En caso de que vengamos de relacion cliente-persona
	        else if (NavegacionEnum.RELACIONES_CLIENTE.equals(destino)){
	        	this.obtieneFlash().put(ParametrosFlashEnum.ID_INTERNA.getParamFlash(),
		                    this.cliente.getIdInterna());
	            this.obtieneFlash().put(
	                    ParametrosFlashEnum.CLIENTE.getParamFlash(), this.cliente);
	            ruta = destino.getRuta();
	            managedBeanStateRecover.finNavegacion(destinoController);
	        }
	        // En caso de que vengamos de relacion cuenta-persona
	        else {
	            this.obtieneFlash().put(
	                    ParametrosFlashEnum.CLIENTE.getParamFlash(), this.cliente);
	            ruta = destino.getRuta();
	            managedBeanStateRecover.finNavegacion(destinoController);
	        }
        } catch (Exception e){
        	ruta = NavegacionEnum.INICIO.getRuta();
        }
        
        return ruta;

    }

    /**
     * Se encarga de emitir el documento de Cedula correspondiente
     */
    public void emitirDocumento() {
        if (this.cliente instanceof ClientePersonaFisicaBean) {
            this.clientePersonaFisica = (ClientePersonaFisicaBean) this.cliente;

            // Consultamos los personas relacionadas
            List<PersonaRelacionadaBean> relaciones = this.consultaPersonasRelacionadas
                    .ejecutarTRN(this.idInternaPersona);
            Boolean tieneCotitular = false;
            Boolean tieneApoderado = false;
            Boolean tienePropietario = false;
            ClientePersonaFisicaBean beneficiario = null;
            ClientePersonaFisicaBean cotitular = null;
            ClientePersonaFisicaBean apoderado = null;
            ClientePersonaFisicaBean propietario = null; 
            
            String fechasNacimientoBeneficiarios = StringUtils.EMPTY;
            String noContrato = StringUtils.EMPTY;
            String estadoCivilCotitular = StringUtils.EMPTY;
            String ocupacionCotitular = StringUtils.EMPTY;
            String sexoCotitular = StringUtils.EMPTY;
            String sexoApoderado = StringUtils.EMPTY;
            String cnaeCotitular = StringUtils.EMPTY; 
            String fechasNacimientoCotitular = StringUtils.EMPTY;
            String lugarNacimientoCotitular = StringUtils.EMPTY;
            String estadoNacimientoCotitular = StringUtils.EMPTY;
            String paisNacimientoCotitular = StringUtils.EMPTY;
            String estadoCivilApoderado = StringUtils.EMPTY;
            String ocupacionApoderado = StringUtils.EMPTY;
            String cnaeApoderado = StringUtils.EMPTY;
            String fechasNacimientoApoderado = StringUtils.EMPTY;
            String lugarNacimientoApoderado = StringUtils.EMPTY;
            String estadoNacimientoApoderado = StringUtils.EMPTY;
            String paisNacimientoApoderado = StringUtils.EMPTY;
            String estadoCivilPropietario = StringUtils.EMPTY;
            String ocupacionPropietario = StringUtils.EMPTY;
            String cnaePropietario = StringUtils.EMPTY;
            String fechasNacimientoPropietario = StringUtils.EMPTY;
            String lugarNacimientoPropietario = StringUtils.EMPTY;
            String estadoNacimientoPropietario = StringUtils.EMPTY;
            String paisNacimientoPropietario = StringUtils.EMPTY;
            String sexoPropietario = StringUtils.EMPTY;
            
            SimpleDateFormat sdf = new SimpleDateFormat(ConstantesFuncionales.GENERAL_DATE_FORMATTER);
            List<CuentaClienteBean> cuenta = consultaCuentasCliente.ejecutarTRN(this.clientePersonaFisica.getIdInterna(), true);
            if(cuenta!=null && !cuenta.isEmpty() && cuenta.get(0).getCuenta() != null){
            	noContrato = cuenta.get(0).getCuenta().getNumeroCuenta().toString();
            }
            

            // Si las relaciones son del tipo deseado consultamos los datos del
            // cliente
            for (PersonaRelacionadaBean personaRelacionadaBean : relaciones) {
                if (DocumentosPersonasController.CTE_RELACION_BENEFICIARIO
                                .equals(personaRelacionadaBean
                                        .getRelacionSeleccionada()
                                        .getClaveFila())) {
                   
                    if(beneficiario==null){
                    	try{
                    		beneficiario = (ClientePersonaFisicaBean) this.consultaPersonasFisica
                                    .ejecutarTRN(personaRelacionadaBean.getIdInterna());
                                  fechasNacimientoBeneficiarios = sdf.format(beneficiario.getFechaNacimiento());
                    		
                    	}catch(ControlableException | NoControlableException ce){
                    		
                    	}
                        
                    }else{
                    	ClientePersonaFisicaBean beneficiarioTemporal = null;
                    	try{
                         beneficiarioTemporal = (ClientePersonaFisicaBean) this.consultaPersonasFisica
                                .ejecutarTRN(personaRelacionadaBean.getIdInterna());
                    	}catch(ControlableException | NoControlableException ce){
                    	}
                    	if(beneficiarioTemporal != null){
	                        //Nombre
	                        if(StringUtils.isNotBlank(beneficiarioTemporal.getNombre())){
	                            beneficiario.setNombre(beneficiario.getNombre() + DocumentosPersonasController.SALTO_LINEA + beneficiarioTemporal.getNombre() );
	                        }else{
	                            beneficiario.setNombre(beneficiario.getNombre() + DocumentosPersonasController.SALTO_LINEA);
	                        }
	                        
	                        if(StringUtils.isNotBlank(beneficiarioTemporal.getApePaterno())){
	                            beneficiario.setApePaterno(beneficiario.getApePaterno() +  DocumentosPersonasController.SALTO_LINEA + beneficiarioTemporal.getApePaterno() );
	                        }else{
	                            beneficiario.setApePaterno(beneficiario.getApePaterno() + DocumentosPersonasController.SALTO_LINEA);
	                        }
	                        
	                        //ApeMaterno
	                        if(StringUtils.isNotBlank(beneficiarioTemporal.getApeMaterno())){
	                            beneficiario.setApeMaterno(beneficiario.getApeMaterno() + DocumentosPersonasController.SALTO_LINEA + beneficiarioTemporal.getApeMaterno() );
	                        }else{
	                            beneficiario.setApeMaterno(beneficiario.getApeMaterno() + DocumentosPersonasController.SALTO_LINEA);
	                        }
	                        //Nombre calle
	                        if(StringUtils.isNotBlank(beneficiario.getDomicilios().get(0).getNombreCalle())){
	                            beneficiario.getDomicilios().get(0).setNombreCalle(beneficiario.getDomicilios().get(0).getNombreCalle() +  DocumentosPersonasController.SALTO_LINEA + beneficiarioTemporal.getDomicilios().get(0).getNombreCalle());
	                        }else{
	                            beneficiario.getDomicilios().get(0).setNombreCalle(beneficiario.getDomicilios().get(0).getNombreCalle() +  DocumentosPersonasController.SALTO_LINEA);
	                        }
	                        //Numero Exterior
	                        if(StringUtils.isNotBlank(beneficiario.getDomicilios().get(0).getNumeroExterior())){
	                            beneficiario.getDomicilios().get(0).setNumeroExterior(beneficiario.getDomicilios().get(0).getNumeroExterior() +  DocumentosPersonasController.SALTO_LINEA + beneficiarioTemporal.getDomicilios().get(0).getNumeroExterior());
	                        }else{
	                            beneficiario.getDomicilios().get(0).setNumeroExterior(beneficiario.getDomicilios().get(0).getNumeroExterior() +  DocumentosPersonasController.SALTO_LINEA);
	                        }
	                        //Numero interior
	                        if(StringUtils.isNotBlank(beneficiario.getDomicilios().get(0).getInterior()) && StringUtils.isNotBlank(beneficiarioTemporal.getDomicilios().get(0).getInterior())){
	                            beneficiario.getDomicilios().get(0).setInterior(beneficiario.getDomicilios().get(0).getInterior() +  DocumentosPersonasController.SALTO_LINEA + beneficiarioTemporal.getDomicilios().get(0).getInterior());
	                        }else if(StringUtils.isNotBlank(beneficiario.getDomicilios().get(0).getInterior()) && !StringUtils.isNotBlank(beneficiarioTemporal.getDomicilios().get(0).getInterior())){
	                            beneficiario.getDomicilios().get(0).setInterior(beneficiario.getDomicilios().get(0).getInterior() +  DocumentosPersonasController.SALTO_LINEA);
	                        }else if(!StringUtils.isNotBlank(beneficiario.getDomicilios().get(0).getInterior()) && StringUtils.isNotBlank(beneficiarioTemporal.getDomicilios().get(0).getInterior())){
	                            beneficiario.getDomicilios().get(0).setInterior(StringUtils.EMPTY +  DocumentosPersonasController.SALTO_LINEA + beneficiarioTemporal.getDomicilios().get(0).getInterior() );
	                        }
	                       
	                        //Colonia
	                        if(StringUtils.isNotBlank(beneficiario.getDomicilios().get(0).getColonia())){
	                            beneficiario.getDomicilios().get(0).setColonia(beneficiario.getDomicilios().get(0).getColonia() +  DocumentosPersonasController.SALTO_LINEA + beneficiarioTemporal.getDomicilios().get(0).getColonia());
	                        }else{
	                            beneficiario.getDomicilios().get(0).setColonia(beneficiario.getDomicilios().get(0).getColonia() +  DocumentosPersonasController.SALTO_LINEA);
	                        }
	                        //nombreMunicipio
	                        if(StringUtils.isNotBlank(beneficiario.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreMunicipio())){
	                            beneficiario.getDomicilios().get(0).getDatosFinalesCatGeo().setNombreMunicipio(beneficiario.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreMunicipio() +  DocumentosPersonasController.SALTO_LINEA + beneficiarioTemporal.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreMunicipio());
	                        }else{
	                            beneficiario.getDomicilios().get(0).getDatosFinalesCatGeo().setNombreMunicipio(beneficiario.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreMunicipio() +  DocumentosPersonasController.SALTO_LINEA);
	                        }
	                        //nombreProvincia
	                        if(StringUtils.isNotBlank(beneficiario.getDomicilios().get(0).getMunicipioCatGeo().getNombreProvincia())){
	                            beneficiario.getDomicilios().get(0).getDatosFinalesCatGeo().setNombreProvincia(beneficiario.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreProvincia() +  DocumentosPersonasController.SALTO_LINEA + beneficiarioTemporal.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreProvincia());
	                        }else{
	                            beneficiario.getDomicilios().get(0).getDatosFinalesCatGeo().setNombreProvincia(beneficiario.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreProvincia() +  DocumentosPersonasController.SALTO_LINEA);
	                        }
	                        //Pais
	                        if(StringUtils.isNotBlank(beneficiario.getDomicilios().get(0).getDatosFinalesCatGeo().getNombrePais())){
	                            beneficiario.getDomicilios().get(0).getDatosFinalesCatGeo().setNombrePais(beneficiario.getDomicilios().get(0).getDatosFinalesCatGeo().getNombrePais() +  DocumentosPersonasController.SALTO_LINEA + beneficiarioTemporal.getDomicilios().get(0).getDatosFinalesCatGeo().getNombrePais());
	                        }else{
	                            beneficiario.getDomicilios().get(0).getDatosFinalesCatGeo().setNombrePais(beneficiario.getDomicilios().get(0).getDatosFinalesCatGeo().getNombrePais() +  DocumentosPersonasController.SALTO_LINEA);
	                        }
	                        //CP
	                        if(StringUtils.isNotBlank(beneficiario.getDomicilios().get(0).getDatosFinalesCatGeo().getCodigoPostal())){
	                            beneficiario.getDomicilios().get(0).getDatosFinalesCatGeo().setCodigoPostal(beneficiario.getDomicilios().get(0).getDatosFinalesCatGeo().getCodigoPostal() +  DocumentosPersonasController.SALTO_LINEA + beneficiarioTemporal.getDomicilios().get(0).getDatosFinalesCatGeo().getCodigoPostal());
	                        }else{
	                            beneficiario.getDomicilios().get(0).getDatosFinalesCatGeo().setCodigoPostal(beneficiario.getDomicilios().get(0).getDatosFinalesCatGeo().getCodigoPostal() +  DocumentosPersonasController.SALTO_LINEA);
	                        }
	                        //Fechas de nacimientos
	                        fechasNacimientoBeneficiarios = fechasNacimientoBeneficiarios + DocumentosPersonasController.SALTO_LINEA + sdf.format(beneficiarioTemporal.getFechaNacimiento());
                    	}
                    }
//                    beneficiario = (ClientePersonaFisicaBean) this.consultaPersonasFisica
//                            .ejecutarTRN(personaRelacionadaBean.getIdInterna());
                }
                if (!tieneCotitular
                        && DocumentosPersonasController.CTE_RELACION_COTITULAR
                                .equals(personaRelacionadaBean
                                        .getRelacionSeleccionada()
                                        .getClaveFila())) {
                    //tieneCotitular = true;
                    if(cotitular == null){
                    	try{
                    		cotitular = (ClientePersonaFisicaBean) this.consultaPersonasFisica
                                    .ejecutarTRN(personaRelacionadaBean.getIdInterna());
                        	estadoCivilCotitular = this.getEstadoCivilCotitular(estadoCivilCotitular, cotitular);
                        	ocupacionCotitular = this.getOcupacionCotitular(ocupacionCotitular, cotitular);
                        	sexoCotitular = this.getSexoCotitular(sexoCotitular, cotitular);
                        	cnaeCotitular = this.getCnaeCotitular(cnaeCotitular, cotitular);
                        	fechasNacimientoCotitular = sdf.format(cotitular.getFechaNacimiento());
                        	lugarNacimientoCotitular = this.getLugarNacimiento(lugarNacimientoCotitular, cotitular);
                        	estadoNacimientoCotitular = this.getEstadoNacimiento(estadoNacimientoCotitular, cotitular);
                        	paisNacimientoCotitular = this.getPaisNacimiento(paisNacimientoCotitular, cotitular);
                    	}catch(ControlableException | NoControlableException ce){
                    		
                    	}
                    	
                    	
                    }else{
                    	ClientePersonaFisicaBean cotitularTemporal = null;
                    	try{
                    		cotitularTemporal = (ClientePersonaFisicaBean) this.consultaPersonasFisica.ejecutarTRN(personaRelacionadaBean.getIdInterna());
                    	}catch(ControlableException | NoControlableException ce){
                    		
                    	}
                    	if (cotitularTemporal != null){
                    		//NOMBRE TITULAR
                        	if(StringUtils.isNotBlank(cotitularTemporal.getNombre())){
                                cotitular.setNombre(cotitular.getNombre() + DocumentosPersonasController.SALTO_LINEA + cotitularTemporal.getNombre() );
                            }else{
                                cotitular.setNombre(cotitular.getNombre() + DocumentosPersonasController.SALTO_LINEA);
                            }
                        	
                        	//APELLIDO PATERNO
                        	if(StringUtils.isNotBlank(cotitularTemporal.getApePaterno())){
                                cotitular.setApePaterno(cotitular.getApePaterno() + DocumentosPersonasController.SALTO_LINEA + cotitularTemporal.getApePaterno() );
                            }else{
                                cotitular.setApePaterno(cotitular.getApePaterno() + DocumentosPersonasController.SALTO_LINEA);
                            }
                        	
                        	//APELLIDO MATERNO
                        	if(StringUtils.isNotBlank(cotitularTemporal.getApeMaterno())){
                                cotitular.setApeMaterno(cotitular.getApeMaterno() + DocumentosPersonasController.SALTO_LINEA + cotitularTemporal.getApeMaterno() );
                            }else{
                                cotitular.setApeMaterno(cotitular.getApeMaterno() + DocumentosPersonasController.SALTO_LINEA);
                            }
                        	
                        	//SEXO
                        	sexoCotitular = sexoCotitular + DocumentosPersonasController.SALTO_LINEA + this.getSexoCotitular(sexoCotitular, cotitularTemporal);
                        	
                        	//CURP
                        	if(StringUtils.isNotBlank(cotitularTemporal.getCurp())){
                                cotitular.setCurp(cotitular.getCurp() + DocumentosPersonasController.SALTO_LINEA + cotitularTemporal.getCurp() );
                            }else{
                                cotitular.setCurp(cotitular.getCurp() + DocumentosPersonasController.SALTO_LINEA);
                            }
                        	
                        	//RFC
                        	if(StringUtils.isNotBlank(cotitularTemporal.getRfc())){
                                cotitular.setRfc(cotitular.getRfc() + DocumentosPersonasController.SALTO_LINEA + cotitularTemporal.getRfc() );
                            }else{
                                cotitular.setRfc(cotitular.getRfc() + DocumentosPersonasController.SALTO_LINEA);
                            }
                        	
                        	//ESTADO_CIVIL
                        	estadoCivilCotitular = estadoCivilCotitular + DocumentosPersonasController.SALTO_LINEA + this.getEstadoCivilCotitular(estadoCivilCotitular, cotitularTemporal);
                        	
                        	//OCUPACION O PROFESION
                        	ocupacionCotitular = ocupacionCotitular + DocumentosPersonasController.SALTO_LINEA + this.getOcupacionCotitular(ocupacionCotitular, cotitularTemporal);
                        	
                        	//CNAE
                        	if(StringUtils.isNotBlank(cotitularTemporal.getCnae())){
                        		cnaeCotitular = cnaeCotitular + DocumentosPersonasController.SALTO_LINEA + this.getCnaeCotitular(cnaeCotitular, cotitularTemporal);
                            }else{
                                cnaeCotitular = this.getCnaeCotitular(cnaeCotitular, cotitularTemporal) + DocumentosPersonasController.SALTO_LINEA;
                            }
                        	
                        	
                        	//FECHA DE NACIMIENTO
                        	fechasNacimientoCotitular = fechasNacimientoCotitular + DocumentosPersonasController.SALTO_LINEA + sdf.format(cotitularTemporal.getFechaNacimiento());
                        	
                        	//LUGAR DE NACIMIENTO
                            lugarNacimientoCotitular = lugarNacimientoCotitular + DocumentosPersonasController.SALTO_LINEA + this.getLugarNacimiento(lugarNacimientoCotitular, cotitularTemporal);
                            
                            //ESTADO
                            estadoNacimientoCotitular = estadoNacimientoCotitular + DocumentosPersonasController.SALTO_LINEA + this.getEstadoNacimiento(estadoNacimientoCotitular, cotitularTemporal);
                            
                            //PAIS DE NACIMIENTO
                            paisNacimientoCotitular = paisNacimientoCotitular + DocumentosPersonasController.SALTO_LINEA + this.getPaisNacimiento(paisNacimientoCotitular, cotitularTemporal);
                            
                            //PROVINCICA
                            if(StringUtils.isNotBlank(cotitular.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreProvincia())){
                                cotitular.getDomicilios().get(0).getDatosFinalesCatGeo().setNombreProvincia(cotitular.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreProvincia() +  DocumentosPersonasController.SALTO_LINEA + cotitularTemporal.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreProvincia());
                            }else{
                                cotitular.getDomicilios().get(0).getDatosFinalesCatGeo().setNombreProvincia(cotitular.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreProvincia() +  DocumentosPersonasController.SALTO_LINEA);
                            }
                            
                          //CP
                            if(StringUtils.isNotBlank(cotitular.getDomicilios().get(0).getDatosFinalesCatGeo().getCodigoPostal())){
                            	cotitular.getDomicilios().get(0).getDatosFinalesCatGeo().setCodigoPostal(cotitular.getDomicilios().get(0).getDatosFinalesCatGeo().getCodigoPostal() +  DocumentosPersonasController.SALTO_LINEA + cotitularTemporal.getDomicilios().get(0).getDatosFinalesCatGeo().getCodigoPostal());
                            }else{
                            	cotitular.getDomicilios().get(0).getDatosFinalesCatGeo().setCodigoPostal(cotitular.getDomicilios().get(0).getDatosFinalesCatGeo().getCodigoPostal() +  DocumentosPersonasController.SALTO_LINEA);
                            }
                            
                          //Numero Exterior
                            if(StringUtils.isNotBlank(cotitular.getDomicilios().get(0).getNumeroExterior())){
                                cotitular.getDomicilios().get(0).setNumeroExterior(cotitular.getDomicilios().get(0).getNumeroExterior() +  DocumentosPersonasController.SALTO_LINEA + cotitularTemporal.getDomicilios().get(0).getNumeroExterior());
                            }else{
                                cotitular.getDomicilios().get(0).setNumeroExterior(cotitular.getDomicilios().get(0).getNumeroExterior() +  DocumentosPersonasController.SALTO_LINEA);
                            }
                            //Numero interior
                            if(StringUtils.isNotBlank(cotitular.getDomicilios().get(0).getInterior()) && StringUtils.isNotBlank(cotitularTemporal.getDomicilios().get(0).getInterior())){
                                cotitular.getDomicilios().get(0).setInterior(cotitular.getDomicilios().get(0).getInterior() +  DocumentosPersonasController.SALTO_LINEA + cotitularTemporal.getDomicilios().get(0).getInterior());
                            }else if(StringUtils.isNotBlank(cotitular.getDomicilios().get(0).getInterior()) && !StringUtils.isNotBlank(cotitularTemporal.getDomicilios().get(0).getInterior())){
                                cotitular.getDomicilios().get(0).setInterior(cotitular.getDomicilios().get(0).getInterior() +  DocumentosPersonasController.SALTO_LINEA);
                            }else if(!StringUtils.isNotBlank(cotitular.getDomicilios().get(0).getInterior()) && StringUtils.isNotBlank(cotitularTemporal.getDomicilios().get(0).getInterior())){
                                cotitular.getDomicilios().get(0).setInterior(StringUtils.EMPTY +  DocumentosPersonasController.SALTO_LINEA + cotitularTemporal.getDomicilios().get(0).getInterior() );
                            }
                            
                          //Colonia
                            if(StringUtils.isNotBlank(cotitular.getDomicilios().get(0).getColonia())){
                                cotitular.getDomicilios().get(0).setColonia(cotitular.getDomicilios().get(0).getColonia() +  DocumentosPersonasController.SALTO_LINEA + cotitularTemporal.getDomicilios().get(0).getColonia());
                            }else{
                                cotitular.getDomicilios().get(0).setColonia(cotitular.getDomicilios().get(0).getColonia() +  DocumentosPersonasController.SALTO_LINEA);
                            }
                            //nombreMunicipio
                            if(StringUtils.isNotBlank(cotitular.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreMunicipio())){
                                cotitular.getDomicilios().get(0).getDatosFinalesCatGeo().setNombreMunicipio(cotitular.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreMunicipio() +  DocumentosPersonasController.SALTO_LINEA + cotitularTemporal.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreMunicipio());
                            }else{
                                cotitular.getDomicilios().get(0).getDatosFinalesCatGeo().setNombreMunicipio(cotitular.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreMunicipio() +  DocumentosPersonasController.SALTO_LINEA);
                            }
                            
                          //Nombre calle
                            if(StringUtils.isNotBlank(cotitular.getDomicilios().get(0).getNombreCalle())){
                                cotitular.getDomicilios().get(0).setNombreCalle(cotitular.getDomicilios().get(0).getNombreCalle() +  DocumentosPersonasController.SALTO_LINEA + cotitularTemporal.getDomicilios().get(0).getNombreCalle());
                            }else{
                                cotitular.getDomicilios().get(0).setNombreCalle(cotitular.getDomicilios().get(0).getNombreCalle() +  DocumentosPersonasController.SALTO_LINEA);
                            }
                            
                          //Pais
                            if(StringUtils.isNotBlank(cotitular.getDomicilios().get(0).getDatosFinalesCatGeo().getNombrePais())){
                                cotitular.getDomicilios().get(0).getDatosFinalesCatGeo().setNombrePais(cotitular.getDomicilios().get(0).getDatosFinalesCatGeo().getNombrePais() +  DocumentosPersonasController.SALTO_LINEA + cotitularTemporal.getDomicilios().get(0).getDatosFinalesCatGeo().getNombrePais());
                            }else{
                                cotitular.getDomicilios().get(0).getDatosFinalesCatGeo().setNombrePais(cotitular.getDomicilios().get(0).getDatosFinalesCatGeo().getNombrePais() +  DocumentosPersonasController.SALTO_LINEA);
                            }
                            
                            //TELEFONO primerCotitular.domicilios[0].telefono
                            if(StringUtils.isNotBlank(cotitular.getDomicilios().get(0).getTelefono())){
                                cotitular.getDomicilios().get(0).setTelefono(cotitular.getDomicilios().get(0).getTelefono() +  DocumentosPersonasController.SALTO_LINEA + cotitularTemporal.getDomicilios().get(0).getTelefono());
                            }else{
                                cotitular.getDomicilios().get(0).setTelefono(cotitular.getDomicilios().get(0).getTelefono() +  DocumentosPersonasController.SALTO_LINEA);
                            }

                    	}
                    	                        
                    }
                    
                }
                if (!tieneApoderado
                        && DocumentosPersonasController.CTE_RELACION_APODERADO
                                .equals(personaRelacionadaBean
                                        .getRelacionSeleccionada()
                                        .getClaveFila())) {
                    //tieneApoderado = true;
                	if(apoderado == null){
                		try{
                			apoderado = (ClientePersonaFisicaBean) this.consultaPersonasFisica
                                    .ejecutarTRN(personaRelacionadaBean.getIdInterna());
                    		sexoApoderado = this.getSexoCotitular(sexoApoderado, apoderado);
                    		estadoCivilApoderado = this.getEstadoCivilCotitular(estadoCivilApoderado, apoderado);
                    		ocupacionApoderado = this.getOcupacionCotitular(ocupacionApoderado, apoderado);
                    		cnaeApoderado = this.getCnaeCotitular(cnaeApoderado, apoderado);
                        	fechasNacimientoApoderado = sdf.format(apoderado.getFechaNacimiento());
                        	lugarNacimientoApoderado = this.getLugarNacimiento(lugarNacimientoApoderado, apoderado);
                        	estadoNacimientoApoderado = this.getEstadoNacimiento(estadoNacimientoApoderado, apoderado);
                        	paisNacimientoApoderado = this.getPaisNacimiento(paisNacimientoApoderado, apoderado);
                		}catch(ControlableException | NoControlableException ce){
                			
                		}
                		
                	}else{
                		ClientePersonaFisicaBean apoderadoTemporal = null;
                		try{
                			apoderadoTemporal =(ClientePersonaFisicaBean) this.consultaPersonasFisica.ejecutarTRN(personaRelacionadaBean.getIdInterna());
                		}catch(ControlableException | NoControlableException ce){
                			
                		}
                		if(apoderadoTemporal != null){
                			//NOMBRE APODERADO
                        	if(StringUtils.isNotBlank(apoderadoTemporal.getNombre())){
                                apoderado.setNombre(apoderado.getNombre() + DocumentosPersonasController.SALTO_LINEA + apoderadoTemporal.getNombre() );
                            }else{
                                apoderado.setNombre(apoderado.getNombre() + DocumentosPersonasController.SALTO_LINEA);
                            }
                        	
                        	//APELLIDO PATERNO
                        	if(StringUtils.isNotBlank(apoderadoTemporal.getApePaterno())){
                                apoderado.setApePaterno(apoderado.getApePaterno() + DocumentosPersonasController.SALTO_LINEA + apoderadoTemporal.getApePaterno() );
                            }else{
                                apoderado.setApePaterno(apoderado.getApePaterno() + DocumentosPersonasController.SALTO_LINEA);
                            }
                        	
                        	//APELLIDO MATERNO
                        	if(StringUtils.isNotBlank(apoderadoTemporal.getApeMaterno())){
                                apoderado.setApeMaterno(apoderado.getApeMaterno() + DocumentosPersonasController.SALTO_LINEA + apoderadoTemporal.getApeMaterno() );
                            }else{
                                apoderado.setApeMaterno(apoderado.getApeMaterno() + DocumentosPersonasController.SALTO_LINEA);
                            }
                        	
                        	//SEXO
                        	sexoApoderado = sexoApoderado + DocumentosPersonasController.SALTO_LINEA + this.getSexoCotitular(sexoApoderado, apoderadoTemporal);
                        	
                        	//CURP
                        	if(StringUtils.isNotBlank(apoderadoTemporal.getCurp())){
                                apoderado.setCurp(apoderado.getCurp() + DocumentosPersonasController.SALTO_LINEA + apoderadoTemporal.getCurp() );
                            }else{
                                apoderado.setCurp(apoderado.getCurp() + DocumentosPersonasController.SALTO_LINEA);
                            }
                        	
                        	//RFC
                        	if(StringUtils.isNotBlank(apoderadoTemporal.getRfc())){
                                apoderado.setRfc(apoderado.getRfc() + DocumentosPersonasController.SALTO_LINEA + apoderadoTemporal.getRfc() );
                            }else{
                                apoderado.setRfc(apoderado.getRfc() + DocumentosPersonasController.SALTO_LINEA);
                            }
                        	
                        	//ESTADO_CIVIL
                        	estadoCivilApoderado = estadoCivilApoderado + DocumentosPersonasController.SALTO_LINEA + this.getEstadoApoderado(estadoCivilApoderado, apoderadoTemporal);
                        	
                        	//OCUPACION O PROFESION
                        	ocupacionApoderado = ocupacionApoderado + DocumentosPersonasController.SALTO_LINEA + this.getOcupacionApoderado(ocupacionApoderado, apoderadoTemporal);
                        	
                        	//CNAE
                        	cnaeApoderado = cnaeApoderado + DocumentosPersonasController.SALTO_LINEA + this.getCnaeApoderado(cnaeApoderado, apoderadoTemporal);
                        	
                        	//FECHA DE NACIMIENTO
                        	fechasNacimientoApoderado = fechasNacimientoApoderado + DocumentosPersonasController.SALTO_LINEA + sdf.format(apoderadoTemporal.getFechaNacimiento());
                        	
                        	//LUGAR DE NACIMIENTO
                        	lugarNacimientoApoderado = lugarNacimientoApoderado + DocumentosPersonasController.SALTO_LINEA + this.getLugarNacimiento(lugarNacimientoApoderado, apoderadoTemporal);
                            
                            //ESTADO
                            estadoNacimientoApoderado = estadoNacimientoApoderado + DocumentosPersonasController.SALTO_LINEA + this.getEstadoNacimiento(estadoNacimientoApoderado, apoderadoTemporal);
                            
                            //PAIS DE NACIMIENTO
                            paisNacimientoApoderado = paisNacimientoApoderado + DocumentosPersonasController.SALTO_LINEA + this.getPaisNacimiento(paisNacimientoApoderado, apoderadoTemporal);
                            
                            //PROVINCICA
                            if(StringUtils.isNotBlank(apoderado.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreProvincia())){
                                apoderado.getDomicilios().get(0).getDatosFinalesCatGeo().setNombreProvincia(apoderado.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreProvincia() +  DocumentosPersonasController.SALTO_LINEA + apoderadoTemporal.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreProvincia());
                            }else{
                                apoderado.getDomicilios().get(0).getDatosFinalesCatGeo().setNombreProvincia(apoderado.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreProvincia() +  DocumentosPersonasController.SALTO_LINEA);
                            }
                            
                          //CP
                            if(StringUtils.isNotBlank(apoderado.getDomicilios().get(0).getDatosFinalesCatGeo().getCodigoPostal())){
                            	apoderado.getDomicilios().get(0).getDatosFinalesCatGeo().setCodigoPostal(apoderado.getDomicilios().get(0).getDatosFinalesCatGeo().getCodigoPostal() +  DocumentosPersonasController.SALTO_LINEA + apoderadoTemporal.getDomicilios().get(0).getDatosFinalesCatGeo().getCodigoPostal());
                            }else{
                            	apoderado.getDomicilios().get(0).getDatosFinalesCatGeo().setCodigoPostal(apoderado.getDomicilios().get(0).getDatosFinalesCatGeo().getCodigoPostal() +  DocumentosPersonasController.SALTO_LINEA);
                            }
                            
                          //Numero Exterior
                            if(StringUtils.isNotBlank(apoderado.getDomicilios().get(0).getNumeroExterior())){
                                apoderado.getDomicilios().get(0).setNumeroExterior(apoderado.getDomicilios().get(0).getNumeroExterior() +  DocumentosPersonasController.SALTO_LINEA + apoderadoTemporal.getDomicilios().get(0).getNumeroExterior());
                            }else{
                                apoderado.getDomicilios().get(0).setNumeroExterior(apoderado.getDomicilios().get(0).getNumeroExterior() +  DocumentosPersonasController.SALTO_LINEA);
                            }
                            //Numero interior
                            if(StringUtils.isNotBlank(apoderado.getDomicilios().get(0).getInterior()) && StringUtils.isNotBlank(apoderadoTemporal.getDomicilios().get(0).getInterior())){
                                apoderado.getDomicilios().get(0).setInterior(apoderado.getDomicilios().get(0).getInterior() +  DocumentosPersonasController.SALTO_LINEA + apoderadoTemporal.getDomicilios().get(0).getInterior());
                            }else if(StringUtils.isNotBlank(apoderado.getDomicilios().get(0).getInterior()) && !StringUtils.isNotBlank(apoderadoTemporal.getDomicilios().get(0).getInterior())){
                                apoderado.getDomicilios().get(0).setInterior(apoderado.getDomicilios().get(0).getInterior() +  DocumentosPersonasController.SALTO_LINEA);
                            }else if(!StringUtils.isNotBlank(apoderado.getDomicilios().get(0).getInterior()) && StringUtils.isNotBlank(apoderadoTemporal.getDomicilios().get(0).getInterior())){
                                apoderado.getDomicilios().get(0).setInterior(StringUtils.EMPTY +  DocumentosPersonasController.SALTO_LINEA + apoderadoTemporal.getDomicilios().get(0).getInterior() );
                            }
                            
                          //Colonia
                            if(StringUtils.isNotBlank(apoderado.getDomicilios().get(0).getColonia())){
                                apoderado.getDomicilios().get(0).setColonia(apoderado.getDomicilios().get(0).getColonia() +  DocumentosPersonasController.SALTO_LINEA + apoderadoTemporal.getDomicilios().get(0).getColonia());
                            }else{
                                apoderado.getDomicilios().get(0).setColonia(apoderado.getDomicilios().get(0).getColonia() +  DocumentosPersonasController.SALTO_LINEA);
                            }
                            //nombreMunicipio
                            if(StringUtils.isNotBlank(apoderado.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreMunicipio())){
                                apoderado.getDomicilios().get(0).getDatosFinalesCatGeo().setNombreMunicipio(apoderado.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreMunicipio() +  DocumentosPersonasController.SALTO_LINEA + apoderadoTemporal.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreMunicipio());
                            }else{
                                apoderado.getDomicilios().get(0).getDatosFinalesCatGeo().setNombreMunicipio(apoderado.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreMunicipio() +  DocumentosPersonasController.SALTO_LINEA);
                            }
                            
                          //Nombre calle
                            if(StringUtils.isNotBlank(apoderado.getDomicilios().get(0).getNombreCalle())){
                                apoderado.getDomicilios().get(0).setNombreCalle(apoderado.getDomicilios().get(0).getNombreCalle() +  DocumentosPersonasController.SALTO_LINEA + apoderadoTemporal.getDomicilios().get(0).getNombreCalle());
                            }else{
                                apoderado.getDomicilios().get(0).setNombreCalle(apoderado.getDomicilios().get(0).getNombreCalle() +  DocumentosPersonasController.SALTO_LINEA);
                            }
                            
                          //Pais
                            if(StringUtils.isNotBlank(apoderado.getDomicilios().get(0).getDatosFinalesCatGeo().getNombrePais())){
                                apoderado.getDomicilios().get(0).getDatosFinalesCatGeo().setNombrePais(apoderado.getDomicilios().get(0).getDatosFinalesCatGeo().getNombrePais() +  DocumentosPersonasController.SALTO_LINEA + apoderadoTemporal.getDomicilios().get(0).getDatosFinalesCatGeo().getNombrePais());
                            }else{
                                apoderado.getDomicilios().get(0).getDatosFinalesCatGeo().setNombrePais(apoderado.getDomicilios().get(0).getDatosFinalesCatGeo().getNombrePais() +  DocumentosPersonasController.SALTO_LINEA);
                            }
                            
                            //TELEFONO primerapoderado.domicilios[0].telefono
                            if(StringUtils.isNotBlank(apoderado.getDomicilios().get(0).getTelefono())){
                                apoderado.getDomicilios().get(0).setTelefono(apoderado.getDomicilios().get(0).getTelefono() +  DocumentosPersonasController.SALTO_LINEA + apoderadoTemporal.getDomicilios().get(0).getTelefono());
                            }else{
                                apoderado.getDomicilios().get(0).setTelefono(apoderado.getDomicilios().get(0).getTelefono() +  DocumentosPersonasController.SALTO_LINEA);
                            }
                		}
                		
                		
                	}
                    
                }
                if (!tienePropietario
                        && (DocumentosPersonasController.CTE_RELACION_PROPIETARIO
                                .equals(personaRelacionadaBean
                                        .getRelacionSeleccionada()
                                        .getClaveFila()) || DocumentosPersonasController.CTE_RELACION_PROPIETARIO_BIS
                                .equals(personaRelacionadaBean
                                        .getRelacionSeleccionada()
                                        .getClaveFila()))) {
                    //tienePropietario = true;
                	if(propietario == null){
                		try{
                			propietario = (ClientePersonaFisicaBean) this.consultaPersonasFisica
                                    .ejecutarTRN(personaRelacionadaBean.getIdInterna());
                    		estadoCivilPropietario = this.getEstadoCivilCotitular(estadoCivilPropietario, propietario);
                    		ocupacionPropietario = this.getOcupacionCotitular(ocupacionPropietario, propietario);
                    		cnaePropietario = this.getCnaeCotitular(cnaePropietario, propietario);
                        	fechasNacimientoPropietario = sdf.format(propietario.getFechaNacimiento());
                        	lugarNacimientoPropietario = this.getLugarNacimiento(lugarNacimientoPropietario, propietario);
                        	estadoNacimientoPropietario = this.getEstadoNacimiento(estadoNacimientoPropietario, propietario);
                        	paisNacimientoPropietario = this.getPaisNacimiento(paisNacimientoPropietario, propietario);
                        	sexoPropietario = this.getSexoCotitular(sexoPropietario, propietario);
                		}catch(ControlableException | NoControlableException ce){
                			
                		}
                		
                	}else{
                		ClientePersonaFisicaBean propietarioTemporal = null;
                		try{
                			propietarioTemporal = (ClientePersonaFisicaBean) this.consultaPersonasFisica.ejecutarTRN(personaRelacionadaBean.getIdInterna());
                		}catch(ControlableException | NoControlableException ce){
                			
                		}
                		if(propietarioTemporal != null){
                			//NOMBRE APODERADO
                        	if(StringUtils.isNotBlank(propietarioTemporal.getNombre())){
                                propietario.setNombre(propietario.getNombre() + DocumentosPersonasController.SALTO_LINEA + propietarioTemporal.getNombre() );
                            }else{
                                propietario.setNombre(propietario.getNombre() + DocumentosPersonasController.SALTO_LINEA);
                            }
                        	
                        	//APELLIDO PATERNO
                        	if(StringUtils.isNotBlank(propietarioTemporal.getApePaterno())){
                                propietario.setApePaterno(propietario.getApePaterno() + DocumentosPersonasController.SALTO_LINEA + propietarioTemporal.getApePaterno() );
                            }else{
                                propietario.setApePaterno(propietario.getApePaterno() + DocumentosPersonasController.SALTO_LINEA);
                            }
                        	
                        	//APELLIDO MATERNO
                        	if(StringUtils.isNotBlank(propietarioTemporal.getApeMaterno())){
                                propietario.setApeMaterno(propietario.getApeMaterno() + DocumentosPersonasController.SALTO_LINEA + propietarioTemporal.getApeMaterno() );
                            }else{
                                propietario.setApeMaterno(propietario.getApeMaterno() + DocumentosPersonasController.SALTO_LINEA);
                            }
                        	
                        	//SEXO
                        	sexoPropietario = sexoPropietario + DocumentosPersonasController.SALTO_LINEA + this.getSexoCotitular(sexoPropietario, propietarioTemporal);
                        	
                        	//CURP
                        	if(StringUtils.isNotBlank(propietarioTemporal.getCurp())){
                                propietario.setCurp(propietario.getCurp() + DocumentosPersonasController.SALTO_LINEA + propietarioTemporal.getCurp() );
                            }else{
                                propietario.setCurp(propietario.getCurp() + DocumentosPersonasController.SALTO_LINEA);
                            }
                        	
                        	//RFC
                        	if(StringUtils.isNotBlank(propietarioTemporal.getRfc())){
                                propietario.setRfc(propietario.getRfc() + DocumentosPersonasController.SALTO_LINEA + propietarioTemporal.getRfc() );
                            }else{
                                propietario.setRfc(propietario.getRfc() + DocumentosPersonasController.SALTO_LINEA);
                            }
                        	
                        	//ESTADO_CIVIL
                        	estadoCivilPropietario = estadoCivilPropietario + DocumentosPersonasController.SALTO_LINEA + this.getEstadoApoderado(estadoCivilPropietario, propietarioTemporal);
                        	
                        	//OCUPACION O PROFESION
                        	ocupacionPropietario = ocupacionPropietario + DocumentosPersonasController.SALTO_LINEA + this.getOcupacionApoderado(ocupacionPropietario, propietarioTemporal);
                        	
                        	//CNAE
                        	cnaePropietario = cnaePropietario + DocumentosPersonasController.SALTO_LINEA + this.getCnaeApoderado(cnaePropietario, propietarioTemporal);
                        	
                        	//FECHA DE NACIMIENTO
                        	fechasNacimientoPropietario = fechasNacimientoPropietario + DocumentosPersonasController.SALTO_LINEA + sdf.format(propietarioTemporal.getFechaNacimiento());
                        	
                        	//LUGAR DE NACIMIENTO
                        	lugarNacimientoPropietario = lugarNacimientoPropietario + DocumentosPersonasController.SALTO_LINEA + this.getLugarNacimiento(lugarNacimientoPropietario, propietarioTemporal);
                            
                            //ESTADO
                            estadoNacimientoPropietario = estadoNacimientoPropietario + DocumentosPersonasController.SALTO_LINEA + this.getEstadoNacimiento(estadoNacimientoPropietario, propietarioTemporal);
                            
                            //PAIS DE NACIMIENTO
                            paisNacimientoPropietario = paisNacimientoPropietario + DocumentosPersonasController.SALTO_LINEA + this.getPaisNacimiento(paisNacimientoPropietario, propietarioTemporal);
                            
                            //PROVINCICA
                            if(StringUtils.isNotBlank(propietario.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreProvincia())){
                                propietario.getDomicilios().get(0).getDatosFinalesCatGeo().setNombreProvincia(propietario.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreProvincia() +  DocumentosPersonasController.SALTO_LINEA + propietarioTemporal.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreProvincia());
                            }else{
                                propietario.getDomicilios().get(0).getDatosFinalesCatGeo().setNombreProvincia(propietario.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreProvincia() +  DocumentosPersonasController.SALTO_LINEA);
                            }
                            
                          //CP
                            if(StringUtils.isNotBlank(propietario.getDomicilios().get(0).getDatosFinalesCatGeo().getCodigoPostal())){
                            	propietario.getDomicilios().get(0).getDatosFinalesCatGeo().setCodigoPostal(propietario.getDomicilios().get(0).getDatosFinalesCatGeo().getCodigoPostal() +  DocumentosPersonasController.SALTO_LINEA + propietarioTemporal.getDomicilios().get(0).getDatosFinalesCatGeo().getCodigoPostal());
                            }else{
                            	propietario.getDomicilios().get(0).getDatosFinalesCatGeo().setCodigoPostal(propietario.getDomicilios().get(0).getDatosFinalesCatGeo().getCodigoPostal() +  DocumentosPersonasController.SALTO_LINEA);
                            }
                            
                          //Numero Exterior
                            if(StringUtils.isNotBlank(propietario.getDomicilios().get(0).getNumeroExterior())){
                                propietario.getDomicilios().get(0).setNumeroExterior(propietario.getDomicilios().get(0).getNumeroExterior() +  DocumentosPersonasController.SALTO_LINEA + propietarioTemporal.getDomicilios().get(0).getNumeroExterior());
                            }else{
                                propietario.getDomicilios().get(0).setNumeroExterior(propietario.getDomicilios().get(0).getNumeroExterior() +  DocumentosPersonasController.SALTO_LINEA);
                            }
                            //Numero interior
                            if(StringUtils.isNotBlank(propietario.getDomicilios().get(0).getInterior()) && StringUtils.isNotBlank(propietarioTemporal.getDomicilios().get(0).getInterior())){
                                propietario.getDomicilios().get(0).setInterior(propietario.getDomicilios().get(0).getInterior() +  DocumentosPersonasController.SALTO_LINEA + propietarioTemporal.getDomicilios().get(0).getInterior());
                            }else if(StringUtils.isNotBlank(propietario.getDomicilios().get(0).getInterior()) && !StringUtils.isNotBlank(propietarioTemporal.getDomicilios().get(0).getInterior())){
                                propietario.getDomicilios().get(0).setInterior(propietario.getDomicilios().get(0).getInterior() +  DocumentosPersonasController.SALTO_LINEA);
                            }else if(!StringUtils.isNotBlank(propietario.getDomicilios().get(0).getInterior()) && StringUtils.isNotBlank(propietarioTemporal.getDomicilios().get(0).getInterior())){
                                propietario.getDomicilios().get(0).setInterior(StringUtils.EMPTY +  DocumentosPersonasController.SALTO_LINEA + propietarioTemporal.getDomicilios().get(0).getInterior() );
                            }
                            
                          //Colonia
                            if(StringUtils.isNotBlank(propietario.getDomicilios().get(0).getColonia())){
                                propietario.getDomicilios().get(0).setColonia(propietario.getDomicilios().get(0).getColonia() +  DocumentosPersonasController.SALTO_LINEA + propietarioTemporal.getDomicilios().get(0).getColonia());
                            }else{
                                propietario.getDomicilios().get(0).setColonia(propietario.getDomicilios().get(0).getColonia() +  DocumentosPersonasController.SALTO_LINEA);
                            }
                            //nombreMunicipio
                            if(StringUtils.isNotBlank(propietario.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreMunicipio())){
                                propietario.getDomicilios().get(0).getDatosFinalesCatGeo().setNombreMunicipio(propietario.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreMunicipio() +  DocumentosPersonasController.SALTO_LINEA + propietarioTemporal.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreMunicipio());
                            }else{
                                propietario.getDomicilios().get(0).getDatosFinalesCatGeo().setNombreMunicipio(propietario.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreMunicipio() +  DocumentosPersonasController.SALTO_LINEA);
                            }
                            
                          //Nombre calle
                            if(StringUtils.isNotBlank(propietario.getDomicilios().get(0).getNombreCalle())){
                                propietario.getDomicilios().get(0).setNombreCalle(propietario.getDomicilios().get(0).getNombreCalle() +  DocumentosPersonasController.SALTO_LINEA + propietarioTemporal.getDomicilios().get(0).getNombreCalle());
                            }else{
                                propietario.getDomicilios().get(0).setNombreCalle(propietario.getDomicilios().get(0).getNombreCalle() +  DocumentosPersonasController.SALTO_LINEA);
                            }
                            
                          //Pais
                            if(StringUtils.isNotBlank(propietario.getDomicilios().get(0).getDatosFinalesCatGeo().getNombrePais())){
                                propietario.getDomicilios().get(0).getDatosFinalesCatGeo().setNombrePais(propietario.getDomicilios().get(0).getDatosFinalesCatGeo().getNombrePais() +  DocumentosPersonasController.SALTO_LINEA + propietarioTemporal.getDomicilios().get(0).getDatosFinalesCatGeo().getNombrePais());
                            }else{
                                propietario.getDomicilios().get(0).getDatosFinalesCatGeo().setNombrePais(propietario.getDomicilios().get(0).getDatosFinalesCatGeo().getNombrePais() +  DocumentosPersonasController.SALTO_LINEA);
                            }
                            
                            
                            
                            
                            //TELEFONO primerapoderado.domicilios[0].telefono
                            if(StringUtils.isNotBlank(propietario.getDomicilios().get(0).getTelefono())){
                                propietario.getDomicilios().get(0).setTelefono(propietario.getDomicilios().get(0).getTelefono() +  DocumentosPersonasController.SALTO_LINEA + propietarioTemporal.getDomicilios().get(0).getTelefono());
                            }else{
                                propietario.getDomicilios().get(0).setTelefono(propietario.getDomicilios().get(0).getTelefono() +  DocumentosPersonasController.SALTO_LINEA);
                            }
                		}
                		
                	}
                    
                }
            }

            // Obtenemos datos sobre nacimiento del cliente
            String lugarNacimientoTitular = StringUtils.EMPTY;
            String estadoNacimientoTitular = StringUtils.EMPTY;
            String paisNacimientoTitular = StringUtils.EMPTY;

                      
            //En caso de que sea codificada
            if (this.clientePersonaFisica.getMunicipioCatGeo() != null
                    && StringUtils.isNotBlank(this.clientePersonaFisica
                            .getMunicipioCatGeo().getCodigoPostal())) {
                lugarNacimientoTitular = StringUtils
                        .defaultString(this.clientePersonaFisica
                                .getMunicipioCatGeo().getNombreMunicipio());
                estadoNacimientoTitular = StringUtils
                        .defaultString(this.clientePersonaFisica
                                .getMunicipioCatGeo()
                                .getNombreComunidadAutonoma());
                paisNacimientoTitular = StringUtils
                        .defaultString(this.clientePersonaFisica
                                .getMunicipioCatGeo().getNombrePais());
            }
            // En caso de no codificada
            else {
                lugarNacimientoTitular = StringUtils
                        .defaultString(this.clientePersonaFisica.getMunicipioCatGeo()
                                .getNombreMunicipio());
                estadoNacimientoTitular = StringUtils
                        .defaultString(this.clientePersonaFisica.getEstado()
                                .getNombreProvincia());
                paisNacimientoTitular = catalogoPaises.getCatalogoDescripcion(this.clientePersonaFisica.getPaisNacionalidad());
            }
            
           

            //Rellenamos la plantilla que mandaremos al jasper
            PlantillaBean plantilla = new PlantillaBean();

            plantilla.setClienteBean(this.clientePersonaFisica);
            plantilla.setPrimerBeneficiario(beneficiario);
            plantilla.setPrimerCotitular(cotitular);
            plantilla.setPrimerApoderado(apoderado);
            plantilla.setPrimerPropietario(propietario);
            
            //rellenamos la lista de beans
            final List<PlantillaBean> listaBeans = new ArrayList<PlantillaBean>();
            listaBeans.add(plantilla);
            
            //Creamos los parametros que se mandan al jasper
            final Map<String, Object> params = new HashMap<String, Object>();
            
            String estadoCivil;
            String ocupacion;
            String cnae;
                          
                       
            String nombreOficina;
           
            try {
                nombreOficina = catalogoCentrosLoaderTask
                        .getCatalogoBean(contextoUtils.getEntidad(),
                                contextoUtils.getSucursal()).getDescripcionL();
            } catch (Exception e) {
                nombreOficina = StringUtils.EMPTY;
            }
            try {
                estadoCivil = catalogoUtils.getCatalogoBean(
                        CatalogoEnum.TP_EST_CIVIL_INDV,
                        this.clientePersonaFisica.getEstadoCivil())
                        .getDescripcionL();
            } catch (Exception e) {
                estadoCivil = StringUtils.EMPTY;
            }
            try {
                ocupacion = catalogoUtils.getCatalogoBean(
                        CatalogoEnum.TP_CNO_INDV,
                        this.clientePersonaFisica.getCno()).getDescripcionL();
            } catch (Exception e) {
                ocupacion = StringUtils.EMPTY;
            }
            try {
                cnae = catalogoUtils.getCatalogoBean(
                        CatalogoEnum.TP_CNAE_PERS,
                        this.clientePersonaFisica.getCnae()).getDescripcionL();
                if(cnae.split("-").length == 2){
                	cnae = cnae.split("-")[1];
                }
            } catch (Exception e) {
                cnae = StringUtils.EMPTY;
            }
            
            // Parametros para el titular
            params.put("NO_CONTRATO", noContrato);
            params.put("ESTADO_CIVIL", estadoCivil);
            params.put("OCUPACION", ocupacion);
            params.put("LUGAR_NACIMIENTO_TITULAR", lugarNacimientoTitular);
            params.put("ESTADO_NACIMIENTO_TITULAR", estadoNacimientoTitular);
            params.put("PAIS_NACIMIENTO_TITULAR", paisNacimientoTitular);
            params.put("NACIONALIDAD", paisNacimientoTitular);
            params.put("CNAE", cnae);

            //Parametros para el beneficiarios
            params.put("FECHAS_NACIMIENTOS_BENEFICARIOS", fechasNacimientoBeneficiarios);
            
            // Parametros para el cotitular
            params.put("ESTADO_CIVIL_COTITULAR", estadoCivilCotitular);
            params.put("OCUPACION_COTITULAR", ocupacionCotitular);
            params.put("LUGAR_NACIMIENTO_COTITULAR", lugarNacimientoCotitular);
            params.put("ESTADO_NACIMIENTO_COTITULAR", estadoNacimientoCotitular);
            params.put("PAIS_NACIMIENTO_COTITULAR", paisNacimientoCotitular);
            params.put("NACIONALIDAD_COTITULAR", paisNacimientoCotitular);
            params.put("CNAE_COTITULAR", cnaeCotitular);
            params.put("SEXO_COTITULAR", sexoCotitular);
            params.put("FECHAS_NACIMIENTOS_COTITULAR", fechasNacimientoCotitular);
           
            
            // Parametros para el apoderado
            params.put("ESTADO_CIVIL_APODERADO", estadoCivilApoderado);
            params.put("OCUPACION_APODERADO", ocupacionApoderado);
            params.put("LUGAR_NACIMIENTO_APODERADO", lugarNacimientoApoderado);
            params.put("ESTADO_NACIMIENTO_APODERADO", estadoNacimientoApoderado);
            params.put("PAIS_NACIMIENTO_APODERADO", paisNacimientoApoderado);
            params.put("NACIONALIDAD_APODERADO", paisNacimientoApoderado);
            params.put("CNAE_APODERADO", cnaeApoderado);
            params.put("SEXO_APODERADO", sexoApoderado);
            params.put("FECHAS_NACIMIENTOS_APODERADO", fechasNacimientoApoderado);
            
            // Parametros para el propietario
            params.put("ESTADO_CIVIL_PROPIETARIO", estadoCivilPropietario);
            params.put("OCUPACION_PROPIETARIO", ocupacionPropietario);
            params.put("LUGAR_NACIMIENTO_PROPIETARIO",
                    lugarNacimientoPropietario);
            params.put("ESTADO_NACIMIENTO_PROPIETARIO",
                    estadoNacimientoPropietario);
            params.put("PAIS_NACIMIENTO_PROPIETARIO", paisNacimientoPropietario);
            params.put("NACIONALIDAD_PROPIETARIO", paisNacimientoPropietario);
            params.put("CNAE_PROPIETARIO", cnaePropietario);
            params.put("FECHAS_NACIMIENTOS_PROPIETARIO", fechasNacimientoPropietario);
            params.put("SEXO_PROPIETARIO", sexoPropietario);
            
            params.put("NOMBRE_OFICINA", nombreOficina);
            params.put("PLAZA_BANCARIA", this.contextoUtils.getPlazaBancaria());
            params.put("NOMBRE_EMPLEADO", this.contextoUtils.getNombre());
            params.put("NUMERO_EMPLEADO", this.contextoUtils.getId());
            
            
            if(this.cliente.getEsClienteRiesgo() && this.clientePersonaFisica.getDatosRiesgo()!=null &&
                    this.clientePersonaFisica.getDatosRiesgo().getReferenciasPersonales()!=null && this.clientePersonaFisica.getDatosRiesgo().getReferenciasPersonales().size()>0){
            	String nombreReferencia = StringUtils.EMPTY;
            	String apellidoPaternoReferencia = StringUtils.EMPTY;
            	String apellidoMaternoReferencia = StringUtils.EMPTY;
            	String domicilioReferencia = StringUtils.EMPTY;
            	String telefonoReferencia = StringUtils.EMPTY;
            	String relacionReferencia = StringUtils.EMPTY;
            	int tamano= this.clientePersonaFisica.getDatosRiesgo().getReferenciasPersonales().size();
            	for(int i = 0; i <= tamano-1; i++){
            		//NOMBRE REFERENCIA
            		if(nombreReferencia == StringUtils.EMPTY){
            			nombreReferencia = this.clientePersonaFisica.getDatosRiesgo().getReferenciasPersonales().get(i).getNombre();
            		}else{
            			nombreReferencia = nombreReferencia + DocumentosPersonasController.SALTO_LINEA + this.clientePersonaFisica.getDatosRiesgo().getReferenciasPersonales().get(i).getNombre();
            		}
            		
            		//APELLIDO PATERNO REFERENCIA
            		if(apellidoPaternoReferencia == StringUtils.EMPTY){
            			apellidoPaternoReferencia = this.clientePersonaFisica.getDatosRiesgo().getReferenciasPersonales().get(i).getApellidoPaterno();
            		}else{
            			apellidoPaternoReferencia = apellidoPaternoReferencia + DocumentosPersonasController.SALTO_LINEA + this.clientePersonaFisica.getDatosRiesgo().getReferenciasPersonales().get(i).getApellidoPaterno();
            		}
            		
            		//APELLIDO MATERNO REFERENCIA
            		if(apellidoMaternoReferencia == StringUtils.EMPTY){
            			apellidoMaternoReferencia = this.clientePersonaFisica.getDatosRiesgo().getReferenciasPersonales().get(i).getApellidoMaterno();
            		}else{
            			apellidoMaternoReferencia = apellidoMaternoReferencia + DocumentosPersonasController.SALTO_LINEA + this.clientePersonaFisica.getDatosRiesgo().getReferenciasPersonales().get(i).getApellidoMaterno();
            		}
            		
            		//DOMICILIO REFERENCIA
            		if(domicilioReferencia == StringUtils.EMPTY){
            			domicilioReferencia = this.clientePersonaFisica.getDatosRiesgo().getReferenciasPersonales().get(i).getDomicilio();
            		}else{
            			domicilioReferencia = domicilioReferencia + DocumentosPersonasController.SALTO_LINEA + this.clientePersonaFisica.getDatosRiesgo().getReferenciasPersonales().get(i).getDomicilio();
            		}
            		
            		//TELEFONO REFERENCIA
            		if(telefonoReferencia == StringUtils.EMPTY){
            			telefonoReferencia = this.clientePersonaFisica.getDatosRiesgo().getReferenciasPersonales().get(i).getTelefono();
            		}else{
            			telefonoReferencia = telefonoReferencia + DocumentosPersonasController.SALTO_LINEA + this.clientePersonaFisica.getDatosRiesgo().getReferenciasPersonales().get(i).getTelefono();
            		}
            		
            		//RELACION REFERENCIA
            		if(relacionReferencia == StringUtils.EMPTY){
            			relacionReferencia = this.clientePersonaFisica.getDatosRiesgo().getReferenciasPersonales().get(i).getRelacionParentesco();
            		}else{
            			relacionReferencia = relacionReferencia + DocumentosPersonasController.SALTO_LINEA + this.clientePersonaFisica.getDatosRiesgo().getReferenciasPersonales().get(i).getRelacionParentesco();
            		}
            	}
                params.put("NOMBRE_REFERENCIA", nombreReferencia);
                params.put("APE_PATERNO_REFERENCIA", apellidoPaternoReferencia);
                params.put("APE_MATERNO_REFERENCIA", apellidoMaternoReferencia);
                params.put("DOMICILIO_REFERENCIA", domicilioReferencia);
                params.put("TELEFONO_REFERENCIA", telefonoReferencia);
                params.put("RELACION_REFERENCIA", relacionReferencia);
            }
            
            //Rellenamos las imagnes de logo
            final Map<String, String> images = new HashMap<String, String>();
            images.put("Logo_bsfi_bn.png", "LOGO");

            // Actualmente solo funciona la cedula de persona fisica con bajo riesgo
            if (this.clientePersonaFisica.getEsClienteRiesgo()) {
                pdfUtils.generaPdf(ConstantesFuncionales.CTE_CEDULA_PF_AR, params, images, null,
                        "P0700200", listaBeans);
            } else {
                pdfUtils.generaPdf(ConstantesFuncionales.CTE_CEDULA_PF_BR, params, images, null,
                        "P0700100", listaBeans);
            }
        } else if (this.cliente instanceof ClientePersonaMoralBean) {
            this.clientePersonaMoral = (ClientePersonaMoralBean) this.cliente;
            
            // Consultamos los personas relacionadas
            List<PersonaRelacionadaBean> relaciones = this.consultaPersonasRelacionadas
                    .ejecutarTRN(this.idInternaPersona);
            
            
            Boolean tieneApoderado = false;
            Boolean tienePropietario = false;
           
            ClientePersonaFisicaBean apoderado = null;
            ClientePersonaFisicaBean propietario = null;

            // Si las relaciones son del tipo deseado consultamos los datos del
            // cliente
         // Obtenemos datos sobre nacimiento del apoderado
            String lugarNacimientoApoderado = StringUtils.EMPTY;
            String estadoNacimientoApoderado = StringUtils.EMPTY;
            String paisNacimientoApoderado = StringUtils.EMPTY;
            Integer edad = null;
            String edadApoderado = StringUtils.EMPTY;
            String estadoCivilApoderado = StringUtils.EMPTY;
            String ocupacionApoderado = StringUtils.EMPTY;
            String cnaeApoderado = StringUtils.EMPTY;
            String sexoApoderado = StringUtils.EMPTY;
            String fechasNacimientoApoderado =  StringUtils.EMPTY;
            String lugarNacimientoPropietario = StringUtils.EMPTY;
            String estadoNacimientoPropietario = StringUtils.EMPTY;
            String paisNacimientoPropietario = StringUtils.EMPTY;
            String edadPropietario = StringUtils.EMPTY;
            String estadoCivilPropietario = StringUtils.EMPTY;
            String ocupacionPropietario = StringUtils.EMPTY;
            String cnaePropietario = StringUtils.EMPTY;
            String fechasNacimientoPropietario = StringUtils.EMPTY;
            String sexoPropietario = StringUtils.EMPTY;
            String noContrato = StringUtils.EMPTY;
            SimpleDateFormat sdf = new SimpleDateFormat(ConstantesFuncionales.GENERAL_DATE_FORMATTER);
            List<CuentaClienteBean> cuenta = consultaCuentasCliente.ejecutarTRN(this.clientePersonaMoral.getIdInterna(), true);
            if(!cuenta.isEmpty() && cuenta.get(0).getCuenta() != null){
            	noContrato = cuenta.get(0).getCuenta().getNumeroCuenta().toString();
            }
            for (PersonaRelacionadaBean personaRelacionadaBean : relaciones) {
                
                if (!tieneApoderado
                        && DocumentosPersonasController.CTE_RELACION_APODERADO
                                .equals(personaRelacionadaBean
                                        .getRelacionSeleccionada()
                                        .getClaveFila())) {
                    //tieneApoderado = true;
                	if(apoderado == null){
                		try{
                			apoderado = (ClientePersonaFisicaBean) this.consultaPersonasFisica
                                    .ejecutarTRN(personaRelacionadaBean.getIdInterna());
                    		sexoApoderado = this.getSexoCotitular(sexoApoderado, apoderado);
                    		estadoCivilApoderado = this.getEstadoCivilCotitular(estadoCivilApoderado, apoderado);
                    		ocupacionApoderado = this.getOcupacionCotitular(ocupacionApoderado, apoderado);
                    		cnaeApoderado = this.getCnaeCotitular(cnaeApoderado, apoderado);
                        	fechasNacimientoApoderado = sdf.format(apoderado.getFechaNacimiento());
                        	lugarNacimientoApoderado = this.getLugarNacimiento(lugarNacimientoApoderado, apoderado);
                        	estadoNacimientoApoderado = this.getEstadoNacimiento(estadoNacimientoApoderado, apoderado);
                        	paisNacimientoApoderado = this.getPaisNacimiento(paisNacimientoApoderado, apoderado);
                        	edadApoderado = this.getEdad(edad, apoderado);
                		}catch(ControlableException | NoControlableException ce){
                			
                		}
                		
                    	
                	}else{
                		ClientePersonaFisicaBean apoderadoTemporal = null;
                		try{
                			apoderadoTemporal =(ClientePersonaFisicaBean) this.consultaPersonasFisica.ejecutarTRN(personaRelacionadaBean.getIdInterna());
                		}catch(ControlableException | NoControlableException ce){
                			
                		}
                		if(apoderadoTemporal != null){
                			//NOMBRE APODERADO
                        	if(StringUtils.isNotBlank(apoderadoTemporal.getNombre())){
                                apoderado.setNombre(apoderado.getNombre() + DocumentosPersonasController.SALTO_LINEA + apoderadoTemporal.getNombre() );
                            }else{
                                apoderado.setNombre(apoderado.getNombre() + DocumentosPersonasController.SALTO_LINEA);
                            }
                        	
                        	//APELLIDO PATERNO
                        	if(StringUtils.isNotBlank(apoderadoTemporal.getApePaterno())){
                                apoderado.setApePaterno(apoderado.getApePaterno() + DocumentosPersonasController.SALTO_LINEA + apoderadoTemporal.getApePaterno() );
                            }else{
                                apoderado.setApePaterno(apoderado.getApePaterno() + DocumentosPersonasController.SALTO_LINEA);
                            }
                        	
                        	//APELLIDO MATERNO
                        	if(StringUtils.isNotBlank(apoderadoTemporal.getApeMaterno())){
                                apoderado.setApeMaterno(apoderado.getApeMaterno() + DocumentosPersonasController.SALTO_LINEA + apoderadoTemporal.getApeMaterno() );
                            }else{
                                apoderado.setApeMaterno(apoderado.getApeMaterno() + DocumentosPersonasController.SALTO_LINEA);
                            }
                        	
                        	//SEXO
                        	sexoApoderado = sexoApoderado + DocumentosPersonasController.SALTO_LINEA + this.getSexoCotitular(sexoApoderado, apoderadoTemporal);
                        	
                        	//EDAD
                        	edadApoderado = edadApoderado + DocumentosPersonasController.SALTO_LINEA + this.getEdad(edad, apoderadoTemporal);
                        	
                        	//CURP
                        	if(StringUtils.isNotBlank(apoderadoTemporal.getCurp())){
                                apoderado.setCurp(apoderado.getCurp() + DocumentosPersonasController.SALTO_LINEA + apoderadoTemporal.getCurp() );
                            }else{
                                apoderado.setCurp(apoderado.getCurp() + DocumentosPersonasController.SALTO_LINEA);
                            }
                        	
                        	//RFC
                        	if(StringUtils.isNotBlank(apoderadoTemporal.getRfc())){
                                apoderado.setRfc(apoderado.getRfc() + DocumentosPersonasController.SALTO_LINEA + apoderadoTemporal.getRfc() );
                            }else{
                                apoderado.setRfc(apoderado.getRfc() + DocumentosPersonasController.SALTO_LINEA);
                            }
                        	
                        	//ESTADO_CIVIL
                        	estadoCivilApoderado = estadoCivilApoderado + DocumentosPersonasController.SALTO_LINEA + this.getEstadoApoderado(estadoCivilApoderado, apoderadoTemporal);
                        	
                        	//OCUPACION O PROFESION
                        	ocupacionApoderado = ocupacionApoderado + DocumentosPersonasController.SALTO_LINEA + this.getOcupacionApoderado(ocupacionApoderado, apoderadoTemporal);
                        	
                        	//CNAE
                        	cnaeApoderado = cnaeApoderado + DocumentosPersonasController.SALTO_LINEA + this.getCnaeApoderado(cnaeApoderado, apoderadoTemporal);
                        	
                        	//FECHA DE NACIMIENTO
                        	fechasNacimientoApoderado = fechasNacimientoApoderado + DocumentosPersonasController.SALTO_LINEA + sdf.format(apoderadoTemporal.getFechaNacimiento());
                        	
                        	//LUGAR DE NACIMIENTO
                        	lugarNacimientoApoderado = lugarNacimientoApoderado + DocumentosPersonasController.SALTO_LINEA + this.getLugarNacimiento(lugarNacimientoApoderado, apoderadoTemporal);
                            
                            //ESTADO
                            estadoNacimientoApoderado = estadoNacimientoApoderado + DocumentosPersonasController.SALTO_LINEA + this.getEstadoNacimiento(estadoNacimientoApoderado, apoderadoTemporal);
                            
                            //PAIS DE NACIMIENTO
                            paisNacimientoApoderado = paisNacimientoApoderado + DocumentosPersonasController.SALTO_LINEA + this.getPaisNacimiento(paisNacimientoApoderado, apoderadoTemporal);
                            
                            //PROVINCICA
                            if(StringUtils.isNotBlank(apoderado.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreProvincia())){
                                apoderado.getDomicilios().get(0).getDatosFinalesCatGeo().setNombreProvincia(apoderado.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreProvincia() +  DocumentosPersonasController.SALTO_LINEA + apoderadoTemporal.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreProvincia());
                            }else{
                                apoderado.getDomicilios().get(0).getDatosFinalesCatGeo().setNombreProvincia(apoderado.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreProvincia() +  DocumentosPersonasController.SALTO_LINEA);
                            }
                            
                          //CP
                            if(StringUtils.isNotBlank(apoderado.getDomicilios().get(0).getDatosFinalesCatGeo().getCodigoPostal())){
                            	apoderado.getDomicilios().get(0).getDatosFinalesCatGeo().setCodigoPostal(apoderado.getDomicilios().get(0).getDatosFinalesCatGeo().getCodigoPostal() +  DocumentosPersonasController.SALTO_LINEA + apoderadoTemporal.getDomicilios().get(0).getDatosFinalesCatGeo().getCodigoPostal());
                            }else{
                            	apoderado.getDomicilios().get(0).getDatosFinalesCatGeo().setCodigoPostal(apoderado.getDomicilios().get(0).getDatosFinalesCatGeo().getCodigoPostal() +  DocumentosPersonasController.SALTO_LINEA);
                            }
                            
                          //Numero Exterior
                            if(StringUtils.isNotBlank(apoderado.getDomicilios().get(0).getNumeroExterior())){
                                apoderado.getDomicilios().get(0).setNumeroExterior(apoderado.getDomicilios().get(0).getNumeroExterior() +  DocumentosPersonasController.SALTO_LINEA + apoderadoTemporal.getDomicilios().get(0).getNumeroExterior());
                            }else{
                                apoderado.getDomicilios().get(0).setNumeroExterior(apoderado.getDomicilios().get(0).getNumeroExterior() +  DocumentosPersonasController.SALTO_LINEA);
                            }
                            //Numero interior
                            if(StringUtils.isNotBlank(apoderado.getDomicilios().get(0).getInterior()) && StringUtils.isNotBlank(apoderadoTemporal.getDomicilios().get(0).getInterior())){
                                apoderado.getDomicilios().get(0).setInterior(apoderado.getDomicilios().get(0).getInterior() +  DocumentosPersonasController.SALTO_LINEA + apoderadoTemporal.getDomicilios().get(0).getInterior());
                            }else if(StringUtils.isNotBlank(apoderado.getDomicilios().get(0).getInterior()) && !StringUtils.isNotBlank(apoderadoTemporal.getDomicilios().get(0).getInterior())){
                                apoderado.getDomicilios().get(0).setInterior(apoderado.getDomicilios().get(0).getInterior() +  DocumentosPersonasController.SALTO_LINEA);
                            }else if(!StringUtils.isNotBlank(apoderado.getDomicilios().get(0).getInterior()) && StringUtils.isNotBlank(apoderadoTemporal.getDomicilios().get(0).getInterior())){
                                apoderado.getDomicilios().get(0).setInterior(StringUtils.EMPTY +  DocumentosPersonasController.SALTO_LINEA + apoderadoTemporal.getDomicilios().get(0).getInterior() );
                            }
                            
                          //Colonia
                            if(StringUtils.isNotBlank(apoderado.getDomicilios().get(0).getColonia())){
                                apoderado.getDomicilios().get(0).setColonia(apoderado.getDomicilios().get(0).getColonia() +  DocumentosPersonasController.SALTO_LINEA + apoderadoTemporal.getDomicilios().get(0).getColonia());
                            }else{
                                apoderado.getDomicilios().get(0).setColonia(apoderado.getDomicilios().get(0).getColonia() +  DocumentosPersonasController.SALTO_LINEA);
                            }
                            //nombreMunicipio
                            if(StringUtils.isNotBlank(apoderado.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreMunicipio())){
                                apoderado.getDomicilios().get(0).getDatosFinalesCatGeo().setNombreMunicipio(apoderado.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreMunicipio() +  DocumentosPersonasController.SALTO_LINEA + apoderadoTemporal.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreMunicipio());
                            }else{
                                apoderado.getDomicilios().get(0).getDatosFinalesCatGeo().setNombreMunicipio(apoderado.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreMunicipio() +  DocumentosPersonasController.SALTO_LINEA);
                            }
                            
                          //Nombre calle
                            if(StringUtils.isNotBlank(apoderado.getDomicilios().get(0).getNombreCalle())){
                                apoderado.getDomicilios().get(0).setNombreCalle(apoderado.getDomicilios().get(0).getNombreCalle() +  DocumentosPersonasController.SALTO_LINEA + apoderadoTemporal.getDomicilios().get(0).getNombreCalle());
                            }else{
                                apoderado.getDomicilios().get(0).setNombreCalle(apoderado.getDomicilios().get(0).getNombreCalle() +  DocumentosPersonasController.SALTO_LINEA);
                            }
                            
                          //Pais
                            if(StringUtils.isNotBlank(apoderado.getDomicilios().get(0).getDatosFinalesCatGeo().getNombrePais())){
                                apoderado.getDomicilios().get(0).getDatosFinalesCatGeo().setNombrePais(apoderado.getDomicilios().get(0).getDatosFinalesCatGeo().getNombrePais() +  DocumentosPersonasController.SALTO_LINEA + apoderadoTemporal.getDomicilios().get(0).getDatosFinalesCatGeo().getNombrePais());
                            }else{
                                apoderado.getDomicilios().get(0).getDatosFinalesCatGeo().setNombrePais(apoderado.getDomicilios().get(0).getDatosFinalesCatGeo().getNombrePais() +  DocumentosPersonasController.SALTO_LINEA);
                            }
                            
                            //TELEFONO primerapoderado.domicilios[0].telefono
                            if(StringUtils.isNotBlank(apoderado.getDomicilios().get(0).getTelefono())){
                                apoderado.getDomicilios().get(0).setTelefono(apoderado.getDomicilios().get(0).getTelefono() +  DocumentosPersonasController.SALTO_LINEA + apoderadoTemporal.getDomicilios().get(0).getTelefono());
                            }else{
                                apoderado.getDomicilios().get(0).setTelefono(apoderado.getDomicilios().get(0).getTelefono() +  DocumentosPersonasController.SALTO_LINEA);
                            }
                		}
                		
                		
                	}
                    
                    
                }
                if (!tienePropietario
                        && (DocumentosPersonasController.CTE_RELACION_PROPIETARIO
                                .equals(personaRelacionadaBean
                                        .getRelacionSeleccionada()
                                        .getClaveFila()) || DocumentosPersonasController.CTE_RELACION_PROPIETARIO_BIS
                                .equals(personaRelacionadaBean
                                        .getRelacionSeleccionada()
                                        .getClaveFila()))) {
                    if(propietario == null){
                    	try{
                    		propietario = (ClientePersonaFisicaBean) this.consultaPersonasFisica
                                    .ejecutarTRN(personaRelacionadaBean.getIdInterna());
                       	estadoCivilPropietario = this.getEstadoCivilCotitular(estadoCivilPropietario, propietario);
                    		ocupacionPropietario = this.getOcupacionCotitular(ocupacionPropietario, propietario);
                    		cnaePropietario = this.getCnaeCotitular(cnaePropietario, propietario);
                        	fechasNacimientoPropietario = sdf.format(propietario.getFechaNacimiento());
                        	lugarNacimientoPropietario = this.getLugarNacimiento(lugarNacimientoPropietario, propietario);
                        	estadoNacimientoPropietario = this.getEstadoNacimiento(estadoNacimientoPropietario, propietario);
                        	paisNacimientoPropietario = this.getPaisNacimiento(paisNacimientoPropietario, propietario);
                        	sexoPropietario = this.getSexoCotitular(sexoPropietario, propietario);
                        	edadPropietario = this.getEdad(edad, propietario);
                    	}catch(ControlableException | NoControlableException ce){
                    		
                    	}
                    	 
                 	}else{
                 		ClientePersonaFisicaBean propietarioTemporal = null;
                 		try{
                 			propietarioTemporal = (ClientePersonaFisicaBean) this.consultaPersonasFisica.ejecutarTRN(personaRelacionadaBean.getIdInterna());
                 		}catch(ControlableException | NoControlableException ce){
                 			
                 		}
                 		if(propietarioTemporal != null){
                 			//NOMBRE APODERADO
                         	if(StringUtils.isNotBlank(propietarioTemporal.getNombre())){
                                 propietario.setNombre(propietario.getNombre() + DocumentosPersonasController.SALTO_LINEA + propietarioTemporal.getNombre() );
                             }else{
                                 propietario.setNombre(propietario.getNombre() + DocumentosPersonasController.SALTO_LINEA);
                             }
                         	
                         	//APELLIDO PATERNO
                         	if(StringUtils.isNotBlank(propietarioTemporal.getApePaterno())){
                                 propietario.setApePaterno(propietario.getApePaterno() + DocumentosPersonasController.SALTO_LINEA + propietarioTemporal.getApePaterno() );
                             }else{
                                 propietario.setApePaterno(propietario.getApePaterno() + DocumentosPersonasController.SALTO_LINEA);
                             }
                         	
                         	//APELLIDO MATERNO
                         	if(StringUtils.isNotBlank(propietarioTemporal.getApeMaterno())){
                                 propietario.setApeMaterno(propietario.getApeMaterno() + DocumentosPersonasController.SALTO_LINEA + propietarioTemporal.getApeMaterno() );
                             }else{
                                 propietario.setApeMaterno(propietario.getApeMaterno() + DocumentosPersonasController.SALTO_LINEA);
                             }
                         	
                         	//SEXO
                         	sexoPropietario = sexoPropietario + DocumentosPersonasController.SALTO_LINEA + this.getSexoCotitular(sexoPropietario, propietarioTemporal);
                         	
                         	//EDAD
                         	edadPropietario = edadPropietario + DocumentosPersonasController.SALTO_LINEA + this.getEdad(edad, propietarioTemporal);
                         	
                         	//CURP
                         	if(StringUtils.isNotBlank(propietarioTemporal.getCurp())){
                                 propietario.setCurp(propietario.getCurp() + DocumentosPersonasController.SALTO_LINEA + propietarioTemporal.getCurp() );
                             }else{
                                 propietario.setCurp(propietario.getCurp() + DocumentosPersonasController.SALTO_LINEA);
                             }
                         	
                         	//RFC
                         	if(StringUtils.isNotBlank(propietarioTemporal.getRfc())){
                                 propietario.setRfc(propietario.getRfc() + DocumentosPersonasController.SALTO_LINEA + propietarioTemporal.getRfc() );
                             }else{
                                 propietario.setRfc(propietario.getRfc() + DocumentosPersonasController.SALTO_LINEA);
                             }
                         	
                         	//ESTADO_CIVIL
                         	estadoCivilPropietario = estadoCivilPropietario + DocumentosPersonasController.SALTO_LINEA + this.getEstadoApoderado(estadoCivilPropietario, propietarioTemporal);
                         	
                         	//OCUPACION O PROFESION
                         	ocupacionPropietario = ocupacionPropietario + DocumentosPersonasController.SALTO_LINEA + this.getOcupacionApoderado(ocupacionPropietario, propietarioTemporal);
                         	
                         	//CNAE
                         	cnaePropietario = cnaePropietario + DocumentosPersonasController.SALTO_LINEA + this.getCnaeApoderado(cnaePropietario, propietarioTemporal);
                         	
                         	//FECHA DE NACIMIENTO
                         	fechasNacimientoPropietario = fechasNacimientoPropietario + DocumentosPersonasController.SALTO_LINEA + sdf.format(propietarioTemporal.getFechaNacimiento());
                         	
                         	//LUGAR DE NACIMIENTO
                         	lugarNacimientoPropietario = lugarNacimientoPropietario + DocumentosPersonasController.SALTO_LINEA + this.getLugarNacimiento(lugarNacimientoPropietario, propietarioTemporal);
                             
                             //ESTADO
                             estadoNacimientoPropietario = estadoNacimientoPropietario + DocumentosPersonasController.SALTO_LINEA + this.getEstadoNacimiento(estadoNacimientoPropietario, propietarioTemporal);
                             
                             //PAIS DE NACIMIENTO
                             paisNacimientoPropietario = paisNacimientoPropietario + DocumentosPersonasController.SALTO_LINEA + this.getPaisNacimiento(paisNacimientoPropietario, propietarioTemporal);
                             
                             //PROVINCICA
                             if(StringUtils.isNotBlank(propietario.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreProvincia())){
                                 propietario.getDomicilios().get(0).getDatosFinalesCatGeo().setNombreProvincia(propietario.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreProvincia() +  DocumentosPersonasController.SALTO_LINEA + propietarioTemporal.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreProvincia());
                             }else{
                                 propietario.getDomicilios().get(0).getDatosFinalesCatGeo().setNombreProvincia(propietario.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreProvincia() +  DocumentosPersonasController.SALTO_LINEA);
                             }
                             
                           //CP
                             if(StringUtils.isNotBlank(propietario.getDomicilios().get(0).getDatosFinalesCatGeo().getCodigoPostal())){
                             	propietario.getDomicilios().get(0).getDatosFinalesCatGeo().setCodigoPostal(propietario.getDomicilios().get(0).getDatosFinalesCatGeo().getCodigoPostal() +  DocumentosPersonasController.SALTO_LINEA + propietarioTemporal.getDomicilios().get(0).getDatosFinalesCatGeo().getCodigoPostal());
                             }else{
                             	propietario.getDomicilios().get(0).getDatosFinalesCatGeo().setCodigoPostal(propietario.getDomicilios().get(0).getDatosFinalesCatGeo().getCodigoPostal() +  DocumentosPersonasController.SALTO_LINEA);
                             }
                             
                           //Numero Exterior
                             if(StringUtils.isNotBlank(propietario.getDomicilios().get(0).getNumeroExterior())){
                                 propietario.getDomicilios().get(0).setNumeroExterior(propietario.getDomicilios().get(0).getNumeroExterior() +  DocumentosPersonasController.SALTO_LINEA + propietarioTemporal.getDomicilios().get(0).getNumeroExterior());
                             }else{
                                 propietario.getDomicilios().get(0).setNumeroExterior(propietario.getDomicilios().get(0).getNumeroExterior() +  DocumentosPersonasController.SALTO_LINEA);
                             }
                             //Numero interior
                             if(StringUtils.isNotBlank(propietario.getDomicilios().get(0).getInterior()) && StringUtils.isNotBlank(propietarioTemporal.getDomicilios().get(0).getInterior())){
                                 propietario.getDomicilios().get(0).setInterior(propietario.getDomicilios().get(0).getInterior() +  DocumentosPersonasController.SALTO_LINEA + propietarioTemporal.getDomicilios().get(0).getInterior());
                             }else if(StringUtils.isNotBlank(propietario.getDomicilios().get(0).getInterior()) && !StringUtils.isNotBlank(propietarioTemporal.getDomicilios().get(0).getInterior())){
                                 propietario.getDomicilios().get(0).setInterior(propietario.getDomicilios().get(0).getInterior() +  DocumentosPersonasController.SALTO_LINEA);
                             }else if(!StringUtils.isNotBlank(propietario.getDomicilios().get(0).getInterior()) && StringUtils.isNotBlank(propietarioTemporal.getDomicilios().get(0).getInterior())){
                                 propietario.getDomicilios().get(0).setInterior(StringUtils.EMPTY +  DocumentosPersonasController.SALTO_LINEA + propietarioTemporal.getDomicilios().get(0).getInterior() );
                             }
                             
                           //Colonia
                             if(StringUtils.isNotBlank(propietario.getDomicilios().get(0).getColonia())){
                                 propietario.getDomicilios().get(0).setColonia(propietario.getDomicilios().get(0).getColonia() +  DocumentosPersonasController.SALTO_LINEA + propietarioTemporal.getDomicilios().get(0).getColonia());
                             }else{
                                 propietario.getDomicilios().get(0).setColonia(propietario.getDomicilios().get(0).getColonia() +  DocumentosPersonasController.SALTO_LINEA);
                             }
                             //nombreMunicipio
                             if(StringUtils.isNotBlank(propietario.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreMunicipio())){
                                 propietario.getDomicilios().get(0).getDatosFinalesCatGeo().setNombreMunicipio(propietario.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreMunicipio() +  DocumentosPersonasController.SALTO_LINEA + propietarioTemporal.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreMunicipio());
                             }else{
                                 propietario.getDomicilios().get(0).getDatosFinalesCatGeo().setNombreMunicipio(propietario.getDomicilios().get(0).getDatosFinalesCatGeo().getNombreMunicipio() +  DocumentosPersonasController.SALTO_LINEA);
                             }
                             
                           //Nombre calle
                             if(StringUtils.isNotBlank(propietario.getDomicilios().get(0).getNombreCalle())){
                                 propietario.getDomicilios().get(0).setNombreCalle(propietario.getDomicilios().get(0).getNombreCalle() +  DocumentosPersonasController.SALTO_LINEA + propietarioTemporal.getDomicilios().get(0).getNombreCalle());
                             }else{
                                 propietario.getDomicilios().get(0).setNombreCalle(propietario.getDomicilios().get(0).getNombreCalle() +  DocumentosPersonasController.SALTO_LINEA);
                             }
                             
                           //Pais
                             if(StringUtils.isNotBlank(propietario.getDomicilios().get(0).getDatosFinalesCatGeo().getNombrePais())){
                                 propietario.getDomicilios().get(0).getDatosFinalesCatGeo().setNombrePais(propietario.getDomicilios().get(0).getDatosFinalesCatGeo().getNombrePais() +  DocumentosPersonasController.SALTO_LINEA + propietarioTemporal.getDomicilios().get(0).getDatosFinalesCatGeo().getNombrePais());
                             }else{
                                 propietario.getDomicilios().get(0).getDatosFinalesCatGeo().setNombrePais(propietario.getDomicilios().get(0).getDatosFinalesCatGeo().getNombrePais() +  DocumentosPersonasController.SALTO_LINEA);
                             }
                             
                             
                             
                             
                             //TELEFONO primerapoderado.domicilios[0].telefono
                             if(StringUtils.isNotBlank(propietario.getDomicilios().get(0).getTelefono())){
                                 propietario.getDomicilios().get(0).setTelefono(propietario.getDomicilios().get(0).getTelefono() +  DocumentosPersonasController.SALTO_LINEA + propietarioTemporal.getDomicilios().get(0).getTelefono());
                             }else{
                                 propietario.getDomicilios().get(0).setTelefono(propietario.getDomicilios().get(0).getTelefono() +  DocumentosPersonasController.SALTO_LINEA);
                             }
                 		}
                 		
                 	}
                   
                }
                
            }

            
            DomicilioTipoBean domicilioFiscal = null;
            DomicilioTipoBean domicilioSocial = null;
            Boolean tieneDomicilioFiscal = false;
            Boolean tieneDomicilioSocial = false;
            
            //Obtenemos los datos sobre los domicilios del cliente
            domicilioController
                    .setDomiciliosList(consultaDomiciliosPersonaBackEnd
                            .ejecutarTRN(
                                    this.idInternaPersona,
                                    null));
            if (domicilioController.getDomiciliosList() != null
                    && domicilioController.getDomiciliosList().size() > 0) {
                for (DomicilioTipoBean domicilio : domicilioController
                        .getDomiciliosList()) {
                    if(domicilio.isDomicilioFiscalGrouping() && !tieneDomicilioFiscal){
                        tieneDomicilioFiscal = true;
                        domicilio.setIdInternoPersona(this.idInternaPersona + StringUtils.EMPTY);
                        domicilioFiscal = consultaDetalleDomicilio(domicilio);
                    }
                    if(domicilio.isDomicilioSocialGrouping() && !tieneDomicilioSocial){
                        tieneDomicilioSocial = true;
                        domicilio.setIdInternoPersona(this.idInternaPersona + StringUtils.EMPTY);
                        domicilioSocial = consultaDetalleDomicilio(domicilio);
                    }
                    
                }
            }
            
                     
            
            

            //Rellenamos la plantilla que mandaremos al jasper
            PlantillaBean plantilla = new PlantillaBean();

            plantilla.setClienteBean(this.clientePersonaMoral);
            
            plantilla.setPrimerApoderado(apoderado);
            plantilla.setPrimerPropietario(propietario);
            plantilla.setDomicilioFiscal(domicilioFiscal);
            plantilla.setDomicilioSocial(domicilioSocial);
            
            //rellenamos la lista de beans
            final List<PlantillaBean> listaBeans = new ArrayList<PlantillaBean>();
            listaBeans.add(plantilla);
            
            //Creamos los parametros que se mandan al jasper
            final Map<String, Object> params = new HashMap<String, Object>();
            
            //Obtenemos datos del titular
            String cnae;
            String estructuraLegal;
           
                       
            String nombreOficina;
           
            try {
                nombreOficina = catalogoCentrosLoaderTask
                        .getCatalogoBean(contextoUtils.getEntidad(),
                                contextoUtils.getSucursal()).getDescripcionL();
            } catch (Exception e) {
                nombreOficina = StringUtils.EMPTY;
            }
           
            try {
                cnae = catalogoUtils.getCatalogoBean(
                        CatalogoEnum.TP_CNAE_PERS,
                        this.clientePersonaMoral.getCnae()).getDescripcionL();
                if(cnae.split("-").length == 2){
                	cnae = cnae.split("-")[1];
                }
            } catch (Exception e) {
                cnae = StringUtils.EMPTY;
            }
            try {
                estructuraLegal = catalogoUtils.getCatalogoBean(
                        CatalogoEnum.TP_ESTRCT_LGL_ORG,
                        this.clientePersonaMoral.getEstructuraLegal()).getDescripcionL();
            } catch (Exception e) {
                estructuraLegal = StringUtils.EMPTY;
            }
                 
            
            
            
            
            // Parametros para el titular
            params.put("ESTRUCTURA_LEGAL", estructuraLegal);
            params.put("CNAE", cnae);
            params.put("NO_CONTRATO", noContrato);

             
            // Parametros para el apoderado
            params.put("ESTADO_CIVIL_APODERADO", estadoCivilApoderado);
            params.put("OCUPACION_APODERADO", ocupacionApoderado);
            params.put("LUGAR_NACIMIENTO_APODERADO", lugarNacimientoApoderado);
            params.put("ESTADO_NACIMIENTO_APODERADO", estadoNacimientoApoderado);
            params.put("PAIS_NACIMIENTO_APODERADO", paisNacimientoApoderado);
            params.put("NACIONALIDAD_APODERADO", paisNacimientoApoderado);
            params.put("CNAE_APODERADO", cnaeApoderado);
            params.put("SEXO_APODERADO", sexoApoderado);
            params.put("EDAD_APODERADO", edadApoderado);
            params.put("FECHAS_NACIMIENTOS_APODERADO", fechasNacimientoApoderado);
            
            
            // Parametros para el propietario
            params.put("ESTADO_CIVIL_PROPIETARIO", estadoCivilPropietario);
            params.put("OCUPACION_PROPIETARIO", ocupacionPropietario);
            params.put("LUGAR_NACIMIENTO_PROPIETARIO",
                    lugarNacimientoPropietario);
            params.put("ESTADO_NACIMIENTO_PROPIETARIO",
                    estadoNacimientoPropietario);
            params.put("PAIS_NACIMIENTO_PROPIETARIO", paisNacimientoPropietario);
            params.put("NACIONALIDAD_PROPIETARIO", paisNacimientoPropietario);
            params.put("CNAE_PROPIETARIO", cnaePropietario);
            params.put("EDAD_PROPIETARIO", edadPropietario);
            params.put("FECHAS_NACIMIENTOS_PROPIETARIO", fechasNacimientoPropietario);
            params.put("SEXO_PROPIETARIO", sexoPropietario);
            
            params.put("NOMBRE_OFICINA", nombreOficina);
            params.put("PLAZA_BANCARIA", this.contextoUtils.getPlazaBancaria());
            params.put("NOMBRE_EMPLEADO", this.contextoUtils.getNombre());
            params.put("NUMERO_EMPLEADO", this.contextoUtils.getId());

            
            
            //En caso de cliente de riesgo debemos obtener mas datos    
            if (this.cliente.getEsClienteRiesgo()){
                if(this.clientePersonaMoral.getRelacionesClienteRiesgo()!=null &&
                        this.clientePersonaMoral.getRelacionesClienteRiesgo().getReferenciasComerciales()!=null && this.clientePersonaMoral.getRelacionesClienteRiesgo().getReferenciasComerciales().size()>0){ 
                	String nombreReferenciaComercial = StringUtils.EMPTY;
                	String giroReferenciaComercial = StringUtils.EMPTY;
                	String domicilioReferenciaComercial = StringUtils.EMPTY;
                	String telefonoReferenciaComercial = StringUtils.EMPTY;
                	String relacionReferenciaComercial = StringUtils.EMPTY;
                	int tamano= this.clientePersonaMoral.getRelacionesClienteRiesgo().getReferenciasComerciales().size();
                	for(int i = 0; i <= tamano-1; i++){
                		//NOMBRE REFERENCIA COMERCIAL
                		if(nombreReferenciaComercial == StringUtils.EMPTY){
                			nombreReferenciaComercial = this.clientePersonaMoral.getRelacionesClienteRiesgo().getReferenciasComerciales().get(i).getNombre();
                		}else{
                			nombreReferenciaComercial = nombreReferenciaComercial + DocumentosPersonasController.SALTO_LINEA + this.clientePersonaMoral.getRelacionesClienteRiesgo().getReferenciasComerciales().get(i).getNombre();
                		}
                		
                		//GIRO REFERENCIA COMERCIAL
                		if(giroReferenciaComercial == StringUtils.EMPTY){
                			giroReferenciaComercial = this.clientePersonaMoral.getRelacionesClienteRiesgo().getReferenciasComerciales().get(i).getGiro();
                		}else{
                			giroReferenciaComercial = giroReferenciaComercial + DocumentosPersonasController.SALTO_LINEA + this.clientePersonaMoral.getRelacionesClienteRiesgo().getReferenciasComerciales().get(i).getGiro();
                		}
                		
                		//DOMICILIO REFERENCIA COMERCIAL
                		if(domicilioReferenciaComercial == StringUtils.EMPTY){
                			domicilioReferenciaComercial = this.clientePersonaMoral.getRelacionesClienteRiesgo().getReferenciasComerciales().get(i).getDomicilio();
                		}else{
                			domicilioReferenciaComercial = domicilioReferenciaComercial + DocumentosPersonasController.SALTO_LINEA + this.clientePersonaMoral.getRelacionesClienteRiesgo().getReferenciasComerciales().get(i).getDomicilio();
                		}
                		
                		//TELEFONO REFERENCIA COMERCIAL
                		if(telefonoReferenciaComercial == StringUtils.EMPTY){
                			telefonoReferenciaComercial = this.clientePersonaMoral.getRelacionesClienteRiesgo().getReferenciasComerciales().get(i).getTelefono();
                		}else{
                			telefonoReferenciaComercial = telefonoReferenciaComercial + DocumentosPersonasController.SALTO_LINEA + this.clientePersonaMoral.getRelacionesClienteRiesgo().getReferenciasComerciales().get(i).getTelefono();
                		}
                		
                		//RELACION REFERENCIA COMERCIAL
                		if(relacionReferenciaComercial == StringUtils.EMPTY){
                			relacionReferenciaComercial = this.clientePersonaMoral.getRelacionesClienteRiesgo().getReferenciasComerciales().get(i).getRelacion();
                		}else{
                			relacionReferenciaComercial = relacionReferenciaComercial + DocumentosPersonasController.SALTO_LINEA + this.clientePersonaMoral.getRelacionesClienteRiesgo().getReferenciasComerciales().get(i).getRelacion();
                		}
                	}                    
                    params.put("NOMBRE_REFERENCIA_COMERCIAL",nombreReferenciaComercial);
                    params.put("GIRO_REFERENCIA_COMERCIAL", giroReferenciaComercial);
                    params.put("DOMICILIO_REFERENCIA_COMERCIAL", domicilioReferenciaComercial);
                    params.put("TELEFONO_REFERENCIA_COMERCIAL", telefonoReferenciaComercial);
                    params.put("RELACION_REFERENCIA_COMERCIAL", relacionReferenciaComercial);
                }
                
                if(this.clientePersonaMoral.getRelacionesClienteRiesgo()!=null &&
                        this.clientePersonaMoral.getRelacionesClienteRiesgo().getReferenciasBancarias()!=null && this.clientePersonaMoral.getRelacionesClienteRiesgo().getReferenciasBancarias().size()>0){
                	String bancoReferenciaBancaria = StringUtils.EMPTY;
                	String cuentaReferenciaBancaria = StringUtils.EMPTY;
                	String tipoCuentaReferenciaBancaria = StringUtils.EMPTY;
                	int tamano= this.clientePersonaMoral.getRelacionesClienteRiesgo().getReferenciasBancarias().size();
                	for(int i = 0; i <= tamano-1; i++){
                		//BANCO REFERENCIA BANCARIA
                		if(bancoReferenciaBancaria == StringUtils.EMPTY){
                			bancoReferenciaBancaria = this.clientePersonaMoral.getRelacionesClienteRiesgo().getReferenciasBancarias().get(i).getBanco();
                		}else{
                			bancoReferenciaBancaria = bancoReferenciaBancaria + DocumentosPersonasController.SALTO_LINEA + this.clientePersonaMoral.getRelacionesClienteRiesgo().getReferenciasBancarias().get(i).getBanco();
                		}
                		
                		//CUENTA REFERENCIA BANCARIA
                		if(cuentaReferenciaBancaria == StringUtils.EMPTY){
                			cuentaReferenciaBancaria = this.clientePersonaMoral.getRelacionesClienteRiesgo().getReferenciasBancarias().get(i).getNumCuenta();
                		}else{
                			cuentaReferenciaBancaria = cuentaReferenciaBancaria + DocumentosPersonasController.SALTO_LINEA + this.clientePersonaMoral.getRelacionesClienteRiesgo().getReferenciasBancarias().get(i).getNumCuenta();
                		}
                		
                		//TIPO CUENTA REFERENCIA BANCARIA
                		if(tipoCuentaReferenciaBancaria == StringUtils.EMPTY){
                			tipoCuentaReferenciaBancaria = this.clientePersonaMoral.getRelacionesClienteRiesgo().getReferenciasBancarias().get(i).getTipoCuenta();
                		}else{
                			tipoCuentaReferenciaBancaria = tipoCuentaReferenciaBancaria + DocumentosPersonasController.SALTO_LINEA + this.clientePersonaMoral.getRelacionesClienteRiesgo().getReferenciasBancarias().get(i).getTipoCuenta();
                		}                		
                		
                	}
                    params.put("BANCO_REFERENCIA_BANCARIA", bancoReferenciaBancaria);
                    params.put("CUENTA__REFERENCIA_BANCARIA", cuentaReferenciaBancaria);
                    params.put("TIPO_CUENTA_REFERENCIA_BANCARIA", tipoCuentaReferenciaBancaria);
                   
                }
                
              //Obtenemos los datos de las relaciones del primer accionista
                if(this.clientePersonaMoral.getRelacionesClienteRiesgo()!=null && 
                    this.clientePersonaMoral.getRelacionesClienteRiesgo().getAccionistas() != null && this.clientePersonaMoral.getRelacionesClienteRiesgo().getAccionistas().size()>0){
                    params.put("NOMBRE_ACCIONISTA_1", this.clientePersonaMoral.getRelacionesClienteRiesgo().getAccionistas().get(0).getNombre());
                    params.put("APE_PATERNO_ACCIONISTA_1", this.clientePersonaMoral.getRelacionesClienteRiesgo().getAccionistas().get(0).getApellidoPaterno());
                    params.put("APE_MATERNO_ACCIONISTA_1", this.clientePersonaMoral.getRelacionesClienteRiesgo().getAccionistas().get(0).getApellidoMaterno());
                    params.put("RFC_ACCIONISTA_1",this.clientePersonaMoral.getRelacionesClienteRiesgo().getAccionistas().get(0).getRfc());
                    params.put("PORCENTAJE_ACCIONISTA_1", this.clientePersonaMoral.getRelacionesClienteRiesgo().getAccionistas().get(0).getPorcentaje());
                    
                }
                //Obtenemos los datos de las relaciones del segundo accionista
                if(this.clientePersonaMoral.getRelacionesClienteRiesgo()!=null && 
                    this.clientePersonaMoral.getRelacionesClienteRiesgo().getAccionistas() != null && this.clientePersonaMoral.getRelacionesClienteRiesgo().getAccionistas().size()>1){
                    params.put("NOMBRE_ACCIONISTA_2", this.clientePersonaMoral.getRelacionesClienteRiesgo().getAccionistas().get(1).getNombre());
                    params.put("APE_PATERNO_ACCIONISTA_2", this.clientePersonaMoral.getRelacionesClienteRiesgo().getAccionistas().get(1).getApellidoPaterno());
                    params.put("APE_MATERNO_ACCIONISTA_2", this.clientePersonaMoral.getRelacionesClienteRiesgo().getAccionistas().get(1).getApellidoMaterno());
                    params.put("RFC_ACCIONISTA_2",this.clientePersonaMoral.getRelacionesClienteRiesgo().getAccionistas().get(1).getRfc());
                    params.put("PORCENTAJE_ACCIONISTA_2", this.clientePersonaMoral.getRelacionesClienteRiesgo().getAccionistas().get(1).getPorcentaje());
                    
                }
                //Obtenemos los datos de las relaciones del primer funcionario
                if(this.clientePersonaMoral.getRelacionesClienteRiesgo()!=null && 
                    this.clientePersonaMoral.getRelacionesClienteRiesgo().getFuncionarios() != null && this.clientePersonaMoral.getRelacionesClienteRiesgo().getFuncionarios().size()>0){
                    params.put("NOMBRE_FUNCIONARIO_1", this.clientePersonaMoral.getRelacionesClienteRiesgo().getFuncionarios().get(0).getNombre());
                    params.put("APE_PATERNO_FUNCIONARIO_1", this.clientePersonaMoral.getRelacionesClienteRiesgo().getFuncionarios().get(0).getApellidoPaterno());
                    params.put("APE_MATERNO_FUNCIONARIO_1", this.clientePersonaMoral.getRelacionesClienteRiesgo().getFuncionarios().get(0).getApellidoMaterno());
                    params.put("RFC_FUNCIONARIO_1",this.clientePersonaMoral.getRelacionesClienteRiesgo().getFuncionarios().get(0).getRfc());
                    params.put("PUESTO_FUNCIONARIO_1", this.clientePersonaMoral.getRelacionesClienteRiesgo().getFuncionarios().get(0).getPuesto());
                    
                }
                
                //Obtenemos los datos de las relaciones del segundo funcionario
                if(this.clientePersonaMoral.getRelacionesClienteRiesgo()!=null && 
                    this.clientePersonaMoral.getRelacionesClienteRiesgo().getFuncionarios() != null && this.clientePersonaMoral.getRelacionesClienteRiesgo().getFuncionarios().size()>1){
                    params.put("NOMBRE_FUNCIONARIO_2", this.clientePersonaMoral.getRelacionesClienteRiesgo().getFuncionarios().get(1).getNombre());
                    params.put("APE_PATERNO_FUNCIONARIO_2", this.clientePersonaMoral.getRelacionesClienteRiesgo().getFuncionarios().get(1).getApellidoPaterno());
                    params.put("APE_MATERNO_FUNCIONARIO_2", this.clientePersonaMoral.getRelacionesClienteRiesgo().getFuncionarios().get(1).getApellidoMaterno());
                    params.put("RFC_FUNCIONARIO_2",this.clientePersonaMoral.getRelacionesClienteRiesgo().getFuncionarios().get(1).getRfc());
                    params.put("PUESTO_FUNCIONARIO_2", this.clientePersonaMoral.getRelacionesClienteRiesgo().getFuncionarios().get(1).getPuesto());
                    
                }

              //Obtenemos los datos de las relaciones del tercer funcionario
                if(this.clientePersonaMoral.getRelacionesClienteRiesgo()!=null && 
                        this.clientePersonaMoral.getRelacionesClienteRiesgo().getFuncionarios() != null && this.clientePersonaMoral.getRelacionesClienteRiesgo().getFuncionarios().size()>2){
                        params.put("NOMBRE_FUNCIONARIO_3", this.clientePersonaMoral.getRelacionesClienteRiesgo().getFuncionarios().get(2).getNombre());
                        params.put("APE_PATERNO_FUNCIONARIO_3", this.clientePersonaMoral.getRelacionesClienteRiesgo().getFuncionarios().get(2).getApellidoPaterno());
                        params.put("APE_MATERNO_FUNCIONARIO_3", this.clientePersonaMoral.getRelacionesClienteRiesgo().getFuncionarios().get(2).getApellidoMaterno());
                        params.put("RFC_FUNCIONARIO_3",this.clientePersonaMoral.getRelacionesClienteRiesgo().getFuncionarios().get(2).getRfc());
                        params.put("PUESTO_FUNCIONARIO_3", this.clientePersonaMoral.getRelacionesClienteRiesgo().getFuncionarios().get(2).getPuesto());
                        
                }
                
                
                //Obtenemos datos del grupo
                String nacionalidadGrupo;
                String paisGrupo;
                String coberturaGrupo;
                try {
                    nacionalidadGrupo = catalogoPaises.getCatalogoDescripcion(this.clientePersonaMoral.getGrupoFilialBean().getNacionalidad());
                   
                }catch (Exception e) {
                    nacionalidadGrupo = StringUtils.EMPTY;
                }
                try {
                    paisGrupo = catalogoPaises.getCatalogoDescripcion(this.clientePersonaMoral.getGrupoFilialBean().getPais());
                } catch (Exception e) {
                    paisGrupo = StringUtils.EMPTY;
                }
                try {
                    coberturaGrupo = catalogoUtils.getCatalogoBean(
                            CatalogoEnum.TP_AMBTO_ORG,
                            propietario.getCnae()).getDescripcionL();
                } catch (Exception e) {
                    coberturaGrupo = StringUtils.EMPTY;
                }
                
                params.put("NACIONALIDAD_GRUPO", nacionalidadGrupo);
                params.put("PAIS_GRUPO", paisGrupo);
                params.put("COBERTURA_GRUPO", coberturaGrupo);
                
            }
            
            //Rellenamos las imagnes de logo
            final Map<String, String> images = new HashMap<String, String>();
            images.put("Logo_bsfi_bn.png", "LOGO");
            
            
            
            if (this.clientePersonaMoral.getEsClienteRiesgo()) {
                pdfUtils.generaPdf(ConstantesFuncionales.CTE_CEDULA_PM_AR, params, images, null,
                        "P0700400", listaBeans);
            } else {
                pdfUtils.generaPdf(ConstantesFuncionales.CTE_CEDULA_PM_BR, params, images, null,
                        "P0700300", listaBeans);
            }
        }

    }

        /**
         * Funcion que consulta todo el detalle de un domicilio
         * 
         * @param domicilio
         * @return
         */
        public DomicilioTipoBean consultaDetalleDomicilio(
                DomicilioTipoBean domicilio) {

            if (domicilio != null && domicilio.getIdInternoPersona() != null
                    && domicilio.getNumeroDireccion() != 0) {

                LOGGER.debug("/*/*/*/*/*/Numero de domicilio: "
                        + domicilio.getNumeroDireccion());

                DomicilioBean domicilioBean = consultaDetalleDomicilioBackEnd.ejectuarTRN(Integer.valueOf(domicilio.getIdInternoPersona()),domicilio.getNumeroDireccion());
                // Se hace un wrapp para que no se pierdan los datos del
                // agrupamiento
                // de tipo de domicilio
                domicilioWrapper.wrappDomicilio(domicilio, domicilioBean);

                
                // Verificar el grouping
                LOGGER.debug("-----------------Detalle domicilio---------");
                LOGGER.debug("El domicilio tiene los siguientes tags de tipo de domicilio: "
                        + domicilio.getGrouping().size());
                for (TipoDomicilioEnum tipo : domicilio.getGrouping()) {
                    LOGGER.debug(tipo.getTipoDomicilio());
                }

                return domicilio;
            } else {
                return domicilio;
            }
        }    
    
        
    /**
     * @return Metodo utilizado para acceder al controller de documentos por
     *         primera vez.
     */
    public String inicio() {
        return NavegacionEnum.DOCUMENTOS.getRuta();
    }

    /**
     * 
     */
    public boolean emitirDocumento(final DocumentoPersonaBean documento) {
        return ConstantesFuncionales.getCodigosCedulaConocimiento().contains(
                documento.getTipo());
    }

    /**
     * Se encarga de crear un nuevo documento
     */
    public void nuevoDocumento() {
        if (tipoDocumento != null
                && StringUtils.isNotBlank(tipoDocumento.getClaveFila())) {

            DocumentoPersonaBean documento = new DocumentoPersonaBean();
            documento.setTipo(this.tipoDocumento.getClaveFila());
            documento.setTipoDesc(this.tipoDocumento.getDescripcionL());
            documento.setFecha(contextoUtils.getFechaContableActual());
            this.documentosNuevos.add(documento);

        }

    }

    /**
     * En el caso de venir desde un flujo de alta de persona se deben crear
     * documentos de identificacion, domicilio y cedula
     */
    public void creaDocumentosIniciales() {

        // Crear documento identificacion
        DocumentoPersonaBean documentoIdentificacion = new DocumentoPersonaBean();
        // Crear documento domicilio
        DocumentoPersonaBean documentoDomicilio = new DocumentoPersonaBean();
        // Crear documento cedula
        DocumentoPersonaBean documentoCedula = new DocumentoPersonaBean();
        String tipoDocumentoIdentificacion = null;
        String tipoDocumentoDomicilio = null;
        String descripcionDocumentoDomicilio = null;

        // Se obtiene el tipo de identificacion
        tipoDocumentoIdentificacion = ConstantesFuncionales
                .getRelacionIdentificacionDocumento().get(
                        this.cliente.getTipoIdentificacion());

        // Se obtiene el tipo de documento de domicilio
        if (this.cliente.getDomicilios() != null
                && this.cliente.getDomicilios().size() > 0) {
            tipoDocumentoDomicilio = this.cliente.getDomicilios().get(0)
                    .getComprobanteDomicilio();
            descripcionDocumentoDomicilio = this.cliente.getDomicilios().get(0)
                    .getDescripcion();
        }

        // Crear documento identificacion en caso de menores se incluye el
        // acta de nacimiento y la credencial IFE para el representante legal en
        // cualquier otro caso
        // el documento informado

        if (this.obtieneFlash().get(
                ParametrosFlashEnum.CLIENTE_PF_BEAN.getParamFlash()) != null
                && ConstantesFuncionales.isMenorEdad(this.cliente
                        .getFechaNacimiento())) {

            documentoIdentificacion
                    .setTipo(ConstantesFuncionales.CTE_ACTA_NACIMIENTO);
            documentoIdentificacion.setTipoDesc(catalogoUtils.getCatalogoBean(
                    CatalogoEnum.TP_DOC, documentoIdentificacion.getTipo())
                    .getDescripcionL());

            this.documentosNuevos.add(documentoIdentificacion);

            // Crear documento identificacion representante
            DocumentoPersonaBean documentoIdentificacionRepresentante = new DocumentoPersonaBean();

            documentoIdentificacionRepresentante
                    .setTipo(ConstantesFuncionales.CTE_CREDENCIAL_IFE);
            documentoIdentificacionRepresentante.setTipoDesc(catalogoUtils
                    .getCatalogoBean(CatalogoEnum.TP_DOC,
                            documentoIdentificacionRepresentante.getTipo())
                    .getDescripcionL());
            this.documentosNuevos.add(documentoIdentificacionRepresentante);

        } else if (StringUtils.isNotBlank(tipoDocumentoIdentificacion)) {
            documentoIdentificacion.setTipo(tipoDocumentoIdentificacion);
            CatalogoBean catalogo = catalogoUtils.getCatalogoBean(
                    CatalogoEnum.TP_DOC, tipoDocumentoIdentificacion);

            documentoIdentificacion.setTipoDesc(catalogo.getDescripcionL());
            this.documentosNuevos.add(documentoIdentificacion);
        }

        // Crear documento domicilio
        if (StringUtils.isNotBlank(tipoDocumentoDomicilio)) {
            documentoDomicilio.setTipo(tipoDocumentoDomicilio);
            CatalogoBean catalogo = catalogoUtils.getCatalogoBean(
                    CatalogoEnum.TP_DOC, tipoDocumentoDomicilio);

            documentoDomicilio.setTipoDesc(catalogo.getDescripcionL());
            documentoDomicilio.setDescripcion(descripcionDocumentoDomicilio);
            this.documentosNuevos.add(documentoDomicilio);
        }

        // Crear cedula conocimiento
        if (this.obtieneFlash().get(
                ParametrosFlashEnum.CLIENTE_PF_BEAN.getParamFlash()) != null) {
            documentoCedula
                    .setTipo(ConstantesFuncionales.CTE_CEDULA_PERSONA_FISICA);
            CatalogoBean catalogo = catalogoUtils.getCatalogoBean(
                    CatalogoEnum.TP_DOC,
                    ConstantesFuncionales.CTE_CEDULA_PERSONA_FISICA);
            documentoCedula.setTipoDesc(catalogo.getDescripcionL());
            this.documentosNuevos.add(documentoCedula);
        } else if (this.obtieneFlash().get(
                ParametrosFlashEnum.CLIENTE_PM_BEAN.getParamFlash()) != null) {
            documentoCedula
                    .setTipo(ConstantesFuncionales.CTE_CEDULA_PERSONA_MORAL);
            CatalogoBean catalogo = catalogoUtils.getCatalogoBean(
                    CatalogoEnum.TP_DOC,
                    ConstantesFuncionales.CTE_CEDULA_PERSONA_MORAL);
            documentoCedula.setTipoDesc(catalogo.getDescripcionL());
            this.documentosNuevos.add(documentoCedula);
        }

    }

    public void inicializate() {
        LOGGER.debug("Entra el metodo inicializate");
        // Iniciamos los arrayList
        this.documentosNuevos = new ArrayList<DocumentoPersonaBean>();
        this.documentosConsultados = new ArrayList<DocumentoPersonaBean>();
        /**
         * Logica para obtener los parametros de entrada
         */
        
        Boolean modificacionCliente = (Boolean) this.obtieneFlash().get(
                ParametrosFlashEnum.MODIFICACION_CLIENTE.getParamFlash());
        
        // Caso de que se acceda desde el alta o modificacion de cliente persona
        // fisica o moral
        if (this.obtieneFlash().get(
                ParametrosFlashEnum.MODIFICACION_CLIENTE.getParamFlash()) != null) {
            // Caso de persona fisica
        	this.setIsAlta(true);
            if (this.obtieneFlash().get(
                    ParametrosFlashEnum.CLIENTE_PF_BEAN.getParamFlash()) != null) {
                this.cliente = (ClienteBean) this.obtieneFlash().get(
                        ParametrosFlashEnum.CLIENTE_PF_BEAN.getParamFlash());
            } else if (this.obtieneFlash().get(
                    ParametrosFlashEnum.CLIENTE_PM_BEAN.getParamFlash()) != null) {
                this.cliente = (ClienteBean) this.obtieneFlash().get(
                        ParametrosFlashEnum.CLIENTE_PM_BEAN.getParamFlash());
            }
            // Recuperamos los datos del cliente

            this.idInternaPersona = this.cliente.getIdInterna();

            // Con esta variable sabemos si venimos de una modificacion de
            // cliente o de un alta
            // En caso de alta
            if (!modificacionCliente) {
                this.creaDocumentosIniciales();
            }// Caso modificacion
            else {
                this.documentosConsultados = this.consultaListaDocumentos
                        .ejectuarTRN(this.idInternaPersona, true);

                this.modificaDocumentos();
            }

        }
        // En este caso venimos de la ficha de cliente o en su defecto de la
        // propia vista de documentos
        else if (this.obtieneFlash().get(
                ParametrosFlashEnum.CLIENTE.getParamFlash()) != null) {
        	this.setIsAlta(false);
            this.cliente = (ClienteBean) this.obtieneFlash().get(
                    ParametrosFlashEnum.CLIENTE.getParamFlash());
            this.idInternaPersona = cliente.getIdInterna();
            LOGGER.debug("this.idInternaPersona from CLIENTE-> "
                    + this.idInternaPersona);
            this.documentosConsultados = this.consultaListaDocumentos
                    .ejectuarTRN(this.idInternaPersona, true);
        }

        // En caso de volver a la ficha de cliente
        if (destino == null || NavegacionEnum.FICHA_CLIENTE.equals(destino)) {
            if(modificacionCliente==null){
                this.valorBotonVolver = DocumentosPersonasController.CTE_CANCELAR;
            }
            //En caso de modificacion
            else if(modificacionCliente){
                this.valorBotonVolver = DocumentosPersonasController.CTE_VOLVER_FICHA;
            }//Caso de alta
            else{
                this.valorBotonVolver = DocumentosPersonasController.CTE_IR_FICHA;
            }
        } else {
            this.valorBotonVolver = DocumentosPersonasController.CTE_VOLVER_RELACION;
        }

        // Hacemos un respaldo de la lista de bienes
        backUpUtils
                .respaldaArray((ArrayList<DocumentoPersonaBean>) this.documentosConsultados);

        // Recuperamos el flash por si venimos de la propia ventana y se han
        // producido errores
        if (this.obtieneFlash().get(
                ParametrosFlashEnum.ERROR_DOCUMENTOS.getParamFlash()) != null) {
            this.errorDocumentos = (Boolean) this.obtieneFlash().get(
                    ParametrosFlashEnum.ERROR_DOCUMENTOS.getParamFlash());
            LOGGER.debug("this.errorDocumentos-> " + this.errorDocumentos);
           if (this.obtieneFlash()
                    .get(ParametrosFlashEnum.MODIFICACION_DOCUMENTOS
                            .getParamFlash()) != null) {
                Integer modificacionDocumentos = (Integer) this.obtieneFlash()
                        .get(ParametrosFlashEnum.MODIFICACION_DOCUMENTOS
                                .getParamFlash());
                LOGGER.debug("modificacionDocumentos-> "
                        + modificacionDocumentos);
                if (modificacionDocumentos == DocumentosPersonasController.ALTA_DOCUMENTOS) {
                    RequestContext.getCurrentInstance().execute(
                            "PF('dlgAlta').show()");
                } else if (modificacionDocumentos == DocumentosPersonasController.BAJA_DOCUMENTOS) {
                    RequestContext.getCurrentInstance().execute(
                            "PF('dlgEliminar').show()");
                } else if (modificacionDocumentos == DocumentosPersonasController.MODIFICACION_DOCUMENTOS) {
                    RequestContext.getCurrentInstance().execute(
                            "PF('dlgModificacion').show()");
                }
            }
        }
    }

    private void modificaDocumentos() {
        // debemos analizar si algun comprobante ha cambiado con respecto los
        // que el cliente ya tenia dados de alta
        // Se obtiene el tipo de identificacion
        String tipoDocumentoIdentificacion = ConstantesFuncionales
                .getRelacionIdentificacionDocumento().get(
                        this.cliente.getTipoIdentificacion());
        Boolean cambioDocumentoIdentificacion = true;
        
        if(tipoDocumentoIdentificacion!=null){
            for (DocumentoPersonaBean documento : this.documentosConsultados) {
                if (tipoDocumentoIdentificacion.equals(documento.getTipo())) {
                    cambioDocumentoIdentificacion = false;
                }
            }
            // Creamos el nuevo documento de identificacion
            if (cambioDocumentoIdentificacion) {
                // Crear documento identificacion
                DocumentoPersonaBean documentoIdentificacion = new DocumentoPersonaBean();
                if (StringUtils.isNotBlank(tipoDocumentoIdentificacion)) {
                    documentoIdentificacion.setTipo(tipoDocumentoIdentificacion);
                    CatalogoBean catalogo = catalogoUtils.getCatalogoBean(
                            CatalogoEnum.TP_DOC, tipoDocumentoIdentificacion);
    
                    documentoIdentificacion.setTipoDesc(catalogo.getDescripcionL());
                    this.documentosNuevos.add(documentoIdentificacion);
                }
            }
        }
        String tipoDocumentoDomicilio = StringUtils.EMPTY;
        String descripcionDocumentoDomicilio = StringUtils.EMPTY;
        // Se obtiene el tipo de documento de domicilio
        if (this.cliente.getDomicilios() != null
                && this.cliente.getDomicilios().size() > 0) {
            tipoDocumentoDomicilio = this.cliente.getDomicilios().get(0)
                    .getComprobanteDomicilio();
            descripcionDocumentoDomicilio = this.cliente.getDomicilios().get(0)
                    .getDescripcion();
        }

        Boolean cambioDocumentoDomicilio = true;
        if(tipoDocumentoDomicilio!=null){
            for (DocumentoPersonaBean documento : this.documentosConsultados) {
                if (tipoDocumentoDomicilio.equals(documento.getTipo())) {
                    cambioDocumentoDomicilio = false;
                    // En el caso de que se haya cambiado la descripcion pero no el
                    // tipo
                    if (!documento.getDescripcion().equals(
                            descripcionDocumentoDomicilio)) {
                        this.editaDocumentoConsultado(documento);
                        documento.setDescripcion(descripcionDocumentoDomicilio);
                    }
                }
            }
    
            // Crear documento domicilio
            if (cambioDocumentoDomicilio
                    && StringUtils.isNotBlank(tipoDocumentoDomicilio)) {
                DocumentoPersonaBean documentoDomicilio = new DocumentoPersonaBean();
                documentoDomicilio.setTipo(tipoDocumentoDomicilio);
                CatalogoBean catalogo = catalogoUtils.getCatalogoBean(
                        CatalogoEnum.TP_DOC, tipoDocumentoDomicilio);
    
                documentoDomicilio.setTipoDesc(catalogo.getDescripcionL());
                documentoDomicilio.setDescripcion(descripcionDocumentoDomicilio);
                this.documentosNuevos.add(documentoDomicilio);
            }
        }
        // Comprobamos si el cliente tiene cedula de conocimiento como documento
        // y en caso
        // contrario la incluimos

        DocumentoPersonaBean documentoCedula = new DocumentoPersonaBean();

        // Crear cedula conocimiento
        if (this.obtieneFlash().get(
                ParametrosFlashEnum.CLIENTE_PF_BEAN.getParamFlash()) != null) {
            documentoCedula
                    .setTipo(ConstantesFuncionales.CTE_CEDULA_PERSONA_FISICA);
            CatalogoBean catalogo = catalogoUtils.getCatalogoBean(
                    CatalogoEnum.TP_DOC,
                    ConstantesFuncionales.CTE_CEDULA_PERSONA_FISICA);
            documentoCedula.setTipoDesc(catalogo.getDescripcionL());
        } else if (this.obtieneFlash().get(
                ParametrosFlashEnum.CLIENTE_PM_BEAN.getParamFlash()) != null) {
            documentoCedula
                    .setTipo(ConstantesFuncionales.CTE_CEDULA_PERSONA_MORAL);
            CatalogoBean catalogo = catalogoUtils.getCatalogoBean(
                    CatalogoEnum.TP_DOC,
                    ConstantesFuncionales.CTE_CEDULA_PERSONA_MORAL);
            documentoCedula.setTipoDesc(catalogo.getDescripcionL());
        }

        Boolean incluirNuevaCedulaConocimiento = true;

        for (int i = 0; i < this.documentosConsultados.size()
                && incluirNuevaCedulaConocimiento; i++) {
            if (documentoCedula.getTipo().equals(
                    this.documentosConsultados.get(i).getTipo())) {
                incluirNuevaCedulaConocimiento = false;
            }
        }

        if (incluirNuevaCedulaConocimiento) {
            this.documentosNuevos.add(documentoCedula);
        }

    }

    /**
     * @return List<CatalogoBean> utilizado para cargar el autocomplete de tipos de documentos
     *  filtrando el catalogo correspondiente
     *         
     */
    public List<CatalogoBean> obtenerTiposDocumentos(String query) {
        LOGGER.debug("Obtenemos los valores de combo de identificaciones: ENTRADA");
        List<CatalogoBean> resultado = new ArrayList<>();
        List<CatalogoBean> resultadoBusqueda = catalogoUtils.getCatalogo(CatalogoEnum.TP_DOC);
        
        for (int i=0;i<resultadoBusqueda.size();i++){
            try{
                if(this.cliente instanceof ClientePersonaFisicaBean){
                    if (!ConstantesFuncionales.CTE_CEDULA_PERSONA_MORAL.equals(resultadoBusqueda.get(i).getClaveFila())){
                        resultado.add(resultadoBusqueda.get(i));
                    }
                }else {
                    if(!ConstantesFuncionales.CTE_CEDULA_PERSONA_FISICA.equals(resultadoBusqueda.get(i).getClaveFila())){
                        resultado.add(resultadoBusqueda.get(i));
                    }
                }
            }catch(NumberFormatException nfe){
                LOGGER.debug("Encontrado un registro con valor no numerico: "+resultadoBusqueda.get(i).getClaveFila()+ "no lo tratamos",nfe);
            }
        }
        LOGGER.debug("Obtenemos los valores de combo de identificaciones: SALIDA");
        
        List<CatalogoBean>lst = new ArrayList<CatalogoBean>();
        CatalogoBean est = null;
        
        for(int i=0 ; i<resultado.size() ; i++){
            est = resultado.get(i);
            if( est.getDescripcionL().contains(query.toUpperCase()) && !lst.contains(est)){
                lst.add(est);
                if(lst.size()>=5)break;
                
            }
        }
        
        return lst;
    }
    
    public CatalogoBean getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(CatalogoBean tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public List<DocumentoPersonaBean> getDocumentosNuevos() {
        return documentosNuevos;
    }

    public void setDocumentosNuevos(List<DocumentoPersonaBean> documentosNuevos) {
        this.documentosNuevos = documentosNuevos;
    }

    public List<DocumentoPersonaBean> getDocumentosConsultados() {
        return documentosConsultados;
    }

    public void setDocumentosConsultados(
            List<DocumentoPersonaBean> documentosConsultados) {
        this.documentosConsultados = documentosConsultados;
    }

    public ClientePersonaFisicaBean getClientePersonaFisica() {
        return clientePersonaFisica;
    }

    public void setClientePersonaFisica(
            ClientePersonaFisicaBean clientePersonaFisica) {
        this.clientePersonaFisica = clientePersonaFisica;
    }

    public ClientePersonaMoralBean getClientePersonaMoral() {
        return clientePersonaMoral;
    }

    public void setClientePersonaMoral(
            ClientePersonaMoralBean clientePersonaMoral) {
        this.clientePersonaMoral = clientePersonaMoral;
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

    public ClienteBean getCliente() {
        return cliente;
    }

    public void setCliente(ClienteBean cliente) {
        this.cliente = cliente;
    }

    public Integer getDocumentosEliminados() {
        return documentosEliminados;
    }

    public void setDocumentosEliminados(Integer documentosEliminados) {
        this.documentosEliminados = documentosEliminados;
    }

    public String getValorBotonVolver() {
        return valorBotonVolver;
    }

    public void setValorBotonVolver(String valorBotonVolver) {
        this.valorBotonVolver = valorBotonVolver;
    }
    
    public boolean getIsAlta(){
    	return isAlta;
    }
    
    public void setIsAlta(boolean isAlta){
    	this.isAlta = isAlta;
    }
    
    public String getEstadoCivilCotitular(String estadoCivilCotitular, ClientePersonaFisicaBean cotitular){
    	try {
            estadoCivilCotitular = catalogoUtils.getCatalogoBean(
                    CatalogoEnum.TP_EST_CIVIL_INDV,
                    cotitular.getEstadoCivil()).getDescripcionL();
            return estadoCivilCotitular;
        } catch (Exception e) {
            estadoCivilCotitular = StringUtils.EMPTY;
            return estadoCivilCotitular;
        }
    }
    
    public String getOcupacionCotitular(String ocupacionCotitular, ClientePersonaFisicaBean cotitular){
    	try {
            ocupacionCotitular = catalogoUtils.getCatalogoBean(
                    CatalogoEnum.TP_CNO_INDV, cotitular.getCno())
                    .getDescripcionL();
            return ocupacionCotitular;
        } catch (Exception e) {
            ocupacionCotitular = StringUtils.EMPTY;
            return ocupacionCotitular;
        }
    }
    
    public String getSexoCotitular(String sexoCotitular,ClientePersonaFisicaBean cotitular){
    	if("H".equals(cotitular.getSexo())){
    		sexoCotitular = "HOMBRE";
    	}else{
    		sexoCotitular = "MUJER";
    	}
    	return sexoCotitular;
    }
    
    public String getCnaeCotitular(String cnaeCotitular, ClientePersonaFisicaBean cotitular){
    	try {
            cnaeCotitular = catalogoUtils.getCatalogoBean(
                    CatalogoEnum.TP_CNAE_PERS,
                    cotitular.getCnae()).getDescripcionL();
            return cnaeCotitular;
        } catch (Exception e) {
            cnaeCotitular = StringUtils.EMPTY;
            return cnaeCotitular;
        }
    }
    
    public String getLugarNacimiento(String lugarNacimientoCotitular, ClientePersonaFisicaBean cotitular){
    	 //En caso de que sea codificada
        if (cotitular.getMunicipioCatGeo() != null
                && StringUtils.isNotBlank(cotitular
                        .getMunicipioCatGeo().getCodigoPostal())) {
            return lugarNacimientoCotitular = StringUtils
                    .defaultString(cotitular.getMunicipioCatGeo()
                            .getNombreMunicipio());
        }
        // En caso de no codificada
        else {
            return lugarNacimientoCotitular = StringUtils
                    .defaultString(cotitular.getMunicipioCatGeo()
                            .getNombreMunicipio());
        }
    }
    
    public String getEstadoNacimiento(String estadoNacimientoCotitular, ClientePersonaFisicaBean cotitular){
   	 //En caso de que sea codificada
       if (cotitular.getMunicipioCatGeo() != null
               && StringUtils.isNotBlank(cotitular
                       .getMunicipioCatGeo().getCodigoPostal())) {          
           return estadoNacimientoCotitular = StringUtils
                   .defaultString(cotitular.getMunicipioCatGeo()
                           .getNombreComunidadAutonoma());
       }
       // En caso de no codificada
       else {
          return estadoNacimientoCotitular = StringUtils
                   .defaultString(cotitular.getEstado()
                           .getNombreProvincia());       
       }
   }
    
    public String getPaisNacimiento(String paisNacimientoCotitular, ClientePersonaFisicaBean cotitular){
   	 //En caso de que sea codificada
       if (cotitular.getMunicipioCatGeo() != null
               && StringUtils.isNotBlank(cotitular
                       .getMunicipioCatGeo().getCodigoPostal())) {
          return paisNacimientoCotitular = StringUtils
                   .defaultString(cotitular.getMunicipioCatGeo()
                           .getNombrePais());
       }
       // En caso de no codificada
       else {
           return paisNacimientoCotitular = catalogoPaises.getCatalogoDescripcion(cotitular.getPaisNacionalidad());
       }
   }
    
    public String getEstadoApoderado(String estadoCivilApoderado,ClientePersonaFisicaBean apoderado){
    	try {
            return estadoCivilApoderado = catalogoUtils.getCatalogoBean(
                    CatalogoEnum.TP_EST_CIVIL_INDV,
                    apoderado.getEstadoCivil()).getDescripcionL();
        } catch (Exception e) {
           return estadoCivilApoderado = StringUtils.EMPTY;
        }
    }
    
    public String getOcupacionApoderado(String ocupacionApoderado,ClientePersonaFisicaBean apoderado){
    	try {
            return ocupacionApoderado = catalogoUtils.getCatalogoBean(
                    CatalogoEnum.TP_CNO_INDV, apoderado.getCno())
                    .getDescripcionL();
        } catch (Exception e) {
            return ocupacionApoderado = StringUtils.EMPTY;
        }
    }
    
    public String getCnaeApoderado(String cnaeApoderado,ClientePersonaFisicaBean apoderado){
    	try {
            return cnaeApoderado = catalogoUtils.getCatalogoBean(
                    CatalogoEnum.TP_CNAE_PERS,
                    apoderado.getCnae()).getDescripcionL();
        } catch (Exception e) {
            return cnaeApoderado = StringUtils.EMPTY;
        }
    	
    }
    
    public String getEdad(Integer edadApoderado,ClientePersonaFisicaBean apoderado){
    	//Calculamos la edad
        Calendar fechaNacimientoApoderado = Calendar.getInstance();  
        fechaNacimientoApoderado.setTime(apoderado.getFechaNacimiento());  
        Calendar today = Calendar.getInstance();  
        edadApoderado = today.get(Calendar.YEAR) - fechaNacimientoApoderado.get(Calendar.YEAR);  
        if (today.get(Calendar.MONTH) < fechaNacimientoApoderado.get(Calendar.MONTH)) {
          edadApoderado--;  
        } else if (today.get(Calendar.MONTH) == fechaNacimientoApoderado.get(Calendar.MONTH)
            && today.get(Calendar.DAY_OF_MONTH) < fechaNacimientoApoderado.get(Calendar.DAY_OF_MONTH)) {
          edadApoderado--;  
        }
        return edadApoderado.toString();
    }
    
    
    

}
