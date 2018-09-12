
package mx.babel.bansefi.banksystem.personas.webservices.consultaotrosnombrespersona;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bansefinss.webservicesnss.tr_pe_cons_pe_otro_nomb_trn package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_CONS_PE_OTRO_NOMB_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bansefinss.webservicesnss.tr_pe_cons_pe_otro_nomb_trn
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
     * Create an instance of {@link ResponseBansefi.OTRPECONSPEOTRONOMB }
     * 
     */
    public ResponseBansefi.OTRPECONSPEOTRONOMB createResponseBansefiOTRPECONSPEOTRONOMB() {
        return new ResponseBansefi.OTRPECONSPEOTRONOMB();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPECONSPEOTRONOMB.TRPECONSPEOTRONOMBE }
     * 
     */
    public ResponseBansefi.OTRPECONSPEOTRONOMB.TRPECONSPEOTRONOMBE1 createResponseBansefiOTRPECONSPEOTRONOMBTRPECONSPEOTRONOMBE() {
        return new ResponseBansefi.OTRPECONSPEOTRONOMB.TRPECONSPEOTRONOMBE1();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPECONSPEOTRONOMB }
     * 
     */
    public Ejecutar.ITRPECONSPEOTRONOMB createEjecutarITRPECONSPEOTRONOMB() {
        return new Ejecutar.ITRPECONSPEOTRONOMB();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPECONSPEOTRONOMB.TRPECONSPEOTRONOMBE }
     * 
     */
    public Ejecutar.ITRPECONSPEOTRONOMB.TRPECONSPEOTRONOMBE createEjecutarITRPECONSPEOTRONOMBTRPECONSPEOTRONOMBE() {
        return new Ejecutar.ITRPECONSPEOTRONOMB.TRPECONSPEOTRONOMBE();
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
     * Create an instance of {@link ResponseBansefi.OTRPECONSPEOTRONOMB.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRPECONSPEOTRONOMB.STDTRNMSJPARMV createResponseBansefiOTRPECONSPEOTRONOMBSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRPECONSPEOTRONOMB.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPECONSPEOTRONOMB.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRPECONSPEOTRONOMB.STDTRNOPARMV createResponseBansefiOTRPECONSPEOTRONOMBSTDTRNOPARMV() {
        return new ResponseBansefi.OTRPECONSPEOTRONOMB.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPECONSPEOTRONOMB.TRPECONSPEOTRONOMBE.TRPECONSPEOTRONOMBE }
     * 
     */
    public ResponseBansefi.OTRPECONSPEOTRONOMB.TRPECONSPEOTRONOMBE1.TRPECONSPEOTRONOMBE createResponseBansefiOTRPECONSPEOTRONOMBTRPECONSPEOTRONOMBETRPECONSPEOTRONOMBE() {
        return new ResponseBansefi.OTRPECONSPEOTRONOMB.TRPECONSPEOTRONOMBE1.TRPECONSPEOTRONOMBE();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPECONSPEOTRONOMB.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRPECONSPEOTRONOMB.STDTRNIPARMV createEjecutarITRPECONSPEOTRONOMBSTDTRNIPARMV() {
        return new Ejecutar.ITRPECONSPEOTRONOMB.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPECONSPEOTRONOMB.TRPECONSPEOTRONOMBE.PEOTRONOMBP }
     * 
     */
    public Ejecutar.ITRPECONSPEOTRONOMB.TRPECONSPEOTRONOMBE.PEOTRONOMBP createEjecutarITRPECONSPEOTRONOMBTRPECONSPEOTRONOMBEPEOTRONOMBP() {
        return new Ejecutar.ITRPECONSPEOTRONOMB.TRPECONSPEOTRONOMBE.PEOTRONOMBP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_CONS_PE_OTRO_NOMB_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
