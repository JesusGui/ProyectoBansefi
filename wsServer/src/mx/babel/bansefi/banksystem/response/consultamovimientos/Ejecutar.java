
package mx.babel.bansefi.banksystem.response.consultamovimientos;

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
 *         &lt;element name="FECHA_DESDE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FECHA_HASTA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NUM_SEC" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="CVEIDOF" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NUM_IFE" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "fechadesde",
    "fechahasta",
    "numsec",
    "cveidof",
    "numife"
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
    @XmlElement(name = "FECHA_DESDE", required = true)
    protected String fechadesde;
    @XmlElement(name = "FECHA_HASTA", required = true)
    protected String fechahasta;
    @XmlElement(name = "NUM_SEC", required = true)
    protected BigInteger numsec;
    @XmlElement(name = "CVEIDOF", required = true)
    protected String cveidof;
    @XmlElement(name = "NUM_IFE", required = true)
    protected String numife;

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
     * Obtiene el valor de la propiedad fechadesde.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFECHADESDE() {
        return fechadesde;
    }

    /**
     * Define el valor de la propiedad fechadesde.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFECHADESDE(String value) {
        this.fechadesde = value;
    }

    /**
     * Obtiene el valor de la propiedad fechahasta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFECHAHASTA() {
        return fechahasta;
    }

    /**
     * Define el valor de la propiedad fechahasta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFECHAHASTA(String value) {
        this.fechahasta = value;
    }

    /**
     * Obtiene el valor de la propiedad numsec.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNUMSEC() {
        return numsec;
    }

    /**
     * Define el valor de la propiedad numsec.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNUMSEC(BigInteger value) {
        this.numsec = value;
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
     * Obtiene el valor de la propiedad numife.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNUMIFE() {
        return numife;
    }

    /**
     * Define el valor de la propiedad numife.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNUMIFE(String value) {
        this.numife = value;
    }

}
