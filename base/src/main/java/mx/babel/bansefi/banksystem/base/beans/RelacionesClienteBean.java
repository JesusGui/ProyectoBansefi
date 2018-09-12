package mx.babel.bansefi.banksystem.base.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.babel.bansefi.banksystem.base.beans.domain.GestorRelacionadoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.GrupoRelacionadoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.PersonaMoralAccionistaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.PersonaMoralFuncionarioBean;
import mx.babel.bansefi.banksystem.base.beans.domain.PersonaMoralRBancariaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.PersonaMoralRComercialBean;
import mx.babel.bansefi.banksystem.base.beans.domain.PersonaRelacionadaBean;

/**
 * Clase que define todas las listas de entidades que pueden ser relacionadas a
 * un cliente (accionistas, funcionarios, referencias comerciales, referencias
 * bancarias, personas, grupos y gestores).
 * 
 * @author omar.marquez
 * 
 */
public class RelacionesClienteBean implements Serializable{

	private static final long serialVersionUID = 8519962964708097745L;
	
	private List<PersonaMoralAccionistaBean> accionistas;
	private List<PersonaMoralFuncionarioBean> funcionarios;
	private List<PersonaMoralRComercialBean> referenciasComerciales;
	private List<PersonaMoralRBancariaBean> referenciasBancarias;
	private List<PersonaRelacionadaBean> personas;
	private List<GrupoRelacionadoBean> grupos;
	private List<GestorRelacionadoBean> gestores;

	/**
	 * Constructor.
	 */
	public RelacionesClienteBean() {
		this.accionistas = new ArrayList<>();
		this.funcionarios = new ArrayList<>();
		this.referenciasComerciales = new ArrayList<>();
		this.referenciasBancarias = new ArrayList<>();
		this.personas = new ArrayList<>();
		this.grupos = new ArrayList<>();
		this.gestores = new ArrayList<>();
	}

	/**
	 * Método que devuelve una lista de accionistas.
	 * 
	 * @return lista de accionistas
	 */
	public List<PersonaMoralAccionistaBean> getAccionistas() {
		return accionistas;
	}

	/**
	 * Método que establece una lista de accionistas.
	 * 
	 * @param accionistas
	 */
	public void setAccionistas(List<PersonaMoralAccionistaBean> accionistas) {
		this.accionistas = accionistas;
	}

	/**
	 * Método que devuelve una lista de funcionarios.
	 * 
	 * @return lista de funcionarios
	 */
	public List<PersonaMoralFuncionarioBean> getFuncionarios() {
		return funcionarios;
	}

	/**
	 * Método que establece una lista de funcionarios.
	 * 
	 * @param funcionarios
	 */
	public void setFuncionarios(List<PersonaMoralFuncionarioBean> funcionarios) {
		this.funcionarios = funcionarios;
	}

	/**
	 * Método que devuelve una lista de referencias comerciales.
	 * 
	 * @return lista de referencias comerciales.
	 */
	public List<PersonaMoralRComercialBean> getReferenciasComerciales() {
		return referenciasComerciales;
	}

	/**
	 * Método que establece una lista de referencias comerciales.
	 * 
	 * @param referenciasComerciales
	 */
	public void setReferenciasComerciales(
			List<PersonaMoralRComercialBean> referenciasComerciales) {
		this.referenciasComerciales = referenciasComerciales;
	}

	/**
	 * Método que devuelve una lista de referencias bancarias.
	 * 
	 * @return lista de referencias bancarias
	 */
	public List<PersonaMoralRBancariaBean> getReferenciasBancarias() {
		return referenciasBancarias;
	}

	/**
	 * Método que establece una lista de referencias bancarias.
	 * 
	 * @param referenciasBancarias
	 */
	public void setReferenciasBancarias(
			List<PersonaMoralRBancariaBean> referenciasBancarias) {
		this.referenciasBancarias = referenciasBancarias;
	}

	/**
	 * Método que devuelve una lista de personas.
	 * 
	 * @return lista de personas
	 */
	public List<PersonaRelacionadaBean> getPersonas() {
		return personas;
	}

	/**
	 * Método que establece una lista de personas.
	 * 
	 * @param personas
	 */
	public void setPersonas(List<PersonaRelacionadaBean> personasConsultadas) {
		this.personas = personasConsultadas;
	}

	/**
	 * Método que devuelve una lista de grupos.
	 * 
	 * @return lista de grupos
	 */
	public List<GrupoRelacionadoBean> getGrupos() {
		return grupos;
	}

	/**
	 * Método que establece una lista de grupos.
	 * 
	 * @param grupos
	 */
	public void setGrupos(List<GrupoRelacionadoBean> grupos) {
		this.grupos = grupos;
	}

	/**
	 * Método que devuelve una lista de gestores.
	 * 
	 * @return lista de gestores
	 */
	public List<GestorRelacionadoBean> getGestores() {
		return gestores;
	}

	/**
	 * Método que establece una lista de gestores.
	 * 
	 * @param gestores
	 */
	public void setGestores(List<GestorRelacionadoBean> gestores) {
		this.gestores = gestores;
	}

}