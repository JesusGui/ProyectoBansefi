
package mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalleplanificacion;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalleplanificacion package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CONSU_ADIC_PL_AC_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.cuentas.webservices.consultadetalleplanificacion
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
     * Create an instance of {@link ResponseBansefi.OTRCONSUADICPLACTRN }
     * 
     */
    public ResponseBansefi.OTRCONSUADICPLACTRN createResponseBansefiOTRCONSUADICPLACTRN() {
        return new ResponseBansefi.OTRCONSUADICPLACTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSUADICPLACTRN }
     * 
     */
    public Ejecutar.ITRCONSUADICPLACTRN createEjecutarITRCONSUADICPLACTRN() {
        return new Ejecutar.ITRCONSUADICPLACTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSUADICPLACTRN.TRCONSUADICPLACEVTY }
     * 
     */
    public Ejecutar.ITRCONSUADICPLACTRN.TRCONSUADICPLACEVTY createEjecutarITRCONSUADICPLACTRNTRCONSUADICPLACEVTY() {
        return new Ejecutar.ITRCONSUADICPLACTRN.TRCONSUADICPLACEVTY();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSUADICPLACTRN.TRCONSUADICPLACEVTY.ACPLNFCNP }
     * 
     */
    public Ejecutar.ITRCONSUADICPLACTRN.TRCONSUADICPLACEVTY.ACPLNFCNP createEjecutarITRCONSUADICPLACTRNTRCONSUADICPLACEVTYACPLNFCNP() {
        return new Ejecutar.ITRCONSUADICPLACTRN.TRCONSUADICPLACEVTY.ACPLNFCNP();
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
     * Create an instance of {@link ResponseBansefi.OTRCONSUADICPLACTRN.TRCONSUADICPLACEVTZ }
     * 
     */
    public ResponseBansefi.OTRCONSUADICPLACTRN.TRCONSUADICPLACEVTZ createResponseBansefiOTRCONSUADICPLACTRNTRCONSUADICPLACEVTZ() {
        return new ResponseBansefi.OTRCONSUADICPLACTRN.TRCONSUADICPLACEVTZ();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCONSUADICPLACTRN.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRCONSUADICPLACTRN.STDTRNMSJPARMV createResponseBansefiOTRCONSUADICPLACTRNSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRCONSUADICPLACTRN.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCONSUADICPLACTRN.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRCONSUADICPLACTRN.STDTRNOPARMV createResponseBansefiOTRCONSUADICPLACTRNSTDTRNOPARMV() {
        return new ResponseBansefi.OTRCONSUADICPLACTRN.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSUADICPLACTRN.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRCONSUADICPLACTRN.STDTRNIPARMV createEjecutarITRCONSUADICPLACTRNSTDTRNIPARMV() {
        return new Ejecutar.ITRCONSUADICPLACTRN.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSUADICPLACTRN.TRCONSUADICPLACEVTY.ACPLNFCNP.TIPOPLNFCNSTDP }
     * 
     */
    public Ejecutar.ITRCONSUADICPLACTRN.TRCONSUADICPLACEVTY.ACPLNFCNP.TIPOPLNFCNSTDP createEjecutarITRCONSUADICPLACTRNTRCONSUADICPLACEVTYACPLNFCNPTIPOPLNFCNSTDP() {
        return new Ejecutar.ITRCONSUADICPLACTRN.TRCONSUADICPLACEVTY.ACPLNFCNP.TIPOPLNFCNSTDP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CONSU_ADIC_PL_AC_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
