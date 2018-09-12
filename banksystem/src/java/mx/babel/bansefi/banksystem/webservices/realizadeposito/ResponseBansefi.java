
package mx.babel.bansefi.banksystem.webservices.realizadeposito;

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
 *         &lt;element name="ACUERDO" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="IMPORTE" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="ESTATUS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MOVIMIENTO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TERMINAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FECHAOPERACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HORAOPERACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CENTRO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PLAZA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DIGITO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TITULAR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SECUENCIA" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="IDTASK" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "acuerdo",
    "importe",
    "estatus",
    "movimiento",
    "terminal",
    "fechaoperacion",
    "horaoperacion",
    "centro",
    "plaza",
    "digito",
    "titular",
    "secuencia",
    "idtask"
})
public class ResponseBansefi {

    @XmlElement(name = "ACUERDO")
    protected BigInteger acuerdo;
    @XmlElement(name = "IMPORTE")
    protected BigDecimal importe;
    @XmlElement(name = "ESTATUS")
    protected String estatus;
    @XmlElement(name = "MOVIMIENTO")
    protected String movimiento;
    @XmlElement(name = "TERMINAL")
    protected String terminal;
    @XmlElement(name = "FECHAOPERACION")
    protected String fechaoperacion;
    @XmlElement(name = "HORAOPERACION")
    protected String horaoperacion;
    @XmlElement(name = "CENTRO")
    protected String centro;
    @XmlElement(name = "PLAZA")
    protected String plaza;
    @XmlElement(name = "DIGITO")
    protected String digito;
    @XmlElement(name = "TITULAR")
    protected String titular;
    @XmlElement(name = "SECUENCIA")
    protected BigInteger secuencia;
    @XmlElement(name = "IDTASK")
    protected String idtask;

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
     * Obtiene el valor de la propiedad importe.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIMPORTE() {
        return importe;
    }

    /**
     * Define el valor de la propiedad importe.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIMPORTE(BigDecimal value) {
        this.importe = value;
    }

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
     * Obtiene el valor de la propiedad fechaoperacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFECHAOPERACION() {
        return fechaoperacion;
    }

    /**
     * Define el valor de la propiedad fechaoperacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFECHAOPERACION(String value) {
        this.fechaoperacion = value;
    }

    /**
     * Obtiene el valor de la propiedad horaoperacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHORAOPERACION() {
        return horaoperacion;
    }

    /**
     * Define el valor de la propiedad horaoperacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHORAOPERACION(String value) {
        this.horaoperacion = value;
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

    /**
     * Obtiene el valor de la propiedad secuencia.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSECUENCIA() {
        return secuencia;
    }

    /**
     * Define el valor de la propiedad secuencia.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSECUENCIA(BigInteger value) {
        this.secuencia = value;
    }

    /**
     * Obtiene el valor de la propiedad idtask.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDTASK() {
        return idtask;
    }

    /**
     * Define el valor de la propiedad idtask.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDTASK(String value) {
        this.idtask = value;
    }

}
