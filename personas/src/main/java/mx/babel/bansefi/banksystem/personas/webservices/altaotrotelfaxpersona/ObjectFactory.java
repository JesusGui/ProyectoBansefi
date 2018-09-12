
package mx.babel.bansefi.banksystem.personas.webservices.altaotrotelfaxpersona;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bansefinss.webservicesnss.tr_dr_alta_dr_elctr_trn package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_DR_ALTA_DR_ELCTR_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bansefinss.webservicesnss.tr_dr_alta_dr_elctr_trn
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
     * Create an instance of {@link ResponseBansefi.OTRDRALTADRELCTRTRN }
     * 
     */
    public ResponseBansefi.OTRDRALTADRELCTRTRN createResponseBansefiOTRDRALTADRELCTRTRN() {
        return new ResponseBansefi.OTRDRALTADRELCTRTRN();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRDRALTADRELCTRTRN.TRDRALTADRELCTREVTZ }
     * 
     */
    public ResponseBansefi.OTRDRALTADRELCTRTRN.TRDRALTADRELCTREVTZ createResponseBansefiOTRDRALTADRELCTRTRNTRDRALTADRELCTREVTZ() {
        return new ResponseBansefi.OTRDRALTADRELCTRTRN.TRDRALTADRELCTREVTZ();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRDRALTADRELCTRTRN }
     * 
     */
    public Ejecutar.ITRDRALTADRELCTRTRN createEjecutarITRDRALTADRELCTRTRN() {
        return new Ejecutar.ITRDRALTADRELCTRTRN();
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
     * Create an instance of {@link ResponseBansefi.OTRDRALTADRELCTRTRN.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRDRALTADRELCTRTRN.STDTRNMSJPARMV createResponseBansefiOTRDRALTADRELCTRTRNSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRDRALTADRELCTRTRN.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRDRALTADRELCTRTRN.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRDRALTADRELCTRTRN.STDTRNOPARMV createResponseBansefiOTRDRALTADRELCTRTRNSTDTRNOPARMV() {
        return new ResponseBansefi.OTRDRALTADRELCTRTRN.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRDRALTADRELCTRTRN.TRDRALTADRELCTREVTZ.DRELCTRP }
     * 
     */
    public ResponseBansefi.OTRDRALTADRELCTRTRN.TRDRALTADRELCTREVTZ.DRELCTRP createResponseBansefiOTRDRALTADRELCTRTRNTRDRALTADRELCTREVTZDRELCTRP() {
        return new ResponseBansefi.OTRDRALTADRELCTRTRN.TRDRALTADRELCTREVTZ.DRELCTRP();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRDRALTADRELCTRTRN.TRDRALTADRELCTREVTY }
     * 
     */
    public Ejecutar.ITRDRALTADRELCTRTRN.TRDRALTADRELCTREVTY createEjecutarITRDRALTADRELCTRTRNTRDRALTADRELCTREVTY() {
        return new Ejecutar.ITRDRALTADRELCTRTRN.TRDRALTADRELCTREVTY();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRDRALTADRELCTRTRN.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRDRALTADRELCTRTRN.STDTRNIPARMV createEjecutarITRDRALTADRELCTRTRNSTDTRNIPARMV() {
        return new Ejecutar.ITRDRALTADRELCTRTRN.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_DR_ALTA_DR_ELCTR_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
