
package mx.babel.bansefi.banksystem.personas.webservices.modificaciondatoprofesional;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bansefinss.webservicesnss.tr_pe_modif_compl_prof_trn package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_MODIF_COMPL_PROF_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bansefinss.webservicesnss.tr_pe_modif_compl_prof_trn
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
     * Create an instance of {@link ResponseBansefi.OTRPEMODIFCOMPLPROFT }
     * 
     */
    public ResponseBansefi.OTRPEMODIFCOMPLPROFT createResponseBansefiOTRPEMODIFCOMPLPROFT() {
        return new ResponseBansefi.OTRPEMODIFCOMPLPROFT();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEMODIFCOMPLPROFT }
     * 
     */
    public Ejecutar.ITRPEMODIFCOMPLPROFT createEjecutarITRPEMODIFCOMPLPROFT() {
        return new Ejecutar.ITRPEMODIFCOMPLPROFT();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEMODIFCOMPLPROFT.TRPEMODIFCOMPLPROFEV }
     * 
     */
    public Ejecutar.ITRPEMODIFCOMPLPROFT.TRPEMODIFCOMPLPROFEV createEjecutarITRPEMODIFCOMPLPROFTTRPEMODIFCOMPLPROFEV() {
        return new Ejecutar.ITRPEMODIFCOMPLPROFT.TRPEMODIFCOMPLPROFEV();
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
     * Create an instance of {@link ResponseBansefi.OTRPEMODIFCOMPLPROFT.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRPEMODIFCOMPLPROFT.STDTRNMSJPARMV createResponseBansefiOTRPEMODIFCOMPLPROFTSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRPEMODIFCOMPLPROFT.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPEMODIFCOMPLPROFT.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRPEMODIFCOMPLPROFT.STDTRNOPARMV createResponseBansefiOTRPEMODIFCOMPLPROFTSTDTRNOPARMV() {
        return new ResponseBansefi.OTRPEMODIFCOMPLPROFT.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEMODIFCOMPLPROFT.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRPEMODIFCOMPLPROFT.STDTRNIPARMV createEjecutarITRPEMODIFCOMPLPROFTSTDTRNIPARMV() {
        return new Ejecutar.ITRPEMODIFCOMPLPROFT.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEMODIFCOMPLPROFT.TRPEMODIFCOMPLPROFEV.PEACTPROFINDVP }
     * 
     */
    public Ejecutar.ITRPEMODIFCOMPLPROFT.TRPEMODIFCOMPLPROFEV.PEACTPROFINDVP createEjecutarITRPEMODIFCOMPLPROFTTRPEMODIFCOMPLPROFEVPEACTPROFINDVP() {
        return new Ejecutar.ITRPEMODIFCOMPLPROFT.TRPEMODIFCOMPLPROFEV.PEACTPROFINDVP();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEMODIFCOMPLPROFT.TRPEMODIFCOMPLPROFEV.STDFECANTGLABORALV }
     * 
     */
    public Ejecutar.ITRPEMODIFCOMPLPROFT.TRPEMODIFCOMPLPROFEV.STDFECANTGLABORALV createEjecutarITRPEMODIFCOMPLPROFTTRPEMODIFCOMPLPROFEVSTDFECANTGLABORALV() {
        return new Ejecutar.ITRPEMODIFCOMPLPROFT.TRPEMODIFCOMPLPROFEV.STDFECANTGLABORALV();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_MODIF_COMPL_PROF_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
