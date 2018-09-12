package mx.babel.bansefi.banksystem.personas.backend;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.GestorRelacionadoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;
import mx.babel.bansefi.banksystem.base.utils.BooleanToStringConverter;
import mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.consultagestor.ConsultaGestoresRelacionadosServicio;
import mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.consultagestor.Ejecutar.ITRPERLGTLSCNSTRNI;
import mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.consultagestor.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.consultagestor.ResponseBansefi.OTRPERLGTLSCNSTRNO.TRPERLGTLSCNSEVTZ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la TRN de consulta de gestores relacionados a un cliente.
 * 
 * @author omar.marquez
 * 
 */
@Component
public class ConsultaGestoresRelacionadosBackEnd extends BackEndBean {

	private static final long serialVersionUID = 9179868696902563894L;

	private BooleanToStringConverter convertidor;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Constructor.
	 */
	public ConsultaGestoresRelacionadosBackEnd() {
		super();
		this.convertidor = new BooleanToStringConverter();
	}

	/**
	 * Método que ejecuta la TRN de consulta de gestores relacionados.
	 * 
	 * @param idInternoPersona
	 * @return lista de gestores relacionados
	 */
	public List<GestorRelacionadoBean> ejecutarTRN(Integer idInternoPersona){
		ITRPERLGTLSCNSTRNI itrperlgtlscnstrni = initPeticion(idInternoPersona);

		EjecutarResult resultado = (EjecutarResult) servicioWebUtils
				.ejecutarWS(ConsultaGestoresRelacionadosServicio.class,
						itrperlgtlscnstrni);

		super.verificaRespuesta(resultado);

		return obtenerListaGestoresRelacionados(resultado);
	}

	/**
	 * Método privado que prepara los datos que serán enviados a la TRN.
	 * 
	 * @param idInternoPersona
	 * @return parametros de entrada encapsulados en un objeto
	 *         ITRPERLGTLSCNSTRNI
	 */
	private ITRPERLGTLSCNSTRNI initPeticion(Integer idInternoPersona){
		ITRPERLGTLSCNSTRNI itrperlgtlscnstrni = new ITRPERLGTLSCNSTRNI();

		super.initialize(itrperlgtlscnstrni);

		itrperlgtlscnstrni.setELEVATORPOSITION(0);
		itrperlgtlscnstrni.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);

		itrperlgtlscnstrni.getTRPERLGTLSCNSEVTY().getPEPERSRLGESTORP()
				.setCODNRBEEN(super.getEntidad());
		itrperlgtlscnstrni.getTRPERLGTLSCNSEVTY().getPEPERSRLGESTORP()
				.setIDINTERNOPE(idInternoPersona);

		itrperlgtlscnstrni.getSTDTRNIPARMV().setIDINTERNOTERMTN(
				super.getTerminal());
		itrperlgtlscnstrni.getSTDTRNIPARMV().setCODTX(
				CodTxConstants.COD_TX_TR_PE_RL_GT_LS_CNS_TRN);

		return itrperlgtlscnstrni;
	}

	/**
	 * Método privado que devuelve una lista de gestores relacionados a partir
	 * del resultado de la consulta.
	 * 
	 * @param resultado
	 * @return lista de gestores relacionados
	 * @throws NullPointerException
	 */
	private List<GestorRelacionadoBean> obtenerListaGestoresRelacionados(
			EjecutarResult resultado) throws NullPointerException {
		List<GestorRelacionadoBean> gestores = null;
		if (resultado.getResponseBansefi().getOTRPERLGTLSCNSTRNO()
				.getNUMBEROFRECORDS() > 0) {
			gestores = new ArrayList<>();
			for (TRPERLGTLSCNSEVTZ elemento : resultado.getResponseBansefi()
					.getOTRPERLGTLSCNSTRNO().getTRPERLGTLSCNSEVTZ()) {
				GestorRelacionadoBean gestor = crearGestorRelacionadoBean(elemento);
				if (gestor != null && gestor.getIdInterna() != null
						&& gestor.getIdInterna() != 0) {
					gestores.add(gestor);
				}
			}
		}
		return gestores;
	}

	/**
	 * Método privado que devuelve un objeto tipo GestorRelacionadoBean.
	 * 
	 * @param elemento
	 * @return gestor
	 * @throws NullPointerException
	 */
	private GestorRelacionadoBean crearGestorRelacionadoBean(
			TRPERLGTLSCNSEVTZ elemento) throws NullPointerException {
		GestorRelacionadoBean gestor = null;
		if (elemento != null) {
			gestor = new GestorRelacionadoBean();
			gestor.setIdInterna(elemento.getIDINTERNOPE());
			gestor.setIdExterna(elemento.getCODIDEXTPERS().trim());
			gestor.setNombre(elemento.getNOMBGT().trim());
			gestor.setChkComercial(convertidor.convertFrom(elemento
					.getINDGTCOMRCLGT().trim()));
			gestor.setChkContacto(convertidor.convertFrom(elemento
					.getINDGTCONTCTGT().trim()));
			gestor.setChkEspecial(convertidor.convertFrom(elemento
					.getINDGTSITESPCLGT().trim()));
			gestor.setEstado(EstadoListadosEnum.CONSULTADO);
		}
		return gestor;
	}

}