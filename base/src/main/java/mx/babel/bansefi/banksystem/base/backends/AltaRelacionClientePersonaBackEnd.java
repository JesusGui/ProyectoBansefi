package mx.babel.bansefi.banksystem.base.backends;

import java.util.Calendar;
import java.util.Date;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.base.utils.StringToDateConverter;
import mx.babel.bansefi.banksystem.base.webservices.relacioncliente.altapersona.AltaRelacionClientePersonaServicio;
import mx.babel.bansefi.banksystem.base.webservices.relacioncliente.altapersona.Ejecutar.ITRPEALTARLPEDSTRN;
import mx.babel.bansefi.banksystem.base.webservices.relacioncliente.altapersona.EjecutarResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la TRN de alta de una relación tipo cliente persona.
 * 
 * @author omar.marquez
 * 
 */
@Component
public class AltaRelacionClientePersonaBackEnd extends BackEndBean {

	private static final long serialVersionUID = 7842634345093406146L;

	private IntegerToDateConverter integerToDateConverter;
	private StringToDateConverter stringToDateConverter;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Constructor.
	 */
	public AltaRelacionClientePersonaBackEnd() {
		super();
		this.integerToDateConverter = new IntegerToDateConverter();
		this.stringToDateConverter = new StringToDateConverter();
	}

	/**
	 * Método que ejecuta la TRN de alta de una relación tipo cliente-persona.
	 * 
	 * @param idInternoPersona1
	 * @param idInternoPersona2
	 * @param relacionSeleccionada
	 * @param observaciones
	 * @param fechaInicio
	 * @param fechaFin
	 * @throws NullPointerException
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	public void ejecutarTRN(Integer idInternoPersona1,
			Integer idInternoPersona2, CatalogoBean relacionSeleccionada,
			String observaciones, Date fechaInicio, Date fechaFin)
			throws NullPointerException, ControlableException,
			NoControlableException {
		ITRPEALTARLPEDSTRN itrpealtarlpedstrn = initPeticion(idInternoPersona1,
				idInternoPersona2, relacionSeleccionada, observaciones, fechaInicio,
				fechaFin);

		EjecutarResult resultado = (EjecutarResult) servicioWebUtils
				.ejecutarWS(AltaRelacionClientePersonaServicio.class,
						itrpealtarlpedstrn);

		super.verificaRespuesta(resultado);
	}

	/**
	 * Método privado que prepara los datos que serán enviados a la TRN.
	 * 
	 * @param idInternaPersona1
	 * @param idInternaPersona2
	 * @param relacionSeleccionada
	 * @param observaciones
	 * @param fechaInicio
	 * @param fechaFin
	 * @return parametros de entrada encapsulados en un objeto
	 *         ITRPEALTARLPEDSTRN
	 */
	private ITRPEALTARLPEDSTRN initPeticion(Integer idInternoPersona1,
			Integer idInternoPersona2, CatalogoBean relacionSeleccionada,
			String observaciones, Date fechaInicio, Date fechaFin) {
		ITRPEALTARLPEDSTRN itrpealtarlpedstrn = new ITRPEALTARLPEDSTRN();

		super.initialize(itrpealtarlpedstrn);
		
		String contenido = relacionSeleccionada.getContenido().substring(0, 3);

		itrpealtarlpedstrn.getTRPEALTARLPEDSEVTY().setCODNRBEEN(
				super.getEntidad());
		
		if(!"000".equals(contenido) &&
				Integer.parseInt(contenido) < Integer.parseInt(relacionSeleccionada.getClaveFila())){
			itrpealtarlpedstrn.getTRPEALTARLPEDSEVTY().setIDINTERNOPE1(
					idInternoPersona2);
			itrpealtarlpedstrn.getTRPEALTARLPEDSEVTY().setIDINTERNOPE2(
					idInternoPersona1);
			itrpealtarlpedstrn.getTRPEALTARLPEDSEVTY().setCODPERSRLPERS(
					contenido);
			itrpealtarlpedstrn.getTRPEALTARLPEDSEVTY().getINDCODPERLPECONTRARI().setSTDCHAR01("S");
			itrpealtarlpedstrn.getTRPEALTARLPEDSEVTY().getINDCODPERLPECONTRARI()
				.setDESCRPERSRLPERS(relacionSeleccionada.getDescripcionC());
		}else{
			itrpealtarlpedstrn.getTRPEALTARLPEDSEVTY().setIDINTERNOPE1(
					idInternoPersona1);
			itrpealtarlpedstrn.getTRPEALTARLPEDSEVTY().setIDINTERNOPE2(
					idInternoPersona2);
			itrpealtarlpedstrn.getTRPEALTARLPEDSEVTY().setCODPERSRLPERS(
					relacionSeleccionada.getClaveFila());
		}
		

		itrpealtarlpedstrn.getTRPEALTARLPEDSEVTY().setTXTPERLPE(observaciones);

		itrpealtarlpedstrn.getTRPEALTARLPEDSEVTY().setFECHAINICRL(
				integerToDateConverter.convertFrom(Calendar.getInstance()
						.getTime()));

		if (fechaInicio == null) {
			itrpealtarlpedstrn.getTRPEALTARLPEDSEVTY()
					.setFECHAINICVLDZ(
							itrpealtarlpedstrn.getTRPEALTARLPEDSEVTY()
									.getFECHAINICRL());
		} else {
			itrpealtarlpedstrn.getTRPEALTARLPEDSEVTY().setFECHAINICVLDZ(
					integerToDateConverter.convertFrom(fechaInicio));
		}

		if (fechaFin == null) {
			itrpealtarlpedstrn.getTRPEALTARLPEDSEVTY().setFECHAFINVLDZ(
					integerToDateConverter.convertFrom(stringToDateConverter
							.convertTo(ConstantesFuncionales.MAX_FECHA_FIN)));
		} else {
			itrpealtarlpedstrn.getTRPEALTARLPEDSEVTY().setFECHAFINVLDZ(
					integerToDateConverter.convertFrom(fechaFin));
		}

		itrpealtarlpedstrn.getSTDTRNIPARMV().setIDINTERNOTERMTN(
				super.getTerminal());
		itrpealtarlpedstrn.getSTDTRNIPARMV().setCODTX(
				CodTxConstants.COD_TX_TR_PE_ALTA_RL_PE_DS_TRN);

		return itrpealtarlpedstrn;
	}

}