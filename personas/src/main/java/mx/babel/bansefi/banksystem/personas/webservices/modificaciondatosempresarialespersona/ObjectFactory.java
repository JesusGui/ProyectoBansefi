
package mx.babel.bansefi.banksystem.personas.webservices.modificaciondatosempresarialespersona;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bansefinss.webservicesnss.tr_pe_modif_compl_empr_trn package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_MODIF_COMPL_EMPR_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bansefinss.webservicesnss.tr_pe_modif_compl_empr_trn
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Ejecutar }
     * 
     */
    public Ejecutar createEjecutar() {
        return new Ejecutar();
    }

    /**
     * Create an instance of {@link ResponseBansefi }
     * 
     */
    public ResponseBansefi createResponseBansefi() {
        return new ResponseBansefi();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPEMODIFCOMPLEMPRT }
     * 
     */
    public ResponseBansefi.OTRPEMODIFCOMPLEMPRT createResponseBansefiOTRPEMODIFCOMPLEMPRT() {
        return new ResponseBansefi.OTRPEMODIFCOMPLEMPRT();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEMODIFCOMPLEMPRT }
     * 
     */
    public Ejecutar.ITRPEMODIFCOMPLEMPRT createEjecutarITRPEMODIFCOMPLEMPRT() {
        return new Ejecutar.ITRPEMODIFCOMPLEMPRT();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEMODIFCOMPLEMPRT.TRPEMODIFCOMPLEMPREV }
     * 
     */
    public Ejecutar.ITRPEMODIFCOMPLEMPRT.TRPEMODIFCOMPLEMPREV createEjecutarITRPEMODIFCOMPLEMPRTTRPEMODIFCOMPLEMPREV() {
        return new Ejecutar.ITRPEMODIFCOMPLEMPRT.TRPEMODIFCOMPLEMPREV();
    }

    /**
     * Create an instance of {@link EjecutarResponse }
     * 
     */
    public EjecutarResponse createEjecutarResponse() {
        return new EjecutarResponse();
    }

    /**
     * Create an instance of {@link EjecutarResult }
     * 
     */
    public EjecutarResult createEjecutarResult() {
        return new EjecutarResult();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPEMODIFCOMPLEMPRT.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRPEMODIFCOMPLEMPRT.STDTRNMSJPARMV createResponseBansefiOTRPEMODIFCOMPLEMPRTSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRPEMODIFCOMPLEMPRT.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPEMODIFCOMPLEMPRT.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRPEMODIFCOMPLEMPRT.STDTRNOPARMV createResponseBansefiOTRPEMODIFCOMPLEMPRTSTDTRNOPARMV() {
        return new ResponseBansefi.OTRPEMODIFCOMPLEMPRT.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEMODIFCOMPLEMPRT.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRPEMODIFCOMPLEMPRT.STDTRNIPARMV createEjecutarITRPEMODIFCOMPLEMPRTSTDTRNIPARMV() {
        return new Ejecutar.ITRPEMODIFCOMPLEMPRT.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEMODIFCOMPLEMPRT.TRPEMODIFCOMPLEMPREV.PEEMPRESAP }
     * 
     */
    public Ejecutar.ITRPEMODIFCOMPLEMPRT.TRPEMODIFCOMPLEMPREV.PEEMPRESAP createEjecutarITRPEMODIFCOMPLEMPRTTRPEMODIFCOMPLEMPREVPEEMPRESAP() {
        return new Ejecutar.ITRPEMODIFCOMPLEMPRT.TRPEMODIFCOMPLEMPREV.PEEMPRESAP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_MODIF_COMPL_EMPR_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
