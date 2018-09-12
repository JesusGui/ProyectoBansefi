
package mx.babel.bansefi.banksystem.base.webservices.modificabienmateriales;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bansefinss.webservicesnss.tr_modi_bien_materiales_trn package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_MODI_BIEN_MATERIALES_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bansefinss.webservicesnss.tr_modi_bien_materiales_trn
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
     * Create an instance of {@link ResponseBansefi.OTRMODIBIENMATERIALES }
     * 
     */
    public ResponseBansefi.OTRMODIBIENMATERIALES createResponseBansefiOTRMODIBIENMATERIALES() {
        return new ResponseBansefi.OTRMODIBIENMATERIALES();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRMODIBIENMATERIALES }
     * 
     */
    public Ejecutar.ITRMODIBIENMATERIALES createEjecutarITRMODIBIENMATERIALES() {
        return new Ejecutar.ITRMODIBIENMATERIALES();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRMODIBIENMATERIALES.TRMODIBIENMATERIALESE }
     * 
     */
    public Ejecutar.ITRMODIBIENMATERIALES.TRMODIBIENMATERIALESE createEjecutarITRMODIBIENMATERIALESTRMODIBIENMATERIALESE() {
        return new Ejecutar.ITRMODIBIENMATERIALES.TRMODIBIENMATERIALESE();
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
     * Create an instance of {@link ResponseBansefi.OTRMODIBIENMATERIALES.TRMODIBIENMATERIALESE }
     * 
     */
    public ResponseBansefi.OTRMODIBIENMATERIALES.TRMODIBIENMATERIALESE createResponseBansefiOTRMODIBIENMATERIALESTRMODIBIENMATERIALESE() {
        return new ResponseBansefi.OTRMODIBIENMATERIALES.TRMODIBIENMATERIALESE();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRMODIBIENMATERIALES.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRMODIBIENMATERIALES.STDTRNMSJPARMV createResponseBansefiOTRMODIBIENMATERIALESSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRMODIBIENMATERIALES.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRMODIBIENMATERIALES.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRMODIBIENMATERIALES.STDTRNOPARMV createResponseBansefiOTRMODIBIENMATERIALESSTDTRNOPARMV() {
        return new ResponseBansefi.OTRMODIBIENMATERIALES.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRMODIBIENMATERIALES.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRMODIBIENMATERIALES.STDTRNIPARMV createEjecutarITRMODIBIENMATERIALESSTDTRNIPARMV() {
        return new Ejecutar.ITRMODIBIENMATERIALES.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRMODIBIENMATERIALES.TRMODIBIENMATERIALESE.DATOSCARACTV }
     * 
     */
    public Ejecutar.ITRMODIBIENMATERIALES.TRMODIBIENMATERIALESE.DATOSCARACTV createEjecutarITRMODIBIENMATERIALESTRMODIBIENMATERIALESEDATOSCARACTV() {
        return new Ejecutar.ITRMODIBIENMATERIALES.TRMODIBIENMATERIALESE.DATOSCARACTV();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_MODI_BIEN_MATERIALES_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
