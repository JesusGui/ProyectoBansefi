
package mx.babel.bansefi.banksystem.oficina.webservices.consultadetallediarioelectronico;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.oficina.webservices.consultadetallediarioelectronico package. 
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

    private final static QName _EjecutarResult_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "CONDIA02", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.oficina.webservices.consultadetallediarioelectronico
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
     * Create an instance of {@link ResponseBansefi.OCONDIA02O }
     * 
     */
    public ResponseBansefi.OCONDIA02O createResponseBansefiOCONDIA02O() {
        return new ResponseBansefi.OCONDIA02O();
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
     * Create an instance of {@link Ejecutar.ICONDIA02I }
     * 
     */
    public Ejecutar.ICONDIA02I createEjecutarICONDIA02I() {
        return new Ejecutar.ICONDIA02I();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OCONDIA02O.CAMPOENTRADA }
     * 
     */
    public ResponseBansefi.OCONDIA02O.CAMPOENTRADA createResponseBansefiOCONDIA02OCAMPOENTRADA() {
        return new ResponseBansefi.OCONDIA02O.CAMPOENTRADA();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OCONDIA02O.CAMPOSSALIDA }
     * 
     */
    public ResponseBansefi.OCONDIA02O.CAMPOSSALIDA createResponseBansefiOCONDIA02OCAMPOSSALIDA() {
        return new ResponseBansefi.OCONDIA02O.CAMPOSSALIDA();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "CONDIA02", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(_EjecutarResult_QNAME, EjecutarResult.class, null, value);
    }

}
