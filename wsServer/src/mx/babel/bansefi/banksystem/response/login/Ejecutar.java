
package mx.babel.bansefi.banksystem.response.login;

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
 *         &lt;element name="USUARIO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PASSWORD" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NEWPASSWORD" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IP" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FORZADO" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "usuario",
    "password",
    "newpassword",
    "ip",
    "forzado"
})
@XmlRootElement(name = "Ejecutar")
public class Ejecutar {

    @XmlElement(name = "USUARIO", required = true)
    protected String usuario;
    @XmlElement(name = "PASSWORD", required = true)
    protected String password;
    @XmlElement(name = "NEWPASSWORD", required = true)
    protected String newpassword;
    @XmlElement(name = "IP", required = true)
    protected String ip;
    @XmlElement(name = "FORZADO", required = true)
    protected String forzado;

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
     * Obtiene el valor de la propiedad password.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPASSWORD() {
        return password;
    }

    /**
     * Define el valor de la propiedad password.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPASSWORD(String value) {
        this.password = value;
    }

    /**
     * Obtiene el valor de la propiedad newpassword.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNEWPASSWORD() {
        return newpassword;
    }

    /**
     * Define el valor de la propiedad newpassword.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNEWPASSWORD(String value) {
        this.newpassword = value;
    }

    /**
     * Obtiene el valor de la propiedad ip.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIP() {
        return ip;
    }

    /**
     * Define el valor de la propiedad ip.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIP(String value) {
        this.ip = value;
    }

    /**
     * Obtiene el valor de la propiedad forzado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFORZADO() {
        return forzado;
    }

    /**
     * Define el valor de la propiedad forzado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFORZADO(String value) {
        this.forzado = value;
    }

}
