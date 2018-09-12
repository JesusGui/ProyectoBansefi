
package mx.babel.bansefi.banksystem.cuentas.webservices.anuladocumento;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.cuentas.webservices.anuladocumento package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_DE_CANCELAR_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.cuentas.webservices.anuladocumento
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
     * Create an instance of {@link ResponseBansefi.OTRDECANCELARTRNO }
     * 
     */
    public ResponseBansefi.OTRDECANCELARTRNO createResponseBansefiOTRDECANCELARTRNO() {
        return new ResponseBansefi.OTRDECANCELARTRNO();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRDECANCELARTRNO.TRDECANCELAREVTZ }
     * 
     */
    public ResponseBansefi.OTRDECANCELARTRNO.TRDECANCELAREVTZ createResponseBansefiOTRDECANCELARTRNOTRDECANCELAREVTZ() {
        return new ResponseBansefi.OTRDECANCELARTRNO.TRDECANCELAREVTZ();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRDECANCELARTRNI }
     * 
     */
    public Ejecutar.ITRDECANCELARTRNI createEjecutarITRDECANCELARTRNI() {
        return new Ejecutar.ITRDECANCELARTRNI();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRDECANCELARTRNI.TRDECANCELAREVTY }
     * 
     */
    public Ejecutar.ITRDECANCELARTRNI.TRDECANCELAREVTY createEjecutarITRDECANCELARTRNITRDECANCELAREVTY() {
        return new Ejecutar.ITRDECANCELARTRNI.TRDECANCELAREVTY();
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
     * Create an instance of {@link ResponseBansefi.OTRDECANCELARTRNO.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRDECANCELARTRNO.STDTRNMSJPARMV createResponseBansefiOTRDECANCELARTRNOSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRDECANCELARTRNO.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRDECANCELARTRNO.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRDECANCELARTRNO.STDTRNOPARMV createResponseBansefiOTRDECANCELARTRNOSTDTRNOPARMV() {
        return new ResponseBansefi.OTRDECANCELARTRNO.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRDECANCELARTRNO.TRDECANCELAREVTZ.DEDOCEMTDOE }
     * 
     */
    public ResponseBansefi.OTRDECANCELARTRNO.TRDECANCELAREVTZ.DEDOCEMTDOE createResponseBansefiOTRDECANCELARTRNOTRDECANCELAREVTZDEDOCEMTDOE() {
        return new ResponseBansefi.OTRDECANCELARTRNO.TRDECANCELAREVTZ.DEDOCEMTDOE();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRDECANCELARTRNI.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRDECANCELARTRNI.STDTRNIPARMV createEjecutarITRDECANCELARTRNISTDTRNIPARMV() {
        return new Ejecutar.ITRDECANCELARTRNI.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRDECANCELARTRNI.TRDECANCELAREVTY.DEDOCEMTDOE }
     * 
     */
    public Ejecutar.ITRDECANCELARTRNI.TRDECANCELAREVTY.DEDOCEMTDOE createEjecutarITRDECANCELARTRNITRDECANCELAREVTYDEDOCEMTDOE() {
        return new Ejecutar.ITRDECANCELARTRNI.TRDECANCELAREVTY.DEDOCEMTDOE();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRDECANCELARTRNI.TRDECANCELAREVTY.NUMACRLDEV }
     * 
     */
    public Ejecutar.ITRDECANCELARTRNI.TRDECANCELAREVTY.NUMACRLDEV createEjecutarITRDECANCELARTRNITRDECANCELAREVTYNUMACRLDEV() {
        return new Ejecutar.ITRDECANCELARTRNI.TRDECANCELAREVTY.NUMACRLDEV();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_DE_CANCELAR_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
