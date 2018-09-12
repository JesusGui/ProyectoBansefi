package mx.babel.arq.comun.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PaginacionBean implements Serializable{

	private static final long serialVersionUID = -1192971713175831963L;
	
	public static final int LONGITUD_PAGINA = 50;
	public static final int LLAVE_INICIO = 0;
	public static final int LLAVE_FIN = 1;
	
	private boolean masDatos;
	private Object ultimoDatoConsultaAnterior;
	private int pagina;
	private int numeroDatos;
	/**
	 * @return Atributo masDatos
	 */
	public Boolean getMasDatos() {
		return masDatos;
	}
	/**
	 * @param masDatos Atributo masDatos a definir
	 */
	public void setMasDatos(Boolean masDatos) {
		this.masDatos = masDatos;
	}
	/**
	 * @return Atributo ultimoDatoConsultaAnterior
	 */
	public Object getUltimoDatoConsultaAnterior() {
		return ultimoDatoConsultaAnterior;
	}
	/**
	 * @param ultimoDatoConsultaAnterior Atributo ultimoDatoConsultaAnterior a definir
	 */
	public void setUltimoDatoConsultaAnterior(Object ultimoDatoConsultaAnterior) {
		this.ultimoDatoConsultaAnterior = ultimoDatoConsultaAnterior;
	}
	/**
	 * @return Atributo pagina
	 */
	public int getPagina() {
		return pagina;
	}
	/**
	 * @param pagina Atributo pagina a definir
	 */
	public void setPagina(int pagina) {
		this.pagina = pagina;
	}
	/**
	 * @return Atributo numeroDatos
	 */
	public int getNumeroDatos() {
		return numeroDatos;
	}
	/**
	 * @param numeroDatos Atributo numeroDatos a definir
	 */
	public void setNumeroDatos(int numeroDatos) {
		this.numeroDatos = numeroDatos;
	}
	
	/**
	 * Lògica para adicionar los datos nuevos de la consulta y mover el nùmero de pàgina
	 * @param numeroDatos longitud de datos en consulta
	 */
	public void adicionaNumeroDatos(int numeroDatos){
		this.numeroDatos = this.numeroDatos + numeroDatos;
		this.pagina++;
	}
	
	/**
	 * Mapa con los indices de inicio y fin de la ùltima pàgina consultada
	 * @return mapa de indices
	 */
	public Map<Integer,Integer> indicePaginaActual(){
		Map<Integer, Integer> indices = new HashMap<Integer, Integer>();
		indices.put(PaginacionBean.LLAVE_INICIO,(this.pagina -1) * PaginacionBean.LONGITUD_PAGINA);
		indices.put(PaginacionBean.LLAVE_FIN, this.numeroDatos);
		return indices;
	}
}
