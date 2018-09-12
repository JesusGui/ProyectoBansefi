package mx.babel.bansefi.banksystem.base.handlers;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.PartialResponseWriter;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import javax.faces.event.PhaseId;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.sesion.contexto.beans.BSContexto;
import mx.babel.bansefi.banksystem.base.backends.login.LogoutBackEnd;
import mx.babel.bansefi.banksystem.base.controllers.ErrorGenericoController;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.application.exceptionhandler.ExceptionInfo;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author Javier.martinnino
 * Clase encargada del desplegado de excepciones propias de la apliación.
 *
 */
public class CustomExceptionHandler extends ExceptionHandlerWrapper {

	private static final Logger LOGGER = LogManager.getLogger(CustomExceptionHandler.class.getName());
	private static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

	private static final String LOGIN_XHTML ="login.xhtml";
	private static final String DASHBOARD_XHTML ="inicio.xhtml";
	private static final String MSG_EXCEPTION ="msgException";

	private final ExceptionHandler wrapped;

	public CustomExceptionHandler(final ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public ExceptionHandler getWrapped() {
	  return wrapped;
	}

	@Override
	public void handle() throws FacesException {
		final Iterator<ExceptionQueuedEvent> iterator = getUnhandledExceptionQueuedEvents().iterator();
		final FacesContext facesContext = FacesContext.getCurrentInstance();
		final RequestContext reqContext = RequestContext.getCurrentInstance();

		ErrorGenericoController errorGenericoController;
		try{
			errorGenericoController = facesContext.getApplication().evaluateExpressionGet(facesContext, "#{errorGenericoController}", ErrorGenericoController.class);
		}catch(final NullPointerException npe){
			//excepcion cuando existe un error de sintáxis en la vista .xhtml
			errorGenericoController = new ErrorGenericoController();
			LOGGER.error("Error al intentar obtener el controller para gestión de errores en la vista.");
		}

		while (iterator.hasNext()) {
		    final ExceptionQueuedEvent event = iterator.next();
		    final ExceptionQueuedEventContext context = (ExceptionQueuedEventContext)event.getSource();
		    final Throwable throwable = context.getException();

		    try {

		    	// always log the exception
		    	final StringBuilder sb = new StringBuilder();
		    	String loginId = "";
		    	 try {
		    	     loginId = "["+((BSContexto) getSession().getAttribute("bsctxt")).getUsuario().getDireccionIp().replace(".","")+
		    	             ((BSContexto) getSession().getAttribute("bsctxt")).getUsuario().getId()+"] - ";
		    	 } catch (IllegalStateException | NullPointerException e) {

		         }
		    	for(final StackTraceElement stElement : throwable.getStackTrace()){
		    	    sb.append( "\n" ).append( loginId ).append(stElement.toString());
		    	}
                LOGGER.error(throwable.getClass() + ": " +  throwable.getMessage() + ": " + throwable.getCause() + sb.toString());

				// Si vienen del metodo init no buscamos si el origen fue una ControlableException o NoControlableException
				Throwable throwable2 = throwable;
				while (!(throwable2.getClass()==mx.babel.arq.comun.exceptions.ControlableException.class) &&
					 !(throwable2.getClass()==mx.babel.arq.comun.exceptions.NoControlableException.class) &&
					 throwable2.getCause() !=null ){
					throwable2 = throwable2.getCause();
				}

				if (throwable2 !=null && (throwable2.getClass()==mx.babel.arq.comun.exceptions.ControlableException.class
						|| throwable2.getClass()==mx.babel.arq.comun.exceptions.NoControlableException.class)){

					ControlableException ce = null;
					NoControlableException nce = null;
					if(throwable2.getClass()==mx.babel.arq.comun.exceptions.ControlableException.class){
						ce = (ControlableException)throwable2;
						errorGenericoController.setMensajeError(ce.getMensajeUsuario());
						errorGenericoController.setMensajeDetalleError(ce.getMensajeDetalle());
					}else if(throwable2.getClass()==mx.babel.arq.comun.exceptions.NoControlableException.class){
						nce = (NoControlableException)throwable2;
						errorGenericoController.setMensajeError(nce.getMensajeUsuario());
						if("".equals(nce.getMensajeDetalle())){
							errorGenericoController.setMensajeDetalleError(nce.getMessage());
						}else{
							errorGenericoController.setMensajeDetalleError(nce.getMensajeDetalle());
						}
						errorGenericoController.setMostrarAceptar(true);
					}

					final HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
					final String url = request.getRequestURL().toString();
					if(url.indexOf(LOGIN_XHTML)!=-1){
						//return to login
						errorGenericoController.setRedireccion(NavegacionEnum.LOGIN.getRuta());
					}else{
						//return to dashboard
						errorGenericoController.setRedireccion(NavegacionEnum.INICIO.getRuta());
					}
					reqContext.execute("PF('dlgExcepcion').show();");
					reqContext.update("dlgExcepcion");
			     }else{
			    	final DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
					errorGenericoController.setMensajeDetalleError("Ha ocurrido un error inesperado en el sistema a las "+
							hourFormat.format(Calendar.getInstance().getTime())+".Por favor, contacte con el equipo de soporte");
					errorGenericoController.setMostrarAceptar(true);
					final HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
					final String url = request.getRequestURL().toString();
					if(url.indexOf(LOGIN_XHTML)!=-1){
						//return to login
						errorGenericoController.setMostrarAceptar(false);
						reqContext.execute("PF('dlgExcepcion').show();");
						reqContext.update("dlgExcepcion");
					}else if(FacesContext.getCurrentInstance().getCurrentPhaseId() == PhaseId.RENDER_RESPONSE && url.indexOf(DASHBOARD_XHTML)!= -1){
						//cant show dashboard, therefore must close sessión and return to login
						final LogoutBackEnd logoutBackEnd = new LogoutBackEnd();
						final BSContexto contexto = ((BSContexto) getSession().getAttribute("bsctxt"));
						logoutBackEnd.ejecutarWS(contexto.getUsuario().getId(),contexto.getUsuario().getEntidad(),contexto.getUsuario().getSucursal(),contexto.getUsuario().getDireccionIp());
						getSession().removeAttribute("bsctxt");
						getSession().invalidate();

						FacesContext.getCurrentInstance().getExternalContext().getSession(true);
						errorGenericoController.setMostrarAceptar(false);
						facesContext.getExternalContext().getSessionMap().put(MSG_EXCEPTION, errorGenericoController);
						final ConfigurableNavigationHandler configurableNavigationHandler = (ConfigurableNavigationHandler) FacesContext
								.getCurrentInstance().getApplication()
								.getNavigationHandler();

						configurableNavigationHandler.performNavigation(NavegacionEnum.LOGIN.getRuta());

					}else if(FacesContext.getCurrentInstance().getCurrentPhaseId() == PhaseId.INVOKE_APPLICATION && url.indexOf(DASHBOARD_XHTML)!= -1){

						errorGenericoController.setMostrarAceptar(false);

						facesContext.getExternalContext().getSessionMap().put(MSG_EXCEPTION, errorGenericoController);
						final ConfigurableNavigationHandler configurableNavigationHandler = (ConfigurableNavigationHandler) FacesContext
								.getCurrentInstance().getApplication()
								.getNavigationHandler();

						configurableNavigationHandler.performNavigation(NavegacionEnum.INICIO.getRuta());

					}else{
						//return to dashboard
						errorGenericoController.setMostrarAceptar(false);
						facesContext.getExternalContext().getSessionMap().put(MSG_EXCEPTION, errorGenericoController);
						final ConfigurableNavigationHandler configurableNavigationHandler = (ConfigurableNavigationHandler) FacesContext
								.getCurrentInstance().getApplication()
								.getNavigationHandler();

						configurableNavigationHandler.performNavigation(NavegacionEnum.INICIO.getRuta());
					}

			     }

			} catch (final Exception ex) {
				LOGGER.error("Could not handle exception!", ex);
		    } finally {
		    	iterator.remove();
		    }
		}
	  	getWrapped().handle();
	}

	protected ExceptionInfo createExceptionInfo(final Throwable rootCause) throws IOException {
	     final ExceptionInfo info = new ExceptionInfo();
	     info.setException(rootCause);
	     info.setMessage(rootCause.getMessage());
	     info.setStackTrace(rootCause.getStackTrace());
	     info.setTimestamp(new Date());
	     info.setType(rootCause.getClass().getName());

	     final StringWriter sw = new StringWriter();
	     final PrintWriter pw = new PrintWriter(sw);
	     LOGGER.error(rootCause.getMessage());
	     info.setFormattedStackTrace(sw.toString().replaceAll("(\r\n|\n)", "<br/>"));
	     pw.close();
	     sw.close();

	     final SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_PATTERN);
	     info.setFormattedTimestamp(format.format(info.getTimestamp()));

	     return info;
	 }

