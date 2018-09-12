
package mx.babel.bansefi.banksystem.base.webservices.busquedapersonasfisicas;

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
 *         &lt;element name="NOMBRE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AP_PATERNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AP_MATERNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OFICINA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FECHA_NAC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "nombre",
    "appaterno",
    "apmaterno",
    "oficina",
    "fechanac",
    "domicilio"
})
public class ResponseBansefi {

    @XmlElement(name = "ID_INTERNO")
    protected BigInteger idinterno;
    @XmlElement(name = "ID_EXTERNO")
    protected String idexterno;
    @XmlElement(name = "NOMBRE")
    protected String nombre;
    @XmlElement(name = "AP_PATERNO")
    protected String appaterno;
    @XmlElement(name = "AP_MATERNO")
    protected String apmaterno;
    @XmlElement(name = "OFICINA")
    protected String oficina;
    @XmlElement(name = "FECHA_NAC")
    protected String fechanac;
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
     * Obtiene el valor de la propiedad fechanac.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFECHANAC() {
        return fechanac;
    }

    /**
     * Define el valor de la propiedad fechanac.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFECHANAC(String value) {
        this.fechanac = value;
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
