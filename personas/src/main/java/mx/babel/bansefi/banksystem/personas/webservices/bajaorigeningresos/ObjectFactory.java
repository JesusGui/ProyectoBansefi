
package mx.babel.bansefi.banksystem.personas.webservices.bajaorigeningresos;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bansefinss.webservicesnss.tr_pe_baja_orgn_ing_trn package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_BAJA_ORGN_ING_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bansefinss.webservicesnss.tr_pe_baja_orgn_ing_trn
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
     * Create an instance of {@link ResponseBansefi.OTRPEBAJAORGNINGTRN }
     * 
     */
    public ResponseBansefi.OTRPEBAJAORGNINGTRN createResponseBansefiOTRPEBAJAORGNINGTRN() {
        return new ResponseBansefi.OTRPEBAJAORGNINGTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEBAJAORGNINGTRN }
     * 
     */
    public Ejecutar.ITRPEBAJAORGNINGTRN createEjecutarITRPEBAJAORGNINGTRN() {
        return new Ejecutar.ITRPEBAJAORGNINGTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEBAJAORGNINGTRN.TRPEBAJAORGNINGEVTY }
     * 
     */
    public Ejecutar.ITRPEBAJAORGNINGTRN.TRPEBAJAORGNINGEVTY createEjecutarITRPEBAJAORGNINGTRNTRPEBAJAORGNINGEVTY() {
        return new Ejecutar.ITRPEBAJAORGNINGTRN.TRPEBAJAORGNINGEVTY();
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
     * Create an instance of {@link ResponseBansefi.OTRPEBAJAORGNINGTRN.TRPEBAJAORGNINGEVTZ }
     * 
     */
    public ResponseBansefi.OTRPEBAJAORGNINGTRN.TRPEBAJAORGNINGEVTZ createResponseBansefiOTRPEBAJAORGNINGTRNTRPEBAJAORGNINGEVTZ() {
        return new ResponseBansefi.OTRPEBAJAORGNINGTRN.TRPEBAJAORGNINGEVTZ();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPEBAJAORGNINGTRN.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRPEBAJAORGNINGTRN.STDTRNMSJPARMV createResponseBansefiOTRPEBAJAORGNINGTRNSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRPEBAJAORGNINGTRN.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPEBAJAORGNINGTRN.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRPEBAJAORGNINGTRN.STDTRNOPARMV createResponseBansefiOTRPEBAJAORGNINGTRNSTDTRNOPARMV() {
        return new ResponseBansefi.OTRPEBAJAORGNINGTRN.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEBAJAORGNINGTRN.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRPEBAJAORGNINGTRN.STDTRNIPARMV createEjecutarITRPEBAJAORGNINGTRNSTDTRNIPARMV() {
        return new Ejecutar.ITRPEBAJAORGNINGTRN.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEBAJAORGNINGTRN.TRPEBAJAORGNINGEVTY.PEORGNINGRINDVP }
     * 
     */
    public Ejecutar.ITRPEBAJAORGNINGTRN.TRPEBAJAORGNINGEVTY.PEORGNINGRINDVP createEjecutarITRPEBAJAORGNINGTRNTRPEBAJAORGNINGEVTYPEORGNINGRINDVP() {
        return new Ejecutar.ITRPEBAJAORGNINGTRN.TRPEBAJAORGNINGEVTY.PEORGNINGRINDVP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_BAJA_ORGN_ING_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
