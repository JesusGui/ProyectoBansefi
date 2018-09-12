package mx.babel.bansefi.banksystem.cuentas.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioBean;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;
import mx.babel.bansefi.banksystem.base.enums.EstadosCuentaEnum;
import mx.babel.bansefi.banksystem.base.enums.TipoDomicilioEnum;

public class DomicilioCuentaBean extends DomicilioBean{

	private static final long serialVersionUID = 1357227081953107631L;

	private Boolean activo;
	private String centro;
	private String domicilio;
	private String nombreLocalidad;
	private String nombreCliente;
	private Integer idPersona;
	private Date fechaInicio;
	private Date fechaCierre;
	private Boolean masDatos;
	private Boolean consultado;
	private List<TipoDomicilioEnum> tipoDomicilio;
	private String codPersDir;
	private Boolean nuevo;
	private boolean inicial;
	private boolean modificarCentro;
	private EstadoListadosEnum estadoLista;
	private CatalogoBean catalogoCentro;
	private String respaldo;
	
	/**
	 * @return Atributo activo
	 */
	public Boolean getActivo() {
		return activo;
	}
	/**
	 * @param activo Atributo activo a definir
	 */
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	/**
	 * @return Atributo centro
	 */
	public String getCentro() {
		return centro;
	}
	/**
	 * @param centro Atributo centro a definir
	 */
	public void setCentro(String centro) {
		this.centro = centro;
	}
	/**
	 * @return Atributo domicilio
	 */
	public String getDomicilio() {
		return domicilio;
	}
	/**
	 * @param domicilio Atributo domicilio a definir
	 */
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	/**
	 * @return Atributo localidad
	 */
	public String getNombreLocalidad() {
		return nombreLocalidad;
	}
	/**
	 * @param localidad Atributo localidad a definir
	 */
	public void setNombreLocalidad(String nombreLocalidad) {
		this.nombreLocalidad = nombreLocalidad;
	}
	/**
	 * @return Atributo nombreCliente
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}
	/**
	 * @param nombreCliente Atributo nombreCliente a definir
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	/**
	 * @return Atributo idPersona
	 */
	public Integer getIdPersona() {
		return idPersona;
	}
	/**
	 * @param idPersona Atributo idPersona a definir
	 */
	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}
	/**
	 * @return Atributo fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * @param fechaInicio Atributo fechaInicio a definir
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	public Date getFechaCierre() {
		return fechaCierre;
	}
	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
	/**
	 * @return Atributo masDatos
	 */
	public Boolean getMasDatos() {
		return masDatos;
	}
	/**
	 * @param masDatos Atributo masDatos a definir
	 */
	public void setMasDatos(Boolean masDatos) {
		this.masDatos = masDatos;
	}
	/**
	 * @return Atributo consultado
	 */
	public Boolean getConsultado() {
		if(consultado == null){
			consultado = false;
		}
		return consultado;
	}
	/**
	 * @param consultado Atributo consultado a definir
	 */
	public void setConsultado(Boolean consultado) {
		this.consultado = consultado;
	}
	/**
	 * @return Atributo tipoDomicilio
	 */
	public List<TipoDomicilioEnum> getTipoDomicilio() {
		if(tipoDomicilio == null){
			tipoDomicilio = new ArrayList<TipoDomicilioEnum>();
		}
		return tipoDomicilio;
	}
	/**
	 * @param tipoDomicilio Atributo tipoDomicilio a definir
	 */
	public void setTipoDomicilio(List<TipoDomicilioEnum> tipoDomicilio) {
		this.tipoDomicilio = tipoDomicilio;
	}
	
	public String getCodPersDir() {
		return codPersDir;
	}
	public void setCodPersDir(String codPersDir) {
		this.codPersDir = codPersDir;
	}
	/**
	 * @return Atributo nuevo
	 */
	public Boolean getNuevo() {
		return nuevo;
	}
	/**
	 * @param nuevo Atributo nuevo a definir
	 */
	public void setNuevo(Boolean nuevo) {
		this.nuevo = nuevo;
	}
	
	public boolean isInicial() {
		return inicial;
	}
	
	public void setInicial(boolean inicial) {
		this.inicial = inicial;
	}
	
	public boolean isModificarCentro() {
		return modificarCentro;
	}
	
	public void setModificarCentro(boolean modificarCentro) {
		this.modificarCentro = modificarCentro;
	}
	
	public EstadoListadosEnum getEstadoLista() {
		return estadoLista;
	}
	
	public void setEstadoLista(EstadoListadosEnum estadoLista) {
		this.estadoLista = estadoLista;
	}
	
	public CatalogoBean getCatalogoCentro() {
		return catalogoCentro;
	}
	
	public void setCatalogoCentro(CatalogoBean catalogoCentro) {
		this.catalogoCentro = catalogoCentro;
	}
	
	public String getRespaldo() {
		return respaldo;
	}
	
	public void setRespaldo(String respaldo) {
		this.respaldo = respaldo;
	}
	
	
}
