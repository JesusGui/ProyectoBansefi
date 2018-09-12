
package mx.babel.bansefi.banksystem.personas.webservices.modificacionreferenciaspersonalesriesgo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bansefinss.webservicesnss.pe_modi_indv_ref_per_trn package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_MODI_INDV_REF_PER_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bansefinss.webservicesnss.pe_modi_indv_ref_per_trn
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
     * Create an instance of {@link ResponseBansefi.OPEMODIINDVREFPERTRN }
     * 
     */
    public ResponseBansefi.OPEMODIINDVREFPERTRN createResponseBansefiOPEMODIINDVREFPERTRN() {
        return new ResponseBansefi.OPEMODIINDVREFPERTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.IPEMODIINDVREFPERTRN }
     * 
     */
    public Ejecutar.IPEMODIINDVREFPERTRN createEjecutarIPEMODIINDVREFPERTRN() {
        return new Ejecutar.IPEMODIINDVREFPERTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.IPEMODIINDVREFPERTRN.PEMODIINDVREFPEREVT }
     * 
     */
    public Ejecutar.IPEMODIINDVREFPERTRN.PEMODIINDVREFPEREVT createEjecutarIPEMODIINDVREFPERTRNPEMODIINDVREFPEREVT() {
        return new Ejecutar.IPEMODIINDVREFPERTRN.PEMODIINDVREFPEREVT();
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
     * Create an instance of {@link ResponseBansefi.OPEMODIINDVREFPERTRN.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OPEMODIINDVREFPERTRN.STDTRNMSJPARMV createResponseBansefiOPEMODIINDVREFPERTRNSTDTRNMSJPARMV() {
        return new ResponseBansefi.OPEMODIINDVREFPERTRN.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OPEMODIINDVREFPERTRN.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OPEMODIINDVREFPERTRN.STDTRNOPARMV createResponseBansefiOPEMODIINDVREFPERTRNSTDTRNOPARMV() {
        return new ResponseBansefi.OPEMODIINDVREFPERTRN.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.IPEMODIINDVREFPERTRN.STDTRNIPARMV }
     * 
     */
    public Ejecutar.IPEMODIINDVREFPERTRN.STDTRNIPARMV createEjecutarIPEMODIINDVREFPERTRNSTDTRNIPARMV() {
        return new Ejecutar.IPEMODIINDVREFPERTRN.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.IPEMODIINDVREFPERTRN.PEMODIINDVREFPEREVT.PEPERSRLREFPERP }
     * 
     */
    public Ejecutar.IPEMODIINDVREFPERTRN.PEMODIINDVREFPEREVT.PEPERSRLREFPERP createEjecutarIPEMODIINDVREFPERTRNPEMODIINDVREFPEREVTPEPERSRLREFPERP() {
        return new Ejecutar.IPEMODIINDVREFPERTRN.PEMODIINDVREFPEREVT.PEPERSRLREFPERP();
    }

    /**
     * Create an instance of {@link Ejecutar.IPEMODIINDVREFPERTRN.PEMODIINDVREFPEREVT.PEPERSRLREFPERE }
     * 
     */
    public Ejecutar.IPEMODIINDVREFPERTRN.PEMODIINDVREFPEREVT.PEPERSRLREFPERE createEjecutarIPEMODIINDVREFPERTRNPEMODIINDVREFPEREVTPEPERSRLREFPERE() {
        return new Ejecutar.IPEMODIINDVREFPERTRN.PEMODIINDVREFPEREVT.PEPERSRLREFPERE();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_MODI_INDV_REF_PER_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
