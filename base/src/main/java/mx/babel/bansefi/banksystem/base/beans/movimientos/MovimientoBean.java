package mx.babel.bansefi.banksystem.base.beans.movimientos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.babel.arq.comun.beans.PaginacionBean;
import mx.babel.bansefi.banksystem.base.beans.AuditoriaBean;
import mx.babel.bansefi.banksystem.base.beans.DatosAmpliadosBean;
import mx.babel.bansefi.banksystem.base.beans.LiquidacionBean;
import mx.babel.bansefi.banksystem.base.beans.domain.TipoMovimiento;

/**
 * Clase que define los atributos de un movimiento y/o apunte.
 * 
 * @author mario.montesdeoca
 * 
 */
public class MovimientoBean extends PaginacionBean implements Serializable {

	private static final long serialVersionUID = -7770507503845796787L;

	private TipoMovimiento tipo;
	private String subTipo;
	private String estado;
	private Date fechaAlta;
	private Date fechaVencimiento;
	private Date fechaOperacion;
	private String empleado;
	private String originador;
	private String centro;
	private String oficinaTerminal;
	private Integer numeroBloqueo;
	private String motivoCancelacion;
	private String subTipoDescription;
	private String codigoOperacion;
	private String indicador;

	// Atributos generales de un movimiento.
	private Long numCuenta;
	private String codigoCuenta;
	private Date fechaContable;
	private Date fechaValor;
	private String concepto;
	private String conceptoDetalle;
	private Integer numSec;
	private String signo;
	private BigDecimal importe;
	private BigDecimal saldo;
	private String moneda;
	private String porcentajeAbono;
	private String codigoOrigen;
	private String codigoOrigenApunte;
	private String idOrigenApunte;
	private int conceptoApunteLen;
	private int apunteLiquidacion;
	private BigDecimal saldoFinArrastre;
	private String codLinea;
	private String idGrupoProducto;
	private String productoVendible;

	// Atributos especiales.
	private String indicador1;
	private String indicador2;
	private String indicador3;
	private String indicador4;
	private String indicador5;
	private String indicador6;
	private String indicador7;
	private String indicador8;
	private String indicador9;
	private String indicador10;
	private List<String> indicadores;
	private AuditoriaBean auditoriaBean;
	private List<DatosAmpliadosBean> detalleTipoTranPlanifApunte;
	private List<DatosAmpliadosBean> detalleInfoAdicionalApunte;
	private LiquidacionBean liquidacionBean;

	/**
	 * Constructor.
	 */
	public MovimientoBean() {
		super();
		this.indicadores = new ArrayList<>();
		this.auditoriaBean = new AuditoriaBean();
		this.liquidacionBean = new LiquidacionBean();
		this.detalleTipoTranPlanifApunte = new ArrayList<>();
		this.detalleInfoAdicionalApunte = new ArrayList<>();
		this.apunteLiquidacion = -1;
	}

	/**
	 * @return Atributo tipo
	 */
	public TipoMovimiento getTipo() {
		return tipo;
	}

