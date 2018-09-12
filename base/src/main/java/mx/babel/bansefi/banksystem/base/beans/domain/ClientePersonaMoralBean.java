package mx.babel.bansefi.banksystem.base.beans.domain;

import java.util.List;

import mx.babel.bansefi.banksystem.base.beans.RelacionesClienteBean;

/**
 * Clase para definir el modelo de Clientes que son personas morales.
 * @author mario.montesdeoca
 * 
 */
public class ClientePersonaMoralBean extends ClienteBean{

	private static final long serialVersionUID = -7501957183627718450L;
	
	private ConstitucionPersonaMoralBean constitucionBean;
	
	private GrupoFilialPersonaMoralBean grupoFilialBean;
	
	private DonativosPersonaMoralBean donativosBean;
	
	private DomicilioTipoBean domicilioBean;

	private String sector;

	private String localidad;

	private String disponibilidadBursatil;
	
	private String razonSocial;
	
	private String estructuraLegal;
	
	private String ambito;
	
	private boolean importador;
	
	private boolean exportador;
	
	private String otrosRecursos;
	
	private List<String> recursos;
	
	private RelacionesClienteBean relacionesClienteRiesgo;

	/**
	 * @return Atributo sector
	 */
	public String getSector() {
		return sector;
	}

	/**
	 * @param sector Atributo sector a definir
	 */
	public void setSector(String sector) {
		this.sector = sector;
	}

	/**
	 * @return Atributo localidad
	 */
	public String getLocalidad() {
		return localidad;
	}

	/**
	 * @param localidad Atributo localidad a definir
	 */
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	/**
	 * @return Atributo disponibilidadBursatil
	 */
	public String getDisponibilidadBursatil() {
		return disponibilidadBursatil;
	}

	/**
	 * @param disponibilidadBursatil Atributo disponibilidadBursatil a definir
	 */
	public void setDisponibilidadBursatil(String disponibilidadBursatil) {
		this.disponibilidadBursatil = disponibilidadBursatil;
	}

	@Override
	public TipoCliente getTipo(){
		return TipoCliente.PERSONA_MORAL;
	}

	/**
	 * @return the razonSocial
	 */
	public String getRazonSocial() {
		return razonSocial;
	}

	/**
	 * @param razonSocial the razonSocial to set
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	/**
	 * @return the estructuraLegal
	 */
	public String getEstructuraLegal() {
		return estructuraLegal;
	}

	/**
	 * @param estructuraLegal the estructuraLegal to set
	 */
	public void setEstructuraLegal(String estructuraLegal) {
		this.estructuraLegal = estructuraLegal;
	}

	/**
	 * @return the ambito
	 */
	public String getAmbito() {
		return ambito;
	}

	/**
	 * @param ambito the ambito to set
	 */
	public void setAmbito(String ambito) {
		this.ambito = ambito;
	}

	/**
	 * @return the importador
	 */
	public boolean getImportador() {
		return importador;
	}

	/**
	 * @param importador the importador to set
	 */
	public void setImportador(boolean importador) {
		this.importador = importador;
	}
	
	/**
	 * @return the Exportador
	 */
	public boolean getExportador() {
		return exportador;
	}

	/**
	 * @param Exportador the Exportador to set
	 */
	public void setExportador(boolean exportador) {
		this.exportador = exportador;
	}
	
	/**
	 * @return the constitucionBean
	 */
	public ConstitucionPersonaMoralBean getConstitucionBean() {
		return constitucionBean;
	}

	/**
	 * @param constitucionBean the constitucionBean to set
	 */
	public void setConstitucionBean(ConstitucionPersonaMoralBean constitucionBean) {
		this.constitucionBean = constitucionBean;
	}

	/**
	 * @return the grupoFiliarBean
	 */
	public GrupoFilialPersonaMoralBean getGrupoFilialBean() {
		return grupoFilialBean;
	}

	/**
	 * @param grupoFiliarBean the grupoFiliarBean to set
	 */
	public void setGrupoFilialBean(GrupoFilialPersonaMoralBean grupoFilialBean) {
		this.grupoFilialBean = grupoFilialBean;
	}

	/**
	 * @return the donativosBean
	 */
	public DonativosPersonaMoralBean getDonativosBean() {
		return donativosBean;
	}

	/**
	 * @param donativosBean the donativosBean to set
	 */
	public void setDonativosBean(DonativosPersonaMoralBean donativosBean) {
		this.donativosBean = donativosBean;
	}
	
	/**
	 * @return the domicilioBean
	 */
	public DomicilioTipoBean getDomicilioBean() {
		return domicilioBean;
	}

	/**
	 * @param domicilioBean the domicilioBean to set
	 */
	public void setDomicilioBean(DomicilioTipoBean domicilioBean) {
		this.domicilioBean = domicilioBean;
	}

	public String getOtrosRecursos() {
		return otrosRecursos;
	}

	public void setOtrosRecursos(String otrosRecursos) {
		this.otrosRecursos = otrosRecursos;
	}
	
	public List<String> getRecursos() {
		return recursos;
	}

	public void setRecursos(List<String> recursos) {
		this.recursos = recursos;
	}

	/**
	 * @return the relacionesClienteRiesgo
	 */
	public RelacionesClienteBean getRelacionesClienteRiesgo() {
		return relacionesClienteRiesgo;
	}

	/**
	 * @param relacionesClienteRiesgo the relacionesClienteRiesgo to set
	 */
	public void setRelacionesClienteRiesgo(RelacionesClienteBean relacionesClienteRiesgo) {
		this.relacionesClienteRiesgo = relacionesClienteRiesgo;
	}

}
