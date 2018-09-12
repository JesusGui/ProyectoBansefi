
package mx.babel.bansefi.banksystem.base.webservices.consultaposicion;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import mx.babel.bansefi.banksystem.base.webservices.consultaposicion.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultaposicion.EjecutarResponse;
import mx.babel.bansefi.banksystem.base.webservices.consultaposicion.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultaposicion.ResponseBansefi;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.base.webservices.consultaposicion package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_CONSULTA_POSICION_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.base.webservices.consultaposicion
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
     * Create an instance of {@link ResponseBansefi.OTRPECONSULTAPOSICION }
     * 
     */
    public ResponseBansefi.OTRPECONSULTAPOSICION createResponseBansefiOTRPECONSULTAPOSICION() {
        return new ResponseBansefi.OTRPECONSULTAPOSICION();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPECONSULTAPOSICION.TRPECONSULTAPOSICIONE }
     * 
     */
    public ResponseBansefi.OTRPECONSULTAPOSICION.TRPECONSULTAPOSICIONE createResponseBansefiOTRPECONSULTAPOSICIONTRPECONSULTAPOSICIONE() {
        return new ResponseBansefi.OTRPECONSULTAPOSICION.TRPECONSULTAPOSICIONE();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPECONSULTAPOSICION.TRPECONSULTAPOSICIONE.PERCONCEPTOSLST }
     * 
     */
    public ResponseBansefi.OTRPECONSULTAPOSICION.TRPECONSULTAPOSICIONE.PERCONCEPTOSLST createResponseBansefiOTRPECONSULTAPOSICIONTRPECONSULTAPOSICIONEPERCONCEPTOSLST() {
        return new ResponseBansefi.OTRPECONSULTAPOSICION.TRPECONSULTAPOSICIONE.PERCONCEPTOSLST();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPECONSULTAPOSICION.TRPECONSULTAPOSICIONE.PERCONCEPTOSLST.PERCONCEPTOSV }
     * 
     */
    public ResponseBansefi.OTRPECONSULTAPOSICION.TRPECONSULTAPOSICIONE.PERCONCEPTOSLST.PERCONCEPTOSV createResponseBansefiOTRPECONSULTAPOSICIONTRPECONSULTAPOSICIONEPERCONCEPTOSLSTPERCONCEPTOSV() {
        return new ResponseBansefi.OTRPECONSULTAPOSICION.TRPECONSULTAPOSICIONE.PERCONCEPTOSLST.PERCONCEPTOSV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPECONSULTAPOSICION.TRPECONSULTAPOSICIONE.PERPERSONASLST }
     * 
     */
    public ResponseBansefi.OTRPECONSULTAPOSICION.TRPECONSULTAPOSICIONE.PERPERSONASLST createResponseBansefiOTRPECONSULTAPOSICIONTRPECONSULTAPOSICIONEPERPERSONASLST() {
        return new ResponseBansefi.OTRPECONSULTAPOSICION.TRPECONSULTAPOSICIONE.PERPERSONASLST();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPECONSULTAPOSICION }
     * 
     */
    public Ejecutar.ITRPECONSULTAPOSICION createEjecutarITRPECONSULTAPOSICION() {
        return new Ejecutar.ITRPECONSULTAPOSICION();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPECONSULTAPOSICION.TRPECONSULTAPOSICIONE }
     * 
     */
    public Ejecutar.ITRPECONSULTAPOSICION.TRPECONSULTAPOSICIONE createEjecutarITRPECONSULTAPOSICIONTRPECONSULTAPOSICIONE() {
        return new Ejecutar.ITRPECONSULTAPOSICION.TRPECONSULTAPOSICIONE();
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
     * Create an instance of {@link ResponseBansefi.OTRPECONSULTAPOSICION.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRPECONSULTAPOSICION.STDTRNMSJPARMV createResponseBansefiOTRPECONSULTAPOSICIONSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRPECONSULTAPOSICION.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPECONSULTAPOSICION.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRPECONSULTAPOSICION.STDTRNOPARMV createResponseBansefiOTRPECONSULTAPOSICIONSTDTRNOPARMV() {
        return new ResponseBansefi.OTRPECONSULTAPOSICION.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPECONSULTAPOSICION.TRPECONSULTAPOSICIONE.PERCONCEPTOSLST.PERCONCEPTOSV.PERSALDOSV }
     * 
     */
    public ResponseBansefi.OTRPECONSULTAPOSICION.TRPECONSULTAPOSICIONE.PERCONCEPTOSLST.PERCONCEPTOSV.PERSALDOSV createResponseBansefiOTRPECONSULTAPOSICIONTRPECONSULTAPOSICIONEPERCONCEPTOSLSTPERCONCEPTOSVPERSALDOSV() {
        return new ResponseBansefi.OTRPECONSULTAPOSICION.TRPECONSULTAPOSICIONE.PERCONCEPTOSLST.PERCONCEPTOSV.PERSALDOSV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPECONSULTAPOSICION.TRPECONSULTAPOSICIONE.PERPERSONASLST.PERPERSONASV }
     * 
     */
    public ResponseBansefi.OTRPECONSULTAPOSICION.TRPECONSULTAPOSICIONE.PERPERSONASLST.PERPERSONASV createResponseBansefiOTRPECONSULTAPOSICIONTRPECONSULTAPOSICIONEPERPERSONASLSTPERPERSONASV() {
        return new ResponseBansefi.OTRPECONSULTAPOSICION.TRPECONSULTAPOSICIONE.PERPERSONASLST.PERPERSONASV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPECONSULTAPOSICION.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRPECONSULTAPOSICION.STDTRNIPARMV createEjecutarITRPECONSULTAPOSICIONSTDTRNIPARMV() {
        return new Ejecutar.ITRPECONSULTAPOSICION.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPECONSULTAPOSICION.TRPECONSULTAPOSICIONE.ACACP }
     * 
     */
    public Ejecutar.ITRPECONSULTAPOSICION.TRPECONSULTAPOSICIONE.ACACP createEjecutarITRPECONSULTAPOSICIONTRPECONSULTAPOSICIONEACACP() {
        return new Ejecutar.ITRPECONSULTAPOSICION.TRPECONSULTAPOSICIONE.ACACP();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPECONSULTAPOSICION.TRPECONSULTAPOSICIONE.GRGRPP }
     * 
     */
    public Ejecutar.ITRPECONSULTAPOSICION.TRPECONSULTAPOSICIONE.GRGRPP createEjecutarITRPECONSULTAPOSICIONTRPECONSULTAPOSICIONEGRGRPP() {
        return new Ejecutar.ITRPECONSULTAPOSICION.TRPECONSULTAPOSICIONE.GRGRPP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_CONSULTA_POSICION_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
