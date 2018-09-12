
package mx.babel.bansefi.banksystem.base.webservices.validaidinterna;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.base.webservices.validaidinterna package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CONS_MINIMA_PERSONA_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.base.webservices.validaidinterna
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
     * Create an instance of {@link ResponseBansefi.OTRCONSMINIMAPERSONAT }
     * 
     */
    public ResponseBansefi.OTRCONSMINIMAPERSONAT createResponseBansefiOTRCONSMINIMAPERSONAT() {
        return new ResponseBansefi.OTRCONSMINIMAPERSONAT();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCONSMINIMAPERSONAT.TRCONSMINIMAPERSONAEV }
     * 
     */
    public ResponseBansefi.OTRCONSMINIMAPERSONAT.TRCONSMINIMAPERSONAEV createResponseBansefiOTRCONSMINIMAPERSONATTRCONSMINIMAPERSONAEV() {
        return new ResponseBansefi.OTRCONSMINIMAPERSONAT.TRCONSMINIMAPERSONAEV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSMINIMAPERSONAT }
     * 
     */
    public Ejecutar.ITRCONSMINIMAPERSONAT createEjecutarITRCONSMINIMAPERSONAT() {
        return new Ejecutar.ITRCONSMINIMAPERSONAT();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSMINIMAPERSONAT.TRCONSMINIMAPERSONAEV }
     * 
     */
    public Ejecutar.ITRCONSMINIMAPERSONAT.TRCONSMINIMAPERSONAEV createEjecutarITRCONSMINIMAPERSONATTRCONSMINIMAPERSONAEV() {
        return new Ejecutar.ITRCONSMINIMAPERSONAT.TRCONSMINIMAPERSONAEV();
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
     * Create an instance of {@link ResponseBansefi.OTRCONSMINIMAPERSONAT.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRCONSMINIMAPERSONAT.STDTRNMSJPARMV createResponseBansefiOTRCONSMINIMAPERSONATSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRCONSMINIMAPERSONAT.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCONSMINIMAPERSONAT.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRCONSMINIMAPERSONAT.STDTRNOPARMV createResponseBansefiOTRCONSMINIMAPERSONATSTDTRNOPARMV() {
        return new ResponseBansefi.OTRCONSMINIMAPERSONAT.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCONSMINIMAPERSONAT.TRCONSMINIMAPERSONAEV.STDTLFNOSCONSMINIMAV }
     * 
     */
    public ResponseBansefi.OTRCONSMINIMAPERSONAT.TRCONSMINIMAPERSONAEV.STDTLFNOSCONSMINIMAV createResponseBansefiOTRCONSMINIMAPERSONATTRCONSMINIMAPERSONAEVSTDTLFNOSCONSMINIMAV() {
        return new ResponseBansefi.OTRCONSMINIMAPERSONAT.TRCONSMINIMAPERSONAEV.STDTLFNOSCONSMINIMAV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSMINIMAPERSONAT.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRCONSMINIMAPERSONAT.STDTRNIPARMV createEjecutarITRCONSMINIMAPERSONATSTDTRNIPARMV() {
        return new Ejecutar.ITRCONSMINIMAPERSONAT.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSMINIMAPERSONAT.TRCONSMINIMAPERSONAEV.PEPERSP }
     * 
     */
    public Ejecutar.ITRCONSMINIMAPERSONAT.TRCONSMINIMAPERSONAEV.PEPERSP createEjecutarITRCONSMINIMAPERSONATTRCONSMINIMAPERSONAEVPEPERSP() {
        return new Ejecutar.ITRCONSMINIMAPERSONAT.TRCONSMINIMAPERSONAEV.PEPERSP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CONS_MINIMA_PERSONA_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
