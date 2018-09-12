package mx.babel.bansefi.banksystem.base.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.TipoCliente;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.webservices.consultarepresentantelegal.ConsultaRepresentanteLegalServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultarepresentantelegal.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultarepresentantelegal.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultarepresentantelegal.ResponseBansefi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * BackEnd para consultas de representantes legales de una cliente.
 * 
 * @author mario.montesdeoca
 *
 */
@Component
public class ConsultaRepresentanteLegalBackEnd extends BackEndBean{


	private static final long serialVersionUID = 5209099710439882282L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Función para obtener el id interna de la persona física que funge como representante 
	 * legal del cliente consultado.
	 * 
	 * @param idInterno Id interno del cliente que necesita un representante legal.
	 * @return id del representante legal
	 * @throws NoControlableException Excepción controlada de errores en front end
	 * @throws ControlableException Excepción no controlada de errores en host
	 */
	public Integer ejecutarTRN(Integer idInterno, TipoCliente tipo){
		return ejecutarTRN(idInterno, tipo, false);			
	}
	
	/**
	 * Función para obtener el id interna de la persona física que funge como representante 
	 * legal del cliente consultado.
	 * 
	 * @param idInterno Id interno del cliente que necesita un representante legal.
	 * @return id del representante legal
	 * @throws NoControlableException Excepción controlada de errores en front end
	 * @throws ControlableException Excepción no controlada de errores en host
	 */
	public Integer ejecutarTRN(Integer idInterno, TipoCliente tipo, Boolean menor){
		Ejecutar.ITRPERLPELSCNSTRNI contexto = initPeticion(idInterno);
		
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return null;
			}
		}
		
		return getIdResponsable(respuesta.getResponseBansefi(), idInterno, tipo, menor);				
	}
	
	/**
	 * 
	 * Función que obtiene el id interno del representante legal a partir de la 
	 * respuesta del web service.
	 * 
	 * @param response Respuesta del web service
	 * @return el id interno del representante legal
	 */
	private Integer getIdResponsable(ResponseBansefi response, Integer idInterno, TipoCliente tipo, Boolean menor)
			throws NoControlableException, ControlableException{
		Integer idResponsable = null;		
		if(verificaListaPersonas(response)){
			ResponseBansefi.OTRPERLPELSCNSTRNO.TRPERLPELSCNSEVTZ listaClientes = 
					response.getOTRPERLPELSCNSTRNO().getTRPERLPELSCNSEVTZ();
			for (ResponseBansefi.OTRPERLPELSCNSTRNO.TRPERLPELSCNSEVTZ.PEPERSRLPERSP responsable : 
				listaClientes.getPEPERSRLPERSP()){
				if(tipo.equals(TipoCliente.PERSONA_FISICA)){
					if(menor){
						if(ConstantesFuncionales.REPRESENTANTE_MENOR_CONTRARIA.equals(responsable.getCODPERSRLPERS())){
							idResponsable = responsable.getIDINTERNOPE1();
							if(idResponsable.equals(idInterno)){
								idResponsable = responsable.getIDINTERNOPE2();
							}
						}
					}else{
						if(ConstantesFuncionales.REPRESENTA_A.contains(responsable.getCODPERSRLPERS())){
							idResponsable = responsable.getIDINTERNOPE1();
							if(idResponsable.equals(idInterno)){
								idResponsable = responsable.getIDINTERNOPE2();
							}
						}
						if(ConstantesFuncionales.REPRESENTADO_POR.contains(responsable.getCODPERSRLPERS())){
							idResponsable = responsable.getIDINTERNOPE2();
							if(idResponsable.equals(idInterno)){
								idResponsable = responsable.getIDINTERNOPE1();
							}
						}
					}
				}else{
					if(ConstantesFuncionales.APODERADO_DE.equals(responsable.getCODPERSRLPERS())){
						if(responsable.getIDINTERNOPE1() != idInterno){
							idResponsable = responsable.getIDINTERNOPE1();
						}else{
							idResponsable = responsable.getIDINTERNOPE2();
						}
					}
				}
			}
		}
		return idResponsable;
	}
	
	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 * 
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRPERLPELSCNSTRNI initPeticion(Integer idInterno){
		Ejecutar.ITRPERLPELSCNSTRNI contexto = new Ejecutar.ITRPERLPELSCNSTRNI();
		Ejecutar.ITRPERLPELSCNSTRNI.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRPERLPELSCNSTRNI.STDTRNIPARMV();
		Ejecutar.ITRPERLPELSCNSTRNI.TRPERLPELSCNSEVTY cuerpoContexto =
				new Ejecutar.ITRPERLPELSCNSTRNI.TRPERLPELSCNSEVTY();
		Ejecutar.ITRPERLPELSCNSTRNI.TRPERLPELSCNSEVTY.PEPERSP persona = 
				new Ejecutar.ITRPERLPELSCNSTRNI.TRPERLPELSCNSEVTY.PEPERSP();
		contexto.setSTDTRNIPARMV(contextoEntrada);
		contexto.setTRPERLPELSCNSEVTY(cuerpoContexto);
		super.initialize(contexto);
		
		persona.setCODNRBEEN(super.getEntidad());
		if(idInterno != null){
			persona.setIDINTERNOPE(idInterno);
		}
		cuerpoContexto.setPEPERSP(persona);		
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_PE_RL_PE_LS_CNS_TRN);
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		
		contexto.setELEVATORPOSITION(1);
		contexto.setSCROLLABLEOCCURS(50);
		
		return contexto;
	}
	
	/**
	 * Función para ejecutar el servicio web de consulta de representante legal 
	 * y obtener su respuesta.
	 * 
	 * @param contexto Objeto para envío a servicio web.
	 * @return Respuesta del servicio web
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRPERLPELSCNSTRNI contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaRepresentanteLegalServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta de "
					+ "representante legal.", e);
		}
		return respuesta;
	}
	
	/**
	 * Función que verifica que la lista de personas de la respuesta del servicio web no sea vacía.
	 * 
	 * @param response Respuesta Bansefi con la lista de personas
	 * @return <code>true</code> sí la lista contiene por lo menos una persona.
	 */
	private Boolean verificaListaPersonas(ResponseBansefi response) {
		Boolean noNulo =false;
		if(response != null && response.getOTRPERLPELSCNSTRNO() != null 
				&& response.getOTRPERLPELSCNSTRNO().getTRPERLPELSCNSEVTZ() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
}
