package mx.babel.bansefi.banksystem.personas.backend;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.personas.beans.CnaeBean;
import mx.babel.bansefi.banksystem.personas.webservices.consultaobjetosocialcnae.ConsultaObjetoSocialCnaeServicio;
import mx.babel.bansefi.banksystem.personas.webservices.consultaobjetosocialcnae.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.consultaobjetosocialcnae.Ejecutar.ITRPECONSCNAEOBJSOCT;
import mx.babel.bansefi.banksystem.personas.webservices.consultaobjetosocialcnae.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.consultaobjetosocialcnae.ResponseBansefi.OTRPECONSCNAEOBJSOCT.TRPECONSCNAEOBJSOCEV1.TRPECONSCNAEOBJSOCEV;
import mx.babel.bansefi.banksystem.personas.wrappers.ActividadEmpresarialWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * BackEnd para Consulta de Objetos Social y CNAE's.
 * 
 * @author alejandro.pineda
 * 
 */
@Component
public class ConsultaObjetoSocialCnaesBackEnd extends BackEndBean {

	private static final long serialVersionUID = -5246136323152603848L;

	@Autowired
	private ActividadEmpresarialWrapper actividadEmpresarialWrapper;
	
	@Autowired	
	ServicioWebUtils servicioWebUtils;

	/**
	 * Método para ejecutar la TRN de consulta de Objeto Social y CNAE's.
	 * 
	 * @param idInterno
	 *            IdInterno a consultar.
	 * @return Lista de Cnaes.
	 */
	public List<CnaeBean> ejecutarTRN(int idInterno){
		Ejecutar.ITRPECONSCNAEOBJSOCT contexto = new Ejecutar.ITRPECONSCNAEOBJSOCT();
		contexto.setELEVATORPOSITION(0);
		contexto.setSCROLLABLEOCCURS(50);

		Ejecutar.ITRPECONSCNAEOBJSOCT.TRPECONSCNAEOBJSOCEV contextoEntrada = new Ejecutar.ITRPECONSCNAEOBJSOCT.TRPECONSCNAEOBJSOCEV();

		Ejecutar.ITRPECONSCNAEOBJSOCT.TRPECONSCNAEOBJSOCEV.PEOTROCNAEP contextoEntradaCampos = new Ejecutar.ITRPECONSCNAEOBJSOCT.TRPECONSCNAEOBJSOCEV.PEOTROCNAEP();
		contextoEntradaCampos.setCODNRBEEN(super.getEntidad());
		contextoEntradaCampos.setIDINTERNOPE(idInterno);
		contextoEntradaCampos.setCODCNAEPERS("");
		contextoEntradaCampos.setFECHAINICCRT(0);
		contextoEntradaCampos.setHORAOPRCN(0);
		contextoEntrada.setPEOTROCNAEP(contextoEntradaCampos);

		Ejecutar.ITRPECONSCNAEOBJSOCT.TRPECONSCNAEOBJSOCEV.PEOBJSOCIALP contextoEntradaCamposVacios = new Ejecutar.ITRPECONSCNAEOBJSOCT.TRPECONSCNAEOBJSOCEV.PEOBJSOCIALP();
		contextoEntradaCamposVacios.setCODNRBEEN(super.getEntidad());
		contextoEntradaCamposVacios.setIDINTERNOPE(idInterno);
		contextoEntrada.setPEOBJSOCIALP(contextoEntradaCamposVacios);

		Ejecutar.ITRPECONSCNAEOBJSOCT.STDTRNIPARMV contextoComun = new Ejecutar.ITRPECONSCNAEOBJSOCT.STDTRNIPARMV();
		contextoComun.setIDINTERNOTERMTN(super.getTerminal());
		contextoComun.setIDEMPLAUT("");
		contextoComun.setNUMSEC(0);
		contextoComun.setCODTX("POT14CON");
		contextoComun.setCODTXDI("");

		contexto.setSTDTRNIPARMV(contextoComun);
		contexto.setTRPECONSCNAEOBJSOCEV(contextoEntrada);

		return this.obtenerRespuestaServicio(contexto);

	}

	/**
	 * Método para obtener resultados del servicio de consulta de objeto social
	 * y cnaes.
	 * 
	 * @param contextoEntrada
	 *            Contexto entrada con los datos a consultar.
	 * @return Lista de resultados del servicio
	 */
	public List<CnaeBean> obtenerRespuestaServicio(ITRPECONSCNAEOBJSOCT contextoEntrada){
		List<CnaeBean> listaCnaes = new ArrayList<>();
		
		// Se ejecuta el WebService correspondiente
		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
				ConsultaObjetoSocialCnaeServicio.class, contextoEntrada);

		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return listaCnaes;
			}
		}
		
		String objSocial = respuesta.getResponseBansefi().getOTRPECONSCNAEOBJSOCT()
				.getTRPECONSCNAEOBJSOCEV()
				.getTRPECONSCNAEOBJSOC2().getOBJSOCIALPE().trim();
		
		for (TRPECONSCNAEOBJSOCEV resultado : respuesta.getResponseBansefi()
				.getOTRPECONSCNAEOBJSOCT()
				.getTRPECONSCNAEOBJSOCEV()
				.getTRPECONSCNAEOBJSOCEV()) {
			CnaeBean cnae = actividadEmpresarialWrapper
					.wrapperConsultaObjetoSocialCnae(resultado);
			cnae.setObjSocial(objSocial);
			if(cnae.getIdInterno() != 0 && !("").equals(cnae.getCodCnae().trim())){
				listaCnaes.add(cnae);
			}
			
		}
			
		return listaCnaes;
	}
}
