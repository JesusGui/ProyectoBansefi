package mx.babel.bansefi.banksystem.integracionesAppWhere.dto;

import mx.babel.bansefi.banksystem.base.beans.DepositoIPFBean;
import mx.babel.bansefi.banksystem.base.beans.PosicionCuentaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.AnotacionBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DocumentoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.SaldoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.StatusCuentaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.CuentaRelacionadaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.PanBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.RelacionadoBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.ProductoSimpleBean;
import mx.babel.bansefi.banksystem.base.enums.CuentaEnum;
import mx.babel.bansefi.banksystem.base.enums.EstadosCuentaEnum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Bean que se utiliza para el autocomplete de cuentas contables
 * @author manuel.nieto
 *
 */
public class CuentaContableDTO implements Serializable{

	private static final long serialVersionUID = -1183122735047311413L;

	private Long numeroCuenta;

	private String nombreTitular;
	private String tipoIdentificacionTitular;
	private String numIdentificacionTitular;

	private String tipoCuenta;
	private CuentaEnum tipoCuentaEnum;
	private String estado;
	private EstadosCuentaEnum estadoEnum;
	private Date fechaEstado;

	private String codLinea;
	private String idGrupoProducto;
	private String idProducto;
	private String idTarifaProducto;
	private String tarifa;
	private String nivelCuenta;

	private String centro;

	private String entidad;
	private String plazaBancaria;
	private String digitoVerificador;
	private String cuentaClabe;

	private String moneda;
	private String pan;

	private String viejoTelefono;
	private String nuevoTelefono;

	private List<RelacionadoBean> personasRelacionadas;
	private List<DocumentoBean> documentos;
	private List<CuentaRelacionadaBean> cuentasRelacionadas;
	private List<PanBean> panesRelacionados;
	private SaldoBean saldoBean;
	private StatusCuentaBean statusCuentaBean;

	private PosicionCuentaBean posicion;

	private List<AnotacionBean> anotaciones;

	private List<ProductoSimpleBean> productosSimples;

	private List<DepositoIPFBean> ipfs;

