
package mx.babel.bansefi.banksystem.personas.webservices.modificacionaccionistafuncionario;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.personas.webservices.modificacionaccionistafuncionario package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_MODI_ORG_ACC_FUN_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.personas.webservices.modificacionaccionistafuncionario
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
     * Create an instance of {@link ResponseBansefi.OPEMODIORGACCFUNTRN }
     * 
     */
    public ResponseBansefi.OPEMODIORGACCFUNTRN createResponseBansefiOPEMODIORGACCFUNTRN() {
        return new ResponseBansefi.OPEMODIORGACCFUNTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.IPEMODIORGACCFUNTRN }
     * 
     */
    public Ejecutar.IPEMODIORGACCFUNTRN createEjecutarIPEMODIORGACCFUNTRN() {
        return new Ejecutar.IPEMODIORGACCFUNTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.IPEMODIORGACCFUNTRN.PEMODIORGACCFUNEVTY }
     * 
     */
    public Ejecutar.IPEMODIORGACCFUNTRN.PEMODIORGACCFUNEVTY createEjecutarIPEMODIORGACCFUNTRNPEMODIORGACCFUNEVTY() {
        return new Ejecutar.IPEMODIORGACCFUNTRN.PEMODIORGACCFUNEVTY();
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
     * Create an instance of {@link ResponseBansefi.OPEMODIORGACCFUNTRN.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OPEMODIORGACCFUNTRN.STDTRNMSJPARMV createResponseBansefiOPEMODIORGACCFUNTRNSTDTRNMSJPARMV() {
        return new ResponseBansefi.OPEMODIORGACCFUNTRN.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OPEMODIORGACCFUNTRN.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OPEMODIORGACCFUNTRN.STDTRNOPARMV createResponseBansefiOPEMODIORGACCFUNTRNSTDTRNOPARMV() {
        return new ResponseBansefi.OPEMODIORGACCFUNTRN.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.IPEMODIORGACCFUNTRN.STDTRNIPARMV }
     * 
     */
    public Ejecutar.IPEMODIORGACCFUNTRN.STDTRNIPARMV createEjecutarIPEMODIORGACCFUNTRNSTDTRNIPARMV() {
        return new Ejecutar.IPEMODIORGACCFUNTRN.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.IPEMODIORGACCFUNTRN.PEMODIORGACCFUNEVTY.PEPERSRLACCFUNP }
     * 
     */
    public Ejecutar.IPEMODIORGACCFUNTRN.PEMODIORGACCFUNEVTY.PEPERSRLACCFUNP createEjecutarIPEMODIORGACCFUNTRNPEMODIORGACCFUNEVTYPEPERSRLACCFUNP() {
        return new Ejecutar.IPEMODIORGACCFUNTRN.PEMODIORGACCFUNEVTY.PEPERSRLACCFUNP();
    }

    /**
     * Create an instance of {@link Ejecutar.IPEMODIORGACCFUNTRN.PEMODIORGACCFUNEVTY.PEPERSRLACCFUNE }
     * 
     */
    public Ejecutar.IPEMODIORGACCFUNTRN.PEMODIORGACCFUNEVTY.PEPERSRLACCFUNE createEjecutarIPEMODIORGACCFUNTRNPEMODIORGACCFUNEVTYPEPERSRLACCFUNE() {
        return new Ejecutar.IPEMODIORGACCFUNTRN.PEMODIORGACCFUNEVTY.PEPERSRLACCFUNE();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_MODI_ORG_ACC_FUN_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
