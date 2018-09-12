package mx.babel.bansefi.banksystem.base.backends;

import java.io.Serializable;

/**
 * Clase de respuesta generica para los webServices de bansefi.
 * @author luis.gcastellano
 *
 */
public class RespuestaGenerica implements Serializable{

	private static final long serialVersionUID = 2669388097906945113L;

	private String transaccionId;
    
    private String estatus;
    
    private String codigo;
    
    private String mensaje;
    
    private String numeroTarea;

    public RespuestaGenerica(){
        super();
    }
    
    public RespuestaGenerica(String transaccionId, String status, String codigo,
            String mensaje, String numeroTarea) {
        super();
        this.transaccionId = transaccionId;
        this.estatus = status;
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.numeroTarea = numeroTarea;
    }

    public String getTransaccionId() {
        return transaccionId;
    }

    public void setTransaccionId(String transaccionId) {
        this.transaccionId = transaccionId;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getNumeroTarea() {
        return numeroTarea;
    }

    public void setNumeroTarea(String numeroTarea) {
        this.numeroTarea = numeroTarea;
    }
    
}
