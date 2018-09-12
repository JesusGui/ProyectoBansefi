
package mx.babel.bansefi.banksystem.base.webservices.consultagrupo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.base.webservices.consultagrupo package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_GR_CONS_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.base.webservices.consultagrupo
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
     * Create an instance of {@link ResponseBansefi.OTRGRCONSTRNO }
     * 
     */
    public ResponseBansefi.OTRGRCONSTRNO createResponseBansefiOTRGRCONSTRNO() {
        return new ResponseBansefi.OTRGRCONSTRNO();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRGRCONSTRNI }
     * 
     */
    public Ejecutar.ITRGRCONSTRNI createEjecutarITRGRCONSTRNI() {
        return new Ejecutar.ITRGRCONSTRNI();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRGRCONSTRNI.TRGRCONSEVTY }
     * 
     */
    public Ejecutar.ITRGRCONSTRNI.TRGRCONSEVTY createEjecutarITRGRCONSTRNITRGRCONSEVTY() {
        return new Ejecutar.ITRGRCONSTRNI.TRGRCONSEVTY();
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
     * Create an instance of {@link ResponseBansefi.OTRGRCONSTRNO.TRGRCONSEVTZ }
     * 
     */
    public ResponseBansefi.OTRGRCONSTRNO.TRGRCONSEVTZ createResponseBansefiOTRGRCONSTRNOTRGRCONSEVTZ() {
        return new ResponseBansefi.OTRGRCONSTRNO.TRGRCONSEVTZ();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRGRCONSTRNO.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRGRCONSTRNO.STDTRNMSJPARMV createResponseBansefiOTRGRCONSTRNOSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRGRCONSTRNO.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRGRCONSTRNO.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRGRCONSTRNO.STDTRNOPARMV createResponseBansefiOTRGRCONSTRNOSTDTRNOPARMV() {
        return new ResponseBansefi.OTRGRCONSTRNO.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRGRCONSTRNI.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRGRCONSTRNI.STDTRNIPARMV createEjecutarITRGRCONSTRNISTDTRNIPARMV() {
        return new Ejecutar.ITRGRCONSTRNI.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRGRCONSTRNI.TRGRCONSEVTY.GRGRPP }
     * 
     */
    public Ejecutar.ITRGRCONSTRNI.TRGRCONSEVTY.GRGRPP createEjecutarITRGRCONSTRNITRGRCONSEVTYGRGRPP() {
        return new Ejecutar.ITRGRCONSTRNI.TRGRCONSEVTY.GRGRPP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_GR_CONS_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
