package mx.babel.bansefi.banksystem.administracion.backends.mantenimientocentros;

import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.administracion.beans.centro.CentroControladorBean;
import mx.babel.bansefi.banksystem.administracion.webservices.altacentrocontrolador.AltaCentroControladorServicio;
import mx.babel.bansefi.banksystem.administracion.webservices.altacentrocontrolador.Ejecutar;
import mx.babel.bansefi.banksystem.administracion.webservices.altacentrocontrolador.EjecutarResult;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd para ejecutar el servicio de alta de centro controlador.
 * 
 * @author alejandro.pineda
 * 
 */
@Component
public class AltaCentroControladorBackEnd extends BackEndBean {

	private static final long serialVersionUID = 4339240640772517782L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Metodo que ejecuta el servicio de alta de centros controladores.
	 * 
	 * @param centroCtrl
	 *            Bean del centro controlador.
	 * @return Codigo de retorno del servicio.
	 */
	public int ejecutarTRN(CentroControladorBean centroCtrl){
		Ejecutar.ITRALTACENTCTRLTRNI contexto = new Ejecutar.ITRALTACENTCTRLTRNI();

		Ejecutar.ITRALTACENTCTRLTRNI.TRALTACENTCTRLEVTY contextoEntrada = new Ejecutar.ITRALTACENTCTRLTRNI.TRALTACENTCTRLEVTY();

		Ejecutar.ITRALTACENTCTRLTRNI.TRALTACENTCTRLEVTY.UOCENTCTRLE contextoEntradaCampos = new Ejecutar.ITRALTACENTCTRLTRNI.TRALTACENTCTRLEVTY.UOCENTCTRLE();
		contextoEntradaCampos.setCODNRBEEN(super.getEntidad());
		contextoEntradaCampos.setCODINTERNOUO(centroCtrl.getCodigoCentro());
		contextoEntradaCampos.setCODCENTCTRL(centroCtrl.getCodigoCentroControlador());
		contextoEntrada.setUOCENTCTRLE(contextoEntradaCampos);

		Ejecutar.ITRALTACENTCTRLTRNI.STDTRNIPARMV contextoComun = new Ejecutar.ITRALTACENTCTRLTRNI.STDTRNIPARMV();
		contextoComun.setIDINTERNOTERMTN(super.getTerminal());
		contextoComun.setIDEMPLAUT("");
		contextoComun.setNUMSEC(0);
		contextoComun.setCODTX("GCS02MON");
		contextoComun.setCODTXDI("");

		contexto.setTRALTACENTCTRLEVTY(contextoEntrada);
		contexto.setSTDTRNIPARMV(contextoComun);

		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(AltaCentroControladorServicio.class, contexto);

		super.verificaRespuesta(respuesta);

		return respuesta.getResponseBansefi().getOTRALTACENTCTRLTRNO().getRTRNCD();
	}	

}
