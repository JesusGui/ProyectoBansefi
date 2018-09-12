package mx.babel.arq.comun.utils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.net.Authenticator;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.SocketException;
import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.handler.Handler;

import mx.babel.arq.comun.constants.ServicioWebConstants;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.handlers.HandlerSOAP;
import mx.babel.arq.sesion.utils.ContextoUtils;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Clase para invocar a servicios web
 *
 * @author gerard.chavez
 *
 */
@Component
public class ServicioWebUtils implements Serializable {

	private static final long serialVersionUID = -8910707014646270994L;

	@Autowired
	ContextoUtils contexto;

	private static final Logger LOGGER = LogManager.getLogger(ServicioWebUtils.class.getName());

	private static String NAME_SRV_NOTIF = "mx.babel.bansefi.banksystem.base.webservices.mensajes.NotificacionesServicio";
	private static String NAME_SRV_LOGIN = "mx.babel.bansefi.banksystem.base.webservices.login.LoginServicio";
	
	private final static String NAME_SRV_LOGONNSF = "LOGONNSF";
	private final static String TRN_SUFFIX = "_TRN";
	private final static String WSDL_SUFFIX = "/WSDL";
	
	private final static String ATTR_WSDL = "wsdl";
	private final static String ATTR_TARGETNAMESPACE = "targetnamespace";
	
	/**
	 * Método de utileria para mediante reflección invocar a un servicio web
	 *
	 * @param servicio
	 *            clase del servicio a invocar
	 * @param params
	 *            varargs de parámetros a pasar al servicio web
	 * @return objeto de retorno por parte del servicio web
	 */
	@SuppressWarnings("rawtypes")
	public Object ejecutarWS(final Class servicio, final Object... params)
			throws NoControlableException {
		Object resultado = null;
		try {
			Authenticator.setDefault(new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					if (contexto != null && contexto.existeContexto()) {
						return new PasswordAuthentication(contexto.getId(),
								contexto.getPwd().toCharArray());
					} else {
						return new PasswordAuthentication(
								ProveedorMensajeSpringUtils
										.getValorConfiguracion("catalogo.usuario"),
								ProveedorMensajeSpringUtils
										.getValorConfiguracion(
												"catalogo.password")
										.toCharArray());
					}
				}
			});
			
			WebServiceClient ann = (WebServiceClient) servicio.getAnnotation(WebServiceClient.class);
			String serviceName = ann.name();
			 
			URL wsdlUrl;
			QName qname;
			
			JSONObject servicioExcepcional = verificaServicioExcepcional(servicio.getSimpleName().toLowerCase());
				
			if(servicioExcepcional != null){
				wsdlUrl = new URL(getJSONAttr(servicioExcepcional,ATTR_WSDL) + serviceName + WSDL_SUFFIX);
				qname = new QName(getJSONAttr(servicioExcepcional,ATTR_TARGETNAMESPACE) + serviceName,ann.name());
			}else if(NAME_SRV_LOGONNSF.equals(serviceName)){
				wsdlUrl = new URL(ServicioWebConstants.WSDL_LOGON + serviceName + WSDL_SUFFIX);
				qname = new QName(ServicioWebConstants.TARGETNAMESPACE_LOGON + serviceName,ann.name());
			}else if(serviceName.endsWith(TRN_SUFFIX)){
				wsdlUrl = new URL(ServicioWebConstants.URL_WSDL + serviceName + WSDL_SUFFIX);
				qname = new QName(ServicioWebConstants.TARGETNAMESPACE_TRN + serviceName,ann.name());
			}else{
				wsdlUrl = new URL(ServicioWebConstants.URL_WSDL_WS + serviceName+WSDL_SUFFIX);
				qname = new QName(ServicioWebConstants.TARGETNAMESPACE_WS + serviceName,ann.name());
			}

			
			final Object cliente = servicio.getDeclaredConstructor(URL.class, QName.class).newInstance(wsdlUrl,qname);
			final Object o = MethodUtils.invokeExactMethod(cliente,"getSoapPort", null);

			// Establecer timeout para invocar servicio web
			final Client client = ClientProxy.getClient(o);
			final HTTPConduit http = (HTTPConduit) client.getConduit();

			final HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
			httpClientPolicy.setConnectionTimeout(Long.valueOf(ProveedorMensajeSpringUtils.getValorConfiguracion("connection.timeout"))); // 0 para tiempo ilimitado
			httpClientPolicy.setReceiveTimeout(Long.valueOf(ProveedorMensajeSpringUtils.getValorConfiguracion("response.timeout")));
			http.setClient(httpClientPolicy);

