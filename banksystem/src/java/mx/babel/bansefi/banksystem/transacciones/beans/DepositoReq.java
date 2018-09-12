package mx.babel.bansefi.banksystem.transacciones.beans;

import java.util.Date;

import mx.babel.bansefi.banksystem.base.backends.ConsultaGenerica;

/**
 * Bean con propiedades de consulta para realizar el deposito.
 * @author luis.gcastellano
 *
 */
public class DepositoReq extends ConsultaGenerica{

    private static final long serialVersionUID = 1L;
    
    private String cuenta;
    
    private Double importe;
    
    private Date fechavalor;
    
    private String codoperacion;
    
    private String concepto;
    
    public DepositoReq(){
        super();
    }

    public DepositoReq(String cuenta, Double importe, Date fechavalor,
            String codoperacion, String concepto) {
        super();
        this.cuenta = cuenta;
        this.importe = importe;
        this.fechavalor = fechavalor;
        this.codoperacion = codoperacion;
        this.concepto = concepto;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public Date getFechavalor() {
        return fechavalor;
    }

    public void setFechavalor(Date fechavalor) {
        this.fechavalor = fechavalor;
    }

    public String getCodoperacion() {
        return codoperacion;
    }

    public void setCodoperacion(String codoperacion) {
        this.codoperacion = codoperacion;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }
    
    
    
}
