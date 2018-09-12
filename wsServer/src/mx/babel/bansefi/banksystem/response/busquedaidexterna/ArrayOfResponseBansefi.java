
package mx.babel.bansefi.banksystem.response.busquedaidexterna;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfResponseBansefi complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfResponseBansefi">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ResponseBansefi" type="{http://BansefiNSS/WebServicesNSS/TR_PE_CB_ID_EXTERNA_CNS_TRN}ResponseBansefi" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfResponseBansefi", propOrder = {
    "responseBansefi"
})
public class ArrayOfResponseBansefi {

    @XmlElement(name = "ResponseBansefi")
    protected List<ResponseBansefi> responseBansefi;

    /**
     * Gets the value of the responseBansefi property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the responseBansefi property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResponseBansefi().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResponseBansefi }
     * 
     * 
     */
    public List<ResponseBansefi> getResponseBansefi() {
        if (responseBansefi == null) {
            responseBansefi = new ArrayList<ResponseBansefi>();
        }
        return this.responseBansefi;
    }

}
