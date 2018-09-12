package mx.babel.bansefi.banksystem.base.beans;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.babel.arq.comun.beans.PaginacionBean;
import mx.babel.bansefi.banksystem.base.beans.movimientos.MovimientoBean;

/**
 * @author gerard.chavez
 * 
 */
public class LiquidacionBean extends PaginacionBean {

	private static final long serialVersionUID = -1333732674490956330L;

	private String tipoLiqFiltro;
	private Date fechaInicioFiltro;
	private Date fechaFinFiltro;
	private boolean incluirAnuladosFiltro;
	private boolean incluirMovsAnuladosFiltro;
	private List<LiquidacionBean> listaResultadosLiquidaciones;
	private int paginaVista;

	private Date fechaLiquidacion;
	private String tipoLiquidacion;
	private BigDecimal netoOperacion;
	private String signo;
	private String aj;
	private String situacion;
	private BigDecimal importePendiente;
	private Date periodoDesdeLiquidacion;
	private Date periodoHastaLiquidacion;
	private String idLiquidacion;
	private String codLinea;
	private String idGrpPd;

	private String modalidadLiquidacion;
	private String tipoComunicado;
	private String tipoBloqueo;
	private Date fechaUltimoMov;
	private Date fechaUltimoCalc;
	private boolean imputacionParcial;
	private String codOperacionLiq;
	private String indCobroParcial;
	private String indDevengo;
	private String indCompDias;
	private String indBaseCalc;
	private String indSitComercial;
	private String numSubAcuerdo;

	private boolean mostrarConsNumsLiquidacion;
	private boolean mostrarConsNumsDemora;

	private List<LiquidacionConceptoBean> listaConceptos;
	private LiquidacionConceptoBean conceptoSeleccionado;
	private List<LiquidacionInformacionBean> listaInformacion;
	private List<MovimientoBean> listaMovimientos;
	private List<LiquidacionSaldoBean> listaSaldos;
	private AuditoriaBean auditoriaBean;
	private LiquidacionNumerosBean numerosBean;
	private LiquidacionNumerosBean numerosDemoraBean;

	private int numsec;

	private List<LiquidacionConceptoDemoraBean> listaConceptosDemora;

	public Date getFechaLiquidacion() {
		return fechaLiquidacion;
	}

	public void setFechaLiquidacion(Date fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
	}

	public String getTipoLiquidacion() {
		return tipoLiquidacion;
	}

	public void setTipoLiquidacion(String tipoLiquidacion) {
		this.tipoLiquidacion = tipoLiquidacion;
	}

	public BigDecimal getNetoOperacion() {
		return netoOperacion;
	}

	public void setNetoOperacion(BigDecimal netoOperacion) {
		this.netoOperacion = netoOperacion;
	}

	public String getSigno() {
		return signo;
	}

	public void setSigno(String signo) {
		this.signo = signo;
	}

	public String getAj() {
		return aj;
	}

	public void setAj(String aj) {
		this.aj = aj;
	}

	public String getSituacion() {
		return situacion;
	}

	public void setSituacion(String situacion) {
		this.situacion = situacion;
	}

	public BigDecimal getImportePendiente() {
		return importePendiente;
	}

	public void setImportePendiente(BigDecimal importePendiente) {
		this.importePendiente = importePendiente;
	}

	public Date getPeriodoDesdeLiquidacion() {
		return periodoDesdeLiquidacion;
	}

	public void setPeriodoDesdeLiquidacion(Date periodoDesdeLiquidacion) {
		this.periodoDesdeLiquidacion = periodoDesdeLiquidacion;
	}

	public Date getPeriodoHastaLiquidacion() {
		return periodoHastaLiquidacion;
	}

	public void setPeriodoHastaLiquidacion(Date periodoHastaLiquidacion) {
		this.periodoHastaLiquidacion = periodoHastaLiquidacion;
	}

	public String getIdLiquidacion() {
		return idLiquidacion;
	}

