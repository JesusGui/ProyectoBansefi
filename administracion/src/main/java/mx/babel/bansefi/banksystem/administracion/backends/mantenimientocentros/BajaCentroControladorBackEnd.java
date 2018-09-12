package mx.babel.bansefi.banksystem.administracion.backends.mantenimientocentros;

import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.administracion.beans.centro.CentroControladorBean;
import mx.babel.bansefi.banksystem.administracion.webservices.bajacentrocontrolador.BajaCentroControladorServicio;
import mx.babel.bansefi.banksystem.administracion.webservices.bajacentrocontrolador.Ejecutar.ITRBAJACENTCTRLTRNI;
import mx.babel.bansefi.banksystem.administracion.webservices.bajacentrocontrolador.EjecutarResult;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd para la ejecución del servicio de baja de centros
 * controladores.
 * 
 * @author alejandro.pineda
 * 
 */
@Component
public class BajaCentroControladorBackEnd extends BackEndBean {

	private static final long serialVersionUID = 6067676447868566864L;

	@Autowired
	private ServicioWebUtils servicioWebUtils;

	/**
	 * Método que ejecuta el servicio de baja de centro controlador.
	 * 
	 * @param centroCtrl
	 *            Bean del centro controlador.
	 * @return Codigo de retorno del servicio
	 */
	public int ejecutarTRN(CentroControladorBean centroCtrl){

		ITRBAJACENTCTRLTRNI itrbajacentctrltrni = initPeticion(centroCtrl);

		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
				BajaCentroControladorServicio.class,itrbajacentctrltrni);

		super.verificaRespuesta(respuesta);

		return respuesta.getResponseBansefi().getOTRBAJACENTCTRLTRNO().getRTRNCD();
	}

	private ITRBAJACENTCTRLTRNI initPeticion(CentroControladorBean centroCtrl) {

		ITRBAJACENTCTRLTRNI itrbajacentctrltrni = new ITRBAJACENTCTRLTRNI();
		super.initialize(itrbajacentctrltrni);

		itrbajacentctrltrni.getTRBAJACENTCTRLEVTY().getUOCENTCTRLP()
				.setCODCENTCTRL(centroCtrl.getCodigoCentroControlador());
		itrbajacentctrltrni.getTRBAJACENTCTRLEVTY().getUOCENTCTRLP()
				.setCODINTERNOUO(centroCtrl.getCodigoCentro());
		itrbajacentctrltrni.getTRBAJACENTCTRLEVTY().getUOCENTCTRLP()
				.setCODNRBEEN(super.getEntidad());

		itrbajacentctrltrni.getTRBAJACENTCTRLEVTY().getUOCENTROP()
				.setCODINTERNOUO(centroCtrl.getCodigoCentro());
		itrbajacentctrltrni.getTRBAJACENTCTRLEVTY().getUOCENTROP()
				.setCODNRBEEN(super.getEntidad());

		itrbajacentctrltrni.getSTDTRNIPARMV().setIDINTERNOTERMTN(
				super.getTerminal());
		itrbajacentctrltrni.getSTDTRNIPARMV().setCODTX(
				CodTxConstants.COD_TX_TR_BAJA_CENT_CTRL_TRN);

		return itrbajacentctrltrni;
	}

}
