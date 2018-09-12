package mx.babel.bansefi.banksystem.cuentas.utils;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ProveedorMensajeSpringUtils;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.backends.ConsultaMinimaPersonaBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaPersonaFisicaBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaPersonaMoralBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaRelacionPersonaCuentaBackEnd;
import mx.babel.bansefi.banksystem.base.backends.consultadomicilio.ConsultaDomicilioBackend;
import mx.babel.bansefi.banksystem.base.beans.ConsultaMinimaPersonaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClienteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaFisicaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaMoralBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioTipoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.CuentaRelacionadaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.RelacionadoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.TipoRelacionPersonaCuenta;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionRangoBean;
import mx.babel.bansefi.banksystem.base.beans.reportes.PersonaPlantillaBean;
import mx.babel.bansefi.banksystem.base.beans.reportes.PlantillaBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoCentrosLoaderTask;
import mx.babel.bansefi.banksystem.base.utils.CatalogoPaisesUtils;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.NumberToLetterConverter;
import mx.babel.bansefi.banksystem.base.utils.PdfUtils;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaCondicionRangoBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaRelacionesCuentaCuentasBackEnd;
import mx.babel.bansefi.banksystem.cuentas.backends.DomicilioCentroBackEnd;
import mx.babel.bansefi.banksystem.cuentas.beans.CampoDocumentoBean;
import mx.babel.bansefi.banksystem.cuentas.beans.EmisionDocumentosBean;
import mx.babel.bansefi.banksystem.cuentas.beans.TarifaBean;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase para generar campos de plantillas word para emisión de documentos de cuentas.
 * 
 * @author mario.montesdeoca
 *
 */
@Component
public class GeneradorDocumentosPdf {
	
	@Autowired
	ContextoUtils contextoUtils;
	
	@Autowired
	CatalogoUtils catalogoUtils;
	
	@Autowired
	CatalogoPaisesUtils catalogoPaisesUtils;
	
	@Autowired
	PdfUtils pdfUtils;
	
	@Autowired
	DomicilioCentroBackEnd domicilioCentroBackEnd;
	
	@Autowired
	ConsultaCondicionRangoBackend condicionRangoBackend;
	
	@Autowired
	ConsultaMinimaPersonaBackEnd consultaMinimaPersonaBackEnd;
	
	@Autowired
	ConsultaRelacionesCuentaCuentasBackEnd consultaRelacionesCuentaCuentasBackEnd;
	
	@Autowired
	ConsultaRelacionPersonaCuentaBackEnd consultaRelacionPersonaCuentaBackEnd;
	
	@Autowired
    ConsultaDomicilioBackend consultaDomicilio;
	
	@Autowired
	CatalogoCentrosLoaderTask catalogoCentros;
	
	@Autowired
	ConsultaPersonaFisicaBackEnd clienteConsultaPersonaFisica;

	@Autowired
	ConsultaPersonaMoralBackEnd clienteConsultaPersonaMoral;

	@Autowired
	ContextoUtils contexto;
	
	private static final String ID_PDS_PLAZO = "029";
	
	private static final String ID_PARMCD_PLAZO = "A38";
	
	private static final String ID_PDS_LIMITE_SALDO = "";
	
	private static final String ID_PARMCD_LIMITE_SALDO = "";
	
