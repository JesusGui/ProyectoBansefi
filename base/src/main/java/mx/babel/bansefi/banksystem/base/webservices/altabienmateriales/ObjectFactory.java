
package mx.babel.bansefi.banksystem.base.webservices.altabienmateriales;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bansefinss.webservicesnss.tr_alta_bien_materiales_trn package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_ALTA_BIEN_MATERIALES_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bansefinss.webservicesnss.tr_alta_bien_materiales_trn
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
     * Create an instance of {@link ResponseBansefi.OTRALTABIENMATERIALES }
     * 
     */
    public ResponseBansefi.OTRALTABIENMATERIALES createResponseBansefiOTRALTABIENMATERIALES() {
        return new ResponseBansefi.OTRALTABIENMATERIALES();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRALTABIENMATERIALES.TRALTABIENMATERIALESE }
     * 
     */
    public ResponseBansefi.OTRALTABIENMATERIALES.TRALTABIENMATERIALESE createResponseBansefiOTRALTABIENMATERIALESTRALTABIENMATERIALESE() {
        return new ResponseBansefi.OTRALTABIENMATERIALES.TRALTABIENMATERIALESE();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRALTABIENMATERIALES }
     * 
     */
    public Ejecutar.ITRALTABIENMATERIALES createEjecutarITRALTABIENMATERIALES() {
        return new Ejecutar.ITRALTABIENMATERIALES();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRALTABIENMATERIALES.TRALTABIENMATERIALESE }
     * 
     */
    public Ejecutar.ITRALTABIENMATERIALES.TRALTABIENMATERIALESE createEjecutarITRALTABIENMATERIALESTRALTABIENMATERIALESE() {
        return new Ejecutar.ITRALTABIENMATERIALES.TRALTABIENMATERIALESE();
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
     * Create an instance of {@link ResponseBansefi.OTRALTABIENMATERIALES.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRALTABIENMATERIALES.STDTRNMSJPARMV createResponseBansefiOTRALTABIENMATERIALESSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRALTABIENMATERIALES.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRALTABIENMATERIALES.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRALTABIENMATERIALES.STDTRNOPARMV createResponseBansefiOTRALTABIENMATERIALESSTDTRNOPARMV() {
        return new ResponseBansefi.OTRALTABIENMATERIALES.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRALTABIENMATERIALES.TRALTABIENMATERIALESE.BICRCTBIENP }
     * 
     */
    public ResponseBansefi.OTRALTABIENMATERIALES.TRALTABIENMATERIALESE.BICRCTBIENP createResponseBansefiOTRALTABIENMATERIALESTRALTABIENMATERIALESEBICRCTBIENP() {
        return new ResponseBansefi.OTRALTABIENMATERIALES.TRALTABIENMATERIALESE.BICRCTBIENP();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRALTABIENMATERIALES.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRALTABIENMATERIALES.STDTRNIPARMV createEjecutarITRALTABIENMATERIALESSTDTRNIPARMV() {
        return new Ejecutar.ITRALTABIENMATERIALES.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRALTABIENMATERIALES.TRALTABIENMATERIALESE.DATOSCARACTV }
     * 
     */
    public Ejecutar.ITRALTABIENMATERIALES.TRALTABIENMATERIALESE.DATOSCARACTV createEjecutarITRALTABIENMATERIALESTRALTABIENMATERIALESEDATOSCARACTV() {
        return new Ejecutar.ITRALTABIENMATERIALES.TRALTABIENMATERIALESE.DATOSCARACTV();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_ALTA_BIEN_MATERIALES_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
