package mx.babel.bansefi.banksystem.cuentas.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class InformacionNumericaBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3278585622360478497L;
	private Integer numTramo;
	private BigDecimal tae;
	
	/**
	 * Constructor
	 */
	public InformacionNumericaBean(){
		super();
	}
	
	/**
	 * 
	 * @return Atributo numTramo
	 */
	public Integer getNumTramo() {
		return numTramo;
	}
	
	/**
	 * 
	 * @param numTramo Atributo numTramo to set
	 */
	public void setNumTramo(Integer numTramo) {
		this.numTramo = numTramo;
	}
	
	/**
	 * 
	 * @return Atributo tae
	 */
	public BigDecimal getTae() {
		return tae;
	}
	
	/**
	 * 
	 * @param tae Atributo tae to set
	 */
	public void setTae(BigDecimal tae) {
		this.tae = tae;
	}

}
