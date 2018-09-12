
package mx.babel.bansefi.banksystem.cuentas.webservices.consultatramoliquidacion;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.cuentas.webservices.consultramoliquidacion package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CONS_HL_TRAMO_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.cuentas.webservices.consultramoliquidacion
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
     * Create an instance of {@link ResponseBansefi.OTRCONSHLTRAMOTRNO }
     * 
     */
    public ResponseBansefi.OTRCONSHLTRAMOTRNO createResponseBansefiOTRCONSHLTRAMOTRNO() {
        return new ResponseBansefi.OTRCONSHLTRAMOTRNO();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCONSHLTRAMOTRNO.TRCONSHLTRAMOEVTZ }
     * 
     */
    public ResponseBansefi.OTRCONSHLTRAMOTRNO.TRCONSHLTRAMOEVTZ createResponseBansefiOTRCONSHLTRAMOTRNOTRCONSHLTRAMOEVTZ() {
        return new ResponseBansefi.OTRCONSHLTRAMOTRNO.TRCONSHLTRAMOEVTZ();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSHLTRAMOTRNI }
     * 
     */
    public Ejecutar.ITRCONSHLTRAMOTRNI createEjecutarITRCONSHLTRAMOTRNI() {
        return new Ejecutar.ITRCONSHLTRAMOTRNI();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSHLTRAMOTRNI.TRCONSHLTRAMOEVTY }
     * 
     */
    public Ejecutar.ITRCONSHLTRAMOTRNI.TRCONSHLTRAMOEVTY createEjecutarITRCONSHLTRAMOTRNITRCONSHLTRAMOEVTY() {
        return new Ejecutar.ITRCONSHLTRAMOTRNI.TRCONSHLTRAMOEVTY();
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
     * Create an instance of {@link ResponseBansefi.OTRCONSHLTRAMOTRNO.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRCONSHLTRAMOTRNO.STDTRNMSJPARMV createResponseBansefiOTRCONSHLTRAMOTRNOSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRCONSHLTRAMOTRNO.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCONSHLTRAMOTRNO.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRCONSHLTRAMOTRNO.STDTRNOPARMV createResponseBansefiOTRCONSHLTRAMOTRNOSTDTRNOPARMV() {
        return new ResponseBansefi.OTRCONSHLTRAMOTRNO.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCONSHLTRAMOTRNO.TRCONSHLTRAMOEVTZ.HLTRAMOE }
     * 
     */
    public ResponseBansefi.OTRCONSHLTRAMOTRNO.TRCONSHLTRAMOEVTZ.HLTRAMOE createResponseBansefiOTRCONSHLTRAMOTRNOTRCONSHLTRAMOEVTZHLTRAMOE() {
        return new ResponseBansefi.OTRCONSHLTRAMOTRNO.TRCONSHLTRAMOEVTZ.HLTRAMOE();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSHLTRAMOTRNI.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRCONSHLTRAMOTRNI.STDTRNIPARMV createEjecutarITRCONSHLTRAMOTRNISTDTRNIPARMV() {
        return new Ejecutar.ITRCONSHLTRAMOTRNI.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSHLTRAMOTRNI.TRCONSHLTRAMOEVTY.HLTRAMOP }
     * 
     */
    public Ejecutar.ITRCONSHLTRAMOTRNI.TRCONSHLTRAMOEVTY.HLTRAMOP createEjecutarITRCONSHLTRAMOTRNITRCONSHLTRAMOEVTYHLTRAMOP() {
        return new Ejecutar.ITRCONSHLTRAMOTRNI.TRCONSHLTRAMOEVTY.HLTRAMOP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CONS_HL_TRAMO_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
