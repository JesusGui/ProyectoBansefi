package mx.babel.bansefi.banksystem.base.beans.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;
import mx.babel.bansefi.banksystem.base.enums.TipoDomicilioEnum;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.stereotype.Component;

/**
 * Bean que extiende de DomicilioBean agregando el tipo de domicilio
 * 
 * @author manuel.nieto
 * 
 */
@Component
public class DomicilioTipoBean extends DomicilioBean implements Serializable, Cloneable {

	private static final long serialVersionUID = -6758082176164306360L;

	/**
	 * Agrupamiento de difentes direcciones que tengan el mismo tipo de
	 * domicilio
	 */
	private List<TipoDomicilioEnum> grouping;
	/**
	 * Cadenas solo con los nombres de los diferentes tipos de domicilios que
	 * tiene esta direccion Usado para trabajar con el componente de
	 * SelectManyCheckbox
	 */
	private List<String> tagsTipoDomicilio;
	/**
	 * Variables que se necesitan para el alta y edición de domicilios
	 */
	private String idInternoPersona;
	private String codigoDireccion;
	private String codigoRegimenOcupacion;
	private String indicadorMasDomicilios;
	private String codigoPais;

	/**
	 * Bandera para saber si el usuario desea editar solo este domicilio o todos
	 * los domicilios compartidos true: edita este domicilio y todos los
	 * compartidos false: edita solo este domicilio
	 */
	private boolean editaCompartidos;

	/**
	 * Variable que indica si panel donde se despliega el domicilio esta cerrado
	 * o abierto
	 */
	private boolean tooglePanel;

	/**
	 * Lista de domicilios compartidos
	 */
	private List<DomicilioCompartidoBean> domiciliosCompartidos;
	/**
	 * Variable que indica si ya se consulto todo el detalle del dicilio
	 */
	private boolean loadedAllData = false;

	/**
	 * Variable que indica el estado del bean
	 */
	private EstadoListadosEnum estadoBean = EstadoListadosEnum.NUEVO;

	public DomicilioTipoBean() {
		this.editaCompartidos = true;
	}

	/**
	 * Funcion que de acuerdo a la lista de tagsTipoDomicilio, crea el objeto
	 * grouping que es una lista de los Enums correspondientes
	 */
	public void generaAgrupamiento() {
		this.grouping = new ArrayList<TipoDomicilioEnum>();
		for (String tipo : this.getTagsTipoDomicilio()) {
			if (tipo.trim().equals(
					TipoDomicilioEnum.DOMICILIO_HABITUAL.getTipoDomicilio())) {
				grouping.add(TipoDomicilioEnum.DOMICILIO_HABITUAL);
			} else if (tipo.trim().equals(
					TipoDomicilioEnum.DOMICILIO_SOCIAL.getTipoDomicilio())) {
				grouping.add(TipoDomicilioEnum.DOMICILIO_SOCIAL);
			} else if (tipo.trim().equals(
					TipoDomicilioEnum.DOMICILIO_FISCAL.getTipoDomicilio())) {
				grouping.add(TipoDomicilioEnum.DOMICILIO_FISCAL);
			} else if (tipo.trim().equals(
					TipoDomicilioEnum.DOMICILIO_POSTAL.getTipoDomicilio())) {
				grouping.add(TipoDomicilioEnum.DOMICILIO_POSTAL);
			}
		}
	}

	/**
	 * Funcion que apartir del agrupamiento genera la lista para selects
	 */
	public void generaListaSelects() {
		this.tagsTipoDomicilio = new ArrayList<String>();

		if (this.grouping != null) {
			for (TipoDomicilioEnum tipo : this.grouping) {
				if (tipo != null && tipo.getTipoDomicilio() != null) {
					this.tagsTipoDomicilio.add(tipo.getTipoDomicilio());
				}
			}
		}
	}

	/**
	 * Funcion que retorna todos los tipos de domicilio agrupados en una cadena
	 * de texto
	 * 
	 * @return
	 */
	public String tipoDomicilioToString() {
		String resultado = new String();
		boolean primer = false;

		if (this.getTagsTipoDomicilio() == null) {
			generaListaSelects();
		}

		for (String tipo : this.getTagsTipoDomicilio()) {
			if (!primer) {
				resultado += tipo;
				primer = !primer;
			} else {
				resultado += ", " + tipo;
			}
		}

		return resultado;
	}

