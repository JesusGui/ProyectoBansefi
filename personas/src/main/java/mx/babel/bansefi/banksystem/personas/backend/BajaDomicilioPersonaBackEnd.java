package mx.babel.bansefi.banksystem.personas.backend;

import java.io.Serializable;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.enums.TipoDomicilioEnum;
import mx.babel.bansefi.banksystem.base.utils.TipoDomicilioUtils;
import mx.babel.bansefi.banksystem.personas.webservices.bajadomiciliopersona.BajaDomicilioPersonaServicio;
import mx.babel.bansefi.banksystem.personas.webservices.bajadomiciliopersona.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.bajadomiciliopersona.Ejecutar.ITRPEBAJADOMICTRNI.TRPEBAJADOMICEVTY.STDVALORELCTRDRV;
import mx.babel.bansefi.banksystem.personas.webservices.bajadomiciliopersona.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.bajadomiciliopersona.ResponseBansefi;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BajaDomicilioPersonaBackEnd extends BackEndBean implements Serializable{

	private static final long serialVersionUID = -3763081123051422076L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	@Autowired
	TipoDomicilioUtils tipoDomicilioUtils;
	
	private static final Logger LOGGER = LogManager.getLogger(ModificaDomicilioPersonaBackEnd.class);
	
	/**
	 * Función principal que descencadena todo el llamado de la TRN/WebService
	 * 
	 * @param contadores Bean generico para la consulta de Contadores de Centro y Puesto
	 * @return ContadoresCentroBean
	 */
	public String ejecutarTRN(int idInternoPersona, int numeroDireccion, TipoDomicilioEnum tipoDomicilio, String telefono){
		Ejecutar.ITRPEBAJADOMICTRNI contexto = initPeticion(idInternoPersona, numeroDireccion, tipoDomicilio, telefono);
		EjecutarResult respuesta = ejecutarWS(contexto);
		super.verificaRespuesta(respuesta);
		if (verificaResponseBansefi(respuesta)) {
			return respuestaBaja(respuesta.getResponseBansefi());
		}
		return null;
	}
	
	/**
	 * Funcion que recibe la respuesta de la TRN y Wrappea a un objeto propio
	 * del sistema
	 * 
	 * @param response
	 * @return String
	 */
	private String respuestaBaja(ResponseBansefi response){
		String masDir = null;		
		if (verificaRespuestaCliente(response)) {
			// Se recuperan y wrappean los datos de la cuenta
			masDir = response.getOTRPEBAJADOMICTRNO()
					.getTRPEBAJADOMICEVTZ().getINDMASDIRV().getINDMASDIR();
		}

		return masDir;
	}
	
	/**
	 * Función que inicializa los parametros de entrada que necesita el servicio
	 * web para ser ejecutado
	 * 
	 * @param contadoresCentroBean
	 * @return Ejecutar.ITROBTECONTTRNI
	 */
	private Ejecutar.ITRPEBAJADOMICTRNI initPeticion(
			int idInternoPersona, int numeroDireccion, TipoDomicilioEnum tipoDomicilio, String telefono) {
		Ejecutar.ITRPEBAJADOMICTRNI contexto = new Ejecutar.ITRPEBAJADOMICTRNI();
		Ejecutar.ITRPEBAJADOMICTRNI.STDTRNIPARMV contextoEntrada = new Ejecutar.ITRPEBAJADOMICTRNI.STDTRNIPARMV();
		Ejecutar.ITRPEBAJADOMICTRNI.TRPEBAJADOMICEVTY datosEntrada = new Ejecutar.ITRPEBAJADOMICTRNI.TRPEBAJADOMICEVTY();

		initialize(contexto);

		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_PE_BAJA_DOMIC_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(getTerminal());
		
		// Envia la entidad y el id interno del usuario
		Ejecutar.ITRPEBAJADOMICTRNI.TRPEBAJADOMICEVTY.PEPERSRLDIRP pepersrldirp = new Ejecutar.ITRPEBAJADOMICTRNI.TRPEBAJADOMICEVTY.PEPERSRLDIRP();
		pepersrldirp.setCODNRBEEN(getEntidad());
		pepersrldirp.setIDINTERNOPE(idInternoPersona);
//		pepersrldirp.setCODDIR("1");
//		pepersrldirp.setCODPERSRLDIR(tipoDomicilioUtils.getClave(tipoDomicilio));
//		pepersrldirp.setCODPERSRLDIR(tipoDomicilio.getClave());
		pepersrldirp.setNUMDIR(numeroDireccion);
		
		//Baja telefono
		if(!StringUtils.isBlank(telefono)){
			STDVALORELCTRDRV electr = new STDVALORELCTRDRV();
			electr.setVALORELCTRDR(telefono);
			electr.setVALORELCTRDRLEN(telefono.length());	
			datosEntrada.setSTDVALORELCTRDRV(electr);
		}
		
		
		datosEntrada.setPEPERSRLDIRP(pepersrldirp);
				
		contexto.setTRPEBAJADOMICEVTY(datosEntrada);
		contexto.setSTDTRNIPARMV(contextoEntrada);
		initialize(contexto);

		return contexto;

	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto
	 *            Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRPEBAJADOMICTRNI contexto) {
		EjecutarResult respuesta = null;
		try {
			respuesta = (EjecutarResult) this.servicioWebUtils.ejecutarWS(
					BajaDomicilioPersonaServicio.class, contexto);
		} catch (NoControlableException e) {
			LOGGER.error("error", e);
			throw new NoControlableException(
					"Error al invocar servicio web de consulta de "
							+ "Baja Domicilio Persona.", e);
		}
		return respuesta;
	}
	
	/**
	 * Función que valida que la respuesta del servidor no este vacía.
	 * 
	 * @param respuesta
	 *            Objeto respuesta del servicio web
	 * @return <code>true</code> si la respuesta no esta vacía.
	 */
	private Boolean verificaResponseBansefi(EjecutarResult respuesta) {
		Boolean noNulo = false;
		if (respuesta != null && respuesta.getResponseBansefi() != null) {
			noNulo = true;
		}
		return noNulo;
	}
	
	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con los
	 * datos de la consulta.
	 * 
	 * @param response
	 *            Respuesta Bansefi con datos de la consulta
	 * @return <code>true</code> si la respuesta Bansefi contiene elemento no
	 *         nulos
	 */
	private Boolean verificaRespuestaCliente(ResponseBansefi response) {
		Boolean noNulo = false;
		if (response != null
				&& response.getOTRPEBAJADOMICTRNO() != null
				&& response.getOTRPEBAJADOMICTRNO().getTRPEBAJADOMICEVTZ() != null) {
			noNulo = true;
		}
		return noNulo;
	}
	
}
