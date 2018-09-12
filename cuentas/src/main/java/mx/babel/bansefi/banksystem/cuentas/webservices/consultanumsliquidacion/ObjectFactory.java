
package mx.babel.bansefi.banksystem.cuentas.webservices.consultanumsliquidacion;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.cuentas.webservices.consultanumsliquidacion package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_LIQ_CONSULTAR_NUMEROS_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.cuentas.webservices.consultanumsliquidacion
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
     * Create an instance of {@link ResponseBansefi.OTRLIQCONSULTARNUMEROS }
     * 
     */
    public ResponseBansefi.OTRLIQCONSULTARNUMEROS createResponseBansefiOTRLIQCONSULTARNUMEROS() {
        return new ResponseBansefi.OTRLIQCONSULTARNUMEROS();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS }
     * 
     */
    public ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS createResponseBansefiOTRLIQCONSULTARNUMEROSTRLIQCONSULTARNUMEROS() {
        return new ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQACUMNUMEROSLST }
     * 
     */
    public ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQACUMNUMEROSLST createResponseBansefiOTRLIQCONSULTARNUMEROSTRLIQCONSULTARNUMEROSLIQACUMNUMEROSLST() {
        return new ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQACUMNUMEROSLST();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQNUMEROSLST }
     * 
     */
    public ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQNUMEROSLST createResponseBansefiOTRLIQCONSULTARNUMEROSTRLIQCONSULTARNUMEROSLIQNUMEROSLST() {
        return new ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQNUMEROSLST();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQNUMEROSLST.LIQNUMEROSV }
     * 
     */
    public ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQNUMEROSLST.LIQNUMEROSV createResponseBansefiOTRLIQCONSULTARNUMEROSTRLIQCONSULTARNUMEROSLIQNUMEROSLSTLIQNUMEROSV() {
        return new ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQNUMEROSLST.LIQNUMEROSV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRLIQCONSULTARNUMEROS }
     * 
     */
    public Ejecutar.ITRLIQCONSULTARNUMEROS createEjecutarITRLIQCONSULTARNUMEROS() {
        return new Ejecutar.ITRLIQCONSULTARNUMEROS();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS }
     * 
     */
    public Ejecutar.ITRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS createEjecutarITRLIQCONSULTARNUMEROSTRLIQCONSULTARNUMEROS() {
        return new Ejecutar.ITRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.HLINFADICLST }
     * 
     */
    public Ejecutar.ITRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.HLINFADICLST createEjecutarITRLIQCONSULTARNUMEROSTRLIQCONSULTARNUMEROSHLINFADICLST() {
        return new Ejecutar.ITRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.HLINFADICLST();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.HLSDOLST }
     * 
     */
    public Ejecutar.ITRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.HLSDOLST createEjecutarITRLIQCONSULTARNUMEROSTRLIQCONSULTARNUMEROSHLSDOLST() {
        return new Ejecutar.ITRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.HLSDOLST();
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
     * Create an instance of {@link ResponseBansefi.OTRLIQCONSULTARNUMEROS.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRLIQCONSULTARNUMEROS.STDTRNMSJPARMV createResponseBansefiOTRLIQCONSULTARNUMEROSSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRLIQCONSULTARNUMEROS.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRLIQCONSULTARNUMEROS.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRLIQCONSULTARNUMEROS.STDTRNOPARMV createResponseBansefiOTRLIQCONSULTARNUMEROSSTDTRNOPARMV() {
        return new ResponseBansefi.OTRLIQCONSULTARNUMEROS.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQCONSULTARNUMEROSV }
     * 
     */
    public ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQCONSULTARNUMEROSV createResponseBansefiOTRLIQCONSULTARNUMEROSTRLIQCONSULTARNUMEROSLIQCONSULTARNUMEROSV() {
        return new ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQCONSULTARNUMEROSV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQACUMNUMEROSLST.ACUMULADONUMEROSV }
     * 
     */
    public ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQACUMNUMEROSLST.ACUMULADONUMEROSV createResponseBansefiOTRLIQCONSULTARNUMEROSTRLIQCONSULTARNUMEROSLIQACUMNUMEROSLSTACUMULADONUMEROSV() {
        return new ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQACUMNUMEROSLST.ACUMULADONUMEROSV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQNUMEROSLST.LIQNUMEROSV.LIQNUMAV }
     * 
     */
    public ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQNUMEROSLST.LIQNUMEROSV.LIQNUMAV createResponseBansefiOTRLIQCONSULTARNUMEROSTRLIQCONSULTARNUMEROSLIQNUMEROSLSTLIQNUMEROSVLIQNUMAV() {
        return new ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQNUMEROSLST.LIQNUMEROSV.LIQNUMAV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQNUMEROSLST.LIQNUMEROSV.LIQNUMDV }
     * 
     */
    public ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQNUMEROSLST.LIQNUMEROSV.LIQNUMDV createResponseBansefiOTRLIQCONSULTARNUMEROSTRLIQCONSULTARNUMEROSLIQNUMEROSLSTLIQNUMEROSVLIQNUMDV() {
        return new ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQNUMEROSLST.LIQNUMEROSV.LIQNUMDV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQNUMEROSLST.LIQNUMEROSV.LIQNUMEV }
     * 
     */
    public ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQNUMEROSLST.LIQNUMEROSV.LIQNUMEV createResponseBansefiOTRLIQCONSULTARNUMEROSTRLIQCONSULTARNUMEROSLIQNUMEROSLSTLIQNUMEROSVLIQNUMEV() {
        return new ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQNUMEROSLST.LIQNUMEROSV.LIQNUMEV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRLIQCONSULTARNUMEROS.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRLIQCONSULTARNUMEROS.STDTRNIPARMV createEjecutarITRLIQCONSULTARNUMEROSSTDTRNIPARMV() {
        return new Ejecutar.ITRLIQCONSULTARNUMEROS.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.HLHCOLIQE }
     * 
     */
    public Ejecutar.ITRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.HLHCOLIQE createEjecutarITRLIQCONSULTARNUMEROSTRLIQCONSULTARNUMEROSHLHCOLIQE() {
        return new Ejecutar.ITRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.HLHCOLIQE();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.HLOPCIONV }
     * 
     */
    public Ejecutar.ITRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.HLOPCIONV createEjecutarITRLIQCONSULTARNUMEROSTRLIQCONSULTARNUMEROSHLOPCIONV() {
        return new Ejecutar.ITRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.HLOPCIONV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.HLINFADICLST.HLINFADICV }
     * 
     */
    public Ejecutar.ITRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.HLINFADICLST.HLINFADICV createEjecutarITRLIQCONSULTARNUMEROSTRLIQCONSULTARNUMEROSHLINFADICLSTHLINFADICV() {
        return new Ejecutar.ITRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.HLINFADICLST.HLINFADICV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.HLSDOLST.HLSDOEV }
     * 
     */
    public Ejecutar.ITRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.HLSDOLST.HLSDOEV createEjecutarITRLIQCONSULTARNUMEROSTRLIQCONSULTARNUMEROSHLSDOLSTHLSDOEV() {
        return new Ejecutar.ITRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.HLSDOLST.HLSDOEV();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_LIQ_CONSULTAR_NUMEROS_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
