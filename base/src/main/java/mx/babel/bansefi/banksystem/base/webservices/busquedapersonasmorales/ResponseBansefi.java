
package mx.babel.bansefi.banksystem.base.webservices.busquedapersonasmorales;

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
 *         &lt;element name="ID_INTERNO" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="ID_EXTERNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DEN_LEGAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OFICINA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FECHA_CONSTITUCION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DOMICILIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "idinterno",
    "idexterno",
    "denlegal",
    "oficina",
    "fechaconstitucion",
    "domicilio"
})
public class ResponseBansefi {

    @XmlElement(name = "ID_INTERNO")
    protected BigInteger idinterno;
    @XmlElement(name = "ID_EXTERNO")
    protected String idexterno;
    @XmlElement(name = "DEN_LEGAL")
    protected String denlegal;
    @XmlElement(name = "OFICINA")
    protected String oficina;
    @XmlElement(name = "FECHA_CONSTITUCION")
    protected String fechaconstitucion;
    @XmlElement(name = "DOMICILIO")
    protected String domicilio;

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
     * Obtiene el valor de la propiedad denlegal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDENLEGAL() {
        return denlegal;
    }

    /**
     * Define el valor de la propiedad denlegal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDENLEGAL(String value) {
        this.denlegal = value;
    }

    /**
     * Obtiene el valor de la propiedad oficina.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOFICINA() {
        return oficina;
    }

    /**
     * Define el valor de la propiedad oficina.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOFICINA(String value) {
        this.oficina = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaconstitucion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFECHACONSTITUCION() {
        return fechaconstitucion;
    }

    /**
     * Define el valor de la propiedad fechaconstitucion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFECHACONSTITUCION(String value) {
        this.fechaconstitucion = value;
    }

    /**
     * Obtiene el valor de la propiedad domicilio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDOMICILIO() {
        return domicilio;
    }

    /**
     * Define el valor de la propiedad domicilio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDOMICILIO(String value) {
        this.domicilio = value;
    }

}
