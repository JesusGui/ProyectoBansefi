
package mx.babel.bansefi.banksystem.contabilidad.webservices.cierrecontable;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.contabilidad.webservices.cierreContable package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CIERRE_OFICINA_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.contabilidad.webservices.cierreContable
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
     * Create an instance of {@link ResponseBansefi.OTRCIERREOFICINATRNO }
     * 
     */
    public ResponseBansefi.OTRCIERREOFICINATRNO createResponseBansefiOTRCIERREOFICINATRNO() {
        return new ResponseBansefi.OTRCIERREOFICINATRNO();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCIERREOFICINATRNI }
     * 
     */
    public Ejecutar.ITRCIERREOFICINATRNI createEjecutarITRCIERREOFICINATRNI() {
        return new Ejecutar.ITRCIERREOFICINATRNI();
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
     * Create an instance of {@link ResponseBansefi.OTRCIERREOFICINATRNO.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRCIERREOFICINATRNO.STDTRNOPARMV createResponseBansefiOTRCIERREOFICINATRNOSTDTRNOPARMV() {
        return new ResponseBansefi.OTRCIERREOFICINATRNO.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCIERREOFICINATRNO.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRCIERREOFICINATRNO.STDTRNMSJPARMV createResponseBansefiOTRCIERREOFICINATRNOSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRCIERREOFICINATRNO.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCIERREOFICINATRNI.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRCIERREOFICINATRNI.STDTRNIPARMV createEjecutarITRCIERREOFICINATRNISTDTRNIPARMV() {
        return new Ejecutar.ITRCIERREOFICINATRNI.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCIERREOFICINATRNI.TRCIERREOFICINAEVTY }
     * 
     */
    public Ejecutar.ITRCIERREOFICINATRNI.TRCIERREOFICINAEVTY createEjecutarITRCIERREOFICINATRNITRCIERREOFICINAEVTY() {
        return new Ejecutar.ITRCIERREOFICINATRNI.TRCIERREOFICINAEVTY();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CIERRE_OFICINA_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}