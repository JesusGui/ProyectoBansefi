package mx.babel.arq.sesion.contexto.beans;

import java.math.BigInteger;
import java.util.Date;

/**
 * Clase que contiene los datos de usuario que van a almacenarse en sesión.
 * 
 * @author joseluis.pena
 * 
 */
public class UsuarioBean {

	private String id;
	private String nombre;
	private String pwd;
	private String perfil;
	private String region;
	private String entidad;
	private String sucursal;
	private String terminal;
	private String plazaBancaria;
	@SuppressWarnings("unused")
	private String puesto;
	private String direccionIp;
	private BigInteger diasExpiracionPwd;
	private Date fechaContableActual;
	private Date fechaContableAnterior;
	private Date fechaContableSiguiente;

	/**
	 * Constructor.
	 */
	public UsuarioBean() {
		super();
	}

	/**
	 * Método que devuelve el ID del usuario en sesión.
	 * 
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Método que establece el ID del usuario.
	 * 
	 * @param id
	 */
	public void setId(final String id) {
		this.id = id;
	}

	/**
	 * Método que devuelve el nombre del usuario en sesión.
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Método que establece el nombre del usuario.
	 * 
	 * @param nombre
	 */
	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Método que devuelve la contraseña del usuario.
	 * 
	 * @return pwd
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * Método que establece la contraseña del usuario.
	 * 
	 * @param pwd
	 */
	public void setPwd(final String pwd) {
		this.pwd = pwd;
	}

	/**
	 * Método que devuelve el perfil del usuario en sesión.
	 * 
	 * @return perfil
	 */
	public String getPerfil() {
		return perfil;
	}

	/**
	 * Método que establece el perfil del usuario.
	 * 
	 * @param perfil
	 */
	public void setPerfil(final String perfil) {
		this.perfil = perfil;
	}

	/**
	 * Método que devuelve el código de la región.
	 * 
	 * @return region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * Método que establece el código de la región.
	 * 
	 * @param region
	 */
	public void setRegion(final String region) {
		this.region = region;
	}

	/**
	 * Método que devuelve la entidad operativa.
	 * 
	 * @return entidad
	 */
	public String getEntidad() {
		return entidad;
	}

	/**
	 * Método que establece la entidad operativa.
	 * 
	 * @param entidad
	 */
	public void setEntidad(final String entidad) {
		this.entidad = entidad;
	}

	/**
	 * Método que devuelve la sucursal operativa.
	 * 
	 * @return sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * Método que establece la sucursal operativa.
	 * 
	 * @param sucursal
	 */
	public void setSucursal(final String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * Método que devuelve la terminal del usuario.
	 * 
	 * @return terminal
	 */
	public String getTerminal() {
		return terminal;
	}

	/**
	 * Método que establece la terminal.
	 * 
	 * @param terminal
	 */
	public void setTerminal(final String terminal) {
		this.terminal = terminal;
	}

	/**
	 * Método que devuelve la plaza bancaria.
	 * 
	 * @return plazaBancaria
	 */
	public String getPlazaBancaria() {
		return plazaBancaria;
	}

	/**
	 * Método que establece la plaza bancaria.
	 * 
	 * @param plazaBancaria
	 */
	public void setPlazaBancaria(String plazaBancaria) {
		this.plazaBancaria = plazaBancaria;
	}

	/**
	 * Método que devuelve el puesto del usuario.
	 * 
	 * @return puesto
	 */
	public String getPuesto() {
		if (this.terminal != null && !this.terminal.isEmpty()
				&& this.terminal.length() == 8) {
			return terminal.substring(6, 8);
		}
		return terminal;
	}

	/**
	 * Método que establece el puesto del usuario.
	 * 
	 * @param puesto
	 */
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	/**
	 * Método que devuelve la dirección IP desde la que accede el usuario.
	 * 
	 * @return direccionIp
	 */
	public String getDireccionIp() {
		return this.direccionIp;
	}

	/**
	 * Método que establece una dirección IP.
	 * 
	 * @param direccionIp
	 */
	public void setDireccionIp(final String direccionIp) {
		this.direccionIp = direccionIp;
	}

	/**
	 * Método que devuelve el número de días que faltan para que la contraseña
	 * del usuario caduque.
	 * 
	 * @return
	 */
	public BigInteger getDiasExpiracionPwd() {
		return diasExpiracionPwd;
	}

	/**
	 * Método que establece el número de días que faltan para que la contraseña
	 * del usuario caduque.
	 * 
	 * @param diasExpiracionPwd
	 */
	public void setDiasExpiracionPwd(final BigInteger diasExpiracionPwd) {
		this.diasExpiracionPwd = diasExpiracionPwd;
	}

	/**
	 * Método que obtiene la fecha contable actual devuelta por HOST.
	 * 
	 * @return fechaContableActual
	 */
	public Date getFechaContableActual() {
		return fechaContableActual;
	}

	/**
	 * Método que establece la fecha contable actual.
	 * 
	 * @param fechaContableActual
	 */
	public void setFechaContableActual(Date fechaContableActual) {
		this.fechaContableActual = fechaContableActual;
	}

	/**
	 * Método que obtiene la fecha contable anterior devuelta por HOST.
	 * 
	 * @return fechaContableAnterior
	 */
	public Date getFechaContableAnterior() {
		return fechaContableAnterior;
	}

	/**
	 * Método que establece la fecha contable anterior.
	 * 
	 * @param fechaContableAnterior
	 */
	public void setFechaContableAnterior(Date fechaContableAnterior) {
		this.fechaContableAnterior = fechaContableAnterior;
	}

	/**
	 * Método que devuelve la fecha contable siguiente devuelta por HOST.
	 * 
	 * @return fechaContableSiguiente
	 */
	public Date getFechaContableSiguiente() {
		return fechaContableSiguiente;
	}

	/**
	 * Método que establece la fecha contable siguiente.
	 * 
	 * @param fechaContableSiguiente
	 */
	public void setFechaContableSiguiente(Date fechaContableSiguiente) {
		this.fechaContableSiguiente = fechaContableSiguiente;
	}

}