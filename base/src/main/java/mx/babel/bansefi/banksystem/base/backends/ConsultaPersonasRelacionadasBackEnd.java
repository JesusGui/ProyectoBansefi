package mx.babel.bansefi.banksystem.base.backends;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.PersonaRelacionadaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.webservices.consultapersonasrelacionadas.ConsultaPersonasRelacionadasServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultapersonasrelacionadas.Ejecutar.ITRPERLPELSCNSTRNI;
import mx.babel.bansefi.banksystem.base.webservices.consultapersonasrelacionadas.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultapersonasrelacionadas.ResponseBansefi.OTRPERLPELSCNSTRNO.TRPERLPELSCNSEVTZ.PERLPELSV;
import mx.babel.bansefi.banksystem.base.webservices.consultapersonasrelacionadas.ResponseBansefi.OTRPERLPELSCNSTRNO.TRPERLPELSCNSEVTZ.PEPERSRLPERSP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la TRN de consulta de personas relacionadas a un cliente.
 * 
 * @author omar.marquez
 * 
 */
@Component
public class ConsultaPersonasRelacionadasBackEnd extends BackEndBean {

	private static final long serialVersionUID = 7550058947660458005L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	@Autowired
	CatalogoUtils catalogoUtils;

	/**
	 * Constructor.
	 */
	public ConsultaPersonasRelacionadasBackEnd() {
		super();
	}

	/**
	 * Método que ejecuta la TRN de consulta de personas relacionadas.
	 * 
	 * @param idInternoPersona
	 * @return lista de personas relacionadas
	 * @throws NullPointerException
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	public List<PersonaRelacionadaBean> ejecutarTRN(Integer idInternoPersona)
			throws NullPointerException, ControlableException,
			NoControlableException {
		ITRPERLPELSCNSTRNI itrperlpelscnstrni = initPeticion(idInternoPersona);
		
		EjecutarResult resultado = (EjecutarResult) servicioWebUtils
				.ejecutarWS(ConsultaPersonasRelacionadasServicio.class,
						itrperlpelscnstrni);
		try{
		    super.verificaRespuesta(resultado);
		
    	}catch(ControlableException ce){
            if(ce.getRtncod()==RETURN_COD_SIN_DATOS){
                return new ArrayList<PersonaRelacionadaBean>();
            }else{
                throw ce;
            }
    }
		

		return obtenerListaPersonasRelacionadas(resultado, idInternoPersona);
	}

	/**
	 * Método privado que prepara los datos que serán enviados a la TRN.
	 * 
	 * @param idInternoPersona
	 * @return parametros de entrada encapsulados en un objeto
	 *         ITRPERLPELSCNSTRNI
	 * @throws NullPointerException
	 */
	private ITRPERLPELSCNSTRNI initPeticion(Integer idInternoPersona)
			throws NullPointerException {
		ITRPERLPELSCNSTRNI itrperlpelscnstrni = new ITRPERLPELSCNSTRNI();

		super.initialize(itrperlpelscnstrni);

		itrperlpelscnstrni.setELEVATORPOSITION(0);
		itrperlpelscnstrni.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);

		itrperlpelscnstrni.getTRPERLPELSCNSEVTY().getPEPERSP()
				.setCODNRBEEN(super.getEntidad());
		itrperlpelscnstrni.getTRPERLPELSCNSEVTY().getPEPERSP()
				.setIDINTERNOPE(idInternoPersona);

		itrperlpelscnstrni.getSTDTRNIPARMV().setIDINTERNOTERMTN(
				super.getTerminal());
		itrperlpelscnstrni.getSTDTRNIPARMV().setCODTX(
				CodTxConstants.COD_TX_TR_PE_RL_PE_LS_CNS_TRN);

		return itrperlpelscnstrni;
	}

	/**
	 * Método privado que devuelve una lista de personas relacionadas a partir
	 * del resultado de la consulta.
	 * 
	 * @param resultado
	 * @return lista de personas relacionadas
	 * @throws NullPointerException
	 */
	private List<PersonaRelacionadaBean> obtenerListaPersonasRelacionadas(
			EjecutarResult resultado, Integer idInternoPersona) throws NullPointerException {
		List<PersonaRelacionadaBean> personas = null;
		if (resultado.getResponseBansefi().getOTRPERLPELSCNSTRNO()
				.getNUMBEROFRECORDS() > 0) {
			personas = new ArrayList<>();
			for (int i = 0; i < resultado.getResponseBansefi().getOTRPERLPELSCNSTRNO()
				.getNUMBEROFRECORDS(); i++){
				PEPERSRLPERSP relacion = resultado.getResponseBansefi()
						.getOTRPERLPELSCNSTRNO().getTRPERLPELSCNSEVTZ()
						.getPEPERSRLPERSP().get(i);
				PERLPELSV elemento = resultado.getResponseBansefi()
						.getOTRPERLPELSCNSTRNO().getTRPERLPELSCNSEVTZ()
						.getPERLPELSV().get(i);
				PersonaRelacionadaBean persona = crearPersonaRelacionadaBean(elemento, relacion, idInternoPersona);
				if (persona != null && persona.getIdInterna() != null
						&& persona.getIdInterna() != 0) {
					personas.add(persona);
				}
			}
		}
		return personas;
	}

	/**
	 * Método privado que devuelve un objeto tipo PersonaRelacionadaBean.
	 * 
	 * @param elemento
	 * @return persona
	 * @throws NullPointerException
	 */
	private PersonaRelacionadaBean crearPersonaRelacionadaBean(
			PERLPELSV elemento, PEPERSRLPERSP relacion, Integer idInternoPersona) throws NullPointerException {
		PersonaRelacionadaBean persona = null;
		if (elemento != null) {
			persona = new PersonaRelacionadaBean();
			persona.setIdInterna(elemento.getIDINTERNOPE());
			persona.setIdExterna(elemento.getIDEXTPE().trim());
			persona.setCodIdExterna(elemento.getCODIDEXTPERS().trim());
			persona.setNombre(elemento.getNOMB50().trim());
			if (elemento.getCODPERSRLPERS() != null
					&& !elemento.getCODPERSRLPERS().trim().isEmpty()) {
				CatalogoBean catalogoBean = null;
				catalogoBean = catalogoUtils.getCatalogoBean(
						CatalogoEnum.TP_PERS_RL_PERS, elemento
								.getCODPERSRLPERS().trim());
				if(!"000".equals(catalogoBean.getContenido().substring(0, 2)) 
						&& idInternoPersona.intValue() == relacion.getIDINTERNOPE2()){
					catalogoBean = catalogoUtils.getCatalogoBean(
							CatalogoEnum.TP_PERS_RL_PERS, catalogoBean.getContenido().substring(0, 3));
				}
				persona.setRelacionSeleccionada(catalogoBean);
			}
			persona.setEstado(EstadoListadosEnum.CONSULTADO);
		}
		return persona;
	}

}