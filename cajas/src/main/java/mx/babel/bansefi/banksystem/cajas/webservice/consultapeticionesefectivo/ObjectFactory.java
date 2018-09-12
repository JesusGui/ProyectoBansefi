
package mx.babel.bansefi.banksystem.cajas.webservice.consultapeticionesefectivo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.cajas.webservice.consultapeticionesefectivo package. 
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

    private final static QName _EjecutarResult_QNAME = new QName("http://BansefiNSS/WebServicesNSS/TR_AUT_INIC_PETCN_SM_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.cajas.webservice.consultapeticionesefectivo
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
     * Create an instance of {@link ResponseBansefi.OTRAUTINICPETCNSMTRN }
     * 
     */
    public ResponseBansefi.OTRAUTINICPETCNSMTRN createResponseBansefiOTRAUTINICPETCNSMTRN() {
        return new ResponseBansefi.OTRAUTINICPETCNSMTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRAUTINICPETCNSMTRN }
     * 
     */
    public Ejecutar.ITRAUTINICPETCNSMTRN createEjecutarITRAUTINICPETCNSMTRN() {
        return new Ejecutar.ITRAUTINICPETCNSMTRN();
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
     * Create an instance of {@link ResponseBansefi.OTRAUTINICPETCNSMTRN.SMSOLCTDESGLSE }
     * 
     */
    public ResponseBansefi.OTRAUTINICPETCNSMTRN.SMSOLCTDESGLSE createResponseBansefiOTRAUTINICPETCNSMTRNSMSOLCTDESGLSE() {
        return new ResponseBansefi.OTRAUTINICPETCNSMTRN.SMSOLCTDESGLSE();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRAUTINICPETCNSMTRN.EXEXISTDESGLSE }
     * 
     */
    public ResponseBansefi.OTRAUTINICPETCNSMTRN.EXEXISTDESGLSE createResponseBansefiOTRAUTINICPETCNSMTRNEXEXISTDESGLSE() {
        return new ResponseBansefi.OTRAUTINICPETCNSMTRN.EXEXISTDESGLSE();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRAUTINICPETCNSMTRN.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRAUTINICPETCNSMTRN.STDTRNMSJPARMV createResponseBansefiOTRAUTINICPETCNSMTRNSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRAUTINICPETCNSMTRN.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRAUTINICPETCNSMTRN.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRAUTINICPETCNSMTRN.STDTRNOPARMV createResponseBansefiOTRAUTINICPETCNSMTRNSTDTRNOPARMV() {
        return new ResponseBansefi.OTRAUTINICPETCNSMTRN.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRAUTINICPETCNSMTRN.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRAUTINICPETCNSMTRN.STDTRNIPARMV createEjecutarITRAUTINICPETCNSMTRNSTDTRNIPARMV() {
        return new Ejecutar.ITRAUTINICPETCNSMTRN.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRAUTINICPETCNSMTRN.TRAUTINICPETCNSMEVT }
     * 
     */
    public Ejecutar.ITRAUTINICPETCNSMTRN.TRAUTINICPETCNSMEVT createEjecutarITRAUTINICPETCNSMTRNTRAUTINICPETCNSMEVT() {
        return new Ejecutar.ITRAUTINICPETCNSMTRN.TRAUTINICPETCNSMEVT();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://BansefiNSS/WebServicesNSS/TR_AUT_INIC_PETCN_SM_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(_EjecutarResult_QNAME, EjecutarResult.class, null, value);
    }

}
