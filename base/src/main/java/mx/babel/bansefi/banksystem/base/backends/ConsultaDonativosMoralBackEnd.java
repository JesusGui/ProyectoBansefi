package mx.babel.bansefi.banksystem.base.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.DonativosPersonaMoralBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.webservices.consultadonativosmoral.ConsultaDonativosMoralServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultadonativosmoral.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultadonativosmoral.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultadonativosmoral.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.webservices.consultadonativosmoral.ResponseBansefi.OTRCNSINFFINDNTTRNO;
import mx.babel.bansefi.banksystem.base.wrappers.ClienteWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaDonativosMoralBackEnd extends BackEndBean{

	private static final long serialVersionUID = -1103537414176253250L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	@Autowired
	private ClienteWrapper clienteWrapper; 
	
	/**
	 * Función encargada de consultar el uso de la cuenta de una persona
	 * por medio de servicios web.
	 * 
	 * @param idInterno id de la persona a consultar
	 * @return bean de uso de cuenta
	 * @throws NoControlableException Excepción controlada de errores en front end
	 * @throws ControlableException Excepción no controlada de errores en host
	 */
	public DonativosPersonaMoralBean ejecutarTRN(Integer idInterno){
		Ejecutar.ITRCNSINFFINDNTTRNI contexto = initPeticion(idInterno);
		EjecutarResult respuesta = ejecutarWS(contexto);
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() == RETURN_COD_SIN_DATOS){
				return new DonativosPersonaMoralBean();
			}else{
				throw ce;
			}
		}
		if(verificaResponseBansefi(respuesta)){
			return getDonativos(respuesta.getResponseBansefi());
		}
		return null;
	}
	
	/**
	 * Función que construye la lista de cuentas a partir de la respuesta al servidor.
	 * 
	 * @param response
	 * @return
	 */
	private DonativosPersonaMoralBean getDonativos(ResponseBansefi response){
		DonativosPersonaMoralBean resultado = new DonativosPersonaMoralBean();		
		if(verificaRespuestaCliente(response)){
			OTRCNSINFFINDNTTRNO.TRCNSINFFINDNTEVTZ.PEORGDNTVSV donativos = response.getOTRCNSINFFINDNTTRNO().getTRCNSINFFINDNTEVTZ().getPEORGDNTVSV();
			OTRCNSINFFINDNTTRNO.TRCNSINFFINDNTEVTZ.PEORGFINANCV datosFinancieros = response.getOTRCNSINFFINDNTTRNO().getTRCNSINFFINDNTEVTZ().getPEORGFINANCV();
			
			resultado = this.clienteWrapper.wrappDonativos(donativos);
			
			// Frecuencia
			resultado.setActivoFijo(datosFinancieros.getIMPACTFIJO());
			resultado.setActivoCirculante(datosFinancieros.getIMPACTCRCLT());
			resultado.setPasivoCortoPlazo(datosFinancieros.getIMPPASCTPLZ());
			resultado.setPasivoLargoPlazo(datosFinancieros.getIMPPASLGPLZ());
			resultado.setCapitalContable(datosFinancieros.getIMPCAPCTBLE());
			resultado.setMoneda(datosFinancieros.getCODNUMRCOMONEDA().trim());			
			
			//Resultados
			resultado.setIngresoAnual(datosFinancieros.getIMPINGANUAL());
			resultado.setCostoVentas(datosFinancieros.getIMPCOSTVTA());
			resultado.setGastos(datosFinancieros.getIMPGASTO());
			resultado.setUtilidad(datosFinancieros.getIMPUTILIDAD());
			resultado.setNumEmpleados(datosFinancieros.getNUMEMPLEADO());
			resultado.setNumSucursales(datosFinancieros.getNUMSUCURSAL());
			
			//Comercio Exterior
			resultado.setIngresoExp(datosFinancieros.getIMPINGEXP());
			resultado.setPagoExp(datosFinancieros.getIMPPAGOEXP());
			resultado.setUsDolares(datosFinancieros.getIMPDLLSMNDA());
			resultado.setIndicarMoneda(datosFinancieros.getCODNUMRCOMONEDA1().trim());
			
		}	
		return resultado;
	}
	
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param idInterno Id del cliente de tipo persona física
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITRCNSINFFINDNTTRNI initPeticion(Integer idInterno){
		Ejecutar.ITRCNSINFFINDNTTRNI contexto = new Ejecutar.ITRCNSINFFINDNTTRNI();
		
		Ejecutar.ITRCNSINFFINDNTTRNI.STDTRNIPARMV contextoEntrada = new Ejecutar.ITRCNSINFFINDNTTRNI.STDTRNIPARMV();
		Ejecutar.ITRCNSINFFINDNTTRNI.TRCNSINFFINDNTEVTY.PEPERSP cuerpoContexto = new Ejecutar.ITRCNSINFFINDNTTRNI.TRCNSINFFINDNTEVTY.PEPERSP();
		
		super.initialize(contexto);
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_PE_CONS_INDV_USO_CTA_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());		
		
		cuerpoContexto.setIDINTERNOPE(idInterno);
		cuerpoContexto.setCODNRBEEN(super.getEntidad());
		
		contexto.setSTDTRNIPARMV(contextoEntrada);
		contexto.getTRCNSINFFINDNTEVTY().setPEPERSP(cuerpoContexto);
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRCNSINFFINDNTTRNI contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaDonativosMoralServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta de "
					+ "donativos.", e);
		}
		return respuesta;
	}
	
	/**
	 * Función que valida que la respuesta del servidor no este vacía. 
	 *  
	 * @param respuesta Objeto respuesta del servicio web
	 * @return <code>true</code> si la respuesta no esta vacía.
	 */
	private Boolean verificaResponseBansefi(EjecutarResult respuesta){
		Boolean noNulo = false;
		if(respuesta != null && respuesta.getResponseBansefi() != null){
			noNulo = true;
		}
		return noNulo;
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
		if(response!= null && response.getOTRCNSINFFINDNTTRNO() != null &&
				response.getOTRCNSINFFINDNTTRNO().getTRCNSINFFINDNTEVTZ() != null &&
						response.getOTRCNSINFFINDNTTRNO().getTRCNSINFFINDNTEVTZ().getPEORGFINANCV() != null){
			noNulo = true;
		}
		return noNulo;
	}
}
