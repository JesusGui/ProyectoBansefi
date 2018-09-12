package mx.babel.bansefi.banksystem.cuentas.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import mx.babel.bansefi.banksystem.base.beans.ApuntesBean;

/**
 * Bean que almacena la informacion generada tras la simulacion de
 * cancelacion de cuenta
 * @author manuel.nieto
 *
 */
public class SimulacionCancelacionCuentaBean implements Serializable{

	private static final long serialVersionUID = -7822477197663777730L;
	
	private BigDecimal deudaImpagada;
	private BigDecimal ivaOperacion;
	private BigDecimal comisionMantenimiento;
	private BigDecimal saldo;
	private BigDecimal importeNeto;
	private String importeNetoDebeHaber;
	private BigDecimal interesDeudor;
	private BigDecimal interesAcreedor;
	private String interesAcreedorDebeHaber;
	private BigDecimal importe;
	private String  codigoLinea;
	private String  idGRPPD;
	
	
	public String getCodigoLinea() {
		return codigoLinea;
	}
	public void setCodigoLinea(String codigoLinea) {
		this.codigoLinea = codigoLinea;
	}
	public String getIdGRPPD() {
		return idGRPPD;
	}
	public void setIdGRPPD(String idGRPPD) {
		this.idGRPPD = idGRPPD;
	}
	private List<ApuntesBean> apuntes;
	private List<DesgloseApuntesBean> desgloseConceptos;
	public List<DesgloseApuntesBean> getDesgloseConceptos() {
		return desgloseConceptos;
	}
	public void setDesgloseConceptos(List<DesgloseApuntesBean> desgloseConceptos) {
		this.desgloseConceptos = desgloseConceptos;
	}
	public BigDecimal getDeudaImpagada() {
		return deudaImpagada;
	}
	public void setDeudaImpagada(BigDecimal deudaImpagada) {
		this.deudaImpagada = deudaImpagada;
	}
	public List<ApuntesBean> getApuntes() {
		return apuntes;
	}
	public void setApuntes(List<ApuntesBean> apuntes) {
		this.apuntes = apuntes;
	}
	public BigDecimal getIvaOperacion() {
		return ivaOperacion;
	}
	public void setIvaOperacion(BigDecimal ivaOperacion) {
		this.ivaOperacion = ivaOperacion;
	}
	public BigDecimal getComisionMantenimiento() {
		return comisionMantenimiento;
	}
	public void setComisionMantenimento(BigDecimal comisionMantenimiento) {
		this.comisionMantenimiento = comisionMantenimiento;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	public BigDecimal getImporteNeto() {
		return importeNeto;
	}
	public void setImporteNeto(BigDecimal importeNeto) {
		this.importeNeto = importeNeto;
	}
	public BigDecimal getInteresDeudor() {
		return interesDeudor;
	}
	public void setInteresDeudor(BigDecimal interesDeudor) {
		this.interesDeudor = interesDeudor;
	}
	public void setComisionMantenimiento(BigDecimal comisionMantenimiento) {
		this.comisionMantenimiento = comisionMantenimiento;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public BigDecimal getInteresAcreedor() {
		return interesAcreedor;
	}
	public void setInteresAcreedor(BigDecimal interesAcreedor) {
		this.interesAcreedor = interesAcreedor;
	}
	public String getImporteNetoDebeHaber() {
		return importeNetoDebeHaber;
	}
	public void setImporteNetoDebeHaber(String importeNetoDebeHaber) {
		this.importeNetoDebeHaber = importeNetoDebeHaber;
	}
	public String getInteresAcreedorDebeHaber() {
		return interesAcreedorDebeHaber;
	}
	public void setInteresAcreedorDebeHaber(String interesAcreedorDebeHaber) {
		this.interesAcreedorDebeHaber = interesAcreedorDebeHaber;
	}	
	
}
