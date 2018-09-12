package mx.babel.bansefi.banksystem.cuentas.backends;

import java.io.Serializable;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.TransaccionalidadBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.cuentas.beans.TraspasoTFBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultatraspasotf.ConsultaTraspasoTFServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultatraspasotf.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultatraspasotf.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultatraspasotf.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaTraspasoTFWrapper;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author manuel.nieto
 * 
 */
@Component
public class ConsultaTraspasoTFBackEnd extends BackEndBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	@Autowired
	CatalogoUtils catalogoUtils;

	@Autowired
	ConsultaTraspasoTFWrapper consultaTraspasoTFWrapper;

	private static final Logger LOGGER = LogManager.getLogger(ConsultaTraspasoTFBackEnd.class);

	/**
	 * Función principal que descencadena todo el llamado de la TRN/WebService
	 * 
	 * @param contadores
	 *            Bean generico para la consulta de Contadores de Centro y
	 *            Puesto
	 * @return ContadoresCentroBean
	 */
	public TraspasoTFBean ejecutarTRN(Long numeroSecuentaTF) {
		Ejecutar.ITRPETCNOBTETRFRCIATR contexto = initPeticion(numeroSecuentaTF);
		EjecutarResult respuesta = ejecutarWS(contexto);
		super.verificaRespuesta(respuesta);
		if (verificaResponseBansefi(respuesta)) {
			return respuestaConsultaTraspasoTF(respuesta.getResponseBansefi());
		}
		return null;
	}

	/**
	 * Funcion que recibe la respuesta de la TRN y Wrappea a un objeto propio
	 * del sistema
	 * 
	 * @param response
	 * @return ContadoresCentroBean
	 */
	private TraspasoTFBean respuestaConsultaTraspasoTF(ResponseBansefi response) {
		TraspasoTFBean traspaso = null;
		if (verificaRespuestaCliente(response)) {
			traspaso = consultaTraspasoTFWrapper.wrappRespuestaConsultaTF(response.getOTRPETCNOBTETRFRCIATR().getTRPETCNOBTETRFRCIAEVT());

			// Recupera el codigo de transferencia
			if (response.getOTRPETCNOBTETRFRCIATR().getTRPETCNOBTETRFRCIAEVT().getTFTFRLTPTFE() != null
					&& response.getOTRPETCNOBTETRFRCIATR().getTRPETCNOBTETRFRCIAEVT().getTFTFRLTPTFE().get(0) != null) {
				traspaso.setCodigoTransferencia(Integer.parseInt(response.getOTRPETCNOBTETRFRCIATR().getTRPETCNOBTETRFRCIAEVT().getTFTFRLTPTFE().get(0).getCODTF()));
			}

			// Entidad
			if (StringUtils.isNotBlank(traspaso.getOrigenEntidad()) && traspaso.getOrigenEntidad().length() > 4) {
				traspaso.setOrigenEntidad(traspaso.getOrigenEntidad().substring(0, 4));
			} else {
				// Si es nulo tomar campo <TF_TRFRCIA_E><COD_NRBE_EN>
				traspaso.setOrigenEntidad(response.getOTRPETCNOBTETRFRCIATR().getTRPETCNOBTETRFRCIAEVT().getTFTRFRCIAE().getCODNRBEEN());
			}

			// Consulta de catalogos

			// COD TIPO TRANSFERENCIA
			try {
				CatalogoBean tipoTransferencia = catalogoUtils.getCatalogoBean(CatalogoEnum.TP_TF, String.valueOf(traspaso.getCodigoTransferencia()));
				if (tipoTransferencia != null) {
					traspaso.setTipoTransferencia(tipoTransferencia.getDescripcionL());
				} else {
					traspaso.setTipoTransferencia(traspaso.getCodigoTipoTransferencia());
				}
			} catch (ControlableException e) {
				LOGGER.debug("No se encontró ninguna coincidencia");
			}

			// COD MOTIDO DEVOLUCION
			if (StringUtils.isNotBlank(traspaso.getCodigoMotivoDevolucion())) {
				try {
					CatalogoBean motivoDevolucion = catalogoUtils.getCatalogoBean(CatalogoEnum.MOT_DEV_TF, traspaso.getCodigoMotivoDevolucion());
					if (motivoDevolucion != null) {
						traspaso.setMotivoDevolucion(traspaso.getCodigoMotivoDevolucion() + " - " + motivoDevolucion.getDescripcionL());
					} else {
						traspaso.setMotivoDevolucion(traspaso.getCodigoMotivoDevolucion());
					}
				} catch (ControlableException e) {
					LOGGER.debug("No se encontró ninguna coincidencia");
				}
			}

			// COD CLASE DE TRANSFERENCIA
			if (StringUtils.isNotBlank(traspaso.getCodigoClaseTransaccion())) {
				try {
					CatalogoBean claseTransferencia = catalogoUtils.getCatalogoBean(CatalogoEnum.CLASE_TF, traspaso.getCodigoClaseTransaccion());
					if (claseTransferencia != null) {
						traspaso.setClaseTransaccion(traspaso.getCodigoClaseTransaccion() + " - " + claseTransferencia.getDescripcionL());
					} else {
						traspaso.setClaseTransaccion(traspaso.getCodigoClaseTransaccion());
					}
				} catch (ControlableException e) {
					LOGGER.debug("No se encontró ninguna coincidencia");
				}
			}

			// TIPO DE ID BENEFICIARIO
			if (StringUtils.isNotBlank(traspaso.getTipoIdBeneficiario())) {
				try {
					CatalogoBean tipoIdBeneficiario = catalogoUtils.getCatalogoBean(CatalogoEnum.TP_ID_EXT_PERS, traspaso.getTipoIdBeneficiario());
					if (tipoIdBeneficiario != null) {
						traspaso.setIdBeneficiario(traspaso.getTipoIdBeneficiario() + " - " + tipoIdBeneficiario);
					} else {
						traspaso.setIdBeneficiario(traspaso.getTipoIdBeneficiario());
					}
				} catch (ControlableException e) {
					LOGGER.debug("No se encontró ninguna coincidencia");
				}
			}

		}

		return traspaso;
	}

	/**
	 * Función que inicializa los parametros de entrada que necesita el servicio
	 * web para ser ejecutado
	 * 
	 * @param contadoresCentroBean
	 * @return Ejecutar.ITROBTECONTTRNI
	 */
	private Ejecutar.ITRPETCNOBTETRFRCIATR initPeticion(Long numeroSecuenciaTF) {
		Ejecutar.ITRPETCNOBTETRFRCIATR contexto = new Ejecutar.ITRPETCNOBTETRFRCIATR();
		Ejecutar.ITRPETCNOBTETRFRCIATR.STDTRNIPARMV contextoEntrada = new Ejecutar.ITRPETCNOBTETRFRCIATR.STDTRNIPARMV();
		Ejecutar.ITRPETCNOBTETRFRCIATR.TRPETCNOBTETRFRCIAEVT datosEntrada = new Ejecutar.ITRPETCNOBTETRFRCIATR.TRPETCNOBTETRFRCIAEVT();

		initialize(contexto);
		initialize(datosEntrada);

		datosEntrada.setSTDCHAR01("0");
		datosEntrada.getTFTRFRCIAP().setCODNRBEEN(super.getEntidad());
		datosEntrada.getTFTRFRCIAP().setNUMSECTF(numeroSecuenciaTF);

		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_PETCN_OBTE_TRFRCIA_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(getTerminal());

		contexto.setTRPETCNOBTETRFRCIAEVT(datosEntrada);
		contexto.setSTDTRNIPARMV(contextoEntrada);

		initialize(contexto);

		return contexto;

	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto
	 *            Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRPETCNOBTETRFRCIATR contexto) {
		EjecutarResult respuesta = null;
		try {
			respuesta = (EjecutarResult) this.servicioWebUtils.ejecutarWS(ConsultaTraspasoTFServicio.class, contexto);
		} catch (NoControlableException e) {
			LOGGER.error("error", e);
			throw new NoControlableException("Error al invocar servicio web de consulta de " + "cosulta de trapaso TF.", e);
		}
		return respuesta;
	}

	/**
	 * Función que valida que la respuesta del servidor no este vacía.
	 * 
	 * @param respuesta
	 *            Objeto respuesta del servicio web
	 * @return <code>true</code> si la respuesta no esta vacía.
	 */
	private Boolean verificaResponseBansefi(EjecutarResult respuesta) {
		Boolean noNulo = false;
		if (respuesta != null && respuesta.getResponseBansefi() != null) {
			noNulo = true;
		}
		return noNulo;
	}

	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con los
	 * datos de la consulta.
	 * 
	 * @param response
	 *            Respuesta Bansefi con datos de la consulta
	 * @return <code>true</code> si la respuesta Bansefi contiene elemento no
	 *         nulos
	 */
	private Boolean verificaRespuestaCliente(ResponseBansefi response) {
		Boolean noNulo = false;
		if (response != null && response.getOTRPETCNOBTETRFRCIATR() != null && response.getOTRPETCNOBTETRFRCIATR().getTRPETCNOBTETRFRCIAEVT() != null) {
			noNulo = true;
		}
		return noNulo;
	}
}
