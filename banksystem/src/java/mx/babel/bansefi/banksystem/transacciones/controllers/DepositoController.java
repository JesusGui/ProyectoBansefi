package mx.babel.bansefi.banksystem.transacciones.controllers;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.backends.ConsultaCuentaBackend;
import mx.babel.bansefi.banksystem.base.backends.anotaciones.ConsultaListaAnotacionesBackEnd;
import mx.babel.bansefi.banksystem.base.backends.consultatitular.ConsultaTitularBackend;
import mx.babel.bansefi.banksystem.base.backends.consultatitular.ConsultaTitularRes;
import mx.babel.bansefi.banksystem.base.beans.domain.AnotacionBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaFisicaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.BusquedaEnum;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;
import mx.babel.bansefi.banksystem.base.utils.NumberToLetterConverter;
import mx.babel.bansefi.banksystem.base.utils.PdfUtils;
import mx.babel.bansefi.banksystem.transacciones.backends.DepositoBackend;
import mx.babel.bansefi.banksystem.transacciones.beans.DepositoBean;
import mx.babel.bansefi.banksystem.transacciones.beans.DepositoRes;
import mx.babel.bansefi.banksystem.transacciones.enums.ConceptoDepositoEnum;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Se encarga de la logica del deposito de efectivo.
 *
 * @author luis.gcastellano
 */
@ManagedBean(name = "depositoController")
@ViewScoped
@Component
@Scope("view")
public class DepositoController implements Serializable {

    private static final long serialVersionUID = -4246884307356741848L;

    private static final String CTE_ESPACIO = " ";
    private static final String CTE_CERO = "0";
    private static final double CTE_MOSTRAR_UNIDADES_MILLON = 1000000;
    private static final String CTE_SI = "Si";
    private static final String CTE_NO = "No";
    private static final int NUM_ANOTACIONES = 6;
    

 	@Autowired
    private ConsultaCuentaBackend consultaCuentaBackend;

    @Autowired
    private ContextoUtils contextoUtils;

    @Autowired
    private ConsultaTitularBackend consultaTitular;

    @Autowired
    private DepositoBackend depositoBackend;

    @Autowired
    private PdfUtils pdfUtils;

    @Autowired
    ManagedBeanStateRecover managedBeanStateRecover;

    @ManagedProperty(value = "deposito")
    private DepositoBean deposito;

    @Autowired
	CatalogoUtils catalogoUtils;

    @Autowired
    ConsultaListaAnotacionesBackEnd consultaAnotacionescuentaBackEnd;

    // Controla la que se muestre el dialogo de confirmación
    private boolean confirmarDeposito;

    private boolean muestraTitular;

    private boolean muestraTitularError;

    private boolean cuentaInvalida;

    private boolean muestraDialogo;

    private CuentaBean cuentaBean;

    private String estatus;

    private String fechaMinima = ConstantesFuncionales.MIN_FECHA_INICIO;

    private Date fechaContable;

    private DepositoRes respuesta;

    private SimpleDateFormat formatter = new SimpleDateFormat(ConstantesFuncionales.GENERAL_DATE_FORMATTER_INT);

    private boolean esDepositoIPF = false;
    
    private int anotacionesVisibles;

    /**
     * Enum para definir a donde se redireccionará al seleccionar un objeto de
     * la tabla de resultados.
     */
    private NavegacionEnum destino;
    private Object destinoController;

    /**
     * Constructor.
     */
    public DepositoController() {
        super();
    }

    /**
     * @return Metodo utilizado para acceder al paso 1 del alta de Clientes por
     *         primera vez.
     */
    public String inicio() {
        return NavegacionEnum.REALIZAR_DEPOSITO.getRuta();
    }

