package mx.babel.bansefi.banksystem.response.listacentros;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.7.7.redhat-1
 * 2015-04-17T12:56:58.819-05:00
 * Generated source version: 2.7.7.redhat-1
 * 
 */
@WebServiceClient(name = "TR_LIST_NOMB_CENT_TRN", 
                  wsdlLocation = "http://localhost:8080/wsServer/TR_LIST_NOMB_CENT_TRN.wsdl",
                  targetNamespace = "http://BansefiNSS/WebServicesNSS/TR_LIST_NOMB_CENT_TRN") 
public class TRLISTNOMBCENTTRN extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://BansefiNSS/WebServicesNSS/TR_LIST_NOMB_CENT_TRN", "TR_LIST_NOMB_CENT_TRN");
    public final static QName SoapPort = new QName("http://BansefiNSS/WebServicesNSS/TR_LIST_NOMB_CENT_TRN", "SoapPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/wsServer/TR_LIST_NOMB_CENT_TRN.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(TRLISTNOMBCENTTRN.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://localhost:8080/wsServer/TR_LIST_NOMB_CENT_TRN.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public TRLISTNOMBCENTTRN(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public TRLISTNOMBCENTTRN(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public TRLISTNOMBCENTTRN() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public TRLISTNOMBCENTTRN(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public TRLISTNOMBCENTTRN(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public TRLISTNOMBCENTTRN(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
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