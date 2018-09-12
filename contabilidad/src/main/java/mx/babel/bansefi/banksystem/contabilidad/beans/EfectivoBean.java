/**
 *
 */
package mx.babel.bansefi.banksystem.contabilidad.beans;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author joseluis.pena
 *
 */
public class EfectivoBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8938288054516235392L;

    private String valorFacial;
    private String soporte;
    private String formato;
    private String dSlashV;
    private Integer unidades;
    private BigDecimal importeEnviar;
    private BigDecimal importePedido;
    private BigDecimal importeAutorizado;
    private BigDecimal importeRecibido;
    private Integer numPrecinto;
    /**
     * @return the valorFacial
     */
    public String getValorFacial() {
        return valorFacial;
    }
    /**
     * @param valorFacial the valorFacial to set
     */
    public void setValorFacial(final String valorFacial) {
        this.valorFacial = valorFacial;
    }
    /**
     * @return the soporte
     */
    public String getSoporte() {
        return soporte;
    }
    /**
     * @param soporte the soporte to set
     */
    public void setSoporte(final String soporte) {
        this.soporte = soporte;
    }
    /**
     * @return the formato
     */
    public String getFormato() {
        return formato;
    }
    /**
     * @param formato the formato to set
     */
    public void setFormato(final String formato) {
        this.formato = formato;
    }
    /**
     * @return the dSlashV
     */
    public String getdSlashV() {
        return dSlashV;
    }
    /**
     * @param dSlashV the dSlashV to set
     */
    public void setdSlashV(final String dSlashV) {
        this.dSlashV = dSlashV;
    }
    /**
     * @return the unidades
     */
    public Integer getUnidades() {
        return unidades;
    }
    /**
     * @param unidades the unidades to set
     */
    public void setUnidades(final Integer unidades) {
        this.unidades = unidades;
    }
    /**
     * @return the importeEnviar
     */
    public BigDecimal getImporteEnviar() {
        return importeEnviar;
    }
    /**
     * @param importeEnviar the importeEnviar to set
     */
    public void setImporteEnviar(final BigDecimal importeEnviar) {
        this.importeEnviar = importeEnviar;
    }
    /**
     * @return the importePedido
     */
    public BigDecimal getImportePedido() {
        return importePedido;
    }
    /**
     * @param importePedido the importePedido to set
     */
    public void setImportePedido(final BigDecimal importePedido) {
        this.importePedido = importePedido;
    }
    /**
     * @return the importeAutorizado
     */
    public BigDecimal getImporteAutorizado() {
        return importeAutorizado;
    }
    /**
     * @param importeAutorizado the importeAutorizado to set
     */
    public void setImporteAutorizado(final BigDecimal importeAutorizado) {
        this.importeAutorizado = importeAutorizado;
    }
    /**
     * @return the importeRecibido
     */
    public BigDecimal getImporteRecibido() {
        return importeRecibido;
    }
    /**
     * @param importeRecibido the importeRecibido to set
     */
    public void setImporteRecibido(final BigDecimal importeRecibido) {
        this.importeRecibido = importeRecibido;
    }
    /**
     * @return the numPrecinto
     */
    public Integer getNumPrecinto() {
        return numPrecinto;
    }
    /**
     * @param numPrecinto the numPrecinto to set
     */
    public void setNumPrecinto(final Integer numPrecinto) {
        this.numPrecinto = numPrecinto;
    }
    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