    /**
     * Se inicializan las variables de la vista.
     */
    @PostConstruct
    public void init() {
        if (this.obtieneFlash().get(ParametrosFlashEnum.ES_DEPOSITO_IPF.getParamFlash()) != null
              && ((Boolean)this.obtieneFlash().get(ParametrosFlashEnum.ES_DEPOSITO_IPF.getParamFlash())) == true
              && this.obtieneFlash().get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash()) != null) {
            //El deposito se realizara para permitir un alta de IPF
            inicializaDatosFlujo();
            esDepositoIPF = true;
            final BigDecimal importeMinimoIPF = (BigDecimal)this.obtieneFlash().get(ParametrosFlashEnum.IMPORTE_MINIMO_IPF.getParamFlash());
            final ClientePersonaFisicaBean titular = (ClientePersonaFisicaBean)this.obtieneFlash().get(ParametrosFlashEnum.TITULAR_CUENTA.getParamFlash());
            this.deposito.setCuentaDeposito((Long)this.obtieneFlash().get(ParametrosFlashEnum.NUMERO_CUENTA.getParamFlash()));
            this.buscaTitular();
            this.deposito.setIdentificacionClienteDeposito(ConstantesFuncionales.getRelacionIdentificacionDocumento().get(titular.getTipoIdentificacion()));
            this.deposito.setNumeroIdentificacionClienteDeposito(titular.getNumIdentificacion());
            //Tenemos que hacer la caca de restar pasta y minimo
            this.deposito.setImporteDeposito(importeMinimoIPF.doubleValue());
            //¿? que onda con los millones
            //this.mostrarUnidadesMillon();
            //this.deposito.setUnidadesMillon(unidadesMillon);
            this.deposito.setOrdenanteDeposito(titular.getNombre());
            this.destino = managedBeanStateRecover.getDestino();
            this.destinoController = managedBeanStateRecover.getController();
        }else{
        	 // Si el flash tiene valor lo recuperamos
            if (this.obtieneFlash().get(ParametrosFlashEnum.DEPOSITO.getParamFlash()) != null) {
                this.deposito = (DepositoBean) this.obtieneFlash().get(
                        ParametrosFlashEnum.DEPOSITO.getParamFlash());
                if (this.obtieneFlash().get(ParametrosFlashEnum.OBJETO_ENTRADA_PLANTILLA.getParamFlash()) != null) {
                	respuesta = (DepositoRes)this.obtieneFlash().get(ParametrosFlashEnum.OBJETO_ENTRADA_PLANTILLA.getParamFlash());
                	cuentaBean = consultaCuentaBackend.ejecutarTRN(this.deposito.getCuentaDeposito());
                	this.generaPlantillaDeposito(respuesta);
                }
        	} else if(this.obtieneFlash().get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash()) != null) {
        		this.deposito = (DepositoBean) this.obtieneFlash().get(
                        ParametrosFlashEnum.DEPOSITO.getParamFlash());
                managedBeanStateRecover.recuperaController(this);
            } else {
            	inicializaDatosFlujo();
            }

          //SE OBTIENE EL VALOR DE LA BUSQUEDA DE CUENTAS
        	if(this.obtieneFlash().get(ParametrosFlashEnum.NUMERO_CUENTA.getParamFlash()) != null){
        		managedBeanStateRecover.recuperaController(this);
        		this.deposito.setCuentaDeposito((Long)this.obtieneFlash().get(ParametrosFlashEnum.NUMERO_CUENTA.getParamFlash()));
        		this.muestraTitular = true;
        		this.deposito.setTitular((String)this.obtieneFlash().get(ParametrosFlashEnum.TITULAR_CUENTA.getParamFlash()));
        		this.deposito.setNivelCuenta((String)this.obtieneFlash().get(ParametrosFlashEnum.NIVEL_CUENTA.getParamFlash()));
        		this.deposito.setAnotaciones(consultaAnotacionescuentaBackEnd.ejecutarTRN(this.deposito.getCuentaDeposito(),true));
        		this.establecerAnotacionesVisibles();
        	}

