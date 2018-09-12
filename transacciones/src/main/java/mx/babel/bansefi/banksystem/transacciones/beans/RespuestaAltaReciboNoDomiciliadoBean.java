package mx.babel.bansefi.banksystem.transacciones.beans;

import java.io.Serializable;

/**
 * Bean que almacena la respuesta del alta de recibos no 
 * domiciliados
 * @author manuel.nieto
 *
 */
public class RespuestaAltaReciboNoDomiciliadoBean implements Serializable{

	private static final long serialVersionUID = 4295127046636501749L;
	
	private String noInternoDocumento;

	public String getNoInternoDocumento() {
		return noInternoDocumento;
	}

	public void setNoInternoDocumento(String noInternoDocumento) {
		this.noInternoDocumento = noInternoDocumento;
	}
	
	
	
}
