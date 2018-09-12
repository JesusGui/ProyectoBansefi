
package mx.babel.bansefi.banksystem.personas.webservices.consultaotrasidentificacionespersona;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bansefinss.webservicesnss.tr_pe_cons_pe_id_ext_trn package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_CONS_PE_ID_EXT_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bansefinss.webservicesnss.tr_pe_cons_pe_id_ext_trn
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
     * Create an instance of {@link ResponseBansefi.OTRPECONSPEIDEXTTRN }
     * 
     */
    public ResponseBansefi.OTRPECONSPEIDEXTTRN createResponseBansefiOTRPECONSPEIDEXTTRN() {
        return new ResponseBansefi.OTRPECONSPEIDEXTTRN();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPECONSPEIDEXTTRN.TRPECONSPEIDEXTEVT }
     * 
     */
    public ResponseBansefi.OTRPECONSPEIDEXTTRN.TRPECONSPEIDEXTEVT1 createResponseBansefiOTRPECONSPEIDEXTTRNTRPECONSPEIDEXTEVT() {
        return new ResponseBansefi.OTRPECONSPEIDEXTTRN.TRPECONSPEIDEXTEVT1();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPECONSPEIDEXTTRN }
     * 
     */
    public Ejecutar.ITRPECONSPEIDEXTTRN createEjecutarITRPECONSPEIDEXTTRN() {
        return new Ejecutar.ITRPECONSPEIDEXTTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPECONSPEIDEXTTRN.TRPECONSPEIDEXTEVT }
     * 
     */
    public Ejecutar.ITRPECONSPEIDEXTTRN.TRPECONSPEIDEXTEVT createEjecutarITRPECONSPEIDEXTTRNTRPECONSPEIDEXTEVT() {
        return new Ejecutar.ITRPECONSPEIDEXTTRN.TRPECONSPEIDEXTEVT();
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
     * Create an instance of {@link ResponseBansefi.OTRPECONSPEIDEXTTRN.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRPECONSPEIDEXTTRN.STDTRNMSJPARMV createResponseBansefiOTRPECONSPEIDEXTTRNSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRPECONSPEIDEXTTRN.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPECONSPEIDEXTTRN.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRPECONSPEIDEXTTRN.STDTRNOPARMV createResponseBansefiOTRPECONSPEIDEXTTRNSTDTRNOPARMV() {
        return new ResponseBansefi.OTRPECONSPEIDEXTTRN.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPECONSPEIDEXTTRN.TRPECONSPEIDEXTEVT.TRPECONSPEIDEXTEVT }
     * 
     */
    public ResponseBansefi.OTRPECONSPEIDEXTTRN.TRPECONSPEIDEXTEVT1.TRPECONSPEIDEXTEVT createResponseBansefiOTRPECONSPEIDEXTTRNTRPECONSPEIDEXTEVTTRPECONSPEIDEXTEVT() {
        return new ResponseBansefi.OTRPECONSPEIDEXTTRN.TRPECONSPEIDEXTEVT1.TRPECONSPEIDEXTEVT();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPECONSPEIDEXTTRN.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRPECONSPEIDEXTTRN.STDTRNIPARMV createEjecutarITRPECONSPEIDEXTTRNSTDTRNIPARMV() {
        return new Ejecutar.ITRPECONSPEIDEXTTRN.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPECONSPEIDEXTTRN.TRPECONSPEIDEXTEVT.PEIDEXTP }
     * 
     */
    public Ejecutar.ITRPECONSPEIDEXTTRN.TRPECONSPEIDEXTEVT.PEIDEXTP createEjecutarITRPECONSPEIDEXTTRNTRPECONSPEIDEXTEVTPEIDEXTP() {
        return new Ejecutar.ITRPECONSPEIDEXTTRN.TRPECONSPEIDEXTEVT.PEIDEXTP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_CONS_PE_ID_EXT_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
