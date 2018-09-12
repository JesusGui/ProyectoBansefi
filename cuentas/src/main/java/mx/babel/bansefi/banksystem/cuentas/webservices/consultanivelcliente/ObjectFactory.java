
package mx.babel.bansefi.banksystem.cuentas.webservices.consultanivelcliente;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.cuentas.webservices.consultanivelcliente package. 
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

    private final static QName EJECUTARRESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_BUSCA_NIVEL_PER_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.cuentas.webservices.consultanivelcliente
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
     * Create an instance of {@link ResponseBansefi.OTRBUSCANIVELPERTRNO }
     * 
     */
    public ResponseBansefi.OTRBUSCANIVELPERTRNO createResponseBansefiOTRBUSCANIVELPERTRNO() {
        return new ResponseBansefi.OTRBUSCANIVELPERTRNO();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRBUSCANIVELPERTRNI }
     * 
     */
    public Ejecutar.ITRBUSCANIVELPERTRNI createEjecutarITRBUSCANIVELPERTRNI() {
        return new Ejecutar.ITRBUSCANIVELPERTRNI();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRBUSCANIVELPERTRNI.TRBUSCANIVELPERV }
     * 
     */
    public Ejecutar.ITRBUSCANIVELPERTRNI.TRBUSCANIVELPERV createEjecutarITRBUSCANIVELPERTRNITRBUSCANIVELPERV() {
        return new Ejecutar.ITRBUSCANIVELPERTRNI.TRBUSCANIVELPERV();
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
     * Create an instance of {@link ResponseBansefi.OTRBUSCANIVELPERTRNO.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRBUSCANIVELPERTRNO.STDTRNMSJPARMV createResponseBansefiOTRBUSCANIVELPERTRNOSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRBUSCANIVELPERTRNO.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRBUSCANIVELPERTRNO.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRBUSCANIVELPERTRNO.STDTRNOPARMV createResponseBansefiOTRBUSCANIVELPERTRNOSTDTRNOPARMV() {
        return new ResponseBansefi.OTRBUSCANIVELPERTRNO.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRBUSCANIVELPERTRNI.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRBUSCANIVELPERTRNI.STDTRNIPARMV createEjecutarITRBUSCANIVELPERTRNISTDTRNIPARMV() {
        return new Ejecutar.ITRBUSCANIVELPERTRNI.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRBUSCANIVELPERTRNI.TRBUSCANIVELPERV.PEPERSP }
     * 
     */
    public Ejecutar.ITRBUSCANIVELPERTRNI.TRBUSCANIVELPERV.PEPERSP createEjecutarITRBUSCANIVELPERTRNITRBUSCANIVELPERVPEPERSP() {
        return new Ejecutar.ITRBUSCANIVELPERTRNI.TRBUSCANIVELPERV.PEPERSP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_BUSCA_NIVEL_PER_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTARRESULT_QNAME, EjecutarResult.class, null, value);
    }

}
