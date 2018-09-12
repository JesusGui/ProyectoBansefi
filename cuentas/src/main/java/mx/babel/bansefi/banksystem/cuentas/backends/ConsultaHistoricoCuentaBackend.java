package mx.babel.bansefi.banksystem.cuentas.backends;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.backends.ConsultaCatalogoBackend;
import mx.babel.bansefi.banksystem.base.beans.AuditoriaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoCentrosLoaderTask;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultahistoricocuenta.ConsultaHistoricoCuentaServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultahistoricocuenta.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultahistoricocuenta.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultahistoricocuenta.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaHistoricoCuentaWrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaHistoricoCuentaBackend extends BackEndBean implements
Serializable {

	private static final long serialVersionUID = 6745893077591016847L;
	
	private static final Logger LOGGER = LogManager.getLogger(ConsultaHistoricoCuentaBackend.class);

	@Autowired
	private ServicioWebUtils servicioWebUtils;

	@Autowired
	private ConsultaCatalogoBackend consultaCatalogoBackend;
	
	@Autowired
	private CargarAuditoriaBackend cargarAuditoriaBackend;
	
	@Autowired
    private CatalogoCentrosLoaderTask catalogoCentrosLoaderTask;
	
	@Autowired
	private ConsultaHistoricoCuentaWrapper wrapper;
	
	@Autowired
	private CatalogoUtils catalogoUtils;
	
	public List<AuditoriaBean> ejecutarTRN(AuditoriaBean bean) throws ControlableException, NoControlableException{
		
		List<AuditoriaBean> lstBeanRespuesta = null;
		Ejecutar.ITRCONSUESTADOSACTRN contexto = null;
		Ejecutar.ITRCONSUESTADOSACTRN.TRCONSUESTADOSACEVTY datosConsulta = null;
		Ejecutar.ITRCONSUESTADOSACTRN.STDTRNIPARMV pie = null;
		EjecutarResult respuestaTRN = null;
		ResponseBansefi responseBansefi = null;
		
		contexto = new Ejecutar.ITRCONSUESTADOSACTRN();
		
		contexto.setELEVATORPOSITION(0);
		contexto.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);
		
		datosConsulta = this.cargaDatosConsulta(bean);
		contexto.setTRCONSUESTADOSACEVTY(datosConsulta);
		
		pie = this.cargarPie();
		contexto.setSTDTRNIPARMV(pie);
		
		try {
			respuestaTRN = (EjecutarResult)servicioWebUtils.ejecutarWS(ConsultaHistoricoCuentaServicio.class, contexto);
		} catch (Exception e) {
			throw new NoControlableException("Error al ejecutar la TRN. Mensaje de la excepción:"+ e.getMessage(), e);
		}
		
		try {
			if (verificaResponseBansefi(respuestaTRN) ) {
				responseBansefi = respuestaTRN.getResponseBansefi();
				
				lstBeanRespuesta = desempaquetarBeanDeRespuesta(responseBansefi);

			} else {
				LOGGER.debug("No hay información en la respuesta de la TRN.");
			}

		} catch (Exception e) {
			throw new NoControlableException(
					"Error al obtener la información que proviene de la TRN. Mensaje de la excepción:"
							+ e.getMessage(), e);
		}

		
		return lstBeanRespuesta;
	}
	
	private Ejecutar.ITRCONSUESTADOSACTRN.TRCONSUESTADOSACEVTY cargaDatosConsulta(AuditoriaBean bean){
		Ejecutar.ITRCONSUESTADOSACTRN.TRCONSUESTADOSACEVTY datosConsulta = null;
		Ejecutar.ITRCONSUESTADOSACTRN.TRCONSUESTADOSACEVTY.ACECVACP acecvacp = null;
		
		datosConsulta = new Ejecutar.ITRCONSUESTADOSACTRN.TRCONSUESTADOSACEVTY();
		acecvacp = new Ejecutar.ITRCONSUESTADOSACTRN.TRCONSUESTADOSACEVTY.ACECVACP();
		
		acecvacp.setCODNRBEEN(this.getEntidad());
		acecvacp.setNUMSECAC( Long.valueOf(bean.getCuenta()) );
		
		datosConsulta.setACECVACP(acecvacp);
		
		return datosConsulta;
	}
	
	private Ejecutar.ITRCONSUESTADOSACTRN.STDTRNIPARMV cargarPie(){
		Ejecutar.ITRCONSUESTADOSACTRN.STDTRNIPARMV pie = null;
		
		pie = new Ejecutar.ITRCONSUESTADOSACTRN.STDTRNIPARMV();
		pie.setCODTX(CodTxConstants.COD_TX_TR_CONSU_AUDIT_ECV_AC_TRN);
		pie.setIDINTERNOTERMTN(this.getTerminal());
		
		return pie;
	}
	
	private Boolean verificaResponseBansefi(EjecutarResult respuesta) {
		boolean traeRespuesta = false;

		traeRespuesta = (respuesta != null && 
				respuesta.getResponseBansefi() != null &&
				respuesta.getResponseBansefi().getOTRCONSUESTADOSACTRN()!=null &&
				Integer.valueOf(respuesta.getResponseBansefi().getOTRCONSUESTADOSACTRN().getNUMBEROFRECORDS())>0 &&
				Integer.valueOf(respuesta.getResponseBansefi().getOTRCONSUESTADOSACTRN().getRTRNCD())==BackEndBean.RETURN_COD_OK);

		return traeRespuesta;
	}

	private List<AuditoriaBean> desempaquetarBeanDeRespuesta(ResponseBansefi responseBansefi){
		List<AuditoriaBean> lst = null;
		AuditoriaBean bean = null;
		Integer numOfRecords = 0;
		
		numOfRecords = Integer.valueOf(responseBansefi.getOTRCONSUESTADOSACTRN().getNUMBEROFRECORDS());
		
		lst = new ArrayList<AuditoriaBean>(numOfRecords);
		for(int i=0 ; i<numOfRecords ; i++){
			bean = wrapper.wrappConsultaAuditoriaHistorico(responseBansefi.getOTRCONSUESTADOSACTRN().getTRCONSUESTADOSACEVTZ().getACECVACE().get(i));
			wrapper.cargaDatosEmpleadoPorEstado(bean, responseBansefi.getOTRCONSUESTADOSACTRN().getTRCONSUESTADOSACEVTZ().getACECVACV().get(i));
			this.obtenerDescripciones(bean);
			
			lst.add(bean);
		}

		return lst;
	}
	
	private void obtenerDescripciones(AuditoriaBean beanRespuesta){
		CatalogoBean c = null;
		
		// Se obtiene la descripción del estado de la cuenta.
		try {
			beanRespuesta.setCodigoEstadoAcuerdoStr(
					catalogoUtils.getCatalogoBean(CatalogoEnum.TP_ECV_AC, beanRespuesta.getCodigoEstadoAcuerdo()).getDescripcionL() );
		} catch (Exception e) {
			LOGGER.debug("Exception al obtener la descripción del estado de la cuenta ("
					+ beanRespuesta.getTransaccion() + "):" + e.getMessage());
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

		// Se obtienen y se cargan los datos de descripción 
		// - empleado origen, 
		// - el empleado autorizado 
		// además se carga el id del centro del acuerdo
		// - id del centro del acuerdo
		try {
			
			cargarAuditoriaBackend.ejecutarTRN(beanRespuesta);
			
		} catch (Exception e) {
			LOGGER.debug("Exception al obtener la descripción del empleado origen ("
					+ beanRespuesta.getEmpleadoOrigen() + "):" + e.getMessage());
		}
		
		// se obtiene descripción del centro Ac.
		try{
			c = catalogoCentrosLoaderTask.getCatalogoBean(beanRespuesta.getEntidad(), beanRespuesta.getCentroAc());
			beanRespuesta.setCentroAcDesc(c.getDescripcionL());
		} catch(Exception e){
			LOGGER.debug("Exception al obtener la descripción del centro Ac ("
					+ beanRespuesta.getCentroOperativo() + "):" + e.getMessage());
		}
		
		// se obtiene descripción del centro op.
		try{
			c = catalogoCentrosLoaderTask.getCatalogoBean(beanRespuesta.getEntidad(), beanRespuesta.getCentroOperativo());
			beanRespuesta.setCentroOperativoDesc(c.getDescripcionL());
		} catch(Exception e){
			LOGGER.debug("Exception al obtener la descripción del centro op ("
					+ beanRespuesta.getCentroOperativo() + "):" + e.getMessage());
		}

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
}
