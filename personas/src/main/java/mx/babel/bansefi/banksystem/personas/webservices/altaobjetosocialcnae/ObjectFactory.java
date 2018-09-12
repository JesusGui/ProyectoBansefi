
package mx.babel.bansefi.banksystem.personas.webservices.altaobjetosocialcnae;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bansefinss.webservicesnss.tr_pe_ampli_cnae_objsoc_trn package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_AMPLI_CNAE_OBJSOC_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bansefinss.webservicesnss.tr_pe_ampli_cnae_objsoc_trn
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
     * Create an instance of {@link ResponseBansefi.OTRPEAMPLICNAEOBJSOC }
     * 
     */
    public ResponseBansefi.OTRPEAMPLICNAEOBJSOC createResponseBansefiOTRPEAMPLICNAEOBJSOC() {
        return new ResponseBansefi.OTRPEAMPLICNAEOBJSOC();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPEAMPLICNAEOBJSOC.TRPEAMPLICNAEOBJSOCE }
     * 
     */
    public ResponseBansefi.OTRPEAMPLICNAEOBJSOC.TRPEAMPLICNAEOBJSOCE createResponseBansefiOTRPEAMPLICNAEOBJSOCTRPEAMPLICNAEOBJSOCE() {
        return new ResponseBansefi.OTRPEAMPLICNAEOBJSOC.TRPEAMPLICNAEOBJSOCE();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEAMPLICNAEOBJSOC }
     * 
     */
    public Ejecutar.ITRPEAMPLICNAEOBJSOC createEjecutarITRPEAMPLICNAEOBJSOC() {
        return new Ejecutar.ITRPEAMPLICNAEOBJSOC();
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
     * Create an instance of {@link ResponseBansefi.OTRPEAMPLICNAEOBJSOC.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRPEAMPLICNAEOBJSOC.STDTRNMSJPARMV createResponseBansefiOTRPEAMPLICNAEOBJSOCSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRPEAMPLICNAEOBJSOC.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPEAMPLICNAEOBJSOC.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRPEAMPLICNAEOBJSOC.STDTRNOPARMV createResponseBansefiOTRPEAMPLICNAEOBJSOCSTDTRNOPARMV() {
        return new ResponseBansefi.OTRPEAMPLICNAEOBJSOC.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPEAMPLICNAEOBJSOC.TRPEAMPLICNAEOBJSOCE.PEOBJSOCIALP }
     * 
     */
    public ResponseBansefi.OTRPEAMPLICNAEOBJSOC.TRPEAMPLICNAEOBJSOCE.PEOBJSOCIALP createResponseBansefiOTRPEAMPLICNAEOBJSOCTRPEAMPLICNAEOBJSOCEPEOBJSOCIALP() {
        return new ResponseBansefi.OTRPEAMPLICNAEOBJSOC.TRPEAMPLICNAEOBJSOCE.PEOBJSOCIALP();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEAMPLICNAEOBJSOC.TRPEAMPLICNAEOBJSOCE }
     * 
     */
    public Ejecutar.ITRPEAMPLICNAEOBJSOC.TRPEAMPLICNAEOBJSOCE createEjecutarITRPEAMPLICNAEOBJSOCTRPEAMPLICNAEOBJSOCE() {
        return new Ejecutar.ITRPEAMPLICNAEOBJSOC.TRPEAMPLICNAEOBJSOCE();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEAMPLICNAEOBJSOC.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRPEAMPLICNAEOBJSOC.STDTRNIPARMV createEjecutarITRPEAMPLICNAEOBJSOCSTDTRNIPARMV() {
        return new Ejecutar.ITRPEAMPLICNAEOBJSOC.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_AMPLI_CNAE_OBJSOC_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
