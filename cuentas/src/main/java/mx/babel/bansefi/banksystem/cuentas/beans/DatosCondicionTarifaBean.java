package mx.babel.bansefi.banksystem.cuentas.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * @author joseluis.pena
 *
 */
public class DatosCondicionTarifaBean implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 2514309078303535085L;
    private String idProductoSimple;
    private String nomProductoSimple;
	private String idParmCd;

	private Date fechaInicioValidez;
	private Date fechaEstadoActual;

	private String idCcps;
	private String codEstrctCd;
    /**
     * @return the idProductoSimple
     */
    public String getIdProductoSimple() {
        return idProductoSimple;
    }
    /**
     * @param idProductoSimple the idProductoSimple to set
     */
    public void setIdProductoSimple(final String idProductoSimple) {
        this.idProductoSimple = idProductoSimple;
    }
    /**
     * @return the nomProductoSimple
     */
    public String getNomProductoSimple() {
        return nomProductoSimple;
    }
    /**
     * @param nomProductoSimple the nomProductoSimple to set
     */
    public void setNomProductoSimple(final String nomProductoSimple) {
        this.nomProductoSimple = nomProductoSimple;
    }
    /**
     * @return the idParmCd
     */
    public String getIdParmCd() {
        return idParmCd;
    }
    /**
     * @param idParmCd the idParmCd to set
     */
    public void setIdParmCd(final String idParmCd) {
        this.idParmCd = idParmCd;
    }
    /**
     * @return the fechaInicioValidez
     */
    public Date getFechaInicioValidez() {
        return fechaInicioValidez;
    }
    /**
     * @param fechaInicioValidez the fechaInicioValidez to set
     */
    public void setFechaInicioValidez(final Date fechaInicioValidez) {
        this.fechaInicioValidez = fechaInicioValidez;
    }
    /**
     * @return the fechaEstadoActual
     */
    public Date getFechaEstadoActual() {
        return fechaEstadoActual;
    }
    /**
     * @param fechaEstadoActual the fechaEstadoActual to set
     */
    public void setFechaEstadoActual(final Date fechaEstadoActual) {
        this.fechaEstadoActual = fechaEstadoActual;
    }
    /**
     * @return the idCcps
     */
    public String getIdCcps() {
        return idCcps;
    }
    /**
     * @param idCcps the idCcps to set
     */
    public void setIdCcps(final String idCcps) {
        this.idCcps = idCcps;
    }
    /**
     * @return the codEstrctCd
     */
    public String getCodEstrctCd() {
        return codEstrctCd;
    }
    /**
     * @param codEstrctCd the codEstrctCd to set
     */
    public void setCodEstrctCd(final String codEstrctCd) {
        this.codEstrctCd = codEstrctCd;
    }


}
