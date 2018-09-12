package mx.babel.arq.sesion.contexto.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase define los atributos que tendrá cada elemento del menú.
 * 
 * @author omar.marquez
 * 
 */

public class MenuBean {

	private String codigo;
	private String nombre;
	private String vinculoURL;
	private String imagenURL;
	private List<SubmenuBean> submenus;

	/**
	 * Constructor.
	 */
	public MenuBean() {
		super();
		submenus = new ArrayList<>();
	}

	/**
	 * Método que devuelve el código del menú.
	 * 
	 * @return codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Método que establece el código del menú.
	 * 
	 * @param codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Método que devuelve el nombre descriptivo de cada menú.
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Método que establece el nombre que tendrá el menú.
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Método que devuelve una URL.
	 * 
	 * @return vinculoURL
	 */
	public String getVinculoURL() {
		return vinculoURL;
	}

	/**
	 * Método que establece la URL de un recurso.
	 * 
	 * @param vinculoURL
	 */
	public void setVinculoURL(String vinculoURL) {
		this.vinculoURL = vinculoURL;
	}

	/**
	 * Método que devuelve la URL de la imagen asociada a un menú.
	 * 
	 * @return
	 */
	public String getImagenURL() {
		return imagenURL;
	}

	/**
	 * Método que establece la URL de la imagen asociada a un menú.
	 * 
	 * @param imagenURL
	 */
	public void setImagenURL(String imagenURL) {
		this.imagenURL = imagenURL;
	}

	/**
	 * Método que devuelve una lista de submenus.
	 * 
	 * @return submenus
	 */
	public List<SubmenuBean> getSubmenus() {
		return submenus;
	}

	/**
	 * Método que establece una lista de submenus.
	 * 
	 * @param submenus
	 */
	public void setSubmenus(List<SubmenuBean> submenus) {
		this.submenus = submenus;
	}

}