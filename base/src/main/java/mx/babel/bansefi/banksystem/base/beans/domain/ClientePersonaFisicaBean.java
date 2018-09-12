package mx.babel.bansefi.banksystem.base.beans.domain;

import java.util.Date;

import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;

/**
 * Clase para definir el modelo de Clientes que son personas físicas.
 * @author mario.montesdeoca
 * 
 */
public class ClientePersonaFisicaBean extends ClienteBean{

	private static final long serialVersionUID = 1L;

	private Integer numHijos;
	
	private String tratamiento;
	
	private String apePaterno;
	
	private String apeMaterno;
	
	private String sexo;
	
	private String curp;
	
	private String estadoCivil;
	
	private String regimenEconomico;
	
	private String paisNacionalidad;
	
	private String idioma;
	
	private Boolean relacionConsejero;	
	
	private Boolean relacionAltaCargo;
	
	private String estadoLaboral;
	
	private String cno;
	
	/**
	 * En caso de ser <code>null</code> el cliente no es trabajador autónomo
	 */
	private Date fechaAltaAutonomo;
	
	private Boolean autonomo;
	
	private Date solvenciaMoralDesde;
	
	private Date solvenciaMoralHasta;
	
	private String solvenciaMoral;
	
	private RiesgoClientePersonaFisicaBean datosRiesgo;
	
	private Boolean isCliente;
	
	private Boolean isGestor;
	
	private GestorBean datosGestor;
	
	private String idIntegrante;
	
	private String capacidadLegal;
	
	/**
	 * @return Atributo numHijos
	 */
	public Integer getNumHijos() {
		return numHijos;
	}

	/**
	 * @param numHijos Atributo numHijos a definir
	 */
	public void setNumHijos(Integer numHijos) {
		this.numHijos = numHijos;
	}

	/**
	 * @return Atributo tratamiento
	 */
	public String getTratamiento() {
		return tratamiento;
	}

