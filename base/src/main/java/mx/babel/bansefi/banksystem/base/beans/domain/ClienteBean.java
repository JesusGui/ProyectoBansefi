package mx.babel.bansefi.banksystem.base.beans.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.babel.arq.catalogo.beans.CatalogoGeoBean;

/**
 * Clase para definir el modelo de Clientes
 * @author mario.montesdeoca
 *
 */
public class ClienteBean implements Serializable {


	private static final long serialVersionUID = 1095406226337942971L;

	private Integer idInterna;

	/**
	 * Nombre del cliente / En caso de ser persona moral este campo correspondrá a la razón social.
	 */
	private String nombre;

	private String tipoIdentificacion;

	private String desTipoId;

	private String numIdentificacion;

	private String rfc;

	private String telefono;

	private String paisResidencia;

	private String razonAlta;

	private String situacionEconomica;

	private CatalogoGeoBean municipioCatGeo;

	private String municipio;

	private CatalogoGeoBean estado;

	private String pais;

	private TipoCliente tipoClienteEnum;

	/**
	 * Fecha de nacimiento en caso de ser persona física. / Fecha de constitución en caso de ser persona moral.
	 */
	private Date fechaNacimiento;

	/**
	 * Fecha de fallecimeinto en caso de ser persona física. / Fecha de cierre en caso de ser persona moral.
	 */
	private Date fechaFallecimiento;

	private Boolean correspondenciaOficina;

	private String numOficina;

	private Boolean noPublicidad;

	private Boolean noRetencion;

	private Integer direccionPrincipal;

	private List<DomicilioTipoBean> domicilios;

	private List<DocumentoBean> documentos;

	private UsoCuentaBean usoCuenta;

	private TransaccionalidadBean transaccionalidad;

	private ClienteBean representante;

	private List<AnotacionBean> anotaciones;

	private String cnae;

	private String oficinaAlta;
	
	private Boolean esClienteRiesgo;

	private Boolean fusionado;
	
	private Boolean indCliente;

	/**
	 * @return Atributo idInterna
	 */
	public Integer getIdInterna() {
		return idInterna;
	}

	/**
	 * @param idInterna Atributo idInterna a definir
	 */
	public void setIdInterna(final Integer idInterna) {
		this.idInterna = idInterna;
	}

