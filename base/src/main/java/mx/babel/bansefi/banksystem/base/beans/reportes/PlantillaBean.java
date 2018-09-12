package mx.babel.bansefi.banksystem.base.beans.reportes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.babel.bansefi.banksystem.base.beans.domain.ClienteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioTipoBean;

public class PlantillaBean implements Serializable{

	private static final long serialVersionUID = 404827839164535821L;

	private ClienteBean clienteBean;
	
	private CuentaBean cuenta;
	
	private DomicilioTipoBean domicilio;
	
	private DomicilioTipoBean domicilioFiscal;
	
	private DomicilioTipoBean domicilioSocial;
	
	private Date fechaFormalizacion;

	private String rutaImg;

	private String rutaReport;

	private String sucursal;
	
	private String numeroSucursal;
	
	private String direccionSucursal;
	
	private String saldoMinimo;
	
	private String abonosMensuales;
	
	private String curpCliente;
	
	private String ocupacion;
	
	private String nacionalidad;
	
	private String duracionPlazo;
	
	private String empleado;
	
	private String cuentaOperativa;
	
	private String tipoCuenta;
	
	private String folioOportunidades;
	
	private String integranteId;
	
	private List<PersonaPlantillaBean> beneficiarios;
	
	private List<PersonaPlantillaBean> representantes;
	
	private List<PersonaPlantillaBean> autorizados;
	
	private List<PersonaPlantillaBean> apoderados;

	private ClienteBean primerBeneficiario;
	
	private ClienteBean primerCotitular;
	
	private ClienteBean primerApoderado;
	
	private ClienteBean primerPropietario;
	
	
		
	/**
	 * @return Atributo clienteBean
	 */
	public ClienteBean getClienteBean() {
		return clienteBean;
	}

	/**
	 * @param clienteBean Atributo clienteBean a definir
	 */
	public void setClienteBean(ClienteBean clienteBean) {
		this.clienteBean = clienteBean;
	}

	/**
	 * @return Atributo cuenta
	 */
	public CuentaBean getCuenta() {
		return cuenta;
	}

	/**
	 * @param cuenta Atributo cuenta a definir
	 */
	public void setCuenta(CuentaBean cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * @return Atributo domicilio
	 */
	public DomicilioTipoBean getDomicilio() {
		return domicilio;
	}

	/**
	 * @param domicilio Atributo domicilio a definir
	 */
	public void setDomicilio(DomicilioTipoBean domicilio) {
		this.domicilio = domicilio;
	}

	/**
	 * @return Atributo fechaFormalizacion
	 */
	public Date getFechaFormalizacion() {
		return fechaFormalizacion;
	}

	/**
	 * @param fechaFormalizacion Atributo fechaFormalizacion a definir
	 */
	public void setFechaFormalizacion(Date fechaFormalizacion) {
		this.fechaFormalizacion = fechaFormalizacion;
	}

	/**
	 * @return Atributo rutaImg
	 */
	public String getRutaImg() {
		return rutaImg;
	}

	/**
	 * @param rutaImg Atributo sucursal a definir
	 */
	public void setRutaImg(String rutaImg) {
		this.rutaImg = rutaImg;
	}

	/**
	 * @return Atributo rutaReport
	 */
	public String getRutaReport() {
		return rutaReport;
	}

	/**
	 * @param rutaReport Atributo sucursal a definir
	 */
	public void setRutaReport(String rutaReport) {
		this.rutaReport = rutaReport;
	}

	/**
	 * @return Atributo sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * @param sucursal Atributo sucursal a definir
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * @return Atributo numeroSucursal
	 */
	public String getNumeroSucursal() {
		return numeroSucursal;
	}

	/**
	 * @param numeroSucursal Atributo numeroSucursal a definir
	 */
	public void setNumeroSucursal(String numeroSucursal) {
		this.numeroSucursal = numeroSucursal;
	}

	/**
	 * @return Atributo direccionSucursal
	 */
	public String getDireccionSucursal() {
		return direccionSucursal;
	}

	/**
	 * @param direccionSucursal Atributo direccionSucursal a definir
	 */
	public void setDireccionSucursal(String direccionSucursal) {
		this.direccionSucursal = direccionSucursal;
	}

	/**
	 * @return Atributo saldoMinimo
	 */
	public String getSaldoMinimo() {
		return saldoMinimo;
	}

	/**
	 * @param saldoMinimo Atributo saldoMinimo a definir
	 */
	public void setSaldoMinimo(String saldoMinimo) {
		this.saldoMinimo = saldoMinimo;
	}

	/**
	 * @return Atributo abonosMensuales
	 */
	public String getAbonosMensuales() {
		return abonosMensuales;
	}

	/**
	 * @param abonosMensuales Atributo abonosMensuales a definir
	 */
	public void setAbonosMensuales(String abonosMensuales) {
		this.abonosMensuales = abonosMensuales;
	}

	/**
	 * @return Atributo curpCliente
	 */
	public String getCurpCliente() {
		return curpCliente;
	}

	/**
	 * @param curpCliente Atributo curpCliente a definir
	 */
	public void setCurpCliente(String curpCliente) {
		this.curpCliente = curpCliente;
	}

	/**
	 * @return Atributo ocupacion
	 */
	public String getOcupacion() {
		return ocupacion;
	}

	/**
	 * @param ocupacion Atributo ocupacion a definir
	 */
	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	/**
	 * @return Atributo nacionalidad
	 */
	public String getNacionalidad() {
		return nacionalidad;
	}

	/**
	 * @param nacionalidad Atributo nacionalidad a definir
	 */
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	/**
	 * @return Atributo duracionPlazo
	 */
	public String getDuracionPlazo() {
		return duracionPlazo;
	}

	/**
	 * @param duracionPlazo Atributo duracionPlazo a definir
	 */
	public void setDuracionPlazo(String duracionPlazo) {
		this.duracionPlazo = duracionPlazo;
	}

	/**
	 * @return Atributo empleado
	 */
	public String getEmpleado() {
		return empleado;
	}

	/**
	 * @param empleado Atributo empleado a definir
	 */
	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}

