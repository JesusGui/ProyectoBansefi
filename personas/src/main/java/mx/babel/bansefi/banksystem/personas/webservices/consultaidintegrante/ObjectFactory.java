
package mx.babel.bansefi.banksystem.personas.webservices.consultaidintegrante;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.personas.webservices.consultaidintegrante package. 
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

    private final static QName _EjecutarResult_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_CONS_INTGRNT_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.personas.webservices.consultaidintegrante
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
     * Create an instance of {@link ResponseBansefi.OPECONSINTGRNTTRNO }
     * 
     */
    public ResponseBansefi.OPECONSINTGRNTTRNO createResponseBansefiOPECONSINTGRNTTRNO() {
        return new ResponseBansefi.OPECONSINTGRNTTRNO();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OPECONSINTGRNTTRNO.PECONSINTGRNTEVTZ }
     * 
     */
    public ResponseBansefi.OPECONSINTGRNTTRNO.PECONSINTGRNTEVTZ createResponseBansefiOPECONSINTGRNTTRNOPECONSINTGRNTEVTZ() {
        return new ResponseBansefi.OPECONSINTGRNTTRNO.PECONSINTGRNTEVTZ();
    }

    /**
     * Create an instance of {@link Ejecutar.IPECONSINTGRNTTRNI }
     * 
     */
    public Ejecutar.IPECONSINTGRNTTRNI createEjecutarIPECONSINTGRNTTRNI() {
        return new Ejecutar.IPECONSINTGRNTTRNI();
    }

    /**
     * Create an instance of {@link Ejecutar.IPECONSINTGRNTTRNI.PECONSINTGRNTEVTY }
     * 
     */
    public Ejecutar.IPECONSINTGRNTTRNI.PECONSINTGRNTEVTY createEjecutarIPECONSINTGRNTTRNIPECONSINTGRNTEVTY() {
        return new Ejecutar.IPECONSINTGRNTTRNI.PECONSINTGRNTEVTY();
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
     * Create an instance of {@link ResponseBansefi.OPECONSINTGRNTTRNO.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OPECONSINTGRNTTRNO.STDTRNMSJPARMV createResponseBansefiOPECONSINTGRNTTRNOSTDTRNMSJPARMV() {
        return new ResponseBansefi.OPECONSINTGRNTTRNO.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OPECONSINTGRNTTRNO.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OPECONSINTGRNTTRNO.STDTRNOPARMV createResponseBansefiOPECONSINTGRNTTRNOSTDTRNOPARMV() {
        return new ResponseBansefi.OPECONSINTGRNTTRNO.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OPECONSINTGRNTTRNO.PECONSINTGRNTEVTZ.PEDICDTAPCE }
     * 
     */
    public ResponseBansefi.OPECONSINTGRNTTRNO.PECONSINTGRNTEVTZ.PEDICDTAPCE createResponseBansefiOPECONSINTGRNTTRNOPECONSINTGRNTEVTZPEDICDTAPCE() {
        return new ResponseBansefi.OPECONSINTGRNTTRNO.PECONSINTGRNTEVTZ.PEDICDTAPCE();
    }

    /**
     * Create an instance of {@link Ejecutar.IPECONSINTGRNTTRNI.STDTRNIPARMV }
     * 
     */
    public Ejecutar.IPECONSINTGRNTTRNI.STDTRNIPARMV createEjecutarIPECONSINTGRNTTRNISTDTRNIPARMV() {
        return new Ejecutar.IPECONSINTGRNTTRNI.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.IPECONSINTGRNTTRNI.PECONSINTGRNTEVTY.PEDICDTAPCP }
     * 
     */
    public Ejecutar.IPECONSINTGRNTTRNI.PECONSINTGRNTEVTY.PEDICDTAPCP createEjecutarIPECONSINTGRNTTRNIPECONSINTGRNTEVTYPEDICDTAPCP() {
        return new Ejecutar.IPECONSINTGRNTTRNI.PECONSINTGRNTEVTY.PEDICDTAPCP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_CONS_INTGRNT_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(_EjecutarResult_QNAME, EjecutarResult.class, null, value);
    }

}
