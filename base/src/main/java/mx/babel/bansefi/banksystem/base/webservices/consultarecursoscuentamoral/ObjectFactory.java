
package mx.babel.bansefi.banksystem.base.webservices.consultarecursoscuentamoral;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bansefinss.webservicesnss.pe_cons_org_rec_cta_ds_trn package. 
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

    private final static QName _EjecutarResult_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_CONS_ORG_REC_CTA_DS_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bansefinss.webservicesnss.pe_cons_org_rec_cta_ds_trn
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
     * Create an instance of {@link ResponseBansefi.OPECONSORGRECCTADST }
     * 
     */
    public ResponseBansefi.OPECONSORGRECCTADST createResponseBansefiOPECONSORGRECCTADST() {
        return new ResponseBansefi.OPECONSORGRECCTADST();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OPECONSORGRECCTADST.PECONSORGRECCTADSEV }
     * 
     */
    public ResponseBansefi.OPECONSORGRECCTADST.PECONSORGRECCTADSEV createResponseBansefiOPECONSORGRECCTADSTPECONSORGRECCTADSEV() {
        return new ResponseBansefi.OPECONSORGRECCTADST.PECONSORGRECCTADSEV();
    }

    /**
     * Create an instance of {@link Ejecutar.IPECONSORGRECCTADST }
     * 
     */
    public Ejecutar.IPECONSORGRECCTADST createEjecutarIPECONSORGRECCTADST() {
        return new Ejecutar.IPECONSORGRECCTADST();
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
     * Create an instance of {@link ResponseBansefi.OPECONSORGRECCTADST.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OPECONSORGRECCTADST.STDTRNMSJPARMV createResponseBansefiOPECONSORGRECCTADSTSTDTRNMSJPARMV() {
        return new ResponseBansefi.OPECONSORGRECCTADST.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OPECONSORGRECCTADST.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OPECONSORGRECCTADST.STDTRNOPARMV createResponseBansefiOPECONSORGRECCTADSTSTDTRNOPARMV() {
        return new ResponseBansefi.OPECONSORGRECCTADST.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OPECONSORGRECCTADST.PECONSORGRECCTADSEV.PEPERSRCSOSCTAE }
     * 
     */
    public ResponseBansefi.OPECONSORGRECCTADST.PECONSORGRECCTADSEV.PEPERSRCSOSCTAE createResponseBansefiOPECONSORGRECCTADSTPECONSORGRECCTADSEVPEPERSRCSOSCTAE() {
        return new ResponseBansefi.OPECONSORGRECCTADST.PECONSORGRECCTADSEV.PEPERSRCSOSCTAE();
    }

    /**
     * Create an instance of {@link Ejecutar.IPECONSORGRECCTADST.PECONSORGRECCTADSEV }
     * 
     */
    public Ejecutar.IPECONSORGRECCTADST.PECONSORGRECCTADSEV createEjecutarIPECONSORGRECCTADSTPECONSORGRECCTADSEV() {
        return new Ejecutar.IPECONSORGRECCTADST.PECONSORGRECCTADSEV();
    }

    /**
     * Create an instance of {@link Ejecutar.IPECONSORGRECCTADST.STDTRNIPARMV }
     * 
     */
    public Ejecutar.IPECONSORGRECCTADST.STDTRNIPARMV createEjecutarIPECONSORGRECCTADSTSTDTRNIPARMV() {
        return new Ejecutar.IPECONSORGRECCTADST.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_CONS_ORG_REC_CTA_DS_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(_EjecutarResult_QNAME, EjecutarResult.class, null, value);
    }

}
