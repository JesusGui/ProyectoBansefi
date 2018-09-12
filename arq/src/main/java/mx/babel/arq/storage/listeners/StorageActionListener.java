package mx.babel.arq.storage.listeners;

import java.util.List;

import javax.faces.component.ActionSource;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import mx.babel.arq.storage.beans.StorageMgrBean;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.context.RequestContext;

public class StorageActionListener implements ActionListener {

    private static final Logger LOGGER = LogManager.getLogger(StorageActionListener.class);
    private final ActionListener delegate;

    public StorageActionListener(final ActionListener delegate) {
        this.delegate = delegate;
    }
    @Override
    public void processAction(final ActionEvent actionEvent)
            throws AbortProcessingException {

           final String nickname = (String)actionEvent.getComponent().getAttributes().get("store");
           if(nickname != null && "true".equalsIgnoreCase(nickname)){
               final FacesContext context = FacesContext.getCurrentInstance();
               final List<UIComponent> componentes = context.getViewRoot().getChildren();
               final String className = containsStorageFunctionality(componentes);
               if(StringUtils.isNotEmpty(className)){
                   final ActionSource actionSource = (ActionSource)
                           actionEvent.getComponent();
                   final String elActionConjure = actionSource.getAction().getExpressionString();
                   final String elStorageBean = "#" + "{"
                           + className.substring(0, 1).toLowerCase()
                           + className.substring(1) + "}";
                   final StorageMgrBean bean = context.getApplication()
                           .evaluateExpressionGet(context, elStorageBean, StorageMgrBean.class);
                   bean.setGoHomeId(context.getApplication().evaluateExpressionGet(context, elActionConjure, String.class));
                   RequestContext.getCurrentInstance().execute("PF('dlgCancelarProceso').show()");

               }else{
                   delegate.processAction(actionEvent);
               }

           }else{
               delegate.processAction(actionEvent);
           }
    }

    /**
     * Método privado que recorre de manera RECURSIVA todo el UIViewRoot y
     * establece la validación de los campos de porcentaje a falso.
     *
     * @param componentes
     */
    private String containsStorageFunctionality(final List<UIComponent> componentes) {
        String result = null;
        if (componentes != null && !componentes.isEmpty()) {
            for (final UIComponent componente : componentes) {
                if (componente.getClientId().contains("storeFlowLabel") && componente instanceof OutputLabel) {
                    final String className = ((String)((OutputLabel)componente).getValue());
                    if(StringUtils.isNotEmpty(className)){
                        result = className.substring(0, className.indexOf("$"));
                    }
                    break;
                }
                result = this.containsStorageFunctionality(componente.getChildren());
                if(null != result){
                    break;
                }
            }
        }
        return result;
    }


}