	/**
	 * @param tipo
	 *            Atributo tipo a definir
	 */
	public void setTipo(final TipoMovimiento tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return Atributo subTipo
	 */
	public String getSubTipo() {
		return subTipo;
	}

	/**
	 * @param subTipo
	 *            Atributo subTipo a definir
	 */
	public void setSubTipo(final String subTipo) {
		this.subTipo = subTipo;
	}

	/**
	 * @return Atributo estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            Atributo estado a definir
	 */
	public void setEstado(final String estado) {
		this.estado = estado;
	}

	/**
	 * @return Atributo fechaAlta
	 */
	public Date getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * @param fechaAlta
	 *            Atributo fechaAlta a definir
	 */
	public void setFechaAlta(final Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	/**
	 * @return Atributo fechaVencimiento
	 */
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * @param fechaVencimiento
	 *            Atributo fechaVencimiento a definir
	 */
	public void setFechaVencimiento(final Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * @return Atributo fechaOperacion
	 */
	public Date getFechaOperacion() {
		return fechaOperacion;
	}

	/**
	 * @param fechaOperacion
	 *            Atributo fechaOperacion a definir
	 */
	public void setFechaOperacion(final Date fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}

	/**
	 * @return Atributo fechaValor
	 */
	public Date getFechaValor() {
		return fechaValor;
	}

	/**
	 * @param fechaValor
	 *            Atributo fechaValor a definir
	 */
	public void setFechaValor(final Date fechaValor) {
		this.fechaValor = fechaValor;
	}

	/**
	 * @return Atributo concepto
	 */
	public String getConcepto() {
		return concepto;
	}

	/**
	 * @param concepto
	 *            Atributo concepto a definir
	 */
	public void setConcepto(final String concepto) {
		this.concepto = concepto;
	}

	/**
	 * @return Atributo conceptoDetalle
	 */
	public String getConceptoDetalle() {
		return conceptoDetalle;
	}

	/**
	 * @param conceptoDetalle
	 *            Atributo conceptoDetalle a definir
	 */
	public void setConceptoDetalle(String conceptoDetalle) {
		this.conceptoDetalle = conceptoDetalle;
	}

	/**
	 * @return Atributo empleado
	 */
	public String getEmpleado() {
		return empleado;
	}

	/**
	 * @param empleado
	 *            Atributo empleado a definir
	 */
	public void setEmpleado(final String empleado) {
		this.empleado = empleado;
	}

	/**
	 * @return Atributo originador
	 */
	public String getOriginador() {
		return originador;
	}

	/**
	 * @param originador
	 *            Atributo originador a definir
	 */
	public void setOriginador(final String originador) {
		this.originador = originador;
	}

	/**
	 * @return Atributo centro
	 */
	public String getCentro() {
		return centro;
	}

	/**
	 * @param centro
	 *            Atributo centro a definir
	 */
	public void setCentro(final String centro) {
		this.centro = centro;
	}

	/**
	 * @return Atributo importe
	 */
	public BigDecimal getImporte() {
		return importe;
	}

	/**
	 * @param importe
	 *            Atributo importe a definir
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	/**
	 * @return Atributo saldo
	 */
	public BigDecimal getSaldo() {
		return saldo;
	}

	/**
	 * @param saldo
	 *            Atributo saldo a definir
	 */
	public void setSaldo(final BigDecimal saldo) {
		this.saldo = saldo;
	}

	/**
	 * @return Atributo oficinaTerminal
	 */
	public String getOficinaTerminal() {
		return oficinaTerminal;
	}

	/**
	 * @param oficinaTerminal
	 *            Atributo oficinaTerminal a definir
	 */
	public void setOficinaTerminal(final String oficinaTerminal) {
		this.oficinaTerminal = oficinaTerminal;
	}

	/**
	 * @return Atributo numeroBloqueo
	 */
	public Integer getNumeroBloqueo() {
		return numeroBloqueo;
	}

	/**
	 * @param numeroBloqueo
	 *            Atributo numeroBloqueo a definir
	 */
	public void setNumeroBloqueo(final Integer numeroBloqueo) {
		this.numeroBloqueo = numeroBloqueo;
	}

	/**
	 * @return Atributo motivoCancelacion
	 */
	public String getMotivoCancelacion() {
		return motivoCancelacion;
	}

	/**
	 * @param motivoCancelacion
	 *            Atributo motivoCancelacion a definir
	 */
	public void setMotivoCancelacion(final String motivoCancelacion) {
		this.motivoCancelacion = motivoCancelacion;
	}

	/**
	 * @return Atributo subTipoDescription
	 */
	public String getSubTipoDescription() {
		return subTipoDescription;
	}

	/**
	 * @param subTipoDescription
	 *            Atributo subTipoDescription a definir
	 */
	public void setSubTipoDescription(final String subTipoDescription) {
		this.subTipoDescription = subTipoDescription;
	}

	/**
	 * @return Atributo codigoOperacion
	 */
	public String getCodigoOperacion() {
		return codigoOperacion;
	}

	/**
	 * @param codigoOperacion
	 *            Atributo codigoOperacion a definir
	 */
	public void setCodigoOperacion(final String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}

	/**
	 * @return Atributo signo
	 */
	public String getSigno() {
		return signo;
	}

	/**
	 * @param signo
	 *            Atributo signo a definir
	 */
	public void setSigno(final String signo) {
		this.signo = signo;
	}

	/**
	 * @return Atributo indicador
	 */
	public String getIndicador() {
		return indicador;
	}

	/**
	 * @param indicador
	 *            Atributo indicador a definir
	 */
	public void setIndicador(final String indicador) {
		this.indicador = indicador;
	}

	/**
	 * @return Atributo codigoCuenta
	 */
	public String getCodigoCuenta() {
		return codigoCuenta;
	}

	/**
	 * @param codigoCuenta
	 *            Atributo codigo cuenta a definir
	 */
	public void setCodigoCuenta(String codigoCuenta) {
		this.codigoCuenta = codigoCuenta;
	}

	/**
	 * @return Atributo moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * @param moneda
	 *            Atributo moneda a definir
	 */
	public void setMoneda(final String moneda) {
		this.moneda = moneda;
	}

	/**
	 * @return Atributo numSec
	 */
	public Integer getNumSec() {
		return numSec;
	}

	/**
	 * @param numSec
	 *            Atributo numSec a definir
	 */
	public void setNumSec(Integer numSec) {
		this.numSec = numSec;
	}

	/**
	 * @return Atributo numCuenta
	 */
	public Long getNumCuenta() {
		return numCuenta;
	}

	/**
	 * @param numCuenta
	 *            Atributo numCuenta a definir
	 */
	public void setNumCuenta(Long numCuenta) {
		this.numCuenta = numCuenta;
	}

	/**
	 * @return Atributo fechaContable
	 */
	public Date getFechaContable() {
		return fechaContable;
	}

	/**
	 * @param fechaContable
	 *            Atributo fechaContable a definir
	 */
	public void setFechaContable(Date fechaContable) {
		this.fechaContable = fechaContable;
	}

	/**
	 * @return Atributo porcentajeAbono
	 */
	public String getPorcentajeAbono() {
		return porcentajeAbono;
	}

	/**
	 * @param porcentajeAbono
	 *            Atributo porcentajeAbono a definir
	 */
	public void setPorcentajeAbono(String porcentajeAbono) {
		this.porcentajeAbono = porcentajeAbono;
	}

	/**
	 * @return Atributo indicador1
	 */
	public String getIndicador1() {
		return indicador1;
	}

	/**
	 * @param indicador1
	 *            Atributo indicador1 a definir
	 */
	public void setIndicador1(String indicador1) {
		this.indicador1 = indicador1;
	}

	/**
	 * @return Atributo indicador2
	 */
	public String getIndicador2() {
		return indicador2;
	}

	/**
	 * @param indicador2
	 *            Atributo indicador2 a definir
	 */
	public void setIndicador2(String indicador2) {
		this.indicador2 = indicador2;
	}

	/**
	 * @return Atributo indicador3
	 */
	public String getIndicador3() {
		return indicador3;
	}

	/**
	 * @param indicador3
	 *            Atributo indicador3 a definir
	 */
	public void setIndicador3(String indicador3) {
		this.indicador3 = indicador3;
	}

	/**
	 * @return Atributo indicador4
	 */
	public String getIndicador4() {
		return indicador4;
	}

	/**
	 * @param indicador4
	 *            Atributo indicador4 a definir
	 */
	public void setIndicador4(String indicador4) {
		this.indicador4 = indicador4;
	}

	/**
	 * @return Atributo indicador5
	 */
	public String getIndicador5() {
		return indicador5;
	}

	/**
	 * @param indicador5
	 *            Atributo indicador5 a definir
	 */
	public void setIndicador5(String indicador5) {
		this.indicador5 = indicador5;
	}

	/**
	 * @return Atributo indicador6
	 */
	public String getIndicador6() {
		return indicador6;
	}

	/**
	 * @param indicador6
	 *            Atributo indicador6 a definir
	 */
	public void setIndicador6(String indicador6) {
		this.indicador6 = indicador6;
	}

	/**
	 * @return Atributo indicador7
	 */
	public String getIndicador7() {
		return indicador7;
	}

	/**
	 * @param indicador7
	 *            Atributo indicador7 a definir
	 */
	public void setIndicador7(String indicador7) {
		this.indicador7 = indicador7;
	}

	/**
	 * @return Atributo indicador8
	 */
	public String getIndicador8() {
		return indicador8;
	}

	/**
	 * @param indicador8
	 *            Atributo indicador8 a definir
	 */
	public void setIndicador8(String indicador8) {
		this.indicador8 = indicador8;
	}

	/**
	 * @return Atributo indicador9
	 */
	public String getIndicador9() {
		return indicador9;
	}

	/**
	 * @param indicador9
	 *            Atributo indicador9 a definir
	 */
	public void setIndicador9(String indicador9) {
		this.indicador9 = indicador9;
	}

	/**
	 * @return Atributo indicador10
	 */
	public String getIndicador10() {
		return indicador10;
	}

	/**
	 * @param indicador10
	 *            Atributo indicador10 a definir
	 */
	public void setIndicador10(String indicador10) {
		this.indicador10 = indicador10;
	}

	/**
	 * @return Atributo indicadores
	 */
	public List<String> getIndicadores() {
		return indicadores;
	}

	/**
	 * @param indicadores
	 *            Atributo indicadores a definir
	 */
	public void setIndicadores(List<String> indicadores) {
		this.indicadores = indicadores;
	}

	/**
	 * @return Atributo auditoriaBean
	 */
	public AuditoriaBean getAuditoriaBean() {
		return auditoriaBean;
	}

	/**
	 * @param auditoriaBean
	 *            Atributo auditoriaBean a definir
	 */
	public void setAuditoriaBean(AuditoriaBean auditoriaBean) {
		this.auditoriaBean = auditoriaBean;
	}

	public List<DatosAmpliadosBean> getDetalleTipoTranPlanifApunte() {
		return detalleTipoTranPlanifApunte;
	}

	public void setDetalleTipoTranPlanifApunte(
			List<DatosAmpliadosBean> detalleTipoTranPlanifApunte) {
		this.detalleTipoTranPlanifApunte = detalleTipoTranPlanifApunte;
	}

	public List<DatosAmpliadosBean> getDetalleInfoAdicionalApunte() {
		return detalleInfoAdicionalApunte;
	}

	public void setDetalleInfoAdicionalApunte(
			List<DatosAmpliadosBean> detalleInfoAdicionalApunte) {
		this.detalleInfoAdicionalApunte = detalleInfoAdicionalApunte;
	}

	/**
	 * @return Atributo liquidacionBean
	 */
	public LiquidacionBean getLiquidacionBean() {
		return liquidacionBean;
	}

	/**
	 * @param liquidacionBean
	 *            Atributo liquidacionBean a definir
	 */
	public void setLiquidacionBean(LiquidacionBean liquidacionBean) {
		this.liquidacionBean = liquidacionBean;
	}

	/**
	 * @return Atributo codigoOrigen
	 */
	public String getCodigoOrigen() {
		return codigoOrigen;
	}

	/**
	 * @param codigoOrigen
	 *            Atributo codigoOrigen a definir
	 */
	public void setCodigoOrigen(String codigoOrigen) {
		this.codigoOrigen = codigoOrigen;
	}

	/**
	 * @return Atributo codigoOrigenApunte
	 */
	public String getCodigoOrigenApunte() {
		return codigoOrigenApunte;
	}

	/**
	 * @param codigoOrigenApunte
	 *            Atributo codigoOrigenApunte a definir
	 */
	public void setCodigoOrigenApunte(String codigoOrigenApunte) {
		this.codigoOrigenApunte = codigoOrigenApunte;
	}

	/**
	 * @return Atributo idOrigenApunte
	 */
	public String getIdOrigenApunte() {
		return idOrigenApunte;
	}

	/**
	 * @param idOrigenApunte
	 *            Atributo idOrigenApunte a definir
	 */
	public void setIdOrigenApunte(String idOrigenApunte) {
		this.idOrigenApunte = idOrigenApunte;
	}

	/**
	 * @return Atributo conceptoApunteLen
	 */
	public int getConceptoApunteLen() {
		return conceptoApunteLen;
	}

	/**
	 * @param conceptoApunteLen
	 *            Atributo conceptoApunteLen a definir
	 */
	public void setConceptoApunteLen(int conceptoApunteLen) {
		this.conceptoApunteLen = conceptoApunteLen;
	}

	/**
	 * @return Atributo apunteLiquidacion
	 */
	public int getApunteLiquidacion() {
		return apunteLiquidacion;
	}

	/**
	 * @param apunteLiquidacion
	 *            Atributo apunteLiquidacion a definir
	 */
	public void setApunteLiquidacion(int apunteLiquidacion) {
		this.apunteLiquidacion = apunteLiquidacion;
	}

	/**
	 * @return the saldoFinArrastre
	 */
	public BigDecimal getSaldoFinArrastre() {
		return saldoFinArrastre;
	}

	/**
	 * @param saldoFinArrastre
	 *            the saldoFinArrastre to set
	 */
	public void setSaldoFinArrastre(BigDecimal saldoFinArrastre) {
		this.saldoFinArrastre = saldoFinArrastre;
	}

	/**
	 * @return the codLinea
	 */
	public String getCodLinea() {
		return codLinea;
	}

	/**
	 * @param codLinea
	 *            the codLinea to set
	 */
	public void setCodLinea(String codLinea) {
		this.codLinea = codLinea;
	}

	/**
	 * @return the idGrupoProducto
	 */
	public String getIdGrupoProducto() {
		return idGrupoProducto;
	}

	/**
	 * @param idGrupoProducto
	 *            the idGrupoProducto to set
	 */
	public void setIdGrupoProducto(String idGrupoProducto) {
		this.idGrupoProducto = idGrupoProducto;
	}

	/**
	 * @return the productoVendible
	 */
	public String getProductoVendible() {
		return productoVendible;
	}

	/**
	 * @param productoVendible
	 *            the productoVendible to set
	 */
	public void setProductoVendible(String productoVendible) {
		this.productoVendible = productoVendible;
	}

}