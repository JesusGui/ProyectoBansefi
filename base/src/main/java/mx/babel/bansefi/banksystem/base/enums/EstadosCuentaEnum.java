package mx.babel.bansefi.banksystem.base.enums;

import org.apache.commons.lang3.StringUtils;

/** Enumerado con los estados posibles de la cuenta.
 * @author joseluis.pena
 *
 */
public enum EstadosCuentaEnum {
    OFERTADO("OFERTADO", "OFERTAR"),
    SOLICITADO("SOLICITADO", "SOLICITAR"),
    PROPUESTO("PROPUESTO", "PROPONER"),
    APROBADO("APROBADO", "APROBAR"),
    ACTIVO("ACTIVO", "ACTIVAR"),
    SUSPENDIDO("SUSPENDIDO", "SUSPENDER"),
    VENCIDO("VENCIDO", "VENCER"),
    CANCELADO("CANCELADO", "CANCELAR"),
    ARCHIVADO("ARCHIVADO", "ARCHIVAR");

    private String nombre; //Nombre del tipo de estado
    private String estado; // Codigo del estado
    private String accion;
    

    private EstadosCuentaEnum(final String nombre, final String accion) {
        this.nombre = nombre;
        this.accion = accion;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    public static final boolean isSolicitado(final String estado){
        return StringUtils.equals(EstadosCuentaEnum.SOLICITADO.getEstado(), estado);
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}  
	

}
