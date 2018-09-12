
package mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.consultagestor;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.consultagestor package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_RL_GT_LS_CNS_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.consultagestor
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
     * Create an instance of {@link ResponseBansefi.OTRPERLGTLSCNSTRNO }
     * 
     */
    public ResponseBansefi.OTRPERLGTLSCNSTRNO createResponseBansefiOTRPERLGTLSCNSTRNO() {
        return new ResponseBansefi.OTRPERLGTLSCNSTRNO();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPERLGTLSCNSTRNI }
     * 
     */
    public Ejecutar.ITRPERLGTLSCNSTRNI createEjecutarITRPERLGTLSCNSTRNI() {
        return new Ejecutar.ITRPERLGTLSCNSTRNI();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPERLGTLSCNSTRNI.TRPERLGTLSCNSEVTY }
     * 
     */
    public Ejecutar.ITRPERLGTLSCNSTRNI.TRPERLGTLSCNSEVTY createEjecutarITRPERLGTLSCNSTRNITRPERLGTLSCNSEVTY() {
        return new Ejecutar.ITRPERLGTLSCNSTRNI.TRPERLGTLSCNSEVTY();
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
     * Create an instance of {@link ResponseBansefi.OTRPERLGTLSCNSTRNO.TRPERLGTLSCNSEVTZ }
     * 
     */
    public ResponseBansefi.OTRPERLGTLSCNSTRNO.TRPERLGTLSCNSEVTZ createResponseBansefiOTRPERLGTLSCNSTRNOTRPERLGTLSCNSEVTZ() {
        return new ResponseBansefi.OTRPERLGTLSCNSTRNO.TRPERLGTLSCNSEVTZ();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPERLGTLSCNSTRNO.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRPERLGTLSCNSTRNO.STDTRNMSJPARMV createResponseBansefiOTRPERLGTLSCNSTRNOSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRPERLGTLSCNSTRNO.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPERLGTLSCNSTRNO.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRPERLGTLSCNSTRNO.STDTRNOPARMV createResponseBansefiOTRPERLGTLSCNSTRNOSTDTRNOPARMV() {
        return new ResponseBansefi.OTRPERLGTLSCNSTRNO.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPERLGTLSCNSTRNI.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRPERLGTLSCNSTRNI.STDTRNIPARMV createEjecutarITRPERLGTLSCNSTRNISTDTRNIPARMV() {
        return new Ejecutar.ITRPERLGTLSCNSTRNI.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPERLGTLSCNSTRNI.TRPERLGTLSCNSEVTY.PEPERSRLGESTORP }
     * 
     */
    public Ejecutar.ITRPERLGTLSCNSTRNI.TRPERLGTLSCNSEVTY.PEPERSRLGESTORP createEjecutarITRPERLGTLSCNSTRNITRPERLGTLSCNSEVTYPEPERSRLGESTORP() {
        return new Ejecutar.ITRPERLGTLSCNSTRNI.TRPERLGTLSCNSEVTY.PEPERSRLGESTORP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_RL_GT_LS_CNS_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
