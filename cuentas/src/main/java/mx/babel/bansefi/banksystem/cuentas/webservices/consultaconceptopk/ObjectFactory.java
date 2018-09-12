
package mx.babel.bansefi.banksystem.cuentas.webservices.consultaconceptopk;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.cuentas.webservices.consultaconceptopk package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_NOTIF_CONS_PK_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.cuentas.webservices.consultaconceptopk
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
     * Create an instance of {@link ResponseBansefi.OTRNOTIFCONSPKTRNO }
     * 
     */
    public ResponseBansefi.OTRNOTIFCONSPKTRNO createResponseBansefiOTRNOTIFCONSPKTRNO() {
        return new ResponseBansefi.OTRNOTIFCONSPKTRNO();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRNOTIFCONSPKTRNI }
     * 
     */
    public Ejecutar.ITRNOTIFCONSPKTRNI createEjecutarITRNOTIFCONSPKTRNI() {
        return new Ejecutar.ITRNOTIFCONSPKTRNI();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRNOTIFCONSPKTRNI.TRNOTIFCONSPKEVTY }
     * 
     */
    public Ejecutar.ITRNOTIFCONSPKTRNI.TRNOTIFCONSPKEVTY createEjecutarITRNOTIFCONSPKTRNITRNOTIFCONSPKEVTY() {
        return new Ejecutar.ITRNOTIFCONSPKTRNI.TRNOTIFCONSPKEVTY();
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
     * Create an instance of {@link ResponseBansefi.OTRNOTIFCONSPKTRNO.TRNOTIFCONSPKEVTZ }
     * 
     */
    public ResponseBansefi.OTRNOTIFCONSPKTRNO.TRNOTIFCONSPKEVTZ createResponseBansefiOTRNOTIFCONSPKTRNOTRNOTIFCONSPKEVTZ() {
        return new ResponseBansefi.OTRNOTIFCONSPKTRNO.TRNOTIFCONSPKEVTZ();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRNOTIFCONSPKTRNO.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRNOTIFCONSPKTRNO.STDTRNMSJPARMV createResponseBansefiOTRNOTIFCONSPKTRNOSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRNOTIFCONSPKTRNO.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRNOTIFCONSPKTRNO.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRNOTIFCONSPKTRNO.STDTRNOPARMV createResponseBansefiOTRNOTIFCONSPKTRNOSTDTRNOPARMV() {
        return new ResponseBansefi.OTRNOTIFCONSPKTRNO.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRNOTIFCONSPKTRNI.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRNOTIFCONSPKTRNI.STDTRNIPARMV createEjecutarITRNOTIFCONSPKTRNISTDTRNIPARMV() {
        return new Ejecutar.ITRNOTIFCONSPKTRNI.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRNOTIFCONSPKTRNI.TRNOTIFCONSPKEVTY.PKPARMCDP }
     * 
     */
    public Ejecutar.ITRNOTIFCONSPKTRNI.TRNOTIFCONSPKEVTY.PKPARMCDP createEjecutarITRNOTIFCONSPKTRNITRNOTIFCONSPKEVTYPKPARMCDP() {
        return new Ejecutar.ITRNOTIFCONSPKTRNI.TRNOTIFCONSPKEVTY.PKPARMCDP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_NOTIF_CONS_PK_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}