        	if(this.obtieneFlash().get(ParametrosFlashEnum.ES_DEPOSITO_IPF.getParamFlash()) != null
                    && ((Boolean)this.obtieneFlash().get(ParametrosFlashEnum.ES_DEPOSITO_IPF.getParamFlash())) == true){
        	    this.esDepositoIPF = true;
                this.destino = managedBeanStateRecover.getDestino();
                this.destinoController = managedBeanStateRecover.getController();
        	}

        }
    }

    /**
     *
     */
    private void inicializaDatosFlujo() {
        this.deposito = new DepositoBean();
        this.cuentaBean = new CuentaBean();
        this.muestraDialogo = false;
        this.deposito.setMostrarUnidadesMillon(false);
        this.deposito.setCuentaPuenteDeposito(false);
        this.deposito.setImprimirSaldoDeposito(true);
        this.deposito.setFechaValorDeposito(contextoUtils.getFechaContableActual());
        this.confirmarDeposito = false;
        this.muestraTitular = false;
        this.muestraTitularError = false;
        this.deposito.setOperacionDeposito("010002");
        this.calculaConcepto();
        this.obtieneFlash().put(ParametrosFlashEnum.DEPOSITO.getParamFlash(),
                this.deposito);
    }

    /**
     * Al seleccionar una operación, calcula el concepto.
     */
    public void calculaConcepto() {
        String concepto = "";
        for (final ConceptoDepositoEnum conceptoEnum : ConceptoDepositoEnum
                .values()) {
            if (conceptoEnum.getCodigo().equals(
                    this.deposito.getOperacionDeposito())) {
                concepto = conceptoEnum.getConcepto();
            }
        }
        this.deposito.setConceptoDeposito(concepto);
        this.cambiarPuente();
    }

    public void mostrarDialogo() {
    	if(this.validaCuentaPuente()){
    		if (this.muestraTitular) {
			    this.muestraDialogo = true;
			} else {
			    this.muestraDialogo = false;
			}
    	}
    	else{
    		this.muestraDialogo = false;
    		FacesContext.getCurrentInstance().addMessage("operacionDeposito", 
    				new FacesMessage(FacesMessage.SEVERITY_ERROR,
    						"ERROR",
    						"Operación y Cuenta puente no concuerdan"));
    	}
    }

    /**
     * Se encarga de traducir la descripcion de la operacion en caso de que sea
     * una de las del combo.
     */
    public void calculaOperacionDescripcion() {

        for (final ConceptoDepositoEnum conceptoEnum : ConceptoDepositoEnum
                .values()) {
            if (conceptoEnum.getCodigo().equals(
                    this.deposito.getOperacionDeposito())) {
                this.deposito.setOperacionDepositoDescripcion(conceptoEnum
                        .getConcepto());
            }
        }
    }

    /**
     * Determina si se deben mostrar los campos para insertar las unidades de
     * millon.
     */
    public void mostrarUnidadesMillon() {
        if (this.deposito.getImporteDeposito() != null
                && this.deposito.getImporteDeposito() >= DepositoController.CTE_MOSTRAR_UNIDADES_MILLON) {
            this.deposito.setMostrarUnidadesMillon(true);
        } else {
        	this.deposito.setUnidadesMillon(null);
            this.deposito.setMostrarUnidadesMillon(false);
        }
    }

    /**
     * Realiza el deposito haciendo la llamada al WS Tambien se encarga de
     * consultar los saldos de las cuentas.
     *
     * @return Un String con la ruta de la pagina de resumen.
     */
    public String continuarDeposito() {
        String retorno = null;

        this.calculaOperacionDescripcion();

        respuesta = this.depositoBackend.ejecutarWS(this.deposito);

        if (respuesta != null && DepositoController.CTE_CERO.equals(respuesta.getEstatus())) {
        	this.obtieneFlash().put(ParametrosFlashEnum.DEPOSITO.getParamFlash(),
                    this.deposito);
            this.obtieneFlash().put(ParametrosFlashEnum.OBJETO_ENTRADA_PLANTILLA.getParamFlash(),respuesta);
            retorno = NavegacionEnum.RESUMEN_DEPOSITO.getRuta();

            if(this.destino != null && this.destinoController != null){
                this.obtieneFlash().put(ParametrosFlashEnum.NAVEGACION_BUSQUEDA.getParamFlash(), this.destino);
                this.obtieneFlash().put(ParametrosFlashEnum.CONTROLLER_DESTINO.getParamFlash(), this.destinoController);
                this.obtieneFlash().put(ParametrosFlashEnum.ES_DEPOSITO_IPF.getParamFlash(), this.esDepositoIPF);
            }

        }
        //SE MUESTRA DIALOGO SOBRE ERROR EN LA CUENTA
        else {
        	this.estatus = this.tipoEstatus(respuesta);
        	switch(this.estatus){
        		case "BLOQUEADA": 		RequestContext.getCurrentInstance().execute("PF('dlgConfirmarDeposito').hide()");
        								RequestContext.getCurrentInstance().execute("PF('dlgCuentaError').show()");
        						  		break;
        		case "CERRADA" : 		RequestContext.getCurrentInstance().execute("PF('dlgConfirmarDeposito').hide()");
        			              		RequestContext.getCurrentInstance().execute("PF('dlgCuentaError').show()");
        			              		break;
        		case "INCORRECTA" : 	RequestContext.getCurrentInstance().execute("PF('dlgConfirmarDeposito').hide()");
        			                	RequestContext.getCurrentInstance().execute("PF('dlgAvisoCuentaIncorrecta').show()");
        			                	break;
        		case "INVALIDA" :   	RequestContext.getCurrentInstance().execute("PF('dlgConfirmarDeposito').hide()");
        								RequestContext.getCurrentInstance().execute("PF('dlgAvisoCuentaInvalida').show()");
        								break;
        		case "INACTIVA" :   	RequestContext.getCurrentInstance().execute("PF('dlgConfirmarDeposito').hide()");
        								RequestContext.getCurrentInstance().execute("PF('dlgAvisoCuentaInactiva').show()");
        							   	break;
        		case "IMPERCEPTIBLE" :	RequestContext.getCurrentInstance().execute("PF('dlgConfirmarDeposito').hide()");
										RequestContext.getCurrentInstance().execute("PF('dlgAvisoCuentaNoVista').show()");
										break;
        	}

        }

        return retorno;
    }

    public String tipoEstatus(final DepositoRes respuesta){
    	switch(respuesta.getCodigo().trim()){
			case "ARQE159" : return "BLOQUEADA";
			case "ARQE135" : return "INCORRECTA";
			case "ARQE114" : return "INVALIDA";
			case "WNOT8545" : return "CERRADA";
			case "DOME0004" : return "INACTIVA";
			case "CHEE021" : return "IMPERCEPTIBLE";
			case "NTFE0092" : return respuesta.getMensaje();
			default : return "";
    	}
    }

    /**
     * Método para generar el comprobante de depósito
     * @param datos
     * @throws ControlableException
     * @throws NoControlableException
     */
    public void generaPlantillaDeposito(final DepositoRes datos)
            throws ControlableException, NoControlableException {

    	final StringBuffer nombrePdf = new StringBuffer(this.contextoUtils.getId());
    	final Integer fecha = Integer.parseInt(datos.getFechaOperacion().replaceAll("-", ""));
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("tipoCuenta", this.cuentaBean.getTipoCuenta());
        try {
        	final Date date = formatter.parse(fecha.toString());
			params.put("fecha", date);
		} catch (final ParseException e) {
			params.put("fecha", new Date());
		}
		params.put("hora", datos.getHoraOperacion().replaceAll("\\.", ":"));
        params.put("plaza", this.buscarPlaza(datos.getPlaza()));
        params.put("oficina", datos.getSucursal());
        params.put("nombreTitular", this.deposito.getTitular());
        params.put("clabe", this.creaClabe(this.contextoUtils.getEntidad(),
                datos.getPlaza(), this.deposito.getCuentaDeposito().toString(),
                datos.getDigito()));
        params.put("importeEfectivo", this.deposito.getImporteDeposito());

        try{
        	params.put("importeLetra", (NumberToLetterConverter
                    .convertirImporteAImporteEnletra(this.deposito
                            .getImporteDeposito())));
        }catch(final NumberFormatException nfe){
        	params.put("importeLetra", "No es posible realizar la conversión.");
        }

        params.put("total", this.deposito.getImporteDeposito());
        params.put("operacion", datos.getMovimiento());
        params.put("terminal", datos.getTerminal());
        params.put("fechaOperacion", datos.getFechaOperacion().replaceAll("-", ""));
        params.put("horaOperacion", datos.getHoraOperacion().replaceAll("\\.", ""));

        pdfUtils.generaPdf("depositoReporte.jrxml", params, null, null,
                nombrePdf.toString(), null);
    }

    /**7
     * Método que busca el titular de la cuenta
     * @throws ControlableException
     * @throws NoControlableException
     */
    public void buscaTitular() throws ControlableException,
            NoControlableException {

        this.setMuestraTitular(false);
        this.setMuestraTitularError(false);

        final ConsultaTitularRes respuesta = this.consultaTitular
                .ejecutarWS(this.deposito.getCuentaDeposito().toString());

        if (respuesta != null && DepositoController.CTE_CERO.equals(respuesta.getEstatus())) {
            this.muestraTitular = true;
            this.deposito.setTitular(respuesta.getNombre());
            this.deposito.setNivelCuenta(respuesta.getNivelCuenta());
            this.deposito.setAnotaciones(consultaAnotacionescuentaBackEnd.ejecutarTRN(this.deposito.getCuentaDeposito(),true));
            this.establecerAnotacionesVisibles();
        } else {

            this.muestraTitularError = true;
        }
    }

    /**
     *
     * @param clave de la plaza a buscar
     * @return Descripcion de la plaza
     */
    public String buscarPlaza(final String clave){
    	String descripcion = clave;
    	final List<CatalogoBean> catalogo = catalogoUtils.getCatalogo(CatalogoEnum.TP_PLAZA_BANCARIA);

    	for (final CatalogoBean elemento : catalogo) {
			if(elemento.getClaveFila().equals(clave.trim())){
				descripcion = elemento.getDescripcionL();
			}
		}

    	return descripcion;
    }

    /**
     *
     * @param entidad
     * @param plaza
     * @param cuenta
     * @param digito
     * @return CLABE interbancaria
     */
    public String creaClabe(final String entidad, final String plaza, String cuenta,
            final String digito) {

        cuenta = org.apache.commons.lang.StringUtils.leftPad(cuenta, 10, DepositoController.CTE_CERO);

        final StringBuffer clabeBuffer =
				new StringBuffer().append(entidad.substring(1)).append(plaza.trim())
						.append(DepositoController.CTE_ESPACIO)
						.append(DepositoController.CTE_CERO)
						.append(cuenta)
						.append(DepositoController.CTE_ESPACIO)
						.append(digito);

        return clabeBuffer.toString();
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
     * Obtiene la dirección del Dashboard
     * @return
     */
    public String irMenu(){
    	return NavegacionEnum.INICIO.getRuta();
    }

    /**
     * Obtiene la dirección para realizar un
     * depósito
     * @return
     */
    public String irDeposito(){
    	return NavegacionEnum.REALIZAR_DEPOSITO.getRuta();
    }

    /**
     * Obtiene la dirección del alta de IPF
     * @return
     */
    public String irAltaIPF(){
        managedBeanStateRecover.finNavegacion(destinoController);
        return destino.getRuta();
    }

    /**
     * Función empleada para redireccionar a la búsqueda de cuentas
     * @return ruta a la búsqueda de cuentas
     */
    public String buscarCuentas() {
    	managedBeanStateRecover.enviaController(this, NavegacionEnum.REALIZAR_DEPOSITO);
    	obtieneFlash().put(ParametrosFlashEnum.TIPO_BUSQUEDA.getParamFlash(), BusquedaEnum.CUENTAS.getBusquedaValor());
		return NavegacionEnum.BUSQUEDA.getRuta();
	}

    /**
     * Método para validar la cuenta puente
     */
    public boolean validaCuentaPuente(){
    	if(this.deposito.isCuentaPuenteDeposito() &&
    			this.deposito.getOperacionDeposito().equals("010002")){
    		return false;
    	}

    	if(!this.deposito.isCuentaPuenteDeposito() &&
    			this.deposito.getOperacionDeposito().equals("990002")){
    		return false;
    	}

    	return true;
    }
    
    public void cambiarPuente(){
    	if("990002".equals(this.deposito.getOperacionDeposito())){
    		this.deposito.setCuentaPuenteDeposito(true);
    	}else{
    		this.deposito.setCuentaPuenteDeposito(false);
    	}
    	
    }
    
    public void cambiarOperacion(){
    	if(this.deposito.isCuentaPuenteDeposito()){
    		this.deposito.setOperacionDeposito("990002");
    	}else{
    		this.deposito.setOperacionDeposito("010002");
    	}
    	this.calculaConcepto();
    }
    
    /**
	 * Método que consulta el detalle de la anotacion seleccionada
	 *
	 * @return La URL a la que redireccionará
	 */
	public String detalleAnotacion(final AnotacionBean anotacionBean) {
		managedBeanStateRecover.enviaController(this, NavegacionEnum.REALIZAR_DEPOSITO);
		obtieneFlash().put(ParametrosFlashEnum.ANOTACION_BEAN.getParamFlash(),anotacionBean);
		return NavegacionEnum.DETALLE_ANOTACIONES.getRuta();
	}
	
	public void establecerAnotacionesVisibles(){
		int numAnotacionesVisibles = 0;
		if(this.deposito.getAnotaciones() != null){
			if(this.deposito.getAnotaciones().size() > NUM_ANOTACIONES){
				numAnotacionesVisibles = DepositoController.NUM_ANOTACIONES;
			}else{
				numAnotacionesVisibles = this.deposito.getAnotaciones().size();
			}
		}
		this.anotacionesVisibles = numAnotacionesVisibles;
	}
	
	public int getAnotacionesVisibles(){
		return anotacionesVisibles;
	}
	
	public void setAnotacionesVisible(final int anotacionesVisibles){
		this.anotacionesVisibles = anotacionesVisibles;
	}
	
	public String getTipoAnotacion(final String clave){
		try{
			return catalogoUtils.getCatalogoBean(CatalogoEnum.TIPO_ANOTACION,
					clave).getDescripcionL();
			
		}catch(final ControlableException ce){
			return "";			
		}
	}
	
	public String obtenerSubCodigoAnotacion(final String clave){
		try{
			return catalogoUtils.getCatalogoBean(CatalogoEnum.TP_ANTCN, clave).getDescripcionL();
		}catch(final ControlableException ce){
			return "";			
		}
	}
	
	public void mostrarTodasAnotaciones(){
		this.setAnotacionesVisible(this.deposito.getAnotaciones().size());
	}

    public String getFechaMinima() {
		return fechaMinima;
	}

	public void setFechaMinima(final String fechaMinima) {
		this.fechaMinima = fechaMinima;
	}

	public DepositoBean getDeposito() {
        return this.deposito;
    }

    public void setDeposito(final DepositoBean deposito) {
        this.deposito = deposito;
    }

    public String isImprimirSaldoDepositoString() {
        return this.converterBoolean2String(this.deposito
                .isImprimirSaldoDeposito());
    }

    public String isCuentaPuenteDepositoString() {
        return this.converterBoolean2String(this.deposito
                .isCuentaPuenteDeposito());
    }

    private String converterBoolean2String(final boolean value) {
        if (value == true) {
            return DepositoController.CTE_SI;
        } else {
            return DepositoController.CTE_NO;
        }
    }

    public boolean isConfirmarDeposito() {
        return this.confirmarDeposito;
    }

    public void setConfirmarDeposito(final boolean confirmarDeposito) {
        this.confirmarDeposito = confirmarDeposito;
    }

    public ContextoUtils getContextoUtils() {
        return this.contextoUtils;
    }

    public void setContextoUtils(final ContextoUtils contextoUtils) {
        this.contextoUtils = contextoUtils;
    }

    public boolean isMuestraTitular() {
        return this.muestraTitular;
    }

    public void setMuestraTitular(final boolean muestraTitular) {
        this.muestraTitular = muestraTitular;
    }

    public boolean isMuestraTitularError() {
        return this.muestraTitularError;
    }

    public void setMuestraTitularError(final boolean muestraTitularError) {
        this.muestraTitularError = muestraTitularError;
    }

    public boolean isMuestraDialogo() {
        return muestraDialogo;
    }

    public void setMuestraDialogo(final boolean muestraDialogo) {
        this.muestraDialogo = muestraDialogo;
    }


    public boolean isCuentaInvalida(){
    	return cuentaInvalida;
    }

    public void setCuentaInvalida(final boolean cuentaInvalida){
    	this.cuentaInvalida = cuentaInvalida;
    }


	public CuentaBean getCuentaBean() {
		return cuentaBean;
	}

	public void setCuentaBean(final CuentaBean cuentaBean) {
		this.cuentaBean = cuentaBean;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(final String estatus) {
		this.estatus = estatus;
	}

	public Date getFechaContable() {
		return fechaContable;
	}

	public void setFechaContable(final Date fechaContable) {
		this.fechaContable = fechaContable;
	}

	public DepositoRes getRespuesta() {
		return respuesta;
	}

	public SimpleDateFormat getFormatter() {
		return formatter;
	}

	public void setFormatter(final SimpleDateFormat formatter) {
		this.formatter = formatter;
	}

    /**
     * @return the esDepositoIPF
     */
    public boolean isEsDepositoIPF() {
        return esDepositoIPF;
    }

    /**
     * @param esDepositoIPF the esDepositoIPF to set
     */
    public void setEsDepositoIPF(final boolean esDepositoIPF) {
        this.esDepositoIPF = esDepositoIPF;
    }
    
    



}
