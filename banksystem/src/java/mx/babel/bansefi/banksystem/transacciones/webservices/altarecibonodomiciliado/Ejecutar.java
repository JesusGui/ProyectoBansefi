
package mx.babel.bansefi.banksystem.transacciones.webservices.altarecibonodomiciliado;

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
 *         &lt;element name="CVESERV" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NUMTELRCBO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IMPORTE" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="TIPOPGO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DOMCTA" type="{http://www.w3.org/2001/XMLSchema}integer"/>
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
    "cveserv",
    "numtelrcbo",
    "importe",
    "tipopgo",
    "domcta"
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
    @XmlElement(name = "CVESERV", required = true)
    protected String cveserv;
    @XmlElement(name = "NUMTELRCBO", required = true)
    protected String numtelrcbo;
    @XmlElement(name = "IMPORTE", required = true)
    protected BigDecimal importe;
    @XmlElement(name = "TIPOPGO", required = true)
    protected String tipopgo;
    @XmlElement(name = "DOMCTA", required = true)
    protected BigInteger domcta;

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
     * Obtiene el valor de la propiedad cveserv.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCVESERV() {
        return cveserv;
    }

    /**
     * Define el valor de la propiedad cveserv.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCVESERV(String value) {
        this.cveserv = value;
    }

    /**
     * Obtiene el valor de la propiedad numtelrcbo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNUMTELRCBO() {
        return numtelrcbo;
    }

    /**
     * Define el valor de la propiedad numtelrcbo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNUMTELRCBO(String value) {
        this.numtelrcbo = value;
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
     * Obtiene el valor de la propiedad tipopgo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTIPOPGO() {
        return tipopgo;
    }

    /**
     * Define el valor de la propiedad tipopgo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTIPOPGO(String value) {
        this.tipopgo = value;
    }

    /**
     * Obtiene el valor de la propiedad domcta.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDOMCTA() {
        return domcta;
    }

    /**
     * Define el valor de la propiedad domcta.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDOMCTA(BigInteger value) {
        this.domcta = value;
    }

}
