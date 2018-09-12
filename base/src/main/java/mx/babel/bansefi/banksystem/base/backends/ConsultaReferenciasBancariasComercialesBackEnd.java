package mx.babel.bansefi.banksystem.base.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.RelacionesClienteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.PersonaMoralRBancariaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.PersonaMoralRComercialBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;
import mx.babel.bansefi.banksystem.base.webservices.consultareferenciasocioeconomicas.ConsultaReferenciaSocioeconomicasServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultareferenciasocioeconomicas.Ejecutar.IPECONSORGSOCIOECTRN;
import mx.babel.bansefi.banksystem.base.webservices.consultareferenciasocioeconomicas.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultareferenciasocioeconomicas.ResponseBansefi.OPECONSORGSOCIOECTRN.PECONSORGSOCIOECEVTZ.PEREFBANLS;
import mx.babel.bansefi.banksystem.base.webservices.consultareferenciasocioeconomicas.ResponseBansefi.OPECONSORGSOCIOECTRN.PECONSORGSOCIOECEVTZ.PEREFCOMLS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaReferenciasBancariasComercialesBackEnd extends BackEndBean {

	private static final long serialVersionUID = 440908150020996613L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Método que ejecuta la TRN de consulta de referencias bancarias y comerciales.
	 * 
	 * @param idInternoPersona
	 * @return lista de referencias bancarias y comerciales
	 */
	public RelacionesClienteBean ejecutarTRN(Integer idInternoPersona){
		
		IPECONSORGSOCIOECTRN ipeconsorgsocioectrn = initPeticion(idInternoPersona);

		EjecutarResult resultado = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaReferenciaSocioeconomicasServicio.class,ipeconsorgsocioectrn);
		
		try{
			super.verificaRespuesta(resultado);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return new RelacionesClienteBean();
			}
		}		

		return obtenerListaReferenciasBancariasComercialesRelacionadas(resultado);
	}

	/**
	 * Método privado que prepara los datos que serán enviados a la TRN.
	 * 
	 * @param idInternoPersona
	 * @return parametros de entrada encapsulados en un objeto
	 *         IPECONSORGSOCIOECTRN
	 */
	private IPECONSORGSOCIOECTRN initPeticion(Integer idInternoPersona){
		IPECONSORGSOCIOECTRN ipeconsorgsocioectrn = new IPECONSORGSOCIOECTRN();

		super.initialize(ipeconsorgsocioectrn);

		ipeconsorgsocioectrn.setELEVATORPOSITION(0);
		ipeconsorgsocioectrn.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);

		ipeconsorgsocioectrn.getPECONSORGSOCIOECEVTY().getPEPERSRLREFBANP().setCODNRBEEN(super.getEntidad());
		ipeconsorgsocioectrn.getPECONSORGSOCIOECEVTY().getPEPERSRLREFBANP().setIDINTERNOPE(idInternoPersona);
		
		ipeconsorgsocioectrn.getPECONSORGSOCIOECEVTY().getPEPERSRLREFCOMP().setCODNRBEEN(super.getEntidad());
		ipeconsorgsocioectrn.getPECONSORGSOCIOECEVTY().getPEPERSRLREFCOMP().setIDINTERNOPE(idInternoPersona);

		ipeconsorgsocioectrn.getSTDTRNIPARMV().setIDINTERNOTERMTN(super.getTerminal());
		ipeconsorgsocioectrn.getSTDTRNIPARMV().setCODTX(CodTxConstants.COD_TX_PE_CONS_ORG_ACC_FUN_TRN);

		return ipeconsorgsocioectrn;
	}

	/**
	 * Método privado que devuelve una lista de referencias bancarias a partir
	 * del resultado de la consulta.
	 * 
	 * @param resultado
	 * @return lista de referencias bancarias y comerciales
	 */
	private RelacionesClienteBean obtenerListaReferenciasBancariasComercialesRelacionadas(EjecutarResult resultado){
		RelacionesClienteBean relacionesCliente = new RelacionesClienteBean();
		if (resultado.getResponseBansefi().getOPECONSORGSOCIOECTRN()
				.getPECONSORGSOCIOECEVTZ().getPEREFBANLS().size() > 0) {
			for (PEREFBANLS elemento : resultado.getResponseBansefi()
					.getOPECONSORGSOCIOECTRN().getPECONSORGSOCIOECEVTZ()
					.getPEREFBANLS()) {
				PersonaMoralRBancariaBean refBancaria = crearReferenciaBancaria(elemento);
				if (refBancaria != null && refBancaria.getIdInterno() != 0) {
					relacionesCliente.getReferenciasBancarias().add(refBancaria);
				}
			}
		}
		
		if (resultado.getResponseBansefi().getOPECONSORGSOCIOECTRN()
				.getPECONSORGSOCIOECEVTZ().getPEREFCOMLS().size() > 0) {
			for (PEREFCOMLS elemento : resultado.getResponseBansefi()
					.getOPECONSORGSOCIOECTRN().getPECONSORGSOCIOECEVTZ()
					.getPEREFCOMLS()) {
				PersonaMoralRComercialBean refComercial = crearReferenciaComercial(elemento);
				if (refComercial != null && refComercial.getIdInterno() != 0) {
					relacionesCliente.getReferenciasComerciales().add(refComercial);
				}
			}
		}
		
		return relacionesCliente;
	}

	/**
	 * Método privado que devuelve un objeto tipo PersonaMoralRBancariaBean.
	 * 
	 * @param elemento
	 * @return referencia bancaria
	 */
	private PersonaMoralRBancariaBean crearReferenciaBancaria(PEREFBANLS elemento){
		PersonaMoralRBancariaBean refBancaria = null;
		if (elemento != null) {
			refBancaria = new PersonaMoralRBancariaBean();
			refBancaria.setIdInterno(elemento.getNUMSEC());
			refBancaria.setBanco(elemento.getBANCO().trim());
			refBancaria.setNumCuenta(elemento.getNUMCTACHAR().trim());
			refBancaria.setTipoCuenta(elemento.getTIPOCUENTA().trim());
			refBancaria.setEstado(EstadoListadosEnum.CONSULTADO);
		}
		return refBancaria;
	}
	
	/**
	 * Método privado que devuelve un objeto tipo PersonaMoralRComercialBean.
	 * 
	 * @param elemento
	 * @return referencia comercial
	 */
	private PersonaMoralRComercialBean crearReferenciaComercial(PEREFCOMLS elemento){
		PersonaMoralRComercialBean refComercial = null;
		if (elemento != null) {
			refComercial = new PersonaMoralRComercialBean();
			refComercial.setIdInterno(elemento.getNUMSEC());
			refComercial.setNombre(elemento.getNOMB50().trim());
			refComercial.setGiro(elemento.getGIRO().trim());
			refComercial.setDomicilio(elemento.getDOMIC50().trim());
			refComercial.setTelefono(elemento.getTELEFONO().trim());
			refComercial.setRelacion(elemento.getPERSRLTIT().trim());
			refComercial.setEstado(EstadoListadosEnum.CONSULTADO);
		}
		return refComercial;
	}
	
}