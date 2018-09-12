
package mx.babel.bansefi.banksystem.cuentas.webservices.consultanivelacuerdo;

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
 *         &lt;element name="SAL-NIVEL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SAL-DESCR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SAL-RC" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
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
    "salnivel",
    "saldescr",
    "salrc"
})
public class ResponseBansefi {

    @XmlElement(name = "SAL-NIVEL")
    protected String salnivel;
    @XmlElement(name = "SAL-DESCR")
    protected String saldescr;
    @XmlElement(name = "SAL-RC")
    protected BigInteger salrc;

    /**
     * Obtiene el valor de la propiedad salnivel.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSALNIVEL() {
        return salnivel;
    }

    /**
     * Define el valor de la propiedad salnivel.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSALNIVEL(String value) {
        this.salnivel = value;
    }

    /**
     * Obtiene el valor de la propiedad saldescr.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSALDESCR() {
        return saldescr;
    }

    /**
     * Define el valor de la propiedad saldescr.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSALDESCR(String value) {
        this.saldescr = value;
    }

    /**
     * Obtiene el valor de la propiedad salrc.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSALRC() {
        return salrc;
    }

    /**
     * Define el valor de la propiedad salrc.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSALRC(BigInteger value) {
        this.salrc = value;
    }

}
