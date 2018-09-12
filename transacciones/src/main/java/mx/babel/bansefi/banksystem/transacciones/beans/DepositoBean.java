package mx.babel.bansefi.banksystem.transacciones.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import mx.babel.bansefi.banksystem.base.beans.domain.AnotacionBean;

/**
 * Bean que se encarga de recoger los atributos de un Deposito.
 * @author luis.gcastellano
 *
 */

public class DepositoBean implements Serializable{

    private static final long serialVersionUID = 1L;
   
    private Long cuentaDeposito;
    
    private Double importeDeposito;
    
    private boolean mostrarUnidadesMillon;
    
    private Integer unidadesMillon;
    
    private String operacionDeposito;
    
    private String operacionDepositoDescripcion;
    
    private Date fechaValorDeposito;
    
    private boolean imprimirSaldoDeposito;
    
    private boolean cuentaPuenteDeposito;
    
    private String conceptoDeposito;
    
    private String ordenanteDeposito;
    
    private String titular;
    
    private String identificacionClienteDeposito;
    
    private String numeroIdentificacionClienteDeposito;
    
    private String nivelCuenta;
    
    private List<AnotacionBean> anotaciones;
    
    /**
     * Constructor por defecto.
     */
    public DepositoBean(){
        super();
    }

    /**
     * Constructor con parametros.
     * 
     */
    public DepositoBean(Long cuentaDeposito, Double importeDeposito,
            String operacionDeposito, Date fechaValorDeposito,
            boolean imprimirSaldoDeposito, boolean cuentaPuenteDeposito,
            String conceptoDeposito, String ordenanteDeposito) {
        super();
        this.cuentaDeposito = cuentaDeposito;
        this.importeDeposito = importeDeposito;
        this.operacionDeposito = operacionDeposito;
        this.fechaValorDeposito = fechaValorDeposito;
        this.imprimirSaldoDeposito = imprimirSaldoDeposito;
        this.cuentaPuenteDeposito = cuentaPuenteDeposito;
        this.conceptoDeposito = conceptoDeposito;
        this.ordenanteDeposito = ordenanteDeposito;
    }

    public Long getCuentaDeposito() {
        return cuentaDeposito;
    }

    public void setCuentaDeposito(Long cuentaDeposito) {
        this.cuentaDeposito = cuentaDeposito;
    }

    public Double getImporteDeposito() {
        return importeDeposito;
    }

    public void setImporteDeposito(Double importeDeposito) {
        this.importeDeposito = importeDeposito;
    }

    public String getOperacionDeposito() {
        return operacionDeposito;
    }

    public void setOperacionDeposito(String operacionDeposito) {
        this.operacionDeposito = operacionDeposito;
    }

    public Date getFechaValorDeposito() {
        return fechaValorDeposito;
    }

    public void setFechaValorDeposito(Date fechaValorDeposito) {
        this.fechaValorDeposito = fechaValorDeposito;
    }

    public boolean isImprimirSaldoDeposito() {
        return imprimirSaldoDeposito;
    }

    public void setImprimirSaldoDeposito(boolean imprimirSaldoDeposito) {
        this.imprimirSaldoDeposito = imprimirSaldoDeposito;
    }

    public boolean isCuentaPuenteDeposito() {
        return cuentaPuenteDeposito;
    }

    public void setCuentaPuenteDeposito(boolean cuentaPuenteDeposito) {
        this.cuentaPuenteDeposito = cuentaPuenteDeposito;
    }

    public String getConceptoDeposito() {
        return conceptoDeposito;
    }

    public void setConceptoDeposito(String conceptoDeposito) {
        this.conceptoDeposito = conceptoDeposito;
    }

    public String getOrdenanteDeposito() {
        return ordenanteDeposito;
    }

    public void setOrdenanteDeposito(String ordenanteDeposito) {
        this.ordenanteDeposito = ordenanteDeposito;
    }

    public boolean isMostrarUnidadesMillon() {
        return mostrarUnidadesMillon;
    }

    public void setMostrarUnidadesMillon(boolean mostrarUnidadesMillon) {
        this.mostrarUnidadesMillon = mostrarUnidadesMillon;
    }

    public Integer getUnidadesMillon() {
        return unidadesMillon;
    }

    public void setUnidadesMillon(Integer unidadesMillon) {
        this.unidadesMillon = unidadesMillon;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }


    public String getOperacionDepositoDescripcion() {
        return operacionDepositoDescripcion;
    }

    public void setOperacionDepositoDescripcion(String operacionDepositoDescripcion) {
        this.operacionDepositoDescripcion = operacionDepositoDescripcion;
    }

    public String getIdentificacionClienteDeposito() {
        return identificacionClienteDeposito;
    }

    public void setIdentificacionClienteDeposito(
            String identificacionClienteDeposito) {
        this.identificacionClienteDeposito = identificacionClienteDeposito;
    }

    public String getNumeroIdentificacionClienteDeposito() {
        return numeroIdentificacionClienteDeposito;
    }

    public void setNumeroIdentificacionClienteDeposito(
            String numeroIdentificacionClienteDeposito) {
        this.numeroIdentificacionClienteDeposito = numeroIdentificacionClienteDeposito;
    }

	public String getNivelCuenta() {
		return nivelCuenta;
	}

	public void setNivelCuenta(String nivelCuenta) {
		this.nivelCuenta = nivelCuenta;
	}

	public List<AnotacionBean> getAnotaciones() {
		return anotaciones;
	}

	public void setAnotaciones(List<AnotacionBean> anotaciones) {
		this.anotaciones = anotaciones;
	}
 
    
    
}
