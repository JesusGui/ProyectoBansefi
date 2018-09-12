package mx.babel.bansefi.banksystem.cuentas.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.utils.VariablePlantillaUtils;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.backends.ConsultaPersonaFisicaBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaPersonaMoralBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaRelacionPersonaCuentaBackEnd;
import mx.babel.bansefi.banksystem.base.beans.domain.ClienteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaFisicaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.RelacionadoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.TipoRelacionPersonaCuenta;
import mx.babel.bansefi.banksystem.base.utils.CatalogoCentrosLoaderTask;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaRelacionesCuentaCuentasBackEnd;
import mx.babel.bansefi.banksystem.cuentas.beans.EmisionDocumentosBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase para generar campos de plantillas word para emisión de documentos de cuentas.
 * 
 * @author mario.montesdeoca
 *
 */
@Component
public class CamposGenericosDocumentoUtils {
	
	@Autowired
	ContextoUtils contextoUtils;
	
	@Autowired
	ConsultaRelacionPersonaCuentaBackEnd consultaRelacionPersonaCuentaBackEnd;

	@Autowired
	ConsultaRelacionesCuentaCuentasBackEnd consultaRelacionesCuentaCuentasBackEnd;
	
	@Autowired
	ConsultaPersonaFisicaBackEnd clienteConsultaPersonaFisica;

	@Autowired
	ConsultaPersonaMoralBackEnd clienteConsultaPersonaMoral;
	
	@Autowired
	CatalogoCentrosLoaderTask catalogoCentros;
	
	private DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	/**
	 * Método para inicializar los campos genéricos de una plantilla.
	 * 
	 * @param documento Bean con detalles del documento
	 * @param cuenta Bean con detalles de la cuenta
	 * @param cliente Bean con detalles del cliente titular
	 * @param variables Mapa que contiene las variables
	 */
	public void setCamposGenericos(EmisionDocumentosBean documento, 
			CuentaBean cuenta, ClienteBean cliente, Map<String, VariablePlantillaUtils> variables){
		prepareCuenta(cuenta);
		prepareCliente(cliente);
		variables.put("«num_ofic_ac_prop»", creaVariable(contextoUtils.getSucursal()));
		variables.put("«nomb_ofic_ac_prop»",creaVariable(obtenerDescripcionCentro(contextoUtils.getSucursal())));
		variables.put("«direccion_ofic_ac_prop»", creaVariable("Obtener la dirección"));
		
		variables.put("«num_ac_prop»", creaVariable(cuenta.getNumeroCuenta()));
		variables.put("«fec_formalizacion»", creaVariable(formatter.format(cuenta.getFechaEstado())));
		
		if(cliente.getFechaNacimiento() != null){
			variables.put("«fec_nacimconstit_titular»", creaVariable(formatter.format(cliente.getFechaNacimiento())));
		}
		variables.put("«loc_nacimconstit_titular»", creaVariable(cliente.getLugarNacimiento()));
		variables.put("«pais_nacimconstit_titular»", creaVariable(cliente.getPais()));
		variables.put("«descr_doc_titular»", creaVariable(cliente.getTipoIdentificacion()));
		variables.put("«nifcif_titular»",creaVariable(cliente.getNumIdentificacion()));
		variables.put("«rfc_titular»", creaVariable(cliente.getRfc()));
		
		DomicilioBean domicilio = getDomicilioCliente(cliente);
		variables.put("«domic_fiscal_titular»", creaVariable(getDireccion(domicilio)));
		variables.put("«tfno_titular»", creaVariable(domicilio.getTelefono()));
		
		ClientePersonaFisicaBean representante = getRepresentanteCliente(cliente);
		variables.put("«VARIOS_REPRESENTANTES_COLUM1»", creaVariable(representante.getNombreCompleto()));
		variables.put("«VARIOS_REPRESENTANTES_COLUM2»", creaVariable(representante.getTipoIdentificacion()));
		variables.put("«VARIOS_REPRESENTANTES_COLUM3»", creaVariable(representante.getNumIdentificacion()));
		variables.put("«VARIOS_REPRESENTANTES_COLUM4»", creaVariable(representante.getCurp()));

		//Firmas
		StringBuilder firmaRepresentantes = new StringBuilder("");
		for (RelacionadoBean relacionadoBean : cuenta.getPersonasRelacionadas()) {
			if (relacionadoBean.getTipo().name().equals("REPRESENTANTE_LEGAL")) {
				firmaRepresentantes.append(relacionadoBean.getPersona().getNombreCompleto() + "\n");
			}
		}
		variables.put("«VARIOS_REPRESENTANTES_FIRMA_COLUM1»", creaVariable(firmaRepresentantes));

		List<RelacionadoBean> titulares = getRelacionados(cuenta, TipoRelacionPersonaCuenta.TITULAR);
		String newLine = System.lineSeparator();
		StringBuffer titularesString = new StringBuffer();
		for (RelacionadoBean relacionadoBean : titulares) {
			if(relacionadoBean.getPersona() != null){
				titularesString.append(relacionadoBean.getPersona().getNombreCompleto());
				titularesString.append(newLine);
			}
		}
		variables.put("«FIRMAS_TITULAR_REPETITIVO_COLUM1»", creaVariable(titularesString));
		List<RelacionadoBean> beneficiarios = getRelacionados(cuenta, TipoRelacionPersonaCuenta.BENEFICIARIO);
		StringBuffer beneficiariosString = new StringBuffer();
		StringBuffer porcentajeBeneficiariosString = new StringBuffer();
		for (RelacionadoBean relacionadoBean : beneficiarios) {
			if(relacionadoBean.getPersona() != null){
				beneficiariosString.append(relacionadoBean.getPersona().getNombreCompleto());
				beneficiariosString.append(newLine);
				porcentajeBeneficiariosString.append(relacionadoBean.getPorcentaje()).append("%");
				porcentajeBeneficiariosString.append(newLine);
			}
		}
		variables.put("«TANDAHORRO_TRADICIONAL_BENEF_R_COLUM1»", creaVariable(beneficiariosString));
		variables.put("«APARTADO_REPETITIVO_BENEFICIA_COLUM1»", creaVariable(porcentajeBeneficiariosString));
		List<RelacionadoBean> autorizados = getRelacionados(cuenta, TipoRelacionPersonaCuenta.AUTORIZADO);
		StringBuffer autorizadosString = new StringBuffer();
		for (RelacionadoBean relacionadoBean : autorizados) {
			if(relacionadoBean.getPersona() != null){
				autorizadosString.append(relacionadoBean.getPersona().getNombreCompleto());
				autorizadosString.append(newLine);
			}
		}
		variables.put("«VARIOS_AUTORIZADOS_COLUM1»", creaVariable(autorizadosString));
	}
	
