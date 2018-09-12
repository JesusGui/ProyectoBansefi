package mx.babel.bansefi.banksystem.response.altarelacioncuentacuenta;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.7.7.redhat-1
 * 2015-05-11T18:34:37.211-05:00
 * Generated source version: 2.7.7.redhat-1
 * 
 */
@WebServiceClient(name = "TR_ALTA_RX_PANT_TRN", 
                  wsdlLocation = "http://localhost:8080/wsServer/TR_ALTA_RX_PANT_TRN.wsdl",
                  targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_ALTA_RX_PANT_TRN") 
public class AltaRelacionCuentaCuentaServicio extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://BansefiNSS/WebServicesNSS/TR_ALTA_RX_PANT_TRN", "TR_ALTA_RX_PANT_TRN");
    public final static QName SOAPPORT = new QName("http://BansefiNSS/WebServicesNSS/TR_ALTA_RX_PANT_TRN", "SoapPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/wsServer/TR_ALTA_RX_PANT_TRN.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(AltaRelacionCuentaCuentaServicio.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://localhost:8080/wsServer/TR_ALTA_RX_PANT_TRN.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public AltaRelacionCuentaCuentaServicio(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public AltaRelacionCuentaCuentaServicio(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public AltaRelacionCuentaCuentaServicio() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public AltaRelacionCuentaCuentaServicio(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public AltaRelacionCuentaCuentaServicio(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public AltaRelacionCuentaCuentaServicio(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns SoapPort
     */
    @WebEndpoint(name = "SoapPort")
    public SoapPort getSoapPort() {
        return super.getPort(SOAPPORT, SoapPort.class);
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
        return super.getPort(SOAPPORT, SoapPort.class, features);
    }

}
