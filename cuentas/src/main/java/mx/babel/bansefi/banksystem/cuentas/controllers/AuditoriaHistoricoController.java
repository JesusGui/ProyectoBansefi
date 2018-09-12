package mx.babel.bansefi.banksystem.cuentas.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.validator.ValidatorException;

import mx.babel.bansefi.banksystem.base.beans.AuditoriaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaHistoricoCuentaBackend;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@ManagedBean
@ViewScoped
@Component
@Scope("view")
public class AuditoriaHistoricoController implements Serializable {

	private static final long serialVersionUID = 6963441142620299370L;

	Flash flash = null;

	private List<AuditoriaBean> lstHistorico = null;
	private CuentaBean cuentaBean;

	// @Autowired
	// ConsultaAuditoriaHistoricoBackend backend;

	@Autowired
	ConsultaHistoricoCuentaBackend backend;

	@Autowired
	CatalogoUtils catalogoUtils;

	private static final Logger LOGGER = LogManager
			.getLogger(AuditoriaHistoricoController.class);

	@PostConstruct
	public void init() {
		AuditoriaBean bean = null;
		FacesMessage msg = null;
		final String FORMAT_MSG_SUMMARY = "Datos insuficientes";
		final String FORMAT_MSG_DETAIL = "No se cargó el número de cuenta desde la pantalla anterior.";

		flash = FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();

		// carga los datos dela consulta de auditoría e histórico
		bean = new AuditoriaBean();

		if (obtieneFlash().get(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash()) != null) {
			this.cuentaBean = (CuentaBean) obtieneFlash().get(
					ParametrosFlashEnum.CUENTA_BEAN.getParamFlash());
		}
		// else {
		// LOGGER.error("No se cargó el bean de cuenta en la pantalla anterior.");
		// msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		// FORMAT_MSG_SUMMARY, FORMAT_MSG_DETAIL);
		// throw new ValidatorException(msg);
		// }

		// bean.setCentroAc(this.cuentaBean.getNumeroCuenta().toString());
		if (this.cuentaBean != null
				&& this.cuentaBean.getNumeroCuenta() != null) {
			bean.setCuenta(this.cuentaBean.getNumeroCuenta().toString());
			lstHistorico = backend.ejecutarTRN(bean);
			LOGGER.debug("regresando resultados. Registros:"
					+ ((lstHistorico != null) ? lstHistorico.size() : 0));
		}
	}

	private Flash obtieneFlash() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
	}

	public String regresar() {
		obtieneFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),
				this.cuentaBean);
		return NavegacionEnum.FICHA_CUENTA.getRuta();
	}

	/*
	 * public void getCatalogos() { if (lstHistorico != null &&
	 * !lstHistorico.isEmpty()) { for (AuditoriaBean historico : lstHistorico) {
	 * // Get catalogo situacion try { CatalogoBean catalogo =
	 * catalogoUtils.getCatalogoBean( CatalogoEnum.TP_ECV_AC,
	 * historico.getIdSituacion() + "");
	 * 
	 * historico.setSituacion(catalogo.getDescripcionC());
	 * 
	 * } catch (NullPointerException | ControlableException e) {
	 * LOGGER.debug("Error al consultar el catalogo de tipo de transaccion");
	 * LOGGER.debug(e); } // Get catalogo tipo de transaccion try { CatalogoBean
	 * catalogo = catalogoUtils.getCatalogoBean( CatalogoEnum.TP_TX,
	 * historico.getTransaccion());
	 * 
	 * historico.setTransaccionDesc(catalogo.getDescripcionC());
	 * 
	 * } catch (NullPointerException | ControlableException e) {
	 * LOGGER.debug("Error al consultar el catalogo de tipo de transaccion");
	 * LOGGER.debug(e); }
	 * 
	 * // Get catalogo tipo de entidad try { CatalogoBean catalogo =
	 * catalogoUtils.getCatalogoBean( CatalogoEnum.TP_ENTIDAD_CR,
	 * historico.getEntidad());
	 * 
	 * historico.setEntidadDesc(catalogo.getDescripcionC());
	 * 
	 * } catch (NullPointerException | ControlableException e) {
	 * LOGGER.debug("Error al consultar el catalogo de tipo de transaccion");
	 * LOGGER.debug(e); }
	 * 
	 * // Get catalogo origen de empleado // try { // CatalogoBean catalogo =
	 * catalogoUtils.getCatalogoBean( // CatalogoEnum.TP_ENTIDAD_CR,
	 * historico.getEntidad()); // //
	 * historico.setEntidadDesc(catalogo.getDescripcionC()); // // } catch
	 * (NullPointerException | ControlableException e) { //
	 * LOGGER.debug("Error al consultar el catalogo de tipo de transaccion"); //
	 * LOGGER.debug(e); // }
	 * 
	 * // Get catalogo origen de empleado try { CatalogoBean catalogo =
	 * catalogoUtils.getCatalogoBean( CatalogoEnum.TP_ENTIDAD_CR,
	 * historico.getEntidad());
	 * 
	 * historico.setEntidadDesc(catalogo.getDescripcionC());
	 * 
	 * } catch (NullPointerException | ControlableException e) {
	 * LOGGER.debug("Error al consultar el catalogo de tipo de transaccion");
	 * LOGGER.debug(e); } } } }
	 */

	public List<AuditoriaBean> getLstHistorico() {
		return lstHistorico;
	}

	public void setLstHistorico(List<AuditoriaBean> lstHistorico) {
		this.lstHistorico = lstHistorico;
	}

	public CuentaBean getCuentaBean() {
		return cuentaBean;
	}

	public void setCuentaBean(CuentaBean cuentaBean) {
		this.cuentaBean = cuentaBean;
	}

}