	 protected void handleRedirect(final FacesContext context, final Throwable rootCause, final ExceptionInfo info, final boolean responseResetted) throws IOException {
	     context.getExternalContext().getFlash().put(ExceptionInfo.ATTRIBUTE_NAME, info);
	     context.getExternalContext().getFlash().setRedirect(true);

	     final Map<String, String> errorPages = RequestContext.getCurrentInstance().getApplicationContext().getConfig().getErrorPages();

	     // get error page by exception type
	     String errorPage = errorPages.get(rootCause.getClass().getName());

		 // get default error page
		 if (errorPage == null) {
		     errorPage = errorPages.get(null);
		 }

		 if (errorPage == null) {
		     throw new IllegalArgumentException(
		             "No default error page (Status 500 or java.lang.Throwable) and no error page for type \"" + rootCause.getClass() + "\" defined!");
		 }

		 final String url = context.getExternalContext().getRequestContextPath() + errorPage;

		 // workaround for mojarra -> mojarra doesn't reset PartialResponseWriter#inChanges if we call externalContext#resetResponse
		if (responseResetted && context.getPartialViewContext().isAjaxRequest()) {
		    final ExternalContext externalContext = context.getExternalContext();
		    final PartialResponseWriter writer = context.getPartialViewContext().getPartialResponseWriter();
		    externalContext.addResponseHeader("Content-Type", "text/xml; charset=" + externalContext.getResponseCharacterEncoding());
		    externalContext.addResponseHeader("Cache-Control", "no-cache");
			externalContext.setResponseContentType("text/xml");

			writer.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
			writer.startElement("partial-response", null);
			writer.startElement("redirect", null);
			writer.writeAttribute("url", url, null);
			writer.endElement("redirect");
			writer.endElement("partial-response");
		}else {
		        context.getExternalContext().redirect(url);
		}
	    context.responseComplete();
	 }

	 /**
	  * Método utilizado para obtener la sesión actual
	  *
	 * @return HttSession objeto de sesión nativo
	 */
	private HttpSession getSession() {
			final ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
					.currentRequestAttributes();
			return attr.getRequest().getSession(false);
	 }


}