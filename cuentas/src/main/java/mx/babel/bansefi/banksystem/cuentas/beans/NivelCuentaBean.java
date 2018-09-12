package mx.babel.bansefi.banksystem.cuentas.beans;

import java.io.Serializable;
import java.util.Date;

public class NivelCuentaBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5659196301413667793L;
    private String codigoNivel;
    private String descripcionEstadoCuenta;
    private Date fechaOperacion;
    /**
     * @return the codigoNivel
     */
    public String getCodigoNivel() {
        return codigoNivel;
    }
    /**
     * @param codigoNivel the codigoNivel to set
     */
    public void setCodigoNivel(final String codigoNivel) {
        this.codigoNivel = codigoNivel;
    }
    /**
     * @return the descripcionEstadoCuenta
     */
    public String getDescripcionEstadoCuenta() {
        return descripcionEstadoCuenta;
    }
    /**
     * @param descripcionEstadoCuenta the descripcionEstadoCuenta to set
     */
    public void setDescripcionEstadoCuenta(final String descripcionEstadoCuenta) {
        this.descripcionEstadoCuenta = descripcionEstadoCuenta;
    }
    /**
     * @return the fechaOperacion
     */
    public Date getFechaOperacion() {
        return fechaOperacion;
    }
    /**
     * @param fechaOperacion the fechaOperacion to set
     */
    public void setFechaOperacion(final Date fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }



}
