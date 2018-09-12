
package mx.babel.bansefi.banksystem.personas.webservices.bajaotrotelfaxpersona;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.personas.webservices.bajaotrotelfaxpersona package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_DR_BAJA_DR_ELCTR_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.personas.webservices.bajaotrotelfaxpersona
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
     * Create an instance of {@link ResponseBansefi.OTRDRBAJADRELCTRTRN }
     * 
     */
    public ResponseBansefi.OTRDRBAJADRELCTRTRN createResponseBansefiOTRDRBAJADRELCTRTRN() {
        return new ResponseBansefi.OTRDRBAJADRELCTRTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRDRBAJADRELCTRTRN }
     * 
     */
    public Ejecutar.ITRDRBAJADRELCTRTRN createEjecutarITRDRBAJADRELCTRTRN() {
        return new Ejecutar.ITRDRBAJADRELCTRTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRDRBAJADRELCTRTRN.TRDRBAJADRELCTREVTY }
     * 
     */
    public Ejecutar.ITRDRBAJADRELCTRTRN.TRDRBAJADRELCTREVTY createEjecutarITRDRBAJADRELCTRTRNTRDRBAJADRELCTREVTY() {
        return new Ejecutar.ITRDRBAJADRELCTRTRN.TRDRBAJADRELCTREVTY();
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
     * Create an instance of {@link ResponseBansefi.OTRDRBAJADRELCTRTRN.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRDRBAJADRELCTRTRN.STDTRNMSJPARMV createResponseBansefiOTRDRBAJADRELCTRTRNSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRDRBAJADRELCTRTRN.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRDRBAJADRELCTRTRN.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRDRBAJADRELCTRTRN.STDTRNOPARMV createResponseBansefiOTRDRBAJADRELCTRTRNSTDTRNOPARMV() {
        return new ResponseBansefi.OTRDRBAJADRELCTRTRN.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRDRBAJADRELCTRTRN.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRDRBAJADRELCTRTRN.STDTRNIPARMV createEjecutarITRDRBAJADRELCTRTRNSTDTRNIPARMV() {
        return new Ejecutar.ITRDRBAJADRELCTRTRN.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRDRBAJADRELCTRTRN.TRDRBAJADRELCTREVTY.DRELCTRP }
     * 
     */
    public Ejecutar.ITRDRBAJADRELCTRTRN.TRDRBAJADRELCTREVTY.DRELCTRP createEjecutarITRDRBAJADRELCTRTRNTRDRBAJADRELCTREVTYDRELCTRP() {
        return new Ejecutar.ITRDRBAJADRELCTRTRN.TRDRBAJADRELCTREVTY.DRELCTRP();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRDRBAJADRELCTRTRN.TRDRBAJADRELCTREVTY.PEPERSP }
     * 
     */
    public Ejecutar.ITRDRBAJADRELCTRTRN.TRDRBAJADRELCTREVTY.PEPERSP createEjecutarITRDRBAJADRELCTRTRNTRDRBAJADRELCTREVTYPEPERSP() {
        return new Ejecutar.ITRDRBAJADRELCTRTRN.TRDRBAJADRELCTREVTY.PEPERSP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_DR_BAJA_DR_ELCTR_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
