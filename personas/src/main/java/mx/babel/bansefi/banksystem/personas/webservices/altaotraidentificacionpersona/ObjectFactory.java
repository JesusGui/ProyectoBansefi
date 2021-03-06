
package mx.babel.bansefi.banksystem.personas.webservices.altaotraidentificacionpersona;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.personas.webservices.altaotraidentificacionpersona package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_ALTA_PE_ID_EXT_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.personas.webservices.altaotraidentificacionpersona
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
     * Create an instance of {@link ResponseBansefi.OTRPEALTAPEIDEXTTRN }
     * 
     */
    public ResponseBansefi.OTRPEALTAPEIDEXTTRN createResponseBansefiOTRPEALTAPEIDEXTTRN() {
        return new ResponseBansefi.OTRPEALTAPEIDEXTTRN();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPEALTAPEIDEXTTRN.TRPEALTAPEIDEXTEVT }
     * 
     */
    public ResponseBansefi.OTRPEALTAPEIDEXTTRN.TRPEALTAPEIDEXTEVT createResponseBansefiOTRPEALTAPEIDEXTTRNTRPEALTAPEIDEXTEVT() {
        return new ResponseBansefi.OTRPEALTAPEIDEXTTRN.TRPEALTAPEIDEXTEVT();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEALTAPEIDEXTTRN }
     * 
     */
    public Ejecutar.ITRPEALTAPEIDEXTTRN createEjecutarITRPEALTAPEIDEXTTRN() {
        return new Ejecutar.ITRPEALTAPEIDEXTTRN();
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
     * Create an instance of {@link ResponseBansefi.OTRPEALTAPEIDEXTTRN.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRPEALTAPEIDEXTTRN.STDTRNMSJPARMV createResponseBansefiOTRPEALTAPEIDEXTTRNSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRPEALTAPEIDEXTTRN.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPEALTAPEIDEXTTRN.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRPEALTAPEIDEXTTRN.STDTRNOPARMV createResponseBansefiOTRPEALTAPEIDEXTTRNSTDTRNOPARMV() {
        return new ResponseBansefi.OTRPEALTAPEIDEXTTRN.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPEALTAPEIDEXTTRN.TRPEALTAPEIDEXTEVT.PEIDEXTP }
     * 
     */
    public ResponseBansefi.OTRPEALTAPEIDEXTTRN.TRPEALTAPEIDEXTEVT.PEIDEXTP createResponseBansefiOTRPEALTAPEIDEXTTRNTRPEALTAPEIDEXTEVTPEIDEXTP() {
        return new ResponseBansefi.OTRPEALTAPEIDEXTTRN.TRPEALTAPEIDEXTEVT.PEIDEXTP();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEALTAPEIDEXTTRN.TRPEALTAPEIDEXTEVT }
     * 
     */
    public Ejecutar.ITRPEALTAPEIDEXTTRN.TRPEALTAPEIDEXTEVT createEjecutarITRPEALTAPEIDEXTTRNTRPEALTAPEIDEXTEVT() {
        return new Ejecutar.ITRPEALTAPEIDEXTTRN.TRPEALTAPEIDEXTEVT();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEALTAPEIDEXTTRN.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRPEALTAPEIDEXTTRN.STDTRNIPARMV createEjecutarITRPEALTAPEIDEXTTRNSTDTRNIPARMV() {
        return new Ejecutar.ITRPEALTAPEIDEXTTRN.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_ALTA_PE_ID_EXT_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
