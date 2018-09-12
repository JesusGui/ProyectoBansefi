
package mx.babel.bansefi.banksystem.personas.webservices.modificaotronombrepersona;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bansefinss.webservicesnss.tr_pe_modif_pe_otro_nomb_trn package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_MODIF_PE_OTRO_NOMB_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bansefinss.webservicesnss.tr_pe_modif_pe_otro_nomb_trn
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
     * Create an instance of {@link ResponseBansefi.OTRPEMODIFPEOTRONOMB }
     * 
     */
    public ResponseBansefi.OTRPEMODIFPEOTRONOMB createResponseBansefiOTRPEMODIFPEOTRONOMB() {
        return new ResponseBansefi.OTRPEMODIFPEOTRONOMB();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPEMODIFPEOTRONOMB.TRPEMODIFPEOTRONOMB }
     * 
     */
    public ResponseBansefi.OTRPEMODIFPEOTRONOMB.TRPEMODIFPEOTRONOMB createResponseBansefiOTRPEMODIFPEOTRONOMBTRPEMODIFPEOTRONOMB() {
        return new ResponseBansefi.OTRPEMODIFPEOTRONOMB.TRPEMODIFPEOTRONOMB();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEMODIFPEOTRONOMB }
     * 
     */
    public Ejecutar.ITRPEMODIFPEOTRONOMB createEjecutarITRPEMODIFPEOTRONOMB() {
        return new Ejecutar.ITRPEMODIFPEOTRONOMB();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEMODIFPEOTRONOMB.TRPEMODIFPEOTRONOMB }
     * 
     */
    public Ejecutar.ITRPEMODIFPEOTRONOMB.TRPEMODIFPEOTRONOMB createEjecutarITRPEMODIFPEOTRONOMBTRPEMODIFPEOTRONOMB() {
        return new Ejecutar.ITRPEMODIFPEOTRONOMB.TRPEMODIFPEOTRONOMB();
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
     * Create an instance of {@link ResponseBansefi.OTRPEMODIFPEOTRONOMB.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRPEMODIFPEOTRONOMB.STDTRNMSJPARMV createResponseBansefiOTRPEMODIFPEOTRONOMBSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRPEMODIFPEOTRONOMB.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPEMODIFPEOTRONOMB.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRPEMODIFPEOTRONOMB.STDTRNOPARMV createResponseBansefiOTRPEMODIFPEOTRONOMBSTDTRNOPARMV() {
        return new ResponseBansefi.OTRPEMODIFPEOTRONOMB.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPEMODIFPEOTRONOMB.TRPEMODIFPEOTRONOMB.PEOTRONOMBP }
     * 
     */
    public ResponseBansefi.OTRPEMODIFPEOTRONOMB.TRPEMODIFPEOTRONOMB.PEOTRONOMBP createResponseBansefiOTRPEMODIFPEOTRONOMBTRPEMODIFPEOTRONOMBPEOTRONOMBP() {
        return new ResponseBansefi.OTRPEMODIFPEOTRONOMB.TRPEMODIFPEOTRONOMB.PEOTRONOMBP();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEMODIFPEOTRONOMB.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRPEMODIFPEOTRONOMB.STDTRNIPARMV createEjecutarITRPEMODIFPEOTRONOMBSTDTRNIPARMV() {
        return new Ejecutar.ITRPEMODIFPEOTRONOMB.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEMODIFPEOTRONOMB.TRPEMODIFPEOTRONOMB.PEOTRONOMBP }
     * 
     */
    public Ejecutar.ITRPEMODIFPEOTRONOMB.TRPEMODIFPEOTRONOMB.PEOTRONOMBP createEjecutarITRPEMODIFPEOTRONOMBTRPEMODIFPEOTRONOMBPEOTRONOMBP() {
        return new Ejecutar.ITRPEMODIFPEOTRONOMB.TRPEMODIFPEOTRONOMB.PEOTRONOMBP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_MODIF_PE_OTRO_NOMB_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
