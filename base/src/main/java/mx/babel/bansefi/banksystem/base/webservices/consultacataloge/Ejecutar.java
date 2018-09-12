
package mx.babel.bansefi.banksystem.base.webservices.consultacataloge;

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
 *         &lt;element name="CLAVE_DE_ACTIVACION1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CLAVE_DE_FILA_NOMBRE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CLAVE_DE_PAGINACION1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NUMERO_DE_REGISTROS1" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "clavedeactivacion1",
    "clavedefilanombre",
    "clavedepaginacion1",
    "numeroderegistros1"
})
@XmlRootElement(name = "Ejecutar")
public class Ejecutar {

    @XmlElement(name = "USERHEADER", required = true)
    protected String userheader;
    @XmlElement(name = "PASSHEADER", required = true)
    protected String passheader;
    @XmlElement(name = "IPHEADER", required = true)
    protected String ipheader;
    @XmlElement(name = "CLAVE_DE_ACTIVACION1", required = true)
    protected String clavedeactivacion1;
    @XmlElement(name = "CLAVE_DE_FILA_NOMBRE", required = true)
    protected String clavedefilanombre;
    @XmlElement(name = "CLAVE_DE_PAGINACION1", required = true)
    protected String clavedepaginacion1;
    @XmlElement(name = "NUMERO_DE_REGISTROS1", required = true)
    protected String numeroderegistros1;

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
     * Obtiene el valor de la propiedad clavedeactivacion1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCLAVEDEACTIVACION1() {
        return clavedeactivacion1;
    }

    /**
     * Define el valor de la propiedad clavedeactivacion1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCLAVEDEACTIVACION1(String value) {
        this.clavedeactivacion1 = value;
    }

    /**
     * Obtiene el valor de la propiedad clavedefilanombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCLAVEDEFILANOMBRE() {
        return clavedefilanombre;
    }

    /**
     * Define el valor de la propiedad clavedefilanombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCLAVEDEFILANOMBRE(String value) {
        this.clavedefilanombre = value;
    }

    /**
     * Obtiene el valor de la propiedad clavedepaginacion1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCLAVEDEPAGINACION1() {
        return clavedepaginacion1;
    }

    /**
     * Define el valor de la propiedad clavedepaginacion1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCLAVEDEPAGINACION1(String value) {
        this.clavedepaginacion1 = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroderegistros1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNUMERODEREGISTROS1() {
        return numeroderegistros1;
    }

    /**
     * Define el valor de la propiedad numeroderegistros1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNUMERODEREGISTROS1(String value) {
        this.numeroderegistros1 = value;
    }

}
