
package mx.babel.bansefi.banksystem.personas.webservices.consultaotrostelfaxpersona;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bansefinss.webservicesnss.tr_dr_cons_dr_elctr_trn package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_DR_CONS_DR_ELCTR_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bansefinss.webservicesnss.tr_dr_cons_dr_elctr_trn
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
     * Create an instance of {@link ResponseBansefi.OTRDRCONSDRELCTRTRN }
     * 
     */
    public ResponseBansefi.OTRDRCONSDRELCTRTRN createResponseBansefiOTRDRCONSDRELCTRTRN() {
        return new ResponseBansefi.OTRDRCONSDRELCTRTRN();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRDRCONSDRELCTRTRN.TRDRCONSDRELCTREVTZ }
     * 
     */
    public ResponseBansefi.OTRDRCONSDRELCTRTRN.TRDRCONSDRELCTREVTZ createResponseBansefiOTRDRCONSDRELCTRTRNTRDRCONSDRELCTREVTZ() {
        return new ResponseBansefi.OTRDRCONSDRELCTRTRN.TRDRCONSDRELCTREVTZ();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRDRCONSDRELCTRTRN }
     * 
     */
    public Ejecutar.ITRDRCONSDRELCTRTRN createEjecutarITRDRCONSDRELCTRTRN() {
        return new Ejecutar.ITRDRCONSDRELCTRTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRDRCONSDRELCTRTRN.TRDRCONSDRELCTREVTY }
     * 
     */
    public Ejecutar.ITRDRCONSDRELCTRTRN.TRDRCONSDRELCTREVTY createEjecutarITRDRCONSDRELCTRTRNTRDRCONSDRELCTREVTY() {
        return new Ejecutar.ITRDRCONSDRELCTRTRN.TRDRCONSDRELCTREVTY();
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
     * Create an instance of {@link ResponseBansefi.OTRDRCONSDRELCTRTRN.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRDRCONSDRELCTRTRN.STDTRNMSJPARMV createResponseBansefiOTRDRCONSDRELCTRTRNSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRDRCONSDRELCTRTRN.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRDRCONSDRELCTRTRN.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRDRCONSDRELCTRTRN.STDTRNOPARMV createResponseBansefiOTRDRCONSDRELCTRTRNSTDTRNOPARMV() {
        return new ResponseBansefi.OTRDRCONSDRELCTRTRN.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRDRCONSDRELCTRTRN.TRDRCONSDRELCTREVTZ.TRDRCONSDRELCTREVTV }
     * 
     */
    public ResponseBansefi.OTRDRCONSDRELCTRTRN.TRDRCONSDRELCTREVTZ.TRDRCONSDRELCTREVTV createResponseBansefiOTRDRCONSDRELCTRTRNTRDRCONSDRELCTREVTZTRDRCONSDRELCTREVTV() {
        return new ResponseBansefi.OTRDRCONSDRELCTRTRN.TRDRCONSDRELCTREVTZ.TRDRCONSDRELCTREVTV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRDRCONSDRELCTRTRN.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRDRCONSDRELCTRTRN.STDTRNIPARMV createEjecutarITRDRCONSDRELCTRTRNSTDTRNIPARMV() {
        return new Ejecutar.ITRDRCONSDRELCTRTRN.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRDRCONSDRELCTRTRN.TRDRCONSDRELCTREVTY.PEPERSRLDIRP }
     * 
     */
    public Ejecutar.ITRDRCONSDRELCTRTRN.TRDRCONSDRELCTREVTY.PEPERSRLDIRP createEjecutarITRDRCONSDRELCTRTRNTRDRCONSDRELCTREVTYPEPERSRLDIRP() {
        return new Ejecutar.ITRDRCONSDRELCTRTRN.TRDRCONSDRELCTREVTY.PEPERSRLDIRP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_DR_CONS_DR_ELCTR_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
