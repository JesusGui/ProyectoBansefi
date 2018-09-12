
package mx.babel.bansefi.banksystem.personas.webservices.consultalistadatosprofesionales;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.personas.webservices.consultalistadatosprofesionales package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_PROF_LS_CNS_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.personas.webservices.consultalistadatosprofesionales
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
     * Create an instance of {@link ResponseBansefi.OTRPEPROFLSCNSTRNO }
     * 
     */
    public ResponseBansefi.OTRPEPROFLSCNSTRNO createResponseBansefiOTRPEPROFLSCNSTRNO() {
        return new ResponseBansefi.OTRPEPROFLSCNSTRNO();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPEPROFLSCNSTRNO.TRPEPROFLSCNSEVTZ }
     * 
     */
    public ResponseBansefi.OTRPEPROFLSCNSTRNO.TRPEPROFLSCNSEVTZ createResponseBansefiOTRPEPROFLSCNSTRNOTRPEPROFLSCNSEVTZ() {
        return new ResponseBansefi.OTRPEPROFLSCNSTRNO.TRPEPROFLSCNSEVTZ();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEPROFLSCNSTRNI }
     * 
     */
    public Ejecutar.ITRPEPROFLSCNSTRNI createEjecutarITRPEPROFLSCNSTRNI() {
        return new Ejecutar.ITRPEPROFLSCNSTRNI();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEPROFLSCNSTRNI.TRPEPROFLSCNSEVTY }
     * 
     */
    public Ejecutar.ITRPEPROFLSCNSTRNI.TRPEPROFLSCNSEVTY createEjecutarITRPEPROFLSCNSTRNITRPEPROFLSCNSEVTY() {
        return new Ejecutar.ITRPEPROFLSCNSTRNI.TRPEPROFLSCNSEVTY();
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
     * Create an instance of {@link ResponseBansefi.OTRPEPROFLSCNSTRNO.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRPEPROFLSCNSTRNO.STDTRNMSJPARMV createResponseBansefiOTRPEPROFLSCNSTRNOSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRPEPROFLSCNSTRNO.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPEPROFLSCNSTRNO.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRPEPROFLSCNSTRNO.STDTRNOPARMV createResponseBansefiOTRPEPROFLSCNSTRNOSTDTRNOPARMV() {
        return new ResponseBansefi.OTRPEPROFLSCNSTRNO.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPEPROFLSCNSTRNO.TRPEPROFLSCNSEVTZ.TRPEPROFLSCNSEVTV }
     * 
     */
    public ResponseBansefi.OTRPEPROFLSCNSTRNO.TRPEPROFLSCNSEVTZ.TRPEPROFLSCNSEVTV createResponseBansefiOTRPEPROFLSCNSTRNOTRPEPROFLSCNSEVTZTRPEPROFLSCNSEVTV() {
        return new ResponseBansefi.OTRPEPROFLSCNSTRNO.TRPEPROFLSCNSEVTZ.TRPEPROFLSCNSEVTV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEPROFLSCNSTRNI.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRPEPROFLSCNSTRNI.STDTRNIPARMV createEjecutarITRPEPROFLSCNSTRNISTDTRNIPARMV() {
        return new Ejecutar.ITRPEPROFLSCNSTRNI.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEPROFLSCNSTRNI.TRPEPROFLSCNSEVTY.PEACTPROFINDVP }
     * 
     */
    public Ejecutar.ITRPEPROFLSCNSTRNI.TRPEPROFLSCNSEVTY.PEACTPROFINDVP createEjecutarITRPEPROFLSCNSTRNITRPEPROFLSCNSEVTYPEACTPROFINDVP() {
        return new Ejecutar.ITRPEPROFLSCNSTRNI.TRPEPROFLSCNSEVTY.PEACTPROFINDVP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_PROF_LS_CNS_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
