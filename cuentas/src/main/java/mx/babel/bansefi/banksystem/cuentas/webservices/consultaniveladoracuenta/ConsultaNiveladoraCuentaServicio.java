package mx.babel.bansefi.banksystem.cuentas.webservices.consultaniveladoracuenta;

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
 * 2015-09-07T18:44:32.954-05:00
 * Generated source version: 2.7.7.redhat-1
 *
 */
@WebServiceClient(name = "TR_CONSU_NIVEL_AC_TRN",
                  wsdlLocation = "",
                  targetNamespace = "")
public class ConsultaNiveladoraCuentaServicio extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName(ServicioWebConstants.TARGETNAMESPACE_TRN + "TR_CONSU_NIVEL_AC_TRN", "TR_CONSU_NIVEL_AC_TRN");
    public final static QName SOAPPORT = new QName(ServicioWebConstants.TARGETNAMESPACE_TRN + "TR_CONSU_NIVEL_AC_TRN", "SoapPort");
    static {
        URL url = null;
        try {
            url = new URL(ServicioWebConstants.URL_WSDL+"TR_CONSU_NIVEL_AC_TRN/WSDL");
        } catch (final MalformedURLException e) {
            java.util.logging.Logger.getLogger(ConsultaNiveladoraCuentaServicio.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", ServicioWebConstants.URL_WSDL+"TR_CONSU_NIVEL_AC_TRN/WSDL");
        }
        WSDL_LOCATION = url;
    }

    public ConsultaNiveladoraCuentaServicio(final URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ConsultaNiveladoraCuentaServicio(final URL wsdlLocation, final QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ConsultaNiveladoraCuentaServicio() {
        super(WSDL_LOCATION, SERVICE);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ConsultaNiveladoraCuentaServicio(final WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ConsultaNiveladoraCuentaServicio(final URL wsdlLocation, final WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ConsultaNiveladoraCuentaServicio(final URL wsdlLocation, final QName serviceName, final WebServiceFeature ... features) {
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
    public SoapPort getSoapPort(final WebServiceFeature... features) {
        return super.getPort(SOAPPORT, SoapPort.class, features);
    }

}