	/**
	 * @return Atributo cuentaOperativa
	 */
	public String getCuentaOperativa() {
		return cuentaOperativa;
	}

	/**
	 * @param cuentaOperativa Atributo cuentaOperativa a definir
	 */
	public void setCuentaOperativa(String cuentaOperativa) {
		this.cuentaOperativa = cuentaOperativa;
	}
	
	/**
	 * 
	 * @return Atributo folioOportunidades
	 */
	public String getFolioOportunidades(){
		return folioOportunidades;
	}
	
	/**
	 * 
	 * @param folioOportunidades Atributo folioOportunidades a definir
	 */
	public void setFolioOportunidades(String folioOportunidades){
		this.folioOportunidades = folioOportunidades;
	}
	
	public String getIntegranteId(){
		return integranteId;
	}
	
	public void setIntegranteId(String integranteId){
		this.integranteId = integranteId;
	}
	
	
	/**
	 * 
	 * @return Atributo tipoCuenta
	 */
	public String getTipoCuenta(){
		return tipoCuenta;
	}
	
	/**
	 * 
	 * @param tipoCuenta Atributo tipoCuent a definir
	 */
	public void setTipoCuenta(String tipoCuenta){
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * @return Atributo beneficiarios
	 */
	public List<PersonaPlantillaBean> getBeneficiarios() {
		if(beneficiarios == null){
			beneficiarios = new ArrayList<PersonaPlantillaBean>();
		}
		return beneficiarios;
	}

	/**
	 * @param beneficiarios Atributo beneficiarios a definir
	 */
	public void setBeneficiarios(List<PersonaPlantillaBean> beneficiarios) {
		this.beneficiarios = beneficiarios;
	}

	/**
	 * @return Atributo representantes
	 */
	public List<PersonaPlantillaBean> getRepresentantes() {
		if(representantes == null){
			representantes = new ArrayList<PersonaPlantillaBean>();
		}
		return representantes;
	}

	/**
	 * @param representantes Atributo representantes a definir
	 */
	public void setRepresentantes(List<PersonaPlantillaBean> representantes) {
		this.representantes = representantes;
	}

	/**
	 * @return Atributo autorizados
	 */
	public List<PersonaPlantillaBean> getAutorizados() {
		if(autorizados == null){
			autorizados = new ArrayList<PersonaPlantillaBean>();
		}
		return autorizados;
	}

	/**
	 * @param autorizados Atributo autorizados a definir
	 */
	public void setAutorizados(List<PersonaPlantillaBean> autorizados) {
		this.autorizados = autorizados;
	}
	
	/**
	 * @return Atributo apoderados
	 */
	public List<PersonaPlantillaBean> getApoderados() {
		if(apoderados == null){
			apoderados = new ArrayList<PersonaPlantillaBean>();
		}
		return apoderados;
	}

	/**
	 * @param apoderados Atributo apoderados a definir
	 */
	public void setApoderados(List<PersonaPlantillaBean> apoderados) {
		this.apoderados = apoderados;
	}

	/**
	 * @return Atributo serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    public ClienteBean getPrimerBeneficiario() {
        return primerBeneficiario;
    }

    public void setPrimerBeneficiario(ClienteBean primerBeneficiario) {
        this.primerBeneficiario = primerBeneficiario;
    }

    public ClienteBean getPrimerCotitular() {
        return primerCotitular;
    }

    public void setPrimerCotitular(ClienteBean primerCotitular) {
        this.primerCotitular = primerCotitular;
    }

    public ClienteBean getPrimerApoderado() {
        return primerApoderado;
    }

    public void setPrimerApoderado(ClienteBean primerApoderado) {
        this.primerApoderado = primerApoderado;
    }

    public ClienteBean getPrimerPropietario() {
        return primerPropietario;
    }

    public void setPrimerPropietario(ClienteBean primerPropietario) {
        this.primerPropietario = primerPropietario;
    }

    public DomicilioTipoBean getDomicilioSocial() {
        return domicilioSocial;
    }

    public void setDomicilioSocial(DomicilioTipoBean domicilioSocial) {
        this.domicilioSocial = domicilioSocial;
    }

    public DomicilioTipoBean getDomicilioFiscal() {
        return domicilioFiscal;
    }

    public void setDomicilioFiscal(DomicilioTipoBean domicilioFiscal) {
        this.domicilioFiscal = domicilioFiscal;
    }
}
