
package mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.bajagestor;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.bajagestor package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_BAJA_RL_GT_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.bajagestor
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
     * Create an instance of {@link ResponseBansefi.OTRPEBAJARLGTTRNO }
     * 
     */
    public ResponseBansefi.OTRPEBAJARLGTTRNO createResponseBansefiOTRPEBAJARLGTTRNO() {
        return new ResponseBansefi.OTRPEBAJARLGTTRNO();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEBAJARLGTTRNI }
     * 
     */
    public Ejecutar.ITRPEBAJARLGTTRNI createEjecutarITRPEBAJARLGTTRNI() {
        return new Ejecutar.ITRPEBAJARLGTTRNI();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEBAJARLGTTRNI.TRPEBAJARLGTEVTY }
     * 
     */
    public Ejecutar.ITRPEBAJARLGTTRNI.TRPEBAJARLGTEVTY createEjecutarITRPEBAJARLGTTRNITRPEBAJARLGTEVTY() {
        return new Ejecutar.ITRPEBAJARLGTTRNI.TRPEBAJARLGTEVTY();
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
     * Create an instance of {@link ResponseBansefi.OTRPEBAJARLGTTRNO.TRPEBAJARLGTEVTZ }
     * 
     */
    public ResponseBansefi.OTRPEBAJARLGTTRNO.TRPEBAJARLGTEVTZ createResponseBansefiOTRPEBAJARLGTTRNOTRPEBAJARLGTEVTZ() {
        return new ResponseBansefi.OTRPEBAJARLGTTRNO.TRPEBAJARLGTEVTZ();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPEBAJARLGTTRNO.INDMASGTV }
     * 
     */
    public ResponseBansefi.OTRPEBAJARLGTTRNO.INDMASGTV createResponseBansefiOTRPEBAJARLGTTRNOINDMASGTV() {
        return new ResponseBansefi.OTRPEBAJARLGTTRNO.INDMASGTV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPEBAJARLGTTRNO.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRPEBAJARLGTTRNO.STDTRNMSJPARMV createResponseBansefiOTRPEBAJARLGTTRNOSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRPEBAJARLGTTRNO.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPEBAJARLGTTRNO.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRPEBAJARLGTTRNO.STDTRNOPARMV createResponseBansefiOTRPEBAJARLGTTRNOSTDTRNOPARMV() {
        return new ResponseBansefi.OTRPEBAJARLGTTRNO.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEBAJARLGTTRNI.INDMASGTV }
     * 
     */
    public Ejecutar.ITRPEBAJARLGTTRNI.INDMASGTV createEjecutarITRPEBAJARLGTTRNIINDMASGTV() {
        return new Ejecutar.ITRPEBAJARLGTTRNI.INDMASGTV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEBAJARLGTTRNI.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRPEBAJARLGTTRNI.STDTRNIPARMV createEjecutarITRPEBAJARLGTTRNISTDTRNIPARMV() {
        return new Ejecutar.ITRPEBAJARLGTTRNI.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEBAJARLGTTRNI.TRPEBAJARLGTEVTY.PEPERSRLGESTORP }
     * 
     */
    public Ejecutar.ITRPEBAJARLGTTRNI.TRPEBAJARLGTEVTY.PEPERSRLGESTORP createEjecutarITRPEBAJARLGTTRNITRPEBAJARLGTEVTYPEPERSRLGESTORP() {
        return new Ejecutar.ITRPEBAJARLGTTRNI.TRPEBAJARLGTEVTY.PEPERSRLGESTORP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_BAJA_RL_GT_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