			final String entorno = ProveedorMensajeSpringUtils.getValorConfiguracion("ambiente");
			final String endpoint = ProveedorMensajeSpringUtils.getValoresServiciosWeb(servicio.getSimpleName().toLowerCase() + ".url." + entorno);
			final BindingProvider bp = (BindingProvider) o;
			bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);

			Object params2[];

			if (NAME_SRV_NOTIF.equalsIgnoreCase(servicio.getName()) || NAME_SRV_LOGIN.equalsIgnoreCase(servicio.getName())) {
				bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY,params[0].toString());
				bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY,params[1].toString());
				params2 = params;
			} else{
				bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, contexto.getId());
				bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, contexto.getPwd());
				params2 = new Object[params.length + 3];
				params2[0] = contexto.getId();
				params2[1] = contexto.getPwd();
				params2[2] = contexto.getIp();
				for (int i = 0; i < params.length; i++){
					params2[i + 3] = params[i];
				}
			}

			// Create a new list since: If the returned chain is modified a call
			// to setHandlerChain is required to configure the binding instance
			// with the new chain.
			final List<Handler> handlerChain = bp.getBinding().getHandlerChain();
			handlerChain.add(new HandlerSOAP());
			bp.getBinding().setHandlerChain(handlerChain);
			resultado = MethodUtils.invokeMethod(o, "ejecutar", params2);
		} catch(JSONException e){
			LOGGER.error("Error al invocar servicio excepcional web, no se ha localizado definición completa del servicio en archivo de configuración.", e);
			throw new NoControlableException("Error al invocar servicio excepcional web, no se ha localizado definición completa del servicio en archivo de configuración..", e);
		} catch (InstantiationException | IllegalAccessException | MalformedURLException e) {
			LOGGER.error("Error al invocar servicio web, no se ha podido instanciar alguna de las clases necesarias.", e);
			throw new NoControlableException("Error al invocar servicio web, no se ha podido instanciar alguna de las clases necesarias.", e);
		} catch (final NoSuchMethodException e) {
			LOGGER.error("Error al invocar servicio web, no se tiene el método Ejecutar", e);
			throw new NoControlableException("Error al invocar servicio web, no se tiene el método Ejecutar", e);
		} catch (final InvocationTargetException e) {
			final Throwable throwable = e.getCause();
			if (throwable instanceof SocketException || throwable instanceof  WebServiceException || throwable instanceof ConnectException) {
				if (throwable.getMessage()!=null && throwable.getMessage().indexOf("IP HEADER-LOGON INCORRECTA")!=-1)
					throw new NoControlableException(throwable.getMessage(),"");
				else
					throw new NoControlableException("No hay conexión con la base de datos. Por favor contacte con su centro de soporte.", "");
			}
			LOGGER.error("Error al invocar servicio web, no se ha podido invocar el método.", e);
			throw new NoControlableException("Error al invocar servicio web, no se ha podido invocar el método.", e);
		} catch (final WebServiceException wse) {
			LOGGER.error("Error al invocar servicio web, no se ha podido obtener la conexión.",	wse);
			throw new NoControlableException("No hay conexión con la base de datos. Por favor contacte con su centro de soporte.", "");
		}
		return resultado;
	}
	
	private static JSONArray getServiciosExcepcionales(){
		String json = ProveedorMensajeSpringUtils.getValorConfiguracion("servicios.excepciones");
		JSONArray serviciosExcepcionales = null;
		try {
			serviciosExcepcionales = new JSONArray(json);
		} catch (JSONException | NullPointerException e) {
		}
		return serviciosExcepcionales;
	}
	
	private JSONObject verificaServicioExcepcional(String nombreServicio){
		JSONObject servicio = null;
		JSONArray array = getServiciosExcepcionales();
		if(array != null){
			for(int i = 0;i < array.length();i++){
				JSONObject servicioEvaluado = null;
				String nombre = "";
				try {
					servicioEvaluado = array.getJSONObject(i);
					nombre = servicioEvaluado.getString("nombre");
				} catch (JSONException e) {
				}
				if(servicioEvaluado != null){
					if(nombreServicio.equals(nombre.toLowerCase())){
						servicio = servicioEvaluado;
					}
				}
			}
		}
		return servicio;
	}
	
	private String getJSONAttr(JSONObject servicioExcepcional, String name) throws JSONException{
		String value= "";
		value = servicioExcepcional.getString(name);
		return value;
	}

}