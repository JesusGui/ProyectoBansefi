package mx.babel.bansefi.banksystem.base.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.GrupoFilialPersonaMoralBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.webservices.consultagrupofilialmoral.ConsultaGrupoFilialMoralServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultagrupofilialmoral.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultagrupofilialmoral.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultagrupofilialmoral.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.webservices.consultagrupofilialmoral.ResponseBansefi.OPECONSORGGRPFTRNO.PECONSORGGRPFEVTZ;
import mx.babel.bansefi.banksystem.base.webservices.consultagrupofilialmoral.ResponseBansefi.OPECONSORGGRPFTRNO.PECONSORGGRPFEVTZ.PEGRPEMPREADICV;
import mx.babel.bansefi.banksystem.base.webservices.consultagrupofilialmoral.ResponseBansefi.OPECONSORGGRPFTRNO.PECONSORGGRPFEVTZ.PEINFFINANPZAV;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaGrupoFilialMoralBackEnd extends BackEndBean{

	private static final long serialVersionUID = -1103537414176253250L;

	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	/**
	 * Función encargada de consultar el uso de la cuenta de una persona
	 * por medio de servicios web.
	 * 
	 * @param idInterno id de la persona a consultar
	 * @return bean de uso de cuenta
	 * @throws NoControlableException Excepción controlada de errores en front end
	 * @throws ControlableException Excepción no controlada de errores en host
	 */
	public GrupoFilialPersonaMoralBean ejecutarTRN(Integer idInterno){
		Ejecutar.IPECONSORGGRPFTRNI contexto = initPeticion(idInterno);
		EjecutarResult respuesta = ejecutarWS(contexto);
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() == RETURN_COD_SIN_DATOS){
				return new GrupoFilialPersonaMoralBean();
			}else{
				throw ce;
			}
		}
		return getGrupoFilial(respuesta.getResponseBansefi());
	}
	
	/**
	 * Función que construye la lista de cuentas a partir de la respuesta al servidor.
	 * 
	 * @param response
	 * @return
	 */
	private GrupoFilialPersonaMoralBean getGrupoFilial(ResponseBansefi response){
		GrupoFilialPersonaMoralBean resultado = new GrupoFilialPersonaMoralBean();		
		if(verificaRespuestaCliente(response)){
			PECONSORGGRPFEVTZ usosCuenta = response.getOPECONSORGGRPFTRNO().getPECONSORGGRPFEVTZ();
			PEGRPEMPREADICV pertenencia = usosCuenta.getPEGRPEMPREADICV();
			PEINFFINANPZAV plazas = usosCuenta.getPEINFFINANPZAV();
			
			resultado.setPerteneceGrupoFiliar("S".equals(pertenencia.getINDGPOFILIA().trim()));
			if (resultado.isPerteneceGrupoFiliar()) {
				resultado.setPais(usosCuenta.getCODPAISNAL().trim());
				resultado.setPaisResidencia(usosCuenta.getCODPAISRES().trim());
				resultado.setCoberturaAmbito(usosCuenta.getCODAMBTOORG().trim());
				resultado.setNombre(pertenencia.getNOMBGR().trim());
				resultado.setRfc(pertenencia.getRFC().trim());
				resultado.setProducto(pertenencia.getDESCRPROCTO().trim());
				resultado.setParticipacion(pertenencia.getPORCGPOFILIAL());
				resultado.setEmpleados(pertenencia.getNUMEMPLEADO());
				resultado.setOficinas(pertenencia.getNUMOFCNA());
				resultado.setVentasAnuales(pertenencia.getIMPVTASANUAL());
				resultado.setTelefono(pertenencia.getNUMTLFNODOMIC().trim());
				resultado.setEmail(pertenencia.getNOMBCORREO().trim());
			}
			
			resultado.setOpcion1(plazas.getPLAZAOPER01().trim());
			resultado.setOpcion2(plazas.getPLAZAOPER02().trim());
			resultado.setOpcion3(plazas.getPLAZAOPER03().trim());
			resultado.setOpcion4(plazas.getPLAZAOPER04().trim());
			resultado.setOpcion5(plazas.getPLAZAOPER05().trim());
			resultado.setOpcion6(plazas.getPLAZAOPER06().trim());
		}
		return resultado;
	}
	
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param idInterno Id del cliente de tipo persona física
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.IPECONSORGGRPFTRNI initPeticion(Integer idInterno){
		Ejecutar.IPECONSORGGRPFTRNI contexto = new Ejecutar.IPECONSORGGRPFTRNI();
		
		Ejecutar.IPECONSORGGRPFTRNI.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.IPECONSORGGRPFTRNI.STDTRNIPARMV();
		Ejecutar.IPECONSORGGRPFTRNI.PECONSORGGRPFEVTY p = 
				new Ejecutar.IPECONSORGGRPFTRNI.PECONSORGGRPFEVTY();
		Ejecutar.IPECONSORGGRPFTRNI.PECONSORGGRPFEVTY.PEPERSP cuerpoContexto =
				new Ejecutar.IPECONSORGGRPFTRNI.PECONSORGGRPFEVTY.PEPERSP();
		
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_PE_CONS_INDV_USO_CTA_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());		
		
		cuerpoContexto.setIDINTERNOPE(idInterno);
		cuerpoContexto.setCODNRBEEN(super.getEntidad());
		contexto.setSTDTRNIPARMV(contextoEntrada);
		p.setPEPERSP(cuerpoContexto);
		contexto.setPECONSORGGRPFEVTY(p);
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.IPECONSORGGRPFTRNI contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaGrupoFilialMoralServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta de "
					+ "grupo o filial.", e);
		}
		return respuesta;
	}
	
	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con los datos
	 * de la persona física.
	 * 
	 * @param response Respuesta Bansefi con datos de la persona moral
	 * @return <code>true</code> si la respuesta Bansefi contiene elemento no nulos
	 */
	private Boolean verificaRespuestaCliente(ResponseBansefi response){
		Boolean noNulo = false;
		if(response!= null && response.getOPECONSORGGRPFTRNO() != null &&
				response.getOPECONSORGGRPFTRNO().getPECONSORGGRPFEVTZ() != null &&
						response.getOPECONSORGGRPFTRNO().getPECONSORGGRPFEVTZ().getPEGRPEMPREADICV() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
}