	/**
	 * Funcion que busca en el agrupamiento si existe el tipo de domicilio
	 * habitual
	 * 
	 * @return
	 */
	public boolean isDomicilioHabitualGrouping() {
		if (grouping != null) {
			for (TipoDomicilioEnum domicilio : grouping) {
				if (TipoDomicilioEnum.DOMICILIO_HABITUAL.equals(domicilio)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Funcion que busca en el agrupamiento si existe el tipo de domicilio
	 * fiscal
	 * 
	 * @return
	 */
	public boolean isDomicilioFiscalGrouping() {
		if (grouping != null) {
			for (TipoDomicilioEnum domicilio : grouping) {
				if (TipoDomicilioEnum.DOMICILIO_FISCAL.equals(domicilio)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Funcion que busca en el agrupamiento si existe el tipo de domicilio
	 * social
	 * 
	 * @return
	 */
	public boolean isDomicilioSocialGrouping() {
		for (TipoDomicilioEnum domicilio : grouping) {
			if (TipoDomicilioEnum.DOMICILIO_SOCIAL.equals(domicilio)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Funcion que busca en el agrupamiento si existe el tipo de domicilio
	 * postal
	 * 
	 * @return
	 */
	public boolean isDomicilioPostalGrouping() {
		for (TipoDomicilioEnum domicilio : grouping) {
			if (TipoDomicilioEnum.DOMICILIO_POSTAL.equals(domicilio)) {
				return true;
			}
		}
		return false;
	}

	public List<TipoDomicilioEnum> getGrouping() {
		if (grouping == null) {
			grouping = new ArrayList<TipoDomicilioEnum>();
		}
		return grouping;
	}

	public String getStringGrouping() {
		return tipoDomicilioToString();
	}

	public void setGrouping(List<TipoDomicilioEnum> grouping) {
		this.grouping = grouping;
	}

	public List<String> getTagsTipoDomicilio() {
		return tagsTipoDomicilio;
	}

	public void setTagsTipoDomicilio(List<String> tagsTipoDomicilio) {
		this.tagsTipoDomicilio = tagsTipoDomicilio;
		generaAgrupamiento();
	}

	public String getIdInternoPersona() {
		return idInternoPersona;
	}

	public void setIdInternoPersona(String idInternoPersona) {
		this.idInternoPersona = idInternoPersona;
	}

	public String getCodigoDireccion() {
		return codigoDireccion;
	}

	public void setCodigoDireccion(String codigoDireccion) {
		this.codigoDireccion = codigoDireccion;
	}

	public String getCodigoRegimenOcupacion() {
		return codigoRegimenOcupacion;
	}

	public void setCodigoRegimenOcupacion(String codigoRegimenOcupacion) {
		this.codigoRegimenOcupacion = codigoRegimenOcupacion;
	}

	public String getIndicadorMasDomicilios() {
		return indicadorMasDomicilios;
	}

	public void setIndicadorMasDomicilios(String indicadorMasDomicilios) {
		this.indicadorMasDomicilios = indicadorMasDomicilios;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public List<DomicilioCompartidoBean> getDomiciliosCompartidos() {
		return domiciliosCompartidos;
	}

	public void setDomiciliosCompartidos(
			List<DomicilioCompartidoBean> domiciliosCompartidos) {
		this.domiciliosCompartidos = domiciliosCompartidos;
	}

	public boolean isLoadedAllData() {
		return loadedAllData;
	}

	public void setLoadedAllData(boolean loadedAllData) {
		this.loadedAllData = loadedAllData;
	}

	/**
	 * Funcion que determina si tiene los suficientes datos de resumen
	 * 
	 * @return
	 */
	public boolean tieneDatosResumen() {
		if (!StringUtils.isBlank(tipoDomicilioToString())
				&& !StringUtils.isBlank(super.getNombreCalle())) {
			return true;
		} else {
			return false;
		}
	}

	public EstadoListadosEnum getEstadoBean() {
		return estadoBean;
	}

	public void setEstadoBean(EstadoListadosEnum estadoBean) {
		this.estadoBean = estadoBean;
	}

	public boolean isEditaCompartidos() {
		return editaCompartidos;
	}

	public void setEditaCompartidos(boolean editaCompartidos) {
		this.editaCompartidos = editaCompartidos;
	}

	/**
	 * Funcion que retorna la variable de estado del toggle del panel True:
	 * abierto False: cerrado
	 * 
	 * @return
	 */
	public boolean isTooglePanel() {
		return tooglePanel;
	}

	public void setTooglePanel(boolean tooglePanel) {
		this.tooglePanel = tooglePanel;
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj,
				new String[] { "serialVersionUID" });
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this,
				new String[] { "serialVersionUID" });
	}
	
	public Object clone(){
        return super.clone();
    }

}
