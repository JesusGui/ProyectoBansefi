
package mx.babel.bansefi.banksystem.personas.webservices.bajareferenciacomercial;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.personas.webservices.bajareferenciacomercial package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_BAJA_ORG_REF_COM_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.personas.webservices.bajareferenciacomercial
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
     * Create an instance of {@link ResponseBansefi.OPEBAJAORGREFCOMTRN }
     * 
     */
    public ResponseBansefi.OPEBAJAORGREFCOMTRN createResponseBansefiOPEBAJAORGREFCOMTRN() {
        return new ResponseBansefi.OPEBAJAORGREFCOMTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.IPEBAJAORGREFCOMTRN }
     * 
     */
    public Ejecutar.IPEBAJAORGREFCOMTRN createEjecutarIPEBAJAORGREFCOMTRN() {
        return new Ejecutar.IPEBAJAORGREFCOMTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.IPEBAJAORGREFCOMTRN.PEBAJAORGREFCOMEVTY }
     * 
     */
    public Ejecutar.IPEBAJAORGREFCOMTRN.PEBAJAORGREFCOMEVTY createEjecutarIPEBAJAORGREFCOMTRNPEBAJAORGREFCOMEVTY() {
        return new Ejecutar.IPEBAJAORGREFCOMTRN.PEBAJAORGREFCOMEVTY();
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
     * Create an instance of {@link ResponseBansefi.OPEBAJAORGREFCOMTRN.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OPEBAJAORGREFCOMTRN.STDTRNMSJPARMV createResponseBansefiOPEBAJAORGREFCOMTRNSTDTRNMSJPARMV() {
        return new ResponseBansefi.OPEBAJAORGREFCOMTRN.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OPEBAJAORGREFCOMTRN.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OPEBAJAORGREFCOMTRN.STDTRNOPARMV createResponseBansefiOPEBAJAORGREFCOMTRNSTDTRNOPARMV() {
        return new ResponseBansefi.OPEBAJAORGREFCOMTRN.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.IPEBAJAORGREFCOMTRN.STDTRNIPARMV }
     * 
     */
    public Ejecutar.IPEBAJAORGREFCOMTRN.STDTRNIPARMV createEjecutarIPEBAJAORGREFCOMTRNSTDTRNIPARMV() {
        return new Ejecutar.IPEBAJAORGREFCOMTRN.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.IPEBAJAORGREFCOMTRN.PEBAJAORGREFCOMEVTY.PEPERSRLREFCOMP }
     * 
     */
    public Ejecutar.IPEBAJAORGREFCOMTRN.PEBAJAORGREFCOMEVTY.PEPERSRLREFCOMP createEjecutarIPEBAJAORGREFCOMTRNPEBAJAORGREFCOMEVTYPEPERSRLREFCOMP() {
        return new Ejecutar.IPEBAJAORGREFCOMTRN.PEBAJAORGREFCOMEVTY.PEPERSRLREFCOMP();
    }

    /**
     * Create an instance of {@link Ejecutar.IPEBAJAORGREFCOMTRN.PEBAJAORGREFCOMEVTY.PEPERSRLREFCOME }
     * 
     */
    public Ejecutar.IPEBAJAORGREFCOMTRN.PEBAJAORGREFCOMEVTY.PEPERSRLREFCOME createEjecutarIPEBAJAORGREFCOMTRNPEBAJAORGREFCOMEVTYPEPERSRLREFCOME() {
        return new Ejecutar.IPEBAJAORGREFCOMTRN.PEBAJAORGREFCOMEVTY.PEPERSRLREFCOME();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_BAJA_ORG_REF_COM_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
