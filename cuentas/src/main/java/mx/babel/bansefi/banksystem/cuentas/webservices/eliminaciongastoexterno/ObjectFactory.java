
package mx.babel.bansefi.banksystem.cuentas.webservices.eliminaciongastoexterno;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.cuentas.webservices.eliminaciongastoexterno package. 
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

    private final static QName _EjecutarResult_QNAME = new QName("http://BansefiNSS/WebServicesNSS/TR_BAJA_GASTOS_EXT_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.cuentas.webservices.eliminaciongastoexterno
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
     * Create an instance of {@link ResponseBansefi.OTRBAJAGASTOSEXTTRNO }
     * 
     */
    public ResponseBansefi.OTRBAJAGASTOSEXTTRNO createResponseBansefiOTRBAJAGASTOSEXTTRNO() {
        return new ResponseBansefi.OTRBAJAGASTOSEXTTRNO();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRBAJAGASTOSEXTTRNI }
     * 
     */
    public Ejecutar.ITRBAJAGASTOSEXTTRNI createEjecutarITRBAJAGASTOSEXTTRNI() {
        return new Ejecutar.ITRBAJAGASTOSEXTTRNI();
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
     * Create an instance of {@link ResponseBansefi.OTRBAJAGASTOSEXTTRNO.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRBAJAGASTOSEXTTRNO.STDTRNMSJPARMV createResponseBansefiOTRBAJAGASTOSEXTTRNOSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRBAJAGASTOSEXTTRNO.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRBAJAGASTOSEXTTRNO.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRBAJAGASTOSEXTTRNO.STDTRNOPARMV createResponseBansefiOTRBAJAGASTOSEXTTRNOSTDTRNOPARMV() {
        return new ResponseBansefi.OTRBAJAGASTOSEXTTRNO.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRBAJAGASTOSEXTTRNO.TRBAJAGASTOSEXTEVTZ }
     * 
     */
    public ResponseBansefi.OTRBAJAGASTOSEXTTRNO.TRBAJAGASTOSEXTEVTZ createResponseBansefiOTRBAJAGASTOSEXTTRNOTRBAJAGASTOSEXTEVTZ() {
        return new ResponseBansefi.OTRBAJAGASTOSEXTTRNO.TRBAJAGASTOSEXTEVTZ();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRBAJAGASTOSEXTTRNI.TRBAJAGASTOSEXTEVTY }
     * 
     */
    public Ejecutar.ITRBAJAGASTOSEXTTRNI.TRBAJAGASTOSEXTEVTY createEjecutarITRBAJAGASTOSEXTTRNITRBAJAGASTOSEXTEVTY() {
        return new Ejecutar.ITRBAJAGASTOSEXTTRNI.TRBAJAGASTOSEXTEVTY();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRBAJAGASTOSEXTTRNI.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRBAJAGASTOSEXTTRNI.STDTRNIPARMV createEjecutarITRBAJAGASTOSEXTTRNISTDTRNIPARMV() {
        return new Ejecutar.ITRBAJAGASTOSEXTTRNI.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://BansefiNSS/WebServicesNSS/TR_BAJA_GASTOS_EXT_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(_EjecutarResult_QNAME, EjecutarResult.class, null, value);
    }

}
