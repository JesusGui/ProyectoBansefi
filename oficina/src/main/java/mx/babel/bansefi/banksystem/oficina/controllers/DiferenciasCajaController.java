package mx.babel.bansefi.banksystem.oficina.controllers;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.oficina.beans.DiferenciaCajaBean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@ManagedBean(name = "diferenciasCajaController")
@ViewScoped
@Component
@Scope("view")
public class DiferenciasCajaController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LogManager
			.getLogger(DiferenciasCajaController.class);

	@Autowired
	private ContextoUtils contextoUtils;
	
	private String numeroCentro;
	private Date fechaSistema;
	private Date fechaDesde;
	private Date fechaHasta;
	private String destino;
	private List<DiferenciaCajaBean> lstDiferencias;

	@PostConstruct
	public void init() {
		LOGGER.debug("Inicializando vista...");
		this.numeroCentro = contextoUtils.getTerminal().substring(2, 6);
		
		iniciarCamposBuscador();
	}
	
	public String inicio(){
		return NavegacionEnum.DIFERENCIAS_CAJA.getRuta();
	}
	
	private void iniciarCamposBuscador(){
		this.fechaSistema = Calendar.getInstance().getTime();
		this.destino = "3";
	}

	public void buscar() {
		LOGGER.debug("Buscando diferencias, obtiene resultados y reinicia el buscador.");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		lstDiferencias = new ArrayList<DiferenciaCajaBean>();
		DiferenciaCajaBean bean = null;
		
		bean = new DiferenciaCajaBean();
		bean.setFecha(sdf.format(Calendar.getInstance().getTime()));
		bean.setDestino("destino 1");
		bean.setSaldoContabilidad("5000.00");
		bean.setArqueo("5000.00");
		bean.setDiferencias("5000.00");

		lstDiferencias.add(bean);		
		
		iniciarCamposBuscador();
	}
	
	public String regresar(){
		return NavegacionEnum.INICIO.getRuta();
	}
	
	public String cancelar(){
		return null;
	}
	
	/**
	 * Método que devuelve un indicador booleano para determinar si la vigencia
	 * del mensaje es válida. Este método también es accedido desde la vista:
	 * 'altaMensajes.xhtml'.
	 */
	public boolean verificarFechas() {
		if (fechaHasta.equals(fechaDesde) || fechaHasta.after(fechaDesde)) {
			return true;
		} else {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							"messages",
							new FacesMessage(
									FacesMessage.SEVERITY_WARN,
									"¡Atención!",
									"La fecha de inicio no puede ser menor a la de término. Por favor, modifique la vigencia del mensaje."));
			return false;
		}
	}

	

	public String getNumeroCentro() {
		return numeroCentro;
	}

	public void setNumeroCentro(String numeroCentro) {
		this.numeroCentro = numeroCentro;
	}

	public Date getFechaSistema() {
		return fechaSistema;
	}

	public void setFechaSistema(Date fechaSistema) {
		this.fechaSistema = fechaSistema;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public List<DiferenciaCajaBean> getLstDiferencias() {
		return lstDiferencias;
	}

	public void setLstDiferencias(List<DiferenciaCajaBean> lstDiferencias) {
		this.lstDiferencias = lstDiferencias;
	}

	
}
