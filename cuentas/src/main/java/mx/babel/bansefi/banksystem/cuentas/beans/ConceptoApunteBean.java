package mx.babel.bansefi.banksystem.cuentas.beans;

import java.io.Serializable;

public class ConceptoApunteBean implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 4923401885202800336L;
    private String nombre;
    private String unidades;
    private String codEstrctCd;
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * @param nombre the nombre to set
     */
    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }
    /**
     * @return the unidades
     */
    public String getUnidades() {
        return unidades;
    }
    /**
     * @param unidades the unidades to set
     */
    public void setUnidades(final String unidades) {
        this.unidades = unidades;
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
