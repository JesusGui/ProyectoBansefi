package mx.babel.arq.banksystem.components;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.primefaces.context.RequestContext;
import org.primefaces.renderkit.InputRenderer;
import org.primefaces.util.HTML;
import org.primefaces.util.MessageFactory;
import org.primefaces.util.WidgetBuilder;

/**
 * Clase encargada de pintar del componente para selección de rangos de fechas por mes
 * @author gerard.chavez
 *
 */
public class CalendarMonthRangeRenderer extends InputRenderer  {

    @Override
    public void decode(FacesContext context, UIComponent component) {
        CalendarMonthRange calendar = (CalendarMonthRange) component;

        if(calendar.isDisabled() || calendar.isReadonly()) {
            return;
        }

        String param = calendar.getClientId(context) + "_input";
        String submittedValue = context.getExternalContext().getRequestParameterMap().get(param);

        if(submittedValue != null) {
            calendar.setSubmittedValue(submittedValue);
        }

        decodeBehaviors(context, calendar);
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        CalendarMonthRange calendar = (CalendarMonthRange) component;
        String markupValue = CalendarMonthRangeUtils.getValueAsString(context, calendar);
        String widgetValue = calendar.isTimeOnly() ? CalendarMonthRangeUtils.getTimeOnlyValueAsString(context, calendar) : markupValue;

        encodeMarkup(context, calendar, markupValue);
        encodeScript(context, calendar, widgetValue);
    }

    protected void encodeMarkup(FacesContext context, CalendarMonthRange calendar, String value) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String clientId = calendar.getClientId(context);
        String inputId = clientId + "_input";
        boolean popup = calendar.isPopup();

        writer.startElement("span", calendar);
        writer.writeAttribute("id", clientId, null);
        if (calendar.getStyle() != null)
            writer.writeAttribute("style", calendar.getStyle(), null);
        if (calendar.getStyleClass() != null)
            writer.writeAttribute("class", calendar.getStyleClass(), null);

        //inline container
        if(!popup) {
            writer.startElement("div", null);
            writer.writeAttribute("id", clientId + "_inline", null);
            writer.endElement("div");
        }

        //input
        encodeInput(context, calendar, inputId, value, popup);
        
