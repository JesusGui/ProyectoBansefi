
package mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.modificapersona;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.modificapersona package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_MODI_RL_PE_DS_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.modificapersona
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
     * Create an instance of {@link ResponseBansefi.OTRPEMODIRLPEDSTRN }
     * 
     */
    public ResponseBansefi.OTRPEMODIRLPEDSTRN createResponseBansefiOTRPEMODIRLPEDSTRN() {
        return new ResponseBansefi.OTRPEMODIRLPEDSTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEMODIRLPEDSTRN }
     * 
     */
    public Ejecutar.ITRPEMODIRLPEDSTRN createEjecutarITRPEMODIRLPEDSTRN() {
        return new Ejecutar.ITRPEMODIRLPEDSTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEMODIRLPEDSTRN.TRPEMODIRLPEDSEVTY }
     * 
     */
    public Ejecutar.ITRPEMODIRLPEDSTRN.TRPEMODIRLPEDSEVTY createEjecutarITRPEMODIRLPEDSTRNTRPEMODIRLPEDSEVTY() {
        return new Ejecutar.ITRPEMODIRLPEDSTRN.TRPEMODIRLPEDSEVTY();
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
     * Create an instance of {@link ResponseBansefi.OTRPEMODIRLPEDSTRN.TRPEMODIRLPEDSEVTZ }
     * 
     */
    public ResponseBansefi.OTRPEMODIRLPEDSTRN.TRPEMODIRLPEDSEVTZ createResponseBansefiOTRPEMODIRLPEDSTRNTRPEMODIRLPEDSEVTZ() {
        return new ResponseBansefi.OTRPEMODIRLPEDSTRN.TRPEMODIRLPEDSEVTZ();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPEMODIRLPEDSTRN.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRPEMODIRLPEDSTRN.STDTRNMSJPARMV createResponseBansefiOTRPEMODIRLPEDSTRNSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRPEMODIRLPEDSTRN.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPEMODIRLPEDSTRN.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRPEMODIRLPEDSTRN.STDTRNOPARMV createResponseBansefiOTRPEMODIRLPEDSTRNSTDTRNOPARMV() {
        return new ResponseBansefi.OTRPEMODIRLPEDSTRN.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEMODIRLPEDSTRN.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRPEMODIRLPEDSTRN.STDTRNIPARMV createEjecutarITRPEMODIRLPEDSTRNSTDTRNIPARMV() {
        return new Ejecutar.ITRPEMODIRLPEDSTRN.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEMODIRLPEDSTRN.TRPEMODIRLPEDSEVTY.PEPERSRLPERSP }
     * 
     */
    public Ejecutar.ITRPEMODIRLPEDSTRN.TRPEMODIRLPEDSEVTY.PEPERSRLPERSP createEjecutarITRPEMODIRLPEDSTRNTRPEMODIRLPEDSEVTYPEPERSRLPERSP() {
        return new Ejecutar.ITRPEMODIRLPEDSTRN.TRPEMODIRLPEDSEVTY.PEPERSRLPERSP();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEMODIRLPEDSTRN.TRPEMODIRLPEDSEVTY.INDCODPERLPECONTRARI }
     * 
     */
    public Ejecutar.ITRPEMODIRLPEDSTRN.TRPEMODIRLPEDSEVTY.INDCODPERLPECONTRARI createEjecutarITRPEMODIRLPEDSTRNTRPEMODIRLPEDSEVTYINDCODPERLPECONTRARI() {
        return new Ejecutar.ITRPEMODIRLPEDSTRN.TRPEMODIRLPEDSEVTY.INDCODPERLPECONTRARI();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_MODI_RL_PE_DS_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
