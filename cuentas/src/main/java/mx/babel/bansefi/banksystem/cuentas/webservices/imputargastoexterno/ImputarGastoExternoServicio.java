package mx.babel.bansefi.banksystem.cuentas.webservices.imputargastoexterno;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

import mx.babel.arq.comun.constants.ServicioWebConstants;

/**
 * This class was generated by Apache CXF 2.7.14.redhat-1
 * 2016-02-24T16:30:46.606-06:00
 * Generated source version: 2.7.14.redhat-1
 * 
 */
@WebServiceClient(name = "TR_AC_IMPUTAR_GASTO_EXT_TRN", 
                  wsdlLocation = "",
                  targetNamespace = "") 
public class ImputarGastoExternoServicio extends Service {

    public final static URL WSDL_LOCATION;

    
    public final static QName SERVICE = new QName(ServicioWebConstants.TARGETNAMESPACE_TRN + "TR_AC_IMPUTAR_GASTO_EXT_TRN", "TR_AC_IMPUTAR_GASTO_EXT_TRN");
    public final static QName SoapPort = new QName(ServicioWebConstants.TARGETNAMESPACE_TRN + "TR_AC_IMPUTAR_GASTO_EXT_TRN", "SoapPort");

    
    static {
        URL url = null;
        try {
           
            
            url = new URL(ServicioWebConstants.URL_WSDL+"TR_AC_IMPUTAR_GASTO_EXT_TRN/WSDL");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(ImputarGastoExternoServicio.class.getName())
                .log(java.util.logging.Level.INFO, 
                		 "Can not initialize the default wsdl from {0}", ServicioWebConstants.URL_WSDL+"TR_AC_IMPUTAR_GASTO_EXT_TRN/WSDL");
        }
        WSDL_LOCATION = url;
    }

    public ImputarGastoExternoServicio(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ImputarGastoExternoServicio(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ImputarGastoExternoServicio() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ImputarGastoExternoServicio(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ImputarGastoExternoServicio(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ImputarGastoExternoServicio(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
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