
package mx.babel.bansefi.banksystem.base.webservices.bajabieningresogasto;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.base.webservices.bajabieningresogasto package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_BAJA_BIEN_ING_GAS_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.base.webservices.bajabieningresogasto
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
     * Create an instance of {@link ResponseBansefi.OTRBAJABIENINGGASTRN }
     * 
     */
    public ResponseBansefi.OTRBAJABIENINGGASTRN createResponseBansefiOTRBAJABIENINGGASTRN() {
        return new ResponseBansefi.OTRBAJABIENINGGASTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRBAJABIENINGGASTRN }
     * 
     */
    public Ejecutar.ITRBAJABIENINGGASTRN createEjecutarITRBAJABIENINGGASTRN() {
        return new Ejecutar.ITRBAJABIENINGGASTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRBAJABIENINGGASTRN.TRBAJABIENINGGASEVT }
     * 
     */
    public Ejecutar.ITRBAJABIENINGGASTRN.TRBAJABIENINGGASEVT createEjecutarITRBAJABIENINGGASTRNTRBAJABIENINGGASEVT() {
        return new Ejecutar.ITRBAJABIENINGGASTRN.TRBAJABIENINGGASEVT();
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
     * Create an instance of {@link ResponseBansefi.OTRBAJABIENINGGASTRN.TRBAJABIENINGGASEVT }
     * 
     */
    public ResponseBansefi.OTRBAJABIENINGGASTRN.TRBAJABIENINGGASEVT createResponseBansefiOTRBAJABIENINGGASTRNTRBAJABIENINGGASEVT() {
        return new ResponseBansefi.OTRBAJABIENINGGASTRN.TRBAJABIENINGGASEVT();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRBAJABIENINGGASTRN.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRBAJABIENINGGASTRN.STDTRNMSJPARMV createResponseBansefiOTRBAJABIENINGGASTRNSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRBAJABIENINGGASTRN.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRBAJABIENINGGASTRN.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRBAJABIENINGGASTRN.STDTRNOPARMV createResponseBansefiOTRBAJABIENINGGASTRNSTDTRNOPARMV() {
        return new ResponseBansefi.OTRBAJABIENINGGASTRN.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRBAJABIENINGGASTRN.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRBAJABIENINGGASTRN.STDTRNIPARMV createEjecutarITRBAJABIENINGGASTRNSTDTRNIPARMV() {
        return new Ejecutar.ITRBAJABIENINGGASTRN.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRBAJABIENINGGASTRN.TRBAJABIENINGGASEVT.PEPERSRLBIENP }
     * 
     */
    public Ejecutar.ITRBAJABIENINGGASTRN.TRBAJABIENINGGASEVT.PEPERSRLBIENP createEjecutarITRBAJABIENINGGASTRNTRBAJABIENINGGASEVTPEPERSRLBIENP() {
        return new Ejecutar.ITRBAJABIENINGGASTRN.TRBAJABIENINGGASEVT.PEPERSRLBIENP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_BAJA_BIEN_ING_GAS_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
