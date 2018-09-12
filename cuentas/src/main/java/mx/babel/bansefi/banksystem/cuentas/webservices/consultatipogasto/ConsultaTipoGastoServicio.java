package mx.babel.bansefi.banksystem.cuentas.webservices.consultatipogasto;

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
 * 2016-02-18T18:22:16.232-06:00
 * Generated source version: 2.7.14.redhat-1
 * 
 */
@WebServiceClient(name = "TR_YF_GASTO_HST_CNS_TRN", 
                  wsdlLocation = "",
                  targetNamespace = "") 
public class ConsultaTipoGastoServicio extends Service {

    public final static URL WSDL_LOCATION;

 
    
    public final static QName SERVICE = new QName(ServicioWebConstants.TARGETNAMESPACE_TRN + "TR_YF_GASTO_HST_CNS_TRN", "TR_YF_GASTO_HST_CNS_TRN");
    public final static QName SOAPPORT = new QName(ServicioWebConstants.TARGETNAMESPACE_TRN + "TR_YF_GASTO_HST_CNS_TRN", "SoapPort");

    static {
        URL url = null;
        try {
            url = new URL(ServicioWebConstants.URL_WSDL+"TR_YF_GASTO_HST_CNS_TRN/WSDL");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(ConsultaTipoGastoServicio.class.getName())
                .log(java.util.logging.Level.INFO, 
                		"Can not initialize the default wsdl from {0}", ServicioWebConstants.URL_WSDL + "TR_YF_GASTO_HST_CNS_TRN");
        }
        WSDL_LOCATION = url;
    }

    public ConsultaTipoGastoServicio(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ConsultaTipoGastoServicio(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ConsultaTipoGastoServicio() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ConsultaTipoGastoServicio(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ConsultaTipoGastoServicio(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ConsultaTipoGastoServicio(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
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
