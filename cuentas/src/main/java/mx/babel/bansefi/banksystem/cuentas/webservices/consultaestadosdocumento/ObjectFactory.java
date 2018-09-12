
package mx.babel.bansefi.banksystem.cuentas.webservices.consultaestadosdocumento;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.cuentas.webservices.consultaestadosdocumento package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_DE_CONS_ECVS_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.cuentas.webservices.consultaestadosdocumento
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
     * Create an instance of {@link ResponseBansefi.OTRDECONSECVSTRNO }
     * 
     */
    public ResponseBansefi.OTRDECONSECVSTRNO createResponseBansefiOTRDECONSECVSTRNO() {
        return new ResponseBansefi.OTRDECONSECVSTRNO();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRDECONSECVSTRNI }
     * 
     */
    public Ejecutar.ITRDECONSECVSTRNI createEjecutarITRDECONSECVSTRNI() {
        return new Ejecutar.ITRDECONSECVSTRNI();
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
     * Create an instance of {@link ResponseBansefi.OTRDECONSECVSTRNO.TRDECONSECVSEVTZ }
     * 
     */
    public ResponseBansefi.OTRDECONSECVSTRNO.TRDECONSECVSEVTZ createResponseBansefiOTRDECONSECVSTRNOTRDECONSECVSEVTZ() {
        return new ResponseBansefi.OTRDECONSECVSTRNO.TRDECONSECVSEVTZ();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRDECONSECVSTRNO.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRDECONSECVSTRNO.STDTRNMSJPARMV createResponseBansefiOTRDECONSECVSTRNOSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRDECONSECVSTRNO.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRDECONSECVSTRNO.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRDECONSECVSTRNO.STDTRNOPARMV createResponseBansefiOTRDECONSECVSTRNOSTDTRNOPARMV() {
        return new ResponseBansefi.OTRDECONSECVSTRNO.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRDECONSECVSTRNI.TRDECONSECVSEVTY }
     * 
     */
    public Ejecutar.ITRDECONSECVSTRNI.TRDECONSECVSEVTY createEjecutarITRDECONSECVSTRNITRDECONSECVSEVTY() {
        return new Ejecutar.ITRDECONSECVSTRNI.TRDECONSECVSEVTY();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRDECONSECVSTRNI.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRDECONSECVSTRNI.STDTRNIPARMV createEjecutarITRDECONSECVSTRNISTDTRNIPARMV() {
        return new Ejecutar.ITRDECONSECVSTRNI.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_DE_CONS_ECVS_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
