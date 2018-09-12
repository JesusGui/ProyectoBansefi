package mx.babel.bansefi.banksystem.base.beans.domain;

import java.util.ArrayList;
import java.util.List;

import mx.babel.bansefi.banksystem.base.beans.PosicionCuentaBean;

/**
 * Clase para definir el modelo de Clientes que son Grupos.
 * @author mario.montesdeoca
 * 
 */
public class ClienteGrupoBean extends ClienteBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4974828234315767591L;

	private String tipoGrupo;
	
	private List<ClienteBean> integrantes;
	
	private PosicionCuentaBean posicion;
	
	/**
	 * @return Atributo tipoGrupo
	 */
	public String getTipoGrupo() {
		return tipoGrupo;
	}

	/**
	 * @param tipoGrupo Atributo tipoGrupo a definir
	 */
	public void setTipoGrupo(String tipoGrupo) {
		this.tipoGrupo = tipoGrupo;
	}

	/**
	 * @return Atributo integrantes
	 */
	public List<ClienteBean> getIntegrantes() {
		if(integrantes == null){
			integrantes = new ArrayList<ClienteBean>();
		}
		return integrantes;
	}

	/**
	 * @param integrantes Atributo integrantes a definir
	 */
	public void setIntegrantes(List<ClienteBean> integrantes) {
		this.integrantes = integrantes;
	}

	@Override
	public TipoCliente getTipo(){
		return TipoCliente.GRUPO;
	}

	/**
	 * @return the posicion
	 */
	public PosicionCuentaBean getPosicion() {
		return posicion;
	}

	/**
	 * @param posicion the posicion to set
	 */
	public void setPosicion(PosicionCuentaBean posicion) {
		this.posicion = posicion;
	}
}
