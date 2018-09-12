/**
 *
 */
package mx.babel.bansefi.banksystem.contabilidad.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author joseluis.pena
 *
 */
public class ParrillaEfectivoBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7773276527414762913L;
    private List<EfectivoBean> listadoEfectivo;
    private BigDecimal totalPedido;
    private BigDecimal totalAutorizado;
    private BigDecimal totalRecibido;
    private BigDecimal totalEnviado;
    /**
     * @return the listadoEfectivo
     */
    public List<EfectivoBean> getListadoEfectivo() {
        return listadoEfectivo;
    }
    /**
     * @param listadoEfectivo the listadoEfectivo to set
     */
    public void setListadoEfectivo(final List<EfectivoBean> listadoEfectivo) {
        this.listadoEfectivo = listadoEfectivo;
    }
    /**
     * @return the totalPedido
     */
    public BigDecimal getTotalPedido() {
        return totalPedido;
    }
    /**
     * @param total the totalPedido to set
     */
    public void setTotalPedido(final BigDecimal totalPedido) {
        this.totalPedido = totalPedido;
    }
    /**
     * @return the totalAutorizado
     */
    public BigDecimal getTotalAutorizado() {
        return totalAutorizado;
    }
    /**
     * @param totalAutorizado the totalAutorizado to set
     */
    public void setTotalAutorizado(final BigDecimal totalAutorizado) {
        this.totalAutorizado = totalAutorizado;
    }
    /**
     * @return the totalRecibido
     */
    public BigDecimal getTotalRecibido() {
        return totalRecibido;
    }
    /**
     * @param totalRecibido the totalRecibido to set
     */
    public void setTotalRecibido(final BigDecimal totalRecibido) {
        this.totalRecibido = totalRecibido;
    }
    /**
     * @return the totalEnviado
     */
    public BigDecimal getTotalEnviado() {
        return totalEnviado;
    }
    /**
     * @param totalEnviado the totalEnviado to set
     */
    public void setTotalEnviado(final BigDecimal totalEnviado) {
        this.totalEnviado = totalEnviado;
    }




}
