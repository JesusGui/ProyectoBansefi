package mx.babel.bansefi.banksystem.cuentas.controllers;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.RelacionadoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.TipoRelacionPersonaCuenta;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaInformacionDerivadaBackEnd;
import mx.babel.bansefi.banksystem.cuentas.beans.InformacionDerivadaBean;

/**
 * 
 * @author alejandro.villegas
 *
 */

@ManagedBean
@ViewScoped
@Component
@Scope("view")
public class InformacionDerivadaController implements Serializable {

	
	@Autowired
	ConsultaInformacionDerivadaBackEnd consultaInformacionDerivadaBackEnd;
	@Autowired
	CatalogoUtils catalogoUtils;
	@Autowired
	ManagedBeanStateRecover managedBeanStateRecover;
	
	private static final long serialVersionUID = -8076243779647217474L;	
	
	private List<InformacionDerivadaBean> informacionDerivada;
	private CuentaBean cuentaBean;
	private int pagina;
	private String mensaje;
	private Object destinoController;
	private RelacionadoBean titular;
	
	/**
	 * Método que se ejecutará al cargar la pagina y ejecutará
	 * el TRN.
	 */
	
	@PostConstruct
	public void init(){
		/**
		 * Obtenemos la cuenta del cliente
		 */
		if (this.obtieneFlash().get(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash()) != null) {
			this.cuentaBean = (CuentaBean) this.obtieneFlash()
								.get(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash());
			this.destinoController = managedBeanStateRecover.getController();
			try{
				this.setInformacionDerivada(this.generaDescripcion(consultaInformacionDerivadaBackEnd.ejecutarTRN(this.cuentaBean)));
				this.setPagina(10);
				if(this.informacionDerivada.size() < 1){
					this.setMensaje("No hay datos que consultar");
				}
			}catch(ControlableException ce){
				this.informacionDerivada = new ArrayList<InformacionDerivadaBean>();
				this.setMensaje("Hubo un error al obtener la información derivada de esta cuenta");
			}catch(NoControlableException nce){
				this.informacionDerivada = new ArrayList<InformacionDerivadaBean>();
				this.setMensaje("Hubo un error al obtener la información derivada de esta cuenta");
			}
			this.titular = encontrarTitular();
		}else{
			this.informacionDerivada = new ArrayList<InformacionDerivadaBean>();
			this.setMensaje("No hay datos que consultar");
		}
		
		
	}
	
	/**
	 * Funcion que regresa la lista de información Derivada con la descripción
	 * correspondiente
	 * @param listaInformacion a asignar descripciones
	 * @return listaInformacion con descripción
	 */
	private List<InformacionDerivadaBean> generaDescripcion(List<InformacionDerivadaBean> listaInformacion){
		List<CatalogoBean> catalogo = catalogoUtils.getCatalogo(CatalogoEnum.TP_INF_DRVD_AC);
		List<CatalogoBean> catalogoNumerico = catalogoUtils.getCatalogo(CatalogoEnum.DESCRIPCION_INF_DERIVADA);
		Boolean descAsignada = false;
		
		for(int i = 0; i < listaInformacion.size(); i++){
			if(listaInformacion.get(i).getTipo() != null && 
					listaInformacion.get(i).getTipo().trim().equals("liqtramointlstv")){
				for(int j = 0; j < catalogoNumerico.size(); j++){
					if(listaInformacion.get(i).getInformacionDerivada().trim().equals(catalogoNumerico.get(j).getClaveFila())){
						descAsignada = true;
						listaInformacion.get(i).setDescripcionInformacion(catalogoNumerico.get(j).getDescripcionL());
					}
				}
				if(!descAsignada){
					listaInformacion.get(i).setDescripcionInformacion("INT.DEUDOR NO AUTORIZ.");
				}
			}
		}
		
		for(int i = 0; i < listaInformacion.size(); i++){
			if(listaInformacion.get(i).getTipo() == null){
				for(int j = 0; j < catalogo.size(); j++){
					if(listaInformacion.get(i).getInformacionDerivada().equals(catalogo.get(j).getClaveFila())){
						listaInformacion.get(i).setDescripcionInformacion(catalogo.get(j).getDescripcionL());
					}
				}
			}
		}
		
		return generaValor(listaInformacion);
	}
	
