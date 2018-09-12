
package mx.babel.bansefi.banksystem.base.webservices.consultamovimientos;

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
 *         &lt;element name="TITULAR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FECHA_OPER" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ACUERDO" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="CENTRO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NUM_SEC" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="COD_OPER" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FECHA_VALOR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IMPORTE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SIGNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="INDICADOR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SALDO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MONEDA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OF_ORIG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OF_TERMINAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CONCEPTO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "fechaoper",
    "acuerdo",
    "centro",
    "numsec",
    "codoper",
    "fechavalor",
    "importe",
    "signo",
    "indicador",
    "saldo",
    "moneda",
    "oforig",
    "ofterminal",
    "concepto"
})
public class ResponseBansefi {

    @XmlElement(name = "TITULAR")
    protected String titular;
    @XmlElement(name = "FECHA_OPER")
    protected String fechaoper;
    @XmlElement(name = "ACUERDO")
    protected BigInteger acuerdo;
    @XmlElement(name = "CENTRO")
    protected String centro;
    @XmlElement(name = "NUM_SEC")
    protected BigInteger numsec;
    @XmlElement(name = "COD_OPER")
    protected String codoper;
    @XmlElement(name = "FECHA_VALOR")
    protected String fechavalor;
    @XmlElement(name = "IMPORTE")
    protected String importe;
    @XmlElement(name = "SIGNO")
    protected String signo;
    @XmlElement(name = "INDICADOR")
    protected String indicador;
    @XmlElement(name = "SALDO")
    protected String saldo;
    @XmlElement(name = "MONEDA")
    protected String moneda;
    @XmlElement(name = "OF_ORIG")
    protected String oforig;
    @XmlElement(name = "OF_TERMINAL")
    protected String ofterminal;
    @XmlElement(name = "CONCEPTO")
    protected String concepto;

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
     * Obtiene el valor de la propiedad fechaoper.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFECHAOPER() {
        return fechaoper;
    }

    /**
     * Define el valor de la propiedad fechaoper.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFECHAOPER(String value) {
        this.fechaoper = value;
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
     * Obtiene el valor de la propiedad numsec.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNUMSEC() {
        return numsec;
    }

    /**
     * Define el valor de la propiedad numsec.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNUMSEC(BigInteger value) {
        this.numsec = value;
    }

    /**
     * Obtiene el valor de la propiedad codoper.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCODOPER() {
        return codoper;
    }

    /**
     * Define el valor de la propiedad codoper.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCODOPER(String value) {
        this.codoper = value;
    }

    /**
     * Obtiene el valor de la propiedad fechavalor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFECHAVALOR() {
        return fechavalor;
    }

    /**
     * Define el valor de la propiedad fechavalor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFECHAVALOR(String value) {
        this.fechavalor = value;
    }

    /**
     * Obtiene el valor de la propiedad importe.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIMPORTE() {
        return importe;
    }

    /**
     * Define el valor de la propiedad importe.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIMPORTE(String value) {
        this.importe = value;
    }

    /**
     * Obtiene el valor de la propiedad signo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSIGNO() {
        return signo;
    }

    /**
     * Define el valor de la propiedad signo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSIGNO(String value) {
        this.signo = value;
    }

    /**
     * Obtiene el valor de la propiedad indicador.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINDICADOR() {
        return indicador;
    }

    /**
     * Define el valor de la propiedad indicador.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINDICADOR(String value) {
        this.indicador = value;
    }

    /**
     * Obtiene el valor de la propiedad saldo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSALDO() {
        return saldo;
    }

    /**
     * Define el valor de la propiedad saldo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSALDO(String value) {
        this.saldo = value;
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
     * Obtiene el valor de la propiedad oforig.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOFORIG() {
        return oforig;
    }

    /**
     * Define el valor de la propiedad oforig.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOFORIG(String value) {
        this.oforig = value;
    }

    /**
     * Obtiene el valor de la propiedad ofterminal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOFTERMINAL() {
        return ofterminal;
    }

    /**
     * Define el valor de la propiedad ofterminal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOFTERMINAL(String value) {
        this.ofterminal = value;
    }

    /**
     * Obtiene el valor de la propiedad concepto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCONCEPTO() {
        return concepto;
    }

    /**
     * Define el valor de la propiedad concepto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCONCEPTO(String value) {
        this.concepto = value;
    }

}
