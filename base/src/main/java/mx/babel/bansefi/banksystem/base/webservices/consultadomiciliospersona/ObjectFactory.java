
package mx.babel.bansefi.banksystem.base.webservices.consultadomiciliospersona;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.cuentas.webservices.consultadomiciliospersona package. 
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

    private final static QName _EjecutarResult_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_DR_DM_LS_CNS_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.cuentas.webservices.consultadomiciliospersona
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
     * Create an instance of {@link ResponseBansefi.OTRDRDMLSCNSTRNO }
     * 
     */
    public ResponseBansefi.OTRDRDMLSCNSTRNO createResponseBansefiOTRDRDMLSCNSTRNO() {
        return new ResponseBansefi.OTRDRDMLSCNSTRNO();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRDRDMLSCNSTRNO.TRDRDMLSCNSEVTZ }
     * 
     */
    public ResponseBansefi.OTRDRDMLSCNSTRNO.TRDRDMLSCNSEVTZ createResponseBansefiOTRDRDMLSCNSTRNOTRDRDMLSCNSEVTZ() {
        return new ResponseBansefi.OTRDRDMLSCNSTRNO.TRDRDMLSCNSEVTZ();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRDRDMLSCNSTRNI }
     * 
     */
    public Ejecutar.ITRDRDMLSCNSTRNI createEjecutarITRDRDMLSCNSTRNI() {
        return new Ejecutar.ITRDRDMLSCNSTRNI();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRDRDMLSCNSTRNI.TRDRDMLSCNSEVTY }
     * 
     */
    public Ejecutar.ITRDRDMLSCNSTRNI.TRDRDMLSCNSEVTY createEjecutarITRDRDMLSCNSTRNITRDRDMLSCNSEVTY() {
        return new Ejecutar.ITRDRDMLSCNSTRNI.TRDRDMLSCNSEVTY();
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
     * Create an instance of {@link ResponseBansefi.OTRDRDMLSCNSTRNO.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRDRDMLSCNSTRNO.STDTRNMSJPARMV createResponseBansefiOTRDRDMLSCNSTRNOSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRDRDMLSCNSTRNO.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRDRDMLSCNSTRNO.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRDRDMLSCNSTRNO.STDTRNOPARMV createResponseBansefiOTRDRDMLSCNSTRNOSTDTRNOPARMV() {
        return new ResponseBansefi.OTRDRDMLSCNSTRNO.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRDRDMLSCNSTRNO.TRDRDMLSCNSEVTZ.TRDRDMLSCNSEVTV }
     * 
     */
    public ResponseBansefi.OTRDRDMLSCNSTRNO.TRDRDMLSCNSEVTZ.TRDRDMLSCNSEVTV createResponseBansefiOTRDRDMLSCNSTRNOTRDRDMLSCNSEVTZTRDRDMLSCNSEVTV() {
        return new ResponseBansefi.OTRDRDMLSCNSTRNO.TRDRDMLSCNSEVTZ.TRDRDMLSCNSEVTV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRDRDMLSCNSTRNI.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRDRDMLSCNSTRNI.STDTRNIPARMV createEjecutarITRDRDMLSCNSTRNISTDTRNIPARMV() {
        return new Ejecutar.ITRDRDMLSCNSTRNI.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRDRDMLSCNSTRNI.TRDRDMLSCNSEVTY.PEPERSRLDIRP }
     * 
     */
    public Ejecutar.ITRDRDMLSCNSTRNI.TRDRDMLSCNSEVTY.PEPERSRLDIRP createEjecutarITRDRDMLSCNSTRNITRDRDMLSCNSEVTYPEPERSRLDIRP() {
        return new Ejecutar.ITRDRDMLSCNSTRNI.TRDRDMLSCNSEVTY.PEPERSRLDIRP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_DR_DM_LS_CNS_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(_EjecutarResult_QNAME, EjecutarResult.class, null, value);
    }

}