	public void setIdLiquidacion(String idLiquidacion) {
		this.idLiquidacion = idLiquidacion;
	}

	public String getCodLinea() {
		return codLinea;
	}

	public void setCodLinea(String codLinea) {
		this.codLinea = codLinea;
	}

	public String getIdGrpPd() {
		return idGrpPd;
	}

	public void setIdGrpPd(String idGrpPd) {
		this.idGrpPd = idGrpPd;
	}

	public List<LiquidacionConceptoBean> getListaConceptos() {
		return listaConceptos;
	}

	public void setListaConceptos(List<LiquidacionConceptoBean> listaConceptos) {
		this.listaConceptos = listaConceptos;
	}

	public LiquidacionConceptoBean getConceptoSeleccionado() {
		return conceptoSeleccionado;
	}

	public void setConceptoSeleccionado(
			LiquidacionConceptoBean conceptoSeleccionado) {
		this.conceptoSeleccionado = conceptoSeleccionado;
	}

	public List<LiquidacionInformacionBean> getListaInformacion() {
		return listaInformacion;
	}

	public void setListaInformacion(
			List<LiquidacionInformacionBean> listaInformacion) {
		this.listaInformacion = listaInformacion;
	}

	public String getModalidadLiquidacion() {
		return modalidadLiquidacion;
	}

	public void setModalidadLiquidacion(String modalidadLiquidacion) {
		this.modalidadLiquidacion = modalidadLiquidacion;
	}

	public String getTipoComunicado() {
		return tipoComunicado;
	}

	public void setTipoComunicado(String tipoComunicado) {
		this.tipoComunicado = tipoComunicado;
	}

	public String getTipoBloqueo() {
		return tipoBloqueo;
	}

	public void setTipoBloqueo(String tipoBloqueo) {
		this.tipoBloqueo = tipoBloqueo;
	}

	public Date getFechaUltimoMov() {
		return fechaUltimoMov;
	}

	public void setFechaUltimoMov(Date fechaUltimoMov) {
		this.fechaUltimoMov = fechaUltimoMov;
	}

	public Date getFechaUltimoCalc() {
		return fechaUltimoCalc;
	}

	public void setFechaUltimoCalc(Date fechaUltimoCalc) {
		this.fechaUltimoCalc = fechaUltimoCalc;
	}

	public boolean isImputacionParcial() {
		return imputacionParcial;
	}

	public void setImputacionParcial(boolean imputacionParcial) {
		this.imputacionParcial = imputacionParcial;
	}

	public List<MovimientoBean> getListaMovimientos() {
		return listaMovimientos;
	}

	public void setListaMovimientos(List<MovimientoBean> listaMovimientos) {
		this.listaMovimientos = listaMovimientos;
	}

	public AuditoriaBean getAuditoriaBean() {
		return auditoriaBean;
	}

	public void setAuditoriaBean(AuditoriaBean auditoriaBean) {
		this.auditoriaBean = auditoriaBean;
	}

	public List<LiquidacionSaldoBean> getListaSaldos() {
		return listaSaldos;
	}

	public void setListaSaldos(List<LiquidacionSaldoBean> listaSaldos) {
		this.listaSaldos = listaSaldos;
	}

	public LiquidacionNumerosBean getNumerosBean() {
		return numerosBean;
	}

	public void setNumerosBean(LiquidacionNumerosBean numerosBean) {
		this.numerosBean = numerosBean;
	}

	public LiquidacionNumerosBean getNumerosDemoraBean() {
		return numerosDemoraBean;
	}

	public void setNumerosDemoraBean(LiquidacionNumerosBean numerosDemoraBean) {
		this.numerosDemoraBean = numerosDemoraBean;
	}

	public String getTipoLiqFiltro() {
		return tipoLiqFiltro;
	}

	public void setTipoLiqFiltro(String tipoLiqFiltro) {
		this.tipoLiqFiltro = tipoLiqFiltro;
	}

