
package mx.babel.bansefi.banksystem.cuentas.webservices.eliminaciongastoexterno;

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
 *         &lt;element name="EjecutarResult" type="{http://BansefiNSS/WebServicesNSS/TR_BAJA_GASTOS_EXT_TRN}EjecutarResult" minOccurs="0"/>
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
    "ejecutarResult"
})
@XmlRootElement(name = "EjecutarResponse")
public class EjecutarResponse {

    @XmlElement(name = "EjecutarResult")
    protected EjecutarResult ejecutarResult;

    /**
     * Obtiene el valor de la propiedad ejecutarResult.
     * 
     * @return
     *     possible object is
     *     {@link EjecutarResult }
     *     
     */
    public EjecutarResult getEjecutarResult() {
        return ejecutarResult;
    }

    /**
     * Define el valor de la propiedad ejecutarResult.
     * 
     * @param value
     *     allowed object is
     *     {@link EjecutarResult }
     *     
     */
    public void setEjecutarResult(EjecutarResult value) {
        this.ejecutarResult = value;
    }

}
