package mx.babel.bansefi.banksystem.base.beans.domain;

import java.io.Serializable;

import mx.babel.arq.catalogo.beans.CatalogoGeoBean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.stereotype.Component;

/**
 * Clase para definir el modelo de Domicilio.
 * 
 * @author mario.montesdeoca
 * 
 */
@Component
public class DomicilioBean implements Serializable, Cloneable {

	private static final long serialVersionUID = 5679696182587952684L;

	private CatalogoGeoBean codigoPostalCatGeo;
	
	private CatalogoGeoBean municipioCatGeo;
	
	private CatalogoGeoBean datosFinalesCatGeo;
	
	private int numeroDireccion;

	private String codigoPostal;

	private String municipio;

	private String estado;

	private String pais;

	private String tipoCalle;

	private String nombreCalle;

	private String numeroExterior;

	private String interior;

	private String departamento;

	private String casa;

	private String colonia;

	private String comprobanteDomicilio;

	private String descripcion;

	private String regimenOcupacion;

	private String unidadHabitacional;

	private String edificio;

	private String bloque;

	private String entrada;

	private String fase;

	private String lote;

	private String manzana;

	private String otrosDatos;

	private String telefono;

	private String codArGeo;

	private int numArGeo;
	
	/**
	 * @return Atributo numeroDireccion
	 */
	public int getNumeroDireccion() {
		return numeroDireccion;
	}

	/**
	 * @param numeroDireccion
	 *            Atributo numeroDireccion a definir
	 */
	public void setNumeroDireccion(int numeroDireccion) {
		this.numeroDireccion = numeroDireccion;
	}

	/**
	 * El componente de domicilio lo utiliza para tener el último código postal capturado
	 * @deprecated utilizar los CatalogoGeoBean
	 * @return Atributo codigoPostal
	 */
	public String getCodigoPostal() {
		return codigoPostal;
	}

	/**
	 * @deprecated utilizar los CatalogoGeoBean
	 * @param codigoPostal
	 *            Atributo codigoPostal a definir
	 */
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	/**
	 * El componente de domicilio lo utiliza para tener el último municipio capturado
	 * @deprecated utilizar los CatalogoGeoBean
	 * @return Atributo municipio
	 */
	public String getMunicipio() {
		return municipio;
	}

	/**
	 * @deprecated utilizar los CatalogoGeoBean
	 * @param municipio
	 *            Atributo municipio a definir
	 */
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	/**
	 * @deprecated utilizar los CatalogoGeoBean
	 * @return Atributo estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @deprecated utilizar los CatalogoGeoBean
	 * @param estado
	 *            Atributo estado a definir
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @deprecated utilizar los CatalogoGeoBean
	 * @return Atributo pais
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * @deprecated utilizar los CatalogoGeoBean
	 * @param pais
	 *            Atributo pais a definir
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}

	/**
	 * @return Atributo tipoCalle
	 */
	public String getTipoCalle() {
		return tipoCalle;
	}

	/**
	 * @param tipoCalle
	 *            Atributo tipoCalle a definir
	 */
	public void setTipoCalle(String tipoCalle) {
		this.tipoCalle = tipoCalle;
	}

	/**
	 * @return Atributo nombreCalle
	 */
	public String getNombreCalle() {
		return nombreCalle;
	}

	/**
	 * @param nombreCalle
	 *            Atributo nombreCalle a definir
	 */
	public void setNombreCalle(String nombreCalle) {
		this.nombreCalle = nombreCalle;
	}

	/**
	 * @return Atributo numeroExterior
	 */
	public String getNumeroExterior() {
		return numeroExterior;
	}

	/**
	 * @param numeroExterior
	 *            Atributo numeroExterior a definir
	 */
	public void setNumeroExterior(String numeroExterior) {
		this.numeroExterior = numeroExterior;
	}

	/**
	 * @return Atributo departamento
	 */
	public String getDepartamento() {
		return departamento;
	}

	/**
	 * @param departamento
	 *            Atributo departamento a definir
	 */
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	/**
	 * @return Atributo casa
	 */
	public String getCasa() {
		return casa;
	}

	/**
	 * @param casa
	 *            Atributo casa a definir
	 */
	public void setCasa(String casa) {
		this.casa = casa;
	}

	/**
	 * @return Atributo colonia
	 */
	public String getColonia() {
		return colonia;
	}

	/**
	 * @param colonia
	 *            Atributo colonia a definir
	 */
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	/**
	 * @return Atributo comprobanteDomicilio
	 */
	public String getComprobanteDomicilio() {
		return comprobanteDomicilio;
	}

	/**
	 * @param comprobanteDomicilio
	 *            Atributo comprobanteDomicilio a definir
	 */
	public void setComprobanteDomicilio(String comprobanteDomicilio) {
		this.comprobanteDomicilio = comprobanteDomicilio;
	}

	/**
	 * @return Atributo descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 *            Atributo descripcion a definir
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return Atributo regimenOcupacion
	 */
	public String getRegimenOcupacion() {
		return regimenOcupacion;
	}

	/**
	 * @param regimenOcupacion
	 *            Atributo regimenOcupacion a definir
	 */
	public void setRegimenOcupacion(String regimenOcupacion) {
		this.regimenOcupacion = regimenOcupacion;
	}

	/**
	 * @return Atributo unidadHabitacional
	 */
	public String getUnidadHabitacional() {
		return unidadHabitacional;
	}

