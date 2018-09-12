package mx.babel.bansefi.banksystem.transacciones.beans;

import java.math.BigDecimal;
import java.math.BigInteger;

import mx.babel.bansefi.banksystem.base.backends.RespuestaGenerica;

/**
 * Bean con propiedades de respuesta para realizar un deposito.
 * @author luis.gcastellano
 *
 */
public class DepositoRes extends RespuestaGenerica{

    private static final long serialVersionUID = 1L;

    private BigInteger acuerdo;
    
    private BigDecimal importe;
    
    private String estatus;
    
    private String movimiento;
    
    private String terminal;
    
    private String fechaOperacion;
    
    private String horaOperacion;
    
    private String sucursal;
    
    private String plaza;
    
    private String digito;
    
    private String titular;
    
    private BigInteger secuencia;
    
    private String idtask;
    
    public DepositoRes(){
        super();
    }

    public DepositoRes(BigInteger acuerdo, BigDecimal importe, String estatus,
            String movimiento, String terminal, String fechaoperacion,
            String horaoperacion, String centro, String plaza, String digito,
            String titular, BigInteger secuencia, String idtask) {
        super();
        this.acuerdo = acuerdo;
        this.importe = importe;
        this.estatus = estatus;
        this.movimiento = movimiento;
        this.terminal = terminal;
        this.fechaOperacion = fechaoperacion;
        this.horaOperacion = horaoperacion;
        this.sucursal = centro;
        this.plaza = plaza;
        this.digito = digito;
        this.titular = titular;
        this.secuencia = secuencia;
        this.idtask = idtask;
    }

    public BigInteger getAcuerdo() {
        return acuerdo;
    }

    public void setAcuerdo(BigInteger acuerdo) {
        this.acuerdo = acuerdo;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(String fechaoperacion) {
        this.fechaOperacion = fechaoperacion;
    }

    public String getHoraOperacion() {
        return horaOperacion;
    }

    public void setHoraOperacion(String horaOperacion) {
        this.horaOperacion = horaOperacion;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getPlaza() {
        return plaza;
    }

    public void setPlaza(String plaza) {
        this.plaza = plaza;
    }

    public String getDigito() {
        return digito;
    }

    public void setDigito(String digito) {
        this.digito = digito;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public BigInteger getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(BigInteger secuencia) {
        this.secuencia = secuencia;
    }

    public String getIdtask() {
        return idtask;
    }

    public void setIdtask(String idtask) {
        this.idtask = idtask;
    }
    
      
    
    
}
