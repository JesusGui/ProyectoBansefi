
package mx.babel.bansefi.banksystem.oficina.webservices.busquedaapunte;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ResponseBansefi complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ResponseBansefi">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FECHA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IDTASK" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ENTIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CENTRO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TERMIN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="USUARIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SERVICIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CODIGO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ESTADO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ACUERDO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SECUENCIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IMPORTE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HORAINICIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HORAFIN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TIMEEJEC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TERMINANUL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="USUARIOANUL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SERVICANUL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HORAANUL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IDTASKANUL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ENTRADA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SALIDA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseBansefi", propOrder = {
    "fecha",
    "idtask",
    "entidad",
    "centro",
    "termin",
    "usuario",
    "servicio",
    "codigo",
    "estado",
    "acuerdo",
    "secuencia",
    "importe",
    "horainicio",
    "horafin",
    "timeejec",
    "terminanul",
    "usuarioanul",
    "servicanul",
    "horaanul",
    "idtaskanul",
    "entrada",
    "salida"
})
public class ResponseBansefi {

    @XmlElement(name = "FECHA")
    protected String fecha;
    @XmlElement(name = "IDTASK")
    protected String idtask;
    @XmlElement(name = "ENTIDAD")
    protected String entidad;
    @XmlElement(name = "CENTRO")
    protected String centro;
    @XmlElement(name = "TERMIN")
    protected String termin;
    @XmlElement(name = "USUARIO")
    protected String usuario;
    @XmlElement(name = "SERVICIO")
    protected String servicio;
    @XmlElement(name = "CODIGO")
    protected String codigo;
    @XmlElement(name = "ESTADO")
    protected String estado;
    @XmlElement(name = "ACUERDO")
    protected String acuerdo;
    @XmlElement(name = "SECUENCIA")
    protected String secuencia;
    @XmlElement(name = "IMPORTE")
    protected String importe;
    @XmlElement(name = "HORAINICIO")
    protected String horainicio;
    @XmlElement(name = "HORAFIN")
    protected String horafin;
    @XmlElement(name = "TIMEEJEC")
    protected String timeejec;
    @XmlElement(name = "TERMINANUL")
    protected String terminanul;
    @XmlElement(name = "USUARIOANUL")
    protected String usuarioanul;
    @XmlElement(name = "SERVICANUL")
    protected String servicanul;
    @XmlElement(name = "HORAANUL")
    protected String horaanul;
    @XmlElement(name = "IDTASKANUL")
    protected String idtaskanul;
    @XmlElement(name = "ENTRADA")
    protected String entrada;
    @XmlElement(name = "SALIDA")
    protected String salida;

    /**
     * Obtiene el valor de la propiedad fecha.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFECHA() {
        return fecha;
    }

    /**
     * Define el valor de la propiedad fecha.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFECHA(String value) {
        this.fecha = value;
    }

    /**
     * Obtiene el valor de la propiedad idtask.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDTASK() {
        return idtask;
    }

    /**
     * Define el valor de la propiedad idtask.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDTASK(String value) {
        this.idtask = value;
    }

    /**
     * Obtiene el valor de la propiedad entidad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getENTIDAD() {
        return entidad;
    }

    /**
     * Define el valor de la propiedad entidad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setENTIDAD(String value) {
        this.entidad = value;
    }

    /**
     * Obtiene el valor de la propiedad centro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCENTRO() {
        return centro;
    }

    /**
     * Define el valor de la propiedad centro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCENTRO(String value) {
        this.centro = value;
    }

    /**
     * Obtiene el valor de la propiedad termin.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTERMIN() {
        return termin;
    }

    /**
     * Define el valor de la propiedad termin.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTERMIN(String value) {
        this.termin = value;
    }

    /**
     * Obtiene el valor de la propiedad usuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUSUARIO() {
        return usuario;
    }

    /**
     * Define el valor de la propiedad usuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUSUARIO(String value) {
        this.usuario = value;
    }

    /**
     * Obtiene el valor de la propiedad servicio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSERVICIO() {
        return servicio;
    }

    /**
     * Define el valor de la propiedad servicio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSERVICIO(String value) {
        this.servicio = value;
    }

    /**
     * Obtiene el valor de la propiedad codigo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCODIGO() {
        return codigo;
    }

    /**
     * Define el valor de la propiedad codigo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCODIGO(String value) {
        this.codigo = value;
    }

    /**
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getESTADO() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setESTADO(String value) {
        this.estado = value;
    }

    /**
     * Obtiene el valor de la propiedad acuerdo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getACUERDO() {
        return acuerdo;
    }

    /**
     * Define el valor de la propiedad acuerdo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setACUERDO(String value) {
        this.acuerdo = value;
    }

    /**
     * Obtiene el valor de la propiedad secuencia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSECUENCIA() {
        return secuencia;
    }

    /**
     * Define el valor de la propiedad secuencia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSECUENCIA(String value) {
        this.secuencia = value;
    }

    /**
     * Obtiene el valor de la propiedad importe.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIMPORTE() {
        return importe;
    }

    /**
     * Define el valor de la propiedad importe.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIMPORTE(String value) {
        this.importe = value;
    }

    /**
     * Obtiene el valor de la propiedad horainicio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHORAINICIO() {
        return horainicio;
    }

    /**
     * Define el valor de la propiedad horainicio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHORAINICIO(String value) {
        this.horainicio = value;
    }

    /**
     * Obtiene el valor de la propiedad horafin.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHORAFIN() {
        return horafin;
    }

    /**
     * Define el valor de la propiedad horafin.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHORAFIN(String value) {
        this.horafin = value;
    }

    /**
     * Obtiene el valor de la propiedad timeejec.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTIMEEJEC() {
        return timeejec;
    }

    /**
     * Define el valor de la propiedad timeejec.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTIMEEJEC(String value) {
        this.timeejec = value;
    }

    /**
     * Obtiene el valor de la propiedad terminanul.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTERMINANUL() {
        return terminanul;
    }

    /**
     * Define el valor de la propiedad terminanul.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTERMINANUL(String value) {
        this.terminanul = value;
    }

    /**
     * Obtiene el valor de la propiedad usuarioanul.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUSUARIOANUL() {
        return usuarioanul;
    }

    /**
     * Define el valor de la propiedad usuarioanul.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUSUARIOANUL(String value) {
        this.usuarioanul = value;
    }

    /**
     * Obtiene el valor de la propiedad servicanul.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSERVICANUL() {
        return servicanul;
    }

    /**
     * Define el valor de la propiedad servicanul.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSERVICANUL(String value) {
        this.servicanul = value;
    }

    /**
     * Obtiene el valor de la propiedad horaanul.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHORAANUL() {
        return horaanul;
    }

    /**
     * Define el valor de la propiedad horaanul.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHORAANUL(String value) {
        this.horaanul = value;
    }

    /**
     * Obtiene el valor de la propiedad idtaskanul.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDTASKANUL() {
        return idtaskanul;
    }

    /**
     * Define el valor de la propiedad idtaskanul.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDTASKANUL(String value) {
        this.idtaskanul = value;
    }

    /**
     * Obtiene el valor de la propiedad entrada.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getENTRADA() {
        return entrada;
    }

    /**
     * Define el valor de la propiedad entrada.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setENTRADA(String value) {
        this.entrada = value;
    }

    /**
     * Obtiene el valor de la propiedad salida.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSALIDA() {
        return salida;
    }

    /**
     * Define el valor de la propiedad salida.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSALIDA(String value) {
        this.salida = value;
    }

}
