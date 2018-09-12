
package mx.babel.bansefi.banksystem.base.webservices.consultaempleado;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.base.webservices.consultaempleado package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CONS_EMPL_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.base.webservices.consultaempleado
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
     * Create an instance of {@link ResponseBansefi.OTRCONSEMPLTRNO }
     * 
     */
    public ResponseBansefi.OTRCONSEMPLTRNO createResponseBansefiOTRCONSEMPLTRNO() {
        return new ResponseBansefi.OTRCONSEMPLTRNO();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCONSEMPLTRNO.TRCONSEMPLEVTZ }
     * 
     */
    public ResponseBansefi.OTRCONSEMPLTRNO.TRCONSEMPLEVTZ createResponseBansefiOTRCONSEMPLTRNOTRCONSEMPLEVTZ() {
        return new ResponseBansefi.OTRCONSEMPLTRNO.TRCONSEMPLEVTZ();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSEMPLTRNI }
     * 
     */
    public Ejecutar.ITRCONSEMPLTRNI createEjecutarITRCONSEMPLTRNI() {
        return new Ejecutar.ITRCONSEMPLTRNI();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSEMPLTRNI.TRCONSEMPLEVTY }
     * 
     */
    public Ejecutar.ITRCONSEMPLTRNI.TRCONSEMPLEVTY createEjecutarITRCONSEMPLTRNITRCONSEMPLEVTY() {
        return new Ejecutar.ITRCONSEMPLTRNI.TRCONSEMPLEVTY();
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
     * Create an instance of {@link ResponseBansefi.OTRCONSEMPLTRNO.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRCONSEMPLTRNO.STDTRNMSJPARMV createResponseBansefiOTRCONSEMPLTRNOSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRCONSEMPLTRNO.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCONSEMPLTRNO.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRCONSEMPLTRNO.STDTRNOPARMV createResponseBansefiOTRCONSEMPLTRNOSTDTRNOPARMV() {
        return new ResponseBansefi.OTRCONSEMPLTRNO.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCONSEMPLTRNO.TRCONSEMPLEVTZ.PECONSINDVOBJTRDV }
     * 
     */
    public ResponseBansefi.OTRCONSEMPLTRNO.TRCONSEMPLEVTZ.PECONSINDVOBJTRDV createResponseBansefiOTRCONSEMPLTRNOTRCONSEMPLEVTZPECONSINDVOBJTRDV() {
        return new ResponseBansefi.OTRCONSEMPLTRNO.TRCONSEMPLEVTZ.PECONSINDVOBJTRDV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCONSEMPLTRNO.TRCONSEMPLEVTZ.EPPEDPOBJTRDV }
     * 
     */
    public ResponseBansefi.OTRCONSEMPLTRNO.TRCONSEMPLEVTZ.EPPEDPOBJTRDV createResponseBansefiOTRCONSEMPLTRNOTRCONSEMPLEVTZEPPEDPOBJTRDV() {
        return new ResponseBansefi.OTRCONSEMPLTRNO.TRCONSEMPLEVTZ.EPPEDPOBJTRDV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSEMPLTRNI.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRCONSEMPLTRNI.STDTRNIPARMV createEjecutarITRCONSEMPLTRNISTDTRNIPARMV() {
        return new Ejecutar.ITRCONSEMPLTRNI.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSEMPLTRNI.TRCONSEMPLEVTY.CLAVEEMPLEADOV }
     * 
     */
    public Ejecutar.ITRCONSEMPLTRNI.TRCONSEMPLEVTY.CLAVEEMPLEADOV createEjecutarITRCONSEMPLTRNITRCONSEMPLEVTYCLAVEEMPLEADOV() {
        return new Ejecutar.ITRCONSEMPLTRNI.TRCONSEMPLEVTY.CLAVEEMPLEADOV();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CONS_EMPL_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
