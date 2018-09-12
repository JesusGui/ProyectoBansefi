package mx.babel.bansefi.banksystem.base.backends;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.ReferenciaPersonalBean;
import mx.babel.bansefi.banksystem.base.beans.domain.RiesgoClientePersonaFisicaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;
import mx.babel.bansefi.banksystem.base.webservices.consultareferenciaspersonalesriesgo.ConsultaReferenciasPersonalesRiesgoServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultareferenciaspersonalesriesgo.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultareferenciaspersonalesriesgo.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultareferenciaspersonalesriesgo.ResponseBansefi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para consulta de referencias personales para personas de riesgo
 * 
 * @author javier.martinnino
 *
 */
@Component
public class ConsultaReferenciasPersonalesRiesgoBackEnd extends BackEndBean implements Serializable {
	
	private static final long serialVersionUID = -5397617229403876960L;
	
	@Autowired
	ConsultaInformacionFinancieraRiesgoBackEnd consultaInformacionFinancieraRiesgoBackEnd;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	/**
	 * Función encargada de las referencias personales de una persona de riesgo por medio de servicios web.
	 * 
	 * @param idInterna del cliente a consultar
	 * @return RiesgoClientePersonaFisicaBean datos de riesgo de cliente
	 */
	public RiesgoClientePersonaFisicaBean ejecutarTRN(Integer idInterna){
		Ejecutar.IPECONSINDVSOCIOECTRN contexto = initPeticion(idInterna);
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
		if(verificaResponseBansefi(respuesta)){
			return consultaReferencias(respuesta.getResponseBansefi(),idInterna);
		}
		return null;
	}
		
