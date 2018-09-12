
package mx.babel.bansefi.banksystem.transacciones.webservices.validaemisora;

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
 *         &lt;element name="LONREFER" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="COMISCTE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="COMINCTE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="COMITIPO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "lonrefer",
    "comiscte",
    "comincte",
    "comitipo"
})
public class ResponseBansefi {

    @XmlElement(name = "LONREFER")
    protected String lonrefer;
    @XmlElement(name = "COMISCTE")
    protected String comiscte;
    @XmlElement(name = "COMINCTE")
    protected String comincte;
    @XmlElement(name = "COMITIPO")
    protected String comitipo;

    /**
     * Obtiene el valor de la propiedad lonrefer.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLONREFER() {
        return lonrefer;
    }

    /**
     * Define el valor de la propiedad lonrefer.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLONREFER(String value) {
        this.lonrefer = value;
    }

    /**
     * Obtiene el valor de la propiedad comiscte.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCOMISCTE() {
        return comiscte;
    }

    /**
     * Define el valor de la propiedad comiscte.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCOMISCTE(String value) {
        this.comiscte = value;
    }

    /**
     * Obtiene el valor de la propiedad comincte.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCOMINCTE() {
        return comincte;
    }

    /**
     * Define el valor de la propiedad comincte.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCOMINCTE(String value) {
        this.comincte = value;
    }

    /**
     * Obtiene el valor de la propiedad comitipo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCOMITIPO() {
        return comitipo;
    }

    /**
     * Define el valor de la propiedad comitipo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCOMITIPO(String value) {
        this.comitipo = value;
    }

}
