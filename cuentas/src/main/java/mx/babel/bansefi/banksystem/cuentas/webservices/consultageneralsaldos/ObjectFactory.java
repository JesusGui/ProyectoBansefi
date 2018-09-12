
package mx.babel.bansefi.banksystem.cuentas.webservices.consultageneralsaldos;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.cuentas.webservices.consultageneralsaldos package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CONS_SALDOS_GEN_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.cuentas.webservices.consultageneralsaldos
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
     * Create an instance of {@link ResponseBansefi.OTRCONSSALDOSGENTRNO }
     * 
     */
    public ResponseBansefi.OTRCONSSALDOSGENTRNO createResponseBansefiOTRCONSSALDOSGENTRNO() {
        return new ResponseBansefi.OTRCONSSALDOSGENTRNO();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSSALDOSGENTRNI }
     * 
     */
    public Ejecutar.ITRCONSSALDOSGENTRNI createEjecutarITRCONSSALDOSGENTRNI() {
        return new Ejecutar.ITRCONSSALDOSGENTRNI();
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
     * Create an instance of {@link ResponseBansefi.OTRCONSSALDOSGENTRNO.CTCTASDOSV }
     * 
     */
    public ResponseBansefi.OTRCONSSALDOSGENTRNO.CTCTASDOSV createResponseBansefiOTRCONSSALDOSGENTRNOCTCTASDOSV() {
        return new ResponseBansefi.OTRCONSSALDOSGENTRNO.CTCTASDOSV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCONSSALDOSGENTRNO.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRCONSSALDOSGENTRNO.STDTRNMSJPARMV createResponseBansefiOTRCONSSALDOSGENTRNOSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRCONSSALDOSGENTRNO.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCONSSALDOSGENTRNO.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRCONSSALDOSGENTRNO.STDTRNOPARMV createResponseBansefiOTRCONSSALDOSGENTRNOSTDTRNOPARMV() {
        return new ResponseBansefi.OTRCONSSALDOSGENTRNO.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSSALDOSGENTRNI.LIQSOLOUNACTA }
     * 
     */
    public Ejecutar.ITRCONSSALDOSGENTRNI.LIQSOLOUNACTA createEjecutarITRCONSSALDOSGENTRNILIQSOLOUNACTA() {
        return new Ejecutar.ITRCONSSALDOSGENTRNI.LIQSOLOUNACTA();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSSALDOSGENTRNI.SDSDOE }
     * 
     */
    public Ejecutar.ITRCONSSALDOSGENTRNI.SDSDOE createEjecutarITRCONSSALDOSGENTRNISDSDOE() {
        return new Ejecutar.ITRCONSSALDOSGENTRNI.SDSDOE();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSSALDOSGENTRNI.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRCONSSALDOSGENTRNI.STDTRNIPARMV createEjecutarITRCONSSALDOSGENTRNISTDTRNIPARMV() {
        return new Ejecutar.ITRCONSSALDOSGENTRNI.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSSALDOSGENTRNI.NUMTARJETAV }
     * 
     */
    public Ejecutar.ITRCONSSALDOSGENTRNI.NUMTARJETAV createEjecutarITRCONSSALDOSGENTRNINUMTARJETAV() {
        return new Ejecutar.ITRCONSSALDOSGENTRNI.NUMTARJETAV();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CONS_SALDOS_GEN_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}