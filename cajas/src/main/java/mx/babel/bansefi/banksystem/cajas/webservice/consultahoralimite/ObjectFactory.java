
package mx.babel.bansefi.banksystem.cajas.webservice.consultahoralimite;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.cajas.webservice.consultahoralimite package. 
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

    private final static QName _EjecutarResult_QNAME = new QName("http://BansefiNSS/WebServicesNSS/TR_CONS_PX_CCA_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.cajas.webservice.consultahoralimite
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
     * Create an instance of {@link ResponseBansefi.OTRCONSPXCCATRNO }
     * 
     */
    public ResponseBansefi.OTRCONSPXCCATRNO createResponseBansefiOTRCONSPXCCATRNO() {
        return new ResponseBansefi.OTRCONSPXCCATRNO();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCONSPXCCATRNO.TRCONSPXCCAEVTZ }
     * 
     */
    public ResponseBansefi.OTRCONSPXCCATRNO.TRCONSPXCCAEVTZ createResponseBansefiOTRCONSPXCCATRNOTRCONSPXCCAEVTZ() {
        return new ResponseBansefi.OTRCONSPXCCATRNO.TRCONSPXCCAEVTZ();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSPXCCATRNI }
     * 
     */
    public Ejecutar.ITRCONSPXCCATRNI createEjecutarITRCONSPXCCATRNI() {
        return new Ejecutar.ITRCONSPXCCATRNI();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSPXCCATRNI.TRCONSPXCCAEVTY }
     * 
     */
    public Ejecutar.ITRCONSPXCCATRNI.TRCONSPXCCAEVTY createEjecutarITRCONSPXCCATRNITRCONSPXCCAEVTY() {
        return new Ejecutar.ITRCONSPXCCATRNI.TRCONSPXCCAEVTY();
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
     * Create an instance of {@link ResponseBansefi.OTRCONSPXCCATRNO.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRCONSPXCCATRNO.STDTRNMSJPARMV createResponseBansefiOTRCONSPXCCATRNOSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRCONSPXCCATRNO.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCONSPXCCATRNO.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRCONSPXCCATRNO.STDTRNOPARMV createResponseBansefiOTRCONSPXCCATRNOSTDTRNOPARMV() {
        return new ResponseBansefi.OTRCONSPXCCATRNO.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCONSPXCCATRNO.TRCONSPXCCAEVTZ.PXPARAMCCAE }
     * 
     */
    public ResponseBansefi.OTRCONSPXCCATRNO.TRCONSPXCCAEVTZ.PXPARAMCCAE createResponseBansefiOTRCONSPXCCATRNOTRCONSPXCCAEVTZPXPARAMCCAE() {
        return new ResponseBansefi.OTRCONSPXCCATRNO.TRCONSPXCCAEVTZ.PXPARAMCCAE();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSPXCCATRNI.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRCONSPXCCATRNI.STDTRNIPARMV createEjecutarITRCONSPXCCATRNISTDTRNIPARMV() {
        return new Ejecutar.ITRCONSPXCCATRNI.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSPXCCATRNI.TRCONSPXCCAEVTY.PXPARAMCCAP }
     * 
     */
    public Ejecutar.ITRCONSPXCCATRNI.TRCONSPXCCAEVTY.PXPARAMCCAP createEjecutarITRCONSPXCCATRNITRCONSPXCCAEVTYPXPARAMCCAP() {
        return new Ejecutar.ITRCONSPXCCATRNI.TRCONSPXCCAEVTY.PXPARAMCCAP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://BansefiNSS/WebServicesNSS/TR_CONS_PX_CCA_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(_EjecutarResult_QNAME, EjecutarResult.class, null, value);
    }

}
