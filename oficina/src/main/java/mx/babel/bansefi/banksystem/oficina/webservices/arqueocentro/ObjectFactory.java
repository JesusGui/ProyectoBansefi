
package mx.babel.bansefi.banksystem.oficina.webservices.arqueocentro;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.oficina.webservices.arqueocentro package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_ARQUEO_CAJA_EX_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.oficina.webservices.arqueocentro
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
     * Create an instance of {@link ResponseBansefi.OTRARQUEOCAJAEXTRNO }
     * 
     */
    public ResponseBansefi.OTRARQUEOCAJAEXTRNO createResponseBansefiOTRARQUEOCAJAEXTRNO() {
        return new ResponseBansefi.OTRARQUEOCAJAEXTRNO();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRARQUEOCAJAEXTRNI }
     * 
     */
    public Ejecutar.ITRARQUEOCAJAEXTRNI createEjecutarITRARQUEOCAJAEXTRNI() {
        return new Ejecutar.ITRARQUEOCAJAEXTRNI();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRARQUEOCAJAEXTRNI.TRARQUEOOFCNAEXEVTY }
     * 
     */
    public Ejecutar.ITRARQUEOCAJAEXTRNI.TRARQUEOOFCNAEXEVTY createEjecutarITRARQUEOCAJAEXTRNITRARQUEOOFCNAEXEVTY() {
        return new Ejecutar.ITRARQUEOCAJAEXTRNI.TRARQUEOOFCNAEXEVTY();
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
     * Create an instance of {@link ResponseBansefi.OTRARQUEOCAJAEXTRNO.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRARQUEOCAJAEXTRNO.STDTRNOPARMV createResponseBansefiOTRARQUEOCAJAEXTRNOSTDTRNOPARMV() {
        return new ResponseBansefi.OTRARQUEOCAJAEXTRNO.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRARQUEOCAJAEXTRNO.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRARQUEOCAJAEXTRNO.STDTRNMSJPARMV createResponseBansefiOTRARQUEOCAJAEXTRNOSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRARQUEOCAJAEXTRNO.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRARQUEOCAJAEXTRNI.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRARQUEOCAJAEXTRNI.STDTRNIPARMV createEjecutarITRARQUEOCAJAEXTRNISTDTRNIPARMV() {
        return new Ejecutar.ITRARQUEOCAJAEXTRNI.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRARQUEOCAJAEXTRNI.TRARQUEOOFCNAEXEVTY.EXEXISTMONEDAE }
     * 
     */
    public Ejecutar.ITRARQUEOCAJAEXTRNI.TRARQUEOOFCNAEXEVTY.EXEXISTMONEDAE createEjecutarITRARQUEOCAJAEXTRNITRARQUEOOFCNAEXEVTYEXEXISTMONEDAE() {
        return new Ejecutar.ITRARQUEOCAJAEXTRNI.TRARQUEOOFCNAEXEVTY.EXEXISTMONEDAE();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRARQUEOCAJAEXTRNI.TRARQUEOOFCNAEXEVTY.EXEXISTDESGLSE }
     * 
     */
    public Ejecutar.ITRARQUEOCAJAEXTRNI.TRARQUEOOFCNAEXEVTY.EXEXISTDESGLSE createEjecutarITRARQUEOCAJAEXTRNITRARQUEOOFCNAEXEVTYEXEXISTDESGLSE() {
        return new Ejecutar.ITRARQUEOCAJAEXTRNI.TRARQUEOOFCNAEXEVTY.EXEXISTDESGLSE();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_ARQUEO_CAJA_EX_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
