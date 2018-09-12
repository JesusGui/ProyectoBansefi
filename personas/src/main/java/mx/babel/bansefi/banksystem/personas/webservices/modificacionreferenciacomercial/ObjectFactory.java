
package mx.babel.bansefi.banksystem.personas.webservices.modificacionreferenciacomercial;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.babel.bansefi.banksystem.personas.webservices.modificacionreferenciacomercial package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_MODI_ORG_REF_COM_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.babel.bansefi.banksystem.personas.webservices.modificacionreferenciacomercial
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
     * Create an instance of {@link ResponseBansefi.OPEMODIORGREFCOMTRN }
     * 
     */
    public ResponseBansefi.OPEMODIORGREFCOMTRN createResponseBansefiOPEMODIORGREFCOMTRN() {
        return new ResponseBansefi.OPEMODIORGREFCOMTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.IPEMODIORGREFCOMTRN }
     * 
     */
    public Ejecutar.IPEMODIORGREFCOMTRN createEjecutarIPEMODIORGREFCOMTRN() {
        return new Ejecutar.IPEMODIORGREFCOMTRN();
    }

    /**
     * Create an instance of {@link Ejecutar.IPEMODIORGREFCOMTRN.PEMODIORGREFCOMEVTY }
     * 
     */
    public Ejecutar.IPEMODIORGREFCOMTRN.PEMODIORGREFCOMEVTY createEjecutarIPEMODIORGREFCOMTRNPEMODIORGREFCOMEVTY() {
        return new Ejecutar.IPEMODIORGREFCOMTRN.PEMODIORGREFCOMEVTY();
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
     * Create an instance of {@link ResponseBansefi.OPEMODIORGREFCOMTRN.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OPEMODIORGREFCOMTRN.STDTRNMSJPARMV createResponseBansefiOPEMODIORGREFCOMTRNSTDTRNMSJPARMV() {
        return new ResponseBansefi.OPEMODIORGREFCOMTRN.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OPEMODIORGREFCOMTRN.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OPEMODIORGREFCOMTRN.STDTRNOPARMV createResponseBansefiOPEMODIORGREFCOMTRNSTDTRNOPARMV() {
        return new ResponseBansefi.OPEMODIORGREFCOMTRN.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.IPEMODIORGREFCOMTRN.STDTRNIPARMV }
     * 
     */
    public Ejecutar.IPEMODIORGREFCOMTRN.STDTRNIPARMV createEjecutarIPEMODIORGREFCOMTRNSTDTRNIPARMV() {
        return new Ejecutar.IPEMODIORGREFCOMTRN.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link Ejecutar.IPEMODIORGREFCOMTRN.PEMODIORGREFCOMEVTY.PEPERSRLREFCOMP }
     * 
     */
    public Ejecutar.IPEMODIORGREFCOMTRN.PEMODIORGREFCOMEVTY.PEPERSRLREFCOMP createEjecutarIPEMODIORGREFCOMTRNPEMODIORGREFCOMEVTYPEPERSRLREFCOMP() {
        return new Ejecutar.IPEMODIORGREFCOMTRN.PEMODIORGREFCOMEVTY.PEPERSRLREFCOMP();
    }

    /**
     * Create an instance of {@link Ejecutar.IPEMODIORGREFCOMTRN.PEMODIORGREFCOMEVTY.PEPERSRLREFCOME }
     * 
     */
    public Ejecutar.IPEMODIORGREFCOMTRN.PEMODIORGREFCOMEVTY.PEPERSRLREFCOME createEjecutarIPEMODIORGREFCOMTRNPEMODIORGREFCOMEVTYPEPERSRLREFCOME() {
        return new Ejecutar.IPEMODIORGREFCOMTRN.PEMODIORGREFCOMEVTY.PEPERSRLREFCOME();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "PE_MODI_ORG_REF_COM_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
