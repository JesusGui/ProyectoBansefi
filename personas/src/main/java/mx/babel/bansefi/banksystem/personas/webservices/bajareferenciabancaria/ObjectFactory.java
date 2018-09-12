
package mx.babel.bansefi.banksystem.personas.webservices.bajareferenciabancaria;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.personas.webservices.bajareferenciabancaria package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_BAJA_ORG_REF_BAN_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.personas.webservices.bajareferenciabancaria
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
     * Create an instance of {@link ResponseBansefi.OPEBAJAORGREFBANTRN }
     * 
     */
    public ResponseBansefi.OPEBAJAORGREFBANTRN createResponseBansefiOPEBAJAORGREFBANTRN() {
        return new ResponseBansefi.OPEBAJAORGREFBANTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.IPEBAJAORGREFBANTRN }
     * 
     */
    public Ejecutar.IPEBAJAORGREFBANTRN createEjecutarIPEBAJAORGREFBANTRN() {
        return new Ejecutar.IPEBAJAORGREFBANTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.IPEBAJAORGREFBANTRN.PEBAJAORGREFBANEVTY }
     * 
     */
    public Ejecutar.IPEBAJAORGREFBANTRN.PEBAJAORGREFBANEVTY createEjecutarIPEBAJAORGREFBANTRNPEBAJAORGREFBANEVTY() {
        return new Ejecutar.IPEBAJAORGREFBANTRN.PEBAJAORGREFBANEVTY();
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
     * Create an instance of {@link ResponseBansefi.OPEBAJAORGREFBANTRN.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OPEBAJAORGREFBANTRN.STDTRNMSJPARMV createResponseBansefiOPEBAJAORGREFBANTRNSTDTRNMSJPARMV() {
        return new ResponseBansefi.OPEBAJAORGREFBANTRN.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OPEBAJAORGREFBANTRN.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OPEBAJAORGREFBANTRN.STDTRNOPARMV createResponseBansefiOPEBAJAORGREFBANTRNSTDTRNOPARMV() {
        return new ResponseBansefi.OPEBAJAORGREFBANTRN.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.IPEBAJAORGREFBANTRN.STDTRNIPARMV }
     * 
     */
    public Ejecutar.IPEBAJAORGREFBANTRN.STDTRNIPARMV createEjecutarIPEBAJAORGREFBANTRNSTDTRNIPARMV() {
        return new Ejecutar.IPEBAJAORGREFBANTRN.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.IPEBAJAORGREFBANTRN.PEBAJAORGREFBANEVTY.PEPERSRLREFBANP }
     * 
     */
    public Ejecutar.IPEBAJAORGREFBANTRN.PEBAJAORGREFBANEVTY.PEPERSRLREFBANP createEjecutarIPEBAJAORGREFBANTRNPEBAJAORGREFBANEVTYPEPERSRLREFBANP() {
        return new Ejecutar.IPEBAJAORGREFBANTRN.PEBAJAORGREFBANEVTY.PEPERSRLREFBANP();
    }

    /**
     * Create an instance of {@link Ejecutar.IPEBAJAORGREFBANTRN.PEBAJAORGREFBANEVTY.PEPERSRLREFBANE }
     * 
     */
    public Ejecutar.IPEBAJAORGREFBANTRN.PEBAJAORGREFBANEVTY.PEPERSRLREFBANE createEjecutarIPEBAJAORGREFBANTRNPEBAJAORGREFBANEVTYPEPERSRLREFBANE() {
        return new Ejecutar.IPEBAJAORGREFBANTRN.PEBAJAORGREFBANEVTY.PEPERSRLREFBANE();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_BAJA_ORG_REF_BAN_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
