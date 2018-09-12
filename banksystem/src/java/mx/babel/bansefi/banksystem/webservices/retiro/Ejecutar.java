
package mx.babel.bansefi.banksystem.webservices.retiro;

import java.math.BigDecimal;
import java.math.BigInteger;
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
 *         &lt;element name="ENTIDAD" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ACUERDO" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="IMPORTE" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="FECVALOR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CODOPE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CVEIDOF" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IFE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CONCEPTO" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "entidad",
    "acuerdo",
    "importe",
    "fecvalor",
    "codope",
    "cveidof",
    "ife",
    "concepto"
})
@XmlRootElement(name = "Ejecutar")
public class Ejecutar {

    @XmlElement(name = "USERHEADER", required = true)
    protected String userheader;
    @XmlElement(name = "PASSHEADER", required = true)
    protected String passheader;
    @XmlElement(name = "IPHEADER", required = true)
    protected String ipheader;
    @XmlElement(name = "ENTIDAD", required = true)
    protected String entidad;
    @XmlElement(name = "ACUERDO", required = true)
    protected BigInteger acuerdo;
    @XmlElement(name = "IMPORTE", required = true)
    protected BigDecimal importe;
    @XmlElement(name = "FECVALOR", required = true)
    protected String fecvalor;
    @XmlElement(name = "CODOPE", required = true)
    protected String codope;
    @XmlElement(name = "CVEIDOF", required = true)
    protected String cveidof;
    @XmlElement(name = "IFE", required = true)
    protected String ife;
    @XmlElement(name = "CONCEPTO", required = true)
    protected String concepto;

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
     * Obtiene el valor de la propiedad acuerdo.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getACUERDO() {
        return acuerdo;
    }

    /**
     * Define el valor de la propiedad acuerdo.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setACUERDO(BigInteger value) {
        this.acuerdo = value;
    }

    /**
     * Obtiene el valor de la propiedad importe.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIMPORTE() {
        return importe;
    }

    /**
     * Define el valor de la propiedad importe.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIMPORTE(BigDecimal value) {
        this.importe = value;
    }

    /**
     * Obtiene el valor de la propiedad fecvalor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFECVALOR() {
        return fecvalor;
    }

    /**
     * Define el valor de la propiedad fecvalor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFECVALOR(String value) {
        this.fecvalor = value;
    }

    /**
     * Obtiene el valor de la propiedad codope.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCODOPE() {
        return codope;
    }

    /**
     * Define el valor de la propiedad codope.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCODOPE(String value) {
        this.codope = value;
    }

    /**
     * Obtiene el valor de la propiedad cveidof.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCVEIDOF() {
        return cveidof;
    }

    /**
     * Define el valor de la propiedad cveidof.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCVEIDOF(String value) {
        this.cveidof = value;
    }

    /**
     * Obtiene el valor de la propiedad ife.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIFE() {
        return ife;
    }

    /**
     * Define el valor de la propiedad ife.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIFE(String value) {
        this.ife = value;
    }

    /**
     * Obtiene el valor de la propiedad concepto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCONCEPTO() {
        return concepto;
    }

    /**
     * Define el valor de la propiedad concepto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCONCEPTO(String value) {
        this.concepto = value;
    }

}
