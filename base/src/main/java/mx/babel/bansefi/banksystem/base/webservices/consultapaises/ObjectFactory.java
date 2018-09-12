
package mx.babel.bansefi.banksystem.base.webservices.consultapaises;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.base.backends.consultapaises package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_AG_CNS_NOMBRE_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.base.backends.consultapaises
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
     * Create an instance of {@link ResponseBansefi.OTRAGCNSNOMBRETRNO }
     * 
     */
    public ResponseBansefi.OTRAGCNSNOMBRETRNO createResponseBansefiOTRAGCNSNOMBRETRNO() {
        return new ResponseBansefi.OTRAGCNSNOMBRETRNO();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRAGCNSNOMBRETRNI }
     * 
     */
    public Ejecutar.ITRAGCNSNOMBRETRNI createEjecutarITRAGCNSNOMBRETRNI() {
        return new Ejecutar.ITRAGCNSNOMBRETRNI();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRAGCNSNOMBRETRNI.TRAGCNSNOMBREEVTY }
     * 
     */
    public Ejecutar.ITRAGCNSNOMBRETRNI.TRAGCNSNOMBREEVTY createEjecutarITRAGCNSNOMBRETRNITRAGCNSNOMBREEVTY() {
        return new Ejecutar.ITRAGCNSNOMBRETRNI.TRAGCNSNOMBREEVTY();
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
     * Create an instance of {@link ResponseBansefi.OTRAGCNSNOMBRETRNO.TRAGCNSNOMBREEVTZ }
     * 
     */
    public ResponseBansefi.OTRAGCNSNOMBRETRNO.TRAGCNSNOMBREEVTZ createResponseBansefiOTRAGCNSNOMBRETRNOTRAGCNSNOMBREEVTZ() {
        return new ResponseBansefi.OTRAGCNSNOMBRETRNO.TRAGCNSNOMBREEVTZ();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRAGCNSNOMBRETRNO.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRAGCNSNOMBRETRNO.STDTRNMSJPARMV createResponseBansefiOTRAGCNSNOMBRETRNOSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRAGCNSNOMBRETRNO.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRAGCNSNOMBRETRNO.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRAGCNSNOMBRETRNO.STDTRNOPARMV createResponseBansefiOTRAGCNSNOMBRETRNOSTDTRNOPARMV() {
        return new ResponseBansefi.OTRAGCNSNOMBRETRNO.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRAGCNSNOMBRETRNI.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRAGCNSNOMBRETRNI.STDTRNIPARMV createEjecutarITRAGCNSNOMBRETRNISTDTRNIPARMV() {
        return new Ejecutar.ITRAGCNSNOMBRETRNI.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRAGCNSNOMBRETRNI.TRAGCNSNOMBREEVTY.AGLACB }
     * 
     */
    public Ejecutar.ITRAGCNSNOMBRETRNI.TRAGCNSNOMBREEVTY.AGLACB createEjecutarITRAGCNSNOMBRETRNITRAGCNSNOMBREEVTYAGLACB() {
        return new Ejecutar.ITRAGCNSNOMBRETRNI.TRAGCNSNOMBREEVTY.AGLACB();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_AG_CNS_NOMBRE_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
