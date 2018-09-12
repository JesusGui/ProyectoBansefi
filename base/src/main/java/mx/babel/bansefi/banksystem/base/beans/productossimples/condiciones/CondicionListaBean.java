package mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.babel.bansefi.banksystem.base.beans.PreferenceItemBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.CondicionBean;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DualListModel;



public class CondicionListaBean extends CondicionBean {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Map<String, PreferenceItemBean> listaOriginal;
    private List<PreferenceItemBean> lista;
    private List<PreferenceItemBean> listaNoSeleccionados;
    private String preferente;


    public CondicionListaBean() {
        super("lista");
    }

    /**
     * @return the lista
     */
    public List<PreferenceItemBean> getLista() {
        return lista;
    }
    /**
     * @param lista the lista to set
     */
    public void setLista(final List<PreferenceItemBean> lista) {
        this.lista = lista;
    }

    /**
     * @param listaNoSeleccionados the listaNoSeleccionados to set
     */
    public void setListaNoSeleccionados(final List<PreferenceItemBean> listaNoSeleccionados) {
        listaOriginal = new HashMap<String, PreferenceItemBean>();
        if(null != listaNoSeleccionados){
            for(final PreferenceItemBean item : listaNoSeleccionados){
                listaOriginal.put(item.getId(), item);
            }
            if(this.lista == null){
                this.listaNoSeleccionados = listaNoSeleccionados;
            }else{
                this.listaNoSeleccionados = new ArrayList<PreferenceItemBean>();
                for(final PreferenceItemBean allListItem : listaNoSeleccionados){
                    boolean found = false;
                    for(final PreferenceItemBean selectedListItem:this.lista){
                        if(StringUtils.equals(allListItem.getId(), selectedListItem.getId())){
                            selectedListItem.setDesc(allListItem.getDesc());
                            found = true;
                            break;
                        }
                    }
                    if(! found){
                        this.listaNoSeleccionados.add(allListItem);
                    }
                }
            }
        }
    }

    public DualListModel<PreferenceItemBean> getDualList(){
        if(this.lista == null){
            this.lista = new ArrayList<>();
        }
        if(this.listaNoSeleccionados == null){
            this.listaNoSeleccionados = new ArrayList<>();
        }
        return new DualListModel<PreferenceItemBean>(this.lista, this.listaNoSeleccionados);
    };

    public void setDualList(final DualListModel<PreferenceItemBean> dualListModel){
    }


    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .appendSuper(super.hashCode())
            .append(lista).toHashCode();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CondicionListaBean)) {
            return false;
        }
        final CondicionListaBean castObj = (CondicionListaBean) obj;
        final EqualsBuilder eq = new EqualsBuilder();
        if(lista != null && castObj.lista != null
             &&lista.size() == castObj.lista.size()){
            //TODO corregir este desagisado
            for(int i = 0; i<lista.size(); i++){
                eq.append(lista.get(i), castObj.lista.get(i));
            }
        }else{
            eq.appendSuper(false);
        }
        return eq.isEquals();

    }

    /**
     * @return the sourceList
     */
    public String getSourceList() {
        final StringBuilder sb = new StringBuilder();
        if(this.lista != null){
            for(final PreferenceItemBean item :  this.lista){
                sb.append(item.getId()).append(";");
            }
        }
        return sb.toString();
    }

    /**
     * @param sourceList the sourceList to set
     */
    public void setSourceList(final String sourceList) {
        if(StringUtils.contains(sourceList, ";")){
            final String[] splitIds = sourceList.split(";");
            this.lista = new ArrayList<>();
            for(final String st : splitIds){
                if(this.listaOriginal.containsKey(st)){
                    this.lista.add(this.listaOriginal.get(st));
                }
            }
        }
    }

    /**
     * @return the targetList
     */
    public String getTargetList() {
        final StringBuilder sb = new StringBuilder();
        if(this.listaNoSeleccionados != null){
            for(final PreferenceItemBean item :  this.listaNoSeleccionados){
                sb.append(item.getId()).append(";");
            }
        }
        return sb.toString();
    }

    /**
     * @param targetList the targetList to set
     */
    public void setTargetList(final String targetList) {
        if(StringUtils.contains(targetList, ";")){
            final String[] splitIds = targetList.split(";");
            this.listaNoSeleccionados = new ArrayList<>();
            for(final String st : splitIds){
                if(this.listaOriginal.containsKey(st)){
                    this.listaNoSeleccionados.add(this.listaOriginal.get(st));
                }
            }
        }
    }

    /**
     * @param preferente the preferente to set
     */
    public void setPreferente(final String preferente) {
        for(final PreferenceItemBean item : this.lista){
            if(StringUtils.equals(preferente, item.getId())){
                item.setPreferente(true);
            }else{
                item.setPreferente(false);
            }
        }
    }

    /**
     * @param preferente the preferente to set
     */
    public String getPreferente() {
        for(final PreferenceItemBean item : this.lista){
            if(item.isPreferente()){
                return item.getId();
            }
        }
        return "";
    }

}