	/**
	 * Mètodo para generar un pdf a partir de la plantilla seleccionada.
	 * 
	 * @param documento documento a generar
	 * @param cuenta Cuenta asociada al documento
	 * @param cliente Cliente titular de la cuenta
	 */
	public void generarDocumento(EmisionDocumentosBean documento, 
			CuentaBean cuenta, ClienteBean cliente, TarifaBean tarifa){
		final Map<String, Object> params = new HashMap<String, Object>();
		final Map<String, Object> formularioParams = new HashMap<String, Object>();
		Double saldoMinimo = null;
		for (final CampoDocumentoBean campo : documento.getCampos()) {
			String valor = campo.getValor();
			String desc = campo.getDescripcionVariable();
			try{
				if(("saldo_minimo_requerido").equals(desc)){
					saldoMinimo = new Double(campo.getValor());
					formularioParams.put("SALDO_MINIMO", saldoMinimo);
					//params.put("SALDO_MINIMO_REQUERIDO",  saldoMinimo.toString());
					try{
						formularioParams.put("IMPORTE_LETRA", (NumberToLetterConverter.convertirImporteAImporteEnletraMN(saldoMinimo)));
			        }catch(final NumberFormatException nfe){
			        	formularioParams.put("IMPORTE_LETRA", "No es posible realizar la conversión.");
			        }
				}
				
			}catch(final NumberFormatException nfe){
				formularioParams.put("SALDO_MINIMO", new Double(0));
	        }
			
			if(valor != null){
				valor = valor.toUpperCase();
			}
			formularioParams.put(campo.getDescripcionVariable().toUpperCase(), valor);
		}

		params.put("A", new String("asd"));
		params.put("OTORGANTE", "OTRO");
		params.put("FORMULARIO_PARAMS_MAP", formularioParams);
		String plantilla = documento.getFormulario().toString();
		while (plantilla.length() < 7) {
			plantilla = "0" + plantilla;
		}
		plantilla = "P" + plantilla;
		final List<PlantillaBean> listaBeans = new ArrayList<PlantillaBean>();
		listaBeans.add(setCamposGenericos(cuenta, cliente, tarifa, params));
		params.put("DATA_SOURCE", listaBeans);
		pdfUtils.generaPdf("PlantillaDocumento.jrxml", params, getImagenes(), getSubReportes(plantilla), plantilla ,
				listaBeans, plantilla, 2);
	}
	
	/**
	 * Mètodo para generar el map de imagenes utilizadas en los reportes
	 * @return Mapa de imagenes
	 */
	private Map<String, String> getImagenes(){
		Map<String, String> imagenes = new HashMap<String, String>();
		imagenes.put("logo_top.png", "LOGO");
		imagenes.put("logo-cuadrado.png", "BACKGROUND");
		imagenes.put("P05000100_Codigo.png", "CODIGO_BARRAS");
		return imagenes;
	}
	
	/**
	 * Método para generar el map de subreportes utilizados para el reporte
	 * @return Mapa de subreportes
	 */
	private Map<String, String> getSubReportes(String plantilla){
		Map<String, String> subReportes = new HashMap<String, String>();
		subReportes.put(plantilla + ".jrxml", "FORMULARIO");
		subReportes.put("datos_generales.jrxml", "REPORTE_DATOS_GENERALES");
		subReportes.put("datosGeneralesCliente.jrxml", "REPORTE_GENERAL_CLIENTE");
		subReportes.put("datosRepresentanteLegal.jrxml", "REPORTE_REPRESENTANTE");
		subReportes.put("datosRepresentanteLegal_subreport.jrxml", "REPORTE_REPRESENTANTE_SUBREPORT");
		subReportes.put("datosRepresentanteLegalNombre_subreport.jrxml","REPORTE_NOMBRE_REPRESENTANTE_SUBREPORT");
		subReportes.put("datosRepresentanteLegalMoral.jrxml", "REPORTE_REPRESENTANTE_MORAL");
		subReportes.put("datosRepresentanteLegalVacio.jrxml", "REPORTE_REPRESENTANTE_VACIO");
		subReportes.put("datosRepresentanteLegalCaso.jrxml", "REPORTE_REPRESENTANTE_CASO");
		subReportes.put("informacion_principal.jrxml", "REPORTE_INFORMACION_PRINCIPAL");
		subReportes.put("informacionCuenta.jrxml", "REPORTE_INFORMACION_CUENTA");
		subReportes.put("escrituraConstitutiva.jrxml", "REPORTE_ESCRITURA_CONSTITUTIVA");
		subReportes.put("datosBeneficiario.jrxml", "REPORTE_DATOS_BENEFICIARIO");
		subReportes.put("datosBeneficiario_subreport.jrxml", "REPORTE_DATOS_BENEFICIARIO_SUBREPORT");
		subReportes.put("PersonasAutorizadas.jrxml", "REPORTE_PERSONAS_AUTORIZADAS");
		subReportes.put("PersonasAutorizadas_subreport.jrxml", "REPORTE_PERSONAS_AUTORIZADAS_SUBREPORT");
		subReportes.put("tipoCuenta.jrxml", "REPORTE_TIPO_CUENTA");
		subReportes.put("datosRepresentanteLegal2.jrxml", "REPORTE_REPRESENTANTE2");
		subReportes.put("datosRepresentanteLegal2_subreport.jrxml", "REPORTE_REPRESENTANTE2_SUBREPORT");
		subReportes.put("PlazoAhorro.jrxml", "REPORTE_PLAZO_AHORRO");
		subReportes.put("plazo.jrxml", "REPORTE_PLAZO");
		subReportes.put("usoDeInformacion.jrxml", "REPORTE_USO_INFORMACION");
		subReportes.put("P0500002_FIRMAS_CLAUSULA_subreport.jrxml", "REPORTE_P0500002_CLAUSULAS");
		subReportes.put("anexoComisiones.jrxml", "REPORTE_ANEXO_COMISIONES");
		subReportes.put("anexoComisionesFirmas.jrxml", "REPORTE_ANEXO_COMISIONES_FIRMAS");
		subReportes.put("anexoComision.jrxml", "REPORTE_COMISION");
		subReportes.put("seccionFirmas.jrxml", "REPORTE_SECCION_FIRMAS");
		subReportes.put("nombreRepresentantesFirmas.jrxml", "REPORTE_SECCION_FIRMAS_SUBREPORT");
		subReportes.put("informacionCuenta.jrxml", "REPORTE_INFORMACION_CUENTA");
		subReportes.put("datosInformacionCuentaDebiCuenta.jrxml", "REPORTE_INFORMACION_DEBICUENTA");

		return subReportes;
	}
	
