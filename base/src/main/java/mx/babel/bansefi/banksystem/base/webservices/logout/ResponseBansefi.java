
package mx.babel.bansefi.banksystem.base.webservices.logout;

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
 *         &lt;element name="USUARIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NOMBRE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ENTIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CENTRO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="VENTANILLA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PERFIL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IPFIRMADA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "usuario",
    "nombre",
    "entidad",
    "centro",
    "ventanilla",
    "perfil",
    "ipfirmada"
})
public class ResponseBansefi {

    @XmlElement(name = "USUARIO")
    protected String usuario;
    @XmlElement(name = "NOMBRE")
    protected String nombre;
    @XmlElement(name = "ENTIDAD")
    protected String entidad;
    @XmlElement(name = "CENTRO")
    protected String centro;
    @XmlElement(name = "VENTANILLA")
    protected String ventanilla;
    @XmlElement(name = "PERFIL")
    protected String perfil;
    @XmlElement(name = "IPFIRMADA")
    protected String ipfirmada;

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
     * Obtiene el valor de la propiedad ventanilla.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVENTANILLA() {
        return ventanilla;
    }

    /**
     * Define el valor de la propiedad ventanilla.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVENTANILLA(String value) {
        this.ventanilla = value;
    }

    /**
     * Obtiene el valor de la propiedad perfil.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPERFIL() {
        return perfil;
    }

    /**
     * Define el valor de la propiedad perfil.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPERFIL(String value) {
        this.perfil = value;
    }

    /**
     * Obtiene el valor de la propiedad ipfirmada.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIPFIRMADA() {
        return ipfirmada;
    }

    /**
     * Define el valor de la propiedad ipfirmada.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIPFIRMADA(String value) {
        this.ipfirmada = value;
    }

}
