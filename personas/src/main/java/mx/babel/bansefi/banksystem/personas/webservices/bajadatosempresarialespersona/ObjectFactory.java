
package mx.babel.bansefi.banksystem.personas.webservices.bajadatosempresarialespersona;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.personas.webservices.bajadatosempresarialespersona package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_BAJA_COMPL_EMPR_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.personas.webservices.bajadatosempresarialespersona
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
     * Create an instance of {@link ResponseBansefi.OTRPEBAJACOMPLEMPRTR }
     * 
     */
    public ResponseBansefi.OTRPEBAJACOMPLEMPRTR createResponseBansefiOTRPEBAJACOMPLEMPRTR() {
        return new ResponseBansefi.OTRPEBAJACOMPLEMPRTR();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEBAJACOMPLEMPRTR }
     * 
     */
    public Ejecutar.ITRPEBAJACOMPLEMPRTR createEjecutarITRPEBAJACOMPLEMPRTR() {
        return new Ejecutar.ITRPEBAJACOMPLEMPRTR();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEBAJACOMPLEMPRTR.TRPEBAJACOMPLEMPREVT }
     * 
     */
    public Ejecutar.ITRPEBAJACOMPLEMPRTR.TRPEBAJACOMPLEMPREVT createEjecutarITRPEBAJACOMPLEMPRTRTRPEBAJACOMPLEMPREVT() {
        return new Ejecutar.ITRPEBAJACOMPLEMPRTR.TRPEBAJACOMPLEMPREVT();
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
     * Create an instance of {@link ResponseBansefi.OTRPEBAJACOMPLEMPRTR.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRPEBAJACOMPLEMPRTR.STDTRNMSJPARMV createResponseBansefiOTRPEBAJACOMPLEMPRTRSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRPEBAJACOMPLEMPRTR.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPEBAJACOMPLEMPRTR.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRPEBAJACOMPLEMPRTR.STDTRNOPARMV createResponseBansefiOTRPEBAJACOMPLEMPRTRSTDTRNOPARMV() {
        return new ResponseBansefi.OTRPEBAJACOMPLEMPRTR.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEBAJACOMPLEMPRTR.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRPEBAJACOMPLEMPRTR.STDTRNIPARMV createEjecutarITRPEBAJACOMPLEMPRTRSTDTRNIPARMV() {
        return new Ejecutar.ITRPEBAJACOMPLEMPRTR.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEBAJACOMPLEMPRTR.TRPEBAJACOMPLEMPREVT.PEEMPRESAP }
     * 
     */
    public Ejecutar.ITRPEBAJACOMPLEMPRTR.TRPEBAJACOMPLEMPREVT.PEEMPRESAP createEjecutarITRPEBAJACOMPLEMPRTRTRPEBAJACOMPLEMPREVTPEEMPRESAP() {
        return new Ejecutar.ITRPEBAJACOMPLEMPRTR.TRPEBAJACOMPLEMPREVT.PEEMPRESAP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_BAJA_COMPL_EMPR_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
