
package mx.babel.bansefi.banksystem.cuentas.webservices.modificaciondatosadicionales;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para EjecutarResult complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="EjecutarResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TRANID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ESTATUS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CODIGO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MENSAJE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NUMTASK" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ResponseBansefi" type="{http://BansefiNSS/WebServicesNSS/TR_MODIF_AMPLIAR_AC_TRN}ResponseBansefi" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EjecutarResult", propOrder = {
    "tranid",
    "estatus",
    "codigo",
    "mensaje",
    "numtask",
    "responseBansefi"
})
public class EjecutarResult {

    @XmlElement(name = "TRANID", required = true)
    protected String tranid;
    @XmlElement(name = "ESTATUS", required = true)
    protected String estatus;
    @XmlElement(name = "CODIGO", required = true)
    protected String codigo;
    @XmlElement(name = "MENSAJE", required = true)
    protected String mensaje;
    @XmlElement(name = "NUMTASK", required = true)
    protected String numtask;
    @XmlElement(name = "ResponseBansefi")
    protected ResponseBansefi responseBansefi;

    /**
     * Obtiene el valor de la propiedad tranid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTRANID() {
        return tranid;
    }

    /**
     * Define el valor de la propiedad tranid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTRANID(String value) {
        this.tranid = value;
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
     * Obtiene el valor de la propiedad codigo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCODIGO() {
        return codigo;
    }

    /**
     * Define el valor de la propiedad codigo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCODIGO(String value) {
        this.codigo = value;
    }

    /**
     * Obtiene el valor de la propiedad mensaje.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMENSAJE() {
        return mensaje;
    }

    /**
     * Define el valor de la propiedad mensaje.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMENSAJE(String value) {
        this.mensaje = value;
    }

    /**
     * Obtiene el valor de la propiedad numtask.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNUMTASK() {
        return numtask;
    }

    /**
     * Define el valor de la propiedad numtask.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNUMTASK(String value) {
        this.numtask = value;
    }

    /**
     * Obtiene el valor de la propiedad responseBansefi.
     * 
     * @return
     *     possible object is
     *     {@link ResponseBansefi }
     *     
     */
    public ResponseBansefi getResponseBansefi() {
        return responseBansefi;
    }

    /**
     * Define el valor de la propiedad responseBansefi.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseBansefi }
     *     
     */
    public void setResponseBansefi(ResponseBansefi value) {
        this.responseBansefi = value;
    }

}
