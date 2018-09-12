
package mx.babel.bansefi.banksystem.personas.webservices.altadatosempresarialespersona;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.personas.webservices.altadatosempresarialespersona package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_ALTA_COMPL_EMPR_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.personas.webservices.altadatosempresarialespersona
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
     * Create an instance of {@link ResponseBansefi.OTRPEALTACOMPLEMPRTR }
     * 
     */
    public ResponseBansefi.OTRPEALTACOMPLEMPRTR createResponseBansefiOTRPEALTACOMPLEMPRTR() {
        return new ResponseBansefi.OTRPEALTACOMPLEMPRTR();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPEALTACOMPLEMPRTR.TRPEALTACOMPLEMPREVT }
     * 
     */
    public ResponseBansefi.OTRPEALTACOMPLEMPRTR.TRPEALTACOMPLEMPREVT createResponseBansefiOTRPEALTACOMPLEMPRTRTRPEALTACOMPLEMPREVT() {
        return new ResponseBansefi.OTRPEALTACOMPLEMPRTR.TRPEALTACOMPLEMPREVT();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEALTACOMPLEMPRTR }
     * 
     */
    public Ejecutar.ITRPEALTACOMPLEMPRTR createEjecutarITRPEALTACOMPLEMPRTR() {
        return new Ejecutar.ITRPEALTACOMPLEMPRTR();
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
     * Create an instance of {@link ResponseBansefi.OTRPEALTACOMPLEMPRTR.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRPEALTACOMPLEMPRTR.STDTRNMSJPARMV createResponseBansefiOTRPEALTACOMPLEMPRTRSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRPEALTACOMPLEMPRTR.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPEALTACOMPLEMPRTR.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRPEALTACOMPLEMPRTR.STDTRNOPARMV createResponseBansefiOTRPEALTACOMPLEMPRTRSTDTRNOPARMV() {
        return new ResponseBansefi.OTRPEALTACOMPLEMPRTR.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPEALTACOMPLEMPRTR.TRPEALTACOMPLEMPREVT.PEEMPRESAP }
     * 
     */
    public ResponseBansefi.OTRPEALTACOMPLEMPRTR.TRPEALTACOMPLEMPREVT.PEEMPRESAP createResponseBansefiOTRPEALTACOMPLEMPRTRTRPEALTACOMPLEMPREVTPEEMPRESAP() {
        return new ResponseBansefi.OTRPEALTACOMPLEMPRTR.TRPEALTACOMPLEMPREVT.PEEMPRESAP();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEALTACOMPLEMPRTR.TRPEALTACOMPLEMPREVT }
     * 
     */
    public Ejecutar.ITRPEALTACOMPLEMPRTR.TRPEALTACOMPLEMPREVT createEjecutarITRPEALTACOMPLEMPRTRTRPEALTACOMPLEMPREVT() {
        return new Ejecutar.ITRPEALTACOMPLEMPRTR.TRPEALTACOMPLEMPREVT();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEALTACOMPLEMPRTR.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRPEALTACOMPLEMPRTR.STDTRNIPARMV createEjecutarITRPEALTACOMPLEMPRTRSTDTRNIPARMV() {
        return new Ejecutar.ITRPEALTACOMPLEMPRTR.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_ALTA_COMPL_EMPR_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
