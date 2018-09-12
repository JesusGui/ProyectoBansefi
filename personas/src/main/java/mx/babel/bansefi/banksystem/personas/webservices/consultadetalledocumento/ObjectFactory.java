
package mx.babel.bansefi.banksystem.personas.webservices.consultadetalledocumento;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.personas.webservices.consultadetalledocumento package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CONS_DOCUMENTO_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.personas.webservices.consultadetalledocumento
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
     * Create an instance of {@link ResponseBansefi.OTRCONSDOCUMENTOTRNO }
     * 
     */
    public ResponseBansefi.OTRCONSDOCUMENTOTRNO createResponseBansefiOTRCONSDOCUMENTOTRNO() {
        return new ResponseBansefi.OTRCONSDOCUMENTOTRNO();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSDOCUMENTOTRNI }
     * 
     */
    public Ejecutar.ITRCONSDOCUMENTOTRNI createEjecutarITRCONSDOCUMENTOTRNI() {
        return new Ejecutar.ITRCONSDOCUMENTOTRNI();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSDOCUMENTOTRNI.TRCONSDOCUMENTOEVTY }
     * 
     */
    public Ejecutar.ITRCONSDOCUMENTOTRNI.TRCONSDOCUMENTOEVTY createEjecutarITRCONSDOCUMENTOTRNITRCONSDOCUMENTOEVTY() {
        return new Ejecutar.ITRCONSDOCUMENTOTRNI.TRCONSDOCUMENTOEVTY();
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
     * Create an instance of {@link ResponseBansefi.OTRCONSDOCUMENTOTRNO.TRCONSDOCUMENTOEVTZ }
     * 
     */
    public ResponseBansefi.OTRCONSDOCUMENTOTRNO.TRCONSDOCUMENTOEVTZ createResponseBansefiOTRCONSDOCUMENTOTRNOTRCONSDOCUMENTOEVTZ() {
        return new ResponseBansefi.OTRCONSDOCUMENTOTRNO.TRCONSDOCUMENTOEVTZ();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCONSDOCUMENTOTRNO.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRCONSDOCUMENTOTRNO.STDTRNMSJPARMV createResponseBansefiOTRCONSDOCUMENTOTRNOSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRCONSDOCUMENTOTRNO.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCONSDOCUMENTOTRNO.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRCONSDOCUMENTOTRNO.STDTRNOPARMV createResponseBansefiOTRCONSDOCUMENTOTRNOSTDTRNOPARMV() {
        return new ResponseBansefi.OTRCONSDOCUMENTOTRNO.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSDOCUMENTOTRNI.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRCONSDOCUMENTOTRNI.STDTRNIPARMV createEjecutarITRCONSDOCUMENTOTRNISTDTRNIPARMV() {
        return new Ejecutar.ITRCONSDOCUMENTOTRNI.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSDOCUMENTOTRNI.TRCONSDOCUMENTOEVTY.DCDOCP }
     * 
     */
    public Ejecutar.ITRCONSDOCUMENTOTRNI.TRCONSDOCUMENTOEVTY.DCDOCP createEjecutarITRCONSDOCUMENTOTRNITRCONSDOCUMENTOEVTYDCDOCP() {
        return new Ejecutar.ITRCONSDOCUMENTOTRNI.TRCONSDOCUMENTOEVTY.DCDOCP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CONS_DOCUMENTO_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
