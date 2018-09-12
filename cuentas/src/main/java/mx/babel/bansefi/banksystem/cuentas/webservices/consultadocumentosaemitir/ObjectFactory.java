
package mx.babel.bansefi.banksystem.cuentas.webservices.consultadocumentosaemitir;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.cuentas.webservices.consultadocumentosaemitir package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_DE_TODOS_DOCU_AC_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.cuentas.webservices.consultadocumentosaemitir
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
     * Create an instance of {@link ResponseBansefi.OTRDETODOSDOCUACTRN }
     * 
     */
    public ResponseBansefi.OTRDETODOSDOCUACTRN createResponseBansefiOTRDETODOSDOCUACTRN() {
        return new ResponseBansefi.OTRDETODOSDOCUACTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRDETODOSDOCUACTRN }
     * 
     */
    public Ejecutar.ITRDETODOSDOCUACTRN createEjecutarITRDETODOSDOCUACTRN() {
        return new Ejecutar.ITRDETODOSDOCUACTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRDETODOSDOCUACTRN.TRDETODOSDOCUACEVTY }
     * 
     */
    public Ejecutar.ITRDETODOSDOCUACTRN.TRDETODOSDOCUACEVTY createEjecutarITRDETODOSDOCUACTRNTRDETODOSDOCUACEVTY() {
        return new Ejecutar.ITRDETODOSDOCUACTRN.TRDETODOSDOCUACEVTY();
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
     * Create an instance of {@link ResponseBansefi.OTRDETODOSDOCUACTRN.TRDETODOSDOCUACEVTZ }
     * 
     */
    public ResponseBansefi.OTRDETODOSDOCUACTRN.TRDETODOSDOCUACEVTZ createResponseBansefiOTRDETODOSDOCUACTRNTRDETODOSDOCUACEVTZ() {
        return new ResponseBansefi.OTRDETODOSDOCUACTRN.TRDETODOSDOCUACEVTZ();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRDETODOSDOCUACTRN.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRDETODOSDOCUACTRN.STDTRNMSJPARMV createResponseBansefiOTRDETODOSDOCUACTRNSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRDETODOSDOCUACTRN.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRDETODOSDOCUACTRN.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRDETODOSDOCUACTRN.STDTRNOPARMV createResponseBansefiOTRDETODOSDOCUACTRNSTDTRNOPARMV() {
        return new ResponseBansefi.OTRDETODOSDOCUACTRN.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRDETODOSDOCUACTRN.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRDETODOSDOCUACTRN.STDTRNIPARMV createEjecutarITRDETODOSDOCUACTRNSTDTRNIPARMV() {
        return new Ejecutar.ITRDETODOSDOCUACTRN.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRDETODOSDOCUACTRN.TRDETODOSDOCUACEVTY.ACACP }
     * 
     */
    public Ejecutar.ITRDETODOSDOCUACTRN.TRDETODOSDOCUACEVTY.ACACP createEjecutarITRDETODOSDOCUACTRNTRDETODOSDOCUACEVTYACACP() {
        return new Ejecutar.ITRDETODOSDOCUACTRN.TRDETODOSDOCUACEVTY.ACACP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_DE_TODOS_DOCU_AC_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
