package mx.babel.bansefi.banksystem.base.webservices.validaidinterna;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

import mx.babel.arq.comun.constants.ServicioWebConstants;

/**
 * This class was generated by Apache CXF 2.7.7.redhat-1
 * 2015-06-15T20:57:40.255-05:00
 * Generated source version: 2.7.7.redhat-1
 * 
 */
@WebServiceClient(name = "TR_CONS_MINIMA_PERSONA_TRN", 
                  wsdlLocation = "",
                  targetNamespace = "") 
public class ValidaIdInternaServicio extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName(ServicioWebConstants.TARGETNAMESPACE_TRN + "TR_CONS_MINIMA_PERSONA_TRN", "TR_CONS_MINIMA_PERSONA_TRN");
    public final static QName SoapPort = new QName(ServicioWebConstants.TARGETNAMESPACE_TRN + "TR_CONS_MINIMA_PERSONA_TRN", "SoapPort");
    static {
        URL url = null;
        try {
            url = new URL(ServicioWebConstants.URL_WSDL+"TR_CONS_MINIMA_PERSONA_TRN/WSDL");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(ValidaIdInternaServicio.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", ServicioWebConstants.URL_WSDL+"TR_CONS_MINIMA_PERSONA_TRN/WSDL");
        }
        WSDL_LOCATION = url;
    }

    public ValidaIdInternaServicio(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ValidaIdInternaServicio(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ValidaIdInternaServicio() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ValidaIdInternaServicio(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ValidaIdInternaServicio(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ValidaIdInternaServicio(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns SoapPort
     */
    @WebEndpoint(name = "SoapPort")
    public SoapPort getSoapPort() {
        return super.getPort(SoapPort, SoapPort.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SoapPort
     */
    @WebEndpoint(name = "SoapPort")
    public SoapPort getSoapPort(WebServiceFeature... features) {
        return super.getPort(SoapPort, SoapPort.class, features);
    }

}
