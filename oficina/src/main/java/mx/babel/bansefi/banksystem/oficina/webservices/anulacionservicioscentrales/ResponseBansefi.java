
package mx.babel.bansefi.banksystem.oficina.webservices.anulacionservicioscentrales;

import java.math.BigDecimal;
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
 *         &lt;element name="ESTATUS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MOVIMIENTO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ACUERDO" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="MONTO_TOTAL" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="TERMINAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FECHA_OPRCN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HORA_OPRCN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OFICINA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PLAZA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DIGITO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TITULAR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "estatus",
    "movimiento",
    "acuerdo",
    "montototal",
    "terminal",
    "fechaoprcn",
    "horaoprcn",
    "oficina",
    "plaza",
    "digito",
    "titular"
})
public class ResponseBansefi {

    @XmlElement(name = "ESTATUS")
    protected String estatus;
    @XmlElement(name = "MOVIMIENTO")
    protected String movimiento;
    @XmlElement(name = "ACUERDO")
    protected BigInteger acuerdo;
    @XmlElement(name = "MONTO_TOTAL")
    protected BigDecimal montototal;
    @XmlElement(name = "TERMINAL")
    protected String terminal;
    @XmlElement(name = "FECHA_OPRCN")
    protected String fechaoprcn;
    @XmlElement(name = "HORA_OPRCN")
    protected String horaoprcn;
    @XmlElement(name = "OFICINA")
    protected String oficina;
    @XmlElement(name = "PLAZA")
    protected String plaza;
    @XmlElement(name = "DIGITO")
    protected String digito;
    @XmlElement(name = "TITULAR")
    protected String titular;

    /**
     * Obtiene el valor de la propiedad estatus.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getESTATUS() {
        return estatus;
    }

    /**
     * Define el valor de la propiedad estatus.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setESTATUS(String value) {
        this.estatus = value;
    }

    /**
     * Obtiene el valor de la propiedad movimiento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMOVIMIENTO() {
        return movimiento;
    }

    /**
     * Define el valor de la propiedad movimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMOVIMIENTO(String value) {
        this.movimiento = value;
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
     * Obtiene el valor de la propiedad montototal.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMONTOTOTAL() {
        return montototal;
    }

    /**
     * Define el valor de la propiedad montototal.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMONTOTOTAL(BigDecimal value) {
        this.montototal = value;
    }

    /**
     * Obtiene el valor de la propiedad terminal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTERMINAL() {
        return terminal;
    }

    /**
     * Define el valor de la propiedad terminal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTERMINAL(String value) {
        this.terminal = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaoprcn.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFECHAOPRCN() {
        return fechaoprcn;
    }

    /**
     * Define el valor de la propiedad fechaoprcn.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFECHAOPRCN(String value) {
        this.fechaoprcn = value;
    }

    /**
     * Obtiene el valor de la propiedad horaoprcn.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHORAOPRCN() {
        return horaoprcn;
    }

    /**
     * Define el valor de la propiedad horaoprcn.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHORAOPRCN(String value) {
        this.horaoprcn = value;
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
     * Obtiene el valor de la propiedad plaza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPLAZA() {
        return plaza;
    }

    /**
     * Define el valor de la propiedad plaza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPLAZA(String value) {
        this.plaza = value;
    }

    /**
     * Obtiene el valor de la propiedad digito.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDIGITO() {
        return digito;
    }

    /**
     * Define el valor de la propiedad digito.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDIGITO(String value) {
        this.digito = value;
    }

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

}
