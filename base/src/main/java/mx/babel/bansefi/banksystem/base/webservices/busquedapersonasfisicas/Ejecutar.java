
package mx.babel.bansefi.banksystem.base.webservices.busquedapersonasfisicas;

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
 *         &lt;element name="NUM_IFE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ID_INTERNO" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="ID_EXTERNO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NOMBRE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AP_PATERNO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AP_MATERNO" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "numife",
    "idinterno",
    "idexterno",
    "nombre",
    "appaterno",
    "apmaterno"
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
    @XmlElement(name = "NUM_IFE", required = true)
    protected String numife;
    @XmlElement(name = "ID_INTERNO", required = true)
    protected BigInteger idinterno;
    @XmlElement(name = "ID_EXTERNO", required = true)
    protected String idexterno;
    @XmlElement(name = "NOMBRE", required = true)
    protected String nombre;
    @XmlElement(name = "AP_PATERNO", required = true)
    protected String appaterno;
    @XmlElement(name = "AP_MATERNO", required = true)
    protected String apmaterno;

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

    /**
     * Obtiene el valor de la propiedad idinterno.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIDINTERNO() {
        return idinterno;
    }

    /**
     * Define el valor de la propiedad idinterno.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIDINTERNO(BigInteger value) {
        this.idinterno = value;
    }

    /**
     * Obtiene el valor de la propiedad idexterno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDEXTERNO() {
        return idexterno;
    }

    /**
     * Define el valor de la propiedad idexterno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDEXTERNO(String value) {
        this.idexterno = value;
    }

    /**
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNOMBRE() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNOMBRE(String value) {
        this.nombre = value;
    }

    /**
     * Obtiene el valor de la propiedad appaterno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAPPATERNO() {
        return appaterno;
    }

    /**
     * Define el valor de la propiedad appaterno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAPPATERNO(String value) {
        this.appaterno = value;
    }

    /**
     * Obtiene el valor de la propiedad apmaterno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAPMATERNO() {
        return apmaterno;
    }

    /**
     * Define el valor de la propiedad apmaterno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAPMATERNO(String value) {
        this.apmaterno = value;
    }

}
