
package mx.babel.bansefi.banksystem.personas.webservices.bajadocumento;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.personas.webservices.bajadocumento package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_BAJA_DC_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.personas.webservices.bajadocumento
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
     * Create an instance of {@link ResponseBansefi.OTRPEBAJADCTRNO }
     * 
     */
    public ResponseBansefi.OTRPEBAJADCTRNO createResponseBansefiOTRPEBAJADCTRNO() {
        return new ResponseBansefi.OTRPEBAJADCTRNO();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEBAJADCTRNI }
     * 
     */
    public Ejecutar.ITRPEBAJADCTRNI createEjecutarITRPEBAJADCTRNI() {
        return new Ejecutar.ITRPEBAJADCTRNI();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEBAJADCTRNI.TRPEBAJADCEVTY }
     * 
     */
    public Ejecutar.ITRPEBAJADCTRNI.TRPEBAJADCEVTY createEjecutarITRPEBAJADCTRNITRPEBAJADCEVTY() {
        return new Ejecutar.ITRPEBAJADCTRNI.TRPEBAJADCEVTY();
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
     * Create an instance of {@link ResponseBansefi.OTRPEBAJADCTRNO.TRPEBAJADCEVTZ }
     * 
     */
    public ResponseBansefi.OTRPEBAJADCTRNO.TRPEBAJADCEVTZ createResponseBansefiOTRPEBAJADCTRNOTRPEBAJADCEVTZ() {
        return new ResponseBansefi.OTRPEBAJADCTRNO.TRPEBAJADCEVTZ();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPEBAJADCTRNO.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRPEBAJADCTRNO.STDTRNMSJPARMV createResponseBansefiOTRPEBAJADCTRNOSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRPEBAJADCTRNO.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPEBAJADCTRNO.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRPEBAJADCTRNO.STDTRNOPARMV createResponseBansefiOTRPEBAJADCTRNOSTDTRNOPARMV() {
        return new ResponseBansefi.OTRPEBAJADCTRNO.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEBAJADCTRNI.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRPEBAJADCTRNI.STDTRNIPARMV createEjecutarITRPEBAJADCTRNISTDTRNIPARMV() {
        return new Ejecutar.ITRPEBAJADCTRNI.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEBAJADCTRNI.TRPEBAJADCEVTY.PEPERSRLDOCP }
     * 
     */
    public Ejecutar.ITRPEBAJADCTRNI.TRPEBAJADCEVTY.PEPERSRLDOCP createEjecutarITRPEBAJADCTRNITRPEBAJADCEVTYPEPERSRLDOCP() {
        return new Ejecutar.ITRPEBAJADCTRNI.TRPEBAJADCEVTY.PEPERSRLDOCP();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEBAJADCTRNI.TRPEBAJADCEVTY.BIBIENRLDOCP }
     * 
     */
    public Ejecutar.ITRPEBAJADCTRNI.TRPEBAJADCEVTY.BIBIENRLDOCP createEjecutarITRPEBAJADCTRNITRPEBAJADCEVTYBIBIENRLDOCP() {
        return new Ejecutar.ITRPEBAJADCTRNI.TRPEBAJADCEVTY.BIBIENRLDOCP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_BAJA_DC_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
