
package mx.babel.bansefi.banksystem.cuentas.webservices.consultachequebancario;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.cuentas.webservices.consultachequebancario package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CONSULTA_CHEQUE_2_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.cuentas.webservices.consultachequebancario
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
     * Create an instance of {@link ResponseBansefi.OTRCONSULTACHEQUE2TRN }
     * 
     */
    public ResponseBansefi.OTRCONSULTACHEQUE2TRN createResponseBansefiOTRCONSULTACHEQUE2TRN() {
        return new ResponseBansefi.OTRCONSULTACHEQUE2TRN();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCONSULTACHEQUE2TRN.TRCONSULTACHEQUE2EVT }
     * 
     */
    public ResponseBansefi.OTRCONSULTACHEQUE2TRN.TRCONSULTACHEQUE2EVT createResponseBansefiOTRCONSULTACHEQUE2TRNTRCONSULTACHEQUE2EVT() {
        return new ResponseBansefi.OTRCONSULTACHEQUE2TRN.TRCONSULTACHEQUE2EVT();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSULTACHEQUE2TRN }
     * 
     */
    public Ejecutar.ITRCONSULTACHEQUE2TRN createEjecutarITRCONSULTACHEQUE2TRN() {
        return new Ejecutar.ITRCONSULTACHEQUE2TRN();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSULTACHEQUE2TRN.TRCONSULTACHEQUEEVTY }
     * 
     */
    public Ejecutar.ITRCONSULTACHEQUE2TRN.TRCONSULTACHEQUEEVTY createEjecutarITRCONSULTACHEQUE2TRNTRCONSULTACHEQUEEVTY() {
        return new Ejecutar.ITRCONSULTACHEQUE2TRN.TRCONSULTACHEQUEEVTY();
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
     * Create an instance of {@link ResponseBansefi.OTRCONSULTACHEQUE2TRN.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRCONSULTACHEQUE2TRN.STDTRNMSJPARMV createResponseBansefiOTRCONSULTACHEQUE2TRNSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRCONSULTACHEQUE2TRN.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCONSULTACHEQUE2TRN.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRCONSULTACHEQUE2TRN.STDTRNOPARMV createResponseBansefiOTRCONSULTACHEQUE2TRNSTDTRNOPARMV() {
        return new ResponseBansefi.OTRCONSULTACHEQUE2TRN.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCONSULTACHEQUE2TRN.TRCONSULTACHEQUE2EVT.RMREMP }
     * 
     */
    public ResponseBansefi.OTRCONSULTACHEQUE2TRN.TRCONSULTACHEQUE2EVT.RMREMP createResponseBansefiOTRCONSULTACHEQUE2TRNTRCONSULTACHEQUE2EVTRMREMP() {
        return new ResponseBansefi.OTRCONSULTACHEQUE2TRN.TRCONSULTACHEQUE2EVT.RMREMP();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSULTACHEQUE2TRN.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRCONSULTACHEQUE2TRN.STDTRNIPARMV createEjecutarITRCONSULTACHEQUE2TRNSTDTRNIPARMV() {
        return new Ejecutar.ITRCONSULTACHEQUE2TRN.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSULTACHEQUE2TRN.TRCONSULTACHEQUEEVTY.CJCHQPAGARECOMPP }
     * 
     */
    public Ejecutar.ITRCONSULTACHEQUE2TRN.TRCONSULTACHEQUEEVTY.CJCHQPAGARECOMPP createEjecutarITRCONSULTACHEQUE2TRNTRCONSULTACHEQUEEVTYCJCHQPAGARECOMPP() {
        return new Ejecutar.ITRCONSULTACHEQUE2TRN.TRCONSULTACHEQUEEVTY.CJCHQPAGARECOMPP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CONSULTA_CHEQUE_2_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}