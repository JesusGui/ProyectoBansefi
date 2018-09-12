
package mx.babel.bansefi.banksystem.base.webservices.mensajes;

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
 *         &lt;element name="ENTIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="REGION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CENTRO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="USUARIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CLAVE" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="TERMINAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PRIORIDAD" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="TEXTO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FEC_VIG_DESDE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FEC_VIG_HASTA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="USUARIO_ALTA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FECHA_ALTA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="USUARIO_MOD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FECHA_MOD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "entidad",
    "region",
    "centro",
    "usuario",
    "clave",
    "terminal",
    "prioridad",
    "texto",
    "fecvigdesde",
    "fecvighasta",
    "usuarioalta",
    "fechaalta",
    "usuariomod",
    "fechamod"
})
public class ResponseBansefi {

    @XmlElement(name = "ENTIDAD")
    protected String entidad;
    @XmlElement(name = "REGION")
    protected String region;
    @XmlElement(name = "CENTRO")
    protected String centro;
    @XmlElement(name = "USUARIO")
    protected String usuario;
    @XmlElement(name = "CLAVE")
    protected BigInteger clave;
    @XmlElement(name = "TERMINAL")
    protected String terminal;
    @XmlElement(name = "PRIORIDAD")
    protected BigInteger prioridad;
    @XmlElement(name = "TEXTO")
    protected String texto;
    @XmlElement(name = "FEC_VIG_DESDE")
    protected String fecvigdesde;
    @XmlElement(name = "FEC_VIG_HASTA")
    protected String fecvighasta;
    @XmlElement(name = "USUARIO_ALTA")
    protected String usuarioalta;
    @XmlElement(name = "FECHA_ALTA")
    protected String fechaalta;
    @XmlElement(name = "USUARIO_MOD")
    protected String usuariomod;
    @XmlElement(name = "FECHA_MOD")
    protected String fechamod;

    /**
     * Obtiene el valor de la propiedad entidad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getENTIDAD() {
        return entidad;
    }

    /**
     * Define el valor de la propiedad entidad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setENTIDAD(String value) {
        this.entidad = value;
    }

    /**
     * Obtiene el valor de la propiedad region.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREGION() {
        return region;
    }

    /**
     * Define el valor de la propiedad region.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREGION(String value) {
        this.region = value;
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
     * Obtiene el valor de la propiedad usuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUSUARIO() {
        return usuario;
    }

    /**
     * Define el valor de la propiedad usuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUSUARIO(String value) {
        this.usuario = value;
    }

    /**
     * Obtiene el valor de la propiedad clave.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCLAVE() {
        return clave;
    }

    /**
     * Define el valor de la propiedad clave.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCLAVE(BigInteger value) {
        this.clave = value;
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
     * Obtiene el valor de la propiedad prioridad.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPRIORIDAD() {
        return prioridad;
    }

    /**
     * Define el valor de la propiedad prioridad.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPRIORIDAD(BigInteger value) {
        this.prioridad = value;
    }

    /**
     * Obtiene el valor de la propiedad texto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTEXTO() {
        return texto;
    }

    /**
     * Define el valor de la propiedad texto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTEXTO(String value) {
        this.texto = value;
    }

    /**
     * Obtiene el valor de la propiedad fecvigdesde.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFECVIGDESDE() {
        return fecvigdesde;
    }

    /**
     * Define el valor de la propiedad fecvigdesde.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFECVIGDESDE(String value) {
        this.fecvigdesde = value;
    }

    /**
     * Obtiene el valor de la propiedad fecvighasta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFECVIGHASTA() {
        return fecvighasta;
    }

    /**
     * Define el valor de la propiedad fecvighasta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFECVIGHASTA(String value) {
        this.fecvighasta = value;
    }

    /**
     * Obtiene el valor de la propiedad usuarioalta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUSUARIOALTA() {
        return usuarioalta;
    }

    /**
     * Define el valor de la propiedad usuarioalta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUSUARIOALTA(String value) {
        this.usuarioalta = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaalta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFECHAALTA() {
        return fechaalta;
    }

    /**
     * Define el valor de la propiedad fechaalta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFECHAALTA(String value) {
        this.fechaalta = value;
    }

    /**
     * Obtiene el valor de la propiedad usuariomod.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUSUARIOMOD() {
        return usuariomod;
    }

    /**
     * Define el valor de la propiedad usuariomod.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUSUARIOMOD(String value) {
        this.usuariomod = value;
    }

    /**
     * Obtiene el valor de la propiedad fechamod.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFECHAMOD() {
        return fechamod;
    }

    /**
     * Define el valor de la propiedad fechamod.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFECHAMOD(String value) {
        this.fechamod = value;
    }

}
