
package mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.modificagestor;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.modificagestor package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_MODIF_RL_GT_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.modificagestor
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
     * Create an instance of {@link ResponseBansefi.OTRPEMODIFRLGTTRNO }
     * 
     */
    public ResponseBansefi.OTRPEMODIFRLGTTRNO createResponseBansefiOTRPEMODIFRLGTTRNO() {
        return new ResponseBansefi.OTRPEMODIFRLGTTRNO();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEMODIFRLGTTRNI }
     * 
     */
    public Ejecutar.ITRPEMODIFRLGTTRNI createEjecutarITRPEMODIFRLGTTRNI() {
        return new Ejecutar.ITRPEMODIFRLGTTRNI();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEMODIFRLGTTRNI.TRPEMODIFRLGTEVTY }
     * 
     */
    public Ejecutar.ITRPEMODIFRLGTTRNI.TRPEMODIFRLGTEVTY createEjecutarITRPEMODIFRLGTTRNITRPEMODIFRLGTEVTY() {
        return new Ejecutar.ITRPEMODIFRLGTTRNI.TRPEMODIFRLGTEVTY();
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
     * Create an instance of {@link ResponseBansefi.OTRPEMODIFRLGTTRNO.TRPEMODIFRLGTEVTZ }
     * 
     */
    public ResponseBansefi.OTRPEMODIFRLGTTRNO.TRPEMODIFRLGTEVTZ createResponseBansefiOTRPEMODIFRLGTTRNOTRPEMODIFRLGTEVTZ() {
        return new ResponseBansefi.OTRPEMODIFRLGTTRNO.TRPEMODIFRLGTEVTZ();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPEMODIFRLGTTRNO.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRPEMODIFRLGTTRNO.STDTRNMSJPARMV createResponseBansefiOTRPEMODIFRLGTTRNOSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRPEMODIFRLGTTRNO.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPEMODIFRLGTTRNO.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRPEMODIFRLGTTRNO.STDTRNOPARMV createResponseBansefiOTRPEMODIFRLGTTRNOSTDTRNOPARMV() {
        return new ResponseBansefi.OTRPEMODIFRLGTTRNO.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEMODIFRLGTTRNI.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRPEMODIFRLGTTRNI.STDTRNIPARMV createEjecutarITRPEMODIFRLGTTRNISTDTRNIPARMV() {
        return new Ejecutar.ITRPEMODIFRLGTTRNI.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEMODIFRLGTTRNI.TRPEMODIFRLGTEVTY.PEPERSRLGESTORP }
     * 
     */
    public Ejecutar.ITRPEMODIFRLGTTRNI.TRPEMODIFRLGTEVTY.PEPERSRLGESTORP createEjecutarITRPEMODIFRLGTTRNITRPEMODIFRLGTEVTYPEPERSRLGESTORP() {
        return new Ejecutar.ITRPEMODIFRLGTTRNI.TRPEMODIFRLGTEVTY.PEPERSRLGESTORP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_MODIF_RL_GT_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
