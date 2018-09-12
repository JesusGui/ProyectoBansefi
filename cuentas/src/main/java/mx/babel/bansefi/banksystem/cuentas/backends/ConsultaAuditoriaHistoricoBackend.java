package mx.babel.bansefi.banksystem.cuentas.backends;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.backends.ConsultaCatalogoBackend;
import mx.babel.bansefi.banksystem.base.beans.AuditoriaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaauditoriahistorico.ConsultaAuditoriaHistoricoServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaauditoriahistorico.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaauditoriahistorico.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaauditoriahistorico.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaAuditoriaHistoricoWrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaAuditoriaHistoricoBackend extends BackEndBean implements
		Serializable {

	private static final long serialVersionUID = -4715868164970843634L;

	private static final Logger LOGGER = LogManager
			.getLogger(ConsultaAuditoriaHistoricoBackend.class.getName());

	@Autowired
	ConsultaAuditoriaHistoricoWrapper wrapper;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	@Autowired
	ConsultaCatalogoBackend consultaCatalogoBackend;

	public AuditoriaBean ejecutarTRN(AuditoriaBean bean){
		AuditoriaBean beanRespuesta = null;
		Ejecutar.ITRCONSUAUDITECVACTR contexto = null;
		Ejecutar.ITRCONSUAUDITECVACTR.TRCONSUAUDITECVACEVT datosConsulta = null;
		Ejecutar.ITRCONSUAUDITECVACTR.STDTRNIPARMV pie = null;
		EjecutarResult respuestaTRN = null;
		ResponseBansefi responseBansefi = null;
		Date fechaContableDelTrn = null;
		Date fechaOperacionDelTrn = null;
		SimpleDateFormat sdf = null;
		
		try {
			contexto = initPeticion();
			datosConsulta = cargaDatosConsulta(bean);
			contexto.setTRCONSUAUDITECVACEVT(datosConsulta);
			pie = cargaParametrosInternos();
			contexto.setSTDTRNIPARMV(pie);
		} catch (Exception e) {
			throw new NoControlableException(
					"Error al cargar los datos para la petición de la TRN. Mensaje de la excepción:"
							+ e.getMessage(), e);
		}

		// Se ejecuta la TRN y se obtiene la respuesta en bruto
		try {
			respuestaTRN = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaAuditoriaHistoricoServicio.class, contexto);
		} catch (Exception e) {
			throw new NoControlableException(
					"Error al ejecutar la TRN. Mensaje de la excepción:"
							+ e.getMessage(), e);
		}

		// Se toma la respuesta en bruto y se convierte en un objeto de la aplicación.
		try {

			if (verificaResponseBansefi(respuestaTRN)) {
				responseBansefi = respuestaTRN.getResponseBansefi();

				if (responseBansefi.getOTRCONSUAUDITECVACTR().getRTRNCD() == BackEndBean.RETURN_COD_OK) {
					beanRespuesta = desempaquetarBeanDeRespuesta(responseBansefi);
				} else {
					LOGGER.debug("Código de retorno:"
							+ responseBansefi.getOTRCONSUAUDITECVACTR()
									.getRTRNCD());
				}
			} else {
				LOGGER.debug("No hay información en la respuesta de la TRN.");
			}

		} catch (Exception e) {
			throw new NoControlableException(
					"Error al obtener la información que proviene de la TRN. Mensaje de la excepción:"
							+ e.getMessage(), e);
		}

		if (beanRespuesta != null) {
			
			sdf = new SimpleDateFormat(ConstantesFuncionales.GENERAL_DATE_FORMATTER);
			//se asigna la terminal
			beanRespuesta.setTerminal(this.getTerminal());
			
			fechaContableDelTrn = this.getFechaSistema();
			//se asigna la fecha contable
			if(fechaContableDelTrn!=null){
				beanRespuesta.setFechaContableStr(sdf.format(fechaContableDelTrn));
			}
			
			//se asigna la fecha de operación
			fechaOperacionDelTrn = beanRespuesta.getFechaOperacion();
			if(fechaOperacionDelTrn!=null){
				beanRespuesta.setFechaOperacionStr(sdf.format(fechaOperacionDelTrn));
			}
			
			//Se asigna la descripción de la entidad.
			try{
				if(beanRespuesta.getEntidad() != null){
					beanRespuesta.setEntidadDesc(this.getDescripcion(
							beanRespuesta.getEntidad(), 
							CatalogoEnum.TP_ENTIDAD_CR));
				}
			}catch(Exception e){
				LOGGER.debug("Exception al obtener la descripción de la entidad ("
						+ beanRespuesta.getEntidad() +"):" + e.getMessage());
			}

			// Se obtiene la descripción transacción
			try {
				if (beanRespuesta.getTransaccion() != null) {
					beanRespuesta.setTransaccionDesc(this.getDescripcion(
							beanRespuesta.getTransaccion(),
							CatalogoEnum.TP_TX));
				}
			} catch (Exception e) {
				LOGGER.debug("Exception al obtener la descripción de la transacción ("
						+ beanRespuesta.getTransaccion() + "):" + e.getMessage());
			}

			// se obtiene descripción de la entidad a partir de un catálogo.
			try {
				if (beanRespuesta.getEntidad() != null) {
					beanRespuesta.setEntidadDesc(this.getDescripcion(
							beanRespuesta.getEntidad(),
							CatalogoEnum.TP_ENTIDAD_CR));
				}
			} catch (Exception e) {
				LOGGER.debug("Exception al obtener la descripción de la entidad ("
						+ beanRespuesta.getEntidad() + "):" + e.getMessage());
			}

		}

		return beanRespuesta;
	}

	private String getDescripcion(String key, CatalogoEnum CAT) {
		String descripcion = null;
		List<CatalogoBean> lstCatalogoBean = null;
		CatalogoBean catalogoBean = null;

		lstCatalogoBean = consultaCatalogoBackend.ejecutarTRN(CAT);
		for (int i = 0; i < lstCatalogoBean.size(); i++) {
			catalogoBean = lstCatalogoBean.get(i);
			if (catalogoBean.getClaveFila().equalsIgnoreCase(key)) {
				descripcion = catalogoBean.getDescripcionL();
				break;
			}
		}

		return descripcion;
	}

	private Ejecutar.ITRCONSUAUDITECVACTR initPeticion() throws Exception {
		Ejecutar.ITRCONSUAUDITECVACTR contexto = null;

		contexto = new Ejecutar.ITRCONSUAUDITECVACTR();
		contexto.setSCROLLABLEOCCURS(BackEndBean.SCROLLABLE_OCCURS);

		return contexto;

	}

	private Ejecutar.ITRCONSUAUDITECVACTR.TRCONSUAUDITECVACEVT cargaDatosConsulta(
			AuditoriaBean bean) throws Exception {
		Ejecutar.ITRCONSUAUDITECVACTR.TRCONSUAUDITECVACEVT datosConsulta = null;
		Ejecutar.ITRCONSUAUDITECVACTR.TRCONSUAUDITECVACEVT.ACECVACP datosEmpaquetados = null;
//		Ejecutar.ITRCONSUAUDITECVACTR.TRCONSUAUDITECVACEVT.ACECVACP.TIPOECVSTDP momento = null;
		Long centroAc = null;

		datosEmpaquetados = new Ejecutar.ITRCONSUAUDITECVACTR.TRCONSUAUDITECVACEVT.ACECVACP();
		datosEmpaquetados.setCODNRBEEN(getEntidad());
		// datosEmpaquetados.setCODCENTUO(bean.getCentroOperativo());
		centroAc = (bean.getCentroAc() != null && !bean.getCentroAc().isEmpty()) ? Long
				.parseLong(bean.getCentroAc()) : 0L;
		datosEmpaquetados.setNUMSECAC(centroAc);

		datosConsulta = new Ejecutar.ITRCONSUAUDITECVACTR.TRCONSUAUDITECVACEVT();
		datosConsulta.setACECVACP(datosEmpaquetados);

		return datosConsulta;
	}

	private Ejecutar.ITRCONSUAUDITECVACTR.STDTRNIPARMV cargaParametrosInternos()
			throws Exception {
		Ejecutar.ITRCONSUAUDITECVACTR.STDTRNIPARMV datosInternos = null;

		datosInternos = new Ejecutar.ITRCONSUAUDITECVACTR.STDTRNIPARMV();
		datosInternos.setIDINTERNOTERMTN(getTerminal());
		datosInternos.setCODTX(CodTxConstants.COD_TX_TR_CONSU_AUDIT_ECV_AC_TRN);

		return datosInternos;

	}

	private Boolean verificaResponseBansefi(EjecutarResult respuesta) {
		boolean traeRespuesta = false;

		traeRespuesta = (respuesta != null && respuesta.getResponseBansefi() != null);

		return traeRespuesta;
	}

	private AuditoriaBean desempaquetarBeanDeRespuesta(
			ResponseBansefi result) {
		return wrapper.wrappConsultaAuditoriaHistorico(result);

	}
}
