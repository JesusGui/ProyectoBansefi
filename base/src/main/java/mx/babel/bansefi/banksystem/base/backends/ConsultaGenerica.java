package mx.babel.bansefi.banksystem.base.backends;

import java.io.Serializable;

/**
 * Bean con propiedades genericas de las request de los WS.
 * @author luis.gcastellano
 *
 */
public class ConsultaGenerica implements Serializable{

	private static final long serialVersionUID = 6257012580384060898L;

	private String usuarioId;
    
    private String passheader;
    
    private String ipheader;
    
    private String entidad;

    public ConsultaGenerica() {
        super();
    }
    
    public ConsultaGenerica(String userheader, String passheader,
            String ipheader, String entidad) {
        super();
        this.usuarioId = userheader;
        this.passheader = passheader;
        this.ipheader = ipheader;
        this.entidad = entidad;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getPassheader() {
        return passheader;
    }

    public void setPassheader(String passheader) {
        this.passheader = passheader;
    }

    public String getIpheader() {
        return ipheader;
    }

    public void setIpheader(String ipheader) {
        this.ipheader = ipheader;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }
    
    
}
