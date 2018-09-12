
package mx.babel.bansefi.banksystem.personas.webservices.modificacionreferenciabancaria;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.personas.webservices.modificacionreferenciabancaria package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_MODI_ORG_REF_BAN_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.personas.webservices.modificacionreferenciabancaria
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
     * Create an instance of {@link ResponseBansefi.OPEMODIORGREFBANTRN }
     * 
     */
    public ResponseBansefi.OPEMODIORGREFBANTRN createResponseBansefiOPEMODIORGREFBANTRN() {
        return new ResponseBansefi.OPEMODIORGREFBANTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.IPEMODIORGREFBANTRN }
     * 
     */
    public Ejecutar.IPEMODIORGREFBANTRN createEjecutarIPEMODIORGREFBANTRN() {
        return new Ejecutar.IPEMODIORGREFBANTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.IPEMODIORGREFBANTRN.PEMODIORGREFBANEVTY }
     * 
     */
    public Ejecutar.IPEMODIORGREFBANTRN.PEMODIORGREFBANEVTY createEjecutarIPEMODIORGREFBANTRNPEMODIORGREFBANEVTY() {
        return new Ejecutar.IPEMODIORGREFBANTRN.PEMODIORGREFBANEVTY();
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
     * Create an instance of {@link ResponseBansefi.OPEMODIORGREFBANTRN.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OPEMODIORGREFBANTRN.STDTRNOPARMV createResponseBansefiOPEMODIORGREFBANTRNSTDTRNOPARMV() {
        return new ResponseBansefi.OPEMODIORGREFBANTRN.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OPEMODIORGREFBANTRN.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OPEMODIORGREFBANTRN.STDTRNMSJPARMV createResponseBansefiOPEMODIORGREFBANTRNSTDTRNMSJPARMV() {
        return new ResponseBansefi.OPEMODIORGREFBANTRN.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.IPEMODIORGREFBANTRN.STDTRNIPARMV }
     * 
     */
    public Ejecutar.IPEMODIORGREFBANTRN.STDTRNIPARMV createEjecutarIPEMODIORGREFBANTRNSTDTRNIPARMV() {
        return new Ejecutar.IPEMODIORGREFBANTRN.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.IPEMODIORGREFBANTRN.PEMODIORGREFBANEVTY.PEPERSRLREFBANP }
     * 
     */
    public Ejecutar.IPEMODIORGREFBANTRN.PEMODIORGREFBANEVTY.PEPERSRLREFBANP createEjecutarIPEMODIORGREFBANTRNPEMODIORGREFBANEVTYPEPERSRLREFBANP() {
        return new Ejecutar.IPEMODIORGREFBANTRN.PEMODIORGREFBANEVTY.PEPERSRLREFBANP();
    }

    /**
     * Create an instance of {@link Ejecutar.IPEMODIORGREFBANTRN.PEMODIORGREFBANEVTY.PEPERSRLREFBANE }
     * 
     */
    public Ejecutar.IPEMODIORGREFBANTRN.PEMODIORGREFBANEVTY.PEPERSRLREFBANE createEjecutarIPEMODIORGREFBANTRNPEMODIORGREFBANEVTYPEPERSRLREFBANE() {
        return new Ejecutar.IPEMODIORGREFBANTRN.PEMODIORGREFBANEVTY.PEPERSRLREFBANE();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_MODI_ORG_REF_BAN_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
