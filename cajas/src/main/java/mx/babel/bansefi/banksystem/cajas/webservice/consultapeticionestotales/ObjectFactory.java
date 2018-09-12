
package mx.babel.bansefi.banksystem.cajas.webservice.consultapeticionestotales;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.cajas.webservice.consultapeticionestotales package. 
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

    private final static QName _EjecutarResult_QNAME = new QName("http://BansefiNSS/WebServicesNSS/TR_AUT_TOT_INIC_SM_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.cajas.webservice.consultapeticionestotales
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
     * Create an instance of {@link ResponseBansefi.OTRAUTTOTINICSMTRNO }
     * 
     */
    public ResponseBansefi.OTRAUTTOTINICSMTRNO createResponseBansefiOTRAUTTOTINICSMTRNO() {
        return new ResponseBansefi.OTRAUTTOTINICSMTRNO();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRAUTTOTINICSMTRNO.TRAUTTOTINICSMEVTZ }
     * 
     */
    public ResponseBansefi.OTRAUTTOTINICSMTRNO.TRAUTTOTINICSMEVTZ createResponseBansefiOTRAUTTOTINICSMTRNOTRAUTTOTINICSMEVTZ() {
        return new ResponseBansefi.OTRAUTTOTINICSMTRNO.TRAUTTOTINICSMEVTZ();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRAUTTOTINICSMTRNI }
     * 
     */
    public Ejecutar.ITRAUTTOTINICSMTRNI createEjecutarITRAUTTOTINICSMTRNI() {
        return new Ejecutar.ITRAUTTOTINICSMTRNI();
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
     * Create an instance of {@link ResponseBansefi.OTRAUTTOTINICSMTRNO.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRAUTTOTINICSMTRNO.STDTRNMSJPARMV createResponseBansefiOTRAUTTOTINICSMTRNOSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRAUTTOTINICSMTRNO.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRAUTTOTINICSMTRNO.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRAUTTOTINICSMTRNO.STDTRNOPARMV createResponseBansefiOTRAUTTOTINICSMTRNOSTDTRNOPARMV() {
        return new ResponseBansefi.OTRAUTTOTINICSMTRNO.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRAUTTOTINICSMTRNO.TRAUTTOTINICSMEVTZ.SMAUTTOTV }
     * 
     */
    public ResponseBansefi.OTRAUTTOTINICSMTRNO.TRAUTTOTINICSMEVTZ.SMAUTTOTV createResponseBansefiOTRAUTTOTINICSMTRNOTRAUTTOTINICSMEVTZSMAUTTOTV() {
        return new ResponseBansefi.OTRAUTTOTINICSMTRNO.TRAUTTOTINICSMEVTZ.SMAUTTOTV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRAUTTOTINICSMTRNO.TRAUTTOTINICSMEVTZ.EXEXISTMONEDAE }
     * 
     */
    public ResponseBansefi.OTRAUTTOTINICSMTRNO.TRAUTTOTINICSMEVTZ.EXEXISTMONEDAE createResponseBansefiOTRAUTTOTINICSMTRNOTRAUTTOTINICSMEVTZEXEXISTMONEDAE() {
        return new ResponseBansefi.OTRAUTTOTINICSMTRNO.TRAUTTOTINICSMEVTZ.EXEXISTMONEDAE();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRAUTTOTINICSMTRNI.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRAUTTOTINICSMTRNI.STDTRNIPARMV createEjecutarITRAUTTOTINICSMTRNISTDTRNIPARMV() {
        return new Ejecutar.ITRAUTTOTINICSMTRNI.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRAUTTOTINICSMTRNI.TRAUTTOTINICSMEVTY }
     * 
     */
    public Ejecutar.ITRAUTTOTINICSMTRNI.TRAUTTOTINICSMEVTY createEjecutarITRAUTTOTINICSMTRNITRAUTTOTINICSMEVTY() {
        return new Ejecutar.ITRAUTTOTINICSMTRNI.TRAUTTOTINICSMEVTY();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://BansefiNSS/WebServicesNSS/TR_AUT_TOT_INIC_SM_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(_EjecutarResult_QNAME, EjecutarResult.class, null, value);
    }

}
