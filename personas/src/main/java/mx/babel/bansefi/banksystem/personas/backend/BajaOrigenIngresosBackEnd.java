package mx.babel.bansefi.banksystem.personas.backend;

import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.personas.beans.OrigenIngresosBean;
import mx.babel.bansefi.banksystem.personas.webservices.bajaorigeningresos.BajaOrigenIngresosServicio;
import mx.babel.bansefi.banksystem.personas.webservices.bajaorigeningresos.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.bajaorigeningresos.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.wrappers.DatosEconomicosWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd para la ejecución del servicio de baja de un Origen de
 * Ingresos.
 * 
 * @author alejandro.pineda
 * 
 */
@Component
public class BajaOrigenIngresosBackEnd extends BackEndBean {

	private static final long serialVersionUID = 2948267737836360291L;

	@Autowired
	private DatosEconomicosWrapper datosEconomicosWrapper;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Método para ejecutar la TRN de baja de origen de ingresos.
	 * 
	 * @param origenIngresosBean
	 *            Bean con los datos a almacenar.
	 * @return int Código de retorno del servicio
	 */
	public int ejecutarTRN(OrigenIngresosBean origenIngresosBean){
		
		Ejecutar.ITRPEBAJAORGNINGTRN contexto = new Ejecutar.ITRPEBAJAORGNINGTRN();
		Ejecutar.ITRPEBAJAORGNINGTRN.TRPEBAJAORGNINGEVTY contextoEntrada = new Ejecutar.ITRPEBAJAORGNINGTRN.TRPEBAJAORGNINGEVTY();

		Ejecutar.ITRPEBAJAORGNINGTRN.TRPEBAJAORGNINGEVTY.PEORGNINGRINDVP contextoEntradaCampos = null;

		origenIngresosBean.setEntidad(super.getEntidad());
		contextoEntradaCampos = datosEconomicosWrapper.wrapperBajaOrigenIngresos(origenIngresosBean);
		contextoEntrada.setPEORGNINGRINDVP(contextoEntradaCampos);

		Ejecutar.ITRPEBAJAORGNINGTRN.STDTRNIPARMV contextoComun = new Ejecutar.ITRPEBAJAORGNINGTRN.STDTRNIPARMV();
		contextoComun.setIDINTERNOTERMTN(super.getTerminal());
		contextoComun.setIDEMPLAUT("");
		contextoComun.setNUMSEC(0);
		contextoComun.setCODTX("POT40MON");
		contextoComun.setCODTXDI("");

		contexto.setSTDTRNIPARMV(contextoComun);
		contexto.setTRPEBAJAORGNINGEVTY(contextoEntrada);

		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(BajaOrigenIngresosServicio.class, contexto);

		super.verificaRespuesta(respuesta);
		
		return respuesta.getResponseBansefi().getOTRPEBAJAORGNINGTRN().getRTRNCD();

	}

}
