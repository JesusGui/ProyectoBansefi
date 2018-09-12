
package mx.babel.bansefi.banksystem.oficina.webservices.arqueopuesto;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bansefinss.webservicesnss.tr_tn_cont_host_cns_trn package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_TN_CONT_HOST_CNS_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bansefinss.webservicesnss.tr_tn_cont_host_cns_trn
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
     * Create an instance of {@link ResponseBansefi.OTRTNCONTHOSTCNSTRN }
     * 
     */
    public ResponseBansefi.OTRTNCONTHOSTCNSTRN createResponseBansefiOTRTNCONTHOSTCNSTRN() {
        return new ResponseBansefi.OTRTNCONTHOSTCNSTRN();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRTNCONTHOSTCNSTRN.TRCONSCONTHOSTEVTZ }
     * 
     */
    public ResponseBansefi.OTRTNCONTHOSTCNSTRN.TRCONSCONTHOSTEVTZ createResponseBansefiOTRTNCONTHOSTCNSTRNTRCONSCONTHOSTEVTZ() {
        return new ResponseBansefi.OTRTNCONTHOSTCNSTRN.TRCONSCONTHOSTEVTZ();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRTNCONTHOSTCNSTRN }
     * 
     */
    public Ejecutar.ITRTNCONTHOSTCNSTRN createEjecutarITRTNCONTHOSTCNSTRN() {
        return new Ejecutar.ITRTNCONTHOSTCNSTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRTNCONTHOSTCNSTRN.TRCONSCONTHOSTEVTY }
     * 
     */
    public Ejecutar.ITRTNCONTHOSTCNSTRN.TRCONSCONTHOSTEVTY createEjecutarITRTNCONTHOSTCNSTRNTRCONSCONTHOSTEVTY() {
        return new Ejecutar.ITRTNCONTHOSTCNSTRN.TRCONSCONTHOSTEVTY();
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
     * Create an instance of {@link ResponseBansefi.OTRTNCONTHOSTCNSTRN.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRTNCONTHOSTCNSTRN.STDTRNOPARMV createResponseBansefiOTRTNCONTHOSTCNSTRNSTDTRNOPARMV() {
        return new ResponseBansefi.OTRTNCONTHOSTCNSTRN.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRTNCONTHOSTCNSTRN.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRTNCONTHOSTCNSTRN.STDTRNMSJPARMV createResponseBansefiOTRTNCONTHOSTCNSTRNSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRTNCONTHOSTCNSTRN.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRTNCONTHOSTCNSTRN.TRCONSCONTHOSTEVTZ.TNCONTHOSTE }
     * 
     */
    public ResponseBansefi.OTRTNCONTHOSTCNSTRN.TRCONSCONTHOSTEVTZ.TNCONTHOSTE createResponseBansefiOTRTNCONTHOSTCNSTRNTRCONSCONTHOSTEVTZTNCONTHOSTE() {
        return new ResponseBansefi.OTRTNCONTHOSTCNSTRN.TRCONSCONTHOSTEVTZ.TNCONTHOSTE();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRTNCONTHOSTCNSTRN.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRTNCONTHOSTCNSTRN.STDTRNIPARMV createEjecutarITRTNCONTHOSTCNSTRNSTDTRNIPARMV() {
        return new Ejecutar.ITRTNCONTHOSTCNSTRN.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRTNCONTHOSTCNSTRN.TRCONSCONTHOSTEVTY.TNCONTHOSTP }
     * 
     */
    public Ejecutar.ITRTNCONTHOSTCNSTRN.TRCONSCONTHOSTEVTY.TNCONTHOSTP createEjecutarITRTNCONTHOSTCNSTRNTRCONSCONTHOSTEVTYTNCONTHOSTP() {
        return new Ejecutar.ITRTNCONTHOSTCNSTRN.TRCONSCONTHOSTEVTY.TNCONTHOSTP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_TN_CONT_HOST_CNS_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
