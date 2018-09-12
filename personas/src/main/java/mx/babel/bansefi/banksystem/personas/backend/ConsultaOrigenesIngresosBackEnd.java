package mx.babel.bansefi.banksystem.personas.backend;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.personas.beans.OrigenIngresosBean;
import mx.babel.bansefi.banksystem.personas.webservices.consultaorigenesingresos.ConsultaOrigenesIngresosServicio;
import mx.babel.bansefi.banksystem.personas.webservices.consultaorigenesingresos.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.consultaorigenesingresos.Ejecutar.ITRPECONSORGNINGTRN;
import mx.babel.bansefi.banksystem.personas.webservices.consultaorigenesingresos.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.consultaorigenesingresos.ResponseBansefi.OTRPECONSORGNINGTRN.TRPECONSORGNINGEVTZ.TRPECONSORGNINGEVTV;
import mx.babel.bansefi.banksystem.personas.wrappers.DatosEconomicosWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd para consultar el lista de origenes de ingresos.
 * 
 * @author alejandro.pineda
 * 
 */
@Component
public class ConsultaOrigenesIngresosBackEnd extends BackEndBean {

	private static final long serialVersionUID = 2372380583031317929L;

	@Autowired
	private DatosEconomicosWrapper datosEconomicosWrapper;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Método para ejecutar la TRN de consulta lista de origenes de ingresos.
	 * 
	 * @param idInterno Id Interno de la persona a consultar.
	 * @return Lista con los resultados del servicio.
	 */
	public List<OrigenIngresosBean> ejecutarTRN(int idInterno){
		Ejecutar.ITRPECONSORGNINGTRN contexto = new Ejecutar.ITRPECONSORGNINGTRN();
		contexto.setELEVATORPOSITION(0);
		contexto.setSCROLLABLEOCCURS(50);
		Ejecutar.ITRPECONSORGNINGTRN.TRPECONSORGNINGEVTY contextoEntrada = new Ejecutar.ITRPECONSORGNINGTRN.TRPECONSORGNINGEVTY();

		Ejecutar.ITRPECONSORGNINGTRN.TRPECONSORGNINGEVTY.PEORGNINGRINDVP contextoEntradaCampos = new Ejecutar.ITRPECONSORGNINGTRN.TRPECONSORGNINGEVTY.PEORGNINGRINDVP();
		contextoEntradaCampos.setCODNRBEEN(super.getEntidad());
		contextoEntradaCampos.setIDINTERNOPE(idInterno);
		contextoEntradaCampos.setCODORGNINGRINDV("");
		contextoEntrada.setPEORGNINGRINDVP(contextoEntradaCampos);

		Ejecutar.ITRPECONSORGNINGTRN.STDTRNIPARMV contextoComun = new Ejecutar.ITRPECONSORGNINGTRN.STDTRNIPARMV();
		contextoComun.setIDINTERNOTERMTN(super.getTerminal());
		contextoComun.setIDEMPLAUT("");
		contextoComun.setNUMSEC(0);
		contextoComun.setCODTX("POT41CON");
		contextoComun.setCODTXDI("");

		contexto.setSTDTRNIPARMV(contextoComun);
		contexto.setTRPECONSORGNINGEVTY(contextoEntrada);

		return this.obtenerRespuestaServicio(contexto);

	}

	/**
	 * Método para obtener los resultados del servicio de consulta lista de
	 * origenes de ingresos.
	 * 
	 * @param contextoEntrada
	 * @return Lista con los resultados del servicio.
	 */
	private List<OrigenIngresosBean> obtenerRespuestaServicio(ITRPECONSORGNINGTRN contextoEntrada){
		 
		List<OrigenIngresosBean> listaOrigenes = new ArrayList<>(0);
		
		// Se ejecuta el WebService correspondiente
		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
				ConsultaOrigenesIngresosServicio.class, contextoEntrada);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return listaOrigenes;
			}
		}
		
		for (TRPECONSORGNINGEVTV resultado : respuesta.getResponseBansefi()
				.getOTRPECONSORGNINGTRN().getTRPECONSORGNINGEVTZ()
				.getTRPECONSORGNINGEVTV()) {
			OrigenIngresosBean origenIngreso = datosEconomicosWrapper
					.wrapperConsultaOrigenIngresos(resultado);
			if(!("").equals(origenIngreso.getCodOrigenIngresos())){
				listaOrigenes.add(origenIngreso);
			}
		}
		
		return listaOrigenes;
	}

}
