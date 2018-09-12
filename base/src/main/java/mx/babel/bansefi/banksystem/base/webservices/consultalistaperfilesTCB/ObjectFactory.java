
package mx.babel.bansefi.banksystem.base.webservices.consultalistaperfilesTCB;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.base.backends.consultalistaperfilesTCB package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_LIST_PERFIL_ENT_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.base.backends.consultalistaperfilesTCB
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
     * Create an instance of {@link ResponseBansefi.OTRLISTPERFILENTTRNO }
     * 
     */
    public ResponseBansefi.OTRLISTPERFILENTTRNO createResponseBansefiOTRLISTPERFILENTTRNO() {
        return new ResponseBansefi.OTRLISTPERFILENTTRNO();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRLISTPERFILENTTRNO.TRLISTPERFILENTEVTZ }
     * 
     */
    public ResponseBansefi.OTRLISTPERFILENTTRNO.TRLISTPERFILENTEVTZ createResponseBansefiOTRLISTPERFILENTTRNOTRLISTPERFILENTEVTZ() {
        return new ResponseBansefi.OTRLISTPERFILENTTRNO.TRLISTPERFILENTEVTZ();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRLISTPERFILENTTRNI }
     * 
     */
    public Ejecutar.ITRLISTPERFILENTTRNI createEjecutarITRLISTPERFILENTTRNI() {
        return new Ejecutar.ITRLISTPERFILENTTRNI();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRLISTPERFILENTTRNI.TRLISTPERFILENTEVTY }
     * 
     */
    public Ejecutar.ITRLISTPERFILENTTRNI.TRLISTPERFILENTEVTY createEjecutarITRLISTPERFILENTTRNITRLISTPERFILENTEVTY() {
        return new Ejecutar.ITRLISTPERFILENTTRNI.TRLISTPERFILENTEVTY();
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
     * Create an instance of {@link ResponseBansefi.OTRLISTPERFILENTTRNO.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRLISTPERFILENTTRNO.STDTRNMSJPARMV createResponseBansefiOTRLISTPERFILENTTRNOSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRLISTPERFILENTTRNO.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRLISTPERFILENTTRNO.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRLISTPERFILENTTRNO.STDTRNOPARMV createResponseBansefiOTRLISTPERFILENTTRNOSTDTRNOPARMV() {
        return new ResponseBansefi.OTRLISTPERFILENTTRNO.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRLISTPERFILENTTRNO.TRLISTPERFILENTEVTZ.CRENTPERFILTXE }
     * 
     */
    public ResponseBansefi.OTRLISTPERFILENTTRNO.TRLISTPERFILENTEVTZ.CRENTPERFILTXE createResponseBansefiOTRLISTPERFILENTTRNOTRLISTPERFILENTEVTZCRENTPERFILTXE() {
        return new ResponseBansefi.OTRLISTPERFILENTTRNO.TRLISTPERFILENTEVTZ.CRENTPERFILTXE();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRLISTPERFILENTTRNI.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRLISTPERFILENTTRNI.STDTRNIPARMV createEjecutarITRLISTPERFILENTTRNISTDTRNIPARMV() {
        return new Ejecutar.ITRLISTPERFILENTTRNI.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRLISTPERFILENTTRNI.TRLISTPERFILENTEVTY.CRENTPERFILTXP }
     * 
     */
    public Ejecutar.ITRLISTPERFILENTTRNI.TRLISTPERFILENTEVTY.CRENTPERFILTXP createEjecutarITRLISTPERFILENTTRNITRLISTPERFILENTEVTYCRENTPERFILTXP() {
        return new Ejecutar.ITRLISTPERFILENTTRNI.TRLISTPERFILENTEVTY.CRENTPERFILTXP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_LIST_PERFIL_ENT_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
