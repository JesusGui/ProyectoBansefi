
package mx.babel.bansefi.banksystem.base.webservices.consultaperfilempleado;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.base.webservices.consultaperfilempleado package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_EP_CON_PERFIL_EMPL_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.base.webservices.consultaperfilempleado
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
     * Create an instance of {@link ResponseBansefi.OTREPCONPERFILEMPLTR }
     * 
     */
    public ResponseBansefi.OTREPCONPERFILEMPLTR createResponseBansefiOTREPCONPERFILEMPLTR() {
        return new ResponseBansefi.OTREPCONPERFILEMPLTR();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTREPCONPERFILEMPLTR.TREPCONPERFILEMPLEVT }
     * 
     */
    public ResponseBansefi.OTREPCONPERFILEMPLTR.TREPCONPERFILEMPLEVT createResponseBansefiOTREPCONPERFILEMPLTRTREPCONPERFILEMPLEVT() {
        return new ResponseBansefi.OTREPCONPERFILEMPLTR.TREPCONPERFILEMPLEVT();
    }

    /**
     * Create an instance of {@link Ejecutar.ITREPCONPERFILEMPLTR }
     * 
     */
    public Ejecutar.ITREPCONPERFILEMPLTR createEjecutarITREPCONPERFILEMPLTR() {
        return new Ejecutar.ITREPCONPERFILEMPLTR();
    }

    /**
     * Create an instance of {@link Ejecutar.ITREPCONPERFILEMPLTR.TREPCONPERFILEMPLEVT }
     * 
     */
    public Ejecutar.ITREPCONPERFILEMPLTR.TREPCONPERFILEMPLEVT createEjecutarITREPCONPERFILEMPLTRTREPCONPERFILEMPLEVT() {
        return new Ejecutar.ITREPCONPERFILEMPLTR.TREPCONPERFILEMPLEVT();
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
     * Create an instance of {@link ResponseBansefi.OTREPCONPERFILEMPLTR.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTREPCONPERFILEMPLTR.STDTRNMSJPARMV createResponseBansefiOTREPCONPERFILEMPLTRSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTREPCONPERFILEMPLTR.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTREPCONPERFILEMPLTR.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTREPCONPERFILEMPLTR.STDTRNOPARMV createResponseBansefiOTREPCONPERFILEMPLTRSTDTRNOPARMV() {
        return new ResponseBansefi.OTREPCONPERFILEMPLTR.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTREPCONPERFILEMPLTR.TREPCONPERFILEMPLEVT.EPEMPLPERFILTXE }
     * 
     */
    public ResponseBansefi.OTREPCONPERFILEMPLTR.TREPCONPERFILEMPLEVT.EPEMPLPERFILTXE createResponseBansefiOTREPCONPERFILEMPLTRTREPCONPERFILEMPLEVTEPEMPLPERFILTXE() {
        return new ResponseBansefi.OTREPCONPERFILEMPLTR.TREPCONPERFILEMPLEVT.EPEMPLPERFILTXE();
    }

    /**
     * Create an instance of {@link Ejecutar.ITREPCONPERFILEMPLTR.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITREPCONPERFILEMPLTR.STDTRNIPARMV createEjecutarITREPCONPERFILEMPLTRSTDTRNIPARMV() {
        return new Ejecutar.ITREPCONPERFILEMPLTR.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITREPCONPERFILEMPLTR.TREPCONPERFILEMPLEVT.EPEMPLPERFILTXP }
     * 
     */
    public Ejecutar.ITREPCONPERFILEMPLTR.TREPCONPERFILEMPLEVT.EPEMPLPERFILTXP createEjecutarITREPCONPERFILEMPLTRTREPCONPERFILEMPLEVTEPEMPLPERFILTXP() {
        return new Ejecutar.ITREPCONPERFILEMPLTR.TREPCONPERFILEMPLEVT.EPEMPLPERFILTXP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_EP_CON_PERFIL_EMPL_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
