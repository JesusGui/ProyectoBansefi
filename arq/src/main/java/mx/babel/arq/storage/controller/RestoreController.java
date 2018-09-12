package mx.babel.arq.storage.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import mx.babel.arq.comun.utils.HashUtils;
import mx.babel.arq.storage.beans.StorageMgrBean;

import org.springframework.stereotype.Controller;


/**Funcionalidad que permite restaurar los datos de un Controlador
 * y lanzar su flujo en el paso en el que fue pausado.
 * @author joseluis.pena
 *
 */
@Controller
@ViewScoped
public class RestoreController {
	
	private List<SelectItem> restoreList;
	
	private String selectedIndex;
	private String dataToRestore;
	
	public void setPlainList(String plainList){
		if(null!=plainList && plainList.length()>0){
			restoreList = new ArrayList<SelectItem>();
			String[] splittedList = plainList.split("\\|");
			String[] splittedIndex = null;
			for(String keys:splittedList){
				splittedIndex = keys.split("\\+");
				restoreList.add(new SelectItem(keys, splittedIndex[2]+" "+ splittedIndex[1]));
			}
		}
	}
	public String getPlainList(){
		return "";
	}
	
	
	/**
	 * @return the restoreList
	 */
	public List<SelectItem> getRestoreList() {
		return restoreList;
	}



	/**
	 * @param restoreList the restoreList to set
	 */
	public void setRestoreList(List<SelectItem> restoreList) {
		this.restoreList = restoreList;
	}



	public String restoreExecutingFlow(){
		String[] splittedIndex = selectedIndex.split("\\+");
		byte[] restoredBytes = HashUtils.decodeB64(splittedIndex[0]);
		
		String keyData = new String(restoredBytes);
		String[] indexData = keyData.split("\\+");
		FacesContext context = FacesContext.getCurrentInstance();

		String beanName = "#"+"{" +indexData[0].substring(0, 1).toLowerCase()+indexData[0].substring(1)+"}";
		StorageMgrBean bean = (StorageMgrBean) context.getApplication().evaluateExpressionGet(context, beanName, StorageMgrBean.class);
		bean.restoreContext(dataToRestore);
		return indexData[1];
	}
	/**
	 * @return the selectedIndex
	 */
	public String getSelectedIndex() {
		return selectedIndex;
	}
	/**
	 * @param selectedIndex the selectedIndex to set
	 */
	public void setSelectedIndex(String selectedIndex) {
		this.selectedIndex = selectedIndex;
	}
	/**
	 * @return the dataToRestore
	 */
	public String getDataToRestore() {
		return null;
	}
	/**
	 * @param dataToRestore the dataToRestore to set
	 */
	public void setDataToRestore(String dataToRestore) {
		this.dataToRestore = dataToRestore;
	}
	
	
	
	
}
