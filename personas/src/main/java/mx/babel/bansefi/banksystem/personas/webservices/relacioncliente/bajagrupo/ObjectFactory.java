
package mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.bajagrupo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.bajagrupo package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_GR_BAJA_RL_PE_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.bajagrupo
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
     * Create an instance of {@link ResponseBansefi.OTRGRBAJARLPETRNO }
     * 
     */
    public ResponseBansefi.OTRGRBAJARLPETRNO createResponseBansefiOTRGRBAJARLPETRNO() {
        return new ResponseBansefi.OTRGRBAJARLPETRNO();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRGRBAJARLPETRNO.TRGRBAJARLPEEVTZ }
     * 
     */
    public ResponseBansefi.OTRGRBAJARLPETRNO.TRGRBAJARLPEEVTZ createResponseBansefiOTRGRBAJARLPETRNOTRGRBAJARLPEEVTZ() {
        return new ResponseBansefi.OTRGRBAJARLPETRNO.TRGRBAJARLPEEVTZ();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRGRBAJARLPETRNI }
     * 
     */
    public Ejecutar.ITRGRBAJARLPETRNI createEjecutarITRGRBAJARLPETRNI() {
        return new Ejecutar.ITRGRBAJARLPETRNI();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRGRBAJARLPETRNI.TRGRBAJARLPEEVTY }
     * 
     */
    public Ejecutar.ITRGRBAJARLPETRNI.TRGRBAJARLPEEVTY createEjecutarITRGRBAJARLPETRNITRGRBAJARLPEEVTY() {
        return new Ejecutar.ITRGRBAJARLPETRNI.TRGRBAJARLPEEVTY();
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
     * Create an instance of {@link ResponseBansefi.OTRGRBAJARLPETRNO.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRGRBAJARLPETRNO.STDTRNMSJPARMV createResponseBansefiOTRGRBAJARLPETRNOSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRGRBAJARLPETRNO.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRGRBAJARLPETRNO.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRGRBAJARLPETRNO.STDTRNOPARMV createResponseBansefiOTRGRBAJARLPETRNOSTDTRNOPARMV() {
        return new ResponseBansefi.OTRGRBAJARLPETRNO.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRGRBAJARLPETRNO.TRGRBAJARLPEEVTZ.INDPERTNGRPPEV }
     * 
     */
    public ResponseBansefi.OTRGRBAJARLPETRNO.TRGRBAJARLPEEVTZ.INDPERTNGRPPEV createResponseBansefiOTRGRBAJARLPETRNOTRGRBAJARLPEEVTZINDPERTNGRPPEV() {
        return new ResponseBansefi.OTRGRBAJARLPETRNO.TRGRBAJARLPEEVTZ.INDPERTNGRPPEV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRGRBAJARLPETRNI.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRGRBAJARLPETRNI.STDTRNIPARMV createEjecutarITRGRBAJARLPETRNISTDTRNIPARMV() {
        return new Ejecutar.ITRGRBAJARLPETRNI.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRGRBAJARLPETRNI.TRGRBAJARLPEEVTY.GRGRPRLPERSP }
     * 
     */
    public Ejecutar.ITRGRBAJARLPETRNI.TRGRBAJARLPEEVTY.GRGRPRLPERSP createEjecutarITRGRBAJARLPETRNITRGRBAJARLPEEVTYGRGRPRLPERSP() {
        return new Ejecutar.ITRGRBAJARLPETRNI.TRGRBAJARLPEEVTY.GRGRPRLPERSP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_GR_BAJA_RL_PE_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
