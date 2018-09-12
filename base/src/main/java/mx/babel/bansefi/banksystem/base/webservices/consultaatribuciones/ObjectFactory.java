
package mx.babel.bansefi.banksystem.base.webservices.consultaatribuciones;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bansefinss.webservicesnss.tr_ep_con_lis_atrib_empl_trn package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_EP_CON_LIS_ATRIB_EMPL_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bansefinss.webservicesnss.tr_ep_con_lis_atrib_empl_trn
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
     * Create an instance of {@link ResponseBansefi.OTREPCONLISATRIBEMPL }
     * 
     */
    public ResponseBansefi.OTREPCONLISATRIBEMPL createResponseBansefiOTREPCONLISATRIBEMPL() {
        return new ResponseBansefi.OTREPCONLISATRIBEMPL();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTREPCONLISATRIBEMPL.TREPCONLISATRIBEMPL }
     * 
     */
    public ResponseBansefi.OTREPCONLISATRIBEMPL.TREPCONLISATRIBEMPL1 createResponseBansefiOTREPCONLISATRIBEMPLTREPCONLISATRIBEMPL() {
        return new ResponseBansefi.OTREPCONLISATRIBEMPL.TREPCONLISATRIBEMPL1();
    }

    /**
     * Create an instance of {@link Ejecutar.ITREPCONLISATRIBEMPL }
     * 
     */
    public Ejecutar.ITREPCONLISATRIBEMPL createEjecutarITREPCONLISATRIBEMPL() {
        return new Ejecutar.ITREPCONLISATRIBEMPL();
    }

    /**
     * Create an instance of {@link Ejecutar.ITREPCONLISATRIBEMPL.TREPCONLISATRIBEMPL }
     * 
     */
    public Ejecutar.ITREPCONLISATRIBEMPL.TREPCONLISATRIBEMPL createEjecutarITREPCONLISATRIBEMPLTREPCONLISATRIBEMPL() {
        return new Ejecutar.ITREPCONLISATRIBEMPL.TREPCONLISATRIBEMPL();
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
     * Create an instance of {@link ResponseBansefi.OTREPCONLISATRIBEMPL.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTREPCONLISATRIBEMPL.STDTRNMSJPARMV createResponseBansefiOTREPCONLISATRIBEMPLSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTREPCONLISATRIBEMPL.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTREPCONLISATRIBEMPL.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTREPCONLISATRIBEMPL.STDTRNOPARMV createResponseBansefiOTREPCONLISATRIBEMPLSTDTRNOPARMV() {
        return new ResponseBansefi.OTREPCONLISATRIBEMPL.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTREPCONLISATRIBEMPL.TREPCONLISATRIBEMPL.TREPCONLISATRIBEMPL }
     * 
     */
    public ResponseBansefi.OTREPCONLISATRIBEMPL.TREPCONLISATRIBEMPL1.TREPCONLISATRIBEMPL createResponseBansefiOTREPCONLISATRIBEMPLTREPCONLISATRIBEMPLTREPCONLISATRIBEMPL() {
        return new ResponseBansefi.OTREPCONLISATRIBEMPL.TREPCONLISATRIBEMPL1.TREPCONLISATRIBEMPL();
    }

    /**
     * Create an instance of {@link Ejecutar.ITREPCONLISATRIBEMPL.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITREPCONLISATRIBEMPL.STDTRNIPARMV createEjecutarITREPCONLISATRIBEMPLSTDTRNIPARMV() {
        return new Ejecutar.ITREPCONLISATRIBEMPL.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITREPCONLISATRIBEMPL.TREPCONLISATRIBEMPL.EPATRIBUCIONESP }
     * 
     */
    public Ejecutar.ITREPCONLISATRIBEMPL.TREPCONLISATRIBEMPL.EPATRIBUCIONESP createEjecutarITREPCONLISATRIBEMPLTREPCONLISATRIBEMPLEPATRIBUCIONESP() {
        return new Ejecutar.ITREPCONLISATRIBEMPL.TREPCONLISATRIBEMPL.EPATRIBUCIONESP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_EP_CON_LIS_ATRIB_EMPL_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
