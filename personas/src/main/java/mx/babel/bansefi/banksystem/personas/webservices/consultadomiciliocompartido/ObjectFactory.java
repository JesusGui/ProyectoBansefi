
package mx.babel.bansefi.banksystem.personas.webservices.consultadomiciliocompartido;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.personas.webservices.consultadomiciliocompartido package. 
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

    private final static QName _EjecutarResult_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_COMP_LA_CNS_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.personas.webservices.consultadomiciliocompartido
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
     * Create an instance of {@link ResponseBansefi.OTRPECOMPLACNSTRNO }
     * 
     */
    public ResponseBansefi.OTRPECOMPLACNSTRNO createResponseBansefiOTRPECOMPLACNSTRNO() {
        return new ResponseBansefi.OTRPECOMPLACNSTRNO();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPECOMPLACNSTRNO.TRPECOMPLACNSEVTZ }
     * 
     */
    public ResponseBansefi.OTRPECOMPLACNSTRNO.TRPECOMPLACNSEVTZ createResponseBansefiOTRPECOMPLACNSTRNOTRPECOMPLACNSEVTZ() {
        return new ResponseBansefi.OTRPECOMPLACNSTRNO.TRPECOMPLACNSEVTZ();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPECOMPLACNSTRNI }
     * 
     */
    public Ejecutar.ITRPECOMPLACNSTRNI createEjecutarITRPECOMPLACNSTRNI() {
        return new Ejecutar.ITRPECOMPLACNSTRNI();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPECOMPLACNSTRNI.TRPECOMPLACNSEVTY }
     * 
     */
    public Ejecutar.ITRPECOMPLACNSTRNI.TRPECOMPLACNSEVTY createEjecutarITRPECOMPLACNSTRNITRPECOMPLACNSEVTY() {
        return new Ejecutar.ITRPECOMPLACNSTRNI.TRPECOMPLACNSEVTY();
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
     * Create an instance of {@link ResponseBansefi.OTRPECOMPLACNSTRNO.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRPECOMPLACNSTRNO.STDTRNMSJPARMV createResponseBansefiOTRPECOMPLACNSTRNOSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRPECOMPLACNSTRNO.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPECOMPLACNSTRNO.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRPECOMPLACNSTRNO.STDTRNOPARMV createResponseBansefiOTRPECOMPLACNSTRNOSTDTRNOPARMV() {
        return new ResponseBansefi.OTRPECOMPLACNSTRNO.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPECOMPLACNSTRNO.TRPECOMPLACNSEVTZ.TRPECOMPLACNSEVTV }
     * 
     */
    public ResponseBansefi.OTRPECOMPLACNSTRNO.TRPECOMPLACNSEVTZ.TRPECOMPLACNSEVTV createResponseBansefiOTRPECOMPLACNSTRNOTRPECOMPLACNSEVTZTRPECOMPLACNSEVTV() {
        return new ResponseBansefi.OTRPECOMPLACNSTRNO.TRPECOMPLACNSEVTZ.TRPECOMPLACNSEVTV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPECOMPLACNSTRNI.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRPECOMPLACNSTRNI.STDTRNIPARMV createEjecutarITRPECOMPLACNSTRNISTDTRNIPARMV() {
        return new Ejecutar.ITRPECOMPLACNSTRNI.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPECOMPLACNSTRNI.TRPECOMPLACNSEVTY.PECOMPV }
     * 
     */
    public Ejecutar.ITRPECOMPLACNSTRNI.TRPECOMPLACNSEVTY.PECOMPV createEjecutarITRPECOMPLACNSTRNITRPECOMPLACNSEVTYPECOMPV() {
        return new Ejecutar.ITRPECOMPLACNSTRNI.TRPECOMPLACNSEVTY.PECOMPV();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_COMP_LA_CNS_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(_EjecutarResult_QNAME, EjecutarResult.class, null, value);
    }

}
