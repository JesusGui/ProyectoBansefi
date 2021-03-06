package mx.babel.arq.banksystem.components;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UINamingContainer;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.FacesEvent;
import javax.faces.event.PhaseId;

import org.primefaces.event.DateViewChangeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.util.ComponentUtils;
import org.primefaces.util.Constants;

/**
 * Clase base del componente para selección de rangos de fechas por mes
 * @author gerard.chavez
 *
 */
@ResourceDependencies({
    @ResourceDependency(library="primefaces", name="primefaces.css"),
    @ResourceDependency(library="primefaces", name="jquery/jquery.js"),
    @ResourceDependency(library="primefaces", name="jquery/jquery-plugins.js"),
    @ResourceDependency(library="primefaces", name="primefaces.js"),
    @ResourceDependency(name="js/calendarMonthRange.js"),
    @ResourceDependency(name="css/calendarMonthRange.css")
})
public class CalendarMonthRange  extends HtmlInputText implements org.primefaces.component.api.Widget,org.primefaces.component.api.InputHolder,org.primefaces.component.api.MixedClientBehaviorHolder  {

    public static final String COMPONENT_TYPE = "mx.babel.arq.banksystem.components.CalendarMonthRange";
    public static final String COMPONENT_FAMILY = "mx.babel.arq.banksystem.components";
    public final static String INPUT_STYLE_CLASS = "ui-inputfield ui-widget ui-state-default ui-corner-all";
    public final static String MOBILE_POPUP_CONTAINER_CLASS = "ui-calendar ui-calendar-popup";
    public final static String MOBILE_INLINE_CONTAINER_CLASS = "ui-calendar ui-calendar-inline";
    
    private Map<String,AjaxBehaviorEvent> customEvents = new HashMap<String,AjaxBehaviorEvent>();

    private java.util.Locale calculatedLocale;
    private java.util.TimeZone appropriateTimeZone;
    
    private String timeOnlyPattern = null;
    private boolean conversionFailed = false;

    private static final Collection<String> EVENT_NAMES = Collections.unmodifiableCollection(Arrays.asList("blur","change","valueChange","click","dblclick","focus","keydown","keypress","keyup","mousedown","mousemove","mouseout","mouseover","mouseup","select","dateSelect","viewChange"));
    private static final String DEFAULT_RENDERER = "mx.babel.arq.banksystem.components.CalendarMonthRangeRenderer";

    protected enum PropertyKeys {

        placeholder
        ,widgetVar
        ,mindate
        ,maxdate
        ,pages
        ,mode
        ,pattern
        ,locale
        ,navigator
        ,timeZone
        ,readonlyInput
        ,showButtonPanel
        ,effect
        ,effectDuration
        ,showOn
        ,showWeek
        ,disabledWeekends
        ,showOtherMonths
        ,selectOtherMonths
        ,yearRange
        ,timeOnly
        ,stepHour
        ,stepMinute
        ,stepSecond
        ,minHour
        ,maxHour
        ,minMinute
        ,maxMinute
        ,minSecond
        ,maxSecond
        ,pagedate
        ,beforeShowDay
        ,mask
        ,timeControlType
        ,restrictMinDate
        ,restrictMaxDate;

        String toString;

        PropertyKeys(String toString) {
            this.toString = toString;
        }

        PropertyKeys() {
            
        }

        public String toString() {
            return this.toString != null ? this.toString : super.toString();
        }
    }

