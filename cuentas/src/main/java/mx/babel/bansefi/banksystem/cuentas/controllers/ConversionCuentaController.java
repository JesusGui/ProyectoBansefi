package mx.babel.bansefi.banksystem.cuentas.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.utils.CatalogoCentrosLoaderTask;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.cuentas.backends.ConversionCuentaBackEnd;
import mx.babel.bansefi.banksystem.cuentas.beans.ConversionCuentaBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Define las acciones para realizar la Conversion de cuenta
 * 
 * @author Natalio Iván Mendoza Sánchez &lt;nmendoza@uanlmexico.mx&gt;
 * @category Controller
 */
@ManagedBean
@ViewScoped
@Component
@Scope("view")
public class ConversionCuentaController implements Serializable {

	private static final long serialVersionUID = 27L;
	
	private String cuentaObtenida;
	private ConversionCuentaBean conversionCuentaBean = new ConversionCuentaBean();
	private List<ConversionCuentaBean> results;
	
	
	/**
	 * Variable para recuperar el catalogBean de CENTRO elegido en la
	 * modificacion de cliente
	 */
	private CatalogoBean centroSelected;

	@Autowired
	ContextoUtils contextoUtils;

	@Autowired
	CatalogoUtils catalogoUtils;

	@Autowired
	ConversionCuentaBackEnd conversionCuentaBackEnd;

	@Autowired
	CatalogoCentrosLoaderTask catalogoCentros;

	@PostConstruct
	public void init() {
		
	}

	public void Entro() {
		
		if(conversionCuentaBean != null 
				&& conversionCuentaBean.getSeleccion() != null
				&& conversionCuentaBean.getCentro() != null
				&& conversionCuentaBean.getCuenta() != null){				
			String seleccion = conversionCuentaBean.getSeleccion();
			long cuentaNueva;
			String cuentaAntigua = conversionCuentaBean.getCuenta();
			String centro = conversionCuentaBean.getCentro();
	
			results = new ArrayList<>();
	
			if (seleccion.equalsIgnoreCase("nuebaRB")) {
				conversionCuentaBean
						.setMensajeSalidaCentro("Cuenta NUEVA convertida a ANTIGUA :");
				conversionCuentaBean.setMensajeSalidaCuenta("Centro :");
				cuentaAntigua = "0";
				cuentaNueva = Long.parseLong(conversionCuentaBean.getCuenta());
				results.add(conversionCuentaBackEnd.ejecutarTRN(cuentaNueva, "N",
						cuentaAntigua, centro));
	
				for (ConversionCuentaBean datos : results) {
					conversionCuentaBean.setCuentaResult(datos.getCuenta());
					conversionCuentaBean.setCentroResult(datos.getCentro());}
				for (ConversionCuentaBean datos : results) {
					String res = datos.getCentro().trim();
					String cuent = datos.getCuenta().trim();
					if (res.equals("") || datos.getCuenta().equals("0")
							|| cuent.equals("") || cuent.equals("0")) {
						FacesContext
								.getCurrentInstance()
								.addMessage(
										null,
										new FacesMessage(
												FacesMessage.SEVERITY_WARN,
												"Error !  El número de cuenta proporcionado no es válido\n"
														+ "Por favor introduzca otro número de cuenta ",
												" "));
						results = new ArrayList<>();
						conversionCuentaBean = new ConversionCuentaBean();
						centroSelected = null;
					}
	
				}
			} else if (seleccion.equalsIgnoreCase("antiguaRB")) {
				cuentaNueva = 0;
				conversionCuentaBean
						.setMensajeSalidaCentro("Cuenta ANTIGUA convertida a NUEVA :");
				conversionCuentaBean.setMensajeSalidaCuenta("Centro :");
				results.add(conversionCuentaBackEnd.ejecutarTRN(cuentaNueva, "S",
						cuentaAntigua, centro));
				for (ConversionCuentaBean datos : results) {
					conversionCuentaBean.setCuentaResult(datos.getCuenta());
					conversionCuentaBean.setCentroResult(datos.getCentro());;}
				for (ConversionCuentaBean datos : results) {
					String res = datos.getCentro().trim();
					String cuent = datos.getCuenta().trim();
					if (res.equals("") || datos.getCuenta().equals("0")
							 || cuent.equals("") || cuent.equals("0")){
						FacesContext
								.getCurrentInstance()
								.addMessage(
										null,
										new FacesMessage(
												FacesMessage.SEVERITY_WARN,
												"El número de cuenta proporcionado no es válido\n"
														+ "Por favor introduzca otro número de cuenta !  ",
												""));
						results = new ArrayList<>();
						conversionCuentaBean = new ConversionCuentaBean();
						centroSelected = null;
					}
	
				}
	
			}
		}
	}

	public void inicializa() {
		results = new ArrayList<>();
		conversionCuentaBean = new ConversionCuentaBean();
		centroSelected = null;
	}

	/**
	 * @return the conversionCuentaBean
	 */
	public ConversionCuentaBean getConversionCuentaBean() {
		return conversionCuentaBean;
	}

	/**
	 * @param conversionCuentaBean
	 *            the conversionCuentaBean to set
	 */
	public void setConversionCuentaBean(
			ConversionCuentaBean conversionCuentaBean) {
		this.conversionCuentaBean = conversionCuentaBean;
	}

	/**
	 * @return the results
	 */
	public List<ConversionCuentaBean> getResults() {
		return results;
	}

	/**
	 * @param results
	 *            the results to set
	 */
	public void setResults(List<ConversionCuentaBean> results) {
		this.results = results;
	}

	/**
	 * @return the cuenta
	 */
	public String getcuentaObtenida() {
		return cuentaObtenida;
	}

	/**
	 * @param cuenta
	 *            the cuenta to set
	 */
	public void setcuentaObtenida(String cuentaObtenida) {
		this.cuentaObtenida = cuentaObtenida;
	}

	/**
	 * @return centroSelected
	 */
	public CatalogoBean getCentroSelected() {
		if (this.conversionCuentaBean.getCentro() != null
				&& !("").equals(this.conversionCuentaBean.getCentro())) {
			centroSelected = catalogoCentros.getCatalogoBean(
					contextoUtils.getEntidad(),
					this.conversionCuentaBean.getCentro());
		}
		return centroSelected;
	}

	/**
	 * @param centroSelected
	 */
	public void setCentroSelected(CatalogoBean centroSelected) {
		this.centroSelected = centroSelected;
		if (this.centroSelected != null) {
			this.conversionCuentaBean.setCentro(this.centroSelected
					.getClaveFila());
		}
	}
}
