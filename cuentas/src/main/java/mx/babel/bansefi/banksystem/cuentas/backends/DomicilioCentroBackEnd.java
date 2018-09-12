package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.webservices.domiciliocentro.DomicilioCentroServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.domiciliocentro.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.domiciliocentro.EjecutarResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DomicilioCentroBackEnd extends BackEndBean{

	private static final long serialVersionUID = -7941814471717842606L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	/**
	 * Método para ejecutar el WS de Detalle del centro.
	 * 
	 * @param codigoCentro Código del centro a consultar.
	 * @return AltaCentroBean Bean con los datos obtenidos del WS.
	 */
	public int ejecutarTRN(String codigoCentro){
		Ejecutar.ITRCONSCENTROTRNI entradaTrn = initPeticion(codigoCentro);

		EjecutarResult respuesta = ejecutarWS(entradaTrn);

		super.verificaRespuesta(respuesta);
		
		int direccion = 0;
		if(respuesta.getResponseBansefi() != null && respuesta.getResponseBansefi().getOTRCONSCENTROTRNO() != null 
				&& respuesta.getResponseBansefi().getOTRCONSCENTROTRNO().getTRCONSCENTROEVTZ() != null &&
				respuesta.getResponseBansefi().getOTRCONSCENTROTRNO().getTRCONSCENTROEVTZ().getNUMDIRPRALV() != null){
			direccion = respuesta.getResponseBansefi().getOTRCONSCENTROTRNO().getTRCONSCENTROEVTZ().getNUMDIRPRALV().getNUMDIRPRAL();
		}
		return direccion;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto
	 *            Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRCONSCENTROTRNI contexto) {
		EjecutarResult respuesta = null;
		try {
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					DomicilioCentroServicio.class, contexto);
		} catch (NoControlableException e) {
			throw new NoControlableException(
					"Error al invocar servicio web que realiza "
							+ "la consulta de domicilio de centro.", e);
		}
		return respuesta;
	}
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio
	 * web.
	 * 
	 * @param codigoCentro código del centro
	 *            idInterna del cliente a consultar
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITRCONSCENTROTRNI initPeticion(String codigoCentro) {
		Ejecutar.ITRCONSCENTROTRNI contexto = new Ejecutar.ITRCONSCENTROTRNI();
		Ejecutar.ITRCONSCENTROTRNI.STDTRNIPARMV contextoComun = new Ejecutar.ITRCONSCENTROTRNI.STDTRNIPARMV();
		Ejecutar.ITRCONSCENTROTRNI.TRCONSCENTROEVTY.PYPARAMVVVP contextoEntradaCamposVacios = 
				new Ejecutar.ITRCONSCENTROTRNI.TRCONSCENTROEVTY.PYPARAMVVVP();
		Ejecutar.ITRCONSCENTROTRNI.TRCONSCENTROEVTY.UOCENTROP contextoEntradaCampos = 
				new Ejecutar.ITRCONSCENTROTRNI.TRCONSCENTROEVTY.UOCENTROP();
		Ejecutar.ITRCONSCENTROTRNI.TRCONSCENTROEVTY contextoEntrada = 
				new Ejecutar.ITRCONSCENTROTRNI.TRCONSCENTROEVTY();
		contextoEntrada.setUOCENTROP(contextoEntradaCampos);
		contextoEntrada.setPYPARAMVVVP(contextoEntradaCamposVacios);
		contexto.setTRCONSCENTROEVTY(contextoEntrada);
		contexto.setSTDTRNIPARMV(contextoComun);
		super.initialize(contexto);

		contextoComun.setIDINTERNOTERMTN(super.getTerminal());
		contextoComun.setCODTX(CodTxConstants.COD_TX_TR_CONS_CENTRO_TRN);

		contextoEntradaCamposVacios.setCODINTERNOUO(codigoCentro);
		contextoEntradaCamposVacios.setCODNRBEEN(super.getEntidad());


		contextoEntradaCampos.setCODNRBEEN(super.getEntidad());
		contextoEntradaCampos.setCODINTERNOUO(codigoCentro);

		return contexto;
	}

}
