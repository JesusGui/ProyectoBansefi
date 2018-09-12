package mx.babel.bansefi.banksystem.base.backends.consultatitular;

import java.math.BigInteger;

import mx.babel.bansefi.banksystem.base.backends.RespuestaGenerica;


/**
 * Bean con propiedades de respuesta de la consulta de titular.
 * @author luis.gcastellano
 *
 */
public class ConsultaTitularRes extends RespuestaGenerica{
    
    private static final long serialVersionUID = 1L;
    
    private BigInteger identificadorPersona;
    
    private String nombre;
    
    private String rfc;
   
    private String curp;
    
    private BigInteger ordenRelacion;
    
    private String nivelCuenta;

    public ConsultaTitularRes() {
        super();
    }
    
    public ConsultaTitularRes(BigInteger idinternope, String nombre,
            String idrfc, String idcurp) {
        super();
        this.identificadorPersona = idinternope;
        this.nombre = nombre;
        this.rfc = idrfc;
        this.curp = idcurp;
    }

    public BigInteger getIdentificadorPersona() {
        return identificadorPersona;
    }

    public void setIdentificadorPersona(BigInteger identificadorPersona) {
        this.identificadorPersona = identificadorPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

	public BigInteger getOrdenRelacion() {
		return ordenRelacion;
	}

	public void setOrdenRelacion(BigInteger ordenRelacion) {
		this.ordenRelacion = ordenRelacion;
	}

	public String getNivelCuenta() {
		return nivelCuenta;
	}

	public void setNivelCuenta(String nivelCuenta) {
		this.nivelCuenta = nivelCuenta;
	}
  
}
