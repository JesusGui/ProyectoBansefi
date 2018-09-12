
package mx.babel.bansefi.banksystem.personas.webservices.bajaotraidentificacionpersona;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bansefinss.webservicesnss.tr_pe_baja_pe_id_ext_trn package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_BAJA_PE_ID_EXT_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bansefinss.webservicesnss.tr_pe_baja_pe_id_ext_trn
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
     * Create an instance of {@link ResponseBansefi.OTRPEBAJAPEIDEXTTRN }
     * 
     */
    public ResponseBansefi.OTRPEBAJAPEIDEXTTRN createResponseBansefiOTRPEBAJAPEIDEXTTRN() {
        return new ResponseBansefi.OTRPEBAJAPEIDEXTTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEBAJAPEIDEXTTRN }
     * 
     */
    public Ejecutar.ITRPEBAJAPEIDEXTTRN createEjecutarITRPEBAJAPEIDEXTTRN() {
        return new Ejecutar.ITRPEBAJAPEIDEXTTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEBAJAPEIDEXTTRN.TRPEBAJAPEIDEXTEVT }
     * 
     */
    public Ejecutar.ITRPEBAJAPEIDEXTTRN.TRPEBAJAPEIDEXTEVT createEjecutarITRPEBAJAPEIDEXTTRNTRPEBAJAPEIDEXTEVT() {
        return new Ejecutar.ITRPEBAJAPEIDEXTTRN.TRPEBAJAPEIDEXTEVT();
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
     * Create an instance of {@link ResponseBansefi.OTRPEBAJAPEIDEXTTRN.TRPEBAJAPEIDEXTEVT }
     * 
     */
    public ResponseBansefi.OTRPEBAJAPEIDEXTTRN.TRPEBAJAPEIDEXTEVT createResponseBansefiOTRPEBAJAPEIDEXTTRNTRPEBAJAPEIDEXTEVT() {
        return new ResponseBansefi.OTRPEBAJAPEIDEXTTRN.TRPEBAJAPEIDEXTEVT();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPEBAJAPEIDEXTTRN.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRPEBAJAPEIDEXTTRN.STDTRNMSJPARMV createResponseBansefiOTRPEBAJAPEIDEXTTRNSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRPEBAJAPEIDEXTTRN.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPEBAJAPEIDEXTTRN.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRPEBAJAPEIDEXTTRN.STDTRNOPARMV createResponseBansefiOTRPEBAJAPEIDEXTTRNSTDTRNOPARMV() {
        return new ResponseBansefi.OTRPEBAJAPEIDEXTTRN.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEBAJAPEIDEXTTRN.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRPEBAJAPEIDEXTTRN.STDTRNIPARMV createEjecutarITRPEBAJAPEIDEXTTRNSTDTRNIPARMV() {
        return new Ejecutar.ITRPEBAJAPEIDEXTTRN.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEBAJAPEIDEXTTRN.TRPEBAJAPEIDEXTEVT.PEIDEXTP }
     * 
     */
    public Ejecutar.ITRPEBAJAPEIDEXTTRN.TRPEBAJAPEIDEXTEVT.PEIDEXTP createEjecutarITRPEBAJAPEIDEXTTRNTRPEBAJAPEIDEXTEVTPEIDEXTP() {
        return new Ejecutar.ITRPEBAJAPEIDEXTTRN.TRPEBAJAPEIDEXTEVT.PEIDEXTP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_BAJA_PE_ID_EXT_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
