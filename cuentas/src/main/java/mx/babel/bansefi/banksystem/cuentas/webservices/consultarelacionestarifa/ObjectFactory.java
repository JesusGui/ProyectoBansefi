
package mx.babel.bansefi.banksystem.cuentas.webservices.consultarelacionestarifa;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.cuentas.webservices.consultarelacionestarifa package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_LISTA_LG_RL_PERS_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.cuentas.webservices.consultarelacionestarifa
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
     * Create an instance of {@link ResponseBansefi.OTRLISTALGRLPERSTRN }
     * 
     */
    public ResponseBansefi.OTRLISTALGRLPERSTRN createResponseBansefiOTRLISTALGRLPERSTRN() {
        return new ResponseBansefi.OTRLISTALGRLPERSTRN();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRLISTALGRLPERSTRN.TRLISTALGRLPERSEVTZ }
     * 
     */
    public ResponseBansefi.OTRLISTALGRLPERSTRN.TRLISTALGRLPERSEVTZ createResponseBansefiOTRLISTALGRLPERSTRNTRLISTALGRLPERSEVTZ() {
        return new ResponseBansefi.OTRLISTALGRLPERSTRN.TRLISTALGRLPERSEVTZ();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRLISTALGRLPERSTRN }
     * 
     */
    public Ejecutar.ITRLISTALGRLPERSTRN createEjecutarITRLISTALGRLPERSTRN() {
        return new Ejecutar.ITRLISTALGRLPERSTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRLISTALGRLPERSTRN.TRLISTALGRLPERSEVTY }
     * 
     */
    public Ejecutar.ITRLISTALGRLPERSTRN.TRLISTALGRLPERSEVTY createEjecutarITRLISTALGRLPERSTRNTRLISTALGRLPERSEVTY() {
        return new Ejecutar.ITRLISTALGRLPERSTRN.TRLISTALGRLPERSEVTY();
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
     * Create an instance of {@link ResponseBansefi.OTRLISTALGRLPERSTRN.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRLISTALGRLPERSTRN.STDTRNMSJPARMV createResponseBansefiOTRLISTALGRLPERSTRNSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRLISTALGRLPERSTRN.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRLISTALGRLPERSTRN.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRLISTALGRLPERSTRN.STDTRNOPARMV createResponseBansefiOTRLISTALGRLPERSTRNSTDTRNOPARMV() {
        return new ResponseBansefi.OTRLISTALGRLPERSTRN.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRLISTALGRLPERSTRN.TRLISTALGRLPERSEVTZ.LGPERSACE }
     * 
     */
    public ResponseBansefi.OTRLISTALGRLPERSTRN.TRLISTALGRLPERSEVTZ.LGPERSACE createResponseBansefiOTRLISTALGRLPERSTRNTRLISTALGRLPERSEVTZLGPERSACE() {
        return new ResponseBansefi.OTRLISTALGRLPERSTRN.TRLISTALGRLPERSEVTZ.LGPERSACE();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRLISTALGRLPERSTRN.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRLISTALGRLPERSTRN.STDTRNIPARMV createEjecutarITRLISTALGRLPERSTRNSTDTRNIPARMV() {
        return new Ejecutar.ITRLISTALGRLPERSTRN.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRLISTALGRLPERSTRN.TRLISTALGRLPERSEVTY.LGPERSACP }
     * 
     */
    public Ejecutar.ITRLISTALGRLPERSTRN.TRLISTALGRLPERSEVTY.LGPERSACP createEjecutarITRLISTALGRLPERSTRNTRLISTALGRLPERSEVTYLGPERSACP() {
        return new Ejecutar.ITRLISTALGRLPERSTRN.TRLISTALGRLPERSEVTY.LGPERSACP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_LISTA_LG_RL_PERS_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
