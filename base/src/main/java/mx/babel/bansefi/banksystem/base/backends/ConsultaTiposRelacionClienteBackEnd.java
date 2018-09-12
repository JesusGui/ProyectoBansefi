package mx.babel.bansefi.banksystem.base.backends;

import java.util.Date;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.PersonaRelacionadaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.base.utils.StringToDateConverter;
import mx.babel.bansefi.banksystem.base.webservices.consultatiposrelacioncliente.ConsultaTiposRelacionClienteServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultatiposrelacioncliente.Ejecutar.ITRPECONSRLPEDSTRN;
import mx.babel.bansefi.banksystem.base.webservices.consultatiposrelacioncliente.EjecutarResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la TRN de consulta de detalle de las relaciones de un cliente.
 * 
 * @author omar.marquez
 * 
 */
@Component
public class ConsultaTiposRelacionClienteBackEnd extends BackEndBean {

	private static final long serialVersionUID = 5667572769651847571L;

	private IntegerToDateConverter integerToDateConverter;
	private StringToDateConverter stringToDateConverter;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Constructor.
	 */
	public ConsultaTiposRelacionClienteBackEnd() {
		super();
		this.integerToDateConverter = new IntegerToDateConverter();
		this.stringToDateConverter = new StringToDateConverter();
	}

	/**
	 * Método que ejecuta la TRN de consulta tipos de relación de un cliente
	 * (detalle relación cliente-persona).
	 * 
	 * @param idInternoPersona1
	 * @param idInternoPersona2
	 * @param codigoRelacion
	 * @return persona
	 * @throws NullPointerException
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	public PersonaRelacionadaBean ejecutarTRN(Integer idInternoPersona1,
			Integer idInternoPersona2, String codigoRelacion)
			throws NullPointerException, ControlableException,
			NoControlableException {
		ITRPECONSRLPEDSTRN itrpeconsrlpedstrn = initPeticion(idInternoPersona1,
				idInternoPersona2, codigoRelacion);

		EjecutarResult resultado = (EjecutarResult) servicioWebUtils
				.ejecutarWS(ConsultaTiposRelacionClienteServicio.class,
						itrpeconsrlpedstrn);

		try {
			super.verificaRespuesta(resultado);
		} catch (ControlableException e) {
			if (e.getRtncod() != RETURN_COD_SIN_DATOS) {
				throw e;
			} else {
				ITRPECONSRLPEDSTRN itrpeconsrlpedstrn2 = initPeticion(
						idInternoPersona2, idInternoPersona1, codigoRelacion);

				EjecutarResult resultado2 = (EjecutarResult) servicioWebUtils
						.ejecutarWS(ConsultaTiposRelacionClienteServicio.class,
								itrpeconsrlpedstrn2);

				super.verificaRespuesta(resultado2);

				return obtenerPersonaRelacionadaBean(resultado2);
			}
		}

		return obtenerPersonaRelacionadaBean(resultado);
	}

	/**
	 * Método privado que prepara los datos que serán enviados a la TRN.
	 * 
	 * @param idInternoPersona1
	 * @param idInternoPersona2
	 * @param codigoRelacion
	 * @return parametros de entrada encapsulados en un objeto
	 *         ITRPECONSRLPEDSTRN
	 * @throws NullPointerException
	 */
	private ITRPECONSRLPEDSTRN initPeticion(Integer idInternoPersona1,
			Integer idInternoPersona2, String codigoRelacion)
			throws NullPointerException {
		ITRPECONSRLPEDSTRN itrpeconsrlpedstrn = new ITRPECONSRLPEDSTRN();

		super.initialize(itrpeconsrlpedstrn);

		itrpeconsrlpedstrn.getTRPECONSRLPEDSEVTY().getPEPERSRLPERSP()
				.setCODNRBEEN(super.getEntidad());
		itrpeconsrlpedstrn.getTRPECONSRLPEDSEVTY().getPEPERSRLPERSP()
				.setIDINTERNOPE1(idInternoPersona1);
		itrpeconsrlpedstrn.getTRPECONSRLPEDSEVTY().getPEPERSRLPERSP()
				.setCODPERSRLPERS(codigoRelacion);
		itrpeconsrlpedstrn.getTRPECONSRLPEDSEVTY().getPEPERSRLPERSP()
				.setIDINTERNOPE2(idInternoPersona2);

		itrpeconsrlpedstrn.getSTDTRNIPARMV().setIDINTERNOTERMTN(
				super.getTerminal());
		itrpeconsrlpedstrn.getSTDTRNIPARMV().setCODTX(
				CodTxConstants.COD_TX_TR_PE_CONS_RL_PE_DS_TRN);

		return itrpeconsrlpedstrn;
	}

	/**
	 * Método privado que devuelve un objeto tipo PersonaRelacionadaBean con los
	 * campos complementarios de la persona (observaciones, fecha inicio y fecha
	 * fin).
	 * 
	 * @param resultado
	 * @return persona con datos complementarios
	 * @throws NullPointerException
	 */
	private PersonaRelacionadaBean obtenerPersonaRelacionadaBean(
			EjecutarResult resultado) throws NullPointerException {
		PersonaRelacionadaBean persona = null;
		if (resultado.getResponseBansefi().getOTRPECONSRLPEDSTRN()
				.getTRPECONSRLPEDSEVTZ() != null) {
			persona = new PersonaRelacionadaBean();
			persona.setObservaciones(resultado.getResponseBansefi()
					.getOTRPECONSRLPEDSTRN().getTRPECONSRLPEDSEVTZ()
					.getTXTPERLPE().trim());
			persona.setFechaCreacion(integerToDateConverter.convertTo(resultado
					.getResponseBansefi().getOTRPECONSRLPEDSTRN()
					.getTRPECONSRLPEDSEVTZ().getFECHAINICRL()));
			persona.setFechaInicio(integerToDateConverter.convertTo(resultado
					.getResponseBansefi().getOTRPECONSRLPEDSTRN()
					.getTRPECONSRLPEDSEVTZ().getFECHAINICVLDZ()));

			Date fechaFinHost = integerToDateConverter.convertTo(resultado
					.getResponseBansefi().getOTRPECONSRLPEDSTRN()
					.getTRPECONSRLPEDSEVTZ().getFECHAFINVLDZ());

			Date maxFechaFin = stringToDateConverter
					.convertTo(ConstantesFuncionales.MAX_FECHA_FIN);

			if (fechaFinHost.compareTo(maxFechaFin) == 0) {
				persona.setFechaFin(null);
			} else {
				persona.setFechaFin(fechaFinHost);
			}
		}
		return persona;
	}
	
}