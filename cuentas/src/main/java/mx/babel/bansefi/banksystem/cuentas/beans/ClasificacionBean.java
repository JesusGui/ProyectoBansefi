package mx.babel.bansefi.banksystem.cuentas.beans;

import java.io.Serializable;

public class ClasificacionBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8347154018795129235L;
	private String codigoActivo;
	private String situacionIrregular;
	private String situacionReclama;
	private String codigoGarantia;
	private String descGarantiaContable;
	private String garantiaCirbe;
	private String persContable;
	private String residente;
	private String socio;
	private String finalidad;
	private String cnae;
	
	
	/**Mètodo que obtiene el còdigo activo
	 * 
	 * @return codigoActivo
	 */
	public String getCodigoActivo(){
		return codigoActivo;
	}
	
	/**Mètodo que establece el còdigo activo 
	 * 
	 * @param codigoActivo
	 */
	public void setCodigoActivo(String codigoActivo){
		this.codigoActivo = codigoActivo;
	}
		
	/**Mètodo que obtiene la situaciòn irregular
	 * 
	 * @return situacionIrregular
	 */
	public String getSituacionIrregular(){
		return situacionIrregular;
	}
	
	/**Mètodo que establece la situaciòn irregular
	 * 
	 * @param situacionIrregular
	 */
	public void setSituacionIrregular(String situacionIrregular){
		this.situacionIrregular = situacionIrregular;
	}
		
	/**Mètodo que obtiene la situaciòn de reclamaciòn
	 * 
	 * @return situacionReclama
	 */
	public String getSituacionReclama(){
		return situacionReclama;
	}
	
	/**Mètodo que establece la situaciòn de reclamaciòn
	 * 
	 * @param situacionReclama
	 */
	public void setSituacionReclama(String situacionReclama){
		this.situacionReclama = situacionReclama;
	}
		
	/**Mètodo que obtiene el còdigo de garantìa
	 * 
	 * @return codigoGarantia
	 */
	public String getCodigoGarantia(){
		return codigoGarantia;
	}
	
	/**Mètodo que establece el còdigo de garantìa
	 * 
	 * @param codigoGarantia
	 */
	public void setCodigoGarantia(String codigoGarantia){
		this.codigoGarantia = codigoGarantia;
	}
		
	/**Método que obtiene la garantía contable
	 * 
	 * @return garantiContable
	 */
	public String getDescGarantiaContable(){
		return descGarantiaContable;
	}
	
	/**Método que establece la descripciòn de la garantía contable
	 * 
	 * @param garantiaContable
	 */
	public void setDescGarantiaContable(String descGarantiaContable){
		this.descGarantiaContable = descGarantiaContable;
	}
	
	/**Mètodo que obtiene la garantìa Cirbe
	 * 
	 * @return garantiaCirbe
	 */
	public String getGarantiaCirbe(){
		return garantiaCirbe;
	}
	
	/**Mètodo que establece la garantia Cirbe
	 * 
	 * @param garantiaCirbe
	 */
	public void setGarantiaCirbe(String garantiaCirbe){
		this.garantiaCirbe = garantiaCirbe;
	}
		
	/**Mètodo que obtiene la persona contable
	 * 
	 * @return persContable
	 */
	public String getPersContable(){
		return persContable;
	}
	
	/**Mètodo que establece la persona contable
	 * 
	 * @param persContable
	 */
	public void setPersContable(String persContable){
		this.persContable = persContable;
	}
	
		
	/**Mètodo que obtiene si existe o no el residente
	 * 
	 * @return residente
	 */
	public String getResidente(){
		return residente;
	}
	
	/**Mètodo que establece si existe o no residente
	 * 
	 * @param residente
	 */
	public void setResidente(String residente){
		this.residente = residente;
	}
	
	
	
	/**Mètodo que obtiene si hay un socio o no
	 * 
	 * @return socio
	 */
	public String getSocio(){
		return socio;
	}
	
	/**Mètodo que establece si hay socio o no
	 * 
	 * @param socio
	 */
	public void setSocio(String socio){
		this.socio = socio;
	}
	
	/**Mètodo que obtiene la finalidad
	 * 
	 * @return finalidad
	 */
	public String getFinalidad(){
		return finalidad;
	}
	
	/**Mètodo que establece la finalidad
	 * 
	 * @param finalidad
	 */
	public void setFinalidad(String finalidad){
		this.finalidad = finalidad;
	}
	
	/**Mètodo que obtiene la CNAE
	 * 
	 * @return cnae
	 */
	public String getCnae(){
		return cnae;
	}
	
	/**Mètodo que establece la CNAE
	 * 
	 * @param cnae
	 */
	public void setCnae(String cnae){
		this.cnae = cnae;
	}
	
	
}
