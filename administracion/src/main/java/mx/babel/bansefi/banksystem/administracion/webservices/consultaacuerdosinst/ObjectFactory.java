
package mx.babel.bansefi.banksystem.administracion.webservices.consultaacuerdosinst;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bansefinss.webservicesnss.tr_cons_ac_inst_ofic_trn package. 
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

    private final static QName EJECUTAR_RESULT_QNAME = new QName(mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CONS_AC_INST_OFIC_TRN", "EjecutarResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bansefinss.webservicesnss.tr_cons_ac_inst_ofic_trn
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
     * Create an instance of {@link ResponseBansefi.OTRCONSACINSTOFICTRN }
     * 
     */
    public ResponseBansefi.OTRCONSACINSTOFICTRN createResponseBansefiOTRCONSACINSTOFICTRN() {
        return new ResponseBansefi.OTRCONSACINSTOFICTRN();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCONSACINSTOFICTRN.TRCONSACINSTOFICEVT }
     * 
     */
    public ResponseBansefi.OTRCONSACINSTOFICTRN.TRCONSACINSTOFICEVT createResponseBansefiOTRCONSACINSTOFICTRNTRCONSACINSTOFICEVT() {
        return new ResponseBansefi.OTRCONSACINSTOFICTRN.TRCONSACINSTOFICEVT();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCONSACINSTOFICTRN.TRCONSACINSTOFICEVT.UOACINSTLAV }
     * 
     */
    public ResponseBansefi.OTRCONSACINSTOFICTRN.TRCONSACINSTOFICEVT.UOACINSTLAV createResponseBansefiOTRCONSACINSTOFICTRNTRCONSACINSTOFICEVTUOACINSTLAV() {
        return new ResponseBansefi.OTRCONSACINSTOFICTRN.TRCONSACINSTOFICEVT.UOACINSTLAV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSACINSTOFICTRN }
     * 
     */
    public Ejecutar.ITRCONSACINSTOFICTRN createEjecutarITRCONSACINSTOFICTRN() {
        return new Ejecutar.ITRCONSACINSTOFICTRN();
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
     * Create an instance of {@link ResponseBansefi.OTRCONSACINSTOFICTRN.STDTRNMSJPARMV }
     * 
     */
    public ResponseBansefi.OTRCONSACINSTOFICTRN.STDTRNMSJPARMV createResponseBansefiOTRCONSACINSTOFICTRNSTDTRNMSJPARMV() {
        return new ResponseBansefi.OTRCONSACINSTOFICTRN.STDTRNMSJPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCONSACINSTOFICTRN.STDTRNOPARMV }
     * 
     */
    public ResponseBansefi.OTRCONSACINSTOFICTRN.STDTRNOPARMV createResponseBansefiOTRCONSACINSTOFICTRNSTDTRNOPARMV() {
        return new ResponseBansefi.OTRCONSACINSTOFICTRN.STDTRNOPARMV();
    }

    /**
     * Create an instance of {@link ResponseBansefi.OTRCONSACINSTOFICTRN.TRCONSACINSTOFICEVT.UOACINSTLAV.UOACINSTLGV }
     * 
     */
    public ResponseBansefi.OTRCONSACINSTOFICTRN.TRCONSACINSTOFICEVT.UOACINSTLAV.UOACINSTLGV createResponseBansefiOTRCONSACINSTOFICTRNTRCONSACINSTOFICEVTUOACINSTLAVUOACINSTLGV() {
        return new ResponseBansefi.OTRCONSACINSTOFICTRN.TRCONSACINSTOFICEVT.UOACINSTLAV.UOACINSTLGV();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSACINSTOFICTRN.TRCONSACINSTOFICEVT }
     * 
     */
    public Ejecutar.ITRCONSACINSTOFICTRN.TRCONSACINSTOFICEVT createEjecutarITRCONSACINSTOFICTRNTRCONSACINSTOFICEVT() {
        return new Ejecutar.ITRCONSACINSTOFICTRN.TRCONSACINSTOFICEVT();
    }

    /**
     * Create an instance of {@link Ejecutar.ITRCONSACINSTOFICTRN.STDTRNIPARMV }
     * 
     */
    public Ejecutar.ITRCONSACINSTOFICTRN.STDTRNIPARMV createEjecutarITRCONSACINSTOFICTRNSTDTRNIPARMV() {
        return new Ejecutar.ITRCONSACINSTOFICTRN.STDTRNIPARMV();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = mx.babel.arq.comun.constants.ServicioWebConstants.SOAP_PORT_URL_TRN + "TR_CONS_AC_INST_OFIC_TRN", name = "EjecutarResult")
    public JAXBElement<EjecutarResult> createEjecutarResult(EjecutarResult value) {
        return new JAXBElement<EjecutarResult>(EJECUTAR_RESULT_QNAME, EjecutarResult.class, null, value);
    }

}
