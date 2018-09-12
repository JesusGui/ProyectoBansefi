package mx.babel.bansefi.banksystem.cuentas.controllers;

import java.io.Serializable;
import java.util.ArrayList;
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
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.backends.ConsultaPosicionBackEnd;
import mx.babel.bansefi.banksystem.base.beans.ConceptoPosicionBean;
import mx.babel.bansefi.banksystem.base.beans.PosicionCuentaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClienteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClienteGrupoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.RelacionadoBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Controlador encargado de efectuar la consulta de la posición de un grupo o cuenta
 * 
 * @author babel
 * 
 */
@ManagedBean
@ViewScoped
@Component
@Scope("view")
public class PosicionController implements Serializable {

	private static final long serialVersionUID = -7581039737862731942L;

	private static final Logger LOGGER = LogManager.getLogger(PosicionController.class.getName());
	
	private static final String STYLE_TOTAL = "fila-total";
	
	private PosicionCuentaBean posicion;
	
	private List<String> columnas;
	
	private List<String> relacionesCuenta = new ArrayList<String>();
	
	private Map<String,String> descripciones;
	
	private Map<String,String> tipos;
	
	private boolean verMsg = false;
	
	private String mensaje = "";
	
	private String codigoError;
	
	private String mensajeError;
	
	@Autowired
    CatalogoUtils catalogo;
	
	@Autowired
	ConsultaPosicionBackEnd consultaPosicionBackend;

	
	@PostConstruct
	public void init() {
	
		LOGGER.debug("Inicializando ConsultaPosicionController...");
		
 	    List<CatalogoBean> catPosicion = catalogo.getCatalogo(CatalogoEnum.POSICION);
 	    List<CatalogoBean> catIDs = catalogo.getCatalogo(CatalogoEnum.TP_ID_EXT_PERS);
 	    
 	   CuentaBean cuenta = (CuentaBean)this.obtieneFlash().get(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash());
		this.posicion = new PosicionCuentaBean();
		
		
		// Viene desde la ficha de cuenta
		if (this.obtieneFlash().get(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash())!=null) {
			
			try {
				consultaPosicionBackend.ejecutarTRN(null,cuenta);
				this.posicion = cuenta.getPosicion();

				for (ClienteBean cli : this.posicion.getIntervinientes()) {
					for (RelacionadoBean rel : cuenta.getPersonasRelacionadas()) {
						if (cli.getIdInterna().intValue() == rel.getPersona().getIdInterna().intValue())
							relacionesCuenta.add(rel.getTipo().getNombre());
					}
				}
				
			} catch (ControlableException e) {
				if (e.getRtncod() == BackEndBean.RETURN_COD_SIN_DATOS){
					this.posicion.setConceptos(new ArrayList<ConceptoPosicionBean>());
					this.posicion.setIntervinientes(new ArrayList<ClienteBean>());
					ClienteBean cli = null;
					for (RelacionadoBean rel : cuenta.getPersonasRelacionadas()) {
						cli = new ClienteBean();
						cli.setNombre(rel.getPersona().getNombreCompleto());
						cli.setTipoIdentificacion(rel.getPersona().getTipoIdentificacion());
						cli.setNumIdentificacion(rel.getPersona().getNumIdentificacion());
						this.posicion.getIntervinientes().add(cli);
						relacionesCuenta.add(rel.getTipo().getNombre());
					}
				}else{
					this.setCodigoError(e.getMensajeUsuario());
					this.setMensajeError(e.getMensajeDetalle());
					RequestContext.getCurrentInstance().execute("PF('dlgAvisoError').show()");
				}
			}
			
			this.mensaje = "cuenta";
			
		// Viene desde de la ficha de cliente - grupo
		} else if (this.obtieneFlash().get(ParametrosFlashEnum.CLIENTE.getParamFlash())!=null){
			
			ClienteGrupoBean grupo = (ClienteGrupoBean)obtieneFlash().get(ParametrosFlashEnum.CLIENTE.getParamFlash());
			this.posicion = grupo.getPosicion();
			this.mensaje = "grupo";
			
			if (posicion == null) {
				this.posicion = new PosicionCuentaBean();
				this.posicion.setCliente(grupo);
				this.posicion.setConceptos(new ArrayList<ConceptoPosicionBean>());
				this.posicion.setIntervinientes(new ArrayList<ClienteBean>());
			}
			
			this.obtieneFlash().put(ParametrosFlashEnum.CUENTAS_FICHA.getParamFlash(),obtieneFlash().get(ParametrosFlashEnum.CUENTAS_FICHA.getParamFlash()));
			this.obtieneFlash().put(ParametrosFlashEnum.CUENTAS_FICHA2.getParamFlash(),obtieneFlash().get(ParametrosFlashEnum.CUENTAS_FICHA2.getParamFlash()));
		}
		this.posicion.setCuenta(cuenta);
		this.cargarDescripciones(catPosicion, catIDs);
		this.cargarPosicion();
		
	}


