package mx.babel.bansefi.banksystem.cuentas.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.ClienteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.CuentaRelacionadaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.RelacionadoBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaCamposDocumentoBackEnd;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaEstadosDocumentoBackEnd;
import mx.babel.bansefi.banksystem.cuentas.backends.HistoricoRelacionCuentaCuentaBackEnd;
import mx.babel.bansefi.banksystem.cuentas.backends.HistoricoRelacionPersonaCuentaBackEnd;
import mx.babel.bansefi.banksystem.cuentas.beans.EmisionDocumentosBean;
import mx.babel.bansefi.banksystem.cuentas.beans.HistoricoRelacionadoBean;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Controlador encargado de gestionar la vistas de historicos de relaciones de cuentas y
 * emisiòn de documentos.
 * 
 * @author mario.montesdeoca
 * 
 */
@ManagedBean(name = "historicoCuentaController")
@Component
@Scope("view")
public class HistoricoCuentaController implements Serializable{

	private static final long serialVersionUID = 8035361508335570719L;
	
	@Autowired
	ContextoUtils contextoUtils;
	@Autowired
	ManagedBeanStateRecover managedBeanStateRecover;
	@Autowired
	CatalogoUtils catalogoUtils;
	
	@Autowired
	ConsultaCamposDocumentoBackEnd consultaCamposDocumentoBackEnd;
	
	@Autowired
	HistoricoRelacionPersonaCuentaBackEnd historicoRelacionPersonaCuentaBackEnd;
	
	@Autowired
	HistoricoRelacionCuentaCuentaBackEnd historicoRelacionCuentaCuentaBackEnd;
	
	@Autowired
	ConsultaEstadosDocumentoBackEnd consultaEstadosDocumentoBackEnd;
	
	/**
	 * Cliente titular de la cuenta que se está dando de alta
	 */
	private ClienteBean clienteBean;
	/**
	 * Cuenta a la cual se le asignarán nuevas relaciones
	 */
	private CuentaBean cuentaBean;
	/**
	 * Bean de relacionado para historico
	 */
	private RelacionadoBean relacionado;
	/**
	 * Bean de cuenta relacionada para historico
	 */
	private CuentaRelacionadaBean relacionada;
	/**
	 * Lista con historico de la relación
	 */
	private List<HistoricoRelacionadoBean> historicoRelacion;
	/**
	 * Bean de documento para hitorico
	 */
	private EmisionDocumentosBean historicoDocumento;
	/**
	 * Lista con historial de docuemnto
	 */
	private List<EmisionDocumentosBean> historico;
	
	/**
	 * Enum para definir a donde se redireccionará al seleccionar un objeto de
	 * la tabla de resultados.
	 */
	private NavegacionEnum destino;
	private Object destinoController;
	