        writer.endElement("span");
       
    }
    
    protected void encodeInput(FacesContext context, CalendarMonthRange calendar, String id, String value, boolean popup) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String type = popup ? "text" : "hidden";
        boolean disabled = calendar.isDisabled();

        writer.startElement("input", null);
        writer.writeAttribute("id", id, null);
        writer.writeAttribute("name", id, null);
        writer.writeAttribute("type", type, null);

        if(!isValueBlank(value)) {
            writer.writeAttribute("value", value, null);
        }

        if(popup) {
            String inputStyleClass = CalendarMonthRange.INPUT_STYLE_CLASS;
            if (disabled)
                inputStyleClass = inputStyleClass + " ui-state-disabled";
            if (!calendar.isValid())
                inputStyleClass = inputStyleClass + " ui-state-error";
            
            writer.writeAttribute("class", inputStyleClass, null);
              
            if (calendar.isReadonly() || calendar.isReadonlyInput())
                writer.writeAttribute("readonly", "readonly", null);
            if (calendar.isDisabled())
                writer.writeAttribute("disabled", "disabled", null);
        
            renderPassThruAttributes(context, calendar, HTML.INPUT_TEXT_ATTRS_WITHOUT_EVENTS);
            renderDomEvents(context, calendar, HTML.INPUT_TEXT_EVENTS);
        }
        
        if(RequestContext.getCurrentInstance().getApplicationContext().getConfig().isClientSideValidationEnabled()) {
            renderValidationMetadata(context, calendar);
        }
        
        writer.endElement("input");
    }

    protected void encodeScript(FacesContext context, CalendarMonthRange calendar, String value) throws IOException {
        String clientId = calendar.getClientId(context);
        Locale locale = calendar.calculateLocale(context);
        String pattern = "dd/MM/yyyy";
        String mask = calendar.getMask();
        WidgetBuilder wb = getWidgetBuilder(context);
        wb.initWithDomReady("CalendarMonthRange", calendar.resolveWidgetVar(), clientId);
        
        wb.attr("popup", calendar.isPopup())
            .attr("locale", locale.toString())
            .attr("dateFormat", CalendarMonthRangeUtils.convertPattern(pattern));
        
        //default date
        Object pagedate = calendar.getPagedate();
        String defaultDate = null;
        
        if(calendar.isConversionFailed()) {
            defaultDate = CalendarMonthRangeUtils.getValueAsString(context, calendar, new Date());
        }
        else if(!isValueBlank(value)) {
            defaultDate = value;
        } 
        else if(pagedate != null) {
            defaultDate = CalendarMonthRangeUtils.getValueAsString(context, calendar, pagedate);
        }
        
        wb.attr("defaultDate", defaultDate, null)
            .attr("numberOfMonths", calendar.getPages(), 1)
            .attr("minDate", CalendarMonthRangeUtils.getValueAsString(context, calendar, calendar.getMindate()), null)
            .attr("maxDate", CalendarMonthRangeUtils.getValueAsString(context, calendar, calendar.getMaxdate()), null)
            .attr("showButtonPanel", calendar.isShowButtonPanel(), false)
            .attr("showWeek", calendar.isShowWeek(), false)
            .attr("disabledWeekends", calendar.isDisabledWeekends(), false)
            .attr("disabled", calendar.isDisabled(), false)
            .attr("yearRange", calendar.getYearRange(), null)
            .attr("restrictMinDate", calendar.isRestrictMinDate(), false)
            .attr("restrictMaxDate", calendar.isRestrictMaxDate(), false);
        
        if(calendar.isNavigator()) {
            wb.attr("changeMonth", true).attr("changeYear", true);
        }
        
        if(calendar.getEffect() != null) {
            wb.attr("showAnim", calendar.getEffect()).attr("duration", calendar.getEffectDuration());
        }
        
        String beforeShowDay = calendar.getBeforeShowDay();
        if(beforeShowDay != null) {
            wb.nativeAttr("preShowDay", beforeShowDay);
        }
        
        String showOn = calendar.getShowOn();
        if(!"focus".equalsIgnoreCase(showOn)) {
            wb.attr("showOn", showOn);
        }
        
        if(calendar.isShowOtherMonths()) {
            wb.attr("showOtherMonths", true).attr("selectOtherMonths", true);
        }
        
        if(calendar.hasTime()) {
            wb.attr("timeOnly", calendar.isTimeOnly())
                .attr("stepHour", calendar.getStepHour())
                .attr("stepMinute", calendar.getStepMinute())
                .attr("stepSecond", calendar.getStepSecond())
                .attr("hourMin", calendar.getMinHour())
                .attr("hourMax", calendar.getMaxHour())
                .attr("minuteMin", calendar.getMinMinute())
                .attr("minuteMax", calendar.getMaxMinute())
                .attr("secondMin", calendar.getMinSecond())
                .attr("secondMax", calendar.getMaxSecond())
                .attr("controlType", calendar.getTimeControlType(), null);
        }
        
        if(mask != null && !"false".equals(mask)) {
            String maskTemplate = ("true".equals(mask)) ? pattern.replaceAll("[a-zA-Z]", "9"): mask;
            wb.attr("mask", maskTemplate);
        }
        
        encodeClientBehaviors(context, calendar);
        
        wb.finish();
    }

    @Override
    public Object getConvertedValue(FacesContext context, UIComponent component, Object value) throws ConverterException {
        CalendarMonthRange calendar = (CalendarMonthRange) component;
        String submittedValue = (String) value;
        SimpleDateFormat format = null;

        if(isValueBlank(submittedValue)) {
            return null;
        }
        
        //Delegate to user supplied converter if defined
        try {
            Converter converter = calendar.getConverter();
            if(converter != null) {
                return converter.getAsObject(context, calendar, submittedValue);
            }
        }
        catch(ConverterException e){
            calendar.setConversionFailed(true);
                   
            throw e;
        }
        
        //Use built-in converter
        format = new SimpleDateFormat(calendar.calculatePattern(), calendar.calculateLocale(context));
        format.setLenient(false);
        format.setTimeZone(calendar.calculateTimeZone());
        try {
            return format.parse(submittedValue);
        } 
        catch (ParseException e) {
            calendar.setConversionFailed(true);
            
            FacesMessage message = null;
            Object[] params = new Object[3];
            params[0] = submittedValue;
            params[1] = format.format(new Date());
            params[2] = MessageFactory.getLabel(context, calendar);
            
            if(calendar.isTimeOnly()) {
                message = MessageFactory.getMessage("javax.faces.converter.DateTimeConverter.TIME", FacesMessage.SEVERITY_ERROR, params);
            } 
            else if(calendar.hasTime()) {
                message = MessageFactory.getMessage("javax.faces.converter.DateTimeConverter.DATETIME", FacesMessage.SEVERITY_ERROR, params);
            } 
            else {
                message = MessageFactory.getMessage("javax.faces.converter.DateTimeConverter.DATE", FacesMessage.SEVERITY_ERROR, params);
            }
            
            throw new ConverterException(message);
        }
    }
}
