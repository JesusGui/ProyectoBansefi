
package mx.babel.bansefi.banksystem.cuentas.webservices.consultalistadominiocondiciones;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.cuentas.webservices.consultalistadominiocondiciones package. 
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

    private final static QName EJECUTARRESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_LISTA_DOM_PK_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.cuentas.webservices.consultalistadominiocondiciones
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
     * Create an instance of {@link ResponseBansefi.OTRLISTADOMPKTRNO }
     * 
     */
    public ResponseBansefi.OTRLISTADOMPKTRNO createResponseBansefiOTRLISTADOMPKTRNO() {
        return new ResponseBansefi.OTRLISTADOMPKTRNO();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRLISTADOMPKTRNO.TRLISTADOMPKEVTZ }
     * 
     */
    public ResponseBansefi.OTRLISTADOMPKTRNO.TRLISTADOMPKEVTZ createResponseBansefiOTRLISTADOMPKTRNOTRLISTADOMPKEVTZ() {
        return new ResponseBansefi.OTRLISTADOMPKTRNO.TRLISTADOMPKEVTZ();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRLISTADOMPKTRNI }
     * 
     */
    public Ejecutar.ITRLISTADOMPKTRNI createEjecutarITRLISTADOMPKTRNI() {
        return new Ejecutar.ITRLISTADOMPKTRNI();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRLISTADOMPKTRNI.TRLISTADOMPKEVTY }
     * 
     */
    public Ejecutar.ITRLISTADOMPKTRNI.TRLISTADOMPKEVTY createEjecutarITRLISTADOMPKTRNITRLISTADOMPKEVTY() {
        return new Ejecutar.ITRLISTADOMPKTRNI.TRLISTADOMPKEVTY();
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
     * Create an instance of {@link ResponseBansefi.OTRLISTADOMPKTRNO.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRLISTADOMPKTRNO.STDTRNMSJPARMV createResponseBansefiOTRLISTADOMPKTRNOSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRLISTADOMPKTRNO.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRLISTADOMPKTRNO.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRLISTADOMPKTRNO.STDTRNOPARMV createResponseBansefiOTRLISTADOMPKTRNOSTDTRNOPARMV() {
        return new ResponseBansefi.OTRLISTADOMPKTRNO.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRLISTADOMPKTRNO.TRLISTADOMPKEVTZ.PKDOMINIOV }
     * 
     */
    public ResponseBansefi.OTRLISTADOMPKTRNO.TRLISTADOMPKEVTZ.PKDOMINIOV createResponseBansefiOTRLISTADOMPKTRNOTRLISTADOMPKEVTZPKDOMINIOV() {
        return new ResponseBansefi.OTRLISTADOMPKTRNO.TRLISTADOMPKEVTZ.PKDOMINIOV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRLISTADOMPKTRNI.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRLISTADOMPKTRNI.STDTRNIPARMV createEjecutarITRLISTADOMPKTRNISTDTRNIPARMV() {
        return new Ejecutar.ITRLISTADOMPKTRNI.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRLISTADOMPKTRNI.TRLISTADOMPKEVTY.PKDOMPARMCDP }
     * 
     */
    public Ejecutar.ITRLISTADOMPKTRNI.TRLISTADOMPKEVTY.PKDOMPARMCDP createEjecutarITRLISTADOMPKTRNITRLISTADOMPKEVTYPKDOMPARMCDP() {
        return new Ejecutar.ITRLISTADOMPKTRNI.TRLISTADOMPKEVTY.PKDOMPARMCDP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_LISTA_DOM_PK_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTARRESULT_QNAME, EjecutarResult.class, null, value);
    }

}
