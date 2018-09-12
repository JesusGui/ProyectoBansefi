
package mx.babel.bansefi.banksystem.administracion.webservices.altacentrocontrolador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bansefinss.webservicesnss.tr_alta_cent_ctrl_trn package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_ALTA_CENT_CTRL_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bansefinss.webservicesnss.tr_alta_cent_ctrl_trn
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
     * Create an instance of {@link ResponseBansefi.OTRALTACENTCTRLTRNO }
     * 
     */
    public ResponseBansefi.OTRALTACENTCTRLTRNO createResponseBansefiOTRALTACENTCTRLTRNO() {
        return new ResponseBansefi.OTRALTACENTCTRLTRNO();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRALTACENTCTRLTRNO.TRALTACENTCTRLEVTZ }
     * 
     */
    public ResponseBansefi.OTRALTACENTCTRLTRNO.TRALTACENTCTRLEVTZ createResponseBansefiOTRALTACENTCTRLTRNOTRALTACENTCTRLEVTZ() {
        return new ResponseBansefi.OTRALTACENTCTRLTRNO.TRALTACENTCTRLEVTZ();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRALTACENTCTRLTRNI }
     * 
     */
    public Ejecutar.ITRALTACENTCTRLTRNI createEjecutarITRALTACENTCTRLTRNI() {
        return new Ejecutar.ITRALTACENTCTRLTRNI();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRALTACENTCTRLTRNI.TRALTACENTCTRLEVTY }
     * 
     */
    public Ejecutar.ITRALTACENTCTRLTRNI.TRALTACENTCTRLEVTY createEjecutarITRALTACENTCTRLTRNITRALTACENTCTRLEVTY() {
        return new Ejecutar.ITRALTACENTCTRLTRNI.TRALTACENTCTRLEVTY();
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
     * Create an instance of {@link ResponseBansefi.OTRALTACENTCTRLTRNO.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRALTACENTCTRLTRNO.STDTRNMSJPARMV createResponseBansefiOTRALTACENTCTRLTRNOSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRALTACENTCTRLTRNO.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRALTACENTCTRLTRNO.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRALTACENTCTRLTRNO.STDTRNOPARMV createResponseBansefiOTRALTACENTCTRLTRNOSTDTRNOPARMV() {
        return new ResponseBansefi.OTRALTACENTCTRLTRNO.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRALTACENTCTRLTRNO.TRALTACENTCTRLEVTZ.UOCENTCTRLP }
     * 
     */
    public ResponseBansefi.OTRALTACENTCTRLTRNO.TRALTACENTCTRLEVTZ.UOCENTCTRLP createResponseBansefiOTRALTACENTCTRLTRNOTRALTACENTCTRLEVTZUOCENTCTRLP() {
        return new ResponseBansefi.OTRALTACENTCTRLTRNO.TRALTACENTCTRLEVTZ.UOCENTCTRLP();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRALTACENTCTRLTRNI.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRALTACENTCTRLTRNI.STDTRNIPARMV createEjecutarITRALTACENTCTRLTRNISTDTRNIPARMV() {
        return new Ejecutar.ITRALTACENTCTRLTRNI.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRALTACENTCTRLTRNI.TRALTACENTCTRLEVTY.UOCENTCTRLE }
     * 
     */
    public Ejecutar.ITRALTACENTCTRLTRNI.TRALTACENTCTRLEVTY.UOCENTCTRLE createEjecutarITRALTACENTCTRLTRNITRALTACENTCTRLEVTYUOCENTCTRLE() {
        return new Ejecutar.ITRALTACENTCTRLTRNI.TRALTACENTCTRLEVTY.UOCENTCTRLE();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_ALTA_CENT_CTRL_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
