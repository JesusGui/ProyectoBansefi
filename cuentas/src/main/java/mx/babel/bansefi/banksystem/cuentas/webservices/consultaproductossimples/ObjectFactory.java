
package mx.babel.bansefi.banksystem.cuentas.webservices.consultaproductossimples;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.cuentas.webservices.consultaproductossimples package. 
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

    private final static QName _EJECUTARRESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_LISTA_RL_PV_PS_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.cuentas.webservices.consultaproductossimples
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
     * Create an instance of {@link ResponseBansefi.OTRLISTARLPVPSTRNO }
     * 
     */
    public ResponseBansefi.OTRLISTARLPVPSTRNO createResponseBansefiOTRLISTARLPVPSTRNO() {
        return new ResponseBansefi.OTRLISTARLPVPSTRNO();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRLISTARLPVPSTRNI }
     * 
     */
    public Ejecutar.ITRLISTARLPVPSTRNI createEjecutarITRLISTARLPVPSTRNI() {
        return new Ejecutar.ITRLISTARLPVPSTRNI();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRLISTARLPVPSTRNI.TRLISTARLPVPSEVTY }
     * 
     */
    public Ejecutar.ITRLISTARLPVPSTRNI.TRLISTARLPVPSEVTY createEjecutarITRLISTARLPVPSTRNITRLISTARLPVPSEVTY() {
        return new Ejecutar.ITRLISTARLPVPSTRNI.TRLISTARLPVPSEVTY();
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
     * Create an instance of {@link ResponseBansefi.OTRLISTARLPVPSTRNO.TRLISTARLPVPSEVTZ }
     * 
     */
    public ResponseBansefi.OTRLISTARLPVPSTRNO.TRLISTARLPVPSEVTZ createResponseBansefiOTRLISTARLPVPSTRNOTRLISTARLPVPSEVTZ() {
        return new ResponseBansefi.OTRLISTARLPVPSTRNO.TRLISTARLPVPSEVTZ();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRLISTARLPVPSTRNO.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRLISTARLPVPSTRNO.STDTRNMSJPARMV createResponseBansefiOTRLISTARLPVPSTRNOSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRLISTARLPVPSTRNO.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRLISTARLPVPSTRNO.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRLISTARLPVPSTRNO.STDTRNOPARMV createResponseBansefiOTRLISTARLPVPSTRNOSTDTRNOPARMV() {
        return new ResponseBansefi.OTRLISTARLPVPSTRNO.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRLISTARLPVPSTRNI.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRLISTARLPVPSTRNI.STDTRNIPARMV createEjecutarITRLISTARLPVPSTRNISTDTRNIPARMV() {
        return new Ejecutar.ITRLISTARLPVPSTRNI.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRLISTARLPVPSTRNI.TRLISTARLPVPSEVTY.PVPDVRLPDSP }
     * 
     */
    public Ejecutar.ITRLISTARLPVPSTRNI.TRLISTARLPVPSEVTY.PVPDVRLPDSP createEjecutarITRLISTARLPVPSTRNITRLISTARLPVPSEVTYPVPDVRLPDSP() {
        return new Ejecutar.ITRLISTARLPVPSTRNI.TRLISTARLPVPSEVTY.PVPDVRLPDSP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_LISTA_RL_PV_PS_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(_EJECUTARRESULT_QNAME, EjecutarResult.class, null, value);
    }

}
