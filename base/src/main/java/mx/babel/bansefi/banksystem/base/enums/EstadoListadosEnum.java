package mx.babel.bansefi.banksystem.base.enums;

public enum EstadoListadosEnum {
    CONSULTADO("panel-dinamico consultado"),
    INACTIVO("panel-dinamico inactivo"),
    NUEVO("panel-dinamico nuevo"),
    MODIFICADO("panel-dinamico modificado"),
    ELIMINADO("panel-dinamico eliminado"),
    POR_REACTIVAR("panel-dinamico por-reactivar");
    
    
    private String styleClass;

    /**
     * Constructor.
     * @param estado .
     */
    private EstadoListadosEnum(final String styleClass){
        this.styleClass = styleClass;
    }

    public String getStyleClass() {
        return styleClass;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

}
