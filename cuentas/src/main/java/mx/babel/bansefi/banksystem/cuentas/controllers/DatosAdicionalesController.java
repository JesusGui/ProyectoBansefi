package mx.babel.bansefi.banksystem.cuentas.controllers;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.EstadosCuentaEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;
import mx.babel.bansefi.banksystem.base.utils.PdfUtils;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaDatosAdicionalesBackEnd;
import mx.babel.bansefi.banksystem.cuentas.backends.ModificacionDatosAdicionalesBackend;
import mx.babel.bansefi.banksystem.cuentas.beans.DatoAdicionalBean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Define las acciones que pueden realizar con Datos Adicionales
 * 
 * @author Alejandro Villegas
 * @category Controller
 */
@ManagedBean
@ViewScoped
@Component
@Scope("view")
public class DatosAdicionalesController implements Serializable {
	private static final long serialVersionUID = 544773292184482727L;
	private List<DatoAdicionalBean> datosAdicionales;
	private CuentaBean cuentaBean;
	private static final Logger LOGGER = LogManager
			.getLogger(ConsultaDatosAdicionalesBackEnd.class);
	private boolean registroGuardado;
	private boolean habilitarImpresion;
	
	/**
	 * Guarda reporte
	 */
	private boolean reimpresion;
	private String reporte;
	private List<CuentaBean> datosImpresion;
	private String nombreReporte;
	
	@Autowired
	ContextoUtils contextoUtils;
	@Autowired
	CatalogoUtils catalogoUtils;
	@Autowired
	ConsultaDatosAdicionalesBackEnd consultaDatosAdicionalesBackEnd;
	@Autowired
	ModificacionDatosAdicionalesBackend modificacionDatos;
	@Autowired
	ManagedBeanStateRecover managedBeanStateRecover;

	@Autowired
    private PdfUtils pdfUtils;
	