	/**
	 * Método para inicializar los campos genéricos de una plantilla.
	 * 
	 * @param documento Bean con detalles del documento
	 * @param cuenta Bean con detalles de la cuenta
	 * @param cliente Bean con detalles del cliente titular
	 * @param variables Mapa que contiene las variables
	 */
	public PlantillaBean setCamposGenericos(CuentaBean cuenta, ClienteBean cliente, TarifaBean tarifa, Map<String, Object> params){
		prepareCuenta(cuenta);
		cliente = prepareCliente(cliente);
		cliente.setTipoIdentificacion(getTipoIdentificacion(cliente));
		PlantillaBean plantilla = new PlantillaBean();
		String ruta = ProveedorMensajeSpringUtils.getValorConfiguracion("ruta.plantillas")+contexto.getEntidad()+ProveedorMensajeSpringUtils.getValorConfiguracion("ruta.plantillas.jasper");
		plantilla.setRutaReport(ruta);
		plantilla.setEmpleado(contextoUtils.getNombre());
		plantilla.setSucursal(obtenerDescripcionCentro(contextoUtils.getSucursal()));
		plantilla.setNumeroSucursal(contextoUtils.getSucursal());
		plantilla.setDireccionSucursal(obtenerDireccionSucursal(contextoUtils.getSucursal()));
		plantilla.setFechaFormalizacion(cuenta.getFechaEstado());
		//TODO obtener saldo mínimo 
		plantilla.setSaldoMinimo("");
		plantilla.setAbonosMensuales(null);
		plantilla.setCurpCliente(obtenerCurp(cliente));
		plantilla.setOcupacion(obtenerOcupacion(cliente));
		plantilla.setNacionalidad(obtenerNacionalidad(cliente));
		plantilla.setDuracionPlazo(obtenerPlazo(cuenta));
		plantilla.setCuentaOperativa(obtenerCuentaOperativa(cuenta));
		plantilla.setTipoCuenta(getTipoCuenta(cuenta, tarifa));
		List<RelacionadoBean> beneficiarios = getRelacionados(cuenta, TipoRelacionPersonaCuenta.BENEFICIARIO);
		for (RelacionadoBean relacionadoBean : beneficiarios) {
			if(relacionadoBean.getPersona() != null){
				PersonaPlantillaBean persona = new PersonaPlantillaBean();
				persona.setNombre(relacionadoBean.getPersona().getNombreCompleto());
				persona.setNumeroIdentificacion(relacionadoBean.getPersona().getNumIdentificacion());
				persona.setTipoidentificacion(getTipoIdentificacion(relacionadoBean.getPersona()));
				persona.setCantidad(relacionadoBean.getPorcentaje());
				plantilla.getBeneficiarios().add(persona);
			}
		}
		List<RelacionadoBean> autorizados = getRelacionados(cuenta, TipoRelacionPersonaCuenta.AUTORIZADO);
		for(RelacionadoBean relacionadoBean : autorizados) {
			if(relacionadoBean.getPersona() != null){
				PersonaPlantillaBean persona = new PersonaPlantillaBean();
				persona.setNombre(relacionadoBean.getPersona().getNombreCompleto());
				persona.setNumeroIdentificacion(relacionadoBean.getPersona().getNumIdentificacion());
				persona.setTipoidentificacion(getTipoIdentificacion(relacionadoBean.getPersona()));
				plantilla.getAutorizados().add(persona);
			}
		}
		
		List<RelacionadoBean> apoderados = getRelacionados(cuenta, TipoRelacionPersonaCuenta.APODERADO);
		for(RelacionadoBean relacionadoBean : apoderados) {
			if(relacionadoBean.getPersona() != null){
				PersonaPlantillaBean persona = new PersonaPlantillaBean();
				persona.setNombre(relacionadoBean.getPersona().getNombreCompleto());
				persona.setNumeroIdentificacion(relacionadoBean.getPersona().getNumIdentificacion());
				persona.setTipoidentificacion(getTipoIdentificacion(relacionadoBean.getPersona()));
				plantilla.getApoderados().add(persona);
				plantilla.getRepresentantes().add(persona);
			}
		}
		
		List<RelacionadoBean> representantes = getRelacionados(cuenta, TipoRelacionPersonaCuenta.REPRESENTANTE_LEGAL);
		for(RelacionadoBean relacionadoBean : representantes) {
			if(relacionadoBean.getPersona() != null){
				PersonaPlantillaBean persona = new PersonaPlantillaBean();
				persona.setNombre(relacionadoBean.getPersona().getNombreCompleto());
				persona.setNumeroIdentificacion(relacionadoBean.getPersona().getNumIdentificacion());
				persona.setTipoidentificacion(getTipoIdentificacion(relacionadoBean.getPersona()));
				plantilla.getRepresentantes().add(persona);
				plantilla.getApoderados().add(persona);
			}
		}
		
		plantilla.setClienteBean(cliente);
		plantilla.setCuenta(cuenta);
		plantilla.setDomicilio(getDomicilioCliente(cliente));
		return plantilla;
	}
	
