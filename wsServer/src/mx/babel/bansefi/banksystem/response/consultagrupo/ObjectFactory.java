
package mx.babel.bansefi.banksystem.response.consultagrupo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.personas.webservices.consultagrupo package. 
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

    private final static QName _EjecutarResult_QNAME = new QName("http://BansefiNSS/WebServicesNSS/TR_GR_CONS_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.personas.webservices.consultagrupo
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
     * Create an instance of {@link ResponseBansefi.TRGRCONSTRNO }
     * 
     */
    public ResponseBansefi.TRGRCONSTRNO createResponseBansefiTRGRCONSTRNO() {
        return new ResponseBansefi.TRGRCONSTRNO();
    }

    /**
     * Create an instance of {@link Ejecutar.TRGRCONSTRNI }
     * 
     */
    public Ejecutar.TRGRCONSTRNI createEjecutarTRGRCONSTRNI() {
        return new Ejecutar.TRGRCONSTRNI();
    }

    /**
     * Create an instance of {@link Ejecutar.TRGRCONSTRNI.TRGRCONSEVTY }
     * 
     */
    public Ejecutar.TRGRCONSTRNI.TRGRCONSEVTY createEjecutarTRGRCONSTRNITRGRCONSEVTY() {
        return new Ejecutar.TRGRCONSTRNI.TRGRCONSEVTY();
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
     * Create an instance of {@link ArrayOfResponseBansefi }
     * 
     */
    public ArrayOfResponseBansefi createArrayOfResponseBansefi() {
        return new ArrayOfResponseBansefi();
    }

    /**
     * Create an instance of {@link ResponseBansefi.TRGRCONSTRNO.TRGRCONSEVTZ }
     * 
     */
    public ResponseBansefi.TRGRCONSTRNO.TRGRCONSEVTZ createResponseBansefiTRGRCONSTRNOTRGRCONSEVTZ() {
        return new ResponseBansefi.TRGRCONSTRNO.TRGRCONSEVTZ();
    }

    /**
     * Create an instance of {@link ResponseBansefi.TRGRCONSTRNO.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.TRGRCONSTRNO.STDTRNMSJPARMV createResponseBansefiTRGRCONSTRNOSTDTRNMSJPARMV() {
        return new ResponseBansefi.TRGRCONSTRNO.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.TRGRCONSTRNO.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.TRGRCONSTRNO.STDTRNOPARMV createResponseBansefiTRGRCONSTRNOSTDTRNOPARMV() {
        return new ResponseBansefi.TRGRCONSTRNO.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.TRGRCONSTRNI.STDTRNIPARMV }
     * 
     */
    public Ejecutar.TRGRCONSTRNI.STDTRNIPARMV createEjecutarTRGRCONSTRNISTDTRNIPARMV() {
        return new Ejecutar.TRGRCONSTRNI.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.TRGRCONSTRNI.TRGRCONSEVTY.GRGRPP }
     * 
     */
    public Ejecutar.TRGRCONSTRNI.TRGRCONSEVTY.GRGRPP createEjecutarTRGRCONSTRNITRGRCONSEVTYGRGRPP() {
        return new Ejecutar.TRGRCONSTRNI.TRGRCONSEVTY.GRGRPP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://BansefiNSS/WebServicesNSS/TR_GR_CONS_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(_EjecutarResult_QNAME, EjecutarResult.class, null, value);
    }

}