	/**
	 * Funcion que regresa la lista de información Derivada con los valores
	 * correspondientes
	 * @param listaInformacion
	 * @return
	 */
	private List<InformacionDerivadaBean> generaValor(List<InformacionDerivadaBean> listaInformacion){
		List<CatalogoBean> catalogo = catalogoUtils.getCatalogo(CatalogoEnum.VALOR_INF_DERIVADA);
		
		
		for(int i = 0; i < listaInformacion.size(); i++){
			if(listaInformacion.get(i).getTipo() == null){
				if(!listaInformacion.get(i).getInformacionDerivada().trim().equals("20")){
					if(listaInformacion.get(i).getValor().trim().equals("S") || 
							listaInformacion.get(i).getValor().trim().equals("1")){
						listaInformacion.get(i).setValor("SI");
					} else if(listaInformacion.get(i).getValor().trim().equals("N") || 
							listaInformacion.get(i).getValor().trim().equals("0")){
						listaInformacion.get(i).setValor("NO");
					}
				} else if(listaInformacion.get(i).getValor().trim().equals("N")){
						listaInformacion.get(i).setValor("NO");
				}
				
				for(int j = 0; j < catalogo.size(); j++){
					if(listaInformacion.get(i).getValor().trim().equals(catalogo.get(j).getClaveFila())){
						listaInformacion.get(i).setValor(catalogo.get(j).getDescripcionL());
					}
				}
			}
		}
		
		//VALOR PARA TAE INTERES DEUDOR
		for(int i = 0; i < listaInformacion.size(); i++){
			if(listaInformacion.get(i).getTipo() != null &&
					listaInformacion.get(i).getTipo().equals("liqtramointlstv") &&
					listaInformacion.get(i).getDescripcionInformacion().contains("DEUDOR") &&
					listaInformacion.get(i).getValor().equals("")){
				listaInformacion.get(i).setValor(listaInformacion.get(i - 1).getValor());
			}
		}
		
		return listaInformacion;
	}
	
	/**
	 * Función que devuelve la lista con la informacion
	 * derivada del cliente.
	 * @return informacionDerivada
	 */
	public List<InformacionDerivadaBean> getInformacionDerivada(){
		return informacionDerivada;
	}
	
	/**
	 * @param listaInformacion Atributo informacionDerivada to Set
	 */
	public void setInformacionDerivada(List<InformacionDerivadaBean> listaInformacion) {
		this.informacionDerivada = listaInformacion;
	}
	
	/**
	 * Funcion que permite obtener la persona relacionada a la cuenta
	 * @return
	 */
	private RelacionadoBean encontrarTitular(){
		for (final RelacionadoBean relacionado : this.cuentaBean.getPersonasRelacionadas()) {
			if(TipoRelacionPersonaCuenta.TITULAR.equals(relacionado.getTipo())){
				if(relacionado.getNumero() == 1){
					return relacionado;
				}
			}
		}
		return null;
	}
	
	/**
	 * Método para mostrar los siguientes datos en la tabla 
	 * de información derivada
	 */
	public void agregaPagina(){
		this.pagina += 10;
	}
	
	/**
	 * Función para regresar a la ficha de la cuenta
	 * @return Ruta de FichaCuenta
	 */
	public String irAtras(){
		managedBeanStateRecover.finNavegacion(destinoController);
		return NavegacionEnum.FICHA_CUENTA.getRuta();
	}
	
	/**
	 * Funccion para regresar al dashboard
	 * @return Ruta del Dashboard
	 */
	public String irDashboard(){
		return NavegacionEnum.INICIO.getRuta();
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
	 * Función que devuelve el bean de la cuenta.
	 * @return cuentaBean
	 */
	public CuentaBean getCuentaBean() {
		return cuentaBean;
	}

	/**
	 * Función que asigna un valor a cuentaBean.
	 * @param cuentaBean
	 */
	public void setCuentaBean(CuentaBean cuentaBean) {
		this.cuentaBean = cuentaBean;
	}

	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Object getDestinoController() {
		return destinoController;
	}

	public void setDestinoController(Object destinoController) {
		this.destinoController = destinoController;
	}

	/**
	 * @return the titular
	 */
	public RelacionadoBean getTitular() {
		return titular;
	}

	/**
	 * @param titular the titular to set
	 */
	public void setTitular(RelacionadoBean titular) {
		this.titular = titular;
	}
	
}
