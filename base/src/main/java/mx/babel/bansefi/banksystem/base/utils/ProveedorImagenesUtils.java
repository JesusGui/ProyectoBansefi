package mx.babel.bansefi.banksystem.base.utils;

import mx.babel.bansefi.banksystem.base.enums.ImagenEnum;

/**
 * Clase de utilidad que permite obtener la URL de un ImagenEnum.
 * 
 * @author omar.marquez
 */
public class ProveedorImagenesUtils {

	/**
	 * Método que devuelve la ruta de un ImagenEnum a partir de un valor de
	 * entrada tipo entero.
	 * 
	 * @param valor
	 * @return url del ImagenEnum
	 */
	public static String obtenerUrlImagen(int valor) {
		switch (valor) {
		case 1:
			return ImagenEnum.UNO.getUrlImagen();
		case 2:
			return ImagenEnum.DOS.getUrlImagen();
		case 3:
			return ImagenEnum.TRES.getUrlImagen();
		case 4:
			return ImagenEnum.CUATRO.getUrlImagen();
		case 5:
			return ImagenEnum.CINCO.getUrlImagen();
		case 6:
			return ImagenEnum.SEIS.getUrlImagen();
		case 7:
			return ImagenEnum.SIETE.getUrlImagen();
		case 8:
			return ImagenEnum.OCHO.getUrlImagen();
		case 9:
			return ImagenEnum.NUEVE.getUrlImagen();
		case 10:
			return ImagenEnum.DIEZ.getUrlImagen();
		case 11:
			return ImagenEnum.ONCE.getUrlImagen();
		case 12:
			return ImagenEnum.DOCE.getUrlImagen();
		case 13:
			return ImagenEnum.TRECE.getUrlImagen();
		case 14:
			return ImagenEnum.CATORCE.getUrlImagen();
		case 15:
			return ImagenEnum.QUINCE.getUrlImagen();
		case 16:
			return ImagenEnum.DIECISEIS.getUrlImagen();
		case 17:
			return ImagenEnum.DIECISIETE.getUrlImagen();
		case 18:
			return ImagenEnum.DIECIOCHO.getUrlImagen();
		case 19:
			return ImagenEnum.DIECINUEVE.getUrlImagen();
		case 20:
			return ImagenEnum.VEINTE.getUrlImagen();
		case 21:
			return ImagenEnum.VEINTIUNO.getUrlImagen();
		case 22:
			return ImagenEnum.VEINTIDOS.getUrlImagen();
		case 23:
			return ImagenEnum.VEINTITRES.getUrlImagen();
		case 24:
			return ImagenEnum.VEINTICUATRO.getUrlImagen();
		case 25:
			return ImagenEnum.VEINTICINCO.getUrlImagen();
		case 26:
			return ImagenEnum.VEINTISEIS.getUrlImagen();
		case 27:
			return ImagenEnum.VEINTISIETE.getUrlImagen();
		case 28:
			return ImagenEnum.VEINTIOCHO.getUrlImagen();
		case 29:
			return ImagenEnum.VEINTINUEVE.getUrlImagen();
		case 30:
			return ImagenEnum.TREINTA.getUrlImagen();
		case 31:
			return ImagenEnum.TREINTAYUNO.getUrlImagen();
		case 32:
			return ImagenEnum.TREINTAYDOS.getUrlImagen();
		case 33:
			return ImagenEnum.TREINTAYTRES.getUrlImagen();
		default:
			return ImagenEnum.PREDETERMINADA.getUrlImagen();
		}
	}

}