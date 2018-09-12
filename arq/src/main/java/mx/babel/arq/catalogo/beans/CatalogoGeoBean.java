package mx.babel.arq.catalogo.beans; 

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class CatalogoGeoBean implements Serializable, Cloneable {

	private static final long serialVersionUID = -3426960429457651955L;
	
	private Integer id;

	private String numArGeo;
	private String codigoPostal;
	private String subCodigoPostal;
	private String numAgLocine;
	private String codigoProvincia;

	private String nombreLocalidad;
	private String indPzaBancaria;
	private String codigoMunicipio;
	private String codigoEntidadColectiva;
	private String nombreMunicipio;

	private String prNumArGeo;
	private String codigoComunidadAutonoma;
	private String nombreProvincia;
	private String prefijoTelefono;
	private String matricula;

	private String codigoImpuesto;
	private String caNumArGeo;
	private String nombreComunidadAutonoma;
	private String codigoPais;
	private String nombrePais;
	
	private String municipioLocalidad;
	private String codArGeo;
	private String codigoLocalidadCompuesto;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumArGeo() {
		return numArGeo;
	}

	public void setNumArGeo(String numArGeo) {
		this.numArGeo = numArGeo;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getSubCodigoPostal() {
		return subCodigoPostal;
	}

	public void setSubCodigoPostal(String subCodigoPostal) {
		this.subCodigoPostal = subCodigoPostal;
	}

	public String getNumAgLocine() {
		return numAgLocine;
	}

	public void setNumAgLocine(String numAgLocine) {
		this.numAgLocine = numAgLocine;
	}

	public String getCodigoProvincia() {
		return codigoProvincia;
	}

	public void setCodigoProvincia(String codigoProvincia) {
		this.codigoProvincia = codigoProvincia;
	}

	public String getNombreLocalidad() {
		return nombreLocalidad;
	}

	public void setNombreLocalidad(String nombreLocalidad) {
		this.nombreLocalidad = nombreLocalidad;
	}

	public String getIndPzaBancaria() {
		return indPzaBancaria;
	}

	public void setIndPzaBancaria(String indPzaBancaria) {
		this.indPzaBancaria = indPzaBancaria;
	}

	public String getCodigoMunicipio() {
		return codigoMunicipio;
	}

	public void setCodigoMunicipio(String codigoMunicipio) {
		this.codigoMunicipio = codigoMunicipio;
	}

	public String getCodigoEntidadColectiva() {
		return codigoEntidadColectiva;
	}

	public void setCodigoEntidadColectiva(String codigoEntidadColectiva) {
		this.codigoEntidadColectiva = codigoEntidadColectiva;
	}

	public String getNombreMunicipio() {
		return nombreMunicipio;
	}

	public void setNombreMunicipio(String nombreMunicipio) {
		this.nombreMunicipio = nombreMunicipio;
	}

	public String getPrNumArGeo() {
		return prNumArGeo;
	}

	public void setPrNumArGeo(String prNumArGeo) {
		this.prNumArGeo = prNumArGeo;
	}

	public String getCodigoComunidadAutonoma() {
		return codigoComunidadAutonoma;
	}

	public void setCodigoComunidadAutonoma(String codigoComunidadAutonoma) {
		this.codigoComunidadAutonoma = codigoComunidadAutonoma;
	}

	public String getNombreProvincia() {
		return nombreProvincia;
	}

	public void setNombreProvincia(String nombreProvincia) {
		this.nombreProvincia = nombreProvincia;
	}

	public String getPrefijoTelefono() {
		return prefijoTelefono;
	}

	public void setPrefijoTelefono(String prefijoTelefono) {
		this.prefijoTelefono = prefijoTelefono;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getCodigoImpuesto() {
		return codigoImpuesto;
	}

	public void setCodigoImpuesto(String codigoImpuesto) {
		this.codigoImpuesto = codigoImpuesto;
	}

	public String getCaNumArGeo() {
		return caNumArGeo;
	}

	public void setCaNumArGeo(String caNumArGeo) {
		this.caNumArGeo = caNumArGeo;
	}

	/**
	 * Corresponde al nombre del estado de la república mexicana.
	 * @return nombre de un estado de la república mexicana.
	 */
	public String getNombreComunidadAutonoma() {
		return nombreComunidadAutonoma;
	}

	public void setNombreComunidadAutonoma(String nombreComunidadAutonoma) {
		this.nombreComunidadAutonoma = nombreComunidadAutonoma;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getNombrePais() {
		return nombrePais;
	}

	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}

	public String getMunicipioLocalidad() {
		return municipioLocalidad;
	}

	public void setMunicipioLocalidad(String municipioLocalidad) {
		this.municipioLocalidad = municipioLocalidad;
	}

	public String getCodArGeo() {
		return codArGeo;
	}

	public void setCodArGeo(String codArGeo) {
		this.codArGeo = codArGeo;
	}

	public String getCodigoLocalidadCompuesto() {
		return codigoLocalidadCompuesto;
	}

	public void setCodigoLocalidadCompuesto(String codigoLocalidadCompuesto) {
		this.codigoLocalidadCompuesto = codigoLocalidadCompuesto;
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(obj, this,
				new String[] { "serialVersionUID" });
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this,
				new String[] { "serialVersionUID" });
	}
	
    @Override
    public Object clone(){
    	Object clone = null;
    	
    	 try {
             clone = super.clone();
         }catch(CloneNotSupportedException e) {
             // No ocurre porque no heredamos de otra clase sino de Object
         }
    	 
    	 ((CatalogoGeoBean)clone).setId(this.getId() );
    	 
    	 ((CatalogoGeoBean)clone).setNumArGeo(this.getNumArGeo() );
    	 ((CatalogoGeoBean)clone).setCodigoPostal(this.getCodigoPostal() );
    	 ((CatalogoGeoBean)clone).setSubCodigoPostal(this.getSubCodigoPostal() );
    	 ((CatalogoGeoBean)clone).setNumAgLocine(this.getNumAgLocine() );
    	 ((CatalogoGeoBean)clone).setCodigoProvincia(this.getCodigoProvincia() );
    	
    	 ((CatalogoGeoBean)clone).setNombreLocalidad(this.getNombreLocalidad() );
    	 ((CatalogoGeoBean)clone).setIndPzaBancaria(this.getIndPzaBancaria() );
    	 ((CatalogoGeoBean)clone).setCodigoMunicipio(this.getCodigoMunicipio() );
    	 ((CatalogoGeoBean)clone).setCodigoEntidadColectiva(this.getCodigoEntidadColectiva() );
    	 ((CatalogoGeoBean)clone).setNombreMunicipio(this.getNombreMunicipio() );
    	 
    	 ((CatalogoGeoBean)clone).setPrNumArGeo(this.getPrNumArGeo() );
    	 ((CatalogoGeoBean)clone).setCodigoComunidadAutonoma(this.getCodigoComunidadAutonoma() );
    	 ((CatalogoGeoBean)clone).setNombreProvincia(this.getNombreProvincia() );
    	 ((CatalogoGeoBean)clone).setPrefijoTelefono(this.getPrefijoTelefono() );
    	 ((CatalogoGeoBean)clone).setMatricula(this.getMatricula() );
    	 
    	 ((CatalogoGeoBean)clone).setCodigoImpuesto(this.getCodigoImpuesto() );
    	 ((CatalogoGeoBean)clone).setCaNumArGeo(this.getCaNumArGeo() );
    	 ((CatalogoGeoBean)clone).setNombreComunidadAutonoma(this.getNombreComunidadAutonoma() );
    	 ((CatalogoGeoBean)clone).setCodigoPais(this.getCodigoPais() );
    	 ((CatalogoGeoBean)clone).setNombrePais(this.getNombrePais() );
    	 
    	 ((CatalogoGeoBean)clone).setMunicipioLocalidad( this.getMunicipioLocalidad() );
    	 ((CatalogoGeoBean)clone).setCodArGeo( this.getCodArGeo() );
    	 ((CatalogoGeoBean)clone).setCodigoLocalidadCompuesto( this.getCodigoLocalidadCompuesto() );

    	 return clone;
    }

	@Override
	public String toString() {
		return "CatalogoGeoBean [id=" + id + ",numArGeo=" + numArGeo + ", codigoPostal="
				+ codigoPostal + ", subCodigoPostal=" + subCodigoPostal
				+ ", numAgLocine=" + numAgLocine + ", codigoProvincia="
				+ codigoProvincia + ", nombreLocalidad=" + nombreLocalidad
				+ ", indPzaBancaria=" + indPzaBancaria + ", codigoMunicipio="
				+ codigoMunicipio + ", codigoEntidadColectiva="
				+ codigoEntidadColectiva + ", nombreMunicipio="
				+ nombreMunicipio + ", prNumArGeo=" + prNumArGeo
				+ ", codigoComunidadAutonoma=" + codigoComunidadAutonoma
				+ ", nombreProvincia=" + nombreProvincia + ", prefijoTelefono="
				+ prefijoTelefono + ", matricula=" + matricula
				+ ", codigoImpuesto=" + codigoImpuesto + ", caNumArGeo="
				+ caNumArGeo + ", nombreComunidadAutonoma="
				+ nombreComunidadAutonoma + ", codigoPais=" + codigoPais
				+ ", nombrePais=" + nombrePais + ", municipioLocalidad="
				+ municipioLocalidad + ", codArGeo=" + codArGeo
				+ ", codigoLocalidadCompuesto=" + codigoLocalidadCompuesto
				+ "]";
	}

}
