package mx.babel.bansefi.banksystem.personas.enums;

import mx.babel.arq.comun.exceptions.NoControlableException;

public enum BeanCrudEnum {
    SIN_CAMBIOS(0),
    ALTA(1),
    MODIFICACION(2),
    BAJA(4),
    ALTA_MODIFICACION(3),
    ALTA_BAJA(5),
    MODIFICACION_BAJA(6),
    ALTA_MODIFICACION_BAJA(7);

    private int clave;

    private BeanCrudEnum(final int clave) {
        this.clave = clave;
    }

    /**
     * @return the clave
     */
    public int getClave() {
        return clave;
    }

    public static BeanCrudEnum addNewState(final BeanCrudEnum state, final BeanCrudEnum newState){
        return BeanCrudEnum.getState(state.getClave()+newState.getClave());
    }

    public static BeanCrudEnum removeNewState(final BeanCrudEnum state, final BeanCrudEnum removeState){
        return BeanCrudEnum.getState(state.getClave()-removeState.getClave());
    }

    public static BeanCrudEnum getState(final BeanCrudEnum state){
        return BeanCrudEnum.getState(state.getClave());
    }

    public static BeanCrudEnum getState(final int clave){
        for(final BeanCrudEnum state: BeanCrudEnum.values() ){
            if(state.getClave() == clave){
                return state;
            }

        }
        throw new NoControlableException("Ha ocurrido un error al recuperar un estado",
                "BeanCrudEnum no contiene una correspondencia para el estado "+clave);
    }

    public static boolean isModified(final BeanCrudEnum state){
        return state.getClave() == MODIFICACION.getClave()
                || state.getClave() == ALTA_MODIFICACION.getClave()
                || state.getClave() == MODIFICACION_BAJA.getClave();
    }

    public static boolean isDeleted(final BeanCrudEnum state) {
        return state.getClave() == BAJA.getClave()
                || state.getClave() == ALTA_BAJA.getClave()
                || state.getClave() == MODIFICACION_BAJA.getClave();
    }

    public static boolean isAlta(final BeanCrudEnum state) {
        return state.getClave() == ALTA.getClave()
                || state.getClave() == ALTA_BAJA.getClave()
                || state.getClave() == ALTA_MODIFICACION.getClave()
                || state.getClave() == ALTA_MODIFICACION_BAJA.getClave();
    }




}
