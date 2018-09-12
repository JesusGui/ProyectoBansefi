
package mx.babel.bansefi.banksystem.base.webservices.modificacionanotaciones;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.base.webservices.modificacionanotaciones package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_AN_MODI_DATOS_DP_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.base.webservices.modificacionanotaciones
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
     * Create an instance of {@link ResponseBansefi.OTRANMODIDATOSDPTRN }
     * 
     */
    public ResponseBansefi.OTRANMODIDATOSDPTRN createResponseBansefiOTRANMODIDATOSDPTRN() {
        return new ResponseBansefi.OTRANMODIDATOSDPTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRANMODIDATOSDPTRN }
     * 
     */
    public Ejecutar.ITRANMODIDATOSDPTRN createEjecutarITRANMODIDATOSDPTRN() {
        return new Ejecutar.ITRANMODIDATOSDPTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRANMODIDATOSDPTRN.TRANMODIDATOSDPEVTY }
     * 
     */
    public Ejecutar.ITRANMODIDATOSDPTRN.TRANMODIDATOSDPEVTY createEjecutarITRANMODIDATOSDPTRNTRANMODIDATOSDPEVTY() {
        return new Ejecutar.ITRANMODIDATOSDPTRN.TRANMODIDATOSDPEVTY();
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
     * Create an instance of {@link ResponseBansefi.OTRANMODIDATOSDPTRN.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRANMODIDATOSDPTRN.STDTRNMSJPARMV createResponseBansefiOTRANMODIDATOSDPTRNSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRANMODIDATOSDPTRN.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRANMODIDATOSDPTRN.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRANMODIDATOSDPTRN.STDTRNOPARMV createResponseBansefiOTRANMODIDATOSDPTRNSTDTRNOPARMV() {
        return new ResponseBansefi.OTRANMODIDATOSDPTRN.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRANMODIDATOSDPTRN.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRANMODIDATOSDPTRN.STDTRNIPARMV createEjecutarITRANMODIDATOSDPTRNSTDTRNIPARMV() {
        return new Ejecutar.ITRANMODIDATOSDPTRN.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRANMODIDATOSDPTRN.TRANMODIDATOSDPEVTY.ANANTCNP }
     * 
     */
    public Ejecutar.ITRANMODIDATOSDPTRN.TRANMODIDATOSDPEVTY.ANANTCNP createEjecutarITRANMODIDATOSDPTRNTRANMODIDATOSDPEVTYANANTCNP() {
        return new Ejecutar.ITRANMODIDATOSDPTRN.TRANMODIDATOSDPEVTY.ANANTCNP();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRANMODIDATOSDPTRN.TRANMODIDATOSDPEVTY.ANORIGENV }
     * 
     */
    public Ejecutar.ITRANMODIDATOSDPTRN.TRANMODIDATOSDPEVTY.ANORIGENV createEjecutarITRANMODIDATOSDPTRNTRANMODIDATOSDPEVTYANORIGENV() {
        return new Ejecutar.ITRANMODIDATOSDPTRN.TRANMODIDATOSDPEVTY.ANORIGENV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRANMODIDATOSDPTRN.TRANMODIDATOSDPEVTY.ANEMPLDESTINOV }
     * 
     */
    public Ejecutar.ITRANMODIDATOSDPTRN.TRANMODIDATOSDPEVTY.ANEMPLDESTINOV createEjecutarITRANMODIDATOSDPTRNTRANMODIDATOSDPEVTYANEMPLDESTINOV() {
        return new Ejecutar.ITRANMODIDATOSDPTRN.TRANMODIDATOSDPEVTY.ANEMPLDESTINOV();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_AN_MODI_DATOS_DP_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