	@PostConstruct
	public void init() {
		this.habilitarImpresion = false;
		datosAdicionales = new ArrayList<>();
		try {
			if (getFlash().get(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash()) == null)
				throw new NoControlableException(
						"Error al llamar a TRN de consulta de datos adicionales con cuenta vacía",
						"Error al llamar a TRN de consulta de planificaciones con cuenta vacía");

			cuentaBean = (CuentaBean) getFlash().get(
					ParametrosFlashEnum.CUENTA_BEAN.getParamFlash());
			
			this.habilitarImpresion = false;

			loadData();
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
	}

	public List<DatoAdicionalBean> getDatosAdicionales() {
		return datosAdicionales;
	}

	public void setDatosAdicionales(List<DatoAdicionalBean> datosAdicionales) {
		this.datosAdicionales = datosAdicionales;
	}

	public String redirectHome() {
		List<DatoAdicionalBean> listaModificados = new ArrayList<DatoAdicionalBean>();
		
		if(!registroGuardado && 
				datosAdicionales != null  && !datosAdicionales.isEmpty()){
			listaModificados = this.datosModificados(datosAdicionales);
		}
		
		if(this.isCuentaActiva()){
			if (!listaModificados.isEmpty()) {
				for (DatoAdicionalBean elemento : datosAdicionales) {
					if(elemento.getIndicadorEstructura().trim().equals(ConstantesFuncionales.ESTR_IMPORTE)){
						if(elemento.getValor().equals("") ||
								elemento.getValor().equals("0")){
							elemento.setImporte(null);
						}
					}
				}
				RequestContext.getCurrentInstance().execute("PF('dlgVolver').show()");
				return null;
			} else {
				getFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
						cuentaBean);
				managedBeanStateRecover.enviaController(this,
						NavegacionEnum.BUSQUEDA);

				return NavegacionEnum.FICHA_CUENTA.getRuta();
			}
		} else {
			getFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
					cuentaBean);
			managedBeanStateRecover.enviaController(this,
					NavegacionEnum.BUSQUEDA);

			return NavegacionEnum.FICHA_CUENTA.getRuta();
		}
		
	}
	
	public String regresar(){
		getFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				cuentaBean);
		managedBeanStateRecover.enviaController(this,
				NavegacionEnum.BUSQUEDA);
		
		RequestContext.getCurrentInstance().execute("PF('dlgVolver').hide()");

		return NavegacionEnum.FICHA_CUENTA.getRuta();
	}

	public void save() {
		List<DatoAdicionalBean> listaModificados = new ArrayList<DatoAdicionalBean>();
		
		if(datosAdicionales != null  && !datosAdicionales.isEmpty()){
			listaModificados = this.datosModificados(datosAdicionales);
		}
		
		//NO SE MODIFICÓ EL TELÉFONO
		if(!this.telefonoModificado()){
			//SE ELIMINÓ UN IMPORTE
			if(this.eliminoImporte(this.datosAdicionales)){
				RequestContext.getCurrentInstance().execute("PF('dlgImporte').show()");
			} else {
				try {
					if (!listaModificados.isEmpty()) {
						modificacionDatos.ejecutarTRN(cuentaBean.getNumeroCuenta(),
								listaModificados);
						this.registroGuardado = true;
						this.loadData();
						RequestContext.getCurrentInstance().execute("PF('dlgModificacion').show()");
					} else {
						RequestContext.getCurrentInstance().execute("PF('dlgSinModificacion').show()");						
					}
				} catch (NoControlableException ce) {
					LOGGER.error(ce.getMessage());
				}
			}
		//SE MODIFICÓ EL TELÉFONO
		} else if(this.documentoEntregado()){
			try {
					modificacionDatos.ejecutarTRN(cuentaBean.getNumeroCuenta(),
							listaModificados);
					this.registroGuardado = true;
					//SI LA ENTIDAD ES BANSEFI SE PERMITE LA IMPRESIÓN
					if(cuentaBean.getEntidad().trim().equals("0166")){
						this.habilitarImpresion = true;
						this.generarPlantilla();
					}		
					this.loadData();
					RequestContext.getCurrentInstance().execute("PF('dlgModificacion').show()");
			} catch (NoControlableException ce) {
				LOGGER.error(ce.getMessage());
			}
		//SE MODIFICÓ EL TELÉFONO PERO NO SE HA ENTREGADO DOCUMENTO
		} else {
			RequestContext.getCurrentInstance().execute("PF('dlgTelefono').show()");
		}
	}
	
	public List<DatoAdicionalBean> datosModificados(List<DatoAdicionalBean> lista){
		List<DatoAdicionalBean> listaModificados = new ArrayList<DatoAdicionalBean>();
		if(lista != null){
			for (DatoAdicionalBean elemento : lista) {
				if(elemento.getInputSwitch() != null && 
						elemento.getIndicadorEstructura().trim().equals(ConstantesFuncionales.ESTR_INDICADOR)){
					if(!elemento.getInputSwitch()){
						elemento.setValor("NO");
					}
					if(elemento.getInputSwitch()){
						elemento.setValor("SI");
					}
				} else if(elemento.getIndicadorEstructura().trim().equals(ConstantesFuncionales.ESTR_IMPORTE)){
					if(elemento.getImporte().compareTo(BigDecimal.ZERO) == 1){
						elemento.setValor(String.valueOf(elemento.getImporte()));
					} else {
						elemento.setValor("");
					}
					
				}  else if(elemento.getIndicadorEstructura().trim().equals(ConstantesFuncionales.ESTR_TEXTO)){
					if(elemento.getOldValue() != null){
						//PARA LOS CASOS EN QUE SE HAYAN ALMACENADO MINÚSCULAS
						elemento.setOldValue(elemento.getOldValue().toUpperCase());
					}
					elemento.setValor(elemento.getValor() != null ? elemento.getValor().toUpperCase(): elemento.getValor());
				}
				
				if(elemento.getOldValue() == null && 
						elemento.getValor() != null &&
						(!elemento.getValor().trim().equals("") &&
								!elemento.getValor().equals("NO"))){
					listaModificados.add(elemento);
				} else if(elemento.getOldValue() != null && 
						!elemento.getOldValue().trim().equals(elemento.getValor().trim()) &&
						!elemento.getIndicadorEstructura().trim().equals(ConstantesFuncionales.ESTR_IMPORTE)){
					listaModificados.add(elemento);
				} else if(elemento.getOldValue() != null && 
						elemento.getIndicadorEstructura().trim().equals(ConstantesFuncionales.ESTR_IMPORTE)){
					if (BigDecimal.valueOf(Double.parseDouble(elemento.getOldValue())).
						compareTo(elemento.getImporte()) != 0 ) {
						//SE IMPLEMENTA LOCAL EUROPEO (ALEMANIA)
						NumberFormat formatter = NumberFormat.getInstance(new Locale("de"));
						elemento.setValor(formatter.format(elemento.getImporte()));
						listaModificados.add(elemento);
					}
				}	
				
			} 
		}
		
		return listaModificados;
	}
	
	/**
	 * @param listaModificados
	 * @return eliminoImporte[T/F]
	 */
	public Boolean eliminoImporte(List<DatoAdicionalBean> listaAdicionales){
		Boolean eliminoImporte = false;
		
		if(listaAdicionales != null){
			for (DatoAdicionalBean elemento : listaAdicionales) {
				if(elemento.getIndicadorEstructura().trim().equals(ConstantesFuncionales.ESTR_IMPORTE)){
					if(elemento.getOldValue() != null &&
							elemento.getImporte().compareTo(BigDecimal.ZERO) != 1){
						eliminoImporte = true;
						elemento.setImporte(convertNumber(elemento.getOldValue()));
					} else if(elemento.getValor().equals("")){
						elemento.setImporte(null);
					}
				}
			}
		}
		
		return eliminoImporte;
	}

	public void loadData() {
		datosAdicionales = consultaDatosAdicionalesBackEnd.ejecutarTRN(cuentaBean);
		
		for (DatoAdicionalBean elemento : datosAdicionales) {
			if(elemento.getIndicadorEstructura().trim().equals(ConstantesFuncionales.ESTR_INDICADOR)){
				elemento.setDeshabilitado(false);
				//PARA AC_OTRS_IDFR_EXT_E SI VALOR_AC_ID_EXT VIENE INFORMADO
				if(elemento.getEntityType().trim().equals("ACOTRSIDFREXTE") &&
						elemento.getValor() != null){
					elemento.setInputSwitch(true);
				}
				
				//PARA AC_INFADI_OTR_PERS_E SI VAL_AC_INFADI_PER VIENE INFORMADO
				if(elemento.getEntityType().trim().equals("ACINFADIOTRPERSE") &&
						elemento.getValor() != null){
					elemento.setInputSwitch(true);
				}
				
				//PARA AC_INF_ADIC_E SI VALOR_AC_INF_ADIC VIENE INFORMADO
				if(elemento.getEntityType().trim().equals("ACINFADICE") &&
						elemento.getValor() != null){
					elemento.setInputSwitch(true);
				}
				
				if(elemento.getValor() != null && 
						elemento.getValor().trim().equals("SI")){
					elemento.setInputSwitch(true);
				}
				
				if(elemento.getValor() == null || 
						elemento.getValor().trim().equals("NO")){
					elemento.setInputSwitch(false);
				}
				
				if(elemento.getCodInfAdicAc().trim().equals("47")){
					if(!this.cuentaBean.getCentro().trim()
							.equals(contextoUtils.getSucursal().trim()) ||
							elemento.getOldValue() == null){
						elemento.setDeshabilitado(true);
					}
				}
			}
			
			if(elemento.getValor() != null && elemento.getIndicadorEstructura().trim().equals(ConstantesFuncionales.ESTR_IMPORTE)){
				elemento.setImporte(convertNumber(elemento.getValor()));
				elemento.setOldValue(convertNumber(elemento.getOldValue()).toString());
			}
			
			if(elemento.getIndicadorEstructura().trim().equals(ConstantesFuncionales.ESTR_TELEFONO)){
				elemento.setInputSwitch(false);
			} else if(elemento.getOldValue() != null){
				elemento.setRequerido(true);
			}
		}
	}
	
	/**
	 * Funcion que convierte la cadena devuelta por la consulta para ser transformada
	 * en un importe.
	 * Detecta el formato en que viene el importe
	 * @param number
	 * @return
	 */
	private BigDecimal convertNumber(String number){
		BigDecimal convertedNumber = BigDecimal.ZERO;		
		
		try{
			convertedNumber = new BigDecimal(number);
		}catch(NumberFormatException ex){
			LOGGER.debug("Occurrió un error al transformar el importe." +  number, ex);
			LOGGER.debug("Se intentara con otro formato ###.###,##");
			
			DecimalFormatSymbols symbols = new DecimalFormatSymbols();
			symbols.setGroupingSeparator('.');
			symbols.setDecimalSeparator(',');
			String pattern = "#,##0.0#";
			DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
			decimalFormat.setParseBigDecimal(true);
			
			try{
				convertedNumber = (BigDecimal) decimalFormat.parse(number);
			}catch(ParseException ex2){
				LOGGER.debug("Occurrió un error al transformar el importe." +  number, ex2);	
				LOGGER.debug("Se intentara con otro formato ###,###.##");
				
				symbols.setGroupingSeparator(',');
				symbols.setDecimalSeparator('.');
				decimalFormat = new DecimalFormat(pattern, symbols);
				decimalFormat.setParseBigDecimal(true);
				
				try{
					convertedNumber = (BigDecimal) decimalFormat.parse(number);
				}catch(ParseException ex3){
					LOGGER.debug("Occurrió un error al transformar el importe." +  number, ex3);						
				}
			}
			
		}
		
		return convertedNumber;
	}
	
	private boolean telefonoModificado(){		
		if(datosAdicionales != null){
			for (DatoAdicionalBean elemento : datosAdicionales){
				if(elemento.getIndicadorEstructura().trim().equals(ConstantesFuncionales.ESTR_TELEFONO)){
					if(elemento.getOldValue() == null && 
							!elemento.getValor().equals("")){
						return true;
					} else if(elemento.getOldValue() != null && 
							!elemento.getOldValue().trim().equals(elemento.getValor().trim())){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Función que indica si se han entregado los documentos pertinentes
	 * a cada teléfono registrado.
	 * @return FALSO si no se han entegado todos los documentos requeridos
	 */
	private boolean documentoEntregado(){
		int registros = 0;
		int documentosEntregados = 0;
		for (DatoAdicionalBean elemento : datosAdicionales){
			if(elemento.getIndicadorEstructura().trim().equals(ConstantesFuncionales.ESTR_TELEFONO)){
				registros ++;
				//SE MODIFICÓ EL TELÉFONO
				if(elemento.getOldValue() != null && 
						!elemento.getOldValue().trim().equals(elemento.getValor().trim())){
					if(elemento.getInputSwitch()){
						documentosEntregados ++;
					}
				//SE INGRESA UN TELÉFONO NUEVO
				} else if(elemento.getOldValue() == null && 
						!elemento.getValor().equals("")){
					if(elemento.getInputSwitch()){
						documentosEntregados ++;
					}
				//NO SE HANN HECHO CAMBIOS
				} else if(elemento.getOldValue() != null && 
						elemento.getOldValue().trim().equals(elemento.getValor().trim())){
					documentosEntregados ++;
				}
			}
		}
		//SE HAN ENTREGADO TODOS LOS DOCUMENTOS CORRESPONDIENTES
		if(registros == documentosEntregados){
			return true;
		} else {
			return false;
		}
		
	}

	private Flash getFlash() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
	}
	
	/**
	 * @param cuentaBean
	 *            the cuentaBean to set
	 * @author Alejandro Villegas
	 */
	public void setCuentaBean(CuentaBean cuentaBean) {
		this.cuentaBean = cuentaBean;
	}

	/**
	 * Método para imprimir alguna de las plantillas de modificacíon de
	 * datos adicionales
	 */
	public void generarPlantilla(){
		StringBuffer nombrePdf = new StringBuffer(this.contextoUtils.getId());
		List<CuentaBean> listaCuentas;
		String nuevoTelefono;
		String viejoTelefono;
		
		for (DatoAdicionalBean elemento : datosAdicionales) {
			if(elemento.getIndicadorEstructura().trim().equals(ConstantesFuncionales.ESTR_TELEFONO)){
				//ASOCIAR
				if(elemento.getOldValue() == null &&
						!elemento.getValor().trim().equals("")){
					//ENVIAMOS LOS ULTIMOS 10 DIGITOS
					if(elemento.getValor().length() > 10){
						nuevoTelefono = elemento.getValor().substring(elemento.getValor().length() - 10);
					} else {
						nuevoTelefono = elemento.getValor();
					}
					
					cuentaBean.setNuevoTelefono(nuevoTelefono);
					listaCuentas = new ArrayList<CuentaBean>();
					listaCuentas.add(cuentaBean);
//					pdfUtils.generaPdf("datosAdicionalesAsociarTelefono.jrxml", null, null, null,
//			        		 nombrePdf.toString(), listaCuentas);
					
					guardaReporte(nombrePdf.toString(), "datosAdicionalesAsociarTelefono.jrxml", listaCuentas);
			        		 
				//DESASOCIAR
				} else if(elemento.getOldValue() != null && 
						elemento.getValor().trim().equals("")){
					//ENVIAMOS LOS ULTIMOS 10 DIGITOS
					if(elemento.getOldValue().length() > 10){
						viejoTelefono = elemento.getOldValue().substring(elemento.getOldValue().length() - 10);
					} else {
						viejoTelefono = elemento.getOldValue();
					}
					
					cuentaBean.setViejoTelefono(viejoTelefono);
					listaCuentas = new ArrayList<CuentaBean>();
					listaCuentas.add(cuentaBean);
//					pdfUtils.generaPdf("datosAdicionalesDesasociarTelefono.jrxml", null, null, null,
//	        		 nombrePdf.toString(), listaCuentas);
					
					guardaReporte(nombrePdf.toString(), "datosAdicionalesDesasociarTelefono.jrxml", listaCuentas);
					
				//CAMBIAR
				} else if(elemento.getOldValue() != null &&
						!elemento.getOldValue().trim().equals(elemento.getValor().trim())){
					//ENVIAMOS LOS ULTIMOS 10 DIGITOS
					if(elemento.getValor().length() > 10){
						nuevoTelefono = elemento.getValor().substring(elemento.getValor().length() - 10);
					} else {
						nuevoTelefono = elemento.getValor();
					}
					if(elemento.getOldValue().length() > 10){
						viejoTelefono = elemento.getOldValue().substring(elemento.getOldValue().length() - 10);
					} else {
						viejoTelefono = elemento.getOldValue();
					}
					cuentaBean.setViejoTelefono(viejoTelefono);
					cuentaBean.setNuevoTelefono(nuevoTelefono);
					listaCuentas = new ArrayList<CuentaBean>();
					listaCuentas.add(cuentaBean);
//					pdfUtils.generaPdf("datosAdicionalesCambiarTelefono.jrxml", null, null, null,
//	        		 nombrePdf.toString(), listaCuentas);
					
					guardaReporte(nombrePdf.toString(), "datosAdicionalesCambiarTelefono.jrxml", listaCuentas);
				} else if(this.reimpresion){
					pdfUtils.generaPdf(this.reporte, null, null, null,
			        		 this.nombreReporte, this.datosImpresion);
				}
			}
		}
	}
	
	/**
	 * Reimprime reporte
	 */
	public void reimpresion(){
		this.reimpresion = true;
		generarPlantilla();
		this.reimpresion = false;
	}
	
	/**
	 * Guarda la info del ultimo reporte para su futura reimpresion
	 * @param nombreReporte
	 * @param tipoReporte
	 * @param datosReporte
	 */
	public void guardaReporte(String nombreReporte, String tipoReporte, List<CuentaBean> datosReporte){
		this.nombreReporte = nombreReporte;
		this.reporte = tipoReporte;
		this.datosImpresion = datosReporte;
	}
	
	/**
	 * Funcion que indica si la cuenta se encuentra
	 * en estado activo
	 * @return
	 */
	public boolean isCuentaActiva(){
		if(this.cuentaBean != null && this.cuentaBean.getEstadoEnum() != null){
			if(!this.cuentaBean.getEstadoEnum().equals(EstadosCuentaEnum.CANCELADO) 
					&& !this.cuentaBean.getEstadoEnum().equals(EstadosCuentaEnum.APROBADO)){
				return true;
			}
		}
		
		return false;
	}

	/**
	 * @return the cuentaBean
	 * @author Alejandro Villegas
	 */
	public CuentaBean getCuentaBean() {
		return cuentaBean;
	}

	public boolean isRegistroGuardado() {
		return registroGuardado;
	}

	public void setRegistroGuardado(boolean registroGuardado) {
		this.registroGuardado = registroGuardado;
	}

	public boolean isHabilitarImpresion() {
		return habilitarImpresion;
	}

	public void setHabilitarImpresion(boolean habilitarImpresion) {
		this.habilitarImpresion = habilitarImpresion;
	}

	public boolean isReimpresion() {
		return reimpresion;
	}

	public void setReimpresion(boolean reimpresion) {
		this.reimpresion = reimpresion;
	}
	
}