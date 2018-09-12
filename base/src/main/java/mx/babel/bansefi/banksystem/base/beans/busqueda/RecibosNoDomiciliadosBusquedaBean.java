package mx.babel.bansefi.banksystem.base.beans.busqueda;

import java.io.Serializable;

import mx.babel.arq.comun.annotations.CampoBusquedaAnnotation;
import mx.babel.arq.comun.annotations.NavegaAnnotation;
import mx.babel.arq.comun.beans.PaginacionBean;

import org.springframework.stereotype.Component;

/**
 * Clase que define el modelo para la busqueda de recibos no domiciliados
 * @author manuel.nieto
 */
@Component
@NavegaAnnotation(campoEnum = "RESUMEN_TRANSACCION_RECIBO_NO_DOMICILIADO")
public class RecibosNoDomiciliadosBusquedaBean extends PaginacionBean implements Serializable {

	private static final long serialVersionUID = -21390116120098901L;

	//Entrada
	@CampoBusquedaAnnotation(requerido = true, desplegar = 0, longitud = 14, tituloCampo="Nº Operación")
	private String numOperacion;

	public String getNumOperacion() {
		return numOperacion;
	}

	public void setNumOperacion(String numOperacion) {
		this.numOperacion = numOperacion;
	}

	
	
	
}
