package mx.babel.bansefi.banksystem.cuentas.backends;

import java.io.Serializable;

import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.AuditoriaBean;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.cargaauditoria.CargaAuditoriaServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.cargaauditoria.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.cargaauditoria.EjecutarResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CargarAuditoriaBackend extends BackEndBean implements Serializable {

	private static final long serialVersionUID = -6281144509912549141L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	private static final Logger LOGGER = LogManager.getLogger(CargarAuditoriaBackend.class);
			
	public void ejecutarTRN(AuditoriaBean auditoria) {
		Ejecutar.ITRCARGARAUDITTRNI contexto = null;
		EjecutarResult respuesta = null;

		contexto = initPeticion(auditoria);
		respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(CargaAuditoriaServicio.class, contexto);

		cargarDatos(auditoria, respuesta);
		
	}
	
	private Ejecutar.ITRCARGARAUDITTRNI initPeticion(AuditoriaBean auditoria) {
		Ejecutar.ITRCARGARAUDITTRNI contexto = new Ejecutar.ITRCARGARAUDITTRNI();
		Ejecutar.ITRCARGARAUDITTRNI.STDTRNIPARMV contextoEntrada = new Ejecutar.ITRCARGARAUDITTRNI.STDTRNIPARMV();
		Ejecutar.ITRCARGARAUDITTRNI.TRCARGARAUDITEVTY cuerpoContexto = new Ejecutar.ITRCARGARAUDITTRNI.TRCARGARAUDITEVTY();

		contextoEntrada.setCODTX(auditoria.getTransaccion());
		contextoEntrada.setIDINTERNOTERMTN(this.getTerminal());

		Ejecutar.ITRCARGARAUDITTRNI.TRCARGARAUDITEVTY.EMPLEADOSAUDITV empleadosAudit = new Ejecutar.ITRCARGARAUDITTRNI.TRCARGARAUDITEVTY.EMPLEADOSAUDITV();
		empleadosAudit.setIDINTERNOEMPLEP(auditoria.getEmpleadoOrigen());
		empleadosAudit.setIDEMPLAUT(auditoria.getEmpleadoAutorizado());
		empleadosAudit.setCODNRBEEN(auditoria.getEntidad());

		cuerpoContexto.setEMPLEADOSAUDITV(empleadosAudit);

		Ejecutar.ITRCARGARAUDITTRNI.TRCARGARAUDITEVTY.CARGARAUDITACV cargaAudit = new Ejecutar.ITRCARGARAUDITTRNI.TRCARGARAUDITEVTY.CARGARAUDITACV();
		cargaAudit.setCODNRBEEN(auditoria.getEntidad());
		cargaAudit.setNUMSECAC( Long.valueOf(auditoria.getCuenta()) );
		cuerpoContexto.setCARGARAUDITACV(cargaAudit);

		Ejecutar.ITRCARGARAUDITTRNI.TRCARGARAUDITEVTY.FECHACTBLEV fechaContable = new Ejecutar.ITRCARGARAUDITTRNI.TRCARGARAUDITEVTY.FECHACTBLEV();
		IntegerToDateConverter converter = new IntegerToDateConverter();
		fechaContable.setFECHACTBLE(converter.convertFrom(auditoria
				.getFechaContable()));

		contexto.setSTDTRNIPARMV(contextoEntrada);
		contexto.setTRCARGARAUDITEVTY(cuerpoContexto);
		//super.initialize(contexto);

		return contexto;
	}
	
	private void cargarDatos(AuditoriaBean auditoria, EjecutarResult respuesta){
		if(respuesta!=null && respuesta.getResponseBansefi()!=null && respuesta.getResponseBansefi().getOTRCARGARAUDITTRNO()!=null ){
			if(respuesta.getResponseBansefi().getOTRCARGARAUDITTRNO().getRTRNCD() == RETURN_COD_OK){
				auditoria.setEmpleadoOrigenDesc( respuesta.getResponseBansefi().getOTRCARGARAUDITTRNO().getTRCARGARAUDITEVTZ().getNOMBEMPLORIG().getNOMB50() );
				auditoria.setEmpleadoAutorizadoDesc( respuesta.getResponseBansefi().getOTRCARGARAUDITTRNO().getTRCARGARAUDITEVTZ().getNOMBEMPLAUT().getNOMB50() );
				auditoria.setCentroAc(respuesta.getResponseBansefi().getOTRCARGARAUDITTRNO().getTRCARGARAUDITEVTZ().getCODINTERNOUOACV().getCODINTERNOUO());
			}
		}else{
			LOGGER.error("Error al cargar la respuesta de la TRN viene vacía.");
		}
		
	}
	
}
