package mx.babel.arq.banksystem.components;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.FacesEvent;

import org.primefaces.event.SlideEndEvent;
import org.primefaces.util.Constants;

/**  
 * @author javier.martinnino
 * Clase utilizada para la creacion del Componente SelectOneRadioSlider
 *
 */
@ResourceDependencies({
	@ResourceDependency(library="primefaces", name="primefaces.css"),
	@ResourceDependency(library="primefaces", name="jquery/jquery.js"),
	@ResourceDependency(library="primefaces", name="jquery/jquery-plugins.js"),
	@ResourceDependency(library="primefaces", name="primefaces.js"),
	@ResourceDependency(name="js/selectOneRadioSlider.js"),
	@ResourceDependency(name="css/selectOneRadioSlider.css")
	
})
public class SelectOneRadioSlider extends UIComponentBase implements org.primefaces.component.api.Widget,javax.faces.component.behavior.ClientBehaviorHolder {

	
	public static final String COMPONENT_TYPE = "mx.babel.arq.banksystem.components.SelectOneRadioSlider";
	public static final String COMPONENT_FAMILY = "mx.babel.arq.banksystem.components";
	private static final String DEFAULT_RENDERER = "mx.babel.arq.banksystem.components.SelectOneRadioSliderRenderer";
	private static final String DEFAULT_EVENT = "slideEnd";
	private static final Collection<String> EVENT_NAMES = Collections.unmodifiableCollection(Arrays.asList(DEFAULT_EVENT));
	
	protected enum PropertyKeys {

		widgetVar
		,forValue("for")
		,display
		,minValue
		,maxValue
		,style
		,styleClass
		,animate
		,type
		,step
		,disabled
		,onSlideStart
		,onSlide
		,onSlideEnd
		,range
		,displayTemplate
		,id;

		String toString;

		PropertyKeys(String toString) {
			this.toString = toString;
		}

		PropertyKeys() {
			
		}
		
		@Override
		public String toString() {
			return (this.toString != null) ? this.toString : super.toString();
}
	}

	public SelectOneRadioSlider() {
		setRendererType(DEFAULT_RENDERER);
	}
	
	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	public java.lang.String getWidgetVar() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.widgetVar, null);
	}
	public void setWidgetVar(java.lang.String _widgetVar) {
		getStateHelper().put(PropertyKeys.widgetVar, _widgetVar);
	}

	public java.lang.String getFor() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.forValue, null);
	}
	public void setFor(java.lang.String _for) {
		getStateHelper().put(PropertyKeys.forValue, _for);
	}

	public java.lang.String getDisplay() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.display, null);
	}
	public void setDisplay(java.lang.String _display) {
		getStateHelper().put(PropertyKeys.display, _display);
	}

	public int getMinValue() {
		return (java.lang.Integer) getStateHelper().eval(PropertyKeys.minValue, 0);
	}
	public void setMinValue(int _minValue) {
		getStateHelper().put(PropertyKeys.minValue, _minValue);
	}

	public int getMaxValue() {
		return (java.lang.Integer) getStateHelper().eval(PropertyKeys.maxValue, 100);
	}
	public void setMaxValue(int _maxValue) {
		getStateHelper().put(PropertyKeys.maxValue, _maxValue);
	}

	public java.lang.String getStyle() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.style, null);
	}
	public void setStyle(java.lang.String _style) {
		getStateHelper().put(PropertyKeys.style, _style);
	}

	public java.lang.String getStyleClass() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.styleClass, null);
	}
	public void setStyleClass(java.lang.String _styleClass) {
		getStateHelper().put(PropertyKeys.styleClass, _styleClass);
	}

	public boolean isAnimate() {
		return (java.lang.Boolean) getStateHelper().eval(PropertyKeys.animate, true);
	}
	public void setAnimate(boolean _animate) {
		getStateHelper().put(PropertyKeys.animate, _animate);
	}

	public java.lang.String getType() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.type, "horizontal");
	}
	public void setType(java.lang.String _type) {
		getStateHelper().put(PropertyKeys.type, _type);
	}

	public int getStep() {
		return (java.lang.Integer) getStateHelper().eval(PropertyKeys.step, 1);
	}
	public void setStep(int _step) {
		getStateHelper().put(PropertyKeys.step, _step);
	}

	public boolean isDisabled() {
		return (java.lang.Boolean) getStateHelper().eval(PropertyKeys.disabled, false);
	}
	public void setDisabled(boolean _disabled) {
		getStateHelper().put(PropertyKeys.disabled, _disabled);
	}

	public java.lang.String getOnSlideStart() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.onSlideStart, null);
	}
	public void setOnSlideStart(java.lang.String _onSlideStart) {
		getStateHelper().put(PropertyKeys.onSlideStart, _onSlideStart);
	}

	public java.lang.String getOnSlide() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.onSlide, null);
	}
	public void setOnSlide(java.lang.String _onSlide) {
		getStateHelper().put(PropertyKeys.onSlide, _onSlide);
	}

	public java.lang.String getOnSlideEnd() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.onSlideEnd, null);
	}
	public void setOnSlideEnd(java.lang.String _onSlideEnd) {
		getStateHelper().put(PropertyKeys.onSlideEnd, _onSlideEnd);
	}

	public boolean isRange() {
		return (java.lang.Boolean) getStateHelper().eval(PropertyKeys.range, false);
	}
	public void setRange(boolean _range) {
		getStateHelper().put(PropertyKeys.range, _range);
	}

	public java.lang.String getDisplayTemplate() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.displayTemplate, null);
	}
	public void setDisplayTemplate(java.lang.String _displayTemplate) {
		getStateHelper().put(PropertyKeys.displayTemplate, _displayTemplate);
	}
	
	@Override
	public java.lang.String getId() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.id, null);
	}
	
	@Override
	public void setId(java.lang.String _id) {
		getStateHelper().put(PropertyKeys.id, _id);
	}
	
    @Override
    public Collection<String> getEventNames() {
        return EVENT_NAMES;
    }

    @Override
    public String getDefaultEventName() {
        return DEFAULT_EVENT;
    }

    @Override
    public void queueEvent(FacesEvent event) {
        FacesContext context = getFacesContext();

        if(isRequestSource(context)) {
            Map<String,String> params = context.getExternalContext().getRequestParameterMap();
            String eventName = params.get(Constants.RequestParams.PARTIAL_BEHAVIOR_EVENT_PARAM);
            String clientId = getClientId(context);

            AjaxBehaviorEvent behaviorEvent = (AjaxBehaviorEvent) event;

            if(("slideEnd").equals(eventName)) {
                int sliderValue = Integer.parseInt(params.get(clientId + "_slideValue"));
                SlideEndEvent slideEndEvent = new SlideEndEvent(this, behaviorEvent.getBehavior(), sliderValue);
                slideEndEvent.setPhaseId(behaviorEvent.getPhaseId());
                super.queueEvent(slideEndEvent);
            }
        }
        else {
            super.queueEvent(event);
        }
    }

    private boolean isRequestSource(FacesContext context) {
        return this.getClientId(context).equals(context.getExternalContext().getRequestParameterMap().get(Constants.RequestParams.PARTIAL_SOURCE_PARAM));
    }
    
    @Override
	public String resolveWidgetVar() {
		FacesContext context = getFacesContext();
		String userWidgetVar = (String) getAttributes().get("widgetVar");

		if(userWidgetVar != null)
			return userWidgetVar;
		 else
			return "widget_" + getClientId(context).replaceAll("-|" + UINamingContainer.getSeparatorChar(context), "_");
	}
}