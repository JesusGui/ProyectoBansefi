
package mx.babel.bansefi.banksystem.base.webservices.consultasaldo;

import java.math.BigDecimal;

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
 *         &lt;element name="TITULAR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SALDO_CONTABLE" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="TOTAL_RETENIDO" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="TOTAL_AUTORIZADO" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="DISPONIBLE" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="CENTRO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MONEDA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CLABE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "titular",
    "saldocontable",
    "totalretenido",
    "totalautorizado",
    "disponible",
    "centro",
    "dc",
    "moneda",
    "clabe"
})
public class ResponseBansefi {

    @XmlElement(name = "TITULAR")
    protected String titular;
    @XmlElement(name = "SALDO_CONTABLE")
    protected BigDecimal saldocontable;
    @XmlElement(name = "TOTAL_RETENIDO")
    protected BigDecimal totalretenido;
    @XmlElement(name = "TOTAL_AUTORIZADO")
    protected BigDecimal totalautorizado;
    @XmlElement(name = "DISPONIBLE")
    protected BigDecimal disponible;
    @XmlElement(name = "CENTRO")
    protected String centro;
    @XmlElement(name = "DC")
    protected String dc;
    @XmlElement(name = "MONEDA")
    protected String moneda;
    @XmlElement(name = "CLABE")
    protected String clabe;

    /**
     * Obtiene el valor de la propiedad titular.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTITULAR() {
        return titular;
    }

    /**
     * Define el valor de la propiedad titular.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTITULAR(String value) {
        this.titular = value;
    }

    /**
     * Obtiene el valor de la propiedad saldocontable.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSALDOCONTABLE() {
        return saldocontable;
    }

    /**
     * Define el valor de la propiedad saldocontable.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSALDOCONTABLE(BigDecimal value) {
        this.saldocontable = value;
    }

    /**
     * Obtiene el valor de la propiedad totalretenido.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTOTALRETENIDO() {
        return totalretenido;
    }

    /**
     * Define el valor de la propiedad totalretenido.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTOTALRETENIDO(BigDecimal value) {
        this.totalretenido = value;
    }

    /**
     * Obtiene el valor de la propiedad totalautorizado.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTOTALAUTORIZADO() {
        return totalautorizado;
    }

    /**
     * Define el valor de la propiedad totalautorizado.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTOTALAUTORIZADO(BigDecimal value) {
        this.totalautorizado = value;
    }

    /**
     * Obtiene el valor de la propiedad disponible.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDISPONIBLE() {
        return disponible;
    }

    /**
     * Define el valor de la propiedad disponible.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDISPONIBLE(BigDecimal value) {
        this.disponible = value;
    }

    /**
     * Obtiene el valor de la propiedad centro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCENTRO() {
        return centro;
    }

    /**
     * Define el valor de la propiedad centro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCENTRO(String value) {
        this.centro = value;
    }

    /**
     * Obtiene el valor de la propiedad dc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDC() {
        return dc;
    }

    /**
     * Define el valor de la propiedad dc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDC(String value) {
        this.dc = value;
    }

    /**
     * Obtiene el valor de la propiedad moneda.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMONEDA() {
        return moneda;
    }

    /**
     * Define el valor de la propiedad moneda.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMONEDA(String value) {
        this.moneda = value;
    }

    /**
     * Obtiene el valor de la propiedad clabe.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCLABE() {
        return clabe;
    }

    /**
     * Define el valor de la propiedad clabe.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCLABE(String value) {
        this.clabe = value;
    }

}
