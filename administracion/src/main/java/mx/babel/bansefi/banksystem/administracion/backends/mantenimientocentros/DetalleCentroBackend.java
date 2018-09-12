package mx.babel.bansefi.banksystem.administracion.backends.mantenimientocentros;

import java.io.Serializable;

import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.administracion.beans.centro.AltaCentroBean;
import mx.babel.bansefi.banksystem.administracion.webservices.detallecentro.DetalleCentroServicio;
import mx.babel.bansefi.banksystem.administracion.webservices.detallecentro.Ejecutar;
import mx.babel.bansefi.banksystem.administracion.webservices.detallecentro.EjecutarResult;
import mx.babel.bansefi.banksystem.administracion.wrappers.VentanaCentroWrapper;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd que ejecutar el webService para el detalle de un centro.
 * 
 * @author alejandro.pineda
 * 
 */
@Component
public class DetalleCentroBackend extends BackEndBean implements Serializable {

	private static final long serialVersionUID = 1158381798000086109L;

	@Autowired
	private VentanaCentroWrapper wrapperBeanService;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Método para ejecutar el WS de Detalle del centro.
	 * 
	 * @param codigoCentro Código del centro a consultar.
	 * @return AltaCentroBean Bean con los datos obtenidos del WS.
	 */
	public AltaCentroBean ejecutarTRN(String codigoCentro){
		
		Ejecutar.ITRCONSCENTROTRNI entradaTrn = new Ejecutar.ITRCONSCENTROTRNI();
		Ejecutar.ITRCONSCENTROTRNI.STDTRNIPARMV contextoComun = new Ejecutar.ITRCONSCENTROTRNI.STDTRNIPARMV();

		contextoComun.setIDINTERNOTERMTN(super.getTerminal());
		contextoComun.setIDEMPLAUT("");
		contextoComun.setNUMSEC(0);
		contextoComun.setCODTX("GCA19CON");
		contextoComun.setCODTXDI("");

		Ejecutar.ITRCONSCENTROTRNI.TRCONSCENTROEVTY contextoEntrada = new Ejecutar.ITRCONSCENTROTRNI.TRCONSCENTROEVTY();

		Ejecutar.ITRCONSCENTROTRNI.TRCONSCENTROEVTY.PYPARAMVVVP contextoEntradaCamposVacios = new Ejecutar.ITRCONSCENTROTRNI.TRCONSCENTROEVTY.PYPARAMVVVP();
		contextoEntradaCamposVacios.setCODINTERNOUO(codigoCentro);
		contextoEntradaCamposVacios.setCODNRBEEN(super.getEntidad());

		Ejecutar.ITRCONSCENTROTRNI.TRCONSCENTROEVTY.UOCENTROP contextoEntradaCampos = new Ejecutar.ITRCONSCENTROTRNI.TRCONSCENTROEVTY.UOCENTROP();

		contextoEntradaCampos.setCODNRBEEN(super.getEntidad());
		contextoEntradaCampos.setCODINTERNOUO(codigoCentro);

		contextoEntrada.setUOCENTROP(contextoEntradaCampos);
		contextoEntrada.setPYPARAMVVVP(contextoEntradaCamposVacios);
		entradaTrn.setTRCONSCENTROEVTY(contextoEntrada);
		entradaTrn.setSTDTRNIPARMV(contextoComun);

		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
				DetalleCentroServicio.class, entradaTrn);

		super.verificaRespuesta(respuesta);

		AltaCentroBean altaCentroBean = new AltaCentroBean();
		
		altaCentroBean = wrapperBeanService.wrappDetalleCentro(respuesta.getResponseBansefi()
				.getOTRCONSCENTROTRNO().getTRCONSCENTROEVTZ());
		
		altaCentroBean.setCentro(altaCentroBean.getNumero());

		return altaCentroBean;
	}
}
