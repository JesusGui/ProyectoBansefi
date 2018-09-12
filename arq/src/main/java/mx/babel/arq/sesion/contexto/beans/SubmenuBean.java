package mx.babel.arq.sesion.contexto.beans;

/**
 * Esta clase define los atributos que tendrán los submenús.
 * 
 * @author omar.marquez
 * 
 */

public class SubmenuBean {

	private String idMenu;
	private String codigo;
	private String nombre;
	private String vinculoURL;
	private String imagenURL;
	private boolean operativaFrecuente;

	/**
	 * Constructor.
	 * 
	 * @param idMenu
	 * @param codigo
	 * @param nombre
	 * @param vinculoURL
	 * @param imagenURL
	 * @param isOperativaFrecuente
	 */
	public SubmenuBean(String idMenu, String codigo, String nombre,
			String vinculoURL, String imagenURL, boolean isOperativaFrecuente) {
		this.idMenu = idMenu;
		this.codigo = codigo;
		this.nombre = nombre;
		this.vinculoURL = vinculoURL;
		this.imagenURL = imagenURL;
		this.operativaFrecuente = isOperativaFrecuente;
	}

	/**
	 * Constructor.
	 * 
	 * @param idMenu
	 * @param codigo
	 * @param nombre
	 * @param vinculoURL
	 * @param imagenURL
	 */
	public SubmenuBean(String idMenu, String codigo, String nombre,
			String vinculoURL, String imagenURL) {
		this.idMenu = idMenu;
		this.codigo = codigo;
		this.nombre = nombre;
		this.vinculoURL = vinculoURL;
		this.imagenURL = imagenURL;
	}

	/**
	 * Constructor.
	 * 
	 * @param codigo
	 * @param nombre
	 * @param vinculoURL
	 * @param imagenURL
	 */
	public SubmenuBean(String codigo, String nombre, String vinculoURL,
			String imagenURL) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.vinculoURL = vinculoURL;
		this.imagenURL = imagenURL;
	}

	/**
	 * Constructor.
	 */
	public SubmenuBean() {
		super();
		operativaFrecuente = false;
	}

	/**
	 * Método que devuelve el ID del menú padre.
	 * 
	 * @return idMenu
	 */
	public String getIdMenu() {
		return idMenu;
	}

	/**
	 * Método que establece el ID del menú padre.
	 * 
	 * @param idMenu
	 */
	public void setIdMenu(String idMenu) {
		this.idMenu = idMenu;
	}

	/**
	 * Método que devuelve el código del submenú.
	 * 
	 * @return codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Método que establece el código del submenú.
	 * 
	 * @param codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Método que devuelve el nombre descriptivo del submenú.
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Método que establece el nombre del submenú.
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
	 * Método que devuelve la URL de la imagen asociada a un submenú.
	 * 
	 * @return imagenURL
	 */
	public String getImagenURL() {
		return imagenURL;
	}

	/**
	 * Método que establece la URL de la imagen asociada a un submenú.
	 * 
	 * @param imagenURL
	 */
	public void setImagenURL(String imagenURL) {
		this.imagenURL = imagenURL;
	}

	/**
	 * Método que devuelve un indicador booleano para determinar sí la operativa
	 * es o no frecuente.
	 * 
	 * @return operativaFrecuente
	 */
	public boolean isOperativaFrecuente() {
		return operativaFrecuente;
	}

	/**
	 * Método que establece el valor del indicador booleano que determina sí la
	 * operativa es o no frecuente.
	 * 
	 * @param operativaFrecuente
	 */
	public void setOperativaFrecuente(boolean operativaFrecuente) {
		this.operativaFrecuente = operativaFrecuente;
	}

}