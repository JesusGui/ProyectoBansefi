package mx.babel.bansefi.banksystem.base.enums;

/**
 * Enumerado que contiene las URL's de las imágenes que serán cargadas en el
 * dashboard.
 * 
 * @author omar.marquez
 * 
 */
public enum ImagenEnum {
	
	PREDETERMINADA("../../img/dashboard/generica.png"), // Imagen cargada por defecto
	UNO("../../img/dashboard/consulta-de-saldo.png"), // Consulta Saldo
	DOS("../../img/dashboard/consulta-de-movimientos.png"), // Consulta Movimientos
	TRES("../../img/dashboard/deposito.png"), // Depósito
	CUATRO("../../img/dashboard/retiro.png"), // Retiro
	CINCO("../../img/dashboard/generica.png"), // Mantenimiento Entidades
	SEIS("../../img/dashboard/generica.png"), // Alta Mantenimiento Centros
	SIETE("../../img/dashboard/alta-de-cliente.png"), // Alta Mantenimiento Empleados
	OCHO("../../img/dashboard/alta-de-cliente.png"), // Alta Mantenimiento Clientes
	NUEVE("../../img/dashboard/alta-de-cuenta.png"), // Alta Mantenimiento Cuentas
	DIEZ("../../img/dashboard/busqueda.png"), // Consulta Clientes
	ONCE("../../img/dashboard/busqueda.png"), // Consulta Cuentas
	DOCE("../../img/dashboard/busqueda.png"), // Consulta Entidades
	TRECE("../../img/dashboard/busqueda.png"), // Consulta Centro
	CATORCE("../../img/dashboard/busqueda.png"), // Consulta Empleado
	QUINCE("../../img/dashboard/arqueo-puesto.png"), // Arqueo de puesto
	DIECISEIS("../../img/dashboard/arqueo-centro.png"), // Arqueo de centro
	DIECISIETE("../../img/dashboard/generica.png"), // Contadores puesto
	DIECIOCHO("../../img/dashboard/generica.png"), // Contadores centro
	DIECINUEVE("../../img/dashboard/cierre-contable-puesto.png"), // Cierre contable de oficina
	VEINTE("../../img/dashboard/diario-electronico.png"), // Diario electrónico
	VEINTIUNO("../../img/dashboard/generica.png"), // Consulta anotaciones
	VEINTIDOS("../../img/dashboard/generica.png"), // Consulta domicilios
	VEINTITRES("../../img/dashboard/generica.png"), // Bloqueo de cuenta
	VEINTICUATRO("../../img/dashboard/alta-de-cliente-moral.png"), // Alta persona moral
	VEINTICINCO("../../img/dashboard/generica.png"), // Alta anotaciones
	VEINTISEIS("../../img/dashboard/generica.png"), // Apuntes cuentas mutuas y ajenas
	VEINTISIETE("../../img/dashboard/generica.png"), // Consulta notificaciones
	VEINTIOCHO("../../img/dashboard/generica.png"), // Retenciones
	VEINTINUEVE("../../img/dashboard/generica.png"), // Gestor de incidencias
	TREINTA("../../img/dashboard/generica.png"), // Anulaciones
	TREINTAYUNO("../../img/dashboard/generica.png"), // Ficha de empleado
	TREINTAYDOS("../../img/dashboard/generica.png"), // Alta de notificaciones
	TREINTAYTRES("../../img/dashboard/generica.png"); // Recibos no domiciliados
	
	private String urlImagen;

	/**
	 * Constructor.
	 * 
	 * @param urlImagen
	 */
	private ImagenEnum(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	/**
	 * Método que devuelve la URL de la imagen asociada al enumerado.
	 * 
	 * @return URL de la imagen
	 */
	public String getUrlImagen() {
		return urlImagen;
	}

	/**
	 * Método que establece la URL de la imagen asociada al enumerado.
	 * 
	 * @param urlImagen
	 */
	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

}