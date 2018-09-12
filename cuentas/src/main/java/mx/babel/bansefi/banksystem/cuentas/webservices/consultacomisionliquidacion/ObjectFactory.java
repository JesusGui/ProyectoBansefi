
package mx.babel.bansefi.banksystem.cuentas.webservices.consultacomisionliquidacion;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.cuentas.webservices.consultacomisionliquidacion package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CONS_KT_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.cuentas.webservices.consultacomisionliquidacion
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
     * Create an instance of {@link ResponseBansefi.OTRCONSKTTRNO }
     * 
     */
    public ResponseBansefi.OTRCONSKTTRNO createResponseBansefiOTRCONSKTTRNO() {
        return new ResponseBansefi.OTRCONSKTTRNO();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCONSKTTRNO.TRCONSKTEVTZ }
     * 
     */
    public ResponseBansefi.OTRCONSKTTRNO.TRCONSKTEVTZ createResponseBansefiOTRCONSKTTRNOTRCONSKTEVTZ() {
        return new ResponseBansefi.OTRCONSKTTRNO.TRCONSKTEVTZ();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCONSKTTRNO.TRCONSKTEVTZ.KTDATOSLST }
     * 
     */
    public ResponseBansefi.OTRCONSKTTRNO.TRCONSKTEVTZ.KTDATOSLST createResponseBansefiOTRCONSKTTRNOTRCONSKTEVTZKTDATOSLST() {
        return new ResponseBansefi.OTRCONSKTTRNO.TRCONSKTEVTZ.KTDATOSLST();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSKTTRNI }
     * 
     */
    public Ejecutar.ITRCONSKTTRNI createEjecutarITRCONSKTTRNI() {
        return new Ejecutar.ITRCONSKTTRNI();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSKTTRNI.TRCONSKTEVTY }
     * 
     */
    public Ejecutar.ITRCONSKTTRNI.TRCONSKTEVTY createEjecutarITRCONSKTTRNITRCONSKTEVTY() {
        return new Ejecutar.ITRCONSKTTRNI.TRCONSKTEVTY();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSKTTRNI.TRCONSKTEVTY.KTCLAVEV }
     * 
     */
    public Ejecutar.ITRCONSKTTRNI.TRCONSKTEVTY.KTCLAVEV createEjecutarITRCONSKTTRNITRCONSKTEVTYKTCLAVEV() {
        return new Ejecutar.ITRCONSKTTRNI.TRCONSKTEVTY.KTCLAVEV();
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
     * Create an instance of {@link ResponseBansefi.OTRCONSKTTRNO.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRCONSKTTRNO.STDTRNMSJPARMV createResponseBansefiOTRCONSKTTRNOSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRCONSKTTRNO.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCONSKTTRNO.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRCONSKTTRNO.STDTRNOPARMV createResponseBansefiOTRCONSKTTRNOSTDTRNOPARMV() {
        return new ResponseBansefi.OTRCONSKTTRNO.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCONSKTTRNO.TRCONSKTEVTZ.AFAPNTEE }
     * 
     */
    public ResponseBansefi.OTRCONSKTTRNO.TRCONSKTEVTZ.AFAPNTEE createResponseBansefiOTRCONSKTTRNOTRCONSKTEVTZAFAPNTEE() {
        return new ResponseBansefi.OTRCONSKTTRNO.TRCONSKTEVTZ.AFAPNTEE();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCONSKTTRNO.TRCONSKTEVTZ.KTDATOSLST.KTDATOSV }
     * 
     */
    public ResponseBansefi.OTRCONSKTTRNO.TRCONSKTEVTZ.KTDATOSLST.KTDATOSV createResponseBansefiOTRCONSKTTRNOTRCONSKTEVTZKTDATOSLSTKTDATOSV() {
        return new ResponseBansefi.OTRCONSKTTRNO.TRCONSKTEVTZ.KTDATOSLST.KTDATOSV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSKTTRNI.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRCONSKTTRNI.STDTRNIPARMV createEjecutarITRCONSKTTRNISTDTRNIPARMV() {
        return new Ejecutar.ITRCONSKTTRNI.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSKTTRNI.TRCONSKTEVTY.TRCONSAFV }
     * 
     */
    public Ejecutar.ITRCONSKTTRNI.TRCONSKTEVTY.TRCONSAFV createEjecutarITRCONSKTTRNITRCONSKTEVTYTRCONSAFV() {
        return new Ejecutar.ITRCONSKTTRNI.TRCONSKTEVTY.TRCONSAFV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSKTTRNI.TRCONSKTEVTY.KTCLAVEV.IMPFACTURADOV }
     * 
     */
    public Ejecutar.ITRCONSKTTRNI.TRCONSKTEVTY.KTCLAVEV.IMPFACTURADOV createEjecutarITRCONSKTTRNITRCONSKTEVTYKTCLAVEVIMPFACTURADOV() {
        return new Ejecutar.ITRCONSKTTRNI.TRCONSKTEVTY.KTCLAVEV.IMPFACTURADOV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSKTTRNI.TRCONSKTEVTY.KTCLAVEV.HLCLAVEAFV }
     * 
     */
    public Ejecutar.ITRCONSKTTRNI.TRCONSKTEVTY.KTCLAVEV.HLCLAVEAFV createEjecutarITRCONSKTTRNITRCONSKTEVTYKTCLAVEVHLCLAVEAFV() {
        return new Ejecutar.ITRCONSKTTRNI.TRCONSKTEVTY.KTCLAVEV.HLCLAVEAFV();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CONS_KT_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}