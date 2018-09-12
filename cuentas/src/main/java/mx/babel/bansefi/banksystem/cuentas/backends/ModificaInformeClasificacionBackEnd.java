package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.beans.ClasificacionBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificarclasificacioncuenta.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificarclasificacioncuenta.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificarclasificacioncuenta.ModificarClasificacionCuenta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModificaInformeClasificacionBackEnd extends BackEndBean {

	private static final long serialVersionUID = 7076232795713337273L;

	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	public void ejecutarTRN(long numeroCuenta, ClasificacionBean clasificacion)
		throws NoControlableException, ControlableException{
		Ejecutar.ITRALTADATOSCLACTRN contexto = initPeticion(numeroCuenta, clasificacion);
		EjecutarResult respuesta = ejecutarWS(contexto);		
		super.verificaRespuesta(respuesta);				
	}
		
	public Ejecutar.ITRALTADATOSCLACTRN initPeticion(long numeroCuenta, ClasificacionBean clasificacion){
		Ejecutar.ITRALTADATOSCLACTRN contexto = new Ejecutar.ITRALTADATOSCLACTRN();
		Ejecutar.ITRALTADATOSCLACTRN.STDTRNIPARMV contextoEntrada = new Ejecutar.ITRALTADATOSCLACTRN.STDTRNIPARMV();
		Ejecutar.ITRALTADATOSCLACTRN.TRALTADATOSCLACEVTY altaClasificacion = new Ejecutar.ITRALTADATOSCLACTRN.TRALTADATOSCLACEVTY();
		Ejecutar.ITRALTADATOSCLACTRN.TRALTADATOSCLACEVTY.ACACP datosEntrada = new Ejecutar.ITRALTADATOSCLACTRN.TRALTADATOSCLACEVTY.ACACP();
		Ejecutar.ITRALTADATOSCLACTRN.TRALTADATOSCLACEVTY.ACINFCONTABV infoContable = new Ejecutar.ITRALTADATOSCLACTRN.TRALTADATOSCLACEVTY.ACINFCONTABV();
		
		super.initialize(contexto);
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TR_ALTA_DATOS_CL_AC_TRN);
		contextoEntrada.setIDINTERNOTERMTN(this.getTerminal());
		
		datosEntrada.setCODNRBEEN(this.getEntidad());
		datosEntrada.setNUMSECAC(numeroCuenta);
		
		infoContable.setCODCNAEPERS(clasificacion.getCnae());
		infoContable.setCODFNDADAC(clasificacion.getFinalidad());
		infoContable.setINDFNDADCONSUMO("");
		
		altaClasificacion.setACACP(datosEntrada);
		altaClasificacion.setACINFCONTABV(infoContable);
		
		contexto.setSTDTRNIPARMV(contextoEntrada);
		contexto.setTRALTADATOSCLACEVTY(altaClasificacion);
		
		super.initialize(contexto);
		return contexto;
	}
	
	public EjecutarResult ejecutarWS(Ejecutar.ITRALTADATOSCLACTRN peticion){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ModificarClasificacionCuenta.class, peticion);
			
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de alta "
					+ "informe de Clasificación",e);
		}
		return respuesta;
	}
	
	public Boolean verificaResponseBansefi(EjecutarResult respuesta){
		Boolean noNulo = false;
		if(respuesta != null && respuesta.getResponseBansefi() != null){
			noNulo = true;
		}
		return noNulo;
	}

}
