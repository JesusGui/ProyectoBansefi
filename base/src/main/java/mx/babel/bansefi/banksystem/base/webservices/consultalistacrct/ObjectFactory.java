
package mx.babel.bansefi.banksystem.base.webservices.consultalistacrct;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bansefinss.webservicesnss.tr_crct_lst_trn package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CRCT_LST_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bansefinss.webservicesnss.tr_crct_lst_trn
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
     * Create an instance of {@link ResponseBansefi.OTRCRCTLSTTRNO }
     * 
     */
    public ResponseBansefi.OTRCRCTLSTTRNO createResponseBansefiOTRCRCTLSTTRNO() {
        return new ResponseBansefi.OTRCRCTLSTTRNO();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCRCTLSTTRNO.TRCRCTLSTEVTZ }
     * 
     */
    public ResponseBansefi.OTRCRCTLSTTRNO.TRCRCTLSTEVTZ createResponseBansefiOTRCRCTLSTTRNOTRCRCTLSTEVTZ() {
        return new ResponseBansefi.OTRCRCTLSTTRNO.TRCRCTLSTEVTZ();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCRCTLSTTRNI }
     * 
     */
    public Ejecutar.ITRCRCTLSTTRNI createEjecutarITRCRCTLSTTRNI() {
        return new Ejecutar.ITRCRCTLSTTRNI();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCRCTLSTTRNI.TRCRCTLSTEVTY }
     * 
     */
    public Ejecutar.ITRCRCTLSTTRNI.TRCRCTLSTEVTY createEjecutarITRCRCTLSTTRNITRCRCTLSTEVTY() {
        return new Ejecutar.ITRCRCTLSTTRNI.TRCRCTLSTEVTY();
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
     * Create an instance of {@link ResponseBansefi.OTRCRCTLSTTRNO.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRCRCTLSTTRNO.STDTRNMSJPARMV createResponseBansefiOTRCRCTLSTTRNOSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRCRCTLSTTRNO.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCRCTLSTTRNO.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRCRCTLSTTRNO.STDTRNOPARMV createResponseBansefiOTRCRCTLSTTRNOSTDTRNOPARMV() {
        return new ResponseBansefi.OTRCRCTLSTTRNO.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCRCTLSTTRNO.TRCRCTLSTEVTZ.TRCRCTLSTEVTV }
     * 
     */
    public ResponseBansefi.OTRCRCTLSTTRNO.TRCRCTLSTEVTZ.TRCRCTLSTEVTV createResponseBansefiOTRCRCTLSTTRNOTRCRCTLSTEVTZTRCRCTLSTEVTV() {
        return new ResponseBansefi.OTRCRCTLSTTRNO.TRCRCTLSTEVTZ.TRCRCTLSTEVTV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCRCTLSTTRNI.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRCRCTLSTTRNI.STDTRNIPARMV createEjecutarITRCRCTLSTTRNISTDTRNIPARMV() {
        return new Ejecutar.ITRCRCTLSTTRNI.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCRCTLSTTRNI.TRCRCTLSTEVTY.BICRCTBIENP }
     * 
     */
    public Ejecutar.ITRCRCTLSTTRNI.TRCRCTLSTEVTY.BICRCTBIENP createEjecutarITRCRCTLSTTRNITRCRCTLSTEVTYBICRCTBIENP() {
        return new Ejecutar.ITRCRCTLSTTRNI.TRCRCTLSTEVTY.BICRCTBIENP();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCRCTLSTTRNI.TRCRCTLSTEVTY.BIVALORBIENP }
     * 
     */
    public Ejecutar.ITRCRCTLSTTRNI.TRCRCTLSTEVTY.BIVALORBIENP createEjecutarITRCRCTLSTTRNITRCRCTLSTEVTYBIVALORBIENP() {
        return new Ejecutar.ITRCRCTLSTTRNI.TRCRCTLSTEVTY.BIVALORBIENP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CRCT_LST_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