	/**
	 * Función que construye la lista de referencias personales a devolver
	 * 
	 * @param response
	 * @param idInterna del cliente
	 * @return RiesgoClientePersonaFisicaBean datos de riesgo de cliente
	 */
	private RiesgoClientePersonaFisicaBean consultaReferencias(ResponseBansefi response, Integer idInterna){
		RiesgoClientePersonaFisicaBean datosRiesgo = new RiesgoClientePersonaFisicaBean();		
		if(verificaRespuestaCliente(response)){			
			// Se recuperan y wrappean los datos del listado de referencias personales
			
			datosRiesgo.setDenominacionEmpresa(response.getOPECONSINDVSOCIOECTRN().getPECONSINDVSOCIOECEVT().getPEDATOSEMPLEOV().getDENOMLEGALOR().trim());
			datosRiesgo.setCalle(response.getOPECONSINDVSOCIOECTRN().getPECONSINDVSOCIOECEVT().getPEDATOSEMPLEOV().getNOMBREVIA().trim());
			datosRiesgo.setNumExterior(response.getOPECONSINDVSOCIOECTRN().getPECONSINDVSOCIOECEVT().getPEDATOSEMPLEOV().getVALDOMICNUMERO().trim());
			datosRiesgo.setNumInterior(response.getOPECONSINDVSOCIOECTRN().getPECONSINDVSOCIOECEVT().getPEDATOSEMPLEOV().getVALDOMICPLANTA().trim());
			datosRiesgo.setMunicipio(response.getOPECONSINDVSOCIOECTRN().getPECONSINDVSOCIOECEVT().getPEDATOSEMPLEOV().getNOMBLOCALIDADAG().trim());
			datosRiesgo.setCodigoPostal(response.getOPECONSINDVSOCIOECTRN().getPECONSINDVSOCIOECEVT().getPEDATOSEMPLEOV().getCODPOSTALAG().trim());
			datosRiesgo.setColonia(response.getOPECONSINDVSOCIOECTRN().getPECONSINDVSOCIOECEVT().getPEDATOSEMPLEOV().getNOMBCOLONIA().trim());
			datosRiesgo.setEstado(response.getOPECONSINDVSOCIOECTRN().getPECONSINDVSOCIOECEVT().getPEDATOSEMPLEOV().getNOMBCOMAUTNMAAG().trim());
			
			
			List<ReferenciaPersonalBean> referencias = new ArrayList<ReferenciaPersonalBean>();
			
			for (int i=0;i<response.getOPECONSINDVSOCIOECTRN().getNUMBEROFRECORDS();i++){
				ReferenciaPersonalBean referencia = new ReferenciaPersonalBean();
				
				referencia.setNumeroSecuencia(response.getOPECONSINDVSOCIOECTRN().getPECONSINDVSOCIOECEVT().getPEREFPERSLS().get(i).getNUMSEC());
				referencia.setNombre(response.getOPECONSINDVSOCIOECTRN().getPECONSINDVSOCIOECEVT().getPEREFPERSLS().get(i).getNOMBINNOMBPILA().trim());
				referencia.setApellidoPaterno(response.getOPECONSINDVSOCIOECTRN().getPECONSINDVSOCIOECEVT().getPEREFPERSLS().get(i).getNOMBINAPE1IN().trim());
				referencia.setApellidoMaterno(response.getOPECONSINDVSOCIOECTRN().getPECONSINDVSOCIOECEVT().getPEREFPERSLS().get(i).getNOMBINAPE2IN().trim());
				referencia.setTelefono(response.getOPECONSINDVSOCIOECTRN().getPECONSINDVSOCIOECEVT().getPEREFPERSLS().get(i).getTELEFONO().trim());
				referencia.setDomicilio(response.getOPECONSINDVSOCIOECTRN().getPECONSINDVSOCIOECEVT().getPEREFPERSLS().get(i).getDOMIC50().trim());
				referencia.setRelacionParentesco(response.getOPECONSINDVSOCIOECTRN().getPECONSINDVSOCIOECEVT().getPEREFPERSLS().get(i).getPERSRLTIT().trim());
				referencia.setEstado(EstadoListadosEnum.CONSULTADO);
				
				referencias.add(referencia);
			}
			datosRiesgo.setReferenciasPersonales(referencias);
		}
		
		// Consultamos 
		datosRiesgo = this.consultaInformacionFinancieraRiesgoBackEnd.ejecutarTRN(idInterna,datosRiesgo);
		
		return datosRiesgo;
	}
	
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param idInterno Id del cliente de tipo persona física
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.IPECONSINDVSOCIOECTRN initPeticion(Integer idInterna){
		Ejecutar.IPECONSINDVSOCIOECTRN contexto = new Ejecutar.IPECONSINDVSOCIOECTRN();
		
		Ejecutar.IPECONSINDVSOCIOECTRN.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.IPECONSINDVSOCIOECTRN.STDTRNIPARMV();
		
		Ejecutar.IPECONSINDVSOCIOECTRN.PECONSINDVSOCIOECEVT.PEPERSRLREFPERP referenciasPersonales =
				new Ejecutar.IPECONSINDVSOCIOECTRN.PECONSINDVSOCIOECEVT.PEPERSRLREFPERP();
		
		Ejecutar.IPECONSINDVSOCIOECTRN.PECONSINDVSOCIOECEVT.PEPERSRLEMPACTP datosEmpleo =
				new Ejecutar.IPECONSINDVSOCIOECTRN.PECONSINDVSOCIOECEVT.PEPERSRLEMPACTP();
		
		super.initialize(contexto);
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_PE_CONS_INDV_SOCIOEC_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		
		referenciasPersonales.setCODNRBEEN(super.getEntidad());
		referenciasPersonales.setIDINTERNOPE(idInterna);
		
		datosEmpleo.setCODNRBEEN(super.getEntidad());
		datosEmpleo.setIDINTERNOPE(idInterna);
		
		contexto.getPECONSINDVSOCIOECEVT().setPEPERSRLREFPERP(referenciasPersonales);
		contexto.getPECONSINDVSOCIOECEVT().setPEPERSRLEMPACTP(datosEmpleo);
		contexto.setSTDTRNIPARMV(contextoEntrada);
		
		contexto.setELEVATORPOSITION(1);
		contexto.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);
		
		return contexto;
	}
		
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.IPECONSINDVSOCIOECTRN contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaReferenciasPersonalesRiesgoServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta de referencias personales"
					+ "para clientes de riesgo.", e);
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
	 * no nulos.
	 * 
	 * @param response Respuesta Bansefi
	 * @return <code>true</code> si la respuesta Bansefi contiene elemento no nulos
	 */
	private Boolean verificaRespuestaCliente(ResponseBansefi response){
		Boolean noNulo = false;
		if(response!= null && response.getOPECONSINDVSOCIOECTRN() != null &&
				response.getOPECONSINDVSOCIOECTRN().getPECONSINDVSOCIOECEVT() != null &&
						response.getOPECONSINDVSOCIOECTRN().getPECONSINDVSOCIOECEVT().getPEREFPERSLS() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
}
