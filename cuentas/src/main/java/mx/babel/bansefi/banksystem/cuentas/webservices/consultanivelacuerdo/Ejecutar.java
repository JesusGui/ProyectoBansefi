
package mx.babel.bansefi.banksystem.cuentas.webservices.consultanivelacuerdo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="USERHEADER" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PASSHEADER" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IPHEADER" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LINEA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="GRP-PD" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ID-PDV" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ID-TRFA-PDV" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "userheader",
    "passheader",
    "ipheader",
    "linea",
    "grppd",
    "idpdv",
    "idtrfapdv"
})
@XmlRootElement(name = "Ejecutar")
public class Ejecutar {

    @XmlElement(name = "USERHEADER", required = true)
    protected String userheader;
    @XmlElement(name = "PASSHEADER", required = true)
    protected String passheader;
    @XmlElement(name = "IPHEADER", required = true)
    protected String ipheader;
    @XmlElement(name = "LINEA", required = true)
    protected String linea;
    @XmlElement(name = "GRP-PD", required = true)
    protected String grppd;
    @XmlElement(name = "ID-PDV", required = true)
    protected String idpdv;
    @XmlElement(name = "ID-TRFA-PDV", required = true)
    protected String idtrfapdv;

    /**
     * Obtiene el valor de la propiedad userheader.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUSERHEADER() {
        return userheader;
    }

    /**
     * Define el valor de la propiedad userheader.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUSERHEADER(String value) {
        this.userheader = value;
    }

    /**
     * Obtiene el valor de la propiedad passheader.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPASSHEADER() {
        return passheader;
    }

    /**
     * Define el valor de la propiedad passheader.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPASSHEADER(String value) {
        this.passheader = value;
    }

    /**
     * Obtiene el valor de la propiedad ipheader.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIPHEADER() {
        return ipheader;
    }

    /**
     * Define el valor de la propiedad ipheader.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIPHEADER(String value) {
        this.ipheader = value;
    }

    /**
     * Obtiene el valor de la propiedad linea.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLINEA() {
        return linea;
    }

    /**
     * Define el valor de la propiedad linea.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLINEA(String value) {
        this.linea = value;
    }

    /**
     * Obtiene el valor de la propiedad grppd.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGRPPD() {
        return grppd;
    }

    /**
     * Define el valor de la propiedad grppd.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGRPPD(String value) {
        this.grppd = value;
    }

    /**
     * Obtiene el valor de la propiedad idpdv.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDPDV() {
        return idpdv;
    }

    /**
     * Define el valor de la propiedad idpdv.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDPDV(String value) {
        this.idpdv = value;
    }

    /**
     * Obtiene el valor de la propiedad idtrfapdv.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDTRFAPDV() {
        return idtrfapdv;
    }

    /**
     * Define el valor de la propiedad idtrfapdv.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDTRFAPDV(String value) {
        this.idtrfapdv = value;
    }

}
