
package mx.babel.bansefi.banksystem.personas.webservices.modificaotraidentificacionpersona;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bansefinss.webservicesnss.tr_pe_modif_pe_id_ext_trn package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_MODIF_PE_ID_EXT_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bansefinss.webservicesnss.tr_pe_modif_pe_id_ext_trn
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
     * Create an instance of {@link ResponseBansefi.OTRPEMODIFPEIDEXTTR }
     * 
     */
    public ResponseBansefi.OTRPEMODIFPEIDEXTTR createResponseBansefiOTRPEMODIFPEIDEXTTR() {
        return new ResponseBansefi.OTRPEMODIFPEIDEXTTR();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEMODIFPEIDEXTTR }
     * 
     */
    public Ejecutar.ITRPEMODIFPEIDEXTTR createEjecutarITRPEMODIFPEIDEXTTR() {
        return new Ejecutar.ITRPEMODIFPEIDEXTTR();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEMODIFPEIDEXTTR.TRPEMODIFPEIDEXTEVT }
     * 
     */
    public Ejecutar.ITRPEMODIFPEIDEXTTR.TRPEMODIFPEIDEXTEVT createEjecutarITRPEMODIFPEIDEXTTRTRPEMODIFPEIDEXTEVT() {
        return new Ejecutar.ITRPEMODIFPEIDEXTTR.TRPEMODIFPEIDEXTEVT();
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
     * Create an instance of {@link ResponseBansefi.OTRPEMODIFPEIDEXTTR.TRPEMODIFPEIDEXTEVT }
     * 
     */
    public ResponseBansefi.OTRPEMODIFPEIDEXTTR.TRPEMODIFPEIDEXTEVT createResponseBansefiOTRPEMODIFPEIDEXTTRTRPEMODIFPEIDEXTEVT() {
        return new ResponseBansefi.OTRPEMODIFPEIDEXTTR.TRPEMODIFPEIDEXTEVT();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPEMODIFPEIDEXTTR.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRPEMODIFPEIDEXTTR.STDTRNMSJPARMV createResponseBansefiOTRPEMODIFPEIDEXTTRSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRPEMODIFPEIDEXTTR.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPEMODIFPEIDEXTTR.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRPEMODIFPEIDEXTTR.STDTRNOPARMV createResponseBansefiOTRPEMODIFPEIDEXTTRSTDTRNOPARMV() {
        return new ResponseBansefi.OTRPEMODIFPEIDEXTTR.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEMODIFPEIDEXTTR.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRPEMODIFPEIDEXTTR.STDTRNIPARMV createEjecutarITRPEMODIFPEIDEXTTRSTDTRNIPARMV() {
        return new Ejecutar.ITRPEMODIFPEIDEXTTR.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEMODIFPEIDEXTTR.TRPEMODIFPEIDEXTEVT.PEIDEXTP }
     * 
     */
    public Ejecutar.ITRPEMODIFPEIDEXTTR.TRPEMODIFPEIDEXTEVT.PEIDEXTP createEjecutarITRPEMODIFPEIDEXTTRTRPEMODIFPEIDEXTEVTPEIDEXTP() {
        return new Ejecutar.ITRPEMODIFPEIDEXTTR.TRPEMODIFPEIDEXTEVT.PEIDEXTP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_MODIF_PE_ID_EXT_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
