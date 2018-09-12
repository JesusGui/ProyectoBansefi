
package mx.babel.bansefi.banksystem.response.busquedanombre;

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
 *         &lt;element name="ResponseBansefi" type="{http://BansefiNSS/WebServicesNSS/TR_PE_CB_NOMBRE_CNS_TRN}ArrayOfResponseBansefi" minOccurs="0"/>
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
    "responseBansefi"
})
public class EjecutarResult {

    @XmlElement(name = "ResponseBansefi")
    protected ArrayOfResponseBansefi responseBansefi;

    /**
     * Obtiene el valor de la propiedad responseBansefi.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfResponseBansefi }
     *     
     */
    public ArrayOfResponseBansefi getResponseBansefi() {
        return responseBansefi;
    }

    /**
     * Define el valor de la propiedad responseBansefi.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfResponseBansefi }
     *     
     */
    public void setResponseBansefi(ArrayOfResponseBansefi value) {
        this.responseBansefi = value;
    }

}
