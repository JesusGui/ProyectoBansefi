package mx.babel.bansefi.banksystem.personas.backend;

import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.BooleanToStringConverter;
import mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.altagestor.AltaRelacionClienteGestorServicio;
import mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.altagestor.Ejecutar.ITRPEALTARLGTTRNI;
import mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.altagestor.EjecutarResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la TRN de alta de una relación tipo cliente gestor.
 * 
 * @author omar.marquez
 * 
 */
@Component
public class AltaRelacionClienteGestorBackEnd extends BackEndBean {

	private static final long serialVersionUID = 6290368740174379869L;

	private BooleanToStringConverter convertidor;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Constructor.
	 */
	public AltaRelacionClienteGestorBackEnd() {
		super();
		this.convertidor = new BooleanToStringConverter();
	}

	/**
	 * Método que ejecuta la TRN de alta de una relación tipo cliente-gestor.
	 * 
	 * @param idInternaPersona
	 * @param idInternaGestor
	 * @param indComercial
	 * @param indContacto
	 * @param indEspecial
	 */
	public void ejecutarTRN(Integer idInternaPersona, Integer idInternaGestor,
			boolean indComercial, boolean indContacto, boolean indEspecial) {
		ITRPEALTARLGTTRNI itrpealtarlgttrni = initPeticion(idInternaPersona,
				idInternaGestor, indComercial, indContacto, indEspecial);

		EjecutarResult resultado = (EjecutarResult) servicioWebUtils
				.ejecutarWS(AltaRelacionClienteGestorServicio.class,
						itrpealtarlgttrni);

		super.verificaRespuesta(resultado);
	}

	/**
	 * Método privado que prepara los datos que serán enviados a la TRN.
	 * 
	 * @param idInternaPersona
	 * @param idInternaGestor
	 * @param indComercial
	 * @param indContacto
	 * @param indEspecial
	 * @return parametros de entrada encapsulados en un objeto
	 *         ITRPEALTARLGTTRNI.
	 */
	private ITRPEALTARLGTTRNI initPeticion(Integer idInternaPersona,
			Integer idInternaGestor, boolean indComercial, boolean indContacto,
			boolean indEspecial){
		ITRPEALTARLGTTRNI itrpealtarlgttrni = new ITRPEALTARLGTTRNI();

		super.initialize(itrpealtarlgttrni);

		itrpealtarlgttrni.getTRPEALTARLGTEVTY()
				.setCODNRBEEN(super.getEntidad());
		itrpealtarlgttrni.getTRPEALTARLGTEVTY()
				.setIDINTERNOPE(idInternaPersona);
		itrpealtarlgttrni.getTRPEALTARLGTEVTY().setIDINTERNOPEGT(
				idInternaGestor);
		itrpealtarlgttrni.getTRPEALTARLGTEVTY().setINDGTCOMRCLGT(
				convertidor.convertTo(indComercial));
		itrpealtarlgttrni.getTRPEALTARLGTEVTY().setINDGTCONTCTGT(
				convertidor.convertTo(indContacto));
		itrpealtarlgttrni.getTRPEALTARLGTEVTY().setINDGTSITESPCLGT(
				convertidor.convertTo(indEspecial));

		itrpealtarlgttrni.getSTDTRNIPARMV().setIDINTERNOTERMTN(
				super.getTerminal());
		itrpealtarlgttrni.getSTDTRNIPARMV().setCODTX(
				CodTxConstants.COD_TX_TR_PE_ALTA_RL_GT_TRN);

		return itrpealtarlgttrni;
	}

}