	/**
	 * @param unidadHabitacional
	 *            Atributo unidadHabitacional a definir
	 */
	public void setUnidadHabitacional(String unidadHabitacional) {
		this.unidadHabitacional = unidadHabitacional;
	}

	/**
	 * @return Atributo edificio
	 */
	public String getEdificio() {
		return edificio;
	}

	/**
	 * @param edificio
	 *            Atributo edificio a definir
	 */
	public void setEdificio(String edificio) {
		this.edificio = edificio;
	}

	/**
	 * @return Atributo bloque
	 */
	public String getBloque() {
		return bloque;
	}

	/**
	 * @param bloque
	 *            Atributo bloque a definir
	 */
	public void setBloque(String bloque) {
		this.bloque = bloque;
	}

	/**
	 * @return Atributo entrada
	 */
	public String getEntrada() {
		return entrada;
	}

	/**
	 * @param entrada
	 *            Atributo entrada a definir
	 */
	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}

	/**
	 * @return Atributo fase
	 */
	public String getFase() {
		return fase;
	}

	/**
	 * @param fase
	 *            Atributo fase a definir
	 */
	public void setFase(String fase) {
		this.fase = fase;
	}

	/**
	 * @return Atributo lote
	 */
	public String getLote() {
		return lote;
	}

	/**
	 * @param lote
	 *            Atributo lote a definir
	 */
	public void setLote(String lote) {
		this.lote = lote;
	}

	/**
	 * @return Atributo manzana
	 */
	public String getManzana() {
		return manzana;
	}

	/**
	 * @param manzana
	 *            Atributo manzana a definir
	 */
	public void setManzana(String manzana) {
		this.manzana = manzana;
	}

	/**
	 * @return Atributo otrosDatos
	 */
	public String getOtrosDatos() {
		return otrosDatos;
	}

	/**
	 * @param otrosDatos
	 *            Atributo otrosDatos a definir
	 */
	public void setOtrosDatos(String otrosDatos) {
		this.otrosDatos = otrosDatos;
	}

	/**
	 * @return Atributo telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono
	 *            Atributo telefono a definir
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCodArGeo() {
		return codArGeo;
	}

	public void setCodArGeo(String codArGeo) {
		this.codArGeo = codArGeo;
	}

	public int getNumArGeo() {
		return numArGeo;
	}

	public void setNumArGeo(int numArGeo) {
		this.numArGeo = numArGeo;
	}

	/**
	 * Este método devuelve un string con el formato de dirección utilizado en
	 * las vistas para un domicilio.
	 * 
	 * @return String con formato de dirección
	 */
	public String getDireccion() {
		String domicilio = tipoCalle + " " + nombreCalle + " " + numeroExterior
				+ ", ";
		domicilio = domicilio + colonia + ", ";
		domicilio = domicilio + municipio + ", ";
		domicilio = domicilio + estado ;
		
		domicilio = domicilio.replaceAll("null,", "").replaceAll("null", "")
				.trim();
		return domicilio;
	}


	public String getInterior() {
		return interior;
	}

	public void setInterior(String interior) {
		this.interior = interior;
	}

	public CatalogoGeoBean getCodigoPostalCatGeo() {
		return codigoPostalCatGeo;
	}

	public void setCodigoPostalCatGeo(CatalogoGeoBean codigoPostalCatGeo) {
		this.codigoPostalCatGeo = codigoPostalCatGeo;
	}

	public CatalogoGeoBean getMunicipioCatGeo() {
		return municipioCatGeo;
	}

	public void setMunicipioCatGeo(CatalogoGeoBean municipioCatGeo) {
		this.municipioCatGeo = municipioCatGeo;
	}

	public CatalogoGeoBean getDatosFinalesCatGeo() {
		return datosFinalesCatGeo;
	}

	public void setDatosFinalesCatGeo(CatalogoGeoBean datosFinalesCatGeo) {
		this.datosFinalesCatGeo = datosFinalesCatGeo;
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
	public String toString() {
		return "DomicilioBean [codigoPostalCatGeo=" + codigoPostalCatGeo
				+ ", municipioCatGeo=" + municipioCatGeo
				+ ", datosFinalesCatGeo=" + datosFinalesCatGeo
				+ ", numeroDireccion=" + numeroDireccion + ", codigoPostal="
				+ codigoPostal + ", municipio=" + municipio + ", estado="
				+ estado + ", pais=" + pais + ", tipoCalle=" + tipoCalle
				+ ", nombreCalle=" + nombreCalle + ", numeroExterior="
				+ numeroExterior + ", interior=" + interior + ", departamento="
				+ departamento + ", casa=" + casa + ", colonia=" + colonia
				+ ", comprobanteDomicilio=" + comprobanteDomicilio
				+ ", descripcion=" + descripcion + ", regimenOcupacion="
				+ regimenOcupacion + ", unidadHabitacional="
				+ unidadHabitacional + ", edificio=" + edificio + ", bloque="
				+ bloque + ", entrada=" + entrada + ", fase=" + fase
				+ ", lote=" + lote + ", manzana=" + manzana + ", otrosDatos="
				+ otrosDatos + ", telefono=" + telefono + ", codArGeo="
				+ codArGeo + ", numArGeo=" + numArGeo + "]";
	}
	
	public Object clone(){
        Object obj=null;
        try{
            obj=super.clone();
        }catch(CloneNotSupportedException ex){
        }
        return obj;
    }

	
}
