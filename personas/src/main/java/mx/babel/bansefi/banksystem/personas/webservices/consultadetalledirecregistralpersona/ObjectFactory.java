
package mx.babel.bansefi.banksystem.personas.webservices.consultadetalledirecregistralpersona;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bansefinss.webservicesnss.tr_dr_rgstro_cns_trn package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_DR_RGSTRO_CNS_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bansefinss.webservicesnss.tr_dr_rgstro_cns_trn
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
     * Create an instance of {@link ResponseBansefi.OTRDRRGSTROCNSTRNO }
     * 
     */
    public ResponseBansefi.OTRDRRGSTROCNSTRNO createResponseBansefiOTRDRRGSTROCNSTRNO() {
        return new ResponseBansefi.OTRDRRGSTROCNSTRNO();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRDRRGSTROCNSTRNO.TRDRRGSTROCNSEVTZ }
     * 
     */
    public ResponseBansefi.OTRDRRGSTROCNSTRNO.TRDRRGSTROCNSEVTZ createResponseBansefiOTRDRRGSTROCNSTRNOTRDRRGSTROCNSEVTZ() {
        return new ResponseBansefi.OTRDRRGSTROCNSTRNO.TRDRRGSTROCNSEVTZ();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRDRRGSTROCNSTRNI }
     * 
     */
    public Ejecutar.ITRDRRGSTROCNSTRNI createEjecutarITRDRRGSTROCNSTRNI() {
        return new Ejecutar.ITRDRRGSTROCNSTRNI();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRDRRGSTROCNSTRNI.TRDRRGSTROCNSEVTY }
     * 
     */
    public Ejecutar.ITRDRRGSTROCNSTRNI.TRDRRGSTROCNSEVTY createEjecutarITRDRRGSTROCNSTRNITRDRRGSTROCNSEVTY() {
        return new Ejecutar.ITRDRRGSTROCNSTRNI.TRDRRGSTROCNSEVTY();
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
     * Create an instance of {@link ResponseBansefi.OTRDRRGSTROCNSTRNO.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRDRRGSTROCNSTRNO.STDTRNMSJPARMV createResponseBansefiOTRDRRGSTROCNSTRNOSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRDRRGSTROCNSTRNO.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRDRRGSTROCNSTRNO.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRDRRGSTROCNSTRNO.STDTRNOPARMV createResponseBansefiOTRDRRGSTROCNSTRNOSTDTRNOPARMV() {
        return new ResponseBansefi.OTRDRRGSTROCNSTRNO.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRDRRGSTROCNSTRNO.TRDRRGSTROCNSEVTZ.DRCOMPRGSTROV }
     * 
     */
    public ResponseBansefi.OTRDRRGSTROCNSTRNO.TRDRRGSTROCNSEVTZ.DRCOMPRGSTROV createResponseBansefiOTRDRRGSTROCNSTRNOTRDRRGSTROCNSEVTZDRCOMPRGSTROV() {
        return new ResponseBansefi.OTRDRRGSTROCNSTRNO.TRDRRGSTROCNSEVTZ.DRCOMPRGSTROV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRDRRGSTROCNSTRNI.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRDRRGSTROCNSTRNI.STDTRNIPARMV createEjecutarITRDRRGSTROCNSTRNISTDTRNIPARMV() {
        return new Ejecutar.ITRDRRGSTROCNSTRNI.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRDRRGSTROCNSTRNI.TRDRRGSTROCNSEVTY.DRRGSTROP }
     * 
     */
    public Ejecutar.ITRDRRGSTROCNSTRNI.TRDRRGSTROCNSEVTY.DRRGSTROP createEjecutarITRDRRGSTROCNSTRNITRDRRGSTROCNSEVTYDRRGSTROP() {
        return new Ejecutar.ITRDRRGSTROCNSTRNI.TRDRRGSTROCNSEVTY.DRRGSTROP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_DR_RGSTRO_CNS_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