	/**
	 * @return Atributo nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre Atributo nombre a definir
	 */
	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return Atributo tipoIdentificacion
	 */
	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	/**
	 * @param tipoIdentificacion Atributo tipoIdentificacion a definir
	 */
	public void setTipoIdentificacion(final String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	/**
	 * @return Atributo numIdentificacion
	 */
	public String getNumIdentificacion() {
		return numIdentificacion;
	}

	/**
	 * @param numIdentificacion Atributo numIdentificacion a definir
	 */
	public void setNumIdentificacion(final String numIdentificacion) {
		this.numIdentificacion = numIdentificacion;
	}

	/**
	 * @return Atributo rfc
	 */
	public String getRfc() {
		return rfc;
	}

	/**
	 * @param rfc Atributo rfc a definir
	 */
	public void setRfc(final String rfc) {
		this.rfc = rfc;
	}

	/**
	 * @return Atributo telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono Atributo telefono a definir
	 */
	public void setTelefono(final String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return Atributo paisResidencia
	 */
	public String getPaisResidencia() {
		return paisResidencia;
	}

	/**
	 * @param paisResidencia Atributo paisResidencia a definir
	 */
	public void setPaisResidencia(final String paisResidencia) {
		this.paisResidencia = paisResidencia;
	}

	/**
	 * @return Atributo razonAlta
	 */
	public String getRazonAlta() {
		return razonAlta;
	}

	/**
	 * @param razonAlta Atributo razonAlta a definir
	 */
	public void setRazonAlta(final String razonAlta) {
		this.razonAlta = razonAlta;
	}

	/**
	 * @return Atributo situacionEconomica
	 */
	public String getSituacionEconomica() {
		return situacionEconomica;
	}

	/**
	 * @param situacionEconomica Atributo situacionEconomica a definir
	 */
	public void setSituacionEconomica(final String situacionEconomica) {
		this.situacionEconomica = situacionEconomica;
	}

	public CatalogoGeoBean getMunicipioCatGeo() {
		return municipioCatGeo;
	}

	public void setMunicipioCatGeo(final CatalogoGeoBean municipioCatGeo) {
		this.municipioCatGeo = municipioCatGeo;
	}

	/**
	 * @return Atributo municipio
	 */
	public String getMunicipio() {
		return municipio;
	}

	/**
	 * @param municipio Atributo municipio a definir
	 */
	public void setMunicipio(final String municipio) {
		this.municipio = municipio;
	}

	/**
	 * @return Atributo estado
	 */
	public CatalogoGeoBean getEstado() {
		return estado;
	}

	/**
	 * @param estado Atributo estado a definir
	 */
	public void setEstado(final CatalogoGeoBean estado) {
		this.estado = estado;
	}

	/**
	 * @return Atributo pais
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * @param pais Atributo pais a definir
	 */
	public void setPais(final String pais) {
		this.pais = pais;
	}

	/**
	 * @return Atributo fechaNacimiento
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * @param fechaNacimiento Atributo fechaNacimiento a definir
	 */
	public void setFechaNacimiento(final Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * @return Atributo fechaFallecimiento
	 */
	public Date getFechaFallecimiento() {
		return fechaFallecimiento;
	}

	/**
	 * @param fechaFallecimiento Atributo fechaFallecimiento a definir
	 */
	public void setFechaFallecimiento(final Date fechaFallecimiento) {
		this.fechaFallecimiento = fechaFallecimiento;
	}

	/**
	 * @return Atributo correspondenciaOficina
	 */
	public Boolean getCorrespondenciaOficina() {
		return correspondenciaOficina;
	}

	/**
	 * @param correspondenciaOficina Atributo correspondenciaOficina a definir
	 */
	public void setCorrespondenciaOficina(final Boolean correspondenciaOficina) {
		this.correspondenciaOficina = correspondenciaOficina;
	}

	/**
	 * @return Atributo numOficina
	 */
	public String getNumOficina() {
		return numOficina;
	}

	/**
	 * @param numOficina Atributo numOficina a definir
	 */
	public void setNumOficina(final String numOficina) {
		this.numOficina = numOficina;
	}

	/**
	 * @return Atributo noPublicidad
	 */
	public Boolean getNoPublicidad() {
		return noPublicidad;
	}

	/**
	 * @param noPublicidad Atributo noPublicidad a definir
	 */
	public void setNoPublicidad(final Boolean noPublicidad) {
		this.noPublicidad = noPublicidad;
	}

	/**
	 * @return Atributo noRetencion
	 */
	public Boolean getNoRetencion() {
		return noRetencion;
	}

	/**
	 * @param noRetencion Atributo noRetencion a definir
	 */
	public void setNoRetencion(final Boolean noRetencion) {
		this.noRetencion = noRetencion;
	}

	public Integer getDireccionPrincipal() {
		return direccionPrincipal;
	}

	public void setDireccionPrincipal(final Integer direccionPrincipal) {
		this.direccionPrincipal = direccionPrincipal;
	}

	/**
	 * @return Atributo domicilio
	 */
	public List<DomicilioTipoBean> getDomicilios() {
		if(domicilios == null){
			domicilios = new ArrayList<DomicilioTipoBean>();
		}
		return domicilios;
	}

	/**
	 * @param domicilios Atributo domicilio a definir
	 */
	public void setDomicilios(final List<DomicilioTipoBean> domicilios) {
		this.domicilios = domicilios;
	}

	/**
	 * @return Atributo documentos
	 */
	public List<DocumentoBean> getDocumentos() {
		if(documentos == null){
			documentos = new ArrayList<DocumentoBean>();
		}
		return documentos;
	}

	/**
	 * @param documentos Atributo documentos a definir
	 */
	public void setDocumentos(final List<DocumentoBean> documentos) {
		this.documentos = documentos;
	}

	/**
	 * @return Atributo usoCuenta
	 */
	public UsoCuentaBean getUsoCuenta() {
		return usoCuenta;
	}

	/**
	 * @param usoCuenta Atributo usoCuenta a definir
	 */
	public void setUsoCuenta(final UsoCuentaBean usoCuenta) {
		this.usoCuenta = usoCuenta;
	}

	/**
	 * @return Atributo transaccionalidad
	 */
	public TransaccionalidadBean getTransaccionalidad() {
		return transaccionalidad;
	}

	/**
	 * @param transaccionalidad Atributo transaccionalidad a definir
	 */
	public void setTransaccionalidad(final TransaccionalidadBean transaccionalidad) {
		this.transaccionalidad = transaccionalidad;
	}

	/**
	 * @return Atributo representante
	 */
	public ClienteBean getRepresentante() {
		return representante;
	}

	/**
	 * @param representante Atributo representante a definir
	 */
	public void setRepresentante(final ClienteBean representante) {
		this.representante = representante;
	}

	/**
	 * @return Atributo anotaciones
	 */
	public List<AnotacionBean> getAnotaciones() {
		if(anotaciones == null){
			anotaciones = new ArrayList<AnotacionBean>();
		}
		return anotaciones;
	}

	/**
	 * @param anotaciones Atributo anotaciones a definir
	 */
	public void setAnotaciones(final List<AnotacionBean> anotaciones) {
		this.anotaciones = anotaciones;
	}

	/**
	 * Función para regresar el tipo del cliente.
	 * @return el tipo de cliente
	 */
	public TipoCliente getTipo(){
		return null;
	}

	/**
	 * Función que obtiene el nombre completo del cliente.
	 * @return Nombre completo del cliente
	 */
	public String getNombreCompleto(){
		return nombre;
	}

	/**
	 * Función que obtiene el lugar de nacimiento/consititución del cliente.
	 *
	 * @return Lugar de nacimiento/constitucion del cliente
	 */
	public String getLugarNacimiento(){
		String lugarNacimiento = "";
		if(municipio != null && !municipio.isEmpty()){
			lugarNacimiento = lugarNacimiento + municipio + ", ";
		}
		if(estado != null && estado.getNombreProvincia()!=null && !estado.getNombreProvincia().isEmpty()){
			lugarNacimiento = lugarNacimiento + estado.getNombreProvincia() + ", ";
		}
		if(pais != null && !pais.isEmpty()){
			lugarNacimiento = lugarNacimiento + pais;
		}
		return lugarNacimiento;
	}

	/**
	 * @return the tipoCliente
	 */
	public String getTipoCliente() {
		if(tipoClienteEnum != null){
			return tipoClienteEnum.getTipo();
		}
		return null;
	}

	/**
	 * @param tipoCliente the tipoCliente to set
	 */
	public void setTipoCliente(final String tipoCliente) {
		this.tipoClienteEnum = TipoCliente.getTipoCliente(tipoCliente);
	}

    /**
     * @return the tipoClienteEnum
     */
    public TipoCliente getTipoClienteEnum() {
        return tipoClienteEnum;
    }

    /**
     * @param tipoClienteEnum the tipoClienteEnum to set
     */
    public void setTipoClienteEnum(final TipoCliente tipoClienteEnum) {
        this.tipoClienteEnum = tipoClienteEnum;
    }

	public String getCnae() {
		return cnae;
	}

	public void setCnae(final String cnae) {
		this.cnae = cnae;
	}

	public String getOficinaAlta() {
		return oficinaAlta;
	}

	public void setOficinaAlta(final String oficinaAlta) {
		this.oficinaAlta = oficinaAlta;
	}

	/**
	 * @return the desTipoId
	 */
	public String getDesTipoId() {
		return desTipoId;
	}

	/**
	 * @param desTipoId the desTipoId to set
	 */
	public void setDesTipoId(final String desTipoId) {
		this.desTipoId = desTipoId;
	}

	/**
     * Función que retorna el icono a mostrar en funcion del tipo de cliente
     *
     * @return <code>String</code> la clase a mostrar
     */
    public String claseTipoPersona(){
    	if(this.getTipoClienteEnum() == null){
    		this.tipoClienteEnum = this.getTipo();
    	}
        if((TipoCliente.PERSONA_FISICA.equals(this.getTipoClienteEnum()) || TipoCliente.MENOR_EDAD_DISCAPAZ.equals(this.getTipoClienteEnum())
                || TipoCliente.CLIENTE_GESTOR.equals(this.getTipoClienteEnum()) || TipoCliente.GESTOR.equals(this.getTipoClienteEnum()))){
            return "nombre-altacuenta";
        }
        if(TipoCliente.PERSONA_MORAL.equals(this.getTipoClienteEnum())){
            return "nombre-personamoral";
        }
        if(TipoCliente.GRUPO.equals(this.getTipoClienteEnum())){
            return "nombre-grupo";
        }
        return "";
    }

	/**
	 * @return the esClienteRiesgo
	 */
	public Boolean getEsClienteRiesgo() {
		return esClienteRiesgo;
	}

	/**
	 * @param esClienteRiesgo the esClienteRiesgo to set
	 */
	public void setEsClienteRiesgo(Boolean esClienteRiesgo) {
		this.esClienteRiesgo = esClienteRiesgo;
	}

	/**
	 * @return Atributo fusionado
	 */
	public Boolean getFusionado() {
		return fusionado;
	}

	/**
	 * @param fusionado Atributo fusionado a definir
	 */
	public void setFusionado(Boolean fusionado) {
		this.fusionado = fusionado;
	}

	public Boolean getIndCliente() {
		return indCliente;
	}

	public void setIndCliente(Boolean indCliente) {
		this.indCliente = indCliente;
	}

	/**
	 * Método para consultar la fecha de nacimiento/constitucion del cliente
	 */
	public String obtenerFechaNac() {
		// formateo a dd/mm/yyyy para que puedas comparar con el 11/11/1111
		SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
		String fechaFormat = fecha.format(fechaNacimiento);
		if(fechaFormat.equals("11/11/1111")) {
			return "";
		} else {
			return fecha.format(fechaNacimiento);
		}
	}

}
