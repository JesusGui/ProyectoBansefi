
package mx.babel.bansefi.banksystem.base.webservices.consultatitularws;

import java.math.BigInteger;
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
 *         &lt;element name="ID-INTERNO-PE" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="NOMBRE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ID-RFC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ID-CURP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NUM-RL-ORDEN" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="NIVEL-CUENTA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "idinternope",
    "nombre",
    "idrfc",
    "idcurp",
    "numrlorden",
    "nivelcuenta"
})
public class ResponseBansefi {

    @XmlElement(name = "ID-INTERNO-PE")
    protected BigInteger idinternope;
    @XmlElement(name = "NOMBRE")
    protected String nombre;
    @XmlElement(name = "ID-RFC")
    protected String idrfc;
    @XmlElement(name = "ID-CURP")
    protected String idcurp;
    @XmlElement(name = "NUM-RL-ORDEN")
    protected BigInteger numrlorden;
    @XmlElement(name = "NIVEL-CUENTA")
    protected String nivelcuenta;

    /**
     * Obtiene el valor de la propiedad idinternope.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIDINTERNOPE() {
        return idinternope;
    }

    /**
     * Define el valor de la propiedad idinternope.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIDINTERNOPE(BigInteger value) {
        this.idinternope = value;
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
     * Obtiene el valor de la propiedad idrfc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDRFC() {
        return idrfc;
    }

    /**
     * Define el valor de la propiedad idrfc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDRFC(String value) {
        this.idrfc = value;
    }

    /**
     * Obtiene el valor de la propiedad idcurp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDCURP() {
        return idcurp;
    }

    /**
     * Define el valor de la propiedad idcurp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDCURP(String value) {
        this.idcurp = value;
    }

    /**
     * Obtiene el valor de la propiedad numrlorden.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNUMRLORDEN() {
        return numrlorden;
    }

    /**
     * Define el valor de la propiedad numrlorden.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNUMRLORDEN(BigInteger value) {
        this.numrlorden = value;
    }

    /**
     * Obtiene el valor de la propiedad nivelcuenta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNIVELCUENTA() {
        return nivelcuenta;
    }

    /**
     * Define el valor de la propiedad nivelcuenta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNIVELCUENTA(String value) {
        this.nivelcuenta = value;
    }

}
