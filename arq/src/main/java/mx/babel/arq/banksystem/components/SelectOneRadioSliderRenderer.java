/*
 * Copyright 2009-2014 PrimeTek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mx.babel.arq.banksystem.components;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.component.UISelectItem;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.model.SelectItem;

import org.primefaces.expression.SearchExpressionFacade;
import org.primefaces.renderkit.CoreRenderer;
import org.primefaces.util.ComponentUtils;
import org.primefaces.util.WidgetBuilder;

/**  
 * @author javier.martinnino
 * Clase utilizada para el renderizado del nuevo Componente SelectOneRadioSlider
 *
 */
public class SelectOneRadioSliderRenderer extends CoreRenderer{

	@Override
	public void decode(FacesContext context, UIComponent component) {
		decodeBehaviors(context, component);
	}

	@Override
	public void encodeEnd(FacesContext context, UIComponent component)
			throws IOException {
		SelectOneRadioSlider slider = (SelectOneRadioSlider) component;

		encodeMarkup(context, slider);
		encodeScript(context, slider);
	}
	
	protected List<SelectItem> getSelectItems(FacesContext context, SelectOneRadioSlider component) {
        List<SelectItem> selectItems = new ArrayList<SelectItem>();

        for(UIComponent child : component.getChildren()) {
            if(child instanceof UISelectItem) {
                UISelectItem uiSelectItem = (UISelectItem) child;
                Object selectItemValue = uiSelectItem.getValue();
                
                if(selectItemValue == null) {
                    selectItems.add(new SelectItem(uiSelectItem.getItemValue(), uiSelectItem.getItemLabel(), uiSelectItem.getItemDescription(), uiSelectItem.isItemDisabled(), uiSelectItem.isItemEscaped(), uiSelectItem.isNoSelectionOption()));
                }
                else {
                    selectItems.add((SelectItem) selectItemValue);
                }	
			}
            else if(child instanceof UISelectItems) {
                UISelectItems uiSelectItems = (UISelectItems) child;
				Object value = uiSelectItems.getValue();
                
                if(value != null) {
                    if(value instanceof SelectItem) {
                        selectItems.add((SelectItem) value);
                    }
                    else if(value.getClass().isArray()) {
                        for(int i = 0; i < Array.getLength(value); i++) {
                            Object item = Array.get(value, i);

                            if(item instanceof SelectItem)
                                selectItems.add((SelectItem) item);
                            else
                                selectItems.add(createSelectItem(context, uiSelectItems, item, null));
                        }
                    }
                    else if(value instanceof Map) {
                        Map map = (Map) value;

                        for(Iterator it = map.keySet().iterator(); it.hasNext();) {
                            Object key = it.next();
                            
                            selectItems.add(createSelectItem(context, uiSelectItems, map.get(key), String.valueOf(key)));
                        }
                    }
                    else if(value instanceof Collection) {
                        Collection collection = (Collection) value;

                        for(Iterator it = collection.iterator(); it.hasNext();) {
                            Object item = it.next();
                            if(item instanceof SelectItem)
                                selectItems.add((SelectItem) item);
                            else
                                selectItems.add(createSelectItem(context, uiSelectItems, item, null));
                        }               
                    }
                }
			}
        }

        return selectItems;
	}
	
