
package mx.babel.bansefi.banksystem.response.login;

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
 *         &lt;element name="USUARIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NOMBRE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ENTIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CENTRO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="VENTANILLA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PERFIL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="APLICACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NOMBREAPLICA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MENU" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NOMBREMENU" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="VINCULAMENU" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SUBMENU" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NOMBRESUBMENU" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="VINCULASUBMENU" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SUBSUBMENU" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NOMBRESUBSUBMENU" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="VINCULASUBSUBMENU" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FECSYS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="REGION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EXPIRACION" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="FRECUENTE" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
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
    "aplicacion",
    "nombreaplica",
    "menu",
    "nombremenu",
    "vinculamenu",
    "submenu",
    "nombresubmenu",
    "vinculasubmenu",
    "subsubmenu",
    "nombresubsubmenu",
    "vinculasubsubmenu",
    "fecsys",
    "region",
    "expiracion",
    "frecuente"
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
    @XmlElement(name = "APLICACION")
    protected String aplicacion;
    @XmlElement(name = "NOMBREAPLICA")
    protected String nombreaplica;
    @XmlElement(name = "MENU")
    protected String menu;
    @XmlElement(name = "NOMBREMENU")
    protected String nombremenu;
    @XmlElement(name = "VINCULAMENU")
    protected String vinculamenu;
    @XmlElement(name = "SUBMENU")
    protected String submenu;
    @XmlElement(name = "NOMBRESUBMENU")
    protected String nombresubmenu;
    @XmlElement(name = "VINCULASUBMENU")
    protected String vinculasubmenu;
    @XmlElement(name = "SUBSUBMENU")
    protected String subsubmenu;
    @XmlElement(name = "NOMBRESUBSUBMENU")
    protected String nombresubsubmenu;
    @XmlElement(name = "VINCULASUBSUBMENU")
    protected String vinculasubsubmenu;
    @XmlElement(name = "FECSYS")
    protected String fecsys;
    @XmlElement(name = "REGION")
    protected String region;
    @XmlElement(name = "EXPIRACION")
    protected BigInteger expiracion;
    @XmlElement(name = "FRECUENTE")
    protected BigInteger frecuente;

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
     * Obtiene el valor de la propiedad aplicacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAPLICACION() {
        return aplicacion;
    }

    /**
     * Define el valor de la propiedad aplicacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAPLICACION(String value) {
        this.aplicacion = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreaplica.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNOMBREAPLICA() {
        return nombreaplica;
    }

    /**
     * Define el valor de la propiedad nombreaplica.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNOMBREAPLICA(String value) {
        this.nombreaplica = value;
    }

    /**
     * Obtiene el valor de la propiedad menu.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMENU() {
        return menu;
    }

    /**
     * Define el valor de la propiedad menu.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMENU(String value) {
        this.menu = value;
    }

    /**
     * Obtiene el valor de la propiedad nombremenu.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNOMBREMENU() {
        return nombremenu;
    }

    /**
     * Define el valor de la propiedad nombremenu.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNOMBREMENU(String value) {
        this.nombremenu = value;
    }

    /**
     * Obtiene el valor de la propiedad vinculamenu.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVINCULAMENU() {
        return vinculamenu;
    }

    /**
     * Define el valor de la propiedad vinculamenu.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVINCULAMENU(String value) {
        this.vinculamenu = value;
    }

    /**
     * Obtiene el valor de la propiedad submenu.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSUBMENU() {
        return submenu;
    }

    /**
     * Define el valor de la propiedad submenu.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSUBMENU(String value) {
        this.submenu = value;
    }

    /**
     * Obtiene el valor de la propiedad nombresubmenu.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNOMBRESUBMENU() {
        return nombresubmenu;
    }

    /**
     * Define el valor de la propiedad nombresubmenu.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNOMBRESUBMENU(String value) {
        this.nombresubmenu = value;
    }

    /**
     * Obtiene el valor de la propiedad vinculasubmenu.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVINCULASUBMENU() {
        return vinculasubmenu;
    }

    /**
     * Define el valor de la propiedad vinculasubmenu.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVINCULASUBMENU(String value) {
        this.vinculasubmenu = value;
    }

    /**
     * Obtiene el valor de la propiedad subsubmenu.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSUBSUBMENU() {
        return subsubmenu;
    }

    /**
     * Define el valor de la propiedad subsubmenu.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSUBSUBMENU(String value) {
        this.subsubmenu = value;
    }

    /**
     * Obtiene el valor de la propiedad nombresubsubmenu.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNOMBRESUBSUBMENU() {
        return nombresubsubmenu;
    }

    /**
     * Define el valor de la propiedad nombresubsubmenu.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNOMBRESUBSUBMENU(String value) {
        this.nombresubsubmenu = value;
    }

    /**
     * Obtiene el valor de la propiedad vinculasubsubmenu.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVINCULASUBSUBMENU() {
        return vinculasubsubmenu;
    }

    /**
     * Define el valor de la propiedad vinculasubsubmenu.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVINCULASUBSUBMENU(String value) {
        this.vinculasubsubmenu = value;
    }

    /**
     * Obtiene el valor de la propiedad fecsys.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFECSYS() {
        return fecsys;
    }

    /**
     * Define el valor de la propiedad fecsys.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFECSYS(String value) {
        this.fecsys = value;
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
     * Obtiene el valor de la propiedad expiracion.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getEXPIRACION() {
        return expiracion;
    }

    /**
     * Define el valor de la propiedad expiracion.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setEXPIRACION(BigInteger value) {
        this.expiracion = value;
    }

    /**
     * Obtiene el valor de la propiedad frecuente.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFRECUENTE() {
        return frecuente;
    }

    /**
     * Define el valor de la propiedad frecuente.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFRECUENTE(BigInteger value) {
        this.frecuente = value;
    }

}
