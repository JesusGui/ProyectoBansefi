
package mx.babel.bansefi.banksystem.personas.webservices.consultadetalledatoprofesional;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.personas.webservices.consultadetalledatoprofesional package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_CONS_COMPL_PROF_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.personas.webservices.consultadetalledatoprofesional
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
     * Create an instance of {@link ResponseBansefi.OTRPECONSCOMPLPROFTR }
     * 
     */
    public ResponseBansefi.OTRPECONSCOMPLPROFTR createResponseBansefiOTRPECONSCOMPLPROFTR() {
        return new ResponseBansefi.OTRPECONSCOMPLPROFTR();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPECONSCOMPLPROFTR.TRPECONSCOMPLPROFEVT }
     * 
     */
    public ResponseBansefi.OTRPECONSCOMPLPROFTR.TRPECONSCOMPLPROFEVT createResponseBansefiOTRPECONSCOMPLPROFTRTRPECONSCOMPLPROFEVT() {
        return new ResponseBansefi.OTRPECONSCOMPLPROFTR.TRPECONSCOMPLPROFEVT();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPECONSCOMPLPROFTR }
     * 
     */
    public Ejecutar.ITRPECONSCOMPLPROFTR createEjecutarITRPECONSCOMPLPROFTR() {
        return new Ejecutar.ITRPECONSCOMPLPROFTR();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPECONSCOMPLPROFTR.TRPECONSCOMPLPROFEVT }
     * 
     */
    public Ejecutar.ITRPECONSCOMPLPROFTR.TRPECONSCOMPLPROFEVT createEjecutarITRPECONSCOMPLPROFTRTRPECONSCOMPLPROFEVT() {
        return new Ejecutar.ITRPECONSCOMPLPROFTR.TRPECONSCOMPLPROFEVT();
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
     * Create an instance of {@link ResponseBansefi.OTRPECONSCOMPLPROFTR.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRPECONSCOMPLPROFTR.STDTRNMSJPARMV createResponseBansefiOTRPECONSCOMPLPROFTRSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRPECONSCOMPLPROFTR.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPECONSCOMPLPROFTR.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRPECONSCOMPLPROFTR.STDTRNOPARMV createResponseBansefiOTRPECONSCOMPLPROFTRSTDTRNOPARMV() {
        return new ResponseBansefi.OTRPECONSCOMPLPROFTR.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPECONSCOMPLPROFTR.TRPECONSCOMPLPROFEVT.STDFECANTGLABORALV }
     * 
     */
    public ResponseBansefi.OTRPECONSCOMPLPROFTR.TRPECONSCOMPLPROFEVT.STDFECANTGLABORALV createResponseBansefiOTRPECONSCOMPLPROFTRTRPECONSCOMPLPROFEVTSTDFECANTGLABORALV() {
        return new ResponseBansefi.OTRPECONSCOMPLPROFTR.TRPECONSCOMPLPROFEVT.STDFECANTGLABORALV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPECONSCOMPLPROFTR.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRPECONSCOMPLPROFTR.STDTRNIPARMV createEjecutarITRPECONSCOMPLPROFTRSTDTRNIPARMV() {
        return new Ejecutar.ITRPECONSCOMPLPROFTR.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPECONSCOMPLPROFTR.TRPECONSCOMPLPROFEVT.PEACTPROFINDVP }
     * 
     */
    public Ejecutar.ITRPECONSCOMPLPROFTR.TRPECONSCOMPLPROFEVT.PEACTPROFINDVP createEjecutarITRPECONSCOMPLPROFTRTRPECONSCOMPLPROFEVTPEACTPROFINDVP() {
        return new Ejecutar.ITRPECONSCOMPLPROFTR.TRPECONSCOMPLPROFEVT.PEACTPROFINDVP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_CONS_COMPL_PROF_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
