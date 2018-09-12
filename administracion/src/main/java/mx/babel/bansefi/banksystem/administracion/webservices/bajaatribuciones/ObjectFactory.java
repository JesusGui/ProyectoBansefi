
package mx.babel.bansefi.banksystem.administracion.webservices.bajaatribuciones;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bansefinss.webservicesnss.tr_baja_atrib_empl_trn package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_BAJA_ATRIB_EMPL_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bansefinss.webservicesnss.tr_baja_atrib_empl_trn
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
     * Create an instance of {@link ResponseBansefi.OTRBAJAATRIBEMPLTRNO }
     * 
     */
    public ResponseBansefi.OTRBAJAATRIBEMPLTRNO createResponseBansefiOTRBAJAATRIBEMPLTRNO() {
        return new ResponseBansefi.OTRBAJAATRIBEMPLTRNO();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRBAJAATRIBEMPLTRNI }
     * 
     */
    public Ejecutar.ITRBAJAATRIBEMPLTRNI createEjecutarITRBAJAATRIBEMPLTRNI() {
        return new Ejecutar.ITRBAJAATRIBEMPLTRNI();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRBAJAATRIBEMPLTRNI.TRBAJAATRIBEMPLEVTY }
     * 
     */
    public Ejecutar.ITRBAJAATRIBEMPLTRNI.TRBAJAATRIBEMPLEVTY createEjecutarITRBAJAATRIBEMPLTRNITRBAJAATRIBEMPLEVTY() {
        return new Ejecutar.ITRBAJAATRIBEMPLTRNI.TRBAJAATRIBEMPLEVTY();
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
     * Create an instance of {@link ResponseBansefi.OTRBAJAATRIBEMPLTRNO.TRBAJAATRIBEMPLEVTZ }
     * 
     */
    public ResponseBansefi.OTRBAJAATRIBEMPLTRNO.TRBAJAATRIBEMPLEVTZ createResponseBansefiOTRBAJAATRIBEMPLTRNOTRBAJAATRIBEMPLEVTZ() {
        return new ResponseBansefi.OTRBAJAATRIBEMPLTRNO.TRBAJAATRIBEMPLEVTZ();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRBAJAATRIBEMPLTRNO.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRBAJAATRIBEMPLTRNO.STDTRNMSJPARMV createResponseBansefiOTRBAJAATRIBEMPLTRNOSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRBAJAATRIBEMPLTRNO.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRBAJAATRIBEMPLTRNO.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRBAJAATRIBEMPLTRNO.STDTRNOPARMV createResponseBansefiOTRBAJAATRIBEMPLTRNOSTDTRNOPARMV() {
        return new ResponseBansefi.OTRBAJAATRIBEMPLTRNO.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRBAJAATRIBEMPLTRNI.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRBAJAATRIBEMPLTRNI.STDTRNIPARMV createEjecutarITRBAJAATRIBEMPLTRNISTDTRNIPARMV() {
        return new Ejecutar.ITRBAJAATRIBEMPLTRNI.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRBAJAATRIBEMPLTRNI.TRBAJAATRIBEMPLEVTY.EPATRIBUCIONESP }
     * 
     */
    public Ejecutar.ITRBAJAATRIBEMPLTRNI.TRBAJAATRIBEMPLEVTY.EPATRIBUCIONESP createEjecutarITRBAJAATRIBEMPLTRNITRBAJAATRIBEMPLEVTYEPATRIBUCIONESP() {
        return new Ejecutar.ITRBAJAATRIBEMPLTRNI.TRBAJAATRIBEMPLEVTY.EPATRIBUCIONESP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_BAJA_ATRIB_EMPL_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
