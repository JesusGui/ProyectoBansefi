
package mx.babel.bansefi.banksystem.cuentas.webservices.bajabloqueocuenta;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the mx.babel.bansefi.banksystem.cuentas.webservices.bajabloqueocuenta package.
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

    private final static QName _EJECUTARRESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_BAJA_BLOQUEO_PRTCN_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.cuentas.webservices.bajabloqueocuenta
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
     * Create an instance of {@link ResponseBansefi.OTRBAJABLOQUEOPRTCNTR }
     *
     */
    public ResponseBansefi.OTRBAJABLOQUEOPRTCNTR createResponseBansefiOTRBAJABLOQUEOPRTCNTR() {
        return new ResponseBansefi.OTRBAJABLOQUEOPRTCNTR();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRBAJABLOQUEOPRTCNTR }
     *
     */
    public Ejecutar.ITRBAJABLOQUEOPRTCNTR createEjecutarITRBAJABLOQUEOPRTCNTR() {
        return new Ejecutar.ITRBAJABLOQUEOPRTCNTR();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRBAJABLOQUEOPRTCNTR.TRBAJABLOQUEOPRTCNEVT }
     *
     */
    public Ejecutar.ITRBAJABLOQUEOPRTCNTR.TRBAJABLOQUEOPRTCNEVT createEjecutarITRBAJABLOQUEOPRTCNTRTRBAJABLOQUEOPRTCNEVT() {
        return new Ejecutar.ITRBAJABLOQUEOPRTCNTR.TRBAJABLOQUEOPRTCNEVT();
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
     * Create an instance of {@link ResponseBansefi.OTRBAJABLOQUEOPRTCNTR.STDTRNMSJPARMV }
     *
     */
    public ResponseBansefi.OTRBAJABLOQUEOPRTCNTR.STDTRNMSJPARMV createResponseBansefiOTRBAJABLOQUEOPRTCNTRSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRBAJABLOQUEOPRTCNTR.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRBAJABLOQUEOPRTCNTR.STDTRNOPARMV }
     *
     */
    public ResponseBansefi.OTRBAJABLOQUEOPRTCNTR.STDTRNOPARMV createResponseBansefiOTRBAJABLOQUEOPRTCNTRSTDTRNOPARMV() {
        return new ResponseBansefi.OTRBAJABLOQUEOPRTCNTR.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRBAJABLOQUEOPRTCNTR.STDTRNIPARMV }
     *
     */
    public Ejecutar.ITRBAJABLOQUEOPRTCNTR.STDTRNIPARMV createEjecutarITRBAJABLOQUEOPRTCNTRSTDTRNIPARMV() {
        return new Ejecutar.ITRBAJABLOQUEOPRTCNTR.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRBAJABLOQUEOPRTCNTR.TRBAJABLOQUEOPRTCNEVT.BPBLOQUEOPRTCNP }
     *
     */
    public Ejecutar.ITRBAJABLOQUEOPRTCNTR.TRBAJABLOQUEOPRTCNEVT.BPBLOQUEOPRTCNP createEjecutarITRBAJABLOQUEOPRTCNTRTRBAJABLOQUEOPRTCNEVTBPBLOQUEOPRTCNP() {
        return new Ejecutar.ITRBAJABLOQUEOPRTCNTR.TRBAJABLOQUEOPRTCNEVT.BPBLOQUEOPRTCNP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     *
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_BAJA_BLOQUEO_PRTCN_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(final EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(_EJECUTARRESULT_QNAME, EjecutarResult.class, null, value);
    }

}
