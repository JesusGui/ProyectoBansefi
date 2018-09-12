package mx.babel.bansefi.banksystem.personas.backend;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.GrupoRelacionadoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;
import mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.consultagrupo.ConsultaGruposRelacionadosServicio;
import mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.consultagrupo.Ejecutar.ITRPERLGRLSTTRNI;
import mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.consultagrupo.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.relacioncliente.consultagrupo.ResponseBansefi.OTRPERLGRLSTTRNO.TRPERLGRLSTEVTZ.TRPERLGRLSTEVTV;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la TRN de consulta de grupos relacionados a un cliente.
 * 
 * @author omar.marquez
 * 
 */
@Component
public class ConsultaGruposRelacionadosBackEnd extends BackEndBean {

	private static final long serialVersionUID = 7718733656722966984L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Método que ejecuta la TRN de consulta de grupos relacionados.
	 * 
	 * @param idInternoPersona
	 * @return lista de grupos relacionados
	 */
	public List<GrupoRelacionadoBean> ejecutarTRN(Integer idInternoPersona){
		ITRPERLGRLSTTRNI itrperlgrlsttrni = initPeticion(idInternoPersona);

		EjecutarResult resultado = (EjecutarResult) servicioWebUtils
				.ejecutarWS(ConsultaGruposRelacionadosServicio.class,
						itrperlgrlsttrni);

		super.verificaRespuesta(resultado);

		return obtenerListaGruposRelacionados(resultado);
	}

	/**
	 * Método privado que prepara los datos que serán enviados a la TRN.
	 * 
	 * @param idInternoPersona
	 * @return parametros de entrada encapsulados en un objeto ITRPERLGRLSTTRNI
	 */
	private ITRPERLGRLSTTRNI initPeticion(Integer idInternoPersona){
		ITRPERLGRLSTTRNI itrperlgrlsttrni = new ITRPERLGRLSTTRNI();

		super.initialize(itrperlgrlsttrni);

		itrperlgrlsttrni.setELEVATORPOSITION(0);
		itrperlgrlsttrni.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);

		itrperlgrlsttrni.getTRPERLGRLSTEVTY().getGRGRPRLPERSP()
				.setCODNRBEEN(super.getEntidad());
		itrperlgrlsttrni.getTRPERLGRLSTEVTY().getGRGRPRLPERSP()
				.setIDINTERNOPE(idInternoPersona);

		itrperlgrlsttrni.getSTDTRNIPARMV().setIDINTERNOTERMTN(
				super.getTerminal());
		itrperlgrlsttrni.getSTDTRNIPARMV().setCODTX(
				CodTxConstants.COD_TX_TR_PE_RL_GR_LST_TRN);

		return itrperlgrlsttrni;
	}

	/**
	 * Método privado que devuelve una lista de grupos relacionados a partir del
	 * resultado de la consulta.
	 * 
	 * @param idInternoPersona
	 * @return lista de grupos relacionados
	 * @throws NullPointerException
	 */
	private List<GrupoRelacionadoBean> obtenerListaGruposRelacionados(
			EjecutarResult resultado) throws NullPointerException {
		List<GrupoRelacionadoBean> grupos = null;
		if (resultado.getResponseBansefi().getOTRPERLGRLSTTRNO()
				.getNUMBEROFRECORDS() > 0) {
			grupos = new ArrayList<>();
			for (TRPERLGRLSTEVTV elemento : resultado.getResponseBansefi()
					.getOTRPERLGRLSTTRNO().getTRPERLGRLSTEVTZ()
					.getTRPERLGRLSTEVTV()) {
				GrupoRelacionadoBean grupo = crearGrupoRelacionadoBean(elemento);
				if (grupo != null && grupo.getIdExterna() != null
						&& !grupo.getIdExterna().isEmpty()) {
					grupos.add(grupo);
				}
			}
		}
		return grupos;
	}

	/**
	 * Método privado que devuelve un objeto tipo GrupoRelacionadoBean.
	 * 
	 * @param elemento
	 * @return grupo
	 * @throws NullPointerException
	 */
	private GrupoRelacionadoBean crearGrupoRelacionadoBean(
			TRPERLGRLSTEVTV elemento) throws NullPointerException {
		GrupoRelacionadoBean grupo = null;
		if (elemento != null) {
			grupo = new GrupoRelacionadoBean();
			grupo.setIdExterna(elemento.getIDEXTGR().trim());
			grupo.setNombre(elemento.getNOMBGR().trim());
			grupo.setTipoGrupo(elemento.getCODGRP().trim());
			grupo.setEstado(EstadoListadosEnum.CONSULTADO);
		}
		return grupo;
	}

}