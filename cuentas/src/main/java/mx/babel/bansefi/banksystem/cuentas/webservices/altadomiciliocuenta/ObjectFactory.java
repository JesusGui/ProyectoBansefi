
package mx.babel.bansefi.banksystem.cuentas.webservices.altadomiciliocuenta;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.cuentas.webservices.altadomiciliocuenta package. 
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

    private final static QName _EjecutarResult_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_AC_CREAR_AC_DIR_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.cuentas.webservices.altadomiciliocuenta
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
     * Create an instance of {@link ResponseBansefi.OTRACCREARACDIRTRNO }
     * 
     */
    public ResponseBansefi.OTRACCREARACDIRTRNO createResponseBansefiOTRACCREARACDIRTRNO() {
        return new ResponseBansefi.OTRACCREARACDIRTRNO();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRACCREARACDIRTRNO.TRACCREARACDIREVTZ }
     * 
     */
    public ResponseBansefi.OTRACCREARACDIRTRNO.TRACCREARACDIREVTZ createResponseBansefiOTRACCREARACDIRTRNOTRACCREARACDIREVTZ() {
        return new ResponseBansefi.OTRACCREARACDIRTRNO.TRACCREARACDIREVTZ();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRACCREARACDIRTRNI }
     * 
     */
    public Ejecutar.ITRACCREARACDIRTRNI createEjecutarITRACCREARACDIRTRNI() {
        return new Ejecutar.ITRACCREARACDIRTRNI();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRACCREARACDIRTRNI.TRACCREARACDIREVTY }
     * 
     */
    public Ejecutar.ITRACCREARACDIRTRNI.TRACCREARACDIREVTY createEjecutarITRACCREARACDIRTRNITRACCREARACDIREVTY() {
        return new Ejecutar.ITRACCREARACDIRTRNI.TRACCREARACDIREVTY();
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
     * Create an instance of {@link ResponseBansefi.OTRACCREARACDIRTRNO.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRACCREARACDIRTRNO.STDTRNMSJPARMV createResponseBansefiOTRACCREARACDIRTRNOSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRACCREARACDIRTRNO.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRACCREARACDIRTRNO.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRACCREARACDIRTRNO.STDTRNOPARMV createResponseBansefiOTRACCREARACDIRTRNOSTDTRNOPARMV() {
        return new ResponseBansefi.OTRACCREARACDIRTRNO.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRACCREARACDIRTRNO.TRACCREARACDIREVTZ.ACDOMICILIOV }
     * 
     */
    public ResponseBansefi.OTRACCREARACDIRTRNO.TRACCREARACDIREVTZ.ACDOMICILIOV createResponseBansefiOTRACCREARACDIRTRNOTRACCREARACDIREVTZACDOMICILIOV() {
        return new ResponseBansefi.OTRACCREARACDIRTRNO.TRACCREARACDIREVTZ.ACDOMICILIOV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRACCREARACDIRTRNI.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRACCREARACDIRTRNI.STDTRNIPARMV createEjecutarITRACCREARACDIRTRNISTDTRNIPARMV() {
        return new Ejecutar.ITRACCREARACDIRTRNI.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRACCREARACDIRTRNI.TRACCREARACDIREVTY.ACACRLDIRPERE }
     * 
     */
    public Ejecutar.ITRACCREARACDIRTRNI.TRACCREARACDIREVTY.ACACRLDIRPERE createEjecutarITRACCREARACDIRTRNITRACCREARACDIREVTYACACRLDIRPERE() {
        return new Ejecutar.ITRACCREARACDIRTRNI.TRACCREARACDIREVTY.ACACRLDIRPERE();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_AC_CREAR_AC_DIR_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(_EjecutarResult_QNAME, EjecutarResult.class, null, value);
    }

}
