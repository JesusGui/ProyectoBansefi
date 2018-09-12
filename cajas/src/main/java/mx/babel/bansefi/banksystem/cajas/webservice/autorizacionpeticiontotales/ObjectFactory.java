
package mx.babel.bansefi.banksystem.cajas.webservice.autorizacionpeticiontotales;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.cajas.webservice.autorizacionPeticionTotales package. 
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

    private final static QName _EjecutarResult_QNAME = new QName("http://BansefiNSS/WebServicesNSS/TR_AUT_TOT_CONF_SM_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.cajas.webservice.autorizacionPeticionTotales
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
     * Create an instance of {@link ResponseBansefi.OTRAUTTOTCONFSMTRNO }
     * 
     */
    public ResponseBansefi.OTRAUTTOTCONFSMTRNO createResponseBansefiOTRAUTTOTCONFSMTRNO() {
        return new ResponseBansefi.OTRAUTTOTCONFSMTRNO();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRAUTTOTCONFSMTRNO.TRAUTTOTCONFSMEVTZ }
     * 
     */
    public ResponseBansefi.OTRAUTTOTCONFSMTRNO.TRAUTTOTCONFSMEVTZ createResponseBansefiOTRAUTTOTCONFSMTRNOTRAUTTOTCONFSMEVTZ() {
        return new ResponseBansefi.OTRAUTTOTCONFSMTRNO.TRAUTTOTCONFSMEVTZ();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRAUTTOTCONFSMTRNI }
     * 
     */
    public Ejecutar.ITRAUTTOTCONFSMTRNI createEjecutarITRAUTTOTCONFSMTRNI() {
        return new Ejecutar.ITRAUTTOTCONFSMTRNI();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRAUTTOTCONFSMTRNI.TRAUTTOTCONFSMEVTY }
     * 
     */
    public Ejecutar.ITRAUTTOTCONFSMTRNI.TRAUTTOTCONFSMEVTY createEjecutarITRAUTTOTCONFSMTRNITRAUTTOTCONFSMEVTY() {
        return new Ejecutar.ITRAUTTOTCONFSMTRNI.TRAUTTOTCONFSMEVTY();
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
     * Create an instance of {@link ResponseBansefi.OTRAUTTOTCONFSMTRNO.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRAUTTOTCONFSMTRNO.STDTRNMSJPARMV createResponseBansefiOTRAUTTOTCONFSMTRNOSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRAUTTOTCONFSMTRNO.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRAUTTOTCONFSMTRNO.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRAUTTOTCONFSMTRNO.STDTRNOPARMV createResponseBansefiOTRAUTTOTCONFSMTRNOSTDTRNOPARMV() {
        return new ResponseBansefi.OTRAUTTOTCONFSMTRNO.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRAUTTOTCONFSMTRNO.TRAUTTOTCONFSMEVTZ.SMESTADOPETV }
     * 
     */
    public ResponseBansefi.OTRAUTTOTCONFSMTRNO.TRAUTTOTCONFSMEVTZ.SMESTADOPETV createResponseBansefiOTRAUTTOTCONFSMTRNOTRAUTTOTCONFSMEVTZSMESTADOPETV() {
        return new ResponseBansefi.OTRAUTTOTCONFSMTRNO.TRAUTTOTCONFSMEVTZ.SMESTADOPETV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRAUTTOTCONFSMTRNI.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRAUTTOTCONFSMTRNI.STDTRNIPARMV createEjecutarITRAUTTOTCONFSMTRNISTDTRNIPARMV() {
        return new Ejecutar.ITRAUTTOTCONFSMTRNI.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRAUTTOTCONFSMTRNI.TRAUTTOTCONFSMEVTY.SMAUTTOTV }
     * 
     */
    public Ejecutar.ITRAUTTOTCONFSMTRNI.TRAUTTOTCONFSMEVTY.SMAUTTOTV createEjecutarITRAUTTOTCONFSMTRNITRAUTTOTCONFSMEVTYSMAUTTOTV() {
        return new Ejecutar.ITRAUTTOTCONFSMTRNI.TRAUTTOTCONFSMEVTY.SMAUTTOTV();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://BansefiNSS/WebServicesNSS/TR_AUT_TOT_CONF_SM_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(_EjecutarResult_QNAME, EjecutarResult.class, null, value);
    }

}