	private ClienteBean prepareCliente(ClienteBean cliente) {
		ClienteBean clienteBean = null;
		try{
			clienteBean = clienteConsultaPersonaFisica.ejecutarTRN(cliente.getIdInterna());
		}catch(ControlableException e){
		}
		if(clienteBean == null){
			try{
				clienteBean = clienteConsultaPersonaMoral.ejecutarTRN(cliente.getIdInterna());
			}catch(ControlableException e){
			}
		}
		if(clienteBean != null){
			clienteBean.setTipoClienteEnum(clienteBean.getTipo());
			return clienteBean;
		}else{
			clienteBean = prepareCliente2(cliente);
			clienteBean.setTipoClienteEnum(clienteBean.getTipo());
		}
		return clienteBean;
	}
	
	private ClienteBean prepareCliente2(ClienteBean cliente) {
		ConsultaMinimaPersonaBean clienteBean = new ConsultaMinimaPersonaBean();
		if(cliente == null){
			cliente = new ClienteBean();
		}else{
			try{
				clienteBean = consultaMinimaPersonaBackEnd.ejecutarTRN(cliente.getIdInterna());
			}catch(final ControlableException e){
			}
			if(clienteBean != null){
				cliente.setFechaNacimiento(clienteBean.getFechaNacimientoConstitucion());
				cliente.setPais(clienteBean.getNombrePais());
				DomicilioTipoBean domicilio = new DomicilioTipoBean();
				domicilio.setNombreCalle(clienteBean.getDomicilio());
				domicilio.setMunicipio(clienteBean.getNombreLocalidad());
				domicilio.setEstado(clienteBean.getNombreProvincia());
				domicilio.setCodigoPostal(clienteBean.getCodigoPostal());
				cliente.getDomicilios().add(domicilio);
				if(clienteBean.getTelefonos() != null && !clienteBean.getTelefonos().isEmpty()){
					domicilio.setTelefono(clienteBean.getTelefonos().get(0));
				}
				if(cliente instanceof ClientePersonaFisicaBean){
					((ClientePersonaFisicaBean) cliente).setPaisNacionalidad(clienteBean.getNombrePais());
				}
			}
		}
		return cliente;
	}
	