	@PostConstruct
	public void init() {
		if(this.obtieneFlash().get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash())!=null){
			if ((Boolean) this.obtieneFlash().get(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash())) {
				destino = managedBeanStateRecover.getDestino();
				destinoController = managedBeanStateRecover.getController();
				initializeData();
			}else{
				managedBeanStateRecover.recuperaController(this);
			}
		}else{
			initializeData();
		}		
	}
	
	public void initializeData(){
		if (this.obtieneFlash().get(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash()) != null) {
			this.cuentaBean = (CuentaBean) this.obtieneFlash().get(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash());
		} 
		if (this.obtieneFlash().get(ParametrosFlashEnum.CLIENTE.getParamFlash()) != null) {
			this.clienteBean = (ClienteBean) this.obtieneFlash().get(ParametrosFlashEnum.CLIENTE.getParamFlash());
		} 	
		if(this.obtieneFlash().get(ParametrosFlashEnum.HISTORICO_PERSONA_RELACIONADA.getParamFlash()) != null){
			this.relacionado = (RelacionadoBean) this.obtieneFlash().get(ParametrosFlashEnum.HISTORICO_PERSONA_RELACIONADA.getParamFlash());
			initHistoricoRelacionado();
		}
		if(this.obtieneFlash().get(ParametrosFlashEnum.HISTORICO_CUENTA_RELACIONADA.getParamFlash()) != null){
			this.relacionada = (CuentaRelacionadaBean) this.obtieneFlash().get(ParametrosFlashEnum.HISTORICO_CUENTA_RELACIONADA.getParamFlash());
			initHistoricoRelacionado();
		}
		if (this.obtieneFlash().get(ParametrosFlashEnum.HISTORICO_DOCUMENTO.getParamFlash()) != null) {
			this.historicoDocumento = (EmisionDocumentosBean) this.obtieneFlash().get(ParametrosFlashEnum.HISTORICO_DOCUMENTO.getParamFlash());
			initHistorico();
		}
	}
	
	public void initHistoricoRelacionado(){
		if(this.relacionada != null){
			this.historicoRelacion = historicoRelacionCuentaCuentaBackEnd.
					ejecutarTRN(relacionada,  cuentaBean.getNumeroCuenta());
		}
		if(this.relacionado != null){
			this.historicoRelacion = historicoRelacionPersonaCuentaBackEnd.
					ejecutarTRN(relacionado, cuentaBean.getNumeroCuenta());
		}
	}		
	
	/**
	 * Método para inicilizar el listado de documentos y estados de un tipo para
	 * el histórico.
	 */
	public void initHistorico() {
		this.historico = new ArrayList<EmisionDocumentosBean>();
		this.historico.add(this.historicoDocumento);
		this.historico.addAll(this.historicoDocumento.getHistorico());
		if(this.historicoDocumento.getEstados() == null || this.historicoDocumento.getEstados().isEmpty()){
			this.historicoDocumento.setEstados(consultaEstadosDocumentoBackEnd
					.ejecutarTRN(this.historicoDocumento.getIdInterno()));
		}
	}
	
	/**
	 * Método para obtener la ruta a la vista de relaciones de cuenta con
	 * documentos, enviando el bean de cuenta.
	 * 
	 * @return ruta de la vista de relaciones de cuenta-documentos.
	 */
	public String irAHome() {
		String ruta = "";
		if (this.destino == null) {
			ruta =NavegacionEnum.INICIO.getRuta();
		}else{
			ruta = destino.getRuta();
			managedBeanStateRecover.finNavegacion(destinoController);
		}
		return ruta;
	}
	
	/**
	 * Mètodo para obtener la cadena de caracteres que define al estado del
	 * documento.
	 * 
	 * @param estado
	 *            cadena de caracteres con el estado del docuemnto
	 * @return nombre del estado
	 */
	public String getEstadoDocumento(final String estado) {
		if ("A".equals(estado)) {
			return "Anulado";
		}
		if ("E".equals(estado)) {
			return "Emitido";
		}
		if ("F".equals(estado)) {
			return "Formalizado";
		}
		return "";
	}
	
	/**
	 * Método para mostrar o colapsar el panel de campos del documento
	 * 
	 * @param documento
	 */
	public void muestraCamposDocumento(final EmisionDocumentosBean documento) {
		if (documento != null) {
			if (documento.getCampos() == null) {
				initCamposDocumentos(documento);
			}
			if (documento.getMuestraCampos()) {
				documento.setMuestraCampos(false);
			} else {
				documento.setMuestraCampos(true);
			}
		}
	}
	
	/**
	 * Mètodo que consulta los campos asociados a un documento
	 * 
	 * @param documento
	 *            bean con detalles del documento consultado
	 */
	public void initCamposDocumentos(final EmisionDocumentosBean documento) {
		documento.setMuestraCampos(false);
		if (documento.getIdInterno() != null) {
			documento.setCampos(consultaCamposDocumentoBackEnd
					.ejecutarTRN(documento.getIdInterno()));
		}
	}
	
	public String getTipoRelacion(){
		try{
			if(!StringUtils.isEmpty(relacionada.getTipoRelacion())){
			return catalogoUtils.getCatalogoBean(CatalogoEnum.TP_RL_AC_AC, relacionada.getTipoRelacion()).getDescripcionC();
			}
		}catch(NoControlableException | ControlableException e){
		}
		return "N/A";
	}
	
	public String getRazonCambio(){
		try{
			if(!StringUtils.isEmpty(relacionada.getRazonCambio())){
				return catalogoUtils.getCatalogoBean(CatalogoEnum.TP_RZN_ECV_AC_AC, relacionada.getRazonCambio()).getDescripcionC();
			}
		}catch(NoControlableException | ControlableException e){
		}
		return "N/A";
	}
	
	public String getRazonCambioPersona(){
		try{
			if(!StringUtils.isEmpty(relacionado.getRazonCambio())){
				return catalogoUtils.getCatalogoBean(CatalogoEnum.TP_RZN_ECV_RP, relacionado.getRazonCambio()).getDescripcionC();
			}
		}catch(NoControlableException | ControlableException e){
		}
		return "N/A";
	}
	
	public String getEstado(HistoricoRelacionadoBean relacionado){
		if(this.relacionada != null){
			return catalogoUtils.getCatalogoBean(CatalogoEnum.TP_ECV_AC_AC, relacionado.getEstado()).getDescripcionC();
		}if(this.relacionado != null){
			return catalogoUtils.getCatalogoBean(CatalogoEnum.TP_ECV_PERS_AC, relacionado.getEstado()).getDescripcionC();
		}
		return "";
	}
	
	public int getTipoVista(){
		int tipoRelacion = 0;
		if(ConstantesFuncionales.REL_AC_AC_ABONO_INTERES.equals(this.relacionada.getTipoRelacion()) ||
				ConstantesFuncionales.REL_AC_AC_CARGO_INTERES.equals(this.relacionada.getTipoRelacion())){
			tipoRelacion = 1;
		}
		if(ConstantesFuncionales.REL_AC_AC_GARANTIA.equals(this.relacionada.getTipoRelacion())){
			tipoRelacion = 2;
		}
		return tipoRelacion;
	}
		
	/**
	 * Se encarga de obtener el flash.
	 * @return Flash con los datos de la pagina
	 */
	public Flash obtieneFlash() {
		return FacesContext.getCurrentInstance().getExternalContext().getFlash();
	}

	/**
	 * @return Atributo clienteBean
	 */
	public ClienteBean getClienteBean() {
		return clienteBean;
	}

	/**
	 * @param clienteBean Atributo clienteBean a definir
	 */
	public void setClienteBean(ClienteBean clienteBean) {
		this.clienteBean = clienteBean;
	}

	/**
	 * @return Atributo cuentaBean
	 */
	public CuentaBean getCuentaBean() {
		return cuentaBean;
	}

	/**
	 * @param cuentaBean Atributo cuentaBean a definir
	 */
	public void setCuentaBean(CuentaBean cuentaBean) {
		this.cuentaBean = cuentaBean;
	}

	/**
	 * @return Atributo relacionado
	 */
	public RelacionadoBean getRelacionado() {
		return relacionado;
	}

	/**
	 * @param relacionado Atributo relacionado a definir
	 */
	public void setRelacionado(RelacionadoBean relacionado) {
		this.relacionado = relacionado;
	}

	/**
	 * @return Atributo relacionada
	 */
	public CuentaRelacionadaBean getRelacionada() {
		return relacionada;
	}

	/**
	 * @param relacionada Atributo relacionada a definir
	 */
	public void setRelacionada(CuentaRelacionadaBean relacionada) {
		this.relacionada = relacionada;
	}

	/**
	 * @return Atributo historicoRelacion
	 */
	public List<HistoricoRelacionadoBean> getHistoricoRelacion() {
		return historicoRelacion;
	}

	/**
	 * @param historicoRelacion Atributo historicoRelacion a definir
	 */
	public void setHistoricoRelacion(
			List<HistoricoRelacionadoBean> historicoRelacion) {
		this.historicoRelacion = historicoRelacion;
	}

	/**
	 * @return Atributo historicoDocumento
	 */
	public EmisionDocumentosBean getHistoricoDocumento() {
		return historicoDocumento;
	}

	/**
	 * @param historicoDocumento Atributo historicoDocumento a definir
	 */
	public void setHistoricoDocumento(EmisionDocumentosBean historicoDocumento) {
		this.historicoDocumento = historicoDocumento;
	}

	/**
	 * @return Atributo historico
	 */
	public List<EmisionDocumentosBean> getHistorico() {
		return historico;
	}

	/**
	 * @param historico Atributo historico a definir
	 */
	public void setHistorico(List<EmisionDocumentosBean> historico) {
		this.historico = historico;
	}

	/**
	 * @return Atributo destino
	 */
	public NavegacionEnum getDestino() {
		return destino;
	}

	/**
	 * @param destino Atributo destino a definir
	 */
	public void setDestino(NavegacionEnum destino) {
		this.destino = destino;
	}

	/**
	 * @return Atributo destinoController
	 */
	public Object getDestinoController() {
		return destinoController;
	}

	/**
	 * @param destinoController Atributo destinoController a definir
	 */
	public void setDestinoController(Object destinoController) {
		this.destinoController = destinoController;
	}
	
}
