
package mx.babel.bansefi.banksystem.base.webservices.consultacatalogo;

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
 *         &lt;element name="CLAVE_DE_FILA_NOMBRE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DESCRIPCION_CORTA_FL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DESCRIPCION_LARGA_FL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CONTENIDO_DE_DATOS_1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CLAVE_DE_PAGINACION1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "clavedefilanombre",
    "descripcioncortafl",
    "descripcionlargafl",
    "contenidodedatos1",
    "clavedepaginacion1"
})
public class ResponseBansefi {

    @XmlElement(name = "CLAVE_DE_FILA_NOMBRE")
    protected String clavedefilanombre;
    @XmlElement(name = "DESCRIPCION_CORTA_FL")
    protected String descripcioncortafl;
    @XmlElement(name = "DESCRIPCION_LARGA_FL")
    protected String descripcionlargafl;
    @XmlElement(name = "CONTENIDO_DE_DATOS_1")
    protected String contenidodedatos1;
    @XmlElement(name = "CLAVE_DE_PAGINACION1")
    protected String clavedepaginacion1;

    /**
     * Obtiene el valor de la propiedad clavedefilanombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCLAVEDEFILANOMBRE() {
        return clavedefilanombre;
    }

    /**
     * Define el valor de la propiedad clavedefilanombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCLAVEDEFILANOMBRE(String value) {
        this.clavedefilanombre = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcioncortafl.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDESCRIPCIONCORTAFL() {
        return descripcioncortafl;
    }

    /**
     * Define el valor de la propiedad descripcioncortafl.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDESCRIPCIONCORTAFL(String value) {
        this.descripcioncortafl = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcionlargafl.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDESCRIPCIONLARGAFL() {
        return descripcionlargafl;
    }

    /**
     * Define el valor de la propiedad descripcionlargafl.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDESCRIPCIONLARGAFL(String value) {
        this.descripcionlargafl = value;
    }

    /**
     * Obtiene el valor de la propiedad contenidodedatos1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCONTENIDODEDATOS1() {
        return contenidodedatos1;
    }

    /**
     * Define el valor de la propiedad contenidodedatos1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCONTENIDODEDATOS1(String value) {
        this.contenidodedatos1 = value;
    }

    /**
     * Obtiene el valor de la propiedad clavedepaginacion1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCLAVEDEPAGINACION1() {
        return clavedepaginacion1;
    }

    /**
     * Define el valor de la propiedad clavedepaginacion1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCLAVEDEPAGINACION1(String value) {
        this.clavedepaginacion1 = value;
    }

}