	private String getTipoIdentificacion(ClienteBean cliente){
		if(cliente != null){
			try{
				return this.catalogoUtils.getCatalogoBean(CatalogoEnum.TP_ID_EXT_PERS, 
								cliente.getTipoIdentificacion()).getDescripcionL();
			}catch(NullPointerException | NoControlableException | ControlableException nce){
			}
			return cliente.getTipoIdentificacion();
		}
		return "";
	}
	
	private void prepareCuenta(CuentaBean cuentaBean){
		if(cuentaBean == null){
			cuentaBean = new CuentaBean();
		}else{
			try{
				cuentaBean.setPersonasRelacionadas(
					consultaRelacionPersonaCuentaBackEnd.ejecutarTRN(cuentaBean.getNumeroCuenta(),true));
			}catch(ControlableException e){
			}
			try{
				cuentaBean.setCuentasRelacionadas(
					consultaRelacionesCuentaCuentasBackEnd.ejecutarTRN(cuentaBean.getNumeroCuenta()));
			}catch(ControlableException e){
			}
		}
	}
	
	private String obtenerCurp(ClienteBean cliente){
		if(cliente instanceof ClientePersonaFisicaBean){
			return ((ClientePersonaFisicaBean) cliente).getCurp();
		}
		return "";
	}
	
	private String obtenerNacionalidad(ClienteBean cliente){
		if(cliente.getMunicipioCatGeo() != null){
			cliente.setMunicipio(cliente.getMunicipioCatGeo().getNombreMunicipio());
			cliente.setPais(cliente.getMunicipioCatGeo().getNombrePais());
			cliente.setEstado(cliente.getMunicipioCatGeo());
		}
		if(cliente instanceof ClientePersonaFisicaBean){
			try{
				((ClientePersonaFisicaBean) cliente).setPaisNacionalidad(
						this.catalogoPaisesUtils.getCatalogoDescripcion(
								((ClientePersonaFisicaBean)cliente).getPaisNacionalidad()));
			}catch(NullPointerException | NoControlableException | ControlableException nce){
			}
			return ((ClientePersonaFisicaBean) cliente).getPaisNacionalidad();
		}
		return "";
	}
	
	private String obtenerOcupacion(ClienteBean cliente){
		if(cliente instanceof ClientePersonaFisicaBean){
			try{
				((ClientePersonaFisicaBean) cliente).setCno(
						this.catalogoUtils.getCatalogoBean(CatalogoEnum.TP_CNO_INDV, 
								((ClientePersonaFisicaBean)cliente).getCno()).getDescripcionL());
			}catch(NullPointerException | NoControlableException | ControlableException nce){
			}
			return ((ClientePersonaFisicaBean) cliente).getCno();
		}
		if(cliente instanceof ClientePersonaMoralBean){
			try{
				((ClientePersonaMoralBean) cliente).setEstructuraLegal(
						this.catalogoUtils.getCatalogoBean(CatalogoEnum.TP_ESTRCT_LGL_ORG, 
								((ClientePersonaMoralBean)cliente).getEstructuraLegal()).getDescripcionL());
			}catch(NullPointerException | NoControlableException | ControlableException nce){
			}
			return ((ClientePersonaMoralBean) cliente).getEstructuraLegal();
		}
		return "";
	}
	
	private String obtenerCuentaOperativa(CuentaBean cuenta){
		for (CuentaRelacionadaBean cuentaRelacionada : cuenta.getCuentasRelacionadas()) {
			if(ConstantesFuncionales.REL_AC_AC_CUENTA_OPERATIVA.equals(cuentaRelacionada.getTipoRelacion())){
				if(cuentaRelacionada.getCuenta() != null && cuentaRelacionada.getCuenta().getNumeroCuenta() != null){
					return cuentaRelacionada.getCuenta().getNumeroCuenta().toString();
				}
			}
		}
		return "";
	}
	
