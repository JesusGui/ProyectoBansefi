package mx.babel.bansefi.banksystem.base.backends;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import mx.babel.arq.catalogo.beans.CatalogoGeoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.consultadomicilio.ConsultaDomicilioBackend;
import mx.babel.bansefi.banksystem.base.beans.DocumentoPersonaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClienteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaFisicaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioTipoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.TipoCliente;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.utils.CatalogoGeoUtils;
import mx.babel.bansefi.banksystem.base.utils.CedulaConocimientoUtils;
import mx.babel.bansefi.banksystem.base.utils.DomicilioUtils;
import mx.babel.bansefi.banksystem.base.webservices.consultapersonafisica.ConsultaPersonaFisicaServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultapersonafisica.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultapersonafisica.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultapersonafisica.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.wrappers.ClienteWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaPersonaFisicaBackEnd extends BackEndBean{

	private static final long serialVersionUID = -6078851952880096499L;
	
	private static final String FECHA_INICIO_MIN = "11/11/1111";
	
	private static final String INDICADOR_CIERTO = "S";

	@Autowired
	ServicioWebUtils servicioWebUtils;

	@Autowired
	ClienteWrapper clienteWrapper;
	
	@Autowired
	CatalogoGeoUtils catalogoGeoUtils;
	
	@Autowired
    DomicilioUtils domicilioUtils;
	
	@Autowired
    ConsultaListaDocumentosBackend consultaListaDocumentos;
	
	@Autowired
    ConsultaDomicilioBackend consultaDomicilio;
	
	@Autowired
	ConsultaRepresentanteLegalBackEnd clienteConsultaRepresentanteLegal;
	
	@Autowired
	CedulaConocimientoUtils cedulaConocimientoUtils;
	
	/**
	 * Función de devolver un cliente de tipo persona física construyendolo a 
	 * partir de servicios web.
	 * 
	 * @param idInterno id interno del cliente a consultar
	 * @return El cliente de tipo persona física
	 */
	public ClienteBean ejecutarTRN(Integer idInterno) 
			throws NoControlableException, ControlableException{
		ClientePersonaFisicaBean clienteFisica = getClienteResponse(idInterno);
		if(clienteFisica != null){
			if(null != clienteFisica.getRepresentante()){
				Integer idRepresentante = 
						clienteConsultaRepresentanteLegal.ejecutarTRN(idInterno, TipoCliente.PERSONA_FISICA, 
								ConstantesFuncionales.isMenorEdad(clienteFisica.getFechaNacimiento()));
				
				if(idRepresentante != null){
					clienteFisica.setRepresentante(getClienteResponse(idRepresentante));
				}		
			}
		}
		return clienteFisica;			
	}
	
	/**
	 * Función para obtener los datos de un cliente de tipo persona física invocando un 
	 * servicio web.
	 * 
	 * @param idInterno Id interno del cliente de tipo persona física.
	 * @return Objeto con atributos del cliente consultado.
	 */
	public ClientePersonaFisicaBean getClienteResponse(Integer idInterno) 
			throws NoControlableException, ControlableException{
		Ejecutar.ITRPECONSINDVTRNI contexto = initPeticion(idInterno);
		
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
		
		return getCliente(idInterno, respuesta.getResponseBansefi());		
	}
	
	/**
	 * Función encargada de obtener el cliente a partir de la respuesta del servicio web
	 * 
	 * @param idInterno El id interno de la persona moral
	 * @param response El objeto de reultado enviado por el servicio web.
	 * @return El cliente con los datos recibidos del ser web
	 */
	private ClientePersonaFisicaBean getCliente(Integer idInterno, ResponseBansefi response)
			throws NoControlableException, ControlableException{
		ClientePersonaFisicaBean clienteFisica = null;		
		if(verificaRespuestaPersonaFisica(response)){
			ResponseBansefi.OTRPECONSINDVTRNO.TRPECONSINDVEVTZ.PECONSINDVOBJTRDV cliente = 
					response.getOTRPECONSINDVTRNO().getTRPECONSINDVEVTZ().getPECONSINDVOBJTRDV();
			clienteFisica = clienteWrapper.wrappBean(cliente);
			clienteFisica.setCapacidadLegal(cliente.getCODCPCDADLGLIN());
			clienteFisica.setFusionado(ConstantesFuncionales.CLIENTE_FUSIONADO.equals(cliente.getINDCLIENTE()));
			// Se inicializa el objeto del estado

			clienteFisica.setEstado(new CatalogoGeoBean());
			// Construimos el lugar de nacimiento del cliente, cuando es codificado recuperamos los datos a
			// partir del nombre del municipio a partir de uno de los datos obligatorios en los codificados
			if (cliente.getCODENTCOLECAG()!= null && !("").equalsIgnoreCase(cliente.getCODENTCOLECAG().trim())){
				boolean encontrado = false;
				List<CatalogoGeoBean> candidatos = catalogoGeoUtils.getCatalogoGeoBean();
				
				for (int i=0; i<candidatos.size() && !encontrado;i++){
					if (candidatos.get(i).getCodigoMunicipio() != null && candidatos.get(i).getCodigoMunicipio().trim().equals(cliente.getCODMUNICIPIOAG().trim())){
						clienteFisica.setMunicipioCatGeo(candidatos.get(i));
						encontrado = true;
					}
				}
			}else{
				clienteFisica.setMunicipioCatGeo(new CatalogoGeoBean());	
				clienteFisica.getMunicipioCatGeo().setNombreMunicipio(cliente.getNOMBLOCALIDADAG().trim());
				clienteFisica.getMunicipioCatGeo().setMunicipioLocalidad(cliente.getNOMBLOCALIDADAG().trim());
				clienteFisica.getEstado().setNombreProvincia(cliente.getNOMBPROVINCIAAG().trim());
				clienteFisica.setPais(cliente.getCODPAISAG().trim());
				if (cliente.getCODPROVINCIAAG() != null && !("").equalsIgnoreCase(cliente.getCODPROVINCIAAG().trim())){
					clienteFisica.getEstado().setCodigoProvincia(cliente.getCODPROVINCIAAG());
				}
			}
					
					
			// Invertimos el valor del indicador puesto que el indice que devuelve la TRN es de Publicidad
			if (clienteFisica.getNoPublicidad() != null && clienteFisica.getNoPublicidad()){
				clienteFisica.setNoPublicidad(false);
			}else{
				clienteFisica.setNoPublicidad(true);
			}
			
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			
			// Si el cliente no es trabajador independiente vaciamos los datos de fecha de alta y de 
			if (clienteFisica.getAutonomo() ==null || !clienteFisica.getAutonomo()){
				clienteFisica.setFechaAltaAutonomo(null);
				clienteFisica.setCnae(null);
			}
			
			
			
			// Tratamos la fecha de autonomo para no informarla si viene a la fecha de inicio minima
			if (clienteFisica.getFechaAltaAutonomo() !=null && ConsultaPersonaFisicaBackEnd.FECHA_INICIO_MIN.equals(df.format(clienteFisica.getFechaAltaAutonomo()))){
				clienteFisica.setFechaAltaAutonomo(null);
			}
			
			// Tratamos la fecha de fallecimiento para visualizarla a vacio si viene como cliente no fallecido
			if (clienteFisica.getFechaFallecimiento() !=null && ConstantesFuncionales.MAX_FECHA_FIN.equals(df.format(clienteFisica.getFechaFallecimiento()))){
				clienteFisica.setFechaFallecimiento(null);
			}
						
			clienteFisica.setIsCliente(INDICADOR_CIERTO.equals(cliente.getINDCLIENTE().trim()));
			clienteFisica.setIsGestor(INDICADOR_CIERTO.equals(cliente.getINDGESTOR().trim()));	
			
			if(ConstantesFuncionales.isMenorEdad(clienteFisica.getFechaNacimiento()) || 
					(!ConstantesFuncionales.CAPACIDAD_LEGAL_PLENA.equals(cliente.getCODCPCDADLGLIN()) 
					&& !"".equals(cliente.getCODCPCDADLGLIN().trim()))){
				clienteFisica.setRepresentante(new ClientePersonaFisicaBean());
			}
			
			// Consulta de domicilio
			DomicilioTipoBean domicilioPpal = consultaDomicilio.ejectuarTRN(null,cliente.getNUMDIRPRAL());
			if (domicilioPpal != null){
				clienteFisica.getDomicilios().add(domicilioPpal);
			}else{
				clienteFisica.getDomicilios().add(new DomicilioTipoBean());
			}
			
			// Consulta de comprobante de domicilios
			List<DocumentoPersonaBean> documentosConsultados = this.consultaListaDocumentos.ejectuarTRN(clienteFisica.getIdInterna(), false);
			boolean encontrado = false;
			for (int i=0;i<documentosConsultados.size() && !encontrado;i++){
				if (ConstantesFuncionales.getCodigosComprobanteDomicilio().contains(documentosConsultados.get(i).getTipo())){
					clienteFisica.getDomicilios().get(0).setComprobanteDomicilio(documentosConsultados.get(i).getTipo());
					clienteFisica.getDomicilios().get(0).setDescripcion(documentosConsultados.get(i).getDescripcion());
					encontrado = true;
				}
			}
			
			// Consulta de datos de cedula de conocimiento
			ClientePersonaFisicaBean persona = cedulaConocimientoUtils.consultaCedulaConocimiento(clienteFisica.getIdInterna());
			clienteFisica.setTransaccionalidad(persona.getTransaccionalidad());
			clienteFisica.setUsoCuenta(persona.getUsoCuenta());
			clienteFisica.setDatosRiesgo(persona.getDatosRiesgo());
			clienteFisica.setEsClienteRiesgo(persona.getEsClienteRiesgo());

		}
		return clienteFisica;
	}
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param idInterno Id del cliente de tipo persona física
	 * @return Objeto de petición al web service
	 */
	public Ejecutar.ITRPECONSINDVTRNI initPeticion( Integer idInterno) {
		Ejecutar.ITRPECONSINDVTRNI contexto = new Ejecutar.ITRPECONSINDVTRNI();
		Ejecutar.ITRPECONSINDVTRNI.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRPECONSINDVTRNI.STDTRNIPARMV();
		Ejecutar.ITRPECONSINDVTRNI.TRPECONSINDVEVTY cuerpoContexto =
				new Ejecutar.ITRPECONSINDVTRNI.TRPECONSINDVEVTY();
		Ejecutar.ITRPECONSINDVTRNI.TRPECONSINDVEVTY.PEPERSP persona = 
				new Ejecutar.ITRPECONSINDVTRNI.TRPECONSINDVEVTY.PEPERSP();
		contexto.setSTDTRNIPARMV(contextoEntrada);
		contexto.setTRPECONSINDVEVTY(cuerpoContexto);
		super.initialize(contexto);
		
		persona.setCODNRBEEN(getEntidad());
		if(idInterno != null){
			persona.setIDINTERNOPE(idInterno);
		}
		cuerpoContexto.setPEPERSP(persona);
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_PE_CONS_INDV_TRN);
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRPECONSINDVTRNI contexto) 
			throws NoControlableException{
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaPersonaFisicaServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta de "
					+ "persona física.", e);
		}
		return respuesta;
	}
	
	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con los datos
	 * de la persona física.
	 * 
	 * @param response Respuesta Bansefi con datos de la persona moral
	 * @return <code>true</code> si la respuesta Bansefi contiene un cliente de tipo persona moral
	 */
	private Boolean verificaRespuestaPersonaFisica(ResponseBansefi response){
		Boolean noNulo = false;
		if(response != null && response.getOTRPECONSINDVTRNO() != null && 
				response.getOTRPECONSINDVTRNO().getTRPECONSINDVEVTZ() != null && 
				response.getOTRPECONSINDVTRNO().getTRPECONSINDVEVTZ().getPECONSINDVOBJTRDV() 
				!= null){
			noNulo = true;
		}
		return noNulo;
	}	
	
}
