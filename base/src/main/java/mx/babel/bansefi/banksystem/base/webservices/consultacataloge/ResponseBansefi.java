
package mx.babel.bansefi.banksystem.base.webservices.consultacataloge;

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
 *         &lt;element name="CLAVE-FILA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CLAVE-FILA-SELECT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "clavefila",
    "clavefilaselect"
})
public class ResponseBansefi {

    @XmlElement(name = "CLAVE-FILA")
    protected String clavefila;
    @XmlElement(name = "CLAVE-FILA-SELECT")
    protected String clavefilaselect;

    /**
     * Obtiene el valor de la propiedad clavefila.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCLAVEFILA() {
        return clavefila;
    }

    /**
     * Define el valor de la propiedad clavefila.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCLAVEFILA(String value) {
        this.clavefila = value;
    }

    /**
     * Obtiene el valor de la propiedad clavefilaselect.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCLAVEFILASELECT() {
        return clavefilaselect;
    }

    /**
     * Define el valor de la propiedad clavefilaselect.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCLAVEFILASELECT(String value) {
        this.clavefilaselect = value;
    }

}
