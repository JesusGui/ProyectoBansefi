package mx.babel.bansefi.banksystem.cajas.beans;
import java.io.Serializable;
import java.util.Date;

public class HoraBean implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 333528969510763571L;
	
	private Date horaLimitePeticon;
	private Date horaLimiteRecogida;
	
	public Date getHoraLimitePeticon() {
		return horaLimitePeticon;
	}
	public void setHoraLimitePeticon(Date horaLimitePeticon) {
		this.horaLimitePeticon = horaLimitePeticon;
	}
	public Date getHoraLimiteRecogida() {
		return horaLimiteRecogida;
	}
	public void setHoraLimiteRecogida(Date horaLimiteRecogida) {
		this.horaLimiteRecogida = horaLimiteRecogida;
	}
	
	

}
