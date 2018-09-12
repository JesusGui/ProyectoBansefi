package mx.babel.bansefi.banksystem.personas.backend;

import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.personas.beans.OrigenIngresosBean;
import mx.babel.bansefi.banksystem.personas.webservices.altaorigeningreso.AltaOrigenIngresoServicio;
import mx.babel.bansefi.banksystem.personas.webservices.altaorigeningreso.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.altaorigeningreso.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.wrappers.DatosEconomicosWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd para la ejecución del servicio de Alta de un Origen de Ingreso.
 * 
 * @author alejandro.pineda
 * 
 */
@Component
public class AltaOrigenIngresosBackEnd extends BackEndBean {

	private static final long serialVersionUID = -9124721718051141248L;
	
	@Autowired
	private DatosEconomicosWrapper datosEconomicosWrapper;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;


	/**
	 * Método para ejecutar la TRN de alta de origen de ingresos.
	 * 
	 * @param origenIngresosBean
	 *            Bean con los datos a almacenar.
	 * @return int Código de retorno del servicio
	 */
	public int ejecutarTRN(OrigenIngresosBean origenIngresosBean){
		Ejecutar.ITRPEALTAORGNINGTRN contextoEntrada = new Ejecutar.ITRPEALTAORGNINGTRN();
		Ejecutar.ITRPEALTAORGNINGTRN.TRPEALTAORGNINGEVTY contextoEntradaCampos = null;

		origenIngresosBean.setEntidad(super.getEntidad());
		contextoEntradaCampos = datosEconomicosWrapper
				.wrapperAltaOrigenIngresos(origenIngresosBean);
		contextoEntradaCampos.setINDDOMIZNIG("");

		Ejecutar.ITRPEALTAORGNINGTRN.STDTRNIPARMV contextoComun = new Ejecutar.ITRPEALTAORGNINGTRN.STDTRNIPARMV();
		contextoComun.setIDINTERNOTERMTN(super.getTerminal());
		contextoComun.setIDEMPLAUT("");
		contextoComun.setNUMSEC(0);
		contextoComun.setCODTX("POT39MON");
		contextoComun.setCODTXDI("");

		contextoEntrada.setSTDTRNIPARMV(contextoComun);
		contextoEntrada.setTRPEALTAORGNINGEVTY(contextoEntradaCampos);
		
		// Se ejecuta el WebService correspondiente
		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
				AltaOrigenIngresoServicio.class, contextoEntrada);

		super.verificaRespuesta(respuesta);
		
		return respuesta.getResponseBansefi().getOTRPEALTAORGNINGTRN().getRTRNCD();

	}
	
}
