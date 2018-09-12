
package mx.babel.bansefi.banksystem.transacciones.webservices.altarecibonodomiciliado;

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
 *         &lt;element name="HORA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SUCDESC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="REFEREN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IDTRAN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NUMOPER" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LEYENDA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IMPORTE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IMPCOMI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IMPIVA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IMPTOTAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "hora",
    "sucdesc",
    "referen",
    "idtran",
    "numoper",
    "leyenda",
    "importe",
    "impcomi",
    "impiva",
    "imptotal"
})
public class ResponseBansefi {

    @XmlElement(name = "FECHA")
    protected String fecha;
    @XmlElement(name = "HORA")
    protected String hora;
    @XmlElement(name = "SUCDESC")
    protected String sucdesc;
    @XmlElement(name = "REFEREN")
    protected String referen;
    @XmlElement(name = "IDTRAN")
    protected String idtran;
    @XmlElement(name = "NUMOPER")
    protected String numoper;
    @XmlElement(name = "LEYENDA")
    protected String leyenda;
    @XmlElement(name = "IMPORTE")
    protected String importe;
    @XmlElement(name = "IMPCOMI")
    protected String impcomi;
    @XmlElement(name = "IMPIVA")
    protected String impiva;
    @XmlElement(name = "IMPTOTAL")
    protected String imptotal;

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
     * Obtiene el valor de la propiedad hora.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHORA() {
        return hora;
    }

    /**
     * Define el valor de la propiedad hora.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHORA(String value) {
        this.hora = value;
    }

    /**
     * Obtiene el valor de la propiedad sucdesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSUCDESC() {
        return sucdesc;
    }

    /**
     * Define el valor de la propiedad sucdesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSUCDESC(String value) {
        this.sucdesc = value;
    }

    /**
     * Obtiene el valor de la propiedad referen.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREFEREN() {
        return referen;
    }

    /**
     * Define el valor de la propiedad referen.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREFEREN(String value) {
        this.referen = value;
    }

    /**
     * Obtiene el valor de la propiedad idtran.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDTRAN() {
        return idtran;
    }

    /**
     * Define el valor de la propiedad idtran.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDTRAN(String value) {
        this.idtran = value;
    }

    /**
     * Obtiene el valor de la propiedad numoper.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNUMOPER() {
        return numoper;
    }

    /**
     * Define el valor de la propiedad numoper.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNUMOPER(String value) {
        this.numoper = value;
    }

    /**
     * Obtiene el valor de la propiedad leyenda.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLEYENDA() {
        return leyenda;
    }

    /**
     * Define el valor de la propiedad leyenda.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLEYENDA(String value) {
        this.leyenda = value;
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
     * Obtiene el valor de la propiedad impcomi.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIMPCOMI() {
        return impcomi;
    }

    /**
     * Define el valor de la propiedad impcomi.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIMPCOMI(String value) {
        this.impcomi = value;
    }

    /**
     * Obtiene el valor de la propiedad impiva.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIMPIVA() {
        return impiva;
    }

    /**
     * Define el valor de la propiedad impiva.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIMPIVA(String value) {
        this.impiva = value;
    }

    /**
     * Obtiene el valor de la propiedad imptotal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIMPTOTAL() {
        return imptotal;
    }

    /**
     * Define el valor de la propiedad imptotal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIMPTOTAL(String value) {
        this.imptotal = value;
    }

}
