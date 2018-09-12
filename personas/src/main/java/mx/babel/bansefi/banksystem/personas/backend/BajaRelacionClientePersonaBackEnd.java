package mx.babel.bansefi.banksystem.personas.backend;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.bajapersona.BajaRelacionClientePersonaServicio;
import mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.bajapersona.Ejecutar.ITRPEBAJARLPEDSTRN;
import mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.bajapersona.EjecutarResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la TRN de baja de una relación tipo cliente persona.
 * 
 * @author omar.marquez
 * 
 */
@Component
public class BajaRelacionClientePersonaBackEnd extends BackEndBean {

	private static final long serialVersionUID = -5580423938269420054L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Método que ejecuta la TRN de baja de una relación tipo cliente-persona.
	 * 
	 * @param idInternoPersona1
	 * @param idInternoPersona2
	 * @param relacion
	 */
	public void ejecutarTRN(Integer idInternoPersona1,Integer idInternoPersona2, CatalogoBean relacion){
		ITRPEBAJARLPEDSTRN itrpebajarlpedstrn = new ITRPEBAJARLPEDSTRN();

		super.initialize(itrpebajarlpedstrn);
		
		String contenido = relacion.getContenido().substring(0, 3);
		
		if(!"000".equals(contenido) &&
				Integer.parseInt(contenido) < Integer.parseInt(relacion.getClaveFila())){
			itrpebajarlpedstrn.getTRPEBAJARLPEDSEVTY().getPEPERSRLPERSP().setCODNRBEEN(super.getEntidad());
			itrpebajarlpedstrn.getTRPEBAJARLPEDSEVTY().getPEPERSRLPERSP().setIDINTERNOPE1(idInternoPersona2);
			itrpebajarlpedstrn.getTRPEBAJARLPEDSEVTY().getPEPERSRLPERSP().setCODPERSRLPERS(contenido);
			itrpebajarlpedstrn.getTRPEBAJARLPEDSEVTY().getPEPERSRLPERSP().setIDINTERNOPE2(idInternoPersona1);
			itrpebajarlpedstrn.getTRPEBAJARLPEDSEVTY().getINDCODPERLPECONTRARI().setSTDCHAR01("S");
			itrpebajarlpedstrn.getTRPEBAJARLPEDSEVTY().getINDCODPERLPECONTRARI()
				.setDESCRPERSRLPERS(relacion.getDescripcionC());
		} else {
			itrpebajarlpedstrn.getTRPEBAJARLPEDSEVTY().getPEPERSRLPERSP().setCODNRBEEN(super.getEntidad());
			itrpebajarlpedstrn.getTRPEBAJARLPEDSEVTY().getPEPERSRLPERSP().setIDINTERNOPE1(idInternoPersona1);
			itrpebajarlpedstrn.getTRPEBAJARLPEDSEVTY().getPEPERSRLPERSP().setCODPERSRLPERS(relacion.getClaveFila());
			itrpebajarlpedstrn.getTRPEBAJARLPEDSEVTY().getPEPERSRLPERSP().setIDINTERNOPE2(idInternoPersona2);
		}
		
		

		itrpebajarlpedstrn.getSTDTRNIPARMV().setIDINTERNOTERMTN(super.getTerminal());
		itrpebajarlpedstrn.getSTDTRNIPARMV().setCODTX(CodTxConstants.COD_TX_TR_PE_BAJA_RL_PE_DS_TRN);

		EjecutarResult resultado = (EjecutarResult) servicioWebUtils
				.ejecutarWS(BajaRelacionClientePersonaServicio.class,itrpebajarlpedstrn);

		super.verificaRespuesta(resultado);
	}

}