	private void prepareCliente(ClienteBean cliente) {
		ClienteBean clienteBean = new ClienteBean();
		if(cliente == null){
			cliente = new ClienteBean();
		}else{
			try{
				clienteBean =clienteConsultaPersonaFisica.ejecutarTRN(cliente.getIdInterna());
			}catch(final ControlableException e){
			}
			if(clienteBean == null){
				try{
					clienteBean = clienteConsultaPersonaMoral.ejecutarTRN(cliente.getIdInterna());
				}catch(final ControlableException e){
				}
			}
			if(clienteBean != null){
				cliente = clienteBean;
			}
		}
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
	
	/**
	 * Método para crear una nueva variable de la plantilla
	 * @param valor que se le asignará a la variable
	 * @return Bean VariablePlantillaUtils con atributos de la variable
	 */
	private VariablePlantillaUtils creaVariable(Object valor){
		VariablePlantillaUtils variable = new VariablePlantillaUtils();
		variable.setValor("");
		if(valor != null){
			variable.setValor(valor.toString().trim());
		}
		return variable;
	}
	
	/**
	 * Método para verificar los domicilios de un cliente 
	 * @param cliente Cliente al que se le consultaran los domicilios
	 * @return Bean domicilio 
	 */
	private DomicilioBean getDomicilioCliente(ClienteBean cliente){
		if(cliente.getDomicilios()!= null && !cliente.getDocumentos().isEmpty()){
			return cliente.getDomicilios().get(0);
		}
		return new DomicilioBean();
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
	 * Mètodo para obtener el representante legal de un cliente
	 * @param cliente Cliente a consultar
	 * @return Representante legal del cliente
	 */
	private ClientePersonaFisicaBean getRepresentanteCliente(ClienteBean cliente){
		if(cliente.getRepresentante() != null){
			return (ClientePersonaFisicaBean) cliente.getRepresentante();
		}
		return new ClientePersonaFisicaBean();
	}
	
	/**
	 * Mètodo para obtener la direcciòn en una cadena de caracteres 
	 * con el formato esperado por la plantilla.
	 * @param domicilio Bean con detalles de un domicilio
	 * @return Cadena de caracteres con direcciòn 
	 */
	private String getDireccion(DomicilioBean domicilio){
		String direccion = "";
		if(domicilio.getDireccion() != null){
			direccion = domicilio.getDireccion(); 
		}
		if(domicilio.getNumeroExterior() != null){
			direccion = direccion + " " + domicilio.getNumeroExterior();
		}
		if(domicilio.getColonia() != null){
			direccion = direccion + ", " + domicilio.getColonia();
		}
		return direccion;
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
}