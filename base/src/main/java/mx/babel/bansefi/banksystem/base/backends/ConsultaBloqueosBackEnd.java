
package mx.babel.bansefi.banksystem.base.backends;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.EstadoEnum;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.movimientos.BloqueoBean;
import mx.babel.bansefi.banksystem.base.beans.movimientos.MovimientoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.MovimientosWrapper;
import mx.babel.bansefi.banksystem.base.webservices.consultabloqueos.ConsultaBloqueosServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultabloqueos.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultabloqueos.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultabloqueos.ResponseBansefi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para consultas de bloqueos en una cuenta
 *
 * @author mario.montesdeoca
 *
 */
@Component
public class ConsultaBloqueosBackEnd extends BackEndBean{

	private static final long serialVersionUID = -4692708996686245710L;

	@Autowired
	MovimientosWrapper movimientosWrapper;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
     * Función para obtener los datos de bloqueos de una cuenta invocando un
     * servicio web sin tener en cuenta la paginacion. (joseluis.pena)
     *
     * @param consultaMovimientosBean bean con los detalles de la cuenta y sus movimientos.
     */
    public List<MovimientoBean> ejecutarTRN(final String numeroCuenta){
        return this.ejecutarTRN(numeroCuenta, "0", null, null).getBloqueosList();
    }

	/**
	 * Función para obtener los datos de bloqueos de una cuenta invocando un
	 * servicio web que incorpora la funcionalidad de paginacion.
	 *
	 * @param BloqueoBean bean con listado de bloqueos e indicador de mas datos.
	 */
	public BloqueoBean ejecutarTRN(final String numeroCuenta, final String estadoBloqueo, final String codLastBloq,
	        final Integer numLastBloq){
	    final BloqueoBean bloqueoBean = new BloqueoBean();
	    bloqueoBean.setBloqueosList(new ArrayList<MovimientoBean>());
		final Ejecutar.ITRPETICIONCONSULTABP contexto = initPeticion(numeroCuenta, estadoBloqueo,
		        codLastBloq, numLastBloq);

		final EjecutarResult respuesta = getResponse(contexto);

		if(verificaResponseBansefi(respuesta)){
		    bloqueoBean.setBloqueosList(getBloqueos(respuesta.getResponseBansefi()));
	        bloqueoBean.setMasDatos(respuesta.getResponseBansefi().getOTRPETICIONCONSULTABP().getMOREDATAIN() != 0);
		}

		return bloqueoBean;
	}

