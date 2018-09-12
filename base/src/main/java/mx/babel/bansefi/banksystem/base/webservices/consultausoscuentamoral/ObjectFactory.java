
package mx.babel.bansefi.banksystem.base.webservices.consultausoscuentamoral;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bansefinss.webservicesnss.pe_cons_org_uso_cta_trn package. 
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

    private final static QName _EjecutarResult_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_CONS_ORG_USO_CTA_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bansefinss.webservicesnss.pe_cons_org_uso_cta_trn
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
     * Create an instance of {@link ResponseBansefi.OPECONSORGUSOCTATRN }
     * 
     */
    public ResponseBansefi.OPECONSORGUSOCTATRN createResponseBansefiOPECONSORGUSOCTATRN() {
        return new ResponseBansefi.OPECONSORGUSOCTATRN();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OPECONSORGUSOCTATRN.PECONSORGUSOCTAEVTZ }
     * 
     */
    public ResponseBansefi.OPECONSORGUSOCTATRN.PECONSORGUSOCTAEVTZ createResponseBansefiOPECONSORGUSOCTATRNPECONSORGUSOCTAEVTZ() {
        return new ResponseBansefi.OPECONSORGUSOCTATRN.PECONSORGUSOCTAEVTZ();
    }

    /**
     * Create an instance of {@link Ejecutar.IPECONSORGUSOCTATRN }
     * 
     */
    public Ejecutar.IPECONSORGUSOCTATRN createEjecutarIPECONSORGUSOCTATRN() {
        return new Ejecutar.IPECONSORGUSOCTATRN();
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
     * Create an instance of {@link ResponseBansefi.OPECONSORGUSOCTATRN.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OPECONSORGUSOCTATRN.STDTRNMSJPARMV createResponseBansefiOPECONSORGUSOCTATRNSTDTRNMSJPARMV() {
        return new ResponseBansefi.OPECONSORGUSOCTATRN.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OPECONSORGUSOCTATRN.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OPECONSORGUSOCTATRN.STDTRNOPARMV createResponseBansefiOPECONSORGUSOCTATRNSTDTRNOPARMV() {
        return new ResponseBansefi.OPECONSORGUSOCTATRN.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OPECONSORGUSOCTATRN.PECONSORGUSOCTAEVTZ.PEPERSUSOCTAE }
     * 
     */
    public ResponseBansefi.OPECONSORGUSOCTATRN.PECONSORGUSOCTAEVTZ.PEPERSUSOCTAE createResponseBansefiOPECONSORGUSOCTATRNPECONSORGUSOCTAEVTZPEPERSUSOCTAE() {
        return new ResponseBansefi.OPECONSORGUSOCTATRN.PECONSORGUSOCTAEVTZ.PEPERSUSOCTAE();
    }

    /**
     * Create an instance of {@link Ejecutar.IPECONSORGUSOCTATRN.PECONSORGUSOCTAEVTY }
     * 
     */
    public Ejecutar.IPECONSORGUSOCTATRN.PECONSORGUSOCTAEVTY createEjecutarIPECONSORGUSOCTATRNPECONSORGUSOCTAEVTY() {
        return new Ejecutar.IPECONSORGUSOCTATRN.PECONSORGUSOCTAEVTY();
    }

    /**
     * Create an instance of {@link Ejecutar.IPECONSORGUSOCTATRN.STDTRNIPARMV }
     * 
     */
    public Ejecutar.IPECONSORGUSOCTATRN.STDTRNIPARMV createEjecutarIPECONSORGUSOCTATRNSTDTRNIPARMV() {
        return new Ejecutar.IPECONSORGUSOCTATRN.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_CONS_ORG_USO_CTA_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(_EjecutarResult_QNAME, EjecutarResult.class, null, value);
    }

}