	public Date getFechaInicioFiltro() {
		return fechaInicioFiltro;
	}

	public void setFechaInicioFiltro(Date fechaInicioFiltro) {
		this.fechaInicioFiltro = fechaInicioFiltro;
	}

	public Date getFechaFinFiltro() {
		return fechaFinFiltro;
	}

	public void setFechaFinFiltro(Date fechaFinFiltro) {
		this.fechaFinFiltro = fechaFinFiltro;
	}

	public boolean isIncluirAnuladosFiltro() {
		return incluirAnuladosFiltro;
	}

	public void setIncluirAnuladosFiltro(boolean incluirAnuladosFiltro) {
		this.incluirAnuladosFiltro = incluirAnuladosFiltro;
	}

	public boolean isIncluirMovsAnuladosFiltro() {
		return incluirMovsAnuladosFiltro;
	}

	public void setIncluirMovsAnuladosFiltro(boolean incluirMovsAnuladosFiltro) {
		this.incluirMovsAnuladosFiltro = incluirMovsAnuladosFiltro;
	}

	public List<LiquidacionBean> getListaResultadosLiquidaciones() {
		return listaResultadosLiquidaciones;
	}

	public void setListaResultadosLiquidaciones(
			List<LiquidacionBean> listaResultadosLiquidaciones) {
		this.listaResultadosLiquidaciones = listaResultadosLiquidaciones;
	}

	public String getCodOperacionLiq() {
		return codOperacionLiq;
	}

	public void setCodOperacionLiq(String codOperacionLiq) {
		this.codOperacionLiq = codOperacionLiq;
	}

	public String getIndCobroParcial() {
		return indCobroParcial;
	}

	public void setIndCobroParcial(String indCobroParcial) {
		this.indCobroParcial = indCobroParcial;
	}

	public String getIndDevengo() {
		return indDevengo;
	}

	public void setIndDevengo(String indDevengo) {
		this.indDevengo = indDevengo;
	}

	public String getIndCompDias() {
		return indCompDias;
	}

	public void setIndCompDias(String indCompDias) {
		this.indCompDias = indCompDias;
	}

	public String getIndBaseCalc() {
		return indBaseCalc;
	}

	public void setIndBaseCalc(String indBaseCalc) {
		this.indBaseCalc = indBaseCalc;
	}

	public boolean isMostrarConsNumsLiquidacion() {
		return mostrarConsNumsLiquidacion;
	}

	public void setMostrarConsNumsLiquidacion(boolean mostrarConsNumsLiquidacion) {
		this.mostrarConsNumsLiquidacion = mostrarConsNumsLiquidacion;
	}

	public boolean isMostrarConsNumsDemora() {
		return mostrarConsNumsDemora;
	}

	public void setMostrarConsNumsDemora(boolean mostrarConsNumsDemora) {
		this.mostrarConsNumsDemora = mostrarConsNumsDemora;
	}

	public int getPaginaVista() {
		return paginaVista;
	}

	public void setPaginaVista(int paginaVista) {
		this.paginaVista = paginaVista;
	}

	public String getIndSitComercial() {
		return indSitComercial;
	}

	public void setIndSitComercial(String indSitComercial) {
		this.indSitComercial = indSitComercial;
	}

	public String getNumSubAcuerdo() {
		return numSubAcuerdo;
	}

	public void setNumSubAcuerdo(String numSubAcuerdo) {
		this.numSubAcuerdo = numSubAcuerdo;
	}

	public int getNumsec() {
		return numsec;
	}

	public void setNumsec(int numsec) {
		this.numsec = numsec;
	}

	public List<LiquidacionConceptoDemoraBean> getListaConceptosDemora() {
		return listaConceptosDemora;
	}

	public void setListaConceptosDemora(
			List<LiquidacionConceptoDemoraBean> listaConceptosDemora) {
		this.listaConceptosDemora = listaConceptosDemora;
	}

}