	/**
	 * Función encargada de obtener los bloqueos de una cuenta a partir de la respuesta del servicio web
	 *
	 * @param consultaMovimientosBean bean con los detalles de la cuenta y sus movimientos.
	 * @param response El objeto de reultado enviado por el servicio web.
	 * @return
	 */
	private List<MovimientoBean> getBloqueos(final ResponseBansefi response)
			throws NoControlableException, ControlableException{
		verificaResultado(response);
		final List<MovimientoBean> bloqueos = new ArrayList<MovimientoBean>();
		if(verificaRespuestaTRN(response)){
			for (final ResponseBansefi.OTRPETICIONCONSULTABP.TRPETICIONCONSULTABPE.BPLISTABLOQUEOV
					bloqueo: response.getOTRPETICIONCONSULTABP().getTRPETICIONCONSULTABPE().
					getBPLISTABLOQUEOV()) {
				final MovimientoBean movimientoBean = movimientosWrapper.wrappBloqueo(bloqueo);
				if(null != movimientoBean.getNumeroBloqueo()
				        && 0 != movimientoBean.getNumeroBloqueo()){
				    bloqueos.add(movimientoBean);
				}
			}
		}

		return bloqueos;
	}

	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * @param estadoBloqueo
	 * @param paramPaginacion
	 * @param numLastBloq
	 *
	 * @param idInterno Id del cliente de tipo persona moral
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITRPETICIONCONSULTABP initPeticion(final String numeroCuenta, final String estadoBloqueo,
	        final String codLastBloq, final Integer numLastBloq)
			throws ControlableException{
		final Ejecutar.ITRPETICIONCONSULTABP contexto = new Ejecutar.ITRPETICIONCONSULTABP();
		final Ejecutar.ITRPETICIONCONSULTABP.STDTRNIPARMV contextoEntrada =
				new Ejecutar.ITRPETICIONCONSULTABP.STDTRNIPARMV();
		final Ejecutar.ITRPETICIONCONSULTABP.TRPETICIONCONSULTABPE cuerpoContexto =
				new Ejecutar.ITRPETICIONCONSULTABP.TRPETICIONCONSULTABPE();
		contexto.setSTDTRNIPARMV(contextoEntrada);
		contexto.setTRPETICIONCONSULTABPE(cuerpoContexto);
		super.initialize(contexto);

		try{
			cuerpoContexto.setNUMSECAC(Long.parseLong(numeroCuenta));
		}catch(final NumberFormatException nfe){
			throw new ControlableException("No se puede realizar la consulta",
					"El formato de alguno de los parámetros es erroneo");
		}
		cuerpoContexto.setCODNRBEEN(super.getEntidad());
		cuerpoContexto.setCODECVBLQPRTCN(estadoBloqueo);
		if(codLastBloq != null){
		    cuerpoContexto.setCODBLOQUEO(codLastBloq);
		}
		if(numLastBloq != null){
            cuerpoContexto.setNUMBLQPRTCN(numLastBloq);
        }
		contextoEntrada.setCODTX(CodTxConstants.COD_TR_PETICION_CONSULTA_BP_TRN);
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());

		contexto.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);

		return contexto;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult getResponse(final Ejecutar.ITRPETICIONCONSULTABP contexto){
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
						ConsultaBloqueosServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta de "
					+ "bloqueos.", e);
		}
		return respuesta;
	}

	/**
	 * Función que valida que la respues del servidor no este vacía.
	 *
	 * @param respuesta Objeto respuesta del servicio web
	 * @return <code>true</code> si la respuesta no esta vacía.
	 */
	private Boolean verificaResponseBansefi(final EjecutarResult respuesta){
		Boolean noNulo = false;
		if(respuesta != null && respuesta.getResponseBansefi() != null){
			noNulo = true;
		}
		return noNulo;
	}

	/**
	 * Función que valida que la respuesta Bansefi contenga una lista de bloqueos de una cuenta.
	 *
	 * @param response Respuesta Bansefi con datos de los bloqueos
	 * @return <code>true</code> si la respuesta Bansefi contiene una lista de bloqueos
	 */
	private Boolean verificaRespuestaTRN(final ResponseBansefi response){
		Boolean noNulo = false;
		if(response != null && response.getOTRPETICIONCONSULTABP() != null &&
				response.getOTRPETICIONCONSULTABP().getTRPETICIONCONSULTABPE() != null &&
				response.getOTRPETICIONCONSULTABP().getTRPETICIONCONSULTABPE()
				.getBPLISTABLOQUEOV() != null){
			noNulo = true;
		}
		return noNulo;
	}

	/**
	 * Método que verifica los códigos de respuesta entregados por el servicio web.
	 *
	 * @param response Respuesta Bansefi con mensajes
	 * @throws NoControlableException Excepción controlada de errores en front end
	 * @throws ControlableException Excepción no controlada de errores en host
	 */
	private void verificaResultado(final ResponseBansefi response)
			throws NoControlableException, ControlableException{
		int codigo = 1;
		if(response.getOTRPETICIONCONSULTABP().getRTRNCD() != 1
		    && response.getOTRPETICIONCONSULTABP().getRTRNCD() != 7){
			for (final ResponseBansefi.OTRPETICIONCONSULTABP.STDTRNMSJPARMV mensaje :
				response.getOTRPETICIONCONSULTABP().getSTDTRNMSJPARMV()) {
				codigo = mensaje.getTEXTCODE();
			}
		}
		if (codigo != 1) {
			EstadoEnum.mensajesError("trn", codigo);
		}
	}
}
