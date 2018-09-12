package mx.babel.arq.storage.beans;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.HashUtils;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import com.sun.faces.context.flash.ELFlash;

/**
 * Clase abstracta que contiene la logica para serializar todos los beans del
 * contexto utilizados en un flujo para permitir su almacenado en el navegador.
 *
 * @author joseluis.pena
 *
 */
public abstract class StorageMgrBean {

	public static final String LAST_ACTION = "lastAction";

	private String context = null;
	private String flowId = null;
	private String lastAction;
	private String index;
    private String goHomeId = null;

	protected void restoreflash() {
		final Flash flash = FacesContext.getCurrentInstance()
				.getExternalContext().getFlash();
		if (flash.containsKey(StorageMgrBean.LAST_ACTION)) {
			this.lastAction = (String) flash.get(StorageMgrBean.LAST_ACTION);
		}
	}

	/**
	 * Metodo que debe retornar todos aquellos beans de datos utilizados en el
	 * managed bean junto a un id que permita identificarlos en el futuro de
	 * cara a restaurar sus datos.
	 *
	 * @return mapa con todos los objetos almacenados en el managedBean
	 *         identificados por un id que lo identifique
	 */
	protected abstract Map<String, Object> getBeanList();

	/**
	 * Metodo encargado de recibir un mapa de datos y realizar la
	 * correspondencia con los beans declarados en el managedBean.
	 *
	 * @param beanList
	 *            mapa con los objetos almacenados y un id que los identifique y
	 *            permita resturarlos a los beans contenidos en el managedBean
	 */
	protected abstract void setBeanList(Map<String, Object> beanList);

	/**
	 * Metodo que devuelve el nombre del flujo controlado por el managedBean.
	 * Debe ser unico para permitir utilizarlo como clave primaria en el
	 * almacenamiento local del navegador.
	 *
	 * @return nombre del flujo
	 */
	protected abstract String getNombreFlujo();

	public String getContext() {
		final String result = this.context;
		this.context = null;
		return result;
	}

	public void setContext(final String context) {
		this.context = context;
	}

	public void generateContext() {
		final Map<String, Object> beansToSerialize = this.getBeanList();
		if (null != beansToSerialize && !beansToSerialize.isEmpty()) {
			this.context = this.serializeContext(beansToSerialize);
			this.flowId = this.generateFlowId();
		}

	}

	private String generateFlowId() {
		final DateTime now = DateTime.now();
		final String time = now.toString("HH:mm a");
		final String date = now.toString("dd-MM-yyyy");
		final String flujoId = this.getClass().getSimpleName() + "+"
				+ this.getLastAction() + "+" + now.getMillisOfSecond();
		final String machineInfo = HashUtils.encodeB64(flujoId.getBytes());
		final String humanInfo = this.getNombreFlujo() + "+" + time + "+"
				+ date;
		return machineInfo + "+" + humanInfo;
	}

	public void restoreContext(final String data) {
		final Map<String, Object> deserializedBeans = deserializeContext(data);
		if (null != deserializedBeans && !deserializedBeans.isEmpty()) {
			this.setBeanList(deserializedBeans);
		}
	}

	private String serializeContext(final Map<String, Object> map) {
		final ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			final ObjectOutputStream oos = new ObjectOutputStream(bos);

			oos.writeObject(map);
			oos.close();
		} catch (final IOException e) {
			throw new NoControlableException(
					"Ha ocurrido un problema al guardar el proceso", e);
		}
		// String to Store
		return HashUtils.encodeB64(bos.toByteArray());
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> deserializeContext(final String context) {
		final byte[] restoredBytes = HashUtils.decodeB64(context);
		Map<String, Object> anotherMap = null;
		final ByteArrayInputStream bis = new ByteArrayInputStream(restoredBytes);
		try {
			final ObjectInputStream ois = new ObjectInputStream(bis);
			anotherMap = (Map<String, Object>) ois.readObject();
			ois.close();
		} catch (final IOException e) {
			throw new NoControlableException(
					"Ha ocurrido un problema al recuperar el proceso", e);
		} catch (final ClassNotFoundException e) {
			throw new NoControlableException(
					"Ha ocurrido un problema al recuperar el proceso", e);
		}

		return anotherMap;
	}

	public String getFlowId() {
		final String result = this.flowId;
		this.flowId = null;
		return result;
	}

	/**
	 * @return the lastAction
	 */
	public String getLastAction() {
		return lastAction;
	}

	/**
	 * @param lastAction
	 *            the lastAction to set
	 */
	public void setLastAction(final String lastAction) {
		ELFlash.getFlash().put(StorageMgrBean.LAST_ACTION, lastAction);
		this.lastAction = lastAction;
	}

	/**
	 * @return the index
	 */
	public String getIndex() {
		return index;
	}

	/**
	 * @param index
	 *            the index to set
	 */
	public void setIndex(final String index) {
	}

	/**
	 * @param index
	 *            the restoreIndex to set
	 */
	public void setRestoreIndex(final String index) {
		this.index = index;
	}

	/**
     * @return the goHomeId
     */
    public String getGoHomeId() {
        return goHomeId;
    }

    /**
     * @param goHomeId the goHomeId to set
     */
    public void setGoHomeId(final String goHomeId) {
        this.goHomeId = goHomeId;
    }

    public String cancelarProceso(){
	    if(StringUtils.isNotBlank(goHomeId)) {
	        return goHomeId;
	    }
        return "/views/inicio/inicio?faces-redirect=true";
	}

}