	/**
	 * Método para verificar los domicilios de un cliente 
	 * @param cliente Cliente al que se le consultaran los domicilios
	 * @return Bean domicilio 
	 */
	private DomicilioTipoBean getDomicilioCliente(ClienteBean cliente){
		if(cliente.getDomicilios() == null || cliente.getDomicilios().isEmpty()){
			try{
				prepareCliente2(cliente);
			}catch(ControlableException e){
			}
		}
		if(cliente.getDomicilios()!= null && !cliente.getDomicilios().isEmpty()){
			if(cliente.getDomicilios().get(0).getMunicipioCatGeo() != null){
				cliente.getDomicilios().get(0).setMunicipio(
						cliente.getDomicilios().get(0).getMunicipioCatGeo().getNombreMunicipio());
			}
			if(cliente.getDomicilios().get(0).getDireccion() != null){
				cliente.getDomicilios().get(0).setNombreCalle(cliente.getDomicilios().get(0).getDireccion());
			}
			return cliente.getDomicilios().get(0);
		}
		return new DomicilioTipoBean();
	}
	
	/**
	 * Método para obtener los relacionados a la cuenta que tengan un tipo de relaciòn en especifico.
	 * @param cuenta cuenta a la que se consultará
	 * @param tipo Tipo de relación que se busca
	 * @return Lista de personas relacionadas del tipo consultado
	 */
	private List<RelacionadoBean> getRelacionados(CuentaBean cuenta, TipoRelacionPersonaCuenta tipo){
		List<RelacionadoBean> relacionados = new ArrayList<RelacionadoBean>();
		for (RelacionadoBean relacionadoBean : cuenta.getPersonasRelacionadas()) {
			if(tipo.equals(relacionadoBean.getTipo())){
				relacionados.add(relacionadoBean);
			}
		}
		return relacionados;
	}
	
	/**
	 * Función para obtener la descripcion del nombre del centro.
	 *
	 * @return la descripcion del centro
	 */
	public String obtenerDescripcionCentro(final String clave) {
		try {
			return catalogoCentros.getCatalogoBean(contextoUtils.getEntidad(),
					clave).getDescripcionC();
		} catch (final ControlableException ce) {
			return "";
		}
	}
	
	public String obtenerDireccionSucursal(final String clave){
		String direccion = "";
		DomicilioTipoBean domicilioSucursal = null;
		try{
			int direccionPPal = domicilioCentroBackEnd.ejecutarTRN(clave);
			if(direccionPPal != 0){
				domicilioSucursal = (DomicilioTipoBean) this.consultaDomicilio.ejectuarTRN(null,direccionPPal);
			}
		}catch(ControlableException ce){
		}
		if(domicilioSucursal != null){
			direccion = domicilioSucursal.getDireccion();
		}
		return direccion;
	}
	
	public String obtenerPlazo(CuentaBean cuenta){
		String plazo = " ";
		try{
			CondicionRangoBean condicion = condicionRangoBackend.ejecutarTRN(
					cuenta.getNumeroCuenta(), ID_PDS_PLAZO, ID_PARMCD_PLAZO);
			if(condicion != null){
				plazo = Integer.toString(condicion.getPreferente().intValue());
			}
		}catch(ControlableException ce){
		}
		return plazo;
	}
	
	public String obtenerLimiteSaldo(CuentaBean cuenta){
		String limiteSaldo = " ";
		try{
			CondicionRangoBean condicion = condicionRangoBackend.ejecutarTRN(
					cuenta.getNumeroCuenta(), ID_PDS_LIMITE_SALDO, ID_PARMCD_LIMITE_SALDO);
			if(condicion != null && condicion.getPreferente() != null){
				limiteSaldo = NumberFormat.getCurrencyInstance().format(condicion.getPreferente());
			}
		}catch(ControlableException ce){
		}
		return limiteSaldo;
	}
	
	private String getTipoCuenta(CuentaBean cuenta, TarifaBean tarifa){
		String nombre = "";
		if(!StringUtils.isEmpty(cuenta.getTipoCuenta())){
			nombre =  cuenta.getTipoCuenta();
		}else{
			if(tarifa != null){
				if(!StringUtils.isEmpty(tarifa.getNombre())){
					nombre = tarifa.getNombre();
				}
				if(!StringUtils.isEmpty(tarifa.getDescripcion())){
					nombre = nombre +  " " + tarifa.getDescripcion();
				}
			}
		}
		return nombre;
	}
}
