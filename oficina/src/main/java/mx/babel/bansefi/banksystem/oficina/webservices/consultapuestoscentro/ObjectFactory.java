
package mx.babel.bansefi.banksystem.oficina.webservices.consultapuestoscentro;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.oficina.webservices.consultapuestoscentro package. 
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

    private final static QName _EjecutarResult_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CON_LISTA_TERM_CENT_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.oficina.webservices.consultapuestoscentro
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
     * Create an instance of {@link ResponseBansefi.OTRCONLISTATERMCENTT }
     * 
     */
    public ResponseBansefi.OTRCONLISTATERMCENTT createResponseBansefiOTRCONLISTATERMCENTT() {
        return new ResponseBansefi.OTRCONLISTATERMCENTT();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCONLISTATERMCENTT.TRCONLISTATERMCENTEVLIST }
     * 
     */
    public ResponseBansefi.OTRCONLISTATERMCENTT.TRCONLISTATERMCENTEVLIST createResponseBansefiOTRCONLISTATERMCENTTTRCONLISTATERMCENTEV() {
        return new ResponseBansefi.OTRCONLISTATERMCENTT.TRCONLISTATERMCENTEVLIST();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONLISTATERMCENTT }
     * 
     */
    public Ejecutar.ITRCONLISTATERMCENTT createEjecutarITRCONLISTATERMCENTT() {
        return new Ejecutar.ITRCONLISTATERMCENTT();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONLISTATERMCENTT.TRCONLISTATERMCENTEV }
     * 
     */
    public Ejecutar.ITRCONLISTATERMCENTT.TRCONLISTATERMCENTEV createEjecutarITRCONLISTATERMCENTTTRCONLISTATERMCENTEV() {
        return new Ejecutar.ITRCONLISTATERMCENTT.TRCONLISTATERMCENTEV();
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
     * Create an instance of {@link ResponseBansefi.OTRCONLISTATERMCENTT.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRCONLISTATERMCENTT.STDTRNMSJPARMV createResponseBansefiOTRCONLISTATERMCENTTSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRCONLISTATERMCENTT.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCONLISTATERMCENTT.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRCONLISTATERMCENTT.STDTRNOPARMV createResponseBansefiOTRCONLISTATERMCENTTSTDTRNOPARMV() {
        return new ResponseBansefi.OTRCONLISTATERMCENTT.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCONLISTATERMCENTT.TRCONLISTATERMCENTEVLIST.TRCONLISTATERMCENTEV }
     * 
     */
    public ResponseBansefi.OTRCONLISTATERMCENTT.TRCONLISTATERMCENTEVLIST.TRCONLISTATERMCENTEV createResponseBansefiOTRCONLISTATERMCENTTTRCONLISTATERMCENTEVTRCONLISTATERMCENTEV() {
        return new ResponseBansefi.OTRCONLISTATERMCENTT.TRCONLISTATERMCENTEVLIST.TRCONLISTATERMCENTEV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONLISTATERMCENTT.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRCONLISTATERMCENTT.STDTRNIPARMV createEjecutarITRCONLISTATERMCENTTSTDTRNIPARMV() {
        return new Ejecutar.ITRCONLISTATERMCENTT.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONLISTATERMCENTT.TRCONLISTATERMCENTEV.TNTERMINALI1 }
     * 
     */
    public Ejecutar.ITRCONLISTATERMCENTT.TRCONLISTATERMCENTEV.TNTERMINALI1 createEjecutarITRCONLISTATERMCENTTTRCONLISTATERMCENTEVTNTERMINALI1() {
        return new Ejecutar.ITRCONLISTATERMCENTT.TRCONLISTATERMCENTEV.TNTERMINALI1();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CON_LISTA_TERM_CENT_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(_EjecutarResult_QNAME, EjecutarResult.class, null, value);
    }

}
