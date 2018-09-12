
package mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.altagrupo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.altagrupo package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_GR_ALTA_RL_PE_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.altagrupo
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
     * Create an instance of {@link ResponseBansefi.OTRGRALTARLPETRNO }
     * 
     */
    public ResponseBansefi.OTRGRALTARLPETRNO createResponseBansefiOTRGRALTARLPETRNO() {
        return new ResponseBansefi.OTRGRALTARLPETRNO();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRGRALTARLPETRNO.TRGRALTARLPEEVTZ }
     * 
     */
    public ResponseBansefi.OTRGRALTARLPETRNO.TRGRALTARLPEEVTZ createResponseBansefiOTRGRALTARLPETRNOTRGRALTARLPEEVTZ() {
        return new ResponseBansefi.OTRGRALTARLPETRNO.TRGRALTARLPEEVTZ();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRGRALTARLPETRNI }
     * 
     */
    public Ejecutar.ITRGRALTARLPETRNI createEjecutarITRGRALTARLPETRNI() {
        return new Ejecutar.ITRGRALTARLPETRNI();
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
     * Create an instance of {@link ResponseBansefi.OTRGRALTARLPETRNO.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRGRALTARLPETRNO.STDTRNMSJPARMV createResponseBansefiOTRGRALTARLPETRNOSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRGRALTARLPETRNO.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRGRALTARLPETRNO.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRGRALTARLPETRNO.STDTRNOPARMV createResponseBansefiOTRGRALTARLPETRNOSTDTRNOPARMV() {
        return new ResponseBansefi.OTRGRALTARLPETRNO.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRGRALTARLPETRNO.TRGRALTARLPEEVTZ.GRGRPRLPERSP }
     * 
     */
    public ResponseBansefi.OTRGRALTARLPETRNO.TRGRALTARLPEEVTZ.GRGRPRLPERSP createResponseBansefiOTRGRALTARLPETRNOTRGRALTARLPEEVTZGRGRPRLPERSP() {
        return new ResponseBansefi.OTRGRALTARLPETRNO.TRGRALTARLPEEVTZ.GRGRPRLPERSP();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRGRALTARLPETRNO.TRGRALTARLPEEVTZ.INDPERTNGRPPEV }
     * 
     */
    public ResponseBansefi.OTRGRALTARLPETRNO.TRGRALTARLPEEVTZ.INDPERTNGRPPEV createResponseBansefiOTRGRALTARLPETRNOTRGRALTARLPEEVTZINDPERTNGRPPEV() {
        return new ResponseBansefi.OTRGRALTARLPETRNO.TRGRALTARLPEEVTZ.INDPERTNGRPPEV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRGRALTARLPETRNI.TRGRALTARLPEEVTY }
     * 
     */
    public Ejecutar.ITRGRALTARLPETRNI.TRGRALTARLPEEVTY createEjecutarITRGRALTARLPETRNITRGRALTARLPEEVTY() {
        return new Ejecutar.ITRGRALTARLPETRNI.TRGRALTARLPEEVTY();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRGRALTARLPETRNI.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRGRALTARLPETRNI.STDTRNIPARMV createEjecutarITRGRALTARLPETRNISTDTRNIPARMV() {
        return new Ejecutar.ITRGRALTARLPETRNI.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_GR_ALTA_RL_PE_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
