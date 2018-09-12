
package mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.consultagrupo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.consultagrupo package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_RL_GR_LST_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.consultagrupo
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
     * Create an instance of {@link ResponseBansefi.OTRPERLGRLSTTRNO }
     * 
     */
    public ResponseBansefi.OTRPERLGRLSTTRNO createResponseBansefiOTRPERLGRLSTTRNO() {
        return new ResponseBansefi.OTRPERLGRLSTTRNO();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPERLGRLSTTRNO.TRPERLGRLSTEVTZ }
     * 
     */
    public ResponseBansefi.OTRPERLGRLSTTRNO.TRPERLGRLSTEVTZ createResponseBansefiOTRPERLGRLSTTRNOTRPERLGRLSTEVTZ() {
        return new ResponseBansefi.OTRPERLGRLSTTRNO.TRPERLGRLSTEVTZ();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPERLGRLSTTRNI }
     * 
     */
    public Ejecutar.ITRPERLGRLSTTRNI createEjecutarITRPERLGRLSTTRNI() {
        return new Ejecutar.ITRPERLGRLSTTRNI();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPERLGRLSTTRNI.TRPERLGRLSTEVTY }
     * 
     */
    public Ejecutar.ITRPERLGRLSTTRNI.TRPERLGRLSTEVTY createEjecutarITRPERLGRLSTTRNITRPERLGRLSTEVTY() {
        return new Ejecutar.ITRPERLGRLSTTRNI.TRPERLGRLSTEVTY();
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
     * Create an instance of {@link ResponseBansefi.OTRPERLGRLSTTRNO.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRPERLGRLSTTRNO.STDTRNOPARMV createResponseBansefiOTRPERLGRLSTTRNOSTDTRNOPARMV() {
        return new ResponseBansefi.OTRPERLGRLSTTRNO.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPERLGRLSTTRNO.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRPERLGRLSTTRNO.STDTRNMSJPARMV createResponseBansefiOTRPERLGRLSTTRNOSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRPERLGRLSTTRNO.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPERLGRLSTTRNO.TRPERLGRLSTEVTZ.TRPERLGRLSTEVTV }
     * 
     */
    public ResponseBansefi.OTRPERLGRLSTTRNO.TRPERLGRLSTEVTZ.TRPERLGRLSTEVTV createResponseBansefiOTRPERLGRLSTTRNOTRPERLGRLSTEVTZTRPERLGRLSTEVTV() {
        return new ResponseBansefi.OTRPERLGRLSTTRNO.TRPERLGRLSTEVTZ.TRPERLGRLSTEVTV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPERLGRLSTTRNI.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRPERLGRLSTTRNI.STDTRNIPARMV createEjecutarITRPERLGRLSTTRNISTDTRNIPARMV() {
        return new Ejecutar.ITRPERLGRLSTTRNI.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPERLGRLSTTRNI.TRPERLGRLSTEVTY.GRGRPRLPERSP }
     * 
     */
    public Ejecutar.ITRPERLGRLSTTRNI.TRPERLGRLSTEVTY.GRGRPRLPERSP createEjecutarITRPERLGRLSTTRNITRPERLGRLSTEVTYGRGRPRLPERSP() {
        return new Ejecutar.ITRPERLGRLSTTRNI.TRPERLGRLSTEVTY.GRGRPRLPERSP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_RL_GR_LST_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
