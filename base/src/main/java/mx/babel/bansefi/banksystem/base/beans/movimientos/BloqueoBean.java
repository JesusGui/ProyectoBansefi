package mx.babel.bansefi.banksystem.base.beans.movimientos;

import java.io.Serializable;
import java.util.List;

public class BloqueoBean implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 4419510369747106617L;
    private List<MovimientoBean> bloqueosList;
    private boolean masDatos;
    /**
     * @return the bloqueosList
     */
    public List<MovimientoBean> getBloqueosList() {
        return bloqueosList;
    }
    /**
     * @param bloqueosList the bloqueosList to set
     */
    public void setBloqueosList(final List<MovimientoBean> bloqueosList) {
        this.bloqueosList = bloqueosList;
    }
    /**
     * @return the masDatos
     */
    public boolean isMasDatos() {
        return masDatos;
    }
    /**
     * @param masDatos the masDatos to set
     */
    public void setMasDatos(final boolean masDatos) {
        this.masDatos = masDatos;
    }

}
