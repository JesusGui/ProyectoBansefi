
package mx.babel.bansefi.banksystem.personas.webservices.bajaotronombrepersona;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.personas.webservices.bajaotronombrepersona package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_BAJA_PE_OTRO_NOMB_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.personas.webservices.bajaotronombrepersona
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
     * Create an instance of {@link ResponseBansefi.OTRPEBAJAPEOTRONOMB }
     * 
     */
    public ResponseBansefi.OTRPEBAJAPEOTRONOMB createResponseBansefiOTRPEBAJAPEOTRONOMB() {
        return new ResponseBansefi.OTRPEBAJAPEOTRONOMB();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEBAJAPEOTRONOMB }
     * 
     */
    public Ejecutar.ITRPEBAJAPEOTRONOMB createEjecutarITRPEBAJAPEOTRONOMB() {
        return new Ejecutar.ITRPEBAJAPEOTRONOMB();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEBAJAPEOTRONOMB.TRPEBAJAPEOTRONOMBE }
     * 
     */
    public Ejecutar.ITRPEBAJAPEOTRONOMB.TRPEBAJAPEOTRONOMBE createEjecutarITRPEBAJAPEOTRONOMBTRPEBAJAPEOTRONOMBE() {
        return new Ejecutar.ITRPEBAJAPEOTRONOMB.TRPEBAJAPEOTRONOMBE();
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
     * Create an instance of {@link ResponseBansefi.OTRPEBAJAPEOTRONOMB.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRPEBAJAPEOTRONOMB.STDTRNMSJPARMV createResponseBansefiOTRPEBAJAPEOTRONOMBSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRPEBAJAPEOTRONOMB.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRPEBAJAPEOTRONOMB.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRPEBAJAPEOTRONOMB.STDTRNOPARMV createResponseBansefiOTRPEBAJAPEOTRONOMBSTDTRNOPARMV() {
        return new ResponseBansefi.OTRPEBAJAPEOTRONOMB.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEBAJAPEOTRONOMB.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRPEBAJAPEOTRONOMB.STDTRNIPARMV createEjecutarITRPEBAJAPEOTRONOMBSTDTRNIPARMV() {
        return new Ejecutar.ITRPEBAJAPEOTRONOMB.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRPEBAJAPEOTRONOMB.TRPEBAJAPEOTRONOMBE.PEOTRONOMBP }
     * 
     */
    public Ejecutar.ITRPEBAJAPEOTRONOMB.TRPEBAJAPEOTRONOMBE.PEOTRONOMBP createEjecutarITRPEBAJAPEOTRONOMBTRPEBAJAPEOTRONOMBEPEOTRONOMBP() {
        return new Ejecutar.ITRPEBAJAPEOTRONOMB.TRPEBAJAPEOTRONOMBE.PEOTRONOMBP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_PE_BAJA_PE_OTRO_NOMB_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
