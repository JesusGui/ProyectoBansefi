
package mx.babel.bansefi.banksystem.base.webservices.consultadatosgestor;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.personas.webservices.consultadatosgestor package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_GT_CONS_GT_GESTOR_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.personas.webservices.consultadatosgestor
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
     * Create an instance of {@link ResponseBansefi.OTRGTCONSGTGESTORTRN }
     * 
     */
    public ResponseBansefi.OTRGTCONSGTGESTORTRN createResponseBansefiOTRGTCONSGTGESTORTRN() {
        return new ResponseBansefi.OTRGTCONSGTGESTORTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRGTCONSGTGESTORTRN }
     * 
     */
    public Ejecutar.ITRGTCONSGTGESTORTRN createEjecutarITRGTCONSGTGESTORTRN() {
        return new Ejecutar.ITRGTCONSGTGESTORTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRGTCONSGTGESTORTRN.TRGTCONSGTGESTOREVT }
     * 
     */
    public Ejecutar.ITRGTCONSGTGESTORTRN.TRGTCONSGTGESTOREVT createEjecutarITRGTCONSGTGESTORTRNTRGTCONSGTGESTOREVT() {
        return new Ejecutar.ITRGTCONSGTGESTORTRN.TRGTCONSGTGESTOREVT();
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
     * Create an instance of {@link ResponseBansefi.OTRGTCONSGTGESTORTRN.TRGTCONSGTGESTOREVT }
     * 
     */
    public ResponseBansefi.OTRGTCONSGTGESTORTRN.TRGTCONSGTGESTOREVT createResponseBansefiOTRGTCONSGTGESTORTRNTRGTCONSGTGESTOREVT() {
        return new ResponseBansefi.OTRGTCONSGTGESTORTRN.TRGTCONSGTGESTOREVT();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRGTCONSGTGESTORTRN.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRGTCONSGTGESTORTRN.STDTRNMSJPARMV createResponseBansefiOTRGTCONSGTGESTORTRNSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRGTCONSGTGESTORTRN.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRGTCONSGTGESTORTRN.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRGTCONSGTGESTORTRN.STDTRNOPARMV createResponseBansefiOTRGTCONSGTGESTORTRNSTDTRNOPARMV() {
        return new ResponseBansefi.OTRGTCONSGTGESTORTRN.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRGTCONSGTGESTORTRN.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRGTCONSGTGESTORTRN.STDTRNIPARMV createEjecutarITRGTCONSGTGESTORTRNSTDTRNIPARMV() {
        return new Ejecutar.ITRGTCONSGTGESTORTRN.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRGTCONSGTGESTORTRN.TRGTCONSGTGESTOREVT.GTGESTORP }
     * 
     */
    public Ejecutar.ITRGTCONSGTGESTORTRN.TRGTCONSGTGESTOREVT.GTGESTORP createEjecutarITRGTCONSGTGESTORTRNTRGTCONSGTGESTOREVTGTGESTORP() {
        return new Ejecutar.ITRGTCONSGTGESTORTRN.TRGTCONSGTGESTOREVT.GTGESTORP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_GT_CONS_GT_GESTOR_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