	protected SelectItem createSelectItem(FacesContext context, UISelectItems uiSelectItems, Object value, Object label) {
        String var = (String) uiSelectItems.getAttributes().get("var");
        Map<String,Object> attrs = uiSelectItems.getAttributes();
        Map<String,Object> requestMap = context.getExternalContext().getRequestMap();
        
        if(var != null) {
            requestMap.put(var, value);
        }

        Object itemLabelValue = attrs.get("itemLabel");
        Object itemValue = attrs.get("itemValue");
        String description = (String) attrs.get("itemDescription");
        Object itemDisabled = attrs.get("itemDisabled");
        Object itemEscaped = attrs.get("itemLabelEscaped");
        Object noSelection = attrs.get("noSelectionOption");

        if(itemValue == null) {
            itemValue = value;
        }
        
        if(itemLabelValue == null) {
            itemLabelValue = label;
        }

        String itemLabel = itemLabelValue == null ? String.valueOf(value) : String.valueOf(itemLabelValue);
        boolean disabled = itemDisabled == null ? false : Boolean.valueOf(itemDisabled.toString());
        boolean escaped = itemEscaped == null ? false : Boolean.valueOf(itemEscaped.toString());
        boolean noSelectionOption = noSelection == null ? false : Boolean.valueOf(noSelection.toString());
        
        if(var != null) {
            requestMap.remove(var);
        }

        return new SelectItem(itemValue, itemLabel, description, disabled, escaped, noSelectionOption);
    }
	
	protected void encodeMarkup(FacesContext context, SelectOneRadioSlider slider)
			throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		String clientId = slider.getClientId(context);

		List<SelectItem> selectItems = getSelectItems(context, slider);
		String name = slider.getClientId(context);
		writer.startElement("div", slider);
		 writer.startElement("tr", null);
	        int idx = 0;
	        for(SelectItem selectItem : selectItems) {
	            String id = name + UINamingContainer.getSeparatorChar(context) + idx;
	            
	            writer.startElement("td", null);
	            String label = selectItem.getLabel();
	            
	            writer.startElement("label", null);
	            writer.writeAttribute("id", id, null);	           	            
	            if(label != null) {
	                if(selectItem.isEscape())
	                    writer.writeText(label, null);
	                else
	                    writer.write(label);
	            }
	            
	            writer.endElement("label");
	            writer.endElement("td");
	            
	            idx++;
	            
	        }
	        writer.endElement("tr");
	        writer.endElement("div");
	        
		writer.startElement("div", slider);
		writer.writeAttribute("id", clientId, "id");
		if (slider.getStyle() != null)
			writer.writeAttribute("style", slider.getStyle(), null);
		if (slider.getStyleClass() != null)
			writer.writeAttribute("class", slider.getStyleClass(), null);

		writer.endElement("div");
	}

	protected void encodeScript(FacesContext context, SelectOneRadioSlider slider)
			throws IOException {
		String clientId = slider.getClientId(context);
		boolean range = slider.isRange();
		UIComponent output = getTarget(context, slider, slider.getDisplay());

		WidgetBuilder wb = getWidgetBuilder(context);
		wb.initWithDomReady("SelectOneRadioSlider", slider.resolveWidgetVar(), clientId);
		
		List<SelectItem> selectItems = getSelectItems(context, slider);
		Integer minValue=0;
		Integer maxValue = selectItems.size();
		if (maxValue != null){
			maxValue=--maxValue;
		}else{
			maxValue=0;
		}
		
        UIComponent input = getTarget(context, slider, slider.getFor());
        wb.attr("value", ComponentUtils.getValueToRender(context, input))
           .attr("input", input.getClientId(context));
    		
		wb.attr("min", minValue)
				.attr("max", maxValue)
				.attr("animate", slider.isAnimate())
				.attr("step", slider.getStep())
				.attr("orientation", slider.getType())
				.attr("disabled", slider.isDisabled(), false)
				.attr("range", range)
				.attr("displayTemplate", slider.getDisplayTemplate(), null)
				.attr("id",slider.getClientId())
				.callback("onSlideStart", "function(event,ui)",
						slider.getOnSlideStart())
				.callback("onSlide", "function(event,ui)", slider.getOnSlide())
				.callback("onSlideEnd", "function(event,ui)",
						slider.getOnSlideEnd());

		if (output != null) {
			wb.attr("display", output.getClientId(context));
		}

		encodeClientBehaviors(context, slider);

		wb.finish();
	}

	protected UIComponent getTarget(FacesContext context, SelectOneRadioSlider slider,
			String target) {
		if (target == null) {
			return null;
		} else {
			return SearchExpressionFacade.resolveComponent(context, slider, target);
		}
	}
}