	/**
	 * Método que obtiene el número de cuenta.
	 *
	 * @return numeroCuenta
	 */
	public Long getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Método que establece el número de cuenta.
	 *
	 * @param numeroCuenta
	 */
	public void setNumeroCuenta(final Long numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * Método que devuelve el nombre del titular de la cuenta.
	 *
	 * @return nombreTitular
	 */
	public String getNombreTitular() {
		return nombreTitular;
	}

	/**
	 * Método que establece el nombre del titular de la cuenta.
	 *
	 * @param nombreTitular
	 */
	public void setNombreTitular(final String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}

	/**
	 * Método que devuelve el tipo de identificacion del titular de la cuenta.
	 *
	 * @return tipoIdentificacionTitular
	 */
	public String getTipoIdentificacionTitular() {
		return tipoIdentificacionTitular;
	}

	/**
	 * Método que establece el tipo de identificacion del titular de la cuenta.
	 *
	 * @param tipoIdentificacionTitular
	 */
	public void setTipoIdentificacionTitular(final String tipoIdentificacionTitular) {
		this.tipoIdentificacionTitular = tipoIdentificacionTitular;
	}

	/**
	 * Método que devuelve el numero de identificacion del titular de la cuenta.
	 *
	 * @return numIdentificacionTitular
	 */
	public String getNumIdentificacionTitular() {
		return numIdentificacionTitular;
	}

	/**
	 * Método que establece el numero de identificacion del titular de la
	 * cuenta.
	 *
	 * @param numIdentificacionTitular
	 */
	public void setNumIdentificacionTitular(final String numIdentificacionTitular) {
		this.numIdentificacionTitular = numIdentificacionTitular;
	}

	/**
	 * Método que devuelve el nombre / tipo de cuenta.
	 *
	 * @return tipoCuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Método que establece el nombre / tipo de cuenta.
	 *
	 * @param tipoCuenta
	 */
	public void setTipoCuenta(final String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Método que devuelve la clave de la divisa a la que corresponde la cuenta.
	 *
	 * @return moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Método que establece la clave de la divisa de la cuenta.
	 *
	 * @param moneda
	 */
	public void setMoneda(final String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Método que devuelve el pan que corresponde la cuenta.
	 *
	 * @return pan
	 */
	public String getPan() {
		return pan;
	}

	/**
	 * Método que establece la pan de la cuenta.
	 *
	 * @param pan
	 */
	public void setPan(final String pan) {
		this.pan = pan;
	}

	/**Metodo que devuelve el numero anterior asociado a la
	 *		cuenta.
	 * @return
	 */
	public String getViejoTelefono() {
		return viejoTelefono;
	}

	/**
	 * Metodo que establece el numero anterior asociado a la
	 * 		cuenta.
	 * @param viejoTelefono
	 */
	public void setViejoTelefono(final String viejoTelefono) {
		this.viejoTelefono = viejoTelefono;
	}

	/**
	 * Metodo que devuelve el nuevo numero telefonico de la
	 * 		cuenta.
	 * @return
	 */
	public String getNuevoTelefono() {
		return nuevoTelefono;
	}

	/**
	 * Metodo que establece el nuevo numero telefonico de la
	 * 		cuenta.
	 * @param nuevoTelefono
	 */
	public void setNuevoTelefono(final String nuevoTelefono) {
		this.nuevoTelefono = nuevoTelefono;
	}

	/**
	 * Método que devuelve la CLABE asociada a la cuenta. Una CLABE se conforma
	 * por la entidad, plaza, el número de cuenta y el dígito verificador.
	 *
	 * @return cuentaClabe
	 */
	public String getCuentaClabe() {
		return cuentaClabe;
	}

	/**
	 * Método que establece el valor de la cuenta CLABE.
	 *
	 * @param cuentaClabe
	 */
	public void setCuentaClabe(final String cuentaClabe) {
		this.cuentaClabe = cuentaClabe;
	}

	/**
	 * Método que devuelve el digito verificador de la cuenta.
	 *
	 * @return digitoVerificador
	 */

	public String getDigitoVerificador() {
		return digitoVerificador;
	}

	/**
	 * Método que establece el digito verificador de la cuenta.
	 *
	 * @param digitoVerificador
	 */
	public void setDigitoVerificador(final String digitoVerificador) {
		this.digitoVerificador = digitoVerificador;
	}

	/**
	 * Método que devuelve la plaza bancaria de la cuenta.
	 *
	 * @return plazaBancaria
	 */
	public String getPlazaBancaria() {
		return plazaBancaria;
	}

	/**
	 * Método que establece la plaza bancaria de la cuenta.
	 *
	 * @param plazaBancaria
	 */
	public void setPlazaBancaria(final String plazaBancaria) {
		this.plazaBancaria = plazaBancaria;
	}

	/**
	 * Método que devuelve el estado de la cuenta.
	 *
	 * @return Atributo estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Método que establece el estado de la cuenta.
	 *
	 * @param estado
	 *            Atributo estado a definir
	 */
	public void setEstado(final String estado) {
		this.estado = estado;
	}

	/**
	 * @return Atributo personasRelacionadas
	 */
	public List<RelacionadoBean> getPersonasRelacionadas() {
		if (personasRelacionadas == null) {
			personasRelacionadas = new ArrayList<RelacionadoBean>();
		}
		return personasRelacionadas;
	}

	/**
	 * @param personasRelacionadas
	 *            Atributo personasRelacionadas a definir
	 */
	public void setPersonasRelacionadas(
			final List<RelacionadoBean> personasRelacionadas) {
		this.personasRelacionadas = personasRelacionadas;
	}

	/**
	 * @return Atributo documentos
	 */
	public List<DocumentoBean> getDocumentos() {
		return documentos;
	}

	/**
	 * @param documentos
	 *            Atributo documentos a definir
	 */
	public void setDocumentos(final List<DocumentoBean> documentos) {
		this.documentos = documentos;
	}

	/**
	 * @return Atributo cuentasRelacionadas
	 */
	public List<CuentaRelacionadaBean> getCuentasRelacionadas() {
		if (cuentasRelacionadas == null) {
			cuentasRelacionadas = new ArrayList<CuentaRelacionadaBean>();
		}
		return cuentasRelacionadas;
	}

	/**
	 * @param cuentasRelacionadas
	 *            Atributo cuentasRelacionadas a definir
	 */
	public void setCuentasRelacionadas(
			final List<CuentaRelacionadaBean> cuentasRelacionadas) {
		this.cuentasRelacionadas = cuentasRelacionadas;
	}

	/**
	 * @return Atributo panesRelacionados
	 */
	public List<PanBean> getPanesRelacionados() {
		return panesRelacionados;
	}

	/**
	 * @param panesRelacionados Atributo panesRelacionados a definir
	 */
	public void setPanesRelacionados(final List<PanBean> panesRelacionados) {
		this.panesRelacionados = panesRelacionados;
	}

	/**
	 * @return Atributo saldoBean
	 */
	public SaldoBean getSaldoBean() {
		return saldoBean;
	}

	/**
	 * @param saldoBean
	 *            Atributo saldoBean a definir
	 */
	public void setSaldoBean(final SaldoBean saldoBean) {
		this.saldoBean = saldoBean;
	}

	/**
	 * @return the centro
	 */
	public String getCentro() {
		return centro;
	}

	/**
	 * @param centro
	 *            the centro to set
	 */
	public void setCentro(final String centro) {
		this.centro = centro;
	}

	/**
	 * @return the tarifa
	 */
	public String getTarifa() {
		return tarifa;
	}

	/**
	 * @param tarifa
	 *            the tarifa to set
	 */
	public void setTarifa(final String tarifa) {
		this.tarifa = tarifa;
	}

	/**
	 *
	 * @return the nivelCuenta
	 */
	public String getNivelCuenta() {
		return nivelCuenta;
	}

	/**
	 *
	 * @param nivelCuenta
	 * 			the nivelCuenta to set
	 */
	public void setNivelCuenta(final String nivelCuenta) {
		this.nivelCuenta = nivelCuenta;
	}

	/**
	 * @return the entidad
	 */
	public String getEntidad() {
		return entidad;
	}

	/**
	 * @param entidad
	 *            the entidad to set
	 */
	public void setEntidad(final String entidad) {
		this.entidad = entidad;
	}

	/**
	 * @return the codLinea
	 */
	public String getCodLinea() {
		return codLinea;
	}

	/**
	 * @param codLinea
	 *            the codLinea to set
	 */
	public void setCodLinea(final String codLinea) {
		this.codLinea = codLinea;
	}

	/**
	 * @return the idGrupoProducto
	 */
	public String getIdGrupoProducto() {
		return idGrupoProducto;
	}

	/**
	 * @param idGrupoProducto
	 *            the idGrupoProducto to set
	 */
	public void setIdGrupoProducto(final String idGrupoProducto) {
		this.idGrupoProducto = idGrupoProducto;
	}

	/**
	 * @return the idProducto
	 */
	public String getIdProducto() {
		return idProducto;
	}

	/**
	 * @param idProducto
	 *            the idProducto to set
	 */
	public void setIdProducto(final String idProducto) {
		this.idProducto = idProducto;
	}

	/**
	 * @return the idTarifaProducto
	 */
	public String getIdTarifaProducto() {
		return idTarifaProducto;
	}

	/**
	 * @param idTarifaProducto
	 *            the idTarifaProducto to set
	 */
	public void setIdTarifaProducto(final String idTarifaProducto) {
		this.idTarifaProducto = idTarifaProducto;
	}

	/**
	 * @return the fechaEstado
	 */
	public Date getFechaEstado() {
		return fechaEstado;
	}

	/**
	 * @param fechaEstado
	 *            the fechaEstado to set
	 */
	public void setFechaEstado(final Date fechaEstado) {
		this.fechaEstado = fechaEstado;
	}

	/**
	 * @return statusCuentaBean
	 */
	public StatusCuentaBean getStatusCuentaBean() {
		return statusCuentaBean;
	}

	/**
	 * @param statusCuentaBean
	 *            metodo para guardar el statusCuentaBean
	 */
	public void setStatusCuentaBean(final StatusCuentaBean statusCuentaBean) {
		this.statusCuentaBean = statusCuentaBean;
	}

	/**
	 * @return anotaciones
	 */
	public List<AnotacionBean> getAnotaciones() {
		if (anotaciones == null) {
			anotaciones = new ArrayList<AnotacionBean>();
		}
		return anotaciones;
	}

	/**
	 * @param anotaciones
	 *            metodo para guardar anotaciones
	 */
	public void setAnotaciones(final List<AnotacionBean> anotaciones) {
		this.anotaciones = anotaciones;
	}

	@Override
	public String toString() {
		return "CuentaBean [numeroCuenta=" + numeroCuenta + ", nombreTitular="
				+ nombreTitular + ", tipoIdentificacionTitular="
				+ tipoIdentificacionTitular + ", numIdentificacionTitular="
				+ numIdentificacionTitular + ", tipoCuenta=" + tipoCuenta
				+ ", estado=" + estado + ", fechaEstado=" + fechaEstado
				+ ", codLinea=" + codLinea + ", idGrupoProducto="
				+ idGrupoProducto + ", idProducto=" + idProducto
				+ ", idTarifaProducto=" + idTarifaProducto + ", tarifa="
				+ tarifa + ", nivelCuenta=" + nivelCuenta + ", centro="
				+ centro + ", entidad=" + entidad + ", plazaBancaria="
				+ plazaBancaria + ", digitoVerificador=" + digitoVerificador
				+ ", cuentaClabe=" + cuentaClabe + ", moneda=" + moneda
				+ ", personasRelacionadas=" + personasRelacionadas
				+ ", documentos=" + documentos + ", cuentasRelacionadas="
				+ cuentasRelacionadas + ", saldoBean=" + saldoBean
				+ ", statusCuentaBean=" + statusCuentaBean + ", anotaciones="
				+ anotaciones + ", posicion=" + posicion
				+ ", productosSimples=" + productosSimples
				+ ", ipfs=" + ipfs+"]";
	}

	/**
	 * @return the posicion
	 */
	public PosicionCuentaBean getPosicion() {
		return posicion;
	}

	/**
	 * @param posicion the posicion to set
	 */
	public void setPosicion(final PosicionCuentaBean posicion) {
		this.posicion = posicion;
	}

	/**
	 * @return the productosSimples
	 */
	public List<ProductoSimpleBean> getProductosSimples() {
		return productosSimples;
	}

	/**
	 * @param productosSimples the productosSimples to set
	 */
	public void setProductosSimples(final List<ProductoSimpleBean> productosSimples) {
		this.productosSimples = productosSimples;
	}

	public EstadosCuentaEnum getEstadoEnum() {
		return estadoEnum;
	}

	public void setEstadoEnum(final EstadosCuentaEnum estadoEnum) {
		this.estadoEnum = estadoEnum;
	}

	/**
	 * @return the ipfsActivos
	 */
//    public List<DepositoIPFBean> getIpfsActivos() {
//        return ipfsActivos;
//    }

	/**
	 * @param ipfsActivos the ipfsActivos to set
	 */
//    public void setIpfsActivos(final List<DepositoIPFBean> ipfsActivos) {
//        this.ipfsActivos = ipfsActivos;
//    }



	public CuentaEnum getTipoCuentaEnum() {
		return tipoCuentaEnum;
	}

	/**
	 * @return the ipfs
	 */
	public List<DepositoIPFBean> getIpfs() {
		return ipfs;
	}

	/**
	 * @param ipfs the ipfs to set
	 */
	public void setIpfs(final List<DepositoIPFBean> ipfs) {
		this.ipfs = ipfs;
	}

	public void setTipoCuentaEnum(final CuentaEnum tipoCuentaEnum) {
		this.tipoCuentaEnum = tipoCuentaEnum;
	}
	
}
