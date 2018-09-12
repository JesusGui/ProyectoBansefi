package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.CatalogoCentrosLoaderTask;
import mx.babel.bansefi.banksystem.base.utils.TipoDomicilioUtils;
import mx.babel.bansefi.banksystem.cuentas.beans.DomicilioCuentaBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadomicilioscuenta.ConsultaDomiciliosCuentaServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadomicilioscuenta.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadomicilioscuenta.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadomicilioscuenta.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.DomicilioCuentaWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaDomiciliosCuentaBackEnd extends BackEndBean{

	private static final long serialVersionUID = 1974605488919810868L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	@Autowired
	DomicilioCuentaWrapper domicilioCuentaWrapper;
	
	@Autowired
	CatalogoCentrosLoaderTask catalogoCentros;
	
	@Autowired
	TipoDomicilioUtils tipoDomicilioUtils;
	
	public List<DomicilioCuentaBean> ejecutarTRN(Long numeroCuenta){
		
		Ejecutar.ITRCONSUDIRACTRNI contexto = initPeticion(numeroCuenta);
		
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch(ControlableException ce){
			if(ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return new ArrayList<DomicilioCuentaBean>();
			}			
		}
				
		return getRespuesta(respuesta.getResponseBansefi());
	}
	
	private List<DomicilioCuentaBean> getRespuesta(ResponseBansefi respuesta){
		List<DomicilioCuentaBean> domicilios = new ArrayList<DomicilioCuentaBean>();
		if(respuesta.getOTRCONSUDIRACTRNO() != null){
			for(int i = 0; i < respuesta.getOTRCONSUDIRACTRNO().getNUMBEROFRECORDS(); i++){
				DomicilioCuentaBean domicilio = domicilioCuentaWrapper.wrappBean(respuesta.getOTRCONSUDIRACTRNO().getTRCONSUDIRACEVTZ().getDATOSDIRPERV().get(i));
				
				if(!"".equals(domicilio.getCentro()) && domicilio.getCentro() != null){
					domicilio.setCatalogoCentro(catalogoCentros.getCatalogoBean(super.getEntidad(), domicilio.getCentro()));
					
				}
				if(!"".equals(domicilio.getCodPersDir().trim())){
					domicilio.getTipoDomicilio().add(tipoDomicilioUtils.getTipoDomicilio(domicilio.getCodPersDir().trim()));	
				}
				if(domicilio.getFechaInicio() == null){
					domicilio.setFechaInicio(super.getFechaSistema());
					domicilio.setInicial(true);
				}else{
					domicilio.setInicial(false);
				}
				
				domicilio.setActivo(true);
				domicilio.setMasDatos(false);
				domicilio.setNuevo(false);
				domicilios.add(domicilio);
			}
		}
		return domicilios;
	}
	
	private Ejecutar.ITRCONSUDIRACTRNI initPeticion(Long numeroCuenta){
		Ejecutar.ITRCONSUDIRACTRNI contexto = new Ejecutar.ITRCONSUDIRACTRNI();
		Ejecutar.ITRCONSUDIRACTRNI.STDTRNIPARMV cuerpoContexto = 
				new Ejecutar.ITRCONSUDIRACTRNI.STDTRNIPARMV();
		Ejecutar.ITRCONSUDIRACTRNI.TRCONSUDIRACEVTY objetoPeticion =
				new Ejecutar.ITRCONSUDIRACTRNI.TRCONSUDIRACEVTY();
		Ejecutar.ITRCONSUDIRACTRNI.TRCONSUDIRACEVTY.ACACRLDIRPERP cuenta =
				new Ejecutar.ITRCONSUDIRACTRNI.TRCONSUDIRACEVTY.ACACRLDIRPERP();
		contexto.setSTDTRNIPARMV(cuerpoContexto);
		contexto.setTRCONSUDIRACEVTY(objetoPeticion);
		objetoPeticion.setACACRLDIRPERP(cuenta);
		super.initialize(contexto);
		
		cuerpoContexto.setCODTX(CodTxConstants.COD_TX_TR_CONSU_DIR_AC_TRN);
		cuerpoContexto.setIDINTERNOTERMTN(super.getTerminal());
		
		cuenta.setCODNRBEEN(super.getEntidad());
		cuenta.setNUMSECAC(numeroCuenta);
		
		contexto.setELEVATORPOSITION(1);
		contexto.setSCROLLABLEOCCURS(50);

		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRCONSUDIRACTRNI peticion){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaDomiciliosCuentaServicio.class, peticion);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "domicilios de cuenta.", e);
		}
		return respuesta;
	}	
}
