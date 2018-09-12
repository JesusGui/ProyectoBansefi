
package mx.babel.bansefi.banksystem.cuentas.webservices.modificacampodocumento;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.cuentas.webservices.modificacampodocumento package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_DE_MODI_DATOS_ENT_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.cuentas.webservices.modificacampodocumento
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
     * Create an instance of {@link ResponseBansefi.OTRDEMODIDATOSENTTRN }
     * 
     */
    public ResponseBansefi.OTRDEMODIDATOSENTTRN createResponseBansefiOTRDEMODIDATOSENTTRN() {
        return new ResponseBansefi.OTRDEMODIDATOSENTTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRDEMODIDATOSENTTRN }
     * 
     */
    public Ejecutar.ITRDEMODIDATOSENTTRN createEjecutarITRDEMODIDATOSENTTRN() {
        return new Ejecutar.ITRDEMODIDATOSENTTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRDEMODIDATOSENTTRN.TRDEMODIDATOSENTEVT }
     * 
     */
    public Ejecutar.ITRDEMODIDATOSENTTRN.TRDEMODIDATOSENTEVT createEjecutarITRDEMODIDATOSENTTRNTRDEMODIDATOSENTEVT() {
        return new Ejecutar.ITRDEMODIDATOSENTTRN.TRDEMODIDATOSENTEVT();
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
     * Create an instance of {@link ResponseBansefi.OTRDEMODIDATOSENTTRN.TRDEMODIDATOSENTEVT }
     * 
     */
    public ResponseBansefi.OTRDEMODIDATOSENTTRN.TRDEMODIDATOSENTEVT createResponseBansefiOTRDEMODIDATOSENTTRNTRDEMODIDATOSENTEVT() {
        return new ResponseBansefi.OTRDEMODIDATOSENTTRN.TRDEMODIDATOSENTEVT();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRDEMODIDATOSENTTRN.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRDEMODIDATOSENTTRN.STDTRNMSJPARMV createResponseBansefiOTRDEMODIDATOSENTTRNSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRDEMODIDATOSENTTRN.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRDEMODIDATOSENTTRN.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRDEMODIDATOSENTTRN.STDTRNOPARMV createResponseBansefiOTRDEMODIDATOSENTTRNSTDTRNOPARMV() {
        return new ResponseBansefi.OTRDEMODIDATOSENTTRN.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRDEMODIDATOSENTTRN.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRDEMODIDATOSENTTRN.STDTRNIPARMV createEjecutarITRDEMODIDATOSENTTRNSTDTRNIPARMV() {
        return new Ejecutar.ITRDEMODIDATOSENTTRN.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRDEMODIDATOSENTTRN.TRDEMODIDATOSENTEVT.DERLAPP }
     * 
     */
    public Ejecutar.ITRDEMODIDATOSENTTRN.TRDEMODIDATOSENTEVT.DERLAPP createEjecutarITRDEMODIDATOSENTTRNTRDEMODIDATOSENTEVTDERLAPP() {
        return new Ejecutar.ITRDEMODIDATOSENTTRN.TRDEMODIDATOSENTEVT.DERLAPP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_DE_MODI_DATOS_ENT_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
