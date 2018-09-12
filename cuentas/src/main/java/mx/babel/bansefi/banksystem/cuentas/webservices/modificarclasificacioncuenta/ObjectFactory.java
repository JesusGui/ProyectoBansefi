
package mx.babel.bansefi.banksystem.cuentas.webservices.modificarclasificacioncuenta;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.cuentas.webservices.modificarclasificacioncuenta package. 
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

    private final static QName _EjecutarResult_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_ALTA_DATOS_CL_AC_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.cuentas.webservices.modificarclasificacioncuenta
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
     * Create an instance of {@link ResponseBansefi.OTRALTADATOSCLACTRN }
     * 
     */
    public ResponseBansefi.OTRALTADATOSCLACTRN createResponseBansefiOTRALTADATOSCLACTRN() {
        return new ResponseBansefi.OTRALTADATOSCLACTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRALTADATOSCLACTRN }
     * 
     */
    public Ejecutar.ITRALTADATOSCLACTRN createEjecutarITRALTADATOSCLACTRN() {
        return new Ejecutar.ITRALTADATOSCLACTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRALTADATOSCLACTRN.TRALTADATOSCLACEVTY }
     * 
     */
    public Ejecutar.ITRALTADATOSCLACTRN.TRALTADATOSCLACEVTY createEjecutarITRALTADATOSCLACTRNTRALTADATOSCLACEVTY() {
        return new Ejecutar.ITRALTADATOSCLACTRN.TRALTADATOSCLACEVTY();
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
     * Create an instance of {@link ResponseBansefi.OTRALTADATOSCLACTRN.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRALTADATOSCLACTRN.STDTRNMSJPARMV createResponseBansefiOTRALTADATOSCLACTRNSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRALTADATOSCLACTRN.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRALTADATOSCLACTRN.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRALTADATOSCLACTRN.STDTRNOPARMV createResponseBansefiOTRALTADATOSCLACTRNSTDTRNOPARMV() {
        return new ResponseBansefi.OTRALTADATOSCLACTRN.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRALTADATOSCLACTRN.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRALTADATOSCLACTRN.STDTRNIPARMV createEjecutarITRALTADATOSCLACTRNSTDTRNIPARMV() {
        return new Ejecutar.ITRALTADATOSCLACTRN.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRALTADATOSCLACTRN.TRALTADATOSCLACEVTY.ACACP }
     * 
     */
    public Ejecutar.ITRALTADATOSCLACTRN.TRALTADATOSCLACEVTY.ACACP createEjecutarITRALTADATOSCLACTRNTRALTADATOSCLACEVTYACACP() {
        return new Ejecutar.ITRALTADATOSCLACTRN.TRALTADATOSCLACEVTY.ACACP();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRALTADATOSCLACTRN.TRALTADATOSCLACEVTY.ACINFCONTABV }
     * 
     */
    public Ejecutar.ITRALTADATOSCLACTRN.TRALTADATOSCLACEVTY.ACINFCONTABV createEjecutarITRALTADATOSCLACTRNTRALTADATOSCLACEVTYACINFCONTABV() {
        return new Ejecutar.ITRALTADATOSCLACTRN.TRALTADATOSCLACEVTY.ACINFCONTABV();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_ALTA_DATOS_CL_AC_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(_EjecutarResult_QNAME, EjecutarResult.class, null, value);
    }

}
