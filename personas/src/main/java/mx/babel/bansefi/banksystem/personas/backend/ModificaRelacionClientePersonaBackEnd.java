package mx.babel.bansefi.banksystem.personas.backend;

import java.text.ParseException;
import java.util.Date;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.modificapersona.Ejecutar.ITRPEMODIRLPEDSTRN;
import mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.modificapersona.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.modificapersona.ModificaRelacionClientePersonaServicio;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para ejecutar la TRN de modificación de una relación cliente persona.
 * 
 * @author omar.marquez
 * 
 */
@Component
public class ModificaRelacionClientePersonaBackEnd extends BackEndBean {

	private static final long serialVersionUID = 4163596105223565342L;

	private IntegerToDateConverter integerToDateConverter;

	/**
	 * Constructor.
	 */
	public ModificaRelacionClientePersonaBackEnd() {
		super();
		this.integerToDateConverter = new IntegerToDateConverter();
	}

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Método que ejecuta la TRN de modificación de una relación tipo
	 * cliente-persona.
	 * 
	 * @param idInternoPersona1
	 * @param idInternoPersona2
	 * @param codigoRelacion
	 * @param observaciones
	 * @param fechaInicio
	 * @param fechaFin
	 */
	public void ejecutarTRN(Integer idInternoPersona1,
			Integer idInternoPersona2, CatalogoBean relacion,
			String observaciones, Date fechaInicio, Date fechaFin) {
		ITRPEMODIRLPEDSTRN itrpemodirlpedstrn = initPeticion(idInternoPersona1,
				idInternoPersona2, relacion, observaciones, fechaInicio,
				fechaFin);

		EjecutarResult resultado = (EjecutarResult) servicioWebUtils
				.ejecutarWS(ModificaRelacionClientePersonaServicio.class,
						itrpemodirlpedstrn);

		super.verificaRespuesta(resultado);
	}

	/**
	 * Método privado que prepara los datos que serán enviados a la TRN.
	 * 
	 * @param idInternoPersona1
	 * @param idInternoPersona2
	 * @param relacion
	 * @param observaciones
	 * @param fechaInicio
	 * @param fechaFin
	 * @return parametros de entrada encapsulados en un objeto
	 *         ITRPEMODIRLPEDSTRN
	 */
	public ITRPEMODIRLPEDSTRN initPeticion(Integer idInternoPersona1,
			Integer idInternoPersona2, CatalogoBean relacion,
			String observaciones, Date fechaInicio, Date fechaFin) {
		ITRPEMODIRLPEDSTRN itrpemodirlpedstrn = new ITRPEMODIRLPEDSTRN();

		super.initialize(itrpemodirlpedstrn);

		String contenido = relacion.getContenido().substring(0, 3);

		if (!"000".equals(contenido)
				&& Integer.parseInt(contenido) < Integer.parseInt(relacion
						.getClaveFila())) {
			itrpemodirlpedstrn.getTRPEMODIRLPEDSEVTY().getPEPERSRLPERSP()
					.setCODNRBEEN(super.getEntidad());
			itrpemodirlpedstrn.getTRPEMODIRLPEDSEVTY().getPEPERSRLPERSP()
					.setIDINTERNOPE1(idInternoPersona2);
			itrpemodirlpedstrn.getTRPEMODIRLPEDSEVTY().getPEPERSRLPERSP()
					.setCODPERSRLPERS(contenido);
			itrpemodirlpedstrn.getTRPEMODIRLPEDSEVTY().getPEPERSRLPERSP()
					.setIDINTERNOPE2(idInternoPersona1);
			itrpemodirlpedstrn.getTRPEMODIRLPEDSEVTY().getINDCODPERLPECONTRARI().setSTDCHAR01("S");
			itrpemodirlpedstrn.getTRPEMODIRLPEDSEVTY().getINDCODPERLPECONTRARI()
					.setDESCRPERSRLPERS(relacion.getDescripcionC());
		} else {
			itrpemodirlpedstrn.getTRPEMODIRLPEDSEVTY().getPEPERSRLPERSP()
					.setCODNRBEEN(super.getEntidad());
			itrpemodirlpedstrn.getTRPEMODIRLPEDSEVTY().getPEPERSRLPERSP()
					.setIDINTERNOPE1(idInternoPersona1);
			itrpemodirlpedstrn.getTRPEMODIRLPEDSEVTY().getPEPERSRLPERSP()
					.setCODPERSRLPERS(relacion.getClaveFila());
			itrpemodirlpedstrn.getTRPEMODIRLPEDSEVTY().getPEPERSRLPERSP()
					.setIDINTERNOPE2(idInternoPersona2);
		}

		itrpemodirlpedstrn.getTRPEMODIRLPEDSEVTY().setTXTPERLPE(observaciones);

		if (fechaInicio == null) {
			itrpemodirlpedstrn.getTRPEMODIRLPEDSEVTY().setFECHAINICVLDZ(0);
		} else {
			itrpemodirlpedstrn.getTRPEMODIRLPEDSEVTY().setFECHAINICVLDZ(
					obtenerFechaValor(fechaInicio));
		}

		if (fechaFin == null) {
			// try {
			// fechaFin = DateUtils.parseDate(
			// ConstantesFuncionales.MAX_FECHA_FIN,
			// ConstantesFuncionales.GENERAL_DATE_FORMATTER_INT);
			// } catch (ParseException e) {
			// fechaFin = new Date(Long.MAX_VALUE);
			// }
			itrpemodirlpedstrn.getTRPEMODIRLPEDSEVTY().setFECHAFINVLDZ(0);
		} else {
			itrpemodirlpedstrn.getTRPEMODIRLPEDSEVTY().setFECHAFINVLDZ(
					obtenerFechaValor(fechaFin));
		}

		itrpemodirlpedstrn.getSTDTRNIPARMV().setIDINTERNOTERMTN(
				super.getTerminal());
		itrpemodirlpedstrn.getSTDTRNIPARMV().setCODTX(
				CodTxConstants.COD_TX_TR_PE_MODI_RL_PE_DS_TRN);

		return itrpemodirlpedstrn;
	}

	/**
	 * Método privado que convierte una fecha a Integer. Este método llama al
	 * convertidor IntegerToDateConverter pero sí ocurre un error de parseo
	 * entonces devuelve 0 para los campos tipo fecha.
	 * 
	 * @param fecha
	 * @return valorFecha
	 */
	private Integer obtenerFechaValor(Date fecha) {
		Integer valorFecha = null;
		valorFecha = integerToDateConverter.convertFrom(fecha);
		if (valorFecha != null) {
			return valorFecha;
		}
		return 0;
	}

}