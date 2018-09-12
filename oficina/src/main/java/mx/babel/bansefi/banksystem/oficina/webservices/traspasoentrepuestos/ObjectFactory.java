
package mx.babel.bansefi.banksystem.oficina.webservices.traspasoentrepuestos;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.oficina.webservices.traspasoentrepuestos package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_TRASPASO_ACTU_SALDOS_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.oficina.webservices.traspasoentrepuestos
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
     * Create an instance of {@link ResponseBansefi.OTRTRASPASOACTUSALDOS }
     * 
     */
    public ResponseBansefi.OTRTRASPASOACTUSALDOS createResponseBansefiOTRTRASPASOACTUSALDOS() {
        return new ResponseBansefi.OTRTRASPASOACTUSALDOS();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRTRASPASOACTUSALDOS }
     * 
     */
    public Ejecutar.ITRTRASPASOACTUSALDOS createEjecutarITRTRASPASOACTUSALDOS() {
        return new Ejecutar.ITRTRASPASOACTUSALDOS();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRTRASPASOACTUSALDOS.VVVTRASPASOTNTNDP }
     * 
     */
    public Ejecutar.ITRTRASPASOACTUSALDOS.VVVTRASPASOTNTNDP createEjecutarITRTRASPASOACTUSALDOSVVVTRASPASOTNTNDP() {
        return new Ejecutar.ITRTRASPASOACTUSALDOS.VVVTRASPASOTNTNDP();
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
     * Create an instance of {@link ResponseBansefi.OTRTRASPASOACTUSALDOS.STDMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRTRASPASOACTUSALDOS.STDMSJPARMV createResponseBansefiOTRTRASPASOACTUSALDOSSTDMSJPARMV() {
        return new ResponseBansefi.OTRTRASPASOACTUSALDOS.STDMSJPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRTRASPASOACTUSALDOS.STDAPPLPARMV }
     * 
     */
    public Ejecutar.ITRTRASPASOACTUSALDOS.STDAPPLPARMV createEjecutarITRTRASPASOACTUSALDOSSTDAPPLPARMV() {
        return new Ejecutar.ITRTRASPASOACTUSALDOS.STDAPPLPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRTRASPASOACTUSALDOS.VVVTRASPASOTNTNDP.TNORIGEN }
     * 
     */
    public Ejecutar.ITRTRASPASOACTUSALDOS.VVVTRASPASOTNTNDP.TNORIGEN createEjecutarITRTRASPASOACTUSALDOSVVVTRASPASOTNTNDPTNORIGEN() {
        return new Ejecutar.ITRTRASPASOACTUSALDOS.VVVTRASPASOTNTNDP.TNORIGEN();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRTRASPASOACTUSALDOS.VVVTRASPASOTNTNDP.TNDESTINO }
     * 
     */
    public Ejecutar.ITRTRASPASOACTUSALDOS.VVVTRASPASOTNTNDP.TNDESTINO createEjecutarITRTRASPASOACTUSALDOSVVVTRASPASOTNTNDPTNDESTINO() {
        return new Ejecutar.ITRTRASPASOACTUSALDOS.VVVTRASPASOTNTNDP.TNDESTINO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_TRASPASO_ACTU_SALDOS_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}