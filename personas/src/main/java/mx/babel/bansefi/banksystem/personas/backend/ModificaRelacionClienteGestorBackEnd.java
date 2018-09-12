package mx.babel.bansefi.banksystem.personas.backend;

import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.BooleanToStringConverter;
import mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.modificagestor.Ejecutar.ITRPEMODIFRLGTTRNI;
import mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.modificagestor.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.modificagestor.ModificaRelacionClienteGestorServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para ejecutar la TRN de modificación de una relación cliente gestor.
 * 
 * @author omar.marquez
 * 
 */
@Component
public class ModificaRelacionClienteGestorBackEnd extends BackEndBean {

	private static final long serialVersionUID = 8946163140081274608L;

	private BooleanToStringConverter convertidor;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Constructor.
	 */
	public ModificaRelacionClienteGestorBackEnd() {
		super();
		this.convertidor = new BooleanToStringConverter();
	}

	/**
	 * Método que ejecuta la TRN de modificación de una relación tipo
	 * cliente-gestor.
	 * 
	 * @param idInternoPersona
	 * @param idInternoGestor
	 * @param indComercial
	 * @param indContacto
	 * @param indEspecial
	 */
	public void ejecutarTRN(Integer idInternoPersona, Integer idInternoGestor,
			boolean indComercial, boolean indContacto, boolean indEspecial){
		ITRPEMODIFRLGTTRNI itrpemodifrlgttrni = initPeticion(idInternoPersona,
				idInternoGestor, indComercial, indContacto, indEspecial);

		EjecutarResult resultado = (EjecutarResult) servicioWebUtils
				.ejecutarWS(ModificaRelacionClienteGestorServicio.class,
						itrpemodifrlgttrni);

		super.verificaRespuesta(resultado);
	}

	/**
	 * Método privado que prepara los datos que serán enviados a la TRN.
	 * 
	 * @param idInternoPersona
	 * @param idInternoGestor
	 * @param indComercial
	 * @param indContacto
	 * @param indEspecial
	 * @return parametros de entrada encapsulados en un objeto
	 *         ITRPEMODIFRLGTTRNI.
	 */
	private ITRPEMODIFRLGTTRNI initPeticion(Integer idInternoPersona,
			Integer idInternoGestor, boolean indComercial, boolean indContacto,
			boolean indEspecial){
		ITRPEMODIFRLGTTRNI itrpemodifrlgttrni = new ITRPEMODIFRLGTTRNI();

		super.initialize(itrpemodifrlgttrni);

		itrpemodifrlgttrni.getTRPEMODIFRLGTEVTY().getPEPERSRLGESTORP()
				.setCODNRBEEN(super.getEntidad());
		itrpemodifrlgttrni.getTRPEMODIFRLGTEVTY().getPEPERSRLGESTORP()
				.setIDINTERNOPE(idInternoPersona);
		itrpemodifrlgttrni.getTRPEMODIFRLGTEVTY().getPEPERSRLGESTORP()
				.setIDINTERNOPEGT(idInternoGestor);

		itrpemodifrlgttrni.getTRPEMODIFRLGTEVTY().setINDGTCOMRCLGT(
				convertidor.convertTo(indComercial));
		itrpemodifrlgttrni.getTRPEMODIFRLGTEVTY().setINDGTCONTCTGT(
				convertidor.convertTo(indContacto));
		itrpemodifrlgttrni.getTRPEMODIFRLGTEVTY().setINDGTSITESPCLGT(
				convertidor.convertTo(indEspecial));

		itrpemodifrlgttrni.getSTDTRNIPARMV().setIDINTERNOTERMTN(
				super.getTerminal());
		itrpemodifrlgttrni.getSTDTRNIPARMV().setCODTX(
				CodTxConstants.COD_TX_TR_PE_MODIF_RL_GT_TRN);

		return itrpemodifrlgttrni;
	}

}