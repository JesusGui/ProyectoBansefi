
package mx.babel.bansefi.banksystem.administracion.webservices.altaperfilempleado;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.administracion.webservices.altaperfilempleado package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_ALTA_PERFIL_EMPL_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.administracion.webservices.altaperfilempleado
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
     * Create an instance of {@link ResponseBansefi.OTRALTAPERFILEMPLTRN }
     * 
     */
    public ResponseBansefi.OTRALTAPERFILEMPLTRN createResponseBansefiOTRALTAPERFILEMPLTRN() {
        return new ResponseBansefi.OTRALTAPERFILEMPLTRN();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRALTAPERFILEMPLTRN.TRALTAPERFILEMPLEVTZ }
     * 
     */
    public ResponseBansefi.OTRALTAPERFILEMPLTRN.TRALTAPERFILEMPLEVTZ createResponseBansefiOTRALTAPERFILEMPLTRNTRALTAPERFILEMPLEVTZ() {
        return new ResponseBansefi.OTRALTAPERFILEMPLTRN.TRALTAPERFILEMPLEVTZ();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRALTAPERFILEMPLTRN }
     * 
     */
    public Ejecutar.ITRALTAPERFILEMPLTRN createEjecutarITRALTAPERFILEMPLTRN() {
        return new Ejecutar.ITRALTAPERFILEMPLTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRALTAPERFILEMPLTRN.TRALTAPERFILEMPLEVTY }
     * 
     */
    public Ejecutar.ITRALTAPERFILEMPLTRN.TRALTAPERFILEMPLEVTY createEjecutarITRALTAPERFILEMPLTRNTRALTAPERFILEMPLEVTY() {
        return new Ejecutar.ITRALTAPERFILEMPLTRN.TRALTAPERFILEMPLEVTY();
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
     * Create an instance of {@link ResponseBansefi.OTRALTAPERFILEMPLTRN.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRALTAPERFILEMPLTRN.STDTRNMSJPARMV createResponseBansefiOTRALTAPERFILEMPLTRNSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRALTAPERFILEMPLTRN.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRALTAPERFILEMPLTRN.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRALTAPERFILEMPLTRN.STDTRNOPARMV createResponseBansefiOTRALTAPERFILEMPLTRNSTDTRNOPARMV() {
        return new ResponseBansefi.OTRALTAPERFILEMPLTRN.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRALTAPERFILEMPLTRN.TRALTAPERFILEMPLEVTZ.EPEMPLPERFILTXP }
     * 
     */
    public ResponseBansefi.OTRALTAPERFILEMPLTRN.TRALTAPERFILEMPLEVTZ.EPEMPLPERFILTXP createResponseBansefiOTRALTAPERFILEMPLTRNTRALTAPERFILEMPLEVTZEPEMPLPERFILTXP() {
        return new ResponseBansefi.OTRALTAPERFILEMPLTRN.TRALTAPERFILEMPLEVTZ.EPEMPLPERFILTXP();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRALTAPERFILEMPLTRN.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRALTAPERFILEMPLTRN.STDTRNIPARMV createEjecutarITRALTAPERFILEMPLTRNSTDTRNIPARMV() {
        return new Ejecutar.ITRALTAPERFILEMPLTRN.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRALTAPERFILEMPLTRN.TRALTAPERFILEMPLEVTY.EPEMPLPERFILTXE }
     * 
     */
    public Ejecutar.ITRALTAPERFILEMPLTRN.TRALTAPERFILEMPLEVTY.EPEMPLPERFILTXE createEjecutarITRALTAPERFILEMPLTRNTRALTAPERFILEMPLEVTYEPEMPLPERFILTXE() {
        return new Ejecutar.ITRALTAPERFILEMPLTRN.TRALTAPERFILEMPLEVTY.EPEMPLPERFILTXE();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_ALTA_PERFIL_EMPL_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
