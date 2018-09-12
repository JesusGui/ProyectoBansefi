
package mx.babel.bansefi.banksystem.administracion.webservices.modificacionatribuciones;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bansefinss.webservicesnss.tr_ep_modif_atrib_empl_trn package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_EP_MODIF_ATRIB_EMPL_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bansefinss.webservicesnss.tr_ep_modif_atrib_empl_trn
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
     * Create an instance of {@link ResponseBansefi.OTREPMODIFATRIBEMPLT }
     * 
     */
    public ResponseBansefi.OTREPMODIFATRIBEMPLT createResponseBansefiOTREPMODIFATRIBEMPLT() {
        return new ResponseBansefi.OTREPMODIFATRIBEMPLT();
    }

    /**
     * Create an instance of {@link Ejecutar.ITREPMODIFATRIBEMPLT }
     * 
     */
    public Ejecutar.ITREPMODIFATRIBEMPLT createEjecutarITREPMODIFATRIBEMPLT() {
        return new Ejecutar.ITREPMODIFATRIBEMPLT();
    }

    /**
     * Create an instance of {@link Ejecutar.ITREPMODIFATRIBEMPLT.TREPMODIFATRIBEMPLEV }
     * 
     */
    public Ejecutar.ITREPMODIFATRIBEMPLT.TREPMODIFATRIBEMPLEV createEjecutarITREPMODIFATRIBEMPLTTREPMODIFATRIBEMPLEV() {
        return new Ejecutar.ITREPMODIFATRIBEMPLT.TREPMODIFATRIBEMPLEV();
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
     * Create an instance of {@link ResponseBansefi.OTREPMODIFATRIBEMPLT.TREPMODIFATRIBEMPLEV }
     * 
     */
    public ResponseBansefi.OTREPMODIFATRIBEMPLT.TREPMODIFATRIBEMPLEV createResponseBansefiOTREPMODIFATRIBEMPLTTREPMODIFATRIBEMPLEV() {
        return new ResponseBansefi.OTREPMODIFATRIBEMPLT.TREPMODIFATRIBEMPLEV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTREPMODIFATRIBEMPLT.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTREPMODIFATRIBEMPLT.STDTRNMSJPARMV createResponseBansefiOTREPMODIFATRIBEMPLTSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTREPMODIFATRIBEMPLT.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTREPMODIFATRIBEMPLT.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTREPMODIFATRIBEMPLT.STDTRNOPARMV createResponseBansefiOTREPMODIFATRIBEMPLTSTDTRNOPARMV() {
        return new ResponseBansefi.OTREPMODIFATRIBEMPLT.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITREPMODIFATRIBEMPLT.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITREPMODIFATRIBEMPLT.STDTRNIPARMV createEjecutarITREPMODIFATRIBEMPLTSTDTRNIPARMV() {
        return new Ejecutar.ITREPMODIFATRIBEMPLT.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITREPMODIFATRIBEMPLT.TREPMODIFATRIBEMPLEV.EPATRIBUCIONESP }
     * 
     */
    public Ejecutar.ITREPMODIFATRIBEMPLT.TREPMODIFATRIBEMPLEV.EPATRIBUCIONESP createEjecutarITREPMODIFATRIBEMPLTTREPMODIFATRIBEMPLEVEPATRIBUCIONESP() {
        return new Ejecutar.ITREPMODIFATRIBEMPLT.TREPMODIFATRIBEMPLEV.EPATRIBUCIONESP();
    }

    /**
     * Create an instance of {@link Ejecutar.ITREPMODIFATRIBEMPLT.TREPMODIFATRIBEMPLEV.EPATRIBPERFTRANE }
     * 
     */
    public Ejecutar.ITREPMODIFATRIBEMPLT.TREPMODIFATRIBEMPLEV.EPATRIBPERFTRANE createEjecutarITREPMODIFATRIBEMPLTTREPMODIFATRIBEMPLEVEPATRIBPERFTRANE() {
        return new Ejecutar.ITREPMODIFATRIBEMPLT.TREPMODIFATRIBEMPLEV.EPATRIBPERFTRANE();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_EP_MODIF_ATRIB_EMPL_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
