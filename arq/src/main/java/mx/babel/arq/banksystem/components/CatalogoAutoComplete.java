package mx.babel.arq.banksystem.components;

import java.util.ArrayList;
import java.util.List;

import javax.el.ELContext;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.sesion.contexto.beans.BSContexto;
import mx.babel.arq.task.ICatalogoLoaderTask;

import org.apache.commons.lang.StringUtils;
import org.primefaces.component.autocomplete.AutoComplete;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * Clase encargada de extender el componente primeFaces AutoComplete, que incluye
 * la recuperacion de los catalogos y la generacion del listado de sugerencias.
 * @author joseluis.pena
 *
 */
public class CatalogoAutoComplete extends AutoComplete {

	public static final String COMPONENT_FAMILY = "mx.babel.arq.banksystem.components";

    private List suggestions = null;

    private enum PropertyKeys {
    	catalog, catalogSource, maxResults, filterResults
    }

	@Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }

    public String getCatalog() {
        return (String) getStateHelper().get(PropertyKeys.catalog);
    }

    public String getCatalogSource() {
        return (String) getStateHelper().eval(PropertyKeys.catalogSource, "catalogoUtils");
    }

    public String getFilterResults() {
        return (String) getStateHelper().eval(PropertyKeys.filterResults);
    }
    @Override
    public int getMaxResults() {
		return (java.lang.Integer) getStateHelper().eval(PropertyKeys.maxResults, 5);
	}

    @Override
    public void setMaxResults(final int _maxResults) {
		getStateHelper().put(PropertyKeys.maxResults, _maxResults);
	}
    public void setFilterResults(final String filterResults) {
		getStateHelper().put(PropertyKeys.filterResults, filterResults);
	}
    private List<CatalogoBean> getCatalogDataList() {
    	final ICatalogoLoaderTask catalogoUtils = this.getCatalogUtil();
    	final String catalog = this.getCatalog();
        if(catalogoUtils != null){
        	if (catalog !=null){
        		return catalogoUtils.getCatalogo(catalog);
        	}else{
        		// Se incluye la entidad del contexto en caso de no venir informado
            	// el catalogo a consultar
            	return catalogoUtils.getCatalogo(getEntidad());
        	}
        }
    	return new ArrayList<CatalogoBean>();
	}

    public void setCatalog(final String catalog) {
        getStateHelper().put(PropertyKeys.catalog, catalog);
    }

    public void setCatalogSource(final String catalogSource) {
        getStateHelper().put(PropertyKeys.catalogSource, catalogSource);
    }

    private String getEntidad() {
        String entidad ;

        try {
            entidad = ((BSContexto) getSession().getAttribute("bsctxt")).getUsuario().getEntidad();
        } catch (final IllegalStateException e) {
            throw new FacesException(e.getMessage(), e);
        }
        if (entidad == null) {
        	final String msg = "No se pudo recuperar la entidad del contexto";
        	throw new NoControlableException(msg, this.getClass().getName()+": "+msg);
        }
        return entidad;
    }

    private HttpSession getSession() {
		final ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		return attr.getRequest().getSession(false);
	}

    private ICatalogoLoaderTask getCatalogUtil() {
        final FacesContext fc = FacesContext.getCurrentInstance();
        Object bean;

        try {
            final ELContext elContext = fc.getELContext();
            bean = elContext.getELResolver().getValue(elContext, null, this.getCatalogSource());
        } catch (final RuntimeException e) {
            throw new FacesException(e.getMessage(), e);
        }

        if (bean == null || !(bean instanceof ICatalogoLoaderTask)) {
        	final String msg = "Managed bean with name 'catalogoUtils'"
            		+ " was not found.";
        	throw new NoControlableException(msg, this.getClass().getName()+": "+msg+" Check your faces-config.xml or @ManagedBean annotation.");
        }
        return (ICatalogoLoaderTask)bean;
    }

    @Override
    public void broadcast(final javax.faces.event.FacesEvent event) throws javax.faces.event.AbortProcessingException {

		final FacesContext facesContext = getFacesContext();
		if(event instanceof org.primefaces.event.AutoCompleteEvent) {
			final String query = ((org.primefaces.event.AutoCompleteEvent) event).getQuery();
			if(query != null){
				suggestions = this.queryByName(query);
			}
            if(suggestions == null) {
                suggestions = new ArrayList();
            }

            facesContext.renderResponse();
		}
	}

    @Override
    public List getSuggestions() {
        return this.suggestions;
    }

    private List<CatalogoBean> queryByName(final String name){
    	FacesContext context = FacesContext.getCurrentInstance();

		String beanName = "#" + "{"
				+ this.getFilterResults() + "}";
		List<CatalogoBean> listFilters = (List) context.getApplication()
				.evaluateExpressionGet(context, beanName, List.class);	
    	
    	final Integer maxResults = this.getMaxResults();
		// Assumed search using the startsWith
		final List<CatalogoBean> queried = new ArrayList<CatalogoBean>(maxResults);
		final List<CatalogoBean> catalogoDataList = this.getCatalogDataList();
		if(catalogoDataList != null){
			final int tamanyo = catalogoDataList.size();
			for(int i = 0; i<tamanyo && queried.size()<maxResults; i++){
				if(catalogoDataList.get(i).getDescripcionL().toUpperCase().contains(name.toUpperCase())){
					if(listFilters != null){
						if(showValueNoFilter(catalogoDataList.get(i), listFilters)){
							queried.add(catalogoDataList.get(i));
						}
					}else{
						queried.add(catalogoDataList.get(i));
					}
				}
			}
		}
		return queried;
	}

    public CatalogoBean getSelectedValue(final String submittedItemValue) {
        final List<CatalogoBean> catalogoDataList = this.getCatalogDataList();
        if(catalogoDataList != null){
            final int tamanyo = catalogoDataList.size();
            for(final CatalogoBean bean : catalogoDataList){
                if(StringUtils.endsWithIgnoreCase(bean.getClaveFila(), submittedItemValue)){
                    return bean;
                }
            }
        }
        return null;
    }
    
    private Boolean showValueNoFilter(CatalogoBean catalogoBean, List<CatalogoBean> filterValues){
    	for(CatalogoBean filterValue : filterValues){
    		if(catalogoBean.equals(filterValue)){
    			return false;
    		}
    	}
    	return true;
    }

}
