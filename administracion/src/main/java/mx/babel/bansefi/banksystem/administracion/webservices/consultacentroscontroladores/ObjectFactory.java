
package mx.babel.bansefi.banksystem.administracion.webservices.consultacentroscontroladores;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bansefinss.webservicesnss.tr_uo_con_cent_ctrl_trn package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_UO_CON_CENT_CTRL_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bansefinss.webservicesnss.tr_uo_con_cent_ctrl_trn
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
     * Create an instance of {@link ResponseBansefi.OTRUOCONCENTCTRLTRN }
     * 
     */
    public ResponseBansefi.OTRUOCONCENTCTRLTRN createResponseBansefiOTRUOCONCENTCTRLTRN() {
        return new ResponseBansefi.OTRUOCONCENTCTRLTRN();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRUOCONCENTCTRLTRN.TRUOCONCENTCTRLEVTZ }
     * 
     */
    public ResponseBansefi.OTRUOCONCENTCTRLTRN.TRUOCONCENTCTRLEVTZ createResponseBansefiOTRUOCONCENTCTRLTRNTRUOCONCENTCTRLEVTZ() {
        return new ResponseBansefi.OTRUOCONCENTCTRLTRN.TRUOCONCENTCTRLEVTZ();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRUOCONCENTCTRLTRN }
     * 
     */
    public Ejecutar.ITRUOCONCENTCTRLTRN createEjecutarITRUOCONCENTCTRLTRN() {
        return new Ejecutar.ITRUOCONCENTCTRLTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRUOCONCENTCTRLTRN.TRUOCONCENTCTRLEVTY }
     * 
     */
    public Ejecutar.ITRUOCONCENTCTRLTRN.TRUOCONCENTCTRLEVTY createEjecutarITRUOCONCENTCTRLTRNTRUOCONCENTCTRLEVTY() {
        return new Ejecutar.ITRUOCONCENTCTRLTRN.TRUOCONCENTCTRLEVTY();
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
     * Create an instance of {@link ResponseBansefi.OTRUOCONCENTCTRLTRN.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRUOCONCENTCTRLTRN.STDTRNMSJPARMV createResponseBansefiOTRUOCONCENTCTRLTRNSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRUOCONCENTCTRLTRN.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRUOCONCENTCTRLTRN.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRUOCONCENTCTRLTRN.STDTRNOPARMV createResponseBansefiOTRUOCONCENTCTRLTRNSTDTRNOPARMV() {
        return new ResponseBansefi.OTRUOCONCENTCTRLTRN.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRUOCONCENTCTRLTRN.TRUOCONCENTCTRLEVTZ.UOCENTCTRLE }
     * 
     */
    public ResponseBansefi.OTRUOCONCENTCTRLTRN.TRUOCONCENTCTRLEVTZ.UOCENTCTRLE createResponseBansefiOTRUOCONCENTCTRLTRNTRUOCONCENTCTRLEVTZUOCENTCTRLE() {
        return new ResponseBansefi.OTRUOCONCENTCTRLTRN.TRUOCONCENTCTRLEVTZ.UOCENTCTRLE();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRUOCONCENTCTRLTRN.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRUOCONCENTCTRLTRN.STDTRNIPARMV createEjecutarITRUOCONCENTCTRLTRNSTDTRNIPARMV() {
        return new Ejecutar.ITRUOCONCENTCTRLTRN.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRUOCONCENTCTRLTRN.TRUOCONCENTCTRLEVTY.UOCENTCTRLP }
     * 
     */
    public Ejecutar.ITRUOCONCENTCTRLTRN.TRUOCONCENTCTRLEVTY.UOCENTCTRLP createEjecutarITRUOCONCENTCTRLTRNTRUOCONCENTCTRLEVTYUOCENTCTRLP() {
        return new Ejecutar.ITRUOCONCENTCTRLTRN.TRUOCONCENTCTRLEVTY.UOCENTCTRLP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_UO_CON_CENT_CTRL_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
