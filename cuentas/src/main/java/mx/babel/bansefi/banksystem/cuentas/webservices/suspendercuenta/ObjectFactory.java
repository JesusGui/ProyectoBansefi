
package mx.babel.bansefi.banksystem.cuentas.webservices.suspendercuenta;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.cuentas.webservices.suspendercuenta package. 
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

    private final static QName _EjecutarResult_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_SUSPENDER_AC_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.cuentas.webservices.suspendercuenta
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
     * Create an instance of {@link ResponseBansefi.OTRSUSPENDERACTRNO }
     * 
     */
    public ResponseBansefi.OTRSUSPENDERACTRNO createResponseBansefiOTRSUSPENDERACTRNO() {
        return new ResponseBansefi.OTRSUSPENDERACTRNO();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRSUSPENDERACTRNI }
     * 
     */
    public Ejecutar.ITRSUSPENDERACTRNI createEjecutarITRSUSPENDERACTRNI() {
        return new Ejecutar.ITRSUSPENDERACTRNI();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRSUSPENDERACTRNI.TRSUSPENDERACEVTY }
     * 
     */
    public Ejecutar.ITRSUSPENDERACTRNI.TRSUSPENDERACEVTY createEjecutarITRSUSPENDERACTRNITRSUSPENDERACEVTY() {
        return new Ejecutar.ITRSUSPENDERACTRNI.TRSUSPENDERACEVTY();
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
     * Create an instance of {@link ResponseBansefi.OTRSUSPENDERACTRNO.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRSUSPENDERACTRNO.STDTRNMSJPARMV createResponseBansefiOTRSUSPENDERACTRNOSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRSUSPENDERACTRNO.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRSUSPENDERACTRNO.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRSUSPENDERACTRNO.STDTRNOPARMV createResponseBansefiOTRSUSPENDERACTRNOSTDTRNOPARMV() {
        return new ResponseBansefi.OTRSUSPENDERACTRNO.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRSUSPENDERACTRNI.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRSUSPENDERACTRNI.STDTRNIPARMV createEjecutarITRSUSPENDERACTRNISTDTRNIPARMV() {
        return new Ejecutar.ITRSUSPENDERACTRNI.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRSUSPENDERACTRNI.TRSUSPENDERACEVTY.FDESDEHASTAV }
     * 
     */
    public Ejecutar.ITRSUSPENDERACTRNI.TRSUSPENDERACEVTY.FDESDEHASTAV createEjecutarITRSUSPENDERACTRNITRSUSPENDERACEVTYFDESDEHASTAV() {
        return new Ejecutar.ITRSUSPENDERACTRNI.TRSUSPENDERACEVTY.FDESDEHASTAV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRSUSPENDERACTRNI.TRSUSPENDERACEVTY.FDESDEHASTAANTERIORV }
     * 
     */
    public Ejecutar.ITRSUSPENDERACTRNI.TRSUSPENDERACEVTY.FDESDEHASTAANTERIORV createEjecutarITRSUSPENDERACTRNITRSUSPENDERACEVTYFDESDEHASTAANTERIORV() {
        return new Ejecutar.ITRSUSPENDERACTRNI.TRSUSPENDERACEVTY.FDESDEHASTAANTERIORV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRSUSPENDERACTRNI.TRSUSPENDERACEVTY.ACACP }
     * 
     */
    public Ejecutar.ITRSUSPENDERACTRNI.TRSUSPENDERACEVTY.ACACP createEjecutarITRSUSPENDERACTRNITRSUSPENDERACEVTYACACP() {
        return new Ejecutar.ITRSUSPENDERACTRNI.TRSUSPENDERACEVTY.ACACP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_SUSPENDER_AC_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(_EjecutarResult_QNAME, EjecutarResult.class, null, value);
    }

}