    public CalendarMonthRange() {
        setRendererType(DEFAULT_RENDERER);
    }
    
    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }

    public java.lang.String getPlaceholder() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.placeholder, null);
    }
    public void setPlaceholder(java.lang.String _placeholder) {
        getStateHelper().put(PropertyKeys.placeholder, _placeholder);
    }

    public java.lang.String getWidgetVar() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.widgetVar, null);
    }
    public void setWidgetVar(java.lang.String _widgetVar) {
        getStateHelper().put(PropertyKeys.widgetVar, _widgetVar);
    }

    public java.lang.Object getMindate() {
        return getStateHelper().eval(PropertyKeys.mindate, null);
    }
    public void setMindate(java.lang.Object _mindate) {
        getStateHelper().put(PropertyKeys.mindate, _mindate);
    }

    public java.lang.Object getMaxdate() {
        return getStateHelper().eval(PropertyKeys.maxdate, null);
    }
    public void setMaxdate(java.lang.Object _maxdate) {
        getStateHelper().put(PropertyKeys.maxdate, _maxdate);
    }

    public int getPages() {
        return (java.lang.Integer) getStateHelper().eval(PropertyKeys.pages, 1);
    }
    public void setPages(int _pages) {
        getStateHelper().put(PropertyKeys.pages, _pages);
    }

    public java.lang.String getMode() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.mode, "popup");
    }
    public void setMode(java.lang.String _mode) {
        getStateHelper().put(PropertyKeys.mode, _mode);
    }

    public java.lang.String getPattern() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.pattern, null);
    }
    public void setPattern(java.lang.String _pattern) {
        getStateHelper().put(PropertyKeys.pattern, _pattern);
    }

    public java.lang.Object getLocale() {
        return getStateHelper().eval(PropertyKeys.locale, null);
    }
    public void setLocale(java.lang.Object _locale) {
        getStateHelper().put(PropertyKeys.locale, _locale);
    }

    public boolean isNavigator() {
        return (java.lang.Boolean) getStateHelper().eval(PropertyKeys.navigator, false);
    }
    public void setNavigator(boolean _navigator) {
        getStateHelper().put(PropertyKeys.navigator, _navigator);
    }

    public java.lang.Object getTimeZone() {
        return getStateHelper().eval(PropertyKeys.timeZone, null);
    }
    public void setTimeZone(java.lang.Object _timeZone) {
        getStateHelper().put(PropertyKeys.timeZone, _timeZone);
    }

    public boolean isReadonlyInput() {
        return (java.lang.Boolean) getStateHelper().eval(PropertyKeys.readonlyInput, false);
    }
    public void setReadonlyInput(boolean _readonlyInput) {
        getStateHelper().put(PropertyKeys.readonlyInput, _readonlyInput);
    }

    public boolean isShowButtonPanel() {
        return (java.lang.Boolean) getStateHelper().eval(PropertyKeys.showButtonPanel, false);
    }
    public void setShowButtonPanel(boolean _showButtonPanel) {
        getStateHelper().put(PropertyKeys.showButtonPanel, _showButtonPanel);
    }

    public java.lang.String getEffect() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.effect, null);
    }
    public void setEffect(java.lang.String _effect) {
        getStateHelper().put(PropertyKeys.effect, _effect);
    }

    public java.lang.String getEffectDuration() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.effectDuration, "normal");
    }
    public void setEffectDuration(java.lang.String _effectDuration) {
        getStateHelper().put(PropertyKeys.effectDuration, _effectDuration);
    }

    public java.lang.String getShowOn() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.showOn, "focus");
    }
    public void setShowOn(java.lang.String _showOn) {
        getStateHelper().put(PropertyKeys.showOn, _showOn);
    }

    public boolean isShowWeek() {
        return (java.lang.Boolean) getStateHelper().eval(PropertyKeys.showWeek, false);
    }
    public void setShowWeek(boolean _showWeek) {
        getStateHelper().put(PropertyKeys.showWeek, _showWeek);
    }

    public boolean isDisabledWeekends() {
        return (java.lang.Boolean) getStateHelper().eval(PropertyKeys.disabledWeekends, false);
    }
    public void setDisabledWeekends(boolean _disabledWeekends) {
        getStateHelper().put(PropertyKeys.disabledWeekends, _disabledWeekends);
    }

    public boolean isShowOtherMonths() {
        return (java.lang.Boolean) getStateHelper().eval(PropertyKeys.showOtherMonths, false);
    }
    public void setShowOtherMonths(boolean _showOtherMonths) {
        getStateHelper().put(PropertyKeys.showOtherMonths, _showOtherMonths);
    }

    public boolean isSelectOtherMonths() {
        return (java.lang.Boolean) getStateHelper().eval(PropertyKeys.selectOtherMonths, false);
    }
    public void setSelectOtherMonths(boolean _selectOtherMonths) {
        getStateHelper().put(PropertyKeys.selectOtherMonths, _selectOtherMonths);
    }

    public java.lang.String getYearRange() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.yearRange, null);
    }
    public void setYearRange(java.lang.String _yearRange) {
        getStateHelper().put(PropertyKeys.yearRange, _yearRange);
    }

    public boolean isTimeOnly() {
        return (java.lang.Boolean) getStateHelper().eval(PropertyKeys.timeOnly, false);
    }
    public void setTimeOnly(boolean _timeOnly) {
        getStateHelper().put(PropertyKeys.timeOnly, _timeOnly);
    }

    public int getStepHour() {
        return (java.lang.Integer) getStateHelper().eval(PropertyKeys.stepHour, 1);
    }
    public void setStepHour(int _stepHour) {
        getStateHelper().put(PropertyKeys.stepHour, _stepHour);
    }

    public int getStepMinute() {
        return (java.lang.Integer) getStateHelper().eval(PropertyKeys.stepMinute, 1);
    }
    public void setStepMinute(int _stepMinute) {
        getStateHelper().put(PropertyKeys.stepMinute, _stepMinute);
    }

    public int getStepSecond() {
        return (java.lang.Integer) getStateHelper().eval(PropertyKeys.stepSecond, 1);
    }
    public void setStepSecond(int _stepSecond) {
        getStateHelper().put(PropertyKeys.stepSecond, _stepSecond);
    }

    public int getMinHour() {
        return (java.lang.Integer) getStateHelper().eval(PropertyKeys.minHour, 0);
    }
    public void setMinHour(int _minHour) {
        getStateHelper().put(PropertyKeys.minHour, _minHour);
    }

    public int getMaxHour() {
        return (java.lang.Integer) getStateHelper().eval(PropertyKeys.maxHour, 23);
    }
    public void setMaxHour(int _maxHour) {
        getStateHelper().put(PropertyKeys.maxHour, _maxHour);
    }

    public int getMinMinute() {
        return (java.lang.Integer) getStateHelper().eval(PropertyKeys.minMinute, 0);
    }
    public void setMinMinute(int _minMinute) {
        getStateHelper().put(PropertyKeys.minMinute, _minMinute);
    }

    public int getMaxMinute() {
        return (java.lang.Integer) getStateHelper().eval(PropertyKeys.maxMinute, 59);
    }
    public void setMaxMinute(int _maxMinute) {
        getStateHelper().put(PropertyKeys.maxMinute, _maxMinute);
    }

    public int getMinSecond() {
        return (java.lang.Integer) getStateHelper().eval(PropertyKeys.minSecond, 0);
    }
    public void setMinSecond(int _minSecond) {
        getStateHelper().put(PropertyKeys.minSecond, _minSecond);
    }

    public int getMaxSecond() {
        return (java.lang.Integer) getStateHelper().eval(PropertyKeys.maxSecond, 59);
    }
    public void setMaxSecond(int _maxSecond) {
        getStateHelper().put(PropertyKeys.maxSecond, _maxSecond);
    }

    public java.lang.Object getPagedate() {
        return getStateHelper().eval(PropertyKeys.pagedate, null);
    }
    public void setPagedate(java.lang.Object _pagedate) {
        getStateHelper().put(PropertyKeys.pagedate, _pagedate);
    }

    public java.lang.String getBeforeShowDay() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.beforeShowDay, null);
    }
    public void setBeforeShowDay(java.lang.String _beforeShowDay) {
        getStateHelper().put(PropertyKeys.beforeShowDay, _beforeShowDay);
    }

    public java.lang.String getMask() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.mask, "false");
    }
    public void setMask(java.lang.String _mask) {
        getStateHelper().put(PropertyKeys.mask, _mask);
    }

    public java.lang.String getTimeControlType() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.timeControlType, null);
    }
    public void setTimeControlType(java.lang.String _timeControlType) {
        getStateHelper().put(PropertyKeys.timeControlType, _timeControlType);
    }
    
    public boolean isRestrictMinDate() {
        return (java.lang.Boolean) getStateHelper().eval(PropertyKeys.restrictMinDate, false);
    }
    public void setRestrictMinDate(boolean _restrictMinDate) {
        getStateHelper().put(PropertyKeys.restrictMinDate, _restrictMinDate);
    }
    public boolean isRestrictMaxDate() {
        return (java.lang.Boolean) getStateHelper().eval(PropertyKeys.restrictMaxDate, false);
    }
    public void setRestrictMaxDate(boolean _restrictMaxDate) {
        getStateHelper().put(PropertyKeys.restrictMaxDate, _restrictMaxDate);
    }   
    
    public java.util.Locale calculateLocale(FacesContext facesContext) {
        if(calculatedLocale == null) {
            Object userLocale = getLocale();
            if(userLocale != null) {
                if(userLocale instanceof String) {
                    calculatedLocale = ComponentUtils.toLocale((String) userLocale);
                }
                else if(userLocale instanceof java.util.Locale)
                    calculatedLocale = (java.util.Locale) userLocale;
                else
                    throw new IllegalArgumentException("Type:" + userLocale.getClass() + " is not a valid locale type for calendar:" + this.getClientId(facesContext));
            } else {
                calculatedLocale = facesContext.getViewRoot().getLocale();
            }
        }
        
        return calculatedLocale;
    }
    
    public java.util.TimeZone calculateTimeZone() {
        if(appropriateTimeZone == null) {
            Object usertimeZone = getTimeZone();
            if(usertimeZone != null) {
                if(usertimeZone instanceof String)
                    appropriateTimeZone =  java.util.TimeZone.getTimeZone((String) usertimeZone);
                else if(usertimeZone instanceof java.util.TimeZone)
                    appropriateTimeZone = (java.util.TimeZone) usertimeZone;
                else
                    throw new IllegalArgumentException("TimeZone could be either String or java.util.TimeZone");
            } else {
                appropriateTimeZone = java.util.TimeZone.getDefault();
            }
        }
        
        return appropriateTimeZone;
    }
    
    public boolean isPopup() {
        return "popup".equalsIgnoreCase(getMode());
    }

    public boolean hasTime() {
        String pattern = getPattern();

        return pattern != null && pattern.indexOf(":") != -1;
    }

    @Override
    public Collection<String> getEventNames() {
        return EVENT_NAMES;
    }
    
    @Override
    public Collection<String> getUnobstrusiveEventNames() {
        return Collections.unmodifiableCollection(Arrays.asList("dateSelect","viewChange"));
    }

    @Override
    public void queueEvent(FacesEvent event) {
        FacesContext context = getFacesContext();

        if(this.isRequestSource(context) && (event instanceof AjaxBehaviorEvent)) {
            Map<String,String> params = context.getExternalContext().getRequestParameterMap();
            String eventName = params.get(Constants.RequestParams.PARTIAL_BEHAVIOR_EVENT_PARAM);
            String clientId = this.getClientId(context);
            AjaxBehaviorEvent behaviorEvent = (AjaxBehaviorEvent) event;

            if(eventName != null) {
                if("dateSelect".equals(eventName)) {
                    customEvents.put("dateSelect", (AjaxBehaviorEvent) event);
                }
                else if("viewChange".equals(eventName)) {
                    int month = Integer.parseInt(params.get(clientId + "_month"));
                    int year = Integer.parseInt(params.get(clientId + "_year"));
                    DateViewChangeEvent dateViewChangeEvent = new DateViewChangeEvent(this, behaviorEvent.getBehavior(), month, year);
                    dateViewChangeEvent.setPhaseId(behaviorEvent.getPhaseId());
                    super.queueEvent(dateViewChangeEvent);
                }
                else {
                    super.queueEvent(event);        //regular events like change, click, blur
                }
            } 
        }
        else {
            super.queueEvent(event);            //valueChange
        }
    }

    @Override
    public void validate(FacesContext context) {
        super.validate(context);
       
        if(isValid() && isRequestSource(context)) {
            for(Iterator<String> customEventIter = customEvents.keySet().iterator(); customEventIter.hasNext();) {
                AjaxBehaviorEvent behaviorEvent = customEvents.get(customEventIter.next());
                SelectEvent selectEvent = new SelectEvent(this, behaviorEvent.getBehavior(), this.getValue());

                if(behaviorEvent.getPhaseId().equals(PhaseId.APPLY_REQUEST_VALUES)) {
                    selectEvent.setPhaseId(PhaseId.PROCESS_VALIDATIONS);
                }
                else {
                    selectEvent.setPhaseId(PhaseId.INVOKE_APPLICATION);
                }

                super.queueEvent(selectEvent);
            }
        }
    }

    public String calculatePattern() {
        String pattern = this.getPattern();
        Locale locale = this.calculateLocale(getFacesContext());

        return pattern == null ? ((SimpleDateFormat) DateFormat.getDateInstance(DateFormat.SHORT, locale)).toPattern() : pattern;
    }    

    public String calculateTimeOnlyPattern() {
        if(timeOnlyPattern == null) {
            String localePattern = ((SimpleDateFormat) DateFormat.getDateInstance(DateFormat.SHORT, calculateLocale(getFacesContext()))).toPattern();
            String userTimePattern = getPattern();

            timeOnlyPattern = localePattern + " " + userTimePattern;
        }

        return timeOnlyPattern;
    }
        
    public void setConversionFailed(boolean value) {
        this.conversionFailed = value;
    }

    public boolean isConversionFailed() {
        return this.conversionFailed;
    }
    
    @Override
    public String getInputClientId() {
        return this.getClientId(getFacesContext()) + "_input";
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