	/**
	 * @param tratamiento Atributo tratamiento a definir
	 */
	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}

	/**
	 * @return Atributo apePaterno
	 */
	public String getApePaterno() {
		return apePaterno;
	}

	/**
	 * @param apePaterno Atributo apePaterno a definir
	 */
	public void setApePaterno(String apePaterno) {
		this.apePaterno = apePaterno;
	}

	/**
	 * @return Atributo apeMaterno
	 */
	public String getApeMaterno() {
		return apeMaterno;
	}

	/**
	 * @param apeMaterno Atributo apeMaterno a definir
	 */
	public void setApeMaterno(String apeMaterno) {
		this.apeMaterno = apeMaterno;
	}

	/**
	 * @return Atributo sexo
	 */
	public String getSexo() {
		return sexo;
	}

	/**
	 * @param sexo Atributo sexo a definir
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	/**
	 * @return Atributo curp
	 */
	public String getCurp() {
		return curp;
	}

	/**
	 * @param curp Atributo curp a definir
	 */
	public void setCurp(String curp) {
		this.curp = curp;
	}

	/**
	 * @return Atributo estadoCivil
	 */
	public String getEstadoCivil() {
		return estadoCivil;
	}

	/**
	 * @param estadoCivil Atributo estadoCivil a definir
	 */
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	/**
	 * @return Atributo regimenEconomico
	 */
	public String getRegimenEconomico() {
		return regimenEconomico;
	}

	/**
	 * @param regimenEconomico Atributo regimenEconomico a definir
	 */
	public void setRegimenEconomico(String regimenEconomico) {
		this.regimenEconomico = regimenEconomico;
	}

	/**
	 * @return Atributo paisNacionalidad
	 */
	public String getPaisNacionalidad() {
		return paisNacionalidad;
	}

	/**
	 * @param paisNacionalidad Atributo paisNacionalidad a definir
	 */
	public void setPaisNacionalidad(String paisNacionalidad) {
		this.paisNacionalidad = paisNacionalidad;
	}

	/**
	 * @return Atributo idioma
	 */
	public String getIdioma() {
		return idioma;
	}

	/**
	 * @param idioma Atributo idioma a definir
	 */
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	/**
	 * @return Atributo relacionConsejero
	 */
	public Boolean getRelacionConsejero() {
		return relacionConsejero;
	}

	/**
	 * @param relacionConsejero Atributo relacionConsejero a definir
	 */
	public void setRelacionConsejero(Boolean relacionConsejero) {
		this.relacionConsejero = relacionConsejero;
	}

	/**
	 * @return Atributo relacionAltaCargo
	 */
	public Boolean getRelacionAltaCargo() {
		return relacionAltaCargo;
	}

	/**
	 * @param relacionAltaCargo Atributo relacionAltaCargo a definir
	 */
	public void setRelacionAltaCargo(Boolean relacionAltaCargo) {
		this.relacionAltaCargo = relacionAltaCargo;
	}

	/**
	 * @return Atributo estadoLaboral
	 */
	public String getEstadoLaboral() {
		return estadoLaboral;
	}

	/**
	 * @param estadoLaboral Atributo estadoLaboral a definir
	 */
	public void setEstadoLaboral(String estadoLaboral) {
		this.estadoLaboral = estadoLaboral;
	}

	/**
	 * @return Atributo cno
	 */
	public String getCno() {
		return cno;
	}

	/**
	 * @param cno Atributo cno a definir
	 */
	public void setCno(String cno) {
		this.cno = cno;
	}

	/**
	 * @return Atributo fechaAltaAutonomo
	 */
	public Date getFechaAltaAutonomo() {
		return fechaAltaAutonomo;
	}

	/**
	 * @param fechaAltaAutonomo Atributo fechaAltaAutonomo a definir
	 */
	public void setFechaAltaAutonomo(Date fechaAltaAutonomo) {
		this.fechaAltaAutonomo = fechaAltaAutonomo;
	}

	/**
	 * @return Atributo autonomo
	 */
	public Boolean getAutonomo() {
		return autonomo;
	}

	/**
	 * @param autonomo Atributo autonomo a definir
	 */
	public void setAutonomo(Boolean autonomo) {
		this.autonomo = autonomo;
	}

	/**
	 * @return Atributo solvenciaMoralDesde
	 */
	public Date getSolvenciaMoralDesde() {
		return solvenciaMoralDesde;
	}

	/**
	 * @param solvenciaMoralDesde Atributo solvenciaMoralDesde a definir
	 */
	public void setSolvenciaMoralDesde(Date solvenciaMoralDesde) {
		this.solvenciaMoralDesde = solvenciaMoralDesde;
	}

	/**
	 * @return Atributo solvenciaMoralHasta
	 */
	public Date getSolvenciaMoralHasta() {
		return solvenciaMoralHasta;
	}

	/**
	 * @param solvenciaMoralHasta Atributo solvenciaMoralHasta a definir
	 */
	public void setSolvenciaMoralHasta(Date solvenciaMoralHasta) {
		this.solvenciaMoralHasta = solvenciaMoralHasta;
	}

	/**
	 * @return Atributo solvenciaMoral
	 */
	public String getSolvenciaMoral() {
		return solvenciaMoral;
	}

	/**
	 * @param solvenciaMoral Atributo solvenciaMoral a definir
	 */
	public void setSolvenciaMoral(String solvenciaMoral) {
		this.solvenciaMoral = solvenciaMoral;
	}

	/**
	 * @return the datosRiesgo
	 */
	public RiesgoClientePersonaFisicaBean getDatosRiesgo() {
		return datosRiesgo;
	}

	/**
	 * @param datosRiesgo the datosRiesgo to set
	 */
	public void setDatosRiesgo(RiesgoClientePersonaFisicaBean datosRiesgo) {
		this.datosRiesgo = datosRiesgo;
	}

	/**
	 * @return Atributo isCliente
	 */
	public Boolean getIsCliente() {
		if(isCliente == null){
			isCliente = false;
		}
		return isCliente;
	}

	/**
	 * @param isCliente Atributo isCliente a definir
	 */
	public void setIsCliente(Boolean isCliente) {
		this.isCliente = isCliente;
	}

	/**
	 * @return Atributo isGestor
	 */
	public Boolean getIsGestor() {
		if(isGestor == null){
			isGestor = false;
		}
		return isGestor;
	}

	/**
	 * @param isGestor Atributo isGestor a definir
	 */
	public void setIsGestor(Boolean isGestor) {
		this.isGestor = isGestor;
	}

	/**
	 * @return Atributo datosGestor
	 */
	public GestorBean getDatosGestor() {
		return datosGestor;
	}

	/**
	 * @param datosGestor Atributo datosGestor a definir
	 */
	public void setDatosGestor(GestorBean datosGestor) {
		this.datosGestor = datosGestor;
	}

	/**
	 * @return Atributo idIntegrante
	 */
	public String getIdIntegrante() {
		return idIntegrante;
	}

	/**
	 * @param idIntegrante Atributo idIntegrante a definir
	 */
	public void setIdIntegrante(String idIntegrante) {
		this.idIntegrante = idIntegrante;
	}

	public String getCapacidadLegal() {
		return capacidadLegal;
	}

	public void setCapacidadLegal(String capacidadLegal) {
		this.capacidadLegal = capacidadLegal;
	}

	@Override
	public TipoCliente getTipo(){
		if(getIsGestor() && getIsCliente()){
			return TipoCliente.CLIENTE_GESTOR;
		}
		if(getIsGestor() && !getIsCliente()){
			return TipoCliente.GESTOR;
		}
		if((this.getFechaNacimiento() != null && ConstantesFuncionales.isMenorEdad(this.getFechaNacimiento()))|| 
				(!ConstantesFuncionales.CAPACIDAD_LEGAL_PLENA.equals(getSolvenciaMoral()) 
				&& !"".equals(getSolvenciaMoral()))){
			return TipoCliente.MENOR_EDAD_DISCAPAZ;
		}
		return TipoCliente.PERSONA_FISICA;
	}
	
	@Override
	public String getNombreCompleto(){
		String nombreCompleto = "";
		if(getNombre() != null){
			nombreCompleto = nombreCompleto + getNombre();
		}
		if(apePaterno != null){
			nombreCompleto = nombreCompleto + " " + apePaterno;
		}
		if(apeMaterno != null){
			nombreCompleto = nombreCompleto + " " + apeMaterno;
		}
		return nombreCompleto;
	}

	@Override
	public String toString() {
		return "ClientePersonaFisicaBean [numHijos=" + numHijos
				+ ", tratamiento=" + tratamiento + ", apePaterno=" + apePaterno
				+ ", apeMaterno=" + apeMaterno + ", sexo=" + sexo + ", curp="
				+ curp + ", estadoCivil=" + estadoCivil + ", regimenEconomico="
				+ regimenEconomico + ", paisNacionalidad=" + paisNacionalidad
				+ ", idioma=" + idioma + ", relacionConsejero="
				+ relacionConsejero + ", relacionAltaCargo="
				+ relacionAltaCargo + ", estadoLaboral=" + estadoLaboral
				+ ", cno=" + cno + ", fechaAltaAutonomo=" + fechaAltaAutonomo
				+ ", autonomo=" + autonomo + ", solvenciaMoralDesde="
				+ solvenciaMoralDesde + ", solvenciaMoralHasta="
				+ solvenciaMoralHasta + ", solvenciaMoral=" + solvenciaMoral
				+ ", datosRiesgo=" + datosRiesgo + ", isCliente=" + isCliente
				+ ", isGestor=" + isGestor + ", datosGestor=" + datosGestor
				+ "]";
	}

}
