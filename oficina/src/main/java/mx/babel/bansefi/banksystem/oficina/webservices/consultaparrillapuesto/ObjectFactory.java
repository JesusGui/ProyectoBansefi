
package mx.babel.bansefi.banksystem.oficina.webservices.consultaparrillapuesto;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.oficina.webservices.consultaparillapuesto package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CUADRE_OBTE_DATOS_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.oficina.webservices.consultaparillapuesto
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
     * Create an instance of {@link ResponseBansefi.OTRCUADREOBTEDATOSTRN }
     * 
     */
    public ResponseBansefi.OTRCUADREOBTEDATOSTRN createResponseBansefiOTRCUADREOBTEDATOSTRN() {
        return new ResponseBansefi.OTRCUADREOBTEDATOSTRN();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCUADREOBTEDATOSTRN.CUADRELP }
     * 
     */
    public ResponseBansefi.OTRCUADREOBTEDATOSTRN.CUADRELP createResponseBansefiOTRCUADREOBTEDATOSTRNCUADRELP() {
        return new ResponseBansefi.OTRCUADREOBTEDATOSTRN.CUADRELP();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCUADREOBTEDATOSTRN.CUADRELP.TNCUADRELP }
     * 
     */
    public ResponseBansefi.OTRCUADREOBTEDATOSTRN.CUADRELP.TNCUADRELP createResponseBansefiOTRCUADREOBTEDATOSTRNCUADRELPTNCUADRELP() {
        return new ResponseBansefi.OTRCUADREOBTEDATOSTRN.CUADRELP.TNCUADRELP();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCUADREOBTEDATOSTRN }
     * 
     */
    public Ejecutar.ITRCUADREOBTEDATOSTRN createEjecutarITRCUADREOBTEDATOSTRN() {
        return new Ejecutar.ITRCUADREOBTEDATOSTRN();
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
     * Create an instance of {@link ResponseBansefi.OTRCUADREOBTEDATOSTRN.STDMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRCUADREOBTEDATOSTRN.STDMSJPARMV createResponseBansefiOTRCUADREOBTEDATOSTRNSTDMSJPARMV() {
        return new ResponseBansefi.OTRCUADREOBTEDATOSTRN.STDMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCUADREOBTEDATOSTRN.CUADRELP.SUMA }
     * 
     */
    public ResponseBansefi.OTRCUADREOBTEDATOSTRN.CUADRELP.SUMA createResponseBansefiOTRCUADREOBTEDATOSTRNCUADRELPSUMA() {
        return new ResponseBansefi.OTRCUADREOBTEDATOSTRN.CUADRELP.SUMA();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCUADREOBTEDATOSTRN.CUADRELP.TNCUADRELP.UDS }
     * 
     */
    public ResponseBansefi.OTRCUADREOBTEDATOSTRN.CUADRELP.TNCUADRELP.UDS createResponseBansefiOTRCUADREOBTEDATOSTRNCUADRELPTNCUADRELPUDS() {
        return new ResponseBansefi.OTRCUADREOBTEDATOSTRN.CUADRELP.TNCUADRELP.UDS();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCUADREOBTEDATOSTRN.CUADRELP.TNCUADRELP.TOTAL }
     * 
     */
    public ResponseBansefi.OTRCUADREOBTEDATOSTRN.CUADRELP.TNCUADRELP.TOTAL createResponseBansefiOTRCUADREOBTEDATOSTRNCUADRELPTNCUADRELPTOTAL() {
        return new ResponseBansefi.OTRCUADREOBTEDATOSTRN.CUADRELP.TNCUADRELP.TOTAL();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCUADREOBTEDATOSTRN.TNCUADRECBV }
     * 
     */
    public Ejecutar.ITRCUADREOBTEDATOSTRN.TNCUADRECBV createEjecutarITRCUADREOBTEDATOSTRNTNCUADRECBV() {
        return new Ejecutar.ITRCUADREOBTEDATOSTRN.TNCUADRECBV();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CUADRE_OBTE_DATOS_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
