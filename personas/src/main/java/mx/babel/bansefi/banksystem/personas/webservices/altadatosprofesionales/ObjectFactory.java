
package mx.babel.bansefi.banksystem.personas.webservices.altadatosprofesionales;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.personas.webservices.altadatosprofesionales package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_ALTA_COMPL_PROF_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.personas.webservices.altadatosprofesionales
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
     * Create an instance of {@link ResponseBansefi.OTRPEALTACOMPLPROFTR }
     * 
     */
    public ResponseBansefi.OTRPEALTACOMPLPROFTR createResponseBansefiOTRPEALTACOMPLPROFTR() {
        return new ResponseBansefi.OTRPEALTACOMPLPROFTR();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPEALTACOMPLPROFTR.TRPEALTACOMPLPROFEVT }
     * 
     */
    public ResponseBansefi.OTRPEALTACOMPLPROFTR.TRPEALTACOMPLPROFEVT createResponseBansefiOTRPEALTACOMPLPROFTRTRPEALTACOMPLPROFEVT() {
        return new ResponseBansefi.OTRPEALTACOMPLPROFTR.TRPEALTACOMPLPROFEVT();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEALTACOMPLPROFTR }
     * 
     */
    public Ejecutar.ITRPEALTACOMPLPROFTR createEjecutarITRPEALTACOMPLPROFTR() {
        return new Ejecutar.ITRPEALTACOMPLPROFTR();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEALTACOMPLPROFTR.TRPEALTACOMPLPROFEVT }
     * 
     */
    public Ejecutar.ITRPEALTACOMPLPROFTR.TRPEALTACOMPLPROFEVT createEjecutarITRPEALTACOMPLPROFTRTRPEALTACOMPLPROFEVT() {
        return new Ejecutar.ITRPEALTACOMPLPROFTR.TRPEALTACOMPLPROFEVT();
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
     * Create an instance of {@link ResponseBansefi.OTRPEALTACOMPLPROFTR.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRPEALTACOMPLPROFTR.STDTRNMSJPARMV createResponseBansefiOTRPEALTACOMPLPROFTRSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRPEALTACOMPLPROFTR.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPEALTACOMPLPROFTR.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRPEALTACOMPLPROFTR.STDTRNOPARMV createResponseBansefiOTRPEALTACOMPLPROFTRSTDTRNOPARMV() {
        return new ResponseBansefi.OTRPEALTACOMPLPROFTR.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPEALTACOMPLPROFTR.TRPEALTACOMPLPROFEVT.PEACTPROFINDVP }
     * 
     */
    public ResponseBansefi.OTRPEALTACOMPLPROFTR.TRPEALTACOMPLPROFEVT.PEACTPROFINDVP createResponseBansefiOTRPEALTACOMPLPROFTRTRPEALTACOMPLPROFEVTPEACTPROFINDVP() {
        return new ResponseBansefi.OTRPEALTACOMPLPROFTR.TRPEALTACOMPLPROFEVT.PEACTPROFINDVP();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEALTACOMPLPROFTR.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRPEALTACOMPLPROFTR.STDTRNIPARMV createEjecutarITRPEALTACOMPLPROFTRSTDTRNIPARMV() {
        return new Ejecutar.ITRPEALTACOMPLPROFTR.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEALTACOMPLPROFTR.TRPEALTACOMPLPROFEVT.STDFECANTGLABORALV }
     * 
     */
    public Ejecutar.ITRPEALTACOMPLPROFTR.TRPEALTACOMPLPROFEVT.STDFECANTGLABORALV createEjecutarITRPEALTACOMPLPROFTRTRPEALTACOMPLPROFEVTSTDFECANTGLABORALV() {
        return new Ejecutar.ITRPEALTACOMPLPROFTR.TRPEALTACOMPLPROFEVT.STDFECANTGLABORALV();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_ALTA_COMPL_PROF_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
