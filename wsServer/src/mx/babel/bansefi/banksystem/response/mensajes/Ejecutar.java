
package mx.babel.bansefi.banksystem.response.mensajes;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="USERHEADER" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PASSHEADER" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IPHEADER" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OPCION" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ENTIDAD" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="REGION" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CENTRO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="USUARIO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CLAVE" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="TERMINAL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PRIORIDAD" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="TEXTO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FEC_VIG_DESDE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FEC_VIG_HASTA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "userheader",
    "passheader",
    "ipheader",
    "opcion",
    "entidad",
    "region",
    "centro",
    "usuario",
    "clave",
    "terminal",
    "prioridad",
    "texto",
    "fecvigdesde",
    "fecvighasta"
})
@XmlRootElement(name = "Ejecutar")
public class Ejecutar {

    @XmlElement(name = "USERHEADER", required = true)
    protected String userheader;
    @XmlElement(name = "PASSHEADER", required = true)
    protected String passheader;
    @XmlElement(name = "IPHEADER", required = true)
    protected String ipheader;
    @XmlElement(name = "OPCION", required = true)
    protected String opcion;
    @XmlElement(name = "ENTIDAD", required = true)
    protected String entidad;
    @XmlElement(name = "REGION", required = true)
    protected String region;
    @XmlElement(name = "CENTRO", required = true)
    protected String centro;
    @XmlElement(name = "USUARIO", required = true)
    protected String usuario;
    @XmlElement(name = "CLAVE", required = true)
    protected BigInteger clave;
    @XmlElement(name = "TERMINAL", required = true)
    protected String terminal;
    @XmlElement(name = "PRIORIDAD", required = true)
    protected BigInteger prioridad;
    @XmlElement(name = "TEXTO", required = true)
    protected String texto;
    @XmlElement(name = "FEC_VIG_DESDE", required = true)
    protected String fecvigdesde;
    @XmlElement(name = "FEC_VIG_HASTA", required = true)
    protected String fecvighasta;

    /**
     * Obtiene el valor de la propiedad userheader.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUSERHEADER() {
        return userheader;
    }

    /**
     * Define el valor de la propiedad userheader.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUSERHEADER(String value) {
        this.userheader = value;
    }

    /**
     * Obtiene el valor de la propiedad passheader.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPASSHEADER() {
        return passheader;
    }

    /**
     * Define el valor de la propiedad passheader.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPASSHEADER(String value) {
        this.passheader = value;
    }

    /**
     * Obtiene el valor de la propiedad ipheader.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIPHEADER() {
        return ipheader;
    }

    /**
     * Define el valor de la propiedad ipheader.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIPHEADER(String value) {
        this.ipheader = value;
    }

    /**
     * Obtiene el valor de la propiedad opcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOPCION() {
        return opcion;
    }

    /**
     * Define el valor de la propiedad opcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOPCION(String value) {
        this.opcion = value;
    }

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

}
