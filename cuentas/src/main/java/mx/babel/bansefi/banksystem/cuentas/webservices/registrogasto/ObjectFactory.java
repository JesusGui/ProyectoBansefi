
package mx.babel.bansefi.banksystem.cuentas.webservices.registrogasto;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.cuentas.webservices.registrogasto package. 
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

    private final static QName _EjecutarResult_QNAME = new QName("http://BansefiNSS/WebServicesNSS/TR_ALTA_GASTOS_EXT_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.cuentas.webservices.registrogasto
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
     * Create an instance of {@link ResponseBansefi.OTRALTAGASTOSEXTTRNO }
     * 
     */
    public ResponseBansefi.OTRALTAGASTOSEXTTRNO createResponseBansefiOTRALTAGASTOSEXTTRNO() {
        return new ResponseBansefi.OTRALTAGASTOSEXTTRNO();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRALTAGASTOSEXTTRNI }
     * 
     */
    public Ejecutar.ITRALTAGASTOSEXTTRNI createEjecutarITRALTAGASTOSEXTTRNI() {
        return new Ejecutar.ITRALTAGASTOSEXTTRNI();
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
     * Create an instance of {@link ResponseBansefi.OTRALTAGASTOSEXTTRNO.TRALTAGASTOSEXTEVTZ }
     * 
     */
    public ResponseBansefi.OTRALTAGASTOSEXTTRNO.TRALTAGASTOSEXTEVTZ createResponseBansefiOTRALTAGASTOSEXTTRNOTRALTAGASTOSEXTEVTZ() {
        return new ResponseBansefi.OTRALTAGASTOSEXTTRNO.TRALTAGASTOSEXTEVTZ();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRALTAGASTOSEXTTRNO.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRALTAGASTOSEXTTRNO.STDTRNMSJPARMV createResponseBansefiOTRALTAGASTOSEXTTRNOSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRALTAGASTOSEXTTRNO.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRALTAGASTOSEXTTRNO.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRALTAGASTOSEXTTRNO.STDTRNOPARMV createResponseBansefiOTRALTAGASTOSEXTTRNOSTDTRNOPARMV() {
        return new ResponseBansefi.OTRALTAGASTOSEXTTRNO.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRALTAGASTOSEXTTRNI.TRALTAGASTOSEXTEVTY }
     * 
     */
    public Ejecutar.ITRALTAGASTOSEXTTRNI.TRALTAGASTOSEXTEVTY createEjecutarITRALTAGASTOSEXTTRNITRALTAGASTOSEXTEVTY() {
        return new Ejecutar.ITRALTAGASTOSEXTTRNI.TRALTAGASTOSEXTEVTY();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRALTAGASTOSEXTTRNI.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRALTAGASTOSEXTTRNI.STDTRNIPARMV createEjecutarITRALTAGASTOSEXTTRNISTDTRNIPARMV() {
        return new Ejecutar.ITRALTAGASTOSEXTTRNI.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://BansefiNSS/WebServicesNSS/TR_ALTA_GASTOS_EXT_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(_EjecutarResult_QNAME, EjecutarResult.class, null, value);
    }

}
