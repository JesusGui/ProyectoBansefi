package mx.babel.bansefi.banksystem.base.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.RelacionesClienteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.PersonaMoralAccionistaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.PersonaMoralFuncionarioBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;
import mx.babel.bansefi.banksystem.base.webservices.consultaaccionistasfuncionarios.ConsultaAccionistasMoralServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultaaccionistasfuncionarios.Ejecutar.IPECONSORGACCFUNTRN;
import mx.babel.bansefi.banksystem.base.webservices.consultaaccionistasfuncionarios.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultaaccionistasfuncionarios.ResponseBansefi.OPECONSORGACCFUNTRN.PECONSORGACCFUNEVTZ.PEPERSRLACCV;
import mx.babel.bansefi.banksystem.base.webservices.consultaaccionistasfuncionarios.ResponseBansefi.OPECONSORGACCFUNTRN.PECONSORGACCFUNEVTZ.PEPERSRLFUNV;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaAccionistasFuncionariosBackEnd extends BackEndBean {

	private static final long serialVersionUID = -2768398521266654053L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Método que ejecuta la TRN de consulta de accionistas relacionados.
	 * 
	 * @param idInternoPersona
	 * @return lista de accionistas y funcionarios relacionados
	 */
	public RelacionesClienteBean ejecutarTRN(Integer idInternoPersona)
	{
		IPECONSORGACCFUNTRN ipeconsorgaccfuntrn = initPeticion(idInternoPersona);

		EjecutarResult resultado = (EjecutarResult) this.servicioWebUtils.ejecutarWS(ConsultaAccionistasMoralServicio.class,ipeconsorgaccfuntrn);

		try{
			super.verificaRespuesta(resultado);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return new RelacionesClienteBean();
			}
		}

		return obtenerListaAccionistasFuncinariosRelacionados(resultado);
	}

	/**
	 * Método privado que prepara los datos que serán enviados a la TRN.
	 * 
	 * @param idInternoPersona
	 * @return parametros de entrada encapsulados en un objeto
	 *         IPECONSORGACCFUNTRN
	 */
	private IPECONSORGACCFUNTRN initPeticion(Integer idInternoPersona){
		IPECONSORGACCFUNTRN ipeconsorgaccfuntrn = new IPECONSORGACCFUNTRN();

		super.initialize(ipeconsorgaccfuntrn);

		ipeconsorgaccfuntrn.setELEVATORPOSITION(0);
		ipeconsorgaccfuntrn.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);

		ipeconsorgaccfuntrn.getPECONSORGACCFUNEVTY().getPEPERSRLACCFUNP().setCODNRBEEN(super.getEntidad());
		ipeconsorgaccfuntrn.getPECONSORGACCFUNEVTY().getPEPERSRLACCFUNP().setIDINTERNOPE(idInternoPersona);

		ipeconsorgaccfuntrn.getSTDTRNIPARMV().setIDINTERNOTERMTN(super.getTerminal());
		ipeconsorgaccfuntrn.getSTDTRNIPARMV().setCODTX(CodTxConstants.COD_TX_PE_CONS_ORG_ACC_FUN_TRN);

		return ipeconsorgaccfuntrn;
	}

	/**
	 * Método privado que devuelve una lista de accionistas relacionados a
	 * partir del resultado de la consulta.
	 * 
	 * @param resultado
	 * @return lista de accionistas y funcionarios relacionados
	 */
	private RelacionesClienteBean obtenerListaAccionistasFuncinariosRelacionados(EjecutarResult resultado){
		RelacionesClienteBean relaciones = new RelacionesClienteBean();
		
		if (resultado.getResponseBansefi().getOPECONSORGACCFUNTRN().getPECONSORGACCFUNEVTZ().getPEPERSRLACCV().size() > 0) {
			for (PEPERSRLACCV elemento : resultado.getResponseBansefi()
					.getOPECONSORGACCFUNTRN().getPECONSORGACCFUNEVTZ()
					.getPEPERSRLACCV()) {
				PersonaMoralAccionistaBean accionista = crearAccionista(elemento);
				if (accionista != null && accionista.getIdInterno() != 0) {
					relaciones.getAccionistas().add(accionista);
				}
			}
		}
		
		if (resultado.getResponseBansefi().getOPECONSORGACCFUNTRN()
				.getPECONSORGACCFUNEVTZ().getPEPERSRLACCV().size() > 0) {
			for (PEPERSRLFUNV elemento : resultado.getResponseBansefi()
					.getOPECONSORGACCFUNTRN().getPECONSORGACCFUNEVTZ()
					.getPEPERSRLFUNV()) {
				PersonaMoralFuncionarioBean funcionario = crearFuncionario(elemento);
				if (funcionario != null && funcionario.getIdInterno() != 0) {
					relaciones.getFuncionarios().add(funcionario);
				}
			}
		}
		return relaciones;
	}

	/**
	 * Método privado que devuelve un objeto tipo PersonaMoralAccionistaBean.
	 * 
	 * @param elemento
	 * @return accionista
	 */
	private PersonaMoralAccionistaBean crearAccionista(PEPERSRLACCV elemento){
		PersonaMoralAccionistaBean accionista = null;
		if (elemento != null) {
			accionista = new PersonaMoralAccionistaBean();
			accionista.setIdInterno(elemento.getNUMSEC());
			accionista.setNombre(elemento.getNOMBINNOMBPILA().trim());
			accionista.setApellidoPaterno(elemento.getNOMBINAPE1IN().trim());
			accionista.setApellidoMaterno(elemento.getNOMBINAPE2IN().trim());
			accionista.setRfc(elemento.getIDRFC().trim());
			accionista.setPorcentaje(elemento.getPCTACCNRIO());
			accionista.setEstado(EstadoListadosEnum.CONSULTADO);
		}
		return accionista;
	}
	
	/**
	 * Método privado que devuelve un objeto tipo PersonaMoralFuncionarioBean.
	 * 
	 * @param elemento
	 * @return funcionario
	 */
	private PersonaMoralFuncionarioBean crearFuncionario(PEPERSRLFUNV elemento){
		PersonaMoralFuncionarioBean funcionario = null;
		if (elemento != null) {
			funcionario = new PersonaMoralFuncionarioBean();
			funcionario.setIdInterno(elemento.getNUMSEC());
			funcionario.setNombre(elemento.getNOMBINNOMBPILA().trim());
			funcionario.setApellidoPaterno(elemento.getNOMBINAPE1IN().trim());
			funcionario.setApellidoMaterno(elemento.getNOMBINAPE2IN().trim());
			funcionario.setRfc(elemento.getIDRFC().trim());
			funcionario.setPuesto(elemento.getPUESTO().trim());
			funcionario.setEstado(EstadoListadosEnum.CONSULTADO);
		}
		return funcionario;
	}
	
}