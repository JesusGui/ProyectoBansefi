
package mx.babel.bansefi.banksystem.personas.webservices.bajaaccionistasfuncionarios;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.personas.webservices.bajaaccionistasfuncionarios package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_BAJA_ORG_ACC_FUN_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.personas.webservices.bajaaccionistasfuncionarios
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
     * Create an instance of {@link ResponseBansefi.OPEBAJAORGACCFUNTRN }
     * 
     */
    public ResponseBansefi.OPEBAJAORGACCFUNTRN createResponseBansefiOPEBAJAORGACCFUNTRN() {
        return new ResponseBansefi.OPEBAJAORGACCFUNTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.IPEBAJAORGACCFUNTRN }
     * 
     */
    public Ejecutar.IPEBAJAORGACCFUNTRN createEjecutarIPEBAJAORGACCFUNTRN() {
        return new Ejecutar.IPEBAJAORGACCFUNTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.IPEBAJAORGACCFUNTRN.PEBAJAORGACCFUNEVTY }
     * 
     */
    public Ejecutar.IPEBAJAORGACCFUNTRN.PEBAJAORGACCFUNEVTY createEjecutarIPEBAJAORGACCFUNTRNPEBAJAORGACCFUNEVTY() {
        return new Ejecutar.IPEBAJAORGACCFUNTRN.PEBAJAORGACCFUNEVTY();
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
     * Create an instance of {@link ResponseBansefi.OPEBAJAORGACCFUNTRN.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OPEBAJAORGACCFUNTRN.STDTRNMSJPARMV createResponseBansefiOPEBAJAORGACCFUNTRNSTDTRNMSJPARMV() {
        return new ResponseBansefi.OPEBAJAORGACCFUNTRN.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OPEBAJAORGACCFUNTRN.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OPEBAJAORGACCFUNTRN.STDTRNOPARMV createResponseBansefiOPEBAJAORGACCFUNTRNSTDTRNOPARMV() {
        return new ResponseBansefi.OPEBAJAORGACCFUNTRN.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.IPEBAJAORGACCFUNTRN.STDTRNIPARMV }
     * 
     */
    public Ejecutar.IPEBAJAORGACCFUNTRN.STDTRNIPARMV createEjecutarIPEBAJAORGACCFUNTRNSTDTRNIPARMV() {
        return new Ejecutar.IPEBAJAORGACCFUNTRN.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.IPEBAJAORGACCFUNTRN.PEBAJAORGACCFUNEVTY.PEPERSRLACCFUNE }
     * 
     */
    public Ejecutar.IPEBAJAORGACCFUNTRN.PEBAJAORGACCFUNEVTY.PEPERSRLACCFUNE createEjecutarIPEBAJAORGACCFUNTRNPEBAJAORGACCFUNEVTYPEPERSRLACCFUNE() {
        return new Ejecutar.IPEBAJAORGACCFUNTRN.PEBAJAORGACCFUNEVTY.PEPERSRLACCFUNE();
    }

    /**
     * Create an instance of {@link Ejecutar.IPEBAJAORGACCFUNTRN.PEBAJAORGACCFUNEVTY.PEPERSRLACCFUNP }
     * 
     */
    public Ejecutar.IPEBAJAORGACCFUNTRN.PEBAJAORGACCFUNEVTY.PEPERSRLACCFUNP createEjecutarIPEBAJAORGACCFUNTRNPEBAJAORGACCFUNEVTYPEPERSRLACCFUNP() {
        return new Ejecutar.IPEBAJAORGACCFUNTRN.PEBAJAORGACCFUNEVTY.PEPERSRLACCFUNP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_BAJA_ORG_ACC_FUN_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