	private void cargarDescripciones(List<CatalogoBean> catalogoConceptos, List<CatalogoBean> catalogoIDs) {
		 this.descripciones = new HashMap<String,String>();
		 this.tipos = new HashMap<String,String>();
		 for (CatalogoBean c : catalogoConceptos) {
			 this.descripciones.put(c.getDescripcionC(), c.getDescripcionL()!=null ? c.getDescripcionL().trim().toUpperCase() : "");
		 }
		 
		 for (CatalogoBean c : catalogoIDs) {
			 this.tipos.put(c.getClaveFila(), c.getDescripcionL()!=null ? c.getDescripcionL().trim().toUpperCase() : "");
		 }
		 
	}
	
	private void cargarPosicion() {

		columnas = new ArrayList<String>();
		
		if (posicion.getConceptos() != null) {
			for (ConceptoPosicionBean concepto : posicion.getConceptos()){
				concepto.setDescripcion(this.descripciones.get(concepto.getCodDescripcion()));
				if (ConstantesFuncionales.TOTALES_POSICION.contains(concepto.getCodDescripcion())){
					concepto.setEstilo(PosicionController.STYLE_TOTAL);
				}
			}
			
			columnas.add("Total");
			
			Integer numPersona = 0;
			
			for (ClienteBean cli : posicion.getIntervinientes()){
				numPersona++;
				columnas.add("Persona "+numPersona.toString());
//				columnas.add(cli.getNombreCompleto());
				cli.setDesTipoId(this.tipos.get(cli.getTipoIdentificacion()));
			}
			
			verMsg = (posicion.getIntervinientes() != null && posicion.getIntervinientes().size()>=10) ? true : false ;
		}
		
	}
	
	/**
	 * Vuelve a la ficha de la cuenta o del grupo
	 * @return the posicion
	 */
	public String volver() {
		
		String retorno = "";
		if (this.posicion.getCuenta()!=null) {
			retorno = NavegacionEnum.FICHA_CUENTA.getRuta();	
			obtieneFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(), this.posicion.getCuenta());
		} else {
			retorno = NavegacionEnum.FICHA_CLIENTE.getRuta();	
			obtieneFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(), this.posicion.getCliente());
			this.obtieneFlash().put(ParametrosFlashEnum.CUENTAS_FICHA.getParamFlash(),obtieneFlash().get(ParametrosFlashEnum.CUENTAS_FICHA.getParamFlash()));
			this.obtieneFlash().put(ParametrosFlashEnum.CUENTAS_FICHA2.getParamFlash(),obtieneFlash().get(ParametrosFlashEnum.CUENTAS_FICHA2.getParamFlash()));

		}
		return retorno;
	}
	
	/**
	 * Obtiene memoria flash para variables en scope view.
	 * @return Metodo utilizado para recuperar una instancia de Flash
	 */
	public Flash obtieneFlash() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
	}

	/**
	 * Retorna la posición de la cuenta / grupo
	 * @return the posicion
	 */
	public PosicionCuentaBean getPosicion() {
		return posicion;
	}


	/**
	 * Almacena  la posición de la cuenta / grupo
	 * @param posicion the posicion to set
	 */
	public void setPosicion(PosicionCuentaBean posicion) {
		this.posicion = posicion;
	}


	/**
	 * @return the columnas
	 */
	public List<String> getColumnas() {
		return columnas;
	}


	/**
	 * @return the verMsg
	 */
	public boolean isVerMsg() {
		return verMsg;
	}


	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}


	/**
	 * @return the relacionesCuenta
	 */
	public List<String> getRelacionesCuenta() {
		return relacionesCuenta;
	}


	/**
	 * @return the codigoError
	 */
	public String getCodigoError() {
		return codigoError;
	}


	/**
	 * @param codigoError the codigoError to set
	 */
	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}


	/**
	 * @return the mensajeError
	 */
	public String getMensajeError() {
		return mensajeError;
	}


	/**
	 * @param mensajeError the mensajeError to set
	 */
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}
}
