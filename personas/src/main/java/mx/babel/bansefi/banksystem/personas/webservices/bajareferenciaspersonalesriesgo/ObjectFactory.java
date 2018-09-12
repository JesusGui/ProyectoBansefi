
package mx.babel.bansefi.banksystem.personas.webservices.bajareferenciaspersonalesriesgo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bansefinss.webservicesnss.pe_baja_indv_ref_per_trn package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_BAJA_INDV_REF_PER_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bansefinss.webservicesnss.pe_baja_indv_ref_per_trn
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
     * Create an instance of {@link ResponseBansefi.OPEBAJAINDVREFPERTRN }
     * 
     */
    public ResponseBansefi.OPEBAJAINDVREFPERTRN createResponseBansefiOPEBAJAINDVREFPERTRN() {
        return new ResponseBansefi.OPEBAJAINDVREFPERTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.IPEBAJAINDVREFPERTRN }
     * 
     */
    public Ejecutar.IPEBAJAINDVREFPERTRN createEjecutarIPEBAJAINDVREFPERTRN() {
        return new Ejecutar.IPEBAJAINDVREFPERTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.IPEBAJAINDVREFPERTRN.PEBAJAINDVREFPEREVT }
     * 
     */
    public Ejecutar.IPEBAJAINDVREFPERTRN.PEBAJAINDVREFPEREVT createEjecutarIPEBAJAINDVREFPERTRNPEBAJAINDVREFPEREVT() {
        return new Ejecutar.IPEBAJAINDVREFPERTRN.PEBAJAINDVREFPEREVT();
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
     * Create an instance of {@link ResponseBansefi.OPEBAJAINDVREFPERTRN.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OPEBAJAINDVREFPERTRN.STDTRNMSJPARMV createResponseBansefiOPEBAJAINDVREFPERTRNSTDTRNMSJPARMV() {
        return new ResponseBansefi.OPEBAJAINDVREFPERTRN.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OPEBAJAINDVREFPERTRN.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OPEBAJAINDVREFPERTRN.STDTRNOPARMV createResponseBansefiOPEBAJAINDVREFPERTRNSTDTRNOPARMV() {
        return new ResponseBansefi.OPEBAJAINDVREFPERTRN.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.IPEBAJAINDVREFPERTRN.STDTRNIPARMV }
     * 
     */
    public Ejecutar.IPEBAJAINDVREFPERTRN.STDTRNIPARMV createEjecutarIPEBAJAINDVREFPERTRNSTDTRNIPARMV() {
        return new Ejecutar.IPEBAJAINDVREFPERTRN.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.IPEBAJAINDVREFPERTRN.PEBAJAINDVREFPEREVT.PEPERSRLREFPERP }
     * 
     */
    public Ejecutar.IPEBAJAINDVREFPERTRN.PEBAJAINDVREFPEREVT.PEPERSRLREFPERP createEjecutarIPEBAJAINDVREFPERTRNPEBAJAINDVREFPEREVTPEPERSRLREFPERP() {
        return new Ejecutar.IPEBAJAINDVREFPERTRN.PEBAJAINDVREFPEREVT.PEPERSRLREFPERP();
    }

    /**
     * Create an instance of {@link Ejecutar.IPEBAJAINDVREFPERTRN.PEBAJAINDVREFPEREVT.PEPERSRLREFPERE }
     * 
     */
    public Ejecutar.IPEBAJAINDVREFPERTRN.PEBAJAINDVREFPEREVT.PEPERSRLREFPERE createEjecutarIPEBAJAINDVREFPERTRNPEBAJAINDVREFPEREVTPEPERSRLREFPERE() {
        return new Ejecutar.IPEBAJAINDVREFPERTRN.PEBAJAINDVREFPEREVT.PEPERSRLREFPERE();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_BAJA_INDV_REF_PER_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
