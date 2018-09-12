
package mx.babel.bansefi.banksystem.base.webservices.consultaperfiltransaccionalmoralestim;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bansefinss.webservicesnss.pe_cons_tran_estim_trn package. 
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

    private final static QName _EjecutarResult_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_CONS_TRAN_ESTIM_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bansefinss.webservicesnss.pe_cons_tran_estim_trn
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
     * Create an instance of {@link ResponseBansefi.OPECONSTRANESTIMTRNO }
     * 
     */
    public ResponseBansefi.OPECONSTRANESTIMTRNO createResponseBansefiOPECONSTRANESTIMTRNO() {
        return new ResponseBansefi.OPECONSTRANESTIMTRNO();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OPECONSTRANESTIMTRNO.PECONSTRANESTIMEVTZ }
     * 
     */
    public ResponseBansefi.OPECONSTRANESTIMTRNO.PECONSTRANESTIMEVTZ createResponseBansefiOPECONSTRANESTIMTRNOPECONSTRANESTIMEVTZ() {
        return new ResponseBansefi.OPECONSTRANESTIMTRNO.PECONSTRANESTIMEVTZ();
    }

    /**
     * Create an instance of {@link Ejecutar.IPECONSTRANESTIMTRNI }
     * 
     */
    public Ejecutar.IPECONSTRANESTIMTRNI createEjecutarIPECONSTRANESTIMTRNI() {
        return new Ejecutar.IPECONSTRANESTIMTRNI();
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
     * Create an instance of {@link ResponseBansefi.OPECONSTRANESTIMTRNO.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OPECONSTRANESTIMTRNO.STDTRNMSJPARMV createResponseBansefiOPECONSTRANESTIMTRNOSTDTRNMSJPARMV() {
        return new ResponseBansefi.OPECONSTRANESTIMTRNO.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OPECONSTRANESTIMTRNO.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OPECONSTRANESTIMTRNO.STDTRNOPARMV createResponseBansefiOPECONSTRANESTIMTRNOSTDTRNOPARMV() {
        return new ResponseBansefi.OPECONSTRANESTIMTRNO.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OPECONSTRANESTIMTRNO.PECONSTRANESTIMEVTZ.PEPERSTRANESTIME }
     * 
     */
    public ResponseBansefi.OPECONSTRANESTIMTRNO.PECONSTRANESTIMEVTZ.PEPERSTRANESTIME createResponseBansefiOPECONSTRANESTIMTRNOPECONSTRANESTIMEVTZPEPERSTRANESTIME() {
        return new ResponseBansefi.OPECONSTRANESTIMTRNO.PECONSTRANESTIMEVTZ.PEPERSTRANESTIME();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OPECONSTRANESTIMTRNO.PECONSTRANESTIMEVTZ.PEPERSFNTEINGRE }
     * 
     */
    public ResponseBansefi.OPECONSTRANESTIMTRNO.PECONSTRANESTIMEVTZ.PEPERSFNTEINGRE createResponseBansefiOPECONSTRANESTIMTRNOPECONSTRANESTIMEVTZPEPERSFNTEINGRE() {
        return new ResponseBansefi.OPECONSTRANESTIMTRNO.PECONSTRANESTIMEVTZ.PEPERSFNTEINGRE();
    }

    /**
     * Create an instance of {@link Ejecutar.IPECONSTRANESTIMTRNI.PECONSTRANESTIMEVTY }
     * 
     */
    public Ejecutar.IPECONSTRANESTIMTRNI.PECONSTRANESTIMEVTY createEjecutarIPECONSTRANESTIMTRNIPECONSTRANESTIMEVTY() {
        return new Ejecutar.IPECONSTRANESTIMTRNI.PECONSTRANESTIMEVTY();
    }

    /**
     * Create an instance of {@link Ejecutar.IPECONSTRANESTIMTRNI.STDTRNIPARMV }
     * 
     */
    public Ejecutar.IPECONSTRANESTIMTRNI.STDTRNIPARMV createEjecutarIPECONSTRANESTIMTRNISTDTRNIPARMV() {
        return new Ejecutar.IPECONSTRANESTIMTRNI.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_CONS_TRAN_ESTIM_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(_EjecutarResult_QNAME, EjecutarResult.class, null, value);
    }

}
