package mx.babel.bansefi.banksystem.cuentas.enums;

/** Enumerado con los valores posibles para los cargos en alta IPFs
 *  y pago de cuota de IPFS.
 * @author joseluis.pena
 *
 */
public enum TipoCargoEnum {
    ACUERDO("A","Cuenta"), CAJA("C", "Caja"), INTERVENCION("I","Intervención");

    private String tipo;
    private String descripcion;

    private TipoCargoEnum(final String tipo, final String descripcion){
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }



}
